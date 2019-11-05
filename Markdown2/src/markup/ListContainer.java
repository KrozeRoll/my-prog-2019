package markup;

public interface ListContainer {
    StringBuilder toTex(StringBuilder stringBuilder);
    StringBuilder toMarkdown(StringBuilder stringBuilder);
}
