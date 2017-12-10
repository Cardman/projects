package code.xml.components;

import code.util.StringList;

public final class Attr implements Info {

    private static final String NULL_VALUE = "";

    private static final String BEG_ATTR = " ";

    private static final String END_ATTR = "\"";

    private static final String SEPARATOR = "=\"";

    private String name;

    //set for example by setting from the owner element
    private String value;

    //value parsed from string 
    private String escapedValue;

    protected Attr() {
    }

    protected String export() {
        return BEG_ATTR+getName()+SEPARATOR+DocumentBuilder.escape(getValue(), true)+END_ATTR;
    }

    public String getName() {
        return name;
    }

    protected void setName(String _name) {
        name = _name;
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
        escapedValue = DocumentBuilder.escape(value, true);
    }

    public String getEscapedValue() {
        return escapedValue;
    }

    protected void setEscapedValue(String _escapedValue) {
        escapedValue = _escapedValue;
        value = DocumentBuilder.transformSpecialChars(_escapedValue);
    }

    public String getTextContent() {
        return getValue();
    }

    public boolean isEqual(Attr _arg) {
        if (!StringList.quickEq(_arg.getName(), getName())) {
            return false;
        }
        if (!StringList.quickEq(_arg.getValue(), getValue())) {
            return false;
        }
        return true;
    }

    @Override
    public long compareDocumentPosition(Info _other) {
        // TODO Auto-generated method stub
        return 0;
    }
}
