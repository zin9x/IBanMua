package project.banmua.com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import project.banmua.com.R;
import project.banmua.com.gui.ProgressDialog;


public abstract class BaseActivity extends FragmentActivity {
    protected ProgressDialog mProgressDialog;
    protected BaseActivity self;
    protected ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        self = this;
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
public void showProgressDialog() {
    try {
        // showProgressDialog(getString(R.string.message_please_wait));
        if (mProgressDialog == null) {
            try {
                mProgressDialog = new ProgressDialog(self);
                mProgressDialog.show();
                mProgressDialog.setCancelable(false);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                mProgressDialog = new ProgressDialog(self.getParent());
                mProgressDialog.show();
                mProgressDialog.setCancelable(false);
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
    /**
     * Close progress dialog
     */
    public void closeProgressDialog() {
        try {
            // if (progressDialog != null) {
            //
            // // progressDialog.cancel();
            // progressDialog.dismiss();
            // progressDialog = null;
            // }
            if (mProgressDialog != null) {
                mProgressDialog.cancel();
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
