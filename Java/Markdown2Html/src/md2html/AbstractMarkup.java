package md2html;

import java.util.*;
import java.util.function.BiConsumer;

public abstract class AbstractMarkup implements Markup {
    private List<Markup> list;
    private HtmlTags type;

    AbstractMarkup(List<Markup> list, HtmlTags type) {
        this.list = list;
        this.type = type;
    }

    @Override
    public void toHtml(StringBuilder string) {
        toReadable(string, Markup::toHtml, type.getPrefix(), type.getSuffix());
    }

    private void toReadable(StringBuilder string, final BiConsumer<Markup, StringBuilder> biConsumer, String prefix, String suffix) {
        string.append(prefix);
        for (Markup entry : list) {
            biConsumer.accept(entry, string);
        }
        string.append(suffix);
    }
}