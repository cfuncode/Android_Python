package com.cjz.android_python;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.regex.Pattern;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mHead;
    private EditText mEtIp;
    private String ip = null;
    private TextView mTvIp;
    private TextView mTvLocation;
    private TextView mTvOperator;
    private Button mBnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBarNavigationBar();
        setContentView(R.layout.activity_main);
        initView();
        initPython(this);
    }

    // 调用python代码
    void callPythonCode() {
        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("ip").callAttr("setIP", ip);
        String str = pyObject.toString();
        if (BuildConfig.DEBUG) Log.d("MainActivity", str);
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            tvs[i].setText(tips[i] + split[i]);
        }
        int j = tips.length - split.length;
        for (int i = 0; i < j; i++) {
            tvs[split.length + i].setText(tips[split.length + i]);
        }
        Toast.makeText(this, "IP地址信息查询成功", Toast.LENGTH_SHORT).show();
    }

    private String[] tips = {"IP地址：", "归属地：", "运营商："};

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setVisibility(View.GONE);
        mHead = (TextView) findViewById(R.id.head);
        mHead.setText("IP地址信息查询");
        mEtIp = (EditText) findViewById(R.id.et_ip);
        mEtIp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    ip = mEtIp.getText().toString().trim();
                    if (Pattern.matches(IP, ip)) {
                        callPythonCode();
                        hideKeyBoard(mEtIp);
                    } else {
                        Toast.makeText(MainActivity.this, "IPv4地址格式错误", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        mTvIp = (TextView) findViewById(R.id.tv_ip);
        mTvIp.setSelected(true);
        mTvLocation = (TextView) findViewById(R.id.tv_location);
        mTvLocation.setSelected(true);
        mTvOperator = (TextView) findViewById(R.id.tv_operator);
        mTvOperator.setSelected(true);
        mBnQuery = (Button) findViewById(R.id.bn_query);
        mBnQuery.setOnClickListener(this);
        tvs = new TextView[]{mTvIp, mTvLocation, mTvOperator};
    }

    TextView[] tvs;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bn_query:
                ip = mEtIp.getText().toString().trim();
                if (Pattern.matches(IP, ip)) {
                    callPythonCode();
                } else {
                    Toast.makeText(this, "IPv4地址格式错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
