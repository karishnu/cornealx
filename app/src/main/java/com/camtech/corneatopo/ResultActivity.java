package com.camtech.corneatopo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    ImageView heatLeft, heatRight;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LineChart chart = (LineChart) findViewById(R.id.chart);
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.setDescription(null);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 20));
        entries.add(new Entry(20, 40));
        entries.add(new Entry(40, 30));
        entries.add(new Entry(60, 60));
        entries.add(new Entry(80, 70));

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(getResources().getColor(R.color.colorAccent));
        dataSet.setFillColor(getResources().getColor(R.color.colorAccent));
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(255);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setProgress(40);
    }
}
