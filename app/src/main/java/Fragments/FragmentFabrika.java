package Fragments;

import android.support.v4.app.Fragment;

public class FragmentFabrika implements IFragmentFabrikasi {
    @Override
    public Fragment getInstance(Enum fragmentAdi){


        Fragment fragment=new FragmentAnaekran();//default deger.
        switch ((F_Menu_Enum)fragmentAdi){
            case Anaekran:
                fragment=new FragmentAnaekran();
                break;
            case EndeksTablosu:
                fragment=new FragmentEndeksTablosu();
                break;
            case SayacAyarlari:
                fragment=new FragmentSayacAyarlari();
                break;
            case SayacEndeksOku:
                fragment=new FragmentSayacEndeksOku();
                break;
            case SayacYukProfiliOku:
                fragment=new FragmentSayacYukProfiliOku();
                break;
            case SistemAyarlari:
                fragment=new FragmentSistemAyarlari();
                break;
            case Yardim:
                fragment=new FragmentYardim();
                break;
            case YukProfiliTablosu:
                fragment=new FragmentYukProfiliTablosu();
                break;
        }
        return fragment;
    }
}
