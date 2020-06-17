package agh.wta.suchowiak.tagorganizer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import agh.wta.suchowiak.bookorganizer.R;
import books.model.Book;
import books.model.Tag;
import co.lujun.androidtagview.TagContainerLayout;
import lombok.Getter;

public class TagViewAdapter extends RecyclerView.Adapter<TagViewAdapter.TagViewHolder> {

    private List<Map.Entry<Tag, List<Book>>> tagsBooks;
    private onItemClickListener listener;

    public TagViewAdapter(List<Map.Entry<Tag, List<Book>>> tags) {
        this.tagsBooks = tags;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_card, parent, false);
        TagViewHolder tagViewHolder = new TagViewHolder(v, listener);
        return tagViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TagViewHolder holder, int position) {
        Map.Entry<Tag, List<Book>> tagBooks = tagsBooks.get(position);

        holder.getTagName().setText(tagBooks.getKey().getName());
        holder.getTagContainer().setBackgroundColor(Color.parseColor(tagBooks.getKey().getColor()));

        String num = ((Integer) tagBooks.getValue().size()).toString();
        holder.getNumBooks().setText(num);
    }

    @Override
    public int getItemCount() {
        return tagsBooks.size();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @Getter
    static class TagViewHolder extends RecyclerView.ViewHolder {
        private TextView tagName;
        private TextView numBooks;
        private LinearLayout tagContainer;

        TagViewHolder(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);
            tagName = itemView.findViewById(R.id.tag_name);
            numBooks = itemView.findViewById(R.id.num_books);
            tagContainer = itemView.findViewById(R.id.tag_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
