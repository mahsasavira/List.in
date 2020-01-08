package mahsa.example.listin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import mahsa.example.listin.ui.login.LoginActivity;


public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        @SuppressLint("WrongConstant")
        SharedPreferences sh = getSharedPreferences("UserData", MODE_APPEND);
        final String username = sh.getString("username", null);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    if (username != null) {
                        finish();
                        Intent m = new Intent(Splashscreen.this, MainActivity.class);
                        startActivity(m);
                    } else {
                        finish();
                        Intent m = new Intent(Splashscreen.this, LoginActivity.class);
                        startActivity(m);
                    }
                }
            }
        };
        timer.start();
    }
}
