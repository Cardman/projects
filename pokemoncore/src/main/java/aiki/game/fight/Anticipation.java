package aiki.game.fight;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.annot.RwXml;
import code.xml.FromAndToString;

@CheckedData
public final class Anticipation {

    private static final char SEPARATOR=',';

    private Rate damage;

    private byte nbRounds;

    private TargetCoords targetPosition;

    private boolean incrementing;

    public Anticipation() {
        damage = Rate.zero();
        targetPosition = new TargetCoords();
    }

    @RwXml
    Anticipation(String _value) {
        StringList elts_ = StringList.splitChars(_value, SEPARATOR);
        damage = new Rate(elts_.first());
        nbRounds = Byte.parseByte(elts_.get(CustList.SECOND_INDEX));
        targetPosition = new TargetCoords(elts_.last());
        incrementing = !Numbers.eq(targetPosition.getPosition(), Fighter.BACK);
    }

    @FromAndToString
    public static Anticipation newAnticipation(String _string) {
        return new Anticipation(_string);
    }

    public boolean isValid() {
        if (!damage.isZeroOrGt()) {
            return false;
        }
        if (nbRounds < 0) {
            return false;
        }
        if (targetPosition == null) {
            return false;
        }
        return true;
    }

    void increment() {
        if (nbRounds < 2) {
            nbRounds++;
        } else {
            nbRounds = 0;
            incrementing = false;
        }
    }

    @FromAndToString
    @Override
    public String toString() {
        return damage.toString()+SEPARATOR+nbRounds+SEPARATOR+targetPosition;
    }

    public Rate getDamage() {
        return damage;
    }

    public void setDamage(Rate _damage) {
        damage = _damage;
    }

    public byte getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(byte _nbRounds) {
        nbRounds = _nbRounds;
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
}
