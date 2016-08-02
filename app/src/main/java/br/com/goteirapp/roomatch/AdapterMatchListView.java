package br.com.goteirapp.roomatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.goteirapp.roomatch.Controller.MatchController;
import br.com.goteirapp.roomatch.model.Usuario;

/**
 * Created by Gabriel on 22/07/2016.
 */
public class AdapterMatchListView extends BaseAdapter {

    private List<Usuario> itens;
    private Context context;


    public AdapterMatchListView(Context context, List<Usuario> itens) {
        //LISTA DE CANDIDADTOS
        this.itens = itens;
        this.context = context;
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final MatchSuporte auxMatch;
        Usuario usuario = itens.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null ){

            view = inflater.inflate(R.layout.item_match, null);

            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            auxMatch = new MatchSuporte();
            auxMatch.txtTitle = ((TextView) view.findViewById(R.id.tvNomeUsuario));
            auxMatch.imgChat = ((ImageView) view.findViewById(R.id.listaMatch_imgChat));

            auxMatch.txtTitle.setText(usuario.getNome());
            auxMatch.imgChat.setImageResource(R.drawable.chat_usuario);

            auxMatch.imgChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Chat ainda não foi implementado", Toast.LENGTH_SHORT).show();
                }
            });

            view.setTag(auxMatch);
        } else {
            auxMatch = (MatchSuporte) view.getTag();
        }

        Usuario item = itens.get(position);
        auxMatch.txtTitle.setText(item.getNome());


        return view;
    }


    private class MatchSuporte {
        ImageView imgChat;
        TextView txtTitle;
    }
}
