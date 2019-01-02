package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.NameLevel;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.util.LevelMove;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.AvailableMovesInfos;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.Fightable;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.PairNumber;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorPairNumber;

public class FightSimulation {

//    private static final String BEGIN_REG_EXP = "^";
//    private static final String END_REG_EXP = "$";
//    private static final String END_WORD_REG_EXP = "/\\w+$";
    private static final String SEPARATOR_PK = "/";

    private Game game;

    private Coords foeCoords;

    private int noFight;

    private Numbers<Byte> mult;

    private Numbers<Integer> maxActions;

    private CustList<CustList<PkTrainer>> foeTeams;

    private CustList<PkTrainer> allyTeam = new CustList<PkTrainer>();

    private boolean freeTeams;

    /**choices:
    initial name
    gender
    initial level
    initial ability
    initial object
    initial moves
    initial won points since last growth of level
    initial happiness*/
    private CustList<PokemonPlayer> team;

    /**getFirst() index: fight, getSecond() index: initial position*/
    private EqList<StringList> items;

    private CustList<StringMap<Short>> availableEvolutions;

    private CustList<EqList<NameLevel>> evolutions;

    /**for each fight, for each round of fight, positions and possible substitute (position before fight) of front fighters*/
    private CustList<CustList<NumberMap<Byte,Byte>>> frontFighters;

    private CustList<EqList<NameLevel>> infosRealEvolutions;

    private CustList<EqList<StringList>> usedStones;

    private NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> moves;

    private NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> abilities;

    private NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>> evolutionsWhileFight;

    /**position init before fights, fight - stone evolution*/
    private NumberMap<Byte,CustList<EqList<StringList>>> movesBetweenFights;

    private NumberMap<Byte,CustList<EqList<StringList>>> abilitiesBetweenFights;

    private NumberMap<Byte,EqList<StringList>> evolutionsBetweenFights;

    private NumberMap<Byte,AvailableMovesInfos> availableMoves;

    //private Map<Byte, Pair<Pair<Byte,Byte>, StringList>> availableAbilities;

    private NumberMap<Byte,StringMap<Boolean>> availableMovesBetweenFights;

    //private Map<Byte, StringList> availableAbilitiesBetweenFights;

    private NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> keptMoves;

    private NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>> keptAbilities;

    private NumberMap<Byte,CustList<EqList<StringList>>> keptMovesBetweenFights;

    private NumberMap<Byte,EqList<StringList>> keptAbilitiesBetweenFights;

    private NumberMap<Byte,Byte> currentFights;

    //private CustList<CustList<Pair<Pair<String,Short>, Pair<StringList,StringList>>>> availableMovesAbilities;

    //private CustList<Pair<String,Short>> currentKeys;

//    private CustList<CustList<Pair<Pair<String,Short>, Pair<StringList,StringList>>>> movesAbilities;

    /**for each fight - round: map of positions at begin of fight and choices of
    evolution, learnt moves, learnt ability at level*/
    private CustList<CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>>> movesAbilities;

    private CustList<CustList<CustList<ActionMove>>> actionsBeforeRound;

    private CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingFront;

    private CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingBack;

    private boolean probleme;

    private StringList comment;

    private boolean ok;

    private EnvironmentType environment = EnvironmentType.ROAD;

    public FightSimulation(Difficulty _diff, DataBase _import) {
        game = new Game(_import);
        game.initUtilisateurSimulation(DataBase.EMPTY_STRING, null, _diff, _import);
        team = new CustList<PokemonPlayer>();
        items = new EqList<StringList>();
        evolutions = new CustList<EqList<NameLevel>>();
        frontFighters = new CustList<CustList<NumberMap<Byte,Byte>>>();
        mult = new Numbers<Byte>();
        maxActions = new Numbers<Integer>();
        infosRealEvolutions = new CustList<EqList<NameLevel>>();
        usedStones = new CustList<EqList<StringList>>();
        foeTeams = new CustList<CustList<PkTrainer>>();
        moves = new NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>>();
        abilities = new NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>>();
        movesBetweenFights = new NumberMap<Byte,CustList<EqList<StringList>>>();
        abilitiesBetweenFights = new NumberMap<Byte,CustList<EqList<StringList>>>();
        evolutionsBetweenFights = new NumberMap<Byte,EqList<StringList>>();
        keptAbilities = new NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>>();
        keptMoves = new NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>>();
        keptAbilitiesBetweenFights = new NumberMap<Byte,EqList<StringList>>();
        keptMovesBetweenFights = new NumberMap<Byte,CustList<EqList<StringList>>>();
        availableMoves = new NumberMap<Byte,AvailableMovesInfos>();
        availableMovesBetweenFights = new NumberMap<Byte,StringMap<Boolean>>();
        currentFights = new NumberMap<Byte,Byte>();
        actionsBeforeRound = new CustList<CustList<CustList<ActionMove>>>();
        actionsSubstitutingBack = new CustList<CustList<CustList<ActionSwitch>>>();
        actionsSubstitutingFront = new CustList<CustList<CustList<ActionSwitch>>>();
        movesAbilities = new CustList<CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>>>();
        evolutionsWhileFight = new NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>>();
    }

