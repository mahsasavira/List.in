package mahsa.example.listin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mahsa.example.listin.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginbaru = findViewById(R.id.loginbaruu);
        Button daftar = findViewById(R.id.daftar);

        loginbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        final EditText txtUserName = (EditText)findViewById(R.id.username_baru);
        final EditText txtPassword = (EditText)findViewById(R.id.password_baru);
        final EditText txtEmail = (EditText)findViewById(R.id.email_baru);
        daftar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = txtUserName.getText().toString();
                        String password = txtPassword.getText().toString();
                        String email = txtEmail.getText().toString();
                        try {
                            DBUserAdapter dbaUser = new DBUserAdapter(RegisterActivity.this);
                            dbaUser.open();
                            if(dbaUser.Register(username, password, email)){
                                Toast.makeText(RegisterActivity.this,"Berhasil Daftar", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this,"Ga galDaftar", Toast.LENGTH_LONG).show();
                            }
                            dbaUser.close();
                        } catch (Exception e)
                        { // TODO: handle exception
                            Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
