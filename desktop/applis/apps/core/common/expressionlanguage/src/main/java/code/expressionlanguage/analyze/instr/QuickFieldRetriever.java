package code.expressionlanguage.analyze.instr;

public final class QuickFieldRetriever implements FieldRetriever {
    private final Delimiters delimiters;

    public QuickFieldRetriever(Delimiters _delimiters) {
        this.delimiters = _delimiters;
    }

    @Override
    public int processFieldsStaticAccess(int _begin, String _word, int _to) {
        VariableInfo info_ = FullFieldRetriever.word(_begin, _word, _to);
        delimiters.getVariables().add(info_);
        return _to;
    }

    @Override
    public int tryGetVarField(int _begin, String _word, int _to) {
        return _begin;
    }
}
