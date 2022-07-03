package code.mock;

import code.expressionlanguage.structs.Struct;
import code.threads.AbstractThread;
import code.util.IdMap;

public final class MockConcurrentMapThreadStruct extends MockConcurrentMap<AbstractThread, Struct> {
    public MockConcurrentMapThreadStruct() {
        super(new IdMap<AbstractThread, Struct>());
    }
}
