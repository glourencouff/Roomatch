package br.com.goteirapp.roomatch;

import br.com.goteirapp.roomatch.model.Usuario;

/**
 * Created by Gabriel on 21/07/2016.
 */
public class ItemCandidatoListView {

    private Usuario usuario;
    private int idLike;
    private int idDislike;

    public int getIdLike() {
        return idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }

    public String getNomeUsuario() {
        return this.usuario.getNome();
    }

    public void setNomeUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdDislike() {
        return idDislike;
    }

    public void setIdDislike(int idDislike) {
        this.idDislike = idDislike;
    }

    public ItemCandidatoListView() {
        this("", -1, -1);

    }

    public ItemCandidatoListView(String texto, int idLike, int idDislike) {
        this.usuario.setNome(texto);
        this.idLike = idLike;
        this.idDislike = idDislike;
    }
}
