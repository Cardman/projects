package aiki.beans.game;
import aiki.beans.WithFacade;
import aiki.beans.facade.comparators.ComparatorStatisticInfoPkPlayer;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fighter;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import code.bean.Bean;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.StringUtil;

public class PokemonPlayerBean extends Bean implements WithFacade {
    private String name;
    private String image;
    private short level;
    private String gender;
    private String ability;
    private String item;
    private Rate remainingHp;
    private String remainingHpPerCent;
    private Rate fullHp;
    private StringList types;
    private StringList status;
    private String nickname;
    private NatStringTreeMap<UsesOfMove> moves;
    private CustList<StatisticInfoPkPlayer> statistics;
    private Rate wonExpSinceLastLevel;
    private Rate necessaryPointsNextLevel;
    private short happiness;
    private String usedBallCatching;
    private short nbStepsTeamLead;
    private TreeMap<String,String> evolutions;

    private FacadeGame dataBase;

    public FacadeGame getDataBase() {
        return db();
    }

    @Override
    public FacadeGame db() {
        return dataBase;
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        dataBase = _dataBase;
    }

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = getDataBase();
        DataBase data_ = facadeGame_.getData();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        PokemonPlayer pkPlayer_;
        if (facadeGame_.isSelectedTeamPokemon()) {
            pkPlayer_ = (PokemonPlayer) facadeGame_.getSelectedPkTeam();
        } else if (facadeGame_.isSelectedBoxPokemon()) {
            pkPlayer_ = facadeGame_.getSelectedPokemonFirstBox();
        } else if (facadeGame_.isSelectedOtherPokemon()) {
            pkPlayer_ = facadeGame_.getReceivedPokemon();
        } else {
            pkPlayer_ = facadeGame_.getHostedPokemon();
        }
        nickname = pkPlayer_.getNickname();
        name = translatedPokemon_.getVal(pkPlayer_.getName());
        if (pkPlayer_.getUsedBallCatching().isEmpty()) {
            usedBallCatching = DataBase.EMPTY_STRING;
        } else {
            usedBallCatching = translatedItems_.getVal(pkPlayer_.getUsedBallCatching());
        }
        image = BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(pkPlayer_.getName()));
        TreeMap<String,String> evolutions_;
        evolutions_ = new TreeMap<String,String>(new ComparatorTrStrings(translatedPokemon_));
        for (String e: pkPlayer_.getDirectEvolutions(data_)) {
            int[][] img_ = data_.getMaxiPkFront().getVal(e);
            evolutions_.put(e, BaseSixtyFourUtil.getStringByImage(img_));
        }
        evolutions = evolutions_;
        ability = translatedAbilities_.getVal(pkPlayer_.getAbility());
        level = pkPlayer_.getLevel();
        gender = translationsGenders_.getVal(pkPlayer_.getGender());
        item = DataBase.EMPTY_STRING;
        if (!pkPlayer_.getItem().isEmpty()) {
            item = translatedItems_.getVal(pkPlayer_.getItem());
        }
        remainingHp = pkPlayer_.getRemainingHp();
        fullHp = pkPlayer_.pvMax(data_);
        remainingHpPerCent = Rate.multiply(Rate.divide(remainingHp, fullHp), new Rate(100)).evaluate(2);
        types = new StringList();
        for (String t: data_.getPokemon(pkPlayer_.getName()).getTypes()) {
            types.add(translatedTypes_.getVal(t));
        }
        types.sort();
        happiness = pkPlayer_.getHappiness();
        wonExpSinceLastLevel = pkPlayer_.getWonExpSinceLastLevel();
        necessaryPointsNextLevel = numberNecessaryPointsForGrowingLevel(pkPlayer_.getName());
        CustList<StatisticInfoPkPlayer> statistics_;
        statistics_ = new CustList<StatisticInfoPkPlayer>();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            StatisticInfoPkPlayer st_;
            st_ = new StatisticInfoPkPlayer();
            st_.setName(translationsStatistics_.getVal(s));
            st_.setEv(pkPlayer_.getEv().getVal(s));
            st_.setIv(pkPlayer_.getIv().getVal(s));
            st_.setRate(data_.getPokemon(pkPlayer_.getName()).stat(level, s, st_.getEv(), st_.getIv()));
            statistics_.add(st_);
        }
        statistics_.sortElts(new ComparatorStatisticInfoPkPlayer());
        statistics = statistics_;
        NatStringTreeMap<UsesOfMove> moves_;
        moves_ = new NatStringTreeMap<UsesOfMove>();
        for (String m: pkPlayer_.getMoves().getKeys()) {
            UsesOfMove uses_ = pkPlayer_.getMoves().getVal(m);
            moves_.put(translatedMoves_.getVal(m), uses_);
        }
        moves = moves_;
        StringList status_;
        status_ = new StringList();
        for (String s: pkPlayer_.getStatus()) {
            status_.add(translatedStatus_.getVal(s));
        }
        status_.sort();
        status = status_;
        nbStepsTeamLead = pkPlayer_.getNbStepsTeamLead();
    }
    public String getEvo(int _index) {
        String evo_ = evolutions.getKey(_index);
        FacadeGame facadeGame_ = getDataBase();
        DataBase data_ = facadeGame_.getData();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(evo_);
    }

    Rate numberNecessaryPointsForGrowingLevel(String _name){
        FacadeGame facadeGame_ = getDataBase();
        DataBase data_ = facadeGame_.getData();
        PokemonData fPk_=data_.getPokemon(_name);
        String expLitt_=data_.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(level + 1L));
        Rate next_;
        next_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(level));
        current_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toNumberString(), vars_, Rate.one());
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public TreeMap<String,String> getEvolutions() {
        return evolutions;
    }

    public short getLevel() {
        return level;
    }

    public String getGender() {
        return gender;
    }

    public String getAbility() {
        return ability;
    }

    public String getUsedBallCatching() {
        return usedBallCatching;
    }

    public String getItem() {
        return item;
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    public String getRemainingHpPerCent() {
        return remainingHpPerCent;
    }

    public Rate getFullHp() {
        return fullHp;
    }

    public String getNickname() {
        return nickname;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public Rate getNecessaryPointsNextLevel() {
        return necessaryPointsNextLevel;
    }

    public short getHappiness() {
        return happiness;
    }

    public short getNbStepsTeamLead() {
        return nbStepsTeamLead;
    }

    public StringList getTypes() {
        return types;
    }

    public StringList getStatus() {
        return status;
    }

    public NatStringTreeMap<UsesOfMove> getMoves() {
        return moves;
    }

    public CustList<StatisticInfoPkPlayer> getStatistics() {
        return statistics;
    }
}