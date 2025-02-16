package aiki.fight.pokemon;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.GenderConstraints;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.map.pokemon.enums.Gender;
import aiki.util.DataInfoChecker;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;


public final class PokemonData {

    private static final int RATE_BASE = 8;

    private static final long RATE_EV = 1;

    private static final long RATE_IV = 4;

    private static final int RATE_DIV_STAT = 400;

    private static final int BONUS_STAT = 5;

    private static final long BONUS_HP = 5;

    /** DONE */
    private Rate weight;

    /** DONE */
    private StringList types;

    /** DONE */
    private IdMap<Statistic, StatBaseEv> statistics;

    /** DONE */
    private CustList<LevelMove> levMoves;

    /** DONE */
    private GenderRepartition genderRep;

    /** DONE */
    private StringList abilities;

    /** DONE */
    private StringList moveTutors;

    /** DONE */
    private Ints hiddenMoves;

    /** DONE */
    private Ints technicalMoves;

    /** DONE */
    private String baseEvo;

    /** DONE */
    private StringMap<Evolution> evolutions;

    /** DONE */
    private long catchingRate;

    /** DONE */
    private Rate height;

    /** DONE */
    private ExpType expEvo;

    /***/
    private long expRate;

    /***/
    private StringList eggGroups;

    /** DONE */
    private LgInt hatchingSteps;

    /** DONE */
    private long happiness;

    /***/
    private long happinessHatch;

