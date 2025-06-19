package aiki.game.fight;

import aiki.comments.Comment;
import aiki.db.*;
import aiki.game.fight.animations.AnimationInt;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.util.NbEffectFighterCoordss;
import aiki.util.TargetCoordsList;
import aiki.util.TeamPositionList;
import aiki.util.TeamPositionsRate;
import code.maths.*;
import code.util.*;
import code.util.comparators.NaturalComparator;
import code.util.core.BoolVal;

public final class TransientFight {
    private boolean utilisationBaieLanceur;
    private boolean tombeKo;
    /**variable sur un tour*/
    private boolean fullHealing;

    /***/
    private boolean simulation;
    private PkMonteCarloEvts evts;
    private boolean error;

    /***/
    private NbEffectFighterCoordss successfulEffects = new NbEffectFighterCoordss();

    /***/
    private Comment comment = new Comment();

    /***/
    //private boolean entres = true;

    /***/
    private TeamPositionsRate damageByCurrentUser = new TeamPositionsRate();

    /***/
    private IntMap<BoolVal> kos = new IntMap<BoolVal>();

    /***/
    private StringList sufferingTargetStatus = new StringList();

    /***/
    private boolean lettingUserAttackWithStatus = true;

    /***/
    private boolean endRound;

    /***/
    private boolean enabledMessages = true;

    /***/
    private boolean acceptableChoices = true;

    /***/
    private IssueSimulation issue = IssueSimulation.NOTHING;

    /***/
    private boolean invokedMove;

    /***/
    private boolean sending;

    /***/
    private boolean keepStatus;

    /***/
    private boolean enabledHealingPartner;

    /***/
    private boolean changeThrower;

    /***/
    private boolean successfulInvokation;

    /***/
    private boolean successfulUse;

    /***/
    private boolean putKo;

    /***/
    private DamageMoveCountUser damage = new DamageMoveCountUser();

    /***/
    private Rate damageKo = Rate.zero();

    /***/
    private TeamPositionList orderedFighters = new TeamPositionList();

    /***/
    private TeamPositionList remainingFighters = new TeamPositionList();

    /***/
    private CustList<ChosableTargetName> chosablePlayerTargets = new CustList<ChosableTargetName>();

    /***/
    private CustList<ChosableTargetName> chosableFoeTargets = new CustList<ChosableTargetName>();

    /***/
    private TargetCoordsList targetCoords = new TargetCoordsList();
    /***/
//    private byte chosenPlayerTarget = Fighter.BACK;

    /***/
//    private byte chosenFoeTarget = Fighter.BACK;

    /***/
    private int chosenIndexFront = Fighter.BACK;

    /***/
    private int chosenIndexBack = Fighter.BACK;

    /***/
    private IdList<ActionType> possibleActionsCurFighter = new IdList<ActionType>();

    /***/
    private ActionType selectedActionCurFighter = ActionType.NOTHING;

    /***/
    private NatStringTreeMap<ChosenMoveInfos> currentFighterMoves = new NatStringTreeMap<ChosenMoveInfos>();

    /***/
    private String chosenMoveFront = DataBase.EMPTY_STRING;

    /***/
    private String chosenHealingMove = DataBase.EMPTY_STRING;

    /***/
    private int chosenSubstitute = Fighter.BACK;

    /***/
    private int chosenIndex = Fighter.BACK;

    /***/
    private NatStringTreeMap<BoolVal> moves = new NatStringTreeMap<BoolVal>();

    /***/
    private EvolutionChoiceMap evolutions = new EvolutionChoiceMap(new NaturalComparator());

    /***/
    private StringList abilities = new StringList();

    /***/
    private String ability = DataBase.EMPTY_STRING;

    /***/
    private ActivityOfMove currentActivity;

    /***/
    private boolean keepRound = true;

    /***/
    private boolean endRoundFightKoPlayer = true;

    private boolean mustFront = true;

    private boolean computingArtInt;
    /***/
    private CustList<AnimationInt> effects = new CustList<AnimationInt>();

    public TransientFight() {
        damage.setDamage(Rate.zero());
        damage.setDamageClone(Rate.zero());
        damage.setDamageCount(Rate.zero());
    }

