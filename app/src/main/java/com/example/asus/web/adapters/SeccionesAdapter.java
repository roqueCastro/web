package com.example.asus.web.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 09/05/2018.
 */

public class SeccionesAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> listaFragments = new ArrayList<>();
    private final List<String> listaTitulos = new ArrayList<>();

    public SeccionesAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String titulos){
        listaFragments.add(fragment);
        listaTitulos.add(titulos);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitulos.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
