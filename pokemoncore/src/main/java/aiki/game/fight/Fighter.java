package aiki.game.fight;
import aiki.DataBase;
import aiki.Resources;
import aiki.comments.Comment;
import aiki.exceptions.GameLoadException;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCopyMove;
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
import aiki.game.fight.actions.Action;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.consts.Constants;

@RwXml
public final class Fighter {

    public static final String CIBLE_NB_UTILISATION = "CIBLE_NB_UTILISATION";
    public static final String LANCEUR_NB_UTILISATION = "LANCEUR_NB_UTILISATION";
    public static final String NIVEAU = "NIVEAU";
    public static final String FIGHTER_NB_UTILISATION = "FIGHTER_NB_UTILISATION";

//    public static final byte RATE_CENT = 100;

    public static final byte BACK = -100;

    private static StringMap<String> _messages_ = new StringMap<String>();

    private static final String FIGHTER = "aiki.game.fight.Fighter";

    private static final String WON_EV = "wonEv";

    private static final String WON_EV_MAX = "wonEvMax";

    private static final String WON_EXP = "wonExp";

    private static final String GROW_LEVEL = "growLevel";

    private static final String LEARN_MOVE = "learnMove";

    private static final String WON_HAPPINESS = "wonHappiness";

    private static final String MAX_HAPPINESS = "maxHappiness";

    private static final String FULL_HEAL = "fullHeal";

//    private static final String CENT = Byte.toString(RATE_CENT);

    /**Only evolutions can change the name*/
    @CheckedData
    private String name;

    /**Never mind*/
    @CheckedData
    private String nickname;

    /**Gender does not change in a fight*/
    @CheckedData
    private Gender gender;

    /**Never mind*/
    @CheckedData
    private Rate weight;

    /**Never mind*/
    @CheckedData
    private Rate height;

    /**Only evolutions and moves copy from fighter can change the current name; Never mind*/
    @CheckedData
    private String currentName;

    /**Only moves copy from fighter can change the current gender; Never mind*/
    @CheckedData
    private Gender currentGender;

    /**Only effect switch items (moves or abilities) can change the last used item*/
    @CheckedData
    private String lastUsedItem;

    /**Only effect switch items (moves or abilities) can change the item*/
    @CheckedData
    private String item;

    /**expItem does not change in a fight*/
    @CheckedData
    private String expItem;

    /**Only evolutions can change the ability*/
    @CheckedData
    private String ability;

    /**Only evolutions; moves copy from fighter and effect switch abilities can change the current ability; Never mind*/
    @CheckedData
    private String currentAbility;

    /**The key set is not changed*/
    private StringMap<Short> status;

    /**The key set is not changed*/
    private ObjectMap<MoveTeamPosition,Short> statusRelat;

    /**Never mind*/
    @CheckedData
    private LgInt nbRounds;

    /**Never mind even if empty*/
    private StringList types;

    /**Only learning moves by experience; only effect change moves can change the key set*/
    private StringMap<UsesOfMove> moves;

    /**Only learning moves by experience; moves copy from fighter; only effect change moves can change the key set*/
    private StringMap<UsesOfMove> currentMoves;

    /**Never mind*/
    private EnumMap<Statistic,Short> ev;

    /**Never mind*/
    private transient EnumMap<Statistic,Short> iv = new EnumMap<Statistic,Short>();

    /**Never mind*/
    private EnumMap<Statistic,Rate> statisBase;

    /**Never mind*/
    private EnumMap<Statistic,Byte> statisBoost;

    /***/
    @CheckedData
    private Rate remainingHp;

    private transient Rate varHp = Rate.zero();

    /**Never mind even if clone &gt; max hp*/
    @CheckedData
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
    private StringMap<Boolean> enabledMovesForAlly;

    /**Never mind*/
    private StringMap<Rate> damageRateInflictedByType;

    /**Never mind*/
    private StringMap<Rate> damageRateSufferedByType;

    /**Changed after act (use a move; switch or heal)
    even if a switch interrupt the round*/
    @CheckedData
    private boolean acted;

    /***/
    @CheckedData
    private byte groundPlace;

    /***/
    @CheckedData
    private byte groundPlaceSubst;

    /**Never mind even if high (added to wonExpSinceLastLevel for grow level)*/
    @CheckedData
    private Rate wonExp;

    /**Never mind even if high*/
    @CheckedData
    private Rate wonExpSinceLastLevel;

    /**Used for learning moves - evolving (experience); numeric string values*/
    @CheckedData
    private short level;

    /**Never mind*/
    @CheckedData
    private short happiness;

    /**Never mind*/
    @CheckedData
    private String usedBallCatching;

    /**Never mind*/
    private ObjectMap<MoveTeamPosition,Boolean> incrUserAccuracy;

    /**Never mind*/
    private StringMap<Integer> nbUsesMoves;

    /***/
    @CheckedData
    private short nbPrepaRound;

    /**if disappeared then nbPrepaRound &gt; 0*/
    @CheckedData
    private boolean disappeared;

    /***/
    @CheckedData
    private boolean needingToRecharge;

    /**Never mind*/
    private ObjectMap<MoveTeamPosition,AffectedMove> trackingMoves;

    /**Never mind*/
    private ObjectMap<MoveTeamPosition,ActivityOfMove> trappingMoves;

    /**Never mind*/
    @CheckedData
    private String lastSufferedMove;

    /**Never mind*/
    private StringList lastSufferedMoveTypes;

    /**Never mind*/
    private StringMap<Rate> damageSufferedCateg;

    /**Never mind*/
    private StringMap<Rate> damageSufferedCategRound;

    /**Never mind*/
    @CheckedData
    private String lastUsedMove;

    /**Never mind*/
    @CheckedData
    private String usedMoveLastRound;

    /**Never mind*/
    private StringList alreadyInvokedMovesRound;

    /**Never mind*/
    @CheckedData
    private String lastSuccessfulMove;

    /**The key set does not change; the move value change only if copy move from fighter
    This is combined with currentMoves*/
    private StringMap<CopiedMove> copiedMoves;

    /**Never mind*/
    @CheckedData
    private LgInt nbRepeatingSuccessfulMoves;

    /**Never mind*/
    @CheckedData
    private boolean usingItem;

    /**Never mind*/
    @CheckedData
    private boolean successfulMove;

    /**Never mind*/
    @CheckedData
    private boolean changed;

    /***/
    private transient StringMap<Boolean> enabledImmuAbilities;

    /**Never mind*/
    private ObjectMap<MoveTeamPosition,StringList> privateMoves;

    /**Cannot be changed in a fight*/
    @CheckedData
    private boolean belongingToPlayer;

    /***/
    private AbstractAction action;

    /***/
    private StringList movesToBeLearnt;

    /***/
    private StringMap<MovesAbilities> movesAbilitiesEvos;

    private transient Comment comment = new Comment();

    @RwXml
    Fighter(){

    }

