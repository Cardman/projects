package aiki.map.pokemon;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingPp;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionLevel;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.pokemon.evolution.EvolutionStoneGender;
import aiki.fight.status.StatusType;
import aiki.fight.util.LevelMove;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fighter;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public final class PokemonPlayer extends Pokemon implements UsablePokemon {

    public static final String SEPARATOR = "/";

    public static final String POKEMON_PLAYER = "aiki.map.pokemon.pokemonplayer";

//    private static final String CENT = Byte.toString(Fighter.RATE_CENT);

    private static final String HAPPINESS = "happiness";

    private static final String DECREASING_HP = "decreasingHp";


    /***/
    private String name;

    /***/
    private short level;

    /***/
    private Gender gender;

    /**non modifiable une fois affecte a l'objet.*/
    private String ability;

    /**si la chaine de caractere est vide alors le pokemon ne porte pas d'objet, sinon cette chaine vaut le nom de l'objet. */
    private String item;

    /***/
    private Rate remainingHp;

    /***/
    private StringList status;

    /***/
    private EnumMap<Statistic,Short> iv;

    /**nickname du pokemon par defaut le nom du pokemon*/
    private String nickname;

    /***/
    private StringMap<UsesOfMove> moves;

    /***/
    private EnumMap<Statistic,Short> ev;

    /**Points d'experience gagnes depuis la derniere montee de niveau*/
    private Rate wonExpSinceLastLevel;

    /**happiness du pokemon (0 a 255)*/
    private short happiness;

    /**nom de la ball ayant capture le pokemon. Si ce nom est vide, alors le pokemon vient d'une eclosion ou d'un don.*/
    private String usedBallCatching;

    /**Nombre de pas effectue en tete d'equipe.*/
    private short nbStepsTeamLead;

    private Comment commentPk = new Comment();

    /***/
    private StringList newAbilitiesToBeChosen = new StringList();

    /***/
    private StringMap<Boolean> movesToBeKeptEvo = new StringMap<Boolean>();

    /***/
    private String possibleEvolution = DataBase.EMPTY_STRING;

    /***/
    private boolean trading;

    public PokemonPlayer() {
        name = DataBase.EMPTY_STRING;
        level = 1;
        gender = Gender.NO_GENDER;
        ability = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
        remainingHp = Rate.zero();
        status = new StringList();
        nickname = DataBase.EMPTY_STRING;
        moves = new StringMap<UsesOfMove>();
        ev = new EnumMap<Statistic,Short>();
        wonExpSinceLastLevel = Rate.zero();
        usedBallCatching = DataBase.EMPTY_STRING;
    }

    public PokemonPlayer(Pokemon _pokemonDonne,DataBase _import,Sex _sex) {
        this(_pokemonDonne,_import,_sex.getGender());
    }
    public PokemonPlayer(Pokemon _pokemonDonne,DataBase _import,Gender _sex) {
        this(_pokemonDonne,_import);
        setGender(_sex);
    }
    public PokemonPlayer(Pokemon _pokemonDonne,DataBase _import) {
        this(_pokemonDonne, _import, getMovesAtLevel(_pokemonDonne.getName(),_pokemonDonne.getLevel(),_import));
    }

    public PokemonPlayer(Pokemon _pokemonDonne,DataBase _import, StringMap<Short> _moves) {
//        super(_pokemonDonne);
        name = _pokemonDonne.getName();
        level = _pokemonDonne.getLevel();
        gender = _pokemonDonne.getGender();
        ability = _pokemonDonne.getAbility();
        item = _pokemonDonne.getItem();
        initMoves(_moves);
        initEvIv(_import,true);
        status = new StringList();
        nickname=getName();
        usedBallCatching=_import.getDefaultBall();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappiness();
        obtention();
        initPvRestants(_import);
    }
    public PokemonPlayer(Fossil _fossile,DataBase _import){
//        name = DataBase.EMPTY_STRING;
//        level = 1;
//        gender = Gender.NO_GENDER;
//        ability = DataBase.EMPTY_STRING;
//        item = DataBase.EMPTY_STRING;
        status = new StringList();
        setName(_fossile.getPokemon());
        setLevel(_fossile.getLevel());
        setItem(DataBase.EMPTY_STRING);
        nickname=getName();
        initAttaques(_import,true);
        initAleaCapaciteGenre(_import);
        usedBallCatching=_import.getDefaultBall();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappiness();
        obtention();
        initPvRestants(_import);
    }

    public PokemonPlayer(Egg _oeuf,DataBase _import){
//        name = DataBase.EMPTY_STRING;
//        level = 1;
//        gender = Gender.NO_GENDER;
//        ability = DataBase.EMPTY_STRING;
//        item = DataBase.EMPTY_STRING;
        status = new StringList();
        setName(_oeuf.getName());
        setLevel((short) _import.getMinLevel());
        setItem(DataBase.EMPTY_STRING);
        nickname=getName();
        initAttaques(_import,true);
        initAleaCapaciteGenre(_import);
        usedBallCatching=_import.getDefaultBall();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappinessHatch();
        obtention();
        initPvRestants(_import);
    }

    public PokemonPlayer(Fighter _pokemonSauvage,String _pseudo,String _ballCapture,DataBase _import){
//        name = DataBase.EMPTY_STRING;
//        level = 1;
//        gender = Gender.NO_GENDER;
//        ability = DataBase.EMPTY_STRING;
//        item = DataBase.EMPTY_STRING;
        status = new StringList();
        setName(_pokemonSauvage.getName());
        nickname=_pseudo;
        setLevel(_pokemonSauvage.getLevel());
        setGender(_pokemonSauvage.getGender());
        setItem(_pokemonSauvage.getItem());
        setAbility(_pokemonSauvage.getAbility());
        usedBallCatching=_ballCapture;
        happiness=_pokemonSauvage.getHappiness();
        for(String c:_pokemonSauvage.getStatusSet()){
            if (_import.getStatus(c).getDisabledEffIfSwitch()) {
                continue;
            }
            if(!Numbers.eq(_pokemonSauvage.getStatusNbRoundShort(c), 0)){
                status.add(c);
            }
        }
        remainingHp = Rate.zero();
        remainingHp.affect(_pokemonSauvage.getRemainingHp());
        initAttaques(_import,true);
        obtention();
    }

    void initAleaCapaciteGenre(DataBase _import){
        PokemonData fPk_=_import.getPokemon(getName());
        GenderRepartition repartitionGenre_=fPk_.getGenderRep();
        MonteCarloEnum<Gender> loiGenre_ = new MonteCarloEnum<Gender>();
        for(Gender g:repartitionGenre_.getPossibleGenders()){
            loiGenre_.addEvent(g,DataBase.defElementaryEvent());
        }
        LgInt maxRd_ = _import.getMaxRd();
        setGender(loiGenre_.editNumber(maxRd_,_import.getGenerator()));
        MonteCarloString loiCapac_ = new MonteCarloString();
        for(String e:fPk_.getAbilities()){
            loiCapac_.addEvent(e,DataBase.defElementaryEvent());
        }
        setAbility(loiCapac_.editNumber(maxRd_,_import.getGenerator()));
    }

    void initAttaques(DataBase _import, boolean _initEv){
        initMoves(getMovesAtLevel(getName(), getLevel(), _import));
        initEvIv(_import, _initEv);
    }

    void initMoves(StringMap<Short> _moves) {
        if (!trading) {
            moves = new StringMap<UsesOfMove>();
        }
        for (String m: _moves.getKeys()) {
            short pp_ = _moves.getVal(m);
            moves.put(m, new UsesOfMove(pp_));
        }
    }

    void initEvIv(DataBase _import, boolean _initEv) {
        if (!trading) {
            iv = new EnumMap<Statistic,Short>();
            ev = new EnumMap<Statistic,Short>();
        }
        for(Statistic c:Statistic.getStatisticsWithBase()){
            if (_initEv) {
                ev.put(c, (short)0);
            }
            iv.put(c, (short)_import.getMaxIv());
        }
    }

    static StringMap<Short> getMovesAtLevel(String _name, short _level, DataBase _import) {
        StringMap<Short> moves_ = new StringMap<Short>();
        PokemonData fPk_=_import.getPokemon(_name);
        for (String m: fPk_.getMovesAtLevel(_level, _import.getNbMaxMoves())) {
            MoveData fAtt_=_import.getMove(m);
            moves_.put(m, fAtt_.getPp());
        }
        return moves_;
    }

    public StringList getDirectEvolutions(DataBase _import) {
        PokemonData fPk_ = _import.getPokemon(getName());
        return fPk_.getDirectEvolutions(getGender(), true);
    }

    public StringMap<Short> getAllEvolutions(DataBase _import) {
        return getAllEvolutions(getName(), getLevel(), false, _import);
    }

    public static StringMap<Short> getAllEvolutions(String _base, short _level, boolean _sep, DataBase _import) {
        StringMap<Short> evolutionsLevels_ = new StringMap<Short>();
        StringMap<Short> currentEvolutions_ = new StringMap<Short>();
        currentEvolutions_.put(_base, (short) CustList.FIRST_INDEX);
        StringMap<Short> newEvolutions_;
        if (_sep) {
            while (true) {
                newEvolutions_ = new StringMap<Short>();
                for (String e: currentEvolutions_.getKeys()) {
                    PokemonData fPk_ = _import.getPokemon(StringList.splitStrings(e, SEPARATOR).last());
                    for (String e2_: fPk_.getEvolutions().getKeys()) {
                        Evolution evo_ = fPk_.getEvolution(e2_);
                        short max_ = (short) Math.max(currentEvolutions_.getVal(e), _level);
                        if (evo_ instanceof EvolutionLevel) {
                            max_= (short) Math.max(max_, ((EvolutionLevel) evo_).getLevel());
                        }
                        newEvolutions_.put(StringList.concat(e,SEPARATOR,e2_), max_);
                    }
                }
                if (newEvolutions_.isEmpty()) {
                    break;
                }
                evolutionsLevels_.putAllMap(newEvolutions_);
                currentEvolutions_ = new StringMap<Short>(newEvolutions_);
            }
        } else {
            while (true) {
                newEvolutions_ = new StringMap<Short>();
                for (String e: currentEvolutions_.getKeys()) {
                    PokemonData fPk_ = _import.getPokemon(e);
                    for (String e2_: fPk_.getEvolutions().getKeys()) {
                        Evolution evo_ = fPk_.getEvolution(e2_);
                        short max_ = (short) Math.max(currentEvolutions_.getVal(e), _level);
                        if (evo_ instanceof EvolutionLevel) {
                            max_= (short) Math.max(max_, ((EvolutionLevel) evo_).getLevel());
                        }
                        newEvolutions_.put(e2_, max_);
                    }
                }
                if (newEvolutions_.isEmpty()) {
                    break;
                }
                evolutionsLevels_.putAllMap(newEvolutions_);
                currentEvolutions_ = new StringMap<Short>(newEvolutions_);
            }
        }
        return evolutionsLevels_;
    }

    public void initPvRestants(DataBase _import){
        remainingHp=pvMax(_import);
    }

    public void initIv(Difficulty _diff){
        commentPk = new Comment();
        newAbilitiesToBeChosen = new StringList();
        movesToBeKeptEvo = new StringMap<Boolean>();
        possibleEvolution = DataBase.EMPTY_STRING;
        trading = false;
        if (iv == null) {
            iv = new EnumMap<Statistic,Short>();
        }
        for(Statistic s: Statistic.getStatisticsWithBase()){
            iv.put(s, _diff.getIvPlayer());
        }
    }

    public void initHp(DataBase _import) {
        commentPk.clearMessages();
        StringMap<String> mess_ = _import.getMessagesPokemonPlayer();
        if (Rate.strGreater(remainingHp, pvMax(_import))) {
            String name_ = _import.translatePokemon(getName());
            commentPk.addMessage(mess_.getVal(DECREASING_HP), name_, pvMax(_import).toNumberString(), remainingHp.toNumberString());
            remainingHp = pvMax(_import);
        }
    }

    public void obtention(){
        wonExpSinceLastLevel=Rate.zero();
        nbStepsTeamLead=0;
    }

    @Override
    public boolean validate(DataBase _data) {
        if (hasJustBeenCreated()) {
            return false;
        }
        if (getLevel() < _data.getMinLevel()) {
            return false;
        }
        //level >= 1
        if (getLevel() > _data.getMaxLevel()) {
            return false;
        }
        if (!_data.getPokedex().contains(getName())) {
            return false;
        }
        if (!_data.getAbilities().contains(getAbility())) {
            return false;
        }
        if (!getItem().isEmpty()) {
            if (!_data.getItems().contains(getItem())) {
                return false;
            }
        }
        if (happiness < 0) {
            return false;
        }
        if (happiness > _data.getHappinessMax()) {
            return false;
        }
        if (moves.isEmpty()) {
            return false;
        }
        if (moves.size() > _data.getNbMaxMoves()) {
            return false;
        }
        for (EntryCust<String, UsesOfMove> m: moves.entryList()) {
            if (StringList.quickEq(m.getKey(), _data.getDefaultMove())) {
                return false;
            }
            if (!_data.getMoves().contains(m.getKey())) {
                return false;
            }
            UsesOfMove v_ = m.getValue();
            if (v_.getCurrent() < 0) {
                return false;
            }
            if (v_.getMax() < v_.getCurrent()) {
                return false;
            }
            if (v_.getMax() > _data.getMaxPp()) {
                return false;
            }
            if (v_.getMax() == 0) {
                return false;
            }
        }
        if (!Statistic.equalsSet(ev.getKeys(), Statistic.getStatisticsWithBase())) {
            return false;
        }
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            if (ev.getVal(s) < 0) {
                return false;
            }
        }
        for (String s: status) {
            if (!_data.getStatus().contains(s)) {
                return false;
            }
            if (_data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                return false;
            }
        }
        if (nbStepsTeamLead < 0) {
            return false;
        }
        if (nbStepsTeamLead >= _data.getNbNecStepsIncrHappiness()) {
            return false;
        }
        if (!remainingHp.isZeroOrGt()) {
            return false;
        }
        PokemonData fPk_ = _data.getPokemon(getName());
        if (Rate.strGreater(remainingHp, fPk_.statHp(getLevel(),ev,iv))) {
            return false;
        }
        if (!_data.getItems().contains(usedBallCatching)) {
            return false;
        }
        if (!(_data.getItem(usedBallCatching) instanceof Ball)) {
            return false;
        }
        if (!wonExpSinceLastLevel.isZeroOrGt()) {
            return false;
        }
        return true;
    }

    public void initilializeFromExchange(DataBase _dateBase){
        trading = true;
        StringList existingMoves_ = new StringList();
        if (moves == null) {
            moves = new StringMap<UsesOfMove>();
        }
        if (ev == null) {
            ev = new EnumMap<Statistic,Short>();
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                ev.put(s, (short) 0);
            }
        } else {
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                if (!ev.contains(s)) {
                    ev.put(s, (short) 0);
                }
                //Here: ev.contains(s)
            }
        }
        if (iv != null) {
            iv.clear();
        } else {
            iv = new EnumMap<Statistic,Short>();
        }
        for (String m: moves.getKeys()) {
            if (!_dateBase.getMoves().contains(m)) {
                continue;
            }
            if (StringList.quickEq(m, _dateBase.getDefaultMove())) {
                continue;
            }
            existingMoves_.add(m);
        }
        moves.clear();
        //For deleting non existing moves
        if (existingMoves_.isEmpty()) {
            initAttaques(_dateBase,false);
        } else {
            int nb_ = 0;
            for (String m: existingMoves_) {
                if (nb_ == _dateBase.getNbMaxMoves()) {
                    break;
                }
                MoveData fAtt_ = _dateBase.getMove(m);
                short pp_=fAtt_.getPp();
                moves.put(m, new UsesOfMove(pp_));
                nb_++;
            }
            for(Statistic c:Statistic.getStatisticsWithBase()){
                iv.put(c, (short)_dateBase.getMaxIv());
            }
        }
        for (Statistic s: ev.getKeys()) {
            ev.put(s, (short) Math.min(ev.getVal(s), _dateBase.getMaxEv()));
        }
        initPvRestants(_dateBase);
        if (status != null) {
            status.clear();
        } else {
            status = new StringList();
        }
        trading = false;
    }

    public short evGagnes(short _increment, Statistic _stat,short _maxEv){
        short valeur_= ev.getVal(_stat);
        if(valeur_+_increment<_maxEv){
            return _increment;
        }
        return (short) (_maxEv-valeur_);
    }

    public void gainEv(String _objet,short _var,DataBase _import){
        if (_var == 0) {
            return;
        }
        Boost boost_=(Boost) _import.getItem(_objet);
        for (Statistic s: boost_.getEvs().getKeys()) {
            ev.put(s, (short)(ev.getVal(s)+_var));
        }
    }

    public void deplacement(DataBase _import){
        short pasNecesIncrementBonheur_ = _import.getNbNecStepsIncrHappiness();
        nbStepsTeamLead++;
        Comment comment_ = new Comment();
        while (nbStepsTeamLead >= pasNecesIncrementBonheur_) {
            variationBonheur(pointBonheurGagnesSansObjet(_import), _import);
            comment_.addComment(commentPk);
            nbStepsTeamLead -= pasNecesIncrementBonheur_;
        }
        commentPk = comment_;
    }

    public short pointBonheurGagnesSansObjet(DataBase _import){
        Rate mult_=DataBase.defRateProduct();
        if(!getItem().isEmpty()){
            Item objet_=_import.getItem(getItem());
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(!objetAttachable_.getMultWinningHappiness().isZero()){
                    mult_.multiplyBy(objetAttachable_.getMultWinningHappiness());
                }
            }
        }
        if(happiness+mult_.ll()<=_import.getHappinessMax()){
            return (short) mult_.ll();
        }
        return (short) (_import.getHappinessMax()-happiness);
    }

    public void variationBonheur(short _var, DataBase _data){
        clearComment();
        happiness+=_var;
        String name_ = _data.translatePokemon(getName());
        StringMap<String> mess_ = _data.getMessagesPokemonPlayer();
        commentPk.addMessage(mess_.getVal(HAPPINESS), name_, Long.toString(_var));
    }

    public void variationPvRestants(Rate _pv){
        remainingHp.addNb(_pv);
    }

    public boolean isKo(){
        return remainingHp.isZero();
    }

    public StringList moveTutors(DataBase _import){
        PokemonData fPk_ = _import.getPokemon(getName());
        StringList mt_=new StringList(fPk_.getMoveTutors());
        for (LevelMove p:fPk_.getLevMoves()) {
            if (p.getLevel() > getLevel()) {
                continue;
            }
            mt_.add(p.getMove());
        }
        mt_.removeDuplicates();
        StringList.removeAllElements(mt_, moves.getKeys());
        mt_.sort();
        return mt_;
    }

    public boolean learntMove(String _nomAttaque){
        return moves.contains(_nomAttaque);
    }

    public void learnMovesAfterForgettingAll(StringList _moves, DataBase _import) {
        moves.clear();
        for (String m: _moves) {
            learnMove(m, _import);
        }
    }

    public void learnMove(String _nvAtt,DataBase _import){
        short pp_=_import.getMove(_nvAtt).getPp();
        moves.put(_nvAtt, new UsesOfMove(pp_));
    }

    public void learnMove(String _nvAtt,String _ancAtt,DataBase _import){
        short pp_=_import.getMove(_nvAtt).getPp();
        if(moves.size()>=_import.getNbMaxMoves()){
            moves.removeKey(_ancAtt);
        }
        moves.put(_nvAtt, new UsesOfMove(pp_));
    }

    public static StringMap<Boolean> getMovesForEvolution(short _level, StringList _currentMoves, String _evolution, DataBase _import) {
        StringMap<Boolean> moves_;
        moves_ = new StringMap<Boolean>();
        PokemonData pk_ = _import.getPokemon(_evolution);
        for (String m: pk_.getMovesBeforeLevel(_level)) {
            moves_.put(m, false);
        }
        for (String m: _currentMoves) {
            moves_.put(m, true);
        }
        if (moves_.size() <= _import.getNbMaxMoves()) {
            for (String m: moves_.getKeys()) {
                moves_.put(m, true);
            }
        }
        return moves_;
    }

    public StringList directEvolutionsByStone(String _name, DataBase _import) {
        StringList evos_ = new StringList();
        evos_.add(_name);
        for (String i: _import.getItems().getKeys()) {
            Item it_ = _import.getItem(i);
            if (!(it_ instanceof EvolvingStone)) {
                continue;
            }
            evos_.addAllElts(possibleEvolutions(_name, getGender(), i, _import));
        }
        return evos_;
    }

    public StringList possibleEvolutions(String _pierreEvo,DataBase _import){
        return possibleEvolutions(getName(), getGender(), _pierreEvo, _import);
    }

    static StringList possibleEvolutions(String _name, Gender _gender, String _pierreEvo,DataBase _import){
        PokemonData fPk_=_import.getPokemon(_name);
        for(EntryCust<String,Evolution> e: fPk_.getEvolutions().entryList()){
            Evolution evol_=e.getValue();
            if (!(evol_ instanceof EvolutionStone)) {
                continue;
            }
            EvolutionStone evolPierre_=(EvolutionStone)evol_;
            if(!StringList.quickEq(evolPierre_.getStone(),_pierreEvo)){
                continue;
            }
            if(!(evol_ instanceof EvolutionStoneGender)){
                return new StringList(e.getKey());
            }
            EvolutionStoneGender evolPierreGenre_=(EvolutionStoneGender)evol_;
            if(evolPierreGenre_.getGender() == _gender){
                return new StringList(e.getKey());
            }
            break;
        }
        return new StringList();
    }

    public void evolve(String _pierreEvo,DataBase _import) {
        StringList evos_ = possibleEvolutions(_pierreEvo, _import);
        if (evos_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
            return;
        }
        possibleEvolution = evos_.first();
        PokemonData fPkEvo_=_import.getPokemon(possibleEvolution);
        StringList attaquesApprendreEvos_ = new StringList();
        for(LevelMove l: fPkEvo_.getLevMoves()){
            if(moves.contains(l.getMove())){
                continue;
            }
            if(l.getLevel()>getLevel()){
                continue;
            }
            attaquesApprendreEvos_.add(l.getMove());
        }
        StringList capacites_=new StringList(fPkEvo_.getAbilities());
        if (moves.size() + attaquesApprendreEvos_.size() <= _import.getNbMaxMoves()) {
            for (String m: attaquesApprendreEvos_) {
                learnMove(m, _import);
            }
            if (capacites_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
                newAbilitiesToBeChosen.addAllElts(capacites_);
            } else {
                setAbility(capacites_.first());
                setName(possibleEvolution);
                fullHeal(_import);
                possibleEvolution = DataBase.EMPTY_STRING;
            }
        } else {
            for (String m: moves.getKeys()) {
                movesToBeKeptEvo.put(m, true);
            }
            attaquesApprendreEvos_.removeDuplicates();
            for (String m: attaquesApprendreEvos_) {
                movesToBeKeptEvo.put(m, false);
            }
            if (capacites_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
                capacites_.sort();
                newAbilitiesToBeChosen.addAllElts(capacites_);
            }
        }
    }

    public void obtainAbilityAfterEvolving(String _ability,DataBase _import) {
        setAbility(_ability);
        setName(possibleEvolution);
        fullHeal(_import);
        possibleEvolution = DataBase.EMPTY_STRING;
        newAbilitiesToBeChosen.clear();

    }

    public void learnMovesAfterEvolving(String _ability,DataBase _import) {
        learnMovesAfterEvolvingWithOneAbility(_import);
        if (movesToBeKeptEvo.isEmpty()) {
            setAbility(_ability);
            newAbilitiesToBeChosen.clear();
        }
    }

    public void learnMovesAfterEvolvingWithOneAbility(DataBase _import) {
//        CustList<String> keys_ = movesToBeKeptEvo.getKeys(true);
        StringList keys_ = new StringList();
        for (EntryCust<String, Boolean> e: movesToBeKeptEvo.entryList()) {
            if (e.getValue()) {
                keys_.add(e.getKey());
            }
        }
        if (keys_.size() < moves.size()) {
            return;
        }
        if (keys_.size() > _import.getNbMaxMoves()) {
            return;
        }
        moves.clear();
        for (String m: keys_) {
            learnMove(m, _import);
        }
        movesToBeKeptEvo.clear();
        PokemonData fPkEvo_=_import.getPokemon(possibleEvolution);
        setAbility(fPkEvo_.getAbilities().first());
        setName(possibleEvolution);
        fullHeal(_import);
        possibleEvolution = DataBase.EMPTY_STRING;
    }

    public void fullHeal(DataBase _import){
        soinTousStatuts();
        initPvRestants(_import);
        for(String c:moves.getKeys()){
            UsesOfMove pps_=moves.getVal(c);
            pps_.fullHeal();
        }
    }

    public void soinStatuts(StringList _statuts){
        StringList.removeAllElements(status, _statuts);
    }

    public void soinTousStatuts(){
        status.clear();
    }

    public Rate pvSoignesBaie(Berry _baie,DataBase _import){
        Rate lostHp_ = lostHp(_import);
        Rate pvMax_=pvMax(_import);
        if(!_baie.getHealHp().isZero()){
            if (Rate.greaterEq(_baie.getHealHp(), lostHp_)) {
                return lostHp_;
            }
            return _baie.getHealHp();
        }
        if(!_baie.getHealHpBySuperEffMove().isZero()){
            Rate maxRestoredHp_ = Rate.multiply(_baie.getHealHpBySuperEffMove(), pvMax_);
            if (Rate.greaterEq(maxRestoredHp_, lostHp_)) {
                return lostHp_;
            }
            return maxRestoredHp_;
        }
        Rate maxRestoredHp_ = Rate.multiply(_baie.getHealHpRate(), pvMax_);
        if (Rate.greaterEq(maxRestoredHp_, lostHp_)) {
            return lostHp_;
        }
        return maxRestoredHp_;
    }

    public Rate lostHp(DataBase _data) {
        return lostHp(pvMax(_data));
    }
    private Rate lostHp(Rate _maxHp) {
        return Rate.minus(_maxHp, remainingHp);
    }
    public Rate pvSoignesAvecStatut(HealingHpStatus _soin,DataBase _import){
        Rate pvMax_=pvMax(_import);
        Rate lostHp_ = lostHp(pvMax_);
        Rate maxRestoredHp_ = Rate.multiply(_soin.getHealedHpRate(), pvMax_);
        if (Rate.greaterEq(maxRestoredHp_, lostHp_)) {
            return lostHp_;
        }
        return maxRestoredHp_;
    }

    public Rate pvSoignesSansStatut(HealingHp _soin,DataBase _import){
        Rate lostHp_ = lostHp(_import);
        if (Rate.greaterEq(_soin.getHp(), lostHp_)) {
            return lostHp_;
        }
        return _soin.getHp();
    }

    public short pointBonheurGagnes(Item _objet,DataBase _import){
        Rate mult_=DataBase.defRateProduct();
        if(!getItem().isEmpty()){
            Item objet_=_import.getItem(getItem());
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(!objetAttachable_.getMultWinningHappiness().isZero()){
                    mult_.multiplyBy(objetAttachable_.getMultWinningHappiness());
                }
            }
        }
        if(_objet instanceof HealingHp){
            HealingHp soin_=(HealingHp)_objet;
            if(soin_.getHappiness().contains(usedBallCatching)){
                mult_.multiplyBy(new Rate(soin_.getHappiness().getVal(usedBallCatching)));
            }
        }
        if(_objet instanceof Boost){
            Boost boost_=(Boost)_objet;
            if(boost_.getHappiness().contains(usedBallCatching)){
                mult_.multiplyBy(new Rate(boost_.getHappiness().getVal(usedBallCatching)));
            }
        }
        if(happiness+mult_.ll()<=_import.getHappinessMax()){
            return (short) mult_.ll();
        }
        return (short) (_import.getHappinessMax()-happiness);
    }


    public StringMap<Short> ppSoignesAttaques(HealingPp _soin){
        StringMap<Short> soinAttaques_ = new StringMap<Short>();
        if(_soin.isHealingAllMovesPp()){
            for(String c:moves.getKeys()){
                UsesOfMove pp_=moves.getVal(c);
                soinAttaques_.put(c,pp_.getLostPp());
            }
            return soinAttaques_;
        }
        long soinPpAttaques_=_soin.getHealingAllMovesFullpp();
        for(String c:moves.getKeys()){
            UsesOfMove pp_=moves.getVal(c);
            if(pp_.getCurrent()+soinPpAttaques_>=pp_.getMax()){
                soinAttaques_.put(c,pp_.getLostPp());
            }else{
                soinAttaques_.put(c,(short)soinPpAttaques_);
            }
        }
        return soinAttaques_;
    }

    public short ppSoignesAttaqueBaie(Berry _baie,String _attaque){
        UsesOfMove pp_=moves.getVal(_attaque);
        if(pp_.getCurrent()+_baie.getHealPp()>=pp_.getMax()){
            return pp_.getLostPp();
        }
        return (short) _baie.getHealPp();
    }

    public short ppSoignesAttaque(HealingPp _soin,String _attaque){
        UsesOfMove pp_=moves.getVal(_attaque);
        if(_soin.getHealingMoveFullpp()){
            return pp_.getLostPp();
        }
        if(pp_.getCurrent()+_soin.getHealedMovePp()>=pp_.getMax()){
            return pp_.getLostPp();
        }
        return (short) _soin.getHealedMovePp();
    }

    public void soinPpAttaques(StringMap<Short> _soinPp){
        for(String c:_soinPp.getKeys()){
            moves.getVal(c).heal(_soinPp.getVal(c));
        }
    }

    public short wonPp(Boost _boost,String _move, short _max) {
        short pp_=moves.getVal(_move).getMax();
        short delta_ = (short) _boost.getWinPp().ll();
        if(pp_+delta_<_max){
            return delta_;
        }
        return (short) (_max - pp_);
    }

    public void boostPp(String _move,short _delta) {
        moves.getVal(_move).boost(_delta);
    }

    public void endFight(Fighter _pokemon,StringMap<UsesOfMove> _moves, DataBase _import){
        setName(_pokemon.getName());
        setLevel(_pokemon.getLevel());
        setAbility(_pokemon.getAbility());
        happiness = _pokemon.getHappiness();
        ev.putAllMap(_pokemon.getEv());
        remainingHp.affect(_pokemon.getRemainingHp());
        status.clear();
        for(String c:_pokemon.getStatusSet()){
            if (Numbers.eq(_pokemon.getStatusNbRoundShort(c), 0)) {
                continue;
            }
            if (_import.getStatus(c).getDisabledEffIfSwitch()) {
                continue;
            }
            status.add(c);
        }
        moves.clear();
        for (EntryCust<String, UsesOfMove> s: _moves.entryList()) {
            UsesOfMove use_ = s.getValue();
            moves.put(s.getKey(), new UsesOfMove(use_.getCurrent(),use_.getMax()));
        }
        wonExpSinceLastLevel.affect(_pokemon.getWonExpSinceLastLevel());
    }

    public LgInt rateRemainHp(DataBase _import) {
        Rate r_ = Rate.divide(remainingHp, pvMax(_import));
        return r_.percent();
    }

    public Rate pvMax(DataBase _import){
        PokemonData fPk_=_import.getPokemon(getName());
        return fPk_.statHp(getLevel(),ev,iv);
    }

    public void setRemainedHp(Rate _hp) {
        remainingHp.affect(_hp);
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    public void setRemainingHp(Rate _remainingHp) {
        remainingHp = _remainingHp;
    }

    public StringList getStatus() {
        return status;
    }

    public void setStatus(StringList _status) {
        status = _status;
    }

    public EnumMap<Statistic,Short> getIv() {
        return iv;
    }

    public void setIv(EnumMap<Statistic,Short> _iv) {
        iv = _iv;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public StringMap<UsesOfMove> getMoves() {
        return moves;
    }

    public void setMoves(StringMap<UsesOfMove> _moves) {
        moves = _moves;
    }

    public EnumMap<Statistic,Short> getEv() {
        return ev;
    }

    public void setEv(EnumMap<Statistic,Short> _ev) {
        ev = _ev;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public void setWonExpSinceLastLevel(Rate _wonExpSinceLastLevel) {
        wonExpSinceLastLevel = _wonExpSinceLastLevel;
    }

    public short getHappiness() {
        return happiness;
    }

    public void setHappiness(short _happiness) {
        happiness = _happiness;
    }

    public String getUsedBallCatching() {
        return usedBallCatching;
    }

    public void setUsedBallCatching(String _usedBallCatching) {
        usedBallCatching = _usedBallCatching;
    }

    public short getNbStepsTeamLead() {
        return nbStepsTeamLead;
    }

    public void setNbStepsTeamLead(short _nbStepsTeamLead) {
        nbStepsTeamLead = _nbStepsTeamLead;
    }

    public StringMap<Boolean> getMovesToBeKeptEvo() {
        return movesToBeKeptEvo;
    }

    public String getPossibleEvolution() {
        return possibleEvolution;
    }

    public StringList getNewAbilitiesToBeChosen() {
        return newAbilitiesToBeChosen;
    }

    public void clearComment() {
        commentPk.clearMessages();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public short getLevel() {
        return level;
    }

    @Override
    public void setLevel(short _level) {
        level = _level;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender _gender) {
        gender = _gender;
    }

    @Override
    public String getAbility() {
        return ability;
    }

    @Override
    public void setAbility(String _ability) {
        ability = _ability;
    }

    @Override
    public String getItem() {
        return item;
    }

    @Override
    public void setItem(String _item) {
        item = _item;
    }

    public Comment getCommentPk() {
        return commentPk;
    }
}
