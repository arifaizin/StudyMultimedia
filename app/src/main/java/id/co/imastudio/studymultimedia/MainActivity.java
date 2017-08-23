package id.co.imastudio.studymultimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RadioFragment fragment = new RadioFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frame_for_fragment, fragment)
                .commit();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            VideoFragment fragment = new VideoFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.frame_for_fragment, fragment)
                    .addToBackStack("fragment")
                    .commit();
            getSupportActionBar().setTitle("Gallery");
        } else if (id == R.id.nav_slideshow) {
            Intent pindah = new Intent(MainActivity.this, WebActivity.class);
            startActivity(pindah);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            String url = "http://wondho.xyz";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
// set toolbar color and/or setting custom actions before invoking build()
// Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
            CustomTabsIntent customTabsIntent = builder.build();
// and launch the desired Url with CustomTabsIntent.launchUrl()
            customTabsIntent.launchUrl(this, Uri.parse(url));
        } else if (id == R.id.nav_home) {
            RadioFragment fragment = new RadioFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.frame_for_fragment, fragment)
                    .addToBackStack("fragment")
                    .commit();
            getSupportActionBar().setTitle("Home");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
