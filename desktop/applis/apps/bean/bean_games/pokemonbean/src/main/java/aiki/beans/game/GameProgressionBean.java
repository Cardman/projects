package aiki.beans.game;

import aiki.beans.*;
import aiki.comparators.ComparatorTrainerPlaceNames;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.Game;
import aiki.game.GameProgression;
import aiki.map.levels.enums.EnvironmentType;
import code.maths.LgInt;
import code.scripts.pages.aiki.MessagesPkBean;
import code.scripts.pages.aiki.MessagesProgGameprog;
import code.util.*;

public final class GameProgressionBean extends CommonSingleBean implements BeanRenderWithAppName {
    private int[][] heroImage;
    private int[][] heroImageOppositeSex;
    private String nickname;
    private int finishedGame;
    private int[][] endGameImage;
    private NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> notAtAllFamiliesBase;
    private NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> partialFamiliesBaseNotCaught;
    private NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> partialFamiliesBaseCaught;
    private NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> fullFamiliesBase;
    private CustList<TrainerPlaceNames> beatenImportantTrainers;
    private CustList<TrainerPlaceNames> unBeatenImportantTrainers;
    private DictionaryComparator<Integer,PlaceNamePk> remainingOtherTrainerPlaces;
    private StringList visitedPlaces;
    private StringList unVisitedPlaces;
    private LgInt money;
    private long remainStepsRepel;
    private int nbRemainingEggs;
    private int nbRemainingNotMaxLevel;
    private int nbRemainingNotMaxHappiness;

    public GameProgressionBean() {
        setAppName(MessagesPkBean.APP_BEAN);
    }
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesProgGameprog.M_P_95_TITLE));
//        initPage();
        displayBool(getFinishedGame(), CommonBean.TRUE_VALUE,getHeroImage());
        displayBool(getFinishedGame(),CommonBean.TRUE_VALUE,getHeroImageOppositeSex());
        displayBool(getFinishedGame(),CommonBean.TRUE_VALUE,getEndGameImage());
        displayBoolFalse(getFinishedGame(), MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_NICKNAME,getNickname());
        displayBool(getFinishedGame(),CommonBean.FALSE_VALUE,getHeroImage());
//        feedParents();
        build(getNotAtAllFamiliesBase(),file().getVal(MessagesProgGameprog.M_P_95_TITLENOTATALL));
        buildPart(file().getVal(MessagesProgGameprog.M_P_95_TITLEPART));
        build(getFullFamiliesBase(),file().getVal(MessagesProgGameprog.M_P_95_TITLEFULL));
        displayTrainerPlaceNamesList(getUnBeatenImportantTrainers(), MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_UNBEATTRAINER);
        displayTrainerPlaceNamesList(getBeatenImportantTrainers(), MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_BEATTRAINER);
        new BeanDisplayListGrid<PlaceNamePk>(new BeanDisplayPlaceNamePk()).displayGrid(this,getRemainingOtherTrainerPlaces().values(),MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_OTHERTRAINERS,MessagesProgGameprog.M_P_95_OTHERTRAINERSPLACE,MessagesProgGameprog.M_P_95_OTHERTRAINERSNUMBER);
//        for (EntryCust<Integer,PlaceNamePk> e: getRemainingOtherTrainerPlaces().entryList()) {
//            formatMessageDirCts(e.getValue().getName());
//            formatMessageDirCts(Long.toString(e.getValue().getIndex()));
//        }
//        feedParents();
        displayStringList(getUnVisitedPlaces(), MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_UNVISITPLACE);
        displayStringList(getVisitedPlaces(), MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_VISITPLACE);
