package zykj.com.barguotakeout.activity;

import android.os.Bundle;
import android.view.View;
import zykj.com.barguotakeout.R;

/**
 * Created by ss on 15-4-17.
 */
public class RankDetailActivity extends CommonActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTag(RankDetailActivity.class.getSimpleName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_rank);
        initView();
    }

    private void initView() {
    }

    public void back(View v){ finish(); }

}
