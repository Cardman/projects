package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbMoveEffectCopy extends InitDbMoveEffect {

    public static Struct callEffectCopyFighterBeanPpForMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyFighterBeanPpForMovesGet(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickDefaultMove(Struct _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickDefaultMove(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickDefaultMoveId(Struct _str, long... _args) {
        callEffectCopyMoveBeanClickDefaultMove(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMove(Struct _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickMove(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickMoveId(Struct _str, long... _args) {
        callEffectCopyMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCopyMoveBeanClickMoveTransId(Struct _str, long... _args) {
        callEffectCopyMoveBeanClickMoveTrans(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMoveTrans(Struct _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickMoveTrans(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanCopyMoveForUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyMoveForUser(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanCopyingMoveForUserDefGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyingMoveForUserDefGet(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanCopyingMoveForUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyingMoveForUserGet(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanDisplayNameGet(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanGetTrDefaultMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrDefaultMove(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanGetTrMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrMove(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanGetTrMoveTrans(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrMoveTrans(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanMovesNotToBeCopiedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanMovesNotToBeCopiedGet(),_str,_args);
    }

    public static Struct callEffectCopyMoveBeanMovesTransformingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanMovesTransformingGet(),_str,_args);
    }
    protected static Struct dispMoveEffCopyMove(boolean _copyingMoveForUserDef, int _copy) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_copyingMoveForUserDef, _copy));
        StringMap<Struct> all_ = beanToEffectCopyMove(pk_);
        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectCopyMove(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE,_pk.beanEffectCopyMoveBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCopyMove() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataDam(boolean _copyingMoveForUserDef, int _copy) {
        FacadeGame facade_ = facade();
        addEff(effectCopyMove(_copyingMoveForUserDef, _copy), facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        chg_.getEffects().add(Instances.newEffectCopyFighter());
        facade_.getData().completeMembers(M_STA,chg_);
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().setDefMove(M_DAM_VERY_BAD);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void addEff(EffectCopyMove _eff, FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectCopyMove effectCopyMove(boolean _copyingMoveForUserDef, int _copy) {
        EffectCopyMove e_ = Instances.newEffectCopyMove();
        e_.setCopyingMoveForUserDef(_copyingMoveForUserDef);
        e_.setCopyingMoveForUser((short) _copy);
        e_.getMovesNotToBeCopied().add(M_WEA);
        return e_;
    }
    protected static Struct dispMoveEffCopyFighter() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam());
        StringMap<Struct> all_ = beanToEffectCopyFighter(pk_);
        StringMap<String> mapping_ = mappingToEffectCopyFighter();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectCopyFighter(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_FIGHTER,_pk.beanEffectCopyFighterBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCopyFighter() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_FIGHTER);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataDam() {
        FacadeGame facade_ = facade();
        addEff(effectCopyFighter(), facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        chg_.getEffects().add(Instances.newEffectCopyFighter());
        facade_.getData().completeMembers(M_STA,chg_);
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().setDefMove(M_DAM_VERY_BAD);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void addEff(EffectCopyFighter _eff, FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectCopyFighter effectCopyFighter() {
        EffectCopyFighter e_ = Instances.newEffectCopyFighter();
        e_.setPpForMoves((short) 1);
        return e_;
    }
}
