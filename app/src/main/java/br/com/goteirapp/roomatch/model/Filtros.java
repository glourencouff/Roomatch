package br.com.goteirapp.roomatch.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gabriel on 24/07/2016.
 */
public class Filtros implements Parcelable{

    private int id;
    private int idUsuario;
    private int mobiliado;
    private int tipoVaga;
    private int tipoResidencia;
    private int minValor;
    private int maxValor;

    public int getMobiliado() {
        return mobiliado;
    }

    public void setMobiliado(int mobiliado) {
        this.mobiliado = mobiliado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(int tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public int getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(int tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public int getMinValor() {
        return minValor;
    }

    public void setMinValor(int minValor) {
        this.minValor = minValor;
    }

    public int getMaxValor() {
        return maxValor;
    }

    public void setMaxValor(int maxValor) {
        this.maxValor = maxValor;
    }

    public Filtros(){
    }


    protected Filtros(Parcel in) {
        id = in.readInt();
        idUsuario = in.readInt();
        mobiliado = in.readInt();
        tipoVaga = in.readInt();
        tipoResidencia = in.readInt();
        minValor = in.readInt();
        maxValor = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idUsuario);
        dest.writeInt(mobiliado);
        dest.writeInt(tipoVaga);
        dest.writeInt(tipoResidencia);
        dest.writeInt(minValor);
        dest.writeInt(maxValor);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Filtros> CREATOR = new Parcelable.Creator<Filtros>() {
        @Override
        public Filtros createFromParcel(Parcel in) {
            return new Filtros(in);
        }

        @Override
        public Filtros[] newArray(int size) {
            return new Filtros[size];
        }
    };
}
