package code.adv;

public final class ValChangingImpl implements ValueChanging {

    private final boolean condition;
    private String action = "";

    public ValChangingImpl(boolean _condition) {
        condition = _condition;
    }

    @Override
    public boolean skip() {
        return condition;
    }

    @Override
    public void act() {
        action = " ";
    }

    public String getAction() {
        return action;
    }
}
