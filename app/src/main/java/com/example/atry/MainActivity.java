package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iammert.library.readablebottombar.ReadableBottomBar;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar rvb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvb = findViewById(R.id.rbb);

        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_position = findViewById(R.id.edit_position);
        Button btn = findViewById(R.id.btn_submit);
        DAOEmployee dao = new DAOEmployee();
        btn.setOnClickListener(v->{
            Employee emp = new Employee(edit_name.getText().toString(),edit_position.getText().toString());
            dao.add(emp).addOnSuccessListener(suc->{
                //Toast.makeText(this,"Record Inserted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Fetch.class);
                startActivity(intent);
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });

        rvb.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case 0:
                        Toast.makeText(MainActivity.this,"Home selected",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:

                        Toast.makeText(MainActivity.this,"Search selected",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Showcart.class));
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"Scanner selected",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Fetch.class));
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"Profile selected",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), scanner.class));
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this,"Account",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        break;
                }
            }
        });
    }
}