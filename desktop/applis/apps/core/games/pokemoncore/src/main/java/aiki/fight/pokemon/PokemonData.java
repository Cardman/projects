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
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;


public final class PokemonData {

    private static final int RATE_BASE = 8;

    private static final int RATE_EV = 1;

    private static final int RATE_IV = 4;

    private static final int RATE_DIV_STAT = 400;

    private static final int BONUS_STAT = 5;

    private static final int BONUS_HP = 5;

    /** DONE */
    private Rate weight;

    /** DONE */
    private StringList types;

    /** DONE */
    private EnumMap<Statistic, StatBaseEv> statistics;

    /** DONE */
    private CustList<LevelMove> levMoves;

    /** DONE */
    private GenderRepartition genderRep;

    /** DONE */
    private StringList abilities;

    /** DONE */
    private StringList moveTutors;

    /** DONE */
    private Shorts hiddenMoves;

    /** DONE */
    private Shorts technicalMoves;

    /** DONE */
    private String baseEvo;

    /** DONE */
    private StringMap<Evolution> evolutions;

    /** DONE */
    private short catchingRate;

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
    private short happiness;

    /***/
    private short happinessHatch;

    public void validate(DataBase _data) {
        eggGroups.removeDuplicates();
        if (expRate <= 0) {
            _data.setError(true);
        }
        if (catchingRate <= 0) {
            _data.setError(true);
        }
        if (happiness <= 0) {
            _data.setError(true);
        }
        if (happinessHatch <= 0) {
            _data.setError(true);
        }
        if (!hatchingSteps.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!weight.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!height.isZeroOrGt()) {
            _data.setError(true);
        }
        if (types.isEmpty()) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(types)) {
            _data.setError(true);
        }
        if (genderRep != GenderRepartition.NO_GENDER) {
            if (genderRep != GenderRepartition.LEGENDARY) {
                if (eggGroups.isEmpty()) {
                    _data.setError(true);
                }
                PokemonData fPkBaseEvo_ = _data.getPokemon(baseEvo);
                if (fPkBaseEvo_ == null || fPkBaseEvo_.genderRep == GenderRepartition.LEGENDARY) {
                    _data.setError(true);
                }
            }
        }
        if (!Statistic.equalsSet(statistics.getKeys(),
                Statistic.getStatisticsWithBase())) {
            _data.setError(true);
        }
        for (EntryCust<Statistic,StatBaseEv> e: statistics.entryList()) {
            StatBaseEv ev_ = e.getValue();
            if (ev_.getBase() <= 0) {
                _data.setError(true);
            }
            if (ev_.getEv() < 0) {
                _data.setError(true);
            }
        }
        if (levMoves.isEmpty()) {
            _data.setError(true);
        }
        if (abilities.isEmpty()) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(moveTutors)) {
            _data.setError(true);
        }
        if (StringUtil.contains(moveTutors, _data.getDefaultMove())) {
            _data.setError(true);
        }
        if (!_data.getAbilities().containsAllAsKeys(abilities)) {
            _data.setError(true);
        }
        if (!_data.getHm().containsAllAsKeys(hiddenMoves)) {
            _data.setError(true);
        }
        if (!_data.getTm().containsAllAsKeys(technicalMoves)) {
            _data.setError(true);
        }
        if (!_data.getPokedex().contains(baseEvo)) {
            _data.setError(true);
        }
        for (String e : evolutions.getKeys()) {
            if (!_data.getPokedex().contains(e)) {
                _data.setError(true);
            }
            if (evolutions.getVal(e).validate(_data, this)) {
                _data.setError(true);
            }
        }
        if (levMoves.isEmpty()) {
            return;
        }
        short min_ = levMoves.first().getLevel();
        if (min_ > _data.getMinLevel()) {
            levMoves.first().setLevel((short) _data.getMinLevel());
        }
        min_ = levMoves.first().getLevel();
        for (LevelMove p : levMoves) {
            if (p.getLevel() < min_) {
                _data.setError(true);
            }
            if (StringUtil.quickEq(p.getMove(), _data.getDefaultMove())) {
                _data.setError(true);
            }
            if (!_data.getMoves().contains(p.getMove())) {
                _data.setError(true);
            }
            min_ = p.getLevel();
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

    public Rate statHp(short _niv, EnumMap<Statistic, Short> _ev,
            EnumMap<Statistic, Short> _iv) {
        return stat(_niv, Statistic.HP, _ev.getVal(Statistic.HP),
                _iv.getVal(Statistic.HP));
    }

    public Rate stat(short _niv, Statistic _stat, short _ev, short _iv) {
        return stat(_niv, statistics.getVal(_stat).getBase(), _stat, _ev, _iv);
    }

    public static Rate stat(short _niv, short _base, Statistic _stat,
            short _ev, short _iv) {
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
    public static Rate stat(short _niv, Rate _base, Statistic _stat, short _ev,
            short _iv) {
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

    public StringList getMovesBeforeLevel(short _level) {
        StringList list_ = new StringList();
        for (LevelMove p : levMoves) {
            if (p.getLevel() > _level) {
                break;
            }
            list_.add(p.getMove());
        }
        return list_;
    }

    public StringList getMovesAtLevel(short _level, int _maxNumber) {
        StringList list_ = new StringList();
        for (LevelMove l : levMoves.getReverse()) {
            if (list_.size() == _maxNumber) {
                break;
            }
            if (l.getLevel() > _level) {
                continue;
            }
            list_.add(l.getMove());
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

    public EnumMap<Statistic, Short> getEvs() {
        EnumMap<Statistic, Short> evs_ = new EnumMap<Statistic, Short>();
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

    public EnumMap<Statistic, StatBaseEv> getStatistics() {
        return statistics;
    }

    public void setStatistics(EnumMap<Statistic, StatBaseEv> _statistics) {
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

    public Shorts getHiddenMoves() {
        return hiddenMoves;
    }

    public void setHiddenMoves(Shorts _hiddenMoves) {
        hiddenMoves = _hiddenMoves;
    }

    public Shorts getTechnicalMoves() {
        return technicalMoves;
    }

    public void setTechnicalMoves(Shorts _technicalMoves) {
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

    public short getCatchingRate() {
        return catchingRate;
    }

    public void setCatchingRate(short _catchingRate) {
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

    public short getHappiness() {
        return happiness;
    }

    public void setHappiness(short _happiness) {
        happiness = _happiness;
    }

    public short getHappinessHatch() {
        return happinessHatch;
    }

    public void setHappinessHatch(short _happinessHatch) {
        happinessHatch = _happinessHatch;
    }
}
