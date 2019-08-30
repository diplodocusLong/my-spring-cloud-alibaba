package com.lianglong.bean;


/**
 * @author lianglong
 * @date 2019/8/26
 */
public class Transfer {
    private static int i = 1;
    private static String str = "abc";
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        int q = returnInt();
        System.out.println(q);
        System.out.println(i);
        String m = returnString(str);
        System.out.println(m);
        System.out.println(str);
        builder.append("hello");
        String s = returnString(builder);
        System.out.println(s);
        System.out.println(builder);


        StringBuffer s1 = new StringBuffer("hello");

        StringBuffer s2 = new StringBuffer("hello");

        change(s1, s2);

        System.out.println(s1);

        System.out.println(s2);

    }

    private static void change(StringBuffer s1, StringBuffer s2) {

        s1.append("world");

        s2 = s1;
    }

    public static int returnInt() {
        try {
            return i++;
        } finally {
            i++;
        }
    }

    public static String returnString(String str) {
        try {
            return str + 1;
        } finally {
            str = str + "bbq";
        }
    }

    public static String returnString(StringBuilder builder) {

        try {
            return builder.append(1).toString();
        } finally {
            builder.append("wq");
        }

    }
}
