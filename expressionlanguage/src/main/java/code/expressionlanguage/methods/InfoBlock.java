package code.expressionlanguage.methods;

public interface InfoBlock extends WithEl {

    boolean isStaticField();

    String getFieldName();

    String getClassName();
}
