package code.xml.components;

import code.util.CustList;
import code.util.StringList;

public final class Attr implements Info {

    private static final String BEG_ATTR = " ";

    private static final String END_ATTR = "\"";

    private static final String SEPARATOR = "=\"";

    private String name;

    //set for example by setting from the owner element
    private String value;

    //value parsed from string 
    private String escapedValue;

    private int index = CustList.INDEX_NOT_FOUND_ELT;

    protected Attr() {
    }

    protected int getIndex() {
        return index;
    }

    protected void setIndex(int _index) {
        index = _index;
    }

    protected String export() {
        return BEG_ATTR+getName()+SEPARATOR+DocumentBuilder.escape(getValue(), true)+END_ATTR;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String _value) {
        value = _value;
        escapedValue = DocumentBuilder.escape(_value, true);
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