//        initPage();
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKLEVEL,Long.toString(getNbRemainingNotMaxLevel()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKHAPPINESS,Long.toString(getNbRemainingNotMaxHappiness()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMEGG,Long.toString(getNbRemainingEggs()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_REPEL,Long.toString(getRemainStepsRepel()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_MONEY,getMoney().toNumberString());
//        feedParents();
    }
    private void build(NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> _groups, String _title) {
        initPage();
        setTitledBorder(_title);
        for (EntryCust<String,CustList<CustList<ImgPkPlayer>>> e: _groups.entryList()) {
            initLine();
            formatMessageDir(e.getKey());
            initGrid();
            int s_ = e.getValue().size();
            getBuilder().colCount(s_);
            list(e.getValue());
//            for (CustList<ImgPkPlayer> s:e.getValue()) {
//                initPage();
//                buildPkList(s);
//                feedParentsCts();
//            }
            feedParents();
            feedParents();
        }
        feedParents();
    }

    private void buildPart(String _title) {
        initPage();
        setTitledBorder(_title);
        int len_ = getPartialFamiliesBaseNotCaught().size();
        for (int i = 0; i < len_; i++) {
            EntryCust<String, CustList<CustList<ImgPkPlayer>>> e_ = getPartialFamiliesBaseNotCaught().getEntry(i);
            initLine();
            formatMessageDir(e_.getKey());
            initGrid();
            int s_ = e_.getValue().size();
            getBuilder().colCount(s_+1);
            formatMessageCts(MessagesPkBean.GAMEPROG, MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTNOTPART);
            list(e_.getValue());
            formatMessageCts(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTPART);
            list(getPartialFamiliesBaseCaught().getValue(i));
            feedParents();
            feedParents();
        }
        feedParents();
    }

    private void list(CustList<CustList<ImgPkPlayer>> _lists) {
        int s_ = _lists.size();
        for (int j = 0; j < s_; j++) {
            initPage();
            buildPkList(_lists.get(j));
            feedParentsCts();
        }
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.GAMEPROG).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        FacadeGame facade_ = facade();
        heroImage = getFrontChosenHeros();
        heroImageOppositeSex = getFrontChosenHerosOppositeSex();
        GameProgression progression_ = facade_.getGameProgression();
        finishedGame = toInt(progression_.isFinishedGame());
        endGameImage = facade_.getData().getEndGameImage().getImage();
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
        for (EntryCust<Integer,Integer> e: progression_.getRemainingOtherTrainerPlaces().entryList()) {
            remainingOtherTrainerPlaces.put(e.getKey(),new PlaceNamePk(e.getValue(),facade_.getMap().getPlace(e.getKey()).getName()));
        }
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

    public int[][] getFrontChosenHeros() {
        FacadeGame facade_ = facade();
        DataBase data_ = facade_.getData();
        Game game_ = facade_.getGame();
        ImageHeroKey i_;
        i_ = ImageHeroKey.direct(EnvironmentType.ROAD, game_.getPlayer());
        return data_.getFrontHeros().getVal(
                i_).getImage();
    }

    public int[][] getFrontChosenHerosOppositeSex() {
        FacadeGame facade_ = facade();
        DataBase data_ = facade_.getData();
        Game game_ = facade_.getGame();
        ImageHeroKey i_;
        i_ = ImageHeroKey.opposite(EnvironmentType.ROAD, game_.getPlayer());
        return data_.getFrontHeros().getVal(
                i_).getImage();
    }
    private void fullFamiliesBaseInit(FacadeGame _facade, GameProgression _progression) {
        fullFamiliesBase = new NatStringTreeMap<CustList<CustList<ImgPkPlayer>>>();
        StringMap<String> pks_ = _facade.getData().getTranslatedPokemon().getVal(getLanguage());
        for (String b: _progression.getFullFamiliesBase().getKeys()) {
            CustList<CustList<ImgPkPlayer>> lists_ = new CustList<CustList<ImgPkPlayer>>();
            for (StringList l: _progression.getFullFamiliesBase().getVal(b)) {
                NatStringTreeMap<ImgPkPlayer> list_ = new NatStringTreeMap<ImgPkPlayer>();
                for (String e: l) {
                    String tr_ = pks_.getVal(e);
                    list_.put(tr_,new ImgPkPlayer(e,tr_,_facade.getData().getMaxiPkFront().getVal(e).getImage()));
                }
                lists_.add(list_.values());
            }
            fullFamiliesBase.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void partialFamiliesBaseNotCaughtInit(FacadeGame _facade, GameProgression _progression) {
        partialFamiliesBaseNotCaught = new NatStringTreeMap<CustList<CustList<ImgPkPlayer>>>();
        StringMap<String> pks_ = _facade.getData().getTranslatedPokemon().getVal(getLanguage());
        for (String b: _progression.getPartialFamiliesBaseNotCaught().getKeys()) {
            CustList<CustList<ImgPkPlayer>> lists_ = new CustList<CustList<ImgPkPlayer>>();
            for (StringList l: _progression.getPartialFamiliesBaseNotCaught().getVal(b)) {
                NatStringTreeMap<ImgPkPlayer> list_ = new NatStringTreeMap<ImgPkPlayer>();
                for (String e: l) {
                    String tr_ = pks_.getVal(e);
                    list_.put(tr_,new ImgPkPlayer(e,tr_,_facade.getData().getMaxiPkFront().getVal(e).getImage()));
                }
                lists_.add(list_.values());
            }
            partialFamiliesBaseNotCaught.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void partialFamiliesBaseCaughtInit(FacadeGame _facade, GameProgression _progression) {
        partialFamiliesBaseCaught = new NatStringTreeMap<CustList<CustList<ImgPkPlayer>>>();
        StringMap<String> pks_ = _facade.getData().getTranslatedPokemon().getVal(getLanguage());
        for (String b: _progression.getPartialFamiliesBaseCaught().getKeys()) {
            CustList<CustList<ImgPkPlayer>> lists_ = new CustList<CustList<ImgPkPlayer>>();
            for (StringList l: _progression.getPartialFamiliesBaseCaught().getVal(b)) {
                NatStringTreeMap<ImgPkPlayer> list_ = new NatStringTreeMap<ImgPkPlayer>();
                for (String e: l) {
                    String tr_ = pks_.getVal(e);
                    list_.put(tr_,new ImgPkPlayer(e,tr_,_facade.getData().getMaxiPkFront().getVal(e).getImage()));
                }
                lists_.add(list_.values());
            }
            partialFamiliesBaseCaught.put(_facade.translatePokemon(b), lists_);
        }
    }

    private void notAtAllFamiliesBaseInit(FacadeGame _facade, GameProgression _progression) {
        notAtAllFamiliesBase = new NatStringTreeMap<CustList<CustList<ImgPkPlayer>>>();
        StringMap<String> pks_ = _facade.getData().getTranslatedPokemon().getVal(getLanguage());
        for (String b: _progression.getNotAtAllFamiliesBase().getKeys()) {
            CustList<CustList<ImgPkPlayer>> lists_ = new CustList<CustList<ImgPkPlayer>>();
            for (StringList l: _progression.getNotAtAllFamiliesBase().getVal(b)) {
                NatStringTreeMap<ImgPkPlayer> list_ = new NatStringTreeMap<ImgPkPlayer>();
                for (String e: l) {
                    String tr_ = pks_.getVal(e);
                    list_.put(tr_,new ImgPkPlayer(e,tr_,_facade.getData().getMaxiPkFront().getVal(e).getImage()));
                }
                lists_.add(list_.values());
            }
            notAtAllFamiliesBase.put(_facade.translatePokemon(b), lists_);
        }
    }
    public String getRemainingOtherTrainersPlaceName(int _index) {
        return remainingOtherTrainerPlaces.getValue(_index).getName();
    }
    public String getTrPokemonNotAll(int _key, int _indexList, int _indexElt) {
        return notAtAllFamiliesBase.getValue(_key).get(_indexList).get(_indexElt).getKey().getTranslation();
    }
    public int[][] getImagePokemonNotAll(int _key, int _indexList, int _indexElt) {
        return notAtAllFamiliesBase.getValue(_key).get(_indexList).get(_indexElt).getImage();
    }
    public String getTrPokemonPartialNot(int _key, int _indexList, int _indexElt) {
        return partialFamiliesBaseNotCaught.getValue(_key).get(_indexList).get(_indexElt).getKey().getTranslation();
    }
    public int[][] getImagePokemonPartialNot(int _key, int _indexList, int _indexElt) {
        return partialFamiliesBaseNotCaught.getValue(_key).get(_indexList).get(_indexElt).getImage();
    }
    public String getTrPokemonPartial(int _key, int _indexList, int _indexElt) {
        return partialFamiliesBaseCaught.getValue(_key).get(_indexList).get(_indexElt).getKey().getTranslation();
    }
    public int[][] getImagePokemonPartial(int _key, int _indexList, int _indexElt) {
        return partialFamiliesBaseCaught.getValue(_key).get(_indexList).get(_indexElt).getImage();
    }
    public String getTrPokemonFull(int _key, int _indexList, int _indexElt) {
        return fullFamiliesBase.getValue(_key).get(_indexList).get(_indexElt).getKey().getTranslation();
    }
    public int[][] getImagePokemonFull(int _key, int _indexList, int _indexElt) {
        return fullFamiliesBase.getValue(_key).get(_indexList).get(_indexElt).getImage();
    }
    public StringList getKeyPokemon(int _key, int _indexList) {
        return map(getPartialFamiliesBaseCaught().getValue(_key).get(_indexList));
    }

    public NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> getPartialFamiliesBaseCaught() {
        return partialFamiliesBaseCaught;
    }

    public static StringList map(CustList<ImgPkPlayer> _values) {
        StringList map_ = new StringList();
        for (ImgPkPlayer e: _values) {
            map_.add(e.getKey().getKey());
        }
        return map_;
    }

    public int getFinishedGame() {
        return finishedGame;
    }

    public int[][] getHeroImage() {
        return heroImage;
    }

    public int[][] getHeroImageOppositeSex() {
        return heroImageOppositeSex;
    }

    public int[][] getEndGameImage() {
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

    public DictionaryComparator<Integer,PlaceNamePk> getRemainingOtherTrainerPlaces() {
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

    public long getRemainStepsRepel() {
        return remainStepsRepel;
    }

    public LgInt getMoney() {
        return money;
    }

    public NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> getFullFamiliesBase() {
        return fullFamiliesBase;
    }

    public NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> getNotAtAllFamiliesBase() {
        return notAtAllFamiliesBase;
    }

    public NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> getPartialFamiliesBaseNotCaught() {
        return partialFamiliesBaseNotCaught;
    }
}