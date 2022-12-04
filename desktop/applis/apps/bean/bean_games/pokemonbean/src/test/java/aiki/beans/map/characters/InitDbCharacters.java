package aiki.beans.map.characters;

import aiki.beans.*;
import aiki.beans.map.*;
import aiki.beans.map.pokemon.*;
import code.bean.nat.*;
import code.util.StringMap;

public abstract class InitDbCharacters extends InitDbLevelMap {

    public static NaSt displayTrainerLevelZero(int _place, int _tile, int _second) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = transitTrainer(_place, _tile, _second, pk_, all_);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return transitTrainerLevelZero(_place, _tile, _second, pk_, all_);
    }

    private static NaSt transitTrainerLevelZero(int _place, int _tile, int _second, PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevelZero(_place, _pk, _all);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,bean_, _tile);
        NaSt tr_ = _all.getVal(AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_, _second);
        return tr_;
    }

    public static NaSt displaySellerLevelZero(int _place, int _tile, int _second) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        NaSt bean_ = transitLevelZero(_place,pk_,all_);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,bean_,_tile);
        NaSt tr_ = all_.getVal(AikiBeansMapStd.BEAN_SELLER);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,tr_,_second);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static NaSt displayTrainer(int _place, int _level, int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        return transitTrainer(_place, _level, _tile, pk_, all_);
    }

    private static NaSt transitTrainer(int _place, int _level, int _tile, PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevel(_place, _level, _pk, _all);
        NaSt tr_ = _all.getVal(AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_, _tile);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static NaSt displayDealer(int _place, int _level, int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        NaSt bean_ = transitLevel(_place, _level,pk_,all_);
        NaSt tr_ = all_.getVal(AikiBeansMapStd.BEAN_DEALER);
        transit(pk_,new MapLevelBeanClickTileOnMap(),bean_,tr_,_tile);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }
    public static String callDealerBeanClickItem() {
        return callDealerBeanClickItem(displayDealer(3,1,12),0);
    }
    public static String callDealerBeanClickItem(NaSt _str, int _item) {
        return navigateData(new DealerBeanClickItem(),_str,_item);
    }
    public static String callDealerBeanClickItemId() {
        NaSt bean_ = displayDealer(3, 1, 12);
        callDealerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callDealerBeanClickTm() {
        return callDealerBeanClickTm(displayDealer(3,1,17),0);
    }

    public static String callDealerBeanClickTm(NaSt _str, int _tm) {
        return navigateData(new DealerBeanClickTm(),_str,_tm);
    }

    public static String callDealerBeanClickTmId() {
        NaSt bean_ = displayDealer(3, 1, 17);
        callDealerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static NaSt callDealerBeanGetAllTm() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetAllTm(),displayDealer(3,1,17));
    }

    public static NaSt callDealerBeanGetItem() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItem(),displayDealer(3,1,12),0);
    }

    public static NaSt callDealerBeanGetItems() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetItems(),displayDealer(3,1,12));
    }

    public static NaSt callDealerBeanGetTm() {
        return BeanPokemonCommonTs.callLongs(new DealerBeanGetTm(),displayDealer(3,1,17),0);
    }


    public static String callSellerBeanClickItem() {
        return callSellerBeanClickItem(displaySellerLevelZero(1,12,13),0);
    }

    public static String callSellerBeanClickItem(NaSt _str, int _item) {
        return navigateData(new SellerBeanClickItem(),_str,_item);
    }

    public static String callSellerBeanClickItemId() {
        NaSt bean_ = displaySellerLevelZero(1,12,13);
        callSellerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callSellerBeanClickTm() {
        return callSellerBeanClickTm(displaySellerLevelZero(1,12,18),0);
    }

    public static String callSellerBeanClickTm(NaSt _str, int _tm) {
        return navigateData(new SellerBeanClickTm(),_str,_tm);
    }

    public static String callSellerBeanClickTmId() {
        NaSt bean_ = displaySellerLevelZero(1, 12, 18);
        callSellerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static NaSt callSellerBeanGetAllTm() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetAllTm(),displaySellerLevelZero(1,12,18));
    }

    public static NaSt callSellerBeanGetItem() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItem(),displaySellerLevelZero(1,12,13),0);
    }

    public static NaSt callSellerBeanGetItems() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetItems(),displaySellerLevelZero(1,12,13));
    }

    public static NaSt callSellerBeanGetTm() {
        return BeanPokemonCommonTs.callLongs(new SellerBeanGetTm(),displaySellerLevelZero(1,12,18),0);
    }

    public static NaSt displayDual() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return transitDual(pk_, all_);
    }

    public static NaSt displayAlly() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt dual_ = transitDual(pk_, all_);
        NaSt ally_ = all_.getVal(AikiBeansMapStd.BEAN_ALLY);
        fwdAlly(ally_,dual_);
        beforeDisplaying(ally_);
        return ally_;
    }

    public static NaSt displayTempTrainer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt dual_ = transitDual(pk_, all_);
        NaSt pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainerDual(pkTeam_,dual_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }

    public static NaSt displayGymTrainer() {
        return displayGym(7);
    }

    public static NaSt displayGymLeader() {
        return displayGym(4);
    }
    public static NaSt displayGym(int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt trainer_ = transitTrainerLevelZero(0,12,_tile,pk_, all_);
        NaSt pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    public static NaSt displayLeague(int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt trainer_ = transitTrainer(8,_level,12,pk_, all_);
        NaSt pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    public static NaSt displayMult(int _no) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt trainer_ = transitTrainer(3,1,7,pk_, all_);
        NaSt pkTeam_ = all_.getVal(AikiBeansMapStd.BEAN_PK_TEAM);
        fwdTrainer(pkTeam_,trainer_);
        callPokemonTeamBeanNoFightSet(pkTeam_,_no);
        beforeDisplaying(pkTeam_);
        return pkTeam_;
    }
    private static NaSt transitDual(PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevel(3, 0, _pk, _all);
        NaSt tr_ = _all.getVal(AikiBeansMapStd.BEAN_DUAL);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,tr_,8);
        return tr_;
    }

    public static String callAllyBeanClickAbility(int _pk) {
        return callAllyBeanClickAbility(displayAlly(),_pk);
    }

    public static String callAllyBeanClickAbility(NaSt _str, int _pk) {
        return navigateData(new AllyBeanClickAbility(),_str,_pk);
    }

    public static String callAllyBeanClickAbilityId(int _pk) {
        NaSt bean_ = displayAlly();
        callAllyBeanClickAbility(bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAllyBeanClickItem(int _pk) {
        return callAllyBeanClickItem(displayAlly(),_pk);
    }

    public static String callAllyBeanClickItem(NaSt _str, int _pk) {
        return navigateData(new AllyBeanClickItem(),_str,_pk);
    }

    public static String callAllyBeanClickItemId(int _pk) {
        NaSt bean_ = displayAlly();
        callAllyBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAllyBeanClickMove(int _pk, int _move) {
        return callAllyBeanClickMove(displayAlly(),_pk,_move);
    }

    public static String callAllyBeanClickMove(NaSt _str, int _pk, int _move) {
        return navigateData(new AllyBeanClickMove(),_str,_pk,_move);
    }

    public static String callAllyBeanClickMoveId(int _pk, int _move) {
        NaSt bean_ = displayAlly();
        callAllyBeanClickMove(bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAllyBeanClickName(int _pk) {
        return callAllyBeanClickName(displayAlly(),_pk);
    }

    public static String callAllyBeanClickName(NaSt _str, int _pk) {
        return navigateData(new AllyBeanClickName(),_str,_pk);
    }

    public static String callAllyBeanClickNameId(int _pk) {
        NaSt bean_ = displayAlly();
        callAllyBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static NaSt callAllyBeanGetAbility(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetAbility(),displayAlly(),_pk);
    }

    public static NaSt callAllyBeanGetImage(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetImage(),displayAlly(),_pk);
    }

    public static NaSt callAllyBeanGetGender() {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetGender(),displayAlly(),0);
    }

    public static NaSt callAllyBeanGetItem(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetItem(),displayAlly(),_pk);
    }

    public static NaSt callAllyBeanGetMove(int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetMove(),displayAlly(),_pk,_move);
    }

    public static NaSt callAllyBeanGetName(int _pk) {
        return BeanPokemonCommonTs.callLongs(new AllyBeanGetName(),displayAlly(),_pk);
    }

    public static NaSt callAllyBeanTeamGet() {
        return BeanPokemonCommonTs.callLongs(new AllyBeanTeamGet(),displayAlly());
    }

//    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }

    public static NaSt callDualFightBeanImageGet() {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageGet(),displayDual());
    }

    public static NaSt callDualFightBeanImageMiniGet() {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanImageMiniGet(),displayDual());
    }

    public static NaSt callDualFightBeanImageMiniSecondGet() {
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

    public static String callTrainerBeanClickMove(NaSt _str) {
        return navigateData(new TrainerBeanClickMove(),_str);
    }

    public static String callTrainerBeanClickMoveId(int _place, int _tile, int _second) {
        NaSt bean_ = displayTrainerLevelZero(_place, _tile, _second);
        callTrainerBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static NaSt callTrainerBeanGetName(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetName(),_str);
    }

    public static NaSt callTrainerBeanGetNameLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetName(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanGetNameOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetName(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanGetTeamsRewards(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTeamsRewards(),_str);
    }

    public static NaSt callTrainerBeanGetTeamsRewardsLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetTeamsRewards(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanGetTeamsRewardsOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetTeamsRewards(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanGetTrMove(int _place, int _tile, int _second) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanGetTrMove(),displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanImageGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageGet(),_str);
    }

    public static NaSt callTrainerBeanImageGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanImageGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageGet(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanImageMiniGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanImageMiniGet(),_str,_args);
    }

    public static NaSt callTrainerBeanImageMiniGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageMiniGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanImageMiniGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageMiniGet(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanMoveGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanMoveGet(),_str);
    }

    public static NaSt callTrainerBeanMoveGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanMoveGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanMoveGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanMoveGet(displayTrainer(_place, _level, _tile));
    }

//    public static Struct callTrainerBeanPageTeamGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanPageTeamGet(),_str,_args);
//    }

//    public static Struct callTrainerBeanTrainerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
//    }

    public static String callPokemonTeamBeanClickAbility(int _no, int _pk) {
        return callPokemonTeamBeanClickAbility(displayMult(_no),_pk);
    }

    public static String callPokemonTeamBeanClickAbility(NaSt _str, int _pk) {
        return navigateData(new PokemonTeamBeanClickAbility(),_str,toInt(callPokemonTeamBeanNoFightGet(_str)),_pk);
    }

    public static String callPokemonTeamBeanClickAbilityId(int _no, int _pk) {
        NaSt bean_ = displayMult(_no);
        callPokemonTeamBeanClickAbility(bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callPokemonTeamBeanClickItem(int _no, int _pk) {
        return callPokemonTeamBeanClickItem(displayMult(_no),_pk);
    }

    public static String callPokemonTeamBeanClickItem(NaSt _str, int _pk) {
        return navigateData(new PokemonTeamBeanClickItem(),_str,toInt(callPokemonTeamBeanNoFightGet(_str)),_pk);
    }

    public static String callPokemonTeamBeanClickItemId(int _no, int _pk) {
        NaSt bean_ = displayMult(_no);
        callPokemonTeamBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callPokemonTeamBeanClickMove(int _no, int _pk, int _move) {
        return callPokemonTeamBeanClickMove(displayMult(_no),_pk,_move);
    }

    public static String callPokemonTeamBeanClickMove(NaSt _str, int _pk, int _move) {
        return navigateData(new PokemonTeamBeanClickMove(),_str,toInt(callPokemonTeamBeanNoFightGet(_str)),_pk,_move);
    }

    public static String callPokemonTeamBeanClickMoveId(int _no, int _pk, int _move) {
        NaSt bean_ = displayMult(_no);
        callPokemonTeamBeanClickMove(bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callPokemonTeamBeanClickName(int _no, int _pk) {
        return callPokemonTeamBeanClickName(displayMult(_no),_pk);
    }

    public static String callPokemonTeamBeanClickName(NaSt _str, int _pk) {
        return navigateData(new PokemonTeamBeanClickName(),_str,toInt(callPokemonTeamBeanNoFightGet(_str)),_pk);
    }

    public static String callPokemonTeamBeanClickNameId(int _no, int _pk) {
        NaSt bean_ = displayMult(_no);
        callPokemonTeamBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static NaSt callPokemonTeamBeanGetAbility(int _no, int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetAbility(),displayMult(_no),_pk);
    }

    public static NaSt callPokemonTeamBeanGetImageTempTrainer(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayTempTrainer(),_pk);
    }

    public static NaSt callPokemonTeamBeanGetImageGymTrainer(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayGymTrainer(),_pk);
    }

    public static NaSt callPokemonTeamBeanGetImageGymLeader(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayGymLeader(),_pk);
    }

    public static NaSt callPokemonTeamBeanGetImageTrainerLeague(int _level,int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayLeague(_level),_pk);
    }

    public static NaSt callPokemonTeamBeanGetImageMulti(int _no,int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetImage(),displayMult(_no),_pk);
    }

    public static NaSt callPokemonTeamBeanGetGender() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetGender(),displayTempTrainer(),0);
    }
    public static NaSt callPokemonTeamBeanGetItem(int _no, int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetItem(),displayMult(_no),_pk);
    }

    public static NaSt callPokemonTeamBeanGetMove(int _no, int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetMove(),displayMult(_no),_pk,_move);
    }

    public static NaSt callPokemonTeamBeanGetName(int _no, int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanGetName(),displayMult(_no),_pk);
    }

    public static NaSt callPokemonTeamBeanMultiplicityGetMult() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanMultiplicityGet(),displayMult(0));
    }

    public static NaSt callPokemonTeamBeanMultiplicityGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanMultiplicityGet(),displayGymLeader());
    }

    public static NaSt callPokemonTeamBeanNoFightGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanNoFightGet(),_str);
    }

    public static NaSt callPokemonTeamBeanRewardGet(int _no) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanRewardGet(),displayMult(_no));
    }

    public static NaSt callPokemonTeamBeanRewardGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanRewardGet(),displayTempTrainer());
    }

    public static NaSt callPokemonTeamBeanTeamGetTempTrainer() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayTempTrainer());
    }

    public static NaSt callPokemonTeamBeanTeamGetGymTrainer() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayGymTrainer());
    }

    public static NaSt callPokemonTeamBeanTeamGetGymLeader() {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayGymLeader());
    }

    public static NaSt callPokemonTeamBeanTeamGetTrainerLeague(int _level) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayLeague(_level));
    }

    public static NaSt callPokemonTeamBeanTeamGetMulti(int _no) {
        return BeanPokemonCommonTs.callLongs(new PokemonTeamBeanTeamGet(),displayMult(_no));
    }

    public static NaSt callPokemonTeamBeanNoFightSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new PokemonTeamBeanNoFightSet(),_str,_args);
    }
    public static void fwdTrainerDual(NaSt _update, NaSt _use) {
        callPokemonTeamBeanTrainerSet(_update,callDualFightBeanTrainerGet(_use));
    }

    public static void fwdTrainer(NaSt _update, NaSt _use) {
        callPokemonTeamBeanTrainerSet(_update,callTrainerBeanTrainerGet(_use));
    }
    public static void fwdAlly(NaSt _update, NaSt _use) {
        callAllyBeanAllySet(_update,callDualFightBeanAllyGet(_use));
    }
    public static NaSt callDualFightBeanAllyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
    }

    public static NaSt callAllyBeanAllySet(NaSt _str, NaSt _args) {
        return BeanPokemonCommonTs.callStruct(new AllyBeanAllySet(),_str,_args);
    }
    public static NaSt callDualFightBeanTrainerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
    }
    public static NaSt callTrainerBeanTrainerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
    }
    public static NaSt callPokemonTeamBeanTrainerSet(NaSt _str, NaSt _args) {
        return BeanPokemonCommonTs.callStruct(new PokemonTeamBeanTrainerSet(),_str,_args);
    }
}
