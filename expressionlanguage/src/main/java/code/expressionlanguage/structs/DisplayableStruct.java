package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public interface DisplayableStruct extends Struct {

    StringStruct getDisplayedString(ContextEl _an);
}
