package aiki.game.fight;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.game.fight.animations.*;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.MoveTarget;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.MoveTargets;
import aiki.util.TeamPositionList;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;


public final class Fight {

    public static final byte CST_PLAYER = 0;

    /***/
    public static final byte CST_FOE = 1;

//    public static final String FIGHT_ACCESS = "aiki.game.fight.fight";

    public static final String ZERO = "0";
    public static final String ONE = "1";

    public static final String ABILITY_END_ROUND = "0";
    public static final String AFF_STATUS = "1";
    public static final String AFF_STATUS_REL = "2";
    public static final String BATON_PASS = "3";
    public static final String CANNOT_USE_MOVE_STATUS = "4";
    public static final String CANNOT_USE_MOVE_STATUS_REL = "5";
    public static final String CHANGED_ABILITY = "6";
    public static final String CHANGED_ABILITY_DISABLED = "7";
    public static final String CHANGED_TYPES = "8";
    public static final String CHANGING_VIEW_POINT_TARGET = "9";
    public static final String CHANGING_VIEW_POINT_USER = "10";
    public static final String CLONE_DAMAGE = "11";
    public static final String CLONE_ZERO = "12";
    public static final String COMBO_MOVE_END_ROUND = "13";
    public static final String COMBO_MOVE_END_ROUND_FOE = "14";
    public static final String COMMON_STATISTIC = "15";
    public static final String COPY_FIGHTER = "16";
    public static final String CREATE_CLONE = "17";
    public static final String CRITICAL_HIT = "18";
    public static final String DISABLED_FOE_TEAM_MOVE = "19";
    public static final String DISABLED_FOE_TEAM_USES_MOVE = "20";
    public static final String DISABLED_MOVE = "21";
    public static final String DISABLED_MOVE_REL = "22";
    public static final String DISABLED_STATUS = "23";
    public static final String DISABLED_STATUS_REL = "24";
    public static final String DISABLED_STATUS_REL_OTHER = "25";
    public static final String DISABLED_TEAM_MOVE = "26";
    public static final String DISABLED_TEAM_USES_MOVE = "27";
    public static final String DISABLED_WEATHER = "28";
    public static final String DISABLE_ITEM = "29";
    public static final String DISAPPEARED = "30";
    public static final String ENABLED_ABILITY = "31";
    public static final String ENABLED_FOE_TEAM_MOVE = "32";
    public static final String ENABLED_MOVE = "33";
    public static final String ENABLED_MOVE_REL = "34";
    public static final String ENABLED_TEAM_MOVE = "35";
    public static final String ENABLED_WEATHER = "36";
    public static final String ERR_BACK_MOVE = "37";
    public static final String ERR_BACK_SWITCH = "38";
    public static final String ERR_BAD_CHOICE = "39";
    public static final String ERR_BELONG_SWITCH = "40";
    public static final String ERR_EVOLVING = "41";
    public static final String ERR_EVOLVING_AB = "42";
    public static final String ERR_FRONT_SWITCH = "43";
    public static final String ERR_KO_SUBSTITUTE = "44";
    public static final String ERR_NO_CHOSEN_TARGET = "45";
    public static final String ERR_NO_EFFECT = "46";
    public static final String ERR_NO_HEALED_MOVE = "47";
    public static final String ERR_NO_ITEM = "48";
    public static final String ERR_SUBSTITUTE = "49";
    public static final String ERR_SUBSTITUTE_BELONG = "50";
    public static final String ERR_SUBSTITUTE_KO_END_ROUND = "51";
    public static final String ERR_SUBSTITUTE_NO_SWITCH_PLACE = "52";
    public static final String ERR_SUBSTITUTE_PLACE = "53";
    public static final String ERR_SUBSTITUTE_USED_PLACE = "54";
    public static final String ERR_SWITCH = "55";
    public static final String ERR_TOO_FAR_TARGET = "56";
    public static final String ERR_TOO_FEW_ACTIONS = "57";
    public static final String ERR_TOO_MANY_ACTIONS = "58";
    public static final String ERR_TOO_MANY_ITEMS = "59";
    public static final String ERR_UNUSABLE_MOVE = "60";
    public static final String FAIL_MOVE = "61";
    public static final String FIGHTER_ALLY = "62";
    public static final String FIGHTER_FOE = "63";
    public static final String FIGHT_EVOLUTION = "64";
    public static final String FIRST_MOVE = "65";
    public static final String FORGET_MOVE_EVOLUTION = "66";
    public static final String GLOBAL_MOVE_END_ROUND = "67";
    public static final String HELP_ALLY = "68";
    public static final String IMMU_CHGT_STAT_MAX = "69";
    public static final String IMMU_CHGT_STAT_MIN = "70";
    public static final String IMMU_LOW_STAT_ABILITY = "71";
    public static final String IMMU_LOW_STAT_ABILITY_ALLY = "72";
    public static final String IMMU_LOW_STAT_ITEM = "73";
    public static final String IMMU_LOW_STAT_ST_ABILITY = "74";
    public static final String IMMU_LOW_STAT_TEAM = "75";
    public static final String IMMU_STAT_ABILITY = "76";
    public static final String IMMU_STAT_ABILITY_ALLY = "77";
    public static final String IMMU_STAT_GLOBAL_MOVE = "78";
    public static final String IMMU_STAT_GLOBAL_MOVE_ABILITY = "79";
    public static final String IMMU_STAT_ITEM = "80";
    public static final String IMMU_STAT_TEAM = "81";
    public static final String INCR_FOE_TEAM_USES_MOVE = "82";
    public static final String INCR_STATUS = "83";
    public static final String INCR_STATUS_REL = "84";
    public static final String INCR_TEAM_USES_MOVE = "85";
    public static final String INVOKE_MOVE = "86";
    public static final String INVOKE_MOVE_FAIL = "87";
    public static final String ITEM_END_ROUND = "88";
    public static final String KEEP_MOVE_EVOLUTION = "89";
    public static final String KO_FIGHTER = "90";
    public static final String LEARN_MOVE_EVOLUTION = "91";
    public static final String LEARN_MOVE_ROUND = "92";
    public static final String LEARN_MOVE_ROUND_DEF = "93";
    public static final String LOOSE_HP = "94";
    public static final String LOOSE_ITEM = "95";
    public static final String MOVE_END_ROUND = "96";
    public static final String MOVE_END_ROUND_REL = "97";
    public static final String MOVE_TYPES = "98";
    public static final String NB_HITS = "99";
    public static final String NO_ACHIEVE_TARGET = "100";
    public static final String NO_PRIVATE_MOVES = "101";
    public static final String NO_VAR_HP = "102";
    public static final String PREPA_ROUND = "103";
    public static final String PRIVATE_MOVES = "104";
    public static final String PROTECTED_AGAINST_SEC_EFF = "105";
    public static final String PROTECTED_BY_DISAPPEARING = "106";
    public static final String PROTECTED_BY_INDIVIDUAL_MOVE = "107";
    public static final String PROTECTED_BY_TEAM_MOVE = "108";
    public static final String PROTECT_BY_ABILITY = "109";
    public static final String PROTECT_BY_ABILITY_DAMAGE = "110";
    public static final String PROTECT_BY_ABILITY_DAMAGE_ALLY = "111";
    public static final String PROTECT_BY_ALLY_ABILITY = "112";
    public static final String PROTECT_BY_ITEM = "113";
    public static final String PROTECT_TYPE_BY_ABILITY = "114";
    public static final String PROTECT_TYPE_BY_ABILITY_WEATHER = "115";
    public static final String PROTECT_TYPE_BY_GLOBAL_MOVE = "116";
    public static final String PROTECT_TYPE_BY_INDIVIDUAL_MOVE = "117";
    public static final String PROTECT_TYPE_BY_ITEM = "118";
    public static final String RECHARGE_ROUND = "119";
    public static final String RE_ENABLE_ITEM = "120";
    public static final String SEND = "121";
    public static final String SEND_SUBSTITUTE = "122";
    public static final String SEND_SUBSTITUTE_FOE = "123";
    public static final String STATUS_BEGIN_ROUND = "124";
    public static final String STATUS_BEGIN_ROUND_REL = "125";
    public static final String STATUS_END_ROUND = "126";
    public static final String STATUS_REL_END_ROUND = "127";
    public static final String SUCCESSFUL_MOVE = "128";
    public static final String SUCCESSFUL_MOVE_BUT_NO_DAMAGE = "129";
    public static final String SWITCH_ITEMS = "130";
    public static final String SWITCH_PLACES = "131";
    public static final String VAR_PP_EFFECT = "132";
    public static final String VAR_STATISTIC = "133";
    public static final String WEATHER_INCR = "134";
    public static final String WIN_HP = "135";
    public static final String WIN_ITEM = "136";
    public static final String WITHDRAW = "137";
    public static final String ISSUE_AFTER_FIGHT = "138";
    public static final String ISSUE_RANDOM = "139";
    public static final String ISSUE_RULES_LEARN = "140";
    public static final String ISSUE_RULES_MOVES = "141";
    public static final String ISSUE_RULES_SWITCH = "142";
    public static final String ISSUE_SENDING = "143";
    public static final String ISSUE_TOO_HARD = "144";
    public static final String ISSUE_USING = "145";

