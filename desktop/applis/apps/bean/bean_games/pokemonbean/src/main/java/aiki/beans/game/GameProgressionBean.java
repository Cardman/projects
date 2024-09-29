package aiki.beans.game;

import aiki.beans.CommonSingleBean;
import aiki.comparators.ComparatorTrainerPlaceNames;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.GameProgression;
import aiki.map.DataMap;
import code.maths.LgInt;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class GameProgressionBean extends CommonSingleBean {
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
    private DictionaryComparator<Short,Integer> remainingOtherTrainerPlaces;
    private StringList visitedPlaces;
    private StringList unVisitedPlaces;
    private LgInt money;
    private int remainStepsRepel;
    private int nbRemainingEggs;
    private int nbRemainingNotMaxLevel;
    private int nbRemainingNotMaxHappiness;

    @Override
    public void beforeDisplaying() {
        FacadeGame facade_ = facade();
        heroImage = facade_.getFrontChosenHeros(getBaseEncode());
        heroImageOppositeSex = facade_.getFrontChosenHerosOppositeSex(getBaseEncode());
        GameProgression progression_ = facade_.getGameProgression();
        finishedGame = progression_.isFinishedGame();
        endGameImage = getStringByImage(facade_.getData().getEndGameImage().getImage());
        nickname = progression_.getNickname();
        notAtAllFamiliesBaseInit(facade_, progression_);
        partialFamiliesBaseCaughtInit(facade_, progression_);
        partialFamiliesBaseNotCaughtInit(facade_, progression_);
        fullFamiliesBaseInit(facade_, progression_);
        unBeatenImportantTrainers = progression_.getUnBeatenImportantTrainers();
        unBeatenImportantTrainers.sortElts(new ComparatorTrainerPlaceNames());
        beatenImportantTrainers = progression_.getBeatenImportantTrainers();
        beatenImportantTrainers.sortElts(new ComparatorTrainerPlaceNames());
        remainingOtherTrainerPlaces = DictionaryComparatorUtil.buildPlaces(facade_.getMap());
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

    private void fullFamiliesBaseInit(FacadeGame _facade, GameProgression _progression) {
        fullFamiliesBase = new NatStringTreeMap<CustList<StringList>>();
        for (String b: _progression.getFullFamiliesBase().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
            for (StringList l: _progression.getFullFamiliesBase().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(DictionaryComparatorUtil.cmpPokemon(_facade.getData(),getLanguage()));
                lists_.add(list_);
            }
            fullFamiliesBase.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void partialFamiliesBaseNotCaughtInit(FacadeGame _facade, GameProgression _progression) {
        partialFamiliesBaseNotCaught = new NatStringTreeMap<CustList<StringList>>();
        for (String b: _progression.getPartialFamiliesBaseNotCaught().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
            for (StringList l: _progression.getPartialFamiliesBaseNotCaught().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(DictionaryComparatorUtil.cmpPokemon(_facade.getData(),getLanguage()));
                lists_.add(list_);
            }
            partialFamiliesBaseNotCaught.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void partialFamiliesBaseCaughtInit(FacadeGame _facade, GameProgression _progression) {
        partialFamiliesBaseCaught = new NatStringTreeMap<CustList<StringList>>();
        for (String b: _progression.getPartialFamiliesBaseCaught().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
            for (StringList l: _progression.getPartialFamiliesBaseCaught().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(DictionaryComparatorUtil.cmpPokemon(_facade.getData(),getLanguage()));
                lists_.add(list_);
            }
            partialFamiliesBaseCaught.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void notAtAllFamiliesBaseInit(FacadeGame _facade, GameProgression _progression) {
        notAtAllFamiliesBase = new NatStringTreeMap<CustList<StringList>>();
        for (String b: _progression.getNotAtAllFamiliesBase().getKeys()) {
            CustList<StringList> lists_ = new CustList<StringList>();
            for (StringList l: _progression.getNotAtAllFamiliesBase().getVal(b)) {
                StringList list_ = new StringList();
                for (String e: l) {
                    list_.add(e);
                }
                list_.sortElts(DictionaryComparatorUtil.cmpPokemon(_facade.getData(),getLanguage()));
                lists_.add(list_);
            }
            notAtAllFamiliesBase.put(_facade.translatePokemon(b), lists_);
        }
    }
    public String getRemainingOtherTrainersPlaceName(int _index) {
        FacadeGame facade_ = facade();
        short key_ = remainingOtherTrainerPlaces.getKey(_index);
        DataMap dataMap_ = facade_.getMap();
        return dataMap_.getPlace(key_).getName();
    }
    public String getTrPokemonNotAll(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getTrPokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonNotAll(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getImagePokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartialNot(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getTrPokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartialNot(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getImagePokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartial(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getTrPokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartial(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getImagePokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonFull(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getTrPokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonFull(int _key, int _indexList, int _indexElt) {
        FacadeGame facade_ = facade();
        return getImagePokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public StringList getKeyPokemon(int _key, int _indexList) {
        CustList<StringList> values_ = partialFamiliesBaseCaught.getValue(_key);
        return values_.get(_indexList);
    }

    private static String getTrPokemon(FacadeGame _facade,NatStringTreeMap<CustList<StringList>> _treeMap, int _key, int _indexList, int _indexElt) {
        CustList<StringList> values_ = _treeMap.getValue(_key);
        StringList value_ = values_.get(_indexList);
        String pkName_ = value_.get(_indexElt);
        return _facade.translatePokemon(pkName_);
    }

    private String getImagePokemon(FacadeGame _facade,NatStringTreeMap<CustList<StringList>> _treeMap, int _key, int _indexList, int _indexElt) {
        CustList<StringList> values_ = _treeMap.getValue(_key);
        StringList value_ = values_.get(_indexList);
        String pkName_ = value_.get(_indexElt);
        int[][] img_ = _facade.getData().getMaxiPkFront().getVal(pkName_).getImage();
        return getStringByImage(img_);
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

    public DictionaryComparator<Short,Integer> getRemainingOtherTrainerPlaces() {
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