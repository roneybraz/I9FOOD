package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.util.ArrayList;

/**
 * Created by Televideo on 21/11/2017.
 */

public class EventoAdapter extends ArrayAdapter<Eventos> {
    Context contexto;
    ArrayList<Eventos> eventos;
    Document document;


    public EventoAdapter(Context context, int resource,ArrayList<Eventos> objects){
        super(context, resource, objects);
        this.contexto = context;
        this.eventos = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View linhaView = LayoutInflater.from(contexto).inflate(R.layout.linha_eventos, parent, false);


        TextView Titulo=(TextView)linhaView.findViewById(R.id.linha_eventos_Titulo);
        TextView Data = (TextView)linhaView.findViewById(R.id.linha_eventos_data);
        TextView Local = (TextView)linhaView.findViewById(R.id.linha_eventos_local);
        TextView Descricao = (TextView)linhaView.findViewById(R.id.linha_eventoDescricao);
        final ImageView Imagem=(ImageView) linhaView.findViewById(R.id.linha_Eventos_img);
        ImageView bntadd_evento=(ImageView) linhaView.findViewById(R.id.linha_evento_add);



        Titulo.setText(eventos.get(position).getNome_Evento());
        Descricao.setText(eventos.get(position).getDesc_Evento());
        Data.setText(eventos.get(position).getData_Evento());
        Local.setText(eventos.get(position).getLocal_Evento());
        Imagem.setImageResource(eventos.get(position).getImagem());




        bntadd_evento.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                Intent it=null;
                it= new Intent(getContext(),CredencialActivity.class);
                contexto.startActivity(it);

            }


        });





        return linhaView;





    }




}
