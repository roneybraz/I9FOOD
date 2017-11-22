package com.example.televideo.i9food;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Televideo on 24/09/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produtos> {

    Context contexto;
    ArrayList<Produtos>produto;


    public ProdutoAdapter(Context context, int resource,ArrayList<Produtos> objects){
        super(context, resource, objects);
        this.contexto = context;
        this.produto = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View linhaView = LayoutInflater.from(contexto).inflate(R.layout.linha_produto, parent, false);


        TextView Titulo=(TextView)linhaView.findViewById(R.id.linha_Titulo);
        TextView Descricao = (TextView)linhaView.findViewById(R.id.linha_Descricao);
        TextView Preco = (TextView)linhaView.findViewById(R.id.linha_Preco);
        ImageView add=(ImageView) linhaView.findViewById(R.id.linha_bntADD);


        ImageView imagem = (ImageView)linhaView.findViewById(R.id.linha_img);
        Titulo.setText(produto.get(position).getTitulo());
        Descricao.setText(produto.get(position).getDescricao());
        Preco.setText(Double.toString(produto.get(position).getPreco()));
        imagem.setImageResource(produto.get(position).getImagem());



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=null;



                it = new Intent(getContext(), PedidosActivity.class);
                Bundle params = new Bundle();
                params.putString("idproduto", String.valueOf(produto.get(position).getCodproduto()));
                params.putString("idtitulo", String.valueOf(produto.get(position).getTitulo()));
                params.putString("idimagem", String.valueOf(produto.get(position).getImagem()));
                params.putString("idpreco", String.valueOf(produto.get(position).getPreco()));

                it.putExtras(params);
                contexto.startActivity(it);




                //Toast.makeText(contexto, produto.get(position).getCodproduto()+"ADD OK", Toast.LENGTH_SHORT).show();


            }
        });



        return linhaView;



    }

}
