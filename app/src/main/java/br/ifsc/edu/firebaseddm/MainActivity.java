package br.ifsc.edu.firebaseddm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edlogin, edsenha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //Acessando realtime
        //firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = firebaseDatabase.getReference();
        //Pessoa p = new Pessoa("Isabela Pereira","999.999.999-99", "Feminino");
        //databaseReference.child("pessoas").push().setValue(p);
        //p = new Pessoa("Lucas Pedroso","999.999.999-99", "Maculino");
        //databaseReference.child("pessoas").push().setValue(p);
        //p = new Pessoa("Natalia Maria","999.999.999-99", "Feminino");
        //databaseReference.child("pessoas").push().setValue(p);

        //databaseReference.child("pessoa").addValueEventListener(new ValueEventListener() {
          //  @Override
            //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  Pessoa p = dataSnapshot.getValue(Pessoa.class);
                //Log.d("DatabasePessoa", p.nome);
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {


            //}
       // });

        edlogin = findViewById(R.id.edLogin);
        edsenha = findViewById(R.id.edSenha);

    }

    public void autenticar(View view) {

        mAuth.signInWithEmailAndPassword(
                edlogin.getText().toString(),
                edsenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login Realizado com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha de Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cadastrar(View view) {
        mAuth.createUserWithEmailAndPassword(
                edlogin.getText().toString(),
                edsenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro Concluido com Sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao criar o Cadastro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void resetarSenha(View view) {
        if (!edlogin.getText().toString().trim().equals(""))
            mAuth.sendPasswordResetEmail(edlogin.getText().toString());
    }
}
