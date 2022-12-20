package code.mock;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class MockConcurrentMapStringStruct extends MockConcurrentMap<String, Struct> {
    public MockConcurrentMapStringStruct() {
        super(new StringMap<Struct>());
    }
}
