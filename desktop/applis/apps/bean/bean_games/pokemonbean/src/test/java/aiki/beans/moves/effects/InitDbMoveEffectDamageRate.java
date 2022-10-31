package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.moves.MoveLineBeanClickMove;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectDamageRate extends InitDbMoveEffect{

    public static Struct callEffectDamageRateBeanRateDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageRateBeanRateDamageGet(),_str,_args);
    }

    public static Struct callEffectDamageRateBeanWinHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectDamageRateBeanWinHpGet(),_str,_args);
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(Rate _dam) {
        FacadeGame f_ = feedDbMoveEffDataDam(_dam);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        return f_;
    }
    protected static Struct dispMoveEffDamageRate(FacadeGame _fac, int _index) {
        return dispMoveEffDamageRate(_fac, _index,0);
    }
    protected static Struct dispMoveEffDamageRate(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectDamageRate(pk_);
        transitToAllMoves(pk_, all_);
        StringMap<String> mapping_ = mappingToEffectDamageRate();
        Struct moveline_ = displayMoveLine(all_, _index, mapping_);
        Struct mbean_ = all_.getVal(AikiBeansMovesStd.BEAN_MOVE);
        transit(pk_,new MoveLineBeanClickMove(), moveline_, mbean_,toInt(callMoveLineBeanIndexGet(moveline_)));
        int noEff_ = toInt(elt(callMoveBeanEffectsGet(mbean_), _indexEff));
        Struct eff_ = byStr(all_, mapping_, callMoveBeanGetPage(mbean_, noEff_));
        fwdEffect(eff_,mbean_, noEff_);
        beforeDisplaying(eff_);
        return eff_;
    }
    public static StringMap<Struct> beanToEffectDamageRate(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE_RATE,_pk.beanEffectDamageRateBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectDamageRate() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFDAMAGERATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE_RATE);
        return map_;
    }
    private static FacadeGame feedDbMoveEffDataDam(Rate _efDam) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, eff(_efDam));
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
    private static EffectDamageRate eff(Rate _rate) {
        EffectDamageRate e_ = Instances.newEffectDamageRate();
        e_.setRateDamage(_rate);
        return e_;
    }
}
