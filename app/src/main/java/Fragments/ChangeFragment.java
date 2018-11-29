package Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.hasan.bluskyproje.IFragmentFabrikasi;
import com.example.hasan.bluskyproje.R;

public class ChangeFragment {
    Context cxt;

    public ChangeFragment(Context cxt) {
        this.cxt = cxt;
    }

    public void change(Enum fragmentIsmı, IFragmentFabrikasi fragmentFabrikasi) {
        Fragment fragment = fragmentFabrikasi.getInstance(fragmentIsmı);
        ((FragmentActivity) cxt).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        cxt = null;
    }
}
