package com.example.televideo.i9food;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Televideo on 21/11/2017.
 */

public class Eventos {




    private int id_evento;



    private int imagem;
    private String nome_Evento;
    private String desc_Evento;
    private String data_Evento;
    private String local_Evento;

    public Eventos(int id_evento, String nome_Evento,String desc_Evento, String data_Evento, String local_Evento,int imagem) {
        this.id_evento = id_evento;
        this.nome_Evento = nome_Evento;
        this.desc_Evento = desc_Evento;
        this.data_Evento = data_Evento;
        this.local_Evento = local_Evento;
        this.imagem=imagem;
    }

    public String getDesc_Evento() {
        return desc_Evento;
    }

    public void setDesc_Evento(String desc_Evento) {
        this.desc_Evento = desc_Evento;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNome_Evento() {
        return nome_Evento;
    }

    public void setNome_Evento(String nome_Evento) {
        this.nome_Evento = nome_Evento;
    }

    public String getData_Evento() {
        return data_Evento;
    }

    public void setData_Evento(String data_Evento) {
        this.data_Evento = data_Evento;
    }

    public String getLocal_Evento() {
        return local_Evento;
    }

    public void setLocal_Evento(String local_Evento) {
        this.local_Evento = local_Evento;
    }
}
