package com.usama.fyp_phr_android.AES;

public class AES {

    //  For Decryption
    public String decryption(String encryption) {
        try {
            String decryption = AESUtils.decrypt(encryption);
            return decryption;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  For Encryption
    public String encryption(String data) {
        try {
            String encryption = AESUtils.encrypt(data);
            return encryption;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
