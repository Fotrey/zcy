package pers.zcy.myblogboot.utils;


import pers.zcy.myblogboot.entity.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringIds2CollectionConverter{

    public static List<Long> apply(String s) {
        if(null == s){
            return null;
        }
        List<Long> digitList = new ArrayList<>();
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(s);
        String result = m.replaceAll("");
        for (int i = 0; i < result.length(); i++) {
            digitList.add(Long.valueOf(result.substring(i, i+1)));

        }
        return digitList;

    }

    public static String recycle(Set<Tag> tags) {
        if(0 == tags.size()){
            return null;
        }
        List<String> idList = tags.stream()
                .map(tag -> tag.getId() + ",")
                .collect(Collectors.toList());
        String unhandled = String.join("", idList);
        String ids = unhandled.substring(0, unhandled.length() - 1);
        return ids;
    }
}
