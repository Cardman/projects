package code.bean.nat;

import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public interface NatDisplayableStruct extends Struct {
    StringStruct getDisplayedString();
}
