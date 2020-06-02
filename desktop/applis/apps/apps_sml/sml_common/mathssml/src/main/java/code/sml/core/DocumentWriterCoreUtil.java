package code.sml.core;

import code.sml.Document;
import code.sml.Element;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.*;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class DocumentWriterCoreUtil {

    private DocumentWriterCoreUtil() {
    }
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

    public static Element setListByte(Bytes _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (byte s: _object) {
            Element sub_ = setByte(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListShort(Shorts _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (short s: _object) {
            Element sub_ = setShort(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListInteger(Ints _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("n");
        setFieldName(elt_, _fieldName);
        for (int s: _object) {
            Element sub_ = setInteger(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListLong(Longs _object, String _fieldName, Document _document) {
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

    public static Element setListListLong(CustList<Longs> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("l");
        setFieldName(elt_, _fieldName);
        for (Longs s: _object) {
            Element sub_ = setListLong(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListListByte(CustList<Bytes> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ql");
        setFieldName(elt_, _fieldName);
        for (Bytes s: _object) {
            Element sub_ = setListByte(s, "", _document);
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

    public static Element setStringMapListInteger(StringMap<Ints> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Ints> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListInteger(s.getValue(), "", _document);
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

    public static Element setMapIntegerByte(IntTreeMap<Byte> _object, String _fieldName, Document _document) {
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

    public static Element setMapByteByte(ByteMap<Byte> _object, String _fieldName, Document _document) {
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

    public static Element setMapByteListByte(ByteMap<Bytes> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Byte,Bytes> s: _object.entryList()) {
            Element sub_ = setByte(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListByte(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapShortBoolean(ShortMap<Boolean> _object, String _fieldName, Document _document) {
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

    public static Element setMapShortString(ShortMap<String> _object, String _fieldName, Document _document) {
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

    public static Element setMapIntegerBoolean(IntMap<Boolean> _object, String _fieldName, Document _document) {
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

    public static Element setMapIntegerString(IntMap<String> _object, String _fieldName, Document _document) {
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
