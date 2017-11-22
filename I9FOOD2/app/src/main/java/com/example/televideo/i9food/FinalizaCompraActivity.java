package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FinalizaCompraActivity extends AppCompatActivity {

    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone

    private Button finaliza;
    private Button cancela;

    ArrayList<Pedidos> pedidos = new ArrayList<>();
    private ListView listapedidos;
    private Realm realm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_compra);

        Realm.init(this);
        realm2 = Realm.getDefaultInstance();

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("PAGAR");
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

        final Spinner spinnerTipoCartao = (Spinner) findViewById(R.id.finaliza_tipoCartão);
        final List<String> list_tipoCartão = new ArrayList<String>();
        list_tipoCartão.add("MASTERCARD");
        list_tipoCartão.add("VISA");
        list_tipoCartão.add("AMERICAN EXPRESS");
        list_tipoCartão.add("ELO");


        ArrayAdapter<String> dataAdapterTipoCartao = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_tipoCartão);
        dataAdapterTipoCartao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoCartao.setAdapter(dataAdapterTipoCartao);



        final Spinner spinnerMes = (Spinner) findViewById(R.id.finaliza_mes);
        final List<String> list_mes = new ArrayList<String>();
        list_mes.add("12");
        list_mes.add("11");
        list_mes.add("10");
        list_mes.add("09");
        list_mes.add("08");
        list_mes.add("07");
        list_mes.add("06");
        list_mes.add("05");
        list_mes.add("04");
        list_mes.add("03");
        list_mes.add("02");
        list_mes.add("01");


        ArrayAdapter<String> dataAdapterMes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_mes);
        dataAdapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(dataAdapterMes);






        final Spinner spinnerAno = (Spinner) findViewById(R.id.finaliza_ano);
        final List<String> list_ano = new ArrayList<String>();
        list_ano.add("25");
        list_ano.add("24");
        list_ano.add("23");
        list_ano.add("22");
        list_ano.add("20");
        list_ano.add("21");
        list_ano.add("20");
        list_ano.add("19");
        list_ano.add("18");
        list_ano.add("17");


        ArrayAdapter<String> dataAdapterAno = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_ano);
        dataAdapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAno.setAdapter(dataAdapterAno);




        final Spinner spinnerParcela = (Spinner) findViewById(R.id.finaliza_parcela);
        final List<String> list_parcela = new ArrayList<String>();
        list_parcela.add("1");
        list_parcela.add("2");
        list_parcela.add("3");
        list_parcela.add("4");
        list_parcela.add("5");
        list_parcela.add("6");
        list_parcela.add("7");
        list_parcela.add("8");
        list_parcela.add("9");
        list_parcela.add("10");



        ArrayAdapter<String> dataAdapterParcela= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_parcela);
        dataAdapterTipoCartao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParcela.setAdapter(dataAdapterParcela);


        finaliza=(Button)findViewById(R.id.finaliza_btnFinaliza);
        cancela=(Button)findViewById(R.id.finaliza_btnCancela);

        finaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<Pedidos> realmLimpar = realm2.where(Pedidos.class).findAll();

                realm2.beginTransaction();
                realmLimpar.deleteAllFromRealm();
                realm2.commitTransaction();

                Intent it=null;
                it= new Intent(getContext(),PedidosActivity.class);
                startActivity(it);
                Toast.makeText(FinalizaCompraActivity.this, "COMPRA REALIZADA COM SUCESSO!", Toast.LENGTH_SHORT).show();

            }
        });


        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it=null;
                it= new Intent(getContext(),PedidosActivity.class);
                startActivity(it);
            }
        });






    }
    private Context getContext()
    {
        return this;
    }
}
