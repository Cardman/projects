package code.mock;

public final class MockProgramInfos extends MockAbsProgramInfos {

    private final MockFrameFactory mockFrameFactory = new MockFrameFactory(this);

    public MockProgramInfos(String _h, String _t, MockEventListIncr _se, MockFileSet _set) {
        super(_h, _t, _se, _set);
    }

    public MockProgramInfos(String _h, String _t, MockEventListIncr _se, MockFileSet _set, MockAbsRand _mar) {
        super(_h, _t, _se, _set, _mar);
    }

    public static MockProgramInfos inst(String _h, String _t, MockEventListIncr _se, MockFileSet _set) {
        return new MockProgramInfos(_h, _t, _se, _set, new MockTrueRand());
    }

    public MockFrameFactory getMockFrameFactory() {
        return mockFrameFactory;
    }
}
