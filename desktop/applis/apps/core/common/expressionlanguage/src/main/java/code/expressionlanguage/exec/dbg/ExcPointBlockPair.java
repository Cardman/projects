package code.expressionlanguage.exec.dbg;

import code.util.core.StringUtil;

public final class ExcPointBlockPair {
    private final String clName;
    private final ExcPoint value;

    public ExcPointBlockPair(String _cl, ExcPoint _v) {
        this.clName = _cl;
        this.value = _v;
    }
    public boolean match(ExcPointBlockPair _b) {
        return match(_b.clName);
    }
    public boolean match(String _file) {
        return StringUtil.quickEq(clName,_file);
    }
    public String keyStr() {
        return getClName();
    }

    public String getClName() {
        return clName;
    }

    public ExcPoint getValue() {
        return value;
    }
}
