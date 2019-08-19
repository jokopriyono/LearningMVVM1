package com.example.samplemvvm1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplemvvm1.databinding.BookItemBinding;
import com.example.samplemvvm1.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<Book> books = new ArrayList<>();

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding bookItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_item, parent, false);
        return new BookViewHolder(bookItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private void loadBooksArrayList(int categoryId) {

    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private BookItemBinding bookItemBinding;

        public BookViewHolder(@NonNull BookItemBinding bookItemBinding) {
            super(bookItemBinding.getRoot());
            this.bookItemBinding = bookItemBinding;
            bookItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(books.get(pos));
                    }
                }
            });
        }
    }
}

