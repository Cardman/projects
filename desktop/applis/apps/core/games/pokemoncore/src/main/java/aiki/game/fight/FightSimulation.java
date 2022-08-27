package aiki.game.fight;
import aiki.comparators.ComparatorFightRound;
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
import aiki.util.Coords;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class FightSimulation {

    private static final String SEPARATOR_PK = "/";

    private final Game game;

    private Coords foeCoords;

    private int noFight;

    private final Bytes mult;

    private final Ints maxActions;

    private final CustList<CustList<PkTrainer>> foeTeams;

    private final CustList<PkTrainer> allyTeam = new CustList<PkTrainer>();

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
    private final CustList<PokemonPlayer> team;

    /**getFirst() index: fight, getSecond() index: initial position*/
    private final CustList<StringList> items;

    private CustList<StringMap<Short>> availableEvolutions;

    private final CustList<CustList<NameLevel>> evolutions;

    /**for each fight, for each round of fight, positions and possible substitute (position before fight) of front fighters*/
    private final CustList<CustList<ByteMap<Byte>>> frontFighters;

    private final CustList<CustList<NameLevel>> infosRealEvolutions;

    private final CustList<CustList<StringList>> usedStones;

    private final ByteMap<TreeMap<KeyFightRound,StringList>> moves;

    private final ByteMap<TreeMap<KeyFightRound,StringList>> abilities;

    private final ByteMap<TreeMap<KeyFightRound,String>> evolutionsWhileFight;

    /**position init before fights, fight - stone evolution*/
    private final ByteMap<CustList<CustList<StringList>>> movesBetweenFights;

    private final ByteMap<CustList<CustList<StringList>>> abilitiesBetweenFights;

    private final ByteMap<CustList<StringList>> evolutionsBetweenFights;

    private final ByteMap<AvailableMovesInfos> availableMoves;

    //private Map<Byte, Pair<Pair<Byte,Byte>, StringList>> availableAbilities;

    private final ByteMap<StringMap<BoolVal>> availableMovesBetweenFights;

    //private Map<Byte, StringList> availableAbilitiesBetweenFights;

    private final ByteMap<TreeMap<KeyFightRound,StringList>> keptMoves;

    private final ByteMap<TreeMap<KeyFightRound,String>> keptAbilities;

    private final ByteMap<CustList<CustList<StringList>>> keptMovesBetweenFights;

    private final ByteMap<CustList<StringList>> keptAbilitiesBetweenFights;

    private final ByteMap<Byte> currentFights;

    //private CustList<CustList<Pair<Pair<String,Short>, Pair<StringList,StringList>>>> availableMovesAbilities;

    //private CustList<Pair<String,Short>> currentKeys;

//    private CustList<CustList<Pair<Pair<String,Short>, Pair<StringList,StringList>>>> movesAbilities;

    /**for each fight - round: map of positions at begin of fight and choices of
    evolution, learnt moves, learnt ability at level*/
    private final CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>> movesAbilities;

    private final CustList<CustList<CustList<ActionMove>>> actionsBeforeRound;

    private final CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingFront;

    private final CustList<CustList<CustList<ActionSwitch>>> actionsSubstitutingBack;

    private boolean probleme;

    private StringList comment;

    private boolean ok;

    private EnvironmentType environment = EnvironmentType.ROAD;

    public FightSimulation(Difficulty _diff, DataBase _import) {
        game = new Game(_import);
        game.initUtilisateurSimulation(DataBase.EMPTY_STRING, null, _diff, _import);
        team = new CustList<PokemonPlayer>();
        items = new CustList<StringList>();
        evolutions = new CustList<CustList<NameLevel>>();
        frontFighters = new CustList<CustList<ByteMap<Byte>>>();
        mult = new Bytes();
        maxActions = new Ints();
        infosRealEvolutions = new CustList<CustList<NameLevel>>();
        usedStones = new CustList<CustList<StringList>>();
        foeTeams = new CustList<CustList<PkTrainer>>();
        moves = new ByteMap<TreeMap<KeyFightRound,StringList>>();
        abilities = new ByteMap<TreeMap<KeyFightRound,StringList>>();
        movesBetweenFights = new ByteMap<CustList<CustList<StringList>>>();
        abilitiesBetweenFights = new ByteMap<CustList<CustList<StringList>>>();
        evolutionsBetweenFights = new ByteMap<CustList<StringList>>();
        keptAbilities = new ByteMap<TreeMap<KeyFightRound,String>>();
        keptMoves = new ByteMap<TreeMap<KeyFightRound,StringList>>();
        keptAbilitiesBetweenFights = new ByteMap<CustList<StringList>>();
        keptMovesBetweenFights = new ByteMap<CustList<CustList<StringList>>>();
        availableMoves = new ByteMap<AvailableMovesInfos>();
        availableMovesBetweenFights = new ByteMap<StringMap<BoolVal>>();
        currentFights = new ByteMap<Byte>();
        actionsBeforeRound = new CustList<CustList<CustList<ActionMove>>>();
        actionsSubstitutingBack = new CustList<CustList<CustList<ActionSwitch>>>();
        actionsSubstitutingFront = new CustList<CustList<CustList<ActionSwitch>>>();
        movesAbilities = new CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>>();
        evolutionsWhileFight = new ByteMap<TreeMap<KeyFightRound,String>>();
    }

    public void initializeFights(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = _foeCoords;
        freeTeams = false;
        noFight = _index;
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
        maxActions.clear();
        mult.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        if (place_ instanceof League) {
            for (Level l: place_.getLevelsList()) {
                LevelLeague level_ = (LevelLeague) l;
                byte mult_ = level_.getTrainer().getMultiplicityFight();
                maxActions.add((int) mult_);
                mult.add(mult_);
                items.add(new StringList());
                usedStones.add(new CustList<StringList>());
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
                    TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
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
            usedStones.add(new CustList<StringList>());
        }
    }

    public void initializeFight(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = new Coords(_foeCoords);
        freeTeams = false;
        noFight = _index;
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
        maxActions.clear();
        mult.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        allyTeam.clear();
        if (place_ instanceof League) {
            LevelLeague l_ = (LevelLeague) place_.getLevelsList().get(foeCoords.getLevel().getLevelIndex());
            foeCoords.getLevel().getPoint().affect(l_.getTrainerCoords());
            byte mult_ = l_.getTrainer().getMultiplicityFight();
            maxActions.add((int) mult_);
            mult.add(mult_);
            items.add(new StringList());
            usedStones.add(new CustList<StringList>());
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
                    TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
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
            usedStones.add(new CustList<StringList>());
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
        usedStones.add(new CustList<StringList>());
        foeTeams.add(_foeTeam);
    }

    public void nextFight(DataBase _import) {
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
        CustList<Level> list_ = place_.getLevelsList();
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
        usedStones.add(new CustList<StringList>());
        foeTeams.add(l_.getTrainer().getTeam());
    }

    public boolean hasNextFight(DataBase _import) {
        if (freeTeams) {
            return false;
        }
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
        if (!(place_ instanceof League)) {
            return false;
        }
        CustList<Level> list_ = place_.getLevelsList();
        byte index_ = foeCoords.getLevel().getLevelIndex();
        index_++;
        return list_.isValidIndex(index_);
    }

    public static StringList possiblesInitialMoves(String _name, short _level, DataBase _import) {
        PokemonData data_ = _import.getPokemon(_name);
        String basePk_ = data_.getBaseEvo();
        StringMap<Short> evos_ = PokemonPlayer.getAllEvolutions(basePk_, _level, true, _import);
        StringList keys_ = new StringList(evos_.getKeys());
        StringList moves_ = new StringList();
        StringList res_ = keys_.filterEndsWith(StringUtil.concat(PokemonPlayer.SEPARATOR,_name));
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
        StringList path_ = StringUtil.splitStrings(res_.first(), PokemonPlayer.SEPARATOR);
        int index_ = IndexConstants.FIRST_INDEX;
        int nbPaths_ = path_.size();
        for (int i = IndexConstants.SECOND_INDEX; i < nbPaths_; i++) {
            String beforeEvo_ = path_.get(index_);
            PokemonData dataBeforeEvo_ = _import.getPokemon(beforeEvo_);
            short level_ = evos_.getVal(StringUtil.join(path_.left(i + 1), PokemonPlayer.SEPARATOR));
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
        evolutions.add(new CustList<NameLevel>());
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
            keys_ = getNextEvos(keys_);
            StringMap<Short> direct_ = new StringMap<Short>();
            for (String k: keys_) {
                String prefix_ = StringUtil.concat(p.getName(),SEPARATOR_PK);
                String rep_ = StringUtil.replaceBegin(k, prefix_);
                direct_.put(rep_, evos_.getVal(k));
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
        for (int i = IndexConstants.FIRST_INDEX; i < nbAvailableEvolutions_; i++) {
            evolutions.add(new CustList<NameLevel>());
        }
    }

    public void setNextEvolutions(int _index, String _currentEvo, short _level, DataBase _import) {
        if (StringUtil.quickEq(_currentEvo, DataBase.EMPTY_STRING)) {
            return;
        }
        StringMap<Short> nextEvos_;
        nextEvos_ = PokemonPlayer.getAllEvolutions(_currentEvo, _level, true, _import);
        StringList keys_ = new StringList(nextEvos_.getKeys());
        keys_ = getNextEvos(keys_);
        StringMap<Short> direct_ = new StringMap<Short>();
        for (String k: keys_) {
            String prefix_ = StringUtil.concat(_currentEvo,SEPARATOR_PK);
            String rep_ = StringUtil.replaceBegin(k, prefix_);
            direct_.put(rep_, nextEvos_.getVal(k));
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
        if (_indexOfCancel <= IndexConstants.FIRST_INDEX) {
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
        keys_ = getNextEvos(keys_);
        StringMap<Short> direct_ = new StringMap<Short>();
        for (String k: keys_) {
            String prefix_ = StringUtil.concat(previous_.getName(),SEPARATOR_PK);
            String rep_ = StringUtil.replaceBegin(k, prefix_);
            direct_.put(rep_, nextEvos_.getVal(k));
        }
        availableEvolutions.get(_index).clear();
        availableEvolutions.get(_index).putAllMap(direct_);
        while (_indexOfCancel < evolutions.get(_index).size()) {
            evolutions.get(_index).remove(_indexOfCancel);
        }
    }

    private static StringList getNextEvos(StringList _evos) {
        StringList list_ = new StringList();
        for (String e : _evos) {
            StringList sep_ = MathExpUtil.getWordsSeparators(e);
            if (sep_.size() != 4) {
                continue;
            }
            list_.add(e);
        }
        return list_;
    }

    public void initializeFrontFighters() {
        frontFighters.clear();
        int nb_ = foeTeams.size();
        for (int k = IndexConstants.FIRST_INDEX; k < nb_; k++) {
            CustList<ByteMap<Byte>> rounds_;
            rounds_ = new CustList<ByteMap<Byte>>();
            int nbRounds_ = nbRound(mult.get(k), foeTeams.get(k).size());
            for (int i = IndexConstants.FIRST_INDEX; i < nbRounds_; i++) {
                ByteMap<Byte> round_;
                round_ = new ByteMap<Byte>();
                int nbMembers_ = team.size();
                for (byte j = IndexConstants.FIRST_INDEX; j < nbMembers_; j++) {
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
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            infosRealEvolutions.add(new CustList<NameLevel>());
        }
        nb_ = foeTeams.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            PseudoFight pseudoFight_;
            pseudoFight_ = new PseudoFight(foeTeams.get(i), pseudoPlayer_, mult.get(i), frontFighters.get(i));
            pseudoFight_.presimulateFight(game.getDifficulty(), _import);
            byte i_ = IndexConstants.FIRST_INDEX;
            for (PseudoPlayerFighter f: pseudoFight_.getPlayerFighters()) {
                infosRealEvolutions.get(i_).addAllElts(f.getInfosRealEvolutions());
                pseudoPlayer_.getEvolutions().get(i_).clear();
                pseudoPlayer_.getEvolutions().get(i_).addAllElts(f.getEvoLevels());
                pseudoPlayer_.getTeam().get(i_).setLevel(f.getLevel());
                pseudoPlayer_.getTeam().get(i_).setName(f.getName());
                pseudoPlayer_.getTeam().get(i_).getWonPointsSinceLastLevel().affect(f.getWonExpSinceLastLevel());
                TreeMap<KeyFightRound,String> treeEvos_;
                treeEvos_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
                for (byte o: f.getEvolutions().getKeys()) {
                    treeEvos_.put(new KeyFightRound(i, o), f.getEvolutions().getVal(o));
                }
                evolutionsWhileFight.put(i_, treeEvos_);
                TreeMap<KeyFightRound,StringList> treeMoves_;
                treeMoves_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                byte iRound_ = IndexConstants.FIRST_INDEX;
                for (StringList o: f.getMoves()) {
                    treeMoves_.put(new KeyFightRound(i, iRound_), o);
                    iRound_++;
                }
                moves.put(i_, treeMoves_);
                StringMap<BoolVal> choicesMoves_;
                StringList initMoves_ = new StringList(team.get(i_).getMoves().getKeys());
                choicesMoves_ = movesToBeChosen(treeMoves_.firstValue(), initMoves_, _import.getNbMaxMoves());
                KeyFightRound firstFightRound_;
                firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
                availableMoves.put(i_, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
                treeMoves_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                treeMoves_.put(firstFightRound_, new StringList(initMoves_));
                keptMoves.put(i_, treeMoves_);
                TreeMap<KeyFightRound,StringList> treeAbilities_;
                treeAbilities_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                iRound_ = IndexConstants.FIRST_INDEX;
                for (StringList o: f.getAbilities()) {
                    if (!o.isEmpty()) {
                        treeAbilities_.put(new KeyFightRound(i, iRound_), o);
                    }
                    iRound_++;
                }
                abilities.put(i_, treeAbilities_);
                TreeMap<KeyFightRound,String> treeKept_;
                treeKept_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
                for (KeyFightRound k: treeAbilities_.getKeys()) {
                    treeKept_.put(new KeyFightRound(k), treeAbilities_.getVal(k).first());
                }
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
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            infosRealEvolutions.add(new CustList<NameLevel>());
        }
        nb_ = foeTeams.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            int index_ = i;
            index_++;
            PseudoFight pseudoFight_;
            pseudoFight_ = new PseudoFight(foeTeams.get(i), pseudoPlayer_, mult.get(i), frontFighters.get(i));
            pseudoFight_.presimulateFight(game.getDifficulty(), _import);
            //Bytes indexes_ = indexesFight(i);
            byte i_ = IndexConstants.FIRST_INDEX;
            for (PseudoPlayerFighter f: pseudoFight_.getPlayerFighters()) {
                infosRealEvolutions.get(i_).addAllElts(f.getInfosRealEvolutions());
                pseudoPlayer_.getEvolutions().get(i_).clear();
                pseudoPlayer_.getEvolutions().get(i_).addAllElts(f.getEvoLevels());
                pseudoPlayer_.getTeam().get(i_).setLevel(f.getLevel());
                pseudoPlayer_.getTeam().get(i_).setName(f.getName());
                pseudoPlayer_.getTeam().get(i_).getWonPointsSinceLastLevel().affect(f.getWonExpSinceLastLevel());
                if (evolutionsWhileFight.contains(i_)) {
                    TreeMap<KeyFightRound,String> tree_;
                    tree_ = evolutionsWhileFight.getVal(i_);
                    for (byte o: f.getEvolutions().getKeys()) {
                        tree_.put(new KeyFightRound(i, o), f.getEvolutions().getVal(o));
                    }
                } else {
                    TreeMap<KeyFightRound,String> tree_;
                    tree_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
                    for (byte o: f.getEvolutions().getKeys()) {
                        tree_.put(new KeyFightRound(i, o), f.getEvolutions().getVal(o));
                    }
                    evolutionsWhileFight.put(i_, tree_);
                }
//                if (moves.contains(indexes_.get(i_))) {
                if (moves.contains(i_)) {
                    TreeMap<KeyFightRound,StringList> tree_;
//                    tree_ = moves.getVal(indexes_.get(i_));
                    tree_ = moves.getVal(i_);
                    byte iRound_ = IndexConstants.FIRST_INDEX;
                    for (StringList o: f.getMoves()) {
                        tree_.put(new KeyFightRound(i, iRound_), o);
                        iRound_++;
                    }
                } else {
                    TreeMap<KeyFightRound,StringList> tree_;
                    tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                    byte iRound_ = IndexConstants.FIRST_INDEX;
                    for (StringList o: f.getMoves()) {
                        tree_.put(new KeyFightRound(i, iRound_), o);
                        iRound_++;
                    }
//                    moves.put(indexes_.get(i_), tree_);
                    moves.put(i_, tree_);
                    StringMap<BoolVal> choicesMoves_;
                    StringList initMoves_ = new StringList(team.get(i_).getMoves().getKeys());
                    choicesMoves_ = movesToBeChosen(tree_.firstValue(), initMoves_, _import.getNbMaxMoves());
                    KeyFightRound firstFightRound_;
                    firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
//                    availableMoves.put(indexes_.get(i_), new Pair<>(firstFightRound_,choicesMoves_));
                    availableMoves.put(i_, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
                    tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                    tree_.put(firstFightRound_, new StringList(initMoves_));
//                    keptMoves.put(indexes_.get(i_), tree_);
                    keptMoves.put(i_, tree_);
                }
//                if (abilities.contains(indexes_.get(i_))) {
                if (abilities.contains(i_)) {
                    TreeMap<KeyFightRound,StringList> tree_;
//                    tree_ = abilities.getVal(indexes_.get(i_));
                    tree_ = abilities.getVal(i_);
                    TreeMap<KeyFightRound,String> treeKept_;
                    treeKept_ = keptAbilities.getVal(i_);
                    byte iRound_ = IndexConstants.FIRST_INDEX;
                    for (StringList o: f.getAbilities()) {
                        if (!o.isEmpty()) {
                            tree_.put(new KeyFightRound(i, iRound_), o);
                            treeKept_.put(new KeyFightRound(i, iRound_), o.first());
                        }
                        iRound_++;
                    }
                } else {
                    TreeMap<KeyFightRound,StringList> tree_;
                    tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                    byte iRound_ = IndexConstants.FIRST_INDEX;
                    for (StringList o: f.getAbilities()) {
                        if (!o.isEmpty()) {
                            tree_.put(new KeyFightRound(i, iRound_), o);
                        }
                        iRound_++;
                    }
//                    abilities.put(indexes_.get(i_), tree_);
                    abilities.put(i_, tree_);
                    TreeMap<KeyFightRound,String> treeKept_;
                    treeKept_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
                    for (KeyFightRound k: tree_.getKeys()) {
                        treeKept_.put(new KeyFightRound(k), tree_.getVal(k).first());
                    }
//                    keptAbilities.put(indexes_.get(i_), treeKept_);
                    keptAbilities.put(i_, treeKept_);
                }
                currentFights.put(i_, (byte) 0);
                i_++;
            }
            i_ = IndexConstants.FIRST_INDEX;
            CustList<StringList> usedStones_;
            usedStones_ = new CustList<StringList>();
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
                    if (!NumberUtil.eq(p2_.getLevel(), p.getLevel())) {
                        continue;
                    }
                    nextEvos_.add(p2_.getName());
                }
                StringList usedStonesPokemon_;
                usedStonesPokemon_ = new StringList();
                CustList<CustList<StringList>> listMoves_;
                CustList<CustList<StringList>> listKeptMoves_;
                if (movesBetweenFights.contains(i_)) {
                    listMoves_ = movesBetweenFights.getVal(i_);
                    listKeptMoves_ = keptMovesBetweenFights.getVal(i_);
                } else {
                    listMoves_ = new CustList<CustList<StringList>>();
                    listKeptMoves_ = new CustList<CustList<StringList>>();
                }
                CustList<CustList<StringList>> listAbilities_;
                CustList<StringList> listKeptAbilities_;
                CustList<StringList> listEvolutions_;
                if (abilitiesBetweenFights.contains(i_)) {
                    listAbilities_ = abilitiesBetweenFights.getVal(i_);
                    listKeptAbilities_ = keptAbilitiesBetweenFights.getVal(i_);
                } else {
                    listAbilities_ = new CustList<CustList<StringList>>();
                    listKeptAbilities_ = new CustList<StringList>();
                }
                if (evolutionsBetweenFights.contains(i_)) {
                    listEvolutions_ = evolutionsBetweenFights.getVal(i_);
                } else {
                    listEvolutions_ = new CustList<StringList>();
                }
                CustList<StringList> groupsMoves_;
                groupsMoves_ = new CustList<StringList>();
                CustList<StringList> groupsAbilities_;
                groupsAbilities_ = new CustList<StringList>();
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
                listKeptMoves_.add(new CustList<StringList>());
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
            if (index_ < items.size()) {
                i_ = IndexConstants.FIRST_INDEX;
                for (PseudoPokemonPlayer p: pseudoPlayer_.getTeam()) {
                    p.setItem(items.get(index_).get(i_));
                    i_++;
                }
            }
        }
    }

    boolean validFrontFighters() {
        int index_ = IndexConstants.FIRST_INDEX;
        for (CustList<ByteMap<Byte>> r: frontFighters) {
            int nbActions_ = maxActions.get(index_);
            for (ByteMap<Byte> r2_: r) {
                Bytes front_ = new Bytes();
                Bytes places_ = new Bytes();
                for (byte f: r2_.getKeys()) {
                    byte a_ = r2_.getVal(f);
                    if (NumberUtil.eq(a_, Fighter.BACK)) {
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
                if (places_.isEmpty()) {
                    return false;
                }
                int min_ = places_.first();
                int max_ = places_.first();
                for (byte p: places_) {
                    if (p < min_) {
                        min_ = p;
                    }
                    if (p > max_) {
                        max_ = p;
                    }
                }
                if (min_ != 0) {
                    return false;
                }
                if (max_ != places_.size() - 1) {
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

    Bytes indexesFight(byte _fight) {
        ByteMap<Byte> fighters_;
        fighters_ = frontFighters.get(_fight).first();
        Bytes indexes_;
        indexes_ = new Bytes();
        Bytes indexesBack_;
        indexesBack_ = new Bytes();
        for (EntryCust<Byte, Byte> e: fighters_.entryList()) {
            if (!NumberUtil.eq(e.getValue(), Fighter.BACK)) {
                indexes_.add(e.getKey());
            } else {
                indexesBack_.add(e.getKey());
            }
        }
        indexesBack_.sort();
        indexes_.addAllElts(indexesBack_);
        return indexes_;
    }

    public void keepMoves(int _index, StringList _moves, DataBase _import) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_;
        nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        TreeMap<KeyFightRound, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(nextKey_)) {
            availableMoves.getVal((byte) _index).getMoves().clear();
            StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
            moves_.clear();
            moves_.addAllElts(_moves);
            return;
        }
        for (String s: availableMoves.getVal((byte) _index).getMoves().getKeys()) {
            availableMoves.getVal((byte) _index).getMoves().put(s, BoolVal.FALSE);
        }
        for (String s: _moves) {
            availableMoves.getVal((byte) _index).getMoves().put(s, BoolVal.TRUE);
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
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).contains(key_);
    }

    public StringList getAvailableAbilities(int _index) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).getVal(key_);
    }

    void addMove(int _index, String _move) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        availableMoves.getVal((byte) _index).getMoves().put(_move, BoolVal.TRUE);
        StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
        moves_.add(_move);
    }

    void addMoveBetweenFights(int _index, int _indexStone, String _move) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        availableMovesBetweenFights.getVal((byte) _index).put(_move, BoolVal.TRUE);
        StringList moves_ = keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).get(_indexStone);
        moves_.add(_move);
    }

    void deleteMove(int _index, String _move) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        availableMoves.getVal((byte) _index).getMoves().put(_move, BoolVal.FALSE);
        StringList moves_ = keptMoves.getVal((byte) _index).getVal(key_);
        StringUtil.removeObj(moves_, _move);
    }

    void deleteMoveBetweenFights(int _index, int _indexStone, String _move) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        availableMovesBetweenFights.getVal((byte) _index).put(_move, BoolVal.FALSE);
        StringList moves_ = keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).get(_indexStone);
        StringUtil.removeObj(moves_, _move);
    }

    public void cancelAllMovesOneFight(int _index, DataBase _import) {
        TreeMap<KeyFightRound, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        StringMap<BoolVal> choicesMoves_;
        StringList initMoves_ = new StringList(team.get((byte) _index).getMoves().getKeys());
        choicesMoves_ = movesToBeChosen(tree_.firstValue(), initMoves_, _import.getNbMaxMoves());
        KeyFightRound firstFightRound_;
        firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        availableMoves.put((byte) _index, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
        tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
        tree_.put(firstFightRound_, new StringList(initMoves_));
        keptMoves.put((byte) _index, tree_);
    }

    public void cancelMovesOneFight(int _index, DataBase _import) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound previousKey_;
        previousKey_ = new KeyFightRound(key_);
        previousKey_.setRound((byte) (previousKey_.getRound()-1));
        KeyFightRound previousPrevKey_;
        previousPrevKey_ = new KeyFightRound(previousKey_);
        previousPrevKey_.setRound((byte) (previousPrevKey_.getRound()-1));
        TreeMap<KeyFightRound, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(previousPrevKey_)) {
            StringMap<BoolVal> choicesMoves_;
            StringList initMoves_= new StringList(team.get(_index).getMoves().getKeys());
            choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(previousKey_);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            keptMoves.getVal((byte) _index).put(previousKey_, getChosenMoves(choicesMoves_));
            keptMoves.getVal((byte) _index).getVal(key_).clear();
            return;
        }
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(previousPrevKey_);
        StringMap<BoolVal> choicesMoves_;
        choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(previousKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        keptMoves.getVal((byte) _index).getVal(key_).clear();
    }

    void validateMovesOneFight(int _index, DataBase _import) {
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_;
        nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        TreeMap<KeyFightRound, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
        StringMap<BoolVal> choicesMoves_;
        choicesMoves_ = movesToBeChosen(tree_.getVal(nextKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(nextKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        keptMoves.getVal((byte) _index).put(nextKey_, getChosenMoves(choicesMoves_));
    }

    public void validateMoves(int _index, DataBase _import) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        KeyFightRound key_;
        key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_;
        nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        boolean betweenFights_= true;
        TreeMap<KeyFightRound, StringList> tree_;
        tree_ = moves.getVal((byte) _index);
        for (KeyFightRound k: tree_.getKeys()) {
            if (NumberUtil.eq(k.getFight(), nextKey_.getFight())) {
                if (NumberUtil.eq(k.getRound(), nextKey_.getRound())) {
                    betweenFights_ = false;
                }
            }
        }
        if (!betweenFights_) {
            StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
            StringMap<BoolVal> choicesMoves_;
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
            nextKey_.setFight(currentFight_);
            nextKey_.setRound(IndexConstants.FIRST_INDEX);
            StringMap<BoolVal> choicesMoves_;
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
            StringMap<BoolVal> choicesMoves_;
            choicesMoves_ = movesToBeChosen(movesToBeLearnt_, initMoves_, _import.getNbMaxMoves());
            keptMovesBetweenFights.getVal((byte) _index).get(currentFight_).add(getChosenMoves(choicesMoves_));

            availableMovesBetweenFights.removeKey((byte) _index);
            availableMovesBetweenFights.put((byte) _index, choicesMoves_);
            return;
        }
        validateKeptMoves(_index, _import, currentFight_, nextKey_, tree_);
    }

    private static StringList getChosenMoves(StringMap<BoolVal> _choicesMoves) {
        StringList moves_ = new StringList();
        for (EntryCust<String, BoolVal> e: _choicesMoves.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                moves_.add(e.getKey());
            }
        }
        return moves_;
    }

    void validateKeptMoves(int _index, DataBase _import, byte _currentFight,
            KeyFightRound _nextKey,
            TreeMap<KeyFightRound, StringList> _tree) {
        int size_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).size();
        if (size_ == movesBetweenFights.getVal((byte) _index).get(_currentFight).size()) {
            StringList initMoves_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).last();
            byte currentFight_ = _currentFight;
            currentFight_++;
            _nextKey.setFight(currentFight_);
            _nextKey.setRound(IndexConstants.FIRST_INDEX);
            StringMap<BoolVal> choicesMoves_;
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
        StringMap<BoolVal> choicesMoves_;
        choicesMoves_ = movesToBeChosen(movesToBeLearnt_, initMoves_, _import.getNbMaxMoves());
        keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).add(getChosenMoves(choicesMoves_));
        availableMovesBetweenFights.getVal((byte) _index).clear();
        availableMovesBetweenFights.getVal((byte) _index).putAllMap(choicesMoves_);
    }

    public void setAbilityBetweenFights(int _index, int _fight, int _stone, String _ability) {
        keptAbilitiesBetweenFights.getVal((byte) _index).get(_fight).set(_stone, _ability);
    }

    public void setAbilityWhileFight(int _index, int _fight, int _round, String _ability) {
        keptAbilities.getVal((byte) _index).put(new KeyFightRound((byte)_fight,(byte)_round), _ability);
    }

    public void validateAllMoves(DataBase _import) {
        ok = true;
        if (!validMoves(_import)) {
            ok = false;
            return;
        }
        int nb_ = foeTeams.size();
        movesAbilities.clear();
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            Bytes indexes_ = indexesFight(i);
            int nbRounds_ = nbRound(mult.get(i), foeTeams.get(i).size());
            CustList<ByteMap<ChoiceOfEvolutionAndMoves>> list_;
            list_ = new CustList<ByteMap<ChoiceOfEvolutionAndMoves>>();
            for (byte j = IndexConstants.FIRST_INDEX; j < nbRounds_; j++) {
                ByteMap<ChoiceOfEvolutionAndMoves> map_;
                map_ = new ByteMap<ChoiceOfEvolutionAndMoves>();
                for (byte k: keptMoves.getKeys()) {
                    ChoiceOfEvolutionAndMoves choice_;
                    choice_ = new ChoiceOfEvolutionAndMoves();
                    choice_.setKeptMoves(keptMoves.getVal(k).getVal(new KeyFightRound(i,j)));
                    if (keptAbilities.getVal(k).contains(new KeyFightRound(i,j))) {
                        choice_.setAbility(keptAbilities.getVal(k).getVal(new KeyFightRound(i,j)));
                        choice_.setName(evolutionsWhileFight.getVal(k).getVal(new KeyFightRound(i,j)));
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
        for (byte f = IndexConstants.FIRST_INDEX; f < nbFrontFighters_; f++) {
            CustList<CustList<ActionSwitch>> actionsSubstitutingFrontFight_;
            actionsSubstitutingFrontFight_ = new CustList<CustList<ActionSwitch>>();
            CustList<CustList<ActionSwitch>> actionsSubstitutingBackFight_;
            actionsSubstitutingBackFight_ = new CustList<CustList<ActionSwitch>>();
            CustList<CustList<ActionMove>> actionsBeforeRoundFight_;
            actionsBeforeRoundFight_ = new CustList<CustList<ActionMove>>();
            Bytes nextFront_;
            int nbFrontFightersFight_ = frontFighters.get(f).size();
            for (byte i = IndexConstants.SECOND_INDEX; i < nbFrontFightersFight_; i++) {
                CustList<ActionSwitch> actionsSubstitutingFrontRound_;
                actionsSubstitutingFrontRound_ = new CustList<ActionSwitch>();
                CustList<ActionSwitch> actionsSubstitutingBackRound_;
                actionsSubstitutingBackRound_ = new CustList<ActionSwitch>();
                byte current_ = i;
                current_--;
                ByteMap<Byte> currentActions_ = frontFighters.get(f).get(current_);
                CustList<KeyFightRound> orderedFronts_;
                orderedFronts_ = new CustList<KeyFightRound>();
                for (byte k: currentActions_.getKeys()) {
                    if (!NumberUtil.eq(currentActions_.getVal(k), Fighter.BACK)) {
                        orderedFronts_.add(new KeyFightRound(currentActions_.getVal(k),k));
                    }
                }
                ByteMap<Byte> nextActions_ = frontFighters.get(f).get(i);
                nextFront_ = new Bytes();
                for (byte k: nextActions_.getKeys()) {
                    if (!NumberUtil.eq(nextActions_.getVal(k), Fighter.BACK)) {
                        nextFront_.add(k);
                    }
                }
                orderedFronts_.sortElts(new ComparatorFightRound());
                for (KeyFightRound k: orderedFronts_) {
                    int f_ = k.getRound();
                    ActionSwitch act_ = new ActionSwitch();
                    if (!nextFront_.containsObj(f_)) {
                        act_.setSubstitute(Fighter.BACK);
                    } else {
                        act_.setSubstitute(nextActions_.getVal((byte)f_));
                    }
                    actionsSubstitutingFrontRound_.add(act_);
                }
                int nbFighters_ = team.size();
                for (byte k = IndexConstants.FIRST_INDEX; k < nbFighters_; k++) {
                    boolean contained_ = false;
                    for (KeyFightRound k2_: orderedFronts_) {
                        int f_ = k2_.getRound();
                        if (NumberUtil.eq(f_, k)) {
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
            for (byte i = IndexConstants.FIRST_INDEX; i < nbFrontFightersFight_; i++) {
                CustList<ActionMove> actionsBeforeRound_;
                actionsBeforeRound_ = new CustList<ActionMove>();
                ByteMap<Byte> map_;
                map_ = frontFighters.get(f).get(i);
                int nbOrderFronts_ = getNbFrontFighters(map_);
                for (byte k = IndexConstants.FIRST_INDEX; k < nbOrderFronts_; k++) {
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

    private static int getNbFrontFighters(ByteMap<Byte> _m) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (EntryCust<Byte, Byte> e: _m.entryList()) {
            if (e.getValue() != Fighter.BACK) {
                i_++;
            }
        }
        return i_;
    }
    boolean validMoves(DataBase _import) {
        for (TreeMap<KeyFightRound, StringList> t: keptMoves.values()) {
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
            int i_ = IndexConstants.FIRST_INDEX;
            for (CustList<StringList> l2_: keptMovesBetweenFights.getVal(k)) {
                if (movesBetweenFights.getVal(k).get(i_).size() != l2_.size()) {
                    return false;
                }
                i_++;
            }
        }
        for (CustList<CustList<StringList>> l: keptMovesBetweenFights.values()) {
            for (CustList<StringList> l2_: l) {
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

    static StringMap<BoolVal> movesToBeChosen(StringList _allMoves, StringList _currentMoves, int _max) {
        StringList movesToBeLearnt_ = new StringList(_allMoves);
        StringMap<BoolVal> choicesMoves_;
        choicesMoves_ = new StringMap<BoolVal>();
        StringUtil.removeAllElements(movesToBeLearnt_, _currentMoves);
        for (String m: _currentMoves) {
            choicesMoves_.put(m, BoolVal.TRUE);
        }
        boolean keepAll_ = _currentMoves.size() + movesToBeLearnt_.size() <= _max;
        for (String m: movesToBeLearnt_) {
            choicesMoves_.put(m, ComparatorBoolean.of(keepAll_));
        }
        return choicesMoves_;
    }

    public StringList possibleMoves(int _fight, int _round, int _index) {
        byte place_ = frontFighters.get(_fight).get(_round).getVal((byte) _index);
        if (NumberUtil.eq(place_, Fighter.BACK)) {
            return new StringList();
        }
        if (NumberUtil.eq(_round, IndexConstants.FIRST_INDEX)) {
            if (NumberUtil.eq(_fight, IndexConstants.FIRST_INDEX)) {
                return new StringList(team.get(_index).getMoves().getKeys());
            }
            int index_ = _fight;
            index_--;
            if (!keptMovesBetweenFights.getVal((byte) _index).get(index_).isEmpty()) {
                return keptMovesBetweenFights.getVal((byte) _index).get(index_).last();
            }
        }
        TreeMap<KeyFightRound, StringList> keptMovesBefore_;
        keptMovesBefore_ = keptMoves.getVal((byte) _index);
        KeyFightRound currentKey_;
        currentKey_ = new KeyFightRound((byte)_fight,(byte)_round);
        CustList<KeyFightRound> keys_;
        keys_ = new CustList<KeyFightRound>(keptMovesBefore_.getKeys());
        int i_ = 0;
        while (true) {
            KeyFightRound k_ = keys_.get(i_);
            if (k_.getFight() != currentKey_.getFight()) {
                i_++;
                continue;
            }
            if (k_.getRound() != currentKey_.getRound()) {
                i_++;
                continue;
            }
            i_--;
            break;
        }
        return keptMovesBefore_.getVal(keys_.get(i_));
    }

    public void chooseMoveFirstFight(int _round, int _index, String _move, boolean _ally, int _foeTarget, DataBase _data) {
        if (_ally) {
            chooseMove(IndexConstants.FIRST_INDEX, _round, _index, _move, TargetCoords.toUserTarget((short) _foeTarget), _data);
            return;
        }
        chooseMove(IndexConstants.FIRST_INDEX, _round, _index, _move, TargetCoords.toFoeTarget((short) _foeTarget), _data);
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
        CustList<DualFight> duals_;
        duals_ = new CustList<DualFight>();
        CustList<TrainerLeague> leagues_;
        leagues_ = new CustList<TrainerLeague>();
        CustList<GymLeader> gymLeaders_;
        gymLeaders_ = new CustList<GymLeader>();
        CustList<GymTrainer> gymTrainers_;
        gymTrainers_ = new CustList<GymTrainer>();
        CustList<TrainerMultiFights> trainers_;
        trainers_ = new CustList<TrainerMultiFights>();
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
                duals_.add(dual_);
            } else {
                GymLeader gym_;
                gym_ = new GymLeader();
                gym_.setMultiplicityFight(mult.first());
                gym_.setTeam(foeTeams.first());
                gymLeaders_.add(gym_);
            }
        } else {
            Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
            if (place_ instanceof League) {
                int i_ = IndexConstants.FIRST_INDEX;
                for (LevelLeague l: ((League)place_).getRooms()) {
                    if (i_ == foeCoords.getLevel().getLevelIndex()) {
                        leagues_.add(l.getTrainer());
                    }
                    i_++;
                }
            } else {
                Level l_ = place_.getLevelByCoords(foeCoords);
                if (l_ instanceof LevelWithWildPokemon) {
                    if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                        DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                        duals_.add(dual_);
                    } else {
                        TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
                        trainers_.add(tr_);
                    }
                } else {
                    if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                        GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                        gymTrainers_.add(tr_);
                    } else {
                        GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                        gymLeaders_.add(tr_);
                    }
                }
            }
        }
        Player player_ = game.getPlayer();
        Bytes indexes_ = indexesFight(IndexConstants.FIRST_INDEX);
        player_.swap(indexes_);
        game.getFight().getComment().clearMessages();
        if (!leagues_.isEmpty()) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), leagues_.first(), _import);
        } else if (!gymLeaders_.isEmpty()) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), gymLeaders_.first(), _import);
        } else if (!gymTrainers_.isEmpty()) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), gymTrainers_.first(), _import);
        } else if (!duals_.isEmpty()) {
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), duals_.first(), _import);
        } else {
            TrainerMultiFights trainer_;
            trainer_ = trainers_.first();
            FightFacade.initFight(game.getFight(), player_, game.getDifficulty(), trainer_, noFight, _import);
        }
        if (!freeTeams) {
            FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
        } else {
            FightFacade.initTypeEnv(game.getFight(), environment, game.getDifficulty(), _import);
        }
        game.simuler(actionsBeforeRound.get(IndexConstants.FIRST_INDEX), actionsSubstitutingFront.get(IndexConstants.FIRST_INDEX),
                actionsSubstitutingBack.get(IndexConstants.FIRST_INDEX), movesAbilities.get(IndexConstants.FIRST_INDEX), _import);
        setComment(game.getFight().getComment().getMessages());
        if (!game.getFight().getAcceptableChoices()) {
            probleme = true;
            return;
        }
        if (proponeEvolutions()) {
            game.getFight().setChoices(movesAbilities.get(IndexConstants.FIRST_INDEX).last());
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
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
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
                    TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
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
            for (byte i = IndexConstants.FIRST_INDEX; i < nbFoes_; i++) {
                Player player_ = game.getPlayer();
                Bytes indexes_ = indexesFight(i);
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
            }
            return;
        }
        int nbFoes_ = foeTeams.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbFoes_; i++) {
            int index_ = i;
            index_++;
            Player player_ = game.getPlayer();
            Bytes indexes_ = indexesFight(i);
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
            byte i_;
            byte iPk_ = IndexConstants.FIRST_INDEX;
            for (UsablePokemon p: player_.getTeam()) {
                i_ = IndexConstants.FIRST_INDEX;
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
                i_ = IndexConstants.FIRST_INDEX;
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

    public boolean isAcceptableChoices() {
        return game.getFight().getAcceptableChoices();
    }

    public EnvironmentType getEnvironment() {
        return environment;
    }

    public IssueSimulation getIssue() {
        return game.getFight().getIssue();
    }

    Game getGame() {
        return game;
    }

    public Coords getFoeCoords() {
        return foeCoords;
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

    public Bytes getMult() {
        return mult;
    }

    public Ints getMaxActions() {
        return maxActions;
    }

    public int getNoFight() {
        return noFight;
    }

    public CustList<PokemonPlayer> getTeamAfterFight() {
        return new CustList<PokemonPlayer>(game.getPlayer().getPokemonPlayerList().values());
    }

    public CustList<PokemonPlayer> getTeam() {
        return team;
    }

    public CustList<StringList> getItems() {
        return items;
    }

    public CustList<CustList<ByteMap<Byte>>> getFrontFighters() {
        return frontFighters;
    }

    public CustList<CustList<StringList>> getUsedStones() {
        return usedStones;
    }

    public CustList<StringMap<Short>> getAvailableEvolutions() {
        return availableEvolutions;
    }

    public CustList<CustList<NameLevel>> getEvolutions() {
        return evolutions;
    }

    public CustList<CustList<NameLevel>> getInfosRealEvolutions() {
        return infosRealEvolutions;
    }

    public ByteMap<TreeMap<KeyFightRound,StringList>> getMoves() {
        return moves;
    }

    public ByteMap<TreeMap<KeyFightRound,StringList>> getAbilities() {
        return abilities;
    }

    public ByteMap<TreeMap<KeyFightRound,String>> getEvolutionsWhileFight() {
        return evolutionsWhileFight;
    }

    public ByteMap<CustList<CustList<StringList>>> getMovesBetweenFights() {
        return movesBetweenFights;
    }

    public ByteMap<CustList<CustList<StringList>>> getAbilitiesBetweenFights() {
        return abilitiesBetweenFights;
    }

    public ByteMap<CustList<StringList>> getEvolutionsBetweenFights() {
        return evolutionsBetweenFights;
    }

    public ByteMap<AvailableMovesInfos> getAvailableMoves() {
        return availableMoves;
    }

    public ByteMap<StringMap<BoolVal>> getAvailableMovesBetweenFights() {
        return availableMovesBetweenFights;
    }

    public ByteMap<TreeMap<KeyFightRound,StringList>> getKeptMoves() {
        return keptMoves;
    }

    public ByteMap<TreeMap<KeyFightRound,String>> getKeptAbilities() {
        return keptAbilities;
    }

    public ByteMap<CustList<CustList<StringList>>> getKeptMovesBetweenFights() {
        return keptMovesBetweenFights;
    }

    public ByteMap<CustList<StringList>> getKeptAbilitiesBetweenFights() {
        return keptAbilitiesBetweenFights;
    }

    ByteMap<Byte> getCurrentFights() {
        return currentFights;
    }

    public CustList<CustList<ByteMap<ChoiceOfEvolutionAndMoves>>> getMovesAbilities() {
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

    public StringList getComment() {
        return comment;
    }

    public void setComment(StringList _comment) {
        comment = _comment;
    }
}
