package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PerfilActivity extends AppCompatActivity {

    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone
    private TextView nome;
    private TextView email;
    ArrayList<Pedidos> pedidos = new ArrayList<>();
    private ListView listapedidos;
    private Realm realm2;
    private ImageView config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Realm.init(this);
        realm2 = Realm.getDefaultInstance();

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("EU");
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


        nome=(TextView)findViewById(R.id.perfil_lblnome);
        email=(TextView)findViewById(R.id.perfil_lblemail);


        SharedPreferences prefs= getSharedPreferences("login",Context.MODE_PRIVATE);
        String NomeUsuario= prefs.getString("nomeUsuario", "nenhum resultado");
        String EmailUsuario= prefs.getString("email", "nenhum resultado");

        nome.setText(NomeUsuario);
        email.setText(EmailUsuario);



        config=(ImageView) findViewById(R.id.perfil_bntConfig);

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=null;
                Bundle params = new Bundle();
                it= new Intent(getContext(),EditPerfilActivity.class);
                params.putString("idedit", "edit");
                it.putExtras(params);
                startActivity(it);









            }
        });










    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_SAIR);
        final MenuItem itemSocial=menu.findItem(R.id.action_redesocial);


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it=null;
                switch (item.getItemId()) {
                    case R.id.action_SAIR:
                        RealmResults<Pedidos> realmLimpar = realm2.where(Pedidos.class).findAll();
                        realm2.beginTransaction();
                        realmLimpar.deleteAllFromRealm();
                        realm2.commitTransaction();

                        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = prefs.edit();


                        ed.putString("idusuario", "");
                        ed.putString("nomeUsuario", "");
                        ed.putString("email", "");
                        ed.putString("senha", "");

                        ed.apply();



                        it = new Intent(getContext(), LoginActivity.class);
                        startActivity(it);

                        break;

                }
                return true;
            }
        });

        itemSocial.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it=null;
                switch (itemSocial.getItemId()){
                    case R.id.action_redesocial:
                        it = new Intent(getContext(), SocialActivity.class);
                        startActivity(it);
                        break;
                }
                return true;
            }
        });


        return true;
    }

    private Context getContext()
    {
        return this;
    }
}
