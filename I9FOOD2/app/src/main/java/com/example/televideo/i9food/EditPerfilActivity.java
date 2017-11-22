package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class EditPerfilActivity extends AppCompatActivity {



    Button btnSalvar;
    EditText txtCPF, txtNome, txtEndereco, txtFone,txtEmail,txtSenha;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);



        //Realm.init(this);
        realm = Realm.getDefaultInstance();

        btnSalvar =(Button)findViewById(R.id.cadastrar_btnSalvar);

        txtCPF=(EditText)findViewById(R.id.cadastrar_txtCPF);
        txtNome=(EditText)findViewById(R.id.cadastrar_txtNOME);
        txtEndereco=(EditText)findViewById(R.id.cadastrar_txtEndereco);
        txtFone=(EditText)findViewById(R.id.cadastrar_txtFone);
        txtEmail=(EditText)findViewById(R.id.cadastrar_txtEmail);
        txtSenha=(EditText)findViewById(R.id.cadastrar_txtSenha);

        SharedPreferences prefs= getSharedPreferences("login", Context.MODE_PRIVATE);
        String EmailUsuario= prefs.getString("email", "nenhum resultado");


        final Usuario usuario = realm.where(Usuario.class).equalTo("email", EmailUsuario).findFirst();
        final int id=usuario.getId();

        txtCPF.setText(usuario.getCpf());
        txtNome.setText(usuario.getNome());
        txtEndereco.setText(usuario.getEndereco());
        txtFone.setText(usuario.getFone());
        txtEmail.setText(usuario.getEmail());
        txtSenha.setText(usuario.getSenha());


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CPF= txtCPF.getText().toString();
                String nome= txtNome.getText().toString();
                String endereco= txtEndereco.getText().toString();
                String fone= txtFone.getText().toString();
                String email= txtEmail.getText().toString();
                String senha= txtSenha.getText().toString();


                realm.beginTransaction();

                usuario.setCpf(CPF);
                usuario.setNome(nome);
                usuario.setEndereco(endereco);
                usuario.setFone(fone);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                realm.commitTransaction();

                SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();


                ed.putString("idusuario", "");
                ed.putString("nomeUsuario", "");
                ed.putString("email", "");
                ed.putString("senha", "");

                ed.apply();


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
