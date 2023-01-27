package code.mock;

import code.util.core.NumberUtil;
import code.util.ints.CharacterCaseConverter;

public final class MockCharacterCaseConverter implements CharacterCaseConverter {
    @Override
    public int index(String _chars, char _current) {
        if (!NumberUtil.isDigit(_current)&&!NumberUtil.isMajHex(_current)&&!NumberUtil.isMinHex(_current)) {
            return -1;
        }
        return _current;
    }
}
