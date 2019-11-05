package markup;

import java.util.*;

public class Paragraph extends AbstractMarkup implements Container  {
    public Paragraph(List<Markup> list) {
        super(list, "paragraph");
    }
}
