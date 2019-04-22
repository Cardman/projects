package code.sml;

import code.util.BooleanList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class DocumentReaderCoreUtil {

    private DocumentReaderCoreUtil() {
    }
    public static boolean isNull(Element _elt) {
        return StringList.quickEq(_elt.getTagName(), "null");
    }

    public static boolean getBoolean(Element _elt) {
        return Boolean.parseBoolean(_elt.getAttribute("value"));
    }

    public static byte getByte(Element _elt) {
        return (byte) Numbers.parseLongZero(_elt.getAttribute("value"));
    }

    public static short getShort(Element _elt) {
        return (short) Numbers.parseLongZero(_elt.getAttribute("value"));
    }

    public static int getInteger(Element _elt) {
        return (int) Numbers.parseLongZero(_elt.getAttribute("value"));
    }

    public static long getLong(Element _elt) {
        return Numbers.parseLongZero(_elt.getAttribute("value"));
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

    public static BooleanList getBooleanList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        BooleanList list_ = new BooleanList(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBoolean(c));
        }
        return list_;
    }

    public static Numbers<Byte> getListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Numbers<Byte> list_ = new Numbers<Byte>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getByte(c));
        }
        return list_;
    }

    public static Numbers<Short> getListShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Numbers<Short> list_ = new Numbers<Short>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getShort(c));
        }
        return list_;
    }

    public static Numbers<Integer> getListInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Numbers<Integer> list_ = new Numbers<Integer>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getInteger(c));
        }
        return list_;
    }

    public static Numbers<Long> getListLong(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        Numbers<Long> list_ = new Numbers<Long>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getLong(c));
        }
        return list_;
    }

    public static CustList<BooleanList> getListBooleanList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<BooleanList> list_ = new CustList<BooleanList>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getBooleanList(c));
        }
        return list_;
    }

    public static CustList<Numbers<Long>> getListListLong(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Numbers<Long>> list_ = new CustList<Numbers<Long>>(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getListLong(c));
        }
        return list_;
    }

    public static CustList<Numbers<Byte>> getListListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CustList<Numbers<Byte>> list_ = new CustList<Numbers<Byte>>(new CollCapacity(len_));
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
        BooleanList values_ = new BooleanList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
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
        Numbers<Short> values_ = new Numbers<Short>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getShort(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
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
        Numbers<Integer> values_ = new Numbers<Integer>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getInteger(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
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
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
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
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static StringMap<Numbers<Integer>> getStringMapListInteger(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Numbers<Integer>> map_ = new StringMap<Numbers<Integer>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<Numbers<Integer>> values_ = new CustList<Numbers<Integer>>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getString(c));
            } else {
                values_.add(getListInteger(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
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
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NatTreeMap<Integer,Byte> getMapIntegerByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NatTreeMap<Integer,Byte> map_ = new NatTreeMap<Integer,Byte>(cap_);
        Numbers<Integer> keys_ = new Numbers<Integer>(cap_);
        Numbers<Byte> values_ = new Numbers<Byte>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Byte,Byte> getMapByteByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,Byte> map_ = new NumberMap<Byte,Byte>(cap_);
        Numbers<Byte> keys_ = new Numbers<Byte>(cap_);
        Numbers<Byte> values_ = new Numbers<Byte>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getByte(c));
            } else {
                values_.add(getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Byte,Numbers<Byte>> getMapByteListByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,Numbers<Byte>> map_ = new NumberMap<Byte,Numbers<Byte>>(cap_);
        Numbers<Byte> keys_ = new Numbers<Byte>(cap_);
        CustList<Numbers<Byte>> values_ = new CustList<Numbers<Byte>>(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getByte(c));
            } else {
                values_.add(getListByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Short,Boolean> getMapShortBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Short,Boolean> map_ = new NumberMap<Short,Boolean>(cap_);
        Numbers<Short> keys_ = new Numbers<Short>(cap_);
        BooleanList values_ = new BooleanList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getShort(c));
            } else {
                values_.add(getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Short,String> getMapShortString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Short,String> map_ = new NumberMap<Short,String>(cap_);
        Numbers<Short> keys_ = new Numbers<Short>(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getShort(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Integer,Boolean> getMapIntegerBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Integer,Boolean> map_ = new NumberMap<Integer,Boolean>(cap_);
        Numbers<Integer> keys_ = new Numbers<Integer>(cap_);
        BooleanList values_ = new BooleanList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static NumberMap<Integer,String> getMapIntegerString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Integer,String> map_ = new NumberMap<Integer,String>(cap_);
        Numbers<Integer> keys_ = new Numbers<Integer>(cap_);
        StringList values_ = new StringList(cap_);
        for (Element c: childElements_) {
            if (hasKey(c)) {
                keys_.add(getInteger(c));
            } else {
                values_.add(getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static boolean hasKey(Element _elt) {
        return _elt.hasAttribute("key");
    }
}
