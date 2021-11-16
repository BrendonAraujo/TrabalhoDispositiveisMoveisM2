package br.com.dlweb.maternidade.paciente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.consulta.R;
import br.com.dlweb.consulta.database.SQLiteHelper;

public class PacienteDataBase {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private static final String table = "paciente";

    public PacienteDataBase(Context ctx){
        StringBuilder sqlCreate = new StringBuilder();
        sqlCreate.append("CREATE TABLE ");
        sqlCreate.append(table);
        sqlCreate.append("(");
        sqlCreate.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCreate.append("nome VARCHAR(50), ");
        sqlCreate.append("grp_sanguineo TINYINT(1), ");
        sqlCreate.append("celular VARCHAR(20), ");
        sqlCreate.append("fixo VARCHAR(20), ");
        sqlCreate.append(");");
        StringBuilder sqlDelete = new StringBuilder();
        sqlDelete.append("DROP TABLE IF NOT EXISTS ");
        sqlDelete.append(table);
        dbHelper = new SQLiteHelper(ctx, ctx.getResources().getString(R.string.database_name), 1, sqlCreate.toString(), sqlDelete.toString());
    }
    public long add(PacienteDataBase p) {
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("grp_sanguineo", p.getGrp_sanguineo());
        cv.put("celular", p.getCelular());
        cv.put("fixo", p.getFixo());
        db = dbHelper.getWritableDatabase();
        long id = db.insert(table, null, cv);
        db.close();
        return id;
    }

    public long edit(PacienteDataBase p) {
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("grp_sanguineo", p.getGrp_sanguineo());
        cv.put("celular", p.getCelular());
        cv.put("fixo", p.getFixo());
        db = dbHelper.getWritableDatabase();
        long rows = db.update(table, cv, "_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return rows;
    }

    public long delete(PacienteDataBase p) {
        db = dbHelper.getWritableDatabase();
        long rows = db.delete(table, "_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return rows;
    }

    public void readAll (Context context, ListView lv) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome","grp_sanguineo", "celular",'Fixo'};
        Cursor data = db.query(table, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarPaciente, R.id.textViewNomeListarPaciente, R.id.textViewCelularListarPaciente};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.Paciente_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Paciente read (int id) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome","grp_sanguineo", "celular",'Fixo';
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(table, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Paciente p = new PacienteDataBase();
        p.setId(data.getInt(0));
        p.setNome(data.getString(1));
        p.setGrp_sanguineo(data.getString(2));
        p.setCelular(data.getString(3));
        p.setFixo(data.getString(4));
        data.close();
        db.close();
        return p;
    }
}