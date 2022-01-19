package com.example.atry;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atry.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Fetch extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView firstname, lastname, agee, username, pi;
    String firstName,lastName,age,userName,pd,imagg;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseStorage storage;
    Button btn,btn2;
    ImageView img;
    ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        agee = findViewById(R.id.age);
        username = findViewById(R.id.userName);
        pi = findViewById(R.id.pid);
        img = findViewById(R.id.imageView3);
        btn = findViewById(R.id.registerBtn);
        storage = FirebaseStorage.getInstance();
        btn2 = findViewById(R.id.button3);
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        img.setImageURI(result);
                        final StorageReference reference = storage.getReference().child("image");
                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        imagg = uri.toString();
                                    }
                                });
                            }
                        });
                    }
                });
//        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(),
//                new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri result) {
//                        img.setImageURI(result);
//                        final StorageReference reference = storage.getReference().child("image");
//                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                btn.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                    }
//                                });
//                            }
//                        });
//                    }
//                });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstname.getText().toString();
                lastName = lastname.getText().toString();
                age = agee.getText().toString();
                userName = username.getText().toString();
                pd = pi.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Items users = new Items(firstName,lastName,age,userName,pd,imagg);

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Items");

                    reference.child(pd).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            firstname.setText("");
                            lastname.setText("");
                            agee.setText("");
                            username.setText("");
                            Toast.makeText(Fetch.this,"Successfuly Updated", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });


    }

}