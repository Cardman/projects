package code.sml;

public abstract class Attr {

    private static final String BEG_ATTR = " ";

    private static final String END_ATTR = "\"";

    private static final String SEPARATOR = "=\"";

    private final String name;

    protected Attr(String _name) {
        name = _name;
    }

    public String export() {
        StringBuilder str_ = new StringBuilder(BEG_ATTR);
        str_.append(getName());
        str_.append(SEPARATOR);
        str_.append(escape());
        str_.append(END_ATTR);
        return str_.toString();
    }

    public abstract Attr copy();
    public abstract String escape();

    public String getName() {
        return name;
    }

}
