package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.instances.Instances;
import aiki.map.levels.enums.EnvironmentType;
import code.bean.nat.*;
import code.util.*;

public abstract class InitDbMoveEffectSwitch extends InitDbMoveEffect {

    public static final String ROAD_TR = "ROAD_TR";

    public static String callEffectSwitchAbilitiesBeanClickAbility(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.clickAbility();
    }

    public static String callEffectSwitchAbilitiesBeanClickAbilityId(EffectSwitchAbilitiesBean _str, int... _args) {
        callEffectSwitchAbilitiesBeanClickAbility(_str,_args);
        return getValAbilityId(_str);
    }

    public static String callEffectSwitchAbilitiesBeanGetTrAbility(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.getTrAbility();
    }

    public static boolean callEffectSwitchAbilitiesBeanGiveConst(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.giveConst();
    }

    public static boolean callEffectSwitchAbilitiesBeanGiveToTarget(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.giveToTarget();
    }

    public static boolean callEffectSwitchAbilitiesBeanGiveToUser(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.giveToUser();
    }

    public static boolean callEffectSwitchAbilitiesBeanIsDefAbility(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.isDefAbility();
    }

    public static boolean callEffectSwitchAbilitiesBeanSwitchAbilities(EffectSwitchAbilitiesBean _str, int... _args) {
        return _str.switchAbilities();
    }

    public static boolean callEffectSwitchItemsBeanDeleteTargetBerry(EffectSwitchItemsBean _str, int... _args) {
        return _str.deleteTargetBerry();
    }

    public static boolean callEffectSwitchItemsBeanGiveTargetItem(EffectSwitchItemsBean _str, int... _args) {
        return _str.giveTargetItem();
    }

    public static boolean callEffectSwitchItemsBeanRemoveTargetItem(EffectSwitchItemsBean _str, int... _args) {
        return _str.removeTargetItem();
    }

    public static boolean callEffectSwitchItemsBeanResuseLastItem(EffectSwitchItemsBean _str, int... _args) {
        return _str.resuseLastItem();
    }

    public static boolean callEffectSwitchItemsBeanSwitchItems(EffectSwitchItemsBean _str, int... _args) {
        return _str.switchItems();
    }

    public static boolean callEffectSwitchItemsBeanTakeItem(EffectSwitchItemsBean _str, int... _args) {
        return _str.takeItem();
    }

    public static boolean callEffectSwitchItemsBeanUseItemAsPossible(EffectSwitchItemsBean _str, int... _args) {
        return _str.useItemAsPossible();
    }

    public static DictionaryComparator<TranslatedKey, TranslatedKey> callEffectSwitchMoveTypesBeanChangeTypesGet(EffectSwitchMoveTypesBean _str, int... _args) {
        return _str.getChangeTypes();
    }

    public static String callEffectSwitchMoveTypesBeanGetTrChangedTypes(EffectSwitchMoveTypesBean _str, int... _args) {
        return _str.getTrChangedTypes(_args[0]);
    }

    public static String callEffectSwitchMoveTypesBeanGetTrReplacingTypes(EffectSwitchMoveTypesBean _str, int... _args) {
        return _str.getTrReplacingTypes(_args[0]);
    }

    public static CustList<TranslatedKey> callEffectSwitchMoveTypesBeanReplacingTypesGet(EffectSwitchMoveTypesBean _str, int... _args) {
        return _str.getReplacingTypes();
    }

    public static boolean callEffectSwitchPointViewBeanAttractDamageMoves(EffectSwitchPointViewBean _str, int... _args) {
        return _str.attractDamageMoves();
    }

    public static boolean callEffectSwitchPointViewBeanMirrorAgainstUser(EffectSwitchPointViewBean _str, int... _args) {
        return _str.mirrorAgainstUser();
    }

    public static boolean callEffectSwitchPointViewBeanThieveBonus(EffectSwitchPointViewBean _str, int... _args) {
        return _str.thieveBonus();
    }

    public static CustList<TranslatedKey> callEffectSwitchTypesBeanAddedTypesGet(EffectSwitchTypesBean _str, int... _args) {
        return _str.getAddedTypes();
    }

