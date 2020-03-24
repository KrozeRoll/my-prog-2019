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

            in.close();
            out.close();
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

        readable.append("<").append(headerTag).append(">");
        new Paragraph(toObject(paragraphContent)).toHtml(readable);
        readable.append("</").append(headerTag).append(">");

        return readable;
    }

    private static List<Markup> toObject(String paragraph) {
        StringBuilder curString = new StringBuilder();
        List<Markup> list = new ArrayList<>();
        int length = paragraph.length();
        for (int i = 0; i < length; i++) {
            int newI = -1;
            if (i < length - 1 && paragraph.charAt(i) == '*' && paragraph.charAt(i + 1) == '*') {
                int close = paragraph.indexOf("**", i + 2);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.STRONG);

            } else if (i < length - 1 && paragraph.charAt(i) == '_' && paragraph.charAt(i + 1) == '_') {
                int close = paragraph.indexOf("__", i + 2);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.STRONG);

            } else if (i < length - 1 && paragraph.charAt(i) == '-' && paragraph.charAt(i + 1) == '-') {
                int close = paragraph.indexOf("--", i + 2);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.STRIKEOUT);

            } else if (paragraph.charAt(i) == '*') {
                int close = findClose('*', i, paragraph);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.EMPHASIS);

            } else if (paragraph.charAt(i) == '_') {
                int close = findClose('_', i, paragraph);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.EMPHASIS);

            } else if (paragraph.charAt(i) == '`') {
                int close = paragraph.indexOf("`", i + 1);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.CODE);

            } else if (paragraph.charAt(i) == '[') {
                int close = paragraph.indexOf("]", i + 1);
                newI = addMarkup(curString, paragraph, i, close, list, HtmlTags.LINK);
            }

            if (newI != -1) {
                i = newI;
                curString = new StringBuilder();
            } else {
                curString.append(paragraph.charAt(i));
            }
        }
        list.add(new Text(curString.toString()));
        return list;
    }

    private static int addMarkup(StringBuilder curString, String paragraph, int i, int close, List<Markup> list, HtmlTags type) {
        if (close != -1) {
            if (curString.length() > 0) {
                list.add(new Text(curString.toString()));
            }
            switch (type) {
                case EMPHASIS:
                    list.add(new Emphasis(toObject(paragraph.substring(i + 1, close))));
                    return close;
                case STRONG:
                    list.add(new Strong(toObject(paragraph.substring(i + 2, close))));
                    return close + 1;
                case STRIKEOUT:
                    list.add(new Strikeout(toObject(paragraph.substring(i + 2, close))));
                    return close + 1;
                case CODE:
                    list.add(new Code(toObject(paragraph.substring(i + 1, close))));
                    return close;
                case LINK:
                    list.add(new Link(toObject(paragraph.substring(i + 1, close)), paragraph.substring(close + 2, paragraph.indexOf(')', close + 2))));
                    return paragraph.indexOf(')', close + 2);
            }
        } else {
            return -1;
        }
        return -1;
    }

    private static int findClose(Character s, int i, String paragraph) {
        boolean crit1;
        boolean crit2 = false;
        int close = i;
        do {
            close = paragraph.indexOf(s, close + 1 + (crit2 ? 1 : 0));
            crit1 = close != -1 && paragraph.charAt(close - 1) == '\\';
            crit2 = close != -1 && close < paragraph.length() - 1 && paragraph.charAt(close + 1) == s;
        } while (crit1 || crit2);
        return close;
    }
}
