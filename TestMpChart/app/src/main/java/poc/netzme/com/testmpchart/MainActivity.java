package poc.netzme.com.testmpchart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);

        MarkerView markerView = new MyMarkerView(this, R.layout.marker_view);
        lineChart.setMarkerView(markerView);

        LineData lineData = makeLineData(10);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.LTGRAY);

        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setDrawLabels(false);
        rightYAxis.setGridColor(Color.LTGRAY);

        YAxis leftYAxis = lineChart.getAxisLeft();
        leftYAxis.setDrawLabels(false);

        leftYAxis.setGridColor(Color.LTGRAY);

        setChartStyle(lineChart, lineData, Color.WHITE);
    }

    private LineData makeLineData(int dataCount) {
        ArrayList<String> xList = new ArrayList<>();
        for (int i=0; i<dataCount; i++) {
            xList.add("8. " + i + 6);
        }

        ArrayList<Entry> yList = new ArrayList<>();
        for (int i=0; i<dataCount ; i++) {
            int number = (int)(Math.random() * 500 ) + 500;
            Entry entry = new Entry(number, i);
            yList.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(yList, "Wiii");

        lineDataSet.setLineWidth(10f);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setCircleRadius(15f);

        lineDataSet.setValueTextSize(8f);

        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleColorHole(Color.WHITE);
        lineDataSet.setDrawCubic(true);
        lineDataSet.setCubicIntensity(0.2f);

        lineDataSet.setDrawFilled(false);

        lineDataSet.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;
                String s = "" + n;
                return s;
            }
        });

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);



        LineData lineData = new LineData(xList, lineDataSets);
        lineData.setDrawValues(false);

        return lineData;
    }

    private void setChartStyle(LineChart mLineChart, LineData lineData, int color) {
        mLineChart.setDrawBorders(false);

        mLineChart.setDescription(null);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);

        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        mLineChart.setPinchZoom(false);

        mLineChart.setBackgroundColor(color);

        mLineChart.setData(lineData);

        Legend mLegend = mLineChart.getLegend();

        mLegend.setEnabled(false);

        mLineChart.animateX(1500);
    }

    private static class MyMarkerView extends MarkerView {

        private TextView textView;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            textView = (TextView) findViewById(R.id.tvContent);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            int n = (int) e.getVal();
            textView.setText(String.valueOf(n));
        }

        @Override
        public int getXOffset(float xpos) {
            return -(getWidth() / 2);
        }

        @Override
        public int getYOffset(float ypos) {
            return -getHeight();
        }
    }
}
