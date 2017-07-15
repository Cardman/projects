package aiki.fight.moves.effects;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;

@RwXml
public class EffectCommonStatistics extends Effect {

    private EnumMap<Statistic,String> commonValue;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (commonValue.contains(Statistic.HP)) {
            throw new DataException();
        }
        EnumList<Statistic> keys_ = commonValue.getKeys();
        keys_.removeObj(Statistic.PV_RESTANTS);
        if (!Statistic.getStatisticsWithBase().containsAllObj(keys_)) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
        }
    }

    public EnumMap<Statistic,String> getCommonValue() {
        return commonValue;
    }

    public void setCommonValue(EnumMap<Statistic,String> _commonValue) {
        commonValue = _commonValue;
    }


}
