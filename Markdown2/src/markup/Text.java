package markup;

public class Text implements Markdown {
    private String string;

    public Text (String string) {
        this.string = string;
    }

    public StringBuilder toTex(StringBuilder string) {
        string.append(this.string);
        return string;
    }

    public StringBuilder toMarkdown(StringBuilder string) {
        string.append(this.string);
        return string;
    }
}
