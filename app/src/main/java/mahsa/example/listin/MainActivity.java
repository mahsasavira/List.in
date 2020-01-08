package mahsa.example.listin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import mahsa.example.listin.ui.about.AboutFragment;
import mahsa.example.listin.ui.list.AddListFragment;
import mahsa.example.listin.ui.list.ListFragment;
import mahsa.example.listin.ui.login.LoginActivity;
import mahsa.example.listin.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_list, R.id.nav_profile, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment f;
        switch (id){
            case R.id.nav_logout:
                removeSharedPreference();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.nav_about:
                f = new AboutFragment();
                actionBar.setTitle("TENTANG");
                replaceFragment(f);
                break;
            case R.id.nav_profile:
                f = new ProfileFragment();
                actionBar.setTitle("PROFIL");
                replaceFragment(f);
                break;
            case R.id.nav_list:
                f = new AddListFragment();
                actionBar.setTitle("TAMBAH CATATAN");
                replaceFragment(f);
                break;
            case R.id.daftar_list:
                f = new ListFragment();
                actionBar.setTitle("DAFTAR CATATAN");
                replaceFragment(f);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment f){
        if(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment) != null){
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).commit();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, f).commit();
    }

    private void removeSharedPreference() {
        this.getSharedPreferences("UserData", 0).edit().clear().commit();
    }
}
