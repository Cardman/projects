package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.ConstType;

public final class QuickFieldRetriever implements FieldRetriever {
    private final Delimiters delimiters;
    private final String string;

    public QuickFieldRetriever(Delimiters _delimiters, String _string) {
        this.delimiters = _delimiters;
        this.string = _string;
    }

    @Override
    public int processFieldsStaticAccess(boolean _ctorCall, int _begin, String _word, int _to) {
        int n_ = ElResolverCommon.addNamed(string, _begin, _to, delimiters.getNamedArgs());
        if (n_ >= _to) {
            return n_;
        }
        VariableInfo info_ = new VariableInfo();
        ConstType type_;
        type_ = ConstType.WORD;
        info_.setKind(type_);
        info_.setFirstChar(_begin);
        info_.setLastChar(_to);
        info_.setName(_word);
        delimiters.getVariables().add(info_);
        return _to;
    }
}
