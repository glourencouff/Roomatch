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
import br.com.goteirapp.roomatch.model.Usuario;

public class LocatarioMatchActivity extends AppCompatActivity {

    ListView lvCandidatos;
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
        List<Integer> idUsuarios = matchController.getUsersLike(Integer.valueOf(usuarioLogado.getId()));
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (idUsuarios != null && !idUsuarios.isEmpty()) {
            for (int i = 0; i < idUsuarios.size(); i++) {
                Usuario usu = new Usuario();
                usu = usuarioController.getUsuario(idUsuarios.get(i));
                usuarios.add(usu);
            }

        }


        lvCandidatos=(ListView) findViewById(R.id.lvCandidatos);
        //lvCandidatos.setAdapter(new AdapterCandidatoListView(this, usuarios));
    }

}
