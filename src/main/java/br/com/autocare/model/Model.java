package br.com.autocare.model;

import java.util.ArrayList;

public interface Model {
    ArrayList<Model> select();
    Model save();
    Model insert(Model model);
}
