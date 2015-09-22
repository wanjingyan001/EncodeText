package com.wanjingyan.encodetext;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by wanjingyan on 2015/9/22.
 */
public class Encrypt {
    public static void main(String[] args) {
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            KeyPair pair = rsa.generateKeyPair();
            System.out.println("公钥" + ((RSAPublicKey) pair.getPublic()).getPublicExponent());
            System.out.println("公钥" + ((RSAPublicKey) pair.getPublic()).getModulus());
            System.out.println("私钥" + ((RSAPrivateKey) pair.getPrivate()).getPrivateExponent());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
