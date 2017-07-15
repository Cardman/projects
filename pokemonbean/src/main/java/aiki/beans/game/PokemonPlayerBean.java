package aiki.beans.game;
import code.bean.Accessible;
import code.bean.Bean;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorStatisticInfoPkPlayer;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.comparators.ComparatorTrStrings;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fighter;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;

public class PokemonPlayerBean extends Bean {

    @Accessible
    private String name;

    @Accessible
    private String image;

    @Accessible
    private short level;

    @Accessible
    private String gender;

    @Accessible
    private String ability;

    @Accessible
    private String item;

    @Accessible
    private Rate remainingHp;

    @Accessible
    private String remainingHpPerCent;

    @Accessible
    private Rate fullHp;

    @Accessible
    private StringList types;

    @Accessible
    private StringList status;

    /**nickname du pokemon par defaut le nom du pokemon*/
    @Accessible
    private String nickname;

    @Accessible
    private NatTreeMap<String,UsesOfMove> moves;

    @Accessible
    private CustList<StatisticInfoPkPlayer> statistics;

    /**Points d'experience gagnes depuis la derniere montee de niveau*/
    @Accessible
    private Rate wonExpSinceLastLevel;

    @Accessible
    private Rate necessaryPointsNextLevel;

    @Accessible
    private short happiness;

    @Accessible
    private String usedBallCatching;

    @Accessible
    private short nbStepsTeamLead;

    @Accessible
    private TreeMap<String,String> evolutions;

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
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
        image = ConverterBufferedImage.surroundImage(data_.getMaxiPkFront().getVal(pkPlayer_.getName()));
        TreeMap<String,String> evolutions_;
        evolutions_ = new TreeMap<String,String>(new ComparatorTrStrings(translatedPokemon_));
        for (String e: pkPlayer_.getDirectEvolutions(data_)) {
            String img_ = data_.getMaxiPkFront().getVal(e);
            img_ = ConverterBufferedImage.surroundImage(img_);
            evolutions_.put(e, img_);
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
        NatTreeMap<String,UsesOfMove> moves_;
        moves_ = new NatTreeMap<String,UsesOfMove>();
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

    @Accessible
    private String getEvo(Long _index) {
        String evo_ = evolutions.getKey(_index.intValue());
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
        DataBase data_ = facadeGame_.getData();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(evo_);
    }

    Rate numberNecessaryPointsForGrowingLevel(String _name){
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
        DataBase data_ = facadeGame_.getData();
        PokemonData fPk_=data_.getPokemon(_name);
        String expLitt_=data_.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level + 1));
        Rate next_;
        next_ = data_.evaluate(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level));
        current_ = data_.evaluate(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toString(), vars_, Rate.one());
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }
}