    public void validate(DataBase _data) {
        eggGroups.removeDuplicates();
        DataInfoChecker.checkPositive(expRate,_data);
        DataInfoChecker.checkPositive(catchingRate,_data);
        DataInfoChecker.checkPositive(happiness,_data);
        DataInfoChecker.checkPositive(happinessHatch,_data);
        DataInfoChecker.checkPositiveOrZero(hatchingSteps,_data);
        DataInfoChecker.checkPositiveOrZero(weight,_data);
        DataInfoChecker.checkPositiveOrZero(height,_data);
        DataInfoChecker.checkEmptyNotStringList(types,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),types,_data);
        if (genderRep != GenderRepartition.NO_GENDER && genderRep != GenderRepartition.LEGENDARY) {
            DataInfoChecker.checkEmptyNotStringList(eggGroups,_data);
            PokemonData fPkBaseEvo_ = _data.getPokemon(baseEvo);
            if (fPkBaseEvo_ == null || fPkBaseEvo_.genderRep == GenderRepartition.LEGENDARY) {
                _data.setError(true);
            }
        }
        if (!Statistic.equalsSet(statistics.getKeys(),
                Statistic.getStatisticsWithBase())) {
            _data.setError(true);
        }
        for (EntryCust<Statistic,StatBaseEv> e: statistics.entryList()) {
            StatBaseEv ev_ = e.getValue();
            DataInfoChecker.checkPositive(ev_.getBase(),_data);
            DataInfoChecker.checkPositiveOrZero(ev_.getEv(),_data);
        }
        DataInfoChecker.checkEmptyNotStringList(abilities,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),moveTutors,_data);
        if (StringUtil.contains(moveTutors, _data.getDefMove())) {
            _data.setError(true);
        }
        DataInfoChecker.checkStringListContains(_data.getAbilities().getKeys(),abilities,_data);
        DataInfoChecker.checkShortsContains(_data.getHm().getKeys(), hiddenMoves, _data);
        DataInfoChecker.checkShortsContains(_data.getTm().getKeys(), technicalMoves, _data);
        DataInfoChecker.checkStringListContains(_data.getPokedex().getKeys(),baseEvo,_data);
        DataInfoChecker.checkStringListContains(_data.getPokedex().getKeys(),evolutions.getKeys(),_data);
        validateEvos(_data);
        if (levMoves.isEmpty()) {
            _data.setError(true);
            return;
        }
        long min_ = levMoves.first().getLevel();
        patchLevelMoves(_data, min_);
        min_ = levMoves.first().getLevel();
        for (LevelMove p : levMoves) {
            DataInfoChecker.checkLower(min_,p.getLevel(),_data);
            if (StringUtil.quickEq(p.getMove(), _data.getDefMove())) {
                _data.setError(true);
            }
            DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),p.getMove(),_data);
            min_ = p.getLevel();
        }
    }

    private void validateEvos(DataBase _data) {
        for (Evolution e : evolutions.values()) {
            if (e.validate(_data, this)) {
                _data.setError(true);
            }
        }
    }

    private void patchLevelMoves(DataBase _data, long _min) {
        if (_min > _data.getMinLevel()) {
            levMoves.first().setLevel(_data.getMinLevel());
        }
    }

    public Evolution getEvolution(String _name) {
        return evolutions.getVal(_name);
    }

    // public static String getFormula(Statistic _statistic) {
    // if (_statistic == Statistic.HP) {
    // return FORM_STATIS_HP;
    // }
    // return FORM_STATIS;
    // }

    public Rate statHp(long _niv, IdMap<Statistic, Long> _ev,
            IdMap<Statistic, Long> _iv) {
        return stat(_niv, Statistic.HP, _ev.getVal(Statistic.HP),
                _iv.getVal(Statistic.HP));
    }

    public Rate stat(long _niv, Statistic _stat, long _ev, long _iv) {
        return stat(_niv, statistics.getVal(_stat).getBase(), _stat, _ev, _iv);
    }

    public static Rate stat(long _niv, long _base, Statistic _stat,
                            long _ev, long _iv) {
        return stat(_niv, new Rate(_base), _stat, _ev, _iv);
    }

    /**
     * Rate stat_=Rate.multiply(_base, new Rate(2));<br/>
     * stat_.addNb(new Rate(_ev+4x_iv,4));<br/>
     * stat_.multiplyBy(new Rate(_niv,100));<br/>
     * if(_stat == Statistic.HP){<br/>
     * stat_.addNb(new Rate(_niv+10));<br/>
     * } else {<br/>
     * stat_.addNb(new Rate(5));<br/>
     * }<br/>
     * return stat_;
     */
    public static Rate stat(long _niv, Rate _base, Statistic _stat, long _ev,
                            long _iv) {
        Rate stat_ = Rate.multiply(_base, new Rate(RATE_BASE));
        stat_.addNb(new Rate(RATE_EV * _ev));
        stat_.addNb(new Rate(RATE_IV * _iv));
        stat_.multiplyBy(new Rate(_niv, RATE_DIV_STAT));
        stat_.addNb(new Rate(BONUS_STAT));
        if (_stat == Statistic.HP) {
            stat_.addNb(new Rate(_niv + BONUS_HP));
        }
        return stat_;
    }

    public StringList getMovesBeforeLevel(long _level) {
        StringList list_ = new StringList();
        for (LevelMove p : levMoves) {
            if (p.getLevel() > _level) {
                break;
            }
            list_.add(p.getMove());
        }
        return list_;
    }

    public StringList getMovesAtLevel(long _level, long _maxNumber) {
        StringList list_ = new StringList();
        for (LevelMove l : levMoves.getReverse()) {
            if (list_.size() == _maxNumber) {
                break;
            }
            if (l.getLevel() <= _level) {
                list_.add(l.getMove());
            }
        }
        return list_;
    }

    public StringList getDirectEvolutions() {
        return getDirectEvolutions(Gender.NO_GENDER, false);
    }

    public StringList getDirectEvolutions(Gender _gender, boolean _onlyPossible) {
        StringList evos_ = new StringList();
        for (String e2_ : getEvolutions().getKeys()) {
            if (_onlyPossible) {
                Evolution e_ = getEvolution(e2_);
                if (e_ instanceof GenderConstraints) {
                    GenderConstraints eSt_ = (GenderConstraints) e_;
                    if (eSt_.getGender() == _gender) {
                        evos_.add(e2_);
                    }
                } else {
                    evos_.add(e2_);
                }
            } else {
                evos_.add(e2_);
            }
        }
        return evos_;
    }

    public IdMap<Statistic, Long> getEvs() {
        IdMap<Statistic, Long> evs_ = new IdMap<Statistic, Long>();
        for (Statistic s : statistics.getKeys()) {
            evs_.put(s, statistics.getVal(s).getEv());
        }
        return evs_;
    }

    public Rate getWeight() {
        return weight;
    }

    public void setWeight(Rate _weight) {
        weight = _weight;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public IdMap<Statistic, StatBaseEv> getStatistics() {
        return statistics;
    }

    public void setStatistics(IdMap<Statistic, StatBaseEv> _statistics) {
        statistics = _statistics;
    }

    public CustList<LevelMove> getLevMoves() {
        return levMoves;
    }

    public void setLevMoves(CustList<LevelMove> _levMoves) {
        levMoves = _levMoves;
    }

    public GenderRepartition getGenderRep() {
        return genderRep;
    }

    public void setGenderRep(GenderRepartition _genderRep) {
        genderRep = _genderRep;
    }

    public StringList getAbilities() {
        return abilities;
    }

    public void setAbilities(StringList _abilities) {
        abilities = _abilities;
    }

    public StringList getMoveTutors() {
        return moveTutors;
    }

    public void setMoveTutors(StringList _moveTutors) {
        moveTutors = _moveTutors;
    }

    public Ints getHiddenMoves() {
        return hiddenMoves;
    }

    public void setHiddenMoves(Ints _hiddenMoves) {
        hiddenMoves = _hiddenMoves;
    }

    public Ints getTechnicalMoves() {
        return technicalMoves;
    }

    public void setTechnicalMoves(Ints _technicalMoves) {
        technicalMoves = _technicalMoves;
    }

    public String getBaseEvo() {
        return baseEvo;
    }

    public void setBaseEvo(String _baseEvo) {
        baseEvo = _baseEvo;
    }

    public StringMap<Evolution> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(StringMap<Evolution> _evolutions) {
        evolutions = _evolutions;
    }

    public long getCatchingRate() {
        return catchingRate;
    }

    public void setCatchingRate(long _catchingRate) {
        catchingRate = _catchingRate;
    }

    public Rate getHeight() {
        return height;
    }

    public void setHeight(Rate _height) {
        height = _height;
    }

    public ExpType getExpEvo() {
        return expEvo;
    }

    public void setExpEvo(ExpType _expEvo) {
        expEvo = _expEvo;
    }

    public long getExpRate() {
        return expRate;
    }

    public void setExpRate(long _expRate) {
        expRate = _expRate;
    }

    public StringList getEggGroups() {
        return eggGroups;
    }

    public void setEggGroups(StringList _eggGroups) {
        eggGroups = _eggGroups;
    }

    public LgInt getHatchingSteps() {
        return hatchingSteps;
    }

    public void setHatchingSteps(LgInt _hatchingSteps) {
        hatchingSteps = _hatchingSteps;
    }

    public long getHappiness() {
        return happiness;
    }

    public void setHappiness(long _happiness) {
        happiness = _happiness;
    }

    public long getHappinessHatch() {
        return happinessHatch;
    }

    public void setHappinessHatch(long _happinessHatch) {
        happinessHatch = _happinessHatch;
    }
}
