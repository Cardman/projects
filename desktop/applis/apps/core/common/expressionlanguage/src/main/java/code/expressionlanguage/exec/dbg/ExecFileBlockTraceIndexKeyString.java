package code.expressionlanguage.exec.dbg;

public final class ExecFileBlockTraceIndexKeyString implements AbsKeyString<ExecFileBlockTraceIndex> {
    @Override
    public String keyString(ExecFileBlockTraceIndex _elt) {
        return _elt.keyStr();
    }
}
