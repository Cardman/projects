package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class ExcPointBlockKey implements AbsKeyPoint{
    private final boolean exact;
    private final String clName;

    public ExcPointBlockKey(boolean _ex, String _cl) {
        this.exact = _ex;
        this.clName = _cl;
    }
    public boolean match(ExcPointBlockKey _b) {
        return match(_b.clName,_b.exact);
    }
    public boolean match(String _file, boolean _ex) {
        return exact == _ex && StringUtil.quickEq(clName,_file);
    }

    public boolean isExact() {
        return exact;
    }

    public String getClName() {
        return clName;
    }
    @Override
    public String keyStr() {
        if (isExact()) {
            return "1"+getClName();
        }
        return "0"+getClName();
    }
}
