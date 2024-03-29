package code.sml.maths;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.*;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class DocumentReaderMathUtil {

    private DocumentReaderMathUtil() {
    }

    public static LgInt getLgInt(Element _boolean) {
        return LgInt.newLgInt(_boolean.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static Rate getRate(Element _boolean) {
        return Rate.newRate(_boolean.getAttribute(DocumentReaderCoreUtil.VALUE));
    }


    public static CustList<LgInt> getListLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<LgInt> list_ = new CustList<LgInt>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getLgInt(c));
        }
        return list_;
    }

    public static CustList<Rate> getListRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Rate> list_ = new CustList<Rate>(new CollCapacity(len_));
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
        CustList<LgInt> values_ = new CustList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
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
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }


    public static CustList<EventFreq<Rate>> getMapRateLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CustList<EventFreq<Rate>> map_ = new CustList<EventFreq<Rate>>(cap_);
        CustList<Rate> keys_ = new CustList<Rate>(cap_);
        CustList<LgInt> values_ = new CustList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getRate(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.add(new EventFreq<Rate>(keys_.get(i), values_.get(i)));
        }
        return map_;
    }

    public static MonteCarloBoolean getBooleanMapLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MonteCarloBoolean map_ = new MonteCarloBoolean(cap_);
        CustList<BoolVal> keys_ = new CustList<BoolVal>(cap_);
        CustList<LgInt> values_ = new CustList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getBoolVal(c));
            } else {
                values_.add(getLgInt(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEvent(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static MonteCarloBoolean getMonteCarloBoolean(Element _elt) {
        return getBooleanMapLgInt(_elt);
    }
    public static MonteCarloString getMonteCarloString(Element _elt) {
        StringMap<LgInt> map_ = getStringMapLgInt(_elt);
        CollCapacity cap_ = new CollCapacity(map_.size());
        MonteCarloString law_ = new MonteCarloString(cap_);
        law_.setLaw(map_);
        return law_;
    }
    public static MonteCarloNumber getMonteCarloNumber(Element _elt) {
        CustList<EventFreq<Rate>> ls_ = getMapRateLgInt(_elt);
        CollCapacity cap_ = new CollCapacity(ls_.size());
        MonteCarloNumber law_ = new MonteCarloNumber(cap_);
        for (EventFreq<Rate> e: ls_) {
            law_.addQuickEvent(e.getEvent(),e.getFreq());
        }
        return law_;
    }
    public static LongMap<Rate> getMapLongRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        LongMap<Rate> map_ = new LongMap<Rate>(cap_);
        Longs keys_ = new Longs(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getLong(c));
            } else {
                values_.add(getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

}
