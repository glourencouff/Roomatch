package br.com.goteirapp.roomatch.model;

/**
 * Created by carlos on 21/07/2016.
 */
public class Match {
    public Match() {
    }

    public int id;
    private int idLocador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(int idLocador) {
        this.idLocador = idLocador;
    }

    public int getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(int idLocatario) {
        this.idLocatario = idLocatario;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public int getMatchValidation() {
        return matchValidation;
    }

    public void setMatchValidation(int matchValidation) {
        this.matchValidation = matchValidation;
    }

    private int idLocatario;
    private int idVaga;
    private int matchValidation;
}
