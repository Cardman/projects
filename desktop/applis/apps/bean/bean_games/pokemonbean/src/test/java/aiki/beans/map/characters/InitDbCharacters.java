package aiki.beans.map.characters;

import aiki.beans.*;
import aiki.beans.map.*;
import aiki.beans.map.elements.*;
import aiki.beans.map.pokemon.*;
import aiki.facade.FacadeGame;
import code.util.*;

public abstract class InitDbCharacters extends InitDbLevelMap {

    public static TrainerBean displayTrainerLevelZero(int _place, int _tile, int _second) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = transitTrainer(_place, _tile, _second, pk_, all_);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return transitTrainerLevelZero(_place, _tile, _second, pk_, all_);
    }

    private static TrainerBean transitTrainerLevelZero(int _place, int _tile, int _second, FacadeGame _pk, StringMap<BeanRenderWithAppName> _all) {
        MapLevelBean bean_ = transitLevelZero(_place, _pk, _all);
        transit(_pk,new MapLevelBeanClickTileOnMap(bean_,_tile),bean_,bean_);
        TrainerBean tr_ = (TrainerBean) _all.getVal(InitDbMap.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(bean_,_second),bean_,tr_);
        return tr_;
    }

    public static SellerBean displaySellerLevelZero(int _place, int _tile, int _second) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        MapLevelBean bean_ = transitLevelZero(_place,pk_,all_);
        transit(pk_,new MapLevelBeanClickTileOnMap(bean_,_tile),bean_,bean_);
        SellerBean tr_ = (SellerBean) all_.getVal(InitDbMap.BEAN_SELLER);
        transit(pk_,new MapLevelBeanClickTileOnMap(bean_,_second),bean_,tr_);
