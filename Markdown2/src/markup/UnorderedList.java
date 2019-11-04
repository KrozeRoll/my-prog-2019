package markup;

import java.util.*;

public class UnorderedList extends AbstractMarkup {
    public UnorderedList(List<Markup> list) {
        super(list, "orderedList");
    }
}
