package br.com.goteirapp.roomatch.model;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by carlos on 21/07/2016.
 */
public class Vaga implements Parcelable{

    public Vaga() {
    }

    public int id;
    private int locador;
    private int vagaValor;
    private String vagaDescricao;
    private int vagaMobiliada;
    private int vagaTipoVaga;
    private int vagaTipoMoradia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocador() {
        return locador;
    }

    public void setLocador(int locador) {
        this.locador = locador;
    }

    public int getVagaValor() {
        return vagaValor;
    }

    public void setVagaValor(int vagaValor) {
        this.vagaValor = vagaValor;
    }

    public String getVagaDescricao() {
        return vagaDescricao;
    }

    public void setVagaDescricao(String vagaDescricao) {
        this.vagaDescricao = vagaDescricao;
    }

    public int getVagaMobiliada() {
        return vagaMobiliada;
    }

    public void setVagaMobiliada(int vagaMobiliada) {
        this.vagaMobiliada = vagaMobiliada;
    }

    public int getVagaTipoVaga() {
        return vagaTipoVaga;
    }

    public void setVagaTipoVaga(int vagaTipoVaga) {
        this.vagaTipoVaga = vagaTipoVaga;
    }

    public int getVagaTipoMoradia() {
        return vagaTipoMoradia;
    }

    public void setVagaTipoMoradia(int vagaTipoMoradia) {
        this.vagaTipoMoradia = vagaTipoMoradia;
    }

    protected Vaga(Parcel in) {
        id = in.readInt();
        locador = in.readInt();
        vagaValor = in.readInt();
        vagaDescricao = in.readString();
        vagaMobiliada = in.readInt();
        vagaTipoVaga = in.readInt();
        vagaTipoMoradia = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(locador);
        dest.writeInt(vagaValor);
        dest.writeString(vagaDescricao);
        dest.writeInt(vagaMobiliada);
        dest.writeInt(vagaTipoVaga);
        dest.writeInt(vagaTipoMoradia);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Vaga> CREATOR = new Parcelable.Creator<Vaga>() {
        @Override
        public Vaga createFromParcel(Parcel in) {
            return new Vaga(in);
        }

        @Override
        public Vaga[] newArray(int size) {
            return new Vaga[size];
        }
    };
}
