package code.maths.litteraladv;


public final class MaBoolStruct implements MaStruct {
    private static final MaBoolStruct FALSE = new MaBoolStruct();
    private static final MaBoolStruct TRUE = new MaBoolStruct();

    public static MaBoolStruct of(boolean _value) {
        return ofBool(_value);
    }

    private static MaBoolStruct ofBool(boolean _value) {
        if (_value) {
            return TRUE;
        }
        return FALSE;
    }
    public MaBoolStruct neg() {
        if (sameBool(this, TRUE)) {
            return FALSE;
        }
        return TRUE;
    }
    @Override
    public boolean sameReference(MaStruct _other) {
        return sameBool(this, _other);
    }

    @Override
    public String displayRsult() {
        if (this == TRUE) {
            return "1";
        }
        return "0";
    }

    public static boolean sameBool(MaStruct _this, MaStruct _other) {
        return _this == _other;
    }

}
