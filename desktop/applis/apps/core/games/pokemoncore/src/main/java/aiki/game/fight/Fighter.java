package aiki.game.fight;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionLevel;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.pokemon.evolution.EvolutionTeam;
import aiki.fight.status.Status;
import aiki.fight.status.StatusType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.TypesDuo;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSimpleHeal;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.actions.ChosenMove;
import aiki.game.fight.actions.ChosenReplacing;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.LevelExpPoints;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class Fighter {

//    public static final byte RATE_CENT = 100;

    public static final int BACK = -1;

    public static final String FULL_HEAL = "0";
    public static final String GROW_LEVEL = "1";
    public static final String LEARN_MOVE = "2";
    public static final String MAX_HAPPINESS = "3";
    public static final String WON_EV = "4";
    public static final String WON_EV_MAX = "5";
    public static final String WON_EXP = "6";
    public static final String WON_HAPPINESS = "7";

//    private static final String CENT = Integer.toString(RATE_CENT);

    /**Only evolutions can change the name*/
    private String name;

    /**Never mind*/
    private String nickname;

    /**Gender does not change in a fight*/
    private Gender gender;

    /**Never mind*/
    private Rate weight;

    /**Never mind*/
    private Rate height;

    /**Only evolutions and moves copy from fighter can change the current name; Never mind*/
    private String currentName;

    /**Only moves copy from fighter can change the current gender; Never mind*/
    private Gender currentGender;

    /**Only effect switch items (moves or abilities) can change the last used item*/
    private String lastUsedItem;

    /**Only effect switch items (moves or abilities) can change the item*/
    private String item;

    /**expItem does not change in a fight*/
    private String expItem;

    /**Only evolutions can change the ability*/
    private String ability;

    /**Only evolutions; moves copy from fighter and effect switch abilities can change the current ability; Never mind*/
    private String currentAbility;

    /**The key set is not changed*/
    private StringMap<Long> status;

    /**The key set is not changed*/
    private MoveTeamPositionsShort statusRelat;

    /**Never mind*/
    private LgInt nbRounds;

    /**Never mind even if empty*/
    private StringList types;

    /**Only learning moves by experience; only effect change moves can change the key set*/
    private StringMap<UsesOfMove> moves;

    /**Only learning moves by experience; moves copy from fighter; only effect change moves can change the key set*/
    private StringMap<UsesOfMove> currentMoves;

    /**Never mind*/
    private IdMap<Statistic,Long> ev;

    /**Never mind*/
    private IdMap<Statistic,Long> iv = new IdMap<Statistic,Long>();

    /**Never mind*/
    private IdMap<Statistic,Rate> statisBase;

    /**Never mind*/
    private IdMap<Statistic,Long> statisBoost;

    /***/
    private Rate remainingHp;

    /**Never mind even if clone &gt; max hp*/
    private Rate clone;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledMoves;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledMovesProt;

    /**Never mind*/
    private StringList protectedAgainstMoveTypes;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledMovesUnprot;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledMovesEndRound;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledMovesConstChoices;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledChangingTypesMoves;

    /**Never mind*/
    private StringMap<ActivityOfMove> enabledCounteringMoves;

    /**Never mind*/
    private StringMap<BoolVal> enabledMovesForAlly;

    /**Never mind*/
    private StringMap<Rate> damageRateInflictedByType;

    /**Never mind*/
    private StringMap<Rate> damageRateSufferedByType;

    /**Changed after act (use a move; switch or heal)
    even if a switch interrupt the round*/
    private boolean acted;

    /***/
    private int groundPlace;

    /***/
    private int groundPlaceSubst;

    /**Never mind even if high (added to wonExpSinceLastLevel for grow level)*/
    private Rate wonExp;

    /**Never mind even if high*/
    private Rate wonExpSinceLastLevel;

    /**Used for learning moves - evolving (experience); numeric string values*/
    private long level;

    /**Never mind*/
    private long happiness;

    /**Never mind*/
    private String usedBallCatching;

    /**Never mind*/
    private MoveTeamPositionsBoolVal incrUserAccuracy;

    /**Never mind*/
    private StringMap<Long> nbUsesMoves;

    /***/
    private long nbPrepaRound;

    /**if disappeared then nbPrepaRound &gt; 0*/
    private boolean disappeared;

    /***/
    private boolean needingToRecharge;

    /**Never mind*/
    private MoveTeamPositionsAffectedMove trackingMoves;

    /**Never mind*/
    private MoveTeamPositionsActivityOfMove trappingMoves;

    /**Never mind*/
    private String lastSufferedMove;

    /**Never mind*/
    private StringList lastSufferedMoveTypes;

    /**Never mind*/
    private StringMap<Rate> damageSufferedCateg;

    /**Never mind*/
    private StringMap<Rate> damageSufferedCategRound;

    /**Never mind*/
    private String lastUsedMove;

    /**Never mind*/
    private String usedMoveLastRound;

    /**Never mind*/
    private StringList alreadyInvokedMovesRound;

    /**Never mind*/
    private String lastSuccessfulMove;

    /**The key set does not change; the move value change only if copy move from fighter
    This is combined with currentMoves*/
    private StringMap<CopiedMove> copiedMoves;

    /**Never mind*/
    private LgInt nbRepeatingSuccessfulMoves;

    /**Never mind*/
    private boolean usingItem;

    /**Never mind*/
    private boolean successfulMove;

    /**Never mind*/
    private boolean changed;

    /***/
    private StringMap<BoolVal> enabledImmuAbilities;

    /**Never mind*/
    private MoveTeamPositionsStringList privateMoves;

    /**Cannot be changed in a fight*/
    private boolean belongingToPlayer;

    /***/
    private AbstractAction action;

    /***/
    private StringList movesToBeLearnt;

    /***/
    private StringMap<MovesAbilities> movesAbilitiesEvos;

    public Fighter(){
    }

    public Fighter(PokemonPlayer _pokemon,DataBase _import,int _placeTerrain){
        initCreatureUser(_pokemon,_import);
        initUserMoves(_pokemon);
        initEvIvUser(_pokemon);
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    public Fighter(PkTrainer _pokemon,DataBase _import,int _placeTerrain){
        initCreatureNonUser(_pokemon,_import);
        initPokemonTrainerMoves(_pokemon,_import);
        initEvIvOther();
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    public Fighter(WildPk _pokemon,DataBase _import,int _placeTerrain){
        initCreatureNonUser(_pokemon,_import);
        initWildPokemonMoves(_import);
        initEvIvOther();
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    void initCreatureUser(PokemonPlayer _pokemon,DataBase _import){
        belongingToPlayer = true;
        initCreature(_pokemon);
        if (_import.usedObjectUsedForExp(item) != null) {
            expItem = item;
        } else {
            expItem = DataBase.EMPTY_STRING;
        }
        usedBallCatching=_pokemon.getUsedBallCatching();
        nickname=_pokemon.getNickname();
        wonExpSinceLastLevel = new Rate(_pokemon.getWonExpSinceLastLevel());
        happiness=_pokemon.getHappiness();
        for(String e:_import.getStatus().getKeys()){
            if (_import.getStatus(e).getStatusType() != StatusType.INDIVIDUEL) {
                continue;
            }
            if(StringUtil.contains(_pokemon.getStatus(), e)){
                status.put(e, 1L);
            }else{
                status.put(e, 0L);
            }
        }
    }
    void initCreatureNonUser(Pokemon _pokemon,DataBase _import){
        initCreature(_pokemon);
        expItem = DataBase.EMPTY_STRING;
        usedBallCatching = DataBase.EMPTY_STRING;
        moves = new StringMap<UsesOfMove>();
        currentMoves = new StringMap<UsesOfMove>();
        nickname=name;
        wonExpSinceLastLevel=Rate.zero();
        happiness= fichePokemon(_import).getHappiness();
        for(String e:_import.getStatus().getKeys()){
            if (_import.getStatus(e).getStatusType() != StatusType.INDIVIDUEL) {
                continue;
            }
            status.put(e, 0L);
        }
    }

    void initCreature(Pokemon _pokemon) {
        statusRelat = new MoveTeamPositionsShort();
        status = new StringMap<Long>();
        incrUserAccuracy = new MoveTeamPositionsBoolVal();
        trackingMoves = new MoveTeamPositionsAffectedMove();
        trappingMoves = new MoveTeamPositionsActivityOfMove();
        privateMoves = new MoveTeamPositionsStringList();
        name = _pokemon.getName();
        currentName = name;
        level=_pokemon.getLevel();
        gender = _pokemon.getGender();
        currentGender = gender;
        item=_pokemon.getItem();
        ability=_pokemon.getAbility();
        currentAbility=ability;
    }

    void initUserMoves(PokemonPlayer _pokemon) {
        movesToBeLearnt = new StringList();
        movesAbilitiesEvos = new StringMap<MovesAbilities>();
        moves = new StringMap<UsesOfMove>();
        currentMoves = new StringMap<UsesOfMove>();
        for (EntryCust<String,UsesOfMove> m: _pokemon.getMoves().entryList()) {
            UsesOfMove pp_ = m.getValue();
            moves.put(m.getKey(), new UsesOfMove(pp_.getCurrent(),pp_.getMax()));
            currentMoves.put(m.getKey(), new UsesOfMove(pp_.getCurrent(),pp_.getMax()));
        }
    }

    void initEvIvUser(PokemonPlayer _pokemon) {
        ev=new IdMap<Statistic,Long>();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            ev.put(s, _pokemon.getEv().getVal(s));
            iv.put(s, _pokemon.getIv().getVal(s));
        }
        remainingHp = new Rate(_pokemon.getRemainingHp());
    }

    void initPokemonTrainerMoves(PkTrainer _pokemon, DataBase _import) {
        movesToBeLearnt = new StringList();
        movesAbilitiesEvos = new StringMap<MovesAbilities>();
        for(String e:_pokemon.getMoves()){
            MoveData fAtt_=_import.getMove(e);
            long pp_=fAtt_.getPp();
            moves.put(e,new UsesOfMove(pp_));
            currentMoves.put(e,new UsesOfMove(pp_));
        }
    }

    void initWildPokemonMoves(DataBase _import) {
        movesToBeLearnt = new StringList();
        movesAbilitiesEvos = new StringMap<MovesAbilities>();
        PokemonData fPk_=fichePokemon(_import);
        for (String m: fPk_.getMovesAtLevel(level, _import.getNbMaxMoves())) {
            MoveData fAtt_=_import.getMove(m);
            long pp_=fAtt_.getPp();
            moves.put(m,new UsesOfMove(pp_));
            currentMoves.put(m,new UsesOfMove(pp_));
        }
    }

    void initEvIvOther() {
        ev = new IdMap<Statistic,Long>();
        for(Statistic c:Statistic.getStatisticsWithBase()){
            ev.put(c, 0L);
            iv.put(c, 0L);
        }
        remainingHp = Rate.zero();
    }

    void initCreatureGeneral(DataBase _import){
        PokemonData fPk_=fichePokemonActuelle(_import);
        types=new StringList(fPk_.getTypes());
        long def_ = _import.getDefaultBoost();
        statisBoost = new IdMap<Statistic,Long>();
        for(Statistic c:Statistic.getStatisticsWithBoost()){
            statisBoost.put(c, def_);
        }
        statisBase = new IdMap<Statistic,Rate>();
        for(Statistic c:Statistic.getStatisticsWithBase()){
            statisBase.put(c, new Rate(fPk_.getStatistics().getVal(c).getBase()));
        }
        acted=false;
//        acted=true;
//        acted=true;
        height = new Rate(fPk_.getHeight());
        weight = new Rate(fPk_.getWeight());
        lastUsedMove = DataBase.EMPTY_STRING;
        usedMoveLastRound = DataBase.EMPTY_STRING;
        lastSuccessfulMove = DataBase.EMPTY_STRING;
        successfulMove=false;
        nbRounds=LgInt.zero();
        clone=Rate.zero();
        nbPrepaRound=0;
        disappeared=false;
        needingToRecharge=false;
        action = null;
        damageSufferedCateg = new StringMap<Rate>();
        damageSufferedCategRound = new StringMap<Rate>();
        for(String e:_import.getCategories()){
            damageSufferedCateg.put(e, Rate.zero());
            damageSufferedCategRound.put(e, Rate.zero());
        }
        lastSufferedMove=DataBase.EMPTY_STRING;
        lastSufferedMoveTypes=new StringList();
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_import.getVarParamsMove(_import.cibleNbUtilisation()));
        attaques_.addAllElts(_import.getVarParamsMove(_import.lanceurNbUtilisation()));
        attaques_.removeDuplicates();
        nbUsesMoves = new StringMap<Long>();
        for(String e:attaques_){
            nbUsesMoves.put(e, 0L);
        }
        //(CIBLE|LANCEUR)_NB_UTILISATION
        wonExp = Rate.zero();
        copiedMoves = new StringMap<CopiedMove>();
        for(String e:_import.getMovesCopyingTemp()){
            copiedMoves.put(e, new CopiedMove(DataBase.EMPTY_STRING,0));
        }
        nbRepeatingSuccessfulMoves=LgInt.zero();
        usingItem=false;
        damageRateSufferedByType = new StringMap<Rate>();
        damageRateInflictedByType = new StringMap<Rate>();
        for(String e:_import.getTypes()){
            damageRateSufferedByType.put(e, DataBase.defRateProduct());
            damageRateInflictedByType.put(e, DataBase.defRateProduct());
        }
        changed=false;
        enabledMoves = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectIndiv()){
            enabledMoves.put(e, new ActivityOfMove(StringUtil.contains(_import.getMovesEffectIndivIncr(), e)));
        }
        protectedAgainstMoveTypes = new StringList();
        enabledMovesProt = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectProt()){
            enabledMovesProt.put(e, new ActivityOfMove(StringUtil.contains(_import.getMovesEffectIndivIncr(), e)));
        }
        enabledMovesUnprot = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectUnprot()){
            enabledMovesUnprot.put(e, new ActivityOfMove(StringUtil.contains(_import.getMovesEffectIndivIncr(), e)));
        }
        enabledMovesForAlly = new StringMap<BoolVal>();
        for(String e:_import.getMovesEffectAlly()){
            enabledMovesForAlly.put(e, BoolVal.FALSE);
        }
        enabledMovesEndRound = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffEndRoundIndiv()){
            enabledMovesEndRound.put(e, new ActivityOfMove(StringUtil.contains(_import.getMovesEffEndRoundIndivIncr(), e)));
        }
        enabledMovesConstChoices = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesConstChoices()){
            enabledMovesConstChoices.put(e, new ActivityOfMove());
        }
        enabledChangingTypesMoves = new StringMap<ActivityOfMove>();
        for (String m: _import.getMovesChangingTypes()) {
            enabledChangingTypesMoves.put(m, new ActivityOfMove());
        }
        enabledCounteringMoves = new StringMap<ActivityOfMove>();
        for (String m: _import.getMovesCountering()) {
            enabledCounteringMoves.put(m, new ActivityOfMove());
        }
        alreadyInvokedMovesRound = new StringList();
        lastUsedItem = DataBase.EMPTY_STRING;
    }


    void initCreatureRelationsAutre(TeamPositionList _cbts,DataBase _import){
        for(TeamPosition c:_cbts){
            ajouterRelationAutre(c,_import);
        }
    }

    void initIvAdv(Difficulty _diff,String _ballCapture){
        for(Statistic c:Statistic.getStatisticsWithBase()){
            iv.put(c,_diff.getIvFoe());
        }
        initHp();
        usedBallCatching=_ballCapture;
    }

    void initIvUt(Difficulty _diff){
        for(Statistic c:Statistic.getStatisticsWithBase()){
            iv.put(c,_diff.getIvPlayer());
        }
    }

    void initHp() {
        if (Rate.strGreater(remainingHp, pvMax())) {
            remainingHp = pvMax();
        }
    }

    //This class is covered
    //byte _user, in back
    boolean validate(DataBase _data, int _numberTeam, Fight _fight) {
        if (koNames(_data) || koAbilities(_data) || koItems(_data) || koHeightWeight() || koTypes(_data) || koOwnedMoves(_data)) {
            return false;
        }
        for (String m: alreadyInvokedMovesRound) {
            if (!_data.getMoves().contains(m)) {
                return false;
            }
        }
        if (koAction(_data, _numberTeam, _fight) || !nbRounds.isZeroOrGt() || koLevel(_data) || koStatisticMaps(_data) || invalidUsedBallCatchingBelongToUser(_data) || koWonPoints() || koHappiness(_data) || koEnabledList(_data, _fight)) {
            return false;
        }
        return !koMovesToBeLearntEvos(_data) && !koSpecMoves(_data) && okAbsentForRound() && nbRepeatingSuccessfulMoves.isZeroOrGt() && okLastUsedMove(_data) && okUsedMoveLastRound(_data) && okLastSuccessfulMove(_data) && okLastSufferedMove(_data) && _data.getTypes().containsAllObj(lastSufferedMoveTypes) && okGroundPositionsHp();
    }

    private boolean koTypes(DataBase _data) {
        for (String t: types) {
            if (!StringUtil.contains(_data.getTypes(), t)) {
                return true;
            }
        }
        return false;
    }

    private boolean koHeightWeight() {
        return height.isZeroOrLt() || weight.isZeroOrLt();
    }

    private boolean koItems(DataBase _data) {
        return invalidItem(_data) || invalidExpItem(_data) || invalidLastUsedItem(_data);
    }

    private boolean koAbilities(DataBase _data) {
        return !_data.getAbilities().contains(ability) || !currentAbility.isEmpty()&&!_data.getAbilities().contains(currentAbility);
    }

    private boolean koNames(DataBase _data) {
        return !_data.getPokedex().contains(name) || !_data.getPokedex().contains(currentName);
    }

    private boolean invalidLastUsedItem(DataBase _data) {
        return !lastUsedItem.isEmpty() && !_data.getItems().contains(lastUsedItem);
    }

    private boolean invalidItem(DataBase _data) {
        return !item.isEmpty() && !_data.getItems().contains(item);
    }

    private boolean invalidExpItem(DataBase _data) {
        return !expItem.isEmpty() && dataExpObject(_data) == null;
    }

    private boolean invalidUsedBallCatchingBelongToUser(DataBase _data) {
        return isBelongingToPlayer() && invalidUsedBallCatching(_data);
    }

    private boolean koWonPoints() {
        return !wonExp.isZeroOrGt() || !wonExpSinceLastLevel.isZeroOrGt();
    }

    private boolean koHappiness(DataBase _data) {
        return happiness < 0 || happiness > _data.getHappinessMax();
    }

    private boolean koLevel(DataBase _data) {
        return level <= 0 || level > _data.getMaxLevel();
    }

    private boolean koStatisticMaps(DataBase _data) {
        return !Statistic.equalsSet(ev.getKeys(), Statistic.getStatisticsWithBase()) || !Statistic.equalsSet(statisBase.getKeys(), Statistic.getStatisticsWithBase()) || !Statistic.equalsSet(statisBoost.getKeys(), Statistic.getStatisticsWithBoost()) || koStatistic(_data);
    }

    private boolean invalidUsedBallCatching(DataBase _data) {
        return !_data.getItems().contains(usedBallCatching) || !(_data.getItem(usedBallCatching) instanceof Ball);
    }

    private boolean koEnabledList(DataBase _data, Fight _fight) {
        if (!_data.getTypes().containsAllObj(protectedAgainstMoveTypes)) {
            return true;
        }
        if (koRelations(_data, _fight)) {
            return true;
        }
        if (koDamageRateInflictedByType(_data)) {
            return true;
        }
        if (koSufferingMoves(_data)) {
            return true;
        }
        if (koEnabled(_data)) {
            return true;
        }
        if (koEnabledMovesEndRound(_data)) {
            return true;
        }
        return !StringUtil.equalsSet(_data.getMovesEffectAlly(), enabledMovesForAlly.getKeys()) || !StringUtil.equalsSet(_data.getMovesConstChoices(), enabledMovesConstChoices.getKeys()) || !StringUtil.equalsSet(_data.getMovesChangingTypes(), enabledChangingTypesMoves.getKeys()) || !StringUtil.equalsSet(_data.getMovesCountering(), enabledCounteringMoves.getKeys());
    }

    private boolean koEnabledMovesEndRound(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getMovesEffEndRoundIndiv(), enabledMovesEndRound.getKeys())) {
            return true;
        }
        for (String m: enabledMovesEndRound.getKeys()) {
            if (enabledMovesEndRound.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koMovesToBeLearnt(DataBase _data) {
        for (String m:movesToBeLearnt) {
            if (!_data.getMoves().contains(m) || moves.contains(m)) {
                return true;
            }
        }
        return false;
    }

    private boolean koDamageRateInflictedByType(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getTypes(), damageRateInflictedByType.getKeys())) {
            return true;
        }
        for (String m: damageRateInflictedByType.getKeys()) {
            if (!damageRateInflictedByType.getVal(m).isZeroOrGt()) {
                return true;
            }
        }
        return false;
    }

    private boolean okGroundPositionsHp() {
        if (!clone.isZeroOrGt() || !remainingHp.isZeroOrGt() || Rate.strGreater(remainingHp, pvMax())) {
            return false;
        }
        if (estKo() && !estArriere() || NumberUtil.eq(groundPlaceSubst, Fighter.BACK) && !estArriere()) {
            return false;
        }
        return !TargetCoords.koPosition(groundPlace) && !TargetCoords.koPosition(groundPlaceSubst);
    }

    private boolean okAbsentForRound() {
        return (nbPrepaRound != 0 || !disappeared) && nbPrepaRound >= 0 && (nbPrepaRound <= 0 || !needingToRecharge);
    }

    private boolean okUsedMoveLastRound(DataBase _data) {
        return usedMoveLastRound.isEmpty() || (_data.getMoves().contains(usedMoveLastRound) && StringUtil.contains(attaquesUtilisables(), usedMoveLastRound));
    }

    private boolean okLastSuccessfulMove(DataBase _data) {
        return lastSuccessfulMove.isEmpty() || _data.getMoves().contains(lastSuccessfulMove);
    }

    private boolean okLastSufferedMove(DataBase _data) {
        return lastSufferedMove.isEmpty() || _data.getMoves().contains(lastSufferedMove);
    }

    private boolean okLastUsedMove(DataBase _data) {
        return lastUsedMove.isEmpty() || _data.getMoves().contains(lastUsedMove);
    }

    private boolean koSufferingMoves(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getTypes(), damageRateSufferedByType.getKeys())) {
            return true;
        }
        for (String m: damageRateSufferedByType.getKeys()) {
            if (!damageRateSufferedByType.getVal(m).isZeroOrGt()) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(_data.getCategories(), damageSufferedCateg.getKeys())) {
            return true;
        }
        for (String m: damageSufferedCateg.getKeys()) {
            if (!damageSufferedCateg.getVal(m).isZeroOrGt()) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(_data.getCategories(), damageSufferedCategRound.getKeys())) {
            return true;
        }
        for (String m: damageSufferedCategRound.getKeys()) {
            if (!damageSufferedCategRound.getVal(m).isZeroOrGt()) {
                return true;
            }
        }
        return false;
    }

    private boolean koStatistic(DataBase _data) {
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            if (ev.getVal(s) < 0 || statisBase.getVal(s).isZeroOrLt()) {
                return true;
            }
        }
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            long boost_ = statisBoost.getVal(s);
            if (boost_ < _data.getMinBoost() || boost_ > _data.getMaxBoost()) {
                return true;
            }
        }
        return false;
    }

    private boolean koOwnedMoves(DataBase _data) {
        if (moves.size() > _data.getNbMaxMoves()) {
            return true;
        }
        for (EntryCust<String, UsesOfMove> m: moves.entryList()) {
            if (StringUtil.quickEq(m.getKey(), _data.getDefMove()) || !_data.getMoves().contains(m.getKey()) || m.getValue().getCurrent() < 0 || m.getValue().getCurrent() > m.getValue().getMax() || m.getValue().getMax() == 0) {
                return true;
            }
        }
        if (moves.isEmpty() || currentMoves.size() > _data.getNbMaxMoves()) {
            return true;
        }
        for (EntryCust<String, UsesOfMove> m: currentMoves.entryList()) {
            if (StringUtil.quickEq(m.getKey(), _data.getDefMove()) || !_data.getMoves().contains(m.getKey()) || m.getValue().getCurrent() < 0 || m.getValue().getCurrent() > m.getValue().getMax() || m.getValue().getMax() == 0) {
                return true;
            }
        }
        return attaquesUtilisables().hasDuplicates();
    }

    private boolean koEnabled(DataBase _data) {
        if (!StringUtil.equalsSet(_data.getMovesEffectIndiv(), enabledMoves.getKeys())) {
            return true;
        }
        for (String m: enabledMoves.getKeys()) {
            if (enabledMoves.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(_data.getMovesEffectUnprot(), enabledMovesUnprot.getKeys())) {
            return true;
        }
        for (String m: enabledMovesUnprot.getKeys()) {
            if (enabledMovesUnprot.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(_data.getMovesEffectProt(), enabledMovesProt.getKeys())) {
            return true;
        }
        for (String m: enabledMovesProt.getKeys()) {
            if (enabledMovesProt.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        return koEnabled();
    }

    private boolean koEnabled() {
        for (String m: enabledMovesConstChoices.getKeys()) {
            if (enabledMovesConstChoices.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        for (String m: enabledChangingTypesMoves.getKeys()) {
            if (enabledChangingTypesMoves.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        for (String m: enabledCounteringMoves.getKeys()) {
            if (enabledCounteringMoves.getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koRelations(DataBase _data, Fight _fight) {
        TeamPositionList fighters_ = FightOrder.fighters(_fight);
        if (!MoveTeamPosition.equalsSet(trackingMoves.getKeys(), relMoves(fighters_, _data.getMovesActingMoveUses())) || !MoveTeamPosition.equalsSet(trappingMoves.getKeys(), relMoves(fighters_, _data.getTrappingMoves())) || !MoveTeamPosition.equalsSet(privateMoves.getKeys(), relMoves(fighters_, _data.getMovesForbidding())) || !MoveTeamPosition.equalsSet(incrUserAccuracy.getKeys(), relMoves(fighters_, _data.getMovesAccuracy()))) {
            return true;
        }
        if (koTrackingTrappingPrivate(_data)) {
            return true;
        }
        return koStatus(_data, fighters_);
    }

    private boolean koTrackingTrappingPrivate(DataBase _data) {
        for (AffectedMove v: trackingMoves.values()) {
            if (!v.getMove().isEmpty() && !_data.getMoves().contains(v.getMove()) || v.getActivity().getNbTurn() < 0) {
                return true;
            }
        }
        for (ActivityOfMove k: trappingMoves.values()) {
            if (k.getNbTurn() < 0) {
                return true;
            }
        }
        for (CustList<String> k: privateMoves.values()) {
            for (String m: k) {
                if (!_data.getMoves().contains(m)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean koSpecMoves(DataBase _data) {
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_data.getVarParamsMove(_data.cibleNbUtilisation()));
        attaques_.addAllElts(_data.getVarParamsMove(_data.lanceurNbUtilisation()));
        if (!StringUtil.equalsSet(attaques_, nbUsesMoves.getKeys())) {
            return true;
        }
        for (String m: nbUsesMoves.getKeys()) {
            if (nbUsesMoves.getVal(m) < 0) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(copiedMoves.getKeys(), _data.getMovesCopyingTemp())) {
            return true;
        }
        for (String m: copiedMoves.getKeys()) {
            CopiedMove val_ = copiedMoves.getVal(m);
            if (val_.getPp() < 0 || !val_.getMove().isEmpty() && !_data.getMoves().contains(val_.getMove())) {
                return true;
            }
        }
        return false;
    }

    private boolean koStatus(DataBase _data, TeamPositionList _fighters) {
        StringList statusSingle_;
        statusSingle_ = new StringList();
        StringList statusRelation_;
        statusRelation_ = new StringList();
        for (String s: _data.getStatus().getKeys()) {
            if (_data.getStatus(s).getStatusType() == StatusType.INDIVIDUEL) {
                statusSingle_.add(s);
            } else {
                statusRelation_.add(s);
            }
        }
        if (!StringUtil.equalsSet(status.getKeys(), statusSingle_) || !MoveTeamPosition.equalsSet(statusRelat.getKeys(), relMoves(_fighters, statusRelation_))) {
            return true;
        }
        for (long s: status.values()) {
            if (s < 0) {
                return true;
            }
        }
        for (long s: statusRelat.values()) {
            if (s < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean koMovesToBeLearntEvos(DataBase _data) {
        if (koMovesToBeLearnt(_data)) {
            return true;
        }
        PokemonData fPk_ = fichePokemon(_data);
        for (EntryCust<String, MovesAbilities> e: movesAbilitiesEvos.entryList()) {
            if (koEvo(_data, fPk_, e.getKey(), e.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean koEvo(DataBase _data, PokemonData _fPk, String _e, MovesAbilities _movesAbilities) {
        if (!_fPk.getEvolutions().contains(_e)) {
            return true;
        }
        for (String m: moves.getKeys()) {
            if (StringUtil.contains(_movesAbilities.getMoves(), m)) {
                return true;
            }
        }
        for (String m: _movesAbilities.getMoves()) {
            if (!_data.getMoves().contains(m)) {
                return true;
            }
        }
        for (String a: _movesAbilities.getAbilities()) {
            if (!_data.getAbilities().contains(a)) {
                return true;
            }
        }
        return false;
    }

    private boolean koAction(DataBase _data, int _numberTeam, Fight _fight) {
        Team team_ = _fight.getTeams().getVal(_numberTeam);
        Ints members_ = new Ints(team_.getMembers().getKeys());
        if (action instanceof ActionMove) {
            ActionMove actionMove_ = (ActionMove) action;
            if (!_data.getMoves().contains(actionMove_.getFirstChosenMove()) || notBackOrInList(members_, actionMove_.getSubstitute())) {
                return true;
            }
            if (_data.getMove(actionMove_.getFirstChosenMove()).getTargetChoice().isWithChoice()) {
                if (actionMove_.getChosenTargets().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                    return true;
                }
            } else if (!actionMove_.getChosenTargets().isEmpty()) {
                return true;
            }
            if (!actionMove_.getFinalChosenMove().isEmpty() && !_data.getMoves().contains(actionMove_.getFinalChosenMove())) {
                return true;
            }
        }
        return koActionSub(_data, members_);
    }

    static boolean notBackOrInList(Ints _members, int _value) {
        return !backOrInList(_members, _value);
    }

    static boolean backOrInList(Ints _members, int _value) {
        return NumberUtil.eq(_value, BACK) || _members.containsObj(_value);
    }

    private boolean koActionSub(DataBase _data, Ints _members) {
        if (action instanceof ActionSwitch) {
            ActionSwitch actionSwitch_ = (ActionSwitch) action;
            if (NumberUtil.eq(actionSwitch_.getSubstitute(), BACK) || !_members.containsObj(actionSwitch_.getSubstitute())) {
                return true;
            }
        }
        return koActionSub(_data);
    }

    private boolean koActionSub(DataBase _data) {
        if (action instanceof ActionHeal) {
            ActionHeal actionHeal_ = (ActionHeal) action;
            if (!(_data.getItem(actionHeal_.getChosenHealingItem()) instanceof Berry) && !(_data.getItem(actionHeal_.getChosenHealingItem()) instanceof HealingItem)) {
                return true;
            }
            if (action instanceof ActionHealMove) {
                //one move
                ActionHealMove actionHealMove_ = (ActionHealMove) action;
                return koActionHealMove(_data, actionHealMove_);
            }
            if (_data.getItem(actionHeal_.getChosenHealingItem()) instanceof HealingPp) {
                HealingPp pp_ = (HealingPp) _data.getItem(actionHeal_.getChosenHealingItem());
                if (pp_.healOneMove()) {
                    return true;
                }
            }
            if (_data.getItem(actionHeal_.getChosenHealingItem()) instanceof Berry) {
                Berry berry_ = (Berry) _data.getItem(actionHeal_.getChosenHealingItem());
                return berry_.getHealPp() > 0;
            }
        }
        return false;
    }

    private boolean koActionHealMove(DataBase _data, ActionHealMove _actionHealMove) {
        boolean instanceOf_ = false;
        if (_data.getItem(_actionHealMove.getChosenHealingItem()) instanceof HealingPp) {
            instanceOf_ = true;
            HealingPp pp_ = (HealingPp) _data.getItem(_actionHealMove.getChosenHealingItem());
            if (!pp_.healOneMove()) {
                return true;
            }
        }
        if (_data.getItem(_actionHealMove.getChosenHealingItem()) instanceof Berry) {
            instanceOf_ = true;
            Berry berry_ = (Berry) _data.getItem(_actionHealMove.getChosenHealingItem());
            if (berry_.getHealPp() == 0) {
                return true;
            }
        }
        if (!instanceOf_) {
            return true;
        }
        return !_data.getMoves().contains(_actionHealMove.getFirstChosenMove());
    }

    private CustList<MoveTeamPosition> relMoves(TeamPositionList _fighters, StringList _moves) {
        StringList relMoves_ = new StringList();
        relMoves_.addAllElts(_moves);
        CustList<MoveTeamPosition> relMovesTh_ = new CustList<MoveTeamPosition>();
        for (TeamPosition f: _fighters) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        return relMovesTh_;
    }

    void ajouterRelationAutre(TeamPosition _cbt,DataBase _import){
        for(String c:_import.getStatus().getKeys()){
            Status statut_=_import.getStatus(c);
            if (statut_.getStatusType() == StatusType.INDIVIDUEL) {
                continue;
            }
            statusRelat.put(new MoveTeamPosition(c,_cbt), 0L);
        }
        for(String e:_import.getMovesActingMoveUses()){
            trackingMoves.put(new MoveTeamPosition(e,_cbt),new AffectedMove(DataBase.EMPTY_STRING,new ActivityOfMove()));
        }
        for(String e:_import.getMovesForbidding()){
            privateMoves.put(new MoveTeamPosition(e,_cbt),new StringList());
        }
        for(String e:_import.getTrappingMoves()){
            trappingMoves.put(new MoveTeamPosition(e,_cbt),new ActivityOfMove());
        }
        for(String e:_import.getMovesAccuracy()){
            incrUserAccuracy.put(new MoveTeamPosition(e,_cbt), BoolVal.FALSE);
        }
    }

    void initRoundFrontFighter() {
        toutSupprimerAttaquesDejaInvoqueesTour();
        reinitEffetTour();
        if(getNbPrepaRound() == 0){
            //prise en compte de l'attaque PATIENCE
            for(String c:getDamageSufferedCateg().getKeys()){
                getDamageSufferedCateg().put(c, Rate.zero());
            }
        }
        for(String c:getDamageSufferedCategRound().getKeys()){
            getDamageSufferedCategRound().put(c, Rate.zero());
        }
        setActed(false);
    }

    Rate numberNecessaryPointsForGrowingLevel(long _niveau,DataBase _import){
        return numberNecessaryPointsForGrowingLevel(name, _niveau, _import);
    }

    static Rate numberNecessaryPointsForGrowingLevel(String _name, long _niveau, DataBase _import) {
        PokemonData fPk_= _import.getPokemon(_name);
        String expLitt_=_import.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        String niv_ = _import.prefixNiveau();
        vars_.put(niv_,Long.toString(_niveau));
        Rate next_;
        next_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(niv_,Long.toString(_niveau - 1L));
        current_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        return _import.evaluatePositiveExp(Rate.minus(next_, current_).toNumberString(), vars_, Rate.one());
    }

    StringList nomEvolutions(DataBase _import,StringList _pkNamesBegin){
        StringList evos_ = new StringList();
        ItemForBattle objet_ = dataExpObject(_import);
        if (objet_ != null && objet_.getAgainstEvo()) {
            return evos_;
        }
        PokemonData fPk_=fichePokemon(_import);
        for(String e:fPk_.getEvolutions().getKeys()){
            Evolution evo_=fPk_.getEvolution(e);
            nomEvolution(_import, _pkNamesBegin, evos_, e, evo_);
        }
        return evos_;
    }

    private void nomEvolution(DataBase _import, StringList _pkNamesBegin, StringList _evos, String _e, Evolution _evo) {
        if(_evo instanceof EvolutionMove){
            evolutionMove(_evos, _e, (EvolutionMove) _evo);
            return;
        }
        if(_evo instanceof EvolutionMoveType){
            evolutionMoveType(_import, _evos, _e, (EvolutionMoveType) _evo);
            _evos.removeDuplicates();
        }
        if(_evo instanceof EvolutionHappiness){
            evolutionHappiness(_import, _evos, _e);
            return;
        }
        if(_evo instanceof EvolutionLevelGender){
            evolutionLevelGender(_evos, _e, (EvolutionLevelGender) _evo);
            return;
        }
        if(_evo instanceof EvolutionLevel){
            evolutionLevel(_evos, _e, (EvolutionLevel) _evo);
            return;
        }
        if(_evo instanceof EvolutionItem){
            evolutionItem(_evos, _e, (EvolutionItem) _evo);
            return;
        }
        if(_evo instanceof EvolutionTeam){
            evolutionTeam(_pkNamesBegin, _evos, _e, (EvolutionTeam) _evo);
        }
    }

    private void evolutionTeam(StringList _pkNamesBegin, StringList _evos, String _e, EvolutionTeam _evo) {
        if(StringUtil.contains(_pkNamesBegin, _evo.getPokemon())){
            _evos.add(_e);
        }
    }

    private void evolutionItem(StringList _evos, String _e, EvolutionItem _evo) {
        if(StringUtil.quickEq(_evo.getItem(),item)){
            _evos.add(_e);
        }
    }

    private void evolutionLevel(StringList _evos, String _e, EvolutionLevel _evo) {
        if(level>= _evo.getLevel()){
            _evos.add(_e);
        }
    }

    private void evolutionLevelGender(StringList _evos, String _e, EvolutionLevelGender _evo) {
        if(gender== _evo.getGender()&&level>= _evo.getLevel()){
            _evos.add(_e);
        }
    }

    private void evolutionHappiness(DataBase _import, StringList _evos, String _e) {
        if(happiness>= _import.getHappinessEvo()){
            _evos.add(_e);
        }
    }

    private void evolutionMove(StringList _evos, String _e, EvolutionMove _evoAtt) {
        if(moves.contains(_evoAtt.getMove())){
            _evos.add(_e);
        }
    }

    private void evolutionMoveType(DataBase _import, StringList _evos, String _e, EvolutionMoveType _evoType) {
        for (String move_: moves.getKeys()) {
            for (String type_: _import.getMove(move_).getTypes()) {
                if (StringUtil.quickEq(type_, _evoType.getType())) {
                    _evos.add(_e);
                }
            }
        }
    }

    boolean possedeObjet(){
        return !item.isEmpty();
    }

    Item ficheObjet(DataBase _import){
        return _import.getItem(item);
    }

    ItemForBattle dataExpObject(DataBase _import){
        return _import.usedObjectUsedForExp(expItem);
    }

    public LgInt rateRemainHp() {
        Rate r_ = Rate.divide(remainingHp, pvMax());
//        r_.multiplyBy(new Rate(CENT));
//        return r_.intPart();
        return r_.percent();
    }

    public LgInt wonExpRate(DataBase _import) {
        if (!belongingToPlayer) {
            return LgInt.zero();
        }
        long next_ = level;
        next_++;
        Rate nec_ = numberNecessaryPointsForGrowingLevel(next_, _import);
        Rate r_ = Rate.divide(Rate.plus(wonExpSinceLastLevel, wonExp), nec_);
//        r_.multiplyBy(new Rate(CENT));
//        return r_.intPart();
        return r_.percent();
    }

    public boolean estKo(){
        return remainingHp.isZero();
    }

    public boolean estArriere(){
        return groundPlace == BACK;
    }

    StringList attaquesUtilisables(){
        StringList liste_ = new StringList();
        for(String c:currentMoves.getKeys()){
            if(copiedMoves.contains(c)){
                if(copiedMoves.getVal(c).getPp() != 0){
                    liste_.add(copiedMoves.getVal(c).getMove());
                }else{
                    liste_.add(c);
                }
            }else{
                liste_.add(c);
            }
        }
        return liste_;
    }

    long powerPointsMove(String _attaque){
        for(String c:copiedMoves.getKeys()){
            if(StringUtil.quickEq(copiedMoves.getVal(c).getMove(),_attaque)){
                return copiedMoves.getVal(c).getPp();
            }
        }
        if (currentMoves.contains(_attaque)) {
            return currentMoves.getVal(_attaque).getCurrent();
        }
        if (moves.contains(_attaque)) {
            return moves.getVal(_attaque).getCurrent();
        }
        return 0;
    }

    long maxPowerPointsMove(String _attaque, DataBase _import){
        for(String c:copiedMoves.getKeys()){
            long pp_ = _import.ppCopiedMove(c);
            if(StringUtil.quickEq(copiedMoves.getVal(c).getMove(),_attaque)){
                return pp_;
            }
        }
        if (currentMoves.contains(_attaque)) {
            return currentMoves.getVal(_attaque).getMax();
        }
        if (moves.contains(_attaque)) {
            return moves.getVal(_attaque).getMax();
        }
        return 0;
    }

    long healedPpMove(String _attaque,String _objet,DataBase _import){
        if(changed){
            return 0;
        }
        StringList listIntersectMoves_;
        listIntersectMoves_ = StringUtil.intersect(moves.getKeys(),currentMoves.getKeys());
        if (!StringUtil.contains(listIntersectMoves_, _attaque)) {
            return 0;
        }
        //moves.contains(_attaque) && currentMoves.contains(_attaque)
        Item objet_=_import.getItem(_objet);
        if (objet_ == null) {
            return 0;
        }
        if(objet_ instanceof HealingPp){
            HealingPp soinPp_=(HealingPp)objet_;
            if(soinPp_.getHealingMoveFullpp()||soinPp_.isHealingAllMovesPp()){
                return currentMoves.getVal(_attaque).getLostPp();
            }
            long var_=soinPp_.getHealedMovePp();
            if(var_==0){
                var_=soinPp_.getHealingAllMovesFullpp();
            }
            if(currentMoves.getVal(_attaque).getCurrent()+var_>currentMoves.getVal(_attaque).getMax()){
                return currentMoves.getVal(_attaque).getLostPp();
            }
            return var_;
        }
        Berry baie_=(Berry)objet_;
        long var_=baie_.getHealPp();
        if(currentMoves.getVal(_attaque).getCurrent()+var_>currentMoves.getVal(_attaque).getMax()){
            return currentMoves.getVal(_attaque).getLostPp();
        }
        return var_;
    }

    void healPowerPoints(String _attaque,long _var){
        UsesOfMove pps_ = currentMoves.getVal(_attaque);
        pps_.heal(_var);
    }


    void usePowerPointsByMove(Difficulty _diff,String _attaque,long _var){
        for(String c:copiedMoves.getKeys()){
            CopiedMove pp_= copiedMoves.getVal(c);
            if(!StringUtil.quickEq(pp_.getMove(),_attaque)){
                continue;
            }
            if(pp_.getPp()<_var){
                pp_.setPp(0);
            }else{
                pp_.setPp(pp_.getPp()-_var);
            }
            return;
        }
        if (currentMoves.contains(_attaque)) {
            UsesOfMove pps_ = currentMoves.getVal(_attaque);
            long ppAct_=pps_.getCurrent();
            if(changed){
                changePpMovesEvo(_var, pps_, ppAct_);
                return;
            }
            if(ppAct_<_var){
                ppAct_=0;
            }else{
                ppAct_= ppAct_-_var;
            }
            pps_.setCurrent(ppAct_);
            if(_diff.getRestoredMovesEndFight()){
                return;
            }
        }
        if (moves.contains(_attaque)) {
            UsesOfMove pps_ = moves.getVal(_attaque);
            changePpMoves(_var, pps_);
        }
    }

    private void changePpMovesEvo(long _var, UsesOfMove _pps, long _ppAct) {
        long ppAct_ = _ppAct;
        if(ppAct_ < _var){
            ppAct_ =0;
        }else{
            ppAct_ = ppAct_ - _var;
        }
        _pps.setCurrent(ppAct_);
    }

    private void changePpMoves(long _var, UsesOfMove _pps) {
        long pp_= _pps.getCurrent();
        if(pp_< _var){
            pp_=0;
        }else{
            pp_= pp_- _var;
        }
        _pps.setCurrent(pp_);
    }

    void effectBatonPass(Fighter _lanceur){
        for (Statistic s: _lanceur.statisBoost.getKeys()) {
            statisBoost.put(s, _lanceur.statisBoost.getVal(s));
        }
        clone.affect(_lanceur.clone);
        for (String s: _lanceur.enabledMoves.getKeys()) {
            enabledMoves.put(s, new ActivityOfMove(_lanceur.enabledMoves.getVal(s)));
        }
        for (String s: _lanceur.enabledMovesProt.getKeys()) {
            enabledMovesProt.put(s, new ActivityOfMove(_lanceur.enabledMovesProt.getVal(s)));
        }
        protectedAgainstMoveTypes.clear();
        protectedAgainstMoveTypes.addAllElts(_lanceur.getProtectedAgainstMoveTypes());
        for (String s: _lanceur.enabledMovesUnprot.getKeys()) {
            enabledMovesUnprot.put(s, new ActivityOfMove(_lanceur.enabledMovesUnprot.getVal(s)));
        }
        for (String s: _lanceur.enabledMovesEndRound.getKeys()) {
            enabledMovesEndRound.put(s, new ActivityOfMove(_lanceur.enabledMovesEndRound.getVal(s)));
        }
        for (String s: _lanceur.damageRateInflictedByType.getKeys()) {
            damageRateInflictedByType.put(s, new Rate(_lanceur.damageRateInflictedByType.getVal(s)));
        }
        for (String s: _lanceur.damageRateSufferedByType.getKeys()) {
            damageRateSufferedByType.put(s, new Rate(_lanceur.damageRateSufferedByType.getVal(s)));
        }
        for (String s: _lanceur.nbUsesMoves.getKeys()) {
            nbUsesMoves.put(s, _lanceur.nbUsesMoves.getVal(s));
        }
        for (String s: _lanceur.damageSufferedCateg.getKeys()) {
            damageSufferedCateg.put(s, new Rate(_lanceur.damageSufferedCateg.getVal(s)));
        }
        for (MoveTeamPosition s: _lanceur.incrUserAccuracy.getKeys()) {
            incrUserAccuracy.put(s, _lanceur.incrUserAccuracy.getVal(s));
        }
        for (MoveTeamPosition s: _lanceur.trappingMoves.getKeys()) {
            trappingMoves.put(s, new ActivityOfMove(_lanceur.trappingMoves.getVal(s)));
        }
        lastSufferedMove=_lanceur.lastSufferedMove;
        nbRepeatingSuccessfulMoves.affect(_lanceur.nbRepeatingSuccessfulMoves);
    }

    void invokeMove(){
        invokeMove(alreadyInvokedMovesRound.last());
    }
    void invokeMove(String _move){
        //action instanceof ActionMove
        ((ActionMove)action).setFinalChosenMove(_move);
        lastUsedMove = _move;
    }

    void successUsingMove(){
        if (!(action instanceof ActionMove)) {
            return;
        }
        successfulMove=true;
        lastSuccessfulMove=((ActionMove)action).getFinalChosenMove();
    }

    void groundPlaceSubst(int _pos) {
        groundPlace = _pos;
        groundPlaceSubst = _pos;
    }

    void affectGroundPlaceBySubst() {
        groundPlace = groundPlaceSubst;
    }

    void affectGroundPlaceSubst() {
        groundPlaceSubst = groundPlace;
    }
    void exitFrontBattle(){
        groundPlace=BACK;
    }

    void exitFrontBattleForBeingSubstitued(){
        groundPlaceSubst=BACK;
    }



    void cancelSubstituing(){
        if (action instanceof ActionSwitch) {
            action = null;
        }
    }



    void affecterPseudoStatut(TeamPosition _combattant,String _pseudoStatut){
        statusRelat.set(new MoveTeamPosition(_pseudoStatut,_combattant),1L);
    }

    /**public for tests*/
    public void affecterStatut(String _statut){
        status.set(_statut,1L);
    }

    void disableAllStatusByEnabledWeather(String _weather, DataBase _data) {
        AbilityData fCapac_ = ficheCapaciteActuelle(_data);
        if (fCapac_ == null) {
            return;
        }
        if (fCapac_.getImmuStatus().contains(_weather)) {
            for(String e:fCapac_.getImmuStatus().getVal(_weather)){
                if(_data.getStatus(e).getStatusType() == StatusType.INDIVIDUEL){
                    supprimerStatut(e);
                }else{
                    supprimerPseudoStatut(e);
                }
            }
        }
    }

    StringList getAddedTypesByEnabledWeather(String _weather, DataBase _data) {
        AbilityData fCapac_ = ficheCapaciteActuelle(_data);
        if (fCapac_ == null) {
            return new StringList();
        }
        if(!fCapac_.getChgtTypeByWeather().contains(_weather)){
            return new StringList();
        }
        return new StringList(fCapac_.getChgtTypeByWeather().getVal(_weather));
    }

    boolean hasObjectEnabledBeingSent(DataBase _import) {
        return effectWhileSendingWithStatistic(_import) != null;
    }
    EffectWhileSendingWithStatistic effectWhileSendingWithStatistic(DataBase _import) {
        if (!possedeObjet()) {
            return null;
        }
        Item objet_= ficheObjet(_import);
        if (!(objet_ instanceof ItemForBattle)) {
            return null;
        }
        ItemForBattle objetAttachableCombat_=(ItemForBattle)objet_;
        if (objetAttachableCombat_.enabledSending()) {
            return objetAttachableCombat_.getEffectSending().first();
        }
        return null;
    }

    void incrementPseudoStatutCombattant(TeamPosition _combattant,String _pseudoStatut) {
        MoveTeamPosition key_ = new MoveTeamPosition(_pseudoStatut,_combattant);
        statusRelat.set(key_, statusRelat.getVal(key_)+1);
    }

    void supprimerPseudoStatutCombattant(TeamPosition _combattant,String _pseudoStatut){
        statusRelat.set(new MoveTeamPosition(_pseudoStatut,_combattant),0L);
    }

    void supprimerPseudoStatut(String _pseudoStatut){
        for(MoveTeamPosition c:statusRelat.getKeys()){
            if(StringUtil.quickEq(c.getMove(),_pseudoStatut)){
                statusRelat.put(c,0L);
            }
        }
    }

    void supprimerStatut(String _statut){
        status.set(_statut,0L);
    }

    void affecterTypes(StringList _types){
        types=new StringList(_types);
    }

    void affecterTypes(String _types){
        types=new StringList(_types);
    }

    void activerAttaque(String _attaque){
        enabledMoves.getVal(_attaque).enableReset();
    }

    void desactiverAttaque(String _attaque){
        enabledMoves.getVal(_attaque).disable();
        enabledMoves.getVal(_attaque).reset();
    }

    void activerAttaqueImmu(String _attaque, DataBase _import){
        enabledMovesProt.getVal(_attaque).enableReset();
        for (Effect e: _import.getMove(_attaque).getEffects()) {
            if (!(e instanceof EffectProtectFromTypes)) {
                continue;
            }
            protectedAgainstMoveTypes.addAllElts(((EffectProtectFromTypes)e).getImmuAgainstTypes());
        }
        protectedAgainstMoveTypes.removeDuplicates();
    }

    void desactiverAttaqueImmu(String _attaque, DataBase _import){
        for (String m: enabledMovesProt.getKeys()) {
            if (!StringUtil.quickEq(m,_attaque)) {
                continue;
            }
            enabledMovesProt.getVal(_attaque).disable();
            enabledMovesProt.getVal(_attaque).reset();
        }
        protectedAgainstMoveTypes.clear();
        for (String m: enabledMovesProt.getKeys()) {
            if (!enabledMovesProt.getVal(m).isEnabled()) {
                continue;
            }
            for (Effect e: _import.getMove(m).getEffects()) {
                if (!(e instanceof EffectProtectFromTypes)) {
                    continue;
                }
                protectedAgainstMoveTypes.addAllElts(((EffectProtectFromTypes)e).getImmuAgainstTypes());
            }
        }
        protectedAgainstMoveTypes.removeDuplicates();
    }

    void activerAttaqueAntiImmu(String _attaque){
        enabledMovesUnprot.getVal(_attaque).enableReset();
    }

    void desactiverAttaqueAntiImmu(String _attaque){
        enabledMovesUnprot.getVal(_attaque).disable();
        enabledMovesUnprot.getVal(_attaque).reset();
    }
    void activerAttaqueBlocantLanceur(String _attaque){
        enabledMovesConstChoices.getVal(_attaque).enableReset();
    }

    void desactiverAttaqueBlocantLanceur(String _attaque){
        enabledMovesConstChoices.getVal(_attaque).disable();
        enabledMovesConstChoices.getVal(_attaque).reset();
    }

    void enableChangingMovesTypes(String _move) {
        enabledChangingTypesMoves.getVal(_move).enableReset();
    }

    void disableChangingMovesTypes(String _move) {
        enabledChangingTypesMoves.getVal(_move).disable();
        enabledChangingTypesMoves.getVal(_move).reset();
    }

    void enableCounteringMoves(String _move) {
        enabledCounteringMoves.getVal(_move).enableReset();
    }

    void disableCounteringMoves(String _move) {
        enabledCounteringMoves.getVal(_move).disable();
        enabledCounteringMoves.getVal(_move).reset();
    }

    void backUpObject(String _objet){
        if (!item.isEmpty() && _objet.isEmpty()) {
            lastUsedItem = item;
        }
        item=_objet;
    }

    void variationBoostStatistique(Statistic _statistique,long _variation){
        long value_ = statisBoost.getVal(_statistique);
        value_ += _variation;
        statisBoost.put(_statistique, value_);
    }

    Rate variationLeftHp(Rate _variation){
        Rate r_ = Rate.zero();
        Rate sum_ = Rate.plus(remainingHp, _variation);
        Rate max_ = pvMax();
        if (Rate.strGreater(sum_, max_)) {
            remainingHp = max_;
            r_.affect(Rate.minus(max_, remainingHp));
        } else {
            remainingHp.addNb(_variation);
            r_.affect(_variation);
        }
        return r_;
    }

    void fullHealMessage(DataBase _import, TransientFight _transientFight) {
        String name_ = _import.translatePokemon(name);
        StringMap<String> mess_ = _import.getMessagesFighter();
        _transientFight.addMessage(mess_.getVal(FULL_HEAL), name_);
    }

    void fullHeal() {
        remainingHp=pvMax();
        for(String c:status.getKeys()){
            supprimerStatut(c);
        }
        for(MoveTeamPosition c:statusRelat.getKeys()){
            statusRelat.put(c,0L);
        }
        for(String c:currentMoves.getKeys()){
            currentMoves.getVal(c).fullHeal();
        }
        for(String c:moves.getKeys()){
            moves.getVal(c).fullHeal();
        }
    }

    void activerAttaqueFinTourIndividuel(String _attaque){
        enabledMovesEndRound.getVal(_attaque).enable();
    }

    void desactiverAttaqueFinTourIndividuel(String _attaque){
        enabledMovesEndRound.getVal(_attaque).disable();
        enabledMovesEndRound.getVal(_attaque).reset();
    }

    void affecterBaseStatistique(Statistic _statistique,Rate _valeur){
        statisBase.put(_statistique, _valeur);
    }

    void apprendreAttaqueEcrasant(String _nouvelleAttaque,String _ancienneAttaque,DataBase _import){
        long pp_=_import.ppCopiedMove(_ancienneAttaque);
        if (!StringUtil.contains(_import.getMovesCopyingTemp(), _ancienneAttaque)) {
            return;
        }
        copiedMoves.put(_ancienneAttaque,new CopiedMove(_nouvelleAttaque,pp_));
    }

    void apprendreAttaqueEcrasantDef(String _nouvelleAttaque,String _ancienneAttaque,DataBase _import){
        long pp_=_import.getMove(_nouvelleAttaque).getPp();
        StringList listIntersectMoves_;
        listIntersectMoves_ = StringUtil.intersect(moves.getKeys(),currentMoves.getKeys());
        if (!StringUtil.contains(listIntersectMoves_, _ancienneAttaque)) {
            return;
        }
        //currentMoves.contains(_ancienneAttaque) && moves.contains(_ancienneAttaque)
        if (!changed) {
            moves.removeKey(_ancienneAttaque);
            moves.put(_nouvelleAttaque,new UsesOfMove(pp_));
        }
        currentMoves.removeKey(_ancienneAttaque);
        currentMoves.put(_nouvelleAttaque,new UsesOfMove(pp_));
    }

    void transformer(Fighter _creature,long _pp){
        currentName=_creature.getCurrentName();
        weight.affect(_creature.getWeight());
        height.affect(_creature.getHeight());
        types=new StringList(_creature.getTypes());
        currentGender=_creature.getCurrentGender();
        currentMoves.clear();
        for(String c:_creature.getCurrentMovesSet()){
            currentMoves.put(c,new UsesOfMove(_pp));
        }
        IdMap<Statistic,Rate> statBase_=_creature.getStatisBase();
        for(Statistic c:statBase_.getKeys()){
            if(c == Statistic.HP){
                continue;
            }
            statisBase.put(c, statBase_.getVal(c));
        }
        for(Statistic c:_creature.getStatisBoost().getKeys()){
            statisBoost.put(c, _creature.getStatisBoost().getVal(c));
        }
        currentAbility=_creature.getCurrentAbility();
        changed=true;
    }

    void formeNormale(DataBase _import){
        currentName=name;
        PokemonData fPk_=fichePokemon(_import);
        weight.affect(fPk_.getWeight());
        height.affect(fPk_.getHeight());
        types.clear();
        types.addAllElts(fPk_.getTypes());
        currentGender=gender;
        if(changed){
            currentMoves.clear();
            for (String m: moves.getKeys()) {
                UsesOfMove pp_ = moves.getVal(m);
                currentMoves.put(m, new UsesOfMove(pp_.getCurrent(),pp_.getMax()));
            }
        }
        IdMap<Statistic,StatBaseEv> statEv_=fPk_.getStatistics();
        for(Statistic c:statEv_.getKeys()){
            statisBase.put(c, new Rate(statEv_.getVal(c).getBase()));
        }
        for(Statistic c:statisBoost.getKeys()){
            statisBoost.put(c, _import.getDefaultBoost());
        }
        changed=false;
        clone.affectZero();
        for (String s: enabledMoves.getKeys()) {
            desactiverAttaque(s);
        }
        for (String s: enabledMovesProt.getKeys()) {
            desactiverAttaqueImmu(s, _import);
        }
        for (String s: enabledMovesUnprot.getKeys()) {
            desactiverAttaqueAntiImmu(s);
        }
        for (String s: enabledMovesConstChoices.getKeys()) {
            desactiverAttaqueBlocantLanceur(s);
        }
        for (String s: enabledChangingTypesMoves.getKeys()) {
            disableChangingMovesTypes(s);
        }
        for (String s: enabledCounteringMoves.getKeys()) {
            disableCounteringMoves(s);
        }
        for (String s: enabledMovesEndRound.getKeys()) {
            desactiverAttaqueFinTourIndividuel(s);
        }
    }

    void creerClone(Rate _taux){
        Rate pvClone_=Rate.multiply(pvMax(), _taux);
        if (!Rate.strGreater(remainingHp, pvClone_)) {
            return;
        }
        clone.affect(pvClone_);
        remainingHp.removeNb(pvClone_);
    }

    void infligerDegatsClone(Rate _taux){
        if (Rate.strGreater(_taux, clone)){
            clone.affectZero();
        }else{
            clone.removeNb(_taux);
        }
    }

    void ajouterAttaquesDejaInvoqueesTour(String _attaque){
        alreadyInvokedMovesRound.add(_attaque);
    }

    void toutSupprimerAttaquesDejaInvoqueesTour(){
        alreadyInvokedMovesRound.clear();
    }

    void affectNoUsesMove(){
        nbRepeatingSuccessfulMoves.affectZero();
    }

    void incrementConsecutiveUsesMove(){
        nbRepeatingSuccessfulMoves.increment();
    }

    void affectNoRoundBeforeUsingMove(){
        nbPrepaRound=0;
    }

    void incrementRoundBeforeUsingMove(){
        nbPrepaRound++;
    }


    void calculateNewLevel(Difficulty _diff, DataBase _import, StringList _pkNamesBegin, TransientFight _te){
        String name_ = _import.translatePokemon(name);
        LevelExpPoints levelWonPoints_ = newLevelWonPoints(_import);
        long achievedLevel_ = levelWonPoints_.getLevel();
        Rate sommeDiffNiveaux_ = levelWonPoints_.getExpPoints();
        long maxNiveau_=_import.getMaxLevel();
        if (achievedLevel_ != maxNiveau_) {
            changeWonPoints(achievedLevel_, sommeDiffNiveaux_, _import);
        } else {
            wonExp.affectZero();
            wonExpSinceLastLevel.affectZero();
        }
        StringMap<BoolVal> newMoves_ = newMoves(achievedLevel_, _diff, _import);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=new StringList();
        StringList attaquesApprendre_=new StringList();
        updateMoveList(newMoves_, attaquesConnues_, attaquesApprendre_);
        initLearntMoves(attaquesApprendre_, attaquesConnues_, _import);
        long monteNiveau_= achievedLevel_-level;
        setLevel(achievedLevel_);
        learnMoves(attaquesApprendre_, _import,_te);
        attaquesConnues_.clear();
        attaquesConnues_.addAllElts(moves.getKeys());
        StringMap<String> mess_ = _import.getMessagesFighter();
        if(monteNiveau_>0){
            _te.addMessage(mess_.getVal(GROW_LEVEL), name_, Long.toString(achievedLevel_));
            winHappinessByGrowingLevel(monteNiveau_,_import,_te);
            formeNormale(_import);
            fullHeal();
            fullHealMessage(_import,_te);
            //groundPlace = groundPlaceSubst;
        }
        proponeMovesAbilitiesForEvolutions(attaquesApprendre_, attaquesConnues_, _import, _pkNamesBegin);
    }

    public static void updateMoveList(StringMap<BoolVal> _newMoves, StringList _attaquesConnues, StringList _attaquesApprendre) {
        for (EntryCust<String,BoolVal> e: _newMoves.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                _attaquesApprendre.add(e.getKey());
            } else {
                _attaquesConnues.add(e.getKey());
            }
        }
    }

    LevelExpPoints newLevelWonPoints(DataBase _import) {
        return newLevelWonPoints(_import, name, level, wonExp, wonExpSinceLastLevel);
    }

    static LevelExpPoints newLevelWonPoints(DataBase _import, String _name, long _level, Rate _wonExp, Rate _wonExpSinceLastLevel) {
        long niveauTmp_= _level;
        niveauTmp_++;
        Rate sommeDiffNiveaux_= numberNecessaryPointsForGrowingLevel(_name, niveauTmp_, _import);
        niveauTmp_--;
        long maxNiveau_=_import.getMaxLevel();
        while(!Rate.strLower(Rate.plus(_wonExp, _wonExpSinceLastLevel), sommeDiffNiveaux_)){
            niveauTmp_++;
            if (niveauTmp_ >= maxNiveau_) {
                if (niveauTmp_ > maxNiveau_) {
                    niveauTmp_--;
                }
                break;
            }
            sommeDiffNiveaux_.addNb(numberNecessaryPointsForGrowingLevel(_name, niveauTmp_ + 1L, _import));
        }
        return new LevelExpPoints(niveauTmp_,sommeDiffNiveaux_);
    }

    void changeWonPoints(long _niveauTmp,Rate _sommeDiffNiveaux, DataBase _import) {
        changeWonPoints(_niveauTmp, _sommeDiffNiveaux, _import, name, wonExp, wonExpSinceLastLevel);
    }

    static void changeWonPoints(long _niveauTmp, Rate _sommeDiffNiveaux, DataBase _import, String _name, Rate _wonExp, Rate _wonExpSinceLastLevel) {
        long maxNiveau_=_import.getMaxLevel();
        if(NumberUtil.eq(_niveauTmp,maxNiveau_)){
            //cas wonExp+wonExpSinceLastLevel>=sommeDiffNiveaux_:
            //==> wonExp+wonExpSinceLastLevel-sommeDiffNiveaux_>=0
            //==> apres affectation wonExp>=0
            //wonExp+wonExpSinceLastLevel-sommeDiffNiveaux_>=0
            _wonExp.addNb(Rate.minus(_wonExpSinceLastLevel, _sommeDiffNiveaux));
            _wonExpSinceLastLevel.affectZero();
        }else{
            //cas niveauTmp_ ne change pas:
            //wonExp>=0 et sommeDiffNiveaux_==nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)
            //==> wonExp-sommeDiffNiveaux_+nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)>=0
            //cas niveauTmp_ change:
            //wonExpSinceLastLevel+wonExp<sommeDiffNiveaux_(n_i,niveauTmp_) fin
            //==> wonExpSinceLastLevel+wonExp>=sommeDiffNiveaux_(n_i,niveauTmp_-1)
            //==> wonExpSinceLastLevel+wonExp-sommeDiffNiveaux_(n_i,niveauTmp_)>=sommeDiffNiveaux_(n_i,niveauTmp_-1)-sommeDiffNiveaux_(n_i,niveauTmp_)
            //==> wonExpSinceLastLevel+wonExp-sommeDiffNiveaux_(n_i,niveauTmp_)>=-nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)
            //==> wonExpSinceLastLevel+wonExp-sommeDiffNiveaux_(n_i,niveauTmp_)+nombrePointsExpNecessPourMonterNiveauDepart(niveauTmp_)>=0
            //==> apres affectation wonExpSinceLastLevel>=0
            _wonExpSinceLastLevel.addNb(Rate.minus(_wonExp, _sommeDiffNiveaux));
            _wonExpSinceLastLevel.addNb(numberNecessaryPointsForGrowingLevel(_name, _niveauTmp + 1L, _import));
            _wonExp.affectZero();
        }
    }

    StringMap<BoolVal> newMoves(long _niveauTmp, Difficulty _diff, DataBase _import) {
        StringMap<BoolVal> newMoves_ = new StringMap<BoolVal>();
        StringList attaquesConnues_=new StringList(moves.getKeys());
        PokemonData fPk_=fichePokemon(_import);
        for(LevelMove nivAtt_: fPk_.getLevMoves()){
            if (StringUtil.contains(attaquesConnues_, nivAtt_.getMove()) || nivAtt_.getLevel() > _niveauTmp || nivAtt_.getLevel() < level || _diff.isSkipLearningMovesWhileNotGrowingLevel() && NumberUtil.eq(nivAtt_.getLevel(), level)) {
                continue;
            }
            newMoves_.put(nivAtt_.getMove(), BoolVal.TRUE);
        }
        for (String m : attaquesConnues_) {
            newMoves_.put(m, BoolVal.FALSE);
        }
        return newMoves_;
    }

    void initLearntMoves(StringList _attaquesApprendre,
            StringList _attaquesConnues,
            DataBase _import) {
        if(_attaquesApprendre.size()+_attaquesConnues.size()>_import.getNbMaxMoves()){
            movesToBeLearnt=new StringList(_attaquesApprendre);
        } else {
            movesToBeLearnt.clear();
        }
    }

    void learnMoves(StringList _attaquesApprendre,
                    DataBase _import, TransientFight _te) {
        if (!movesToBeLearnt.isEmpty()) {
            return;
        }
        for(String e:_attaquesApprendre){
            long pp_=_import.getMove(e).getPp();
            moves.put(e,new UsesOfMove(pp_));
        }
        if (_attaquesApprendre.size() + currentMoves.size() <= _import.getNbMaxMoves()) {
            for(String e:_attaquesApprendre){
                long pp_=_import.getMove(e).getPp();
                currentMoves.put(e,new UsesOfMove(pp_));
            }
        }
        //whatever currentMoves.size <= _import.getNbMaxMoves()
        String name_ = _import.translatePokemon(name);
        StringMap<String> mess_ = _import.getMessagesFighter();
        for (String m: _attaquesApprendre) {
            String move_ = _import.translateMove(m);
            _te.addMessage(mess_.getVal(LEARN_MOVE), name_, move_);
        }
    }

    void proponeMovesAbilitiesForEvolutions(StringList _attaquesApprendre,
            StringList _attaquesConnues,
            DataBase _import,StringList _pkNamesBegin) {
        for(String e:nomEvolutions(_import,_pkNamesBegin)){
            PokemonData fPkEvo_=_import.getPokemon(e);
            StringList attaquesApprendreEvos_=new StringList();
            for (String m : _attaquesApprendre) {
                if (StringUtil.contains(_attaquesConnues, m)) {
                    continue;
                }
                attaquesApprendreEvos_.add(m);
            }
            for(LevelMove l: fPkEvo_.getLevMoves()){
                if(StringUtil.contains(_attaquesConnues, l.getMove())){
                    continue;
                }
                if (l.getLevel() <= level) {
                    attaquesApprendreEvos_.add(l.getMove());
                }
            }
            attaquesApprendreEvos_.removeDuplicates();
            StringList capacites_=new StringList(fPkEvo_.getAbilities());
            capacites_.sort();
            movesAbilitiesEvos.put(e,new MovesAbilities(attaquesApprendreEvos_,capacites_));
        }
    }

    Comment variationGainExperienceMessage(Rate _variation, DataBase _import) {
        Comment c_ = new Comment();
        String name_ = _import.translatePokemon(name);
        StringMap<String> mess_ = _import.getMessagesFighter();
        c_.addMessage(mess_.getVal(WON_EXP), name_, _variation.toNumberString());
        return c_;
    }

    void variationGainExperience(Rate _variation) {
        wonExp.addNb(_variation);
    }

    Comment wonEvStatistic(Statistic _statistique,long _varEv,long _maxEv, DataBase _import){
        Comment c_ = new Comment();
        long ev_=ev.getVal(_statistique);
        String name_ = _import.translatePokemon(name);
        String stat_ = _import.translateStatistics(_statistique);
        StringMap<String> mess_ = _import.getMessagesFighter();
        if(ev_+_varEv<_maxEv){
            ev_ += _varEv;
            c_.addMessage(mess_.getVal(WON_EV), name_, Long.toString(_varEv), stat_);
        }else{
            ev_=_maxEv;
            c_.addMessage(mess_.getVal(WON_EV_MAX), name_, stat_);
        }
        ev.put(_statistique, ev_);
        return c_;
    }

    void winHappinessByGrowingLevel(long _diffNiv, DataBase _import, TransientFight _te){
        Rate mult_=DataBase.defRateProduct();
        ItemForBattle objet_ = dataExpObject(_import);
        if (objet_ != null && !objet_.getMultWinningHappiness().isZero()) {
            mult_.multiplyBy(objet_.getMultWinningHappiness());
        }
        mult_.multiplyBy(_import.getWonHappinessByGrowLevel());
        mult_.multiplyBy(new Rate(_diffNiv));
        long maxBonheur_=_import.getHappinessMax();
        String name_ = _import.translatePokemon(name);
        StringMap<String> mess_ = _import.getMessagesFighter();
        if(happiness+mult_.ll()<=maxBonheur_){
            happiness += mult_.ll();
            _te.addMessage(mess_.getVal(WON_HAPPINESS), name_, mult_.toNumberString());
        }else{
            happiness=maxBonheur_;
            _te.addMessage(mess_.getVal(MAX_HAPPINESS), name_);
        }
    }

    void reinitAttaquesEvosCapacites(){
        movesToBeLearnt.clear();
        movesAbilitiesEvos.clear();
    }

    void learnMovesWithoutEvolving(StringList _attaquesRetenues,DataBase _import){
        StringList attaquesRemplacees_ = new StringList();
        StringList attaquesRemplacant_ = new StringList();
        for(String c:moves.getKeys()){
            if(StringUtil.contains(_attaquesRetenues, c)){
                continue;
            }
            attaquesRemplacees_.add(c);
        }
        for(String e:_attaquesRetenues){
            if(moves.contains(e)){
                continue;
            }
            attaquesRemplacant_.add(e);
        }
        for(String e:attaquesRemplacees_){
            moves.removeKey(e);
        }
        currentMoves.clear();
        for(String e:attaquesRemplacant_){
            long pp_=_import.getMove(e).getPp();
            moves.put(e,new UsesOfMove(pp_));
        }
        currentMoves.putAllMap(moves);
    }

    void evoluer(String _nomEvo, String _capacite, StringList _attaquesRetenues, DataBase _import, Fight _fight){
        evoluerSansApprendreCapacite(_nomEvo,_attaquesRetenues,_import, _fight);
        evoluerSansApprendreAttaque(_nomEvo,_capacite,_import,_fight);
    }

    void evoluerSansApprendreCapacite(String _nomEvo, StringList _attaquesRetenues, DataBase _import, Fight _fight){
        learnMovesWithoutEvolving(_attaquesRetenues,_import);
        evoluerSansApprendreAttaqueCapacite(_nomEvo,_import,_fight);
    }

    void evoluerSansApprendreAttaqueCapacite(String _nomEvo, DataBase _import, Fight _fight){
        PokemonData fPk_=_import.getPokemon(_nomEvo);
        evoluerSansApprendreAttaque(_nomEvo,fPk_.getAbilities().first(),_import, _fight);
    }

    void evoluerSansApprendreAttaque(String _nomEvo, String _capacite, DataBase _import, Fight _fight){
        PokemonData fPk_=_import.getPokemon(_nomEvo);
        for(Statistic c:statisBase.getKeys()){
            statisBase.put(c, new Rate(fPk_.getStatistics().getVal(c).getBase()));
        }
        types=new StringList(fPk_.getTypes());
        ability=_capacite;
        currentAbility=ability;
        weight.affect(fPk_.getWeight());
        height.affect(fPk_.getHeight());
        name=_nomEvo;
        currentName=name;
        fullHeal();
        fullHealMessage(_import,_fight.getTemp());
    }

    void useObject(){
        usingItem=true;
    }

    void restoreLastObject(){
        if (!item.isEmpty()) {
            return;
        }
        if(lastUsedItem.isEmpty()){
            return;
        }
        item=lastUsedItem;
    }

    void tossLastUsedObject(){
        if(usingItem){
            lastUsedItem=item;
            item=DataBase.EMPTY_STRING;
        }
    }

    void setFirstChosenMoveTarget(String _attaque,TargetCoords _cible){
        ActionMove action_ = new ActionMove();
        action_.setChosenTargets(TargetCoordsList.newList(_cible));
        action_.setSubstitute(BACK);
        action_.setFirstChosenMove(_attaque);
        action_.setFinalChosenMove(DataBase.EMPTY_STRING);
        action = action_;
    }

    void setFirstChosenMoveTargetSubstitute(String _attaque, TargetCoords _cible, int _remplacant){
        ActionMove action_ = new ActionMove();
        action_.setChosenTargets(TargetCoordsList.newList(_cible));
        action_.setSubstitute(_remplacant);
        action_.setFirstChosenMove(_attaque);
        action_.setFinalChosenMove(DataBase.EMPTY_STRING);
        action = action_;
    }

    void setFirstChosenMove(String _attaque){
        ActionMove action_ = new ActionMove();
        action_.setChosenTargets(new TargetCoordsList());
        action_.setFirstChosenMove(_attaque);
        action_.setFinalChosenMove(DataBase.EMPTY_STRING);
        action_.setSubstitute(Fighter.BACK);
        action = action_;
    }

    void setChosenHealingObject(String _objet,DataBase _import){
        ActionHeal action_ = new ActionSimpleHeal();
        Item objet_=_import.getItem(_objet);
        if(objet_ instanceof HealingItem){
            HealingItem soin_=(HealingItem)objet_;
            if(soin_.getHealingTeam()){
                action_.setTeam(true);
            }
        }
        action_.setChosenHealingItem(_objet);
        action = action_;
    }

    void setChosenHealingObjectMove(String _objet,String _attaque){
        ActionHealMove action_ = new ActionHealMove();
        action_.setChosenHealingItem(_objet);
        action_.setFirstChosenMove(_attaque);
        action = action_;
    }

    void cancelActions(){
        action = null;
    }

    void setLastUsedMove(){
        if (action instanceof ActionMove) {
            usedMoveLastRound=((ActionMove)action).getFirstChosenMove();
        } else {
            usedMoveLastRound= DataBase.EMPTY_STRING;
        }
    }

    void choisirAttaqueFin(){
        if (action instanceof ActionMove) {
            ((ActionMove)action).setFinalChosenMove(((ActionMove)action).getFirstChosenMove());
        }
    }

    void reinitEffetTour(){
        for(String e:enabledMovesForAlly.getKeys()){
            enabledMovesForAlly.put(e, BoolVal.FALSE);
        }
    }

    void incrementRoundsStatus(String _nomStatut) {
        status.put(_nomStatut, status.getVal(_nomStatut)+1);
    }

    void cancelSubstituteForMove() {
        setSubstituteForMove(BACK);
    }
    void setSubstituteForMove(int _remplacant) {
        if (action instanceof ChosenReplacing) {
            ((ChosenReplacing)action).setSubstitute(_remplacant);
        }
    }

    void setSubstitute(int _remplacant) {
        ActionSwitch action_ = new ActionSwitch();
        action_.setSubstitute(_remplacant);
        action = action_;
    }

    static Rate statistiqueGlobale(IdMap<Statistic,Rate> _statistiquesBase,Statistic _nomStat,long _ev,long _iv, long _level){
        return PokemonData.stat(_level, _statistiquesBase.getVal(_nomStat), _nomStat, _ev, _iv);
    }

    Rate statistiqueGlobaleEvIv(Statistic _nomStat){
        return statistiqueGlobale(statisBase,_nomStat,ev.getVal(_nomStat),iv.getVal(_nomStat), level);
    }

    public Rate pvMax(){
        return statistiqueGlobaleEvIv(Statistic.HP);
    }
    boolean estAssexue(){
        return currentGender==Gender.NO_GENDER;
    }

    boolean canDisableWeather(DataBase _import) {
        if(estArriere()){
            return false;
        }
        AbilityData fCapac_=ficheCapaciteActuelle(_import);
        if (fCapac_ == null) {
            return false;
        }
        if(!fCapac_.enabledSending()){
            return false;
        }
        EffectWhileSendingWithStatistic effetEnvoi_=fCapac_.getEffectSending().first();
        return effetEnvoi_.getDisableWeather();
    }

    boolean noPowerPointForLastUsedMove() {
        return usedMoveLastRound.isEmpty() || !StringUtil.contains(attaquesUtilisables(), usedMoveLastRound) || powerPointsMove(usedMoveLastRound) == 0;
    }

    StringList resistingTypes(DataBase _import) {
        StringList typesResistantsDerniereAttaqueSubie_ =new StringList();
        if(!lastSufferedMoveTypes.isEmpty()){
            for(String e:_import.getTypes()){
                Rate eff_ = DataBase.defRateProduct();
                for (String t: lastSufferedMoveTypes) {
                    eff_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(t,e)));
                }
                if (eff_.lowerThanOne()) {
                    typesResistantsDerniereAttaqueSubie_.add(e);
                }
            }
            for(String e: types){
                typesResistantsDerniereAttaqueSubie_.removeString(e);
            }
        }
        return typesResistantsDerniereAttaqueSubie_;
    }

    boolean spendPowerPoint(String _move, DataBase _import) {
        MoveData fAtt_ = _import.getMove(_move);
        if(!fAtt_.getConstUserChoice()){
            return true;
        }
        if(fAtt_.getRepeatRoundLaw().events().isEmpty()) {
            return true;
        }
        return !StringUtil.quickEq(usedMoveLastRound, _move);
    }
    long varPrio(TeamPosition _fighter, String _move, Fight _fight,DataBase _data) {
        long varPrio_=0;
        MoveData fAtt_=_data.getMove(_move);
        String categOne_ = _data.getCategory(fAtt_);
        AbilityData fCapac_=ficheCapaciteActuelle(_data);
        if(fCapac_ != null){
            if(fCapac_.getIncreasedPrio().contains(categOne_)){
                varPrio_+=fCapac_.getIncreasedPrio().getVal(categOne_);
            }
            for (String type_: FightMoves.moveTypes(_fight, _fighter, _move, _data)) {
                if (fCapac_.getIncreasedPrioTypes().contains(type_)) {
                    varPrio_ += fCapac_.getIncreasedPrioTypes().getVal(type_);
                }
            }
        }
        return varPrio_;
    }
    boolean isSlowing(DataBase _data) {
        boolean slowOne_=false;
        AbilityData fCapacOne_=ficheCapaciteActuelle(_data);
        if(fCapacOne_ != null){
            slowOne_=fCapacOne_.isSlowing();
        }
        return slowOne_;
    }

    AbilityData ficheCapaciteActuelle(DataBase _import){
        return _import.getAbility(currentAbility);
    }

    AbilityData ficheCapacite(DataBase _import){
        return _import.getSafeAbility(ability);
    }

    PokemonData fichePokemonActuelle(DataBase _import){
        return _import.getPokemon(currentName);
    }

    PokemonData fichePokemon(DataBase _import){
        return _import.getPokemon(name);
    }

    StringList enabledIndividualMoves() {
        StringList list_ = new StringList();
        for(String m:enabledMoves.getKeys()){
            if(!enabledMoves.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    StringList enabledIndividualAntiImmuMoves() {
        StringList list_ = new StringList();
        for(String m:enabledMovesUnprot.getKeys()){
            if(!enabledMovesUnprot.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    CustList<MoveTeamPosition> enabledRelationsMoves() {
        CustList<MoveTeamPosition> list_ = new CustList<MoveTeamPosition>();
        for(MoveTeamPosition m:trackingMoves.getKeys()){
            if(!trackingMoves.getVal(m).getActivity().isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    MoveTeamPositionsActivityOfMove enabledRelationsTraps() {
        MoveTeamPositionsActivityOfMove list_ = new MoveTeamPositionsActivityOfMove();
        for(EntryCust<MoveTeamPosition, ActivityOfMove> m:trappingMoves.entryList()){
            if(!m.getValue().isEnabled()){
                continue;
            }
            list_.addEntry(m.getKey(),m.getValue());
        }
        return list_;
    }

    AffectedMove refPartAttaquesSurCombatAtt(MoveTeamPosition _c) {
        return trackingMoves.getVal(_c);
    }

    NatStringTreeMap<BoolVal> getMoves(String _evo) {
        NatStringTreeMap<BoolVal> map_ = new NatStringTreeMap<BoolVal>();
        for (String m: moves.getKeys()) {
            map_.put(m, BoolVal.TRUE);
        }
        if (!_evo.isEmpty()) {
            for (String m: movesAbilitiesEvos.getVal(_evo).getMoves()) {
                map_.put(m, BoolVal.FALSE);
            }
        } else {
            for (String m: movesToBeLearnt) {
                map_.put(m, BoolVal.FALSE);
            }
        }
        return map_;
    }

    StringList getAbilities(String _evo) {
        StringList abilities_ = new StringList();
        if (!_evo.isEmpty()) {
            abilities_.addAllElts(movesAbilitiesEvos.getVal(_evo).getAbilities());
        } else {
            abilities_.add(ability);
        }
        return abilities_;
    }

    public String getFirstChosenMove() {
        if (action instanceof ChosenMove) {
            return ((ChosenMove)action).getFirstChosenMove();
        }
        return DataBase.EMPTY_STRING;
    }

    String getChosenHealingItem() {
        if (action instanceof ActionHeal) {
            return ((ActionHeal)action).getChosenHealingItem();
        }
        return DataBase.EMPTY_STRING;
    }

    String getFinalChosenMove() {
        if (action instanceof ActionMove) {
            return ((ActionMove)action).getFinalChosenMove();
        }
        return DataBase.EMPTY_STRING;
    }

    public TargetCoordsList getChosenTargets() {
        if (action instanceof ActionMove) {
            return ((ActionMove)action).getChosenTargets();
        }
        return new TargetCoordsList();
    }

    int getSubstistute() {
        if (action instanceof ChosenReplacing) {
            return ((ChosenReplacing)action).getSubstitute();
        }
        return BACK;
    }

    public void patch(DataBase _data) {
        AbstractAction action_ = getAction();
        if (!(action_ instanceof ActionHeal) && estKo()) {
            cancelActions();
        }
        if (action_ instanceof ActionMove) {
            TargetCoordsList targets_ = ((ActionMove) action_).getChosenTargets();
            if (targets_ == null) {
                targets_ = new TargetCoordsList();
            }
            TargetCoordsList filter_ = new TargetCoordsList();
            for (TargetCoords t: targets_) {
                if (!TargetCoords.koTeam(t.getTeam())) {
                    filter_.add(t);
                }
            }
            ((ActionMove) action_).setChosenTargets(filter_);
            String move_ = getFirstChosenMove();
            MoveData fAtt_ = _data.getMove(move_);
            if (fAtt_ == null) {
                cancelActions();
                return;
            }
            if (!fAtt_.getTargetChoice().isWithChoice()) {
                getChosenTargets().clear();
            }
        }
    }

    /**Method for test*/
    public void setRemainedHp(Rate _hp) {
        remainingHp.affect(_hp);
    }

    boolean isKoAt(int _index) {
        return getGroundPlaceSubst() == _index && estKo();
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public Rate getWeight() {
        return weight;
    }

    public void setWeight(Rate _weight) {
        weight = _weight;
    }

    public Rate getHeight() {
        return height;
    }

    public void setHeight(Rate _height) {
        height = _height;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String _currentName) {
        currentName = _currentName;
    }

    public Gender getCurrentGender() {
        return currentGender;
    }

    public void setCurrentGender(Gender _currentGender) {
        currentGender = _currentGender;
    }

    public String getLastUsedItem() {
        return lastUsedItem;
    }

    public void setLastUsedItem(String _lastUsedItem) {
        lastUsedItem = _lastUsedItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

    public String getExpItem() {
        return expItem;
    }

    public void setExpItem(String _expItem) {
        expItem = _expItem;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public String getCurrentAbility() {
        return currentAbility;
    }

    public void setCurrentAbility(String _currentAbility) {
        currentAbility = _currentAbility;
    }

    public long getStatusNbRoundShort(String _status) {
        return status.getVal(_status);
    }

    public Long getStatusNbRound(String _status) {
        return status.getVal(_status);
    }

    int getNbStatusByRounds(int _nbRounds) {
        int i_ = IndexConstants.SIZE_EMPTY;
        for (EntryCust<String, Long> e: status.entryList()) {
            if (NumberUtil.eq(e.getValue(), _nbRounds)) {
                i_++;
            }
        }
        return i_;
    }

    public StringMap<Long> getStatus() {
        return status;
    }

    public CustList<String> getStatusSet() {
        return status.getKeys();
    }

    public boolean isSingleStatus(String _status) {
        return status.contains(_status);
    }

    public void setStatus(StringMap<Long> _status) {
        status = _status;
    }

    public long getStatusRelatNbRoundShort(MoveTeamPosition _status) {
        return statusRelat.getVal(_status);
    }

    public Long getStatusRelatNbRound(MoveTeamPosition _status) {
        return statusRelat.getVal(_status);
    }

    public boolean isStatusRelat(MoveTeamPosition _status) {
        return statusRelat.contains(_status);
    }

    public MoveTeamPositionsShort getStatusRelat() {
        return statusRelat;
    }

    public CustList<MoveTeamPosition> getStatusRelatSet() {
        return statusRelat.getKeys();
    }
    public Longs getStatusRelatValues() {
        return new Longs(statusRelat.values());
    }

    public void setStatusRelat(MoveTeamPositionsShort _statusRelat) {
        statusRelat = _statusRelat;
    }

    public LgInt getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(LgInt _nbRounds) {
        nbRounds = _nbRounds;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public UsesOfMove getMove(String _move) {
        return moves.getVal(_move);
    }

    int nbMoves() {
        return moves.size();
    }

    public CustList<String> getMovesSet() {
        return moves.getKeys();
    }

    public StringMap<UsesOfMove> getMoves() {
        return moves;
    }

    public void setMoves(StringMap<UsesOfMove> _moves) {
        moves = _moves;
    }

    public UsesOfMove getCurrentMove(String _move) {
        return currentMoves.getVal(_move);
    }

    public CustList<String> getCurrentMovesSet() {
        return currentMoves.getKeys();
    }

    public StringMap<UsesOfMove> getCurrentMoves() {
        return currentMoves;
    }

    public void setCurrentMoves(StringMap<UsesOfMove> _currentMoves) {
        currentMoves = _currentMoves;
    }

    public IdMap<Statistic,Long> getEv() {
        return ev;
    }

    public void setEv(IdMap<Statistic,Long> _ev) {
        ev = _ev;
    }

    public IdMap<Statistic,Long> getIv() {
        return iv;
    }

    public void setIv(IdMap<Statistic,Long> _iv) {
        iv = _iv;
    }

    public IdMap<Statistic,Rate> getStatisBase() {
        return statisBase;
    }

    public void setStatisBase(IdMap<Statistic,Rate> _statisBase) {
        statisBase = _statisBase;
    }

    public IdMap<Statistic,Long> getStatisBoost() {
        return statisBoost;
    }

    public void setStatisBoost(IdMap<Statistic,Long> _statisBoost) {
        statisBoost = _statisBoost;
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    /**Method for initialization*/
    public void setRemainingHp(Rate _remainingHp) {
        remainingHp = _remainingHp;
    }

    public Rate getClone() {
        return clone;
    }

    public void setClone(Rate _clone) {
        clone = _clone;
    }

    public StringMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public void setEnabledMoves(StringMap<ActivityOfMove> _enabledMoves) {
        enabledMoves = _enabledMoves;
    }

    public StringMap<ActivityOfMove> getEnabledMovesProt() {
        return enabledMovesProt;
    }

    public void setEnabledMovesProt(
            StringMap<ActivityOfMove> _attaquesActivesImmu) {
        enabledMovesProt = _attaquesActivesImmu;
    }

    public StringList getProtectedAgainstMoveTypes() {
        return protectedAgainstMoveTypes;
    }

    public void setProtectedAgainstMoveTypes(StringList _protectedAgainstMoveTypes) {
        protectedAgainstMoveTypes = _protectedAgainstMoveTypes;
    }

    public StringMap<ActivityOfMove> getEnabledMovesUnprot() {
        return enabledMovesUnprot;
    }

    public void setEnabledMovesUnprot(
            StringMap<ActivityOfMove> _attaquesActivesAntiImmu) {
        enabledMovesUnprot = _attaquesActivesAntiImmu;
    }

    public StringMap<ActivityOfMove> getEnabledMovesEndRound() {
        return enabledMovesEndRound;
    }

    public void setEnabledMovesEndRound(
            StringMap<ActivityOfMove> _attaquesActivesFinTourIndividuel) {
        enabledMovesEndRound = _attaquesActivesFinTourIndividuel;
    }

    public StringMap<ActivityOfMove> getEnabledMovesConstChoices() {
        return enabledMovesConstChoices;
    }

    public void setEnabledMovesConstChoices(
            StringMap<ActivityOfMove> _attaquesActivesBlocantLanceur) {
        enabledMovesConstChoices = _attaquesActivesBlocantLanceur;
    }

    public StringMap<ActivityOfMove> getEnabledChangingTypesMoves() {
        return enabledChangingTypesMoves;
    }

    public void setEnabledChangingTypesMoves(
            StringMap<ActivityOfMove> _enabledChangingTypesMoves) {
        enabledChangingTypesMoves = _enabledChangingTypesMoves;
    }

    public StringMap<ActivityOfMove> getEnabledCounteringMoves() {
        return enabledCounteringMoves;
    }

    public void setEnabledCounteringMoves(
            StringMap<ActivityOfMove> _enabledCounteredMoves) {
        enabledCounteringMoves = _enabledCounteredMoves;
    }

    public StringMap<BoolVal> getEnabledMovesForAlly() {
        return enabledMovesForAlly;
    }

    public void setEnabledMovesForAlly(
            StringMap<BoolVal> _attaquesActivesPartenaireTour) {
        enabledMovesForAlly = _attaquesActivesPartenaireTour;
    }

    public StringMap<Rate> getDamageRateInflictedByType() {
        return damageRateInflictedByType;
    }

    public void setDamageRateInflictedByType(
            StringMap<Rate> _coeffDegatsInfligesFctType) {
        damageRateInflictedByType = _coeffDegatsInfligesFctType;
    }

    public StringMap<Rate> getDamageRateSufferedByType() {
        return damageRateSufferedByType;
    }

    public void setDamageRateSufferedByType(
            StringMap<Rate> _coeffDegatsSubisFctType) {
        damageRateSufferedByType = _coeffDegatsSubisFctType;
    }

    public boolean isActed() {
        return acted;
    }

    public void setActed(boolean _acted) {
        acted = _acted;
    }

    public int getGroundPlace() {
        return groundPlace;
    }

    public void setGroundPlace(int _groundPlace) {
        groundPlace = _groundPlace;
    }

    public int getGroundPlaceSubst() {
        return groundPlaceSubst;
    }

    public void setGroundPlaceSubst(int _groundPlaceSubst) {
        groundPlaceSubst = _groundPlaceSubst;
    }

    public Rate getWonExp() {
        return wonExp;
    }

    public void setWonExp(Rate _wonExp) {
        wonExp = _wonExp;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public void setWonExpSinceLastLevel(
            Rate _gainExperienceDepuisDernierNiveau) {
        wonExpSinceLastLevel = _gainExperienceDepuisDernierNiveau;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long _level) {
        level = _level;
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

    public MoveTeamPositionsBoolVal getIncrUserAccuracy() {
        return incrUserAccuracy;
    }

    public void setIncrUserAccuracy(
            MoveTeamPositionsBoolVal _precisionAccrueLanceur) {
        incrUserAccuracy = _precisionAccrueLanceur;
    }

    public StringMap<Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public void setNbUsesMoves(StringMap<Long> _nbUsesMoves) {
        nbUsesMoves = _nbUsesMoves;
    }

    public long getNbPrepaRound() {
        return nbPrepaRound;
    }

    public void setNbPrepaRound(long _nbPrepaRound) {
        nbPrepaRound = _nbPrepaRound;
    }

    public boolean isDisappeared() {
        return disappeared;
    }

    public void setDisappeared(boolean _disappeared) {
        disappeared = _disappeared;
    }

    public boolean isNeedingToRecharge() {
        return needingToRecharge;
    }

    public void setNeedingToRecharge(boolean _needingToRecharge) {
        needingToRecharge = _needingToRecharge;
    }

    public MoveTeamPositionsAffectedMove getTrackingMoves() {
        return trackingMoves;
    }

    public void setTrackingMoves(
            MoveTeamPositionsAffectedMove _attaquesSurCombatAtt) {
        trackingMoves = _attaquesSurCombatAtt;
    }

    public MoveTeamPositionsActivityOfMove getTrappingMoves() {
        return trappingMoves;
    }

    public void setTrappingMoves(
            MoveTeamPositionsActivityOfMove _attaquesPiegeantes) {
        trappingMoves = _attaquesPiegeantes;
    }

    public String getLastSufferedMove() {
        return lastSufferedMove;
    }

    public void setLastSufferedMove(String _lastSufferedMove) {
        lastSufferedMove = _lastSufferedMove;
    }

    public StringList getLastSufferedMoveTypes() {
        return lastSufferedMoveTypes;
    }

    public void setLastSufferedMoveTypes(StringList _lastSufferedMoveTypes) {
        lastSufferedMoveTypes = _lastSufferedMoveTypes;
    }

    public StringMap<Rate> getDamageSufferedCateg() {
        return damageSufferedCateg;
    }

    public void setDamageSufferedCateg(StringMap<Rate> _damageSufferedCateg) {
        damageSufferedCateg = _damageSufferedCateg;
    }

    public StringMap<Rate> getDamageSufferedCategRound() {
        return damageSufferedCategRound;
    }

    public void setDamageSufferedCategRound(
            StringMap<Rate> _degatsSubisCategoriesTour) {
        damageSufferedCategRound = _degatsSubisCategoriesTour;
    }

    public String getLastUsedMove() {
        return lastUsedMove;
    }

    public void setLastUsedMove(String _lastUsedMove) {
        lastUsedMove = _lastUsedMove;
    }

    public String getUsedMoveLastRound() {
        return usedMoveLastRound;
    }

    public void setUsedMoveLastRound(String _usedMoveLastRound) {
        usedMoveLastRound = _usedMoveLastRound;
    }

    public StringList getAlreadyInvokedMovesRound() {
        return alreadyInvokedMovesRound;
    }

    public void setAlreadyInvokedMovesRound(StringList _alreadyInvokedMovesRound) {
        alreadyInvokedMovesRound = _alreadyInvokedMovesRound;
    }

    public String getLastSuccessfulMove() {
        return lastSuccessfulMove;
    }

    public void setLastSuccessfulMove(String _lastSuccessfulMove) {
        lastSuccessfulMove = _lastSuccessfulMove;
    }

    public StringMap<CopiedMove> getCopiedMoves() {
        return copiedMoves;
    }

    public void setCopiedMoves(StringMap<CopiedMove> _copiedMoves) {
        copiedMoves = _copiedMoves;
    }

    public LgInt getNbRepeatingSuccessfulMoves() {
        return nbRepeatingSuccessfulMoves;
    }

    public void setNbRepeatingSuccessfulMoves(LgInt _nbRepeatingSuccessfulMoves) {
        nbRepeatingSuccessfulMoves = _nbRepeatingSuccessfulMoves;
    }

    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean _usingItem) {
        usingItem = _usingItem;
    }

    public boolean isSuccessfulMove() {
        return successfulMove;
    }

    public void setSuccessfulMove(boolean _successfulMove) {
        successfulMove = _successfulMove;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean _changed) {
        changed = _changed;
    }

    public StringMap<BoolVal> getEnabledImmuAbilities() {
        return enabledImmuAbilities;
    }

    public void setEnabledImmuAbilities(StringMap<BoolVal> _enabledImmuAbilities) {
        enabledImmuAbilities = _enabledImmuAbilities;
    }

    public MoveTeamPositionsStringList getPrivateMoves() {
        return privateMoves;
    }

    public void setPrivateMoves(MoveTeamPositionsStringList _privateMoves) {
        privateMoves = _privateMoves;
    }

    public boolean isBelongingToPlayer() {
        return belongingToPlayer;
    }

    public void setBelongingToPlayer(boolean _belongingToPlayer) {
        belongingToPlayer = _belongingToPlayer;
    }

    public AbstractAction getAction() {
        return action;
    }

    public void setAction(AbstractAction _action) {
        action = _action;
    }

    public StringList getMovesToBeLearnt() {
        return movesToBeLearnt;
    }

    public void setMovesToBeLearnt(StringList _movesToBeLearnt) {
        movesToBeLearnt = _movesToBeLearnt;
    }

    public StringMap<MovesAbilities> getMovesAbilitiesEvos() {
        return movesAbilitiesEvos;
    }

    public void setMovesAbilitiesEvos(
            StringMap<MovesAbilities> _evosAttaquesApprendreCapacites) {
        movesAbilitiesEvos = _evosAttaquesApprendreCapacites;
    }

}
