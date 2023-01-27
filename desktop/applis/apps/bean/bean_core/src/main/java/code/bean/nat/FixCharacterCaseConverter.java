package code.bean.nat;

import code.util.ints.CharacterCaseConverter;

public final class FixCharacterCaseConverter implements CharacterCaseConverter {
    @Override
    public int index(String _chars, char _current) {
        return _current;
    }
}
