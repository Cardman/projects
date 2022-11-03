package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.util.StringMap;

public abstract class InitDbMoveEffectStatus extends InitDbMoveEffect {

    public static String callEffectStatusBeanClickLink(Struct _str, long... _args) {
        return navigateData(new EffectStatusBeanClickLink(),_str,_args);
    }

    public static String callEffectStatusBeanClickLinkId(Struct _str, long... _args) {
        callEffectStatusBeanClickLink(_str, _args);
        return getValStatusId(_str);
    }
    public static String callEffectStatusBeanClickLinkDeleted(Struct _str, long... _args) {
        return navigateData(new EffectStatusBeanClickLinkDeleted(),_str,_args);
    }

    public static String callEffectStatusBeanClickLinkDeletedId(Struct _str, long... _args) {
        callEffectStatusBeanClickLinkDeleted(_str, _args);
        return getValStatusId(_str);
    }

    public static Struct callEffectStatusBeanDeletedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanDeletedStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetFail(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetFail(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetTrLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetTrLink(),_str,_args);
    }

    public static Struct callEffectStatusBeanGetTrLinkDeleted(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanGetTrLinkDeleted(),_str,_args);
    }

    public static Struct callEffectStatusBeanIsStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanIsStatus(),_str,_args);
    }

    public static Struct callEffectStatusBeanKoUserHealSubstGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanKoUserHealSubstGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanLawStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanLawStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanMapVarsStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanMapVarsStatusGet(),_str,_args);
    }

    public static Struct callEffectStatusBeanStatusFromUserGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectStatusBeanStatusFromUserGet(),_str,_args);
    }

    protected static FacadeGame feedDbMoveEffDataDamComp(EffectStatus _eff) {
        FacadeGame f_ = feedDbMoveEffDataDam(_eff);
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        return f_;
    }
    protected static Struct dispMoveEffStatus(FacadeGame _fac, int _index) {
        return dispMoveEffStatus(_fac, _index,0);
    }
    protected static Struct dispMoveEffStatus(FacadeGame _fac, int _index, int _indexEff) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectStatus(pk_);
        StringMap<String> mapping_ = mappingToEffectStatus();
        return transitEffect(_index,_indexEff,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectStatus(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_STATUS,_pk.beanEffectStatusBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectStatus() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFSTATUS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_STATUS);
        return map_;
    }
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