    public void initializeFights(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = _foeCoords;
        freeTeams = false;
        noFight = _index;
        Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
        maxActions.clear();
        mult.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        if (place_ instanceof League) {
            for (Level l: ((League)place_).getLevelsList()) {
                LevelLeague level_ = (LevelLeague) l;
                byte mult_ = level_.getTrainer().getMultiplicityFight();
                maxActions.add((int) mult_);
                mult.add(mult_);
                items.add(new StringList());
                usedStones.add(new EqList<StringList>());
                foeTeams.add(level_.getTrainer().getTeam());
            }
        } else {
            Level l_ = place_.getLevelByCoords(foeCoords);
            byte mult_;
            int nbMax_;
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                    DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                    mult_ = dual_.getFoeTrainer().getMultiplicityFight();
                    nbMax_ = 1;
                    foeTeams.add(dual_.getFoeTrainer().getTeam());
                } else {
                    TrainerMultiFights tr_ = (TrainerMultiFights) ((LevelWithWildPokemon)l_).getCharacters().getVal(foeCoords.getLevel().getPoint());
                    CustList<PkTrainer> team_ = tr_.getTeamsRewards().get(noFight).getTeam();
                    mult_ = tr_.getMultiplicityFight();
                    nbMax_ = mult_;
                    foeTeams.add(team_);
                }
            } else {
                if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                    GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                    mult_ = tr_.getMultiplicityFight();
                    foeTeams.add(tr_.getTeam());
                } else {
                    GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                    mult_ = tr_.getMultiplicityFight();
                    foeTeams.add(tr_.getTeam());
                }
                nbMax_ = mult_;
            }
            mult.add(mult_);
            maxActions.add(nbMax_);
            items.add(new StringList());
            usedStones.add(new EqList<StringList>());
        }
    }

    public void initializeFight(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = new Coords(_foeCoords);
        freeTeams = false;
        noFight = _index;
        Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
        maxActions.clear();
        mult.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        allyTeam.clear();
        if (place_ instanceof League) {
            LevelLeague l_ = (LevelLeague)((League)place_).getLevelsList().get(foeCoords.getLevel().getLevelIndex());
            foeCoords.getLevel().getPoint().affect(l_.getTrainerCoords());
            byte mult_ = l_.getTrainer().getMultiplicityFight();
            maxActions.add((int) mult_);
            mult.add(mult_);
            items.add(new StringList());
            usedStones.add(new EqList<StringList>());
            foeTeams.add(new CustList<PkTrainer>(l_.getTrainer().getTeam()));
        } else {
            Level l_ = place_.getLevelByCoords(foeCoords);
            byte mult_;
            int nbMax_;
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                    DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                    mult_ = dual_.getFoeTrainer().getMultiplicityFight();
                    nbMax_ = 1;
                    foeTeams.add(new CustList<PkTrainer>(dual_.getFoeTrainer().getTeam()));
                    allyTeam.addAllElts(dual_.getAlly().getTeam());
                } else {
                    TrainerMultiFights tr_ = (TrainerMultiFights) ((LevelWithWildPokemon)l_).getCharacters().getVal(foeCoords.getLevel().getPoint());
                    CustList<PkTrainer> team_ = tr_.getTeamsRewards().get(noFight).getTeam();
                    mult_ = tr_.getMultiplicityFight();
                    nbMax_ = mult_;
                    foeTeams.add(new CustList<PkTrainer>(team_));
                }
            } else {
                if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                    GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                    mult_ = tr_.getMultiplicityFight();
                    foeTeams.add(new CustList<PkTrainer>(tr_.getTeam()));
                } else {
                    GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                    mult_ = tr_.getMultiplicityFight();
                    foeTeams.add(new CustList<PkTrainer>(tr_.getTeam()));
                }
                nbMax_ = mult_;
            }
            mult.add(mult_);
            maxActions.add(nbMax_);
            items.add(new StringList());
            usedStones.add(new EqList<StringList>());
        }
    }

    public void setTeams(CustList<PkTrainer> _allyTeam, CustList<PkTrainer> _foeTeam, int _multiplicity, int _nbMaxActions, EnvironmentType _env, Coords _coords) {
        foeCoords = new Coords(_coords);
        environment = _env;
        freeTeams = true;
        maxActions.clear();
        mult.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        allyTeam.clear();
        allyTeam.addAllElts(_allyTeam);
        maxActions.add(_nbMaxActions);
        mult.add((byte) _multiplicity);
        items.add(new StringList());
        usedStones.add(new EqList<StringList>());
        foeTeams.add(_foeTeam);
    }

    public void nextFight(DataBase _import) {
        Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
        CustList<Level> list_ = ((League)place_).getLevelsList();
        byte index_ = foeCoords.getLevel().getLevelIndex();
        index_++;
        LevelLeague l_ = (LevelLeague)list_.get(index_);
        maxActions.clear();
        mult.clear();
        items.clear();
        moves.clear();
        abilities.clear();
        evolutions.clear();
        usedStones.clear();
        foeTeams.clear();
        foeCoords.getLevel().setLevelIndex(index_);
        foeCoords.getLevel().getPoint().affect(l_.getTrainerCoords());
        byte mult_ = l_.getTrainer().getMultiplicityFight();
        maxActions.add((int) mult_);
        mult.add(mult_);
        items.add(new StringList());
        usedStones.add(new EqList<StringList>());
        foeTeams.add(l_.getTrainer().getTeam());
    }

    public boolean hasNextFight(DataBase _import) {
        if (freeTeams) {
            return false;
        }
        Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
        if (!(place_ instanceof League)) {
            return false;
        }
        CustList<Level> list_ = ((League)place_).getLevelsList();
        byte index_ = foeCoords.getLevel().getLevelIndex();
        index_++;
        if (!list_.isValidIndex(index_)) {
            return false;
        }
        return true;
    }

    public static StringList possiblesInitialMoves(String _name, short _level, DataBase _import) {
        PokemonData data_ = _import.getPokemon(_name);
        String basePk_ = data_.getBaseEvo();
        StringMap<Short> evos_ = PokemonPlayer.getAllEvolutions(basePk_, _level, true, _import);
        StringList keys_ = new StringList(evos_.getKeys());
        StringList moves_ = new StringList();
//        StringList res_ = keys_.filter(PokemonPlayer.SEPARATOR+_name+END_REG_EXP);
        StringList res_ = keys_.filterEndsWith(StringList.concat(PokemonPlayer.SEPARATOR,_name));
        if (res_.isEmpty() || _level < evos_.getVal(res_.first())) {
            for (LevelMove p: data_.getLevMoves()) {
                if (p.getLevel() > _level) {
                    break;
                }
                moves_.add(p.getMove());
            }
            moves_.addAllElts(data_.getMoveTutors());
            for (Short m: data_.getHiddenMoves()) {
                moves_.add(_import.getHm().getVal(m));
            }
            for (Short m: data_.getTechnicalMoves()) {
                moves_.add(_import.getTm().getVal(m));
            }
            moves_.removeDuplicates();
            return moves_;
        }
        StringList path_ = StringList.splitStrings(res_.first(), PokemonPlayer.SEPARATOR);
        int index_ = CustList.FIRST_INDEX;
        int nbPaths_ = path_.size();
        for (int i = CustList.SECOND_INDEX; i < nbPaths_; i++) {
            String beforeEvo_ = path_.get(index_);
            PokemonData dataBeforeEvo_ = _import.getPokemon(beforeEvo_);
            short level_ = evos_.getVal(path_.mid(CustList.FIRST_INDEX, i + 1).join(PokemonPlayer.SEPARATOR));
            for (LevelMove p: dataBeforeEvo_.getLevMoves()) {
                if (p.getLevel() > level_) {
                    break;
                }
                moves_.add(p.getMove());
            }
            moves_.addAllElts(dataBeforeEvo_.getMoveTutors());
            for (Short m: dataBeforeEvo_.getHiddenMoves()) {
                moves_.add(_import.getHm().getVal(m));
            }
            for (Short m: dataBeforeEvo_.getTechnicalMoves()) {
                moves_.add(_import.getTm().getVal(m));
            }
            index_++;
        }
        for (LevelMove p: data_.getLevMoves()) {
            if (p.getLevel() > _level) {
                break;
            }
            moves_.add(p.getMove());
        }
        moves_.addAllElts(data_.getMoveTutors());
        for (Short m: data_.getHiddenMoves()) {
            moves_.add(_import.getHm().getVal(m));
        }
        for (Short m: data_.getTechnicalMoves()) {
            moves_.add(_import.getTm().getVal(m));
        }
        moves_.removeDuplicates();
        return moves_;
    }

    public void addPokemonPlayer(
            Pokemon _pokemon, StringList _initialMoves,
            byte _happiness, Rate _wonPointsExperienceSinceLastLevel, DataBase _import) {
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        for (String m: _initialMoves) {
            MoveData m_ = _import.getMove(m);
            moves_.put(m, m_.getPp());
        }
        PokemonPlayer pokemonPlayer_ = new PokemonPlayer(_pokemon, _import, moves_);
        pokemonPlayer_.setHappiness(_happiness);
        pokemonPlayer_.getWonExpSinceLastLevel().affect(_wonPointsExperienceSinceLastLevel);
        team.add(pokemonPlayer_);
        for (StringList l: items) {
            l.add(_pokemon.getItem());
        }
        evolutions.add(new EqList<NameLevel>());
    }

    public void setPokemonPlayerName(int _index, String _name) {
        team.get(_index).setName(_name);
    }

    public void setPokemonPlayerLevel(int _index, short _level) {
        team.get(_index).setLevel(_level);
    }

    public void setPokemonPlayerAbility(int _index, String _name) {
        team.get(_index).setAbility(_name);
    }

    public void setPokemonPlayerObject(int _index, String _object) {
        team.get(_index).setItem(_object);
        for (StringList l: items) {
            l.set(_index, _object);
        }
    }

    public void setPokemonPlayerObjectAfterFight(int _fight, int _index, String _object) {
        items.get(_fight + 1).set(_index, _object);
    }

    public void setPokemonPlayerGender(int _index, Gender _gender) {
        team.get(_index).setGender(_gender);
    }

    public void setInitialMoves(int _index, StringList _moves, DataBase _import) {
        team.get(_index).getMoves().clear();
        for (String s: _moves) {
            team.get(_index).learnMove(s, _import);
        }
    }

    public void removePokemonPlayer(int _index) {
        team.remove(_index);
        evolutions.remove(_index);
        for (StringList l: items) {
            l.remove(_index);
        }
    }

    public void validateTeam(DataBase _data) {
        game.getPlayer().getTeam().clear();
        for (PokemonPlayer p: team) {
            PokemonPlayer p_ = new PokemonPlayer();
            p_.setAbility(p.getAbility());
            p_.setName(p.getName());
            p_.setLevel(p.getLevel());
            p_.setGender(p.getGender());
            p_.setItem(p.getItem());
            p_.setHappiness(p.getHappiness());
            p_.getWonExpSinceLastLevel().affect(p.getWonExpSinceLastLevel());
            p_.getRemainingHp().affect(p.getRemainingHp());
            for (String m: p.getMoves().getKeys()) {
                UsesOfMove u_ = p.getMoves().getVal(m);
                p_.getMoves().put(m, new UsesOfMove(u_.getCurrent(), u_.getMax()));
            }
            for (Statistic s: p.getEv().getKeys()) {
                p_.getEv().put(s, p.getEv().getVal(s));
            }
            p_.initIv(game.getDifficulty());
            p_.initPvRestants(_data);
            game.getPlayer().getTeam().add(p_);
        }
        //game.getPlayer().getTeam().addAll(team);
    }

    CustList<StringMap<Short>> getFirstNextEvolutions(DataBase _import) {
        CustList<StringMap<Short>> list_ = new CustList<StringMap<Short>>();
        for (PokemonPlayer p: team) {
            StringMap<Short> evos_;
            evos_ = PokemonPlayer.getAllEvolutions(p.getName(), p.getLevel(), true, _import);
            StringList keys_ = new StringList(evos_.getKeys());
//            keys_ = keys_.filter(BEGIN_REG_EXP+p.getName()+END_WORD_REG_EXP);
            keys_ = getNextEvos(keys_, p.getName());
            StringMap<Short> direct_ = new StringMap<Short>();
            for (String k: keys_) {
                String prefix_ = StringList.concat(p.getName(),SEPARATOR_PK);
                String rep_ = StringList.replaceBegin(k, prefix_);
                direct_.put(rep_, evos_.getVal(k));
//                direct_.put(k.replaceAll(BEGIN_REG_EXP+p.getName()+SEPARATOR_PK, DataBase.EMPTY_STRING), evos_.getVal(k));
            }
            list_.add(direct_);
        }
        return list_;
    }

    public void cancelEvolutions() {
        availableEvolutions.clear();
        evolutions.clear();
    }

    /**Initializing of choices*/
    public void setFirstEvolutions(DataBase _import) {
        availableEvolutions = getFirstNextEvolutions(_import);
        evolutions.clear();
        int nbAvailableEvolutions_ = availableEvolutions.size();
        for (int i = CustList.FIRST_INDEX; i < nbAvailableEvolutions_; i++) {
            evolutions.add(new EqList<NameLevel>());
        }
    }

    public void setNextEvolutions(int _index, String _currentEvo, short _level, DataBase _import) {
        if (StringList.quickEq(_currentEvo, DataBase.EMPTY_STRING)) {
            return;
        }
        StringMap<Short> nextEvos_;
        nextEvos_ = PokemonPlayer.getAllEvolutions(_currentEvo, _level, true, _import);
        StringList keys_ = new StringList(nextEvos_.getKeys());
//        keys_ = keys_.filter(BEGIN_REG_EXP+_currentEvo+END_WORD_REG_EXP);
        keys_ = getNextEvos(keys_, _currentEvo);
        StringMap<Short> direct_ = new StringMap<Short>();
        for (String k: keys_) {
            String prefix_ = StringList.concat(_currentEvo,SEPARATOR_PK);
            String rep_ = StringList.replaceBegin(k, prefix_);
            direct_.put(rep_, nextEvos_.getVal(k));
//            direct_.put(k.replaceAll(BEGIN_REG_EXP+_currentEvo+SEPARATOR_PK, DataBase.EMPTY_STRING), nextEvos_.getVal(k));
        }
        availableEvolutions.get(_index).clear();
        availableEvolutions.get(_index).putAllMap(direct_);
        evolutions.get(_index).add(new NameLevel(_currentEvo, _level));
    }

    public void cancelEvolutions(int _index, DataBase _import) {
        int index_ = evolutions.get(_index).size();
        index_--;
        cancelEvolutions(_index, index_, _import);
    }

    void cancelEvolutions(int _index, int _indexOfCancel, DataBase _import) {
        if (_indexOfCancel <= CustList.FIRST_INDEX) {
            evolutions.get(_index).clear();
            availableEvolutions.get(_index).clear();
            availableEvolutions.get(_index).putAllMap(getFirstNextEvolutions(_import).get(_index));
            return;
        }
        NameLevel previous_;
        previous_ = evolutions.get(_index).get(_indexOfCancel - 1);
        StringMap<Short> nextEvos_;
        nextEvos_ = PokemonPlayer.getAllEvolutions(previous_.getName(), previous_.getLevel(), true, _import);
        StringList keys_ = new StringList(nextEvos_.getKeys());
//        keys_ = keys_.filter(BEGIN_REG_EXP+previous_.getFirst()+END_WORD_REG_EXP);
        keys_ = getNextEvos(keys_, previous_.getName());
        StringMap<Short> direct_ = new StringMap<Short>();
        for (String k: keys_) {
            String prefix_ = StringList.concat(previous_.getName(),SEPARATOR_PK);
            String rep_ = StringList.replaceBegin(k, prefix_);
            direct_.put(rep_, nextEvos_.getVal(k));
//            direct_.put(k.replaceAll(BEGIN_REG_EXP+previous_.getFirst()+SEPARATOR_PK, DataBase.EMPTY_STRING), nextEvos_.getVal(k));
        }
        availableEvolutions.get(_index).clear();
        availableEvolutions.get(_index).putAllMap(direct_);
        while (_indexOfCancel < evolutions.get(_index).size()) {
            evolutions.get(_index).remove(_indexOfCancel);
        }
    }

    private static StringList getNextEvos(StringList _evos, String _word) {
        StringList list_ = new StringList();
        for (String e : _evos) {
            StringList sep_ = StringList.getWordsSeparators(e);
            if (sep_.size() != 4) {
                continue;
            }
            if (!sep_.first().isEmpty()) {
                continue;
            }
            if (!StringList.quickEq(sep_.get(1), _word)) {
                continue;
            }
            if (!StringList.quickEq(sep_.get(2), SEPARATOR_PK)) {
                continue;
            }
            list_.add(e);
        }
        return list_;
    }

    public void initializeFrontFighters() {
        frontFighters.clear();
        int nb_ = foeTeams.size();
        for (int k = CustList.FIRST_INDEX; k < nb_; k++) {
            CustList<NumberMap<Byte,Byte>> rounds_;
            rounds_ = new CustList<NumberMap<Byte,Byte>>();
            int nbRounds_ = nbRound(mult.get(k), foeTeams.get(k).size());
            for (int i = CustList.FIRST_INDEX; i < nbRounds_; i++) {
                NumberMap<Byte,Byte> round_;
                round_ = new NumberMap<Byte,Byte>();
                int nbMembers_ = team.size();
                for (byte j = CustList.FIRST_INDEX; j < nbMembers_; j++) {
                    round_.put(j, Fighter.BACK);
                }
                rounds_.add(round_);
            }
            frontFighters.add(rounds_);
        }
    }

    public void prepareMovesToBeLearntOneFight(DataBase _import) {
        ok = true;
        if (!validFrontFighters()) {
            ok = false;
            return;
        }
        PseudoPlayer pseudoPlayer_ = new PseudoPlayer(team, evolutions);
        pseudoPlayer_.setItems(items);
        currentFights.clear();
        moves.clear();
        availableMoves.clear();
        keptMoves.clear();
        evolutionsWhileFight.clear();
        abilities.clear();
        keptAbilities.clear();
        infosRealEvolutions.clear();
        int nb_ = team.size();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            infosRealEvolutions.add(new EqList<NameLevel>());
        }
        nb_ = foeTeams.size();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            PseudoFight pseudoFight_;
            pseudoFight_ = new PseudoFight(foeTeams.get(i), pseudoPlayer_, mult.get(i), frontFighters.get(i));
            pseudoFight_.presimulateFight(game.getDifficulty(), _import);
            //Numbers<Byte> indexes_ = indexesFight(i);
            byte i_ = CustList.FIRST_INDEX;
            for (PseudoPlayerFighter f: pseudoFight_.getPlayerFighters()) {
                infosRealEvolutions.get(i_).addAllElts(f.getInfosRealEvolutions());
                pseudoPlayer_.getEvolutions().get(i_).clear();
                pseudoPlayer_.getEvolutions().get(i_).addAllElts(f.getEvoLevels());
                pseudoPlayer_.getTeam().get(i_).setLevel(f.getLevel());
                pseudoPlayer_.getTeam().get(i_).setName(f.getName());
                pseudoPlayer_.getTeam().get(i_).getWonPointsSinceLastLevel().affect(f.getWonExpSinceLastLevel());
                TreeMap<PairNumber<Byte,Byte>,String> treeEvos_;
//                treeEvos_ = new TreeMap<Pair<Byte,Byte>,String>(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
                treeEvos_ = new TreeMap<PairNumber<Byte,Byte>,String>(new ComparatorPairNumber<Byte,Byte>());
                for (byte o: f.getEvolutions().getKeys()) {
                    treeEvos_.put(new PairNumber<Byte, Byte>(i, o), f.getEvolutions().getVal(o));
                }
                evolutionsWhileFight.put(i_, treeEvos_);
//                if (moves.contains(indexes_.get(i_))) {
                TreeMap<PairNumber<Byte,Byte>,StringList> treeMoves_;
//                treeMoves_ = new TreeMap<Pair<Byte,Byte>,StringList>(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
                treeMoves_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                byte iRound_ = CustList.FIRST_INDEX;
                for (StringList o: f.getMoves()) {
                    treeMoves_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                    iRound_++;
                }
//                moves.put(indexes_.get(i_), tree_);
                moves.put(i_, treeMoves_);
                StringMap<Boolean> choicesMoves_;
                StringList initMoves_ = new StringList(team.get(i_).getMoves().getKeys());
                choicesMoves_ = movesToBeChosen(treeMoves_.firstEntry().getValue(), initMoves_, _import.getNbMaxMoves());
                PairNumber<Byte,Byte> firstFightRound_;
                firstFightRound_ = new PairNumber<Byte, Byte>(CustList.FIRST_INDEX, CustList.FIRST_INDEX);
//                availableMoves.put(indexes_.get(i_), new Pair<>(firstFightRound_,choicesMoves_));
                availableMoves.put(i_, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
//                treeMoves_ = new TreeMap<Pair<Byte,Byte>,StringList>(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
                treeMoves_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                treeMoves_.put(firstFightRound_, new StringList(initMoves_));
//                keptMoves.put(indexes_.get(i_), tree_);
                keptMoves.put(i_, treeMoves_);
//                if (abilities.contains(indexes_.get(i_))) {
                TreeMap<PairNumber<Byte,Byte>,StringList> treeAbilities_;
//                treeAbilities_ = new TreeMap<Pair<Byte,Byte>,StringList>(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
                treeAbilities_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                iRound_ = CustList.FIRST_INDEX;
                for (StringList o: f.getAbilities()) {
                    if (!o.isEmpty()) {
                        treeAbilities_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                    }
                    iRound_++;
                }
//                abilities.put(indexes_.get(i_), tree_);
                abilities.put(i_, treeAbilities_);
                TreeMap<PairNumber<Byte,Byte>,String> treeKept_;
//                treeKept_ = new TreeMap<Pair<Byte,Byte>,String>(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
                treeKept_ = new TreeMap<PairNumber<Byte,Byte>,String>(new ComparatorPairNumber<Byte,Byte>());
                for (PairNumber<Byte, Byte> k: treeAbilities_.getKeys()) {
                    treeKept_.put(new PairNumber<Byte, Byte>(k), treeAbilities_.getVal(k).first());
                }
//                keptAbilities.put(indexes_.get(i_), treeKept_);
                keptAbilities.put(i_, treeKept_);
                currentFights.put(i_, (byte) 0);
                i_++;
            }
        }
    }

    public void initializeAllMoves(DataBase _import) {
        for (byte k: moves.getKeys()) {
            StringList list_ = new StringList(team.get(k).getMoves().getKeys());
            keepMoves(k, list_, _import);
        }
        validateAllMoves(_import);
    }

    public void prepareMovesToBeLearnt(DataBase _import) {
        ok = true;
        if (!validFrontFighters()) {
            ok = false;
            return;
        }
        PseudoPlayer pseudoPlayer_ = new PseudoPlayer(team, evolutions);
        pseudoPlayer_.setItems(items);
        currentFights.clear();
        infosRealEvolutions.clear();
        int nb_ = team.size();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            infosRealEvolutions.add(new EqList<NameLevel>());
        }
        nb_ = foeTeams.size();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            int index_ = i;
            index_++;
            PseudoFight pseudoFight_;
            pseudoFight_ = new PseudoFight(foeTeams.get(i), pseudoPlayer_, mult.get(i), frontFighters.get(i));
            pseudoFight_.presimulateFight(game.getDifficulty(), _import);
            //Numbers<Byte> indexes_ = indexesFight(i);
            byte i_ = CustList.FIRST_INDEX;
            for (PseudoPlayerFighter f: pseudoFight_.getPlayerFighters()) {
                infosRealEvolutions.get(i_).addAllElts(f.getInfosRealEvolutions());
                pseudoPlayer_.getEvolutions().get(i_).clear();
                pseudoPlayer_.getEvolutions().get(i_).addAllElts(f.getEvoLevels());
                pseudoPlayer_.getTeam().get(i_).setLevel(f.getLevel());
                pseudoPlayer_.getTeam().get(i_).setName(f.getName());
                pseudoPlayer_.getTeam().get(i_).getWonPointsSinceLastLevel().affect(f.getWonExpSinceLastLevel());
                if (evolutionsWhileFight.contains(i_)) {
                    TreeMap<PairNumber<Byte,Byte>,String> tree_;
                    tree_ = evolutionsWhileFight.getVal(i_);
                    for (byte o: f.getEvolutions().getKeys()) {
                        tree_.put(new PairNumber<Byte, Byte>(i, o), f.getEvolutions().getVal(o));
                    }
                } else {
                    TreeMap<PairNumber<Byte,Byte>,String> tree_;
                    tree_ = new TreeMap<PairNumber<Byte,Byte>,String>(new ComparatorPairNumber<Byte,Byte>());
                    for (byte o: f.getEvolutions().getKeys()) {
                        tree_.put(new PairNumber<Byte, Byte>(i, o), f.getEvolutions().getVal(o));
                    }
                    evolutionsWhileFight.put(i_, tree_);
                }
//                if (moves.contains(indexes_.get(i_))) {
                if (moves.contains(i_)) {
                    TreeMap<PairNumber<Byte,Byte>,StringList> tree_;
//                    tree_ = moves.getVal(indexes_.get(i_));
                    tree_ = moves.getVal(i_);
                    byte iRound_ = CustList.FIRST_INDEX;
                    for (StringList o: f.getMoves()) {
                        tree_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                        iRound_++;
                    }
                } else {
                    TreeMap<PairNumber<Byte,Byte>,StringList> tree_;
                    tree_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                    byte iRound_ = CustList.FIRST_INDEX;
                    for (StringList o: f.getMoves()) {
                        tree_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                        iRound_++;
                    }
//                    moves.put(indexes_.get(i_), tree_);
                    moves.put(i_, tree_);
                    StringMap<Boolean> choicesMoves_;
                    StringList initMoves_ = new StringList(team.get(i_).getMoves().getKeys());
                    choicesMoves_ = movesToBeChosen(tree_.firstEntry().getValue(), initMoves_, _import.getNbMaxMoves());
                    PairNumber<Byte,Byte> firstFightRound_;
                    firstFightRound_ = new PairNumber<Byte, Byte>(CustList.FIRST_INDEX, CustList.FIRST_INDEX);
//                    availableMoves.put(indexes_.get(i_), new Pair<>(firstFightRound_,choicesMoves_));
                    availableMoves.put(i_, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
                    tree_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                    tree_.put(firstFightRound_, new StringList(initMoves_));
//                    keptMoves.put(indexes_.get(i_), tree_);
                    keptMoves.put(i_, tree_);
                }
//                if (abilities.contains(indexes_.get(i_))) {
                if (abilities.contains(i_)) {
                    TreeMap<PairNumber<Byte,Byte>,StringList> tree_;
//                    tree_ = abilities.getVal(indexes_.get(i_));
                    tree_ = abilities.getVal(i_);
                    TreeMap<PairNumber<Byte,Byte>,String> treeKept_;
                    treeKept_ = keptAbilities.getVal(i_);
                    byte iRound_ = CustList.FIRST_INDEX;
                    for (StringList o: f.getAbilities()) {
                        if (!o.isEmpty()) {
                            tree_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                            treeKept_.put(new PairNumber<Byte, Byte>(i, iRound_), o.first());
                        }
                        iRound_++;
                    }
                } else {
                    TreeMap<PairNumber<Byte,Byte>,StringList> tree_;
                    tree_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
                    byte iRound_ = CustList.FIRST_INDEX;
                    for (StringList o: f.getAbilities()) {
                        if (!o.isEmpty()) {
                            tree_.put(new PairNumber<Byte, Byte>(i, iRound_), o);
                        }
                        iRound_++;
                    }
//                    abilities.put(indexes_.get(i_), tree_);
                    abilities.put(i_, tree_);
                    TreeMap<PairNumber<Byte,Byte>,String> treeKept_;
                    treeKept_ = new TreeMap<PairNumber<Byte,Byte>,String>(new ComparatorPairNumber<Byte,Byte>());
                    for (PairNumber<Byte, Byte> k: tree_.getKeys()) {
                        treeKept_.put(new PairNumber<Byte, Byte>(k), tree_.getVal(k).first());
                    }
//                    keptAbilities.put(indexes_.get(i_), treeKept_);
                    keptAbilities.put(i_, treeKept_);
                }
                currentFights.put(i_, (byte) 0);
                i_++;
            }
            i_ = CustList.FIRST_INDEX;
            EqList<StringList> usedStones_;
            usedStones_ = new EqList<StringList>();
            for (PseudoPokemonPlayer p: pseudoPlayer_.getTeam()) {
                StringList nextEvos_;
                nextEvos_ = new StringList();
                boolean add_ = false;
                PokemonData data_ = _import.getPokemon(p.getName());
                for (NameLevel p2_: pseudoPlayer_.getEvolutions().get(i_)) {
                    if (data_.getEvolutions().contains(p2_.getName())) {
                        add_ = true;
                    }
                    if (!add_) {
                        continue;
                    }
                    if (!Numbers.eq(p2_.getLevel(), p.getLevel())) {
                        continue;
                    }
                    nextEvos_.add(p2_.getName());
                }
                StringList usedStonesPokemon_;
                usedStonesPokemon_ = new StringList();
                CustList<EqList<StringList>> listMoves_;
                CustList<EqList<StringList>> listKeptMoves_;
                if (movesBetweenFights.contains(i_)) {
                    listMoves_ = movesBetweenFights.getVal(i_);
                    listKeptMoves_ = keptMovesBetweenFights.getVal(i_);
                } else {
                    listMoves_ = new CustList<EqList<StringList>>();
                    listKeptMoves_ = new CustList<EqList<StringList>>();
                }
                CustList<EqList<StringList>> listAbilities_;
                EqList<StringList> listKeptAbilities_;
                EqList<StringList> listEvolutions_;
                if (abilitiesBetweenFights.contains(i_)) {
                    listAbilities_ = abilitiesBetweenFights.getVal(i_);
                    listKeptAbilities_ = keptAbilitiesBetweenFights.getVal(i_);
                } else {
                    listAbilities_ = new CustList<EqList<StringList>>();
                    listKeptAbilities_ = new EqList<StringList>();
                }
                if (evolutionsBetweenFights.contains(i_)) {
                    listEvolutions_ = evolutionsBetweenFights.getVal(i_);
                } else {
                    listEvolutions_ = new EqList<StringList>();
                }
                EqList<StringList> groupsMoves_;
                groupsMoves_ = new EqList<StringList>();
                EqList<StringList> groupsAbilities_;
                groupsAbilities_ = new EqList<StringList>();
                StringList groupsKeptAbilities_;
                groupsKeptAbilities_ = new StringList();
                StringList groupsEvolutions_;
                groupsEvolutions_ = new StringList();
                for (String e: nextEvos_) {
                    if (!(data_.getEvolutions().getVal(e) instanceof EvolutionStone)) {
                        break;
                    }
                    usedStonesPokemon_.add(((EvolutionStone)data_.getEvolutions().getVal(e)).getStone());
                    p.setName(e);
                    infosRealEvolutions.get(i_).add(new NameLevel(e,p.getLevel()));
                    data_ = _import.getPokemon(e);
                    StringList moves_;
                    moves_ = new StringList();
                    for (LevelMove p2_: data_.getLevMoves()) {
                        if (p2_.getLevel() > p.getLevel()) {
                            continue;
                        }
                        moves_.add(p2_.getMove());
                    }
                    moves_.sort();
                    groupsMoves_.add(moves_);
                    groupsAbilities_.add(new StringList(data_.getAbilities()));
                    groupsAbilities_.last().sort();
                    groupsKeptAbilities_.add(data_.getAbilities().first());
                    groupsEvolutions_.add(e);
                }
                listMoves_.add(groupsMoves_);
                listKeptMoves_.add(new EqList<StringList>());
                listAbilities_.add(groupsAbilities_);
                listKeptAbilities_.add(groupsKeptAbilities_);
                listEvolutions_.add(groupsEvolutions_);
                movesBetweenFights.put(i_, listMoves_);
                keptMovesBetweenFights.put(i_, listKeptMoves_);
                abilitiesBetweenFights.put(i_, listAbilities_);
                keptAbilitiesBetweenFights.put(i_, listKeptAbilities_);
                evolutionsBetweenFights.put(i_, listEvolutions_);
                usedStones_.add(usedStonesPokemon_);
                i_++;
            }
            usedStones.set(i, usedStones_);
            if (index_ >= items.size()) {
                break;
            }
            i_ = CustList.FIRST_INDEX;
            for (PseudoPokemonPlayer p: pseudoPlayer_.getTeam()) {
                p.setItem(items.get(index_).get(i_));
                i_++;
            }
        }

