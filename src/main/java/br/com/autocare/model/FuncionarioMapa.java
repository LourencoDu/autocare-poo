package br.com.autocare.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class FuncionarioMapa extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String CAMINHO_ARQUIVO = "funcionariomapa.dat";

    private String id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String localizacao;

    public FuncionarioMapa() {}

    public FuncionarioMapa(String id, String nome, String telefone, String email, String senha, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.localizacao = localizacao;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Funcionário\nID: " + this.id +
                "\nNome: " + this.nome +
                "\nTelefone: " + this.telefone +
                "\nE-mail: " + this.email +
                "\nSenha: " + this.senha +
                "\nLocalização: " + this.localizacao;
    }

    @Override
    public ArrayList<Model> select() {
        ArrayList<Model> lista = new ArrayList<>();
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Model>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public FuncionarioMapa save() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.insert(this);
        } else {
            this.update(this);
        }

        return this;
    }

    public Model insert(Model model) {
        ArrayList<Model> lista = this.select();
        lista.add(model);
        this.salvarLista(lista);
        return model;
    }

    public Model update(Model model) {
        FuncionarioMapa modelAtualizado = (FuncionarioMapa) model;

        ArrayList<Model> lista = this.select();
        boolean atualizado = false;
        int i = 0;

        while (i < lista.size() && !atualizado) {
            FuncionarioMapa item = (FuncionarioMapa) lista.get(i);

            if (Objects.equals(item.getId(), modelAtualizado.getId())) {
                lista.set(i, modelAtualizado);
                atualizado = true;
            }

            i++;
        }

        if (atualizado) {
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
            FuncionarioMapa item = (FuncionarioMapa) lista.get(i);

            if (Objects.equals(item.getId(), this.getId())) {
                lista.remove(i);
                excluido = true;
            }

            i++;
        }

        if (excluido) {
            salvarLista(lista);
            return true;
        }

        return false;
    }
}
