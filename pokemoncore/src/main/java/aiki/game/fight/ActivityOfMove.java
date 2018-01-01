package aiki.game.fight;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Displayable;


@CheckedData
public final class ActivityOfMove implements Displayable {

    private static final char SEPARATOR=';';
    private static final String FALSE = "F";
    private static final String TRUE = "T";

    private short nbTurn;

    private boolean enabled;

    private final boolean incrementCount;

    public ActivityOfMove() {
        incrementCount = true;
    }

    public ActivityOfMove(boolean _increment) {
        incrementCount = _increment;
    }

    public ActivityOfMove(ActivityOfMove _activity) {
        nbTurn = _activity.nbTurn;
        enabled = _activity.enabled;
        incrementCount = _activity.incrementCount;
    }

    public ActivityOfMove(String _value) {
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        nbTurn = Short.parseShort(elts_.first());
        enabled = StringList.quickEq(elts_.get(CustList.SECOND_INDEX), TRUE);
        incrementCount = StringList.quickEq(elts_.last(), TRUE);
    }

    @FromAndToString
    public static ActivityOfMove newActivityOfMove(String _string) {
        return new ActivityOfMove(_string);
    }

    public void enableReset() {
        enable();
        reset();
    }

    public void keepEnabled(boolean _enabled) {
        if (_enabled) {
            increment();
        } else {
            disable();
            reset();
        }
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void reset() {
        nbTurn = 0;
    }

    public void increment() {
        if (incrementCount) {
            nbTurn++;
        }
    }

    @Override
    public String toString() {
        return display();
    }

    public short getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(short _nbTurn) {
        nbTurn = _nbTurn;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    public boolean isIncrementCount() {
        return incrementCount;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(nbTurn);
        str_.append(SEPARATOR);
        if (enabled) {
            str_.append(TRUE);
        } else {
            str_.append(FALSE);
        }
        str_.append(SEPARATOR);
        if (incrementCount) {
            str_.append(TRUE);
        } else {
            str_.append(FALSE);
        }
        return str_.toString();
    }
}