//        currentKeys.clear();
//        int i_ = CustList.FIRST_INDEX;
//        for (PokemonPlayer p: team) {
//            CustList<Pair<String,Short>> l_ = realEvolutions.get(i_);
//            CustList<Pair<Pair<String,Short>, Pair<StringList,StringList>>> movesAbilities_;
//            movesAbilities_ = new CustList<>();
//            int maxLevelBase_ = pseudoPlayer_.getTeam().get(i_).getLevel();
//            if (!l_.isEmpty()) {
//                maxLevelBase_ = l_.first().getSecond();
//            }
//            String name_ = p.getName();
//            PokemonData data_ = _import.getPokemon(name_);
//            Map<Short,StringList> moves_;
//            moves_ = new Map<>();
//            for (Pair<Short,String> p2: data_.getLevMoves()) {
//                if (p2.getFirst() > maxLevelBase_) {
//                    continue;
//                }
//                if (p2.getFirst() < p.getLevel()) {
//                    continue;
//                }
//                if (moves_.contains(p2.getFirst())) {
//                    moves_.getVal(p2.getFirst()).add(p2.getSecond());
//                } else {
//                    moves_.put(p2.getFirst(), new StringList(p2.getSecond()));
//                }
//            }
//            Numbers<Short> keys_ = new Numbers<>(moves_.getKeys());
//            keys_.sort();
//            for (Short k: keys_) {
//                movesAbilities_.add(new Pair<>(new Pair<>(name_,k), new Pair<>(moves_.getVal(k), new StringList())));
//            }
//            if (!l_.isEmpty()) {
//                Pair<String,Short> first_ = l_.first();
//                String nameEvo_ = first_.getFirst();
//                short minLevel_ = first_.getSecond();
//                for (int i = CustList.SECOND_INDEX; i < l_.size(); i++) {
//                    short maxLevel_ = l_.get(i).getSecond();
//                    PokemonData dataEvo_ = _import.getPokemon(nameEvo_);
//                    moves_ = new Map<>();
//                    for (Pair<Short,String> p2: dataEvo_.getLevMoves()) {
//                        if (p2.getFirst() > maxLevel_) {
//                            continue;
//                        }
//                        if (moves_.contains(p2.getFirst())) {
//                            moves_.getVal(p2.getFirst()).add(p2.getSecond());
//                        } else {
//                            moves_.put(p2.getFirst(), new StringList(p2.getSecond()));
//                        }
//                    }
//                    keys_ = new Numbers<>(moves_.getKeys());
//                    keys_.sort();
//                    if (!keys_.containsObj(minLevel_)) {
//                        movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,minLevel_), new Pair<>(new StringList(), dataEvo_.getAbilities())));
//                    } else {
//                        movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,minLevel_), new Pair<>(moves_.getVal(minLevel_), dataEvo_.getAbilities())));
//                        keys_.removeObj(minLevel_);
//                    }
//                    for (Short k: keys_) {
//                        movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,k), new Pair<>(moves_.getVal(k), new StringList())));
//                    }
//                    nameEvo_ = l_.get(i).getFirst();
//                    minLevel_ = l_.get(i).getSecond();
//                }
//                maxLevelBase_ = pseudoPlayer_.getTeam().get(i_).getLevel();
//                PokemonData dataEvo_ = _import.getPokemon(nameEvo_);
//                moves_ = new Map<>();
//                for (Pair<Short,String> p2: dataEvo_.getLevMoves()) {
//                    if (p2.getFirst() > maxLevelBase_) {
//                        continue;
//                    }
//                    if (moves_.contains(p2.getFirst())) {
//                        moves_.getVal(p2.getFirst()).add(p2.getSecond());
//                    } else {
//                        moves_.put(p2.getFirst(), new StringList(p2.getSecond()));
//                    }
//                }
//                keys_ = new Numbers<>(moves_.getKeys());
//                keys_.sort();
//                if (!keys_.containsObj(minLevel_)) {
//                    movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,minLevel_), new Pair<>(new StringList(), dataEvo_.getAbilities())));
//                } else {
//                    movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,minLevel_), new Pair<>(moves_.getVal(minLevel_), dataEvo_.getAbilities())));
//                    keys_.removeObj(minLevel_);
//                }
//                for (Short k: keys_) {
//                    movesAbilities_.add(new Pair<>(new Pair<>(nameEvo_,k), new Pair<>(moves_.getVal(k), new StringList())));
//                }
//            }
//            i_++;
//            availableMovesAbilities.add(movesAbilities_);
//        }
    }

    boolean validFrontFighters() {
        int index_ = CustList.FIRST_INDEX;
        for (CustList<NumberMap<Byte,Byte>> r: frontFighters) {
            int nbActions_ = maxActions.get(index_);
            for (NumberMap<Byte,Byte> r2_: r) {
                Numbers<Byte> front_ = new Numbers<Byte>();
                Numbers<Byte> places_ = new Numbers<Byte>();
                for (byte f: r2_.getKeys()) {
                    byte a_ = r2_.getVal(f);
                    if (Numbers.eq(a_, Fighter.BACK)) {
                        continue;
                    }
                    places_.add(a_);
                    front_.add(f);
                }
                int size_ = places_.size();
                places_.removeDuplicates();
                if (size_ > places_.size()) {
                    return false;
                }
                if (places_.size() > mult.get(index_)) {
                    return false;
                }
                Numbers<Byte> copyPlaces_ = new Numbers<Byte>(places_);
                copyPlaces_.sort();
                if (copyPlaces_.getMinimum().intValue() != 0) {
                    return false;
                }
                if (copyPlaces_.getMaximum().intValue() != copyPlaces_.size() - 1) {
                    return false;
                }
                if (front_.size() > nbActions_) {
                    return false;
                }
            }
            index_++;
        }
        return true;
    }

    public byte getFirstMult() {
        return mult.first();
    }

    public byte nbRounds() {
        return nbRound(mult.first(), foeTeams.first().size());
    }

    static byte nbRound(byte _mult, int _nbFoes) {
        byte nbRounds_ = (byte) (_nbFoes / _mult);
        if (_nbFoes % _mult > 0) {
            nbRounds_++;
        }
        return nbRounds_;
    }

    Numbers<Byte> indexesFight(byte _fight) {
        NumberMap<Byte,Byte> fighters_;
        fighters_ = frontFighters.get(_fight).first();
//        Map<Byte,Byte> map_;
//        map_ = new Map<>(fighters_);
//        for (Byte k: fighters_.getKeys()) {
//            map_.put(k, fighters_.getVal(k));
//        }
        Numbers<Byte> indexes_;
        indexes_ = new Numbers<Byte>();
        Numbers<Byte> indexesBack_;
        indexesBack_ = new Numbers<Byte>();
        for (EntryCust<Byte, Byte> e: fighters_.entryList()) {
            if (!Numbers.eq(e.getValue().byteValue(), Fighter.BACK)) {
                indexes_.add(e.getKey());
            } else {
                indexesBack_.add(e.getKey());
            }
        }
        indexesBack_.sort();
        indexes_.addAllElts(indexesBack_);
//        TreeMap<Byte,CustList<Byte>> reverseMap_;
////        reverseMap_ = new TreeMap<Byte, CustList<Byte>>(map_.reverseMap());
//        reverseMap_ = new TreeMap<Byte, CustList<Byte>>(fighters_.reverseMap());
//        for (byte k: reverseMap_.getKeys()) {
//            if (Numbers.eq(k, Fighter.BACK)) {
//                continue;
//            }
//            indexes_.add(reverseMap_.getVal(k).first());
//        }
//        if (reverseMap_.contains(Fighter.BACK)) {
//            Numbers<Byte> indexesBack_ = new Numbers<>(reverseMap_.getVal(Fighter.BACK));
//            indexesBack_.sort();
//            indexes_.addAllElts(indexesBack_);
//        }
        return indexes_;
    }

    public void keepMoves(int _index, StringList _moves, DataBase _import) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        PairNumber<Byte,Byte> nextKey_;
        nextKey_ = new PairNumber<Byte, Byte>(key_);
        nextKey_.setSecond((byte) (nextKey_.getSecond()+1));
        TreeMap<PairNumber<Byte,Byte>, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(nextKey_)) {
            availableMoves.getVal((byte) _index).getMoves().clear();
            StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
            moves_.clear();
            moves_.addAllElts(_moves);
            return;
        }
        for (String s: availableMoves.getVal((byte) _index).getMoves().getKeys()) {
            availableMoves.getVal((byte) _index).getMoves().put(s, false);
        }
        for (String s: _moves) {
            availableMoves.getVal((byte) _index).getMoves().put(s, true);
        }
        StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
        moves_.clear();
        moves_.addAllElts(_moves);
        validateMovesOneFight(_index, _import);
    }

    public boolean isAvailableMoves(int _index) {
        return !availableMoves.getVal((byte) _index).getMoves().isEmpty();
    }

    public boolean isAvailableAbilities(int _index) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).contains(key_);
    }

    public StringList getAvailableAbilities(int _index) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).getVal(key_);
    }

    void addMove(int _index, String _move) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        availableMoves.getVal((byte) _index).getMoves().put(_move, true);
        StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
        moves_.add(_move);
    }

    void addMoveBetweenFights(int _index, int _indexStone, String _move) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        availableMovesBetweenFights.getVal((byte) _index).put(_move, true);
        StringList moves_ = keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).get(_indexStone);
        moves_.add(_move);
    }

    void deleteMove(int _index, String _move) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        availableMoves.getVal((byte) _index).getMoves().put(_move, false);
        StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
        moves_.removeObj(_move);
    }

    void deleteMoveBetweenFights(int _index, int _indexStone, String _move) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        availableMovesBetweenFights.getVal((byte) _index).put(_move, false);
        StringList moves_ = keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).get(_indexStone);
        moves_.removeObj(_move);
    }

    public void cancelAllMovesOneFight(int _index, DataBase _import) {
        TreeMap<PairNumber<Byte,Byte>, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
//        byte iRound_ = CustList.FIRST_INDEX;
        StringMap<Boolean> choicesMoves_;
        StringList initMoves_ = new StringList(team.get((byte) _index).getMoves().getKeys());
        choicesMoves_ = movesToBeChosen(tree_.firstEntry().getValue(), initMoves_, _import.getNbMaxMoves());
        PairNumber<Byte,Byte> firstFightRound_;
        firstFightRound_ = new PairNumber<Byte, Byte>(CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        availableMoves.put((byte) _index, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
//        availableMoves.put(indexes_.get(i_), new Pair<>(firstFightRound_,choicesMoves_));
        //tree_.put(firstFightRound_, new StringList(initMoves_));
//        keptMoves.put(indexes_.get(i_), tree_);
        tree_ = new TreeMap<PairNumber<Byte,Byte>,StringList>(new ComparatorPairNumber<Byte,Byte>());
        tree_.put(firstFightRound_, new StringList(initMoves_));
        keptMoves.put((byte) _index, tree_);
    }

    public void cancelMovesOneFight(int _index, DataBase _import) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        PairNumber<Byte,Byte> previousKey_;
        previousKey_ = new PairNumber<Byte, Byte>(key_);
        previousKey_.setSecond((byte) (previousKey_.getSecond()-1));
        PairNumber<Byte,Byte> previousPrevKey_;
        previousPrevKey_ = new PairNumber<Byte, Byte>(previousKey_);
        previousPrevKey_.setSecond((byte) (previousPrevKey_.getSecond()-1));
        TreeMap<PairNumber<Byte,Byte>, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(previousPrevKey_)) {
            //keptMoves.getVal((byte) _index).getVal(previousKey_).clear();
            StringMap<Boolean> choicesMoves_;
            StringList initMoves_= new StringList(team.get(_index).getMoves().getKeys());
//            choicesMoves_ = movesToBeChosen(tree_.firstEntry().getValue(), initMoves_, _import.getNbMaxMoves());
//            Pair<Byte,Byte> firstFightRound_;
//            firstFightRound_ = new Pair<>((byte)CustList.FIRST_INDEX, (byte)CustList.FIRST_INDEX);
            //initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
            choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(previousKey_);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            keptMoves.getVal((byte) _index).put(previousKey_, getChosenMoves(choicesMoves_));
            keptMoves.getVal((byte) _index).getVal(key_).clear();
//            availableMoves.put(indexes_.get(i_), new Pair<>(firstFightRound_,choicesMoves_));
//            availableMoves.put((byte) CustList.FIRST_INDEX, new Pair<>(firstFightRound_,choicesMoves_));
            return;
        }
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(previousPrevKey_);
        StringMap<Boolean> choicesMoves_;
        choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(previousKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        //keptMoves.getVal((byte) _index)
        keptMoves.getVal((byte) _index).getVal(key_).clear();
    }

    void validateMovesOneFight(int _index, DataBase _import) {
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        PairNumber<Byte,Byte> nextKey_;
        nextKey_ = new PairNumber<Byte, Byte>(key_);
        nextKey_.setSecond((byte) (nextKey_.getSecond()+1));
        TreeMap<PairNumber<Byte,Byte>, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
        StringMap<Boolean> choicesMoves_;
        choicesMoves_ = movesToBeChosen(tree_.getVal(nextKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(nextKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        keptMoves.getVal((byte) _index).put(nextKey_, getChosenMoves(choicesMoves_));
    }

    public void validateMoves(int _index, DataBase _import) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        PairNumber<Byte,Byte> key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        PairNumber<Byte,Byte> nextKey_;
        nextKey_ = new PairNumber<Byte, Byte>(key_);
        nextKey_.setSecond((byte) (nextKey_.getSecond()+1));
        boolean betweenFights_= true;
        TreeMap<PairNumber<Byte,Byte>, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        for (PairNumber<Byte,Byte> k: tree_.getKeys()) {
            if (k == null) {
                continue;
            }
            if (k.getFirst() == null) {
                continue;
            }
            if (k.getSecond() == null) {
                continue;
            }
            if (Numbers.eq(k.getFirst().byteValue(), nextKey_.getFirst().byteValue())) {
                if (Numbers.eq(k.getSecond().byteValue(), nextKey_.getSecond().byteValue())) {
                    betweenFights_ = false;
                }
            }
//            if (Pair.eq(k, nextKey_)) {
//                betweenFights_ = false;
//            }
        }
        if (!betweenFights_) {
            StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
            StringMap<Boolean> choicesMoves_;
            choicesMoves_ = movesToBeChosen(tree_.getVal(nextKey_), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(nextKey_);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            keptMoves.getVal((byte) _index).put(nextKey_, getChosenMoves(choicesMoves_));
            return;
        }
        if (movesBetweenFights.getVal((byte) _index).get(currentFight_).isEmpty()) {
            StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
            currentFight_++;
            nextKey_.setFirst(currentFight_);
            nextKey_.setSecond(CustList.FIRST_INDEX);
            StringMap<Boolean> choicesMoves_;
            choicesMoves_ = movesToBeChosen(tree_.getVal(nextKey_), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(nextKey_);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            keptMoves.getVal((byte) _index).put(nextKey_, getChosenMoves(choicesMoves_));
            currentFights.put((byte) _index, currentFight_);
            return;
        }
        if (keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).isEmpty()) {
            StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
            StringList movesToBeLearnt_ = movesBetweenFights.getVal((byte) _index).get(currentFight_).first();
            StringMap<Boolean> choicesMoves_;
            choicesMoves_ = movesToBeChosen(movesToBeLearnt_, initMoves_, _import.getNbMaxMoves());
            keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).add(getChosenMoves(choicesMoves_));

            availableMovesBetweenFights.removeKey((byte) _index);
            availableMovesBetweenFights.put((byte) _index, choicesMoves_);

//            if (!availableMovesBetweenFights.contains((byte) _index)) {
//                availableMovesBetweenFights.put((byte) _index, choicesMoves_);
//            } else {
//                availableMovesBetweenFights.getVal((byte) _index).clear();
//                availableMovesBetweenFights.getVal((byte) _index).putAllMap(choicesMoves_);
//            }
            return;
        }
        validateKeptMoves(_index, _import, currentFight_, nextKey_, tree_);
    }

    private static StringList getChosenMoves(StringMap<Boolean> _choicesMoves) {
        StringList moves_ = new StringList();
        for (EntryCust<String, Boolean> e: _choicesMoves.entryList()) {
            if (e.getValue()) {
                moves_.add(e.getKey());
            }
        }
        return moves_;
    }

    void validateKeptMoves(int _index, DataBase _import, byte _currentFight,
            PairNumber<Byte, Byte> _nextKey,
            TreeMap<PairNumber<Byte, Byte>, StringList> _tree) {
        int size_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).size();
        if (size_ == movesBetweenFights.getVal((byte) _index).get(_currentFight).size()) {
            StringList initMoves_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).last();
            byte currentFight_ = _currentFight;
            currentFight_++;
            _nextKey.setFirst(currentFight_);
            _nextKey.setSecond(CustList.FIRST_INDEX);
            StringMap<Boolean> choicesMoves_;
            choicesMoves_ = movesToBeChosen(_tree.getVal(_nextKey), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(_nextKey);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            currentFights.put((byte) _index, currentFight_);
            keptMoves.getVal((byte) _index).put(_nextKey, getChosenMoves(choicesMoves_));
            return;
        }
        StringList initMoves_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).last();
        StringList movesToBeLearnt_ = movesBetweenFights.getVal((byte) _index).get(_currentFight).get(size_);
        StringMap<Boolean> choicesMoves_;
        choicesMoves_ = movesToBeChosen(movesToBeLearnt_, initMoves_, _import.getNbMaxMoves());
        keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).add(getChosenMoves(choicesMoves_));
        availableMovesBetweenFights.getVal((byte) _index).clear();
        availableMovesBetweenFights.getVal((byte) _index).putAllMap(choicesMoves_);
    }

    public void setAbilityBetweenFights(int _index, int _fight, int _stone, String _ability) {
        keptAbilitiesBetweenFights.getVal((byte) _index).get(_fight).set(_stone, _ability);
    }

    public void setAbilityWhileFight(int _index, int _fight, int _round, String _ability) {
        keptAbilities.getVal((byte) _index).put(new PairNumber<Byte, Byte>((byte)_fight,(byte)_round), _ability);
    }

    public void validateAllMoves(DataBase _import) {
        ok = true;
        if (!validMoves(_import)) {
            ok = false;
            return;
        }
        int nb_ = foeTeams.size();
        movesAbilities.clear();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            Numbers<Byte> indexes_ = indexesFight(i);
            int nbRounds_ = nbRound(mult.get(i), foeTeams.get(i).size());
            CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>> list_;
            list_ = new CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>>();
            for (byte j = CustList.FIRST_INDEX; j < nbRounds_; j++) {
                NumberMap<Byte,ChoiceOfEvolutionAndMoves> map_;
                map_ = new NumberMap<Byte,ChoiceOfEvolutionAndMoves>();
                for (byte k: keptMoves.getKeys()) {
                    ChoiceOfEvolutionAndMoves choice_;
                    choice_ = new ChoiceOfEvolutionAndMoves();
                    choice_.setKeptMoves(keptMoves.getVal(k).getVal(new PairNumber<Byte, Byte>(i,j)));
                    if (keptAbilities.getVal(k).contains(new PairNumber<Byte, Byte>(i,j))) {
                        choice_.setAbility(keptAbilities.getVal(k).getVal(new PairNumber<Byte, Byte>(i,j)));
                        choice_.setName(evolutionsWhileFight.getVal(k).getVal(new PairNumber<Byte, Byte>(i,j)));
                    }
                    map_.put(indexes_.get(k), choice_);
                }
                list_.add(map_);
            }
            movesAbilities.add(list_);
        }
        actionsBeforeRound.clear();
        actionsSubstitutingBack.clear();
        actionsSubstitutingFront.clear();
        int nbFrontFighters_ = frontFighters.size();
        for (byte f = CustList.FIRST_INDEX; f < nbFrontFighters_; f++) {
            CustList<CustList<ActionSwitch>> actionsSubstitutingFrontFight_;
            actionsSubstitutingFrontFight_ = new CustList<CustList<ActionSwitch>>();
            CustList<CustList<ActionSwitch>> actionsSubstitutingBackFight_;
            actionsSubstitutingBackFight_ = new CustList<CustList<ActionSwitch>>();
            CustList<CustList<ActionMove>> actionsBeforeRoundFight_;
            actionsBeforeRoundFight_ = new CustList<CustList<ActionMove>>();
            Numbers<Byte> nextFront_;
            int nbFrontFightersFight_ = frontFighters.get(f).size();
            for (byte i = CustList.SECOND_INDEX; i < nbFrontFightersFight_; i++) {
                CustList<ActionSwitch> actionsSubstitutingFrontRound_;
                actionsSubstitutingFrontRound_ = new CustList<ActionSwitch>();
                CustList<ActionSwitch> actionsSubstitutingBackRound_;
                actionsSubstitutingBackRound_ = new CustList<ActionSwitch>();
                byte current_ = i;
                current_--;
                NumberMap<Byte,Byte> currentActions_ = frontFighters.get(f).get(current_);
                EqList<PairNumber<Byte,Byte>> orderedFronts_;
                orderedFronts_ = new EqList<PairNumber<Byte,Byte>>();
                for (byte k: currentActions_.getKeys()) {
                    if (!Numbers.eq(currentActions_.getVal(k).byteValue(), Fighter.BACK)) {
                        orderedFronts_.add(new PairNumber<Byte, Byte>(currentActions_.getVal(k),k));
                    }
                }
                NumberMap<Byte,Byte> nextActions_ = frontFighters.get(f).get(i);
                nextFront_ = new Numbers<Byte>();
                for (byte k: nextActions_.getKeys()) {
                    if (!Numbers.eq(nextActions_.getVal(k).byteValue(), Fighter.BACK)) {
                        nextFront_.add(k);
                    }
                }
//                orderedFronts_.sort(new Comparator<Pair<Byte,Byte>>() {
//                    @Override
//                    public int compare(Pair<Byte, Byte> _o1, Pair<Byte, Byte> _o2) {
//                        return _o1.getFirst().compareTo(_o2.getFirst());
//                    }
//                });
                orderedFronts_.sortElts(new ComparatorPairNumber<Byte,Byte>());
                for (PairNumber<Byte,Byte> k: orderedFronts_) {
                    byte f_ = k.getSecond();
                    ActionSwitch act_ = new ActionSwitch();
                    if (!nextFront_.containsObj(f_)) {
                        act_.setSubstitute(Fighter.BACK);
                    } else {
                        act_.setSubstitute(nextActions_.getVal(f_));
                    }
                    actionsSubstitutingFrontRound_.add(act_);
                }
                int nbFighters_ = team.size();
                for (byte k = CustList.FIRST_INDEX; k < nbFighters_; k++) {
                    boolean contained_ = false;
                    for (PairNumber<Byte,Byte> k2_: orderedFronts_) {
                        byte f_ = k2_.getSecond();
                        if (Numbers.eq(f_, k)) {
                            contained_ = true;
                            break;
                        }
                    }
                    if (contained_) {
                        continue;
                    }
                    ActionSwitch act_ = new ActionSwitch();
                    if (!nextFront_.containsObj(k)) {
                        act_.setSubstitute(Fighter.BACK);
                    } else {
                        act_.setSubstitute(nextActions_.getVal(k));
                    }
                    actionsSubstitutingBackRound_.add(act_);
                }
                actionsSubstitutingFrontFight_.add(actionsSubstitutingFrontRound_);
                actionsSubstitutingBackFight_.add(actionsSubstitutingBackRound_);
            }
            actionsSubstitutingBack.add(actionsSubstitutingBackFight_);
            actionsSubstitutingFront.add(actionsSubstitutingFrontFight_);
            for (byte i = CustList.FIRST_INDEX; i < nbFrontFightersFight_; i++) {
                CustList<ActionMove> actionsBeforeRound_;
                actionsBeforeRound_ = new CustList<ActionMove>();
                NumberMap<Byte,Byte> map_;
                map_ = frontFighters.get(f).get(i);
//                int nbOrderFronts_ = map_.size() - map_.getKeys(Fighter.BACK).size();
                int nbOrderFronts_ = getNbFrontFighters(map_);
                for (byte k = CustList.FIRST_INDEX; k < nbOrderFronts_; k++) {
                    ActionMove action_;
                    action_ = new ActionMove();
                    action_.setFirstChosenMove(DataBase.EMPTY_STRING);
                    action_.setChosenTargets(new EqList<TargetCoords>());
                    actionsBeforeRound_.add(action_);
                }
                actionsBeforeRoundFight_.add(actionsBeforeRound_);
            }
            actionsBeforeRound.add(actionsBeforeRoundFight_);
        }
    }

    private static int getNbFrontFighters(NumberMap<Byte,Byte> _m) {
        int i_ = CustList.FIRST_INDEX;
        for (EntryCust<Byte, Byte> e: _m.entryList()) {
            if (e.getValue() != Fighter.BACK) {
                i_++;
            }
        }
        return i_;
    }
    boolean validMoves(DataBase _import) {
        for (TreeMap<PairNumber<Byte, Byte>, StringList> t: keptMoves.values()) {
            for (StringList m: t.values()) {
                if (m.isEmpty()) {
                    return false;
                }
                if (m.size() > _import.getNbMaxMoves()) {
                    return false;
                }
            }
        }
        for (byte k: keptMovesBetweenFights.getKeys()) {
            int i_ = CustList.FIRST_INDEX;
            for (EqList<StringList> l2_: keptMovesBetweenFights.getVal(k)) {
                if (movesBetweenFights.getVal(k).get(i_).size() != l2_.size()) {
                    return false;
                }
                i_++;
            }
        }
        for (CustList<EqList<StringList>> l: keptMovesBetweenFights.values()) {
            for (EqList<StringList> l2_: l) {
                for (StringList m: l2_) {
                    if (m.isEmpty()) {
                        return false;
                    }
                    if (m.size() > _import.getNbMaxMoves()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static StringMap<Boolean> movesToBeChosen(StringList _allMoves, StringList _currentMoves, int _max) {
        StringList movesToBeLearnt_ = new StringList(_allMoves);
        StringMap<Boolean> choicesMoves_;
        choicesMoves_ = new StringMap<Boolean>();
        movesToBeLearnt_.removeAllElements(_currentMoves);
        for (String m: _currentMoves) {
            choicesMoves_.put(m, true);
        }
        boolean keepAll_ = _currentMoves.size() + movesToBeLearnt_.size() <= _max;
        for (String m: movesToBeLearnt_) {
            choicesMoves_.put(m, keepAll_);
        }
        return choicesMoves_;
    }

    public StringList possibleMoves(int _fight, int _round, int _index) {
        byte place_ = frontFighters.get(_fight).get(_round).getVal((byte) _index);
        if (Numbers.eq(place_, Fighter.BACK)) {
            return new StringList();
        }
        if (Numbers.eq(_round, CustList.FIRST_INDEX)) {
            if (Numbers.eq(_fight, CustList.FIRST_INDEX)) {
                return new StringList(team.get(_index).getMoves().getKeys());
            }
            int index_ = _fight;
            index_--;
            if (!keptMovesBetweenFights.getVal((byte) _index).get(index_).isEmpty()) {
                return keptMovesBetweenFights.getVal((byte) _index).get(index_).last();
            }
        }
        TreeMap<PairNumber<Byte,Byte>, StringList> keptMovesBefore_;
        keptMovesBefore_ = keptMoves.getVal((byte) _index);
        PairNumber<Byte,Byte> currentKey_;
        currentKey_ = new PairNumber<Byte, Byte>((byte)_fight,(byte)_round);
        EqList<PairNumber<Byte,Byte>> keys_;
        keys_ = new EqList<PairNumber<Byte,Byte>>(keptMovesBefore_.getKeys());

        int index_ = keys_.indexOfObj(currentKey_);
        index_--;
        return keptMovesBefore_.getVal(keys_.get(index_));
//        Pair<Byte,Byte> previousKey_;
//        previousKey_ = keptMovesBefore_.lowerKey(currentKey_);
//        return keptMovesBefore_.getVal(previousKey_);
    }

    public void chooseMoveFirstFight(int _round, int _index, String _move, boolean _ally, int _foeTarget, DataBase _data) {
        if (_ally) {
            chooseMove(CustList.FIRST_INDEX, _round, _index, _move, TargetCoords.toUserTarget((short) _foeTarget), _data);
            return;
        }
        chooseMove(CustList.FIRST_INDEX, _round, _index, _move, TargetCoords.toFoeTarget((short) _foeTarget), _data);
    }

    public void chooseMove(int _fight, int _round, int _index, String _move, TargetCoords _target, DataBase _data) {
        byte place_ = frontFighters.get(_fight).get(_round).getVal((byte) _index);
        ActionMove action_ = actionsBeforeRound.get(_fight).get(_round).get(place_);
        action_.setFirstChosenMove(_move);
        action_.getChosenTargets().clear();
        if (!_data.getMove(_move).getTargetChoice().isWithChoice()) {
            return;
        }
        action_.getChosenTargets().add(_target);
    }

    public void simulateFight(DataBase _import) {
        ok = true;
        probleme = false;
        if (!validChoicesMoves()) {
            probleme = true;
            ok = false;
            return;
        }
        setComment(new StringList());
        CustList<Fightable> trainers_;
//        CustList<DualFight> duals_;
        trainers_ = new CustList<Fightable>();
//        duals_ = new CustList<>();
        if (freeTeams) {
            if (!allyTeam.isEmpty()) {
                DualFight dual_;
                dual_ = new DualFight();
                Ally ally_;
                ally_ = new Ally();
                ally_.setTeam(allyTeam);
                dual_.setAlly(ally_);
                TempTrainer tmp_;
                tmp_ = new TempTrainer();
                tmp_.setMultiplicityFight(mult.first());
                tmp_.setTeam(foeTeams.first());
                dual_.setFoeTrainer(tmp_);
                trainers_.add(dual_);
            } else {
                GymLeader gym_;
                gym_ = new GymLeader();
                gym_.setMultiplicityFight(mult.first());
                gym_.setTeam(foeTeams.first());
                trainers_.add(gym_);
            }
        } else {
            Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
            if (place_ instanceof League) {
                int i_ = CustList.FIRST_INDEX;
                for (LevelLeague l: ((League)place_).getRooms()) {
                    if (i_ == foeCoords.getLevel().getLevelIndex()) {
                        trainers_.add(l.getTrainer());
                    }
                    i_++;
                }
            } else {
                Level l_ = place_.getLevelByCoords(foeCoords);
                if (l_ instanceof LevelWithWildPokemon) {
                    if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                        DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                        trainers_.add(dual_);
                    } else {
                        TrainerMultiFights tr_ = (TrainerMultiFights) ((LevelWithWildPokemon)l_).getCharacters().getVal(foeCoords.getLevel().getPoint());
                        trainers_.add(tr_);
                    }
                } else {
                    if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                        GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                        trainers_.add(tr_);
                    } else {
                        GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                        trainers_.add(tr_);
                    }
                }
            }
        }
//        Map<String,Short> pps_;
//        pps_ = new Map<>();
//        for (Entry<String,MoveData> m: _import.getMoves().entrySet()) {
//            pps_.put(m.getKey(), m.getValue().getPp());
//        }
//        if (!duals_.isEmpty()) {
//            Player player_ = game.getPlayer();
//            Numbers<Byte> indexes_ = indexesFight((byte) CustList.FIRST_INDEX);
//            player_.swap(indexes_);
//            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), duals_.first(), _import);
//            FightFacade.initTypeEnv(game.getFight(), _coords, game.getDifficulty(), _import);
//            game.simuler(actionsBeforeRound.get(CustList.FIRST_INDEX), actionsSubstitutingFront.get(CustList.FIRST_INDEX),
//                    actionsSubstitutingBack.get(CustList.FIRST_INDEX), movesAbilities.get(CustList.FIRST_INDEX), _import);
//            if (!game.getFight().getAcceptableChoices()) {
//                probleme = true;
//                return;
//            }
//            if (game.getFight().getState() == FightState.APPRENDRE_EVOLUER) {
//                return;
//            }
//            FightFacade.endFight(game.getFight());
//            player_.affectEndFight(game.getFight(), game.getDifficulty(), _import);
//            return;
//        }
        Player player_ = game.getPlayer();
        Numbers<Byte> indexes_ = indexesFight(CustList.FIRST_INDEX);
        player_.swap(indexes_);
        Fightable trainer_;
        trainer_ = trainers_.get(CustList.FIRST_INDEX);
        game.getFight().getComment().clearMessages();
        if (trainer_ instanceof TrainerLeague) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (TrainerLeague) trainer_, _import);
        } else if (trainer_ instanceof GymLeader) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (GymLeader) trainer_, _import);
        } else if (trainer_ instanceof GymTrainer) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (GymTrainer) trainer_, _import);
        } else if (trainer_ instanceof DualFight) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (DualFight) trainer_, _import);
        } else {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (TrainerMultiFights) trainer_, noFight, _import);
        }
        if (!freeTeams) {
            FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
        } else {
            FightFacade.initTypeEnv(game.getFight(), environment, game.getDifficulty(), _import);
        }
        game.simuler(actionsBeforeRound.get(CustList.FIRST_INDEX), actionsSubstitutingFront.get(CustList.FIRST_INDEX),
                actionsSubstitutingBack.get(CustList.FIRST_INDEX), movesAbilities.get(CustList.FIRST_INDEX), _import);
        setComment(game.getFight().getComment().getMessages());
        if (!game.getFight().getAcceptableChoices()) {
            probleme = true;
            return;
        }
        if (proponeEvolutions()) {
            game.getFight().setChoices(movesAbilities.get(CustList.FIRST_INDEX).last());
            if (!FightFacade.possibleChoices(game.getFight(), _import)) {
                setComment(game.getFight().getComment().getMessages());
                probleme = true;
                return;
            }
            FightFacade.learnAndEvolveAttack(game.getFight(), game.getDifficulty(), _import);
            setComment(game.getFight().getComment().getMessages());
        }
        //If a pokemon has to learn or evolve, then it is never mind that the type of fight is NOTHING or not, because experience had been won
        FightFacade.endFight(game.getFight());
        player_.affectEndFight(game.getFight(), game.getDifficulty(), _import);
    }

    public boolean proponeEvolutions() {
        return game.getFight().getState() == FightState.APPRENDRE_EVOLUER;
    }

    public void simulateFights(DataBase _import) {
        probleme = false;
        if (!validChoicesMoves()) {
            probleme = true;
            return;
        }
        Place place_ = _import.getMap().getPlaces().getVal(foeCoords.getNumberPlace());
        CustList<Trainer> trainers_;
        CustList<DualFight> duals_;
        trainers_ = new CustList<Trainer>();
        duals_ = new CustList<DualFight>();
        if (place_ instanceof League) {
            for (LevelLeague l: ((League)place_).getRooms()) {
                trainers_.add(l.getTrainer());
            }
        } else {
            Level l_ = place_.getLevelByCoords(foeCoords);
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                    DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                    duals_.add(dual_);
                } else {
                    TrainerMultiFights tr_ = (TrainerMultiFights) ((LevelWithWildPokemon)l_).getCharacters().getVal(foeCoords.getLevel().getPoint());
                    trainers_.add(tr_);
                }
            } else {
                if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                    GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                    trainers_.add(tr_);
                } else {
                    GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                    trainers_.add(tr_);
                }
            }
        }
        StringMap<Short> pps_;
        pps_ = new StringMap<Short>();
        for (EntryCust<String,MoveData> m: _import.getMoves().entryList()) {
            pps_.put(m.getKey(), m.getValue().getPp());
        }
        if (!duals_.isEmpty()) {
            int nbFoes_ = foeTeams.size();
            for (byte i = CustList.FIRST_INDEX; i < nbFoes_; i++) {
//                int index_ = i;
//                index_++;
                Player player_ = game.getPlayer();
                Numbers<Byte> indexes_ = indexesFight(i);
                player_.swap(indexes_);
                FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), duals_.first(), _import);
                FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
                game.simuler(actionsBeforeRound.get(i), actionsSubstitutingFront.get(i),
                        actionsSubstitutingBack.get(i), movesAbilities.get(i), _import);
                if (!game.getFight().getAcceptableChoices()) {
                    probleme = true;
                    return;
                }
                FightFacade.endFight(game.getFight());
                player_.affectEndFight(game.getFight(), game.getDifficulty(), _import);
                player_.restore(indexes_);
