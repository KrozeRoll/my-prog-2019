package md2html;

import java.util.*;

public class Strong extends AbstractMarkup {
    public Strong(List<Markup> list) {
        super(list, HtmlTags.STRONG);
    }
}
