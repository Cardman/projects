package aiki.beans.map.characters;

import aiki.beans.*;
import aiki.beans.map.*;
import aiki.beans.map.pokemon.*;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbCharacters extends InitDbLevelMap {

    public static Struct displayTrainerLevelZero(int _place, int _tile, int _second) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = transitTrainer(_place, _tile, _second, pk_, all_);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return transitTrainerLevelZero(_place, _tile, _second, pk_, all_);
    }

    private static Struct transitTrainerLevelZero(int _place, int _tile, int _second, PkData _pk, StringMap<Struct> _all) {
        Struct bean_ = transitLevelZero(_place, _pk, _all);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,bean_, _tile);
        Struct tr_ = _all.getVal(AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_, _second);
        return tr_;
    }

    public static Struct displaySellerLevelZero(int _place, int _tile, int _second) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        Struct bean_ = transitLevelZero(_place,pk_,all_);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,bean_,_tile);
        Struct tr_ = all_.getVal(AikiBeansMapStd.BEAN_SELLER);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,tr_,_second);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static Struct displayTrainer(int _place, int _level, int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        return transitTrainer(_place, _level, _tile, pk_, all_);
    }

    private static Struct transitTrainer(int _place, int _level, int _tile, PkData _pk, StringMap<Struct> _all) {
        Struct bean_ = transitLevel(_place, _level, _pk, _all);
        Struct tr_ = _all.getVal(AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_, _tile);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static Struct displayDealer(int _place, int _level, int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        Struct bean_ = transitLevel(_place, _level,pk_,all_);
        Struct tr_ = all_.getVal(AikiBeansMapStd.BEAN_DEALER);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,tr_,_tile);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }
    public static String callDealerBeanClickItem() {
        return callDealerBeanClickItem(displayDealer(3,1,12),0);
    }
    public static String callDealerBeanClickItem(Struct _str, int _item) {
        return navigateData(new DealerBeanClickItem(),_str,_item);
    }
    public static String callDealerBeanClickItemId() {
        Struct bean_ = displayDealer(3, 1, 12);
        callDealerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callDealerBeanClickTm() {
        return callDealerBeanClickTm(displayDealer(3,1,17),0);
    }

    public static String callDealerBeanClickTm(Struct _str, int _tm) {
        return navigateData(new DealerBeanClickTm(),_str,_tm);
    }

    public static String callDealerBeanClickTmId() {
        Struct bean_ = displayDealer(3, 1, 17);
        callDealerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static Struct callDealerBeanGetAllTm() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetAllTm(),displayDealer(3,1,17));
    }

    public static Struct callDealerBeanGetItem() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItem(),displayDealer(3,1,12),0);
    }

    public static Struct callDealerBeanGetItems() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItems(),displayDealer(3,1,12));
    }

    public static Struct callDealerBeanGetTm() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetTm(),displayDealer(3,1,17),0);
    }


    public static String callSellerBeanClickItem() {
        return callSellerBeanClickItem(displaySellerLevelZero(1,12,13),0);
    }

    public static String callSellerBeanClickItem(Struct _str, int _item) {
        return navigateData(new SellerBeanClickItem(),_str,_item);
    }

    public static String callSellerBeanClickItemId() {
        Struct bean_ = displaySellerLevelZero(1,12,13);
        callSellerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callSellerBeanClickTm() {
        return callSellerBeanClickTm(displaySellerLevelZero(1,12,18),0);
    }

    public static String callSellerBeanClickTm(Struct _str, int _tm) {
        return navigateData(new SellerBeanClickTm(),_str,_tm);
    }

    public static String callSellerBeanClickTmId() {
        Struct bean_ = displaySellerLevelZero(1, 12, 18);
        callSellerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static Struct callSellerBeanGetAllTm() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetAllTm(),displaySellerLevelZero(1,12,18));
    }

    public static Struct callSellerBeanGetItem() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItem(),displaySellerLevelZero(1,12,13),0);
    }

    public static Struct callSellerBeanGetItems() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItems(),displaySellerLevelZero(1,12,13));
    }

    public static Struct callSellerBeanGetTm() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetTm(),displaySellerLevelZero(1,12,18),0);
    }

    public static Struct displayDual() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return transitDual(pk_, all_);
    }

    public static Struct displayAlly() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        Struct dual_ = transitDual(pk_, all_);
        Struct ally_ = all_.getVal(AikiBeansMapStd.BEAN_ALLY);
        fwdAlly(ally_,dual_);
        beforeDisplaying(ally_);
        return ally_;
    }

    public static Struct displayTempTrainer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        Struct dual_ = transitDual(pk_, all_);
        Struct pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainerDual(pkTeam_,dual_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }

    public static Struct displayGymTrainer() {
        return displayGym(7);
    }

    public static Struct displayGymLeader() {
        return displayGym(4);
    }
    public static Struct displayGym(int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        Struct trainer_ = transitTrainerLevelZero(0,12,_tile,pk_, all_);
        Struct pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    public static Struct displayLeague(int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        Struct trainer_ = transitTrainer(8,_level,12,pk_, all_);
        Struct pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    public static Struct displayMult(int _no) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        Struct trainer_ = transitTrainer(3,1,7,pk_, all_);
        Struct pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        callPokemonTeamBeanNoFightSet(pkTeam_,_no);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    private static Struct transitDual(PkData _pk, StringMap<Struct> _all) {
        Struct bean_ = transitLevel(3, 0, _pk, _all);
        Struct tr_ = _all.getVal(AikiBeansMapStd.BEAN_DUAL);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_,8);
        return tr_;
    }

    public static String callAllyBeanClickAbility(int _pk) {
        return callAllyBeanClickAbility(displayAlly(),_pk);
    }

    public static String callAllyBeanClickAbility(Struct _str, int _pk) {
        return navigateData(new AllyBeanClickAbility(),_str,_pk);
    }

    public static String callAllyBeanClickAbilityId(int _pk) {
        Struct bean_ = displayAlly();
        callAllyBeanClickAbility(bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAllyBeanClickItem(int _pk) {
        return callAllyBeanClickItem(displayAlly(),_pk);
    }

    public static String callAllyBeanClickItem(Struct _str, int _pk) {
        return navigateData(new AllyBeanClickItem(),_str,_pk);
    }

    public static String callAllyBeanClickItemId(int _pk) {
        Struct bean_ = displayAlly();
        callAllyBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAllyBeanClickMove(int _pk, int _move) {
        return callAllyBeanClickMove(displayAlly(),_pk,_move);
    }

    public static String callAllyBeanClickMove(Struct _str, int _pk, int _move) {
        return navigateData(new AllyBeanClickMove(),_str,_pk,_move);
    }

    public static String callAllyBeanClickMoveId(int _pk, int _move) {
        Struct bean_ = displayAlly();
        callAllyBeanClickMove(bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAllyBeanClickName(int _pk) {
        return callAllyBeanClickName(displayAlly(),_pk);
    }

    public static String callAllyBeanClickName(Struct _str, int _pk) {
        return navigateData(new AllyBeanClickName(),_str,_pk);
    }

    public static String callAllyBeanClickNameId(int _pk) {
        Struct bean_ = displayAlly();
        callAllyBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static Struct callAllyBeanGetAbility(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetAbility(),displayAlly(),_pk);
    }

    public static Struct callAllyBeanGetImage(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetImage(),displayAlly(),_pk);
    }

    public static Struct callAllyBeanGetItem(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetItem(),displayAlly(),_pk);
    }

    public static Struct callAllyBeanGetMove(int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetMove(),displayAlly(),_pk,_move);
    }

    public static Struct callAllyBeanGetName(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetName(),displayAlly(),_pk);
    }

    public static Struct callAllyBeanTeamGet() {
        return BeanPokemonCommonTs.callLongs(new AllyBeanTeamGet(),displayAlly());
    }

//    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }

    public static Struct callDualFightBeanImageGet() {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageGet(),displayDual());
    }

    public static Struct callDualFightBeanImageMiniGet() {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageMiniGet(),displayDual());
    }

    public static Struct callDualFightBeanImageMiniSecondGet() {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageMiniSecondGet(),displayDual());
    }

