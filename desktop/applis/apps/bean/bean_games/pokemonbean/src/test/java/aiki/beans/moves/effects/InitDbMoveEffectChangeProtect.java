package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.util.TypesDuo;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectChangeProtect extends InitDbMoveEffect {

    public static NaSt callEffectUnprotectFromTypesBeanAttackTargetWithTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getAttackTargetWithTypes());
    }

    public static String callEffectUnprotectFromTypesBeanClickMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).clickMove(_args[0])).getInstance();
    }

    public static String callEffectUnprotectFromTypesBeanClickMoveId(NaSt _str, int... _args) {
        callEffectUnprotectFromTypesBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }

    public static NaSt callEffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getDisableImmuAgainstTypes());
    }

    public static NaSt callEffectUnprotectFromTypesBeanDisableImmuFromMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getDisableImmuFromMoves());
    }

    public static NaSt callEffectUnprotectFromTypesBeanGetTrAttackTargetType(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrAttackTargetType(_args[0]));
    }

    public static NaSt callEffectUnprotectFromTypesBeanGetTrDamageType(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrDamageType(_args[0]));
    }

    public static NaSt callEffectUnprotectFromTypesBeanGetTrDisableImmuMove(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableImmuMove(_args[0]));
    }

    public static NaSt callEffectUnprotectFromTypesBeanGetTrDisableImmuType(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrDisableImmuType(_args[0]));
    }

    public static NaSt callEffectUnprotectFromTypesBeanGetTrPokemonType(NaSt _str, int... _args) {
        return new NaStSt(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrPokemonType(_args[0]));
    }

    public static NaSt callEffectUnprotectFromTypesBeanTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getTypesDuo(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTypes());
    }

    public static NaSt callEffectProtectFromTypesBeanGetTrType(NaSt _str, int... _args) {
        return new NaStSt(( (EffectProtectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getTrType(_args[0]));
    }

    public static NaSt callEffectProtectFromTypesBeanImmuAgainstTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (EffectProtectFromTypesBean) ((PokemonBeanStruct)_str).getInstance()).getImmuAgainstTypes());
    }

    public static NaSt callEffectProtectionBeanProtSingleAgainstKoGet(NaSt _str, int... _args) {
        return new RtSt(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtSingleAgainstKo());
    }

    public static NaSt callEffectProtectionBeanProtSingleGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtSingle());
    }

    public static NaSt callEffectProtectionBeanProtTeamAgainstDamageMovesGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtTeamAgainstDamageMoves());
    }

    public static NaSt callEffectProtectionBeanProtTeamAgainstMultTargetsGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtTeamAgainstMultTargets());
    }

    public static NaSt callEffectProtectionBeanProtTeamAgainstPrioGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtTeamAgainstPrio());
    }

    public static NaSt callEffectProtectionBeanProtTeamAgainstStatusMovesGet(NaSt _str, int... _args) {
        return NaBoSt.of(( (EffectProtectionBean) ((PokemonBeanStruct)_str).getInstance()).getProtTeamAgainstStatusMoves());
    }
    protected static NaSt dispMoveEffProtection(boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        return dispMoveEffProtection(feedDbMoveEffDataProtection(_protSingle, _protTeamAgainstPrio, _protTeamAgainstStatusMoves, _protTeamAgainstDamageMoves, _protTeamAgainstMultTargets));
    }
    protected static NaSt dispMoveEffProtection(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectProtection();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectProtection(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_PROTECTION,_pk.beanEffectProtectionBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectProtection() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTION_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_PROTECTION);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataProtection(boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        FacadeGame facade_ = facade();
        addEffProtection(facade_, _protSingle, _protTeamAgainstPrio, _protTeamAgainstStatusMoves, _protTeamAgainstDamageMoves, _protTeamAgainstMultTargets);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
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
    private static void addEffProtection(FacadeGame _facade, boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effProtection(_protSingle, _protTeamAgainstPrio, _protTeamAgainstStatusMoves, _protTeamAgainstDamageMoves, _protTeamAgainstMultTargets));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectProtection effProtection(boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        EffectProtection e_ = Instances.newEffectProtection();
        e_.setProtSingleAgainstKo(Rate.one());
        e_.setProtSingle(_protSingle);
        e_.setProtTeamAgainstPrio(_protTeamAgainstPrio);
        e_.setProtTeamAgainstStatusMoves(_protTeamAgainstStatusMoves);
        e_.setProtTeamAgainstDamageMoves(_protTeamAgainstDamageMoves);
        e_.setProtTeamAgainstMultTargets(_protTeamAgainstMultTargets);
        return e_;
    }
    protected static NaSt dispMoveEffProtectFromTypes() {
        return dispMoveEffProtectFromTypes(feedDbMoveEffDataProtectFromTypes());
    }
    protected static NaSt dispMoveEffProtectFromTypes(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectProtectFromTypes();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectProtectFromTypes(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_PROTECTFROMTYPES,_pk.beanEffectProtectFromTypesBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectProtectFromTypes() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFPROTECTFROMTYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_PROTECTFROMTYPES);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataProtectFromTypes() {
        FacadeGame facade_ = facade();
        addEffProtectFromTypes(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
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
    private static void addEffProtectFromTypes(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effProtectFromTypes());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectProtectFromTypes effProtectFromTypes() {
        EffectProtectFromTypes e_ = Instances.newEffectProtectFromTypes();
        e_.getImmuAgainstTypes().add(T_TYPE1);
        return e_;
    }
    protected static NaSt dispMoveEffUnprotectFromTypes() {
        return dispMoveEffUnprotectFromTypes(feedDbMoveEffDataUnprotectFromTypes());
    }
    protected static NaSt dispMoveEffUnprotectFromTypes(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectUnprotectFromTypes();
        return transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectUnprotectFromTypes(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_UNPROTECTFROMTYPES,_pk.beanEffectUnprotectFromTypesBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectUnprotectFromTypes() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFUNPROTECTFROMTYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_UNPROTECTFROMTYPES);
//        return map_;
//    }
    protected static FacadeGame feedDbMoveEffDataUnprotectFromTypes() {
        FacadeGame facade_ = facade();
        addEffUnprotectFromTypes(facade_);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
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
    private static void addEffUnprotectFromTypes(FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effUnprotectFromTypes());
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectUnprotectFromTypes effUnprotectFromTypes() {
        EffectUnprotectFromTypes e_ = Instances.newEffectUnprotectFromTypes();
        e_.getAttackTargetWithTypes().add(T_TYPE1);
        e_.getDisableImmuAgainstTypes().add(T_TYPE1);
        e_.getDisableImmuFromMoves().add(M_STA);
        e_.getTypes().add(new TypesDuo(T_TYPE1,T_TYPE2));
        return e_;
    }
}
