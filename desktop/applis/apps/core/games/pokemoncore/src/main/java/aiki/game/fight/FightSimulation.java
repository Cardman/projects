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
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.AvailableMovesInfos;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.*;
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
import aiki.util.TargetCoordsList;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.util.CustList;
import code.util.EntryCust;
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
    private final CustList<Coords> foeCoordsAll;

    private int noFight;

    private final Bytes mult;
    private final Bytes multAll;

    private final Ints maxActions;
    private final Ints maxActionsAll;

    private final CustList<CustList<PkTrainer>> foeTeams;
    private final CustList<CustList<PkTrainer>> foeTeamsAll;

    private final CustList<PkTrainer> allyTeam = new CustList<PkTrainer>();
    private final CustList<CustList<PkTrainer>> allyTeamAll = new CustList<CustList<PkTrainer>>();

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
    private final FightSimulationActions fightSimulationActions;

    private boolean probleme;

    private StringList comment;

    private boolean ok;

    private EnvironmentType environment = EnvironmentType.ROAD;
    private final CustList<EnvironmentType> environmentAll;

    private FightType fightType = FightType.NOTHING;

    private int indexFight;

    public FightSimulation(Difficulty _diff, DataBase _import) {
        game = new Game(_import);
        game.initUtilisateurSimulation(DataBase.EMPTY_STRING, _diff, _import);
        team = new CustList<PokemonPlayer>();
        items = new CustList<StringList>();
        evolutions = new CustList<CustList<NameLevel>>();
        frontFighters = new CustList<CustList<ByteMap<Byte>>>();
        mult = new Bytes();
        multAll = new Bytes();
        foeCoordsAll = new CustList<Coords>();
        environmentAll = new CustList<EnvironmentType>();
        maxActions = new Ints();
        maxActionsAll = new Ints();
        infosRealEvolutions = new CustList<CustList<NameLevel>>();
        usedStones = new CustList<CustList<StringList>>();
        foeTeams = new CustList<CustList<PkTrainer>>();
        foeTeamsAll = new CustList<CustList<PkTrainer>>();
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
        fightSimulationActions = new FightSimulationActions();
        evolutionsWhileFight = new ByteMap<TreeMap<KeyFightRound,String>>();
    }

    public void initializeFights(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = _foeCoords;
        Place place_ = place(_index, _import);
        if (place_ instanceof League) {
            for (Level l: place_.getLevelsList()) {
                LevelLeague level_ = (LevelLeague) l;
                initializeFightLeague(level_);
                environmentAll.add(_import.envType(foeCoordsAll.last()));
            }
            fightType = FightType.DRESSEUR_LIGUE;
        } else {
            initializeFightNonLeague(place_);
            environmentAll.add(_import.envType(foeCoordsAll.last()));
        }
    }

    public void initializeFight(Coords _foeCoords, int _index, DataBase _import) {
        foeCoords = new Coords(_foeCoords);
        Place place_ = place(_index, _import);
        if (place_ instanceof League) {
            LevelLeague l_ = (LevelLeague) place_.getLevelsList().get(foeCoords.getLevel().getLevelIndex());
            foeCoords.getLevel().getPoint().affect(l_.getTrainerCoords());
            initializeFightLeague(l_);
            environmentAll.add(_import.envType(foeCoordsAll.last()));
            for (LevelLeague l: ((League) place_).getRooms().mid(foeCoords.getLevel().getLevelIndex()+1)) {
                TrainerLeague t_ = l.getTrainer();
                byte mult_ = t_.getMultiplicityFight();
                Coords co_ = new Coords(_foeCoords);
                co_.getLevel().setLevelIndex((byte) (foeCoords.getLevel().getLevelIndex()+foeCoordsAll.size()));
                co_.getLevel().getPoint().affect(l.getTrainerCoords());
                store(mult_,mult_,t_.getTeam(), co_);
                environmentAll.add(_import.envType(foeCoordsAll.last()));
            }
            fightType = FightType.DRESSEUR_LIGUE;
        } else {
            initializeFightNonLeague(place_);
            environmentAll.add(_import.envType(foeCoordsAll.last()));
        }
    }

    private void initializeFightLeague(LevelLeague _l) {
        items();
        byte mult_ = _l.getTrainer().getMultiplicityFight();
        maxActions.add((int) mult_);
        mult.add(mult_);
        foeTeams.add(new CustList<PkTrainer>(_l.getTrainer().getTeam()));
        Coords co_ = new Coords(foeCoords);
        co_.getLevel().getPoint().affect(_l.getTrainerCoords());
        store(maxActions.last(), mult.last(), foeTeams.last(), co_);
    }

    private void items() {
        items.add(new StringList());
        usedStones.add(new CustList<StringList>());
    }

    private void store(int _max, byte _m, CustList<PkTrainer> _t, Coords _c) {
        maxActionsAll.add(_max);
        multAll.add(_m);
        foeTeamsAll.add(_t);
        foeCoordsAll.add(_c);
        allyTeamAll.add(new CustList<PkTrainer>());
    }

    private void initializeFightNonLeague(Place _place) {
        Level l_ = _place.getLevelByCoords(foeCoords);
        byte mult_;
        int nbMax_;
        if (l_ instanceof LevelWithWildPokemon) {
            if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
                DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
                mult_ = dual_.getFoeTrainer().getMultiplicityFight();
                nbMax_ = 1;
                foeTeams.add(new CustList<PkTrainer>(dual_.getFoeTrainer().getTeam()));
                foeTeamsAll.add(foeTeams.last());
                allyTeam.addAllElts(dual_.getAlly().getTeam());
                fightType = FightType.TMP_TRAINER;
            } else {
                TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
                CustList<PkTrainer> team_ = tr_.getTeamsRewards().get(noFight).getTeam();
                mult_ = tr_.getMultiplicityFight();
                nbMax_ = mult_;
                foeTeams.add(new CustList<PkTrainer>(team_));
                foeTeamsAll.add(foeTeams.last());
                fightType = FightType.DRESSEUR;
            }
        } else {
            if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
                GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
                mult_ = tr_.getMultiplicityFight();
                foeTeams.add(new CustList<PkTrainer>(tr_.getTeam()));
                foeTeamsAll.add(foeTeams.last());
                fightType = FightType.DRESSEUR_GYM;
            } else {
                GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
                mult_ = tr_.getMultiplicityFight();
                foeTeams.add(new CustList<PkTrainer>(tr_.getTeam()));
                foeTeamsAll.add(foeTeams.last());
                fightType = FightType.GYM_LEADER;
            }
            nbMax_ = mult_;
        }
        allyTeamAll.add(allyTeam);
        mult.add(mult_);
        multAll.add(mult_);
        maxActions.add(nbMax_);
        maxActionsAll.add(nbMax_);
        foeCoordsAll.add(new Coords(foeCoords));
        items.add(new StringList());
        usedStones.add(new CustList<StringList>());
    }

    private Place place(int _index, DataBase _import) {
        freeTeams = false;
        noFight = _index;
        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
        indexFight=0;
        maxActions.clear();
        foeCoordsAll.clear();
        environmentAll.clear();
        maxActionsAll.clear();
        mult.clear();
        multAll.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        foeTeamsAll.clear();
        allyTeamAll.clear();
        allyTeam.clear();
        return place_;
    }

    public void setTeams(CustList<FreeTeamChoice> _choice, Coords _coords) {
        foeCoords = new Coords(_coords);
        indexFight=0;
        environment = _choice.get(0).getEnv();
        freeTeams = true;
        maxActions.clear();
        maxActionsAll.clear();
        foeCoordsAll.clear();
        environmentAll.clear();
        mult.clear();
        multAll.clear();
        items.clear();
        usedStones.clear();
        foeTeams.clear();
        foeTeamsAll.clear();
        allyTeam.clear();
        allyTeamAll.clear();
        allyTeam.addAllElts(_choice.get(0).getAllyTeam());
        maxActions.add(_choice.get(0).getNbMaxActions());
        mult.add((byte) _choice.get(0).getMultiplicity());
        foeTeams.add(_choice.get(0).getFoeTeam());
        for (FreeTeamChoice l: _choice) {
            allyTeamAll.add(l.getAllyTeam());
            foeCoordsAll.add(new Coords(_coords));
            maxActionsAll.add(l.getNbMaxActions());
            environmentAll.add(l.getEnv());
            multAll.add((byte) l.getMultiplicity());
            foeTeamsAll.add(l.getFoeTeam());
        }
        items.add(new StringList());
        usedStones.add(new CustList<StringList>());
    }

    public void nextFight() {
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        CustList<Level> list_ = place_.getLevelsList();
//        byte index_ = foeCoords.getLevel().getLevelIndex();
//        index_++;
        indexFight++;
//        LevelLeague l_ = (LevelLeague)list_.get(index_);
        maxActions.clear();
        mult.clear();
        items.clear();
        moves.clear();
        abilities.clear();
        evolutions.clear();
        usedStones.clear();
        foeTeams.clear();
        allyTeam.clear();
//        foeCoords.getLevel().setLevelIndex(index_);
//        foeCoords.getLevel().getPoint().affect(l_.getTrainerCoords());
//        byte mult_ = l_.getTrainer().getMultiplicityFight();
        maxActions.add(maxActionsAll.get(indexFight));
//        maxActions.add((int) mult_);
        mult.add(multAll.get(indexFight));
//        mult.add(mult_);
        items.add(new StringList());
        usedStones.add(new CustList<StringList>());
        foeTeams.add(foeTeamsAll.get(indexFight));
        allyTeam.addAllElts(allyTeamAll.get(indexFight));
        foeCoords = foeCoordsAll.get(indexFight);
        environment = environmentAll.get(indexFight);
//        foeTeams.add(l_.getTrainer().getTeam());
    }

    public boolean hasNextFight() {
        return foeTeamsAll.isValidIndex(indexFight+1);
//        if (freeTeams) {
//            return false;
//        }
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        if (!(place_ instanceof League)) {
//            return false;
//        }
//        CustList<Level> list_ = place_.getLevelsList();
//        byte index_ = foeCoords.getLevel().getLevelIndex();
//        index_++;
//        return list_.isValidIndex(index_);
    }

    public static StringMap<MoveData> possiblesInitialMoves(String _name, short _level, DataBase _import) {
        PokemonData data_ = _import.getPokemon(_name);
        String basePk_ = data_.getBaseEvo();
        StringMap<Short> evos_ = PokemonPlayer.getAllEvolutions(basePk_, _level, true, _import);
        StringList keys_ = new StringList(evos_.getKeys());
        StringList moves_ = new StringList();
        StringList res_ = keys_.filterEndsWith(StringUtil.concat(PokemonPlayer.SEPARATOR,_name));
        if (res_.isEmpty() || _level < evos_.getVal(res_.first())) {
            feedPossiblesInitialMoves(data_, _level, moves_, _import);
            moves_.removeDuplicates();
            return expand(moves_,_import);
        }
        StringList path_ = StringUtil.splitStrings(res_.first(), PokemonPlayer.SEPARATOR);
        int index_ = IndexConstants.FIRST_INDEX;
        int nbPaths_ = path_.size();
        for (int i = IndexConstants.SECOND_INDEX; i < nbPaths_; i++) {
            String beforeEvo_ = path_.get(index_);
            PokemonData dataBeforeEvo_ = _import.getPokemon(beforeEvo_);
            short level_ = evos_.getVal(StringUtil.join(path_.left(i + 1), PokemonPlayer.SEPARATOR));
            feedPossiblesInitialMoves(dataBeforeEvo_, level_, moves_, _import);
            index_++;
        }
        feedPossiblesInitialMoves(data_, _level, moves_, _import);
        moves_.removeDuplicates();
        return expand(moves_,_import);
    }

    public static StringMap<MoveData> expand(CustList<String> _moves, DataBase _import) {
        StringMap<MoveData> moves_ = new StringMap<MoveData>();
        for (String m: _moves) {
            MoveData v_ = _import.getMove(m);
            if (v_ != null) {
                moves_.addEntry(m, v_);
            }
        }
        return moves_;
    }
    private static void feedPossiblesInitialMoves(PokemonData _data, short _level, StringList _moves, DataBase _import) {
        for (LevelMove p: _data.getLevMoves()) {
            if (p.getLevel() > _level) {
                break;
            }
            _moves.add(p.getMove());
        }
        _moves.addAllElts(_data.getMoveTutors());
        for (Short m: _data.getHiddenMoves()) {
            _moves.add(_import.getHm().getVal(m));
        }
        for (Short m: _data.getTechnicalMoves()) {
            _moves.add(_import.getTm().getVal(m));
        }
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
            StringMap<Short> direct_ = direct(p.getName(), p.getLevel(), _import);
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
        restoreAvailableEvolutions(direct(_currentEvo, _level, _import), _index);
        evolutions.get(_index).add(new NameLevel(_currentEvo, _level));
    }

    private void restoreAvailableEvolutions(StringMap<Short> _currentEvo, int _index) {
        availableEvolutions.get(_index).clear();
        availableEvolutions.get(_index).putAllMap(_currentEvo);
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
        restoreAvailableEvolutions(direct(previous_.getName(), previous_.getLevel(), _import), _index);
        while (_indexOfCancel < evolutions.get(_index).size()) {
            evolutions.get(_index).remove(_indexOfCancel);
        }
    }

    private static StringMap<Short> direct(String _base, short _level, DataBase _import) {
        StringMap<Short> direct_ = new StringMap<Short>();
        for (EntryCust<String, Short> k: getNextEvos(_base, _level, _import).entryList()) {
            String prefix_ = StringUtil.concat(_base, FightSimulation.SEPARATOR_PK);
            String rep_ = StringUtil.replaceBegin(k.getKey(), prefix_);
            direct_.put(rep_, k.getValue());
        }
        return direct_;
    }

    private static StringMap<Short> getNextEvos(String _base, short _level, DataBase _import) {
        StringMap<Short> evos_;
        evos_ = PokemonPlayer.getAllEvolutions(_base, _level, true, _import);
        StringList keys_ = new StringList(evos_.getKeys());
        keys_ = getNextEvos(keys_);
        StringMap<Short> next_ = new StringMap<Short>();
        for (String e: keys_) {
            next_.addEntry(e, evos_.getVal(e));
        }
        return next_;
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
        PseudoPlayer pseudoPlayer_ = new PseudoPlayer(team, evolutions, items);
        currentFights.clear();
        moves.clear();
        availableMoves.clear();
        keptMoves.clear();
        evolutionsWhileFight.clear();
        abilities.clear();
        keptAbilities.clear();
        infosRealEvolutions.clear();
        int nbTeams_ = team.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbTeams_; i++) {
            infosRealEvolutions.add(new CustList<NameLevel>());
        }
        int nb_ = foeTeams.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            CustList<PseudoPlayerFighter> playerFighter_ = playerFighter(_import, pseudoPlayer_, i);
            int nbPlayerFighters_ = playerFighter_.size();
            for (byte j = IndexConstants.FIRST_INDEX; j < nbPlayerFighters_; j++) {
                PseudoPlayerFighter f_ = playerFighter_.get(j);
                affectPseudoPlayer(j, f_, pseudoPlayer_);
                TreeMap<KeyFightRound, String> treeEvos_ = new TreeMap<KeyFightRound, String>(new ComparatorFightRound());
                feedEvolutionsWhileFight(f_, treeEvos_, i);
                evolutionsWhileFight.put(j, treeEvos_);
                TreeMap<KeyFightRound, StringList> treeMoves_ = new TreeMap<KeyFightRound, StringList>(new ComparatorFightRound());
                feedMoves(i, f_, treeMoves_);
                moves.put(j, treeMoves_);
                StringList initMoves_ = new StringList(team.get(j).getMoves().getKeys());
                StringMap<BoolVal> choicesMoves_ = movesToBeChosen(treeMoves_.firstValue(), initMoves_, _import.getNbMaxMoves());
                KeyFightRound firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
                availableMoves.put(j, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
                treeMoves_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
                treeMoves_.put(firstFightRound_, new StringList(initMoves_));
                keptMoves.put(j, treeMoves_);
                TreeMap<KeyFightRound, StringList> treeAbilities_ = new TreeMap<KeyFightRound, StringList>(new ComparatorFightRound());
                feedAbilities(i, f_, treeAbilities_);
                abilities.put(j, treeAbilities_);
                TreeMap<KeyFightRound, String> treeKept_ = keptAbilities(treeAbilities_);
                keptAbilities.put(j, treeKept_);
                currentFights.put(j, (byte) 0);
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
        PseudoPlayer pseudoPlayer_ = new PseudoPlayer(team, evolutions, items);
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
            CustList<PseudoPlayerFighter> playerFighter_ = playerFighter(_import, pseudoPlayer_, i);
            int nbPlayerFighters_ = playerFighter_.size();
            for (byte j = IndexConstants.FIRST_INDEX; j < nbPlayerFighters_; j++) {
                PseudoPlayerFighter f_ = playerFighter_.get(j);
                affectPseudoPlayer(j, f_, pseudoPlayer_);
                evolutionsWhileFight(i, j, f_);
//                if (moves.contains(indexes_.get(j))) {
                moves(_import, i, j, f_);
//                if (abilities.contains(indexes_.get(j))) {
                abilities(i, j, f_);
                currentFights.put(j, (byte) 0);
            }
            CustList<StringList> usedStones_;
            usedStones_ = new CustList<StringList>();
            CustList<PseudoPokemonPlayer> team_ = pseudoPlayer_.getTeam();
            int nbPk_ = team_.size();
            for (byte j = IndexConstants.FIRST_INDEX; j < nbPk_; j++) {
                betweenFights(_import, pseudoPlayer_, usedStones_, j);
            }
            usedStones.set(i, usedStones_);
            if (index_ < items.size()) {
                setItem(team_, items.get(index_));
            }
        }
    }

    private void setItem(CustList<PseudoPokemonPlayer> _team, StringList _items) {
        int nbPk_ = _team.size();
        for (byte j = IndexConstants.FIRST_INDEX; j < nbPk_; j++) {
            _team.get(j).setItem(_items.get(j));
        }
    }

    private void betweenFights(DataBase _import, PseudoPlayer _pseudoPlayer, CustList<StringList> _usedStones, byte _j) {
        CustList<PseudoPokemonPlayer> team_ = _pseudoPlayer.getTeam();
        PseudoPokemonPlayer p_ = team_.get(_j);
        PokemonData data_ = _import.getPokemon(p_.getName());
        StringList nextEvos_ = nextEvos(_pseudoPlayer, _j, p_, data_);
        StringList usedStonesPokemon_ = new StringList();
        CustList<CustList<StringList>> listMoves_;
        CustList<CustList<StringList>> listKeptMoves_;
        if (movesBetweenFights.contains(_j)) {
            listMoves_ = movesBetweenFights.getVal(_j);
            listKeptMoves_ = keptMovesBetweenFights.getVal(_j);
        } else {
            listMoves_ = new CustList<CustList<StringList>>();
            listKeptMoves_ = new CustList<CustList<StringList>>();
        }
        CustList<StringList> listEvolutions_;
        if (evolutionsBetweenFights.contains(_j)) {
            listEvolutions_ = evolutionsBetweenFights.getVal(_j);
        } else {
            listEvolutions_ = new CustList<StringList>();
        }
        CustList<StringList> groupsMoves_ = new CustList<StringList>();
        CustList<StringList> groupsAbilities_ = new CustList<StringList>();
        StringList groupsKeptAbilities_ = new StringList();
        StringList groupsEvolutions_ = new StringList();
        for (String e: nextEvos_) {
            if (!(data_.getEvolutions().getVal(e) instanceof EvolutionStone)) {
                break;
            }
            usedStonesPokemon_.add(((EvolutionStone)data_.getEvolutions().getVal(e)).getStone());
            p_.setName(e);
            infosRealEvolutions.get(_j).add(new NameLevel(e,p_.getLevel()));
            data_ = _import.getPokemon(e);
            StringList moves_ = new StringList();
            for (LevelMove p2_: data_.getLevMoves()) {
                if (p2_.getLevel() > p_.getLevel()) {
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
        CustList<CustList<StringList>> listAbilities_;
        CustList<StringList> listKeptAbilities_;
        if (abilitiesBetweenFights.contains(_j)) {
            listAbilities_ = abilitiesBetweenFights.getVal(_j);
            listKeptAbilities_ = keptAbilitiesBetweenFights.getVal(_j);
        } else {
            listAbilities_ = new CustList<CustList<StringList>>();
            listKeptAbilities_ = new CustList<StringList>();
        }
        listAbilities_.add(groupsAbilities_);
        listKeptAbilities_.add(groupsKeptAbilities_);
        listEvolutions_.add(groupsEvolutions_);
        movesBetweenFights.put(_j, listMoves_);
        keptMovesBetweenFights.put(_j, listKeptMoves_);
        abilitiesBetweenFights.put(_j, listAbilities_);
        keptAbilitiesBetweenFights.put(_j, listKeptAbilities_);
        evolutionsBetweenFights.put(_j, listEvolutions_);
        _usedStones.add(usedStonesPokemon_);
    }

    private StringList nextEvos(PseudoPlayer _pseudoPlayer, byte _i, PseudoPokemonPlayer _p, PokemonData _data) {
        StringList nextEvos_;
        nextEvos_ = new StringList();
        boolean add_ = false;
        for (NameLevel p2_: _pseudoPlayer.getEvolutions().get(_i)) {
            if (_data.getEvolutions().contains(p2_.getName())) {
                add_ = true;
            }
            if (!add_) {
                continue;
            }
            if (NumberUtil.eq(p2_.getLevel(), _p.getLevel())) {
                nextEvos_.add(p2_.getName());
            }
        }
        return nextEvos_;
    }

    private CustList<PseudoPlayerFighter> playerFighter(DataBase _import, PseudoPlayer _pseudoPlayer, byte _i) {
        PseudoFight pseudoFight_;
        pseudoFight_ = new PseudoFight(foeTeams.get(_i), _pseudoPlayer, mult.get(_i), frontFighters.get(_i));
        pseudoFight_.presimulateFight(game.getDifficulty(), _import);
        return pseudoFight_.getPlayerFighters();
    }

    private void abilities(byte _i, byte _j, PseudoPlayerFighter _f) {
        if (abilities.contains(_j)) {
            TreeMap<KeyFightRound,StringList> tree_;
//                    tree_ = abilities.getVal(indexes_.get(j));
            tree_ = abilities.getVal(_j);
            TreeMap<KeyFightRound,String> treeKept_;
            treeKept_ = keptAbilities.getVal(_j);
            feedAbilitiesKept(_i, _f, tree_, treeKept_);
        } else {
            TreeMap<KeyFightRound,StringList> tree_;
            tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
            feedAbilities(_i, _f, tree_);
            //                    abilities.put(indexes_.get(j), tree_);
            abilities.put(_j, tree_);
            TreeMap<KeyFightRound, String> treeKept_ = keptAbilities(tree_);
//                    keptAbilities.put(indexes_.get(j), treeKept_);
            keptAbilities.put(_j, treeKept_);
        }
    }

    private void moves(DataBase _import, byte _i, byte _j, PseudoPlayerFighter _f) {
        if (moves.contains(_j)) {
            TreeMap<KeyFightRound,StringList> tree_;
//                    tree_ = moves.getVal(indexes_.get(j));
            tree_ = moves.getVal(_j);
            feedMoves(_i, _f, tree_);
        } else {
            TreeMap<KeyFightRound,StringList> tree_;
            tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
            feedMoves(_i, _f, tree_);
            //                    moves.put(indexes_.get(j), tree_);
            moves.put(_j, tree_);
            StringMap<BoolVal> choicesMoves_;
            StringList initMoves_ = new StringList(team.get(_j).getMoves().getKeys());
            choicesMoves_ = movesToBeChosen(tree_.firstValue(), initMoves_, _import.getNbMaxMoves());
            KeyFightRound firstFightRound_;
            firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
//                    availableMoves.put(indexes_.get(j), new Pair<>(firstFightRound_,choicesMoves_));
            availableMoves.put(_j, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
            tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
            tree_.put(firstFightRound_, new StringList(initMoves_));
//                    keptMoves.put(indexes_.get(j), tree_);
            keptMoves.put(_j, tree_);
        }
    }

    private void evolutionsWhileFight(byte _i, byte _j, PseudoPlayerFighter _f) {
        if (evolutionsWhileFight.contains(_j)) {
            TreeMap<KeyFightRound,String> tree_;
            tree_ = evolutionsWhileFight.getVal(_j);
            feedEvolutionsWhileFight(_f, tree_, _i);
        } else {
            TreeMap<KeyFightRound,String> tree_;
            tree_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
            feedEvolutionsWhileFight(_f, tree_, _i);
            evolutionsWhileFight.put(_j, tree_);
        }
    }


    private TreeMap<KeyFightRound, String> keptAbilities(TreeMap<KeyFightRound, StringList> _tree) {
        TreeMap<KeyFightRound,String> treeKept_;
        treeKept_ = new TreeMap<KeyFightRound,String>(new ComparatorFightRound());
        for (KeyFightRound k: _tree.getKeys()) {
            treeKept_.put(new KeyFightRound(k), _tree.getVal(k).first());
        }
        return treeKept_;
    }

    private void feedAbilitiesKept(byte _i, PseudoPlayerFighter _f, TreeMap<KeyFightRound, StringList> _tree, TreeMap<KeyFightRound, String> _treeKept) {
        CustList<StringList> abilitiesList_ = _f.getAbilities();
        int nb_ = abilitiesList_.size();
        for (byte j = IndexConstants.FIRST_INDEX; j < nb_; j++) {
            if (!abilitiesList_.get(j).isEmpty()) {
                _tree.put(new KeyFightRound(_i, j), abilitiesList_.get(j));
                _treeKept.put(new KeyFightRound(_i, j), abilitiesList_.get(j).first());
            }
        }
    }

    private void feedMoves(byte _i, PseudoPlayerFighter _f, TreeMap<KeyFightRound, StringList> _tree) {
        CustList<StringList> movesList_ = _f.getMoves();
        int nb_ = movesList_.size();
        for (byte j = IndexConstants.FIRST_INDEX; j < nb_; j++) {
            _tree.put(new KeyFightRound(_i, j), movesList_.get(j));
        }
    }

    private void feedAbilities(byte _i, PseudoPlayerFighter _f, TreeMap<KeyFightRound, StringList> _tree) {
        CustList<StringList> abilitiesList_ = _f.getAbilities();
        int nb_ = abilitiesList_.size();
        for (byte j = IndexConstants.FIRST_INDEX; j < nb_; j++) {
            if (!abilitiesList_.get(j).isEmpty()) {
                _tree.put(new KeyFightRound(_i, j), abilitiesList_.get(j));
            }
        }
    }

    private void affectPseudoPlayer(byte _j, PseudoPlayerFighter _f, PseudoPlayer _pseudoPlayer) {
        infosRealEvolutions.get(_j).addAllElts(_f.getInfosRealEvolutions());
        _pseudoPlayer.getEvolutions().get(_j).clear();
        _pseudoPlayer.getEvolutions().get(_j).addAllElts(_f.getEvoLevels());
        _pseudoPlayer.getTeam().get(_j).setLevel(_f.getLevel());
        _pseudoPlayer.getTeam().get(_j).setName(_f.getName());
        _pseudoPlayer.getTeam().get(_j).affectWonPointsSinceLastLevel(_f.getWonExpSinceLastLevel());
    }

    private void feedEvolutionsWhileFight(PseudoPlayerFighter _f, TreeMap<KeyFightRound, String> _tree, byte _i) {
        for (byte o: _f.getEvolutions().getKeys()) {
            _tree.put(new KeyFightRound(_i, o), _f.getEvolutions().getVal(o));
        }
    }

    boolean validFrontFighters() {
        int nb_ = frontFighters.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            if (invalidFrontFighters(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean invalidFrontFighters(int _i) {
        for (ByteMap<Byte> r2_: frontFighters.get(_i)) {
            if (invalidFrontFighters(_i, r2_)) {
                return true;
            }
        }
        return false;
    }

    private boolean invalidFrontFighters(int _i, ByteMap<Byte> _r) {
        Bytes front_ = new Bytes();
        Bytes places_ = new Bytes();
        for (byte f: _r.getKeys()) {
            byte a_ = _r.getVal(f);
            if (NumberUtil.eq(a_, Fighter.BACK)) {
                continue;
            }
            places_.add(a_);
            front_.add(f);
        }
        if (places_.hasDuplicates() || places_.size() > mult.get(_i) || places_.isEmpty()) {
            return true;
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
        return min_ != 0 || max_ != places_.size() - 1 || front_.size() > maxActions.get(_i);
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
        ByteMap<Byte> fighters_ = frontFighters.get(_fight).first();
        Bytes indexes_ = new Bytes();
        Bytes indexesBack_ = new Bytes();
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
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        TreeMap<KeyFightRound, StringList> tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(nextKey_)) {
            availableMoves.getVal((byte) _index).getMoves().clear();
            affectKeptMoves((byte) _index, key_, _moves);
            return;
        }
        for (String s: availableMoves.getVal((byte) _index).getMoves().getKeys()) {
            availableMoves.getVal((byte) _index).getMoves().put(s, BoolVal.FALSE);
        }
        for (String s: _moves) {
            availableMoves.getVal((byte) _index).getMoves().put(s, BoolVal.TRUE);
        }
        affectKeptMoves((byte) _index, key_, _moves);
        validateMovesOneFight(_index, _import);
    }

    private void affectKeptMoves(byte _index, KeyFightRound _key, StringList _moves) {
        StringList moves_ = keptMoves.getVal(_index).getVal(_key);
        moves_.clear();
        moves_.addAllElts(_moves);
    }

    public boolean isAvailableMoves(int _index) {
        return !availableMoves.getVal((byte) _index).getMoves().isEmpty();
    }

    public boolean isAvailableAbilities(int _index) {
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).contains(key_);
    }

    public StringList getAvailableAbilities(int _index) {
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        return abilities.getVal((byte) _index).getVal(key_);
    }

    void addMove(int _index, String _move) {
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
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
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
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
        TreeMap<KeyFightRound, StringList> tree_ = moves.getVal((byte) _index);
        StringList initMoves_ = new StringList(team.get((byte) _index).getMoves().getKeys());
        StringMap<BoolVal> choicesMoves_ = movesToBeChosen(tree_.firstValue(), initMoves_, _import.getNbMaxMoves());
        KeyFightRound firstFightRound_ = new KeyFightRound(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        availableMoves.put((byte) _index, new AvailableMovesInfos(firstFightRound_,choicesMoves_));
        tree_ = new TreeMap<KeyFightRound,StringList>(new ComparatorFightRound());
        tree_.put(firstFightRound_, new StringList(initMoves_));
        keptMoves.put((byte) _index, tree_);
    }

    public void cancelMovesOneFight(int _index, DataBase _import) {
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound previousKey_ = new KeyFightRound(key_);
        previousKey_.setRound((byte) (previousKey_.getRound()-1));
        KeyFightRound previousPrevKey_ = new KeyFightRound(previousKey_);
        previousPrevKey_.setRound((byte) (previousPrevKey_.getRound()-1));
        TreeMap<KeyFightRound, StringList> tree_ = moves.getVal((byte) _index);
        if (!tree_.contains(previousPrevKey_)) {
            StringList initMoves_= new StringList(team.get(_index).getMoves().getKeys());
            StringMap<BoolVal> choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(previousKey_);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            keptMoves.getVal((byte) _index).put(previousKey_, getChosenMoves(choicesMoves_));
            keptMoves.getVal((byte) _index).getVal(key_).clear();
            return;
        }
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(previousPrevKey_);
        StringMap<BoolVal> choicesMoves_ = movesToBeChosen(tree_.getVal(previousKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(previousKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        keptMoves.getVal((byte) _index).getVal(key_).clear();
    }

    void validateMovesOneFight(int _index, DataBase _import) {
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        TreeMap<KeyFightRound, StringList> tree_ = moves.getVal((byte) _index);
        StringList initMoves_ = keptMoves.getVal((byte) _index).getVal(key_);
        StringMap<BoolVal> choicesMoves_ = movesToBeChosen(tree_.getVal(nextKey_), initMoves_, _import.getNbMaxMoves());
        availableMoves.getVal((byte) _index).setFirst(nextKey_);
        availableMoves.getVal((byte) _index).getMoves().clear();
        availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
        keptMoves.getVal((byte) _index).put(nextKey_, getChosenMoves(choicesMoves_));
    }

    public void validateMoves(int _index, DataBase _import) {
        byte currentFight_ = currentFights.getVal((byte) _index);
        KeyFightRound key_ = availableMoves.getVal((byte) _index).getKey();
        KeyFightRound nextKey_ = new KeyFightRound(key_);
        nextKey_.setRound((byte) (nextKey_.getRound()+1));
        boolean betweenFights_= true;
        TreeMap<KeyFightRound, StringList> tree_ = moves.getVal((byte) _index);
        for (KeyFightRound k: tree_.getKeys()) {
            if (NumberUtil.eq(k.getFight(), nextKey_.getFight()) && NumberUtil.eq(k.getRound(), nextKey_.getRound())) {
                betweenFights_ = false;
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
            StringMap<BoolVal> choicesMoves_ = movesToBeChosen(_tree.getVal(_nextKey), initMoves_, _import.getNbMaxMoves());
            availableMoves.getVal((byte) _index).setFirst(_nextKey);
            availableMoves.getVal((byte) _index).getMoves().clear();
            availableMoves.getVal((byte) _index).getMoves().putAllMap(choicesMoves_);
            currentFights.put((byte) _index, currentFight_);
            keptMoves.getVal((byte) _index).put(_nextKey, getChosenMoves(choicesMoves_));
            return;
        }
        StringList initMoves_ = keptMovesBetweenFights.getVal((byte) _index).get(_currentFight).last();
        StringList movesToBeLearnt_ = movesBetweenFights.getVal((byte) _index).get(_currentFight).get(size_);
        StringMap<BoolVal> choicesMoves_ = movesToBeChosen(movesToBeLearnt_, initMoves_, _import.getNbMaxMoves());
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
        movesAbilitiesInit();
        fightSimulationActions.getActionsBeforeRound().clear();
        fightSimulationActions.getActionsSubstitutingBack().clear();
        fightSimulationActions.getActionsSubstitutingFront().clear();
        int nbFrontFighters_ = frontFighters.size();
        for (byte f = IndexConstants.FIRST_INDEX; f < nbFrontFighters_; f++) {
            actionsSubstitutingFigtht(f);
            actionsBeforeRoundFight(f);
        }
    }

    private void actionsBeforeRoundFight(byte _f) {
        int nbFrontFightersFight_ = frontFighters.get(_f).size();
        CustList<CustList<ActionMove>> actionsBeforeRoundFight_ = new CustList<CustList<ActionMove>>();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbFrontFightersFight_; i++) {
            CustList<ActionMove> actionsBeforeRound_ = new CustList<ActionMove>();
            ByteMap<Byte> map_ = frontFighters.get(_f).get(i);
            int nbOrderFronts_ = getNbFrontFighters(map_);
            for (byte k = IndexConstants.FIRST_INDEX; k < nbOrderFronts_; k++) {
                ActionMove action_ = new ActionMove();
                action_.setFirstChosenMove(DataBase.EMPTY_STRING);
                action_.setChosenTargets(new TargetCoordsList());
                actionsBeforeRound_.add(action_);
            }
            actionsBeforeRoundFight_.add(actionsBeforeRound_);
        }
        fightSimulationActions.getActionsBeforeRound().add(actionsBeforeRoundFight_);
    }

    private void actionsSubstitutingFigtht(byte _f) {
        int nbFrontFightersFight_ = frontFighters.get(_f).size();
        CustList<CustList<ActionSwitch>> actionsSubstitutingFrontFight_ = new CustList<CustList<ActionSwitch>>();
        CustList<CustList<ActionSwitch>> actionsSubstitutingBackFight_ = new CustList<CustList<ActionSwitch>>();
        for (byte i = IndexConstants.SECOND_INDEX; i < nbFrontFightersFight_; i++) {
            CustList<ActionSwitch> actionsSubstitutingFrontRound_ = new CustList<ActionSwitch>();
            CustList<ActionSwitch> actionsSubstitutingBackRound_ = new CustList<ActionSwitch>();
            byte current_ = i;
            current_--;
            Bytes orderedFronts_ = orderedFronts(_f, current_);
            ByteMap<Byte> nextFront_ = nextFront(_f, i);
            int nb_ = orderedFronts_.size();
            for (int k = 0; k < nb_; k++) {
                ActionSwitch act_ = actionSwitch(nextFront_, orderedFronts_.get(k));
                actionsSubstitutingFrontRound_.add(act_);
            }
            int nbFighters_ = team.size();
            for (byte k = IndexConstants.FIRST_INDEX; k < nbFighters_; k++) {
                boolean contained_ = containedFront(orderedFronts_, k);
                if (contained_) {
                    continue;
                }
                ActionSwitch act_ = actionSwitch(nextFront_, k);
                actionsSubstitutingBackRound_.add(act_);
            }
            actionsSubstitutingFrontFight_.add(actionsSubstitutingFrontRound_);
            actionsSubstitutingBackFight_.add(actionsSubstitutingBackRound_);
        }
        fightSimulationActions.getActionsSubstitutingBack().add(actionsSubstitutingBackFight_);
        fightSimulationActions.getActionsSubstitutingFront().add(actionsSubstitutingFrontFight_);
    }

    private boolean containedFront(Bytes _orderedFronts, byte _k) {
        int nb_ = _orderedFronts.size();
        boolean contained_ = false;
        for (int l = 0; l < nb_; l++) {
            if (NumberUtil.eq(_orderedFronts.get(l), _k)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private ByteMap<Byte> nextFront(byte _f, byte _i) {
        ByteMap<Byte> nextActions_ = frontFighters.get(_f).get(_i);
        ByteMap<Byte> nextFront_ = new ByteMap<Byte>();
        for (EntryCust<Byte, Byte> k: nextActions_.entryList()) {
            if (!NumberUtil.eq(k.getValue(), Fighter.BACK)) {
                nextFront_.addEntry(k.getKey(),k.getValue());
            }
        }
        return nextFront_;
    }

    private static ActionSwitch actionSwitch(ByteMap<Byte> _nextFront, int _f) {
        ActionSwitch act_ = new ActionSwitch();
        if (!_nextFront.contains((byte) _f)) {
            act_.setSubstitute(Fighter.BACK);
        } else {
            act_.setSubstitute(_nextFront.getVal((byte) _f));
        }
        return act_;
    }

    private Bytes orderedFronts(byte _f, byte _current) {
        ByteMap<Byte> currentActions_ = frontFighters.get(_f).get(_current);
        CustList<KeyFightRound> orderedFronts_ = new CustList<KeyFightRound>();
        for (EntryCust<Byte, Byte> k: currentActions_.entryList()) {
            if (!NumberUtil.eq(k.getValue(), Fighter.BACK)) {
                orderedFronts_.add(new KeyFightRound(k.getValue(),k.getKey()));
            }
        }
        orderedFronts_.sortElts(new ComparatorFightRound());
        int nb_ = orderedFronts_.size();
        Bytes orderedFrontsList_ = new Bytes();
        for (int i = 0; i < nb_; i++) {
            orderedFrontsList_.add((byte) orderedFronts_.get(i).getRound());
        }
        return orderedFrontsList_;
    }

    private void movesAbilitiesInit() {
        int nbFrontFighters_ = frontFighters.size();
        fightSimulationActions.getMovesAbilities().clear();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbFrontFighters_; i++) {
            int nbFrontFightersFight_ = frontFighters.get(i).size();
            Bytes indexes_ = indexesFight(i);
            CustList<ByteMap<ChoiceOfEvolutionAndMoves>> list_ = new CustList<ByteMap<ChoiceOfEvolutionAndMoves>>();
            for (byte j = IndexConstants.FIRST_INDEX; j < nbFrontFightersFight_; j++) {
                ByteMap<ChoiceOfEvolutionAndMoves> map_ = new ByteMap<ChoiceOfEvolutionAndMoves>();
                for (byte k: keptMoves.getKeys()) {
                    ChoiceOfEvolutionAndMoves choice_ = new ChoiceOfEvolutionAndMoves();
                    choice_.setKeptMoves(keptMoves.getVal(k).getVal(new KeyFightRound(i,j)));
                    if (keptAbilities.getVal(k).contains(new KeyFightRound(i,j))) {
                        choice_.setAbility(keptAbilities.getVal(k).getVal(new KeyFightRound(i,j)));
                        choice_.setName(evolutionsWhileFight.getVal(k).getVal(new KeyFightRound(i,j)));
                    }
                    map_.put(indexes_.get(k), choice_);
                }
                list_.add(map_);
            }
            fightSimulationActions.getMovesAbilities().add(list_);
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
                if (emptyOrTooBig(_import, m)) {
                    return false;
                }
            }
        }
        return validMovesBetweenFights(_import);
    }

    private boolean validMovesBetweenFights(DataBase _import) {
        for (EntryCust<Byte, CustList<CustList<StringList>>> k: keptMovesBetweenFights.entryList()) {
            CustList<CustList<StringList>> movesLists_ = k.getValue();
            int nb_ = movesLists_.size();
            for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
                if (movesBetweenFights.getVal(k.getKey()).get(i).size() != movesLists_.get(i).size()) {
                    return false;
                }
            }
            for (CustList<StringList> l2_: movesLists_) {
                for (StringList m: l2_) {
                    if (emptyOrTooBig(_import, m)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean emptyOrTooBig(DataBase _import, StringList _m) {
        return _m.isEmpty() || _m.size() > _import.getNbMaxMoves();
    }

    static StringMap<BoolVal> movesToBeChosen(StringList _allMoves, StringList _currentMoves, int _max) {
        StringList movesToBeLearnt_ = new StringList(_allMoves);
        StringMap<BoolVal> choicesMoves_ = new StringMap<BoolVal>();
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
        if (NumberUtil.eq(_round, IndexConstants.FIRST_INDEX) && !NumberUtil.eq(_fight, IndexConstants.FIRST_INDEX)) {
            int index_ = _fight;
            index_--;
            if (!keptMovesBetweenFights.getVal((byte) _index).get(index_).isEmpty()) {
                return keptMovesBetweenFights.getVal((byte) _index).get(index_).last();
            }
        }
        TreeMap<KeyFightRound, StringList> keptMovesBefore_ = keptMoves.getVal((byte) _index);
        KeyFightRound currentKey_ = new KeyFightRound((byte) _fight, (byte) _round);
//        CustList<KeyFightRound> keys_;
//        keys_ = new CustList<KeyFightRound>(keptMovesBefore_.getKeys());
        int nb_ = keptMovesBefore_.size();
        int foundPrev_ = -1;
        for (int i  = 0; i < nb_; i++) {
            KeyFightRound k_ = keptMovesBefore_.getKey(i);
            if (k_.getFight() != currentKey_.getFight() || k_.getRound() != currentKey_.getRound()) {
                continue;
            }
            foundPrev_ = i - 1;
        }
        if (!keptMovesBefore_.isValidIndex(foundPrev_)) {
            return new StringList(team.get(_index).getMoves().getKeys());
        }
//        int i_ = 0;
//        while (true) {
//            KeyFightRound k_ = keys_.get(i_);
//            if (k_.getFight() != currentKey_.getFight()) {
//                i_++;
//                continue;
//            }
//            if (k_.getRound() != currentKey_.getRound()) {
//                i_++;
//                continue;
//            }
//            i_--;
//            break;
//        }
        return keptMovesBefore_.getValue(foundPrev_);
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
        ActionMove action_ = fightSimulationActions.getActionsBeforeRound().get(_fight).get(_round).get(place_);
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
        Player player_ = game.getPlayer();
        Bytes indexes_ = indexesFight(IndexConstants.FIRST_INDEX);
        player_.swap(indexes_);
        game.getFight().getComment().clearMessages();
        byte mult_ = multFigtht();
        byte multPl_ = multFigthtPlayer(mult_);
        Fight fight_ = game.getFight();
        Difficulty diff_ = game.getDifficulty();
        FightInitialization.initDefaultFight(fight_, mult_, multPl_);
        FightInitialization.initEquipeUtilisateur(_import, player_, diff_, multPl_, mult_, allyTeam(), fight_);
        fightType(fight_);
        FightInitialization.initEquipeAdversaire(_import, player_, foeTeam(), diff_, fight_);
        FightFacade.beginFight(fight_, _import);
//        if (!freeTeams) {
//            FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
//        } else {
//            FightFacade.initTypeEnv(game.getFight(), environment, game.getDifficulty(), _import);
//        }
        FightFacade.initTypeEnv(game.getFight(), environment, game.getDifficulty(), _import);
        game.simuler(fightSimulationActions,IndexConstants.FIRST_INDEX, _import);
        setComment(game.getFight().getComment().getMessages());
        if (!game.getFight().getAcceptableChoices()) {
            probleme = true;
            return;
        }
        if (proponeEvolutions()) {
            game.getFight().setChoices(fightSimulationActions.getMovesAbilities().get(IndexConstants.FIRST_INDEX).last());
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

    private void fightType(Fight _fight) {
//        CustList<DualFight> duals_;
//        duals_ = new CustList<DualFight>();
//        CustList<TrainerLeague> leagues_;
//        leagues_ = new CustList<TrainerLeague>();
//        CustList<GymLeader> gymLeaders_;
//        gymLeaders_ = new CustList<GymLeader>();
//        CustList<GymTrainer> gymTrainers_;
//        gymTrainers_ = new CustList<GymTrainer>();
//        CustList<TrainerMultiFights> trainers_;
//        trainers_ = new CustList<TrainerMultiFights>();
//        if (freeTeams) {
//            if (!allyTeam.isEmpty()) {
//                DualFight dual_;
//                dual_ = new DualFight();
//                Ally ally_;
//                ally_ = new Ally();
//                ally_.setTeam(allyTeam);
//                dual_.setAlly(ally_);
//                TempTrainer tmp_;
//                tmp_ = new TempTrainer();
//                tmp_.setMultiplicityFight(mult.first());
//                tmp_.setTeam(foeTeams.first());
//                dual_.setFoeTrainer(tmp_);
//                duals_.add(dual_);
//            } else {
//                GymLeader gym_;
//                gym_ = new GymLeader();
//                gym_.setMultiplicityFight(mult.first());
//                gym_.setTeam(foeTeams.first());
//                gymLeaders_.add(gym_);
//            }
//        } else {
//            Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//            if (place_ instanceof League) {
//                int i_ = IndexConstants.FIRST_INDEX;
//                for (LevelLeague l: ((League)place_).getRooms()) {
//                    if (i_ == foeCoords.getLevel().getLevelIndex()) {
//                        leagues_.add(l.getTrainer());
//                    }
//                    i_++;
//                }
//            } else {
//                Level l_ = place_.getLevelByCoords(foeCoords);
//                if (l_ instanceof LevelWithWildPokemon) {
//                    if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
//                        DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
//                        duals_.add(dual_);
//                    } else {
//                        TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
//                        trainers_.add(tr_);
//                    }
//                } else {
//                    if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
//                        GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
//                        gymTrainers_.add(tr_);
//                    } else {
//                        GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
//                        gymLeaders_.add(tr_);
//                    }
//                }
//            }
//        }
//        if (!leagues_.isEmpty()) {
//            _fight.setFightType(FightType.DRESSEUR_LIGUE);
//        } else if (!gymLeaders_.isEmpty()) {
//            _fight.setFightType(FightType.GYM_LEADER);
//        } else if (!gymTrainers_.isEmpty()) {
//            _fight.setFightType(FightType.DRESSEUR_GYM);
//        } else if (!duals_.isEmpty()) {
//            _fight.setFightType(FightType.TMP_TRAINER);
//        } else {
//            _fight.setFightType(FightType.DRESSEUR);
//        }
        _fight.setFightType(fightType);
    }

    private byte multFigtht() {
        if (!mult.isValidIndex(noFight)) {
            return mult.first();
        }
        return mult.get(noFight);
//        if (freeTeams) {
//            return mult.first();
//        }
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        if (place_ instanceof League) {
//            return ((League)place_).getRooms().get(foeCoords.getLevel().getLevelIndex()).getTrainer().getMultiplicityFight();
//        }
//        Level l_ = place_.getLevelByCoords(foeCoords);
//        if (l_ instanceof LevelWithWildPokemon) {
//            if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
//                DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
//                return dual_.getFoeTrainer().getMultiplicityFight();
//            } else {
//                TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
//                return tr_.getMultiplicityFight();
//            }
//        } else {
//            if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
//                GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
//                return tr_.getMultiplicityFight();
//            } else {
//                GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
//                return tr_.getMultiplicityFight();
//            }
//        }
    }
    private byte multFigthtPlayer(byte _mult) {
        if (!allyTeam.isEmpty()) {
            return (byte)DataBase.ONE_POSSIBLE_CHOICE;
        }
        return _mult;
//        if (freeTeams) {
//            if (!allyTeam.isEmpty()) {
//                return (byte)DataBase.ONE_POSSIBLE_CHOICE;
//            }
//            return _mult;
//        }
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        Level l_ = place_.getLevelByCoords(foeCoords);
//        if (l_ instanceof LevelWithWildPokemon && ((LevelWithWildPokemon) l_).containsDualFight(foeCoords.getLevel().getPoint())) {
//            return (byte)DataBase.ONE_POSSIBLE_CHOICE;
//        }
//        return _mult;
    }
    private CustList<PkTrainer> allyTeam() {
        return allyTeam;
//        if (freeTeams) {
//            return allyTeam;
//        }
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        Level l_ = place_.getLevelByCoords(foeCoords);
//        if (l_ instanceof LevelWithWildPokemon && ((LevelWithWildPokemon) l_).containsDualFight(foeCoords.getLevel().getPoint())) {
//            DualFight dual_ = ((LevelWithWildPokemon) l_).getDualFight(foeCoords.getLevel().getPoint());
//            return dual_.getAlly().getTeam();
//        }
//        return new CustList<PkTrainer>();
    }
    private CustList<PkTrainer> foeTeam() {
        if (!foeTeams.isValidIndex(noFight)) {
            return foeTeams.first();
        }
        return foeTeams.get(noFight);
//        if (freeTeams) {
//            return foeTeams.first();
//        }
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        if (place_ instanceof League) {
//            return ((League)place_).getRooms().get(foeCoords.getLevel().getLevelIndex()).getTrainer().getTeam();
//        }
//        Level l_ = place_.getLevelByCoords(foeCoords);
//        if (l_ instanceof LevelWithWildPokemon) {
//            if (((LevelWithWildPokemon)l_).containsDualFight(foeCoords.getLevel().getPoint())) {
//                DualFight dual_ = ((LevelWithWildPokemon)l_).getDualFight(foeCoords.getLevel().getPoint());
//                return dual_.getFoeTrainer().getTeam();
//            } else {
//                TrainerMultiFights tr_ = ((LevelWithWildPokemon)l_).getTrainers().getVal(foeCoords.getLevel().getPoint());
//                return tr_.getTeamsRewards().get(noFight).getTeam();
//            }
//        } else {
//            if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
//                GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
//                return tr_.getTeam();
//            } else {
//                GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
//                return tr_.getTeam();
//            }
//        }
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
//        Place place_ = _import.getMap().getPlace(foeCoords.getNumberPlace());
//        CustList<Trainer> trainers_ = trainersList(place_);
        CustList<PkTrainer> allyTeam_ = allyTeam();
        if (!allyTeam_.isEmpty()) {
            int nbFoes_ = foeTeams.size();
            byte mult_ = multFigtht();
            byte multPl_ = multFigthtPlayer(mult_);
            CustList<PkTrainer> foeTeam_ = foeTeam();
            for (byte i = IndexConstants.FIRST_INDEX; i < nbFoes_; i++) {
                Player player_ = game.getPlayer();
                Bytes indexes_ = indexesFight(i);
                player_.swap(indexes_);
                Fight fight_ = game.getFight();
                Difficulty diff_ = game.getDifficulty();
                FightInitialization.initDefaultFight(fight_, mult_,multPl_);
                FightInitialization.initEquipeUtilisateur(_import, player_, diff_, fight_.getPlayerMaxNumberFrontFighters(), fight_.getMult(), allyTeam_, fight_);
                fight_.setFightType(fightType);
                FightInitialization.initEquipeAdversaire(_import, player_, foeTeam_, diff_, fight_);
                if (issueFight(_import, i, fight_, player_, indexes_)) {
                    return;
                }
            }
            return;
        }
        StringMap<Short> pps_ = ppsDataBase(_import);
        int nbFoes_ = foeTeams.size();
//        int nbFoes_ = trainers_.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbFoes_; i++) {
            int index_ = i;
            index_++;
            Player player_ = game.getPlayer();
            Bytes indexes_ = indexesFight(i);
            player_.swap(indexes_);
//            Trainer trainer_;
//            trainer_ = trainers_.get(i);
            Fight fight_ = game.getFight();
            Difficulty diff_ = game.getDifficulty();
            FightInitialization.initUserTeam(fight_, player_, diff_, _import, mult.get(i));
//            FightInitialization.initUserTeam(fight_, player_, diff_, _import, trainer_.getMultiplicityFight());
            CustList<PkTrainer> foeTeam_ = foeTeams.get(i);
//            CustList<PkTrainer> foeTeam_ = foeTeamMultiple(trainer_, fight_);
            FightInitialization.initEquipeAdversaire(_import, player_, foeTeam_, diff_, fight_);
            if (issueFight(_import, i, fight_, player_, indexes_)) {
                return;
            }
//            FightFacade.beginFight(fight_, _import);
//            FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
//            game.simuler(fightSimulationActions,i, _import);
//            if (!game.getFight().getAcceptableChoices()) {
//                probleme = true;
//                return;
//            }
//            FightFacade.endFight(game.getFight());
//            player_.affectEndFight(game.getFight(), game.getDifficulty(), _import);
//            player_.restore(indexes_);
//            byte i_;
            afterFight(pps_, i, index_, player_);
        }
    }

    private boolean issueFight(DataBase _import, byte _i, Fight _fight, Player _player, Bytes _indexes) {
        FightFacade.beginFight(_fight, _import);
        FightFacade.initTypeEnv(game.getFight(), environment, game.getDifficulty(), _import);
//        FightFacade.initTypeEnv(game.getFight(), foeCoords, game.getDifficulty(), _import);
        game.simuler(fightSimulationActions, _i, _import);
        if (!game.getFight().getAcceptableChoices()) {
            probleme = true;
            return true;
        }
        FightFacade.endFight(game.getFight());
        _player.affectEndFight(game.getFight(), game.getDifficulty(), _import);
        _player.restore(_indexes);
        return false;
    }

    private void afterFight(StringMap<Short> _pps, byte _i, int _index, Player _player) {
        CustList<UsablePokemon> team_ = _player.getTeam();
        int teamSize_ = team_.size();
        for (byte k = 0; k < teamSize_; k++) {
            StringList evos_ = evolutionsBetweenFights.getVal(k).get(_i);
            int evoSize_ = evos_.size();
            for (int j = 0; j < evoSize_; j++) {
                PokemonPlayer pk_;
                pk_ = (PokemonPlayer) team_.get(k);
                pk_.setName(evos_.get(j));
                pk_.setAbility(keptAbilitiesBetweenFights.getVal(k).get(_i).get(j));
                StringList moves_;
                moves_ = keptMovesBetweenFights.getVal(k).get(_i).get(j);
                pk_.getMoves().clear();
                for (String m: moves_) {
                    pk_.getMoves().put(m, new UsesOfMove(_pps.getVal(m)));
                }
            }
        }
        if (_index < items.size()) {
            for (byte k = 0; k < teamSize_; k++) {
                ((Pokemon) team_.get(k)).setItem(items.get(_index).get(k));
            }
        }
    }

    private static StringMap<Short> ppsDataBase(DataBase _import) {
        StringMap<Short> pps_;
        pps_ = new StringMap<Short>();
        for (EntryCust<String,MoveData> m: _import.getMoves().entryList()) {
            pps_.put(m.getKey(), m.getValue().getPp());
        }
        return pps_;
    }

//    private CustList<Trainer> trainersList(Place _place) {
//        CustList<Trainer> trainers_;
//        trainers_ = new CustList<Trainer>();
//        if (_place instanceof League) {
//            for (LevelLeague l: ((League) _place).getRooms()) {
//                trainers_.add(l.getTrainer());
//            }
//        } else {
//            Level l_ = _place.getLevelByCoords(foeCoords);
//            if (l_ instanceof LevelWithWildPokemon) {
//                PointsTrainerMultiFights trainersList_ = ((LevelWithWildPokemon) l_).getTrainers();
//                if (trainersList_.contains(foeCoords.getLevel().getPoint())) {
//                    TrainerMultiFights tr_ = trainersList_.getVal(foeCoords.getLevel().getPoint());
//                    trainers_.add(tr_);
//                }
//            } else {
//                if (((LevelIndoorGym)l_).getGymTrainers().contains(foeCoords.getLevel().getPoint())) {
//                    GymTrainer tr_ = ((LevelIndoorGym)l_).getGymTrainers().getVal(foeCoords.getLevel().getPoint());
//                    trainers_.add(tr_);
//                } else {
//                    GymLeader tr_ = ((LevelIndoorGym)l_).getGymLeader();
//                    trainers_.add(tr_);
//                }
//            }
//        }
//        return trainers_;
//    }
//
//    private CustList<PkTrainer> foeTeamMultiple(Trainer _trainer, Fight _fight) {
//        CustList<PkTrainer> foeTeam_;
//        if (_trainer instanceof TrainerLeague) {
//            _fight.setFightType(FightType.DRESSEUR_LIGUE);
//            foeTeam_ = ((TrainerOneFight) _trainer).getTeam();
//        } else if (_trainer instanceof GymLeader) {
//            _fight.setFightType(FightType.GYM_LEADER);
//            foeTeam_ = ((TrainerOneFight) _trainer).getTeam();
//        } else if (_trainer instanceof GymTrainer) {
//            _fight.setFightType(FightType.DRESSEUR_GYM);
//            foeTeam_ = ((TrainerOneFight) _trainer).getTeam();
//        } else {
//            _fight.setFightType(FightType.DRESSEUR);
//            foeTeam_ = ((TrainerMultiFights) _trainer).getTeamsRewards().get(noFight).getTeam();
//        }
//        return foeTeam_;
//    }

    boolean validChoicesMoves() {
        for (CustList<CustList<ActionMove>> f: fightSimulationActions.getActionsBeforeRound()) {
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
            if (f.estKo() || f.estArriere()) {
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

    public CustList<CustList<PkTrainer>> getAllyTeamAll() {
        return allyTeamAll;
    }

    public Bytes getMultAll() {
        return multAll;
    }

    public CustList<EnvironmentType> getEnvironmentAll() {
        return environmentAll;
    }

    public CustList<CustList<PkTrainer>> getFoeTeamsAll() {
        return foeTeamsAll;
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
        return fightSimulationActions.getMovesAbilities();
    }

    public CustList<CustList<CustList<ActionMove>>> getActionsBeforeRound() {
        return fightSimulationActions.getActionsBeforeRound();
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingFront() {
        return fightSimulationActions.getActionsSubstitutingFront();
    }

    public CustList<CustList<CustList<ActionSwitch>>> getActionsSubstitutingBack() {
        return fightSimulationActions.getActionsSubstitutingBack();
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
