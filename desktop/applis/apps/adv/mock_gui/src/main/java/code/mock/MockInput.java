package code.mock;

public abstract class MockInput extends MockCustComponent{

    @Override
    public boolean isAccessible() {
        if (!isEnabled()) {
            return false;
        }
        return super.isAccessible();
    }
}
