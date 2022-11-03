package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectSwitch extends InitDbMoveEffect {

    public static final String ROAD_TR = "ROAD_TR";

    public static Struct callEffectSwitchAbilitiesBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanClickAbility(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanGetTrAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGetTrAbility(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanGiveConst(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveConst(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanGiveToTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveToTarget(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanGiveToUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveToUser(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanIsDefAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanIsDefAbility(),_str,_args);
    }

    public static Struct callEffectSwitchAbilitiesBeanSwitchAbilities(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanSwitchAbilities(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanDeleteTargetBerry(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanDeleteTargetBerry(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanGiveTargetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanGiveTargetItem(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanRemoveTargetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanRemoveTargetItem(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanResuseLastItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanResuseLastItem(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanSwitchItems(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanSwitchItems(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanTakeItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanTakeItem(),_str,_args);
    }

    public static Struct callEffectSwitchItemsBeanUseItemAsPossible(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanUseItemAsPossible(),_str,_args);
    }

    public static Struct callEffectSwitchMoveTypesBeanChangeTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanChangeTypesGet(),_str,_args);
    }

    public static Struct callEffectSwitchMoveTypesBeanGetTrChangedTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanGetTrChangedTypes(),_str,_args);
    }

    public static Struct callEffectSwitchMoveTypesBeanGetTrReplacingTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanGetTrReplacingTypes(),_str,_args);
    }

    public static Struct callEffectSwitchMoveTypesBeanReplacingTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanReplacingTypesGet(),_str,_args);
    }

    public static Struct callEffectSwitchPointViewBeanAttractDamageMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanAttractDamageMoves(),_str,_args);
    }

    public static Struct callEffectSwitchPointViewBeanMirrorAgainstUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanMirrorAgainstUser(),_str,_args);
    }

    public static Struct callEffectSwitchPointViewBeanThieveBonus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanThieveBonus(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanAddedTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanAddedTypesGet(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanChgtTypeByEnvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanChgtTypeByEnvGet(),_str,_args);
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnv(Struct _str, long... _args) {
        return navigateData(new EffectSwitchTypesBeanClickGlobalMoveFctEnv(),_str,_args);
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnvId(Struct _str, long... _args) {
        callEffectSwitchTypesBeanClickGlobalMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }

    public static Struct callEffectSwitchTypesBeanConstTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanConstTypesGet(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGetTrAddedType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrAddedType(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGetTrConstType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrConstType(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGetTrEnv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrEnv(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGetTrGlobalMoveFctEnv(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrGlobalMoveFctEnv(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGiveConst(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveConst(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGiveToTarget(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveToTarget(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGiveToUser(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveToUser(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanGlobalMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGlobalMovesGet(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanIsConstTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsConstTypes(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanIsResTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsResTypes(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanIsUserTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsUserTypes(),_str,_args);
    }

    public static Struct callEffectSwitchTypesBeanSwitchTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanSwitchTypes(),_str,_args);
    }
    protected static Struct dispMoveEffCopyMove(ConstValuesType _cst, ExchangeType _exc) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_cst, _exc));
        StringMap<Struct> all_ = beanToEffectSwitchTypes(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchTypes();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectSwitchTypes(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHTYPES,_pk.beanEffectSwitchTypesBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchTypes() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHTYPES);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataDam(ConstValuesType _cst, ExchangeType _exc) {
        FacadeGame facade_ = facade();
        addEff(effectSwitchTypes(_cst, _exc), facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        chg_.getEffects().add(Instances.newEffectGlobal());
        facade_.getData().completeMembers(M_STA,chg_);
        StatusMoveData gl_ = moveSta(TargetChoice.TOUS_ADV);
        EffectGlobal egl_ = Instances.newEffectGlobal();
        egl_.getChangedTypesTerrain().add(T_TYPE1);
        gl_.getEffects().add(egl_);
        facade_.getData().completeMembers(M_WEA, gl_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getTranslatedEnvironment().addEntry(EN,new IdMap<EnvironmentType,String>());
        facade_.getData().getTranslatedEnvironment().getVal(EN).addEntry(EnvironmentType.ROAD, ROAD_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().setDefMove(M_DAM_VERY_BAD);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void addEff(EffectSwitchTypes _eff, FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectSwitchTypes effectSwitchTypes(ConstValuesType _cst, ExchangeType _exc) {
        EffectSwitchTypes e_ = Instances.newEffectSwitchTypes();
        e_.setConstValuesType(_cst);
        e_.setExchangeTypes(_exc);
        e_.getChgtTypeByEnv().addEntry(EnvironmentType.ROAD,T_TYPE1);
        e_.getAddedTypes().add(T_TYPE1);
        e_.getConstTypes().add(T_TYPE1);
        return e_;
    }
    protected static Struct dispMoveEffSwitchMoveTypes() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataSwitchMoveTypes());
        StringMap<Struct> all_ = beanToEffectSwitchMoveTypes(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchMoveTypes();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<Struct> beanToEffectSwitchMoveTypes(PkData _pk) {
        StringMap<Struct> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHMOVETYPES,_pk.beanEffectSwitchMoveTypesBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchMoveTypes() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(AikiBeansMovesEffectsStd.WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHMOVETYPES);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataSwitchMoveTypes() {
        FacadeGame facade_ = facade();
        addEff(effectSwitchMoveTypes(), facade_);
        StatusMoveData chg_ = moveSta(TargetChoice.TOUS_ADV);
        chg_.getEffects().add(Instances.newEffectGlobal());
        facade_.getData().completeMembers(M_STA,chg_);
        StatusMoveData gl_ = moveSta(TargetChoice.TOUS_ADV);
        EffectGlobal egl_ = Instances.newEffectGlobal();
        egl_.getChangedTypesTerrain().add(T_TYPE1);
        gl_.getEffects().add(egl_);
        facade_.getData().completeMembers(M_WEA, gl_);
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getTranslatedEnvironment().addEntry(EN,new IdMap<EnvironmentType,String>());
        facade_.getData().getTranslatedEnvironment().getVal(EN).addEntry(EnvironmentType.ROAD, ROAD_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().setDefMove(M_DAM_VERY_BAD);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void addEff(EffectSwitchMoveTypes _eff, FacadeGame _facade) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, _eff);
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectSwitchMoveTypes effectSwitchMoveTypes() {
        EffectSwitchMoveTypes e_ = Instances.newEffectSwitchMoveTypes();
        e_.getChangeTypes().addEntry(T_TYPE1,T_TYPE2);
        e_.getReplacingTypes().add(T_TYPE1);
        return e_;
    }
}
