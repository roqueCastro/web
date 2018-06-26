package com.example.asus.web.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.web.R;
import com.example.asus.web.entidades.Usuario;

import java.util.List;

/**
 * Created by ASUS on 15/05/2018.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> {

    List<Usuario> listaUsuario;

    public UsuariosAdapter(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public UsuariosAdapter.UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list, parent,false );
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosAdapter.UsuariosHolder holder, int position) {
        holder.txtDocumento.setText(listaUsuario.get(position).getDocumento().toString());
        holder.txtNombre.setText(listaUsuario.get(position).getNombre());
        holder.txtProfesion.setText(listaUsuario.get(position).getProfesion());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtDocumento,txtNombre,txtProfesion;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtDocumento=itemView.findViewById(R.id.textDocumentoList);
            txtNombre=itemView.findViewById(R.id.textNombreList);
            txtProfesion=itemView.findViewById(R.id.textProfesiontoList);
        }
    }
}
