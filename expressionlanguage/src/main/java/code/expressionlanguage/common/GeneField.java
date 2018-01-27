package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;

public interface GeneField extends AccessibleBlock {

    boolean isStaticField();

    boolean isFinalField();

    String getClassName();

    String getFieldName();

}
