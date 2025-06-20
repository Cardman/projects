package aiki.map.pokemon;
import aiki.comments.Comment;
import aiki.db.*;
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
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class PokemonPlayer extends Pokemon implements UsablePokemon {

    public static final String SEPARATOR = "/";

//    private static final String CENT = Byte.toString(Fighter.RATE_CENT);
    public static final String CST_HAPPINESS = "0";
    public static final String DECREASING_HP = "1";


    /***/
    private Rate remainingHp;

    /***/
    private StringList status;

    /***/
    private IdMap<Statistic,Long> iv;

    /**nickname du pokemon par defaut le nom du pokemon*/
    private String nickname;

    /***/
    private StringMap<UsesOfMove> moves;

    /***/
    private IdMap<Statistic,Long> ev;

    /**Points d'experience gagnes depuis la derniere montee de niveau*/
    private Rate wonExpSinceLastLevel;

    /**happiness du pokemon (0 a 255)*/
    private long happiness;

    /**nom de la ball ayant capture le pokemon. Si ce nom est vide, alors le pokemon vient d'une eclosion ou d'un don.*/
    private String usedBallCatching;

    /**Nombre de pas effectue en tete d'equipe.*/
    private long nbStepsTeamLead;

    private Comment commentPk = new Comment();

    /***/
    private StringList newAbilitiesToBeChosen = new StringList();

    /***/
    private StringMap<BoolVal> movesToBeKeptEvo = new StringMap<BoolVal>();

    /***/
    private String possibleEvolution = DataBase.EMPTY_STRING;

    /***/
    private boolean trading;

    public PokemonPlayer() {
        setName(DataBase.EMPTY_STRING);
        setLevel(1);
        setGender(Gender.NO_GENDER);
        setAbility(DataBase.EMPTY_STRING);
        setItem(DataBase.EMPTY_STRING);
        remainingHp = Rate.zero();
        status = new StringList();
        nickname = DataBase.EMPTY_STRING;
        moves = new StringMap<UsesOfMove>();
        ev = new IdMap<Statistic,Long>();
        wonExpSinceLastLevel = Rate.zero();
        usedBallCatching = DataBase.EMPTY_STRING;
    }

    public PokemonPlayer(Pokemon _pokemonDonne, DataBase _import, Sex _sex, Difficulty _diff) {
        this(_pokemonDonne,_import, _sex.getGender(), _diff);
    }
    public PokemonPlayer(Pokemon _pokemonDonne, DataBase _import, Gender _sex, Difficulty _diff) {
        this(_pokemonDonne,_import, _diff);
        setGender(_sex);
        initIv(_diff);
        initEv();
        initPvRestants(_import);
    }
    public PokemonPlayer(Pokemon _pokemonDonne, DataBase _import, Difficulty _diff) {
        this(_pokemonDonne, _import, getMovesAtLevel(_pokemonDonne.getName(),_pokemonDonne.getLevel(),_import), _diff);
    }

    public PokemonPlayer(Pokemon _pokemonDonne, DataBase _import, StringMap<Long> _moves, Difficulty _diff) {
        this(_pokemonDonne,_import,_moves,_diff.getIvPlayer());
        initIv(_diff);
        initPvRestants(_import);
    }
    public PokemonPlayer(Pokemon _pokemonDonne,DataBase _import, StringMap<Long> _moves, long _maxIv) {
//        super(_pokemonDonne);
        setName(_pokemonDonne.getName());
        setLevel(_pokemonDonne.getLevel());
        setGender(_pokemonDonne.getGender());
        setAbility(_pokemonDonne.getAbility());
        setItem(_pokemonDonne.getItem());
        initMoves(_moves);
        initEvIv(true,_maxIv);
        status = new StringList();
        nickname=getName();

        usedBallCatching= _import.getBallDef();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappiness();
        obtention();
    }
    public PokemonPlayer(Fossil _fossile, DataBase _import, Difficulty _diff){
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
        initAttaques(_import,true, _diff.getIvPlayer());
        initAleaCapaciteGenre(_import);

        usedBallCatching= _import.getBallDef();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappiness();
        obtention();
        initIv(_diff);
        initPvRestants(_import);
    }

    public PokemonPlayer(Egg _oeuf, DataBase _import, Difficulty _diff){
//        name = DataBase.EMPTY_STRING;
//        level = 1;
//        gender = Gender.NO_GENDER;
//        ability = DataBase.EMPTY_STRING;
//        item = DataBase.EMPTY_STRING;
        status = new StringList();
        setName(_oeuf.getName());
        setLevel(_import.getMinLevel());
        setItem(DataBase.EMPTY_STRING);
        nickname=getName();
        initAttaques(_import,true, _diff.getIvPlayer());
        initAleaCapaciteGenre(_import);

        usedBallCatching= _import.getBallDef();
        PokemonData fPk_ = _import.getPokemon(getName());
        happiness=fPk_.getHappinessHatch();
        obtention();
        initIv(_diff);
        initPvRestants(_import);
    }

    public PokemonPlayer(Fighter _pokemonSauvage, String _pseudo, String _ballCapture, DataBase _import, Difficulty _diff){
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
            if(!NumberUtil.eq(_pokemonSauvage.getStatusNbRoundShort(c), 0)){
                status.add(c);
            }
        }
        remainingHp = Rate.zero();
        remainingHp.affect(_pokemonSauvage.getRemainingHp());
        initAttaques(_import,true, _diff.getIvPlayer());
        obtention();
        initIv(_diff);
    }

    public static void deplacement(PokemonPlayer _pk, int _nb, DataBase _data) {
        for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
            _pk.deplacement(_data);
        }
    }

    void initAleaCapaciteGenre(DataBase _import){
        PokemonData fPk_=_import.getPokemon(getName());
        GenderRepartition repartitionGenre_=fPk_.getGenderRep();
        MonteCarloEnum<Gender> loiGenre_ = new MonteCarloEnum<Gender>();
        for(Gender g:repartitionGenre_.getPossibleGenders()){
            loiGenre_.addQuickEvent(g,DataBase.defElementaryEvent());
        }
        setGender(new PkMonteCarlo<Gender>(_import,loiGenre_).editNumber());
        MonteCarloString loiCapac_ = new MonteCarloString();
        for(String e:fPk_.getAbilities()){
            loiCapac_.addQuickEvent(e,DataBase.defElementaryEvent());
        }
        setAbility(new PkMonteCarlo<String>(_import,loiCapac_).editNumber());
    }

    void initAttaques(DataBase _import, boolean _initEv, long _iv){
        initMoves(getMovesAtLevel(getName(), getLevel(), _import));
        initEvIv(_initEv, _iv);
    }

    void initMoves(StringMap<Long> _moves) {
        if (!trading) {
            moves = new StringMap<UsesOfMove>();
        }
        for (String m: _moves.getKeys()) {
            long pp_ = _moves.getVal(m);
            moves.put(m, new UsesOfMove(pp_));
        }
    }

    void initEvIv(boolean _initEv, long _maxIv) {
        if (!trading) {
            iv = new IdMap<Statistic,Long>();
            ev = new IdMap<Statistic,Long>();
        }
        for(Statistic c:Statistic.getStatisticsWithBase()){
            if (_initEv) {
                ev.put(c, 0L);
            }
            iv.put(c, _maxIv);
        }
    }
    void initEv() {
        for(Statistic c:Statistic.getStatisticsWithBase()){
            ev.put(c, 0L);
        }
    }
    static StringMap<Long> getMovesAtLevel(String _name, long _level, DataBase _import) {
        StringMap<Long> moves_ = new StringMap<Long>();
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

    public StringMap<Long> getAllEvolutions(DataBase _import) {
        return getAllEvolutions(getName(), getLevel(), false, _import);
    }

    public static StringMap<Long> getAllEvolutions(String _base, long _level, boolean _sep, DataBase _import) {
        if (_sep) {
            return getAllEvolutionsSep(_base, _level, _import);
        } else {
            return getAllEvolutionsNoSep(_base, _level, _import);
        }
    }

    private static StringMap<Long> getAllEvolutionsNoSep(String _base, long _level, DataBase _import) {
        StringMap<Long> evolutionsLevels_ = new StringMap<Long>();
        StringMap<Long> currentEvolutions_ = new StringMap<Long>();
        currentEvolutions_.put(_base, IndexConstants.FIRST_INDEX_LG);
        StringMap<Long> newEvolutions_;
        while (true) {
            newEvolutions_ = new StringMap<Long>();
            for (String e: currentEvolutions_.getKeys()) {
                PokemonData fPk_ = _import.getPokemon(e);
                for (String e2_: fPk_.getEvolutions().getKeys()) {
                    Evolution evo_ = fPk_.getEvolution(e2_);
                    long max_ = NumberUtil.max(currentEvolutions_.getVal(e), _level);
                    if (evo_ instanceof EvolutionLevel) {
                        max_= NumberUtil.max(max_, ((EvolutionLevel) evo_).getLevel());
                    }
                    newEvolutions_.put(e2_, max_);
                }
            }
            if (newEvolutions_.isEmpty()) {
                break;
            }
            evolutionsLevels_.putAllMap(newEvolutions_);
            currentEvolutions_ = new StringMap<Long>(newEvolutions_);
        }
        return evolutionsLevels_;
    }

    private static StringMap<Long> getAllEvolutionsSep(String _base, long _level, DataBase _import) {
        StringMap<Long> evolutionsLevels_ = new StringMap<Long>();
        StringMap<Long> currentEvolutions_ = new StringMap<Long>();
        currentEvolutions_.put(_base, IndexConstants.FIRST_INDEX_LG);
        StringMap<Long> newEvolutions_;
        while (true) {
            newEvolutions_ = new StringMap<Long>();
            for (String e: currentEvolutions_.getKeys()) {
                PokemonData fPk_ = _import.getPokemon(StringUtil.splitStrings(e, SEPARATOR).last());
                for (String e2_: fPk_.getEvolutions().getKeys()) {
                    Evolution evo_ = fPk_.getEvolution(e2_);
                    long max_ = NumberUtil.max(currentEvolutions_.getVal(e), _level);
                    if (evo_ instanceof EvolutionLevel) {
                        max_= NumberUtil.max(max_, ((EvolutionLevel) evo_).getLevel());
                    }
                    newEvolutions_.put(StringUtil.concat(e,SEPARATOR,e2_), max_);
                }
            }
            if (newEvolutions_.isEmpty()) {
                break;
            }
            evolutionsLevels_.putAllMap(newEvolutions_);
            currentEvolutions_ = new StringMap<Long>(newEvolutions_);
        }
        return evolutionsLevels_;
    }

    public void initPvRestants(DataBase _import){
        remainingHp=pvMax(_import);
    }

    public void initIv(Difficulty _diff){
        commentPk = new Comment();
        newAbilitiesToBeChosen = new StringList();
        movesToBeKeptEvo = new StringMap<BoolVal>();
        possibleEvolution = DataBase.EMPTY_STRING;
        trading = false;
        if (iv == null) {
            iv = new IdMap<Statistic,Long>();
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
        if (koValidate(_data)) {
            return false;
        }
        for (EntryCust<String, UsesOfMove> m: moves.entryList()) {
            if (StringUtil.quickEq(m.getKey(), _data.getDefMove()) || !_data.getMoves().contains(m.getKey()) || m.getValue().getCurrent() < 0 || m.getValue().getMax() < m.getValue().getCurrent() || m.getValue().getMax() > _data.getMaxPp() || m.getValue().getMax() == 0) {
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
            if (!_data.getStatus().contains(s) || _data.getStatus(s).getStatusType() == StatusType.RELATION_UNIQUE) {
                return false;
            }
        }
        return okValidate(_data);
    }

    private boolean koValidate(DataBase _data) {
        return hasJustBeenCreated() || getLevel() < _data.getMinLevel() || getLevel() > _data.getMaxLevel() || !_data.getPokedex().contains(getName()) || !_data.getAbilities().contains(getAbility()) || !getItem().isEmpty() && !_data.getItems().contains(getItem()) || happiness < 0 || happiness > _data.getHappinessMax() || moves.isEmpty() || moves.size() > _data.getNbMaxMoves();
    }

    private boolean okValidate(DataBase _data) {
        return nbStepsTeamLead >= 0 && nbStepsTeamLead < _data.getNbNecStepsIncrHappiness() && remainingHp.isZeroOrGt() && !Rate.strGreater(remainingHp, _data.getPokemon(getName()).statHp(getLevel(), ev, iv)) && _data.getItems().contains(usedBallCatching) && _data.getItem(usedBallCatching) instanceof Ball && wonExpSinceLastLevel.isZeroOrGt();
    }

    public void initilializeFromExchange(DataBase _dateBase){
        trading = true;
        initMoves();
        initEvWithBase();
        initIv();
        StringList existingMoves_ = new StringList();
        for (String m: moves.getKeys()) {
            if (!_dateBase.getMoves().contains(m) || StringUtil.quickEq(m, _dateBase.getDefMove())) {
                continue;
            }
            existingMoves_.add(m);
        }
        moves.clear();
        //For deleting non existing moves
        if (existingMoves_.isEmpty()) {
            initAttaques(_dateBase,false, _dateBase.getMaxIv());
        } else {
            int nb_ = 0;
            for (String m: existingMoves_) {
                if (nb_ == _dateBase.getNbMaxMoves()) {
                    break;
                }
                MoveData fAtt_ = _dateBase.getMove(m);
                long pp_=fAtt_.getPp();
                moves.put(m, new UsesOfMove(pp_));
                nb_++;
            }
            for(Statistic c:Statistic.getStatisticsWithBase()){
                iv.put(c, _dateBase.getMaxIv());
            }
        }
        for (Statistic s: ev.getKeys()) {
            ev.put(s, NumberUtil.min(ev.getVal(s), _dateBase.getMaxEv()));
        }
        initPvRestants(_dateBase);
        initStatus();
        trading = false;
    }

    private void initStatus() {
        if (status != null) {
            status.clear();
        } else {
            status = new StringList();
        }
    }

    private void initIv() {
        if (iv != null) {
            iv.clear();
        } else {
            iv = new IdMap<Statistic,Long>();
        }
    }

    private void initEvWithBase() {
        if (ev == null) {
            ev = new IdMap<Statistic,Long>();
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                ev.put(s, 0L);
            }
        } else {
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                if (!ev.contains(s)) {
                    ev.put(s, 0L);
                }
                //Here: ev.contains(s)
            }
        }
    }

    private void initMoves() {
        if (moves == null) {
            moves = new StringMap<UsesOfMove>();
        }
    }

    public long evGagnes(long _increment, Statistic _stat,long _maxEv){
        long valeur_= ev.getVal(_stat);
        if(valeur_+_increment<_maxEv){
            return _increment;
        }
        return _maxEv-valeur_;
    }

    public void gainEv(String _objet,long _var,DataBase _import){
        if (_var == 0) {
            return;
        }
        Boost boost_=(Boost) _import.getItem(_objet);
        for (Statistic s: boost_.getEvs().getKeys()) {
            ev.put(s, ev.getVal(s)+_var);
        }
    }

    public void deplacement(DataBase _import){
        long pasNecesIncrementBonheur_ = _import.getNbNecStepsIncrHappiness();
        nbStepsTeamLead++;
        Comment comment_ = new Comment();
        while (nbStepsTeamLead >= pasNecesIncrementBonheur_) {
            variationBonheur(pointBonheurGagnesSansObjet(_import), _import);
            comment_.addComment(commentPk);
            nbStepsTeamLead -= pasNecesIncrementBonheur_;
        }
        commentPk = comment_;
    }

    public long pointBonheurGagnesSansObjet(DataBase _import){
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
            return mult_.ll();
        }
        return _import.getHappinessMax()-happiness;
    }

    public void variationBonheur(long _var, DataBase _data){
        clearComment();
        happiness+=_var;
        String name_ = _data.translatePokemon(getName());
        StringMap<String> mess_ = _data.getMessagesPokemonPlayer();
        commentPk.addMessage(mess_.getVal(CST_HAPPINESS), name_, Long.toString(_var));
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
        StringUtil.removeAllElements(mt_, moves.getKeys());
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
        long pp_=_import.getMove(_nvAtt).getPp();
        moves.put(_nvAtt, new UsesOfMove(pp_));
    }

    public void learnMove(String _nvAtt,String _ancAtt,DataBase _import){
        long pp_=_import.getMove(_nvAtt).getPp();
        if(moves.size()>=_import.getNbMaxMoves()){
            moves.removeKey(_ancAtt);
        }
        moves.put(_nvAtt, new UsesOfMove(pp_));
    }

    public static StringMap<BoolVal> getMovesForEvolution(long _level, StringList _currentMoves, String _evolution, DataBase _import) {
        StringMap<BoolVal> moves_;
        moves_ = new StringMap<BoolVal>();
        PokemonData pk_ = _import.getPokemon(_evolution);
        for (String m: pk_.getMovesBeforeLevel(_level)) {
            moves_.put(m, BoolVal.FALSE);
        }
        for (String m: _currentMoves) {
            moves_.put(m, BoolVal.TRUE);
        }
        if (moves_.size() <= _import.getNbMaxMoves()) {
            for (String m: moves_.getKeys()) {
                moves_.put(m, BoolVal.TRUE);
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
            if (!(evol_ instanceof EvolutionStone) || !StringUtil.quickEq(((EvolutionStone) evol_).getStone(), _pierreEvo)) {
                continue;
            }
            if(!(evol_ instanceof EvolutionStoneGender)){
                return new StringList(e.getKey());
            }
            EvolutionStoneGender evolPierreGenre_=(EvolutionStoneGender)evol_;
            if(evolPierreGenre_.getGender() == _gender){
                return new StringList(e.getKey());
            }
            return new StringList();
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
        StringList attaquesApprendreEvos_ = attaquesApprendreEvos(fPkEvo_);
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
                movesToBeKeptEvo.put(m, BoolVal.TRUE);
            }
            attaquesApprendreEvos_.removeDuplicates();
            for (String m: attaquesApprendreEvos_) {
                movesToBeKeptEvo.put(m, BoolVal.FALSE);
            }
            if (capacites_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
                capacites_.sort();
                newAbilitiesToBeChosen.addAllElts(capacites_);
            }
        }
    }

    private StringList attaquesApprendreEvos(PokemonData _fPkEvo) {
        StringList attaquesApprendreEvos_ = new StringList();
        for(LevelMove l: _fPkEvo.getLevMoves()){
            if (moves.contains(l.getMove()) || l.getLevel() > getLevel()) {
                continue;
            }
            attaquesApprendreEvos_.add(l.getMove());
        }
        return attaquesApprendreEvos_;
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
//        StringList keys_ = new StringList();
//        for (EntryCust<String, BoolVal> e: movesToBeKeptEvo.entryList()) {
//            if (e.getValue() == BoolVal.TRUE) {
//                keys_.add(e.getKey());
//            }
//        }
//        if (keys_.size() < moves.size()) {
//            return;
//        }
        StringList keys_ = keys(_import);
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
    public StringList keys(DataBase _import) {
        return keys(_import, movesToBeKeptEvo, moves);
    }

    public static StringList keys(DataBase _import, StringMap<BoolVal> _movesToBeKeptEvo, StringMap<UsesOfMove> _moves) {
        StringList keys_ = new StringList();
        for (EntryCust<String, BoolVal> e: _movesToBeKeptEvo.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                keys_.add(e.getKey());
            }
        }
        if (keys_.size() < _moves.size()) {
            long nb_ = _import.getNbMaxMoves() + 1;
            for (int i = 0; i < nb_; i++) {
                keys_.add(DataBase.EMPTY_STRING);
            }
            return keys_;
        }
        return keys_;
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
        StringUtil.removeAllElements(status, _statuts);
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

    public long pointBonheurGagnes(Item _objet,DataBase _import){
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
            return mult_.ll();
        }
        return _import.getHappinessMax()-happiness;
    }


    public StringMap<Long> ppSoignesAttaques(HealingPp _soin){
        StringMap<Long> soinAttaques_ = new StringMap<Long>();
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
                soinAttaques_.put(c,soinPpAttaques_);
            }
        }
        return soinAttaques_;
    }

    public long ppSoignesAttaqueBaie(Berry _baie,String _attaque){
        UsesOfMove pp_=moves.getVal(_attaque);
        if(pp_.getCurrent()+_baie.getHealPp()>=pp_.getMax()){
            return pp_.getLostPp();
        }
        return _baie.getHealPp();
    }

    public long ppSoignesAttaque(HealingPp _soin,String _attaque){
        UsesOfMove pp_=moves.getVal(_attaque);
        if(_soin.getHealingMoveFullpp()){
            return pp_.getLostPp();
        }
        if(pp_.getCurrent()+_soin.getHealedMovePp()>=pp_.getMax()){
            return pp_.getLostPp();
        }
        return _soin.getHealedMovePp();
    }

    public void soinPpAttaques(StringMap<Long> _soinPp){
        for(String c:_soinPp.getKeys()){
            moves.getVal(c).heal(_soinPp.getVal(c));
        }
    }

    public long wonPp(Boost _boost,String _move, long _max) {
        long pp_=moves.getVal(_move).getMax();
        long delta_ = _boost.getWinPp().ll();
        if(pp_+delta_<_max){
            return delta_;
        }
        return _max - pp_;
    }

    public void boostPp(String _move,long _delta) {
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
            if (NumberUtil.eq(_pokemon.getStatusNbRoundShort(c), 0) || _import.getStatus(c).getDisabledEffIfSwitch()) {
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

    public IdMap<Statistic,Long> getIv() {
        return iv;
    }

    public void setIv(IdMap<Statistic,Long> _iv) {
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

    public IdMap<Statistic,Long> getEv() {
        return ev;
    }

    public void setEv(IdMap<Statistic,Long> _ev) {
        ev = _ev;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public void setWonExpSinceLastLevel(Rate _wonExpSinceLastLevel) {
        wonExpSinceLastLevel = _wonExpSinceLastLevel;
    }

    public long getHappiness() {
        return happiness;
    }

    public void setHappiness(long _happiness) {
        happiness = _happiness;
    }

    public String getUsedBallCatching() {
        return usedBallCatching;
    }

    public void setUsedBallCatching(String _usedBallCatching) {
        usedBallCatching = _usedBallCatching;
    }

    public long getNbStepsTeamLead() {
        return nbStepsTeamLead;
    }

    public void setNbStepsTeamLead(long _nbStepsTeamLead) {
        nbStepsTeamLead = _nbStepsTeamLead;
    }

    public StringMap<BoolVal> getMovesToBeKeptEvo() {
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

    public Comment getCommentPk() {
        return commentPk;
    }
}
