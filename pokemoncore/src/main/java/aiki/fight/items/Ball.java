package aiki.fight.items;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;


@CheckedData
@RwXml
public final class Ball extends Item {

    public static final String ITEM = "aiki.fight.items.Ball";

    private String catchingRate;

    @Override
    public String getItemType() {
        return ITEM;
    }

    public String getCatchingRate() {
        return catchingRate;
    }
    public void setCatchingRate(String _catchingRate) {
        catchingRate = _catchingRate;
    }

}