//                byte i_ = CustList.FIRST_INDEX;
//                byte iPk_ = CustList.FIRST_INDEX;
//                for (UsablePokemon p: player_.getTeam()) {
//                    i_ = CustList.FIRST_INDEX;
//                    for (String e: evolutionsBetweenFights.getVal(iPk_).get(i)) {
//                        PokemonPlayer pk_;
//                        pk_ = (PokemonPlayer) p;
//                        pk_.setName(e);
//                        pk_.setAbility(keptAbilitiesBetweenFights.getVal(iPk_).get(i).get(i_));
//                        StringList moves_;
//                        moves_ = keptMovesBetweenFights.getVal(iPk_).get(i).get(i_);
//                        pk_.getMoves().clear();
//                        for (String m: moves_) {
//                            pk_.getMoves().put(m, new UsesOfMove(pps_.getVal(m)));
//                        }
//                        i_++;
//                    }
//                    iPk_++;
//                }
//                if (index_ < items.size()) {
//                    i_ = CustList.FIRST_INDEX;
//                    for (UsablePokemon p: player_.getTeam()) {
//                        ((Pokemon) p).setItem(items.get(index_).get(i_));
//                        i_++;
//                    }
//                }
            }
            return;
        }
        int nbFoes_ = foeTeams.size();
        for (byte i = CustList.FIRST_INDEX; i < nbFoes_; i++) {
            int index_ = i;
            index_++;
            Player player_ = game.getPlayer();
            Numbers<Byte> indexes_ = indexesFight(i);
            player_.swap(indexes_);
            Trainer trainer_;
            trainer_ = trainers_.get(i);
            if (trainer_ instanceof TrainerLeague) {
                FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (TrainerLeague) trainer_, _import);
            } else if (trainer_ instanceof GymLeader) {
                FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (GymLeader) trainer_, _import);
            } else if (trainer_ instanceof GymTrainer) {
                FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (GymTrainer) trainer_, _import);
            } else {
                FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), (TrainerMultiFights) trainer_, noFight, _import);
            }
            FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
            game.simuler(actionsBeforeRound.get(i), actionsSubstitutingFront.get(i),
                    actionsSubstitutingBack.get(i), movesAbilities.get(i), _import);
            if (!game.getFight().getAcceptableChoices()) {
                probleme = true;
                return;
            }
            FightFacade.endFight(game.getFight());
            player_.affectEndFight(game.getFight(), game.getDifficulty(), _import);
            player_.restore(indexes_);
            byte i_ = CustList.FIRST_INDEX;
            byte iPk_ = CustList.FIRST_INDEX;
            for (UsablePokemon p: player_.getTeam()) {
                i_ = CustList.FIRST_INDEX;
                for (String e: evolutionsBetweenFights.getVal(iPk_).get(i)) {
                    PokemonPlayer pk_;
                    pk_ = (PokemonPlayer) p;
                    pk_.setName(e);
                    pk_.setAbility(keptAbilitiesBetweenFights.getVal(iPk_).get(i).get(i_));
                    StringList moves_;
                    moves_ = keptMovesBetweenFights.getVal(iPk_).get(i).get(i_);
                    pk_.getMoves().clear();
                    for (String m: moves_) {
                        pk_.getMoves().put(m, new UsesOfMove(pps_.getVal(m)));
                    }
                    i_++;
                }
                iPk_++;
            }
            if (index_ < items.size()) {
                i_ = CustList.FIRST_INDEX;
                for (UsablePokemon p: player_.getTeam()) {
                    ((Pokemon) p).setItem(items.get(index_).get(i_));
                    i_++;
                }
            }
        }
    }

    boolean validChoicesMoves() {
        for (CustList<CustList<ActionMove>> f: actionsBeforeRound) {
            for (CustList<ActionMove> r: f) {
                for (ActionMove a: r) {
                    if (a.getFirstChosenMove().isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isFreeTeams() {
        return freeTeams;
    }

    public StringList getKoPlayers() {
        StringList list_ = new StringList();
        for (Fighter f: game.getFight().getUserTeam().getMembers().values()) {
            if (!f.estKo()) {
                continue;
            }
            list_.add(f.getName());
        }
        return list_;
    }

    public StringList getNotKoFrontFoes() {
        StringList list_ = new StringList();
        for (Fighter f: game.getFight().getFoeTeam().getMembers().values()) {
            if (f.estKo()) {
                continue;
            }
            if (f.estArriere()) {
                continue;
            }
            list_.add(f.getName());
        }
        return list_;
    }

    public StringList getKoFoes() {
        StringList list_ = new StringList();
        for (Fighter f: game.getFight().getFoeTeam().getMembers().values()) {
            if (!f.estKo()) {
                continue;
            }
            list_.add(f.getName());
        }
        return list_;
    }

    public NatTreeMap<Byte, StringList> getEvolutionsAfterFight() {
        NatTreeMap<Byte, StringList> tree_;
        tree_ = new NatTreeMap<Byte, StringList>();
        for (byte f: game.getFight().getUserTeam().getMembers().getKeys()) {
            Fighter f_ = game.getFight().getUserTeam().getMembers().getVal(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            //the value contains only evolutions names
            tree_.put(f, new StringList(f_.getMovesAbilitiesEvos().getKeys()));
        }
        return tree_;
    }

    public NatTreeMap<Byte, StringList> getMovesNoEvoAfterFight() {
        NatTreeMap<Byte, StringList> tree_;
        tree_ = new NatTreeMap<Byte, StringList>();
        for (byte f: game.getFight().getUserTeam().getMembers().getKeys()) {
            Fighter f_ = game.getFight().getUserTeam().getMembers().getVal(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            StringList list_ = new StringList(f_.getMovesToBeLearnt());
            list_.addAllElts(f_.getMovesSet());
            tree_.put(f, list_);
        }
        return tree_;
    }

    public NatTreeMap<Byte,StringMap<StringList>> getAbilitiesAfterFight() {
        NatTreeMap<Byte,StringMap<StringList>> tree_;
        tree_ = new NatTreeMap<Byte,StringMap<StringList>>();
        for (byte f: game.getFight().getUserTeam().getMembers().getKeys()) {
            Fighter f_ = game.getFight().getUserTeam().getMembers().getVal(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            StringMap<StringList> tr_ = new StringMap<StringList>();
            for (String e: f_.getMovesAbilitiesEvos().getKeys()) {
                tr_.put(e, new StringList(f_.getMovesAbilitiesEvos().getVal(e).getAbilities()));
            }
            tree_.put(f, tr_);
        }
        return tree_;
    }

    public NatTreeMap<Byte,StringMap<StringList>> getMovesAfterFight() {
        NatTreeMap<Byte,StringMap<StringList>> tree_;
        tree_ = new NatTreeMap<Byte,StringMap<StringList>>();
        for (byte f: game.getFight().getUserTeam().getMembers().getKeys()) {
            Fighter f_ = game.getFight().getUserTeam().getMembers().getVal(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            StringMap<StringList> tr_ = new StringMap<StringList>();
            for (String e: f_.getMovesAbilitiesEvos().getKeys()) {
                StringList list_ = new StringList(f_.getMovesAbilitiesEvos().getVal(e).getMoves());
                list_.addAllElts(f_.getMovesSet());
                tr_.put(e, list_);
            }
            tree_.put((byte) tree_.size(), tr_);
        }
        return tree_;
    }

    public boolean isAcceptableChoices() {
        return game.getFight().getAcceptableChoices();
    }

    public EnvironmentType getEnvironment() {
        return environment;
    }

    public IssueSimulation getIssue() {
        return game.getFight().getIssue();
    }

//    public Map<Byte, ChoiceOfEvolutionAndMoves> getChoices() {
//        return game.getFight().getChoices();
//    }

    Game getGame() {
        return game;
    }

    void setGame(Game _game) {
        game = _game;
    }

    public Coords getFoeCoords() {
        return foeCoords;
    }

    void setFoeCoords(Coords _foeCoords) {
        foeCoords = _foeCoords;
    }

    public CustList<CustList<PkTrainer>> getFoeTeams() {
        return foeTeams;
    }

    public CustList<PkTrainer> getAllyTeam() {
        return allyTeam;
    }

    public CustList<PkTrainer> getFoeTeam() {
        return foeTeams.first();
    }

    public Numbers<Byte> getMult() {
        return mult;
    }

    public Numbers<Integer> getMaxActions() {
        return maxActions;
    }

    public int getNoFight() {
        return noFight;
    }

    void setNoFight(int _noFight) {
        noFight = _noFight;
    }

    public CustList<PokemonPlayer> getTeamAfterFight() {
        return new CustList<PokemonPlayer>(game.getPlayer().getPokemonPlayerList().values());
    }

    public CustList<PokemonPlayer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PokemonPlayer> _team) {
        team = _team;
    }

    public EqList<StringList> getItems() {
        return items;
    }

    public CustList<CustList<NumberMap<Byte,Byte>>> getFrontFighters() {
        return frontFighters;
    }

    public CustList<EqList<StringList>> getUsedStones() {
        return usedStones;
    }

    public CustList<StringMap<Short>> getAvailableEvolutions() {
        return availableEvolutions;
    }

    public CustList<EqList<NameLevel>> getEvolutions() {
        return evolutions;
    }

    public CustList<EqList<NameLevel>> getInfosRealEvolutions() {
        return infosRealEvolutions;
    }

    public NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> getMoves() {
        return moves;
    }

    public NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> getAbilities() {
        return abilities;
    }

    public NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>> getEvolutionsWhileFight() {
        return evolutionsWhileFight;
    }

    public NumberMap<Byte,CustList<EqList<StringList>>> getMovesBetweenFights() {
        return movesBetweenFights;
    }

    public NumberMap<Byte,CustList<EqList<StringList>>> getAbilitiesBetweenFights() {
        return abilitiesBetweenFights;
    }

    public NumberMap<Byte,EqList<StringList>> getEvolutionsBetweenFights() {
        return evolutionsBetweenFights;
    }

    public NumberMap<Byte,AvailableMovesInfos> getAvailableMoves() {
        return availableMoves;
    }

    public NumberMap<Byte,StringMap<Boolean>> getAvailableMovesBetweenFights() {
        return availableMovesBetweenFights;
    }

    public NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,StringList>> getKeptMoves() {
        return keptMoves;
    }

    public NumberMap<Byte,TreeMap<PairNumber<Byte,Byte>,String>> getKeptAbilities() {
        return keptAbilities;
    }

    public NumberMap<Byte,CustList<EqList<StringList>>> getKeptMovesBetweenFights() {
        return keptMovesBetweenFights;
    }

    public NumberMap<Byte,EqList<StringList>> getKeptAbilitiesBetweenFights() {
        return keptAbilitiesBetweenFights;
    }

    NumberMap<Byte,Byte> getCurrentFights() {
        return currentFights;
    }

    public CustList<CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>>> getMovesAbilities() {
        return movesAbilities;
    }

    public CustList<CustList<CustList<ActionMove>>> getActionsBeforeRound() {
        return actionsBeforeRound;
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingFront() {
        return actionsSubstitutingFront;
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingBack() {
        return actionsSubstitutingBack;
    }

    public boolean getProbleme() {
        return probleme;
    }

    public void setProbleme(boolean _probleme) {
        probleme = _probleme;
    }

    public StringList getComment() {
        return comment;
    }

    public void setComment(StringList _comment) {
        comment = _comment;
    }
}
