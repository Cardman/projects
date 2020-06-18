package code.expressionlanguage.methods;


public interface AccessibleBlock {

    AccessEnum getAccess();

    String getPackageName();

    String getFullName();

    String getOuterFullName();
}
