package com.app.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class ShortCodeUtil {
    public static final ShortCodeUtil INSTANCE = new ShortCodeUtil();
    public static final String DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final Integer BASE = DICTIONARY.length();

    /**
     * create shourt code for given id.
     * @param id id retreived from service
     * @return short code as 62 base string.
     */
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
