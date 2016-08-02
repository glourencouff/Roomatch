package br.com.goteirapp.roomatch.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.goteirapp.roomatch.model.Match;
import br.com.goteirapp.roomatch.model.Usuario;
import br.com.goteirapp.roomatch.model.Vaga;
import br.com.goteirapp.roomatch.sqlite.RoomatchDB;
import br.com.goteirapp.roomatch.sqlite.RoomatchDBHelper;
import br.com.goteirapp.roomatch.util.RoomatchUtil;

/**
 * Created by carlos on 21/07/2016.
 */
public class MatchController {


    private SQLiteDatabase db;
    private RoomatchDBHelper banco;

    public MatchController(Context context){
        banco = new RoomatchDBHelper(context);
    }

        public String insereDado(int idVaga, int idLocatario, int idLocador, int matchValidation) {
            ContentValues valores;

            db = banco.getWritableDatabase();

            long resultado;

            String retorno = "";

            valores = new ContentValues();
            valores.put(RoomatchUtil.MATCH_ID_VAGA, idVaga);
            valores.put(RoomatchUtil.MATCH_ID_LOCATARIO, idLocatario);
            valores.put(RoomatchUtil.MATCH_ID_LOCADOR, idLocador);
            valores.put(RoomatchUtil.MATCH_VALIDATION, matchValidation);

            resultado = db.insert(RoomatchUtil.TABELA_MATCH, null, valores);
            db.close();

            if (resultado == -1) {
                retorno = "Ops.. Ocorreu algum problema";

            } else {
                retorno = "Vaga Curtida";
            }

            return retorno;
        }

        public List<Match> getUsersVaga(Integer idLocatario) {
            db = banco.getReadableDatabase();
            List<Match> matches = new ArrayList<>();

            String[] colunas = new String[]{RoomatchUtil.ID_MATCH,
                    RoomatchUtil.MATCH_ID_VAGA,
                    RoomatchUtil.MATCH_ID_LOCATARIO,
                    RoomatchUtil.MATCH_ID_LOCADOR,
                    RoomatchUtil.MATCH_VALIDATION};

            Cursor cursor = db.query(RoomatchUtil.TABELA_MATCH,
                    colunas,
                    "match_idLocador = ?",
                    new String[]{"" + idLocatario},
                    null,
                    null,
                    null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do{
                    if(cursor.getInt(4) != 0) {
                        Match match = new Match();
                        match.setId(cursor.getInt(0));
                        match.setIdVaga(cursor.getInt(1));
                        match.setIdLocatario(cursor.getInt(2));
                        match.setMatchValidation(cursor.getInt(4));
                        match.setIdLocador(3);
                        matches.add(match); //Pega lista de usuários que curtiram vagas do locador
                    }
                }while (cursor.moveToNext());
            }

            return matches;
        }

        public List<Match> getUsersLike(int idLocatario) {

            db = banco.getReadableDatabase();

            List<Match> matches = new ArrayList<>();

            String[] colunas = new String[]{RoomatchUtil.ID_MATCH,
                    RoomatchUtil.MATCH_ID_VAGA,
                    RoomatchUtil.MATCH_ID_LOCATARIO,
                    RoomatchUtil.MATCH_ID_LOCADOR,
                    RoomatchUtil.MATCH_VALIDATION};

            Cursor cursor = db.query(RoomatchUtil.TABELA_MATCH,
                    colunas,
                    RoomatchUtil.MATCH_ID_LOCATARIO+" = ? and match_validation = 1",
                    new String[]{"" + idLocatario},
                    null,
                    null,
                    null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {
                    Match match = new Match();
                    match.setId(cursor.getInt(0));
                    match.setIdVaga(cursor.getInt(1));
                    match.setIdLocador(cursor.getInt(3));
                    match.setIdLocatario(cursor.getInt(2));
                    match.setMatchValidation(cursor.getInt(4));

                    matches.add(match);

                } while (cursor.moveToNext());
            }
            db.close();
            return matches;
        }




    public boolean deleteMatch(int idLocatario, int idVaga) {
        db = banco.getReadableDatabase();

        int resultado = db.delete(RoomatchUtil.TABELA_MATCH,
                "match_idLocatario = ? and match_vaga = ?",
                new String[]{"" + idLocatario, "" + idVaga});

        if (resultado == 0) {
            return false;
        }else{
            return true;
        }
    }

    public boolean updateMatch(int idLocatario, int idVaga, int estadoLike){
        db = banco.getWritableDatabase();

        String[] colunas = new String[]{RoomatchUtil.ID_MATCH,
                RoomatchUtil.MATCH_ID_VAGA,
                RoomatchUtil.MATCH_ID_LOCATARIO,
                RoomatchUtil.MATCH_ID_LOCADOR,
                RoomatchUtil.MATCH_VALIDATION};

        ContentValues cv = new ContentValues();
        cv.put("match_validation",estadoLike);

        int resultado = db.update(RoomatchUtil.TABELA_MATCH,
                cv,
                "match_idLocatario = ? and match_vaga = ?",
                new String[]{"" + idLocatario, "" + idVaga});

        if (resultado == 0) {
            return false;
        }else{
            return true;
        }
    }


    public boolean hasLike(int idLocatario, int idVaga) {

        db = banco.getReadableDatabase();
        Match match = new Match();
        boolean resultado = false;
        String[] colunas = new String[]{RoomatchUtil.ID_MATCH,
                RoomatchUtil.MATCH_ID_VAGA,
                RoomatchUtil.MATCH_ID_LOCATARIO,
                RoomatchUtil.MATCH_ID_LOCADOR,
                RoomatchUtil.MATCH_VALIDATION};

        Cursor cursor = db.query(RoomatchUtil.TABELA_MATCH,
                colunas,
                "match_idLocatario = ? and match_vaga = ?",
                new String[]{"" + idLocatario, "" + idVaga},
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            if (cursor.getInt(4) == 1){
                resultado = true;
            }
        }
        return resultado;
    }

}
