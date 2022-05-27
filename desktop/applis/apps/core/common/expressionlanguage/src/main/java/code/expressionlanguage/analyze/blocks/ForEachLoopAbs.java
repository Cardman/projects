package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.StringList;

public interface ForEachLoopAbs {
    String getLabel();

    int getExpressionOffset();

    OperationNode getRoot();

    StringList getSepErrors();
}
