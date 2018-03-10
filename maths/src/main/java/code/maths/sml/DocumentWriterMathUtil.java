package code.maths.sml;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.sml.Document;
import code.sml.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.BooleanMap;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NumberMap;
import code.util.ObjectNotNullMap;
import code.util.StringMap;

public final class DocumentWriterMathUtil {

    public static Element setLgInt(LgInt _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("li");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", _boolean.display());
        return elt_;
    }

    public static Element setRate(Rate _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("r");
        setFieldName(elt_, _fieldName);
        elt_.setAttribute("value", _boolean.display());
        return elt_;
    }
    public static Element setListLgInt(EqList<LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ql");
        setFieldName(elt_, _fieldName);
        for (LgInt s: _object) {
            Element sub_ = setLgInt(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListRate(EqList<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ql");
        setFieldName(elt_, _fieldName);
        for (Rate s: _object) {
            Element sub_ = setRate(s, "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapLgInt(StringMap<LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, LgInt> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapRate(StringMap<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<String, Rate> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setRate(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapRateLgInt(ObjectNotNullMap<Rate,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Rate,LgInt> s: _object.entryList()) {
            Element sub_ = setRate(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setBooleanMapLgInt(BooleanMap<LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("sm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Boolean, LgInt> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setBoolean(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMonteCarloBoolean(MonteCarloBoolean _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("mb");
        setFieldName(elt_, _fieldName);
        elt_.appendChild(setBooleanMapLgInt(_object.getLaw(), "law", _document));
        return elt_;
    }

    public static Element setMonteCarloString(MonteCarloString _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ms");
        setFieldName(elt_, _fieldName);
        elt_.appendChild(setStringMapLgInt(_object.getLaw(), "law", _document));
        return elt_;
    }

    public static Element setMonteCarloNumber(MonteCarloNumber _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("ms");
        setFieldName(elt_, _fieldName);
        elt_.appendChild(setMapRateLgInt(_object.getLaw(), "law", _document));
        return elt_;
    }
    public static Element setMapLongRate(NumberMap<Long,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement("nm");
        setFieldName(elt_, _fieldName);
        for (EntryCust<Long,Rate> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setLong(s.getKey(), "", _document);
            setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setRate(s.getValue(), "", _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }
    private static void setKey(Element _elt) {
        _elt.setAttribute("key", "");
    }
    private static void setFieldName(Element _elt, String _fieldName) {
        if (_fieldName.isEmpty()) {
            return;
        }
        _elt.setAttribute("field", _fieldName);
    }
}
