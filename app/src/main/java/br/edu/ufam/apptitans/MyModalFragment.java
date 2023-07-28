package br.edu.ufam.apptitans;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import br.edu.ufam.apptitans.models.Item;
import br.edu.ufam.apptitans.usuariodao.ItemDAO;

public class MyModalFragment extends DialogFragment {
    public interface ModalFragmentListener {
        void onButtonClicked(String value1, String value2);
    }

    private EditText editText1;
    private EditText editText2;
    private Button button;

    private ModalFragmentListener listener;

    public MyModalFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_modal, null);

        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        button = view.findViewById(R.id.button);

        //add propriedades
        button.setText("Salvar");
        editText1.setHint("Insira seu mood");
        editText2.setHint("Insira uma música");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = editText1.getText().toString();
                String value2 = editText2.getText().toString();

                Item item = new Item(value1, value2);
                ItemDAO itemDAO = new ItemDAO(MyModalFragment.this.getContext());
                itemDAO.insereItem(item);

                Log.i("BANCO", "Inseringo item no fragmento");

                Toast.makeText(MyModalFragment.this.getContext(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();;

                if (listener != null) {
                    listener.onButtonClicked(value1, value2);
                }

                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }

    public void setModalFragmentListener(ModalFragmentListener listener) {
        this.listener = listener;
    }
}
