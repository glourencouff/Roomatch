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
import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.Controller.VagaController;
import br.com.goteirapp.roomatch.model.Match;
import br.com.goteirapp.roomatch.model.Usuario;

/**
 * Created by Gabriel on 20/07/2016.
 */


public class AdapterCandidatoListView extends BaseAdapter {

    private List<Match> itens;
    private Context context;
    MatchController matchController;
    VagaController vagaController;
    Usuario usuarioLogado;
    UsuarioController usuarioController;

    public AdapterCandidatoListView(Context context, List<Match> itens,Usuario usuario, MatchController matchController, VagaController vagaController, UsuarioController usuarioController) {
        //LISTA DE CANDIDADTOS
        this.itens = itens;
        this.context = context;
        this.matchController = matchController;
        this.vagaController = vagaController;
        this.usuarioController = usuarioController;
        this.usuarioLogado = usuarioLogado;
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

        final CandidatoSuporte auxCandidato;
        final Match match = itens.get(position);

        Usuario usuario = usuarioController.getUsuario(match.getIdLocatario());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null ){

            view = inflater.inflate(R.layout.item_candidato, null);


            auxCandidato = new CandidatoSuporte();
            auxCandidato.txtTitle = ((TextView) view.findViewById(R.id.tvNomeUsuario));
            auxCandidato.imgLike = ((ImageView) view.findViewById(R.id.ivLike));
            auxCandidato.imgDislike = ((ImageView) view.findViewById(R.id.ivDislike));

            Boolean curtiu = matchController.hasLike(match.getIdLocatario(), match.getIdVaga());

            if(curtiu){
                auxCandidato.imgLike.setImageResource(R.drawable.liked);
            }

            auxCandidato.imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(matchController.hasLike(match.getIdLocatario(), match.getIdVaga())){
                        Toast.makeText(context, "Você já curtiu essa pessoa", Toast.LENGTH_SHORT).show();
                    } else{
                      Boolean resultado  =  matchController.updateMatch(match.getIdLocatario(), match.getIdVaga(), 1);
                        if(resultado){
                            Toast.makeText(context, "Curtiu!.", Toast.LENGTH_SHORT).show();
                            auxCandidato.imgLike.setImageResource(R.drawable.liked);

                        }else{
                            Toast.makeText(context, "Ops..ocorreu um erro", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

            auxCandidato.txtTitle.setText(usuario.getNome());

            view.setTag(auxCandidato);
        } else {
            auxCandidato = (CandidatoSuporte) view.getTag();
        }

        Match item = itens.get(position);
        auxCandidato.txtTitle.setText(usuario.getNome());


        return view;
    }


    private class CandidatoSuporte {
        ImageView imgDislike;
        ImageView imgLike;
        TextView txtTitle;
    }



}
