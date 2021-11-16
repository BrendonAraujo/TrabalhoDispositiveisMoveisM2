package br.com.dlweb.consulta.medico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.dlweb.consulta.R;

public class EditarFragment extends Fragment {

    EditText etNome;
    EditText etCrm;
    EditText etCelular;
    EditText etFixo;
    MedicoDAO md;
    Medico m;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.medico_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_medico = b.getInt("id");
        md = new MedicoDAO(getActivity());
        m = md.read(id_medico);

        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCrm = v.findViewById(R.id.editTextCrmMedico);
        etCelular = v.findViewById(R.id.editTextCelularMedico);
        etFixo = v.findViewById(R.id.editTextFixoMedico);

        etNome.setText(m.getNome());
        etEspecialidade.setText(m.getCrm());
        etCelular.setText(m.getCelular());
        etFixo.setText(m.getFixo());

        Button btnEditar = v.findViewById(R.id.buttonEditarMedico);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_medico);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluirMedico);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.excluir_medico_dialog);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_medico);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return v;
    }

    private void editar (int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do médico!", Toast.LENGTH_LONG).show();
        } else if (etCrm.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o código do Conselho Nacional de Medicina (CRM) do médico!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o celular do médico!", Toast.LENGTH_LONG).show();
        } else if (etFixo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone fixo do médico!", Toast.LENGTH_LONG).show();
        } else {
            m = new Medico();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setCrm(etCrm.getText().toString());
            m.setCelular(etCelular.getText().toString());
            m.setFixo(etFixo.getText().toString());
            md.edit(m);
            Toast.makeText(getActivity(), "Médico editado!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        m = new Medico();
        m.setId(id);
        md.delete(m);
        Toast.makeText(getActivity(), "Médico excluído!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
    }
}