package br.com.goteirapp.roomatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.Controller.MatchController;
import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.Controller.VagaController;
import br.com.goteirapp.roomatch.model.Match;
import br.com.goteirapp.roomatch.model.Usuario;

public class LocatarioMatchActivity extends AppCompatActivity {

    ListView lvMatches;
    MatchController matchController;
    UsuarioController usuarioController;
    VagaController vagaController;
    Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatario_match);

        Bundle extras = getIntent().getExtras();

        matchController = new MatchController(getBaseContext());
        usuarioController = new UsuarioController(getBaseContext());

        if (extras != null) {
            usuarioLogado = new Usuario();
            usuarioLogado = extras.getParcelable("usuario");
        }
        List<Match> matches = matchController.getUsersLike(Integer.valueOf(usuarioLogado.getId()));
        List<Usuario> usuarios = new ArrayList<>();
        if (matches != null && matches.size() > 0) {
            for (int i = 0; i < matches.size(); i++) {
                Usuario usu;
                usu = usuarioController.getUsuario(matches.get(i).getIdLocador());
                usuarios.add(usu);
            }

        }


        lvMatches=(ListView) findViewById(R.id.lvMatches);
        lvMatches.setAdapter(new AdapterMatchListView(this, usuarios));
    }

}
