package markup;

public interface Markdown {
    StringBuilder toTex(StringBuilder stringBuilder);
    StringBuilder toMarkdown(StringBuilder stringBuilder);
}
