package aiki.beans.items;
import code.bean.Accessible;
import code.util.NatTreeMap;
import aiki.DataBase;
import aiki.fight.items.Ball;

public class BallBean extends ItemBean {

    @Accessible
    private String catchingRate;

    @Accessible
    private NatTreeMap<String,String> mapVars;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
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
}
