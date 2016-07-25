package br.com.goteirapp.roomatch;

import br.com.goteirapp.roomatch.Controller.UsuarioController;
import br.com.goteirapp.roomatch.model.Usuario;
import br.com.goteirapp.roomatch.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LoginActivity extends Activity {

    private  ArrayList<String> logins ;
    private  ArrayList<String> senhas;
    private  ArrayList<String> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logins = new ArrayList<String>();
        senhas = new ArrayList<String>();
        emails = new ArrayList<String>();

        setContentView(R.layout.activity_login);
        populaLogins();
        populaSenha();
        populaEmail();
       /* Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String login = extras.getString("login");
            String senha = extras.getString("senha");
            logins.add(login);
            senhas.add(senha);
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String email = extras.getString("email");
            String login = extras.getString("login");
            String senha = extras.getString("senha");
            logins.add(login);
            senhas.add(senha);
            emails.add(email);
        }

    }



    public void login(View view)
    {
        UsuarioController usuarioControler = new UsuarioController(getBaseContext());
        EditText login = (EditText) findViewById(R.id.etLogin);
        EditText senha = (EditText) findViewById(R.id.etSenha);

        String strLogin = login.getText().toString();
        String strSenha = senha.getText().toString();

        Usuario usuarioLogado = usuarioControler.validaLogin(strLogin, strSenha);

        if(usuarioLogado == null){
            Toast.makeText(getApplicationContext(), "Login inválido.", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            intent.putExtra("usuario", usuarioLogado);
            startActivity(intent);
        }



    }

    public void cadastro(View view)
    {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void forgot(View view){
        Intent intent = new Intent(LoginActivity.this, LocadorCadastroActivity.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "Calma, não tá pronto ainda.", Toast.LENGTH_SHORT).show();
    }

    private void populaLogins(){
        logins.add("teste");
    }

    private void populaSenha(){
        senhas.add("teste");
    }

    private void populaEmail(){
        emails.add("teste@teste.com");
    }

    private Boolean comparaLogin(String login){
        Boolean isOk = false;
       /* for (int i = 0; i < logins.size(); i++) {

            logins.

        }   */
        if (logins.contains(login)){
            isOk = true;
        }
        return isOk;
    }

    private Boolean comparaSenha(String senha){
        Boolean isOk = false;
        if(senhas.contains(senha)){
            isOk = true;
        }

        return isOk;
    }
}
