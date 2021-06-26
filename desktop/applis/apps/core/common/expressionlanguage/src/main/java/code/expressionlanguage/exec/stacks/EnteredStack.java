package code.expressionlanguage.exec.stacks;

public interface EnteredStack extends RemovableVars {
    boolean isEntered();
    void setEntered(boolean _entered);
}
