package code.expressionlanguage.methods;

public interface InfoBlock extends WithEl, AccessibleBlock {

    boolean isFinalField();

    boolean isStaticField();

    String getFieldName();

    String getClassName();
}
