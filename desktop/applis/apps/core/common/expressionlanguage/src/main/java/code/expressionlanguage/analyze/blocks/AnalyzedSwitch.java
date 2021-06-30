package code.expressionlanguage.analyze.blocks;

public interface AnalyzedSwitch {
    FileBlock getFile();
    void setInstanceTest(boolean _instance,String _instanceTest);

    void addErrorBlock(String _err);
}
