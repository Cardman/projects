package code.sml;

import code.util.BooleanList;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class DocumentWriterCoreUtil {

    public static Element setNull(String _fieldName, Document _document) {
        Element elt_ = _document.createElement("null");
        setFieldName(elt_, _fieldName);
        return elt_;
    }

    public static Element setBoolean(boolean _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Boolean");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Boolean.toString(_object));
        return elt_;
    }

    public static Element setByte(byte _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Byte");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Byte.toString(_object));
        return elt_;
    }

    public static Element setShort(short _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Short");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Short.toString(_object));
        return elt_;
    }

    public static Element setInteger(int _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Integer");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Integer.toString(_object));
        return elt_;
    }

    public static Element setLong(long _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.Long");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", Long.toString(_object));
        return elt_;
    }

    public static Element setString(String _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("java.lang.String");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", _object);
        return elt_;
    }

    public static Element setStringList(StringList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sl");
        setFieldName(elt_, _fieldName);
        for (String s: _object) {
            Element sub_ = setString(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setBooleanList(BooleanList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("bl");
        setFieldName(elt_, _fieldName);
        for (boolean s: _object) {
            Element sub_ = setBoolean(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setNumbersByte(Numbers<Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (byte s: _object) {
            Element sub_ = setByte(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListShort(Numbers<Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (short s: _object) {
            Element sub_ = setShort(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListInteger(Numbers<Integer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (int s: _object) {
            Element sub_ = setInteger(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListLong(Numbers<Long> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (long s: _object) {
            Element sub_ = setLong(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListBooleanList(CustList<BooleanList> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("l");
        setFieldName(elt_, _fieldName);
        for (BooleanList s: _object) {
            Element sub_ = setBooleanList(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListNumbersLong(CustList<Numbers<Long>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("l");
        setFieldName(elt_, _fieldName);
        for (Numbers<Long> s: _object) {
            Element sub_ = setListLong(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListListByte(EqList<Numbers<Byte>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ql");
        setFieldName(elt_, _fieldName);
        for (Numbers<Byte> s: _object) {
            Element sub_ = setNumbersByte(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapBoolean(StringMap<Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Boolean> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoolean(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapShort(StringMap<Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Short> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setShort(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapInteger(StringMap<Integer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Integer> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setInteger(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapString(StringMap<String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, String> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setString(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapStringList(StringMap<StringList> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, StringList> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setStringList(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapStringMapString(StringMap<StringMap<String>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, StringMap<String>> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setStringMapString(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerByte(NatTreeMap<Integer,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,Byte> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setByte(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapByteByte(NumberMap<Byte,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Byte,Byte> s: _object.entryList()) {
            Element sub_ = setByte(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setByte(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapByteListByte(NumberMap<Byte,Numbers<Byte>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Byte,Numbers<Byte>> s: _object.entryList()) {
            Element sub_ = setByte(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setNumbersByte(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapShortBoolean(NumberMap<Short,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Short,Boolean> s: _object.entryList()) {
            Element sub_ = setShort(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoolean(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapByteShortString(NumberMap<Short,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Short,String> s: _object.entryList()) {
            Element sub_ = setShort(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setString(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerBoolean(NumberMap<Integer,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,Boolean> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoolean(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerString(NumberMap<Integer,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,String> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setString(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static void setKey(Element _elt) {
        _elt.setAttribute("key", "");
    }

    public static void setFieldName(Element _elt, String _fieldName) {
        if (_fieldName.isEmpty()) {
            return;
        }
        _elt.setAttribute("field", _fieldName);
    }
}
