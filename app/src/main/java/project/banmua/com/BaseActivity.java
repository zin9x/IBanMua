package project.banmua.com;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initViews(savedInstanceState);
        initVariables(savedInstanceState);
    }
    protected abstract int layoutId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initVariables(Bundle savedInstanceState);

//    protected final void initHeader() {
//        btnLeft = (MyImageView) findViewById(R.id.btnLeft);
//        lblTitleBar = (TextView) findViewById(R.id.lblTitleBar);
//        btnLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    protected void setHeaderTitle(int stringId) {
//        lblTitleBar.setText(stringId);
//    }
//
//    protected void setHeaderTitle(String string) {
//        lblTitleBar.setText(string);
//    }
}
