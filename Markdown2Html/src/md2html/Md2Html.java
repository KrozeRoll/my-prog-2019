package md2html;

import java.io.*;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(new FileInputStream(new File(args[0])), "UTF-8");
            PrintWriter out;
            out = new PrintWriter(new File(args[1]));

            StringBuilder htmlAns = new StringBuilder();
            String paragraph = in.nextParagraph();
            htmlAns.append(readableParagraph(paragraph));
            paragraph = in.nextParagraph();
            while (paragraph != null) {
                htmlAns.append(System.lineSeparator());
                htmlAns.append(readableParagraph(paragraph));
                paragraph = in.nextParagraph();
            }
            out.println(htmlAns);
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }

    private static int getHeaderLevel(String paragraph) {
        int index = 0;
        while (index < paragraph.length() && paragraph.charAt(index) == '#') {
            index++;
        }
        if (Character.isWhitespace(paragraph.charAt(index))) {
            return index;
        } else {
            return 0;
        }
    }

    private static StringBuilder readableParagraph (String paragraph) {
        int headerLevel = getHeaderLevel(paragraph);
        StringBuilder readable = new StringBuilder();

        String headerTag = (headerLevel == 0 ? "p" : "h" + headerLevel);
        String paragraphContent = (headerLevel == 0 ? paragraph : paragraph.substring(headerLevel + 1));

        readable.append("<" + headerTag + ">");
        new Paragraph(toObject(paragraphContent)).toHtml(readable);
        readable.append("</" + headerTag + ">");

        return readable;
    }

    private static  List<Markup> toObject(String paragraph) {
        StringBuilder curString = new StringBuilder();
        List<Markup> list = new ArrayList<>();
        int length = paragraph.length();
        for (int i = 0; i < length; i++) {
            if (i < length - 1 && paragraph.charAt(i) == '*' && paragraph.charAt(i + 1) == '*') {
                int close = paragraph.indexOf("**", i + 2);
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Strong(toObject(paragraph.substring(i + 2, close))));
                    curString = new StringBuilder();
                    i = close + 1;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (i < length - 1 && paragraph.charAt(i) == '_' && paragraph.charAt(i + 1) == '_') {
                int close = paragraph.indexOf("__", i + 2);
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Strong(toObject(paragraph.substring(i + 2, close))));
                    curString = new StringBuilder();
                    i = close + 1;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (i < length - 1 && paragraph.charAt(i) == '-' && paragraph.charAt(i + 1) == '-') {
                int close = paragraph.indexOf("--", i + 2);
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Strikeout(toObject(paragraph.substring(i + 2, close))));
                    curString = new StringBuilder();
                    i = close + 1;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (paragraph.charAt(i) == '*') {
                int close = paragraph.indexOf("*", i + 1);
                while ((close != -1 && paragraph.charAt(close - 1) == '\\')) {
                    close = paragraph.indexOf("*", close + 1);
                }
                while ((close < paragraph.length() - 1 && paragraph.charAt(close + 1) == '*')) {
                    close = paragraph.indexOf("*", close + 2);
                }
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Emphasis(toObject(paragraph.substring(i + 1, close))));
                    curString = new StringBuilder();
                    i = close;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (paragraph.charAt(i) == '_') {
                int close = paragraph.indexOf("_", i + 1);
                while (close != -1 && paragraph.charAt(close - 1) == '\\') {
                    close = paragraph.indexOf("_", close + 1);
                }
                while ((close < paragraph.length() - 1 && paragraph.charAt(close + 1) == '_')) {
                    close = paragraph.indexOf("_", close + 2);
                }
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Emphasis(toObject(paragraph.substring(i + 1, close))));
                    curString = new StringBuilder();
                    i = close;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (paragraph.charAt(i) == '`') {
                int close = paragraph.indexOf("`", i + 1);
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Code(toObject(paragraph.substring(i + 1, close))));
                    curString = new StringBuilder();
                    i = close;
                } else {
                    curString.append(paragraph.charAt(i));
                }
            } else if (paragraph.charAt(i) == '[') {
                int close = paragraph.indexOf("]", i + 1);
                if (close != -1) {
                    if (curString.length() > 0) {
                        list.add(new Text(curString.toString()));
                    }
                    list.add(new Link(toObject(paragraph.substring(i + 1, close)), paragraph.substring(close + 2, paragraph.indexOf(')', close + 2))));
                    curString = new StringBuilder();
                    i = paragraph.indexOf(')', close + 2);
                } else {
                    curString.append(paragraph.charAt(i));
                }
            }

            else {
                curString.append(paragraph.charAt(i));
            }
        }
        list.add(new Text(curString.toString()));
        return list;
    }
}
