package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("I9FOOD");
        mToolbar.setSubtitle("GOURMET");
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
                        break;
                    case R.id.action_pedidos:
                        it= new Intent(getContext(),PedidosActivity.class);
                        startActivity(it);
                        break;
                }



                return true;
            }
        });
        mToolbarBotao.inflateMenu(R.menu.menu_bottom);


        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //RealmResults<Usuario> realmUsuarios = realm.where(Usuario.class).findAll();


    }
    private Context getContext()
    {
        return this;
    }
}
