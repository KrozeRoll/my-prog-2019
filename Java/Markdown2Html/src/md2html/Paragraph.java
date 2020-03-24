package md2html;

        import java.util.*;

public class Paragraph  {
    protected List<Markup> list;

    public Paragraph(List<Markup> list) {
        this.list = list;
    }

    public void toHtml(StringBuilder string) {
        for (Markup elem : list) {
            elem.toHtml(string);
        }
    }
}
