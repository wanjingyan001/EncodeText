package com.wanjingyan.encodetext;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * todo Base64可以将非字符型数据转为文字传输
 */
public class Base64Fragment extends Fragment implements View.OnClickListener {


    private EditText src;
    private EditText result;

    public Base64Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base64, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        src = ((EditText) view.findViewById(R.id.base64_src));
        result = ((EditText) view.findViewById(R.id.base64_result));
        view.findViewById(R.id.base64_get).setOnClickListener(this);
        view.findViewById(R.id.base64_decode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base64_get:
                String s = src.getText().toString();
                if (!TextUtils.isEmpty(s)) {
                    // TODO: Base64 加密（可以将非字符数据转为文字型数据传输）
//                    try {
//                        String s1 = Base64.encodeToString(s.getBytes("UTF-8"), Base64.DEFAULT);
//                        result.setText(s1);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    // TODO: URLEncoder，将汉字和特定字符转为网络URL路径可以识别的字符串
                    try {
                        String encode = URLEncoder.encode(s, "Utf-8");
                        result.setText(encode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.base64_decode:
                String res = result.getText().toString();
                if (!TextUtils.isEmpty(res)) {
                    // TODO: Base64可以解码
//                    byte[] decode = Base64.decode(res, Base64.DEFAULT);
//                    try {
//                        src.setText(new String(decode, "UTF-8"));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    // TODO:  URLDecoder解码
                    try {
                        String decode = URLDecoder.decode(res, "UTF-8");
                        src.setText(decode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }
}
