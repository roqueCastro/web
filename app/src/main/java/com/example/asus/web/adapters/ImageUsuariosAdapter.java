package com.example.asus.web.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.web.R;
import com.example.asus.web.entidades.Usuario;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by ASUS on 16/05/2018.
 */

public class ImageUsuariosAdapter extends RecyclerView.Adapter<ImageUsuariosAdapter.UsuariosHolder> {

    List<Usuario> usuarios;

    RequestQueue request;
    Context context;

    public ImageUsuariosAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
        request= Volley.newRequestQueue(context);
    }

    @Override
    public ImageUsuariosAdapter.UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list_imagen, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(ImageUsuariosAdapter.UsuariosHolder holder, int position) {
        holder._txtDocu.setText(usuarios.get(position).getDocumento().toString());
        holder._txtNom.setText(usuarios.get(position).getNombre().toString());
        holder._txtProfes.setText(usuarios.get(position).getProfesion().toString());

       /* if(usuarios.get(position).getImagen() != null){
            holder.imagen.setImageBitmap(usuarios.get(position).getImagen());
        }else{
            holder.imagen.setImageResource(R.drawable.img_no_disponible);
        }*/
         if(usuarios.get(position).getRutaImagen() != null){
            String urlImagen = "http://192.168.56.1/ejemploBDRemota/"+usuarios.get(position).getRutaImagen();
            cargarWSImagen(urlImagen, holder);
        }else{
            holder.imagen.setImageResource(R.drawable.img_no_disponible);
        }
    }

    private void cargarWSImagen(String urlImagen, final UsuariosHolder holder) {
        urlImagen= urlImagen.replace(" ", "%20");

        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                holder.imagen.setImageResource(R.drawable.img_no_disponible);
            }
        });
        request.add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class UsuariosHolder extends  RecyclerView.ViewHolder{

        TextView _txtDocu,_txtNom,_txtProfes;
        ImageView imagen;

        public UsuariosHolder(View itemView) {
            super(itemView);
            _txtDocu = (TextView) itemView.findViewById(R.id.textDocumentoListImg);
            _txtNom = (TextView) itemView.findViewById(R.id.textNombreListImg);
            _txtProfes = (TextView) itemView.findViewById(R.id.textProfesionListImg);
            imagen = (ImageView) itemView.findViewById(R.id.idImagenListUsu);
        }
    }
}
