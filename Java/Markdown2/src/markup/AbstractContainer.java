package markup;

import java.util.*;

public abstract class AbstractContainer implements Container {
    protected List<ListItem> list;
    private String type;

    private static final Map<String, List <String>> TEX = Map.of(
            "paragraph", List.of("", ""),
            "orderedList", List.of("\\begin{enumerate}", "\\end{enumerate}"),
            "unorderedList", List.of("\\begin{itemize}", "\\end{itemize}"));

    AbstractContainer(List<ListItem> list, String type) {
        this.list = list;
        this.type = type;
    }

    public StringBuilder toTex(StringBuilder string) {
        string.append(TEX.get(type).get(0));
        for (ListItem elem : list) {
            string = elem.toTex(string);
        }
        string.append(TEX.get(type).get(1));
        return string;
    }
}
