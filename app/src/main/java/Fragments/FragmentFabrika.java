package Fragments;

import android.support.v4.app.Fragment;

public class FragmentFabrika implements IFragmentFabrikasi {
    @Override
    public Fragment getInstance(Enum fragmentAdi) {


        Fragment fragment;
        switch ((F_Menu_Enum) fragmentAdi) {
            case ANAEKRAN:
                fragment = new FragmentAnaekran();
                break;
            case EKLE_NOT:
                fragment = new FragmentNotEkle();
                break;
            case EKLE_KATEGORI:
                fragment = new FragmentKategoriEkle();
                break;
            case SIL_NOT:
                fragment = new FragmentNotSil();
                break;
            case SIL_KATEGORI:
                fragment = new FragmentKategoriSil();
                break;
            default:
                fragment = new FragmentAnaekran();
                break;
        }
        return fragment;
    }
}
