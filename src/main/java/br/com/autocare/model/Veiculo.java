package br.com.autocare.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Veiculo extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String CAMINHO_ARQUIVO = "veiculo.dat";

    private String id;
    private String modelo;
    private String placa;
    private String cor;

    public Veiculo(){}

    public Veiculo(String id, String modelo,  String placa, String cor) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Veiculo\nID: " + this.id + "\nmodelo: " + this.modelo + "\nplaca: " + this.placa + "\ncor: " + this.cor;
    }

    @Override
    public ArrayList<Model> select() {
        ArrayList<Model> listaVeiculos = new ArrayList<>();
        try  {
            File arq = new File(CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                listaVeiculos = (ArrayList<Model>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return listaVeiculos;
    }

    @Override
    public Veiculo save() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.insert(this);
        } else {
            this.update(this);
        }

        return this;
    }

    public Model insert(Model model) {
        ArrayList<Model> listaVeiculos = this.select();
        listaVeiculos.add(model);

        this.salvarLista(listaVeiculos);

        return model;
    }

    public Model update(Model model) {
        Veiculo modelAtualizado = (Veiculo) model;

        ArrayList<Model> listaVeiculos = this.select();
        boolean atualizado = false;
        int i = 0;

        while (i < listaVeiculos.size() && !atualizado) {
            Veiculo item = (Veiculo) listaVeiculos.get(i);

            if (Objects.equals(item.getId(), modelAtualizado.getId())) {
                listaVeiculos.set(i, modelAtualizado);
                atualizado = true;
            }

            i++;
        }

        if(atualizado) {
            salvarLista(listaVeiculos);
        }

        return model;
    }

    @Override
    public boolean delete() {
        ArrayList<Model> listaVeiculos = this.select();
        boolean excluido = false;
        int i = 0;

        while (i < listaVeiculos.size() && !excluido) {
            Veiculo item = (Veiculo) listaVeiculos.get(i);

            if (Objects.equals(item.getId(), this.getId())) {
                listaVeiculos.remove(i);
                excluido = true;
            }

            i++;
        }

        if(excluido) {
            salvarLista(listaVeiculos);
            return true;
        }

        return false;
    }


    private void salvarLista(ArrayList<Model> listaVeiculos) {
        FileOutputStream f;
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (!arq.exists()) {
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(listaVeiculos);
            oos.close();
            System.out.println("Lista salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }
}
