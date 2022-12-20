package code.mock;

import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class MockConcurrentMapStructStruct extends MockConcurrentMap<Struct, Struct> {
    public MockConcurrentMapStructStruct() {
        super(new IdMap<Struct, Struct>());
    }
}
