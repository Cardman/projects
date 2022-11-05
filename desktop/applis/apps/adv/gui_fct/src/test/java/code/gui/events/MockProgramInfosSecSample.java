package code.gui.events;

import code.gui.TextAnswerValue;
import code.mock.MockAbsFrameFactory;
import code.mock.MockAbsProgramInfos;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;

public final class MockProgramInfosSecSample extends MockAbsProgramInfos {
    public MockProgramInfosSecSample(String _h, String _t, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new MockEventListIncr(new int[0],new String[0],new TextAnswerValue[0]), _cust, new MockFileSet(_initMillis, _incrs,_roots));
    }

    @Override
    public MockAbsFrameFactory getMockFrameFactory() {
        return new MockFrameFactorySecSample(this);
    }
}
