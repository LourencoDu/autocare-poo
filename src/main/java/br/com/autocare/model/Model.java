package br.com.autocare.model;

import java.util.ArrayList;

public abstract class Model {
    abstract ArrayList<Model> select();
    abstract Model save();
    abstract boolean delete();
}
