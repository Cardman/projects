package code.expressionlanguage.analyze.blocks;

public interface AnalyzedSwitch {
    FileBlock getFile();
    void setInstanceTest(String _instanceTest);
    void setEnumTest(boolean _enumTest);

    void addErrorBlock(String _err);
}
