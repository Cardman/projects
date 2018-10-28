package code.expressionlanguage.common;

import code.expressionlanguage.methods.AccessibleBlock;
import code.util.StringList;

public interface GeneField extends AccessibleBlock {

    boolean isStaticField();

    boolean isFinalField();

    String getClassName();

    StringList getFieldName();
}