    public static DictionaryComparator<TranslatedKey, TranslatedKey> callEffectSwitchTypesBeanChgtTypeByEnvGet(EffectSwitchTypesBean _str, int... _args) {
        return _str.getChgtTypeByEnv();
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnv(EffectSwitchTypesBean _str, int... _args) {
        return _str.clickGlobalMoveFctEnv(_args[0]);
    }

    public static String callEffectSwitchTypesBeanClickGlobalMoveFctEnvId(EffectSwitchTypesBean _str, int... _args) {
        callEffectSwitchTypesBeanClickGlobalMoveFctEnv(_str,_args);
        return getValMoveId(_str);
    }

    public static CustList<TranslatedKey> callEffectSwitchTypesBeanConstTypesGet(EffectSwitchTypesBean _str, int... _args) {
        return _str.getConstTypes();
    }

    public static String callEffectSwitchTypesBeanGetTrAddedType(EffectSwitchTypesBean _str, int... _args) {
        return _str.getTrAddedType(_args[0]);
    }

    public static String callEffectSwitchTypesBeanGetTrConstType(EffectSwitchTypesBean _str, int... _args) {
        return _str.getTrConstType(_args[0]);
    }

    public static String callEffectSwitchTypesBeanGetTrEnv(EffectSwitchTypesBean _str, int... _args) {
        return _str.getTrEnv(_args[0]);
    }

    public static String callEffectSwitchTypesBeanGetTrGlobalMoveFctEnv(EffectSwitchTypesBean _str, int... _args) {
        return _str.getTrGlobalMoveFctEnv(_args[0]);
    }

    public static boolean callEffectSwitchTypesBeanGiveConst(EffectSwitchTypesBean _str, int... _args) {
        return _str.giveConst();
    }

    public static boolean callEffectSwitchTypesBeanGiveToTarget(EffectSwitchTypesBean _str, int... _args) {
        return _str.giveToTarget();
    }

    public static boolean callEffectSwitchTypesBeanGiveToUser(EffectSwitchTypesBean _str, int... _args) {
        return _str.giveToUser();
    }

    public static CustList<TranslatedKey> callEffectSwitchTypesBeanGlobalMovesGet(EffectSwitchTypesBean _str, int... _args) {
        return _str.getGlobalMoves();
    }

    public static boolean callEffectSwitchTypesBeanIsConstTypes(EffectSwitchTypesBean _str, int... _args) {
        return _str.isConstTypes();
    }

    public static boolean callEffectSwitchTypesBeanIsResTypes(EffectSwitchTypesBean _str, int... _args) {
        return _str.isResTypes();
    }

    public static boolean callEffectSwitchTypesBeanIsUserTypes(EffectSwitchTypesBean _str, int... _args) {
        return _str.isUserTypes();
    }

    public static boolean callEffectSwitchTypesBeanSwitchTypes(EffectSwitchTypesBean _str, int... _args) {
        return _str.switchTypes();
    }
    protected static EffectSwitchTypesBean dispMoveEffCopyMove(ConstValuesType _cst, ExchangeType _exc) {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataDam(_cst, _exc));
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectSwitchTypes();
        return (EffectSwitchTypesBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectSwitchTypes(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHTYPES,_pk.beanEffectSwitchTypesBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectSwitchTypes() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHTYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHTYPES);
//        return map_;
//    }
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
    protected static EffectSwitchMoveTypesBean dispMoveEffSwitchMoveTypes() {
        PkData pk_ = pkDataByFacade(feedDbMoveEffDataSwitchMoveTypes());
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectSwitchMoveTypes();
        return (EffectSwitchMoveTypesBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectSwitchMoveTypes(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHMOVETYPES,_pk.beanEffectSwitchMoveTypesBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectSwitchMoveTypes() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHMOVETYPES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHMOVETYPES);
//        return map_;
//    }
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
    protected static EffectSwitchAbilitiesBean dispMoveEffSwitchAbilities(String _targetAttacksLast, ExchangeType _res) {
        return dispMoveEffSwitchAbilities(feedDbMoveEffDataSwitchAbilities(_targetAttacksLast, _res));
    }
    protected static EffectSwitchAbilitiesBean dispMoveEffSwitchAbilities(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectSwitchAbilities();
        return (EffectSwitchAbilitiesBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectSwitchAbilities(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHABILITIES,_pk.beanEffectSwitchAbilitiesBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectSwitchAbilities() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHABILITIES_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHABILITIES);
//        return map_;
//    }
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
    protected static EffectSwitchItemsBean dispMoveEffSwitchItems(MoveItemType _res) {
        return dispMoveEffSwitchItems(feedDbMoveEffDataSwitchItems(_res));
    }
    protected static EffectSwitchItemsBean dispMoveEffSwitchItems(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectSwitchItems();
        return (EffectSwitchItemsBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectSwitchItems(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHITEMS,_pk.beanEffectSwitchItemsBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectSwitchItems() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHITEMS_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHITEMS);
//        return map_;
//    }
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
    protected static EffectSwitchPointViewBean dispMoveEffSwitchPointView(PointViewChangementType _res) {
        return dispMoveEffSwitchPointView(feedDbMoveEffDataSwitchPointView(_res));
    }
    protected static EffectSwitchPointViewBean dispMoveEffSwitchPointView(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        StringMap<NaSt> all_ = beanToMove(pk_);
//        StringMap<String> mapping_ = mappingToEffectSwitchPointView();
        return (EffectSwitchPointViewBean)transitEffect(0,0,pk_,all_);
    }
    //    public static StringMap<NaSt> beanToEffectSwitchPointView(PkData _pk) {
//        StringMap<NaSt> map_ = beanToEffect(_pk);
//        map_.addEntry(InitDbMoves.BEAN_EFFECT_SWITCHPOINTVIEW,_pk.beanEffectSwitchPointViewBean(EN));
//        return map_;
//    }
//    public static StringMap<String> mappingToEffectSwitchPointView() {
//        StringMap<String> map_ = mappingToEffect();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MOVES_EFFECTS_EFFSWITCHPOINTVIEW_HTML,AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHPOINTVIEW);
//        return map_;
//    }
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
