package fr.mds.goodfellow.myrequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyRequest {

    private Context context;
    private RequestQueue requestQueue;

    public MyRequest(Context context, RequestQueue requestQueue) {
        this.context = context;
        this.requestQueue = requestQueue;
    }

    public void inscription(final String pseudo, final String email, final String password, final String password2, final InscriptionCallback callback){
        String url = "http://192.168.0.33/goodfellow/inscription.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean erreur = jsonObject.getBoolean("erreur");
                    Log.d("APP", response);
                    if(!erreur){
                        callback.onSuccess("Inscription réussit !");
                    }else{
                        JSONObject messages = jsonObject.getJSONObject("message");
                        if(messages.has("pseudo")){
                            errors.put("pseudo", messages.getString("pseudo"));
                        }
                        if(messages.has("email")){
                            errors.put("email", messages.getString("email"));
                        }
                        if(messages.has("password")){
                            errors.put("password", messages.getString("password"));
                        }
                        callback.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
                Log.d("APP", "Error = "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("pseudo", pseudo);
                map.put("email", email);
                map.put("password", password);
                map.put("password2", password2);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public interface InscriptionCallback{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);
        void onError(String message);
    }

    public void connection(final String pseudo, final String password, final ConnexionCallback callback){
        String url = "http://192.168.0.33/goodfellow/connexion.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Boolean erreur = jsonObject.getBoolean("erreur");
                    Log.d("APP", response);

                    if(!erreur){
                        String id = jsonObject.getString("id");
                        String pseudo = jsonObject.getString("pseudo");
                        callback.onSuccess(id, pseudo);
                    }else {
                        callback.onError(jsonObject.getString("message"));
                    }
                } catch (JSONException e) {
                    callback.onError("Une erreur s'est produit");
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("Impossible de se connecter");
                }else if(error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
                Log.d("APP", "Error = "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("pseudo", pseudo);
                map.put("password", password);

                return map;
            }
        };

        requestQueue.add(stringRequest);

    }

    public interface ConnexionCallback{
        void onSuccess(String id, String pseudo);
        void onError(String message);
    }
}
