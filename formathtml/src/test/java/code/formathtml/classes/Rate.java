package code.formathtml.classes;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Displayable;

public final class Rate implements Displayable {

    private static final char ZERO_CHAR = '0';

    private static final char SEPARATOR = '/';

    private static final String ZERO = "0";

    private static final char MINUS = '-';
    private static final char DOT = '.';

    private final boolean zero;

    private final String string;

    public Rate(String _value) {
        string = _value;
        zero = StringList.quickEq(_value,ZERO);
    }

    public static boolean matchesRate(String _input) {
        if (_input.isEmpty()) {
            return false;
        }
        int i_ = CustList.FIRST_INDEX;
        if (_input.charAt(i_) == MINUS) {
            i_++;
        }
        if (i_ >= _input.length()) {
            return false;
        }
        if (_input.charAt(i_) == DOT) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        if (!Character.isDigit(_input.charAt(i_))) {
            return false;
        }
        while (true) {
            if (i_ >= _input.length()) {
                return true;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                if (_input.charAt(i_) == SEPARATOR) {
                    break;
                }
                if (_input.charAt(i_) == DOT) {
                    break;
                }
                return false;
            }
            i_++;
        }
        if (_input.charAt(i_) == DOT) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        if (i_ + 1 >= _input.length()) {
            return false;
        }
        i_++;
        if (_input.charAt(i_) == MINUS) {
            i_++;
        }
        while (true) {
            if (i_ >= _input.length()) {
                return false;
            }
            if (_input.charAt(i_) != ZERO_CHAR) {
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _input.length()) {
                break;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    public boolean eq(Rate _r) {
        return StringList.quickEq(string, _r.string);
    }

    public boolean isZero() {
        return zero;
    }

    @Override
    
    public String display() {
        return string;
    }
}
