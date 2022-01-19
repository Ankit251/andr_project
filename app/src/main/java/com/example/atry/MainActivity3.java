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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity3 extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView firstname, lastname, agee, username;
    String firstName,lastName,age,userName,imge;
    FirebaseDatabase db;
    FirebaseStorage storage;
    DatabaseReference reference;
    Button btn;
    Button btnn;
    ImageView img;
    ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        agee = findViewById(R.id.age);
        username = findViewById(R.id.userName);
        btn = findViewById(R.id.registerBtn);
        btnn = findViewById(R.id.button2);
        img = findViewById(R.id.imageView2);
        db = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        img.setImageURI(result);

                        final StorageReference reference = storage.getReference().child("image");
                        reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                imge = uri.toString();
                                            }
                                        });
                                    }
                                });
//                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                    @Override
//                                    public void onSuccess(Uri uri) {
//                                        db.getReference().child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(MainActivity3.this,"Image uploaded successfully",Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                });
                            }
                        });
                    }
                });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 firstName = firstname.getText().toString();
                 lastName = lastname.getText().toString();
                 age = agee.getText().toString();
                 userName = username.getText().toString();


                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Users users = new Users(firstName,lastName,age,userName,imge);

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

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });
    }
}