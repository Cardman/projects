package code.maths.litteraladv;

public final class MaNullStruct implements MaStruct {
    public static final MaNullStruct NULL_VALUE = new MaNullStruct();
    private MaNullStruct() {
    }
    public static MaStruct def(MaStruct _str) {
        if (_str == null) {
            return NULL_VALUE;
        }
        return _str;
    }
    @Override
    public boolean sameReference(MaStruct _other) {
        return this == _other;
    }

    @Override
    public String displayRsult() {
        return "";
    }
}
