package code.expressionlanguage.instr;

public final class QuickFieldRetriever implements FieldRetriever {
    private final Delimiters delimiters;

    public QuickFieldRetriever(Delimiters delimiters) {
        this.delimiters = delimiters;
    }

    @Override
    public int processFieldsStaticAccess(boolean _ctorCall, int _begin, String _word, int _to) {
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
