package com.example.navigationview_login_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import WebServicies.WsUsuario;

public class MainActivity extends AppCompatActivity {

    EditText et_email = null;
    EditText et_contrasenia = null;
    WsUsuario wsusuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void acceder(View v){
        et_email = (EditText)findViewById(R.id.tf_correo_electronico);
        et_contrasenia = (EditText)findViewById(R.id.tf_contrasenia);

        wsusuario = new WsUsuario("userApis/login", this, Home.class);
        wsusuario.login(et_email.getText().toString(), et_contrasenia.getText().toString());
    }

}