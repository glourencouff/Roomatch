package br.com.goteirapp.roomatch;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import br.com.goteirapp.roomatch.Controller.FiltroController;
import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.model.Filtros;
import br.com.goteirapp.roomatch.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    static final int POP_UP_FILTROS = 1;
    Usuario usuarioLogado;
    Filtros filtros;
    UsuarioController usuarioControler;
    LinearLayout llPopUpfiltros ;
    LinearLayout llFiltrosLembrete;
    FiltroController filtroController;
    Boolean filtroHasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuarioControler = new UsuarioController(getBaseContext());
        Bundle extras = getIntent().getExtras();

        llPopUpfiltros = (LinearLayout) findViewById(R.id.llPopUpFiltros);
        llFiltrosLembrete = (LinearLayout) findViewById(R.id.llFiltrosLembrete);

        llFiltrosLembrete.setVisibility(View.GONE);

        EditText login = (EditText) findViewById(R.id.etNome);
        EditText email = (EditText) findViewById(R.id.etEmail);
        EditText senha = (EditText) findViewById(R.id.etSenha);
        RadioButton locador = (RadioButton) findViewById(R.id.cadastro_rbLocador);
        RadioButton locatario = (RadioButton) findViewById(R.id.cadastro_rbLocatario);
        TextView botao = (TextView) findViewById(R.id.tvLogin);

        RadioGroup tipoPerfil = (RadioGroup)findViewById(R.id.rgTipoPerfil);
        tipoPerfil.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cadastro_rbLocador:
                        llPopUpfiltros.setVisibility(View.GONE);
                        if (llFiltrosLembrete.getVisibility() == View.VISIBLE){
                            filtros = null;
                            llFiltrosLembrete.setVisibility(View.GONE);
                            filtroHasChanged = false;
                        }
                        break;
                    default:
                        llPopUpfiltros.setVisibility(View.VISIBLE);
                }
            }
        });





        if (extras != null){
            usuarioLogado = extras.getParcelable("usuario");
            if(usuarioLogado != null){

                login.setText(usuarioLogado.getNome());
                email.setText(usuarioLogado.getEmail());
                senha.setText(usuarioLogado.getSenha());

                botao.setText("Atualizar");



                switch (usuarioLogado.getTipoPerfil()){
                    case  R.id.cadastro_rbLocador:
                        locador.setChecked(true);
                        llPopUpfiltros.setVisibility(View.GONE);
                        break;
                    default:
                        locatario.setChecked(true);
                        llPopUpfiltros.setVisibility(View.VISIBLE);
                }
                // inicialização do POPUP
            }
        } else {
            locatario.setChecked(true);
            llPopUpfiltros.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // CONFERE DE QUAL ACTIVITY VEIO
        if (requestCode == POP_UP_FILTROS) {
            // CONFERE CODIGO RETORNOADO
            if (resultCode == RESULT_OK) {
                filtroHasChanged = true;
                filtros = data.getParcelableExtra("filtros");
                llFiltrosLembrete.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Filtros modificados com sucesso! Atualize seu cadastro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void cadastroUsuario(View view) {


        //CAMPOS AVALIADOS NO CADASTRO
        EditText login = (EditText) findViewById(R.id.etNome);
        EditText email = (EditText) findViewById(R.id.etEmail);
        EditText senha = (EditText) findViewById(R.id.etSenha);
        RadioGroup  tipoPerfil = (RadioGroup) findViewById(R.id.rgTipoPerfil);

        String strLogin = ((login == null)) ? "" : login.getText().toString();
        String strEmail = ((email == null)) ? "" : email.getText().toString();
        String strSenha = ((senha == null)) ? "" : senha.getText().toString();

        //AVALIA SE É CADASTRO OU ATUALIZAÇÃO

        if(strLogin.length() < 3){
            Toast.makeText(getApplicationContext(), "Login deve possuir pelo menos 3 caracteres", Toast.LENGTH_SHORT).show();
        }else if(strEmail.isEmpty() || strEmail.equals("")){
            Toast.makeText(getApplicationContext(), "Email inválido.", Toast.LENGTH_SHORT).show();
        }else if(strSenha.equals("") || strSenha.isEmpty()){
            Toast.makeText(getApplicationContext(), "Senha inválida.", Toast.LENGTH_SHORT).show();
        }else if(strSenha.length() < 3){
            Toast.makeText(getApplicationContext(), "Senha deve possuir pelo menos 3 caracteres", Toast.LENGTH_SHORT).show();
        }else{

            //ATUALIZAÇÃO

            if(usuarioLogado != null) {

                usuarioLogado.setSenha(strSenha);
                usuarioLogado.setEmail(strEmail);
                usuarioLogado.setTipoPerfil(tipoPerfil.getCheckedRadioButtonId());

                filtroController = new FiltroController(getBaseContext());
                Boolean filtroOk = true;
                if(usuarioLogado.getTipoPerfil() == R.id.cadastro_rbLocatario && filtroHasChanged) {
                    if (filtros.getId() == 0) {
                        long novoId = filtroController.insereDado(filtros);
                        if (novoId != -1) {
                            filtros.setId((int) novoId);
                            usuarioLogado.setIdFiltro(filtros.getId());
                        } else {
                            Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao cadastrar o filtro", Toast.LENGTH_SHORT).show();
                            filtroOk = false;
                        }
                    } else {
                        if (!filtroController.update(filtros)) {
                            Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao atualizar os filtros", Toast.LENGTH_SHORT).show();
                            filtroOk = false;
                        }
                    }
                }

                if(filtroOk){

                    int  resultado = usuarioControler.update(usuarioLogado);

                    if (resultado == -1){
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro ao atualizar.", Toast.LENGTH_SHORT).show();
                    } else{
                        Intent intentAtualizar = new Intent(CadastroActivity.this, MainActivity.class);
                        intentAtualizar.putExtra("usuario", usuarioLogado);
                        Toast.makeText(getApplicationContext(), "Atualização realizada com sucesso.", Toast.LENGTH_SHORT).show();
                        startActivity(intentAtualizar);
                    }
                }
            } else {

                String resultado;

                resultado = usuarioControler.insereDado(strLogin,strEmail,strSenha,tipoPerfil.getCheckedRadioButtonId());

                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                /*intent.putExtra("login", srtLogin);
                intent.putExtra("senha", strSenha);*/


                startActivity(intent);
            }
        }
    }



    public void abrirPopUp(View view){
        Intent intent = new Intent(CadastroActivity.this,Pop_pup_filtos.class);
        intent.putExtra("usuario",usuarioLogado);
        startActivityForResult(intent, POP_UP_FILTROS);
    }



}
