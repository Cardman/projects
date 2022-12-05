package code.bean.nat;

public final class NatArrayStruct extends NaNuSt {

    private final NaSt[] instance;

    public NatArrayStruct(int _len) {
        instance = new NaSt[_len];
    }

    public int getLength() {
        return getInstance().length;
    }
    public NaSt get(int _i) {
        return instance[_i];
    }
    public void set(int _i, NaSt _str) {
        instance[_i]=_str;
    }
    public NaSt[] getInstance() {
        return instance;
    }
}
