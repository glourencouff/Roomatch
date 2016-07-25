package br.com.goteirapp.roomatch.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gabriel on 15/07/2016.
 */
public class Usuario implements Parcelable {

    public Usuario() {
    }

    private String id;
    private String nome;
    private String senha;
    private String email;
    private int tipoPerfil;
    private int idFiltro;


    public int getIdFiltro() { return idFiltro;    }

    public void setIdFiltro(int idFiltro) {   this.idFiltro = idFiltro;   }

    public int getTipoPerfil() { return tipoPerfil; }

    public void setTipoPerfil(int tipoPerfil) {  this.tipoPerfil = tipoPerfil;  }

    public String getEmail() {  return email;    }

    public void setEmail(String email) {   this.email = email;    }

    public String getNome() {   return nome;    }

    public void setNome(String nome) {   this.nome = nome;  }

    public String getSenha() {   return senha;   }

    public void setSenha(String senha) { this.senha = senha; }

    public String getId() {return id;  }

    public void setId(String id) {this.id = id; }

    protected Usuario(Parcel in) {
        id = in.readString();
        nome = in.readString();
        senha = in.readString();
        tipoPerfil = in.readInt();
        idFiltro = in.readInt();
        email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nome);
        dest.writeString(senha);
        dest.writeInt(tipoPerfil);
        dest.writeInt(idFiltro);
        dest.writeString(email);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
