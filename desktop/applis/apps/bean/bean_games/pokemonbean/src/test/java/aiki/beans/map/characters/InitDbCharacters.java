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
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_tile),bean_,bean_);
        NaSt tr_ = _all.getVal(InitDbMap.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_second),bean_,tr_);
        return tr_;
    }

    public static NaSt displaySellerLevelZero(int _place, int _tile, int _second) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        NaSt bean_ = transitLevelZero(_place,pk_,all_);
        transit(pk_,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_tile),bean_,bean_);
        NaSt tr_ = all_.getVal(InitDbMap.BEAN_SELLER);
        transit(pk_,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_second),bean_,tr_);
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
        NaSt tr_ = _all.getVal(InitDbMap.BEAN_TRAINER_FIGHT);
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_tile),bean_,tr_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }

    public static NaSt displayDealer(int _place, int _level, int _tile) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
//        StringMap<String> map_ = mappingToMap();
        NaSt bean_ = transitLevel(_place, _level,pk_,all_);
        NaSt tr_ = all_.getVal(InitDbMap.BEAN_DEALER);
        transit(pk_,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_tile),bean_,tr_);
//        Struct tr_ = byStr(all_, map_, callMapLevelBeanClickTileOnMapStruct(bean_, _tile));
//        beforeDisplaying(tr_);
        return tr_;
    }
    public static String callDealerBeanClickItem() {
        return callDealerBeanClickItem(displayDealer(3,1,12),0);
    }
    public static String callDealerBeanClickItem(NaSt _str, int _item) {
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(_item)).getInstance();
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
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)_str).getInstance()).clickTm(_tm)).getInstance();
    }

    public static String callDealerBeanClickTmId() {
        NaSt bean_ = displayDealer(3, 1, 17);
        callDealerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static NaSt callDealerBeanGetAllTm() {
        return PokemonStandards.getKeys(( (DealerBean) ((PokemonBeanStruct)displayDealer(3,1,17)).getInstance()).getAllTmDealer());
    }

    public static NaSt callDealerBeanGetItem() {
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)displayDealer(3,1,12)).getInstance()).getItem(0));
    }

    public static NaSt callDealerBeanGetItems() {
        return PokemonStandards.getKeys(( (DealerBean) ((PokemonBeanStruct)displayDealer(3,1,12)).getInstance()).getItemsDealer());
    }

    public static NaSt callDealerBeanGetTm() {
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)displayDealer(3,1,17)).getInstance()).getTmDealer(0));
    }


    public static String callSellerBeanClickItem() {
        return callSellerBeanClickItem(displaySellerLevelZero(1,12,13),0);
    }

    public static String callSellerBeanClickItem(NaSt _str, int _item) {
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(_item)).getInstance();
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
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)_str).getInstance()).clickTm(_tm)).getInstance();
    }

    public static String callSellerBeanClickTmId() {
        NaSt bean_ = displaySellerLevelZero(1, 12, 18);
        callSellerBeanClickTm(bean_,0);
        return getValMoveId(bean_);
    }

    public static NaSt callSellerBeanGetAllTm() {
        return PokemonStandards.getKeys(( (SellerBean) ((PokemonBeanStruct)displaySellerLevelZero(1,12,18)).getInstance()).getAllTm());
    }

    public static NaSt callSellerBeanGetItem() {
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)displaySellerLevelZero(1,12,13)).getInstance()).getItem(0));
    }

    public static NaSt callSellerBeanGetItems() {
        return PokemonStandards.getKeys(( (SellerBean) ((PokemonBeanStruct)displaySellerLevelZero(1,12,13)).getInstance()).getItems());
    }

    public static NaSt callSellerBeanGetTm() {
        return new NaStSt(( (SellerBean) ((PokemonBeanStruct)displaySellerLevelZero(1,12,18)).getInstance()).getTm(0));
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
//        NaSt ally_ = new PokemonBeanStruct(new AllyBean());
//        fwdAlly(ally_,dual_);
//        beforeDisplaying(ally_);
        return new PokemonBeanStruct(((DualFightBean)((PokemonBeanStruct)dual_).getBean()).getAllyTeam());
    }

    public static NaSt displayTempTrainer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt dual_ = transitDual(pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainerDual(pkTeam_,dual_);
//        beforeDisplaying(pkTeam_);
        return new PokemonBeanStruct(((DualFightBean)((PokemonBeanStruct)dual_).getBean()).getFoeTeam());
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
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        beforeDisplaying(pkTeam_);
        return new PokemonBeanStruct(((TrainerBean)((PokemonBeanStruct)trainer_).getBean()).getBeans().get(0));
    }
    public static NaSt displayLeague(int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt trainer_ = transitTrainer(8,_level,12,pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        beforeDisplaying(pkTeam_);
        return new PokemonBeanStruct(((TrainerBean)((PokemonBeanStruct)trainer_).getBean()).getBeans().get(0));
    }
    public static NaSt displayMult(int _no) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        NaSt trainer_ = transitTrainer(3,1,7,pk_, all_);
//        NaSt pkTeam_ = new PokemonBeanStruct(new PokemonTeamBean());
//        fwdTrainer(pkTeam_,trainer_);
//        callPokemonTeamBeanNoFightSet(pkTeam_,_no);
//        beforeDisplaying(pkTeam_);
        return new PokemonBeanStruct(((TrainerBean)((PokemonBeanStruct)trainer_).getBean()).getBeans().get(_no));
    }
    private static NaSt transitDual(PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevel(3, 0, _pk, _all);
        NaSt tr_ = _all.getVal(InitDbMap.BEAN_DUAL);
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)bean(_all, CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML),8),bean_,tr_);
        return tr_;
    }

    public static String callAllyBeanClickAbility(int _pk) {
        return callAllyBeanClickAbility(displayAlly(),_pk);
    }

    public static String callAllyBeanClickAbility(NaSt _str, int _pk) {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_str).getInstance()).clickAbility(_pk)).getInstance();
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
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(_pk)).getInstance();
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
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_str).getInstance()).clickMove(_pk,_move)).getInstance();
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
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_str).getInstance()).clickName(_pk)).getInstance();
    }

    public static String callAllyBeanClickNameId(int _pk) {
        NaSt bean_ = displayAlly();
        callAllyBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static NaSt callAllyBeanGetAbility(int _pk) {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getAbility(_pk));
    }

    public static NaSt callAllyBeanGetImage(int _pk) {
        return new NaImgSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getImage(_pk));
    }

    public static NaSt callAllyBeanGetGender() {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getGender(0));
    }

    public static NaSt callAllyBeanGetItem(int _pk) {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getItem(_pk));
    }

    public static NaSt callAllyBeanGetMove(int _pk, int _move) {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getMove(_pk,_move));
    }

    public static NaSt callAllyBeanGetName(int _pk) {
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getName(_pk));
    }

    public static NaSt callAllyBeanTeamGet() {
        return PokemonStandards.getPkTrainerArray(( (AllyBean) ((PokemonBeanStruct)displayAlly()).getInstance()).getTeam());
    }

