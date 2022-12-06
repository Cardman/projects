package code.bean.nat;

public final class NatStringIsEmpty implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return NaBoSt.of(NaPa.getString(_args[0]).getInstance().isEmpty());
    }
}
