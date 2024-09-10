package aiki.beans.moves.effects;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbMoveEffectSwitch extends InitDbMoveEffect {

    public static final String ROAD_TR = "ROAD_TR";

    public static String callEffectSwitchAbilitiesBeanClickAbility(NaSt _str, long... _args) {
        return navigateData(new EffectSwitchAbilitiesBeanClickAbility(),_str,_args);
    }

    public static String callEffectSwitchAbilitiesBeanClickAbilityId(NaSt _str, long... _args) {
        callEffectSwitchAbilitiesBeanClickAbility(_str,_args);
        return getValAbilityId(_str);
    }

    public static NaSt callEffectSwitchAbilitiesBeanGetTrAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGetTrAbility(),_str,_args);
    }

    public static NaSt callEffectSwitchAbilitiesBeanGiveConst(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveConst(),_str,_args);
    }

    public static NaSt callEffectSwitchAbilitiesBeanGiveToTarget(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveToTarget(),_str,_args);
    }

    public static NaSt callEffectSwitchAbilitiesBeanGiveToUser(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanGiveToUser(),_str,_args);
    }

    public static NaSt callEffectSwitchAbilitiesBeanIsDefAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanIsDefAbility(),_str,_args);
    }

    public static NaSt callEffectSwitchAbilitiesBeanSwitchAbilities(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchAbilitiesBeanSwitchAbilities(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanDeleteTargetBerry(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanDeleteTargetBerry(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanGiveTargetItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanGiveTargetItem(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanRemoveTargetItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanRemoveTargetItem(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanResuseLastItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanResuseLastItem(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanSwitchItems(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanSwitchItems(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanTakeItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanTakeItem(),_str,_args);
    }

    public static NaSt callEffectSwitchItemsBeanUseItemAsPossible(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchItemsBeanUseItemAsPossible(),_str,_args);
    }

    public static NaSt callEffectSwitchMoveTypesBeanChangeTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanChangeTypesGet(),_str,_args);
    }

    public static NaSt callEffectSwitchMoveTypesBeanGetTrChangedTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanGetTrChangedTypes(),_str,_args);
    }

    public static NaSt callEffectSwitchMoveTypesBeanGetTrReplacingTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanGetTrReplacingTypes(),_str,_args);
    }

    public static NaSt callEffectSwitchMoveTypesBeanReplacingTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchMoveTypesBeanReplacingTypesGet(),_str,_args);
    }

    public static NaSt callEffectSwitchPointViewBeanAttractDamageMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanAttractDamageMoves(),_str,_args);
    }

    public static NaSt callEffectSwitchPointViewBeanMirrorAgainstUser(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanMirrorAgainstUser(),_str,_args);
    }

    public static NaSt callEffectSwitchPointViewBeanThieveBonus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchPointViewBeanThieveBonus(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanAddedTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanAddedTypesGet(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanChgtTypeByEnvGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanChgtTypeByEnvGet(),_str,_args);
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnv(NaSt _str, long... _args) {
        return navigateData(new EffectSwitchTypesBeanClickGlobalMoveFctEnv(),_str,_args);
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnvId(NaSt _str, long... _args) {
        callEffectSwitchTypesBeanClickGlobalMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }

    public static NaSt callEffectSwitchTypesBeanConstTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanConstTypesGet(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGetTrAddedType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrAddedType(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGetTrConstType(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrConstType(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGetTrEnv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrEnv(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGetTrGlobalMoveFctEnv(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGetTrGlobalMoveFctEnv(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGiveConst(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveConst(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGiveToTarget(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveToTarget(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGiveToUser(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGiveToUser(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanGlobalMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanGlobalMovesGet(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanIsConstTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsConstTypes(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanIsResTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsResTypes(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanIsUserTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanIsUserTypes(),_str,_args);
    }

    public static NaSt callEffectSwitchTypesBeanSwitchTypes(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EffectSwitchTypesBeanSwitchTypes(),_str,_args);
    }
    protected static NaSt dispMoveEffCopyMove(ConstValuesType _cst, ExchangeType _exc) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_cst, _exc));
        StringMap<NaSt> all_ = beanToEffectSwitchTypes(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchTypes();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectSwitchTypes(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHTYPES,_pk.beanEffectSwitchTypesBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchTypes() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHTYPES);
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
    protected static NaSt dispMoveEffSwitchMoveTypes() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataSwitchMoveTypes());
        StringMap<NaSt> all_ = beanToEffectSwitchMoveTypes(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchMoveTypes();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectSwitchMoveTypes(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHMOVETYPES,_pk.beanEffectSwitchMoveTypesBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchMoveTypes() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHMOVETYPES);
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
    protected static NaSt dispMoveEffSwitchAbilities(String _targetAttacksLast, ExchangeType _res) {
        return dispMoveEffSwitchAbilities(feedDbMoveEffDataSwitchAbilities(_targetAttacksLast, _res));
    }
    protected static NaSt dispMoveEffSwitchAbilities(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectSwitchAbilities(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchAbilities();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectSwitchAbilities(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHABILITIES,_pk.beanEffectSwitchAbilitiesBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchAbilities() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHABILITIES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHABILITIES);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataSwitchAbilities(String _targetAttacksLast, ExchangeType _res) {
        FacadeGame facade_ = facade();
        addEffSwitchAbilities(facade_, _targetAttacksLast, _res);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffSwitchAbilities(FacadeGame _facade, String _targetAttacksLast, ExchangeType _res) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effSwitchAbilities(_targetAttacksLast, _res));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectSwitchAbilities effSwitchAbilities(String _targetAttacksLast, ExchangeType _res) {
        EffectSwitchAbilities cl_ = Instances.newEffectSwitchAbilities();
        cl_.setConstAbility(_targetAttacksLast);
        cl_.setExchangeAbility(_res);
        return cl_;
    }
    protected static NaSt dispMoveEffSwitchItems(MoveItemType _res) {
        return dispMoveEffSwitchItems(feedDbMoveEffDataSwitchItems(_res));
    }
    protected static NaSt dispMoveEffSwitchItems(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectSwitchItems(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchItems();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectSwitchItems(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHITEMS,_pk.beanEffectSwitchItemsBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchItems() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHITEMS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHITEMS);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataSwitchItems(MoveItemType _res) {
        FacadeGame facade_ = facade();
        addEffSwitchItems(facade_, _res);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffSwitchItems(FacadeGame _facade, MoveItemType _res) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effSwitchItems(_res));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectSwitchItems effSwitchItems(MoveItemType _res) {
        EffectSwitchItems cl_ = Instances.newEffectSwitchItems();
        cl_.setMoveObject(_res);
        return cl_;
    }
    protected static NaSt dispMoveEffSwitchPointView(PointViewChangementType _res) {
        return dispMoveEffSwitchPointView(feedDbMoveEffDataSwitchPointView(_res));
    }
    protected static NaSt dispMoveEffSwitchPointView(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToEffectSwitchPointView(pk_);
        StringMap<String> mapping_ = mappingToEffectSwitchPointView();
        return transitEffect(0,0,pk_,all_,mapping_);
    }
    public static StringMap<NaSt> beanToEffectSwitchPointView(PkData _pk) {
        StringMap<NaSt> map_ = beanToEffect(_pk);
        map_.addEntry(AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHPOINTVIEW,_pk.beanEffectSwitchPointViewBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToEffectSwitchPointView() {
        StringMap<String> map_ = mappingToEffect();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOINTVIEW_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHPOINTVIEW);
        return map_;
    }
    protected static FacadeGame feedDbMoveEffDataSwitchPointView(PointViewChangementType _res) {
        FacadeGame facade_ = facade();
        addEffSwitchPointView(facade_, _res);
        facade_.getData().completeMembers(M_STA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_DAM_VERY_BAD, moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trs(facade_);
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }
    private static void addEffSwitchPointView(FacadeGame _facade, PointViewChangementType _res) {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        feed(dam_, TargetChoice.UNIQUE_IMPORTE, "1", SwitchType.NOTHING, 0, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(dam_, true, true, true);
        target(dam_, effSwitchPointView(_res));
        _facade.getData().completeMembers(M_DAM, dam_);
    }
    protected static EffectSwitchPointView effSwitchPointView(PointViewChangementType _res) {
        EffectSwitchPointView cl_ = Instances.newEffectSwitchPointView();
        cl_.setPointViewChangement(_res);
        return cl_;
    }
}
