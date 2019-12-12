package br.ifsc.edu.firebaseddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Notas {
    SQLiteDatabase bd;
    Context contexto;
    static String NOME_TABELA = "blocodenotas";
    static final String SCRIPT_CRIACAO_TABELA_BLOCO = "CREATE TABLE IF NOT EXISTS blocodenotas "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nota TEXT NOT NULL, "
            + "dtCriacao NUMERIC,"
            + "dtAlteracao NUMERIC);";

    public Notas(Context c) {


    }

    public List<String> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = bd.rawQuery(queryReturnAll, null);
        List<Nota> notas = construirNotaPorCursor(cursor);
        return convertEntitiesForString(notas);
    }

    private List<String> convertEntitiesForString(List<Nota> notas) {
        List<String> stringNotas = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Nota n : notas) {
            String dtCriacao = dateFormat.format(n.getDataCriacao());
            String dtModificacao = dateFormat.format(n.getDataModificacao());
            stringNotas.add(n.getNome() + "\n CRIAÇÃO: " + dtCriacao + "\n ALTERAÇÃO: " + dtModificacao);
        }
        return stringNotas;
    }

    public List<Nota> recuperarTodasNotas() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = bd.rawQuery(queryReturnAll, null);
        List<Nota> notas = construirNotaPorCursor(cursor);
        return notas;
    }

    private List<Nota> construirNotaPorCursor(Cursor cursor) {
        List<Nota> notas = new ArrayList<Nota>();
        if (cursor == null)
            return notas;

        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex("_id");
                    int indexTexto = cursor.getColumnIndex("texto");
                    int indexDataCriacao = cursor.getColumnIndex("dataCriacao");
                    int indexDataAlteracao = cursor.getColumnIndex("dataAlteracao");

                    int id = cursor.getInt(indexID);
                    String texto = cursor.getString(indexTexto);
                    Long dataCriacao = cursor.getLong(indexDataCriacao);
                    Long dataAlteracao = cursor.getLong(indexDataAlteracao);

                    Nota veiculo = new Nota(id, texto, dataCriacao, dataAlteracao);
                    notas.add(veiculo);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return notas;
    }


    public void salvarNota(String texto) {
        if (texto.trim().equals("")) {
            Toast.makeText(this.contexto, "Nada", Toast.LENGTH_LONG).show();
        } else {
            ContentValues registro = new ContentValues();
            registro.put("texto", texto);
            registro.put("dataCriacao", System.currentTimeMillis());
            registro.put("dataAlteracao", System.currentTimeMillis());
            bd.insert("bloconotas", null, registro);
        }
    }

    public void editarNota(Nota nota) {
        ContentValues valores = gerarContentValeuesNota(nota);
        String[] valoresParaSubstituir = {
                String.valueOf(nota.getId())
        };

        bd.update(NOME_TABELA, valores, "_id" + " = ?", valoresParaSubstituir);
    }

    private ContentValues gerarContentValeuesNota(Nota nota) {
        ContentValues values = new ContentValues();
        values.put("texto", nota.getNome());
        values.put("dataAlteracao", System.currentTimeMillis());

        return values;
    }

    public List<String> recuperarTodosOrderByCriacao() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " order by dataCriacao";
        Cursor cursor = bd.rawQuery(queryReturnAll, null);
        List<Nota> notas = construirNotaPorCursor(cursor);
        return convertEntitiesForString(notas);
    }

    public List<String> recuperarTodosOrderByAlteracao() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " order by dataAlteracao";
        Cursor cursor = bd.rawQuery(queryReturnAll, null);
        List<Nota> notas = construirNotaPorCursor(cursor);
        return convertEntitiesForString(notas);
    }
}
