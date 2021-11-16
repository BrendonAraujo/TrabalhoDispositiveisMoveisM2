package br.com.dlweb.maternidade.consulta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.consulta.R;
import br.com.dlweb.consulta.database.SQLiteHelper;

public class ConsultaDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private static final String table = "paciente";

    public ConsultaDAO(Context ctx){
        StringBuilder sqlCreate = new StringBuilder();
        sqlCreate.append("CREATE TABLE ");
        sqlCreate.append(table);
        sqlCreate.append("(");
        sqlCreate.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCreate.apped("paciente_id INT(11)");
        sqlCreate.apped("medico_id INT(11)");
        sqlCreate.apped("data_hora_inicio DATETIME");
        sqlCreate.apped("data_hora_fim DATETIME");
        sqlCreate.apped("observacao VARCHAR(200)")
        sqlCreate.append(");");
        StringBuilder sqlDelete = new StringBuilder();
        sqlDelete.append("DROP TABLE IF NOT EXISTS ");
        sqlDelete.append(table);
        dbHelper = new SQLiteHelper(ctx, ctx.getResources().getString(R.string.database_name), 1, sqlCreate.toString(), sqlDelete.toString());
    }

    public long add(Consulta c) {
        ContentValues cv = new ContentValues();
        cv.put("pacient_id", c.getPaciente_id());
        cv.put("medico_id", c.getMedico_id());
        cv.put("data_hora_inicio",c.getData_hora_inicio);
        cv.put("data_hora_fim",c.getData_hora_fim);
        cv.put("observacao",c.getObservacao);
        db = dbHelper.getWritableDatabase();
        long id = db.insert(table, null, cv);
        db.close();
        return id;
    }

    public long edit(Consulta c) {
        ContentValues cv = new ContentValues();
        cv.put("pacient_id", c.getPaciente_id());
        cv.put("medico_id", c.getMedico_id());
        cv.put("data_hora_inicio",c.getData_hora_inicio);
        cv.put("data_hora_fim",c.getData_hora_fim);
        cv.put("observacao",c.getObservacao);
        db = dbHelper.getWritableDatabase();
        long rows = db.update(table, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public long delete(Consulta c) {
        db = dbHelper.getWritableDatabase();
        long rows = db.delete(table, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public void readAll (Context context, ListView lv) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "pacient_id","medico_id", "data_hora_inicio","data_hora_fim","observacao"};
        Cursor data = db.query(table, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarConsulta, R.id.textViewNomeListarConsulta, R.id.textViewCelularListarConsulta};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.Consulta_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Consulta read (int id) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "pacient_id","medico_id", "data_hora_inicio","data_hora_fim","observacao"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(table, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Consulta c = new Consulta();
        c.setId(data.getInt(0));
        c.setPacient_id(data.getString(1));
        c.setMedico_id(data.getString(2));
        c.setData_hora_inicio(data.getString(3));
        c.setData_hora_fim(data.getString(4));
        c.setObservacao(data.getString(5));
        data.close();
        db.close();
        return c;
    }
}