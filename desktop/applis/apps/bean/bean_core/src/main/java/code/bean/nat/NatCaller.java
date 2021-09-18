package code.bean.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public interface NatCaller {
    Struct re(ContextEl _cont, Struct _instance, Struct[] _args);
}
