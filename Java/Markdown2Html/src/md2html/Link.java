package md2html;

import java.util.*;

public class Link implements Markup {
    private String url;
    private List <Markup> list;

    public Link(List<Markup> list, String url) {
        this.list = list;
        this.url = url;
    }

    public void toHtml(StringBuilder string) {
        string.append("<a href=\'");
        string.append(url);
        string.append("\'>");
        for (Markup elem : list) {
            elem.toHtml(string);
        }
        string.append("</a>");
    }
}
