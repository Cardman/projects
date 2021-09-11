package code.expressionlanguage.analyze.variables;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.common.StringExpUtil;

public final class FoundVariable {
    private AnaLocalVariable val;
    private int deep = -1;
    public FoundVariable(String _str, AnalyzedPageEl _page) {
        val = _page.getInfosVars().getVal(_str);
        if (val == null) {
            String shortStr_ = StringExpUtil.skipPrefix(_str);
            AnaLocalVariable loc_ = _page.getInfosVars().getVal(shortStr_);
            deep = StringExpUtil.countPrefix(_str, '#');
            if (loc_ != null) {
                deep--;
            }
            AnaCache cache_ = _page.getCache();
            val = cache_.getLocalVar(shortStr_,deep);
        }
    }

    public AnaLocalVariable getVal() {
        return val;
    }

    public int getDeep() {
        return deep;
    }
}
