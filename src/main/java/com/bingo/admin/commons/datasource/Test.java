package com.bingo.admin.commons.datasource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class Test {
    public static void main(String[] args) {
//        StringBuilder rs = new StringBuilder();
//        byte[] rnd = new byte[16];
//        SecureRandom random=new SecureRandom();
//        random.nextBytes(rnd);
//        rs.append("$2a$");
//        rs.append("0");
//
//        rs.append(10);
//        rs.append("$");
//        encode_base64(rnd, rnd.length, rs);
//        System.out.println(rs.toString());
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}
