package aiki.beans.game;
import code.bean.Accessible;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.beans.facade.comparators.ComparatorPlaceNumber;
import aiki.comparators.ComparatorPairStringNumber;
import aiki.comparators.ComparatorTrStrings;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.GameProgression;
import aiki.map.DataMap;

public class GameProgressionBean extends Bean {

    @Accessible
    private String heroImage;

    @Accessible
    private String heroImageOppositeSex;

    @Accessible
    private String nickname;

    @Accessible
    private boolean finishedGame;

    @Accessible
    private String endGameImage;

    @Accessible
    private NatTreeMap<String,EqList<StringList>> notAtAllFamiliesBase;

    @Accessible
    private NatTreeMap<String,EqList<StringList>> partialFamiliesBaseNotCaught;

    @Accessible
    private NatTreeMap<String,EqList<StringList>> partialFamiliesBaseCaught;

    @Accessible
    private NatTreeMap<String,EqList<StringList>> fullFamiliesBase;

    @Accessible
    private EqList<TrainerPlaceNames> beatenImportantTrainers;

    @Accessible
    private EqList<TrainerPlaceNames> unBeatenImportantTrainers;

    @Accessible
    private TreeMap<Short,Integer> remainingOtherTrainerPlaces;

    @Accessible
    private StringList visitedPlaces;

    @Accessible
    private StringList unVisitedPlaces;

    @Accessible
    private LgInt money;

    @Accessible
    private int remainStepsRepel;

    @Accessible
    private int nbRemainingEggs;

    @Accessible
    private int nbRemainingNotMaxLevel;

    @Accessible
    private int nbRemainingNotMaxHappiness;

    @Override
    public void beforeDisplaying() {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        StringMap<String> tr_ = facade_.getData().getTranslatedPokemon().getVal(getLanguage());
        heroImage = ConverterBufferedImage.surroundImage(facade_.getFrontChosenHeros());
        heroImageOppositeSex = ConverterBufferedImage.surroundImage(facade_.getFrontChosenHerosOppositeSex());
        GameProgression progression_ = facade_.getGameProgression();
        finishedGame = progression_.isFinishedGame();
        endGameImage = ConverterBufferedImage.surroundImage(facade_.getData().getEndGameImage());
        nickname = progression_.getNickname();
        notAtAllFamiliesBase = new NatTreeMap<String,EqList<StringList>>();
        for (String b: progression_.getNotAtAllFamiliesBase().getKeys()) {
            EqList<StringList> lists_ = new EqList<StringList>();
            for (StringList l: progression_.getNotAtAllFamiliesBase().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(new ComparatorTrStrings(tr_));
                lists_.add(list_);
            }
            notAtAllFamiliesBase.put(facade_.translatePokemon(b), lists_);
        }
        partialFamiliesBaseCaught = new NatTreeMap<String,EqList<StringList>>();
        for (String b: progression_.getPartialFamiliesBaseCaught().getKeys()) {
            EqList<StringList> lists_ = new EqList<StringList>();
            for (StringList l: progression_.getPartialFamiliesBaseCaught().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(new ComparatorTrStrings(tr_));
                lists_.add(list_);
            }
            partialFamiliesBaseCaught.put(facade_.translatePokemon(b), lists_);
        }
        partialFamiliesBaseNotCaught = new NatTreeMap<String,EqList<StringList>>();
        for (String b: progression_.getPartialFamiliesBaseNotCaught().getKeys()) {
            EqList<StringList> lists_ = new EqList<StringList>();
            for (StringList l: progression_.getPartialFamiliesBaseNotCaught().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(new ComparatorTrStrings(tr_));
                lists_.add(list_);
            }
            partialFamiliesBaseNotCaught.put(facade_.translatePokemon(b), lists_);
        }
        fullFamiliesBase = new NatTreeMap<String,EqList<StringList>>();
        for (String b: progression_.getFullFamiliesBase().getKeys()) {
            EqList<StringList> lists_ = new EqList<StringList>();
            for (StringList l: progression_.getFullFamiliesBase().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(new ComparatorTrStrings(tr_));
                lists_.add(list_);
            }
            fullFamiliesBase.put(facade_.translatePokemon(b), lists_);
        }
        unBeatenImportantTrainers = progression_.getUnBeatenImportantTrainers();
        unBeatenImportantTrainers.sortElts(new ComparatorPairStringNumber());
        beatenImportantTrainers = progression_.getBeatenImportantTrainers();
        beatenImportantTrainers.sortElts(new ComparatorPairStringNumber());
        remainingOtherTrainerPlaces = new TreeMap<Short, Integer>(new ComparatorPlaceNumber(facade_.getMap()));
        remainingOtherTrainerPlaces.putAllMap(progression_.getRemainingOtherTrainerPlaces());
        visitedPlaces = progression_.getVisitedPlaces();
        visitedPlaces.sort();
        unVisitedPlaces = progression_.getUnVisitedPlaces();
        unVisitedPlaces.sort();
        money = new LgInt(progression_.getMoney());
        remainStepsRepel = progression_.getRemainStepsRepel();
        nbRemainingEggs = progression_.getNbRemainingEggs();
        nbRemainingNotMaxHappiness = progression_.getNbRemainingNotMaxHappiness();
        nbRemainingNotMaxLevel = progression_.getNbRemainingNotMaxLevel();
    }

    @Accessible
    private String getRemainingOtherTrainersPlaceName(Long _index) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        short key_ = remainingOtherTrainerPlaces.getKey(_index.intValue());
        DataMap dataMap_ = facade_.getMap();
        return dataMap_.getPlaces().getVal(key_).getName();
    }

    @Accessible
    private String getTrPokemonNotAll(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getImagePokemonNotAll(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getTrPokemonPartialNot(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getImagePokemonPartialNot(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getTrPokemonPartial(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getImagePokemonPartial(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getTrPokemonFull(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }

    @Accessible
    private String getImagePokemonFull(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }

    @Accessible
    private StringList getKeyPokemon(Long _key, Long _indexList) {
        EqList<StringList> values_ = partialFamiliesBaseCaught.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        return value_;
    }

    private static String getTrPokemon(FacadeGame _facade,NatTreeMap<String,EqList<StringList>> _treeMap, Long _key, Long _indexList, Long _indexElt) {
        EqList<StringList> values_ = _treeMap.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        String pkName_ = value_.get(_indexElt.intValue());
        return _facade.translatePokemon(pkName_);
    }

    private static String getImagePokemon(FacadeGame _facade,NatTreeMap<String,EqList<StringList>> _treeMap, Long _key, Long _indexList, Long _indexElt) {
        EqList<StringList> values_ = _treeMap.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        String pkName_ = value_.get(_indexElt.intValue());
        String img_ = _facade.getData().getMaxiPkFront().getVal(pkName_);
        return ConverterBufferedImage.surroundImage(img_);
    }
}
