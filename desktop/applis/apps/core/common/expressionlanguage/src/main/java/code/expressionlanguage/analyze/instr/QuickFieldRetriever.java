package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.ConstType;

public final class QuickFieldRetriever implements FieldRetriever {
    private final Delimiters delimiters;

    public QuickFieldRetriever(Delimiters _delimiters) {
        this.delimiters = _delimiters;
    }

    @Override
    public int processFieldsStaticAccess(int _begin, String _word, int _to) {
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

    @Override
    public int tryGetVarField(int _begin, String _word, int _to) {
        return _begin;
    }
}
