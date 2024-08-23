package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.util.core.*;
import code.util.ints.CharacterCaseConverter;

public final class DefCharacterCaseConverter implements CharacterCaseConverter {
    @Override
    public int index(String _chars, char _current) {
        boolean ok_ = StringExpUtil.isDigit(_current);
        if (ok_) {
            return _current;
        }
        int min_ = NumParsers.toMinCaseLetter(_current);
        int ind_ = _chars.indexOf(min_);
        if (ind_ >= 0) {
            return (char) (ind_ + NumberUtil.MIN_UPP);
        }
        return -1;
    }
}
