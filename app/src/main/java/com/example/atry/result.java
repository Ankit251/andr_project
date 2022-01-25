package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class result extends AppCompatActivity {

    TextView res;
    DatabaseReference reference,referencee;
    Button btn,btn2;
    EditText nm,of,or,de;
    ImageView img,img2;
    FirebaseDatabase db;
    Uri imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        res = findViewById(R.id.rst);
        nm = findViewById(R.id.pname);
        of = findViewById(R.id.offerprice);
        or=findViewById(R.id.originalprice);
        de = findViewById(R.id.description);
        btn2 = findViewById(R.id.cart);
        Intent intent = getIntent();
        String str = intent.getStringExtra("qrr");
        img =  findViewById(R.id.imageView4);
        res.setText(str);
        img2 = findViewById(R.id.dbimg);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
            }
        });



        btn = (Button) findViewById(R.id.sd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dt = res.getText().toString();

                if(!dt.isEmpty()){
                    readData(dt);
                }else {
                    Toast.makeText(result.this,"unable to fetch data",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





    public void readData(String dt){
        reference = FirebaseDatabase.getInstance().getReference("Items");
        reference.child(dt).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(result.this,"Fetched successfully",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String pn = String.valueOf(dataSnapshot.child("firstName").getValue());
                        String ofp = String.valueOf(dataSnapshot.child("lastName").getValue());
                        String im = String.valueOf(dataSnapshot.child("img").getValue());
                        String orp = String.valueOf(dataSnapshot.child("age").getValue());
                        String dep = String.valueOf(dataSnapshot.child("userName").getValue());
                        imm = Uri.parse(im);
                        nm.setText(pn);
                        of.setText(ofp);
                        or.setText(orp);
                        de.setText(dep);

                        Picasso.get().load(im).into(img);


                    }else{
                        Toast.makeText(result.this,"Data doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(result.this,"Failed to fetch data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addtocart(){
        String ofpp = of.getText().toString();
        String nmpp = nm.getText().toString();
        String imag = imm.toString();

        if(!ofpp.isEmpty() && !nmpp.isEmpty()){

            Cart cart = new Cart(ofpp,nmpp,imag);

            db = FirebaseDatabase.getInstance();
            referencee = db.getReference("CART");

            referencee.child(nmpp).setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    Toast.makeText(result.this,"Successfuly Updated", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}