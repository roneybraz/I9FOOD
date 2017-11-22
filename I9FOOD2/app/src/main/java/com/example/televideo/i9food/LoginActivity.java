package com.example.televideo.i9food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


        SharedPreferences prefs= getSharedPreferences("login",Context.MODE_PRIVATE);
        String email= prefs.getString("email", "nenhum resultado");
        String senha= prefs.getString("senha", "nenhum resultado");

        if (!email.equals("")&&!senha.equals("")) {

            try {
                Usuario emailView = realm.where(Usuario.class).equalTo("email", email).findFirst();


                String emailbanco = emailView.getEmail();
                String senhabanco = emailView.getSenha();


                if (email.equals(emailbanco) && senha.equals(senhabanco)) {


                    Intent ToCadastro = new Intent(getContext(), MainActivity.class);
                    startActivity(ToCadastro);


                }

            }catch (RuntimeException e){
                Toast.makeText(LoginActivity.this, "INFORME EMAIL E SENHA!", Toast.LENGTH_SHORT).show();
            }
        }





        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtemail=Email.getText().toString();
                String txtsenha=Senha.getText().toString();


                if (!txtemail.equals("")&&!txtsenha.equals("")) {

                    try {
                        Usuario emailView = realm.where(Usuario.class).equalTo("email", txtemail).findFirst();


                        int idUsuario = emailView.getId();
                        String email = emailView.getEmail();
                        String senha = emailView.getSenha();


                        if (txtemail.equals(email) && txtsenha.equals(senha)) {


                            Intent ToCadastro = new Intent(getContext(), MainActivity.class);
                            startActivity(ToCadastro);

                            SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor ed = prefs.edit();


                            ed.putString("idusuario", String.valueOf(idUsuario));
                            ed.putString("nomeUsuario", emailView.getNome());
                            ed.putString("email", email);
                            ed.putString("senha", senha);

                            ed.apply();


                        } else
                            Toast.makeText(LoginActivity.this, "EMAIL ou SENHA INCORRETO!", Toast.LENGTH_SHORT).show();
                    }catch (RuntimeException e){
                        Toast.makeText(LoginActivity.this, "EMAIL ou SENHA INCORRETO", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(LoginActivity.this, "EMAIL ou SENHA INCORRETO", Toast.LENGTH_SHORT).show();








            }
        });




        ToCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=null;
                Bundle params = new Bundle();
                it= new Intent(getContext(),CadastrarActivity.class);
                params.putString("idnew", "new");
                it.putExtras(params);
                startActivity(it);

            }
        });



    }



    private Context getContext()
    {
        return this;
    }
}
