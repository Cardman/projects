package code.util;


public final class CharList {

    private CharList() {
    }

    public static char[] wrapCharArray(char... _chars) {
        return _chars;
    }

    public static boolean containsChar(char[] _chars, char _char) {
        for (char c: _chars) {
            if (c == _char) {
                return true;
            }
        }
        return false;
    }

}
