package code.util.core;

final class EscapeState {
    private int index;
    private char curChar;
    private int newLength;
    EscapeState(int _index, char _curChar, int _newLength) {
        index = _index;
        curChar = _curChar;
        newLength = _newLength;
    }
    void apply(String _input) {
        index++;
        curChar = _input.charAt(index);
        newLength--;
    }

    int getIndex() {
        return index;
    }

    char getCurChar() {
        return curChar;
    }

    int getNewLength() {
        return newLength;
    }
}
