package agh.wta.suchowiak.bookorganizer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import books.model.Book;
import books.model.Status;
import books.repository.BookRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

public class StatisticsFragment extends Fragment {
    @BindView(R.id.piechart_1) PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_fragment, container, false);
        ButterKnife.bind(this, view);


        setPieChart();
        return view;
    }

    public void setPieChart() {
        //pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setTransparentCircleRadius(61f);
        //pieChart.setHoleColor(Color.WHITE);
        //pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        ArrayList<PieEntry> yValues = new ArrayList<>();

        ArrayList<Book> books = BookRepository.getUserBooks();
        List<Long> chartValues = Arrays.asList(Status.values()).stream().map(
            status -> books.stream().filter(book -> book.getStatus().equals(status)).count()
        ).collect(Collectors.toList());

        for(int i = 0 ; i < chartValues.size(); i++){
            yValues.add(new PieEntry(chartValues.get(i),Status.values()[i].name()));
        }

        PieDataSet dataSet = new PieDataSet(yValues, "Books Status");
                dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
        //PieChart Ends Here
    }
}
