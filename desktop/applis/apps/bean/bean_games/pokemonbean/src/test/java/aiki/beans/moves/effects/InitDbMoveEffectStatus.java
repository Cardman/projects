package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.LgInt;
import code.util.StringMap;

public abstract class InitDbMoveEffectStatus extends InitDbMoveEffect {

    public static String callEffectStatusBeanClickLink(NaSt _str, int... _args) {
        return ( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).clickLink(_args[0]);
    }

    public static String callEffectStatusBeanClickLinkId(NaSt _str, int... _args) {
        callEffectStatusBeanClickLink(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectStatusBeanClickLinkDeleted(NaSt _str, int... _args) {
        return ( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).clickLinkDeleted(_args[0]);
    }

    public static String callEffectStatusBeanClickLinkDeletedId(NaSt _str, int... _args) {
        callEffectStatusBeanClickLinkDeleted(_str, _args);
        return getValStatusId(_str);
    }

    public static NaSt callEffectStatusBeanDeletedStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getDeletedStatus());
    }

    public static String callEffectStatusBeanGetFail(NaSt _str, int... _args) {
        return ( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getFail(_args[0]);
    }

    public static String callEffectStatusBeanGetTrLink(NaSt _str, int... _args) {
        return ( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getTrLink(_args[0]);
    }

    public static String callEffectStatusBeanGetTrLinkDeleted(NaSt _str, int... _args) {
        return ( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getTrLinkDeleted(_args[0]);
    }

    public static NaSt callEffectStatusBeanIsStatus(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).isStatus(_args[0]));
    }

    public static NaSt callEffectStatusBeanKoUserHealSubstGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getKoUserHealSubst());
    }

    public static NaSt callEffectStatusBeanLawStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrLongStatKey(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getLawStatus());
    }

    public static NaSt callEffectStatusBeanMapVarsStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getMapVarsStatus());
    }

    public static NaSt callEffectStatusBeanStatusFromUserGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectStatusBean) ((PokemonBeanStruct)_str).getInstance()).getStatusFromUser());
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectStatus _eff) {
        FacadeGame f_ = feedDbMoveEffDataDam(_eff);
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        return f_;
    }
    protected static NaSt dispMoveEffStatus(FacadeGame _fac, int _index) {
        return dispMoveEffStatus(_fac, _index,0);
    }
    protected static NaSt dispMoveEffStatus(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectStatus();
        return transitEffect(_index,_indexEff,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectStatus(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_STATUS,_pk.beanEffectStatusBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectStatus() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSTATUS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_STATUS);
//        return map_;
//    }
    private static FacadeGame feedDbMoveEffDataDam(EffectStatus _eff) {
        FacadeGame facade_ = facade();
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
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
    protected static EffectStatus eff(boolean _koUserHealSubst, boolean _statusFromUser) {
        EffectStatus e_ = Instances.newEffectStatus();
        e_.setKoUserHealSubst(_koUserHealSubst);
        e_.setStatusFromUser(_statusFromUser);
        return e_;
    }
    protected EffectStatus withLawStatus(EffectStatus _e, String _statis, LgInt _v) {
        _e.getLawStatus().addQuickEvent(_statis,_v);
        return _e;
    }
    protected EffectStatus withDeletedStatus(EffectStatus _e, String _statis) {
        _e.getDeletedStatus().add(_statis);
        return _e;
    }
    protected EffectStatus withLawStatusFail(EffectStatus _e, String _statis, String _v) {
        _e.getLocalFailStatus().addEntry(_statis,_v);
        return _e;
    }
}