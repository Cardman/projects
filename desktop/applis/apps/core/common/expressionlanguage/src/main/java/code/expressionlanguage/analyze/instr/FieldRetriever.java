package code.expressionlanguage.analyze.instr;

public interface FieldRetriever {

    int processFieldsStaticAccess(boolean _ctorCall, int _begin, String _word, int _to);
}
