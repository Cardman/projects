package code.sml.core;

import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DocumentReaderCoreUtil {

    public static final String FIELD = "0";
    public static final String VALUE = "1";

    private DocumentReaderCoreUtil() {
    }

    public static boolean getBoolean(Element _elt) {
        return StringUtil.quickEq("1",_elt.getAttribute(VALUE));
    }

    public static BoolVal getBoolVal(Element _elt) {
        return ComparatorBoolean.of(getBoolean(_elt));
    }

//    public static byte getByte(Element _elt) {
//        return (byte) NumberUtil.parseLongZero(_elt.getAttribute(VALUE));
//    }
//
//    public static short getShort(Element _elt) {
//        return (short) NumberUtil.parseLongZero(_elt.getAttribute(VALUE));
//    }

    public static int getInteger(Element _elt) {
        return (int) NumberUtil.parseLongZero(_elt.getAttribute(VALUE));
    }

    public static long getLong(Element _elt) {
        return NumberUtil.parseLongZero(_elt.getAttribute(VALUE));
    }

    public static String getString(Element _elt) {
        return _elt.getAttribute(VALUE);
    }

    public static StringList getStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        StringList list_ = new StringList(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getString(c));
        }
        return list_;
    }

    public static CustList<BoolVal> getBoolValList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<BoolVal> list_ = new CustList<BoolVal>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBoolVal(c));
        }
        return list_;
    }

//    public static Bytes getListByte(Element _elt) {
//        ElementList childElements_ = _elt.getChildElements();
//        int len_ = childElements_.getLength();
//        Bytes list_ = new Bytes(new CollCapacity(len_));
//        for (Element c: childElements_) {
//            list_.add(getInteger(c));
//        }
//        return list_;
//    }
//
//    public static Shorts getListShort(Element _elt) {
//        ElementList childElements_ = _elt.getChildElements();
//        int len_ = childElements_.getLength();
//        Shorts list_ = new Shorts(new CollCapacity(len_));
//        for (Element c: childElements_) {
//            list_.add(getShort(c));
//        }
//        return list_;
//    }

    public static Ints getListInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Ints list_ = new Ints(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getInteger(c));
        }
        return list_;
    }

    public static Longs getListLong(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Longs list_ = new Longs(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getLong(c));
        }
        return list_;
    }

    public static CustList<CustList<BoolVal>> getListBooleanList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<CustList<BoolVal>> list_ = new CustList<CustList<BoolVal>>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBoolValList(c));
        }
        return list_;
    }

    public static CustList<Longs> getListListLong(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Longs> list_ = new CustList<Longs>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getListLong(c));
        }
        return list_;
    }
//
//    public static CustList<Bytes> getListListByte(Element _elt) {
//        ElementList childElements_ = _elt.getChildElements();
//        int len_ = childElements_.getLength();
//        CustList<Bytes> list_ = new CustList<Bytes>(new CollCapacity(len_));
//        for (Element c: childElements_) {
//            list_.add(getListByte(c));
//        }
//        return list_;
//    }

    public static StringMap<BoolVal> getStringMapBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<BoolVal> map_ = new StringMap<BoolVal>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Long> getStringMapInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Long> map_ = new StringMap<Long>(cap_);
        StringList keys_ = new StringList(cap_);
        Longs values_ = new Longs(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getLong(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<String> getStringMapString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<String> map_ = new StringMap<String>(cap_);
        StringList keys_ = new StringList(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<StringList> getStringMapStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<StringList> map_ = new StringMap<StringList>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<StringList> values_ = new CustList<StringList>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getStringList(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Ints> getStringMapListInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Ints> map_ = new StringMap<Ints>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<Ints> values_ = new CustList<Ints>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getListInteger(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
//
//    public static IntTreeMap<Integer> getMapIntegerByte(Element _elt) {
//        ElementList childElements_ = _elt.getChildElements();
//        int len_ = childElements_.getLength();
//        CollCapacity cap_ = new CollCapacity(len_/2);
//        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>(cap_);
//        Ints keys_ = new Ints(cap_);
//        Ints values_ = new Ints(cap_);
//        for (Element c: childElements_) {
//            if (hasKey(c)) {
//                keys_.add(getInteger(c));
//            } else {
//                values_.add(getInteger(c));
//            }
//        }
//        int min_ = NumberUtil.min(keys_.size(), values_.size());
//        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
//            map_.put(keys_.get(i), values_.get(i));
//        }
//        return map_;
//    }
    public static IntMap<Integer> getMapByteByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IntMap<Integer> map_ = new IntMap<Integer>(cap_);
        Ints keys_ = new Ints(cap_);
        Ints values_ = new Ints(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getInteger(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static IntMap<CustList<Integer>> getMapByteListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IntMap<CustList<Integer>> map_ = new IntMap<CustList<Integer>>(cap_);
        Ints keys_ = new Ints(cap_);
        CustList<CustList<Integer>> values_ = new CustList<CustList<Integer>>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getListInteger(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static IntMap<BoolVal> getMapIntegerBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IntMap<BoolVal> map_ = new IntMap<BoolVal>(cap_);
        Ints keys_ = new Ints(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static LongMap<String> getMapIntegerString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        LongMap<String> map_ = new LongMap<String>(cap_);
        Longs keys_ = new Longs(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getLong(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static boolean hasKey(Element _elt) {
        return _elt.hasAttribute("2");
    }
}
