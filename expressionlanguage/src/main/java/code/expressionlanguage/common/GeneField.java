package code.expressionlanguage.common;

import code.util.StringList;

public interface GeneField {

    boolean isStaticField();

    boolean isFinalField();

    String getClassName();

    StringList getFieldName();
}
