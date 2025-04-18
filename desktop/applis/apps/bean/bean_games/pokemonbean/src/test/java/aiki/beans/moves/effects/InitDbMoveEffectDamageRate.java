package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectDamageRate extends InitDbMoveEffect{

    public static Rate callEffectDamageRateBeanRateDamageGet(EffectDamageRateBean _str, int... _args) {
        return _str.getRateDamage();
    }

    public static boolean callEffectDamageRateBeanWinHpGet(EffectDamageRateBean _str, int... _args) {
        return _str.getWinHp();
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(Rate _dam) {
        FacadeGame f_ = feedDbMoveEffDataDam(_dam);
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
    protected static EffectDamageRateBean dispMoveEffDamageRate(FacadeGame _fac, int _index) {
        return dispMoveEffDamageRate(_fac, _index,0);
    }
    protected static EffectDamageRateBean dispMoveEffDamageRate(FacadeGame _fac, int _index, int _indexEff) {
        FacadeGame pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectDamageRate();
        return (EffectDamageRateBean)transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectDamageRate(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_DAMAGE_RATE,_pk.beanEffectDamageRateBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectDamageRate() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFDAMAGERATE_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE_RATE);
//        return map_;
//    }
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