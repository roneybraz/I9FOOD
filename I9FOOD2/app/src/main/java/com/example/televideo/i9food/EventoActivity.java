package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class EventoActivity extends AppCompatActivity {

    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone
    ArrayList<Eventos> eventos = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("EVENTOS");
        //mToolbar.setSubtitle("GOURMET");
        setSupportActionBar(mToolbar);

        mToolbarBotao=(Toolbar)findViewById(R.id.inc_tb_botao);
        mToolbarBotao.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                Intent it=null;

                switch (menuitem.getItemId()){
                    case R.id.action_busca:
                        it= new Intent(getContext(),MainActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_pedidos:
                        it= new Intent(getContext(),PedidosActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_eventos:
                        it= new Intent(getContext(),EventoActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_perfil:
                        it= new Intent(getContext(),PerfilActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                }



                return true;
            }
        });
        mToolbarBotao.inflateMenu(R.menu.menu_bottom);

        Eventos e = new Eventos(1,"Degustação de Alimentos Saudáveis","Inauguração da plataforma I9FOOD.","22/11/2017","Alfenas MG",R.drawable.local_alfenas);
        eventos.add(e);

        ListView lista = (ListView) findViewById(R.id.eventos_listview);

        ArrayAdapter adpter = new EventoAdapter(this, R.layout.linha_eventos, eventos);

        lista.setAdapter(adpter);



    }
    private Context getContext()
    {
        return this;
    }
}
