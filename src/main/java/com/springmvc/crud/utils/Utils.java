package com.springmvc.crud.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {
    public static String toSlug(String input) {
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");
        Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-").replaceAll("đ", "d").replaceAll("Đ", "d");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase();
    }

    public static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "huyennhat",
            "api_key", "836136537452954",
            "api_secret", "qsXAaQH1f5b5zcLCtXu7-p0NTto",
            "secure", true));
}
