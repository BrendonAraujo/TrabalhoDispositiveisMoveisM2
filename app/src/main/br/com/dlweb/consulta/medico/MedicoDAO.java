package br.com.dlweb.maternidade.medico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.consulta.R;
import br.com.dlweb.consulta.database.SQLiteHelper;

public class MedicoDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private static final String table = "medico";

    public MedicoDAO(Context ctx){
        StringBuilder sqlCreate = new StringBuilder();
        sqlCreate.append("CREATE TABLE ");
        sqlCreate.append(table);
        sqlCreate.append("(");
        sqlCreate.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCreate.append("nome VARCHAR(50), ");
        sqlCreate.append("crm VARCHAR(20), ");
        sqlCreate.append("celular VARCHAR(20), ");
        sqlCreate.append("fixo VARCHAR(20), ");
        sqlCreate.append(");");
        StringBuilder sqlDelete = new StringBuilder();
        sqlDelete.append("DROP TABLE IF NOT EXISTS ");
        sqlDelete.append(table);
        dbHelper = new SQLiteHelper(ctx, ctx.getResources().getString(R.string.database_name), 1, sqlCreate.toString(), sqlDelete.toString());
    }
    public long add(Medico m) {
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("crm", m.getCrm());
        cv.put("celular", m.getCelular());
        cv.put("fixo", m.getFixo());
        db = dbHelper.getWritableDatabase();
        long id = db.insert(table, null, cv);
        db.close();
        return id;
    }

    public long edit(Medico m) {
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("crm", m.getCrm());
        cv.put("celular", m.getCelular());
        cv.put("fixo", m.getFixo());
        db = dbHelper.getWritableDatabase();
        long rows = db.update(table, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return rows;
    }

    public long delete(Medico m) {
        db = dbHelper.getWritableDatabase();
        long rows = db.delete(table, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return rows;
    }

    public void readAll (Context context, ListView lv) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome", "celular",'Fixo'};
        Cursor data = db.query(table, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarMedico, R.id.textViewNomeListarMedico, R.id.textViewCelularListarMedico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.medico_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Medico read (int id) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome", "crm", "celular","fixo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(table, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Medico m = new Medico();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setCrm(data.getString(2));
        m.setCelular(data.getString(3));
        m.setFixo(data.getString(4));
        data.close();
        db.close();
        return m;
    }
}