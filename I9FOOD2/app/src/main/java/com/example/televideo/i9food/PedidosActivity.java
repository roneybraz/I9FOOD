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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PedidosActivity extends AppCompatActivity {

    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone
    private TextView resultado;
    private Button comprar;
    private Button limpar;
    private Double valorTotal=0.00;
    ArrayList<Pedidos> pedidos = new ArrayList<>();
    private ListView listapedidos;
    private Realm realm2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        Realm.init(this);
        realm2 = Realm.getDefaultInstance();

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("PEDIDOS");
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
        try {


            if (getIntent().getExtras() != null) {

                Bundle args = getIntent().getExtras();
                mToolbarBotao.inflateMenu(R.menu.menu_bottom);
                String idProduto = args.getString("idproduto");
                String idTitulo = args.getString("idtitulo");
                String idpreco = args.getString("idpreco");
                String idimagem = args.getString("idimagem");
                SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                final String idusuario = prefs.getString("idusuario", "nenhum resultado");


                if (args.getString("idproduto") != null) {

                    Pedidos pe = new Pedidos();
                    pe.setCodpedido(Pedidos.autoIncrement());
                    pe.setCodproduto(Integer.parseInt(idProduto));
                    pe.setCodUsuario(Integer.parseInt(idusuario));
                    pe.setTitulo(idTitulo);
                    pe.setDescricao("Muito Bom");
                    pe.setPreco(Double.parseDouble(idpreco));

                    pe.setImagem(Integer.parseInt(idimagem));

                    pedidos.add(pe);


                    realm2.beginTransaction();
                    realm2.copyToRealm(pe);
                    realm2.commitTransaction();


                }
            }


            SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
            final String idusuario = prefs.getString("idusuario", "nenhum resultado");
            RealmResults<Pedidos> resultPedidos = realm2.where(Pedidos.class).equalTo("codUsuario", Integer.parseInt(idusuario)).findAll();

            realm2.beginTransaction();

            for (int i = 0; i < resultPedidos.size(); i++) {
                valorTotal = valorTotal + resultPedidos.get(i).getPreco();
            }

            realm2.commitTransaction();
            resultado = (TextView) findViewById(R.id.pedidos_lblREsultados);
            resultado.setText(valorTotal.toString());


            listapedidos = (ListView) findViewById(R.id.pedidos_listPedidos);
            final RealmResults<Pedidos> realmPedidos = realm2.where(Pedidos.class).findAll();
            PedidosAdapter adapter2 = new PedidosAdapter(this, R.layout.linha_pedidos, realmPedidos);
            listapedidos.setAdapter(adapter2);
        }catch (RuntimeException e){
            Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show();
        }



        comprar=(Button)findViewById(R.id.pedidos_btnComprar);
        limpar=(Button)findViewById(R.id.pedidos_btnLimpar);

         comprar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent it=null;
                 it= new Intent(getContext(),FinalizaCompraActivity.class);
                 startActivity(it);




             }
         });

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pedidos p = realm2.where(Pedidos.class).equalTo("codUsuario", 1).findFirst();
                RealmResults<Pedidos> realmLimpar = realm2.where(Pedidos.class).findAll();

                realm2.beginTransaction();
                realmLimpar.deleteAllFromRealm();
                realm2.commitTransaction();

                valorTotal=0.00;

                Intent it=null;
                it= new Intent(getContext(),PedidosActivity.class);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


            }
        });



    }





    private Context getContext()
    {
        return this;
    }
}
