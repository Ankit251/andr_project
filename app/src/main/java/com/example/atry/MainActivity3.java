package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atry.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView firstname, lastname, agee, username;
    String firstName,lastName,age,userName;
    FirebaseDatabase db;
    DatabaseReference reference;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        agee = findViewById(R.id.age);
        username = findViewById(R.id.userName);
        btn = findViewById(R.id.registerBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 firstName = firstname.getText().toString();
                 lastName = lastname.getText().toString();
                 age = agee.getText().toString();
                 userName = username.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Users users = new Users(firstName,lastName,age,userName);

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");

                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            firstname.setText("");
                            lastname.setText("");
                            agee.setText("");
                            username.setText("");
                            Toast.makeText(MainActivity3.this,"Successfuly Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }
}