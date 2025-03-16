package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.util.TypesDuo;
import aiki.instances.Instances;
import aiki.beans.abilities.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbMoveEffectChangeProtect extends InitDbMoveEffect {

    public static CustList<TranslatedKey> callEffectUnprotectFromTypesBeanAttackTargetWithTypesGet(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getAttackTargetWithTypes();
    }

    public static String callEffectUnprotectFromTypesBeanClickMove(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.clickMove(_args[0]);
    }

    public static String callEffectUnprotectFromTypesBeanClickMoveId(EffectUnprotectFromTypesBean _str, int... _args) {
        callEffectUnprotectFromTypesBeanClickMove(_str, _args);
        return getValMoveId(_str);
    }

    public static CustList<TranslatedKey> callEffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getDisableImmuAgainstTypes();
    }

    public static CustList<TranslatedKey> callEffectUnprotectFromTypesBeanDisableImmuFromMovesGet(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getDisableImmuFromMoves();
    }

    public static String callEffectUnprotectFromTypesBeanGetTrAttackTargetType(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTrAttackTargetType(_args[0]);
    }

    public static String callEffectUnprotectFromTypesBeanGetTrDamageType(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTrDamageType(_args[0]);
    }

    public static String callEffectUnprotectFromTypesBeanGetTrDisableImmuMove(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTrDisableImmuMove(_args[0]);
    }

    public static String callEffectUnprotectFromTypesBeanGetTrDisableImmuType(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTrDisableImmuType(_args[0]);
    }

    public static String callEffectUnprotectFromTypesBeanGetTrPokemonType(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTrPokemonType(_args[0]);
    }

    public static CustList<TranslatedKeyPair> callEffectUnprotectFromTypesBeanTypesGet(EffectUnprotectFromTypesBean _str, int... _args) {
        return _str.getTypes();
    }

    public static String callEffectProtectFromTypesBeanGetTrType(EffectProtectFromTypesBean _str, int... _args) {
        return _str.getTrType(_args[0]);
    }

    public static CustList<TranslatedKey> callEffectProtectFromTypesBeanImmuAgainstTypesGet(EffectProtectFromTypesBean _str, int... _args) {
        return _str.getImmuAgainstTypes();
    }

    public static Rate callEffectProtectionBeanProtSingleAgainstKoGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtSingleAgainstKo();
    }

    public static boolean callEffectProtectionBeanProtSingleGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtSingle();
    }

    public static boolean callEffectProtectionBeanProtTeamAgainstDamageMovesGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtTeamAgainstDamageMoves();
    }

    public static boolean callEffectProtectionBeanProtTeamAgainstMultTargetsGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtTeamAgainstMultTargets();
    }

    public static boolean callEffectProtectionBeanProtTeamAgainstPrioGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtTeamAgainstPrio();
    }

    public static boolean callEffectProtectionBeanProtTeamAgainstStatusMovesGet(EffectProtectionBean _str, int... _args) {
        return _str.getProtTeamAgainstStatusMoves();
    }
    protected static EffectProtectionBean dispMoveEffProtection(boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        return dispMoveEffProtection(feedDbMoveEffDataProtection(_protSingle, _protTeamAgainstPrio, _protTeamAgainstStatusMoves, _protTeamAgainstDamageMoves, _protTeamAgainstMultTargets));
    }
    protected static EffectProtectionBean dispMoveEffProtection(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectProtection();
        return (EffectProtectionBean)transitEffect(0,0,pk_,all_);
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
    protected static EffectProtectFromTypesBean dispMoveEffProtectFromTypes() {
        return dispMoveEffProtectFromTypes(feedDbMoveEffDataProtectFromTypes());
    }
    protected static EffectProtectFromTypesBean dispMoveEffProtectFromTypes(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectProtectFromTypes();
        return (EffectProtectFromTypesBean)transitEffect(0,0,pk_,all_);
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
    protected static EffectUnprotectFromTypesBean dispMoveEffUnprotectFromTypes() {
        return dispMoveEffUnprotectFromTypes(feedDbMoveEffDataUnprotectFromTypes());
    }
    protected static EffectUnprotectFromTypesBean dispMoveEffUnprotectFromTypes(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<BeanRenderWithAppName> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectUnprotectFromTypes();
        return (EffectUnprotectFromTypesBean)transitEffect(0,0,pk_,all_);
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
