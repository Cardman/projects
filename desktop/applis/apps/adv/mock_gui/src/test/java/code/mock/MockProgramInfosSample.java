package code.mock;

import code.gui.TextAnswerValue;

public final class MockProgramInfosSample extends MockAbsProgramInfos{
    public MockProgramInfosSample(String _h, String _t, double _se, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new MockEventListIncr(_se,new int[0],new String[0],new TextAnswerValue[0]), _cust, new MockFileSet(_initMillis, _incrs,_roots));
    }
    public MockProgramInfosSample(String _h, String _t, long _initMillis, long[] _incrs, boolean _cust, String... _roots) {
        super(_h, _t, new MockEventListIncr(new int[0],new String[0],new TextAnswerValue[0]), _cust, new MockFileSet(_initMillis, _incrs,_roots));
    }

    @Override
    public MockAbsFrameFactory getMockFrameFactory() {
        return new MockFrameFactorySample(this);
    }
}
