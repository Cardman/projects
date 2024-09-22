package code.bean.nat;

import code.util.core.NumberUtil;
import code.util.ints.CharacterCaseConverter;

public final class FixCharacterCaseConverter implements CharacterCaseConverter {
    public static final long MIN_LOW = 97;
    public static final long MAX_LOW = MIN_LOW+5;
    public static final long MIN_UPP = 65;
    public static final long MAX_UPP = MIN_UPP+5;
    public static final long NB0 = '0';
    public static final long NB9 = '9';

    @Override
    public int index(String _chars, char _current) {
        long prDig_ = NumberUtil.signum(NumberUtil.signum(_current- NB0)+1L)*NumberUtil.signum(1L+NumberUtil.signum(NB9 -_current)) * (_current- NB0);
        long prMin_ = NumberUtil.signum(NumberUtil.signum(_current-MIN_LOW)+1L)*NumberUtil.signum(1L+NumberUtil.signum(MAX_LOW-_current)) * (10+_current-MIN_LOW);
        long prMaj_ = NumberUtil.signum(NumberUtil.signum(_current-MIN_UPP)+1L)*NumberUtil.signum(1L+NumberUtil.signum(MAX_UPP-_current)) * (10+_current-MIN_UPP);
        return (int) (prDig_ + prMin_ + prMaj_);
    }
}
