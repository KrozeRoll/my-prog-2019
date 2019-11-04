package markup;

import java.util.*;

public abstract class AbstractMarkdown implements Markdown {
    private List<Markdown> text = new ArrayList<>();
    private String type;

    final Map<String, String> MARKDOWN = Map.of("paragraph", "",
            "emphasis", "*",
            "strong", "__",
            "strikeout", "~");


    final Map<String, List <String>> TEX = Map.of("paragraph", List.of("", ""),
                                            "emphasis", List.of("\\emph{", "}"),
                                            "strong", List.of("\\textbf{", "}"),
                                            "strikeout", List.of("\\textst{", "}"));

    protected AbstractMarkdown(List<Markdown> text, String type) {
        this.text = text;
        this.type = type;
    }

    public StringBuilder toMarkdown(StringBuilder string) {
        string.append(MARKDOWN.get(type));
        for (Markdown elem : text) {
            string = elem.toMarkdown(string);
        }
        string.append(MARKDOWN.get(type));
        return string;
    }

    public StringBuilder toTex(StringBuilder string) {
        string.append(TEX.get(type).get(0));
        for (Markdown elem : text) {
            string = elem.toTex(string);
        }
        string.append(TEX.get(type).get(1));
        return string;
    }
}
