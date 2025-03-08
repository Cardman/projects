package aiki.beans.game;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import code.maths.LgInt;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;

public abstract class InitDbGameProgressionBean extends InitDbBean {
    public static StringList callGameProgressionBeanGetKeyPokemon(GameProgressionBean _str, int... _args) {
        return _str.getKeyPokemon(_args[0],_args[1]);
    }

    public static int[][] callGameProgressionBeanGetImagePokemonFull(GameProgressionBean _str, int... _args) {
        return _str.getImagePokemonFull(_args[0],_args[1],_args[2]);
    }

    public static String callGameProgressionBeanGetTrPokemonFull(GameProgressionBean _str, int... _args) {
        return _str.getTrPokemonFull(_args[0],_args[1],_args[2]);
    }

    public static int[][] callGameProgressionBeanGetImagePokemonNotAll(GameProgressionBean _str, int... _args) {
        return _str.getImagePokemonNotAll(_args[0],_args[1],_args[2]);
    }

    public static String callGameProgressionBeanGetTrPokemonNotAll(GameProgressionBean _str, int... _args) {
        return _str.getTrPokemonNotAll(_args[0],_args[1],_args[2]);
    }

    public static int[][]  callGameProgressionBeanGetImagePokemonPartialNot(GameProgressionBean _str, int... _args) {
        return _str.getImagePokemonPartialNot(_args[0],_args[1],_args[2]);
    }

    public static String callGameProgressionBeanGetTrPokemonPartialNot(GameProgressionBean _str, int... _args) {
        return _str.getTrPokemonPartialNot(_args[0],_args[1],_args[2]);
    }

    public static int[][] callGameProgressionBeanGetImagePokemonPartial(GameProgressionBean _str, int... _args) {
        return _str.getImagePokemonPartial(_args[0],_args[1],_args[2]);
    }

    public static String callGameProgressionBeanGetTrPokemonPartial(GameProgressionBean _str, int... _args) {
        return _str.getTrPokemonPartial(_args[0],_args[1],_args[2]);
    }

    public static String callGameProgressionBeanGetRemainingOtherTrainersPlaceName(GameProgressionBean _str, int... _args) {
        return _str.getRemainingOtherTrainersPlaceName(_args[0]);
    }

    public static DictionaryComparator<Integer,PlaceNamePk> callGameProgressionBeanRemainingOtherTrainerPlacesGet(GameProgressionBean _str, int... _args) {
        return _str.getRemainingOtherTrainerPlaces();
    }
    public static EntryCust<Integer,PlaceNamePk> eltPlaceNamePk(DictionaryComparator<Integer,PlaceNamePk> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static int firstPlaceNamePk(EntryCust<Integer,PlaceNamePk> _ls) {
        return _ls.getKey();
    }
    public static int secondPlaceNamePk(EntryCust<Integer,PlaceNamePk> _ls) {
        return _ls.getValue().getIndex();
    }
    public static CustList<TrainerPlaceNames> callGameProgressionBeanBeatenImportantTrainersGet(GameProgressionBean _str, int... _args) {
        return _str.getBeatenImportantTrainers();
    }

    public static CustList<TrainerPlaceNames> callGameProgressionBeanUnBeatenImportantTrainersGet(GameProgressionBean _str, int... _args) {
        return _str.getUnBeatenImportantTrainers();

    }
    public static TrainerPlaceNames eltTr(CustList<TrainerPlaceNames> _ls, int _i) {
        return _ls.get(_i);
    }
    public static NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> callGameProgressionBeanFullFamiliesBaseGet(GameProgressionBean _str, int... _args) {
        return _str.getFullFamiliesBase();
    }

    public static NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> callGameProgressionBeanNotAtAllFamiliesBaseGet(GameProgressionBean _str, int... _args) {
        return _str.getNotAtAllFamiliesBase();
    }

    public static NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(GameProgressionBean _str, int... _args) {
        return _str.getPartialFamiliesBaseNotCaught();
    }
    public static EntryCust<String,CustList<CustList<ImgPkPlayer>>> eltFam(NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstFam(EntryCust<String,CustList<CustList<ImgPkPlayer>>> _ls) {
        return _ls.getKey();
    }
    public static CustList<CustList<ImgPkPlayer>> secondFam(EntryCust<String,CustList<CustList<ImgPkPlayer>>> _ls) {
        return _ls.getValue();
    }
    public static StringList eltFam2(CustList<CustList<ImgPkPlayer>> _ls, int _i) {
        return GameProgressionBean.map(_ls.get(_i));
    }
    public static StringList callGameProgressionBeanUnVisitedPlacesGet(GameProgressionBean _str, int... _args) {
        return _str.getUnVisitedPlaces();
    }

    public static StringList callGameProgressionBeanVisitedPlacesGet(GameProgressionBean _str, int... _args) {
        return _str.getVisitedPlaces();
    }

    public static long callGameProgressionBeanNbRemainingNotMaxLevelGet(GameProgressionBean _str, int... _args) {
        return _str.getNbRemainingNotMaxLevel();
    }

    public static long callGameProgressionBeanNbRemainingNotMaxHappinessGet(GameProgressionBean _str, int... _args) {
        return _str.getNbRemainingNotMaxHappiness();
    }

    public static long callGameProgressionBeanNbRemainingEggsGet(GameProgressionBean _str, int... _args) {
        return _str.getNbRemainingEggs();
    }

    public static LgInt callGameProgressionBeanMoneyGet(GameProgressionBean _str, int... _args) {
        return _str.getMoney();
    }

    public static long callGameProgressionBeanRemainStepsRepelGet(GameProgressionBean _str, int... _args) {
        return _str.getRemainStepsRepel();
    }

    public static int[][] callGameProgressionBeanEndGameImageGet(GameProgressionBean _str, int... _args) {
        return _str.getEndGameImage();
    }

    public static boolean callGameProgressionBeanFinishedGameGet(GameProgressionBean _str, int... _args) {
        return CommonBean.toBool(_str.getFinishedGame());
    }

    public static int[][] callGameProgressionBeanHeroImageOppositeSexGet(GameProgressionBean _str, int... _args) {
        return _str.getHeroImageOppositeSex();
    }

    public static int[][] callGameProgressionBeanHeroImageGet(GameProgressionBean _str, int... _args) {
        return _str.getHeroImage();
    }

    public static String callGameProgressionBeanNicknameGet(GameProgressionBean _str, int... _args) {
        return _str.getNickname();
    }

    public static GameProgressionBean beanProg(String _language, FacadeGame _dataBase) {
        GameProgressionBean b_ = new GameProgressionBean();
        b_.setDataBase(_dataBase);
        b_.setLanguage(_language);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.en());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.fr());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_dataBase);
        b_.setBuilder(bu_);
        b_.build(_dataBase);
        return b_;
    }

    public static String callTrainerPlaceNamesGetPlace(TrainerPlaceNames _str, int... _args) {
        return _str.getPlace();
    }

    public static String callTrainerPlaceNamesGetTrainer(TrainerPlaceNames _str, int... _args) {
        return _str.getTrainer();
    }
}
