package code.expressionlanguage.methods;

public interface InitVariable extends StackableBlock {

    String getVariableName();
    int getVariableNameOffset();

    String getClassName();
}
