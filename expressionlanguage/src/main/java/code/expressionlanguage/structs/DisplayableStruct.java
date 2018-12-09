package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;

public interface DisplayableStruct extends Struct {

    StringStruct getDisplayedString(Analyzable _an);
}
