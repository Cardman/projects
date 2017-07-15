package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class Boost extends Item {

    private static final String ITEM = "dbpokemon.fight.items.Boost";

    @CheckedData
    private Rate winPp;
    private StringMap<Short> happiness;
    private EnumMap<Statistic,Short> evs;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!winPp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!evs.isEmpty()) {
            if (!winPp.isZero()) {
                throw new DataException();
            }
        }
        for (EntryCust<Statistic, Short> s: evs.entryList()) {
            if (!s.getKey().isWithBaseStatistic()) {
                throw new DataException();
            }
            if (s.getValue() < 0) {
                throw new DataException();
            }
        }
        for (String k: happiness.getKeys()) {
            if (happiness.getVal(k) < 0) {
                throw new DataException();
            }
            Item obj_ = _data.getItem(k);
            if (!(obj_ instanceof Ball)) {
                throw new DataException();
            }
        }
    }

    public Rate getWinPp() {
        return winPp;
    }
    public void setWinPp(Rate _winPp) {
        winPp = _winPp;
    }
    public StringMap<Short> getHappiness() {
        return happiness;
    }
    public void setHappiness(StringMap<Short> _happiness) {
        happiness = _happiness;
    }
    public EnumMap<Statistic,Short> getEvs() {
        return evs;
    }
    public void setEvs(EnumMap<Statistic,Short> _evs) {
        evs = _evs;
    }

}
