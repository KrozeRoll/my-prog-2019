package markup;

import java.util.*;

public class ListItem {
    protected List<Container> list;

    public ListItem(List<Container> list) {
        this.list = list;
    }

    public StringBuilder toTex(StringBuilder string) {
        for (Container elem : list) {
            string.append("\\item ");
            string = elem.toTex(string);
        }
        return string;
    }
}
