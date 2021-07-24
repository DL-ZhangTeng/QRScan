package com.zhangteng.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhangteng.zxing.client.decode.CaptureActivity;
import com.zhangteng.zxing.client.decode.Intents;
import com.zhangteng.zxing.client.encode.EncodeActivity;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_SCAN = 1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, EncodeActivity.class);
        intent.setAction(Intents.Encode.ACTION);
        intent.putExtra(Intents.Encode.DATA, "android");
        startActivity(intent);
    }

    public void onScan(View view) {
        Intent intent = new Intent(this, CaptureActivity.class);
        intent.setAction(Intents.Scan.ACTION);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("ResultQRCode");
                textView.setText("扫描结果为：" + content);
            }
        }
    }
}
