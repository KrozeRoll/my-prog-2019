package md2html;

import java.util.*;

public class Strikeout extends AbstractMarkup {
    public Strikeout(List<Markup> list) {
        super(list, HtmlTags.STRIKEOUT);
    }
}
