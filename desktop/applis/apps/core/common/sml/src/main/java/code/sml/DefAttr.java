package code.sml;

import code.util.CustList;

public final class DefAttr extends Attr {

    private static final String NULL_VALUE = "";

    //set for example by setting from the owner element
    private String value;

    public DefAttr(String _name) {
        super(_name);
    }

    @Override
    public Attr copy() {
        DefAttr att_ = new DefAttr(getName());
        att_.value = value;
        return att_;
    }

    @Override
    public String escape() {
        return FullNode.escape(getValue(), true);
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

    public void setEscapedValue(String _escapedValue, CustList<EncodedChar> _encodes) {
        value = DocumentBuilder.transformSpecialChars(_escapedValue, _encodes);
    }

}
