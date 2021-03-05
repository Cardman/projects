package code.minirts.rts;

public final class RtsDataBase {

    public static final String EMPTY_STRING = "";

    private final SoldierPattern soldierPattern;

    public RtsDataBase() {
        soldierPattern = new SoldierPattern();
        soldierPattern.setHeight(32);
        soldierPattern.setWidth(32);
    }

    public SoldierPattern getSoldierPattern() {
        return soldierPattern;
    }
}
