package markup;

import java.util.*;

public class ListItem extends AbstractMarkup {
    public ListItem(List<Markup> list) {
        super(list, "listItem");
    }

    public StringBuilder toTex(StringBuilder string) {
        for (Markup elem : list) {
            string.append("\\item ");
            string = elem.toTex(string);
        }
        return string;
    }
}
