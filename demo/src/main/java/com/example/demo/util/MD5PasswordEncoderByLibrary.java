package com.example.demo.util;

//import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.security.crypto.codec.Hex;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import org.apache.commons.codec.binary.Hex;
public class MD5PasswordEncoderByLibrary {

    protected static String[] demergePasswordAndSalt(String mergedPasswordSalt) {
        if (mergedPasswordSalt != null && !"".equals(mergedPasswordSalt)) {
            String password = mergedPasswordSalt;
            String salt = "";
            int saltBegins = mergedPasswordSalt.lastIndexOf("{");
            if (saltBegins != -1 && saltBegins + 1 < mergedPasswordSalt.length()) {
                salt = mergedPasswordSalt.substring(saltBegins + 1, mergedPasswordSalt.length() - 1);
                password = mergedPasswordSalt.substring(0, saltBegins);
            }

            return new String[]{password, salt};
        } else {
            throw new IllegalArgumentException("Cannot pass a null or empty String");
        }
    }

    protected static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt) ? password + "{" + salt.toString() + "}" : password;
        }
    }



    public static String encodePasswordWhithoutSalt(String rawPass) {
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(rawPass.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       return generatedPassword;

    }

    public static String encodePassword(String rawPass, Object salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = getMessageDigest();

        byte[] digest;
        try {
            digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            throw new IllegalStateException("UTF-8 not supported!");
        }

        return "w";//false ? new String(Base64.encodeBase64(digest)) : new String(org.apache.commons.codec.binary.Hex.encodeHex(digest));
    }

    protected static final MessageDigest getMessageDigest() throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException("No such algorithm [" + "MD5" + "]");
        }
    }



}
