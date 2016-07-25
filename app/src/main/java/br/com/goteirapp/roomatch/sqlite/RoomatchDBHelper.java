package br.com.goteirapp.roomatch.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Gabriel on 19/07/2016.
 */

    public class RoomatchDBHelper extends SQLiteOpenHelper {

    private static final String USUARIO_BANCO = "usuario.db";

    //STRINGS DA TABELA DE USUARIO
    private static final String TABELA_USUARIO = "usuarios";
    private static final String ID_USUARIO = "_id";
    private static final String NOME_USUARIO = "login";
    private static final String EMAIL_USUARIO = "email";
    private static final String SENHA_USUARIO = "senha";
    private static final String PERFIL_USUARIO = "perfil";
    private static final String USUARIO_FILTRO = "id_filtro";

    //STRINGS DA TABELA DE VAGAS
    private static final String TABELA_VAGA = "vagas";
    private static final String ID_VAGA = "_id";
    private static final String ID_LOCADOR = "locador";
    private static final String VAGA_VALOR = "vaga_valor";
    private static final String VAGA_DESCRICAO = "vaga_descricao";
    private static final String VAGA_VAGA_MOBILIADA = "vaga_mobiliada"; //NAO É POSSIVEL SALVAR BOOLEAN, 1 = TRUE; 0 = FALSE
    private static final String VAGA_TIPO_VAGA = "vaga_tipo_vaga";
    private static final String VAGA_TIPO_RESIDENCIA = "vaga_tipo_residencia";

    //STRINGS DA TABELA DE MATCHES
    private static final String TABELA_MATCH = "match";
    private static final String ID_MATCH = "_id";
    private static final String MATCH_ID_VAGA = "match_vaga";
    private static final String MATCH_ID_LOCADOR = "match_idLocador";
    private static final String MATCH_ID_LOCATARIO = "match_idLocatario";
    private static final String MATCH_VALIDATION = "match_validation";

    //STRINGS DA TABELA DE FILTROS
    private static final String TABELA_FILTRO = "filtros";
    private static final String ID_FILTRO = "_id";
    private static final String FILTRO_USUARIO = "filtro_id_usuario";
    private static final String FILTRO_MOBILIADO = "filtro_mobiliado";
    private static final String FILTRO_TIPO_VAGA = "filtro_tipo_vaga";
    private static final String FILTRO_TIPO_RESIDENCIA = "filtro_tipo_residencia";
    private static final String FILTRO_VALOR_MINIMO = "filtro_valor_minimo";
    private static final String FILTRO_VALOR_MAXIMO = "filtro_valor_maximo";





    public static final String createTableUsuarioSql = "CREATE TABLE "+TABELA_USUARIO+"("
            + ID_USUARIO + " integer primary key autoincrement,"
            + NOME_USUARIO + " text,"
            + EMAIL_USUARIO + " text,"
            + SENHA_USUARIO + " text,"
            + PERFIL_USUARIO + " integer,"
            + USUARIO_FILTRO + " integer"
            +")";

    public static final String createTableVagaSql = "CREATE TABLE "+TABELA_VAGA+"("
            + ID_VAGA + " integer primary key autoincrement,"
            + ID_LOCADOR + " integer,"
            + VAGA_VALOR + " integer,"
            + VAGA_DESCRICAO + " text,"
            + VAGA_VAGA_MOBILIADA + " integer," //NAO É POSSIVEL SALVAR BOOLEAN, 1 = TRUE; 0 = FALSE
            + VAGA_TIPO_VAGA + " integer,"
            + VAGA_TIPO_RESIDENCIA+ " integer"
            +")";

         /*
        * DADOS REFERENTES A TABELA DE ITEMS ( VAGAS )
        */


    public static final String createTableMatchSql = "CREATE TABLE "+TABELA_MATCH+"("
            + ID_MATCH + " integer primary key autoincrement,"
            + MATCH_ID_VAGA + " integer,"
            + MATCH_ID_LOCADOR + " integer,"
            + MATCH_ID_LOCATARIO + " integer,"
            + MATCH_VALIDATION + " integer"
            +")";


    public static final String createTableFiltroSql = "CREATE TABLE "+TABELA_FILTRO+"("
            + ID_FILTRO + " integer primary key autoincrement,"
            + FILTRO_USUARIO + " integer,"
            + FILTRO_MOBILIADO + " integer,"
            + FILTRO_TIPO_RESIDENCIA + " integer,"
            + FILTRO_TIPO_VAGA + " integer,"
            + FILTRO_VALOR_MINIMO + " integer,"
            + FILTRO_VALOR_MAXIMO + " integer"
            +")";

        private static final int VERSAO = 12;


        public RoomatchDBHelper(Context context) {
            super(context, USUARIO_BANCO,null,VERSAO);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(createTableUsuarioSql);
            db.execSQL(createTableVagaSql);
            db.execSQL(createTableMatchSql);
            db.execSQL(createTableFiltroSql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_VAGA);
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_MATCH);
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_FILTRO);
            onCreate(db);
        }


        @Override
        public  void onOpen(SQLiteDatabase db){
            super.onOpen(db);
        }

}


