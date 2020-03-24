package markup;

public interface Markup {
    StringBuilder toTex(StringBuilder stringBuilder);
    StringBuilder toMarkdown(StringBuilder stringBuilder);
}
