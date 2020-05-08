package com.app.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class ShortCodeUtil {
    public static final ShortCodeUtil INSTANCE = new ShortCodeUtil();
    public static final String DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final Integer BASE = DICTIONARY.length();

    public String getShortCode(Long id) {
        StringBuilder sb = new StringBuilder("");
        while (id > BASE) {
            Integer rem = Math.toIntExact(id % BASE);
            sb.append(DICTIONARY.charAt(rem));
            id = id / BASE;
        }
        return sb.reverse().toString();
    }
}
