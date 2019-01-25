package com.example.miwei.rxapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.miwei.rxapplication.activity.SearchActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListener();
    }

    private void initListener() {

        findViewById(R.id.btn_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_search:

                openSearchActivity();

                break;

                default:
                    break;
        }

    }


    /**
     * 打开基于rxjava搜索联想控件的测试页面
     */
    private void openSearchActivity() {

        startActivity(new Intent(this, SearchActivity.class));

    }
}
