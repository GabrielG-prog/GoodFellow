package fr.mds.goodfellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.mds.goodfellow.session.SessionManager;

public class CalendarActivity extends AppCompatActivity {

    private Button bt_calendarRetour;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        sessionManager = new SessionManager(this);

        bt_calendarRetour = findViewById(R.id.bt_calendarRetour);

        bt_calendarRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
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