    public Fighter(PokemonPlayer _pokemon,DataBase _import,byte _placeTerrain){
        initCreatureUser(_pokemon,_import);
        initUserMoves(_pokemon);
        initEvIvUser(_pokemon);
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    public Fighter(PkTrainer _pokemon,DataBase _import,byte _placeTerrain){
        initCreatureNonUser(_pokemon,_import);
        initPokemonTrainerMoves(_pokemon,_import);
        initEvIvOther(_import);
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    public Fighter(WildPk _pokemon,DataBase _import,byte _placeTerrain){
        initCreatureNonUser(_pokemon,_import);
        initWildPokemonMoves(_import);
        initEvIvOther(_import);
        initCreatureGeneral(_import);
        groundPlace=_placeTerrain;
        groundPlaceSubst=_placeTerrain;
    }

    static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), FIGHTER);
    }

    void initCreatureUser(PokemonPlayer _pokemon,DataBase _import){
        belongingToPlayer = true;
        initCreature(_pokemon);
        if (_import.isObjectUsedForExp(item)) {
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
            if(_pokemon.getStatus().containsObj(e)){
                status.put(e,(short) 1);
            }else{
                status.put(e,(short) 0);
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
            status.put(e,(short) 0);
        }
    }

    void initCreature(Pokemon _pokemon) {
        statusRelat = new ObjectMap<MoveTeamPosition,Short>();
        status = new StringMap<Short>();
        incrUserAccuracy = new ObjectMap<MoveTeamPosition,Boolean>();
        trackingMoves = new ObjectMap<MoveTeamPosition,AffectedMove>();
        trappingMoves = new ObjectMap<MoveTeamPosition,ActivityOfMove>();
        privateMoves = new ObjectMap<MoveTeamPosition,StringList>();
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
        ev=new EnumMap<Statistic,Short>();
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
            short pp_=fAtt_.getPp();
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
            short pp_=fAtt_.getPp();
            moves.put(m,new UsesOfMove(pp_));
            currentMoves.put(m,new UsesOfMove(pp_));
        }
    }

    void initEvIvOther(DataBase _import) {
        ev = new EnumMap<Statistic,Short>();
        for(Statistic c:Statistic.getStatisticsWithBase()){
            ev.put(c, (short)0);
            iv.put(c, (short)0);
        }
        remainingHp = fichePokemon(_import).statHp(level, ev, iv);
    }

    void initCreatureGeneral(DataBase _import){
        PokemonData fPk_=fichePokemonActuelle(_import);
        types=new StringList(fPk_.getTypes());
        byte def_ = (byte) _import.getDefaultBoost();
        statisBoost = new EnumMap<Statistic,Byte>();
        for(Statistic c:Statistic.getStatisticsWithBoost()){
            statisBoost.put(c, def_);
        }
        statisBase = new EnumMap<Statistic,Rate>();
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
        action = new Action();
        damageSufferedCateg = new StringMap<Rate>();
        damageSufferedCategRound = new StringMap<Rate>();
        for(String e:_import.getCategories()){
            damageSufferedCateg.put(e, Rate.zero());
            damageSufferedCategRound.put(e, Rate.zero());
        }
        lastSufferedMove=DataBase.EMPTY_STRING;
        lastSufferedMoveTypes=new StringList();
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_import.getVarParamsMove(CIBLE_NB_UTILISATION));
        attaques_.addAllElts(_import.getVarParamsMove(LANCEUR_NB_UTILISATION));
        attaques_.removeDuplicates();
        nbUsesMoves = new StringMap<Integer>();
        for(String e:attaques_){
            nbUsesMoves.put(e, 0);
        }
        //(CIBLE|LANCEUR)_NB_UTILISATION
        wonExp = Rate.zero();
        copiedMoves = new StringMap<CopiedMove>();
        for(String e:_import.getMovesCopyingTemp()){
            copiedMoves.put(e, new CopiedMove(DataBase.EMPTY_STRING,(short) 0));
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
            enabledMoves.put(e, new ActivityOfMove(_import.getMovesEffectIndivIncr().containsObj(e)));
        }
        protectedAgainstMoveTypes = new StringList();
        enabledMovesProt = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectProt()){
            enabledMovesProt.put(e, new ActivityOfMove(_import.getMovesEffectIndivIncr().containsObj(e)));
        }
        enabledMovesUnprot = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffectUnprot()){
            enabledMovesUnprot.put(e, new ActivityOfMove(_import.getMovesEffectIndivIncr().containsObj(e)));
        }
        enabledMovesForAlly = new StringMap<Boolean>();
        for(String e:_import.getMovesEffectAlly()){
            enabledMovesForAlly.put(e, false);
        }
        enabledMovesEndRound = new StringMap<ActivityOfMove>();
        for(String e:_import.getMovesEffEndRoundIndiv()){
            enabledMovesEndRound.put(e, new ActivityOfMove(_import.getMovesEffEndRoundIndivIncr().containsObj(e)));
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


    void initCreatureRelationsAutre(EqList<TeamPosition> _cbts,DataBase _import){
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
    public void validate(DataBase _data, byte _numberTeam, Fight _fight) {
        if (gender == null) {
            throw new GameLoadException();
        }
        if (action == null) {
            throw new GameLoadException();
        }
        if (currentGender == null) {
            throw new GameLoadException();
        }
        if (nickname == null) {
            throw new GameLoadException();
        }
        if (!_data.getPokedex().contains(name)) {
            throw new GameLoadException();
        }
        if (!_data.getPokedex().contains(currentName)) {
            throw new GameLoadException();
        }
        if (!_data.getAbilities().contains(ability)) {
            throw new GameLoadException();
        }
        if (!_data.getAbilities().contains(currentAbility)) {
            throw new GameLoadException();
        }
        if (!item.isEmpty()) {
            if (!_data.getItems().contains(item)) {
                throw new GameLoadException();
            }
        }
        if (!expItem.isEmpty()) {
            if (!_data.isObjectUsedForExp(expItem)) {
                throw new GameLoadException();
            }
        }
        if (!lastUsedItem.isEmpty()) {
            if (!_data.getItems().contains(lastUsedItem)) {
                throw new GameLoadException();
            }
        }
        if (height.isZeroOrLt()) {
            throw new GameLoadException();
        }
        if (weight.isZeroOrLt()) {
            throw new GameLoadException();
        }
        for (String t: types) {
            if (!_data.getTypes().containsObj(t)) {
                throw new GameLoadException();
            }
        }
        if (moves.size() > _data.getNbMaxMoves()) {
            throw new GameLoadException();
        }
        for (String m: moves.getKeys()) {
            if (StringList.quickEq(m, _data.getDefaultMove())) {
                throw new GameLoadException();
            }
            if (!_data.getMoves().contains(m)) {
                throw new GameLoadException();
            }
            UsesOfMove pp_ = moves.getVal(m);
            if (pp_.getCurrent() < 0) {
                throw new GameLoadException();
            }
            if (pp_.getCurrent() > pp_.getMax()) {
                throw new GameLoadException();
            }
            if (pp_.getMax() == 0) {
                throw new GameLoadException();
            }
        }
        if (moves.isEmpty()) {
            throw new GameLoadException();
        }
        if (currentMoves.size() > _data.getNbMaxMoves()) {
            throw new GameLoadException();
        }
        for (String m: currentMoves.getKeys()) {
            if (StringList.quickEq(m, _data.getDefaultMove())) {
                throw new GameLoadException();
            }
            if (!_data.getMoves().contains(m)) {
                throw new GameLoadException();
            }
            UsesOfMove pp_ = currentMoves.getVal(m);
            if (pp_.getCurrent() < 0) {
                throw new GameLoadException();
            }
            if (pp_.getCurrent() > pp_.getMax()) {
                throw new GameLoadException();
            }
            if (pp_.getMax() == 0) {
                throw new GameLoadException();
            }
        }
        StringList moves_ = attaquesUtilisables();
        int sizeMoves_ = moves_.size();
        moves_.removeDuplicates();
        if (sizeMoves_ != moves_.size()) {
            throw new GameLoadException();
        }
        for (String m: alreadyInvokedMovesRound) {
            if (!_data.getMoves().contains(m)) {
                throw new GameLoadException();
            }
        }
        Team team_ = _fight.getTeams().getVal(_numberTeam);
        Numbers<Byte> partners_ = team_.getMembers().getKeys();
        if (action instanceof ActionMove) {
            ActionMove actionMove_ = (ActionMove) action;
            if (!_data.getMoves().contains(actionMove_.getFirstChosenMove())) {
                throw new GameLoadException();
            }
            if (!Numbers.eq(actionMove_.getSubstitute(),BACK)) {
                if (!partners_.containsObj(actionMove_.getSubstitute())) {
                    throw new GameLoadException();
                }
            }
            if (_data.getMove(actionMove_.getFirstChosenMove()).getTargetChoice().isWithChoice()) {
                if (actionMove_.getChosenTargets().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                    throw new GameLoadException();
                }
            } else {
                if (!actionMove_.getChosenTargets().isEmpty()) {
                    throw new GameLoadException();
                }
            }
            if (!actionMove_.getFinalChosenMove().isEmpty()) {
                if (!_data.getMoves().contains(actionMove_.getFinalChosenMove())) {
                    throw new GameLoadException();
                }
            }
        }
        if (action instanceof ActionSwitch) {
            ActionSwitch actionSwitch_ = (ActionSwitch) action;
            if (Numbers.eq(actionSwitch_.getSubstitute(),BACK)) {
                throw new GameLoadException();
            }
            if (!partners_.containsObj(actionSwitch_.getSubstitute())) {
                throw new GameLoadException();
            }
        }
        if (action instanceof ActionHeal) {
            ActionHeal actionHeal_ = (ActionHeal) action;
            if (!(_data.getItem(actionHeal_.getChosenHealingItem()) instanceof Berry)) {
                if (!(_data.getItem(actionHeal_.getChosenHealingItem()) instanceof HealingItem)) {
                    throw new GameLoadException();
                }
            }
            if (action instanceof ActionHealMove) {
                //one move
                ActionHealMove actionHealMove_ = (ActionHealMove) action;
                boolean instanceOf_ = false;
                if (_data.getItem(actionHealMove_.getChosenHealingItem()) instanceof HealingPp) {
                    instanceOf_ = true;
                    HealingPp pp_ = (HealingPp) _data.getItem(actionHealMove_.getChosenHealingItem());
                    if (!pp_.healOneMove()) {
                        throw new GameLoadException();
                    }
                }
                if (_data.getItem(actionHealMove_.getChosenHealingItem()) instanceof Berry) {
                    instanceOf_ = true;
                    Berry berry_ = (Berry) _data.getItem(actionHealMove_.getChosenHealingItem());
                    if (berry_.getHealPp() == 0) {
                        throw new GameLoadException();
                    }
                }
                if (!instanceOf_) {
                    throw new GameLoadException();
                }
                if (!_data.getMoves().contains(actionHealMove_.getFirstChosenMove())) {
                    throw new GameLoadException();
                }
            } else {
                if (_data.getItem(actionHeal_.getChosenHealingItem()) instanceof HealingPp) {
                    HealingPp pp_ = (HealingPp) _data.getItem(actionHeal_.getChosenHealingItem());
                    if (pp_.healOneMove()) {
                        throw new GameLoadException();
                    }
                }
                if (_data.getItem(actionHeal_.getChosenHealingItem()) instanceof Berry) {
                    Berry berry_ = (Berry) _data.getItem(actionHeal_.getChosenHealingItem());
                    if (berry_.getHealPp() > 0) {
                        throw new GameLoadException();
                    }
                }
            }
        }
        if (!nbRounds.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (level <= 0) {
            throw new GameLoadException();
        }
        if (level > _data.getMaxLevel()) {
            throw new GameLoadException();
        }
        if (!Statistic.equalsSet(ev.getKeys(), Statistic.getStatisticsWithBase())) {
            throw new GameLoadException();
        }
        if (!Statistic.equalsSet(statisBase.getKeys(), Statistic.getStatisticsWithBase())) {
            throw new GameLoadException();
        }
        if (!Statistic.equalsSet(statisBoost.getKeys(), Statistic.getStatisticsWithBoost())) {
            throw new GameLoadException();
        }
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            if (ev.getVal(s) < 0) {
                throw new GameLoadException();
            }
            Rate stat_ = statisBase.getVal(s);
            if (stat_.isZeroOrLt()) {
                throw new GameLoadException();
            }
        }
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            byte boost_ = statisBoost.getVal(s);
            if (boost_ < _data.getMinBoost()) {
                throw new GameLoadException();
            }
            if (boost_ > _data.getMaxBoost()) {
                throw new GameLoadException();
            }
        }
        if (!clone.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (!remainingHp.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (Rate.strGreater(remainingHp, pvMax())) {
            throw new GameLoadException();
        }
        if (estKo()) {
            if (!estArriere()) {
                throw new GameLoadException();
            }
        }
        if (Numbers.eq(groundPlaceSubst, Fighter.BACK)) {
            if (!estArriere()) {
                throw new GameLoadException();
            }
        }
        if (isBelongingToPlayer()) {
            if (!_data.getItems().contains(usedBallCatching)) {
                throw new GameLoadException();
            }
            if (!(_data.getItem(usedBallCatching) instanceof Ball)) {
                throw new GameLoadException();
            }
        }
        if (!wonExp.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (!wonExpSinceLastLevel.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (happiness < 0) {
            throw new GameLoadException();
        }
        if (happiness > _data.getHappinessMax()) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getMovesEffectIndiv(), enabledMoves.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: enabledMoves.getKeys()) {
            if (enabledMoves.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectUnprot(), enabledMovesUnprot.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: enabledMovesUnprot.getKeys()) {
            if (enabledMovesUnprot.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectProt(), enabledMovesProt.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: enabledMovesProt.getKeys()) {
            if (enabledMovesProt.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        if (!_data.getTypes().containsAllObj(protectedAgainstMoveTypes)) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getMovesEffEndRoundIndiv(), enabledMovesEndRound.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: enabledMovesEndRound.getKeys()) {
            if (enabledMovesEndRound.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (Object o: enabledMovesForAlly.values()) {
            if (!(o instanceof Boolean)) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectAlly(), enabledMovesForAlly.getKeys())) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getMovesConstChoices(), enabledMovesConstChoices.getKeys())) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getMovesChangingTypes(), enabledChangingTypesMoves.getKeys())) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getMovesCountering(), enabledCounteringMoves.getKeys())) {
            throw new GameLoadException();
        }
        if (!StringList.equalsSet(_data.getTypes(), damageRateInflictedByType.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: damageRateInflictedByType.getKeys()) {
            if (!damageRateInflictedByType.getVal(m).isZeroOrGt()) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getTypes(), damageRateSufferedByType.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: damageRateSufferedByType.getKeys()) {
            if (!damageRateSufferedByType.getVal(m).isZeroOrGt()) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getCategories(), damageSufferedCateg.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: damageSufferedCateg.getKeys()) {
            if (!damageSufferedCateg.getVal(m).isZeroOrGt()) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(_data.getCategories(), damageSufferedCategRound.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: damageSufferedCategRound.getKeys()) {
            if (!damageSufferedCategRound.getVal(m).isZeroOrGt()) {
                throw new GameLoadException();
            }
        }
        for (String m: enabledMovesConstChoices.getKeys()) {
            if (enabledMovesConstChoices.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (String m: enabledChangingTypesMoves.getKeys()) {
            if (enabledChangingTypesMoves.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (String m: enabledCounteringMoves.getKeys()) {
            if (enabledCounteringMoves.getVal(m).getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (String m:movesToBeLearnt) {
            if (!_data.getMoves().contains(m)) {
                throw new GameLoadException();
            }
            if (moves.contains(m)) {
                throw new GameLoadException();
            }
        }
        PokemonData fPk_ = fichePokemon(_data);
        for (String e: movesAbilitiesEvos.getKeys()) {
            if (!fPk_.getEvolutions().contains(e)) {
                throw new GameLoadException();
            }
            MovesAbilities movesAbilities_ = movesAbilitiesEvos.getVal(e);
            for (String m: moves.getKeys()) {
                if (movesAbilities_.getMoves().containsObj(m)) {
                    throw new GameLoadException();
                }
            }
            for (String m: movesAbilities_.getMoves()) {
                if (!_data.getMoves().contains(m)) {
                    throw new GameLoadException();
                }
            }
            for (String a: movesAbilities_.getAbilities()) {
                if (!_data.getAbilities().contains(a)) {
                    throw new GameLoadException();
                }
            }
        }
        EqList<TeamPosition> fighters_ = FightOrder.fighters(_fight);
        StringList relMoves_;
        relMoves_ = new StringList();
        relMoves_.addAllElts(_data.getMovesActingMoveUses());
        EqList<MoveTeamPosition> relMovesTh_;
        relMovesTh_ = new EqList<MoveTeamPosition>();
        for (TeamPosition f: fighters_) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        if (!MoveTeamPosition.equalsSet(trackingMoves.getKeys(), relMovesTh_)) {
            throw new GameLoadException();
        }
        relMoves_ = new StringList();
        relMoves_.addAllElts(_data.getTrappingMoves());
        relMovesTh_ = new EqList<MoveTeamPosition>();
        for (TeamPosition f: fighters_) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        if (!MoveTeamPosition.equalsSet(trappingMoves.getKeys(), relMovesTh_)) {
            throw new GameLoadException();
        }
        relMoves_ = new StringList();
        relMoves_.addAllElts(_data.getMovesForbidding());
        relMovesTh_ = new EqList<MoveTeamPosition>();
        for (TeamPosition f: fighters_) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        if (!MoveTeamPosition.equalsSet(privateMoves.getKeys(), relMovesTh_)) {
            throw new GameLoadException();
        }
        relMoves_ = new StringList();
        relMoves_.addAllElts(_data.getMovesAccuracy());
        relMovesTh_ = new EqList<MoveTeamPosition>();
        for (TeamPosition f: fighters_) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        for (Object o :incrUserAccuracy.values()) {
            if (!(o instanceof Boolean)) {
                throw new GameLoadException();
            }
        }
        if (!MoveTeamPosition.equalsSet(incrUserAccuracy.getKeys(), relMovesTh_)) {
            throw new GameLoadException();
        }
        for (AffectedMove v: trackingMoves.values()) {
            if (!v.getMove().isEmpty()) {
                if (!_data.getMoves().contains(v.getMove())) {
                    throw new GameLoadException();
                }
            }
            if (v.getActivity().getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (ActivityOfMove k: trappingMoves.values()) {
            if (k.getNbTurn() < 0) {
                throw new GameLoadException();
            }
        }
        for (StringList k: privateMoves.values()) {
            for (String m: k) {
                if (!_data.getMoves().contains(m)) {
                    throw new GameLoadException();
                }
            }
        }
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
        if (!StringList.equalsSet(status.getKeys(), statusSingle_)) {
            throw new GameLoadException();
        }
        relMoves_ = new StringList();
        relMoves_.addAllElts(statusRelation_);
        relMovesTh_ = new EqList<MoveTeamPosition>();
        for (TeamPosition f: fighters_) {
            for (String m: relMoves_) {
                relMovesTh_.add(new MoveTeamPosition(m, f));
            }
        }
        if (!MoveTeamPosition.equalsSet(statusRelat.getKeys(), relMovesTh_)) {
            throw new GameLoadException();
        }
        for (short s: status.values()) {
            if (s < 0) {
                throw new GameLoadException();
            }
        }
        for (short s: statusRelat.values()) {
            if (s < 0) {
                throw new GameLoadException();
            }
        }
        StringList attaques_ = new StringList();
        attaques_.addAllElts(_data.getVarParamsMove(CIBLE_NB_UTILISATION));
        attaques_.addAllElts(_data.getVarParamsMove(LANCEUR_NB_UTILISATION));
        attaques_.removeDuplicates();
        if (!StringList.equalsSet(attaques_, nbUsesMoves.getKeys())) {
            throw new GameLoadException();
        }
        for (String m: nbUsesMoves.getKeys()) {
            if (nbUsesMoves.getVal(m) < 0) {
                throw new GameLoadException();
            }
        }
        if (!StringList.equalsSet(copiedMoves.getKeys(), _data.getMovesCopyingTemp())) {
            throw new GameLoadException();
        }
        for (String m: copiedMoves.getKeys()) {
            CopiedMove val_ = copiedMoves.getVal(m);
            if (val_.getPp() < 0) {
                throw new GameLoadException();
            }
            if (!val_.getMove().isEmpty()) {
                if (!_data.getMoves().contains(val_.getMove())) {
                    throw new GameLoadException();
                }
            }
        }
        if (nbPrepaRound == 0) {
            if (disappeared) {
                throw new GameLoadException();
            }
        }
        if (nbPrepaRound < 0) {
            throw new GameLoadException();
        }
        if (nbPrepaRound > 0) {
            if (needingToRecharge) {
                throw new GameLoadException();
            }
        }
        if (!nbRepeatingSuccessfulMoves.isZeroOrGt()) {
            throw new GameLoadException();
        }
        if (!lastUsedMove.isEmpty()) {
            if (!_data.getMoves().contains(lastUsedMove)) {
                throw new GameLoadException();
            }
        }
        if (!usedMoveLastRound.isEmpty()) {
            if (!_data.getMoves().contains(usedMoveLastRound)) {
                throw new GameLoadException();
            }
            if (!attaquesUtilisables().containsStr(usedMoveLastRound)) {
                throw new GameLoadException();
            }
        }
        if (!lastSuccessfulMove.isEmpty()) {
            if (!_data.getMoves().contains(lastSuccessfulMove)) {
                throw new GameLoadException();
            }
        }
        if (!lastSufferedMove.isEmpty()) {
            if (!_data.getMoves().contains(lastSufferedMove)) {
                throw new GameLoadException();
            }
        }
        if (!_data.getTypes().containsAllObj(lastSufferedMoveTypes)) {
            throw new GameLoadException();
        }
        if (groundPlace != BACK) {
            if (groundPlace < 0) {
                throw new GameLoadException();
            }
        }
        if (groundPlaceSubst != BACK) {
            if (groundPlaceSubst < 0) {
                throw new GameLoadException();
            }
        }
    }

    void ajouterRelationAutre(TeamPosition _cbt,DataBase _import){
        for(String c:_import.getStatus().getKeys()){
            Status statut_=_import.getStatus(c);
            if (statut_.getStatusType() == StatusType.INDIVIDUEL) {
                continue;
            }
            statusRelat.put(new MoveTeamPosition(c,_cbt),(short) 0);
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
            incrUserAccuracy.put(new MoveTeamPosition(e,_cbt),false);
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

    Rate numberNecessaryPointsForGrowingLevel(short _niveau,DataBase _import){
        PokemonData fPk_=fichePokemon(_import);
        String expLitt_=_import.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(DataBase.VAR_PREFIX+NIVEAU,Integer.toString(_niveau));
        Rate next_;
        next_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(DataBase.VAR_PREFIX+NIVEAU,Integer.toString(_niveau - 1));
        current_ = _import.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        return _import.evaluatePositiveExp(Rate.minus(next_, current_).toString(), vars_, Rate.one());
    }

    StringList nomEvolutions(DataBase _import,StringList _pkNamesBegin){
        StringList evos_ = new StringList();
        if(hasExpObject()){
            ItemForBattle objet_=(ItemForBattle) dataExpObject(_import);
            if(objet_.getAgainstEvo()){
                return evos_;
            }
        }
        PokemonData fPk_=fichePokemon(_import);
        for(String e:fPk_.getEvolutions().getKeys()){
            Evolution evo_=fPk_.getEvolution(e);
            if(evo_ instanceof EvolutionMove){
                EvolutionMove evoAtt_=(EvolutionMove)evo_;
                if(moves.contains(evoAtt_.getMove())){
                    evos_.add(e);
                }
                continue;
            }
            if(evo_ instanceof EvolutionMoveType){
                EvolutionMoveType evoType_=(EvolutionMoveType)evo_;
                for (String move_: moves.getKeys()) {
                    for (String type_: _import.getMove(move_).getTypes()) {
                        if (StringList.quickEq(type_, evoType_.getType())) {
                            evos_.add(e);
                        }
                    }
                }
                evos_.removeDuplicates();
            }
            if(evo_ instanceof EvolutionHappiness){
                if(happiness>=_import.getHappinessEvo()){
                    evos_.add(e);
                }
                continue;
            }
            if(evo_ instanceof EvolutionLevelGender){
                EvolutionLevelGender evoNivGenre_=(EvolutionLevelGender)evo_;
                if(gender==evoNivGenre_.getGender()&&level>=evoNivGenre_.getLevel()){
                    evos_.add(e);
                }
                continue;
            }
            if(evo_ instanceof EvolutionLevel){
                EvolutionLevel evoNiv_=(EvolutionLevel)evo_;
                if(level>=evoNiv_.getLevel()){
                    evos_.add(e);
                }
                continue;
            }
            if(evo_ instanceof EvolutionItem){
                EvolutionItem evoObjet_=(EvolutionItem)evo_;
                if(StringList.quickEq(evoObjet_.getItem(),item)){
                    evos_.add(e);
                }
                continue;
            }
            if(evo_ instanceof EvolutionTeam){
                EvolutionTeam evoPlace_=(EvolutionTeam)evo_;
                if(_pkNamesBegin.containsObj(evoPlace_.getPokemon())){
                    evos_.add(e);
                }
                continue;
            }
        }
        return evos_;
    }

    boolean possedeObjet(){
        return !item.isEmpty();
    }

    boolean hasExpObject(){
        return !expItem.isEmpty();
    }

    Item ficheObjet(DataBase _import){
        return _import.getItem(item);
    }

    Item dataExpObject(DataBase _import){
        return _import.getItem(expItem);
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
        short next_ = level;
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

    short powerPointsMove(String _attaque){
        for(String c:copiedMoves.getKeys()){
            if(StringList.quickEq(copiedMoves.getVal(c).getMove(),_attaque)){
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

    short maxPowerPointsMove(String _attaque, DataBase _import){
        for(String c:copiedMoves.getKeys()){
            short pp_ = _import.ppCopiedMove(c);
            if(StringList.quickEq(copiedMoves.getVal(c).getMove(),_attaque)){
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

    short healedPpMove(String _attaque,String _objet,DataBase _import){
        if(changed){
            return 0;
        }
        StringList listIntersectMoves_;
        listIntersectMoves_ = moves.getKeys().intersect(currentMoves.getKeys());
        if (!listIntersectMoves_.containsObj(_attaque)) {
            return 0;
        }
        //moves.contains(_attaque) && currentMoves.contains(_attaque)
        Item objet_=_import.getItem(_objet);
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
            return (short) var_;
        }
        Berry baie_=(Berry)objet_;
        int var_=baie_.getHealPp();
        if(currentMoves.getVal(_attaque).getCurrent()+var_>currentMoves.getVal(_attaque).getMax()){
            return currentMoves.getVal(_attaque).getLostPp();
        }
        return (short) var_;
    }

    void healPowerPoints(String _attaque,short _var){
        UsesOfMove pps_ = currentMoves.getVal(_attaque);
        pps_.heal(_var);
    }


    void usePowerPointsByMove(Difficulty _diff,String _attaque,short _var){
        for(String c:copiedMoves.getKeys()){
            CopiedMove pp_= copiedMoves.getVal(c);
            if(!StringList.quickEq(pp_.getMove(),_attaque)){
                continue;
            }
            if(pp_.getPp()<_var){
                pp_.setPp((short) 0);
            }else{
                pp_.setPp((short) (pp_.getPp()-_var));
            }
            return;
        }
        if (currentMoves.contains(_attaque)) {
            UsesOfMove pps_ = currentMoves.getVal(_attaque);
            short ppAct_=pps_.getCurrent();
            if(changed){
                if(ppAct_<_var){
                    ppAct_=0;
                }else{
                    ppAct_=(short) (ppAct_-_var);
                }
                pps_.setCurrent(ppAct_);
                return;
            }
            if(ppAct_<_var){
                ppAct_=0;
            }else{
                ppAct_=(short) (ppAct_-_var);
            }
            pps_.setCurrent(ppAct_);
            if(_diff.getRestoredMovesEndFight()){
                return;
            }
        }
        if (moves.contains(_attaque)) {
            UsesOfMove pps_ = moves.getVal(_attaque);
            short pp_=pps_.getCurrent();
            if(pp_<_var){
                pp_=0;
            }else{
                pp_=(short) (pp_-_var);
            }
            pps_.setCurrent(pp_);
        }
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
        //action instanceof ActionMove
        ((ActionMove)action).setFinalChosenMove(alreadyInvokedMovesRound.last());
        lastUsedMove = alreadyInvokedMovesRound.last();
    }

    void successUsingMove(){
        if (!(action instanceof ActionMove)) {
            return;
        }
        successfulMove=true;
        lastSuccessfulMove=((ActionMove)action).getFinalChosenMove();
    }

    void exitFrontBattle(){
        groundPlace=BACK;
    }

    void exitFrontBattleForBeingSubstitued(){
        groundPlaceSubst=BACK;
    }



    void cancelSubstituing(){
        if (action instanceof ActionSwitch) {
            action = new Action();
        }
    }



    void affecterPseudoStatut(TeamPosition _combattant,String _pseudoStatut){
        statusRelat.set(new MoveTeamPosition(_pseudoStatut,_combattant),(short) 1);
    }

    /**public for tests*/
    public void affecterStatut(String _statut){
        status.set(_statut,(short) 1);
    }

    void disableAllStatusByEnabledWeather(String _weather, DataBase _data) {
        if (!capaciteActive()) {
            return;
        }
        AbilityData fCapac_ = ficheCapaciteActuelle(_data);
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
        if (!capaciteActive()) {
            return new StringList();
        }
        AbilityData fCapac_ = ficheCapaciteActuelle(_data);
        if(!fCapac_.getChgtTypeByWeather().contains(_weather)){
            return new StringList();
        }
        return new StringList(fCapac_.getChgtTypeByWeather().getVal(_weather));
    }

    boolean hasObjectEnabledBeingSent(DataBase _import) {
        if (!possedeObjet()) {
            return false;
        }
        Item objet_= ficheObjet(_import);
        if (!(objet_ instanceof ItemForBattle)) {
            return false;
        }
        ItemForBattle objetAttachableCombat_=(ItemForBattle)objet_;
        return objetAttachableCombat_.enabledSending();
    }

    void incrementPseudoStatutCombattant(TeamPosition _combattant,String _pseudoStatut) {
        MoveTeamPosition key_ = new MoveTeamPosition(_pseudoStatut,_combattant);
        statusRelat.set(key_,(short) (statusRelat.getVal(key_)+1));
    }

    void supprimerPseudoStatutCombattant(TeamPosition _combattant,String _pseudoStatut){
        statusRelat.set(new MoveTeamPosition(_pseudoStatut,_combattant),(short) 0);
    }

    void supprimerPseudoStatut(String _pseudoStatut){
        for(MoveTeamPosition c:statusRelat.getKeys()){
            if(StringList.quickEq(c.getMove(),_pseudoStatut)){
                statusRelat.put(c,(short) 0);
            }
        }
    }

    void supprimerStatut(String _statut){
        status.set(_statut,(short) 0);
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
        enabledMovesProt.getVal(_attaque).disable();
        enabledMovesProt.getVal(_attaque).reset();
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
        if(!item.isEmpty()){
            if(_objet.isEmpty()){
                lastUsedItem=item;
            }
        }
        item=_objet;
    }

    void variationBoostStatistique(Statistic _statistique,byte _variation){
        byte value_ = statisBoost.getVal(_statistique);
        value_ += _variation;
        statisBoost.put(_statistique, value_);
    }

    void variationLeftHp(Rate _variation){
        varHp.affectZero();
        Rate sum_ = Rate.plus(remainingHp, _variation);
        Rate max_ = pvMax();
        if (Rate.strGreater(sum_, max_)) {
            remainingHp = max_;
            varHp.affect(Rate.minus(max_, remainingHp));
        } else {
            remainingHp.addNb(_variation);
            varHp.affect(_variation);
        }
    }

    void fullHeal(DataBase _import){
        remainingHp=pvMax();
        for(String c:status.getKeys()){
            supprimerStatut(c);
        }
        for(MoveTeamPosition c:statusRelat.getKeys()){
            statusRelat.put(c,(short) 0);
        }
        for(String c:currentMoves.getKeys()){
            currentMoves.getVal(c).fullHeal();
        }
        for(String c:moves.getKeys()){
            moves.getVal(c).fullHeal();
        }
        String name_ = _import.translatePokemon(name);
        comment.addMessage(_messages_.getVal(FULL_HEAL), name_);
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
        MoveData fAtt_=_import.getMove(_ancienneAttaque);
        int nbEffets_=fAtt_.nbEffets();
        short pp_=0;
        for(int i=CustList.FIRST_INDEX;i<nbEffets_;i++){
            Effect effet_=fAtt_.getEffet(i);
            if(!(effet_ instanceof EffectCopyMove)){
                continue;
            }
            EffectCopyMove effetCopieAtt_=(EffectCopyMove)effet_;
            if(effetCopieAtt_.getCopyingMoveForUser()>0){
                pp_=effetCopieAtt_.getCopyingMoveForUser();
                break;
            }
        }
        if (!_import.getMovesCopyingTemp().containsObj(_ancienneAttaque)) {
            return;
        }
        copiedMoves.put(_ancienneAttaque,new CopiedMove(_nouvelleAttaque,pp_));
    }

    void apprendreAttaqueEcrasantDef(String _nouvelleAttaque,String _ancienneAttaque,DataBase _import){
        short pp_=_import.getMove(_nouvelleAttaque).getPp();
        StringList listIntersectMoves_;
        listIntersectMoves_ = moves.getKeys().intersect(currentMoves.getKeys());
        if (!listIntersectMoves_.containsObj(_ancienneAttaque)) {
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

    void transformer(Fighter _creature,short _pp){
        currentName=_creature.getCurrentName();
        weight.affect(_creature.getWeight());
        height.affect(_creature.getHeight());
        types=new StringList(_creature.getTypes());
        currentGender=_creature.getCurrentGender();
        currentMoves.clear();
        for(String c:_creature.getCurrentMovesSet()){
            currentMoves.put(c,new UsesOfMove(_pp));
        }
        EnumMap<Statistic,Rate> statBase_=_creature.getStatisBase();
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
        EnumMap<Statistic,StatBaseEv> statEv_=fPk_.getStatistics();
        for(Statistic c:statEv_.getKeys()){
            statisBase.put(c, new Rate(statEv_.getVal(c).getBase()));
        }
        for(Statistic c:statisBoost.getKeys()){
            statisBoost.put(c, (byte)_import.getDefaultBoost());
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


    void calculateNewLevel(Difficulty _diff,DataBase _import,StringList _pkNamesBegin){
        comment.clearMessages();
        String name_ = _import.translatePokemon(name);
        LevelExpPoints levelWonPoints_ = newLevelWonPoints(_import);
        short achievedLevel_ = levelWonPoints_.getLevel();
        Rate sommeDiffNiveaux_ = levelWonPoints_.getExpPoints();
        short maxNiveau_=(short) _import.getMaxLevel();
        if (achievedLevel_ != maxNiveau_) {
            changeWonPoints(achievedLevel_, sommeDiffNiveaux_, _import);
        } else {
            wonExp.affectZero();
            wonExpSinceLastLevel.affectZero();
        }
        StringMap<Boolean> newMoves_ = newMoves(achievedLevel_, _diff, _import);
//        StringList attaquesConnues_=new StringList(newMoves_.getKeys(false));
//        StringList attaquesApprendre_=new StringList(newMoves_.getKeys(true));
        StringList attaquesConnues_=new StringList();
        StringList attaquesApprendre_=new StringList();
        for (EntryCust<String,Boolean> e: newMoves_.entryList()) {
            if (e.getValue()) {
                attaquesApprendre_.add(e.getKey());
            } else {
                attaquesConnues_.add(e.getKey());
            }
        }
        initLearntMoves(attaquesApprendre_, attaquesConnues_, _import);
        short monteNiveau_=(short) (achievedLevel_-level);
        setLevel(achievedLevel_);
        learnMoves(attaquesApprendre_, _import);
        attaquesConnues_.clear();
        attaquesConnues_.addAllElts(moves.getKeys());
        if(monteNiveau_>0){
            comment.addMessage(_messages_.getVal(GROW_LEVEL), name_, achievedLevel_);
            winHappinessByGrowingLevel(monteNiveau_,_import);
            formeNormale(_import);
            fullHeal(_import);
            //groundPlace = groundPlaceSubst;
        }
        proponeMovesAbilitiesForEvolutions(attaquesApprendre_, attaquesConnues_, _import, _pkNamesBegin);
    }

    LevelExpPoints newLevelWonPoints(DataBase _import) {
        short niveauTmp_=level;
        niveauTmp_++;
        Rate sommeDiffNiveaux_=numberNecessaryPointsForGrowingLevel(niveauTmp_,_import);
        niveauTmp_--;
        short maxNiveau_=(short) _import.getMaxLevel();
        while(true){
            Rate sum_ = Rate.plus(wonExp, wonExpSinceLastLevel);
            if (Rate.strLower(sum_, sommeDiffNiveaux_)){
                break;
            }
            niveauTmp_++;
            if (niveauTmp_ >= maxNiveau_) {
                if (niveauTmp_ > maxNiveau_) {
                    niveauTmp_--;
                }
                break;
            }
            sommeDiffNiveaux_.addNb(numberNecessaryPointsForGrowingLevel((short) (niveauTmp_+1),_import));
        }
        return new LevelExpPoints(niveauTmp_,sommeDiffNiveaux_);
    }

    void changeWonPoints(short _niveauTmp,Rate _sommeDiffNiveaux, DataBase _import) {
        short maxNiveau_=(short) _import.getMaxLevel();
        if(Numbers.eq(_niveauTmp,maxNiveau_)){
            //cas wonExp+wonExpSinceLastLevel>=sommeDiffNiveaux_:
            //==> wonExp+wonExpSinceLastLevel-sommeDiffNiveaux_>=0
            //==> apres affectation wonExp>=0
            //wonExp+wonExpSinceLastLevel-sommeDiffNiveaux_>=0
            wonExp.addNb(Rate.minus(wonExpSinceLastLevel, _sommeDiffNiveaux));
            wonExpSinceLastLevel.affectZero();
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
            wonExpSinceLastLevel.addNb(Rate.minus(wonExp, _sommeDiffNiveaux));
            wonExpSinceLastLevel.addNb(numberNecessaryPointsForGrowingLevel((short) (_niveauTmp+1),_import));
            wonExp.affectZero();
        }
    }

    StringMap<Boolean> newMoves(short _niveauTmp, Difficulty _diff, DataBase _import) {
        StringMap<Boolean> newMoves_ = new StringMap<Boolean>();
        StringList attaquesConnues_=new StringList(moves.getKeys());
        PokemonData fPk_=fichePokemon(_import);
        for(LevelMove nivAtt_: fPk_.getLevMoves()){
            if(attaquesConnues_.containsObj(nivAtt_.getMove())){
                continue;
            }
            if(nivAtt_.getLevel()>_niveauTmp){
                continue;
            }
            if(nivAtt_.getLevel()<level){
                continue;
            }
            if (_diff.isSkipLearningMovesWhileNotGrowingLevel()) {
                if(Numbers.eq(nivAtt_.getLevel(), level)){
                    continue;
                }
            }
            newMoves_.put(nivAtt_.getMove(), true);
        }
        for (String m : attaquesConnues_) {
            newMoves_.put(m, false);
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
            DataBase _import) {
        if (!movesToBeLearnt.isEmpty()) {
            return;
        }
        for(String e:_attaquesApprendre){
            short pp_=_import.getMove(e).getPp();
            moves.put(e,new UsesOfMove(pp_));
        }
        if (_attaquesApprendre.size() + currentMoves.size() <= _import.getNbMaxMoves()) {
            for(String e:_attaquesApprendre){
                short pp_=_import.getMove(e).getPp();
                currentMoves.put(e,new UsesOfMove(pp_));
            }
        }
        //whatever currentMoves.size <= _import.getNbMaxMoves()
        String name_ = _import.translatePokemon(name);
        for (String m: _attaquesApprendre) {
            String move_ = _import.translateMove(m);
            comment.addMessage(_messages_.getVal(LEARN_MOVE), name_, move_);
        }
    }

    void proponeMovesAbilitiesForEvolutions(StringList _attaquesApprendre,
            StringList _attaquesConnues,
            DataBase _import,StringList _pkNamesBegin) {
        for(String e:nomEvolutions(_import,_pkNamesBegin)){
            PokemonData fPkEvo_=_import.getPokemon(e);
            StringList attaquesApprendreEvos_=new StringList();
            for (String m : _attaquesApprendre) {
                if (_attaquesConnues.containsObj(m)) {
                    continue;
                }
                attaquesApprendreEvos_.add(m);
            }
            for(LevelMove l: fPkEvo_.getLevMoves()){
                if(_attaquesConnues.containsObj(l.getMove())){
                    continue;
                }
                if(l.getLevel()>level){
                    continue;
                }
                attaquesApprendreEvos_.add(l.getMove());
            }
            attaquesApprendreEvos_.removeDuplicates();
            StringList capacites_=new StringList(fPkEvo_.getAbilities());
            capacites_.sort();
            movesAbilitiesEvos.put(e,new MovesAbilities(attaquesApprendreEvos_,capacites_));
        }
    }

    void variationGainExperience(Rate _variation, DataBase _import){
        comment.clearMessages();
        wonExp.addNb(_variation);
        String name_ = _import.translatePokemon(name);
        comment.addMessage(_messages_.getVal(WON_EXP), name_, _variation);
    }

    void wonEvStatistic(Statistic _statistique,short _varEv,short _maxEv, DataBase _import){
        comment.clearMessages();
        short ev_=ev.getVal(_statistique);
        String name_ = _import.translatePokemon(name);
        String stat_ = _import.translateStatistics(_statistique);
        if(ev_+_varEv<_maxEv){
            ev_=(short) (ev_+_varEv);
            comment.addMessage(_messages_.getVal(WON_EV), name_, _varEv, stat_);
        }else{
            ev_=_maxEv;
            comment.addMessage(_messages_.getVal(WON_EV_MAX), name_, stat_);
        }
        ev.put(_statistique, ev_);
    }

    void winHappinessByGrowingLevel(short _diffNiv,DataBase _import){
        Rate mult_=DataBase.defRateProduct();
        if(hasExpObject()){
            ItemForBattle objet_=(ItemForBattle) dataExpObject(_import);
            if(!objet_.getMultWinningHappiness().isZero()){
                mult_.multiplyBy(objet_.getMultWinningHappiness());
            }
        }
        mult_.multiplyBy(_import.getWonHappinessByGrowLevel());
        mult_.multiplyBy(new Rate(_diffNiv));
        short maxBonheur_=(short) _import.getHappinessMax();
        String name_ = _import.translatePokemon(name);
        if(happiness+mult_.ll()<=maxBonheur_){
            happiness=(short) (happiness +mult_.ll());
            comment.addMessage(_messages_.getVal(WON_HAPPINESS), name_, mult_);
        }else{
            happiness=maxBonheur_;
            comment.addMessage(_messages_.getVal(MAX_HAPPINESS), name_);
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
            if(_attaquesRetenues.containsObj(c)){
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
            short pp_=_import.getMove(e).getPp();
            moves.put(e,new UsesOfMove(pp_));
        }
        currentMoves.putAllMap(moves);
    }

    void evoluer(String _nomEvo,String _capacite,StringList _attaquesRetenues,DataBase _import){
        evoluerSansApprendreCapacite(_nomEvo,_attaquesRetenues,_import);
        evoluerSansApprendreAttaque(_nomEvo,_capacite,_import);
    }

    void evoluerSansApprendreCapacite(String _nomEvo,StringList _attaquesRetenues,DataBase _import){
        learnMovesWithoutEvolving(_attaquesRetenues,_import);
        evoluerSansApprendreAttaqueCapacite(_nomEvo,_import);
    }

    void evoluerSansApprendreAttaqueCapacite(String _nomEvo,DataBase _import){
        PokemonData fPk_=_import.getPokemon(_nomEvo);
        evoluerSansApprendreAttaque(_nomEvo,fPk_.getAbilities().first(),_import);
    }

    void evoluerSansApprendreAttaque(String _nomEvo,String _capacite,DataBase _import){
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
        fullHeal(_import);
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
        action_.setChosenTargets(new EqList<TargetCoords>(_cible));
        action_.setSubstitute(BACK);
        action_.setFirstChosenMove(_attaque);
        action_.setFinalChosenMove(DataBase.EMPTY_STRING);
        action = action_;
    }

    void setFirstChosenMoveTargetSubstitute(String _attaque, TargetCoords _cible, byte _remplacant){
        ActionMove action_ = new ActionMove();
        action_.setChosenTargets(new EqList<TargetCoords>(_cible));
        action_.setSubstitute(_remplacant);
        action_.setFirstChosenMove(_attaque);
        action_.setFinalChosenMove(DataBase.EMPTY_STRING);
        action = action_;
    }

    void setFirstChosenMove(String _attaque){
        ActionMove action_ = new ActionMove();
        action_.setChosenTargets(new EqList<TargetCoords>());
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
        action = new Action();
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
            enabledMovesForAlly.put(e, false);
        }
    }

    void incrementRoundsStatus(String _nomStatut) {
        status.put(_nomStatut, (short) (status.getVal(_nomStatut)+1));
    }

    void clearMessages() {
        comment.clearMessages();
    }

    public void setSubstituteForMove(byte _remplacant) {
        if (action instanceof ChosenReplacing) {
            ((ChosenReplacing)action).setSubstitute(_remplacant);
        }
    }

    public void setSubstitute(byte _remplacant) {
        ActionSwitch action_ = new ActionSwitch();
        action_.setSubstitute(_remplacant);
        action = action_;
    }

    public static Rate statistiqueGlobale(EnumMap<Statistic,Rate> _statistiquesBase,Statistic _nomStat,short _ev,short _iv, short _level){
        return PokemonData.stat(_level, _statistiquesBase.getVal(_nomStat), _nomStat, _ev, _iv);
    }

    public Rate statistiqueGlobaleEvIv(Statistic _nomStat){
        return statistiqueGlobale(statisBase,_nomStat,ev.getVal(_nomStat),iv.getVal(_nomStat), level);
    }

    public Rate pvMax(){
        return statistiqueGlobaleEvIv(Statistic.HP);
    }
    public boolean estAssexue(){
        return currentGender==Gender.NO_GENDER;
    }

    public boolean capaciteActive(){
        return !currentAbility.isEmpty();
    }

    boolean canDisableWeather(DataBase _import) {
        if(estArriere()){
            return false;
        }
        if(!capaciteActive()){
            return false;
        }
        AbilityData fCapac_=ficheCapaciteActuelle(_import);
        if(!fCapac_.enabledSending()){
            return false;
        }
        EffectWhileSending effetEnvoi_=fCapac_.getEffectSending().first();
        if(!effetEnvoi_.getDisableWeather()){
            return false;
        }
        return true;
    }

    boolean noPowerPointForLastUsedMove() {
        boolean pasPpAttaqueCible_=false;
        if(usedMoveLastRound.isEmpty()){
            pasPpAttaqueCible_=true;
        }else if(!attaquesUtilisables().containsObj(usedMoveLastRound)){
            pasPpAttaqueCible_=true;
        }else if(powerPointsMove(usedMoveLastRound) == 0){
            pasPpAttaqueCible_=true;
        }
        return pasPpAttaqueCible_;
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
        if(!StringList.quickEq(usedMoveLastRound,_move)) {
            return true;
        }
        return false;
    }

    public AbilityData ficheCapaciteActuelle(DataBase _import){
        return _import.getAbility(currentAbility);
    }

    public AbilityData ficheCapacite(DataBase _import){
        return _import.getAbility(ability);
    }

    public PokemonData fichePokemonActuelle(DataBase _import){
        return _import.getPokemon(currentName);
    }

    public PokemonData fichePokemon(DataBase _import){
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

    StringList enabledIndividualImmuMoves() {
        StringList list_ = new StringList();
        for(String m:enabledMovesProt.getKeys()){
            if(!enabledMovesProt.getVal(m).isEnabled()){
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

    EqList<MoveTeamPosition> enabledRelationsMoves() {
        EqList<MoveTeamPosition> list_ = new EqList<MoveTeamPosition>();
        for(MoveTeamPosition m:trackingMoves.getKeys()){
            if(!trackingMoves.getVal(m).getActivity().isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    EqList<MoveTeamPosition> enabledRelationsTraps() {
        EqList<MoveTeamPosition> list_ = new EqList<MoveTeamPosition>();
        for(MoveTeamPosition m:trappingMoves.getKeys()){
            if(!trappingMoves.getVal(m).isEnabled()){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    public AffectedMove refPartAttaquesSurCombatAtt(MoveTeamPosition _c) {
        return trackingMoves.getVal(_c);
    }

    NatTreeMap<String,Boolean> getMoves(String _evo) {
        NatTreeMap<String,Boolean> map_ = new NatTreeMap<String,Boolean>();
        for (String m: moves.getKeys()) {
            map_.put(m, true);
        }
        if (!_evo.isEmpty()) {
            for (String m: movesAbilitiesEvos.getVal(_evo).getMoves()) {
                map_.put(m, false);
            }
        } else {
            for (String m: movesToBeLearnt) {
                map_.put(m, false);
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

    public String getChosenHealingItem() {
        if (action instanceof ActionHeal) {
            return ((ActionHeal)action).getChosenHealingItem();
        }
        return DataBase.EMPTY_STRING;
    }

    public String getFinalChosenMove() {
        if (action instanceof ActionMove) {
            return ((ActionMove)action).getFinalChosenMove();
        }
        return DataBase.EMPTY_STRING;
    }

    public EqList<TargetCoords> getChosenTargets() {
        if (action instanceof ActionMove) {
            return ((ActionMove)action).getChosenTargets();
        }
        return new EqList<TargetCoords>();
    }

    public byte getSubstistute() {
        if (action instanceof ChosenReplacing) {
            return ((ChosenReplacing)action).getSubstitute();
        }
        return BACK;
    }

    /**Method for test*/
    public void setRemainedHp(Rate _hp) {
        remainingHp.affect(_hp);
    }

    boolean isKoAt(byte _index) {
        return getGroundPlaceSubst() == _index && estKo();
    }

    Rate getVarHp() {
        return varHp;
    }

    void setVarHp(Rate _varHp) {
        varHp = _varHp;
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

    public short getStatusNbRoundShort(String _status) {
        return status.getVal(_status).shortValue();
    }

    public Short getStatusNbRound(String _status) {
        return status.getVal(_status);
    }

    int getNbStatusByRounds(short _nbRounds) {
        int i_ = CustList.SIZE_EMPTY;
        for (EntryCust<String, Short> e: status.entryList()) {
            if (Numbers.eq(e.getValue().shortValue(), _nbRounds)) {
                i_++;
            }
        }
        return i_;
    }

    StringMap<Short> getStatus() {
        return status;
    }

    public StringList getStatusSet() {
        return status.getKeys();
    }

    public boolean isSingleStatus(String _status) {
        return status.contains(_status);
    }

    void setStatus(StringMap<Short> _status) {
        status = _status;
    }

    public short getStatusRelatNbRoundShort(MoveTeamPosition _status) {
        return statusRelat.getVal(_status).shortValue();
    }

    public Short getStatusRelatNbRound(MoveTeamPosition _status) {
        return statusRelat.getVal(_status);
    }

    public boolean isStatusRelat(MoveTeamPosition _status) {
        return statusRelat.contains(_status);
    }

    int getNbStatusRelatByRounds(short _nbRounds) {
        int i_ = CustList.SIZE_EMPTY;
        for (EntryCust<MoveTeamPosition, Short> e: statusRelat.entryList()) {
            if (Numbers.eq(e.getValue().shortValue(), _nbRounds)) {
                i_++;
            }
        }
        return i_;
    }

    ObjectMap<MoveTeamPosition,Short> getStatusRelat() {
        return statusRelat;
    }

    public EqList<MoveTeamPosition> getStatusRelatSet() {
        return statusRelat.getKeys();
    }

    void setStatusRelat(ObjectMap<MoveTeamPosition,Short> _statusRelat) {
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

    public StringList getMovesSet() {
        return moves.getKeys();
    }

    StringMap<UsesOfMove> getMoves() {
        return moves;
    }

    void setMoves(StringMap<UsesOfMove> _moves) {
        moves = _moves;
    }

    public UsesOfMove getCurrentMove(String _move) {
        return currentMoves.getVal(_move);
    }

    public StringList getCurrentMovesSet() {
        return currentMoves.getKeys();
    }

    StringMap<UsesOfMove> getCurrentMoves() {
        return currentMoves;
    }

    void setCurrentMoves(StringMap<UsesOfMove> _currentMoves) {
        currentMoves = _currentMoves;
    }

    public EnumMap<Statistic,Short> getEv() {
        return ev;
    }

    public void setEv(EnumMap<Statistic,Short> _ev) {
        ev = _ev;
    }

    public EnumMap<Statistic,Short> getIv() {
        return iv;
    }

    public void setIv(EnumMap<Statistic,Short> _iv) {
        iv = _iv;
    }

    public EnumMap<Statistic,Rate> getStatisBase() {
        return statisBase;
    }

    public void setStatisBase(EnumMap<Statistic,Rate> _statisBase) {
        statisBase = _statisBase;
    }

    public EnumMap<Statistic,Byte> getStatisBoost() {
        return statisBoost;
    }

    public void setStatisBoost(EnumMap<Statistic,Byte> _statisBoost) {
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

    public StringMap<Boolean> getEnabledMovesForAlly() {
        return enabledMovesForAlly;
    }

    public void setEnabledMovesForAlly(
            StringMap<Boolean> _attaquesActivesPartenaireTour) {
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

    public byte getGroundPlace() {
        return groundPlace;
    }

    public void setGroundPlace(byte _groundPlace) {
        groundPlace = _groundPlace;
    }

    public boolean isBackGroundPlaceSubst() {
        return groundPlaceSubst == BACK;
    }

    public byte getGroundPlaceSubst() {
        return groundPlaceSubst;
    }

    public void setGroundPlaceSubst(byte _groundPlaceSubst) {
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

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
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

    public ObjectMap<MoveTeamPosition,Boolean> getIncrUserAccuracy() {
        return incrUserAccuracy;
    }

    public void setIncrUserAccuracy(
            ObjectMap<MoveTeamPosition,Boolean> _precisionAccrueLanceur) {
        incrUserAccuracy = _precisionAccrueLanceur;
    }

    public StringMap<Integer> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public void setNbUsesMoves(StringMap<Integer> _nbUsesMoves) {
        nbUsesMoves = _nbUsesMoves;
    }

    public short getNbPrepaRound() {
        return nbPrepaRound;
    }

    public void setNbPrepaRound(short _nbPrepaRound) {
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

    public ObjectMap<MoveTeamPosition,AffectedMove> getTrackingMoves() {
        return trackingMoves;
    }

    public void setTrackingMoves(
            ObjectMap<MoveTeamPosition,AffectedMove> _attaquesSurCombatAtt) {
        trackingMoves = _attaquesSurCombatAtt;
    }

    public ObjectMap<MoveTeamPosition,ActivityOfMove> getTrappingMoves() {
        return trappingMoves;
    }

    public void setTrappingMoves(
            ObjectMap<MoveTeamPosition,ActivityOfMove> _attaquesPiegeantes) {
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

    public StringMap<Boolean> getEnabledImmuAbilities() {
        return enabledImmuAbilities;
    }

    public void setEnabledImmuAbilities(StringMap<Boolean> _enabledImmuAbilities) {
        enabledImmuAbilities = _enabledImmuAbilities;
    }

    public ObjectMap<MoveTeamPosition,StringList> getPrivateMoves() {
        return privateMoves;
    }

    public void setPrivateMoves(ObjectMap<MoveTeamPosition,StringList> _privateMoves) {
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

    public Comment getComment() {
        return comment;
    }

    void setComment(Comment _comment) {
        comment = _comment;
    }
}
