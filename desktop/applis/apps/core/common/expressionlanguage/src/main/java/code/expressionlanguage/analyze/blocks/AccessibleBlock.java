package code.expressionlanguage.analyze.blocks;


import code.expressionlanguage.common.AccessEnum;

public interface AccessibleBlock {

    AccessEnum getAccess();

    String getPackageName();

    String getFullName();
    String getParentFullName();

    String getOuterFullName();
}
