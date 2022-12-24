package com.springmvc.crud.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "huyennhat",
            "api_key", "836136537452954",
            "api_secret", "qsXAaQH1f5b5zcLCtXu7-p0NTto",
            "secure", true));

}
