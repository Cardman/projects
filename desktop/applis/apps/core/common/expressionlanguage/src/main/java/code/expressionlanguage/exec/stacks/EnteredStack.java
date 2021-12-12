package code.expressionlanguage.exec.stacks;

public interface EnteredStack extends RemovableVars, MultipleBlockStack {
    boolean isEntered();
    void setEntered(boolean _entered);
}
