package com.networkIPS.task.ui.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.networkIPS.task.R;
import com.networkIPS.task.ui.activities.base.BaseActivity;
import com.networkIPS.task.ui.fragments.ArticlesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.txtToolBarTitle)
    TextView txtToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showFragment(ArticlesFragment.getInstance(), false);
    }

    public void setTitle(String title) {
        txtToolBarTitle.setText(title);
    }



}
