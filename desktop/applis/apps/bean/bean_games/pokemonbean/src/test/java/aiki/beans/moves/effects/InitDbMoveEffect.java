package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.beans.moves.InitDbMove;
import aiki.beans.moves.MoveBean;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.util.*;

public abstract class InitDbMoveEffect extends InitDbMove {

    public static String callEffectBeanEffectBeanGet(NaSt _str, int... _args) {
        return EffectBeanTest.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFF_HTML;
    }

    public static long callEffectBeanIndexGet(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).getIndex();
    }

    public static boolean callEffectBeanIsAdjAdv(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAdjAdv();
    }

    public static boolean callEffectBeanIsAdjMult(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAdjMult();
    }

    public static boolean callEffectBeanIsAdjUniq(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAdjUniq();
    }

    public static boolean callEffectBeanIsAllie(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAllie();
    }

    public static boolean callEffectBeanIsAllies(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAllies();
    }

    public static boolean callEffectBeanIsAnyFoe(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAnyFoe();
    }

    public static boolean callEffectBeanIsAutreUniq(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isAutreUniq();
    }

    public static boolean callEffectBeanIsGlobale(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isGlobale();
    }

    public static boolean callEffectBeanIsLanceur(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isLanceur();
    }

    public static boolean callEffectBeanIsPseudoGlobale(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isPseudoGlobale();
    }

    public static boolean callEffectBeanIsTousAdv(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isTousAdv();
    }

    public static boolean callEffectBeanIsUniqueImporte(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).isUniqueImporte();
    }

    public static AbsMap<String,String> callEffectBeanMapVarsFailGet(NaSt _str, int... _args) {
        return (( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).getMapVarsFail());
    }

    public static String callEffectBeanMoveGet(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).getMove();
    }

    public static boolean callEffectBeanNeedSuccessFirstEffectGet(NaSt _str, int... _args) {
        return ( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).getNeedSuccessFirstEffect();
    }

    public static CustList<String> callEffectBeanReasonsGet(NaSt _str, int... _args) {
        return (( (EffectBean) ((PokemonBeanStruct)_str).getInstance()).getReasons());
    }

    //    public static NaSt callEffectBeanMoveSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new EffectBeanMoveSet(),_str,_args);
//    }
//
//    public static NaSt callEffectBeanIndexSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new EffectBeanIndexSet(),_str,_args);
//    }
    protected static NaSt dispMoveEffDamage(FacadeGame _fac, int _index) {
        return dispMoveEffDamage(_fac, _index,0);
    }
    protected static NaSt dispMoveEffDamage(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectDamage();
        return transitEffect(_index, _indexEff, pk_, all_);
    }

    protected static NaSt transitEffect(int _index, int _indexEff, PkData _pk, StringMap<NaSt> _all) {
        NaSt mbean_ = transitMove(_index, _pk, _all);
//        int noEff_ = toInt(elt(callMoveBeanEffectsGet(mbean_), _indexEff));
//        NaSt eff_ = new PokemonBeanStruct(new EffectBean());
//        callMoveBeanGetPage(mbean_, noEff_);
//        NaSt eff_ = byStr(_all, _mapping, callMoveBeanGetPage(mbean_, noEff_));
//        fwdEffect(_pk,eff_,mbean_, noEff_);
//        beforeDisplaying(eff_);
        return new PokemonBeanStruct(((MoveBean)((PokemonBeanStruct)mbean_).getBean()).getBeans().get(_indexEff));
    }

    protected static NaSt transitEffectQuick(int _index, int _indexEff, PkData _pk, StringMap<NaSt> _all) {
        NaSt mbean_ = transitMoveQuick(_index, _pk, _all);
//        int noEff_ = toInt(elt(callMoveBeanEffectsGet(mbean_), _indexEff));
//        NaSt eff_ = new PokemonBeanStruct(new EffectBean());
//        callMoveBeanGetPage(mbean_, noEff_);
//        NaSt eff_ = byStr(_all, _mapping, callMoveBeanGetPage(mbean_, noEff_));
//        fwdEffect(_pk,eff_,mbean_, noEff_);
//        beforeDisplaying(eff_);
        return new PokemonBeanStruct(((MoveBean)((PokemonBeanStruct)mbean_).getBean()).getBeans().get(_indexEff));
    }

//    public static void fwdEffect(PkData _pk, NaSt _update, NaSt _use, int _index) {
//        setFormsBy(_pk,_update,_use);
//        callEffectBeanIndexSet(_update,_index);
//        callEffectBeanMoveSet(_update,toStr(callMoveBeanNameGet(_use)));
//    }
//    public static StringMap<NaSt> beanToEffectDamage(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_DAMAGE,_pk.beanEffectDamageBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectDamage() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE);
//        return map_;
//    }
//    public static StringMap<NaSt> beanToEffect(PkData _pk) {
//        StringMap<NaSt> map_ = beanToMove(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT,_pk.beanEffectBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffect() {
//        StringMap<String> map_ = mappingToMove();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFF_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT);
//        return map_;
//    }

    protected static FacadeGame feedDbMoveEffDamComp(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power, String _fail) {
        FacadeGame f_ = feedDbMoveEffDam(_targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, _s, _k, _dir, _power, _fail);
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
    protected static FacadeGame feedDbMoveEffDam(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power, String _fail) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, _targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, M_STA, M_WEA, 1, 1);
        feed(dam_, _s, _k, _dir);
        EffectDamage ef_ = Instances.newEffectDamage();
        ef_.setPower(_power);
        ef_.setFail(_fail);
        ef_.getRequiredSuccessfulEffects().add(0);
        target(dam_, ef_);
        facade_.getData().completeMembers(M_DAM, dam_);
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
}