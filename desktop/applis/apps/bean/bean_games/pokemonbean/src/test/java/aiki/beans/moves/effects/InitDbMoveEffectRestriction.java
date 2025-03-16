package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.util.StringMap;

public abstract class InitDbMoveEffectRestriction extends InitDbMoveEffect {

    public static boolean callEffectRestrictionBeanForbid(EffectRestrictionBean _str, int... _args) {
        return _str.forbid();
    }

    public static boolean callEffectRestrictionBeanForbidLastMove(EffectRestrictionBean _str, int... _args) {
        return _str.forbidLastMove();
    }

    public static boolean callEffectRestrictionBeanForbidStatusMove(EffectRestrictionBean _str, int... _args) {
        return _str.forbidStatusMove();
    }

    public static boolean callEffectRestrictionBeanForbidTargetUsingItemGet(EffectRestrictionBean _str, int... _args) {
        return _str.getForbidTargetUsingItem();
    }

    public static boolean callEffectRestrictionBeanForbidUseMove(EffectRestrictionBean _str, int... _args) {
        return _str.forbidUseMove();
    }

    public static boolean callEffectRestrictionBeanForbidUserMoves(EffectRestrictionBean _str, int... _args) {
        return _str.forbidUserMoves();
    }

    public static boolean callEffectRestrictionBeanForceUseMove(EffectRestrictionBean _str, int... _args) {
        return _str.forceUseMove();
    }
    protected static EffectRestrictionBean dispMoveEffRestriction(boolean _targetAttacksLast, MoveChoiceRestrictionType _res) {
        return dispMoveEffRestriction(feedDbMoveEffDataRestriction(_targetAttacksLast, _res));
    }
    protected static EffectRestrictionBean dispMoveEffRestriction(FacadeGame _fac) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectRestriction();
        return (EffectRestrictionBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectRestriction(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_RESTRICTION,_pk.beanEffectRestrictionBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectRestriction() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFRESTRICTION_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_RESTRICTION);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataRestriction(boolean _targetAttacksLast, MoveChoiceRestrictionType _res) {
        FacadeGame facade_ = facade();
        addEffRestriction(facade_, _targetAttacksLast, _res);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffRestriction(FacadeGame _facade, boolean _targetAttacksLast, MoveChoiceRestrictionType _res) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effRestriction(_targetAttacksLast, _res));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectRestriction effRestriction(boolean _targetAttacksLast, MoveChoiceRestrictionType _res) {
        EffectRestriction cl_ = Instances.newEffectRestriction();
        cl_.setForbidTargetUsingItem(_targetAttacksLast);
        cl_.setChoiceRestriction(_res);
        return cl_;
    }
}