    private static final String SEPARATOR_COMMENTS = ", ";
//    private boolean utilisationBaieLanceur;
//    private boolean tombeKo;

    /***/
    private FightType fightType;

    /***/
    private EnvironmentType envType;

    /***/
    private byte mult;

    /***/
    private byte playerMaxNumberFrontFighters;

    /***/
    //private Map<String,TeamPosition> lanceursGlobaux;

    /***/
    private StringMap<ActivityOfMove> enabledMoves;

    /***/
    private StringMap<BoolVal> stillEnabledMoves;

    /***/
    private ByteMap<Team> teams;

    /**variable sur un tour*/
//    private boolean fullHealing;

    /***/
    private short nbFleeAttempt;

    /***/
    private LgInt nbRounds;

    /***/
    private Rate winningMoney;

    /***/
//    private String catchingBall;
    private CustList<CatchingBallFoeAction> catchingBalls;

//    private boolean error;

    /***/
//    private NbEffectFighterCoordss successfulEffects = new NbEffectFighterCoordss();

    /***/
//    private Comment comment = new Comment();

    /***/
    //private boolean entres = true;

    /***/
//    private TeamPositionsRate damageByCurrentUser = new TeamPositionsRate();

    /***/
//    private ByteMap<BoolVal> kos = new ByteMap<BoolVal>();

    /***/
//    private StringList sufferingTargetStatus = new StringList();

    /***/
//    private boolean lettingUserAttackWithStatus = true;

    /***/
//    private boolean endRound;

    /***/
    private TeamPosition currentUser;

    /***/
    private byte currentUserFlee = Fighter.BACK;

    /***/
    private FightState state;

    /***/
    private StringMap<Short> usedItemsWhileRound;

    /***/
//    private boolean simulation;

    /**All keys are the player + ally fighters keys*/
    private ByteMap<Byte> firstPositPlayerFighters;

    /**All keys are the foe fighters keys*/
    private ByteMap<Byte> firstPositFoeFighters;

    /***/
    private MoveTargets allyChoice;

    /***/
    private StringList lostObjects;

    /***/
    private ByteMap<ChoiceOfEvolutionAndMoves> choices;

    /***/
    private StringList caughtEvolutions;

    /***/
//    private boolean enabledMessages = true;

    /***/
//    private boolean acceptableChoices = true;

    /***/
//    private IssueSimulation issue = IssueSimulation.NOTHING;

    /***/
//    private boolean invokedMove;
//
//    /***/
//    private boolean sending;
//
//    /***/
//    private boolean keepStatus;
//
//    /***/
//    private boolean enabledHealingPartner;
//
//    /***/
//    private boolean changeThrower;
//
//    /***/
//    private boolean successfulInvokation;
//
//    /***/
//    private boolean successfulUse;
//
//    /***/
//    private boolean putKo;
//
//    /***/
//    private DamageMoveCountUser damage = new DamageMoveCountUser();
//
//    /***/
//    private Rate damageKo = Rate.zero();

//    /***/
//    private TeamPositionList orderedFighters = new TeamPositionList();
//
//    /***/
//    private TeamPositionList remainingFighters = new TeamPositionList();
//
//    /***/
//    private CustList<ChosableTargetName> chosablePlayerTargets = new CustList<ChosableTargetName>();
//
//    /***/
//    private CustList<ChosableTargetName> chosableFoeTargets = new CustList<ChosableTargetName>();

    /***/
//    private byte chosenPlayerTarget = Fighter.BACK;

