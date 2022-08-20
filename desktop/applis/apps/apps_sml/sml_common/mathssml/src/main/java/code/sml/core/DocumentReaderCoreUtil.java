package code.sml.core;

import code.sml.Element;
import code.sml.ElementList;
import code.util.BooleanList;
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

    private DocumentReaderCoreUtil() {
    }
    public static boolean isNull(Element _elt) {
        return StringUtil.quickEq(_elt.getTagName(), "null");
    }

    public static boolean getBoolean(Element _elt) {
        return Boolean.parseBoolean(_elt.getAttribute("value"));
    }

    public static BoolVal getBoolVal(Element _elt) {
        return ComparatorBoolean.of(getBoolean(_elt));
    }

    public static byte getByte(Element _elt) {
        return (byte) NumberUtil.parseLongZero(_elt.getAttribute("value"));
    }

    public static short getShort(Element _elt) {
        return (short) NumberUtil.parseLongZero(_elt.getAttribute("value"));
    }

    public static int getInteger(Element _elt) {
        return (int) NumberUtil.parseLongZero(_elt.getAttribute("value"));
    }

    public static long getLong(Element _elt) {
        return NumberUtil.parseLongZero(_elt.getAttribute("value"));
    }

    public static String getString(Element _elt) {
        return _elt.getAttribute("value");
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

    public static CustList<Boolean> getBooleanList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Boolean> list_ = new CustList<Boolean>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBoolean(c));
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

    public static Bytes getListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Bytes list_ = new Bytes(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getByte(c));
        }
        return list_;
    }

    public static Shorts getListShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Shorts list_ = new Shorts(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getShort(c));
        }
        return list_;
    }

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

    public static CustList<CustList<Boolean>> getListBooleanList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<CustList<Boolean>> list_ = new CustList<CustList<Boolean>>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBooleanList(c));
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

    public static CustList<Bytes> getListListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Bytes> list_ = new CustList<Bytes>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getListByte(c));
        }
        return list_;
    }

    public static StringMap<Boolean> getStringMapBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Boolean> map_ = new StringMap<Boolean>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Short> getStringMapShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Short> map_ = new StringMap<Short>(cap_);
        StringList keys_ = new StringList(cap_);
        Shorts values_ = new Shorts(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getShort(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Integer> getStringMapInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Integer> map_ = new StringMap<Integer>(cap_);
        StringList keys_ = new StringList(cap_);
        Ints values_ = new Ints(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getInteger(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
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
        int min_ = Math.min(keys_.size(), values_.size());
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
        int min_ = Math.min(keys_.size(), values_.size());
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
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<StringMap<String>> getStringMapStringMapString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<StringMap<String>> map_ = new StringMap<StringMap<String>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<StringMap<String>> values_ = new CustList<StringMap<String>>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getStringMapString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static IntTreeMap<Byte> getMapIntegerByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IntTreeMap<Byte> map_ = new IntTreeMap<Byte>(cap_);
        Ints keys_ = new Ints(cap_);
        Bytes values_ = new Bytes(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static ByteMap<Byte> getMapByteByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Byte> map_ = new ByteMap<Byte>(cap_);
        Bytes keys_ = new Bytes(cap_);
        Bytes values_ = new Bytes(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getByte(c));
            } else {
                values_.add(getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static ByteMap<Bytes> getMapByteListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Bytes> map_ = new ByteMap<Bytes>(cap_);
        Bytes keys_ = new Bytes(cap_);
        CustList<Bytes> values_ = new CustList<Bytes>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getByte(c));
            } else {
                values_.add(getListByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static ShortMap<Boolean> getMapShortBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ShortMap<Boolean> map_ = new ShortMap<Boolean>(cap_);
        Shorts keys_ = new Shorts(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getShort(c));
            } else {
                values_.add(getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static ShortMap<String> getMapShortString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ShortMap<String> map_ = new ShortMap<String>(cap_);
        Shorts keys_ = new Shorts(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getShort(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
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
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static IntMap<String> getMapIntegerString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IntMap<String> map_ = new IntMap<String>(cap_);
        Ints keys_ = new Ints(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static boolean hasKey(Element _elt) {
        return _elt.hasAttribute("key");
    }
}
