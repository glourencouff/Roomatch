package br.com.goteirapp.roomatch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.Controller.MatchController;
import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.Controller.VagaController;
import br.com.goteirapp.roomatch.model.Match;
import br.com.goteirapp.roomatch.model.Usuario;

public class CandidatosActivity extends Activity {

    ListView lvCandidatos;
    MatchController matchController;
    UsuarioController usuarioController;
    VagaController vagaController;
    Usuario usuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidatos);

        Bundle extras = getIntent().getExtras();

        matchController = new MatchController(getBaseContext());
        usuarioController = new UsuarioController(getBaseContext());

        if (extras != null) {
            usuarioLogado = new Usuario();
            usuarioLogado = extras.getParcelable("usuario");
        }
        List<Match> matches = matchController.getUsersVaga(Integer.valueOf(usuarioLogado.getId()));


          lvCandidatos=(ListView) findViewById(R.id.lvCandidatos);
        lvCandidatos.setAdapter(new AdapterCandidatoListView(this, matches,usuarioLogado,matchController,vagaController,usuarioController));


    }
}
