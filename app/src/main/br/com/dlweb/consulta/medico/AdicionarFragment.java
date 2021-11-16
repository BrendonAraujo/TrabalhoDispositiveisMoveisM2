package br.com.dlweb.consulta.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutionException;

import br.com.dlweb.consulta.R;

public class AdicionarFragment extends Fragment {

    EditText etNome;
    EditText etCrm;
    EditText etCelular;
    EditText etFixo;

    public AdicionarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.medico_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCrm = v.findViewById(R.id.editTextCr,);
        etCelular = v.findViewById(R.id.editTextCelularMedico);
        etFixo = v.findViewById(R.id.editTextFixoMedico);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarMedico);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do médico!", Toast.LENGTH_LONG).show();
        } else if (etCrm.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, o código do Conselho Nacional de Medicina do médico!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o celular do médico!", Toast.LENGTH_LONG).show();
        } else if (etFixo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone fixo do médico!", Toast.LENGTH_LONG).show();
        }else {
            MedicoDAO md = new MedicoDAO(getActivity());
            Medico m = new Medico();
            m.setId(0);
            m.setNome(etNome.getText().toString());
            m.setCrm(etCrm.getText().toString());
            m.setCelular(etCelular.getText().toString());
            m.setFixo(etFixo.getText().toString());
            md.add(m);
            Toast.makeText(getActivity(), "Médico salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }
}