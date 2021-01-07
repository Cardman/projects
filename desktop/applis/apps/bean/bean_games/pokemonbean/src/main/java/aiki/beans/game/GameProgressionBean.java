package aiki.beans.game;
import aiki.beans.facade.comparators.ComparatorPlaceNumber;
import aiki.comparators.ComparatorTrainerPlaceNames;
import aiki.comparators.ComparatorTrStrings;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.GameProgression;
import aiki.map.DataMap;
import code.bean.Bean;
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.util.*;

public class GameProgressionBean extends Bean {
    private String heroImage;
    private String heroImageOppositeSex;
    private String nickname;
    private boolean finishedGame;
    private String endGameImage;
    private NatStringTreeMap<CustList<StringList>> notAtAllFamiliesBase;
    private NatStringTreeMap<CustList<StringList>> partialFamiliesBaseNotCaught;
    private NatStringTreeMap<CustList<StringList>> partialFamiliesBaseCaught;
    private NatStringTreeMap<CustList<StringList>> fullFamiliesBase;
    private CustList<TrainerPlaceNames> beatenImportantTrainers;
    private CustList<TrainerPlaceNames> unBeatenImportantTrainers;
    private TreeMap<Short,Integer> remainingOtherTrainerPlaces;
    private StringList visitedPlaces;
    private StringList unVisitedPlaces;
    private LgInt money;
    private int remainStepsRepel;
    private int nbRemainingEggs;
    private int nbRemainingNotMaxLevel;
    private int nbRemainingNotMaxHappiness;

    @Override
    public void beforeDisplaying() {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        StringMap<String> tr_ = facade_.getData().getTranslatedPokemon().getVal(getLanguage());
        heroImage = facade_.getFrontChosenHeros();
        heroImageOppositeSex = facade_.getFrontChosenHerosOppositeSex();
        GameProgression progression_ = facade_.getGameProgression();
        finishedGame = progression_.isFinishedGame();
        endGameImage = BaseSixtyFourUtil.getStringByImage(facade_.getData().getEndGameImage());
        nickname = progression_.getNickname();
        notAtAllFamiliesBase = new NatStringTreeMap<CustList<StringList>>();
        for (String b: progression_.getNotAtAllFamiliesBase().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
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
        partialFamiliesBaseCaught = new NatStringTreeMap<CustList<StringList>>();
        for (String b: progression_.getPartialFamiliesBaseCaught().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
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
        partialFamiliesBaseNotCaught = new NatStringTreeMap<CustList<StringList>>();
        for (String b: progression_.getPartialFamiliesBaseNotCaught().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
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
        fullFamiliesBase = new NatStringTreeMap<CustList<StringList>>();
        for (String b: progression_.getFullFamiliesBase().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
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
        unBeatenImportantTrainers.sortElts(new ComparatorTrainerPlaceNames());
        beatenImportantTrainers = progression_.getBeatenImportantTrainers();
        beatenImportantTrainers.sortElts(new ComparatorTrainerPlaceNames());
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
    public String getRemainingOtherTrainersPlaceName(int _index) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        short key_ = remainingOtherTrainerPlaces.getKey(_index);
        DataMap dataMap_ = facade_.getMap();
        return dataMap_.getPlace(key_).getName();
    }
    public String getTrPokemonNotAll(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonNotAll(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartialNot(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartialNot(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartial(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartial(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonFull(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonFull(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public StringList getKeyPokemon(int _key, int _indexList) {
        CustList<StringList> values_ = partialFamiliesBaseCaught.getValue(_key);
        StringList value_ = values_.get(_indexList);
        return value_;
    }

    private static String getTrPokemon(FacadeGame _facade,NatStringTreeMap<CustList<StringList>> _treeMap, int _key, int _indexList, int _indexElt) {
        CustList<StringList> values_ = _treeMap.getValue(_key);
        StringList value_ = values_.get(_indexList);
        String pkName_ = value_.get(_indexElt);
        return _facade.translatePokemon(pkName_);
    }

    private static String getImagePokemon(FacadeGame _facade,NatStringTreeMap<CustList<StringList>> _treeMap, int _key, int _indexList, int _indexElt) {
        CustList<StringList> values_ = _treeMap.getValue(_key);
        StringList value_ = values_.get(_indexList);
        String pkName_ = value_.get(_indexElt);
        int[][] img_ = _facade.getData().getMaxiPkFront().getVal(pkName_);
        return BaseSixtyFourUtil.getStringByImage(img_);
    }

    public boolean getFinishedGame() {
        return finishedGame;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public String getHeroImageOppositeSex() {
        return heroImageOppositeSex;
    }

    public String getEndGameImage() {
        return endGameImage;
    }

    public String getNickname() {
        return nickname;
    }

    public CustList<TrainerPlaceNames> getUnBeatenImportantTrainers() {
        return unBeatenImportantTrainers;
    }

    public CustList<TrainerPlaceNames> getBeatenImportantTrainers() {
        return beatenImportantTrainers;
    }

    public TreeMap<Short,Integer> getRemainingOtherTrainerPlaces() {
        return remainingOtherTrainerPlaces;
    }

    public StringList getUnVisitedPlaces() {
        return unVisitedPlaces;
    }

    public StringList getVisitedPlaces() {
        return visitedPlaces;
    }

    public int getNbRemainingNotMaxLevel() {
        return nbRemainingNotMaxLevel;
    }

    public int getNbRemainingNotMaxHappiness() {
        return nbRemainingNotMaxHappiness;
    }

    public int getNbRemainingEggs() {
        return nbRemainingEggs;
    }

    public int getRemainStepsRepel() {
        return remainStepsRepel;
    }

    public LgInt getMoney() {
        return money;
    }

    public NatStringTreeMap<CustList<StringList>> getFullFamiliesBase() {
        return fullFamiliesBase;
    }

    public NatStringTreeMap<CustList<StringList>> getNotAtAllFamiliesBase() {
        return notAtAllFamiliesBase;
    }

    public NatStringTreeMap<CustList<StringList>> getPartialFamiliesBaseNotCaught() {
        return partialFamiliesBaseNotCaught;
    }
}