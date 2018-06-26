package com.example.asus.web.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.web.R;
import com.example.asus.web.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ConsultaUFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText _documento;
    Spinner spinner;
    Button _btn_Consult;
    TextView _txtDocum,_txtNombre,_txtProfesion;
    ImageView _imagen;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public ConsultaUFragment() {
        // Required empty public constructor
    }


    public static ConsultaUFragment newInstance(String param1, String param2) {
        ConsultaUFragment fragment = new ConsultaUFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_consulta_u, container, false);

        //_documento = vista.findViewById(R.id.input_c_documento);
        spinner = vista.findViewById(R.id.spinnerC);
        _txtDocum = vista.findViewById(R.id.textDocumento);
        _txtNombre = vista.findViewById(R.id.textNombre);
        _txtProfesion = vista.findViewById(R.id.textProfesion);
        _btn_Consult = vista.findViewById(R.id.btn_consultar_id);
        _imagen = vista.findViewById(R.id.imagenId);

        request= Volley.newRequestQueue(getContext());

        _btn_Consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarWebSertvice();
            }
        });


        return vista;
    }

    private void CargarWebSertvice() {

        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Consultando..");
        progreso.show();

        String url = "http://192.168.56.1/ejemploBDRemota/wsJSONConsultaUsuarioOmagen.php?documento="+_documento.getText().toString()+"";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        progreso.hide();
       //Toast.makeText(getContext(), "Message "+ response, Toast.LENGTH_SHORT).show();
        Usuario miUsuario = new Usuario();

        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject=json.getJSONObject(0);
            miUsuario.setNombre(jsonObject.optString("nombre"));
            miUsuario.setDocumento(jsonObject.optInt("documento"));
            miUsuario.setProfesion(jsonObject.optString("profesion"));
            miUsuario.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        _txtNombre.setText(miUsuario.getNombre());
        _txtDocum.setText(miUsuario.getDocumento().toString());
        _txtProfesion.setText(miUsuario.getProfesion());

        if(miUsuario.getImagen() != null){
            _imagen.setImageBitmap(miUsuario.getImagen());
        }else{
            _imagen.setImageResource(R.drawable.img_no_disponible);
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo Consultar " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
