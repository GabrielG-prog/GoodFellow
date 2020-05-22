package fr.mds.goodfellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import fr.mds.goodfellow.session.SessionManager;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView tv_pseudo;
    private ImageButton ib_profil, ib_group, ib_calendar, ib_score, ib_tchat;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_pseudo = findViewById(R.id.tv_pseudo);

        ib_profil = findViewById(R.id.ib_profil);
        ib_group = findViewById(R.id.ib_group);
        ib_calendar = findViewById(R.id.ib_calendar);
        ib_score = findViewById(R.id.ib_score);
        ib_tchat = findViewById(R.id.ib_tchat);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLogged()){
            String pseudo = sessionManager.getPseudo();
            String id = sessionManager.getId();
            tv_pseudo.setText(pseudo);
        }

        ib_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GroupActivity.class);
                startActivity(intent);
            }
        });

        ib_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        ib_tchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TchatActivity.class);
                startActivity(intent);
            }
        });
        ib_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

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
