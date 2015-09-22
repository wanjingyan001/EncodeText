package com.wanjingyan.encodetext;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * todo 加密分为 对称加密和非对称加密，其中非对称加密有公钥和私钥，公钥用于加密，私钥用于解密
 * todo 加密针对的不是字符串，而是byte数组
 * todo Des：数据标准加密，是一种对称加密，密钥只有一个（长度为8位byte数组）
 * <p/>
 * todo 3Des:（名称为：DESede）对称加密，密钥为24位的byte数组，首先用前8位密钥进行一次加密，然后使用中间8位进行一次解密，再用最后8位密钥进行一次加密
 * todo 3Des密钥必须给足24位，不能8位一重复。该方法实质为DES算法做三次
 * <p/>
 * todo AES：高级数据标准加密，对称加密，密钥为32位byte数组，
 * <p/>
 * todo RAS:非对称加密，
 */
public class DesFragment extends Fragment implements View.OnClickListener {


    private EditText edit_key;
    private EditText edit_src;
    private EditText edit_res;
    private String mod = "96349220473319203028028550335049677965917689583153522246572984586848781771415887668969030933090413581084466133747056277854832862199853649329734129015124872310174631105214007411804870298097548989080805114746129972327371311803059144830972911565955642573015005968400432547475525129327786841349486388382516041447";
    private String publicKey = "65537";
    private String privateKey = "13062331909547094085594057797175206076583451437100816811458685409578561364291111247987297802029377023891942360214286237449117396895754797083038434106105637303753888817881954353519721365795555334204716462064001949826573433911332011156007388597389921025795121342507176165349045293533243937963645502712757059493";


    public DesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_des, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit_key = ((EditText) view.findViewById(R.id.des_key));
        edit_src = ((EditText) view.findViewById(R.id.des_src));
        edit_res = ((EditText) view.findViewById(R.id.des_res));
        view.findViewById(R.id.des_encrypt).setOnClickListener(this);
        view.findViewById(R.id.des_decrypt).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        symmetry(v);
        String key = edit_key.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            try {
                KeyFactory rsa = KeyFactory.getInstance("RSA");
                RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(mod), new BigInteger(publicKey));
                PublicKey publicKey = rsa.generatePublic(spec);
                RSAPrivateKeySpec spec1 = new RSAPrivateKeySpec(new BigInteger(mod), new BigInteger(privateKey));
                PrivateKey privateKey = rsa.generatePrivate(spec1);
                Cipher cipher = Cipher.getInstance("RSA");
                switch (v.getId()) {
                    case R.id.des_encrypt:
                        String src = edit_src.getText().toString();
                        if (!TextUtils.isEmpty(src)) {
                            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                            byte[] doFinal = cipher.doFinal(src.getBytes("UTF-8"));
                            edit_res.setText(Base64.encodeToString(doFinal, Base64.DEFAULT));
                        }
                        break;
                    case R.id.des_decrypt:
                        String res = edit_res.getText().toString();
                        if (!TextUtils.isEmpty(res)) {
                            cipher.init(Cipher.DECRYPT_MODE, privateKey);
                            byte[] doFinal = cipher.doFinal(Base64.decode(res, Base64.DEFAULT));
                            edit_src.setText(new String(doFinal, "UTF-8"));
                        }
                        break;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

        }
    }


    // TODO: 对称加密的三种方法
    private void symmetry(View v) {
        String key = edit_key.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            try {
//                byte[] b = new byte[8];
//                byte[] b = new byte[24];
                byte[] b = new byte[32];
                byte[] bytes = key.getBytes("UTF-8");
                System.arraycopy(bytes, 0, b, 0, Math.min(bytes.length, b.length));
                // TODO: 生成一个Des的密钥
//                SecretKeySpec des_key = new SecretKeySpec(b, "DES");
//                SecretKeySpec des_key = new SecretKeySpec(b, "DESede");
                SecretKeySpec des_key = new SecretKeySpec(b, "AES");
                // TODO: 加解密工具
//                Cipher cipher = Cipher.getInstance("DES");
//                Cipher cipher = Cipher.getInstance("DESede");
                Cipher cipher = Cipher.getInstance("AES");
                switch (v.getId()) {
                    case R.id.des_encrypt:
                        String src = edit_src.getText().toString();
                        if (!TextUtils.isEmpty(src)) {
                            // TODO: 初始化模式和密钥 （ENCRYPT_MODE加密模式）
                            cipher.init(Cipher.ENCRYPT_MODE, des_key);
                            // TODO: 对传入的数据进行操作
                            byte[] doFinal = cipher.doFinal(src.getBytes("UTF-8"));
                            Toast.makeText(getActivity(), doFinal.length + "----", Toast.LENGTH_SHORT).show();
                            // TODO: 使用Base64的编码方式将doFinal转为字符串
                            edit_res.setText(Base64.encodeToString(doFinal, Base64.DEFAULT));
                        }
                        break;
                    case R.id.des_decrypt:
                        String res = edit_res.getText().toString();
                        if (!TextUtils.isEmpty(res)) {
                            // TODO: 初始化模式和密钥 （DECRYPT_MODE解密模式）
                            cipher.init(Cipher.DECRYPT_MODE, des_key);
                            // TODO: 使用Base64的解码方式将res转为byte数组，再通过doFinal方法进行处理
                            byte[] doFinal = cipher.doFinal(Base64.decode(res, Base64.DEFAULT));
                            edit_src.setText(new String(doFinal, "UTF-8"));
                        }
                        break;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

        }
    }
}
