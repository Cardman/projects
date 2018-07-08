package code.expressionlanguage.methods;

import code.util.StringList;

public interface InitVariable extends StackableBlock {

    StringList getVariableNames();

    String getClassName();
}
