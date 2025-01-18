package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;

public abstract class InitDbMoveEffectCopy extends InitDbMoveEffect {

    public static NaSt callEffectCopyFighterBeanPpForMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyFighterBeanPpForMovesGet(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickDefaultMove(NaSt _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickDefaultMove(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickDefaultMoveId(NaSt _str, long... _args) {
        callEffectCopyMoveBeanClickDefaultMove(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMove(NaSt _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickMove(),_str,_args);
    }

    public static String callEffectCopyMoveBeanClickMoveId(NaSt _str, long... _args) {
        callEffectCopyMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCopyMoveBeanClickMoveTransId(NaSt _str, long... _args) {
        callEffectCopyMoveBeanClickMoveTrans(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMoveTrans(NaSt _str, long... _args) {
        return navigateData(new EffectCopyMoveBeanClickMoveTrans(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanCopyMoveForUser(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyMoveForUser(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanCopyingMoveForUserDefGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyingMoveForUserDefGet(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanCopyingMoveForUserGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanCopyingMoveForUserGet(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanDisplayNameGet(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanGetTrDefaultMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrDefaultMove(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanGetTrMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrMove(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanGetTrMoveTrans(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanGetTrMoveTrans(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanMovesNotToBeCopiedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanMovesNotToBeCopiedGet(),_str,_args);
    }

    public static NaSt callEffectCopyMoveBeanMovesTransformingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectCopyMoveBeanMovesTransformingGet(),_str,_args);
    }
    protected static NaSt dispMoveEffCopyMove(boolean _copyingMoveForUserDef, int _copy) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_copyingMoveForUserDef, _copy));
        StringMap<NaSt> all_ = beanToEffectCopyMove(pk_);
        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectCopyMove(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE,_pk.beanEffectCopyMoveBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCopyMove() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYMOVE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE);
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
        e_.setCopyingMoveForUser( _copy);
        e_.getMovesNotToBeCopied().add(M_WEA);
        return e_;
    }
    protected static NaSt dispMoveEffCopyFighter() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam());
        StringMap<NaSt> all_ = beanToEffectCopyFighter(pk_);
        StringMap<String> mapping_ = mappingToEffectCopyFighter();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectCopyFighter(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_FIGHTER,_pk.beanEffectCopyFighterBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectCopyFighter() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFCOPYFIGHTER_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_FIGHTER);
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
        e_.setPpForMoves( 1);
        return e_;
    }
}
