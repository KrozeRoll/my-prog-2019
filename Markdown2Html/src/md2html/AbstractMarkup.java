package md2html;

import java.util.*;
import java.util.function.BiConsumer;

public abstract class AbstractMarkup implements Markup {
    private List<Markup> list;
    private String type;

    private enum HtmlTags {
        EMPHASIS("em"),
        STRONG("strong"),
        STRIKEOUT("s"),
        CODE("code");

        String tag;

        HtmlTags(String tag) {
            this.tag = tag;
        }

        String getPrefix() {
            return "<" + tag + ">";
        }

        String getSuffix() {
            return "</" + tag + ">";
        }
    }

    AbstractMarkup(List<Markup> list, String type) {
        this.list = list;
        this.type = type;
    }

    @Override
    public void toHtml(StringBuilder string) {
        toReadable(string, Markup::toHtml, HtmlTags.valueOf(type).getPrefix(), HtmlTags.valueOf(type).getSuffix());
    }

    private void toReadable(StringBuilder string, final BiConsumer<Markup, StringBuilder> biConsumer, String prefix, String suffix) {
        string.append(prefix);
        for (Markup entry : list) {
            biConsumer.accept(entry, string);
        }
        string.append(suffix);
    }
}