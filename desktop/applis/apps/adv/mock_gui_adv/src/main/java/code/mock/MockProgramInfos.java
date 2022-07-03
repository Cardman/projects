package code.mock;

public final class MockProgramInfos extends MockAbsProgramInfos {

    private final MockFrameFactory mockFrameFactory = new MockFrameFactory(this);

    public MockProgramInfos(String _h, String _t, MockEventListIncr _se, boolean _cust, MockFileSet _set) {
        super(_h, _t, _se, _cust, _set);
    }

    public MockFrameFactory getMockFrameFactory() {
        return mockFrameFactory;
    }
}
