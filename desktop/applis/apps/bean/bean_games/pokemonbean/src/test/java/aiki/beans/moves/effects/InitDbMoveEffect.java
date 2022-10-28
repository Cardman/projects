package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.moves.InitDbMove;
import aiki.beans.moves.MoveLineBeanClickMove;
import aiki.facade.FacadeGame;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

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
}
