package com.example.televideo.i9food;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Televideo on 15/11/2017.
 */

public class Usuario extends RealmObject {



    @PrimaryKey

    private int id;
    private String cpf;
    private String nome;
    private String endereco;
    private String fone;
    private String email;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
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


    public static int autoIncrement(){
        int key = 1;
        Realm realm = Realm.getDefaultInstance();
        try{
            key = realm.where(Usuario.class).max("id").intValue() + 1;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return key;
    }








}
