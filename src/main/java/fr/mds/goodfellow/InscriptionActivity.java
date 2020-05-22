package fr.mds.goodfellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import fr.mds.goodfellow.Volley.VolleySingleton;
import fr.mds.goodfellow.myrequest.MyRequest;

public class InscriptionActivity extends AppCompatActivity {

    private Button bt_inscription2, bt_connexion2;
    private EditText et_pseudo, et_email, et_password, et_password2;
    private RequestQueue requestQueue;
    private MyRequest myRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        bt_inscription2 = findViewById(R.id.bt_inscription2);
        bt_connexion2 = findViewById(R.id.bt_connexion2);
        et_pseudo = findViewById(R.id.et_pseudo);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        myRequest = new MyRequest(this, requestQueue);

        bt_connexion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_inscription2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = et_pseudo.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String password2 = et_password2.getText().toString().trim();
                if(pseudo.length() > 0 && email.length() > 0 && password.length() > 0 && password2.length() > 0){
                    myRequest.inscription(pseudo, email, password, password2, new MyRequest.InscriptionCallback() {
                        @Override
                        public void onSuccess(String message) {
                            Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                            intent.putExtra("Inscription", message);
                            startActivity(intent);
                        }

                        @Override
                        public void inputErrors(Map<String, String> errors) {
                            if(errors.get("pseudo") != null){
                                et_pseudo.setError(errors.get("pseudo"));
                            }

                            if(errors.get("email") != null){
                                et_pseudo.setError(errors.get("email"));
                            }

                            if(errors.get("password") != null){
                                et_pseudo.setError(errors.get("password"));
                            }

                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
