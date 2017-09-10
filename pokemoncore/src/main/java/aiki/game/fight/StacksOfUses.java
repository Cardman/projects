package aiki.game.fight;
import code.serialize.CheckedData;
import code.util.CustList;
import code.util.StringList;
import code.util.annot.RwXml;
import code.xml.FromAndToString;


@CheckedData
public final class StacksOfUses {

    private static final char SEPARATOR=',';

    private static final String FALSE="F";

    private static final String TRUE="T";

    private static final String EMPTY_STRING = "";

    private byte nbRounds;

    private boolean firstStacked;

    private boolean lastStacked;

    public StacksOfUses() {
    }

    @RwXml
    StacksOfUses(String _value) {
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        nbRounds = Byte.parseByte(elts_.first());
        firstStacked = StringList.quickEq(elts_.get(CustList.SECOND_INDEX), TRUE);
        lastStacked = StringList.quickEq(elts_.last(), TRUE);
    }

    @FromAndToString
    public static StacksOfUses newStacksOfUses(String _string) {
        return new StacksOfUses(_string);
    }

    public boolean isValid() {
        if (nbRounds < 0) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        String returnedString_ = EMPTY_STRING + nbRounds + SEPARATOR;
        if (firstStacked) {
            returnedString_ += TRUE;
        } else {
            returnedString_ += FALSE;
        }
        returnedString_ += SEPARATOR;
        if (lastStacked) {
            returnedString_ += TRUE;
        } else {
            returnedString_ += FALSE;
        }
        return returnedString_;
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
}
