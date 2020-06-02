package code.sml;

public final class Attr {

    private static final String NULL_VALUE = "";

    private static final String BEG_ATTR = " ";

    private static final String END_ATTR = "\"";

    private static final String SEPARATOR = "=\"";

    private final String name;

    //set for example by setting from the owner element
    private String value;

    protected Attr(String _name) {
        name = _name;
    }

    protected String export() {
        StringBuilder str_ = new StringBuilder(BEG_ATTR);
        str_.append(getName());
        str_.append(SEPARATOR);
        str_.append(DocumentBuilder.escape(getValue(), true));
        str_.append(END_ATTR);
        return str_.toString();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String _value) {
        if (_value == null) {
            value = NULL_VALUE;
        } else {
            value = _value;
        }
    }

    protected void setEscapedValue(String _escapedValue) {
        value = DocumentBuilder.transformSpecialCharsLtGt(_escapedValue);
    }

}
