package aiki.game.fight;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Anticipation implements Displayable{

    private static final char SEPARATOR=',';

    private Rate damage;

    private long nbRounds;

    private TargetCoords targetPosition;

    private boolean incrementing;

    public Anticipation() {
        damage = Rate.zero();
        targetPosition = TargetCoords.def();
    }

    public Anticipation(String _value) {
        StringList elts_ = StringUtil.splitChars(_value, SEPARATOR);
        damage = new Rate(elts_.first());
        nbRounds = NumberUtil.parseLongZero(elts_.get(IndexConstants.SECOND_INDEX));
        targetPosition = new TargetCoords(elts_.last());
        incrementing = !NumberUtil.eq(targetPosition.getPosition(), Fighter.BACK);
    }

    
    public static Anticipation newAnticipation(String _string) {
        return new Anticipation(_string);
    }

    public boolean isValid() {
        if (!damage.isZeroOrGt()) {
            return false;
        }
        return nbRounds >= 0;
    }

    void increment() {
        if (nbRounds < 2) {
            nbRounds++;
        } else {
            nbRounds = 0;
            incrementing = false;
        }
    }

    public Rate getDamage() {
        return damage;
    }

    public void setDamage(Rate _damage) {
        damage = _damage;
    }

    public long getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(long _nbRounds) {
        nbRounds = _nbRounds;
    }

    public boolean isEnabled() {
        return targetPosition.isEnabled();
    }

    public TargetCoords getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(TargetCoords _targetPosition) {
        targetPosition = _targetPosition;
    }

    public boolean isIncrementing() {
        return incrementing;
    }

    public void setIncrementing(boolean _incrementing) {
        incrementing = _incrementing;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(damage.toNumberString());
        str_.append(SEPARATOR).append(nbRounds);
        str_.append(SEPARATOR).append(targetPosition.display());
        return str_.toString();
    }
}
