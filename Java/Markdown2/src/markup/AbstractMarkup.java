package markup;

import java.util.*;

public abstract class AbstractMarkup implements Markup {
    protected List<Markup> list;
    private String type;

    private static final Map<String, String> MARKDOWN = Map.of(
            "paragraph", "",
            "emphasis", "*",
            "strong", "__",
            "strikeout", "~");

    private static final Map<String, List <String>> TEX = Map.of(
            "paragraph", List.of("", ""),
            "emphasis", List.of("\\emph{", "}"),
            "strong", List.of("\\textbf{", "}"),
            "strikeout", List.of("\\textst{", "}"),
            "orderedList", List.of("\\begin{enumerate}", "\\end{enumerate}"),
            "unorderedList", List.of("\\begin{itemize}", "\\end{itemize}"));

    AbstractMarkup(List<Markup> list, String type) {
        this.list = list;
        this.type = type;
    }

    public StringBuilder toMarkdown(StringBuilder string) {
        string.append(MARKDOWN.get(type));
        for (Markup elem : list) {
            string = elem.toMarkdown(string);
        }
        string.append(MARKDOWN.get(type));
        return string;
    }

    public StringBuilder toTex(StringBuilder string) {
        string.append(TEX.get(type).get(0));
        for (Markup elem : list) {
            string = elem.toTex(string);
        }
        string.append(TEX.get(type).get(1));
        return string;
    }
}
