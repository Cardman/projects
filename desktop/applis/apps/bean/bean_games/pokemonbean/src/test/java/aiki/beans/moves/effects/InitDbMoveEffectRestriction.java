package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbMoveEffectRestriction extends InitDbMoveEffect {

    public static Struct callEffectRestrictionBeanForbid(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbid(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidLastMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidLastMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidStatusMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidStatusMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidTargetUsingItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidTargetUsingItemGet(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidUseMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidUseMove(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForbidUserMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForbidUserMoves(),_str,_args);
    }

    public static Struct callEffectRestrictionBeanForceUseMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectRestrictionBeanForceUseMove(),_str,_args);
    }
    protected static Struct dispMoveEffRestriction(boolean _targetAttacksLast, MoveChoiceRestrictionType _res) {
        return dispMoveEffRestriction(feedDbMoveEffDataRestriction(_targetAttacksLast, _res));
    }
    protected static Struct dispMoveEffRestriction(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectRestriction(pk_);
        StringMap<String> mapping_ = mappingToEffectRestriction();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectRestriction(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_RESTRICTION,_pk.beanEffectRestrictionBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectRestriction() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFRESTRICTION_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_RESTRICTION);
        return map_;
    }
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
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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
