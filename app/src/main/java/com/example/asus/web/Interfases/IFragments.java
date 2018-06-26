package com.example.asus.web.Interfases;

import com.example.asus.web.fragment.ConsultaUFragment;
import com.example.asus.web.fragment.ConsultaULFragment;
import com.example.asus.web.fragment.ContenedorFragment;
import com.example.asus.web.fragment.RegistroFragment;
import com.example.asus.web.fragment.WelcomeFragment;

/**
 * Created by ASUS on 11/05/2018.
 */

public interface IFragments extends
        ContenedorFragment.OnFragmentInteractionListener,
        ConsultaUFragment.OnFragmentInteractionListener,
        ConsultaULFragment.OnFragmentInteractionListener,
        RegistroFragment.OnFragmentInteractionListener{
}
