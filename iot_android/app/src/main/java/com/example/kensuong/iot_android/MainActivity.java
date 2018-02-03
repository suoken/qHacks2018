package com.example.kensuong.iot_android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // area for line chart
    private LineChart[] mCharts = new LineChart[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fully screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mCharts[0] = (LineChart) findViewById(R.id.chart0);
        mCharts[1] = (LineChart) findViewById(R.id.chart1);
        mCharts[2] = (LineChart) findViewById(R.id.chart2);
        mCharts[3] = (LineChart) findViewById(R.id.chart3);

        for(int i=0; i<mCharts.length; i++) {
            LineData data = getData(36, 100); // count = 36, range = 100
            setupChart(mCharts[i], data, mColors[i]);
        }
    }

    private void setupChart(LineChart chart, LineData data, int color) {

        // setup a bunch of properties for the line chart
        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);
        chart.setViewPortOffsets(10, 0, 10,0);
        chart.setData(data);
    }

    private LineData getData(int count, int range) {
        // array list gets an entry
        ArrayList<Entry> yVals = new ArrayList<>();

        // Right now the numbers are random on the chart. Need to get the api for the chart
        for (int i=0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1 = new LineDataSet(yVals, "Data Set");
        set1.setLineWidth(3f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        set1.setDrawCircles(false); // makes the circles disappear

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER); // makes the lines smoother
        set1.setCubicIntensity(0.2f);

        LineData data = new LineData(set1);
        return data;

    }

    private int[] mColors = new int[] {
            Color.rgb(137, 230, 81), Color.rgb(240, 230, 30), Color.rgb(89, 199, 250), Color.rgb(250, 104, 119)
    };

//    private static void unclaim(final Activity activity, final ParticleDevice device) {
//        Async.executeAsync(device, new Async.ApiWork<ParticleDevice, Void>(){
//
//            public Integer callApi(ParticleDevice particleDevice)
//                    throws ParticleCloudException, IOException {
//                return particleDevice.getVariable("myVariable");
//            }
//
//            @Override
//            public void onSuccess(Integer value) {
//                Toaster.s(MyActivity.this, "Room temp is " + value + " degrees.");
//            }
//
//            @Override
//            public void onFailure(ParticleCloudException e) {
//                Log.e("some tag", "Something went wrong making an SDK call: ", e);
//                Toaster.l(MyActivity.this, "Uh oh, something went wrong.");
//            }
//
//        }).andIgnoreCallbacksIfActivityIsFinishing(activity);
//    }

    // ParticleCloudSDK.getCloud().logIn("alex.toutongi@gmail.com", "Camaro2010");
//
//    try {
//        ParticleDevice myDevice = ParticleCloudSDK.getCloud().getDevice("2f002a000251363131363432");
//        String nameString = myDevice.getName();
//
//    } catch {
//        break;
//    }

//    public void retieveDevice() {
//        try {
//            ParticleCloudSDK.getCloud().logIn("alex.toutongi@gmail.com", "Camero2010" );
//        } catch (MalformedURLException e) {
//
//        } catch (IOException e) {
//
//        } finally {
//            client.disconnect();
//        }
//    }

}
