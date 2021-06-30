package code.formathtml.exec.blocks;

public final class RendEnumCaseCondition extends RendAbstractCaseCondition {

    private final String value;

    public RendEnumCaseCondition(String _value) {
        value = _value;
    }

    public String getValue() {
        return value;
    }
}
