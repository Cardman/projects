package aiki.game.fight;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class StacksOfUses implements Displayable {

    private static final char SEPARATOR=',';

    private static final String FALSE="F";

    private static final String TRUE="T";

    private byte nbRounds;

    private boolean firstStacked;

    private boolean lastStacked;

    public StacksOfUses() {
    }

    public StacksOfUses(String _value) {
        StringList elts_ = StringUtil.splitChars(_value, SEPARATOR);
        nbRounds = (byte) NumberUtil.parseInt(elts_.first());
        firstStacked = StringUtil.quickEq(elts_.get(IndexConstants.SECOND_INDEX), TRUE);
        lastStacked = StringUtil.quickEq(elts_.last(), TRUE);
    }

    
    public static StacksOfUses newStacksOfUses(String _string) {
        return new StacksOfUses(_string);
    }

    public boolean isValid() {
        return nbRounds >= 0;
    }

    public byte getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(byte _nbRounds) {
        nbRounds = _nbRounds;
    }

    public boolean isFirstStacked() {
        return firstStacked;
    }

    public void setFirstStacked(boolean _firstStacked) {
        firstStacked = _firstStacked;
    }

    public boolean isLastStacked() {
        return lastStacked;
    }

    public void setLastStacked(boolean _lastStacked) {
        lastStacked = _lastStacked;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(nbRounds);
        str_.append(SEPARATOR);
        if (firstStacked) {
            str_.append(TRUE);
        } else {
            str_.append(FALSE);
        }
        str_.append(SEPARATOR);
        if (lastStacked) {
            str_.append(TRUE);
        } else {
            str_.append(FALSE);
        }
        return str_.toString();
    }
}
