package com.example.atry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atry.databinding.ActivityMainBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.iammert.library.readablebottombar.ReadableBottomBar;

public class scanner extends AppCompatActivity {

    Button scanbtn;
     ReadableBottomBar rvb;
    ActivityMainBinding binding;
    public static TextView scantext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_scanner);

        rvb = findViewById(R.id.rbb);

        rvb.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case 0:
                        Toast.makeText(scanner.this,"Home selected",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:

                        Toast.makeText(scanner.this,"Search selected",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(scanner.this,"Scanner selected",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity3.class));
                        break;
                    case 3:
                        Toast.makeText(scanner.this,"Profile selected",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Fetch.class));
                        break;
                    case 4:
                        Toast.makeText(scanner.this,"Account",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        break;
                }
            }
        });
        scantext=(TextView)findViewById(R.id.scantext);
        scanbtn=(Button) findViewById(R.id.scanbtn);


        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),scannerView.class));
            }
        });


    }
}