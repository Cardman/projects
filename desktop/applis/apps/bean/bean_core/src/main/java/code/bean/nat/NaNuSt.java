package code.bean.nat;

public abstract class NaNuSt implements NaSt {
    @Override
    public boolean sameReference(NaSt _other) {
        return this == _other;
    }
}
