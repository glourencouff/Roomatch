package br.com.goteirapp.roomatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.Controller.FiltroController;
import br.com.goteirapp.roomatch.Controller.MatchController;
import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.Controller.VagaController;
import br.com.goteirapp.roomatch.model.Filtros;
import br.com.goteirapp.roomatch.model.Usuario;
import br.com.goteirapp.roomatch.model.Vaga;

public class TabLocatarioActivity extends Activity {
    List<Vaga> vagas;
    VagaController vagaController;
    MatchController matchController;
    FiltroController filtroController;
    Usuario usuarioLogado;
    Filtros filtros;
    static final int DETALHAR_VAGA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_locatario);
        vagas = new ArrayList<>();
        Bundle extras = getIntent().getExtras();

        vagaController = new VagaController(getBaseContext());
        matchController= new MatchController(getBaseContext());
        filtroController = new FiltroController(getBaseContext());

        usuarioLogado = new Usuario();
        if (extras != null){
            usuarioLogado = extras.getParcelable("usuario");
            filtros = filtroController.getFiltroById(usuarioLogado.getIdFiltro());
        }

       if (filtros != null){
           if (filtros.getId()!= 0){
               vagas = vagaController.getVagasByFiltros(Integer.valueOf(usuarioLogado.getId()),filtros);
           }
       } else {
           Toast.makeText(getApplicationContext(), "Usuario não possui filtro selecionado. Selecione os filtros no menu.", Toast.LENGTH_SHORT).show();
           Toast.makeText(getApplicationContext(), "Retornando todas vagas existentes.", Toast.LENGTH_SHORT).show();
           vagas = vagaController.getAllVagas((Integer.valueOf(usuarioLogado.getId())));
       }


        CardContainer mCardContainer = (CardContainer) findViewById(R.id.viewCard);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        UsuarioController usuarioController = new UsuarioController(getBaseContext());
        for (int i = 0; i < vagas.size(); i++) {   //listaPedidos é um ArrayList comum
            final Vaga vaga = vagas.get(i);

            Usuario usuario = usuarioController.getUsuario(vaga.getLocador());


            CardModel card = new CardModel(usuario.getNome(), String.valueOf(vaga.getVagaValor()), ResourcesCompat.getDrawable(getResources(), R.drawable.picture1, null));





            card.setOnClickListener(new CardModel.OnClickListener() {
                @Override
                public void OnClickListener() {
                    Intent intent = new Intent(TabLocatarioActivity.this,DetalhaVagaActivity.class);
                    intent.putExtra("vaga",vaga);
                    startActivityForResult(intent, DETALHAR_VAGA);
                }
            });


            card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
                @Override
                public void onLike() {
                    String retorno = matchController.insereDado(vaga.getId(),Integer.valueOf(usuarioLogado.getId()),vaga.getLocador(),3); //3 VALOR DEFAULT = 3 AINDA NAO FOI CURTIDO 1 = CURTIU , 0 = NAO

                    Toast.makeText(getApplicationContext(), retorno, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDislike() {
                    Toast.makeText(getApplicationContext(), "Curti não...", Toast.LENGTH_SHORT).show();
                }
            });

            adapter.add(card);
        }

/*
        CardModel card1 = new CardModel("Titulo", "Teste", ResourcesCompat.getDrawable(getResources(), R.drawable.picture1, null));
        CardModel card2 = new CardModel("Titulo", "Teste", ResourcesCompat.getDrawable(getResources(), R.drawable.picture2, null));


        card1.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Toast.makeText(getApplicationContext(), "Opa, curti!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDislike() {
                Toast.makeText(getApplicationContext(), "Curti não...", Toast.LENGTH_SHORT).show();
            }
        });

        *//*card2.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Teste", "Teste");
            }
        });*//*

        card2.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Toast.makeText(getApplicationContext(), "Opa, curti!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDislike() {
                Toast.makeText(getApplicationContext(), "Curti não...", Toast.LENGTH_SHORT).show();
            }
        });


        adapter.add(card1);
        adapter.add(card2);*/
        mCardContainer.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // CONFERE DE QUAL ACTIVITY VEIO
        if (requestCode == DETALHAR_VAGA) {
            // CONFERE CODIGO RETORNOADO
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Tela Detalhada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
