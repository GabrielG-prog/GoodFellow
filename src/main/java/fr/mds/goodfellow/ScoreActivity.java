package fr.mds.goodfellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fr.mds.goodfellow.session.SessionManager;

public class ScoreActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        sessionManager = new SessionManager(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_compte:
                Toast.makeText(this, "WAOOO", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_options:
                Toast.makeText(this, "taooooo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_support:
                Toast.makeText(this, "KAKASSWKAA", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_decon:
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
