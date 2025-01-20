package aiki.beans.game;

import aiki.beans.CommonSingleBean;
import aiki.beans.facade.comparators.ComparatorStatisticInfoPkPlayer;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.FightFacade;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.*;

public final class PokemonPlayerBean extends CommonSingleBean {
    private String name;
    private int[][] image;
    private long level;
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
    private long happiness;
    private String usedBallCatching;
    private long nbStepsTeamLead;
    private CustList<ImgPkPlayer> evolutions;

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = facade();
        DataBase data_ = facadeGame_.getData();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        AbsMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        PokemonPlayer pkPlayer_ = facadeGame_.getDisplayed();
        nickname = pkPlayer_.getNickname();
        name = translatedPokemon_.getVal(pkPlayer_.getName());
        if (pkPlayer_.getUsedBallCatching().isEmpty()) {
            usedBallCatching = DataBase.EMPTY_STRING;
        } else {
            usedBallCatching = translatedItems_.getVal(pkPlayer_.getUsedBallCatching());
        }
        image = data_.getMaxiPkFront().getVal(pkPlayer_.getName()).getImage();
        NatStringTreeMap<ImgPkPlayer> tmp_ = new NatStringTreeMap<ImgPkPlayer>();
        for (String e: pkPlayer_.getDirectEvolutions(data_)) {
            int[][] img_ = data_.getMaxiPkFront().getVal(e).getImage();
            String tr_ = translatedPokemon_.getVal(e);
            tmp_.put(tr_, new ImgPkPlayer(e,tr_,img_));
        }
        evolutions = tmp_.values();
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

    Rate numberNecessaryPointsForGrowingLevel(String _name){
        FacadeGame facadeGame_ = facade();
        DataBase data_ = facadeGame_.getData();
        Rate diff_ = FightFacade.numberNecessaryPointsForGrowingLevel(_name,level+1L,data_);
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }

    public String getName() {
        return name;
    }

    public int[][] getImage() {
        return image;
    }

    public CustList<ImgPkPlayer> getEvolutions() {
        return evolutions;
    }

    public long getLevel() {
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

    public long getHappiness() {
        return happiness;
    }

    public long getNbStepsTeamLead() {
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