package books.repository;

import android.os.AsyncTask;

import books.model.Book;

public class AddBookAsync extends AsyncTask<Void,Void,Boolean> {


    @Override
    protected Boolean doInBackground(Void... voids) {
        BookRepository.insertBook();
        return true;
    }
}
