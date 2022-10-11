package aiki.beans.items;
import aiki.db.DataBase;
import aiki.fight.items.Ball;
import code.util.NatStringTreeMap;

public class BallBean extends ItemBean {
    private String catchingRate;
    private NatStringTreeMap<String> mapVars;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        Ball item_ = (Ball) getItem();
        catchingRate = data_.getFormula(item_.getCatchingRate(),getLanguage());
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//        catchingRate = StringList.replace(catchingRate, loc_);
//        catchingRate = catchingRate.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        catchingRate = catchingRate.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        mapVars = data_.getDescriptions(item_.getCatchingRate(),getLanguage());
    }

    public String getCatchingRate() {
        return catchingRate;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }
}