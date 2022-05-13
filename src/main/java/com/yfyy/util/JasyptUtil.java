package com.yfyy.util;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zhang lei
 * @Date: 2022/4/8 23:13
 */
@Component
public class JasyptUtil {

    @Autowired
    private StringEncryptor stringEncryptor;

    /**
     * Jasypt加密
     * @param plainText 原文
     * @return String
     */
    public String encryptWithSHA512(String plainText) {
        return stringEncryptor.encrypt(plainText);
    }

    /**
     * Jasypt解密
     * @param encryptedText 密文
     * @return String
     */
    public String decryptWithSHA512(String encryptedText) {
        return stringEncryptor.decrypt(encryptedText);
    }
}
