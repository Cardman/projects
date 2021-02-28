package code.sml.maths;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustPoint;
import code.maths.geo.Polygon;
import code.maths.geo.Rect;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.EntryCust;
import code.util.*;
import code.util.StringMap;

public final class DocumentWriterMathUtil {


    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
    private static final String FIELD_POINTS = "points";
    private static final String FIELD_LAW = "law";
    private static final String TYPE_CUST_POINT = "CustPoint";
    private static final String TYPE_LG_INT = "li";
    private static final String TYPE_RATE = "r";
    private static final String TYPE_LIST = "l";
    private static final String TYPE_MAP = "m";
    private static final String TYPE_POLYGON = "Polygon";
    private static final String TYPE_RECT = "Rect";

    private DocumentWriterMathUtil() {
    }

    public static Element setLgInt(LgInt _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LG_INT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _boolean.display());
        return elt_;
    }

    public static Element setRate(Rate _boolean, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_RATE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _boolean.display());
        return elt_;
    }
    public static Element setListLgInt(CustList<LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LgInt s: _object) {
            Element sub_ = setLgInt(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setListRate(CustList<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Rate s: _object) {
            Element sub_ = setRate(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapLgInt(AbsMap<String,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
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
        Element elt_ = _document.createElement(TYPE_MAP);
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
        Element elt_ = _document.createElement(TYPE_MAP);
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

    public static Element setBooleanMapLgInt(AbsMap<Boolean,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Boolean, LgInt> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setBoolean(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLgInt(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setMonteCarloBoolean(MonteCarloBoolean _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.appendChild(setBooleanMapLgInt(_object.getLaw(), FIELD_LAW, _document));
        return elt_;
    }

    public static Element setMonteCarloString(MonteCarloString _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.appendChild(setStringMapLgInt(_object.getLaw(), FIELD_LAW, _document));
        return elt_;
    }

    public static Element setMonteCarloNumber(MonteCarloNumber _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.appendChild(setMapRateLgInt(_object, FIELD_LAW, _document));
        return elt_;
    }
    public static Element setMapLongRate(LongMap<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
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

    public static Element setCustPoint(CustPoint _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CUST_POINT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    public static Element setPolygon(Polygon _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_POLYGON);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPolygon(_object,element_,_document);
        return element_;
    }

    private static void setPolygon(Polygon _object, Element _element, Document _document) {
        _element.appendChild(setListCustPoint(_object.getPoints(),FIELD_POINTS,_document));
    }

    public static Element setRect(Rect _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_RECT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setListCustPoint(CustList<CustPoint> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CustPoint s: _object) {
            elt_.appendChild(setCustPoint(s,EMPTY_STRING,_document));
        }
        return elt_;
    }
}
