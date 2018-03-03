package code.sml;

import code.util.CollCapacity;
import code.util.Numbers;
import code.util.StringList;

public final class DocumentWriterCoreUtil {

    public static boolean isNull(Element _boolean) {
        return StringList.quickEq(_boolean.getTagName(), "null");
    }

    public static boolean getBoolean(Element _boolean) {
        return Boolean.parseBoolean(_boolean.getAttribute("value"));
    }

    public static byte getByte(Element _boolean) {
        return (byte) Numbers.parseLongZero(_boolean.getAttribute("value"));
    }

    public static short getShort(Element _boolean) {
        return (short) Numbers.parseLongZero(_boolean.getAttribute("value"));
    }

    public static int getInteger(Element _boolean) {
        return (int) Numbers.parseLongZero(_boolean.getAttribute("value"));
    }

    public static long getLong(Element _boolean) {
        return Numbers.parseLongZero(_boolean.getAttribute("value"));
    }

    public static String getString(Element _boolean) {
        return _boolean.getAttribute("value");
    }

    public static StringList getStringList(Element _boolean) {
        ElementList childElements_ = _boolean.getChildElements();
        int len_ = childElements_.getLength();
        StringList list_ = new StringList(new CollCapacity(len_));
        for (Element c: childElements_) {
            list_.add(getString(c));
        }
        return list_;
    }
}
