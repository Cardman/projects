package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class ExcPointBlockKey implements AbsKeyPoint{
    public static final int SAME = 2;
    public static final int SAME_FAMILY = 1;
    public static final int INHERIT = 0;
    private final int exact;
    private final String clName;

    public ExcPointBlockKey(int _ex, String _cl) {
        this.exact = _ex;
        this.clName = _cl;
    }
    public boolean match(ExcPointBlockKey _b) {
        return match(_b.clName,_b.exact);
    }
    public boolean match(String _file, int _ex) {
        return exact == _ex && StringUtil.quickEq(clName,_file);
    }

    public int isExact() {
        return exact;
    }

    public String getClName() {
        return clName;
    }
    @Override
    public String keyStr() {
        return isExact()+getClName();
    }
}
