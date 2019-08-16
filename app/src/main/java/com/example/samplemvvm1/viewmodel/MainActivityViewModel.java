package com.example.samplemvvm1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.samplemvvm1.model.Book;
import com.example.samplemvvm1.model.Category;
import com.example.samplemvvm1.model.EBookShopRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private EBookShopRepository eBookShopRepository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Book>> allBooks;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        eBookShopRepository = new EBookShopRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories = eBookShopRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getAllBooks(int categoryId) {
        allBooks = eBookShopRepository.getBooks(categoryId);
        return allBooks;
    }

    public void addNewBook(Book book) {
        eBookShopRepository.insertBook(book);
    }

    public void updateBook(Book book) {
        eBookShopRepository.updateBook(book);
    }

    public void deleteBook(Book book) {
        eBookShopRepository.deleteBook(book);
    }
}
