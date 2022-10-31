package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.moves.InitDbMove;
import aiki.beans.moves.MoveLineBeanClickMove;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbMoveEffect extends InitDbMove {

    public static Struct callEffectBeanEffectBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanEffectBeanGet(),_str,_args);
    }

    public static Struct callEffectBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIndexGet(),_str,_args);
    }

    public static Struct callEffectBeanIsAdjAdv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAdjAdv(),_str,_args);
    }

    public static Struct callEffectBeanIsAdjMult(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAdjMult(),_str,_args);
    }

    public static Struct callEffectBeanIsAdjUniq(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAdjUniq(),_str,_args);
    }

    public static Struct callEffectBeanIsAllie(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAllie(),_str,_args);
    }

    public static Struct callEffectBeanIsAllies(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAllies(),_str,_args);
    }

    public static Struct callEffectBeanIsAnyFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAnyFoe(),_str,_args);
    }

    public static Struct callEffectBeanIsAutreUniq(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsAutreUniq(),_str,_args);
    }

    public static Struct callEffectBeanIsGlobale(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsGlobale(),_str,_args);
    }

    public static Struct callEffectBeanIsLanceur(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsLanceur(),_str,_args);
    }

    public static Struct callEffectBeanIsPseudoGlobale(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsPseudoGlobale(),_str,_args);
    }

    public static Struct callEffectBeanIsTousAdv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsTousAdv(),_str,_args);
    }

    public static Struct callEffectBeanIsUniqueImporte(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanIsUniqueImporte(),_str,_args);
    }

    public static Struct callEffectBeanMapVarsFailGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanMapVarsFailGet(),_str,_args);
    }

    public static Struct callEffectBeanMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanMoveGet(),_str,_args);
    }

    public static Struct callEffectBeanNeedSuccessFirstEffectGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanNeedSuccessFirstEffectGet(),_str,_args);
    }

    public static Struct callEffectBeanReasonsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectBeanReasonsGet(),_str,_args);
    }

    public static Struct callEffectBeanMoveSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EffectBeanMoveSet(),_str,_args);
    }

    public static Struct callEffectBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EffectBeanIndexSet(),_str,_args);
    }
    protected static Struct dispMoveEff(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffect(pk_);
        transitToAllMoves(pk_, all_);
        StringMap<String> mapping_ = mappingToEffect();
        Struct moveline_ = displayMoveLine(all_, _index, mapping_);
        Struct mbean_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVE);
        transit(pk_,new MoveLineBeanClickMove(), moveline_, mbean_,toInt(callMoveLineBeanIndexGet(moveline_)));
        int noEff_ = toInt(elt(callMoveBeanEffectsGet(mbean_), _indexEff));
        fwdEffect(byStr(all_,mapping_,callMoveBeanGetPage(mbean_,noEff_)),mbean_, noEff_);
        return mbean_;
    }
    protected static Struct dispMoveEffDamage(FacadeGame _fac, int _index) {
        return dispMoveEffDamage(_fac, _index,0);
    }
    protected static Struct dispMoveEffDamage(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectDamage(pk_);
        transitToAllMoves(pk_, all_);
        StringMap<String> mapping_ = mappingToEffectDamage();
        Struct moveline_ = displayMoveLine(all_, _index, mapping_);
        Struct mbean_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVE);
        transit(pk_,new MoveLineBeanClickMove(), moveline_, mbean_,toInt(callMoveLineBeanIndexGet(moveline_)));
        int noEff_ = toInt(elt(callMoveBeanEffectsGet(mbean_), _indexEff));
        Struct eff_ = byStr(all_, mapping_, callMoveBeanGetPage(mbean_, noEff_));
        fwdEffect(eff_,mbean_, noEff_);
        beforeDisplaying(eff_);
//        fwdEffect(,mbean_,toInt(elt(callMoveBeanEffectsGet(mbean_),_indexEff)));
        return eff_;
    }
    public static void fwdEffect(Struct _update, Struct _use, int _index) {
        callEffectBeanIndexSet(_update,_index);
        callEffectBeanMoveSet(_update,toStr(callMoveBeanNameGet(_use)));
    }
    public static StringMap<Struct> beanToEffectDamage(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE,_pk.beanEffectDamageBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectDamage() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFDAMAGE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE);
        return map_;
    }
    public static StringMap<Struct> beanToEffect(PkData _pk) {
        StringMap<Struct> map_ = beanToMove(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT,_pk.beanEffectBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffect() {
        StringMap<String> map_ = mappingToMove();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFF_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT);
        return map_;
    }

    protected static FacadeGame feedDbMoveEffDamComp(TargetChoice _targ, String _acc, SwitchType _noth, int _rk, boolean _c, boolean _dis, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, boolean _s, boolean _k, boolean _dir, String _power, String _fail) {
        FacadeGame f_ = feedDbMoveEffDam(_targ, _acc, _noth, _rk, _c, _dis, _an, _ep, _rech, _sec, _multi, _prio, _solo, _s, _k, _dir, _power, _fail);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
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