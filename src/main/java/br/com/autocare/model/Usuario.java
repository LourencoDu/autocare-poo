package br.com.autocare.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Usuario extends Model {
    private String id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;

    public Usuario() {
        this.CAMINHO_ARQUIVO = "usuario.dat";
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        return "Usuário\nID: " + this.id + "\nNome: " + this.nome + "\nSobrenome: " + this.sobrenome + "\nTelefone: " + this.telefone + "\nE-mail: " + this.email + "\nSenha: " + this.senha;
    }

    @Override
    public Usuario save() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.insert(this);
        } else {
            this.update(this);
        }

        return this;
    }

    public Model update(Model model) {
        Usuario modelAtualizado = (Usuario) model;

        ArrayList<Model> lista = this.select();
        boolean atualizado = false;
        int i = 0;

        while (i < lista.size() && !atualizado) {
            Usuario item = (Usuario) lista.get(i);

            if (Objects.equals(item.getId(), modelAtualizado.getId())) {
                lista.set(i, modelAtualizado);
                atualizado = true;
            }

            i++;
        }

        if(atualizado) {
            salvarLista(lista);
        }

        return model;
    }

    @Override
    public boolean delete() {
        ArrayList<Model> lista = this.select();
        boolean excluido = false;
        int i = 0;

        while (i < lista.size() && !excluido) {
            Usuario item = (Usuario) lista.get(i);

            if (Objects.equals(item.getId(), this.getId())) {
                lista.remove(i);
                excluido = true;
            }

            i++;
        }

        if(excluido) {
            salvarLista(lista);
            return true;
        }

        return false;
    }
}
