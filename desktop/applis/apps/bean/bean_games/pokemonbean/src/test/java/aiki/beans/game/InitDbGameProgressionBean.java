package aiki.beans.game;

import aiki.beans.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import code.bean.nat.*;

public abstract class InitDbGameProgressionBean extends InitDbBean {
    public static NaSt callGameProgressionBeanGetKeyPokemon(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetKeyPokemon(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetImagePokemonFull(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetImagePokemonFull(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetTrPokemonFull(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetTrPokemonFull(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetImagePokemonNotAll(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetImagePokemonNotAll(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetTrPokemonNotAll(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetTrPokemonNotAll(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetImagePokemonPartialNot(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetImagePokemonPartialNot(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetTrPokemonPartialNot(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetTrPokemonPartialNot(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetImagePokemonPartial(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetImagePokemonPartial(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetTrPokemonPartial(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetTrPokemonPartial(),_str,_args);
    }

    public static NaSt callGameProgressionBeanGetRemainingOtherTrainersPlaceName(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanGetRemainingOtherTrainersPlaceName(),_str,_args);
    }

    public static NaSt callGameProgressionBeanRemainingOtherTrainerPlacesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanRemainingOtherTrainerPlacesGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanBeatenImportantTrainersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanBeatenImportantTrainersGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanUnBeatenImportantTrainersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanUnBeatenImportantTrainersGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanFullFamiliesBaseGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanFullFamiliesBaseGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanNotAtAllFamiliesBaseGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanNotAtAllFamiliesBaseGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanPartialFamiliesBaseNotCaughtGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanUnVisitedPlacesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanUnVisitedPlacesGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanVisitedPlacesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanVisitedPlacesGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanNbRemainingNotMaxLevelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanNbRemainingNotMaxLevelGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanNbRemainingNotMaxHappinessGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanNbRemainingNotMaxHappinessGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanNbRemainingEggsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanNbRemainingEggsGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanMoneyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanMoneyGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanRemainStepsRepelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanRemainStepsRepelGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanEndGameImageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanEndGameImageGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanFinishedGameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanFinishedGameGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanHeroImageOppositeSexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanHeroImageOppositeSexGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanHeroImageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanHeroImageGet(),_str,_args);
    }

    public static NaSt callGameProgressionBeanNicknameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GameProgressionBeanNicknameGet(),_str,_args);
    }

    public static NaSt beanProg(String _language, FacadeGame _dataBase) {
        PkProg stds_ = new PkProg();
        stds_.setDataBase(_dataBase);
//        stds_.setBaseEncode(BASE);
        return stds_.initProg(_language);
    }

    public static NaSt callTrainerPlaceNamesGetPlace(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerPlaceNamesGetPlace(),_str,_args);
    }

    public static NaSt callTrainerPlaceNamesGetTrainer(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new TrainerPlaceNamesGetTrainer(),_str,_args);
    }
}
