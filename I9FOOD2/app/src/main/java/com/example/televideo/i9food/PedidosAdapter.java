package com.example.televideo.i9food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Televideo on 24/09/2017.
 */

public class PedidosAdapter extends ArrayAdapter<Pedidos> {

    Context contexto;
    Realm realm;
    RealmResults<Pedidos> pedidos;


    public PedidosAdapter(Context context, int resource, RealmResults<Pedidos> objects){
        super(context, resource, objects);
        this.contexto = context;
        this.pedidos = objects;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        final View linhaView = LayoutInflater.from(contexto).inflate(R.layout.linha_pedidos, parent, false);




        TextView Titulo=(TextView)linhaView.findViewById(R.id.linha_pedidos_Titulo);
        TextView Preco = (TextView)linhaView.findViewById(R.id.linha_pedidos_Preco);
        ImageView imagem = (ImageView)linhaView.findViewById(R.id.linha_pedidos_img);
        ImageView bntdelete=(ImageView)linhaView.findViewById(R.id.linha_pedidos_delete);

        Titulo.setText(pedidos.get(position).getTitulo());
        Preco.setText(Double.toString(pedidos.get(position).getPreco()));
        imagem.setImageResource(pedidos.get(position).getImagem());




        bntdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=null;



                realm = Realm.getDefaultInstance();

                int idPedido = position;

                Pedidos p = realm.where(Pedidos.class).equalTo("codpedido", idPedido).findFirst();

                realm.beginTransaction();
                pedidos.deleteFromRealm(position);
                realm.commitTransaction();



                Activity activity = (Activity)contexto;
                it= new Intent(getContext(),PedidosActivity.class);
                contexto.startActivity(it);
                activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);



                Toast.makeText(contexto, "ITEM REMOVIDO!", Toast.LENGTH_SHORT).show();




            }


        });



        return linhaView;



    }





}
