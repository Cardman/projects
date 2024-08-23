package code.mock;

public final class MockCommonFrameSample extends MockAbsCommonFrame{
    public MockCommonFrameSample() {
        super("");
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
