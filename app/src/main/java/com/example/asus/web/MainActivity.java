package com.example.asus.web;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asus.web.Interfases.IFragments;
import com.example.asus.web.clases.Utilidades;
import com.example.asus.web.fragment.ConsultaListUsuImageFragment;
import com.example.asus.web.fragment.ConsultaUFragment;
import com.example.asus.web.fragment.ConsultaULFragment;
import com.example.asus.web.fragment.ContenedorFragment;
import com.example.asus.web.fragment.RegistroFragment;
import com.example.asus.web.fragment.WelcomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragments {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (Utilidades.validaPantalla==true){
            registro();
            Utilidades.validaPantalla=false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void registro(){
        android.support.v4.app.Fragment fragmentPrincipal = new RegistroFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, fragmentPrincipal)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        android.support.v4.app.Fragment miFragment= null;
        boolean fragmentSelecionado=false;

        int id = item.getItemId();

         if (id == R.id.nav_registro_u) {
            miFragment= new RegistroFragment();
            fragmentSelecionado=true;
        } else if (id == R.id.nav_consulta_u) {
             miFragment = new ConsultaUFragment();
             fragmentSelecionado = true;
         }else if (id == R.id.nav_share) {
            miFragment= new ContenedorFragment();
            fragmentSelecionado=true;
        }else if (id == R.id.nav_consulta_u_lis) {
            miFragment= new ConsultaULFragment();
            fragmentSelecionado=true;
        }else if (id == R.id.nav_consulta_u_lis_imagen) {
            miFragment= new ConsultaListUsuImageFragment();
            fragmentSelecionado=true;
        }


        if(fragmentSelecionado==true){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, miFragment)
                    .commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
