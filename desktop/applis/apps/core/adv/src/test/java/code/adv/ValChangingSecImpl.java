package code.adv;

public final class ValChangingSecImpl implements ValueChangingSecond {

    private String action = "";

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void act() {
        action = " ";
    }

    public String getAction() {
        return action;
    }
}
