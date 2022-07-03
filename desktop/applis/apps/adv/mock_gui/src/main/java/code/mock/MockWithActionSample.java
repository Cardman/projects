package code.mock;

public final class MockWithActionSample implements MockWithAction{
    private int nb;
    @Override
    public void action(int _nb) {
        nb = _nb;
    }

    public int getNb() {
        return nb;
    }
}
