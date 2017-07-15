package code.xml;

public enum StandardCharsets {
    UTF_8("UTF-8"),
    ISO_8859_1("ISO-8859-1");

    private final String name;

    StandardCharsets(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }
}
