package fr.mds.goodfellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import fr.mds.goodfellow.Volley.VolleySingleton;
import fr.mds.goodfellow.myrequest.MyRequest;
import fr.mds.goodfellow.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button bt_inscription, bt_connexion;
    private EditText et_identifiant, et_motdepasse;
    private RequestQueue requestQueue;
    private MyRequest myRequest;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_inscription = findViewById(R.id.bt_inscription);
        bt_connexion = findViewById(R.id.bt_connexion);

        et_identifiant = findViewById(R.id.et_identifiant);
        et_motdepasse = findViewById(R.id.et_motdepasse);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        myRequest = new MyRequest(this, requestQueue);

        sessionManager = new SessionManager(this);

        bt_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("Inscription")){
            Toast.makeText(this, intent.getStringExtra("Inscription"), Toast.LENGTH_SHORT).show();
        }

        bt_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identifiant = et_identifiant.getText().toString().trim();
                String motdepasse = et_motdepasse.getText().toString().trim();
                if(identifiant.length() > 0 && motdepasse.length() > 0){
                    myRequest.connection(identifiant, motdepasse, new MyRequest.ConnexionCallback() {
                        @Override
                        public void onSuccess(String id, String pseudo) {
                            sessionManager.insertUser(id, pseudo);
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
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
