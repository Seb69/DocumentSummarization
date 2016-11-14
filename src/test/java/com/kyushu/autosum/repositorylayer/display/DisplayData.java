package com.kyushu.autosum.repositorylayer.display;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;


/**
 * @author ANDRE
 * @since 16/05/16
 */
public class DisplayData {

    /**
     * Generic method in order to have a pretty display input Object in console
     *
     * @param t object (generic object)
     */
    public static <T> void input(T t) {

        StringBuilder input = getStringToDisplay(t);

        System.out.println("\n" + "\u001B[32m" + "INPUT (" + t.getClass().getSimpleName() + "): " + "\u001B[0m" + input + "\n");

    }

    /**
     * Generic method in order to have a pretty display output Object in console
     *
     * @param t object (generic object)
     */
    public static <T> void output(T t) {

        StringBuilder input = getStringToDisplay(t);

        System.out.println("\n" + "\u001B[31m" + "OUTPUT (" + t.getClass().getSimpleName() + "): " + "\u001B[0m" + input + "\n");

    }

    static <T> StringBuilder getStringToDisplay(T t) {

        StringBuilder input = new StringBuilder();

        if (t instanceof List)
            input.append(t.toString());

        else if (t instanceof Map)
            input.append(t.toString());

        else if (t instanceof Boolean)
            input.append(((Boolean) t).booleanValue());

        else if (t instanceof Long ||
                t instanceof Double ||
                t instanceof Integer ||
                t instanceof String)
            input.append(t);

        else
            input.append(ToStringBuilder.reflectionToString(t, ToStringStyle.MULTI_LINE_STYLE));

        return input;

    }

}
