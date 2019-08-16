package com.example.samplemvvm1.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Book.class}, version = 1)
public abstract class BooksDatabase extends RoomDatabase {
    private static BooksDatabase instance;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialDataAsyncTask(instance).execute();
        }
    };

    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null) instance = Room.databaseBuilder(context.getApplicationContext(),
                BooksDatabase.class, "books_database")
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build();
        return instance;
    }

    public abstract CategoryDAO categoryDAO();

    public abstract BookDAO bookDAO();

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;

        public InitialDataAsyncTask(BooksDatabase booksDatabase) {
            categoryDAO = booksDatabase.categoryDAO();
            bookDAO = booksDatabase.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1 = new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryName("Text Books Description");

            Category category2 = new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryName("Novels Description");

            Category category3 = new Category();
            category3.setCategoryName("Other Books");
            category3.setCategoryName("Other Books Description");

            categoryDAO.insert(category1);
            categoryDAO.insert(category2);
            categoryDAO.insert(category3);

            Book book1 = new Book();
            book1.setBookName("High School Java");
            book1.setUnitPrice("Rp. 80.000");
            book1.setCategoryId(1);

            Book book2 = new Book();
            book2.setBookName("High School Kotlin");
            book2.setUnitPrice("Rp. 100.000");
            book2.setCategoryId(1);

            Book book3 = new Book();
            book3.setBookName("Mastering Python");
            book3.setUnitPrice("Rp. 65.000");
            book3.setCategoryId(1);

            Book book4 = new Book();
            book4.setBookName("Buku Novel Terkini");
            book4.setUnitPrice("Rp. 120.000");
            book4.setCategoryId(2);

            Book book5 = new Book();
            book5.setBookName("Ada apa dengan Koding?");
            book5.setUnitPrice("Rp. 190.000");
            book5.setCategoryId(2);

            Book book6 = new Book();
            book6.setBookName("How to not pacaran?");
            book6.setUnitPrice("Rp. 99.000");
            book6.setCategoryId(2);

            Book book7 = new Book();
            book7.setBookName("Tutorial lengkap Bumi Datar");
            book7.setUnitPrice("Rp. 175.000");
            book7.setCategoryId(3);

            Book book8 = new Book();
            book8.setBookName("1001 Resep Makanan");
            book8.setUnitPrice("Rp. 110.000");
            book8.setCategoryId(3);

            Book book9 = new Book();
            book9.setBookName("How to miskin?");
            book9.setUnitPrice("Rp. 999.000");
            book9.setCategoryId(3);

            bookDAO.insert(book1);
            bookDAO.insert(book2);
            bookDAO.insert(book3);
            bookDAO.insert(book4);
            bookDAO.insert(book5);
            bookDAO.insert(book6);
            bookDAO.insert(book7);
            bookDAO.insert(book8);
            bookDAO.insert(book9);
            return null;
        }
    }
}
