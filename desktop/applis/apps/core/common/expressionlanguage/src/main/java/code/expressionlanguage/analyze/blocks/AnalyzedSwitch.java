package code.expressionlanguage.analyze.blocks;

public interface AnalyzedSwitch {
    FileBlock getFile();
    boolean isForceInstance();
    void setInstanceTest(boolean _instance,String _instanceTest);

    void addErrorBlock(String _err);
}
