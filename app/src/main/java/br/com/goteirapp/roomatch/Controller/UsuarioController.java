package br.com.goteirapp.roomatch.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.model.Usuario;
import br.com.goteirapp.roomatch.sqlite.RoomatchDBHelper;
import br.com.goteirapp.roomatch.util.RoomatchUtil;

/**
 * Created by Gabriel on 19/07/2016.
 */


public class UsuarioController {

    private SQLiteDatabase db;
    private RoomatchDBHelper banco;

    public UsuarioController(Context context){
        banco = new RoomatchDBHelper(context);
    }

    public String insereDado(String nome, String email, String senha, int perfil){
        ContentValues valores;
        long resultado;

        String retorno = "";

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(RoomatchUtil.NOME_USUARIO, nome);
        valores.put(RoomatchUtil.EMAIL_USUARIO, email);
        valores.put(RoomatchUtil.SENHA_USUARIO, senha);
        valores.put(RoomatchUtil.PERFIL_USUARIO, perfil);
        valores.put(RoomatchUtil.USUARIO_FILTRO, 0);

        resultado = db.insert(RoomatchUtil.TABELA_USUARIO, null, valores);
        db.close();

        if (resultado ==-1) {
            retorno = "Erro ao inserir registro";

        } else {
            retorno = "Registro Inserido com sucesso";
        }


        return  retorno;
    }

    public Usuario validaLogin (String login, String senha){
        Usuario usuarioLogado = null;

        db = banco.getReadableDatabase();

        String[] colunas = new String[]{RoomatchUtil.ID_USUARIO,
                RoomatchUtil.NOME_USUARIO,
                RoomatchUtil.SENHA_USUARIO,
                RoomatchUtil.EMAIL_USUARIO,
                RoomatchUtil.PERFIL_USUARIO,
                RoomatchUtil.USUARIO_FILTRO};


        Cursor cursor = db.query(RoomatchUtil.TABELA_USUARIO,
                                colunas,
                                RoomatchUtil.NOME_USUARIO +" = ? AND " + RoomatchUtil.SENHA_USUARIO + " = ?",
                                new String[]{login,senha} ,
                                null,
                                null,
                                null);

        if (cursor.getCount() > 0){
            usuarioLogado = new Usuario();

            cursor.moveToFirst();
            usuarioLogado.setId(cursor.getString(0));
            usuarioLogado.setNome(cursor.getString(1));
            usuarioLogado.setSenha(cursor.getString(2));
            usuarioLogado.setEmail(cursor.getString(3));
            usuarioLogado.setTipoPerfil(cursor.getInt(4));
            usuarioLogado.setIdFiltro(cursor.getInt(5));

        }

        db.close();

        return usuarioLogado;
    }


    public Usuario getUsuario (Integer id){
        Usuario usuarioLogado = null;
        db = banco.getReadableDatabase();
        String[] colunas = new String[]{RoomatchUtil.ID_USUARIO,
                                        RoomatchUtil.NOME_USUARIO,
                                        RoomatchUtil.SENHA_USUARIO,
                                        RoomatchUtil.EMAIL_USUARIO,
                                        RoomatchUtil.PERFIL_USUARIO,
                                        RoomatchUtil.USUARIO_FILTRO};

        Cursor cursor = db.query(RoomatchUtil.TABELA_USUARIO,
                colunas,
                "_id = ?",
                new String[]{""+id},
                null,
                null,
                null);

        if (cursor.getCount() > 0){
            usuarioLogado = new Usuario();
            cursor.moveToFirst();
            usuarioLogado.setId(cursor.getString(0));
            usuarioLogado.setNome(cursor.getString(1));
            usuarioLogado.setSenha(cursor.getString(2));
            usuarioLogado.setEmail(cursor.getString(3));
            usuarioLogado.setTipoPerfil(cursor.getInt(4));
            usuarioLogado.setIdFiltro(cursor.getInt(5));

        }

        db.close();
        return usuarioLogado;
    }

    public int update(Usuario usuario){
        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(RoomatchUtil.EMAIL_USUARIO, usuario.getEmail());
        valores.put(RoomatchUtil.SENHA_USUARIO, usuario.getSenha());
        valores.put(RoomatchUtil.PERFIL_USUARIO, usuario.getTipoPerfil());
        valores.put(RoomatchUtil.USUARIO_FILTRO, usuario.getIdFiltro());

        int resultado = db.update(RoomatchUtil.TABELA_USUARIO,valores,RoomatchUtil.ID_USUARIO +" = " + usuario.getId(),null);

        return resultado;
    }


   /* public List<Usuario> getAllUsuarios (){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        db = banco.getReadableDatabase();
        String[] colunas = new String[]{RoomatchUtil.ID_USUARIO,
                RoomatchUtil.NOME_USUARIO,
                RoomatchUtil.SENHA_USUARIO,
                RoomatchUtil.EMAIL_USUARIO,
                RoomatchUtil.PERFIL_USUARIO,
                RoomatchUtil.VAGA_MOBILIADA,
                RoomatchUtil.TIPO_VAGA,
                RoomatchUtil.TIPO_RESIDENCIA};

        Cursor cursor = db.query(RoomatchUtil.TABELA_USUARIO,
                colunas,
                null,
                null,
                null,
                null,
                null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Usuario usuarioLogado = new Usuario();
                usuarioLogado.setId(cursor.getString(0));
                usuarioLogado.setNome(cursor.getString(1));
                usuarioLogado.setSenha(cursor.getString(2));
                usuarioLogado.setEmail(cursor.getString(3));
                usuarioLogado.setTipoPerfil(cursor.getInt(4));
                usuarioLogado.setMobiliada(cursor.getInt(5));
                usuarioLogado.setTipoVaga(cursor.getInt(6));
                usuarioLogado.setTipoMoradia(cursor.getInt(7));
                usuarios.add(usuarioLogado);
            } while (cursor.moveToNext());
        }

        db.close();
        return usuarios;
    }
*/
}