    /***/
//    private byte chosenFoeTarget = Fighter.BACK;
//
//    /***/
//    private byte chosenIndexFront = Fighter.BACK;
//
//    /***/
//    private byte chosenIndexBack = Fighter.BACK;
//
//    /***/
//    private IdList<ActionType> possibleActionsCurFighter = new IdList<ActionType>();
//
//    /***/
//    private ActionType selectedActionCurFighter = ActionType.NOTHING;
//
//    /***/
//    private NatStringTreeMap<ChosenMoveInfos> currentFighterMoves = new NatStringTreeMap<ChosenMoveInfos>();
//
//    /***/
//    private String chosenMoveFront = DataBase.EMPTY_STRING;
//
//    /***/
//    private String chosenHealingMove = DataBase.EMPTY_STRING;
//
//    /***/
//    private byte chosenSubstitute = Fighter.BACK;
//
//    /***/
//    private byte chosenIndex = Fighter.BACK;
//
//    /***/
//    private NatStringTreeMap<BoolVal> moves = new NatStringTreeMap<BoolVal>();
//
//    /***/
//    private EvolutionChoiceMap evolutions = new EvolutionChoiceMap(new NaturalComparator());
//
//    /***/
//    private StringList abilities = new StringList();
//
//    /***/
//    private String ability = DataBase.EMPTY_STRING;
//
//    /***/
//    private ActivityOfMove currentActivity;
//
//    /***/
//    private boolean keepRound = true;
//
//    /***/
//    private boolean endRoundFightKoPlayer = true;
//
//    /***/
//    private CustList<AnimationInt> effects = new CustList<AnimationInt>();

    private final TransientFight temp;
    public Fight() {
        temp = new TransientFight();
//        damage.setDamage(Rate.zero());
//        damage.setDamageClone(Rate.zero());
//        damage.setDamageCount(Rate.zero());
    }

    public Fighter wildPokemon(){
        return getFoeTeam().getMembers().getVal((byte)0);
    }
    public Team getFoeTeam() {
        return teams.getVal(CST_FOE);
    }
    public Team getUserTeam() {
        return teams.getVal(CST_PLAYER);
    }

    public static byte foe(byte _eq){
        if(NumberUtil.eq(_eq, CST_PLAYER)){
            return CST_FOE;
        }
        return CST_PLAYER;
    }

    public static TeamPosition toFoeFighter(byte _pos){
        return new TeamPosition(CST_FOE,_pos);
    }

    public static TeamPosition toUserFighter(byte _pos){
        return new TeamPosition(CST_PLAYER,_pos);
    }

    String getFighterName(TeamPosition _teamPosition, DataBase _import) {
        Fighter fighter_ = getFighter(_teamPosition);
        String name_ = _import.translatePokemon(fighter_.getName());
        if (fighter_.isBelongingToPlayer()) {
            return name_;
        }
        return _import.getFighterName(NumberUtil.eq(_teamPosition.getTeam(), CST_FOE),FIGHTER_FOE,FIGHTER_ALLY,name_);
    }

    public TeamPosition getFighterKey(TargetCoords _targetCoords) {
        Team team_ = teams.getVal((byte) _targetCoords.getTeam());
        Bytes keys_ = team_.fightersAtCurrentPlace(_targetCoords);
        if (!keys_.isEmpty()) {
            return new TeamPosition((byte) _targetCoords.getTeam(), keys_.first());
        }
        return new TeamPosition();
    }

    public FighterId getFighterId(TeamPosition _teamPosition) {
        Fighter f_ = getFighter(_teamPosition);
        return new FighterId(f_,_teamPosition);
    }

    public Fighter getFighter(int _t, int _f) {
        return getFighter(new TeamPosition((byte) _t,(byte) _f));
    }
    public Fighter getFighter(TeamPosition _teamPosition) {
        return teams.getVal(_teamPosition.getTeam()).getMembers().getVal(_teamPosition.getPosition());
    }

    TeamPositionList sortedTeamMembersByPosition(byte _team) {
        Bytes positions_ = new Bytes();
        for (byte p: teams.getVal(_team).getMembers().getKeys()) {
            positions_.add(p);
        }
        positions_.sort();
        TeamPositionList list_ = new TeamPositionList();
        for (byte p: positions_) {
            list_.add(new TeamPosition(_team, p));
        }
        return list_;
    }

    void enableGlobalMove(String _move) {
        enabledMoves.getVal(_move).enable();
    }

    void disableGlobalMove(String _move) {
        enabledMoves.getVal(_move).disable();
    }

    public boolean getBeginRound() {
        if (state == FightState.ATTAQUES) {
            return true;
        }
        if (state == FightState.SWITCH_PROPOSE) {
            return true;
        }
        if (state == FightState.APPRENDRE_EVOLUER) {
            return true;
        }
        return state == FightState.SWITCH_WHILE_KO_USER;
    }

    public FightType getFightType() {
        return fightType;
    }

    public void setFightType(FightType _fightType) {
        fightType = _fightType;
    }

    public EnvironmentType getEnvType() {
        return envType;
    }

    public void setEnvType(EnvironmentType _envType) {
        envType = _envType;
    }

    public byte getMult() {
        return mult;
    }

    public void setMult(byte _mult) {
        mult = _mult;
    }

    public byte getPlayerMaxNumberFrontFighters() {
        return playerMaxNumberFrontFighters;
    }

    public void setPlayerMaxNumberFrontFighters(byte _playerMaxNumberFrontFighters) {
        playerMaxNumberFrontFighters = _playerMaxNumberFrontFighters;
    }

    public StringMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public void setEnabledMoves(StringMap<ActivityOfMove> _enabledMoves) {
        enabledMoves = _enabledMoves;
    }

    public StringMap<BoolVal> getStillEnabledMoves() {
        return stillEnabledMoves;
    }

    public void setStillEnabledMoves(StringMap<BoolVal> _stillEnabledMoves) {
        stillEnabledMoves = _stillEnabledMoves;
    }

    public ByteMap<Team> getTeams() {
        return teams;
    }

    public void setTeams(ByteMap<Team> _teams) {
        teams = _teams;
    }

    public short getNbFleeAttempt() {
        return nbFleeAttempt;
    }

    public void setNbFleeAttempt(short _nbFleeAttempt) {
        nbFleeAttempt = _nbFleeAttempt;
    }

    public LgInt getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(LgInt _nbRounds) {
        nbRounds = _nbRounds;
    }

    public Rate getWinningMoney() {
        return winningMoney;
    }

    public void setWinningMoney(Rate _winningMoney) {
        winningMoney = _winningMoney;
    }

    public CustList<CatchingBallFoeAction> getCatchingBalls() {
        return catchingBalls;
    }

    public void setCatchingBalls(CustList<CatchingBallFoeAction> _c) {
        this.catchingBalls = _c;
    }

    public TeamPosition getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(TeamPosition _currentUser) {
        currentUser = _currentUser;
    }

    public byte getCurrentUserFlee() {
        return currentUserFlee;
    }

    public void setCurrentUserFlee(byte _c) {
        this.currentUserFlee = _c;
    }

    public FightState getState() {
        return state;
    }

    public void setState(FightState _state) {
        state = _state;
    }

    public StringMap<Short> getUsedItemsWhileRound() {
        return usedItemsWhileRound;
    }

    public void setUsedItemsWhileRound(StringMap<Short> _usedItemsWhileRound) {
        usedItemsWhileRound = _usedItemsWhileRound;
    }

