package br.usuario.rest;

import com.mysql.cj.util.Base64Decoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PasswordUtils {

    public String convertPassword(String senha) {

        byte[] decodedBytes = Base64.getDecoder().decode(senha);
        String decodedString = new String(decodedBytes);

        System.out.println(decodedString);

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(decodedString.getBytes());
            byte[] codificado = md.digest();
            BigInteger hex = new BigInteger(1, codificado);
            String hashMD5 = hex.toString(16);
            while (hashMD5.length() < 32) {
                hashMD5 = 0 + hashMD5;
            }
            byte[] salt = geraSalt();
            System.out.println("senha MD5: " + hashMD5);
            System.out.println("senha MD5 + Salt: " + hashMD5 + salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return decodedString;
    }
    public static byte[] geraSalt() {
    Random RANDOM = new SecureRandom();
    int ITERATIONS = 10000;
    int KEY_LENGTH = 256;

        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }


}
