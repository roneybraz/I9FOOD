package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone
    ArrayList<Produtos> produto = new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("BUSCA");
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


        Produtos p = new Produtos(1,"Strogonoff light de frango","Feito com creme de ricota light no lugar do creme de leite, manteiga e queijo utilizados no prato tradicional. Perfeito para uma refeição leve e nutritiva." , 16.30,R.drawable.strogonofflightdefrango);
        produto.add(p);//

        p = new Produtos(2,"Salmão ao molho de maracujá ","Um generoso pedaço de salmão grelhado acompanhado de legumes, arroz integral e um delicioso molho de maracujá a parte." , 15.50,R.drawable.salmaoaomolhodemaracuja);
        produto.add(p);//

        p = new Produtos(3,"Peixe branco","Uma generosa posta de meca grelhada e temperada com ervas finas, acompanhada de ratatouille e um delicioso bobó de camarão." ,17.10,R.drawable.peixebranco);
        produto.add(p);//

        p = new Produtos(4,"Filé mignon ao molho funghi","Medalhão de filé mignon ao molho funghi, acompanhado de arroz integral e brócolis cozido." , 11.10,R.drawable.filemignonaomolhofunghi);
        produto.add(p);//

        p = new Produtos(5,"Bife à rolê com cenoura","Bife recheado com cenoura, acompanhado de arroz integral e brócolis cozido. " , 18.99,R.drawable.bifearolecomcenoura);
        produto.add(p);//

        p = new Produtos(6,"Spaguetti integral com carne desfiada","Spaguetti 100% integral al dente, acompanhado de uma suculenta carne de panela desfiada.. Perfeito para uma refeição leve e nutritiva." , 14.90,R.drawable.spaguettiintegral);
        produto.add(p);//

        p = new Produtos(7,"Risoto integral de filé mignon","Feito com creme de ricota light no lugar do creme de leite, manteiga e queijo utilizados no prato tradicional." , 19.99,R.drawable.risotointegraldefilemignon);
        produto.add(p);//

        p = new Produtos(8,"Spaguetti integral com frango","Delicioso spaguetti 100% integral al dente, acompanhado de um suculento frango ao molho vermelho." , 17.50,R.drawable.spaguettiintegralcomfrangoaomolhovermelho);
        produto.add(p);//

        p = new Produtos(9,"Frango com batata doce","Famoso escondidinho de peito de frango desfiado com purê de batata doce." , 13.10,R.drawable.frangocombatatadoce);
        produto.add(p);//

        p = new Produtos(10,"Frango xadrez","Mix de legumes, baixo teor de sódio e muito sabor." , 16.30,R.drawable.frangoxadrez);
        produto.add(p);//

        p = new Produtos(11,"Pão de queijo fit","Pão de Queijo Zero Gordura; Zero Lactose e Zero Glútena" , 14.25,R.drawable.paodequeijofit);
        produto.add(p);//

        p = new Produtos(12,"Torta de aipim com carne","Deliciosa torta sem glúten na massa e recheio de carne moída no molho de tomate." , 17.30,R.drawable.tortadeaipimcomcarne);
        produto.add(p);//

        p = new Produtos(13,"Esfiha integral de frango","Versão saudável da esfiha de frango desfiado da mamãe. " , 18.99,R.drawable.esfihaintegraldefrango);
        produto.add(p);//

        p = new Produtos(14,"Bolo integral de banana","Nossa versão saudável da cuca da vovó." , 10.99,R.drawable.bolointegraldebanana);
        produto.add(p);//

        p = new Produtos(15,"Panqueca integral de banana com whey","Deliciosa panqueca recheada de banana com cobertura de chocolate diet e whey protein." , 9.50,R.drawable.panquecaintegral);
        produto.add(p);//

        p = new Produtos(16,"Bolo de chocolate (zero açúcar)","Parece que tem açúcar, parece que tem farinha de trigo, mas não tem." , 10.50,R.drawable.bolodechocolate);
        produto.add(p);








        ListView lista = (ListView) findViewById(R.id.main_listaProduto);

        ArrayAdapter adpter = new ProdutoAdapter(this, R.layout.linha_produto, produto);

        lista.setAdapter(adpter);






    }





    private Context getContext()
    {
        return this;
    }




}
