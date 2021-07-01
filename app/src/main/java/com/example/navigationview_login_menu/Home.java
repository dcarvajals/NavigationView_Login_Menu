package com.example.navigationview_login_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import Model.Usuario;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView navigationView = findViewById(R.id.navegador_menu);
        navigationView.setItemIconTintList(null);

        NavController navigationController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navigationController);

        drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.imgOpenMenu).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //se le asigna la acción de abrir el menú
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }
        );

        //obtenemos el encabezado del menú
        View headMenu = navigationView.getHeaderView(0);
        ((TextView) headMenu.findViewById(R.id.tv_nombre)).setText(Usuario.getNombres());
        ((TextView) headMenu.findViewById(R.id.tv_correo)).setText(Usuario.getEmail());
        ((TextView) headMenu.findViewById(R.id.tv_rol)).setText(
                Usuario.getRol().equals("U") ? "Usuario" : "Administrador");
        ImageView userImage = headMenu.findViewById(R.id.imgPerfil);
        Glide.with(headMenu)
                .load("https://aplicaciones.uteq.edu.ec/RepositorioUTEQ/Folder/Users/"
                        + Usuario.getRuta_img())
                .error(R.drawable.user)
                .into(userImage);

        Menu bodyMenu = navigationView.getMenu();

        if(Usuario.getRol().equals("U")){
            bodyMenu.findItem(R.id.menuAdmin).setVisible(false);
            bodyMenu.findItem(R.id.menuUser).setVisible(true);
        }else {
            bodyMenu.findItem(R.id.menuAdmin).setVisible(true);
            bodyMenu.findItem(R.id.menuUser).setVisible(true);
        }

        bodyMenu.findItem(R.id.id_cs).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("","Cerrando sesion...");
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        });

    }
}