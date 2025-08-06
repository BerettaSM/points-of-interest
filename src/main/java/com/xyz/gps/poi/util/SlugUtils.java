package com.xyz.gps.poi.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtils {

    private static final Pattern NON_ALNUM = Pattern.compile("[^\\p{Alnum}]");
    private static final Pattern MULTIPLE_DASHES = Pattern.compile("-+");
    private static final Pattern LEADING_AND_TRAILING_DASHES = Pattern.compile("^-+|-+$");

    public static String toSlug(String text) {
        String slug = Normalizer.normalize(text, Normalizer.Form.NFD);
        slug = NON_ALNUM.matcher(slug).replaceAll("-");
        slug = MULTIPLE_DASHES.matcher(slug).replaceAll("-");
        slug = LEADING_AND_TRAILING_DASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase();
    }

}
