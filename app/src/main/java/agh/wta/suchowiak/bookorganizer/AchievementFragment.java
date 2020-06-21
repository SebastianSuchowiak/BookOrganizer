package agh.wta.suchowiak.bookorganizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import books.model.Book;
import books.model.Status;
import books.repository.BookRepository;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementFragment extends Fragment {

    @BindView(R.id.achievement_1) LinearLayout achievement_1;
    @BindView(R.id.achievement_2) LinearLayout achievement_2;
    @BindView(R.id.achievement_3) LinearLayout achievement_3;

    ArrayList<Book> books;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.achievement_fragment, container, false);
        ButterKnife.bind(this, rootView);

        books = BookRepository.getUserBooks();
        setAchievements();

        return rootView;
    }

    void setAchievements(){

        Long booksRead = books.stream().filter(b -> b.getStatus().equals(Status.HAVE_READ)).count();
        if(booksRead > 4){
            completeAchievement(achievement_1);
        }

        int booksAdded = books.size();
        if(booksAdded > 9){
            completeAchievement(achievement_2);
        }

        Long reviewsWritten = books.stream().filter(b -> !b.getReview().trim().isEmpty()).count();
        if(reviewsWritten > 4){
            completeAchievement(achievement_3);
        }
    }

    void completeAchievement(LinearLayout achievement){
        achievement.setBackgroundResource(R.drawable.achieved_shape);
    }
}
