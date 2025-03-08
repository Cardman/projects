package aiki.beans.items;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.items.*;
import code.scripts.pages.aiki.*;
import code.util.NatStringTreeMap;

public final class BallBean extends ItemBean {
    private String catchingRate;
    private NatStringTreeMap<String> mapVars;

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
        formatMessage(MessagesPkBean.IT_BALL,MessagesDataItemsBall.M_P_16_RATE_CATCHING);
        formatMessageDir(catchingRate);
        mapVarsInit(mapVars);
    }
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