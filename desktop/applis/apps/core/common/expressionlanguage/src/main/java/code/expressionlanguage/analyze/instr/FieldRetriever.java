package code.expressionlanguage.analyze.instr;

public interface FieldRetriever {

    int processFieldsStaticAccess(int _begin, String _word, int _to);
    int tryGetVarField(int _begin, String _word, int _to);
}
