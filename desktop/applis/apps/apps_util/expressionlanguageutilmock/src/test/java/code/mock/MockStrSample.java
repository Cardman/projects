package code.mock;

import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.StructCallable;

public final class MockStrSample implements StructCallable {
    @Override
    public Struct call() {
        return new StringStruct("RESULT");
    }
}