//    public static Struct callDualFightBeanAllyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DualFightBeanAllyGet(),_str,_args);
//    }

    public static NaSt callDualFightBeanImageGet() {
        return new NaImgSt(( (DualFightBean) ((PokemonBeanStruct)displayDual()).getInstance()).getImage());
    }

    public static NaSt callDualFightBeanImageMiniGet() {
        return new NaImgSt(( (DualFightBean) ((PokemonBeanStruct)displayDual()).getInstance()).getImageMini());
    }

    public static NaSt callDualFightBeanImageMiniSecondGet() {
        return new NaImgSt(( (DualFightBean) ((PokemonBeanStruct)displayDual()).getInstance()).getImageMiniSecond());
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
        return new NaStSt(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).clickMove()).getInstance();
    }

    public static String callTrainerBeanClickMoveId(int _place, int _tile, int _second) {
        NaSt bean_ = displayTrainerLevelZero(_place, _tile, _second);
        callTrainerBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static NaSt callTrainerBeanGetName(NaSt _str) {
        return new NaStSt(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).getName());
    }

    public static NaSt callTrainerBeanGetNameLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetName(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanGetNameOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetName(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanGetTeamsRewards(NaSt _str) {
        return PokemonStandards.arrId(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).getTeamsRewards().size());
    }

    public static NaSt callTrainerBeanGetTeamsRewardsLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanGetTeamsRewards(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanGetTeamsRewardsOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanGetTeamsRewards(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanGetTrMove(int _place, int _tile, int _second) {
        return new NaStSt(( (TrainerBean) ((PokemonBeanStruct)displayTrainerLevelZero(_place, _tile, _second)).getInstance()).getTrMove());
    }

    public static NaSt callTrainerBeanImageGet(NaSt _str) {
        return new NaImgSt(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).getImage());
    }

    public static NaSt callTrainerBeanImageGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanImageGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageGet(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanImageMiniGet(NaSt _str, int... _args) {
        return new NaImgSt(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).getImageMini());
    }

    public static NaSt callTrainerBeanImageMiniGetLevelZero(int _place, int _tile, int _second) {
        return callTrainerBeanImageMiniGet(displayTrainerLevelZero(_place, _tile, _second));
    }

    public static NaSt callTrainerBeanImageMiniGetOtherLevel(int _place, int _level, int _tile) {
        return callTrainerBeanImageMiniGet(displayTrainer(_place, _level, _tile));
    }

    public static NaSt callTrainerBeanMoveGet(NaSt _str) {
        return new NaStSt(( (TrainerBean) ((PokemonBeanStruct)_str).getInstance()).getMove());
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
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickAbility(toInt(callPokemonTeamBeanNoFightGet(_str)),_pk)).getInstance();
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
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(toInt(callPokemonTeamBeanNoFightGet(_str)),_pk)).getInstance();
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
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickMove(toInt(callPokemonTeamBeanNoFightGet(_str)),_pk,_move)).getInstance();
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
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)_str).getInstance()).clickName(toInt(callPokemonTeamBeanNoFightGet(_str)),_pk)).getInstance();
    }

    public static String callPokemonTeamBeanClickNameId(int _no, int _pk) {
        NaSt bean_ = displayMult(_no);
        callPokemonTeamBeanClickName(bean_,_pk);
        return getValPkId(bean_);
    }

    public static NaSt callPokemonTeamBeanGetAbility(int _no, int _pk) {
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getAbility(_pk));
    }

    public static NaSt callPokemonTeamBeanGetImageTempTrainer(int _pk) {
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayTempTrainer()).getInstance()).getImage(_pk));
    }

    public static NaSt callPokemonTeamBeanGetImageGymTrainer(int _pk) {
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayGymTrainer()).getInstance()).getImage(_pk));
    }

    public static NaSt callPokemonTeamBeanGetImageGymLeader(int _pk) {
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayGymLeader()).getInstance()).getImage(_pk));
    }

    public static NaSt callPokemonTeamBeanGetImageTrainerLeague(int _level,int _pk) {
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayLeague(_level)).getInstance()).getImage(_pk));
    }

    public static NaSt callPokemonTeamBeanGetImageMulti(int _no,int _pk) {
        return new NaImgSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getImage(_pk));
    }

    public static NaSt callPokemonTeamBeanGetGender() {
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayTempTrainer()).getInstance()).getGender(0));
    }
    public static NaSt callPokemonTeamBeanGetItem(int _no, int _pk) {
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getItem(_pk));
    }

    public static NaSt callPokemonTeamBeanGetMove(int _no, int _pk, int _move) {
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getMove(_pk,_move));
    }

    public static NaSt callPokemonTeamBeanGetName(int _no, int _pk) {
        return new NaStSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getName(_pk));
    }

    public static NaSt callPokemonTeamBeanMultiplicityGetMult() {
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(0)).getInstance()).getMultiplicity());
    }

    public static NaSt callPokemonTeamBeanMultiplicityGet() {
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayGymLeader()).getInstance()).getMultiplicity());
    }

    public static NaSt callPokemonTeamBeanNoFightGet(NaSt _str) {
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)_str).getInstance()).getNoFight());
    }

    public static NaSt callPokemonTeamBeanRewardGet(int _no) {
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getReward());
    }

    public static NaSt callPokemonTeamBeanRewardGet() {
        return new NaNbSt(( (PokemonTeamBean) ((PokemonBeanStruct)displayTempTrainer()).getInstance()).getReward());
    }

    public static NaSt callPokemonTeamBeanTeamGetTempTrainer() {
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)displayTempTrainer()).getInstance()).getTeam());
    }

    public static NaSt callPokemonTeamBeanTeamGetGymTrainer() {
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)displayGymTrainer()).getInstance()).getTeam());
    }

    public static NaSt callPokemonTeamBeanTeamGetGymLeader() {
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)displayGymLeader()).getInstance()).getTeam());
    }

    public static NaSt callPokemonTeamBeanTeamGetTrainerLeague(int _level) {
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)displayLeague(_level)).getInstance()).getTeam());
    }

    public static NaSt callPokemonTeamBeanTeamGetMulti(int _no) {
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)displayMult(_no)).getInstance()).getTeam());
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
