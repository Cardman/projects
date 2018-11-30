package aiki.beans.game;
import aiki.beans.facade.comparators.ComparatorPlaceNumber;
import aiki.comparators.ComparatorPairStringNumber;
import aiki.comparators.ComparatorTrStrings;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.GameProgression;
import aiki.map.DataMap;
import code.bean.Bean;
import code.maths.LgInt;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.opers.BaseSixtyFourUtil;

public class GameProgressionBean extends Bean {
    private String heroImage;
    private String heroImageOppositeSex;
    private String nickname;
    private boolean finishedGame;
    private String endGameImage;
    private NatStringTreeMap<EqList<StringList>> notAtAllFamiliesBase;
    private NatStringTreeMap<EqList<StringList>> partialFamiliesBaseNotCaught;
    private NatStringTreeMap<EqList<StringList>> partialFamiliesBaseCaught;
    private NatStringTreeMap<EqList<StringList>> fullFamiliesBase;
    private EqList<TrainerPlaceNames> beatenImportantTrainers;
    private EqList<TrainerPlaceNames> unBeatenImportantTrainers;
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
        notAtAllFamiliesBase = new NatStringTreeMap<EqList<StringList>>();
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
        partialFamiliesBaseCaught = new NatStringTreeMap<EqList<StringList>>();
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
        partialFamiliesBaseNotCaught = new NatStringTreeMap<EqList<StringList>>();
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
        fullFamiliesBase = new NatStringTreeMap<EqList<StringList>>();
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
    public String getRemainingOtherTrainersPlaceName(Long _index) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        short key_ = remainingOtherTrainerPlaces.getKey(_index.intValue());
        DataMap dataMap_ = facade_.getMap();
        return dataMap_.getPlaces().getVal(key_).getName();
    }
    public String getTrPokemonNotAll(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonNotAll(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, notAtAllFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartialNot(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartialNot(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseNotCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonPartial(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getImagePokemonPartial(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, partialFamiliesBaseCaught, _key, _indexList, _indexElt);
    }
    public String getTrPokemonFull(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getTrPokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public String getImagePokemonFull(Long _key, Long _indexList, Long _indexElt) {
        FacadeGame facade_ = (FacadeGame) getDataBase();
        return getImagePokemon(facade_, fullFamiliesBase, _key, _indexList, _indexElt);
    }
    public StringList getKeyPokemon(Long _key, Long _indexList) {
        EqList<StringList> values_ = partialFamiliesBaseCaught.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        return value_;
    }

    private static String getTrPokemon(FacadeGame _facade,NatStringTreeMap<EqList<StringList>> _treeMap, Long _key, Long _indexList, Long _indexElt) {
        EqList<StringList> values_ = _treeMap.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        String pkName_ = value_.get(_indexElt.intValue());
        return _facade.translatePokemon(pkName_);
    }

    private static String getImagePokemon(FacadeGame _facade,NatStringTreeMap<EqList<StringList>> _treeMap, Long _key, Long _indexList, Long _indexElt) {
        EqList<StringList> values_ = _treeMap.getValue(_key.intValue());
        StringList value_ = values_.get(_indexList.intValue());
        String pkName_ = value_.get(_indexElt.intValue());
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

    public EqList<TrainerPlaceNames> getUnBeatenImportantTrainers() {
        return unBeatenImportantTrainers;
    }

    public EqList<TrainerPlaceNames> getBeatenImportantTrainers() {
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

    public NatStringTreeMap<EqList<StringList>> getFullFamiliesBase() {
        return fullFamiliesBase;
    }

    public NatStringTreeMap<EqList<StringList>> getNotAtAllFamiliesBase() {
        return notAtAllFamiliesBase;
    }

    public NatStringTreeMap<EqList<StringList>> getPartialFamiliesBaseNotCaught() {
        return partialFamiliesBaseNotCaught;
    }
}