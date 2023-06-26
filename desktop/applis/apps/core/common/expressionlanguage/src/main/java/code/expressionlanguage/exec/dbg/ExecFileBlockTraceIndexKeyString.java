package code.expressionlanguage.exec.dbg;

public final class ExecFileBlockTraceIndexKeyString implements AbsKeyString<AbsCallContraints> {
    @Override
    public String keyString(AbsCallContraints _elt) {
        return _elt.keyStr();
    }
}
