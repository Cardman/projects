package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.util.StringMap;

public abstract class InitDbMoveEffectCopy extends InitDbMoveEffect {

    public static NaSt callEffectCopyFighterBeanPpForMovesGet(NaSt _str, int... _args) {
        return new NaNbSt(( (EffectCopyFighterBean) ((PokemonBeanStruct)_str).getInstance()).getPpForMoves());
    }

    public static String callEffectCopyMoveBeanClickDefaultMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).clickDefaultMove()).getInstance();
    }

    public static String callEffectCopyMoveBeanClickDefaultMoveId(NaSt _str, int... _args) {
        callEffectCopyMoveBeanClickDefaultMove(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).clickMove(_args[0])).getInstance();
    }

    public static String callEffectCopyMoveBeanClickMoveId(NaSt _str, int... _args) {
        callEffectCopyMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCopyMoveBeanClickMoveTransId(NaSt _str, int... _args) {
        callEffectCopyMoveBeanClickMoveTrans(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMoveTrans(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).clickMoveTrans(_args[0])).getInstance();
    }

    public static NaSt callEffectCopyMoveBeanCopyMoveForUser(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).copyMoveForUser());
    }

    public static NaSt callEffectCopyMoveBeanCopyingMoveForUserDefGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getCopyingMoveForUserDef());
    }

    public static NaSt callEffectCopyMoveBeanCopyingMoveForUserGet(NaSt _str, int... _args) {
        return new NaNbSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getCopyingMoveForUser());
    }

    public static NaSt callEffectCopyMoveBeanDisplayNameGet(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getDisplayName());
    }

    public static NaSt callEffectCopyMoveBeanGetTrDefaultMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrDefaultMove());
    }

    public static NaSt callEffectCopyMoveBeanGetTrMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrMove(_args[0]));
    }

    public static NaSt callEffectCopyMoveBeanGetTrMoveTrans(NaSt _str, int... _args) {
        return new NaStSt(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getTrMoveTrans(_args[0]));
    }

    public static NaSt callEffectCopyMoveBeanMovesNotToBeCopiedGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesNotToBeCopied());
    }

    public static NaSt callEffectCopyMoveBeanMovesTransformingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectCopyMoveBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTransforming());
    }
    protected static NaSt dispMoveEffCopyMove(boolean _copyingMoveForUserDef, int _copy) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_copyingMoveForUserDef, _copy));
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return transitEffect(0,0,pk_,all_);
    }
    protected static NaSt dispMoveEffCopyMoveNoFighter(boolean _copyingMoveForUserDef, int _copy) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDamNoFighter(_copyingMoveForUserDef, _copy));
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectCopyMove(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_COPY_MOVE,_pk.beanEffectCopyMoveBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectCopyMove() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE);
//        return map_;
//    }
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
    protected static FacadeGame feedDbMoveEffDataDamNoFighter(boolean _copyingMoveForUserDef, int _copy) {
        FacadeGame facade_ = facade();
        addEff(effectCopyMove(_copyingMoveForUserDef, _copy), facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
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
        e_.setCopyingMoveForUser( _copy);
        e_.getMovesNotToBeCopied().add(M_WEA);
        return e_;
    }
    protected static NaSt dispMoveEffCopyFighter() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam());
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyFighter();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectCopyFighter(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_COPY_FIGHTER,_pk.beanEffectCopyFighterBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectCopyFighter() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_FIGHTER);
//        return map_;
//    }
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
        e_.setPpForMoves( 1);
        return e_;
    }
}