    public ByteMap<Byte> getFirstPositPlayerFighters() {
        return firstPositPlayerFighters;
    }

    boolean isUsedAt(byte _place) {
        return !getFirstPositPlayerFightersByPlace(_place).isEmpty();
    }

    Bytes getFirstPositPlayerFightersByPlace(byte _place) {
        Bytes l_ = new Bytes();
        for (EntryCust<Byte, Byte> e: firstPositPlayerFighters.entryList()) {
            if (e.getValue() == _place) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public void setFirstPositPlayerFighters(ByteMap<Byte> _firstPositPlayerFighters) {
        firstPositPlayerFighters = _firstPositPlayerFighters;
    }

    public ByteMap<Byte> getFirstPositFoeFighters() {
        return firstPositFoeFighters;
    }

    public void setFirstPositFoeFighters(ByteMap<Byte> _firstPositFoeFighters) {
        firstPositFoeFighters = _firstPositFoeFighters;
    }

    public CustList<MoveTarget> getAllyChoiceSet() {
        return allyChoice.getKeys();
    }

    public Listable<MoveTarget> getAllyChoiceValuesSet() {
        return allyChoice.values();
    }

    public MoveTargets getAllyChoice() {
        return allyChoice;
    }

    public void setAllyChoice(MoveTargets _allyChoice) {
        allyChoice = _allyChoice;
    }

    public StringList getLostObjects() {
        return lostObjects;
    }

    public void setLostObjects(StringList _lostObjects) {
        lostObjects = _lostObjects;
    }

    public ByteMap<ChoiceOfEvolutionAndMoves> getChoices() {
        return choices;
    }

    public void setChoices(ByteMap<ChoiceOfEvolutionAndMoves> _choices) {
        choices = _choices;
    }

//    public byte getChosenPlayerTarget() {
//        return chosenPlayerTarget;
//    }
//
//    void setChosenPlayerTarget(byte _chosenPlayerTarget) {
//        chosenPlayerTarget = _chosenPlayerTarget;
//    }
//
//    public byte getChosenFoeTarget() {
//        return chosenFoeTarget;
//    }
//
//    void setChosenFoeTarget(byte _chosenFoeTarget) {
//        chosenFoeTarget = _chosenFoeTarget;
//    }

    public StringList getCaughtEvolutions() {
        return caughtEvolutions;
    }

    public void setCaughtEvolutions(StringList _caughtEvolutions) {
        caughtEvolutions = _caughtEvolutions;
    }

    public CustList<AnimationInt> getEffects() {
        return getTemp().getEffects();
    }

    void addEffectStatus(TeamPosition _user, TeamPosition _target) {
        AnimationEffect anim_;
        anim_ = new AnimationEffectStatus();
        Fighter user_ = getFighter(_user);
        Fighter target_ = getFighter(_target);
//        anim_.setFromFighter(new TargetCoords(_user.getTeam(), user_.getGroundPlace()));
//        anim_.setToFighter(new TargetCoords(_target.getTeam(), target_.getGroundPlace()));
        anim_.setFromFighter(new TargetCoords(_user.getTeam(), user_.getGroundPlaceSubst()));
        anim_.setToFighter(new TargetCoords(_target.getTeam(), target_.getGroundPlaceSubst()));
        anim_.setIndex(getTemp().getEffects().size());
        getTemp().getEffects().add(anim_);
    }

    void addEffectAbsorb(TeamPosition _from, TeamPosition _to) {
        AnimationEffect anim_;
        anim_ = new AnimationEffect(EffectKind.ABSORB);
        Fighter from_ = getFighter(_from);
        Fighter to_ = getFighter(_to);
//        if (!from_.estKo()) {
//            anim_.setFromFighter(new TargetCoords(_from.getTeam(), from_.getGroundPlace()));
//        } else {
//            anim_.setFromFighter(new TargetCoords(_from.getTeam(), from_.getGroundPlaceSubst()));
//        }
        anim_.setFromFighter(new TargetCoords(_from.getTeam(), from_.getGroundPlaceSubst()));
        anim_.setKoFromFighter(from_.estKo());
//        if (!to_.estKo()) {
//            anim_.setToFighter(new TargetCoords(_to.getTeam(), to_.getGroundPlace()));
//        } else {
//            anim_.setToFighter(new TargetCoords(_to.getTeam(), to_.getGroundPlaceSubst()));
//        }
        anim_.setToFighter(new TargetCoords(_to.getTeam(), to_.getGroundPlaceSubst()));
        anim_.setKoToFighter(to_.estKo());
        anim_.setIndex(getTemp().getEffects().size());
        getTemp().getEffects().add(anim_);
    }

    void addEffectRecoil(TeamPosition _target, EffectDamageRate _effect) {
        if (_effect.getRateDamage().isZeroOrGt()) {
            return;
        }
        addEffectRecoil(_target);
    }

    void addEffectRecoil(TeamPosition _target) {
        AnimationAutoEffect anim_;
        anim_ = new AnimationAutoEffect(AutoEffectKind.RECOIL);
        anim_.setIndex(getTemp().getEffects().size());
        Fighter target_ = getFighter(_target);
//        if (target_.estKo()) {
//            anim_.setUser(new TargetCoords(_target.getTeam(), target_.getGroundPlaceSubst()));
//        } else {
//            anim_.setUser(new TargetCoords(_target.getTeam(), target_.getGroundPlace()));
//        }
        anim_.setUser(new TargetCoords(_target.getTeam(), target_.getGroundPlaceSubst()));
        anim_.setKoUser(target_.estKo());
        getTemp().getEffects().add(anim_);
    }

    void addEffect(TeamPosition _user, TeamPosition _target, Effect _effect) {
        if (_effect instanceof EffectDamageRate) {
            return;
        }
        if (_effect instanceof EffectBatonPass) {
            return;
        }
        AnimationEffect anim_;
        if (_effect instanceof EffectDamage) {
            anim_ = new AnimationEffectDamage();
        } else if (_effect instanceof EffectStatistic) {
            anim_ = new AnimationEffectStatistic();
        } else if (_effect instanceof EffectStatus) {
            anim_ = new AnimationEffectStatus();
        } else if (_effect instanceof EffectSwitchPosition) {
            anim_ = new AnimationEffect(EffectKind.CHANGED_PLACE);
        } else {
            anim_ = new AnimationEffect();
        }
        Fighter user_ = getFighter(_user);
        Fighter target_ = getFighter(_target);
//        anim_.setFromFighter(new TargetCoords(_user.getTeam(), user_.getGroundPlace()));
//        anim_.setToFighter(new TargetCoords(_target.getTeam(), target_.getGroundPlace()));
        anim_.setIndex(getTemp().getEffects().size());
        anim_.setFromFighter(new TargetCoords(_user.getTeam(), user_.getGroundPlaceSubst()));
        anim_.setToFighter(new TargetCoords(_target.getTeam(), target_.getGroundPlaceSubst()));
        getTemp().getEffects().add(anim_);
    }

    void addAnimationStatistic(Statistic _st, byte _variation, boolean _swap) {
        CustList<AnimationInt> list_ = getEffects();
        if (list_.isEmpty()) {
            return;
        }
        AnimationEffectStatistic anim_ = (AnimationEffectStatistic) list_.last();
        InfosAnimationStatistic info_ = new InfosAnimationStatistic();
        info_.setStatistic(_st);
        info_.setVariation(_variation);
        info_.setSwap(_swap);
        anim_.getInfos().add(info_);
    }

    void setAnimationStatus(String _st) {
        CustList<AnimationInt> list_ = getEffects();
        if (list_.isEmpty()) {
            return;
        }
        AnimationEffectStatus anim_ = (AnimationEffectStatus) list_.last();
        anim_.setStatus(_st);
    }

    void setAnimationDamage(Rate _damage, StringList _types) {
        CustList<AnimationInt> list_ = getEffects();
        if (list_.isEmpty()) {
            return;
        }
        AnimationEffectDamage anim_ = (AnimationEffectDamage)list_.last();
        anim_.setDamage(_damage);
        anim_.getTypes().addAllElts(_types);
    }

    void setAnimationKoFighterEffectDamage(TeamPosition _user, EffectDamageRate _effect) {
        if (!_effect.getRateDamage().isZeroOrGt()) {
            return;
        }
        AnimationEffect anim_;
        anim_ = new AnimationEffect();
        Fighter user_ = getFighter(_user);
        Fighter target_ = getFighter(_user);
        anim_.setFromFighter(new TargetCoords(_user.getTeam(), user_.getGroundPlaceSubst()));
        anim_.setToFighter(new TargetCoords(_user.getTeam(), target_.getGroundPlaceSubst()));
        anim_.setKoFromFighter(true);
        anim_.setKoToFighter(true);
        anim_.setIndex(getTemp().getEffects().size());
        getTemp().getEffects().add(anim_);
    }

    void addAnimationKoFighter(TeamPosition _user) {
        AnimationAutoEffect animation_;
        animation_ = new AnimationAutoEffect(AutoEffectKind.KO);
        animation_.setIndex(getTemp().getEffects().size());
        Fighter user_ = getFighter(_user);
        animation_.setUser(new TargetCoords(_user.getTeam(), user_.getGroundPlaceSubst()));
        animation_.setKoUser(true);
        getTemp().getEffects().add(animation_);
    }

    void addAnimationHealthPoints(TeamPosition _fighter, Rate _var) {
        if (!_var.isZeroOrGt()) {
            addEffectRecoil(_fighter);
        } else if (!_var.isZero()) {
            Fighter fighter_ = getFighter(_fighter);
            AnimationHealing animationHeal_;
            animationHeal_ = new AnimationHealing();
            animationHeal_.setIndex(getEffects().size());
//            animationHeal_.setHealed(new TargetCoords(_fighter.getTeam(), fighter_.getGroundPlace()));
            animationHeal_.setHealed(new TargetCoords(_fighter.getTeam(), fighter_.getGroundPlaceSubst()));
            getEffects().add(animationHeal_);
        }
    }

    void setAnimationKoFighter(boolean _koUser, boolean _koTarget, Rate _damage) {
        CustList<AnimationInt> list_ = getEffects();
        if (list_.isEmpty()) {
            return;
        }
        AnimationEffectDamage animation_ = (AnimationEffectDamage) list_.last();
        animation_.setKoToFighter(_koTarget);
        animation_.setKoFromFighter(_koUser);
        if (!_damage.isZero()) {
            animation_.setDamage(_damage);
        }
    }

    void addHelpAllyMessage(TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, HELP_ALLY, name_, other_);

    }

    void addCreateCloneMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        Rate clone_ = getFighter(_fighter).getClone();
        addMessage(_import, CREATE_CLONE, name_, clone_.toNumberString());
    }

