package code.minirts.rts;

public final class DataBase {

    public static final String EMPTY_STRING = "";

    private SoldierPattern soldierPattern;

    public DataBase() {
        soldierPattern = new SoldierPattern();
        soldierPattern.setHeight(32);
        soldierPattern.setWidth(32);
    }

    public SoldierPattern getSoldierPattern() {
        return soldierPattern;
    }
}
