package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.opers.util.ClassField;

public interface GeneField extends AccessibleBlock {

    boolean isStaticField();

    boolean isFinalField();

    String getClassName();

    String getFieldName();

    ClassField getId();

}
