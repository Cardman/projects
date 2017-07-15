package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;


@CheckedData
@RwXml
public class EffectOrder extends Effect {

    private boolean targetAttacksLast;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
//        if (getTargetChoice() == TargetChoice.LANCEUR) {
//            throw new DataException();
//        }
        if (!getTargetChoice().isWithChoice()) {
            throw new DataException();
        }
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }
    public void setTargetAttacksLast(boolean _targetAttacksLast) {
        targetAttacksLast = _targetAttacksLast;
    }
}
