package agh.wta.suchowiak.bookorganizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import books.model.Book;
import books.model.Tag;
import books.repository.BookRepository;
import lombok.val;

public class MainActivity extends AppCompatActivity {

    private LiveData<NavController> currentNavController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBottomNavigationBar();
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.achievement:
                        Toast.makeText(MainActivity.this, "Achievements", Toast.LENGTH_SHORT).show();
                        openFragment(new AchievementFragment());
                    case R.id.tag:
                        Toast.makeText(MainActivity.this, "Tags", Toast.LENGTH_SHORT).show();
                        openFragment(new TagsFragment());
                        break;
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        openFragment(new BooksListFragment());
                        break;
                    case R.id.statistics:
                        Toast.makeText(MainActivity.this, "Statistics", Toast.LENGTH_SHORT).show();
                        openFragment(new StatisticsFragment());
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        openFragment(new SettingsFragment());
                        break;
                }
                return true;
            }
        });

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        bottomNavigationView.setSystemUiVisibility(uiOptions);

        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(currentNavController == null || currentNavController.getValue() == null) {
            return false;
        }
        return currentNavController.getValue().navigateUp();
    }
}
