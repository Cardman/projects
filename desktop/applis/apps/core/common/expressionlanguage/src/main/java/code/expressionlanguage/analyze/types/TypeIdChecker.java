package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.StringExpUtil;

public final class TypeIdChecker implements AbsIdChecker {
    @Override
    public boolean match(String _i) {
        return StringExpUtil.isTypeLeafPartExec(_i);
    }
}
