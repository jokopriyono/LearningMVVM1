package com.example.samplemvvm1;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.samplemvvm1.databinding.ActivityMainBinding;
import com.example.samplemvvm1.model.Book;
import com.example.samplemvvm1.model.Category;
import com.example.samplemvvm1.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityClickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(mainActivityClickHandlers);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                for (Category c : categories) {
                    Log.d(this.getClass().getName(), c.getCategoryName());
                }
            }
        });
        mainActivityViewModel.getAllBooks(3).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                for (Book b : books) {
                    Log.d(this.getClass().getName(), b.getBookName());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandlers {
        public void onFABClicked(View view) {
            Toast.makeText(getApplicationContext(), "FAB Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
