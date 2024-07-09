package code.util.core;

public final class IndexConstants {
    public static final byte INDEX_NOT_FOUND_ELT = -1;
    public static final byte FIRST_INDEX = 0;
    public static final byte SECOND_INDEX = 1;
    public static final byte ONE_ELEMENT = 1;
    public static final byte SIZE_EMPTY = 0;

    private IndexConstants(){
    }

    public static boolean isValidIndex(int _index, int _max) {
        if (_index < FIRST_INDEX) {
            return false;
        }
        return _index < _max;
    }
}
