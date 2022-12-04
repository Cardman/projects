package code.bean.nat;

public final class NaPa {
    private NaPa() {
    }
    public static NaNbSt convertToNumber(NaSt _naif) {
        return (NaNbSt)_naif;
    }

    public static NaBoSt convertToBoolean(NaSt _naif) {
        return (NaBoSt)_naif;
    }

    public static NaStSt getString(NaSt _naif) {
        return (NaStSt)_naif;
    }
}