//    public static Struct callDualFightBeanPageAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanPageAllyGet(),_str,_args);
//    }

//    public static Struct callDualFightBeanPageTeamGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanPageTeamGet(),_str,_args);
//    }
//
//    public static Struct callDualFightBeanTrainerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
//    }

    public static String callTrainerBeanClickMove(int _place, int _tile, int _second) {
        return callTrainerBeanClickMove(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static String callTrainerBeanClickMove(Struct _str) {
        return navigateData(new TrainerBeanClickMove(),_str);
    }

    public static String callTrainerBeanClickMoveId(int _place, int _tile, int _second) {
        Struct bean_ = displayTrainerLevelZero(_place, _tile, _second);
        callTrainerBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static Struct callTrainerBeanGetName(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetName(),_str);
    }

    public static Struct callTrainerBeanGetNameLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetName(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanGetNameOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetName(displayTrainer(_place, _level, _tile));
    }

    public static Struct callTrainerBeanGetTeamsRewards(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTeamsRewards(),_str);
    }

    public static Struct callTrainerBeanGetTeamsRewardsLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetTeamsRewards(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanGetTeamsRewardsOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetTeamsRewards(displayTrainer(_place, _level, _tile));
    }

    public static Struct callTrainerBeanGetTrMove(int _place, int _tile, int _second) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTrMove(),displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanImageGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageGet(),_str);
    }

    public static Struct callTrainerBeanImageGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanImageGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageGet(displayTrainer(_place, _level, _tile));
    }

    public static Struct callTrainerBeanImageMiniGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageMiniGet(),_str,_args);
    }

    public static Struct callTrainerBeanImageMiniGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageMiniGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanImageMiniGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageMiniGet(displayTrainer(_place, _level, _tile));
    }

    public static Struct callTrainerBeanMoveGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanMoveGet(),_str);
    }

    public static Struct callTrainerBeanMoveGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanMoveGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static Struct callTrainerBeanMoveGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanMoveGet(displayTrainer(_place, _level, _tile));
    }

//    public static Struct callTrainerBeanPageTeamGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanPageTeamGet(),_str,_args);
//    }

//    public static Struct callTrainerBeanTrainerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
//    }

    public static Struct callPokemonTeamBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickAbility(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickItem(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickMove(),_str,_args);
    }

    public static Struct callPokemonTeamBeanClickName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanClickName(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetAbility(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetImageTempTrainer(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayTempTrainer(),_pk);
    }

    public static Struct callPokemonTeamBeanGetImageGymTrainer(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayGymTrainer(),_pk);
    }

    public static Struct callPokemonTeamBeanGetImageGymLeader(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayGymLeader(),_pk);
    }

    public static Struct callPokemonTeamBeanGetImageTrainerLeague(int _level,int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayLeague(_level),_pk);
    }

    public static Struct callPokemonTeamBeanGetImageMulti(int _no,int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayMult(_no),_pk);
    }

    public static Struct callPokemonTeamBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetItem(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetMove(),_str,_args);
    }

    public static Struct callPokemonTeamBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetName(),_str,_args);
    }

    public static Struct callPokemonTeamBeanMultiplicityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanMultiplicityGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanNoFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanNoFightGet(),_str,_args);
    }

    public static Struct callPokemonTeamBeanRewardGet(int _no) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanRewardGet(),displayMult(_no));
    }

    public static Struct callPokemonTeamBeanTeamGetTempTrainer() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayTempTrainer());
    }

    public static Struct callPokemonTeamBeanTeamGetGymTrainer() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayGymTrainer());
    }

    public static Struct callPokemonTeamBeanTeamGetGymLeader() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayGymLeader());
    }

    public static Struct callPokemonTeamBeanTeamGetTrainerLeague(int _level) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayLeague(_level));
    }

    public static Struct callPokemonTeamBeanTeamGetMulti(int _no) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayMult(_no));
    }

    public static Struct callPokemonTeamBeanNoFightSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new PokemonTeamBeanNoFightSet(),_str,_args);
    }
    public static void fwdTrainerDual(Struct _update, Struct _use) {
        callPokemonTeamBeanTrainerSet(_update,callDualFightBeanTrainerGet(_use));
    }

    public static void fwdTrainer(Struct _update, Struct _use) {
        callPokemonTeamBeanTrainerSet(_update,callTrainerBeanTrainerGet(_use));
    }
    public static void fwdAlly(Struct _update, Struct _use) {
        callAllyBeanAllySet(_update,callDualFightBeanAllyGet(_use));
    }
    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
    }

    public static Struct callAllyBeanAllySet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new AllyBeanAllySet(),_str,_args);
    }
    public static Struct callDualFightBeanTrainerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
    }
    public static Struct callTrainerBeanTrainerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
    }
    public static Struct callPokemonTeamBeanTrainerSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new PokemonTeamBeanTrainerSet(),_str,_args);
    }
}
