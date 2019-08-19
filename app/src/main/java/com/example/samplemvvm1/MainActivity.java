package com.example.samplemvvm1;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;
    private Category selectedCategory;
    private ArrayList<Category> categoryArrayList = new ArrayList<>();

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
                categoryArrayList.clear();
                categoryArrayList.addAll(categories);
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

    private void showSpinner() {
        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categoryArrayList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(arrayAdapter);

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

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
