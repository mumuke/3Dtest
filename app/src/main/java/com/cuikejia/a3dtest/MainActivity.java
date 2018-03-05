package com.cuikejia.a3dtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ThreeDSlidingLayout mThreeDSlidingLayout;
    private Button mMenuBUtton;
    private ListView contentListView;
    private ArrayAdapter<String> contentListAdapter;

    private String[] contentItems = {"Content Item1", "Content Item2", "Content Item3", "Content Item4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThreeDSlidingLayout = findViewById(R.id.slidingLayout);
        mMenuBUtton = (Button) findViewById(R.id.menuButton);
        contentListView = findViewById(R.id.contentList);
        contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentItems);
        contentListView.setAdapter(contentListAdapter);
        mThreeDSlidingLayout.setScrollEvent(contentListView);

        mMenuBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThreeDSlidingLayout.isLeftLayoutVisible()) {
                    mThreeDSlidingLayout.scrollToRightLayout();
                } else {
                    mThreeDSlidingLayout.scrollToLeftLayout();
                }
            }
        });

        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = contentItems[position];
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
