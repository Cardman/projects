package code.mock;

public final class MockFalseRand implements MockAbsRand{
    @Override
    public boolean edit() {
        return false;
    }
}
