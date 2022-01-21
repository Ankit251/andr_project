package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atry.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity2 extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference reference;
    Button btn;
    TextView user;
    FirebaseDatabase db;
    EditText txt1,txt2,txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main2);

        btn = findViewById(R.id.btn);
        user = findViewById(R.id.tvSignIn);
        txt1 = findViewById(R.id.ug);
        txt2 = findViewById(R.id.eml);
        txt3 = findViewById(R.id.pwr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Logi.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt1.getText().toString();
                String email = txt2.getText().toString();
                String password = txt3.getText().toString();

                if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    Users users = new Users(username,email,password);

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("usrs");


                    reference.child(username).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            txt1.setText("");
                            txt2.setText("");
                            txt3.setText("");
                            Toast.makeText(MainActivity2.this,"Successfuly Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }


}