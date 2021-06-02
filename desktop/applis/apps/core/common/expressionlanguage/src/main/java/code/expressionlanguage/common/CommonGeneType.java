package code.expressionlanguage.common;

import code.util.Ints;

public interface CommonGeneType {
    Ints getTypeVarCounts();
    boolean withoutInstance();

    String getFullName();

    String getGenericString();
}
