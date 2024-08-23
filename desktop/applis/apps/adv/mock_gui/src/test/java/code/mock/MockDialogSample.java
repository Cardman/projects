package code.mock;

public final class MockDialogSample extends MockAbsDialog{
    public MockDialogSample() {
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }
}
