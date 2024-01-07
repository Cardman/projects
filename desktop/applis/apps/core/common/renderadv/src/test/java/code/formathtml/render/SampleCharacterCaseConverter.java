package code.formathtml.render;

import code.util.ints.CharacterCaseConverter;

public final class SampleCharacterCaseConverter implements CharacterCaseConverter {
    @Override
    public int index(String _chars, char _current) {
        return _current;
    }
}
