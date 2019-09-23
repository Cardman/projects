package code.sml.maths;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustPoint;
import code.maths.geo.Polygon;
import code.maths.geo.Rect;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.BooleanList;
import code.util.BooleanMap;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class DocumentReaderMathUtil {

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final String FIELD_LAW = "law";
    private static final String FIELD_POINTS = "points";
 
    public static LgInt getLgInt(Element _boolean) {
        return LgInt.newLgInt(_boolean.getAttribute(ATTR_VALUE));
    }

    public static Rate getRate(Element _boolean) {
        return Rate.newRate(_boolean.getAttribute(ATTR_VALUE));
    }


    public static EqList<LgInt> getListLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        EqList<LgInt> list_ = new EqList<LgInt>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getLgInt(c));
        }
        return list_;
    }

    public static EqList<Rate> getListRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        EqList<Rate> list_ = new EqList<Rate>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getRate(c));
        }
        return list_;
    }

    public static StringMap<LgInt> getStringMapLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<LgInt> map_ = new StringMap<LgInt>(cap_);
        StringList keys_ = new StringList(cap_);
        EqList<LgInt> values_ = new EqList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Rate> getStringMapRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Rate> map_ = new StringMap<Rate>(cap_);
        StringList keys_ = new StringList(cap_);
        EqList<Rate> values_ = new EqList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }


    public static ObjectMap<Rate,LgInt> getMapRateLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Rate,LgInt> map_ = new ObjectMap<Rate,LgInt>(cap_);
        EqList<Rate> keys_ = new EqList<Rate>(cap_);
        EqList<LgInt> values_ = new EqList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getRate(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static BooleanMap<LgInt> getBooleanMapLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        BooleanMap<LgInt> map_ = new BooleanMap<LgInt>(cap_);
        BooleanList keys_ = new BooleanList(cap_);
        EqList<LgInt> values_ = new EqList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getBoolean(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static MonteCarloBoolean getMonteCarloBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MonteCarloBoolean law_ = new MonteCarloBoolean(cap_);
        law_.setLaw(new BooleanMap<LgInt>());
        for (Element c: childElements_) {
            String fieldName_ = c.getAttribute(ATTR_FIELD);
            if (StringList.quickEq(fieldName_, FIELD_LAW)) {
                law_.setLaw(getBooleanMapLgInt(c));
            }
        }
        return law_;
    }
    public static MonteCarloString getMonteCarloString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MonteCarloString law_ = new MonteCarloString(cap_);
        law_.setLaw(new StringMap<LgInt>());
        for (Element c: childElements_) {
            String fieldName_ = c.getAttribute(ATTR_FIELD);
            if (StringList.quickEq(fieldName_, FIELD_LAW)) {
                law_.setLaw(getStringMapLgInt(c));
            }
        }
        return law_;
    }
    public static MonteCarloNumber getMonteCarloNumber(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MonteCarloNumber law_ = new MonteCarloNumber(cap_);
        law_.setLaw(new ObjectMap<Rate,LgInt>(cap_));
        for (Element c: childElements_) {
            String fieldName_ = c.getAttribute(ATTR_FIELD);
            if (StringList.quickEq(fieldName_, FIELD_LAW)) {
                law_.setLaw(getMapRateLgInt(c));
            }
        }
        return law_;
    }
    public static LongMap<Rate> getMapLongRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        LongMap<Rate> map_ = new LongMap<Rate>(cap_);
        Longs keys_ = new Longs(cap_);
        EqList<Rate> values_ = new EqList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getLong(c));
            } else {
                values_.add(getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static CustPoint getCustPoint(Element _elt) {
        return CustPoint.newCustPoint(_elt.getAttribute(ATTR_VALUE));
    }

    public static Polygon getPolygon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Polygon object_ = new Polygon();
        for (Element c: childElements_) {
            getPolygon(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPolygon(Polygon _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_POINTS)) {
            _object.setPoints(getListCustPoint(_element));
            return;
        }
    }

    private static EqList<CustPoint> getListCustPoint(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<CustPoint> list_ = new EqList<CustPoint>(cap_);
        for (Element c: childElements_) {
            list_.add(getCustPoint(c));
        }
        return list_;
    }

    public static Rect getRect(Element _elt) {
        return Rect.newRect(_elt.getAttribute(ATTR_VALUE));
    }
}
