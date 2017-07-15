package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class HealingHp extends HealingItem {

    public static final String ITEM = "dbpokemon.fight.items.HealingHp";

    private Rate hp;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (hp.isZero()) {
            throw new DataException();
        }
        if (!hp.isZeroOrGt()) {
            throw new DataException();
        }
    }
    public Rate getHp() {
        return hp;
    }

    public void setHp(Rate _hp) {
        hp = _hp;
    }
}
