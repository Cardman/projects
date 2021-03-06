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
    public static boolean isTrue(MaStruct _value) {
        return sameBool(TRUE, _value);
    }
    @Override
    public boolean sameReference(MaStruct _other) {
        return sameBool(this, _other);
    }

    public static boolean sameBool(MaStruct _this, MaStruct _other) {
        return _this == _other;
    }

}
