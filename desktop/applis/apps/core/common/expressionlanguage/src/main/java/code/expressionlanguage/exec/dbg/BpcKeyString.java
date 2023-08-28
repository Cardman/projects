package code.expressionlanguage.exec.dbg;

public final class BpcKeyString implements AbsKeyString<BreakPointCondition> {
    @Override
    public String keyString(BreakPointCondition _elt) {
        return _elt.keyStr();
    }
}
