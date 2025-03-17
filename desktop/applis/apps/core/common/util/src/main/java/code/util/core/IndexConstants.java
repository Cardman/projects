package code.util.core;

public final class IndexConstants {
    public static final int INDEX_NOT_FOUND_ELT = -1;
    public static final long FIRST_INDEX_LG = 0;
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int ONE_ELEMENT = 1;
    public static final int SIZE_EMPTY = 0;

    private IndexConstants(){
    }

    public static boolean isValidIndex(int _index, int _max) {
        if (_index < FIRST_INDEX) {
            return false;
        }
        return _index < _max;
    }
}
