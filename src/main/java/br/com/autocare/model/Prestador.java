package br.com.autocare.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Prestador extends Model {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nomeOficina;
    private String cep;
    private String email;
    private String senha;

    public Prestador() {
        this.CAMINHO_ARQUIVO = "prestador.dat";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Prestador\nID: " + this.id + "\nNome da Oficina: " + this.nomeOficina + "\nCEP: " + this.cep + "\nE-mail: " + this.email + "\nSenha: " + this.senha;
    }

    @Override
    public Prestador save() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.insert(this);
        } else {
            this.update(this);
        }

        return this;
    }

    public Model update(Model model) {
        Prestador modelAtualizado = (Prestador) model;

        ArrayList<Model> listaPrestador = this.select();
        boolean atualizado = false;
        int i = 0;

        while (i < listaPrestador.size() && !atualizado) {
            Prestador item = (Prestador) listaPrestador.get(i);

            if (Objects.equals(item.getId(), modelAtualizado.getId())) {
                listaPrestador.set(i, modelAtualizado);
                atualizado = true;
            }

            i++;
        }

        if(atualizado) {
            salvarLista(listaPrestador);
        }

        return model;
    }

    @Override
    public boolean delete() {
        ArrayList<Model> listaPrestador = this.select();
        boolean excluido = false;
        int i = 0;

        while (i < listaPrestador.size() && !excluido) {
            Prestador item = (Prestador) listaPrestador.get(i);

            if (Objects.equals(item.getId(), this.getId())) {
                listaPrestador.remove(i);
                excluido = true;
            }

            i++;
        }

        if(excluido) {
            salvarLista(listaPrestador);
            return true;
        }

        return false;
    }
}
