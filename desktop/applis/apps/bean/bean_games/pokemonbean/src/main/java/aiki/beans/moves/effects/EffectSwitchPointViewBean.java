package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import code.scripts.pages.aiki.MessagesDataEffswitchpointview;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectSwitchPointViewBean extends EffectBean {

    private PointViewChangementType pointViewChangement;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchPointView effect_ = (EffectSwitchPointView) getEffect();
        pointViewChangement = effect_.getPointViewChangement();
    }

    @Override
    public void buildSubEff() {
        procPointViewChangementType(getPointViewChangement(),PointViewChangementType.THIEF_BONUSES, MessagesDataEffswitchpointview.M_P_63_THIEF,getMove());
        procPointViewChangementType(getPointViewChangement(),PointViewChangementType.MIRROR_AGAINST_THROWER,MessagesDataEffswitchpointview.M_P_63_MIRROR,getMove());
        procPointViewChangementType(getPointViewChangement(),PointViewChangementType.ATTRACT_DAMAGES_MOVES,MessagesDataEffswitchpointview.M_P_63_ATTRACT);
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_SWITCHPOINTVIEW,MessagesDataEffswitchpointview.M_P_63_EFFECT);
    }

    public boolean thieveBonus() {
        return pointViewChangement == PointViewChangementType.THIEF_BONUSES;
    }
    public boolean attractDamageMoves() {
        return pointViewChangement == PointViewChangementType.ATTRACT_DAMAGES_MOVES;
    }
    public boolean mirrorAgainstUser() {
        return pointViewChangement == PointViewChangementType.MIRROR_AGAINST_THROWER;
    }

    public PointViewChangementType getPointViewChangement() {
        return pointViewChangement;
    }
}