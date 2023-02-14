package code.mock;

public final class MockWithActionSample implements MockWithAction{
    private int nb;
    @Override
    public void action(int _nb) {
        setNb(_nb);
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int _n) {
        this.nb = _n;
    }
}
