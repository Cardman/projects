package code.formathtml.exec.blocks;

public final class RendQualifEnumCaseCondition extends RendAbstractCaseCondition {

    private final String className;

    private final String name;

    public RendQualifEnumCaseCondition(String _className,String _name) {
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
