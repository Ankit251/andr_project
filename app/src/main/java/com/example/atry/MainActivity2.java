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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity2 extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference reference;
    Button btn;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main2);
        user = findViewById(R.id.data);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();

                if(!username.isEmpty()){
                    readData(username);
                }
                else{
                    Toast.makeText(MainActivity2.this,"Please Enter user name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void readData(String username){
        reference = FirebaseDatabase.getInstance().getReference("Employee");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(MainActivity2.this,"Successfully read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot =task.getResult();
                        String Nm = String.valueOf(dataSnapshot.child("name").getValue());
                        String posi = String.valueOf(dataSnapshot.child("position").getValue());
                        TextView name =findViewById(R.id.name);
                        name.setText(Nm);
                        TextView pos =findViewById(R.id.Position);

                        pos.setText(posi);
                    }else{
                        Toast.makeText(MainActivity2.this,"User doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity2.this,"Failed to retrieve data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}