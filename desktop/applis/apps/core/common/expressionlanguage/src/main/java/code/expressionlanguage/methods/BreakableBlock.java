package code.expressionlanguage.methods;


public interface BreakableBlock extends StackableBlock {
    String getRealLabel();
    int getRealLabelOffset();
}
