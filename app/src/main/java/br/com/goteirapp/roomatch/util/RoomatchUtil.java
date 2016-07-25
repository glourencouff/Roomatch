package br.com.goteirapp.roomatch.util;

/**
 * Created by Gabriel on 19/07/2016.
 */
public final class RoomatchUtil{


    public static final String TABELA_USUARIO = "usuarios";
    public static final String ID_USUARIO = "_id";
    public static final String NOME_USUARIO = "login";
    public static final String EMAIL_USUARIO = "email";
    public static final String SENHA_USUARIO = "senha";
    public static final String PERFIL_USUARIO = "perfil";
    public static final String USUARIO_FILTRO = "id_filtro";

    public static final String TABELA_VAGA = "vagas";
    public static final String ID_VAGA = "_id";
    public static final String ID_LOCADOR = "locador";
    public static final String VAGA_VALOR = "vaga_valor";
    public static final String VAGA_DESCRICAO = "vaga_descricao";
    public static final String VAGA_VAGA_MOBILIADA = "vaga_mobiliada"; //NAO É POSSIVEL SALVAR BOOLEAN, 1 = TRUE; 0 = FALSE
    public static final String VAGA_TIPO_VAGA = "vaga_tipo_vaga";
    public static final String VAGA_TIPO_RESIDENCIA = "vaga_tipo_residencia";


    public static final String TABELA_MATCH = "match";
    public static final String ID_MATCH = "_id";
    public static final String MATCH_ID_VAGA = "match_vaga";
    public static final String MATCH_ID_LOCADOR = "match_idLocador";
    public static final String MATCH_ID_LOCATARIO = "match_idLocatario";
    public static final String MATCH_VALIDATION = "match_validation";

    public static final String TABELA_FILTRO = "filtros";
    public static final String ID_FILTRO = "_id";
    public static final String FILTRO_USUARIO = "filtro_id_usuario";
    public static final String FILTRO_MOBILIADO = "filtro_mobiliado";
    public static final String FILTRO_TIPO_VAGA = "filtro_tipo_vaga";
    public static final String FILTRO_TIPO_RESIDENCIA = "filtro_tipo_residencia";
    public static final String FILTRO_VALOR_MINIMO = "filtro_valor_minimo";
    public static final String FILTRO_VALOR_MAXIMO = "filtro_valor_maximo";

}