//        callMapLevelBeanClickTileOnMapStruct(bean_,_tile);
//        beforeDisplaying(bean_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _second));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static TrainerBean displayTrainer(int _place, int _level, int _tile) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        return transitTrainer(_place, _level, _tile, pk_, all_);
    }

    private static TrainerBean transitTrainer(int _place, int _level, int _tile, FacadeGame _pk, StringMap<BeanRenderWithAppName> _all) {
        MapLevelBean bean_ = transitLevel(_place, _level, _pk, _all);
        TrainerBean tr_ = (TrainerBean) _all.getVal(InitDbMap.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap(bean_,_tile),bean_,tr_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static DealerBean displayDealer(int _place, int _level, int _tile) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        MapLevelBean bean_ = transitLevel(_place, _level,pk_,all_);
        DealerBean tr_ = (DealerBean) all_.getVal(InitDbMap.BEAN_DEALER);
        transit(pk_,new MapLevelBeanClickTileOnMap(bean_,_tile),bean_,tr_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }
    public static String callDealerBeanClickItem() {
        return callDealerBeanClickItem(displayDealer(3,1,12),0);
    }
    public static String callDealerBeanClickItem(DealerBean _str, int _item) {
        return _str.clickItem(_item);
    }
    public static String callDealerBeanClickItemId() {
        DealerBean bean_ = displayDealer(3, 1, 12);
        callDealerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callDealerBeanClickTm() {
        return callDealerBeanClickTm(displayDealer(3,1,17),0);
    }

    public static String callDealerBeanClickTm(DealerBean _str, int _tm) {
        return _str.clickTm(_tm);
    }

    public static String callDealerBeanClickTmId() {
        DealerBean bean_ = displayDealer(3, 1, 17);
        callDealerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static CustList<TranslatedKey> callDealerBeanGetAllTm() {
        return displayDealer(3,1,17).getAllTmDealer();
    }

    public static String callDealerBeanGetItem() {
        return displayDealer(3,1,12).getItem(0);
    }

    public static CustList<TranslatedKey> callDealerBeanGetItems() {
        return displayDealer(3,1,12).getItemsDealer();
    }

    public static String callDealerBeanGetTm() {
        return displayDealer(3,1,17).getTmDealer(0);
    }


    public static String callSellerBeanClickItem() {
        return callSellerBeanClickItem(displaySellerLevelZero(1,12,13),0);
    }

    public static String callSellerBeanClickItem(SellerBean _str, int _item) {
        return _str.clickItem(_item);
    }

    public static String callSellerBeanClickItemId() {
        SellerBean bean_ = displaySellerLevelZero(1,12,13);
        callSellerBeanClickItem(bean_,0);
        return getValItemId(bean_);
    }

    public static String callSellerBeanClickTm() {
        return callSellerBeanClickTm(displaySellerLevelZero(1,12,18),0);
    }

    public static String callSellerBeanClickTm(SellerBean _str, int _tm) {
        return _str.clickTm(_tm);
    }

    public static String callSellerBeanClickTmId() {
        SellerBean bean_ = displaySellerLevelZero(1, 12, 18);
        callSellerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static CustList<TranslatedKey> callSellerBeanGetAllTm() {
        return displaySellerLevelZero(1,12,18).getAllTm();
    }

    public static String callSellerBeanGetItem() {
        return displaySellerLevelZero(1,12,13).getItem(0);
    }

    public static CustList<TranslatedKey> callSellerBeanGetItems() {
        return displaySellerLevelZero(1,12,13).getItems();
    }

    public static String callSellerBeanGetTm() {
        return displaySellerLevelZero(1,12,18).getTm(0);
    }

    public static DualFightBean displayDual() {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return transitDual(pk_, all_);
    }

    public static AllyBean displayAlly() {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        DualFightBean dual_ = transitDual(pk_, all_);
//        NaSt ally_ = new PokemonBeanStruct(new AllyBean());
//        fwdAlly(ally_,dual_);
//        beforeDisplaying(ally_);
        return dual_.getAllyTeam();
    }

    public static PokemonTeamBean displayTempTrainer() {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        DualFightBean dual_ = transitDual(pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainerDual(pkTeam_,dual_);
//        beforeDisplaying(pkTeam_);
        return dual_.getFoeTeam();
    }

    public static PokemonTeamBean displayGymTrainer() {
        return displayGym(7);
    }

    public static PokemonTeamBean displayGymLeader() {
        return displayGym(4);
    }
    public static PokemonTeamBean displayGym(int _tile) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        TrainerBean trainer_ = transitTrainerLevelZero(0,12,_tile,pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        beforeDisplaying(pkTeam_);
        return trainer_.getBeans().get(0);
    }
    public static PokemonTeamBean displayLeague(int _level) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        TrainerBean trainer_ = transitTrainer(8,_level,12,pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        beforeDisplaying(pkTeam_);
        return trainer_.getBeans().get(0);
    }
    public static PokemonTeamBean displayMult(int _no) {
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        TrainerBean trainer_ = transitTrainer(3,1,7,pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        callPokemonTeamBeanNoFightSet(pkTeam_,_no);
//        beforeDisplaying(pkTeam_);
        return trainer_.getBeans().get(_no);
    }
    private static DualFightBean transitDual(FacadeGame _pk, StringMap<BeanRenderWithAppName> _all) {
        MapLevelBean bean_ = transitLevel(3, 0, _pk, _all);
        DualFightBean tr_ = (DualFightBean) _all.getVal(InitDbMap.BEAN_DUAL);
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)bean(_all, CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML),8),bean_,tr_);
        return tr_;
    }

    public static String callAllyBeanClickAbility(int _pk) {
        return callAllyBeanClickAbility(displayAlly(),_pk);
    }

    public static String callAllyBeanClickAbility(AllyBean _str, int _pk) {
        return _str.clickAbility(_pk);
    }

    public static String callAllyBeanClickAbilityId(int _pk) {
        AllyBean bean_ = displayAlly();
        callAllyBeanClickAbility(bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAllyBeanClickItem(int _pk) {
        return callAllyBeanClickItem(displayAlly(),_pk);
    }

    public static String callAllyBeanClickItem(AllyBean _str, int _pk) {
        return _str.clickItem(_pk);
    }

    public static String callAllyBeanClickItemId(int _pk) {
        AllyBean bean_ = displayAlly();
        callAllyBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAllyBeanClickMove(int _pk, int _move) {
        return callAllyBeanClickMove(displayAlly(),_pk,_move);
    }

    public static String callAllyBeanClickMove(AllyBean _str, int _pk, int _move) {
        return _str.clickMove(_pk,_move);
    }

    public static String callAllyBeanClickMoveId(int _pk, int _move) {
        AllyBean bean_ = displayAlly();
        callAllyBeanClickMove(bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAllyBeanClickName(int _pk) {
        return callAllyBeanClickName(displayAlly(),_pk);
    }

    public static String callAllyBeanClickName(AllyBean _str, int _pk) {
        return _str.clickName(_pk);
    }

    public static String callAllyBeanClickNameId(int _pk) {
        AllyBean bean_ = displayAlly();
        callAllyBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static String callAllyBeanGetAbility(int _pk) {
        return displayAlly().getAbility(_pk);
    }

    public static int[][] callAllyBeanGetImage(int _pk) {
        return displayAlly().getImage(_pk);
    }

    public static String callAllyBeanGetGender() {
        return displayAlly().getGender(0);
    }

    public static String callAllyBeanGetItem(int _pk) {
        return displayAlly().getItem(_pk);
    }

    public static String callAllyBeanGetMove(int _pk, int _move) {
        return displayAlly().getMove(_pk,_move);
    }

    public static String callAllyBeanGetName(int _pk) {
        return displayAlly().getName(_pk);
    }

    public static CustList<TranslatedPkElements> callAllyBeanTeamGet() {
        return displayAlly().getTeam();
    }

//    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }

    public static int[][] callDualFightBeanImageGet() {
        return displayDual().getImage();
    }

    public static int[][] callDualFightBeanImageMiniGet() {
        return displayDual().getImageMini();
    }

    public static int[][] callDualFightBeanImageMiniSecondGet() {
        return displayDual().getImageMiniSecond();
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

    public static String callTrainerBeanClickMove(TrainerBean _str) {
        return _str.clickMove();
    }

    public static String callTrainerBeanClickMoveId(int _place, int _tile, int _second) {
        TrainerBean bean_ = displayTrainerLevelZero(_place, _tile, _second);
        callTrainerBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callTrainerBeanGetName(TrainerBean _str) {
        return _str.getName();
    }

    public static String callTrainerBeanGetNameLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetName(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static String callTrainerBeanGetNameOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetName(displayTrainer(_place, _level, _tile));
    }

    public static int callTrainerBeanGetTeamsRewardsLevelZero(int _place, int _tile, int _second) {
        return displayTrainerLevelZero(_place, _tile, _second).getTeamsRewards().size();
    }

    public static int callTrainerBeanGetTeamsRewardsOtherLevel(int _place, int _level, int _tile) {
        return displayTrainer(_place, _level, _tile).getTeamsRewards().size();
    }

    public static String callTrainerBeanGetTrMove(int _place, int _tile, int _second) {
        return displayTrainerLevelZero(_place, _tile, _second).getTrMove();
    }

    public static int[][] callTrainerBeanImageGet(TrainerBean _str) {
        return _str.getImage();
    }

    public static int[][] callTrainerBeanImageGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static int[][] callTrainerBeanImageGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageGet(displayTrainer(_place, _level, _tile));
    }

    public static int[][] callTrainerBeanImageMiniGet(TrainerBean _str, int... _args) {
        return _str.getImageMini();
    }

    public static int[][] callTrainerBeanImageMiniGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageMiniGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static int[][] callTrainerBeanImageMiniGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageMiniGet(displayTrainer(_place, _level, _tile));
    }

    public static String callTrainerBeanMoveGet(TrainerBean _str) {
        return _str.getMove();
    }

    public static String callTrainerBeanMoveGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanMoveGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static String callTrainerBeanMoveGetOtherLevel(int _place, int _level, int _tile) {
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

    public static String callPokemonTeamBeanClickAbility(PokemonTeamBean _str, int _pk) {
        return _str.clickAbility(callPokemonTeamBeanNoFightGet(_str),_pk);
    }

    public static String callPokemonTeamBeanClickAbilityId(int _no, int _pk) {
        PokemonTeamBean bean_ = displayMult(_no);
        callPokemonTeamBeanClickAbility(bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callPokemonTeamBeanClickItem(int _no, int _pk) {
        return callPokemonTeamBeanClickItem(displayMult(_no),_pk);
    }

    public static String callPokemonTeamBeanClickItem(PokemonTeamBean _str, int _pk) {
        return _str.clickItem(callPokemonTeamBeanNoFightGet(_str),_pk);
    }

    public static String callPokemonTeamBeanClickItemId(int _no, int _pk) {
        PokemonTeamBean bean_ = displayMult(_no);
        callPokemonTeamBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callPokemonTeamBeanClickMove(int _no, int _pk, int _move) {
        return callPokemonTeamBeanClickMove(displayMult(_no),_pk,_move);
    }

    public static String callPokemonTeamBeanClickMove(PokemonTeamBean _str, int _pk, int _move) {
        return _str.clickMove(callPokemonTeamBeanNoFightGet(_str),_pk,_move);
    }

    public static String callPokemonTeamBeanClickMoveId(int _no, int _pk, int _move) {
        PokemonTeamBean bean_ = displayMult(_no);
        callPokemonTeamBeanClickMove(bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callPokemonTeamBeanClickName(int _no, int _pk) {
        return callPokemonTeamBeanClickName(displayMult(_no),_pk);
    }

    public static String callPokemonTeamBeanClickName(PokemonTeamBean _str, int _pk) {
        return _str.clickName(callPokemonTeamBeanNoFightGet(_str),_pk);
    }

    public static String callPokemonTeamBeanClickNameId(int _no, int _pk) {
        PokemonTeamBean bean_ = displayMult(_no);
        callPokemonTeamBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static String callPokemonTeamBeanGetAbility(int _no, int _pk) {
        return displayMult(_no).getAbility(_pk);
    }

    public static int[][] callPokemonTeamBeanGetImageTempTrainer(int _pk) {
        return displayTempTrainer().getImage(_pk);
    }

    public static int[][] callPokemonTeamBeanGetImageGymTrainer(int _pk) {
        return displayGymTrainer().getImage(_pk);
    }

    public static int[][] callPokemonTeamBeanGetImageGymLeader(int _pk) {
        return displayGymLeader().getImage(_pk);
    }

    public static int[][] callPokemonTeamBeanGetImageTrainerLeague(int _level,int _pk) {
        return displayLeague(_level).getImage(_pk);
    }

    public static int[][] callPokemonTeamBeanGetImageMulti(int _no,int _pk) {
        return displayMult(_no).getImage(_pk);
    }

    public static String callPokemonTeamBeanGetGender() {
        return displayTempTrainer().getGender(0);
    }
    public static String callPokemonTeamBeanGetItem(int _no, int _pk) {
        return displayMult(_no).getItem(_pk);
    }

    public static String callPokemonTeamBeanGetMove(int _no, int _pk, int _move) {
        return displayMult(_no).getMove(_pk,_move);
    }

    public static String callPokemonTeamBeanGetName(int _no, int _pk) {
        return displayMult(_no).getName(_pk);
    }

    public static long callPokemonTeamBeanMultiplicityGetMult() {
        return displayMult(0).getMultiplicity();
    }

    public static long callPokemonTeamBeanMultiplicityGet() {
        return displayGymLeader().getMultiplicity();
    }

    public static int callPokemonTeamBeanNoFightGet(PokemonTeamBean _str) {
        return _str.getNoFight();
    }

    public static long callPokemonTeamBeanRewardGet(int _no) {
        return displayMult(_no).getReward();
    }

    public static long callPokemonTeamBeanRewardGet() {
        return displayTempTrainer().getReward();
    }

    public static CustList<TranslatedPkElements> callPokemonTeamBeanTeamGetTempTrainer() {
        return displayTempTrainer().getTeam();
    }

    public static CustList<TranslatedPkElements> callPokemonTeamBeanTeamGetGymTrainer() {
        return displayGymTrainer().getTeam();
    }

    public static CustList<TranslatedPkElements> callPokemonTeamBeanTeamGetGymLeader() {
        return displayGymLeader().getTeam();
    }

    public static CustList<TranslatedPkElements> callPokemonTeamBeanTeamGetTrainerLeague(int _level) {
        return displayLeague(_level).getTeam();
    }

    public static CustList<TranslatedPkElements> callPokemonTeamBeanTeamGetMulti(int _no) {
        return displayMult(_no).getTeam();
    }

//    public static NaSt callPokemonTeamBeanNoFightSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new PokemonTeamBeanNoFightSet(),_str,_args);
//    }
//    public static void fwdTrainerDual(NaSt _update, NaSt _use) {
//        callPokemonTeamBeanTrainerSet(_update,callDualFightBeanTrainerGet(_use));
//    }
//
//    public static void fwdTrainer(NaSt _update, NaSt _use) {
//        callPokemonTeamBeanTrainerSet(_update,callTrainerBeanTrainerGet(_use));
//    }
//    public static void fwdAlly(NaSt _update, NaSt _use) {
//        callAllyBeanAllySet(_update,callDualFightBeanAllyGet(_use));
//    }
//    public static NaSt callDualFightBeanAllyGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }
//
//    public static NaSt callAllyBeanAllySet(NaSt _str, NaSt _args) {
//        return BeanPokemonCommonTs.callStruct(new AllyBeanAllySet(),_str,_args);
//    }
//    public static NaSt callDualFightBeanTrainerGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanTrainerGet(),_str,_args);
//    }
//    public static NaSt callTrainerBeanTrainerGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new TrainerBeanTrainerGet(),_str,_args);
//    }
//    public static NaSt callPokemonTeamBeanTrainerSet(NaSt _str, NaSt _args) {
//        return BeanPokemonCommonTs.callStruct(new PokemonTeamBeanTrainerSet(),_str,_args);
//    }
}
