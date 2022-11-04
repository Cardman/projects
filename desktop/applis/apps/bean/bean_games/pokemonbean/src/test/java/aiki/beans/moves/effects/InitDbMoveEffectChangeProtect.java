package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringMap;

public abstract class InitDbMoveEffectChangeProtect extends InitDbMoveEffect {

    public static Struct callEffectUnprotectFromTypesBeanAttackTargetWithTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanAttackTargetWithTypesGet(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanClickMove(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanDisableImmuFromMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanDisableImmuFromMovesGet(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanGetTrAttackTargetType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanGetTrAttackTargetType(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanGetTrDamageType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanGetTrDamageType(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanGetTrDisableImmuMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanGetTrDisableImmuMove(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanGetTrDisableImmuType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanGetTrDisableImmuType(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanGetTrPokemonType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanGetTrPokemonType(),_str,_args);
    }

    public static Struct callEffectUnprotectFromTypesBeanTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectUnprotectFromTypesBeanTypesGet(),_str,_args);
    }

    public static Struct callEffectProtectFromTypesBeanGetTrType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectFromTypesBeanGetTrType(),_str,_args);
    }

    public static Struct callEffectProtectFromTypesBeanImmuAgainstTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectFromTypesBeanImmuAgainstTypesGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtSingleAgainstKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtSingleAgainstKoGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtSingleGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtSingleGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtTeamAgainstDamageMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtTeamAgainstDamageMovesGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtTeamAgainstMultTargetsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtTeamAgainstMultTargetsGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtTeamAgainstPrioGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtTeamAgainstPrioGet(),_str,_args);
    }

    public static Struct callEffectProtectionBeanProtTeamAgainstStatusMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectProtectionBeanProtTeamAgainstStatusMovesGet(),_str,_args);
    }
    protected static Struct dispMoveEffProtection(boolean _protSingle, boolean _protTeamAgainstPrio, boolean _protTeamAgainstStatusMoves, boolean _protTeamAgainstDamageMoves, boolean _protTeamAgainstMultTargets) {
        return dispMoveEffProtection(feedDbMoveEffDataProtection(_protSingle, _protTeamAgainstPrio, _protTeamAgainstStatusMoves, _protTeamAgainstDamageMoves, _protTeamAgainstMultTargets));
    }
    protected static Struct dispMoveEffProtection(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<Struct> all_ = beanToEffectProtection(pk_);
        StringMap<String> mapping_ = mappingToEffectProtection();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectProtection(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_PROTECTION,_pk.beanEffectProtectionBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectProtection() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFPROTECTION_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_PROTECTION);
        return map_;
    }
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
}
