package md2html;

public class Text implements Markup {
    private String string;

    public Text (String string) {
        this.string = string;
    }

    public void toHtml(StringBuilder string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.string.length(); i++) {
            if (this.string.charAt(i) == '<') {
                stringBuilder.append("&lt;");
            } else if (this.string.charAt(i) == '>') {
                stringBuilder.append("&gt;");
            } else if (this.string.charAt(i) == '&') {
                stringBuilder.append("&amp;");
            } else if (i < this.string.length() - 1 && this.string.charAt(i) == '\\' && this.string.charAt(i + 1) != '\\') {
                continue;
            } else {
                stringBuilder.append(this.string.charAt(i));
            }
        }
        string.append(stringBuilder);
    }
}
