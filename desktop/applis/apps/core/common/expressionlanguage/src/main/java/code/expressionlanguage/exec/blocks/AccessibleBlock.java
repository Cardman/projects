package code.expressionlanguage.exec.blocks;


import code.expressionlanguage.common.AccessEnum;

public interface AccessibleBlock {

    AccessEnum getAccess();

    String getPackageName();

    String getFullName();

    String getOuterFullName();
}
