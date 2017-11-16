package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {

    Button Entrar;
    EditText Email,Senha;
    TextView ToCadastrar;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        Email=(EditText)findViewById(R.id.login_txtEmail);
        Senha=(EditText)findViewById(R.id.login_txtSenha);

        Entrar=(Button)findViewById(R.id.login_bntEntrar);

        ToCadastrar=(TextView)findViewById(R.id.login_bntCadastrar);


        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtemail=Email.getText().toString();
                String txtsenha=Senha.getText().toString();

                Usuario emailView= realm.where(Usuario.class).equalTo("email",txtemail).findFirst();

                String email=emailView.getEmail();
                String senha=emailView.getSenha();

                if(txtemail.equals(email) &&  txtsenha.equals(senha)) {
                    Intent ToCadastro = new Intent(getContext(),MainActivity.class);
                    startActivity(ToCadastro);
                }
                else Toast.makeText(LoginActivity.this, "EMAIL ou SENHA INCORRETO!", Toast.LENGTH_SHORT).show();









            }
        });




        ToCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ToCadastro = new Intent(getContext(),CadastrarActivity.class);
                startActivity(ToCadastro);

            }
        });



    }



    private Context getContext()
    {
        return this;
    }
}
