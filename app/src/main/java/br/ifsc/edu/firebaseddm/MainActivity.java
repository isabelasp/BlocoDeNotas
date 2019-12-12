package br.ifsc.edu.firebaseddm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    Notas notas;
    ListView listView;
    List<Nota> listNotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mAuth = FirebaseAuth.getInstance();
        notas = new Notas(this);
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, AdicaoNotaActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.listNotas);
        final List<String> listNotasString = notas.recuperarTodos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listNotasString);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                listNotas = notas.recuperarTodasNotas();
                Nota notaSelecionada = listNotas.get(position);
                if (Objects.nonNull(notaSelecionada)) {
                    Intent it = new Intent(MainActivity.this, AdicaoNotaActivity.class);
                    Bundle b = new Bundle();
                    b.putString("texto", notaSelecionada.getNome());
                    b.putInt("id", notaSelecionada.getId());
                    it.putExtras(b);
                    startActivity(it);
                } else {
                    Toast.makeText(view.getContext(), "ERRO AO CARREGAR DADOS DA NOTA!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ordenarPorCriacao(View view) {
        final List<String> listNotasString = notas.recuperarTodosOrderByCriacao();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listNotasString);
        listView.setAdapter(adapter);
    }

    public void ordenarPorAlteracao(View view) {
        final List<String> listNotasString = notas.recuperarTodosOrderByAlteracao();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listNotasString);
        listView.setAdapter(adapter);
    }

}
