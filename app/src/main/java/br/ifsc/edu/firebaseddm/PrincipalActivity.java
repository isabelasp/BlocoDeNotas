package br.ifsc.edu.firebaseddm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class   PrincipalActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Log.d("FirebaseAuth", mUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não cadastraado");
            finish();
        }
    }
}