package code.formathtml.render;

import code.util.core.NumberUtil;
import code.util.ints.CharacterCaseConverter;

public final class SampleCharacterCaseConverter implements CharacterCaseConverter {
    public static final char MIN_LOW = 97;
    public static final char MAX_LOW = MIN_LOW+5;
    public static final char MIN_UPP = 65;
    public static final char MAX_UPP = MIN_UPP+5;
    @Override
    public int index(String _chars, char _current) {
        int prDig_ = NumberUtil.signum(NumberUtil.signum(_current-'0')+1)*NumberUtil.signum(1+NumberUtil.signum('9'-_current)) * (_current-'0');
        int prMin_ = NumberUtil.signum(NumberUtil.signum(_current-MIN_LOW)+1)*NumberUtil.signum(1+NumberUtil.signum(MAX_LOW-_current)) * (10+_current-MIN_LOW);
        int prMaj_ = NumberUtil.signum(NumberUtil.signum(_current-MIN_UPP)+1)*NumberUtil.signum(1+NumberUtil.signum(MAX_UPP-_current)) * (10+_current-MIN_UPP);
        return prDig_ + prMin_ + prMaj_;
    }
}
