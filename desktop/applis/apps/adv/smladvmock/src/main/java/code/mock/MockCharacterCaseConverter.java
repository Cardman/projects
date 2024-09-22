package code.mock;

import code.util.core.NumberUtil;
import code.util.ints.CharacterCaseConverter;

public final class MockCharacterCaseConverter implements CharacterCaseConverter {
    public static final char MIN_LOW = 97;
    public static final char MAX_LOW = MIN_LOW+5;
    public static final char MIN_UPP = 65;
    public static final char MAX_UPP = MIN_UPP+5;
    @Override
    public int index(String _chars, char _current) {
        if (NumberUtil.isDigit(_current)) {
            return _current-'0';
        }
        if (isMajHex(_current)) {
            return _current-MIN_UPP+10;
        }
        if (isMinHex(_current)) {
            return _current-MIN_LOW+10;
        }
        return -1;
    }

    public static boolean isMajHex(int _ch) {
        return _ch >= MIN_UPP && _ch <= MAX_UPP;
    }

    public static boolean isMinHex(int _ch) {
        return _ch >= MIN_LOW && _ch <= MAX_LOW;
    }
}
