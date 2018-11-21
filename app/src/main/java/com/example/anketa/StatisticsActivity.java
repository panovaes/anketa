package com.example.anketa;


import android.app.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.anketa.answers.Anketa;
import com.example.anketa.answers.CafeAnketaAnswers;
import com.example.anketa.db.DBHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.*;





public class StatisticsActivity extends Activity implements ActionBar.TabListener {
    private static List<Anketa> answers = new ArrayList<>();
    private static String ANKETA_TYPE;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ANKETA_TYPE = getIntent().getStringExtra("ANKETA_TYPE");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(mSectionsPagerAdapter.getPageTitle(i));
            tab.setTabListener(this);

            actionBar.addTab(tab);
        }

        try {
            answers.clear();
            SQLiteDatabase database = DBHelper.getInstance().getReadableDatabase();
            try (Cursor cursor = database.rawQuery("select * from " + getTableName() + "", null)) {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    answers.add(Anketa.of(ANKETA_TYPE, cursor));
                }
            }
        } catch (Exception e) {
            Log.e("ER", "onCreate: ", e);
        }
    }


    private String getTableName() {
        return ANKETA_TYPE.toLowerCase();
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    private static final Description EMPTY_DESCRIPTION = new Description();

    static {
        EMPTY_DESCRIPTION.setText("");
    }


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private BarChart chart;


        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
            this.chart = rootView.findViewById(R.id.chart);

            int queryNum = getArguments().getInt(ARG_SECTION_NUMBER);
            Utils.init(rootView.getContext());

            try {
                List<String> labels = Anketa.getAnswers(ANKETA_TYPE, queryNum);
                Map<String, Integer> dataString = new HashMap<>();
                Map<Float, Integer> dataFloat = new HashMap<>();

                for (String label : labels) {
                    dataString.put(label, 0);
                }

                List<BarEntry> entries = new ArrayList<>();

                for (Anketa answer : answers) {
                    List<Object> ans = answer.getAnswer(queryNum);
                    if (ans.size() == 1) {
                        Object o = ans.get(0);
                        if (o instanceof Float) {
                            float value = (float) o;
                            if (dataFloat.containsKey(value)) {
                                dataFloat.put(value, dataFloat.get(value) + 1);
                            } else {
                                dataFloat.put(value, 1);
                            }
                        } else {
                            String oString = String.valueOf(o);
                            if (dataString.containsKey(oString)) {
                                dataString.put(oString, dataString.get(oString) + 1);
                            } else {
                                dataString.put(oString, 1);
                            }
                        }
                    } else {
                        Log.e("E", "IGNORE");
                    }
                }

                int i = 0;
                for (String key : dataString.keySet()) {
                    entries.add(new BarEntry(i, dataString.get(key), key));
                    i++;
                }

                for (Float key : dataFloat.keySet()) {
                    entries.add(new BarEntry(i, dataFloat.get(key), key));
                    i++;
                }

                BarDataSet set = new BarDataSet(entries, "Варианты ответов");

                set.setColors(ColorTemplate.COLORFUL_COLORS);

                BarData data = new BarData(set);
                data.setValueFormatter(new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return entry.getData().toString();
                    }
                });

                chart.setData(data);
                chart.setFitBars(true);
                chart.setDescription(EMPTY_DESCRIPTION);
                chart.invalidate();
            } catch (Exception e) {
                Log.e("ERROR", "onCreateView: ", e);
            }

            return rootView;
        }
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }


        @Override
        public int getCount() {
            switch (ANKETA_TYPE) {
                case "WORK":
                    return 8;

                case "CAFE":
                    return 5;

                case "SITE":
                    return 10;

                case "PET":
                    return 7;
            }
            return 0;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "Вопрос " + (position + 1);
        }
    }
}
