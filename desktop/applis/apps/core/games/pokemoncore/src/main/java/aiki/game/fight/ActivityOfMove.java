package aiki.game.fight;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class ActivityOfMove implements Displayable {

    private static final char SEPARATOR=';';
    private static final String FALSE = "0";
    private static final String TRUE = "1";

    private long nbTurn;

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
        StringList elts_ = StringUtil.splitChars(_value, SEPARATOR);
        nbTurn = NumberUtil.parseLongZero(elts_.first());
        enabled = elts_.size() > 1 && StringUtil.quickEq(elts_.get(IndexConstants.SECOND_INDEX), TRUE);
        incrementCount = StringUtil.quickEq(elts_.last(), TRUE);
    }

    
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

    public long getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(long _nbTurn) {
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
