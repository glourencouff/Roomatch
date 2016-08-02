package br.com.goteirapp.roomatch;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TabHost;

import java.util.ArrayList;

import br.com.goteirapp.roomatch.model.Usuario;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    Usuario usuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            usuarioLogado = extras.getParcelable("usuario");
        }

        TabHost tbMain = (TabHost) findViewById(android.R.id.tabhost);
        //SELECIONA AS ABAS QUE SERAO EXIBIDAS DEPENDENDO DO PERFIL
        switch (usuarioLogado.getTipoPerfil()){
            case R.id.cadastro_rbLocatario:
                //PERFIL LOCADOR
                TabHost.TabSpec tabLocador = tbMain.newTabSpec("Aba Locador");
                tabLocador.setIndicator("",getResources().getDrawable(R.drawable.home));
                tabLocador.setContent(new Intent(this, TabLocatarioActivity.class).putExtra("usuario",usuarioLogado));
                tbMain.addTab(tabLocador);

                TabHost.TabSpec tabChatLocatario = tbMain.newTabSpec("Aba Chat Locatario");
                tabChatLocatario.setIndicator("",getResources().getDrawable(R.drawable.chat));
                tabChatLocatario.setContent(new Intent(this, LocatarioMatchActivity.class).putExtra("usuario",usuarioLogado));
                tbMain.addTab(tabChatLocatario);
                break;
            case R.id.cadastro_rbLocador:
                //PERFIL LOCATARIO
                TabHost.TabSpec tabLocatario = tbMain.newTabSpec("Aba Locatario");
                tabLocatario.setIndicator("", getResources().getDrawable(R.drawable.add));
                tabLocatario.setContent(new Intent(this, LocadorCadastroActivity.class).putExtra("usuario",usuarioLogado));
                tbMain.addTab(tabLocatario);

                TabHost.TabSpec tabLista = tbMain.newTabSpec("Aba Lista");
                tabLista.setIndicator("", getResources().getDrawable(R.drawable.lista));
                tabLista.setContent(new Intent(this, CandidatosActivity.class).putExtra("usuario", usuarioLogado));
                tbMain.addTab(tabLista);
        }




        TabHost.TabSpec tabMenu = tbMain.newTabSpec("Aba Menu");
        tabMenu.setIndicator("", getResources().getDrawable(R.drawable.menu));
        tabMenu.setContent(new Intent(this, CadastroActivity.class).putExtra("usuario", (Parcelable) usuarioLogado));
        tbMain.addTab(tabMenu);




    }
}
