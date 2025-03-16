package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.util.*;

public abstract class InitDbMoveEffectCopy extends InitDbMoveEffect {

    public static long callEffectCopyFighterBeanPpForMovesGet(EffectCopyFighterBean _str, int... _args) {
        return _str.getPpForMoves();
    }

    public static String callEffectCopyMoveBeanClickDefaultMove(EffectCopyMoveBean _str, int... _args) {
        return _str.clickDefaultMove();
    }

    public static String callEffectCopyMoveBeanClickDefaultMoveId(EffectCopyMoveBean _str, int... _args) {
        callEffectCopyMoveBeanClickDefaultMove(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMove(EffectCopyMoveBean _str, int... _args) {
        return _str.clickMove(_args[0]);
    }

    public static String callEffectCopyMoveBeanClickMoveId(EffectCopyMoveBean _str, int... _args) {
        callEffectCopyMoveBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }
    public static String callEffectCopyMoveBeanClickMoveTransId(EffectCopyMoveBean _str, int... _args) {
        callEffectCopyMoveBeanClickMoveTrans(_str, _args);
        return getValMoveId(_str);
    }

    public static String callEffectCopyMoveBeanClickMoveTrans(EffectCopyMoveBean _str, int... _args) {
        return _str.clickMoveTrans(_args[0]);
    }

    public static boolean callEffectCopyMoveBeanCopyMoveForUser(EffectCopyMoveBean _str, int... _args) {
        return _str.copyMoveForUser();
    }

    public static boolean callEffectCopyMoveBeanCopyingMoveForUserDefGet(EffectCopyMoveBean _str, int... _args) {
        return _str.getCopyingMoveForUserDef();
    }

    public static long callEffectCopyMoveBeanCopyingMoveForUserGet(EffectCopyMoveBean _str, int... _args) {
        return _str.getCopyingMoveForUser();
    }

    public static String callEffectCopyMoveBeanDisplayNameGet(EffectCopyMoveBean _str, int... _args) {
        return _str.getDisplayName();
    }

    public static String callEffectCopyMoveBeanGetTrDefaultMove(EffectCopyMoveBean _str, int... _args) {
        return _str.getTrDefaultMove();
    }

    public static String callEffectCopyMoveBeanGetTrMove(EffectCopyMoveBean _str, int... _args) {
        return _str.getTrMove(_args[0]);
    }

    public static String callEffectCopyMoveBeanGetTrMoveTrans(EffectCopyMoveBean _str, int... _args) {
        return _str.getTrMoveTrans(_args[0]);
    }

    public static CustList<TranslatedKey> callEffectCopyMoveBeanMovesNotToBeCopiedGet(EffectCopyMoveBean _str, int... _args) {
        return _str.getMovesNotToBeCopied();
    }

    public static CustList<TranslatedKey> callEffectCopyMoveBeanMovesTransformingGet(EffectCopyMoveBean _str, int... _args) {
        return _str.getMovesTransforming();
    }
    protected static EffectCopyMoveBean dispMoveEffCopyMove(boolean _copyingMoveForUserDef, int _copy) {
        FacadeGame pk_ = pkDataByFacade(feedDbMoveEffDataDam(_copyingMoveForUserDef, _copy));
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return (EffectCopyMoveBean)transitEffect(0,0,pk_,all_);
    }
    protected static EffectCopyMoveBean dispMoveEffCopyMoveNoFighter(boolean _copyingMoveForUserDef, int _copy) {
        FacadeGame pk_ = pkDataByFacade(feedDbMoveEffDataDamNoFighter(_copyingMoveForUserDef, _copy));
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyMove();
        return (EffectCopyMoveBean)transitEffect(0,0,pk_,all_);
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
    protected static EffectCopyFighterBean dispMoveEffCopyFighter() {
        FacadeGame pk_ = pkDataByFacade(feedDbMoveEffDataDam());
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectCopyFighter();
        return (EffectCopyFighterBean)transitEffect(0,0,pk_,all_);
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