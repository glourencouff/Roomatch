package br.com.goteirapp.roomatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.goteirapp.roomatch.Controller.VagaController;
import br.com.goteirapp.roomatch.model.Usuario;
import br.com.goteirapp.roomatch.model.Vaga;

public class LocadorCadastroActivity extends AppCompatActivity {

    Usuario usuarioLogado;
    //private static final int TWO_MINUTES = 1000 * 60 * 2;
    static final int EXIBIR_MAPA= 1;

    EditText cidade;
    EditText bairro;
    EditText rua;
    EditText numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locador_cadastro);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            usuarioLogado = new Usuario();
            usuarioLogado = extras.getParcelable("usuario");
        }

    }

    public void cadastroLocador(View view) {
        EditText valor = (EditText) findViewById(R.id.etValor);
        EditText descricao = (EditText) findViewById(R.id.etDescricao);

        RadioGroup sexoVaga;
        RadioButton opcaoSexoVaga;
        RadioGroup tipoVaga;
        RadioButton opcaoTipoVaga;

        sexoVaga = (RadioGroup) findViewById(R.id.sexoVaga);
        int selectedId = sexoVaga.getCheckedRadioButtonId();
        opcaoSexoVaga = (RadioButton) findViewById(selectedId);

        String opcaoSexo = opcaoSexoVaga.getText().toString();

        tipoVaga = (RadioGroup) findViewById(R.id.rbTipoMoradia);
        selectedId = tipoVaga.getCheckedRadioButtonId();
        opcaoTipoVaga = (RadioButton) findViewById(selectedId);

        String opcaoTipo = opcaoTipoVaga.getText().toString();

        CheckBox cbVagaMobiliada = (CheckBox) findViewById(R.id.cbVagaMobiliada);

        RadioButton masculino = (RadioButton) findViewById(R.id.locador_rbMasculina);
        RadioButton feminino = (RadioButton) findViewById(R.id.locador_rbFeminina);
        RadioButton casa = (RadioButton) findViewById(R.id.locador_rbCasa);
        RadioButton apartamento = (RadioButton) findViewById(R.id.locador_rbApartamento);



        String srtValor = ((valor == null)) ? "" : valor.getText().toString();
        String strDescricao = ((descricao == null)) ? "" : descricao.getText().toString();


        if (srtValor.equals("") || valor==null){
            Toast.makeText(getApplicationContext(), "Valor inválido.", Toast.LENGTH_SHORT).show();
        }else if(strDescricao.equals("") || descricao==null) {
            Toast.makeText(getApplicationContext(), "Descrição inválida.", Toast.LENGTH_SHORT).show();
        }else if(opcaoSexo.equals("") || opcaoSexoVaga==null){
            Toast.makeText(getApplicationContext(), "Tipo de vaga inválido.", Toast.LENGTH_SHORT).show();
        }else if(opcaoTipo.equals("") || opcaoTipoVaga==null){
            Toast.makeText(getApplicationContext(), "Tipo de moradia inválido.", Toast.LENGTH_SHORT).show();
        } else {

            VagaController vagaController = new VagaController(getBaseContext());

            Vaga novaVaga = new Vaga();
            novaVaga.setLocador(Integer.valueOf(usuarioLogado.getId()));
            novaVaga.setVagaDescricao(strDescricao);
            novaVaga.setVagaMobiliada(((cbVagaMobiliada.isChecked())) ? 1 : 0);
            switch (tipoVaga.getCheckedRadioButtonId()){
                case R.id.locador_rbCasa:
                    novaVaga.setVagaTipoMoradia(R.id.popup_rbCasa);
                    break;
                default:
                    novaVaga.setVagaTipoMoradia(R.id.popup_rbApartamento);
            }

            switch (sexoVaga.getCheckedRadioButtonId()){
                case R.id.locador_rbMasculina:
                    novaVaga.setVagaTipoVaga(R.id.popup_rbMasculina);
                    break;
                default:
                    novaVaga.setVagaTipoVaga(R.id.popup_rbFeminina);
            }

            novaVaga.setVagaValor(Integer.valueOf(srtValor));

            String resultado = vagaController.insereDado(novaVaga);

            if ( !resultado.equals("-1")){
                Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void abrirMapa(View view){
        cidade = (EditText) findViewById(R.id.cadastro_local_cidade);
        bairro = (EditText) findViewById(R.id.cadastro_local_bairro);
        rua = (EditText) findViewById(R.id.cadastro_local_rua);
        numero = (EditText) findViewById(R.id.cadastro_local_numero);
        String address ="";

        if (cidade.getText().length() > 1){

           address = address.concat(cidade.getText().toString() + " ");
           address = address.concat(((!bairro.getText().equals(""))) ? bairro.getText().toString() + " ": "" );
           address = address.concat(((!rua.getText().equals(""))) ? rua.getText().toString() + " ": "" );
           address = address.concat(((!numero.getText().equals(""))) ? numero.getText().toString() + " ": "" );
        }

        Intent intent = new Intent(LocadorCadastroActivity.this,MapaCadastroActivityFragment.class);
        intent.putExtra("usuario", usuarioLogado);
        if(address.length() > 1){
            intent.putExtra("address",address);
            Toast.makeText(this,address,Toast.LENGTH_SHORT).show();
        }
        startActivityForResult(intent, EXIBIR_MAPA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // CONFERE DE QUAL ACTIVITY VEIO
        if (requestCode == EXIBIR_MAPA) {
            // CONFERE CODIGO RETORNOADO
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Retorno do mapa Ok", Toast.LENGTH_SHORT).show();
            }
        }
    }

/*

    private static class LongPressLocationSource implements LocationSource, GoogleMap.OnMapLongClickListener {

        private OnLocationChangedListener mListener;

        */
/**
         * Flag to keep track of the activity's lifecycle. This is not strictly necessary in this
         * case because onMapLongPress events don't occur while the activity containing the map is
         * paused but is included to demonstrate best practices (e.g., if a background service were
         * to be used).
         *//*

        private boolean mPaused;

        @Override
        public void activate(OnLocationChangedListener listener) {
            mListener = listener;
        }

        @Override
        public void deactivate() {
            mListener = null;
        }

        @Override
        public void onMapLongClick(LatLng point) {
            if (mListener != null && !mPaused) {
                Location location = new Location("LongPressLocationProvider");
                location.setLatitude(point.latitude);
                location.setLongitude(point.longitude);
                location.setAccuracy(100);
                mListener.onLocationChanged(location);
            }
        }

        public void setLocation(LatLng latLng){
            Location location = new Location(LocationManager.GPS_PROVIDER);

            location.setLongitude(latLng.longitude);
            location.setLatitude(latLng.latitude);

            if(mListener != null) {
                mListener.onLocationChanged(location);
            }
        }



        public void onPause() {
            mPaused = true;
        }

        public void onResume() {
            mPaused = false;
        }
}
*/




}

