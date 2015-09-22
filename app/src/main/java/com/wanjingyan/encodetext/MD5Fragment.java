package com.wanjingyan.encodetext;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A simple {@link Fragment} subclass.
 */
public class MD5Fragment extends Fragment implements View.OnClickListener {


    private EditText edit;
    private TextView result;

    public MD5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_md5, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit = ((EditText) view.findViewById(R.id.md5_edit));
        view.findViewById(R.id.md5_button).setOnClickListener(this);
        result = ((TextView) view.findViewById(R.id.md5_text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.md5_button:
                String str = edit.getText().toString();
                if (!TextUtils.isEmpty(str)) {
                    try {
                        // TODO: MD5摘要
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] digest = md5.digest(str.getBytes("UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        for (byte b : digest) {
                            builder.append(String.format("%02x", b & 0xff));
                        }
                        result.setText(builder.toString());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
