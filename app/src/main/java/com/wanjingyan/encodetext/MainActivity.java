package com.wanjingyan.encodetext;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigation;
    private ActionBarDrawerToggle toggle;
    private MD5Fragment md5;
    private Base64Fragment base64;
    private DesFragment des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        navigation = ((NavigationView) findViewById(R.id.navigation));
        navigation.setNavigationItemSelectedListener(this);
        //在标题的左侧添加一个按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        md5 = new MD5Fragment();
        base64 = new Base64Fragment();
        des = new DesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .add(R.id.content, md5)
                .add(R.id.content, base64)
                .add(R.id.content, des)
                .detach(base64)
                .detach(des)
                .attach(md5)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()) {
            case R.id.md5_menu:
                transaction.detach(base64).detach(des).attach(md5);
                Toast.makeText(this, "MD5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.base64_menu:
                transaction.detach(md5).detach(des).attach(base64);
                Toast.makeText(this, "Base64", Toast.LENGTH_SHORT).show();
                break;
            case R.id.des_menu:
                transaction.detach(base64).detach(md5).attach(des);
                Toast.makeText(this, "Des", Toast.LENGTH_SHORT).show();
                break;
        }
        transaction.commit();
        drawer.closeDrawer(Gravity.LEFT);
        return false;
    }
}
