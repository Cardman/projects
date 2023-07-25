package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class ExcPointBlockPair {
    private final boolean exact;
    private final String clName;
    private final ExcPoint value;

    public ExcPointBlockPair(boolean _ex, String _cl, ExcPoint _v) {
        this.exact = _ex;
        this.clName = _cl;
        this.value = _v;
    }
    public boolean match(ExcPointBlockPair _b) {
        return match(_b.clName,_b.exact);
    }
    public boolean match(String _file, boolean _ex) {
        return exact == _ex && StringUtil.quickEq(clName,_file);
    }
    public String keyStr() {
        if (isExact()) {
            return "1"+getClName();
        }
        return "0"+getClName();
    }

    public boolean isExact() {
        return exact;
    }

    public String getClName() {
        return clName;
    }

    public ExcPoint getValue() {
        return value;
    }
}
