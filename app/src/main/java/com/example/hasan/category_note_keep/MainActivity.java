package com.example.hasan.category_note_keep;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Toast;

import Fragments.ChangeFragment;
import Fragments.F_Menu_Enum;
import Fragments.FragmentFabrika;
import data.DatabaseContract.NotlarEntry;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentFabrika fragmentFabrika;

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentFabrika = new FragmentFabrika();
        if (savedInstanceState == null) {
            //anaEkranKontrol = true;
            new ChangeFragment(this).change(F_Menu_Enum.ANAEKRAN, fragmentFabrika);
        }

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

        ChangeFragment changeFragment = new ChangeFragment(this);
        F_Menu_Enum fragmentEnum;

        switch (item.getItemId()) {
            case R.id.nav_anaekran:
                fragmentEnum = F_Menu_Enum.ANAEKRAN;
                break;
            case R.id.nav_ekle_not:
                fragmentEnum = F_Menu_Enum.EKLE_NOT;
                break;
            case R.id.nav_ekle_kategori:
                fragmentEnum = F_Menu_Enum.EKLE_KATEGORI;
                break;
            case R.id.nav_sil_not:
                fragmentEnum = F_Menu_Enum.SIL_NOT;
                break;
            case R.id.nav_sil_kategori:
                fragmentEnum = F_Menu_Enum.SIL_KATEGORI;
                break;
            default:
                fragmentEnum = F_Menu_Enum.ANAEKRAN;
                break;
        }

        changeFragment.change(fragmentEnum, fragmentFabrika);
        setTitle(item.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void notEkle() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotlarEntry.COLUMN_NOT_ICERIK, "yeni not");
        contentValues.put(NotlarEntry.COLUMN_OLUSTURULMA_TARIHI, "28-11-18");
        contentValues.put(NotlarEntry.COLUMN_YAPILDI, 1);
        contentValues.put(NotlarEntry.COLUMN_KATEGORI_ID, 2);

        Uri uri = getContentResolver().insert(NotlarEntry.CONTENT_URI, contentValues);
        Toast.makeText(this, "Not eklendi : " + uri, Toast.LENGTH_SHORT).show();
    }
}
