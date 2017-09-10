package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.maths.Rate;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class EffectMultSufferedMovePower extends Effect {

    private StringMap<Rate> multMovePowerFctType;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String s: multMovePowerFctType.getKeys()) {
            if (!_data.getTypes().containsObj(s)) {
                throw new DataException();
            }
            if (!multMovePowerFctType.getVal(s).isZeroOrGt()) {
                throw new DataException();
            }
        }
    }

    public StringMap<Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }

    public void setMultMovePowerFctType(StringMap<Rate> _multMovePowerFctType) {
        multMovePowerFctType = _multMovePowerFctType;
    }
}
