package code.sml.core;

import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class DocumentWriterCoreUtil {

    public static final String VALUE = "1";
    public static final String ANON_TAG = "_";
    public static final String ES = "";
    public static final String FIELD = "0";

    private DocumentWriterCoreUtil() {
    }

    public static Element setBoolean(BoolVal _object, String _fieldName, Document _document) {
        return setBoolean(_object == BoolVal.TRUE,_fieldName,_document);
    }

    public static Element setBoolean(boolean _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        if (_object) {
            elt_.setAttribute(VALUE,"1");
        } else {
            elt_.setAttribute(VALUE,"0");
        }
        return elt_;
    }

    public static Element setByte(byte _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        elt_.setAttribute(VALUE, Long.toString(_object));
        return elt_;
    }

    public static Element setShort(short _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        elt_.setAttribute(VALUE, Long.toString(_object));
        return elt_;
    }

    public static Element setInteger(int _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        elt_.setAttribute(VALUE, Long.toString(_object));
        return elt_;
    }

    public static Element setLong(long _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        elt_.setAttribute(VALUE, Long.toString(_object));
        return elt_;
    }

    public static Element setString(String _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        elt_.setAttribute(VALUE, _object);
        return elt_;
    }

    public static Element setStringList(StringList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (String s: _object) {
            Element sub_ = setString(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setBoolValList(CustList<BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (BoolVal s: _object) {
            Element sub_ = setBoolean(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListByte(Bytes _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (byte s: _object) {
            Element sub_ = setByte(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListShort(Shorts _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (short s: _object) {
            Element sub_ = setShort(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListInteger(Ints _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (int s: _object) {
            Element sub_ = setInteger(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListLong(Longs _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (long s: _object) {
            Element sub_ = setLong(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListBooleanList(CustList<CustList<BoolVal>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (CustList<BoolVal> s: _object) {
            Element sub_ = setBoolValList(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListListLong(CustList<Longs> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (Longs s: _object) {
            Element sub_ = setListLong(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListListByte(CustList<Bytes> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (Bytes s: _object) {
            Element sub_ = setListByte(s, ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapBoolean(StringMap<BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, BoolVal> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoolean(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapInteger(StringMap<Long> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Long> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLong(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapString(StringMap<String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, String> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setString(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapListInteger(StringMap<Ints> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Ints> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListInteger(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapStringList(StringMap<StringList> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, StringList> s: _object.entryList()) {
            Element sub_ = setString(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setStringList(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerByte(IntTreeMap<Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,Byte> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setByte(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapByteByte(IntMap<Integer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer, Integer> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setInteger(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapByteListByte(IntMap<Ints> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,Ints> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListInteger(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerBoolean(IntMap<BoolVal> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<Integer,BoolVal> s: _object.entryList()) {
            Element sub_ = setInteger(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoolean(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapIntegerString(LongMap<String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(ANON_TAG);
        setFieldName(elt_, _fieldName);
        for (EntryCust<Long,String> s: _object.entryList()) {
            Element sub_ = setLong(s.getKey(), ES, _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setString(s.getValue(), ES, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static void setKey(Element _elt) {
        _elt.setAttribute("2", ES);
    }

    public static void setFieldName(Element _elt, String _fieldName) {
        if (_fieldName.isEmpty()) {
            return;
        }
        _elt.setAttribute(FIELD, _fieldName);
    }
}
