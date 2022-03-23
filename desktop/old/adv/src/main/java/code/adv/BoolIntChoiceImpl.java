package code.adv;

public final class BoolIntChoiceImpl implements AbsBoolIntChoice {
    private final boolean ok;

    public BoolIntChoiceImpl(boolean _ok) {
        this.ok = _ok;
    }

    @Override
    public boolean ok() {
        return ok;
    }
}
