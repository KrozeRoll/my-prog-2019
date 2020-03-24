package markup;

import java.util.*;

public class OrderedList extends AbstractContainer  {
    public OrderedList(List<ListItem> list) {
        super(list, "orderedList");
    }
}
