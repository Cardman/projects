package code.expressionlanguage.exec.blocks;

public final class ExecQualifEnumCaseCondition extends ExecAbstractCaseCondition {

    private final String className;

    private final String name;

    public ExecQualifEnumCaseCondition(String _className,String _name) {
        className = _className;
        name = _name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }
}
