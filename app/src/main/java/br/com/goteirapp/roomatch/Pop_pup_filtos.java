package br.com.goteirapp.roomatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;

import br.com.goteirapp.roomatch.Controller.FiltroController;
import br.com.goteirapp.roomatch.model.Filtros;
import br.com.goteirapp.roomatch.model.Usuario;

public class Pop_pup_filtos extends Activity {

    static final int POP_UP_FILTROS = 1;
    Usuario usuarioLogado;
    Filtros filtros;
    FiltroController filtroController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_pup_filtos);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        filtroController = new FiltroController(getBaseContext());

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuarioLogado = extras.getParcelable("usuario");
            if (usuarioLogado != null) {

                if( usuarioLogado.getIdFiltro() != 0){
                    CheckBox mobilado = (CheckBox) findViewById(R.id.popup_cbMobiliado);

                    RadioButton masculino = (RadioButton) findViewById(R.id.popup_rbMasculina);
                    RadioButton feminino = (RadioButton) findViewById(R.id.popup_rbFeminina);

                    RadioButton casa = (RadioButton) findViewById(R.id.popup_rbCasa);
                    RadioButton apartamento = (RadioButton) findViewById(R.id.popup_rbApartamento);

                    RangeBar rangeBar = (RangeBar) findViewById(R.id.popup_rangebar);

                    filtros = filtroController.getFiltroById(usuarioLogado.getIdFiltro());

                    if (filtros != null){
                        mobilado.setChecked(((filtros.getMobiliado() == 1))? true : false);
                        switch (filtros.getTipoVaga()){
                            case R.id.popup_rbMasculina:
                                masculino.setChecked(true);
                                break;
                            default:
                                feminino.setChecked(true);
                        }

                        switch (filtros.getTipoResidencia()){
                            case R.id.popup_rbCasa:
                                casa.setChecked(true);
                                break;
                            default:
                                apartamento.setChecked(true);
                        }

                        rangeBar.setRangePinsByValue((float)filtros.getMinValor(),(float)filtros.getMaxValor());

                    } else {
                        Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao recuperar os filtros", Toast.LENGTH_SHORT).show();
                    }



                }

            }
        }

       /* usuarioLogado.setTipoMoradia(opVaga);
        usuarioLogado.setTipoVaga(opSexo);
        usuarioLogado.setMobiliada(((mobiliada.isChecked())) ? 1 : 0);
        usuarioLogado.setTipoPerfil(tipoPerfilSelected);*/

    }

    public void retornarFiltros(View view){
        Boolean podeProsseguir = true;
        CheckBox mobilado = (CheckBox) findViewById(R.id.popup_cbMobiliado);

        RadioGroup tipoVaga = (RadioGroup) findViewById(R.id.popup_sexoVaga);
        RadioGroup tipoResidencia = (RadioGroup) findViewById(R.id.popup_TipoMoradia);

        RangeBar rangeBar = (RangeBar) findViewById(R.id.popup_rangebar);

        switch (usuarioLogado.getIdFiltro()){
            case 0: // PRIMEIRO FILTRO CRIADO
                filtros = new Filtros();

                filtros.setIdUsuario(Integer.valueOf(usuarioLogado.getId()));
                filtros.setMobiliado(((mobilado.isChecked())) ? 1 : 0);
                filtros.setTipoVaga(tipoVaga.getCheckedRadioButtonId());
                filtros.setTipoResidencia(tipoResidencia.getCheckedRadioButtonId());
                filtros.setMinValor(Integer.valueOf(rangeBar.getLeftPinValue()));
                filtros.setMaxValor(Integer.valueOf(rangeBar.getRightPinValue()));

                /*long novoId = filtroController.insereDado(filtros);

                if(novoId == -1){
                    Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao salvar os filtros", Toast.LENGTH_SHORT).show();
                    podeProsseguir = false;
                } else{
                    filtros.setId((int)novoId);
                }*/
                break;
            default:// ATUALIZAÇÃO DE FILTRO
                if (filtros != null ){
                    filtros.setMobiliado(((mobilado.isChecked())) ? 1 : 0);
                    filtros.setTipoVaga(tipoVaga.getCheckedRadioButtonId());
                    filtros.setTipoResidencia(tipoResidencia.getCheckedRadioButtonId());
                    filtros.setMinValor(Integer.valueOf(rangeBar.getLeftPinValue()));
                    filtros.setMaxValor(Integer.valueOf(rangeBar.getRightPinValue()));

                    /*if (!filtroController.update(filtros)){
                        Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao atualizar os filtros", Toast.LENGTH_SHORT).show();
                        podeProsseguir = false;
                    }*/
                } else {
                    Toast.makeText(getApplicationContext(), "Ops..ocorreu um erro ao recuperar os filtros", Toast.LENGTH_SHORT).show();
                    podeProsseguir = false;
                }
        }

        if (podeProsseguir) {
            Intent intent = new Intent();
            intent.putExtra("filtros",filtros);
            setResult(-1, intent);
            finish();//finishing activity
        }
    }

}
