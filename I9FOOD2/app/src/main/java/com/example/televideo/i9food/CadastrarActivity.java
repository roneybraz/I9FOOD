package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class CadastrarActivity extends AppCompatActivity {


    Button btnSalvar;
    EditText txtCPF, txtNome, txtEndereco, txtFone,txtEmail,txtSenha;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);


        Realm.init(this);
        realm = Realm.getDefaultInstance();

        btnSalvar =(Button)findViewById(R.id.cadastrar_btnSalvar);

        txtCPF=(EditText)findViewById(R.id.cadastrar_txtCPF);
        txtNome=(EditText)findViewById(R.id.cadastrar_txtNOME);
        txtEndereco=(EditText)findViewById(R.id.cadastrar_txtEndereco);
        txtFone=(EditText)findViewById(R.id.cadastrar_txtFone);
        txtEmail=(EditText)findViewById(R.id.cadastrar_txtEmail);
        txtSenha=(EditText)findViewById(R.id.cadastrar_txtSenha);



        btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String CPF= txtCPF.getText().toString();
                    String nome= txtNome.getText().toString();
                    String endereco= txtEndereco.getText().toString();
                    String fone= txtFone.getText().toString();
                    String email= txtEmail.getText().toString();
                    String senha= txtSenha.getText().toString();


                    Usuario usuario = new Usuario();
                    usuario.setId(Usuario.autoIncrement());
                    usuario.setCpf(CPF);
                    usuario.setNome(nome);
                    usuario.setEndereco(endereco);
                    usuario.setFone(fone);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    realm.beginTransaction();
                    realm.copyToRealm(usuario);
                    realm.commitTransaction();

                    Intent ToCadastro = new Intent(getContext(),LoginActivity.class);
                    startActivity(ToCadastro);




                }
            });







    }
    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    private Context getContext()
    {
        return this;
    }
}
