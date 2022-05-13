package com.yfyy.util;

import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 15:04
 */
public class JwtUtil {

    public static RSAKeyPair getKeyPair() throws NoSuchAlgorithmException {
        RSAKeyPair keyPair = new RSAKeyPair();
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);

        KeyPair kp = keyGenerator.genKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
        keyPair.setPrivateKey(Base64.encode(privateKey.getEncoded()));
        keyPair.setPublicKey(Base64.encode(publicKey.getEncoded()));
        return keyPair;
    }

    public static Jws parseToken(String token, String pk) throws Exception {

        return Jwts.parser().setSigningKey(getPublicKey(pk))
                .parseClaimsJws(token);
    }

    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        return privateKey;
    }


    public static String genToken(String key, Map<String, Object> map) throws Exception {

        String token = Jwts.builder()
                .addClaims(map)
                // RS256 with privateKey
                .signWith(SignatureAlgorithm.RS256, getPrivateKey(key)).compact();
        return token;
    }

    public static class RSAKeyPair {
        String publicKey;
        String privateKey;

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }
    }
}
