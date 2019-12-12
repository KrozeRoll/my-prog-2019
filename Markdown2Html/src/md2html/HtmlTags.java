package md2html;

public enum HtmlTags {
    EMPHASIS("em"),
    STRONG("strong"),
    STRIKEOUT("s"),
    CODE("code"),
    LINK("a href");

    String tag;

    HtmlTags(String tag) {
        this.tag = tag;
    }

    String getPrefix() {
        return "<" + tag + ">";
    }

    String getSuffix() {
        return "</" + tag + ">";
    }
}