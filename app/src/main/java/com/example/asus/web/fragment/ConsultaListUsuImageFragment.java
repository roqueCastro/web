package com.example.asus.web.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.web.R;
import com.example.asus.web.Utilidades.Utilidades;
import com.example.asus.web.adapters.ImageUsuariosAdapter;
import com.example.asus.web.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaListUsuImageFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    RecyclerView recyclerUser;

    ArrayList<Usuario> usuarios;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    ImageView imgCox;
    ConnectivityManager con;

    public ConsultaListUsuImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View vista = inflater.inflate(R.layout.fragment_consulta_list_usu_image, container, false);

       imgCox = vista.findViewById(R.id.imgInternet);

       usuarios = new ArrayList<>();

       recyclerUser  = (RecyclerView) vista.findViewById(R.id.idRecyclerImagen);
       recyclerUser.setLayoutManager(new LinearLayoutManager(this.getContext()));
       recyclerUser.setHasFixedSize(true);
       imgCox.setVisibility(View.INVISIBLE);

       request = Volley.newRequestQueue(getContext());

       con = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = con.getActiveNetworkInfo();

        if(info != null && info.isConnected()){
            cargarWebService();
        }else{
            imgCox.setVisibility(View.VISIBLE);
        }


        return vista;
    }

    private void cargarWebService() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Consultando");
        progreso.show();

        String url = Utilidades.HTTP+Utilidades.IP+Utilidades.CARPETA+"wsJSONConsultaListaUsuarioImagen.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario = null;

        JSONArray json = response.optJSONArray("usuario");

        try {
            for(int i=0;i<json.length(); i++){
                usuario = new Usuario();
                JSONObject jsonObject = null;

                jsonObject = json.getJSONObject(i);

                usuario.setDocumento(jsonObject.optInt("documento"));
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setProfesion(jsonObject.optString("profesion"));
                //usuario.setDato(jsonObject.optString("imagen"));
                usuario.setRutaImagen(jsonObject.optString("ruta_imagen"));
                usuarios.add(usuario);
            }
            progreso.hide();
            //envio getContext
            ImageUsuariosAdapter adapter = new ImageUsuariosAdapter(usuarios, getContext());
            recyclerUser.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se a podido tener conexion con el servidor " , Toast.LENGTH_SHORT).show();
            progreso.hide();

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }
}
