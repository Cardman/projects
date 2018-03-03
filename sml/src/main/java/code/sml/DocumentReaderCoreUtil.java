package code.sml;

import code.util.StringList;

public final class DocumentReaderCoreUtil {

    public static Element setNull(String _fieldName, Document _document) {
        Element elt_ = _document.createElement("null");
        setFieldName(elt_, _fieldName);
        return elt_;
    }

    public static Element setBoolean(boolean _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Boolean");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Boolean.toString(_boolean));
        return elt_;
    }

    public static Element setByte(byte _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Byte");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Byte.toString(_boolean));
        return elt_;
    }

    public static Element setShort(short _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Short");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Short.toString(_boolean));
        return elt_;
    }

    public static Element setInteger(int _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Integer");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Integer.toString(_boolean));
        return elt_;
    }

    public static Element setLong(long _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Long");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Long.toString(_boolean));
        return elt_;
    }

    public static Element setString(String _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.String");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", _boolean);
        return elt_;
    }

    public static Element setStringList(StringList _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sl");
        setFieldName(elt_, _fieldName);
        for (String s: _boolean) {
            Element sub_ = setString(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static void setFieldName(Element _elt, String _fieldName) {
        if (_fieldName.isEmpty()) {
            return;
        }
        _elt.setAttribute("field", _fieldName);
    }
}
