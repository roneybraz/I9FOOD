package com.example.televideo.i9food;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Televideo on 17/11/2017.
 */

public class Pedidos extends RealmObject{



    @PrimaryKey

    private  int codpedido;
    private  int codproduto;
    private  int codUsuario;
    private String titulo;
    private String descricao;
    private Double preco;
    private int imagem;
    private boolean add=false;




    public int getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(int codpedido) {
        this.codpedido = codpedido;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsario) {
        this.codUsuario = codUsario;
    }


    public int getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto = codproduto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }



    public static int autoIncrement(){
        int key = 1;
        Realm realm = Realm.getDefaultInstance();
        try{
            key = realm.where(Pedidos.class).max("codpedido").intValue() + 1;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return key;
    }








}
