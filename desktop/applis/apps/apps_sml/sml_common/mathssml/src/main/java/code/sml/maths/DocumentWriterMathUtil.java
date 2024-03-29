package code.sml.maths;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.*;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.EntryCust;
import code.util.*;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class DocumentWriterMathUtil {


    private static final String EMPTY_STRING = "";

    private DocumentWriterMathUtil() {
    }

    public static Element setLgInt(LgInt _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _boolean.display());
        return elt_;
    }

    public static Element setRate(Rate _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _boolean.display());
        return elt_;
    }
    public static Element setListLgInt(CustList<LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LgInt s: _object) {
            Element sub_ = setLgInt(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListRate(CustList<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Rate s: _object) {
            Element sub_ = setRate(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapLgInt(AbsMap<String,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, LgInt> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapRate(StringMap<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, Rate> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMapRateLgInt(MonteCarloNumber _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EventFreq<Rate> s: _object.getEvents()) {
            Element sub_ = setRate(s.getEvent(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getFreq(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setBooleanMapLgInt(CustList<EventFreq<BoolVal>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EventFreq<BoolVal> s: _object) {
            Element sub_ = DocumentWriterCoreUtil.setBoolean(s.getEvent(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getFreq(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMonteCarloBoolean(MonteCarloBoolean _object, String _fieldName, Document _document) {
        return setBooleanMapLgInt(_object.getEvents(), _fieldName, _document);
    }

    public static Element setMonteCarloString(MonteCarloString _object, String _fieldName, Document _document) {
        return setStringMapLgInt(_object.getLaw(), _fieldName, _document);
    }

    public static Element setMonteCarloNumber(MonteCarloNumber _object, String _fieldName, Document _document) {
        return setMapRateLgInt(_object, _fieldName, _document);
    }
    public static Element setMapLongRate(LongMap<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Long,Rate> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setLong(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

}
