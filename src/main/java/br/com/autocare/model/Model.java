package br.com.autocare.model;

import java.io.*;
import java.util.ArrayList;

public abstract class Model implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String CAMINHO_ARQUIVO;

    abstract Model save();
    abstract boolean delete();

    public ArrayList<Model> select() {
        ArrayList<Model> lista = new ArrayList<>();
        try  {
            File arq = new File(this.CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.CAMINHO_ARQUIVO));
                lista = (ArrayList<Model>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    protected void salvarLista(ArrayList<Model> lista) {
        try {
            File arq = new File(this.CAMINHO_ARQUIVO);
            if (!arq.exists()) {
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(lista);
            oos.close();
            System.out.println("Lista salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public Model insert(Model model) {
        ArrayList<Model> lista = this.select();
        lista.add(model);

        this.salvarLista(lista);

        return model;
    }
}