    void addImmuStatTeamMessage(TeamPosition _fighter, String _status, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String move_ = _import.translateMove(_move);
        addMessage(_import, IMMU_STAT_TEAM, name_, status_, move_);
    }

    void addImmuStatGlobalMoveAbilityMessage(TeamPosition _fighter, String _status, String _ability, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String ability_ = _import.translateAbility(_ability);
        String move_ = _import.translateMove(_move);
        addMessage(_import, IMMU_STAT_GLOBAL_MOVE_ABILITY, name_, status_, ability_, move_);
    }

    void addImmuStatItemMessage(TeamPosition _fighter, String _status, String _ability, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String ability_ = _import.translateItem(_ability);
        addMessage(_import, IMMU_STAT_ITEM, name_, status_, ability_);
    }

    void addImmuStatAbilityMessage(TeamPosition _fighter, String _status, String _ability, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, IMMU_STAT_ABILITY, name_, status_, ability_);
    }

    void addImmuStatAbilityAllyMessage(TeamPosition _fighter, String _status, String _ability, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, IMMU_STAT_ABILITY_ALLY, name_, status_, ability_);
    }

    void addImmuStatGlobalMoveMessage(TeamPosition _fighter, String _status, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        addMessage(_import, IMMU_STAT_GLOBAL_MOVE, name_, status_);
    }

    void addImmuChgtStatMaxMessage(TeamPosition _fighter, Statistic _statistic, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_CHGT_STAT_MAX, name_, statis_);
    }

    void addImmuChgtStatMinMessage(TeamPosition _fighter, Statistic _statistic, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_CHGT_STAT_MIN, name_, statis_);
    }

    void addImmuLowStatStAbilityMessage(TeamPosition _fighter, Statistic _statistic, String _ability, String _status, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String ability_ = _import.translateAbility(_ability);
        String statis_ = _import.translateStatistics(_statistic);
        String status_ = _import.translateStatus(_status);
        addMessage(_import, IMMU_LOW_STAT_ST_ABILITY, name_, statis_, ability_, status_);
    }

    void addImmuLowStatItemMessage(TeamPosition _fighter, Statistic _statistic, String _item, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String item_ = _import.translateItem(_item);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_LOW_STAT_ITEM, name_, statis_, item_);
    }

    void addImmuLowStatTeamMessage(TeamPosition _fighter, Statistic _statistic, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_LOW_STAT_TEAM, name_, statis_, move_);
    }

    void addImmuLowStatAbilityMessage(TeamPosition _fighter, Statistic _statistic, String _ability, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String ability_ = _import.translateAbility(_ability);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_LOW_STAT_ABILITY, name_, statis_, ability_);
    }

    void addImmuLowStatAbilityAllyMessage(TeamPosition _fighter, Statistic _statistic, String _ability, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String ability_ = _import.translateAbility(_ability);
        String statis_ = _import.translateStatistics(_statistic);
        addMessage(_import, IMMU_LOW_STAT_ABILITY_ALLY, name_, statis_, ability_);
    }

    void addProtectedByDisappearingMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, PROTECTED_BY_DISAPPEARING, name_);
    }

    void addProtectedByTeamMoveMessage(TeamPosition _fighter, String _move, String _protMove, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String protMove_ = _import.translateMove(_protMove);
        addMessage(_import, PROTECTED_BY_TEAM_MOVE, name_, move_, protMove_);
    }

    void addProtectedByIndividualMoveMessage(TeamPosition _fighter, String _move, String _protMove, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String protMove_ = _import.translateMove(_protMove);
        addMessage(_import, PROTECTED_BY_INDIVIDUAL_MOVE, name_, move_, protMove_);
    }

    void addProtectedAgainstSecEffMessage(TeamPosition _fighter, String _move, String _ability,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, PROTECTED_AGAINST_SEC_EFF, name_, move_, ability_);
    }

    void addProtectByItemMessage(TeamPosition _fighter, String _move, String _item,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String item_ = _import.translateItem(_item);
        addMessage(_import, PROTECT_BY_ITEM, name_, move_, item_);
    }

    void addProtectByAbilityMessage(TeamPosition _fighter, String _move, String _ability,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, PROTECT_BY_ABILITY, name_, move_, ability_);
    }

    void addProtectByAbilityDamageAllyMessage(TeamPosition _fighter, String _move, String _ability,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, PROTECT_BY_ABILITY_DAMAGE_ALLY, name_, move_, ability_);
    }

    void addProtectByAllyAbilityMessage(TeamPosition _fighter, String _move, String _ability,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, PROTECT_BY_ALLY_ABILITY, name_, move_, ability_);
    }

    void addProtectTypeByItemMessage(TeamPosition _fighter, String _type, String _item,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String type_ = _import.translateType(_type);
        String item_ = _import.translateItem(_item);
        addMessage(_import, PROTECT_TYPE_BY_ITEM, name_, type_, item_);
    }

    void addProtectTypeByAbilityMessage(TeamPosition _fighter, String _type, String _ability,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String type_ = _import.translateType(_type);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, PROTECT_TYPE_BY_ABILITY, name_, type_, ability_);
    }

    void addProtectTypeByAbilityWeatherMessage(TeamPosition _fighter, String _type, String _ability, String _move,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String type_ = _import.translateType(_type);
        String ability_ = _import.translateAbility(_ability);
        String move_ = _import.translateMove(_move);
        addMessage(_import, PROTECT_TYPE_BY_ABILITY_WEATHER, name_, type_, ability_, move_);
    }

    void addProtectTypeByIndividualMoveMessage(TeamPosition _fighter, String _type, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String type_ = _import.translateType(_type);
        addMessage(_import, PROTECT_TYPE_BY_INDIVIDUAL_MOVE, name_, type_);
    }

    void addProtectTypeByGlobalMoveMessage(TeamPosition _fighter, String _type, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String type_ = _import.translateType(_type);
        String move_ = _import.translateMove(_move);
        addMessage(_import, PROTECT_TYPE_BY_GLOBAL_MOVE, name_, type_, move_);
    }

    void addChangingWiewPointTargetMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, CHANGING_VIEW_POINT_TARGET, name_);
    }

    void addChangingWiewPointUserMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, CHANGING_VIEW_POINT_USER, name_);
    }

    void addStatusBeginRoundRelMessage(TeamPosition _fighter, String _status, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, CANNOT_USE_MOVE_STATUS_REL, name_, status_, other_);
    }

    void addStatusBeginRoundMessage(TeamPosition _fighter, String _status, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String status_ = _import.translateStatus(_status);
        addMessage(_import, CANNOT_USE_MOVE_STATUS, name_, status_);
    }

    void addFightEvolutionMessage(TeamPosition _fighter, String _evolution, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String evo_ = _import.translatePokemon(_evolution);
        addMessage(_import, FIGHT_EVOLUTION, name_, evo_);
    }

    void addKeepMoveEvolutionMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, KEEP_MOVE_EVOLUTION, name_, move_);
    }

    void addForgetMoveEvolutionMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, FORGET_MOVE_EVOLUTION, name_, move_);
    }

    void addLearnMoveEvolutionMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, LEARN_MOVE_EVOLUTION, name_, move_);
    }

    void addSuccessfulMoveButNoDamageMessage(String _move, TeamPosition _fighter,DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, SUCCESSFUL_MOVE_BUT_NO_DAMAGE, move_, name_);
    }

    void addSuccessfulMoveMessage(String _move, TeamPosition _fighter,DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, SUCCESSFUL_MOVE, move_, name_);
    }

    void addFailMoveMessage(String _move, TeamPosition _fighter,DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, FAIL_MOVE, move_, name_);
    }

    void addNoAchieveTargetMessage(String _move, TeamPosition _fighter,DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, NO_ACHIEVE_TARGET, move_, name_);
    }

    void addNbHitsMessage(long _nbHits, TeamPosition _fighter,DataBase _import) {
        if (_nbHits > 1) {
            String name_ = getFighterName(_fighter, _import);
            addMessage(_import, NB_HITS, Long.toString(_nbHits), name_);
        }
    }

    void addCriticalHitMessage(DataBase _import) {
        addMessage(_import, CRITICAL_HIT);
    }

    void addDisappearedMessage(TeamPosition _fighter,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, DISAPPEARED, name_);
    }

    void addRechargeRoundMessage(TeamPosition _fighter,DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, RECHARGE_ROUND, name_);
    }

    void addPrepaRoundMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, PREPA_ROUND, name_, move_);
    }

    void addSendMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, SEND, name_);
    }

    void addWithdrawMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, WITHDRAW, name_);
    }

    void addInvokeMoveFailMessage(String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        addMessage(_import, INVOKE_MOVE_FAIL, move_);
    }

    void addInvokeMoveMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, INVOKE_MOVE, name_, move_);
    }

    void addFirstMoveMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, FIRST_MOVE, name_, move_);
    }

    void addStatusRelEndRoundMessage(String _status, TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, STATUS_REL_END_ROUND, status_, name_, other_);
    }

    void addStatusEndRoundMessage(String _status, TeamPosition _fighter, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, STATUS_END_ROUND, status_, name_);
    }

    void addItemEndRoundMessage(String _item, TeamPosition _fighter, DataBase _import) {
        String item_ = _import.translateAbility(_item);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, ITEM_END_ROUND, item_, name_);
    }

    void addAbilityEndRoundMessage(String _ability, TeamPosition _fighter, DataBase _import) {
        String ability_ = _import.translateAbility(_ability);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, ABILITY_END_ROUND, ability_, name_);
    }

    void addComboMoveEndRoundMessage(byte _team,StringList _moves, DataBase _import) {
        StringList moves_ = new StringList();
        for (String m: _moves) {
            moves_.add(_import.translateMove(m));
        }
        if (NumberUtil.eq(_team, CST_FOE)) {
            addMessage(_import, COMBO_MOVE_END_ROUND_FOE, StringUtil.join(moves_, SEPARATOR_COMMENTS));
        } else {
            addMessage(_import, COMBO_MOVE_END_ROUND, StringUtil.join(moves_, SEPARATOR_COMMENTS));
        }
    }

    void addMoveEndRoundRelMessage(String _move, TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, MOVE_END_ROUND_REL, move_, name_, other_);
    }

    void addMoveEndRoundMessage(String _move, TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, MOVE_END_ROUND, move_, name_);
    }

    void addGlobalMoveEndRoundMessage(String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        addMessage(_import, GLOBAL_MOVE_END_ROUND, move_);
    }

    void addWeatherEndRoundMessage(String _move, ActivityOfMove _activity, DataBase _import) {
        if (_activity.isEnabled()) {
            addWeatherIncrMessage(_move, _import);
        } else {
            addDisabledWeatherMessage(_move, _import);
        }
    }

    void addWeatherIncrMessage(String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        addMessage(_import, WEATHER_INCR, move_);
    }

    void addDisabledWeatherMessage(String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        addMessage(_import, DISABLED_WEATHER, move_);
    }

    void addEnabledWeatherMessage(String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        addMessage(_import, ENABLED_WEATHER, move_);
    }

    void addVarPpEffectMessage(String _move, TeamPosition _fighter, long _varPp, DataBase _import) {
        String move_ = _import.translateMove(_move);
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, VAR_PP_EFFECT, move_, name_, Long.toString(_varPp));
    }

    void addBatonPassMessage(TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, BATON_PASS, name_, other_);
    }

    void addDisabledTeamUsesMoveMessage(byte _team,String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        if (NumberUtil.eq(_team, CST_FOE)) {
            addMessage(_import, DISABLED_FOE_TEAM_USES_MOVE, move_);
        } else {
            addMessage(_import, DISABLED_TEAM_USES_MOVE, move_);
        }
    }

    void addIncrTeamUsesMoveMessage(byte _team,String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        if (NumberUtil.eq(_team, CST_FOE)) {
            addMessage(_import, INCR_FOE_TEAM_USES_MOVE, move_);
        } else {
            addMessage(_import, INCR_TEAM_USES_MOVE, move_);
        }
    }

    void addDisabledTeamMoveMessage(byte _team, String _move, ActivityOfMove _activity, DataBase _import) {
        if (!_activity.isEnabled()) {
            addDisabledTeamMoveMessage(_team, _move, _import);
        }
    }

    void addDisabledTeamMoveMessage(byte _team,String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        if (NumberUtil.eq(_team, CST_FOE)) {
            addMessage(_import, DISABLED_FOE_TEAM_MOVE, move_);
        } else {
            addMessage(_import, DISABLED_TEAM_MOVE, move_);
        }
    }

    void addEnabledTeamMoveMessage(byte _team,String _move, DataBase _import) {
        String move_ = _import.translateMove(_move);
        if (NumberUtil.eq(_team, CST_FOE)) {
            addMessage(_import, ENABLED_FOE_TEAM_MOVE, move_);
        } else {
            addMessage(_import, ENABLED_TEAM_MOVE, move_);
        }
    }

    void addSwitchPlacesMessage(TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, SWITCH_PLACES, name_, other_);
    }

    void addCopyFighterMessage(TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, COPY_FIGHTER, name_, other_);
    }

    void addLearnMoveRoundDefMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, LEARN_MOVE_ROUND_DEF, name_, move_);
    }

    void addLearnMoveRoundMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, LEARN_MOVE_ROUND, name_, move_);
    }
    void addCommonStatisticMessage(Statistic _statistic, TeamPosition _fighter, TeamPosition _other, Rate _value, DataBase _import) {
        String stat_ = _import.translateStatistics(_statistic);
        String name_ = getFighterName(_fighter, _import);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, COMMON_STATISTIC, stat_, name_, other_, _value.toNumberString());
    }

    void addEnabledMoveRelMessage(TeamPosition _fighter, String _move, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, ENABLED_MOVE_REL, name_, move_, other_);
    }

    void addEnabledMoveMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, ENABLED_MOVE, name_, move_);
    }

    void addDisabledMoveMessage(TeamPosition _fighter, String _move, boolean _relation, TeamPosition _other, DataBase _import) {
        if (_relation) {
            addDisabledMoveRelMessage(_fighter, _move, _other, _import);
        } else {
            addDisabledMoveMessage(_fighter, _move, _import);
        }
    }

    void addDisabledMoveMessage(TeamPosition _fighter, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        addMessage(_import, DISABLED_MOVE, name_, move_);
    }

    void messageDisabling(ActivityOfMove _activity, TeamPosition _fighter, String _move, TeamPosition _other, DataBase _import) {
        if (!_activity.isEnabled()) {
            addDisabledMoveRelMessage(_fighter, _move, _other, _import);
        }
    }

    void addDisabledMoveRelMessage(TeamPosition _fighter, String _move, TeamPosition _other, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String other_ = getFighterName(_other, _import);
        addMessage(_import, DISABLED_MOVE_REL, name_, move_, other_);
    }

    void addDisabledMoveRelMessage(TeamPosition _fighter, String _move, TeamPosition _other, boolean _wasEnabled, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String move_ = _import.translateMove(_move);
        String other_ = getFighterName(_other, _import);
        if (_wasEnabled) {
            addMessage(_import, DISABLED_MOVE_REL, name_, move_, other_);
        }
    }

    void addMoveTypesMessage(TeamPosition _fighter, StringList _types, String _move, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        StringList types_ = new StringList();
        for (String t: _types) {
            types_.add(_import.translateType(t));
        }
        String move_ = _import.translateMove(_move);
        addMessage(_import, MOVE_TYPES, name_, StringUtil.join(types_, SEPARATOR_COMMENTS), move_);
    }
    void addChangedTypesMessage(TeamPosition _fighter, StringList _types, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        StringList types_ = new StringList();
        for (String t: _types) {
            types_.add(_import.translateType(t));
        }
        addMessage(_import, CHANGED_TYPES, name_, StringUtil.join(types_, SEPARATOR_COMMENTS));
    }

    void addSwitchItemsMessage(TeamPosition _fighter, String _oldItem, String _newItem, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        if (_oldItem.isEmpty()) {
            addWinItemMessage(_fighter, _newItem, _import);
            return;
        }
        if (_newItem.isEmpty()) {
            addLooseItemMessage(_fighter, _oldItem, _import);
            return;
        }
        String oldItem_ = _import.translateItem(_oldItem);
        String newItem_ = _import.translateItem(_newItem);
        addMessage(_import, SWITCH_ITEMS, name_, newItem_, oldItem_);
    }

    private void addWinItemMessage(TeamPosition _fighter, String _newItem, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String newItem_ = _import.translateItem(_newItem);
        addMessage(_import, WIN_ITEM, name_, newItem_);
    }

    private void addLooseItemMessage(TeamPosition _fighter, String _oldItem, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        String oldItem_ = _import.translateItem(_oldItem);
        addMessage(_import, LOOSE_ITEM, name_, oldItem_);
    }

    void addEnabledAbilityMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, ENABLED_ABILITY, name_);
    }

    void addChangedAbilityMessage(TeamPosition _fighter, String _ability, DataBase _import) {
        if (_ability.isEmpty()) {
            addChangedAbilityDisabledMessage(_fighter, _import);
            return;
        }
        String name_ = getFighterName(_fighter, _import);
        String ability_ = _import.translateAbility(_ability);
        addMessage(_import, CHANGED_ABILITY, name_, ability_);
    }

    private void addChangedAbilityDisabledMessage(TeamPosition _fighter, DataBase _import) {
        String name_ = getFighterName(_fighter, _import);
        addMessage(_import, CHANGED_ABILITY_DISABLED, name_);
    }

    void addKoFighterMessage(TeamPosition _fighter, DataBase _import) {
        addMessage(_import, KO_FIGHTER, getFighterName(_fighter, _import));
    }

    void addStatusMessage(TeamPosition _fighter, String _status, DataBase _import) {
        String stat_ = _import.translateStatus(_status);
        addMessage(_import, AFF_STATUS, getFighterName(_fighter, _import), stat_);
    }

    void addStatusRelMessage(TeamPosition _fighter, String _status, TeamPosition _otherFighter, DataBase _import) {
        String stat_ = _import.translateStatus(_status);
        addMessage(_import, AFF_STATUS_REL, getFighterName(_fighter, _import), stat_, getFighterName(_otherFighter, _import));
    }

    void addStatisticMessage(TeamPosition _fighter, Statistic _st, long _var, DataBase _import) {
        String stat_ = _import.translateStatistics(_st);
        addMessage(_import, VAR_STATISTIC, stat_, getFighterName(_fighter, _import), Long.toString(_var));
    }

    void addDisabledStatusOtherRelMessage(String _status, TeamPosition _fighter, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        addMessage(_import, DISABLED_STATUS_REL_OTHER, status_, getFighterName(_fighter, _import));
    }

    void addDisabledStatusRelMessage(String _status, TeamPosition _fighter, TeamPosition _other, short _nbRound,DataBase _import) {
        String status_ = _import.translateStatus(_status);
        if (_nbRound > 0) {
            addMessage(_import, DISABLED_STATUS_REL, status_, getFighterName(_fighter, _import), getFighterName(_other, _import));
        }
    }

    void addDisabledStatusMessage(String _status, TeamPosition _fighter, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        addMessage(_import, DISABLED_STATUS, status_, getFighterName(_fighter, _import));
    }

    void addIncrStatusRelMessage(String _status, TeamPosition _fighter, TeamPosition _other, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        addMessage(_import, INCR_STATUS_REL, status_, getFighterName(_fighter, _import), getFighterName(_other, _import));
    }

    void addIncrStatusMessage(String _status, TeamPosition _fighter, DataBase _import) {
        String status_ = _import.translateStatus(_status);
        addMessage(_import, INCR_STATUS, status_, getFighterName(_fighter, _import));
    }

    void addHpCloneMessage(TeamPosition _fighter, Rate _damage, DataBase _import) {
        Fighter fighter_ = getFighter(_fighter);
        if (Rate.greaterEq(_damage.absNb(), fighter_.getClone().absNb())) {
            addDeletedCloneMessage(_fighter, _import);
            return;
        }
        addMessage(_import, CLONE_DAMAGE, getFighterName(_fighter, _import), _damage.absNb().toNumberString());
    }

    void addDeletedCloneMessage(TeamPosition _fighter, DataBase _import) {
        addMessage(_import, CLONE_ZERO, getFighterName(_fighter, _import));
    }

    void addHpMessage(TeamPosition _fighter, DataBase _import, Rate _r) {
        if (_r.isZero()) {
            addMessage(_import, NO_VAR_HP, getFighterName(_fighter, _import));
        } else if (_r.isZeroOrGt()) {
            addMessage(_import, WIN_HP, getFighterName(_fighter, _import), _r.toNumberString());
        } else {
            addMessage(_import, LOOSE_HP, getFighterName(_fighter, _import), _r.absNb().toNumberString());
        }
    }

    void addIssueAfterFightMessage(DataBase _db) {
        temp.addIssueAfterFightMessage(_db);
    }

    void addIssueRandomMessage(DataBase _db) {
        temp.addIssueRandomMessage(_db);
    }

    void addIssueRulesLearnMessage(DataBase _db) {
        temp.addIssueRulesLearnMessage(_db);
    }

    void addIssueRulesMovesMessage(DataBase _db) {
        temp.addIssueRulesMovesMessage(_db);
    }

    void addIssueRulesSwitchMessage(DataBase _db) {
        temp.addIssueRulesSwitchMessage(_db);
    }

    void addIssueSendingMessage(DataBase _db) {
        temp.addIssueSendingMessage(_db);
    }

    void addIssueTooHardMessage(DataBase _db) {
        temp.addIssueTooHardMessage(_db);
    }

    void addIssueUsingMessage(DataBase _db) {
        temp.addIssueUsingMessage(_db);
    }

    void addMessage(DataBase _db, String _key, String... _args) {
        temp.addMessage(_db, _key, _args);
    }

    void addComment(Comment _comment) {
        temp.addComment(_comment);
    }

    public Comment getComment() {
        return temp.getComment();
    }

    void setComment(Comment _comment) {
        temp.setComment(_comment);
    }

    void clearComments() {
        getTemp().clearComments();
    }

    public TransientFight getTemp() {
        return temp;
    }

    void addEmptyMessage() {
        getTemp().addEmptyMessage();
    }

    public boolean isError() {
        return getTemp().isError();
    }

    void setEnabledMessages(boolean _enabledMessages) {
        getTemp().setEnabledMessages(_enabledMessages);
    }

}
