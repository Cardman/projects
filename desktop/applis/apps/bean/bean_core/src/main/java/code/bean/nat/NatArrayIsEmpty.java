package code.bean.nat;

public final class NatArrayIsEmpty implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return NaBoSt.of(((NatArrayStruct)_instance).getLength()==0);
    }
}
