package code.expressionlanguage.utilimpl;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.mock.MockContextGenerator;
import code.threads.AbstractAtomicBoolean;

public final class MockResultContextNextIm implements AbsLightMemoResultContextNext {

    @Override
    public AbsLightContextGenerator generate(AbstractAtomicBoolean _at) {
        return new MockContextGenerator(_at);
    }
}