    void addIssueAfterFightMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_AFTER_FIGHT);
    }

    void addIssueRandomMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_RANDOM);
    }

    void addIssueRulesLearnMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_RULES_LEARN);
    }

    void addIssueRulesMovesMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_RULES_MOVES);
    }

    void addIssueRulesSwitchMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_RULES_SWITCH);
    }

    void addIssueSendingMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_SENDING);
    }

    void addIssueTooHardMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_TOO_HARD);
    }

    void addIssueUsingMessage(DataBase _db) {
        addIssueMessage(_db,Fight.ISSUE_USING);
    }

    void addIssueMessage(DataBase _db, String _key) {
        StringMap<String> messages_ = _db.getMessagesFight();
        addMessage(messages_.getVal(_key));
    }

    void addMessage(DataBase _db, String _key, String... _args) {
        StringMap<String> messages_ = _db.getMessagesFight();
        addMessage(messages_.getVal(_key), _args);
    }

    public void addMessage(String _messageFormat, String... _args) {
        if (enabledMessages) {
            comment.addMessage(_messageFormat, _args);
        }
    }

    void addComment(Comment _comment) {
        if (enabledMessages) {
            comment.addComment(_comment);
        }
    }

    public Comment getComment() {
        return comment;
    }

    void setComment(Comment _comment) {
        comment = _comment;
    }

    void clearComments() {
        if (simulation) {
            return;
        }
        comment.clearMessages();
    }

    void addEmptyMessage() {
        comment.addEmptyMessage();
    }

    public NbEffectFighterCoordss getSuccessfulEffects() {
        return successfulEffects;
    }

    public void setSuccessfulEffects(NbEffectFighterCoordss _successfulEffects) {
        successfulEffects = _successfulEffects;
    }
    public boolean getFullHealing() {
        return fullHealing;
    }

    public void setFullHealing(boolean _fullHealing) {
        fullHealing = _fullHealing;
    }

    public boolean getAcceptableChoices() {
        return acceptableChoices;
    }

    public void setAcceptableChoices(boolean _acceptableChoices) {
        acceptableChoices = _acceptableChoices;
    }

    public boolean getSimulation() {
        return simulation;
    }

    public void setSimulation(boolean _simulation) {
        simulation = _simulation;
    }

    public PkMonteCarloEvts getEvts() {
        return evts;
    }

    public void setEvts(PkMonteCarloEvts _e) {
        this.evts = _e;
    }

    public IssueSimulation getIssue() {
        return issue;
    }

    public void setIssue(IssueSimulation _issue) {
        issue = _issue;
    }

    public boolean isInvokedMove() {
        return invokedMove;
    }

    public void setInvokedMove(boolean _invokedMove) {
        invokedMove = _invokedMove;
    }

    public TeamPositionsRate getDamageByCurrentUser() {
        return damageByCurrentUser;
    }

    public void setDamageByCurrentUser(TeamPositionsRate _damage) {
        damageByCurrentUser = _damage;
    }

    public IntMap<BoolVal> getKos() {
        return kos;
    }

    public void setKos(IntMap<BoolVal> _kos) {
        kos = _kos;
    }

    public StringList getSufferingTargetStatus() {
        return sufferingTargetStatus;
    }

    public void setSufferingTargetStatus(StringList _sufferingTargetStatus) {
        sufferingTargetStatus = _sufferingTargetStatus;
    }

    public boolean getLettingUserAttackWithStatus() {
        return lettingUserAttackWithStatus;
    }

    public void setLettingUserAttackWithStatus(boolean _lettingUserAttackWithStatus) {
        lettingUserAttackWithStatus = _lettingUserAttackWithStatus;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public void setEndRound(boolean _endRound) {
        endRound = _endRound;
    }

    boolean isSending() {
        return sending;
    }

    void setSending(boolean _sending) {
        sending = _sending;
    }

    boolean isKeepStatus() {
        return keepStatus;
    }

    void setKeepStatus(boolean _keepStatus) {
        keepStatus = _keepStatus;
    }

    boolean isEnabledHealingPartner() {
        return enabledHealingPartner;
    }

    boolean isChangeThrower() {
        return changeThrower;
    }

    void setChangeThrower(boolean _changeThrower) {
        changeThrower = _changeThrower;
    }

    boolean isSuccessfulInvokation() {
        return successfulInvokation;
    }

    void setSuccessfulInvokation(boolean _successfulInvokation) {
        successfulInvokation = _successfulInvokation;
    }

    boolean isSuccessfulUse() {
        return successfulUse;
    }

    void setSuccessfulUse(boolean _successfulUse) {
        successfulUse = _successfulUse;
    }

    boolean isPutKo() {
        return putKo;
    }

    void setPutKo(boolean _putKo) {
        putKo = _putKo;
    }

    DamageMoveCountUser getDamage() {
        return damage;
    }

    void setDamage(DamageMoveCountUser _damage) {
        damage = _damage;
    }

    Rate getDamageKo() {
        return damageKo;
    }

    void setDamageKo(Rate _damageKo) {
        damageKo = _damageKo;
    }

    void setEnabledHealingPartner(boolean _enabledHealingPartner) {
        enabledHealingPartner = _enabledHealingPartner;
    }

    TeamPositionList getRemainingFighters() {
        return remainingFighters;
    }

    void setRemainingFighters(TeamPositionList _remainingFighters) {
        remainingFighters = _remainingFighters;
    }

    /**
     <html>
     en:this method is public for comparing<br/>
     fr:cette m&eacute;thode est publique pour comparer
     </html>*/
    public TeamPositionList getOrderedFighters() {
        return orderedFighters;
    }

    void setOrderedFighters(TeamPositionList _orderedFighters) {
        orderedFighters = _orderedFighters;
    }

    public CustList<ChosableTargetName> getChosablePlayerTargets() {
        return chosablePlayerTargets;
    }

    void setChosablePlayerTargets(CustList<ChosableTargetName> _chosablePlayerTargets) {
        chosablePlayerTargets = _chosablePlayerTargets;
    }

    public CustList<ChosableTargetName> getChosableFoeTargets() {
        return chosableFoeTargets;
    }

    void setChosableFoeTargets(CustList<ChosableTargetName> _chosableFoeTargets) {
        chosableFoeTargets = _chosableFoeTargets;
    }

    public TargetCoordsList getTargetCoords() {
        return targetCoords;
    }

    void setTargetCoords(TargetCoordsList _t) {
        targetCoords = _t;
    }

    public int getChosenIndexFront() {
        return chosenIndexFront;
    }

    void setChosenIndexFront(int _chosenIndex) {
        chosenIndexFront = _chosenIndex;
    }

    public int getChosenIndexBack() {
        return chosenIndexBack;
    }

    void setChosenIndexBack(int _chosenIndexBack) {
        chosenIndexBack = _chosenIndexBack;
    }

    public IdList<ActionType> getPossibleActionsCurFighter() {
        return possibleActionsCurFighter;
    }

    void setPossibleActionsCurFighter(IdList<ActionType> _possibleActionsCurFighter) {
        possibleActionsCurFighter = _possibleActionsCurFighter;
    }

    public ActionType getSelectedActionCurFighter() {
        return selectedActionCurFighter;
    }

    void setSelectedActionCurFighter(ActionType _selectedActionCurFighter) {
        selectedActionCurFighter = _selectedActionCurFighter;
    }

    public NatStringTreeMap< ChosenMoveInfos> getCurrentFighterMoves() {
        return currentFighterMoves;
    }

    void setCurrentFighterMoves(NatStringTreeMap< ChosenMoveInfos> _currentFighterMoves) {
        currentFighterMoves = _currentFighterMoves;
    }

    public String getChosenMoveFront() {
        return chosenMoveFront;
    }

    void setChosenMoveFront(String _chosenMoveFront) {
        chosenMoveFront = _chosenMoveFront;
    }

    public String getChosenHealingMove() {
        return chosenHealingMove;
    }

    void setChosenHealingMove(String _chosenHealingMove) {
        chosenHealingMove = _chosenHealingMove;
    }

    public int getChosenSubstitute() {
        return chosenSubstitute;
    }

    void setChosenSubstitute(int _chosenSubstitute) {
        chosenSubstitute = _chosenSubstitute;
    }

    public int getChosenIndex() {
        return chosenIndex;
    }

    void setChosenIndex(int _chosenIndex) {
        chosenIndex = _chosenIndex;
    }

    public NatStringTreeMap< BoolVal> getMoves() {
        return moves;
    }

    void setMoves(NatStringTreeMap< BoolVal> _moves) {
        moves = _moves;
    }

    public EvolutionChoiceMap getEvolutions() {
        return evolutions;
    }

    void setEvolutions(EvolutionChoiceMap _evolutions) {
        evolutions = _evolutions;
    }

    public StringList getAbilities() {
        return abilities;
    }

    void setAbilities(StringList _abilities) {
        abilities = _abilities;
    }

    public String getAbility() {
        return ability;
    }

    void setAbility(String _ability) {
        ability = _ability;
    }

    public boolean isKeepRound() {
        return keepRound;
    }

    void setKeepRound(boolean _keepRound) {
        keepRound = _keepRound;
    }

    public boolean isEndRoundFightKoPlayer(){
        return endRoundFightKoPlayer;
    }

    void setEndRoundFightKoPlayer(boolean _endRoundFightKoPlayer) {
        endRoundFightKoPlayer = _endRoundFightKoPlayer;
    }

    public CustList<AnimationInt> getEffects() {
        return effects;
    }

    void setEffects(CustList<AnimationInt> _effects) {
        effects = _effects;
    }
    public boolean isError() {
        return error;
    }

    void setError(boolean _error) {
        error = _error;
    }

    void setEnabledMessages(boolean _enabledMessages) {
        enabledMessages = _enabledMessages;
    }

    ActivityOfMove getCurrentActivity() {
        return currentActivity;
    }

    void setCurrentActivity(ActivityOfMove _currentActivity) {
        currentActivity = _currentActivity;
    }

    boolean isUtilisationBaieLanceur() {
        return utilisationBaieLanceur;
    }

    void setUtilisationBaieLanceur(boolean _p) {
        this.utilisationBaieLanceur = _p;
    }

    boolean isTombeKo() {
        return tombeKo;
    }

    void setTombeKo(boolean _p) {
        this.tombeKo = _p;
    }

    boolean isComputingArtInt() {
        return computingArtInt;
    }

    void setComputingArtInt(boolean _i) {
        this.computingArtInt = _i;
    }

    boolean isMustFront() {
        return mustFront;
    }

    void setMustFront(boolean _m) {
        this.mustFront = _m;
    }
}
