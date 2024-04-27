package aiki.game.fight;
import aiki.comments.Comment;
import aiki.comparators.ComparatorTr;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingPp;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.animations.AnimationInt;
import aiki.game.fight.comparators.SortedFighterActsComparator;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.comparators.NaturalComparator;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class FightFacade {

    private FightFacade() {
    }

    public static Fight newFight() {
        Fight fight_ = new Fight();
        fight_.getTemp().setSimulation(false);
        fight_.getTemp().setAcceptableChoices(false);
        fight_.getTemp().setIssue(IssueSimulation.NOTHING);
        fight_.setFightType(FightType.NOTHING);
        fight_.setWinningMoney(Rate.zero());
        fight_.setUsedItemsWhileRound(new StringMap<Short>());
        fight_.setChoices(new ByteMap<ChoiceOfEvolutionAndMoves>());
        fight_.setAllyChoice(new MoveTargets());
        fight_.setTeams(new ByteMap<Team>());
        fight_.setLostObjects(new StringList());
        fight_.setFirstPositPlayerFighters(new ByteMap<Byte>());
        fight_.setFirstPositFoeFighters(new ByteMap<Byte>());
        fight_.setState(FightState.RIEN);
        fight_.setCurrentUser(new TeamPosition());
        fight_.setNbRounds(LgInt.zero());
        fight_.setCatchingBalls(new CustList<CatchingBallFoeAction>());
        fight_.setStillEnabledMoves(new StringMap<BoolVal>());
        fight_.setEnabledMoves(new StringMap<ActivityOfMove>());
        fight_.setCaughtEvolutions(new StringList());
        fight_.setEnvType(EnvironmentType.NOTHING);
        return fight_;
    }

    public static void initFight(Fight _fight, Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import) {
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import) {
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _numero, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dual, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,WildPk _pokemon, DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _pokemon, _import);
        beginFight(_fight, _import);
    }

    public static void initFight(Fight _fight, Player _utilisateur, Difficulty _diff, CustList<WildPk> _pokemon, DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _pokemon, _import);
        beginFight(_fight, _import);
    }

    public static void beginFight(Fight _fight, DataBase _import) {
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.CST_FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.CST_PLAYER, _import);
    }

    public static void initTypeEnv(Fight _fight,EnvironmentType _env, Difficulty _diff, DataBase _d){
        _fight.setEnvType(_env);
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _d);
        choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(_fight,_diff,_d);
    }

    public static void initTypeEnv(Fight _fight,Coords _coords, Difficulty _diff, DataBase _d){
        _fight.setEnvType(_d.envType(_coords));
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _d);
        choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(_fight,_diff,_d);
    }

    public static boolean validate(Fight _fight,DataBase _data,Player _user,Difficulty _diff) {
        if (!_fight.getFightType().isExisting()) {
            return true;
        }
        if (koMult(_fight)) {
            return false;
        }
        if (koBasic(_fight, _data)) {
            return false;
        }
        if (koTeams(_fight, _data, _user)) {
            return false;
        }
        disableEffectsExceptHp(_fight, _data);
        if (koFirstPositFighters(_fight)) {
            return false;
        }
        if (koCommon(_fight, _data)) {
            return false;
        }
        if (invalidCatchingBall(_fight, _data)) {
            return false;
        }
        if (koPositionsOnGround(_fight)) {
            return false;
        }
        return okDefault(_fight, _data, _user, _diff);
    }

    private static boolean okDefault(Fight _fight, DataBase _data, Player _user, Difficulty _diff) {
        if (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            return validSwitchAfterUsingMove(_fight, _data);
        } else if (_fight.getState() == FightState.APPRENDRE_EVOLUER) {
            return validLearnEvolve(_fight,_data, _diff);
        } else if (_fight.getState() == FightState.ATTAQUES) {
            return validAttaques(_fight, _data, _diff);
        } else if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
            return validSwitchWhileKoPlayer(_fight,_data);
        } else if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            return validSwitchPropose(_fight,_data);
        } else if (_fight.getState() == FightState.SURNOM) {
            return validSurnom(_fight);
        } else if (_fight.getState() == FightState.CAPTURE_KO) {
            return validCaptureKo(_fight, _data, _user);
        } else {
            return false;
        }
    }

    private static boolean validCaptureKo(Fight _fight, DataBase _data, Player _user) {
        _fight.getChoices().clear();
        if (!_user.existBall(_data)) {
            return false;
        }
        return FightFacade.winOrCaughtWildPk(_fight);
    }

    private static boolean validSurnom(Fight _fight) {
        _fight.getChoices().clear();
        return _fight.getTemp().getKos().getVal(Fight.CST_PLAYER) != BoolVal.TRUE;
    }

    private static boolean invalidCatchingBall(Fight _fight, DataBase _data) {
        if (_fight.getFightType().isWild() && _fight.getCatchingBalls().size() != _fight.getMult()) {
            return true;
        }
        for (CatchingBallFoeAction o: _fight.getCatchingBalls()) {
            if (!StringUtil.quickEq(o.getCatchingBall(),DataBase.EMPTY_STRING) && !(_data.getItem(o.getCatchingBall()) instanceof Ball)) {
                return true;
            }
            Fighter creatureUt_ = _fight.getFighter(Fight.toUserFighter(o.getPlayer()));
            if (creatureUt_ == null) {
                o.setPlayer(_fight.getUserTeam().getMembers().getKey(0));
            }
        }
        return false;
    }

    private static boolean validSwitchPropose(Fight _fight, DataBase _data) {
        _fight.getChoices().clear();
        if (koTeam(_fight)) {
            return false;
        }
        if (!FightEndRound.proponedSwitch(_fight)) {
            return false;
        }
        if (!validAllyChoices(_fight, _data)) {
            return false;
        }
        if (!_fight.getFightType().isWild()) {
            return validSubstitutingTeam(_fight);
        }
        return true;
    }

    private static boolean validSwitchWhileKoPlayer(Fight _fight, DataBase _data) {
        _fight.getChoices().clear();
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            if (!_fight.getFighter(f).estKo()) {
                return false;
            }
        }
        if(koTeam(_fight)){
            return false;
        }
        if (!validAllyChoices(_fight, _data)) {
            return false;
        }
        if (FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
            return validSubstitutingTeam(_fight);
        }
        return true;
    }

    private static boolean validAttaques(Fight _fight, DataBase _data, Difficulty _diff) {
        _fight.getChoices().clear();
        if(FightKo.endedFight(_fight, _diff)){
            return false;
        }
        if (FightEndRound.proponedSwitch(_fight)) {
            return false;
        }
        if (!validAllyChoices(_fight, _data)) {
            return false;
        }
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(t);
            AbstractAction action_ = f_.getAction();
            if (!(action_ instanceof ActionMove)) {
                continue;
            }
            if (f_.estArriere()) {
                return false;
            }
        }
        for (byte b: _fight.getFoeTeam().getMembers().getKeys()) {
            Fighter f_ = _fight.getFoeTeam().getMembers().getVal(b);
            AbstractAction action_ = f_.getAction();
            if (!(action_ instanceof ActionMove)) {
                continue;
            }
            String move_ = ((ActionMove)action_).getFirstChosenMove();
            if (!StringUtil.contains(FightFacade.allowedMovesNotEmpty(_fight, Fight.toFoeFighter(b), _data), move_)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validLearnEvolve(Fight _fight, DataBase _data, Difficulty _diff) {
        if (_fight.getChoices().isEmpty()) {
            return false;
        }
        Bytes list_ = playerFighterWithLearnEvolveChoice(_fight);
        if (!NumberUtil.equalsSetBytes(list_, _fight.getChoices().getKeys())) {
            return false;
        }
        for (byte b: _fight.getChoices().getKeys()) {
            if (invalidChoice(_fight, b)) {
                return false;
            }
        }
        if (!validAllyChoices(_fight, _data)) {
            return false;
        }
        return FightFacade.winOrCaughtWildPk(_fight) || !FightKo.endedFight(_fight, _diff);
    }

    private static boolean invalidChoice(Fight _fight, byte _b) {
        Fighter fighter_ = _fight.getUserTeam().refPartMembres(_b);
        ChoiceOfEvolutionAndMoves choice_ = _fight.getChoices().getVal(_b);
        if (choice_.getName().isEmpty()) {
            StringList possible_ = new StringList();
            possible_.addAllElts(fighter_.getMovesToBeLearnt());
            possible_.addAllElts(fighter_.getMovesSet());
            for (String m: choice_.getKeptMoves()) {
                if (!StringUtil.contains(possible_, m)) {
                    return true;
                }
            }
        } else {
            if (!fighter_.getMovesAbilitiesEvos().contains(choice_.getName())) {
                return true;
            }
            StringList possible_ = new StringList();
            possible_.addAllElts(fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getMoves());
            possible_.addAllElts(fighter_.getMovesSet());
            for (String m: choice_.getKeptMoves()) {
                if (!StringUtil.contains(possible_, m)) {
                    return true;
                }
            }
            possible_.clear();
            possible_.addAllElts(fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getAbilities());
            return possible_.size() > DataBase.ONE_POSSIBLE_CHOICE && !StringUtil.contains(possible_, choice_.getAbility());
        }
        return false;
    }

    private static Bytes playerFighterWithLearnEvolveChoice(Fight _fight) {
        Bytes list_ = new Bytes();
        for (byte b: _fight.getUserTeam().getMembers().getKeys()) {
            Fighter fighter_ = _fight.getUserTeam().refPartMembres(b);
            if (fighter_.isBelongingToPlayer() && (!fighter_.getMovesToBeLearnt().isEmpty() || !fighter_.getMovesAbilitiesEvos().isEmpty())) {
                list_.add(b);
            }
        }
        return list_;
    }

    private static boolean validSwitchAfterUsingMove(Fight _fight, DataBase _data) {
        //The substituted can be KO
        _fight.getChoices().clear();
        if (!NumberUtil.eq(_fight.getCurrentUser().getTeam(), Fight.CST_PLAYER)) {
            return false;
        }
        if (!validAllyChoices(_fight, _data)) {
            return false;
        }
        Team equipe_= _fight.getUserTeam();
        Fighter creature_=equipe_.refPartMembres(_fight.getCurrentUser().getPosition());
        if (creature_ == null) {
            return false;
        }
        MoveData fAtt_= _data.getMove(creature_.getFinalChosenMove());
        if (fAtt_ == null) {
            return false;
        }
        if(fAtt_.getSwitchType() != SwitchType.LANCEUR){
            return false;
        }
        if (!creature_.isActed()) {
            return false;
        }
        if (!creature_.isBelongingToPlayer()) {
            return false;
        }
        return !FightOrder.notKoBackFightersBelongingToUser(_fight, true).isEmpty();
    }

    private static boolean koPositionsOnGround(Fight _fight) {
        if (_fight.getFightType() == FightType.TMP_TRAINER && koAtMostOneFrontPkPerUserGroupTeam(_fight)) {
            return true;
        }
        //never mind places for catching a wild pokemon at the moment of using a ball
        if (atLeastOneFrontPk(_fight) && koAtLeastOneFrontPkForEachTeam(_fight)) {
            return true;
        }
        if (validSwitchTeam(_fight)) {
            adjustFirstPosit(_fight);
            if (invalidSwitchTeam(_fight)) {
                return true;
            }
        }
        if (distinctPlacesGroundSubtCheck(_fight)) {
            exitKoFighters(_fight);
        }
        if (distinctPlacesGroundCheck(_fight) && invalidPlaces(_fight)) {
            return true;
        }
        if (distinctPlacesGroundSubtCheck(_fight) && invalidPlacesSubst(_fight)) {
            return true;
        }
        return koSubstituteState(_fight);
    }

    private static boolean koCommon(Fight _fight, DataBase _data) {
        if (!StringUtil.equalsSet(_data.getMovesEffectGlobal(), _fight.getEnabledMoves().getKeys())) {
            return true;
        }
        for (String m: _fight.getEnabledMoves().getKeys()) {
            if (_fight.getEnabledMoves().getVal(m).getNbTurn() < 0) {
                return true;
            }
        }
        if (!StringUtil.equalsSet(_data.getMovesEffectGlobalWeather(), _fight.getStillEnabledMoves().getKeys())) {
            return true;
        }
        return koChosenTargets(_fight);
    }

    private static boolean koFirstPositFighters(Fight _fight) {
        if (!NumberUtil.equalsSetBytes(_fight.getFirstPositPlayerFighters().getKeys(), _fight.getUserTeam().getMembers().getKeys())) {
            return true;
        }
        int m_ = _fight.getMult();
        Bytes possiblePlaces_ = Team.keysMovesLatter(m_);
        for (byte p: _fight.getFirstPositPlayerFighters().values()) {
            if (Fighter.notBackOrInList(possiblePlaces_,p)) {
                return true;
            }
        }
        if (!NumberUtil.equalsSetBytes(_fight.getFirstPositFoeFighters().getKeys(), _fight.getFoeTeam().getMembers().getKeys())) {
            return true;
        }
        for (byte p: _fight.getFirstPositFoeFighters().values()) {
            if (Fighter.notBackOrInList(possiblePlaces_,p)) {
                return true;
            }
        }
        return false;
    }

    private static boolean koBasic(Fight _fight, DataBase _data) {
        if (_fight.getNbFleeAttempt() < 0) {
            return true;
        }
        if (!_fight.getNbRounds().isZeroOrGt()) {
            return true;
        }
        if (!_fight.getWinningMoney().isZeroOrGt()) {
            return true;
        }
        if (!_data.getPokedex().containsAllAsKeys(_fight.getCaughtEvolutions())) {
            return true;
        }
        if (!_data.getItems().containsAllAsKeys(_fight.getLostObjects())) {
            return true;
        }
        if (!_data.getItems().containsAllAsKeys(_fight.getUsedItemsWhileRound().getKeys())) {
            return true;
        }
        for (Short v: _fight.getUsedItemsWhileRound().values()) {
            if (v < 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean koTeams(Fight _fight, DataBase _data, Player _user) {
        Bytes noTeams_ = new Bytes();
        noTeams_.add(Fight.CST_FOE);
        noTeams_.add(Fight.CST_PLAYER);
        if (!NumberUtil.equalsSetBytes(_fight.getTeams().getKeys(), noTeams_)) {
            return true;
        }
        if (_fight.getFightType().isWild() && !NumberUtil.eq(_fight.getFoeTeam().getMembers().size(), _fight.getMult())) {
            return true;
        }
        for (byte t: _fight.getTeams().getKeys()) {
            if (!_fight.getTeams().getVal(t).validate(_data, t, _fight)) {
                return true;
            }
        }
        Bytes posInit_ = new Bytes();
        int nbMembers_ = _user.getTeam().size();
        for(byte i = IndexConstants.FIRST_INDEX; i<nbMembers_; i++){
            if (!(_user.getTeam().get(i) instanceof PokemonPlayer)) {
                continue;
            }
            posInit_.add(i);
        }
        Bytes pos_ = new Bytes();
        for (byte b: _fight.getUserTeam().getMembers().getKeys()) {
            Fighter f_ = _fight.getUserTeam().refPartMembres(b);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            pos_.add(b);
        }
        if (!NumberUtil.equalsSetBytes(pos_, posInit_)) {
            return true;
        }
        return !NumberUtil.equalsSetBytes(_fight.getUserTeam().getPlayerFightersAgainstFoe().getKeys(), posInit_);
    }

    private static boolean koMult(Fight _fight) {
        if (!_fight.getFightType().isWild() && (_fight.getState() == FightState.SURNOM || _fight.getState() == FightState.CAPTURE_KO)) {
            return true;
        }
        if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER && _fight.getFightType() != FightType.TMP_TRAINER) {
            return true;
        }
        if (_fight.getMult() < 1 || _fight.getMult() > 4) {
            return true;
        }
//        if (_fight.getFightType().isWild() && _fight.getMult() != 1) {
//            return true;
//        }
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            if (_fight.getMult() != 2) {
                return true;
            }
            return _fight.getPlayerMaxNumberFrontFighters() != 1;
        } else {
            return _fight.getPlayerMaxNumberFrontFighters() != _fight.getMult();
        }
    }

    private static boolean validSubstitutingTeam(Fight _fight) {
        TeamPositionList team_;
        team_ = FightOrder.fighters(_fight, Fight.CST_FOE);
        if (!validSubstitutingTeam(_fight, team_)) {
            return false;
        }
        team_ = FightOrder.fighters(_fight, Fight.CST_PLAYER);
        return validSubstitutingTeam(_fight, team_);
    }

    private static boolean koChosenTargets(Fight _fight) {
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(t);
            AbstractAction action_ = f_.getAction();
            if (!(action_ instanceof ActionMove)) {
                continue;
            }
            TargetCoordsList targets_ = ((ActionMove)action_).getChosenTargets();
            if (!targets_.isEmpty()) {
                short pos_ = targets_.first().getPosition();
                if (pos_ < 0 || pos_ >= _fight.getMult()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean invalidPlacesSubst(Fight _fight) {
        if (!validPlacesSubst(_fight, Fight.CST_FOE, false)) {
            return true;
        }
        return !validPlacesSubst(_fight, Fight.CST_PLAYER, onlyDistinctFoeCheckSubst(_fight));
    }

    private static boolean invalidPlaces(Fight _fight) {
        if (!validPlaces(_fight, Fight.CST_FOE)) {
            return true;
        }
        return !validPlaces(_fight, Fight.CST_PLAYER);
    }

    private static void exitKoFighters(Fight _fight) {
        if (!FightEndRound.existSubstitute(_fight)) {
            FightEndRound.exitKoFighters(_fight);
        }
    }

    private static boolean invalidSwitchTeam(Fight _fight) {
        if (!validSwitchTeam(_fight, Fight.CST_PLAYER)) {
            return true;
        }
        return !validSwitchTeam(_fight, Fight.CST_FOE);
    }

    private static void adjustFirstPosit(Fight _fight) {
        TeamPositionList team_;
        team_ = FightOrder.fighters(_fight, Fight.CST_FOE);
        for (TeamPosition t: team_) {
            Fighter f_ = _fight.getFighter(t);
            _fight.getFirstPositFoeFighters().put(t.getPosition(), f_.getGroundPlaceSubst());
        }
        team_ = FightOrder.fighters(_fight, Fight.CST_PLAYER);
        for (TeamPosition t: team_) {
            Fighter f_ = _fight.getFighter(t);
            _fight.getFirstPositPlayerFighters().put(t.getPosition(), f_.getGroundPlaceSubst());
        }
    }

    private static boolean koAtMostOneFrontPkPerUserGroupTeam(Fight _fight) {
        int nbFrontPl_ = 0;
        int nbFrontAlly_ = 0;
        for (TeamPosition f: FightOrder.frontFighters(_fight)) {
            if (f.getTeam() != Fight.CST_PLAYER) {
                continue;
            }
            if (_fight.getFighter(f).isBelongingToPlayer()) {
                nbFrontPl_++;
            } else {
                nbFrontAlly_++;
            }
        }
        if (nbFrontPl_ > DataBase.ONE_POSSIBLE_CHOICE) {
            return true;
        }
        return nbFrontAlly_ > DataBase.ONE_POSSIBLE_CHOICE;
    }

    private static void disableEffectsExceptHp(Fight _fight, DataBase _data) {
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(f);
            if (f_.estArriere()) {
                FightSending.disableEffectsExceptHp(_fight, f, _data);
            }
        }
    }

    private static boolean koAtLeastOneFrontPkForEachTeam(Fight _fight) {
        Bytes fighters_ = new Bytes();
        int mult_ = _fight.getMult();
        for (short i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
            fighters_.addAllElts(_fight.getUserTeam().fightersAtCurrentPlace(i));
        }
        if (fighters_.isEmpty()) {
            return true;
        }
        fighters_.clear();
        for (short i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
            fighters_.addAllElts(_fight.getFoeTeam().fightersAtCurrentPlace(i));
        }
        return fighters_.isEmpty();
    }

    private static boolean distinctPlacesGroundCheck(Fight _fight) {
        return _fight.getState() == FightState.ATTAQUES || _fight.getState() == FightState.SWITCH_APRES_ATTAQUE || _fight.getState() == FightState.SWITCH_WHILE_KO_USER && !FightEndRound.proponedSwitchWhileKoPlayer(_fight);
    }

    private static boolean distinctPlacesGroundSubtCheck(Fight _fight) {
        return _fight.getState() == FightState.ATTAQUES || _fight.getState() == FightState.SWITCH_APRES_ATTAQUE || _fight.getState() == FightState.SWITCH_WHILE_KO_USER || _fight.getState() == FightState.SWITCH_PROPOSE || _fight.getState() == FightState.APPRENDRE_EVOLUER;
    }

    private static boolean onlyDistinctFoeCheckSubst(Fight _fight) {
        return _fight.getState() == FightState.SWITCH_WHILE_KO_USER;
    }

    private static boolean atLeastOneFrontPk(Fight _fight) {
        return _fight.getState() == FightState.ATTAQUES || _fight.getState() == FightState.SWITCH_APRES_ATTAQUE || _fight.getState() == FightState.SWITCH_WHILE_KO_USER && !FightEndRound.proponedSwitchWhileKoPlayer(_fight);
    }

    private static boolean validSwitchTeam(Fight _fight) {
        return _fight.getState() != FightState.SWITCH_WHILE_KO_USER && _fight.getState() != FightState.SWITCH_PROPOSE;
    }
    private static boolean koSubstituteState(Fight _fight) {
        return (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE || _fight.getState() == FightState.ATTAQUES) && koSubstitute(_fight);
    }

    private static boolean koSubstitute(Fight _fight) {
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(t);
            byte subst_ = f_.getSubstistute();
            if (subst_ == Fighter.BACK) {
                continue;
            }
            Fighter part_ = _fight.getTeams().getVal(t.getTeam()).getMembers().getVal(subst_);
            if (part_.estKo()) {
                return true;
            }
            if (part_.isBelongingToPlayer() != f_.isBelongingToPlayer()) {
                return true;
            }
            if (!part_.estArriere()) {
                return true;
            }
        }
        return false;
    }

    static boolean validAllyChoices(Fight _fight, DataBase _data) {
        for (MoveTarget p: _fight.getAllyChoiceSet()) {
            if (!p.getMove().isEmpty() && (!_data.getMoves().contains(p.getMove()) || koTargetByAlly(_fight, p.getTarget()))) {
                return false;
            }
        }
        for (MoveTarget p: _fight.getAllyChoiceValuesSet()) {
            if (!p.getMove().isEmpty() && (!_data.getMoves().contains(p.getMove()) || _data.getMove(p.getMove()).getTargetChoice().isWithChoice() && koTargetByAlly(_fight, p.getTarget()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean koTargetByAlly(Fight _fight, TargetCoords _target) {
        if (_target.getPosition() == Fighter.BACK) {
            return true;
        }
        if (_target.getPosition() < 0) {
            return true;
        }
        if (TargetCoords.koTeam(_target.getTeam())) {
            return true;
        }
        return _target.getPosition() >= _fight.getMult();
    }

    static boolean validPlacesSubst(Fight _fight, byte _team, boolean _onlyDistinctFoeCheckSubst) {
        Bytes distinct_ = new Bytes();
        int nbNotKo_;
        nbNotKo_ = 0;
        for (Fighter t: _fight.getTeams().getVal(_team).getMembers().values()) {
            if (!t.estKo()) {
                nbNotKo_++;
            }
            if (NumberUtil.eq(t.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            if (t.getGroundPlaceSubst() >= _fight.getMult()) {
                return false;
            }
            distinct_.add(t.getGroundPlaceSubst());
        }
        if (distinct_.hasDuplicates()) {
            return false;
        }
        if (!_onlyDistinctFoeCheckSubst && !FightEndRound.existSubstitute(_fight)) {
            return distinct_.size() == nbNotKo_;
        }
        return true;
    }

    static boolean validPlaces(Fight _fight, byte _team) {
        Bytes distinct_ = new Bytes();
        for (Fighter t: _fight.getTeams().getVal(_team).getMembers().values()) {
            if (NumberUtil.eq(t.getGroundPlace(), Fighter.BACK)) {
                continue;
            }
            if (t.getGroundPlace() >= _fight.getMult()) {
                return false;
            }
            distinct_.add(t.getGroundPlace());
        }
        return !distinct_.hasDuplicates();
    }
    static boolean validSwitchTeam(Fight _fight, byte _team) {
        Bytes replace_ = new Bytes();
        for (TeamPosition c:FightOrder.fighters(_fight, _team)) {
            Fighter membre_= _fight.getFighter(c);
            if (!NumberUtil.eq(membre_.getSubstistute(), Fighter.BACK)) {
                replace_.add(membre_.getSubstistute());
            }
        }
        return !replace_.hasDuplicates();
    }
    static boolean validSubstitutingTeam(Fight _fight, TeamPositionList _pseusoTeam) {
        if (!validSubstitutingCommonTeam(_fight, _pseusoTeam)) {
            return false;
        }
        byte teamNo_ = _pseusoTeam.first().getTeam();
        if (NumberUtil.eq(teamNo_, Fight.CST_FOE)) {
            return validSubstitutingFoeTeam(_fight, _pseusoTeam);
        } else {
            return validSubstitutingUserTeam(_fight, _pseusoTeam);
        }
    }

    static boolean validSubstitutingCommonTeam(Fight _fight, TeamPositionList _pseusoTeam) {
        ByteMap<Byte> subst_;
        byte teamNo_ = _pseusoTeam.first().getTeam();
        if (NumberUtil.eq(teamNo_, Fight.CST_FOE)) {
            subst_ = _fight.getFirstPositFoeFighters();
        } else {
            subst_ = _fight.getFirstPositPlayerFighters();
        }
        for(TeamPosition c:_pseusoTeam){
            Fighter membre_= _fight.getFighter(c);
            byte substLoc_ = subst_.getVal(c.getPosition());
            if (membre_.estKo() && !NumberUtil.eq(substLoc_, Fighter.BACK)) {
                return false;
            }
        }
        return true;
    }

    static boolean validSubstitutingFoeTeam(Fight _fight, TeamPositionList _pseusoTeam) {
        Bytes replaceNoPlayer_ = new Bytes();
        ByteMap<Byte> subst_;
        subst_ = _fight.getFirstPositFoeFighters();
        int nbNotKo_ = 0;
        for(TeamPosition c:_pseusoTeam){
            Fighter membre_= _fight.getFighter(c);
            byte substLoc_ = subst_.getVal(c.getPosition());
            if (!membre_.estKo()) {
                nbNotKo_++;
                if (!NumberUtil.eq(substLoc_, Fighter.BACK)) {
                    replaceNoPlayer_.add(substLoc_);
                }
            }
        }
        if (duplicatesOrTooMuch(_fight, replaceNoPlayer_)) {
            return false;
        }
        int nb_ = replaceNoPlayer_.size();
        if (nbNotKo_ > _fight.getMult()) {
            return nb_ == _fight.getMult();
        }
        return nb_ == nbNotKo_;
    }
    static boolean validSubstitutingUserTeam(Fight _fight, TeamPositionList _pseusoTeam) {
        Bytes replaceNoPlayer_ = new Bytes();
        ByteMap<Byte> subst_;
        subst_ = _fight.getFirstPositPlayerFighters();
        int nbNotKoNpc_ = 0;
        for(TeamPosition c:_pseusoTeam){
            Fighter membre_= _fight.getFighter(c);
            byte substLoc_ = subst_.getVal(c.getPosition());
            if (!membre_.estKo()) {
                if (!membre_.isBelongingToPlayer()) {
                    nbNotKoNpc_++;
                }
                if (!NumberUtil.eq(substLoc_, Fighter.BACK) && !membre_.isBelongingToPlayer()) {
                    replaceNoPlayer_.add(substLoc_);
                }
            }
        }
        return duplicatesOrTooMuchUser(_fight, replaceNoPlayer_, nbNotKoNpc_);
    }

    private static boolean duplicatesOrTooMuchUser(Fight _fight, Bytes _replaceNoPlayer, int _nbNotKoNpc) {
        if (duplicatesOrTooMuch(_fight, _replaceNoPlayer)) {
            return false;
        }
        if (!FightArtificialIntelligence.existFree(_fight)) {
            int diff_ = _fight.getMult() - _fight.getPlayerMaxNumberFrontFighters();
            if (_nbNotKoNpc > diff_) {
                return _replaceNoPlayer.size() <= diff_;
            }
            //replaceNoPlayer_.size() <= nbNotKoNpc_
            return true;
        }
        int diff_ = _fight.getMult() - _fight.getPlayerMaxNumberFrontFighters();
        if (_nbNotKoNpc > diff_) {
            return _replaceNoPlayer.size() == diff_;
        }
        return _replaceNoPlayer.size() == _nbNotKoNpc;
    }

    private static boolean duplicatesOrTooMuch(Fight _fight, Bytes _replaceNoPlayer) {
        return _replaceNoPlayer.hasDuplicates() || _replaceNoPlayer.size() > _fight.getMult();
    }

    public static void chooseFrontFighter(Fight _fight, byte _place, Difficulty _diff, DataBase _import) {
        _fight.getTemp().setChosenIndexFront(_place);
        _fight.getTemp().setChosenIndexBack(Fighter.BACK);
        _fight.getTemp().getPossibleActionsCurFighter().clear();
        _fight.getTemp().setTargetCoords(new TargetCoordsList());
        Team equipe_=_fight.getUserTeam();
        CustList<FighterPosition> fighters_ = equipe_.playerFighterAtIndex(_place);
        if (_fight.getState() == FightState.SWITCH_PROPOSE) {
//            byte substitute_ = Fighter.BACK;
//            Bytes listAll_ = new Bytes(equipe_.getMembers().getKeys());
//            listAll_.sort();
//            int i_ = IndexConstants.FIRST_INDEX;
//            for (byte b: listAll_) {
//                Fighter fighter_ = equipe_.getMembers().getVal(b);
//                if (NumberUtil.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                    continue;
//                }
//                if (!fighter_.isBelongingToPlayer()) {
//                    continue;
//                }
//                if (i_ == _place) {
//                    substitute_ = b;
//                    break;
//                }
//                i_++;
//            }
//            assert substitute_ == fighters_.first();
            retSubsChoice(_fight, fighters_);
            return;
        }
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.MOVE);
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.SWITCH);
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.HEALING);
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.NOTHING);
        if (fighters_.isEmpty()) {
            deselectFighter(_fight);
            return;
        }
        Fighter fighter_ = fighters_.first().getFighter();
        AbstractAction action_ = fighter_.getAction();
        if (action_ instanceof ActionMove) {
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
            _fight.getTemp().setSelectedActionCurFighter(ActionType.MOVE);
            _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, fighters_));
            ActionMove actionMove_ = (ActionMove) action_;
            initChosableTargets(_fight, actionMove_.getFirstChosenMove(), _diff, _import, fighters_.first());
//            if (!actionMove_.getChosenTargets().isEmpty()) {
//                if (NumberUtil.eq(actionMove_.getChosenTargets().first().getTeam(), Fight.CST_PLAYER)) {
//                    _fight.setChosenPlayerTarget((byte) actionMove_.getChosenTargets().first().getPosition());
//                    _fight.setChosenFoeTarget(Fighter.BACK);
//                } else {
//                    _fight.setChosenFoeTarget((byte) actionMove_.getChosenTargets().first().getPosition());
//                    _fight.setChosenPlayerTarget(Fighter.BACK);
//                }
//            }
            _fight.getTemp().setTargetCoords(actionMove_.getChosenTargets());
            substitute(_fight, fighter_);
            return;
        }
        if (action_ instanceof ActionSwitch) {
            _fight.getTemp().getCurrentFighterMoves().clear();
            _fight.getTemp().setSelectedActionCurFighter(ActionType.SWITCH);
            substitute(_fight, fighter_);
            return;
        }
        if (action_ instanceof ActionHeal) {
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
            heal(_fight, _import, (ActionHeal) action_, fighters_);
            return;
        }
        _fight.getTemp().setSelectedActionCurFighter(ActionType.MOVE);
        //        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
        _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, fighters_));
    }

    private static void substitute(Fight _fight, Fighter _fighter) {
        int sub_ = Team.indexOfSubstitute(_fight.getUserTeam().getBackTeam(), _fighter.getSubstistute());
        if (sub_ < 0) {
            _fight.getTemp().setChosenSubstitute(Fighter.BACK);
            return;
        }
        _fight.getTemp().setChosenSubstitute((byte) sub_);
    }

    public static void chooseBackFighter(Fight _fight, byte _place, DataBase _import) {
        _fight.clearComments();
        _fight.getTemp().getPossibleActionsCurFighter().clear();
        Team equipe_=_fight.getUserTeam();
        CustList<FighterPosition> fighters_ = equipe_.substituteAtIndex(_place);
        if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            _fight.getTemp().setChosenIndexFront(Fighter.BACK);
            //            byte substitute_ = Fighter.BACK;
//            byte i_ = IndexConstants.FIRST_INDEX;
//            Bytes list_ = new Bytes(equipe_.getMembers().getKeys());
//            list_.sort(new NaturalComparator<Byte>() {
//                @Override
//                public int compare(Byte _o1, Byte _o2) {
//                    return _o1.compareTo(_o2);
//                }
//            });
//            list_.sort();
//            for (byte b: list_) {
//                Fighter fighter_ = equipe_.getMembers().getVal(b);
//                if (!NumberUtil.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                    continue;
//                }
//                if (i_ == _place) {
//                    substitute_ = b;
//                    break;
//                }
//                i_++;
//            }
            _fight.getTemp().setChosenIndexBack(_place);
            retSubsChoice(_fight, fighters_);
            return;
        }
        if (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            chooseBackFighterWhileRound(_fight, _import, fighters_);
            return;
        }
//        if (_fight.getTemp().getSelectedActionCurFighter() == ActionType.SWITCH) {
//            setSubstituteSwitch(_fight, fighters_);
//            validateSwitch(_fight);
//            return;
//        }
//        if (_import.isBatonPassMove(_fight.getTemp().getChosenMoveFront()) || StringUtil.contains(_import.getMovesFullHeal(), _fight.getTemp().getChosenMoveFront())) {
//            chooseBackFighterAddon(_fight, _import, fighters_);
//            validateSwitch(_fight);
//            return;
//        }
        _fight.getTemp().setChosenIndexBack(_place);
        _fight.getTemp().setChosenIndexFront(Fighter.BACK);
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.NOTHING);
        _fight.getTemp().getPossibleActionsCurFighter().add(ActionType.HEALING);
        _fight.getTemp().setSelectedActionCurFighter(ActionType.NOTHING);
        _fight.getTemp().getCurrentFighterMoves().clear();
        if (fighters_.isEmpty()) {
            deselectFighter(_fight);
            return;
        }
        AbstractAction action_ = fighters_.first().getFighter().getAction();
        if (action_ instanceof ActionHeal) {
            heal(_fight, _import, (ActionHeal) action_, fighters_);
        }
    }
    public static boolean requiredSwitch(Fight _fight, DataBase _import){
        return _import.isBatonPassMove(_fight.getTemp().getChosenMoveFront()) || StringUtil.contains(_import.getMovesFullHeal(), _fight.getTemp().getChosenMoveFront());
    }

    public static void chooseSubstituteFighter(Fight _fight, byte _place, DataBase _import) {
        _fight.clearComments();
        Team equipe_=_fight.getUserTeam();
        CustList<FighterPosition> list_ = fightersFront(_fight);
        if (list_.isEmpty()) {
            return;
        }
        Fighter fighter_ = list_.first().getFighter();
        CustList<FighterPosition> subs_ = equipe_.substituteAtIndex(_place);
        if (_fight.getTemp().getSelectedActionCurFighter() == ActionType.SWITCH) {
            setSubstituteSwitch(_fight, subs_, fighter_);
            return;
        }
        chooseBackFighterAddon(_fight, _import, subs_, fighter_);
    }
    private static void retSubsChoice(Fight _fight, CustList<FighterPosition> _fighters) {
        if (_fighters.isEmpty()) {
            deselectFighter(_fight);
            return;
        }
        _fight.getTemp().setChosenSubstitute(_fight.getFirstPositPlayerFighters().getVal(_fighters.first().getFirstPosit()));
    }

    private static void heal(Fight _fight, DataBase _import, ActionHeal _action, CustList<FighterPosition> _sub) {
        _fight.getTemp().setSelectedActionCurFighter(ActionType.HEALING);
        _fight.getTemp().setChosenHealingMove(_action.getChosenHealingItem());
        if (_action instanceof ActionHealMove) {
            _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, _sub));
            _fight.getTemp().setChosenMoveFront(((ActionHealMove) _action).getFirstChosenMove());
        } else {
            _fight.getTemp().getCurrentFighterMoves().clear();
        }
    }

    public static void deselect(Fight _fight) {
        deselectFighter(_fight);
    }

    private static void deselectFighter(Fight _fight) {
        _fight.getTemp().setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
        _fight.getTemp().setSelectedActionCurFighter(ActionType.NOTHING);
        _fight.getTemp().setChosableFoeTargets(new CustList<ChosableTargetName>());
        _fight.getTemp().setChosablePlayerTargets(new CustList<ChosableTargetName>());
        _fight.getTemp().setChosenIndexBack(Fighter.BACK);
        _fight.getTemp().setChosenIndexFront(Fighter.BACK);
        _fight.getTemp().setChosenSubstitute(Fighter.BACK);
        _fight.getTemp().getPossibleActionsCurFighter().clear();
        _fight.getTemp().setChosenMoveFront(DataBase.EMPTY_STRING);
        _fight.getTemp().setChosenHealingMove(DataBase.EMPTY_STRING);
    }

    public static void changeAction(Fight _fight, ActionType _action, DataBase _import, Difficulty _diff) {
        _fight.getTemp().setSelectedActionCurFighter(_action);
//        if (_action == ActionType.MOVE) {
////        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
//            _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, fightersFront(_fight)));
//            return;
//        }
        CustList<FighterPosition> list_ = fighters(_fight);
        if (!list_.isEmpty()) {
//            CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
            Fighter creature_=list_.first().getFighter();
            AbstractAction act_ = creature_.getAction();
            if (act_ instanceof ActionHeal && _action == ActionType.HEALING) {
                heal(_fight,_import, (ActionHeal) act_,list_);
                return;
            }
            if (act_ instanceof ActionSwitch && _action == ActionType.SWITCH) {
                byte sub_ = ((ActionSwitch) act_).getSubstitute();
                CustList<FighterPosition> subs_ = new CustList<FighterPosition>();
                subs_.add(new FighterPosition(_fight.getUserTeam().getMembers().getVal(sub_),sub_));
                setSubstituteSwitch(_fight,subs_,creature_);
                return;
            }
            if (act_ instanceof ActionMove && _action == ActionType.MOVE) {
                ActionMove actionMove_ = (ActionMove) act_;
                _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, list_));
                initChosableTargets(_fight, actionMove_.getFirstChosenMove(), _diff, _import, list_.first());
                _fight.getTemp().setTargetCoords(actionMove_.getChosenTargets());
                substitute(_fight, creature_);
                return;
            }
            creature_.cancelActions();
        }
        _fight.getTemp().setChosableFoeTargets(new CustList<ChosableTargetName>());
        _fight.getTemp().setChosablePlayerTargets(new CustList<ChosableTargetName>());
        _fight.getTemp().setTargetCoords(new TargetCoordsList());
        _fight.getTemp().setChosenSubstitute(Fighter.BACK);
        _fight.getTemp().setChosenMoveFront(DataBase.EMPTY_STRING);
        _fight.getTemp().setChosenHealingMove(DataBase.EMPTY_STRING);
        if (_action == ActionType.MOVE) {
            _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, fightersFront(_fight)));
            return;
        }
        _fight.getTemp().getCurrentFighterMoves().clear();
    }

    static NatStringTreeMap<ChosenMoveInfos> frontFighterMoves(Fight _fight, byte _place, DataBase _import) {
        Team playerTeam_ = _fight.getUserTeam();
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
        CustList<FighterPosition> fighters_ = playerTeam_.playerFighterAtIndex(_place);
        return fighterMovesList(_fight, _import, fighters_);
    }

    static NatStringTreeMap<ChosenMoveInfos> backFighterMoves(Fight _fight, byte _place, DataBase _import) {
        Team playerTeam_ = _fight.getUserTeam();
        CustList<FighterPosition> substitute_ = playerTeam_.substituteAtIndex(_place);
        return fighterMovesList(_fight, _import, substitute_);
    }

    private static NatStringTreeMap<ChosenMoveInfos> fighterMovesList(Fight _fight, DataBase _import, CustList<FighterPosition> _list) {
        if (_list.isEmpty()) {
            return new NatStringTreeMap<ChosenMoveInfos>();
        }
        TeamPosition f_ = Fight.toUserFighter(_list.first().getFirstPosit());
        return fighterMoves(_fight, f_, _import);
    }

    static NatStringTreeMap<ChosenMoveInfos> fighterMoves(Fight _fight, TeamPosition _f, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_f);
        if (!fighter_.isBelongingToPlayer()) {
            return new NatStringTreeMap<ChosenMoveInfos>();
        }
        StringList attaquesAutorisees_ = FightRules.allowedMoves(_fight,_f,_import);
        if (_fight.getTemp().getSelectedActionCurFighter() != ActionType.HEALING && attaquesAutorisees_.isEmpty()) {
            String move_ = _import.getDefMove();
            NatStringTreeMap<ChosenMoveInfos> map_ = new NatStringTreeMap<ChosenMoveInfos>();
            ChosenMoveInfos chosen_ = new ChosenMoveInfos();
            chosen_.setName(move_);
            chosen_.setTypes(_import.getMove(move_).getTypes());
            chosen_.setUsable(true);
            short max_ = fighter_.maxPowerPointsMove(move_, _import);
            short current_ = fighter_.powerPointsMove(move_);
            UsesOfMove uses_ = new UsesOfMove(current_,max_);
            chosen_.setUses(uses_);
            map_.put(_import.translateMove(move_), chosen_);
            return map_;
        }
        StringList allMoves_ = fighter_.attaquesUtilisables();
        allMoves_.addAllElts(attaquesAutorisees_);
        NatStringTreeMap<ChosenMoveInfos> map_ = new NatStringTreeMap<ChosenMoveInfos>();
        for (String m: allMoves_) {
            ChosenMoveInfos chosen_ = new ChosenMoveInfos();
            chosen_.setName(m);
            chosen_.setTypes(_import.getMove(m).getTypes());
            if (_fight.getTemp().getSelectedActionCurFighter() == ActionType.HEALING){
                chosen_.setUsable(fighter_.healedPpMove(m,_fight.getTemp().getChosenHealingMove(),_import) > 0);
            } else {
                chosen_.setUsable(StringUtil.contains(attaquesAutorisees_, m));
            }
            short max_ = fighter_.maxPowerPointsMove(m, _import);
            short current_ = fighter_.powerPointsMove(m);
            UsesOfMove uses_ = new UsesOfMove(current_,max_);
            chosen_.setUses(uses_);
            map_.put(_import.translateMove(m), chosen_);
        }
        return map_;
    }

    public static void chooseMove(Fight _fight, String _move, Difficulty _diff, DataBase _import) {
        if (_fight.getTemp().getSelectedActionCurFighter() == ActionType.HEALING) {
            _fight.getTemp().setChosenMoveFront(_move);
            setChosenHealingItemMove(_fight, _move);
            return;
        }
        if (_fight.getTemp().getSelectedActionCurFighter() != ActionType.MOVE) {
            return;
        }
        _fight.getTemp().getChosableFoeTargets().clear();
        _fight.getTemp().getChosablePlayerTargets().clear();
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(index_);
        CustList<FighterPosition> fighters_ = fightersFront(_fight);
        if (fighters_.isEmpty()) {
            return;
        }
        initChosableTargets(_fight, _move, _diff, _import, fighters_.first());
        CustList<ChosableTargetName> playerTargets_ = _fight.getTemp().getChosablePlayerTargets();
        CustList<ChosableTargetName> foeTargets_ = _fight.getTemp().getChosableFoeTargets();
        if (foeTargets_.isEmpty()) {
            //playerTargets_.size() == foeTargets_.size()
            // ==> playerTargets_.isEmpty()
            setFirstChosenMove(_move, fighters_);
            return;
        }
        Ints possiblePlayerChoices_;
        possiblePlayerChoices_ = indexes(playerTargets_);
        Ints possibleFoeChoices_;
        possibleFoeChoices_ = indexes(foeTargets_);
        _fight.getTemp().setChosenMoveFront(_move);
        if (possiblePlayerChoices_.isEmpty() && possibleFoeChoices_.isEmpty()) {
            setFirstChosenMove(_move, fighters_);
            foeTargets_.clear();
            playerTargets_.clear();
            return;
        }
        if (possiblePlayerChoices_.size() + possibleFoeChoices_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            if (!possibleFoeChoices_.isEmpty()) {
//                CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
                Fighter creature_=fighters_.first().getFighter();
                int possibleFoeChoice_ = possibleFoeChoices_.first();
                creature_.setFirstChosenMoveTarget(_move, TargetCoords.toFoeTarget((short) possibleFoeChoice_));
            } else {
//                CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
                Fighter creature_=fighters_.first().getFighter();
                int possiblePlayerChoice_ = possiblePlayerChoices_.first();
                creature_.setFirstChosenMoveTarget(_move, TargetCoords.toUserTarget((short) possiblePlayerChoice_));
            }
            foeTargets_.clear();
            playerTargets_.clear();
        }
    }
    public static Ints indexes(CustList<ChosableTargetName> _list) {
        int len_ = _list.size();
        Ints inds_ = new Ints();
        for (int i = 0; i < len_; i++) {
            if (_list.get(i).getChosable() == BoolVal.TRUE) {
                inds_.add(i);
            }
        }
        return inds_;
    }

    static void initChosableTargets(Fight _fight, String _move, Difficulty _diff, DataBase _import, FighterPosition _fighter) {
        MoveData move_ = _import.getMove(_move);
        if (!move_.getTargetChoice().isWithChoice()) {
            CustList<ChosableTargetName> playerTargets_ = new CustList<ChosableTargetName>();
            CustList<ChosableTargetName> foeTargets_ = new CustList<ChosableTargetName>();
            _fight.getTemp().setChosenMoveFront(_move);
            _fight.getTemp().setChosableFoeTargets(foeTargets_);
            _fight.getTemp().setChosablePlayerTargets(playerTargets_);
            return;
        }
//        CustList<Byte> fighters_ = _fight.getUserTeam().fightersAtCurrentPlace(_index);
        TeamPosition f_ = Fight.toUserFighter(_fighter.getFirstPosit());
        byte groundPlace_ = _fight.getFighter(f_).getGroundPlace();
        CustList<ChosableTargetName> playerTargets_ = new CustList<ChosableTargetName>();
        CustList<ChosableTargetName> foeTargets_ = new CustList<ChosableTargetName>();
        Bytes playerFightersTakenPlace_ = new Bytes();
        int mult_ = _fight.getMult();
        feedTargets(playerTargets_, mult_, playerFightersTakenPlace_, _fight.getUserTeam());
        Bytes foeFightersTakenPlace_ = new Bytes();
        feedTargets(foeTargets_, mult_, foeFightersTakenPlace_, _fight.getFoeTeam());
        if (move_.getTargetChoice() == TargetChoice.ALLIE) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            anAlly(groundPlace_, playerFightersTakenPlace_, playerTargets_);
        } else if (move_.getTargetChoice() == TargetChoice.ADJ_UNIQ) {
            closerFighters(_fight, playerTargets_, FightOrder.closestFigthersSameTeam(_fight, f_, _diff));
            closerFighters(_fight, foeTargets_, FightOrder.closestFigthersFoeTeam(_fight, f_, _diff));
        } else if (move_.getTargetChoice() == TargetChoice.UNIQUE_IMPORTE) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            allInTeam(playerFightersTakenPlace_, playerTargets_);
//            for (byte k: foeFightersPlace_.getKeys(true))
            allInTeam(foeFightersTakenPlace_, foeTargets_);
        } else if (move_.getTargetChoice() == TargetChoice.AUTRE_UNIQ) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            anAlly(groundPlace_, playerFightersTakenPlace_, playerTargets_);
//            for (byte k: foeFightersPlace_.getKeys(true))
            allInTeam(foeFightersTakenPlace_, foeTargets_);
        } else {
            //ANY_FOE
//            for (byte k: foeFightersPlace_.getKeys(true))
            allInTeam(foeFightersTakenPlace_, foeTargets_);
        }
        _fight.getTemp().setChosenMoveFront(_move);
        _fight.getTemp().setChosableFoeTargets(foeTargets_);
        _fight.getTemp().setChosablePlayerTargets(playerTargets_);
    }

    private static void closerFighters(Fight _fight, CustList<ChosableTargetName> _targets, TeamPositionList _list) {
        for (TeamPosition f: _list) {
            Fighter partner_ = _fight.getFighter(f);
            byte place_ = partner_.getGroundPlace();
            _targets.get(place_).setChosable(BoolVal.TRUE);
            _targets.get(place_).setName(partner_.getName());
            _targets.get(place_).setKey(place_);
        }
    }

    private static void allInTeam(Bytes _fightersTakenPlace, CustList<ChosableTargetName> _targets) {
        for (byte k: _fightersTakenPlace) {
            _targets.get(k).setChosable(BoolVal.TRUE);
        }
    }

    private static void anAlly(byte _groundPlace, Bytes _playerFightersTakenPlace, CustList<ChosableTargetName> _playerTargets) {
        for (byte k: _playerFightersTakenPlace) {
//                playerTargets_.set(k,!Numbers.eq(k, _index));
            _playerTargets.get(k).setChosable(ComparatorBoolean.of(!NumberUtil.eq(k, _groundPlace)));
        }
    }

    private static void feedTargets(CustList<ChosableTargetName> _targets, int _mult, Bytes _fightersTakenPlace, Team _team) {
        for (byte b = IndexConstants.FIRST_INDEX; b < _mult; b++) {
//            CustList<Byte> fightersKeys_ = _fight.getFoeTeam().fightersAtCurrentPlace(b);
            Bytes fightersKeys_ = _team.otherFighterAtIndex(b);
            ChosableTargetName fo_ = new ChosableTargetName();
            _targets.add(fo_);
            if (!fightersKeys_.isEmpty()) {
                Fighter fighter_ = _team.refPartMembres(fightersKeys_.first());
                fo_.setName(fighter_.getName());
                fo_.setKey(fighter_.getGroundPlace());
                _fightersTakenPlace.add(b);
            }
        }
    }

    /** After chosing a fighter among the user's team,
    if the chosen fighter can use a move not being the default move,
    the user has to choose a move for the fighter,
    else
    the default move is automatically chosen*/
    static StringList allowedMovesNotEmpty(Fight _fight, TeamPosition _combattant, DataBase _import){
        StringList attaquesAutorisees_= FightRules.allowedMoves(_fight,_combattant,_import);
        if(attaquesAutorisees_.isEmpty()){
            attaquesAutorisees_.add(_import.getDefMove());
        }
        return attaquesAutorisees_;
    }

    public static void regularRoundAllThrowersChooseActionsFoe(Fight _fight,Difficulty _diff,Player _user, DataBase _import, boolean _enableAnimation){
//        if (!_fight.getRecentComment().isEmpty()) {
//            return;
//        }
        if (_fight.isError() && _fight.getState() != FightState.ATTAQUES) {
            return;
        }
        _fight.clearComments();
        _fight.getTemp().setError(false);
        if (_fight.getState() == FightState.ATTAQUES && !FightRules.playable(_fight, _user, _diff, _import)) {
            _fight.getTemp().setError(true);
            return;
        }
        _fight.getUsedItemsWhileRound().clear();
        cancelCatch(_fight);
        roundCommon(_fight, _diff, _user, _import, _enableAnimation);
    }

    static void roundAllThrowersChooseActionsFoe(Fight _fight,Difficulty _diff,Player _user, DataBase _import){
        choiceArtificialIntelligenceBeginRoundWhenWildFight(_fight, _diff, _import);
        FightRound.roundAllThrowers(_fight,_diff,_user,_import);
        //&& _fight.getState() != FightState.SWITCH_WHILE_KO_USER
        //ia adv
        aiChoice(_fight, _diff, _import);
    }

    static void beginRound(Fight _fight, Difficulty _diff, DataBase _import) {
        choiceArtificialIntelligenceBeginRoundWhenWildFight(_fight, _diff, _import);
        FightRound.beginRound(_fight, _diff, _import);
    }

    private static void choiceArtificialIntelligenceBeginRoundWhenWildFight(Fight _fight, Difficulty _diff, DataBase _import) {
        if (_fight.getBeginRound() && _fight.getFightType().isWild()) {
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight, _diff, _import);
        }
    }

    public static void roundUser(Fight _fight, Difficulty _diff, DataBase _import) {
        _fight.clearComments();
        FightRound.roundUser(_fight, _diff, _import);
    }

    public static void endRoundFightBasic(Fight _fight, Difficulty _diff, Player _user, DataBase _import) {
        FightRound.endRoundFight(_fight, _diff, _user, _import);
//        if (!_fight.getRemainingFighters().isEmpty()) {
//            return;
//        }
        //&& _fight.getState() != FightState.SWITCH_WHILE_KO_USER
        //foe art. int.
        aiChoice(_fight, _diff, _import);
    }

    private static void aiChoice(Fight _fight, Difficulty _diff, DataBase _import) {
        if(!_fight.getTemp().getAcceptableChoices()){
            return;
        }
        if (koTeam(_fight)) {
            return;
        }
        choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(_fight, _diff, _import);
    }

//    public static Bytes getKoPlayerFrontFightersPlaces(Fight _fight) {
//        Bytes places_ = new Bytes();
//        for (Fighter f: _fight.getUserTeam().getMembers().values()) {
//            if (!f.estKo()) {
//                continue;
//            }
//            if (Numbers.eq(f.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
//            places_.add(f.getGroundPlaceSubst());
//        }
//        return places_;
//    }

    public static NatStringTreeMap<BallNumberRate> calculateCatchingRatesSingle(Fight _fight,Difficulty _diff,Player _player,DataBase _import, byte _creatureSauvage, byte _creatureUt) {
        Fighter foe_ = _fight.getFoeTeam().getMembers().getVal(_creatureSauvage);
        if (foe_ == null) {
            return calculateCatchingRatesSingle(_player, _import);
        }
        NatStringTreeMap<BallNumberRate> tree_ = new NatStringTreeMap<BallNumberRate>();
        Fighter user_ = _fight.getFoeTeam().getMembers().getVal(_creatureUt);
        boolean present_ = _player.estAttrape(foe_.getName());
        for (String o: _import.getItems().getKeys()) {
            Item obj_ = _import.getItem(o);
            if (!(obj_ instanceof Ball)) {
                continue;
            }
            LgInt eff_ = _player.getInventory().getNumber(o);
            if (!eff_.isZero()) {
                if (user_ != null || foe_.estKo()) {
                    Rate rate_ = FightRound.calculateCatchingRate(_fight, new FighterPosition(user_,_creatureUt), o, present_, _diff, _import, foe_);
                    MonteCarloNumber law_ = new MonteCarloNumber();
                    law_.addQuickEvent(rate_,LgInt.one());
                    BallNumberRate info_ = new BallNumberRate(eff_, law_, o);
                    tree_.put(_import.translateItem(o), info_);
                } else {
                    MonteCarloNumber law_ = new MonteCarloNumber();
                    for (FighterPosition f: getPlayerToCatch(_fight)) {
                        Rate rate_ = FightRound.calculateCatchingRate(_fight, f, o, present_, _diff, _import, foe_);
                        law_.addQuickEvent(rate_,LgInt.one());
                    }
                    BallNumberRate info_ = new BallNumberRate(eff_, law_, o);
                    tree_.put(_import.translateItem(o), info_);
                }
            }
        }
        return tree_;
    }

    private static NatStringTreeMap<BallNumberRate> calculateCatchingRatesSingle(Player _player, DataBase _import) {
        NatStringTreeMap<BallNumberRate> tree_ = new NatStringTreeMap<BallNumberRate>();
        for (String o: _import.getItems().getKeys()) {
            Item obj_ = _import.getItem(o);
            if (!(obj_ instanceof Ball)) {
                continue;
            }
            LgInt eff_ = _player.getInventory().getNumber(o);
            if (!eff_.isZero()) {
                MonteCarloNumber law_ = new MonteCarloNumber();
                BallNumberRate info_ = new BallNumberRate(eff_, law_, o);
                tree_.put(_import.translateItem(o), info_);
            }
        }
        return tree_;
    }

    public static boolean enoughBall(Fight _fight, Player _user, DataBase _import) {
        Inventory required_ = new Inventory(_import);
        for (CatchingBallFoeAction v: _fight.getCatchingBalls()) {
            String c_ = v.getCatchingBall();
            if (candidate(v)) {
                required_.getItem(c_);
            }
        }
        Inventory possible_ = _user.getInventory();
        for (CatchingBallFoeAction v: _fight.getCatchingBalls()) {
            if (candidate(v) && LgInt.strLower(possible_.getNumber(v.getCatchingBall()), required_.getNumber(v.getCatchingBall()))) {
                return false;
            }
        }
        return true;
    }

    public static IntMap<BallNumberRatePk> calculateCatchingRatesSum(Fight _fight, Player _user, Difficulty _diff, DataBase _import){
        IntMap<BallNumberRatePk> rates_ = new IntMap<BallNumberRatePk>();
        CustList<CatchingBallFoeAction> ls_ = _fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int v = 0; v < s_; v++) {
            CatchingBallFoeAction ca_ = ls_.get(v);
            String c_ = ca_.getCatchingBall();
            if (candidate(ca_)) {
                Fighter creatureUt_ = _fight.getFighter(Fight.toUserFighter(ca_.getPlayer()));
                Fighter creatureSauvage_ = _fight.getFighter(Fight.toFoeFighter((byte) v));
                Rate rate_ = FightRound.calculateCatchingRate(_fight, new FighterPosition(creatureUt_, ca_.getPlayer()), c_, _user.estAttrape(creatureSauvage_.getName()), _diff, _import, creatureSauvage_);
                rates_.addEntry(v, new BallNumberRatePk(creatureSauvage_.getName(),rate_,c_));
            }
        }
        return rates_;
    }
    public static IntMap<CatchingBallFoeAction> attempted(Fight _fight) {
        IntMap<CatchingBallFoeAction> is_ = new IntMap<CatchingBallFoeAction>();
        CustList<CatchingBallFoeAction> ls_ = _fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int v = 0; v < s_; v++) {
            CatchingBallFoeAction ca_ = ls_.get(v);
            if (candidate(ca_)) {
                CatchingBallFoeAction copy_ = new CatchingBallFoeAction();
                copy_.setCatchingBall(ca_.getCatchingBall());
                copy_.setCaught(ca_.isCaught());
                is_.addEntry(v, copy_);
            }
        }
        return is_;
    }
    public static IntMap<CatchingBallFoeAction> swallow(Fight _fight, IntMap<CatchingBallFoeAction> _att) {
        CustList<CatchingBallFoeAction> ls_ = _fight.getCatchingBalls();
        for (EntryCust<Integer, CatchingBallFoeAction> v: _att.entryList()) {
            v.getValue().setCaught(ls_.get(v.getKey()).isCaught());
        }
        return _att;
    }
    public static void attemptCatching(Fight _fight, Difficulty _diff, Player _user, DataBase _import, boolean _enableAnimation){
        _fight.getTemp().setKeepRound(true);
        CustList<CatchingBallFoeAction> ls_ = _fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int v = 0; v < s_; v++) {
            CatchingBallFoeAction ca_ = ls_.get(v);
            String c_ = ca_.getCatchingBall();
            if (candidate(ca_)) {
                _user.useInInventory(c_);
                Fighter creatureUt_ = _fight.getFighter(Fight.toUserFighter(ca_.getPlayer()));
                Fighter creatureSauvage_ = _fight.getFighter(Fight.toFoeFighter((byte) v));
                Rate proba_=FightRound.calculateCatchingRate(_fight, new FighterPosition(creatureUt_, ca_.getPlayer()), c_, _user.estAttrape(creatureSauvage_.getName()), _diff, _import, creatureSauvage_);
                if(FightSuccess.tirage(_import, proba_)){
                    ca_.setCaught(true);
                } else {
                    ca_.setCaught(false);
                    ca_.setCatchingBall(DataBase.EMPTY_STRING);
                }
            }
        }
        if (winOrCaughtWildPk(_fight)) {
            _fight.getTemp().setKeepRound(false);
//            _fight.setCatchingBall(_ball);
            _fight.setState(FightState.SURNOM);
            //le pokemon est capture
            return;
        }
//        frontFighterChoiceFleeingCatching(_fight);
        roundCommon(_fight, _diff, _user, _import, _enableAnimation);
    }

    public static boolean candidate(CatchingBallFoeAction _ca) {
        String c_ = _ca.getCatchingBall();
        return !_ca.isCaught() && !StringUtil.quickEq(c_, DataBase.EMPTY_STRING);
    }

    public static void attemptFlee(Fight _fight,Difficulty _diff,Player _user,DataBase _import, boolean _enableAnimation){
        cancelCatch(_fight);
        MonteCarloNumber probas_= calculateFleeingRate(_fight,_diff,_import);
        LgInt maxRd_ = _import.getMaxRd();
        Rate proba_ = probas_.editNumber(maxRd_, _import.getGenerator());
        if(_fight.getNbFleeAttempt()<255){
            _fight.setNbFleeAttempt((short) (_fight.getNbFleeAttempt()+1));
        }
        _fight.getTemp().setKeepRound(true);
        if(FightSuccess.tirage(_import, proba_)){
            //la fuite est reussie ==> fin de combat
            _fight.getTemp().setKeepRound(false);
            _fight.setState(FightState.REDESSIN_SCENE);
            return;
        }
//        frontFighterChoiceFleeingCatching(_fight);
        roundCommon(_fight, _diff, _user, _import, _enableAnimation);
    }

    private static void cancelCatch(Fight _fight) {
        CustList<CatchingBallFoeAction> ls_ = _fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int v = 0; v < s_; v++) {
            CatchingBallFoeAction ca_ = ls_.get(v);
            if (candidate(ca_)) {
                ca_.setCatchingBall(DataBase.EMPTY_STRING);
            }
        }
    }
    public static MonteCarloNumber calculateFleeingRate(Fight _fight, Difficulty _diff, DataBase _import) {
        return FightRound.calculateFleeingRate(_fight,_diff,_import);
    }

    public static void roundWhileKoPlayer(Fight _fight,Difficulty _diff, Player _user,DataBase _import, boolean _enableAnimation) {
        _fight.clearComments();
        if (FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
            _fight.getTemp().setKeepRound(false);
            _fight.getTemp().setEndRoundFightKoPlayer(false);
            FightSending.sendSubstitutes(_fight, _diff, _user, _import);
        } else {
            _fight.getTemp().setKeepRound(true);
            _fight.getTemp().setEndRoundFightKoPlayer(true);
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
            cancelCatch(_fight);
            roundCommon(_fight, _diff, _user, _import, _enableAnimation);
        }
    }

    private static void roundCommon(Fight _fight, Difficulty _diff, Player _user, DataBase _import, boolean _enableAnimation) {
        if (_enableAnimation) {
            beginRound(_fight, _diff, _import);
        } else {
            roundAllThrowersChooseActionsFoe(_fight, _diff, _user, _import);
        }
    }

    public static void sendSubstitutesChooseActions(Fight _fight,Difficulty _diff, Player _user,DataBase _import){
        _fight.clearComments();
        FightSending.sendSubstitutes(_fight, _diff, _user, _import);
        //||_fight.getState() == FightState.SWITCH_WHILE_KO_USER
        //ia adv
        choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(_fight, _diff, _import);
    }

    private static void choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(Fight _fight, Difficulty _diff, DataBase _import) {
        if (_fight.getState() == FightState.ATTAQUES && !_fight.getFightType().isWild()) {
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight, _diff, _import);
        }
    }

    public static boolean koTeam(Fight _fight){
        return _fight.getTemp().getKos().getVal(Fight.CST_PLAYER)==BoolVal.TRUE|| _fight.getTemp().getKos().getVal(Fight.CST_FOE)==BoolVal.TRUE;
    }

    public static void endFight(Fight _fight){
        _fight.setFightType(FightType.NOTHING);
        _fight.getEffects().clear();
        for(TeamPosition c: fightersBelongingToUser(_fight,true)){
            _fight.getUserTeam().toutSupprimerCombattantsContreAdvMembre(c.getPosition());
        }
    }

    public static boolean winOrCaughtWildPk(Fight _fight){
        if (win(_fight)) {
            return true;
        }
        if (!_fight.getFightType().isWild()) {
            return false;
        }
        for (CatchingBallFoeAction t: _fight.getCatchingBalls()) {
            if (!t.isCaught()) {
                return false;
            }
        }
        return true;
    }
    public static boolean win(Fight _fight){
        return _fight.getTemp().getKos().getVal(Fight.CST_PLAYER)!=BoolVal.TRUE&& _fight.getTemp().getKos().getVal(Fight.CST_FOE)==BoolVal.TRUE;
    }

    public static boolean equality(Fight _fight){
        return _fight.getTemp().getKos().getVal(Fight.CST_PLAYER)==BoolVal.TRUE&& _fight.getTemp().getKos().getVal(Fight.CST_FOE)==BoolVal.TRUE;
    }

    public static boolean loose(Fight _fight){
        return _fight.getTemp().getKos().getVal(Fight.CST_PLAYER)==BoolVal.TRUE&& _fight.getTemp().getKos().getVal(Fight.CST_FOE)!=BoolVal.TRUE;
    }

    public static FightState fightStatement(Fight _fight, boolean _existBall,Difficulty _diff){
        if(_fight.getFightType().isWild()){
            if(loose(_fight)||equality(_fight)){
                return FightState.FIN_CBT_SAUVAGE;
            }
            if (winOrCaughtWildPk(_fight) && _diff.getAllowCatchingKo() && _existBall) {
                return FightState.CAPTURE_KO;
            }
            return FightState.FIN_CBT_SAUVAGE;
        }
        if(win(_fight)){
            return FightState.REDESSIN_SCENE;
        }
        return FightState.RIEN;
    }

    public static StringMap<UsesOfMove> movesAfterFight(Fight _fight, TeamPosition _fighter, Difficulty _diff) {
        Fighter membre_=_fight.getFighter(_fighter);
        StringMap<UsesOfMove> moves_ = new StringMap<UsesOfMove>();
        for(String m: membre_.getMovesSet()){
            UsesOfMove currentUses_ = membre_.getMove(m);
            UsesOfMove uses_ = new UsesOfMove(currentUses_.getMax());
            moves_.put(m, uses_);
        }
        if (!_diff.getRestoredMovesEndFight() && !membre_.isChanged()) {
            for (String m : moves_.getKeys()) {
                if (!StringUtil.contains(membre_.getCurrentMovesSet(), m)) {
                    continue;
                }
                moves_.getVal(m).setCurrent(membre_.getCurrentMove(m).getCurrent());
            }
        }
        return moves_;
    }

    public static CustList<FighterPosition> getPlayerTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        ByteTreeMap<Byte> keys_ = new ByteTreeMap<Byte>();
        ByteMap<Fighter> members_ = team_.getMembers();
        int nb_ = members_.size();
        for (byte i = 0; i < nb_; i++) {
            Fighter f_ = members_.getValue(i);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            keys_.put(i, members_.getKey(i));
        }
        ByteTreeMap<FighterPosition> tree_ = sortedTeam(keys_, members_);
        return tree_.values();
    }

    public static ByteTreeMap<FighterPosition> getFoeFrontTeam(Fight _fight) {
        Team team_ = _fight.getFoeTeam();
        return getFrontTeam(_fight, Fight.CST_FOE,team_);
    }

    public static ByteTreeMap<FighterPosition> getUnionFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        return getFrontTeam(_fight, Fight.CST_PLAYER,team_);
    }

    public static byte retrieve(byte _key, ByteTreeMap<FighterPosition> _map) {
        for (EntryCust<Byte, FighterPosition> e: _map.entryList()) {
            if (e.getValue().getFirstPosit() == _key) {
                return e.getKey();
            }
        }
        return Fighter.BACK;
    }

    private static ByteTreeMap<FighterPosition> getFrontTeam(Fight _fight, byte _cst,Team _team) {
        if (_cst == Fight.CST_PLAYER || !_fight.getFightType().isWild()) {
            ByteTreeMap<FighterPosition> tree_ = new ByteTreeMap<FighterPosition>();
            for (byte k: _team.getMembers().getKeys()) {
                Fighter f_ = _team.getMembers().getVal(k);
                if (NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                    continue;
                }
                tree_.put(f_.getGroundPlaceSubst(), new FighterPosition(f_,k));
            }
            return tree_;
        }
        ByteTreeMap<FighterPosition> tree_ = new ByteTreeMap<FighterPosition>();
        byte max_ = Fighter.BACK;
        for (byte k : _team.getMembers().getKeys()) {
            Fighter f_ = _team.getMembers().getVal(k);
            if (!NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK) && FightOrder.notCaught(_fight, Fight.toFoeFighter(k))) {
                max_ = (byte) NumberUtil.max(max_, f_.getGroundPlaceSubst());
                tree_.put(f_.getGroundPlaceSubst(), new FighterPosition(f_, k));
            }
        }
        max_ = (byte) NumberUtil.max(max_, -1);
        for (byte k: _team.getMembers().getKeys()) {
            Fighter f_ = _team.getMembers().getVal(k);
            if (!tree_.contains(f_.getGroundPlaceSubst()) && FightOrder.notCaught(_fight, Fight.toFoeFighter(k))) {
                tree_.put((byte) (max_ + 1), new FighterPosition(f_, k));
                max_++;
            }
        }
        return tree_;
    }
    public static CustList<FighterPosition> getPlayerToCatch(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        return allFighters(team_);
    }

    public static CustList<FighterPosition> allFighters(Team _team) {
        CustList<FighterPosition> fs_ = new CustList<FighterPosition>();
        short index_ = 0;
        while (true) {
            CustList<FighterPosition> front_ = _team.fighterTeamAtIndex(index_);
            if (front_.isEmpty()) {
                break;
            }
            fs_.add(front_.get(0));
            index_++;
        }
        return fs_;
    }

    public static FighterPosition getSinglePlayerToCatch(Fight _fight, int _index) {
        return getPlayerToCatch(_fight).get(_index);
    }
    public static CustList<FighterPosition> getFoeToBeCaught(Fight _fight) {
        Team team_ = _fight.getFoeTeam();
        return allFighters(team_);
    }
    public static CustList<FighterPosition> getFoeToBeCaught(Fight _fight, boolean _caught) {
        Team team_ = _fight.getFoeTeam();
        CustList<FighterPosition> fs_ = new CustList<FighterPosition>();
        short index_ = 0;
        while (true) {
            CustList<FighterPosition> front_ = team_.fighterTeamAtIndex(index_);
            if (front_.isEmpty() || !_fight.getCatchingBalls().isValidIndex(front_.get(0).getFirstPosit())) {
                break;
            }
            CatchingBallFoeAction act_ = _fight.getCatchingBalls().get(front_.get(0).getFirstPosit());
            if (act_.isCaught() == _caught) {
                fs_.add(front_.get(0));
            }
            index_++;
        }
        return fs_;
    }
    public static FighterPosition getSingleFoeToBeCaught(Fight _fight, int _index) {
        return getFoeToBeCaught(_fight).get(_index);
    }
    public static FighterPosition getSingleFoeToBeCaught(Fight _fight, boolean _caught, int _index) {
        return getFoeToBeCaught(_fight, _caught).get(_index);
    }

    public static CustList<FighterPosition> getPlayerFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        return team_.getFrontTeam().values();
//        ByteTreeMap<Fighter> tree_ = new ByteTreeMap<Fighter>();
//        for (byte k: team_.getMembers().getKeys()) {
//            Fighter f_ = team_.getMembers().getVal(k);
//            if (!f_.isBelongingToPlayer()) {
//                continue;
//            }
//            if (NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
////            if (f_.estArriere()) {
////                continue;
////            }
////            tree_.put(f_.getGroundPlace(), f_);
//            tree_.put(f_.getGroundPlaceSubst(), f_);
//        }
//        return tree_;
    }

    public static CustList<FighterPosition> getPlayerBackTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
//        ByteTreeMap<Fighter> tree_ = new ByteTreeMap<Fighter>();
//        ByteTreeMap<Byte> keys_ = new ByteTreeMap<Byte>();
//        byte index_ = IndexConstants.FIRST_INDEX;
//        for (byte k: team_.getMembers().getKeys()) {
//            Fighter f_ = team_.getMembers().getVal(k);
//            if (!f_.isBelongingToPlayer()) {
//                continue;
//            }
//            if (!NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
////            if (!f_.estArriere()) {
////                continue;
////            }
//            keys_.put(index_,k);
//            index_++;
//        }
//        index_ = IndexConstants.FIRST_INDEX;
//        for (byte k: keys_.values()) {
//            Fighter f_ = team_.getMembers().getVal(k);
//            tree_.put(index_, f_);
//            index_++;
//        }
        return team_.getBackTeam().values();
    }

//    public static CustList<Fighter> getPlayerFrontTeamForSubstituting(Fight _fight) {
//        Team team_ = _fight.getUserTeam();
//        return convertFront(team_.getFrontTeam());
////        Team team_ = _fight.getUserTeam();
////        ByteTreeMap<Fighter> tree_ = new ByteTreeMap<Fighter>();
////        for (byte k: team_.getMembers().getKeys()) {
////            Fighter f_ = team_.getMembers().getVal(k);
////            if (!f_.isBelongingToPlayer()) {
////                continue;
////            }
////            if (NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
////                continue;
////            }
////            tree_.put(f_.getGroundPlaceSubst(), f_);
////        }
////        return tree_;
//    }

//    private static CustList<Fighter> convertFront(ByteTreeMap<FighterPosition> _in) {
//        CustList<Fighter> tree_ = new CustList<Fighter>();
//        for (EntryCust<Byte,FighterPosition> e: _in.entryList()) {
//            tree_.add(e.getValue().getFighter());
//        }
//        return tree_;
//    }
//    public static CustList<Fighter> getPlayerBackTeamForSubstituting(Fight _fight) {
//        Team team_ = _fight.getUserTeam();
////        ByteTreeMap<Fighter> tree_ = new ByteTreeMap<Fighter>();
////        ByteTreeMap<Byte> keys_ = new ByteTreeMap<Byte>();
////        byte index_ = IndexConstants.FIRST_INDEX;
////        for (byte k: team_.getMembers().getKeys()) {
////            Fighter f_ = team_.getMembers().getVal(k);
////            if (!f_.isBelongingToPlayer()) {
////                continue;
////            }
////            if (!NumberUtil.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
////                continue;
////            }
////            keys_.put(index_,k);
////            index_++;
////        }
////        index_ = IndexConstants.FIRST_INDEX;
////        for (byte k: keys_.values()) {
////            Fighter f_ = team_.getMembers().getVal(k);
////            tree_.put(index_, f_);
////            index_++;
////        }
////        return tree_.values();
//        return team_.getBackTeam().values();
//    }

    public static ByteTreeMap<Fighter> getAllyFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        ByteTreeMap<Fighter> tree_ = new ByteTreeMap<Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (f_.isBelongingToPlayer() || f_.estArriere()) {
                continue;
            }
            tree_.put(f_.getGroundPlace(), f_);
        }
        return tree_;
    }

    public static ByteTreeMap<FighterPosition> getAllyBackTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        ByteTreeMap<Byte> keys_ = new ByteTreeMap<Byte>();
        ByteMap<Fighter> members_ = team_.getMembers();
        int nb_ = members_.size();
        for (byte i = 0; i < nb_; i++) {
            Fighter f_ = members_.getValue(i);
            if (f_.isBelongingToPlayer() || !f_.estArriere()) {
                continue;
            }
            keys_.put(i,members_.getKey(i));
        }
        return sortedTeam(keys_, members_);
    }

    private static ByteTreeMap<FighterPosition> sortedTeam(ByteTreeMap<Byte> _keys, ByteMap<Fighter> _members) {
        ByteTreeMap<FighterPosition> tree_ = new ByteTreeMap<FighterPosition>();
        CustList<Byte> keyList_ = _keys.values();
        int nbKeys_ = keyList_.size();
        for (byte i = 0; i < nbKeys_; i++) {
            Fighter f_ = _members.getVal(keyList_.get(i));
            tree_.put(i, new FighterPosition(f_,keyList_.get(i)));
        }
        return tree_;
    }

    public static boolean isChosableForLearningAndEvolving(Fight _fight, byte _key) {
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        if (NumberUtil.eq(key_, Fighter.BACK)) {
            return false;
        }
        return _fight.getChoices().contains(key_);
    }

    public static void choosePokemonForLearningAndEvolving(Fight _fight, byte _key, DataBase _d) {
        _fight.getTemp().setChosenIndex(_key);
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        if (NumberUtil.eq(key_, Fighter.BACK)) {
            _fight.getTemp().setMoves(new NatStringTreeMap<BoolVal>());
            _fight.getTemp().setEvolutions(new EvolutionChoiceMap(new NaturalComparator()));
            _fight.getTemp().setAbilities(new StringList());
            _fight.getTemp().setAbility(DataBase.EMPTY_STRING);
            _fight.getTemp().setChosenIndex(IndexConstants.INDEX_NOT_FOUND_ELT);
            return;
        }
        if(_fight.getChoices().contains(key_)) {
            _fight.getTemp().setEvolutions(getEvolutions(_fight, _d, key_));
            _fight.getTemp().getEvolutions().put(DataBase.EMPTY_STRING, BoolVal.FALSE);
            String name_ = _fight.getChoices().getVal(key_).getName();
            _fight.getTemp().getEvolutions().put(name_, BoolVal.TRUE);
            _fight.getTemp().setAbilities(getAbilities(_fight, name_, key_));
            _fight.getTemp().setAbility(_fight.getChoices().getVal(key_).getAbility());
            NatStringTreeMap<BoolVal> tree_ = getMoves(_fight, name_, key_);
            for (String m : tree_.getKeys()) {
                tree_.put(m, BoolVal.FALSE);
            }
            for (String m : _fight.getChoices().getVal(key_).getKeptMoves()) {
                tree_.put(m, BoolVal.TRUE);
            }
            _fight.getTemp().setMoves(tree_);
        } else {
            _fight.getTemp().setChosenIndex(IndexConstants.INDEX_NOT_FOUND_ELT);
            _fight.getTemp().setMoves(new NatStringTreeMap<BoolVal>());
            _fight.getTemp().setEvolutions(new EvolutionChoiceMap(new NaturalComparator()));
            _fight.getTemp().setAbilities(new StringList());
            _fight.getTemp().setAbility(DataBase.EMPTY_STRING);
        }
    }

    static NatStringTreeMap<BoolVal> getMoves(Fight _fight, String _evo, byte _key) {
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(_key);
        NatStringTreeMap<BoolVal> map_ = new NatStringTreeMap<BoolVal>();
        map_.putAllMap(fighter_.getMoves(_evo));
        return map_;
    }

    static EvolutionChoiceMap getEvolutions(Fight _fight, DataBase _d, byte _key) {
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(_key);
        String lg_ = _d.getLanguage();
        StringMap<String> m_ = _d.getTranslatedPokemonCurLanguage(lg_);
        EvolutionChoiceMap map_;
        map_ = new EvolutionChoiceMap(new ComparatorTr<String>(m_));
        map_.put(DataBase.EMPTY_STRING, BoolVal.TRUE);
        for (String e: fighter_.getMovesAbilitiesEvos().getKeys()) {
            map_.put(e, BoolVal.FALSE);
        }
        return map_;
    }

    static StringList getAbilities(Fight _fight, String _evo, byte _key) {
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(_key);
        return fighter_.getAbilities(_evo);
    }

    static ByteMap<ChoiceOfEvolutionAndMoves> defaultChoices(Fight _fight) {
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = new ByteMap<ChoiceOfEvolutionAndMoves>();
        for (byte k: playerFighterWithLearnEvolveChoice(_fight)) {
            Fighter fighter_ = _fight.getUserTeam().refPartMembres(k);
            ChoiceOfEvolutionAndMoves defaultChoice_ = new ChoiceOfEvolutionAndMoves();
            NatStringTreeMap< BoolVal> map_ = fighter_.getMoves(DataBase.EMPTY_STRING);
            StringList keptMoves_ = movesList(map_);
            keptMoves_.sort();
            defaultChoice_.setKeptMoves(keptMoves_);
            choices_.put(k, defaultChoice_);
        }
        return choices_;
    }

    public static void addOrForgetMove(Fight _fight, String _move) {
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(_fight.getTemp().getChosenIndex());
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        if (StringUtil.contains(choice_.getKeptMoves(), _move)) {
            choice_.getKeptMoves().removeString(_move);
            _fight.getTemp().getMoves().put(_move, BoolVal.FALSE);
        } else {
            choice_.getKeptMoves().add(_move);
            _fight.getTemp().getMoves().put(_move, BoolVal.TRUE);
        }
    }

    public static void setAbility(Fight _fight, String _ability) {
        byte index_ = _fight.getTemp().getChosenIndex();
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(index_);
        if (!choices_.contains(key_)) {
            return;
        }
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        choice_.setAbility(_ability);
        _fight.getTemp().setAbility(_ability);
    }

    public static void setEvolution(Fight _fight, String _evo) {
        byte index_ = _fight.getTemp().getChosenIndex();
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(index_);
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        choice_.getKeptMoves().clear();
        String backEvo_ = choice_.getName();
        //set _fight.getEvolutions() map for displaying
        _fight.getTemp().getEvolutions().put(backEvo_, BoolVal.FALSE);
        _fight.getTemp().getEvolutions().put(_evo, BoolVal.TRUE);
        choice_.setName(_evo);
        StringList abilities_ = getAbilities(_fight, _evo, key_);
        _fight.getTemp().setAbilities(abilities_);
        _fight.getTemp().setAbility(abilities_.first());
        choice_.setAbility(abilities_.first());
        NatStringTreeMap<BoolVal> moves_ = getMoves(_fight, _evo, key_);
        _fight.getTemp().setMoves(moves_);
        StringList movesList_ = movesList(moves_);
        choice_.getKeptMoves().addAllElts(movesList_);
    }

    private static StringList movesList(NatStringTreeMap<BoolVal> _moves) {
        StringList keptMoves_ = new StringList();
        for (String m: _moves.getKeys()) {
            if (_moves.getVal(m) != BoolVal.TRUE) {
                continue;
            }
            keptMoves_.add(m);
        }
        return keptMoves_;
    }

    public static boolean possibleChoices(Fight _fight,DataBase _import) {
        _fight.clearComments();
        boolean valid_ = true;
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        for (byte k: choices_.getKeys()) {
            ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(k);
            Fighter fighter_ = _fight.getUserTeam().refPartMembres(k);
            String name_ = _import.translatePokemon(fighter_.getName());
            boolean validMoves_ = true;
            if (choice_.getKeptMoves().size() < fighter_.nbMoves()) {
                valid_ = false;
                validMoves_ = false;
            }
            if (choice_.getKeptMoves().size() > _import.getNbMaxMoves()) {
                valid_ = false;
                validMoves_ = false;
            }
            if (!validMoves_) {
                int nbChosen_ = choice_.getKeptMoves().size();
                int max_ = _import.getNbMaxMoves();
                int min_ = fighter_.nbMoves();
                _fight.addMessage(_import,Fight.ERR_EVOLVING, name_,Long.toString(min_), Long.toString(max_), Long.toString(nbChosen_));
            }
            if (choice_.getName().isEmpty()) {
                continue;
            }
            if (fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getAbilities().size() > DataBase.ONE_POSSIBLE_CHOICE && choice_.getAbility().isEmpty()) {
                valid_ = false;
                _fight.addMessage(_import, Fight.ERR_EVOLVING_AB, name_);
            }
        }
        _fight.getTemp().setError(!valid_);
        return valid_;
    }

    public static void learnAndEvolveAttack(Fight _fight,Difficulty _diff,DataBase _import) {
        FightEndRound.learnAndEvolve(_fight, _import);
        //heal ko fighter whose ground place for substitute is not back and restore their ground place to subst
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            Fighter fighter_ = _fight.getFighter(f);
//            if (!fighter_.estKo()) {
//                continue;
//            }
//            if (Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
            fighter_.fullHeal();
            fighter_.fullHealMessage(_import,_fight.getTemp());
            fighter_.affectGroundPlaceBySubst();
        }
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, false)) {
            Fighter fighter_ = _fight.getFighter(f);
//            if (!fighter_.estKo()) {
//                continue;
//            }
//            if (Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
            fighter_.fullHeal();
            fighter_.fullHealMessage(_import,_fight.getTemp());
            fighter_.affectGroundPlaceBySubst();
        }
        if(FightKo.endedFight(_fight,_diff)){
            return;
        }
        if(FightEndRound.proponedSwitch(_fight)){
            //if (FightEndRound.existSubstitute(_fight)) {
                //for switching places
            FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
            _fight.setState(FightState.SWITCH_PROPOSE);
            return;
            //}
        }
        //init places if no substitute for achieving far targets
        _fight.setState(FightState.ATTAQUES);
        FightEndRound.setPlacesForFighters(_fight, true);
        choiceArtificialIntelligenceMovesChoiceWhenTrainerFight(_fight, _diff, _import);
    }

    public static TeamPositionList fightersBelongingToUser(Fight _fight,boolean _user) {
        TeamPositionList list_ = new TeamPositionList();
        ByteMap<Fighter> map_ = _fight.getUserTeam().getMembers();
        for(byte c:map_.getKeys()){
            if (ComparatorBoolean.diff(map_.getVal(c).isBelongingToPlayer(), _user)) {
                continue;
            }
            list_.add(Fight.toUserFighter(c));
        }
        return list_;
    }

//    static void frontFighterChoiceFleeingCatching(Fight _fight){
//        Team equipe_=_fight.getUserTeam();
////        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
//        CustList<FighterPosition> cbts_= equipe_.playerFighterAtIndex(IndexConstants.FIRST_INDEX);
//        Fighter creatureLanceur_=cbts_.first().getFighter();
//        creatureLanceur_.cancelActions();
//    }

    public static TeamPositionsStringMapTeamPositionsRate
            remainingThrowersTargetsHp(Fight _fight, Difficulty _diff, DataBase _import) {
        TeamPositionsStringMapTeamPositionsRate map_;
        map_ = new TeamPositionsStringMapTeamPositionsRate();
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            StringList moves_ = allowedMovesNotEmpty(_fight, f, _import);
            StringMap<TeamPositionsPairRatesPair> mapMovesTargets_ = new StringMap<TeamPositionsPairRatesPair>();
            for (String m: moves_) {
                if (!(_import.getMove(m) instanceof DamagingMoveData)) {
                    continue;
                }
                TeamPositionsPairRates fighters_ = FightArtificialIntelligence.remainingFoeTargetHpAll(_fight, f, m, _diff, _import);
//                for (TargetCoords t: mapTargets_.getKeys()) {
//                    Team team_ = _fight.getTeams().getVal((byte) t.getTeam());
//                    for (byte f2_: team_.fightersAtCurrentPlace(t)) {
//                        fighters_.put(new TeamPosition((byte) t.getTeam(), f2_), mapTargets_.getVal(t));
//                    }
//                }
                TeamPositionsPairRates mapFighters_ = FightArtificialIntelligence.remainingPartnerTargetHp(_fight, f, m, _diff, _import);
                mapMovesTargets_.put(m, new TeamPositionsPairRatesPair(fighters_,mapFighters_));
            }
            map_.put(f, mapMovesTargets_);
        }
        return map_;
    }

    public static CustList<MovesListTeamPositionsList> sortedFightersBeginRoundWildFight(Fight _fight, Difficulty _diff, DataBase _data) {
        CustList<CustList<FighterNamePkNameMv>> allKeys_ = new CustList<CustList<FighterNamePkNameMv>>();
        allKeys_.add(new CustList<FighterNamePkNameMv>());
        for (EntryCust<Byte,Fighter> e:_fight.getFoeTeam().getMembers().entryList()) {
            StringList moves_ = allowedMovesNotEmpty(_fight, Fight.toFoeFighter(e.getKey()), _data);
            CustList<FighterNamePkNameMv> loc_ = new CustList<FighterNamePkNameMv>();
            NatStringTreeMap<String> s_ = new NatStringTreeMap<String>();
            for (String m: moves_) {
                s_.put(_data.translateMove(m),m);
            }
            for (EntryCust<String, String> m: s_.entryList()) {
                FighterNamePkNameMv k_ = new FighterNamePkNameMv();
                k_.setNameMv(m.getValue());
                k_.setNameMvTr(m.getKey());
                k_.setNamePk(e.getValue().getName());
                k_.setNumber(e.getKey());
                loc_.add(k_);
            }
            allKeys_ = loc_.cartesian(allKeys_);
        }
        CustList<MovesListTeamPositionsList> ls_ = new CustList<MovesListTeamPositionsList>();
        for (CustList<FighterNamePkNameMv> e: allKeys_) {
            for (FighterNamePkNameMv k:e) {
                FightArtificialIntelligence.setFirstChosenMove(_fight,Fight.toFoeFighter(k.getNumber()),k.getNameMv(),_diff,_data);
            }
            fightersSortMove(_fight, _data);
            ls_.add(new MovesListTeamPositionsList(e,new TeamPositionList(_fight.getTemp().getOrderedFighters())));
            for (FighterNamePkNameMv k:e) {
                _fight.getFoeTeam().getMembers().getVal(k.getNumber()).cancelActions();
            }
        }
        return ls_;
    }

    public static TeamPositionList sortedFightersBeginRound(Fight _fight, DataBase _data) {
        FightRound.setAllyChoices(_fight, _data);
        fightersSortMove(_fight, _data);
        cancelActions(_fight, FightOrder.fightersBelongingToUser(_fight, false));
        return _fight.getTemp().getOrderedFighters();
    }

    private static void fightersSortMove(Fight _fight, DataBase _data) {
        TeamPositionList fightersUsingMove_ = sortFighters(_fight, _data);
        for (TeamPosition f: fightersUsingMove_) {
            ((ActionMove) _fight.getFighter(f).getAction()).setFinalChosenMove(DataBase.EMPTY_STRING);
        }
    }

    private static TeamPositionList sortFighters(Fight _fight, DataBase _data) {
        TeamPositionList fightersUsingMove_ = FightOrder.fightersUsingMove(_fight, FightOrder.fighters(_fight));
        _fight.getTemp().getOrderedFighters().clear();
        _fight.getTemp().getOrderedFighters().addAllElts(fightersUsingMove_);
        for (TeamPosition f: fightersUsingMove_) {
            _fight.getFighter(f).choisirAttaqueFin();
        }
        FightOrder.sortFightersUsingMoveAmongList(_fight, _data);
        return fightersUsingMove_;
    }

    public static TeamPositionActionMoveMap
            sortedFightersUsingMoveDependingOnPlayerChoices(
                    Fight _fight, DataBase _data) {
        FightRound.setAllyChoices(_fight, _data);
        TeamPositionList fightersUsingMove_ = sortFighters(_fight, _data);
        TeamPositionActionMoveMap tree_;
        tree_ = new TeamPositionActionMoveMap(new SortedFighterActsComparator(_fight));
        for (TeamPosition f: _fight.getTemp().getOrderedFighters()) {
            tree_.put(f, (ActionMove) _fight.getFighter(f).getAction());
        }
        for (TeamPosition f: fightersUsingMove_) {
            ((ActionMove) _fight.getFighter(f).getAction()).setFinalChosenMove(DataBase.EMPTY_STRING);
        }
        cancelActions(_fight, FightOrder.fightersBelongingToUser(_fight, false));
        return tree_;
    }

    static void cancelActions(Fight _fight, TeamPositionList _fighters) {
        for (TeamPosition f: _fighters) {
            Fighter fighter_ = _fight.getFighter(f);
            fighter_.cancelActions();
        }
    }

    public static void setFirstChosenMoveFoeTarget(Fight _fight,byte _foeTarget){
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        CustList<FighterPosition> list_ = fightersFront(_fight);
        Fighter creature_=list_.first().getFighter();
        creature_.setFirstChosenMoveTarget(_fight.getTemp().getChosenMoveFront(),TargetCoords.toFoeTarget(_foeTarget));
    }

    public static void setFirstChosenMovePlayerTarget(Fight _fight,byte _playerTarget){
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        CustList<FighterPosition> list_ = fightersFront(_fight);
        Fighter creature_=list_.first().getFighter();
        creature_.setFirstChosenMoveTarget(_fight.getTemp().getChosenMoveFront(),TargetCoords.toUserTarget(_playerTarget));
    }

    static void setFirstChosenMove(String _attaque, CustList<FighterPosition> _ls){
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(_pos);
        if (_ls.isEmpty()) {
            return;
        }
        Fighter creature_= _ls.first().getFighter();
        creature_.setFirstChosenMove(_attaque);
    }

    public static void setSubstituteEndRound(Fight _fight,byte _newPlace) {
        CustList<FighterPosition> fighters_ = fighters(_fight);
        subsChoice(fighters_, _fight, _newPlace);
        _fight.getTemp().setChosenSubstitute(_newPlace);
    }

    static void setSubstituteFront(Fight _fight,byte _newPlace){
        CustList<FighterPosition> fighters_ = fightersFront(_fight);
//        byte substitute_ = Fighter.BACK;
//        Bytes listAll_ = new Bytes(equipe_.getMembers().getKeys());
//        listAll_.sort();
//        int i_ = IndexConstants.FIRST_INDEX;
//        for (byte b: listAll_) {
//            Fighter fighter_ = equipe_.getMembers().getVal(b);
//            if (NumberUtil.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
//            if (i_ == sub_) {
//                substitute_ = b;
//                break;
//            }
//            i_++;
//        }
//        for (Byte b: equipe_.getMembers().getKeys()) {
//            Fighter fighter_ = equipe_.getMembers().getVal(b);
//            if (!Numbers.eq(fighter_.getGroundPlaceSubst(), sub_)) {
//                continue;
//            }
//            list_.add(b);
//        }
//        if (list_.isEmpty()) {
//            return;
//        }
        subsChoice(fighters_, _fight, _newPlace);
    }

    private static CustList<FighterPosition> fightersFront(Fight _fight) {
        byte sub_ = _fight.getTemp().getChosenIndexFront();
        Team equipe_= _fight.getUserTeam();
//        CustList<Byte> list_ = new CustList<>();
        //MOD
        return equipe_.playerFighterAtIndex(sub_);
    }

    static void setSubstituteBack(Fight _fight,byte _newPlace){
        CustList<FighterPosition> bytes_ = fightersBack(_fight);
//        byte substitute_ = Fighter.BACK;
//        byte i_ = IndexConstants.FIRST_INDEX;
//        Bytes list_ = new Bytes(equipe_.getMembers().getKeys());
//        list_.sort(new NaturalComparator<Byte>() {
//            @Override
//            public int compare(Byte _o1, Byte _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
//        list_.sort();
//        for (byte b: list_) {
//            Fighter fighter_ = equipe_.getMembers().getVal(b);
//            if (!NumberUtil.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
//            if (i_ == sub_) {
//                substitute_ = b;
//                break;
//            }
//            i_++;
//        }
        subsChoice(bytes_, _fight, _newPlace);
    }

    private static CustList<FighterPosition> fightersBack(Fight _fight) {
        byte sub_ = _fight.getTemp().getChosenIndexBack();
        Team equipe_= _fight.getUserTeam();
        return equipe_.substituteAtIndex(sub_);
    }

    private static void subsChoice(CustList<FighterPosition> _fighters, Fight _fight, byte _newPlace) {
        if (_fighters.isEmpty()) {
            return;
        }
        _fight.getFirstPositPlayerFighters().put(_fighters.first().getFirstPosit(), _newPlace);
    }

    private static void setSubstituteSwitch(Fight _fight, CustList<FighterPosition> _subs, Fighter _fighter){
        //en:_fight.getSelectedActionCurFighter() is ActionType.SWITCH
        //fr:_fight.getSelectedActionCurFighter() vaut ActionType.SWITCH
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        if (_subs.isEmpty()) {
            _fighter.cancelActions();
        } else {
            _fighter.setSubstitute(_subs.first().getFirstPosit());
        }
        substitute(_fight,_fighter);
//        if (_subs.isEmpty()) {
//
//            _fight.getTemp().setChosenSubstitute(Fighter.BACK);
//            return;
//        }
//
//        _fight.getTemp().setChosenSubstitute((byte) Team.indexOfSubstitute(_fight.getUserTeam().getBackTeam(), _subs.first().getFirstPosit()));
    }

    static void chooseBackFighterWhileRound(Fight _fight, DataBase _data, CustList<FighterPosition> _subs) {
        _fight.getTemp().setError(false);
        if (_subs.isEmpty()) {
            cancelChooseBackFighterWhileRound(_fight);
            return;
        }
        Fighter fighter_ = _subs.first().getFighter();
        if (fighter_.estKo()) {
            String name_ = _data.translatePokemon(fighter_.getName());
            _fight.addMessage(_data,Fight.ERR_KO_SUBSTITUTE, name_);
            _fight.getTemp().setError(true);
            return;
        }
        fighter_ = _fight.getFighter(_fight.getCurrentUser());
        setSubstituteForMove(_subs.first(),fighter_);
    }

    private static void chooseBackFighterAddon(Fight _fight, DataBase _data, CustList<FighterPosition> _subs, Fighter _fighter) {
        _fight.getTemp().setError(false);
        if (_subs.isEmpty()) {
            _fighter.cancelSubstituteForMove();
            return;
        }
        Fighter fighter_ = _subs.first().getFighter();
        if (fighter_.estKo()) {
            String name_ = _data.translatePokemon(fighter_.getName());
            _fight.addMessage(_data,Fight.ERR_KO_SUBSTITUTE, name_);
            _fight.getTemp().setError(true);
            return;
        }
        //the window does not give the possibility to choose an ally pokemon
//        if (!fighter_.isBelongingToPlayer()) {
//            _fight.setError(true);
//            return;
//        }
        setSubstituteForMove(_subs.first(), _fighter);
    }

    static void setSubstituteForMove(FighterPosition _substitute, Fighter _fighter) {
//        CustList<Byte> list_ = team_.fightersAtCurrentPlace(_index);
        _fighter.setSubstituteForMove(_substitute.getFirstPosit());
    }

    public static void cancelChooseBackFighterWhileRound(Fight _fight) {
        Fighter fighter_ = _fight.getFighter(_fight.getCurrentUser());
        fighter_.cancelSubstituteForMove();
    }

    public static void setChosenHealingItem(Fight _fight, String _item,DataBase _import) {
        CustList<FighterPosition> list_ = fighters(_fight);
        setChosenHealingItem(_fight, _item, _import, list_);
    }

    static void setChosenHealingItemFront(Fight _fight,String _objet,DataBase _import){
        CustList<FighterPosition> list_ = fightersFront(_fight);
        setChosenHealingItem(_fight, _objet, _import, list_);
    }

    static void setChosenHealingItemBack(Fight _fight,String _objet,DataBase _import){
        CustList<FighterPosition> substitute_ = fightersBack(_fight);
        setChosenHealingItem(_fight, _objet, _import, substitute_);
    }

    private static void setChosenHealingItem(Fight _fight, String _objet, DataBase _import, CustList<FighterPosition> _list) {
        if (_list.isEmpty()) {
            return;
        }
        Item obj_ = _import.getItem(_objet);
        boolean chooseMove_ = false;
        if (obj_ instanceof HealingPp) {
            HealingPp pp_ = (HealingPp) obj_;
            chooseMove_ = pp_.healOneMove();
//            if (pp_.getHealingMoveFullpp()) {
//                chooseMove_ = true;
//            }
//            if (pp_.getHealedMovePp() > 0) {
//                chooseMove_ = true;
//            }
        }
        if (obj_ instanceof Berry) {
            Berry berry_ = (Berry) obj_;
            if (berry_.getHealPp() > 0) {
                chooseMove_ = true;
            }
        }
        _fight.getTemp().getCurrentFighterMoves().clear();
        if (chooseMove_) {
            _fight.getTemp().setChosenHealingMove(_objet);
            _fight.getTemp().setCurrentFighterMoves(fighterMovesList(_fight, _import, _list));
            return;
        }
        Fighter creature_= _list.first().getFighter();
        creature_.setChosenHealingObject(_objet, _import);
    }

    static void setChosenHealingItemMove(Fight _fight, String _move) {
        CustList<FighterPosition> list_ = fighters(_fight);
        Fighter creature_=list_.first().getFighter();
        creature_.setChosenHealingObjectMove(_fight.getTemp().getChosenHealingMove(),_move);
    }

    private static CustList<FighterPosition> fighters(Fight _fight) {
        byte index_ = _fight.getTemp().getChosenIndexFront();
        CustList<FighterPosition> list_;
        if (!NumberUtil.eq(index_, Fighter.BACK)) {
            list_ = fightersFront(_fight);
        } else {
            list_ = fightersBack(_fight);
        }
        return list_;
    }

    public static Rate gainBase(PointFoeExpObject _pointsFoeExp, Difficulty _diff, DataBase _import, String _expItem, short _winner, short _looser, byte _position) {
        return FightKo.gainBase(_pointsFoeExp, _diff, _import, _expItem, _winner, _looser, _position);
    }

    public static Rate rateWonPoint(Difficulty _diff, DataBase _import, short _winner, short _looser) {
        return FightKo.rateWonPoint(_diff, _import, _winner, _looser);
    }

    public static void simulate(Fight _fight,FightSimulationActions _fightSimulationActions, int _index,
                                Player _utilisateur,Difficulty _diff,DataBase _import){
        _fight.getTemp().setSimulation(true);
        int round_ = IndexConstants.FIRST_INDEX;
        while (round_ < _fightSimulationActions.getActionsBeforeRound(_index).size()) {
            if (stopSimulation(_fight)) {
                return;
            }
            ByteMap<ChoiceOfEvolutionAndMoves> evolutions_ = _fightSimulationActions.getMovesAbilities(_index).get(round_);
            beforeRound(_fight, _fightSimulationActions, _index, _diff, _import, round_);
            if (!FightRules.playable(_fight, _utilisateur, _diff, _import)) {
                _fight.getTemp().setIssue(IssueSimulation.RULES_MOVES);
                _fight.addIssueRulesMovesMessage(_import);
                _fight.getTemp().setAcceptableChoices(false);
                return;
            }
            if (!endedFullRound(_fight, _utilisateur, _diff, _import)) {
                return;
            }
//            while (true) {
//                roundAllThrowersChooseActionsFoe(_fight, _diff, _utilisateur, _import);
//                if (FightKo.endedFight(_fight, _diff)) {
//                    _fight.setAcceptableChoices(win(_fight));
//                    return;
//                }
//                if (_fight.getState() != FightState.SWITCH_APRES_ATTAQUE) {
//                    break;
//                }
//            }
//            do {
//                roundAllThrowersChooseActionsFoe(_fight,_diff,_utilisateur,_import);
//                if(FightKo.endedFight(_fight,_diff)){
//                    _fight.setAcceptableChoices(win(_fight));
//                    return;
//                }
//            } while (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE);
            if (_fight.getState() == FightState.APPRENDRE_EVOLUER && issueEvolve(_fight, _diff, _import, evolutions_)) {
                return;
            }
            if (existPlayerFaintedOrFoeNotFainted(_fight,_import)) {
                return;
            }
//            if(win(_fight)){
//                return;
//            }
            if (notSubsti(_fight, _fightSimulationActions, _index, _utilisateur, _diff, _import, round_)) {
                return;
            }
            round_++;
        }
    }

    private static boolean issueEvolve(Fight _fight, Difficulty _diff, DataBase _import, ByteMap<ChoiceOfEvolutionAndMoves> _evolutions) {
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        for (byte c : _fight.getChoices().getKeys()) {
//                    ChoiceOfEvolutionAndMoves calculated_;
//                    calculated_ = _fight.getChoices().getVal(c);
            //_fight.setChosenIndex(c);
//                    for (String m: new StringList(calculated_.getKeptMoves())) {
//                        byte key_ = _fight.getUserTeam().fighterAtIndex(_fight.getChosenIndex());
//                        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
//                        choice_.getKeptMoves().removeString(m);
//                    }
//                    if (!evolutions_.getVal(c).getName().isEmpty()) {
//                        //setEvolution(_fight, evolutions_.getVal(c).getName());
//                        //setAbility(_fight, evolutions_.getVal(c).getAbility());
//                    }
            byte key_ = _fight.getUserTeam().fighterAtIndex(c);
            ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
            if (!_evolutions.getVal(c).getName().isEmpty()) {
                choice_.setName(_evolutions.getVal(c).getName());
                choice_.setAbility(_evolutions.getVal(c).getAbility());
            }
            choice_.getKeptMoves().clear();
            choice_.getKeptMoves().addAllElts(_evolutions.getVal(c).getKeptMoves());
//                    for (String m: evolutions_.getVal(c).getKeptMoves()) {
//                        choice_.getKeptMoves().add(m);
//                    }
        }
        if (!possibleChoices(_fight, _import)) {
            _fight.getTemp().setIssue(IssueSimulation.RULES_LEARN);
            _fight.addIssueRulesLearnMessage(_import);
            _fight.getTemp().setAcceptableChoices(false);
            return true;
        }
        learnAndEvolveAttack(_fight, _diff, _import);
        return false;
    }

    private static boolean existPlayerFaintedOrFoeNotFainted(Fight _fight, DataBase _import) {
        Team equipeUt_ = _fight.getUserTeam();
        for (byte c : equipeUt_.getMembers().getKeys()) {
            Fighter creatureLanceur_ = equipeUt_.getMembers().getVal(c);
            if (!creatureLanceur_.estKo()) {
                continue;
            }
            _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            _fight.addIssueAfterFightMessage(_import);
            _fight.getTemp().setAcceptableChoices(false);
            return true;
        }
        Team equipeAdv_ = _fight.getFoeTeam();
        for (byte c : equipeAdv_.getMembers().getKeys()) {
            Fighter creatureLanceur_ = equipeAdv_.getMembers().getVal(c);
            if (creatureLanceur_.estKo() || creatureLanceur_.estArriere()) {
                continue;
            }
            _fight.getTemp().setIssue(IssueSimulation.NOT_KO_FOE);
            _fight.addIssueAfterFightMessage(_import);
            _fight.getTemp().setAcceptableChoices(false);
            return true;
        }
        return false;
    }

    private static boolean notSubsti(Fight _fight, FightSimulationActions _fightSimulationActions, int _index, Player _utilisateur, Difficulty _diff, DataBase _import, int _round) {
        FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
        _fight.setState(FightState.SWITCH_PROPOSE);
        boolean test_ = false;
        //_fight.getState() != FightState.ATTAQUES
        while (keepLoop(_fight, test_,_import)) {
            simuSubsti(_fight, _fightSimulationActions, _index, _diff, _import, _round);
            if (!FightRules.substitutable(_fight, _diff, _import)) {
                _fight.getTemp().setIssue(IssueSimulation.RULES_SWITCH);
                _fight.addIssueRulesSwitchMessage(_import);
                _fight.getTemp().setAcceptableChoices(false);
                return true;
            }
            FightSending.sendSubstitutes(_fight, _diff, _utilisateur, _import);
//                if (!keepLoop(_fight)) {
//                    break;
//                }
//                for(Byte c:equipeUt_.getMembers().getKeys()){
//                    Fighter creatureLanceur_=equipeUt_.getMembers().getVal(c);
//                    if(!creatureLanceur_.estKo()){
//                        continue;
//                    }
//                    _fight.setAcceptableChoices(false);
//                    return;
//                }
//                if(win(_fight)){
//                    return;
//                }
//                if (_fight.getState() != FightState.ATTAQUES) {
//                    round_++;
//                }
            test_ = true;
        }
        return false;
    }

    private static void simuSubsti(Fight _fight, FightSimulationActions _fightSimulationActions, int _index, Difficulty _diff, DataBase _import, int _round) {
        CustList<ActionSwitch> backActions_ = _fightSimulationActions.getActionsSubstitutingBack(_index).get(_round);
        CustList<ActionSwitch> frontActions_ = _fightSimulationActions.getActionsSubstitutingFront(_index).get(_round);
        int nbActionsLen_;
        nbActionsLen_ = frontActions_.size();
        for (byte f = IndexConstants.FIRST_INDEX; f < nbActionsLen_; f++) {
            chooseFrontFighter(_fight, f, _diff, _import);
            setSubstituteFront(_fight, frontActions_.get(f).getSubstitute());
        }
        nbActionsLen_ = backActions_.size();
        for (byte f = IndexConstants.FIRST_INDEX; f < nbActionsLen_; f++) {
            chooseBackFighter(_fight, f, _import);
            setSubstituteBack(_fight, backActions_.get(f).getSubstitute());
        }
    }

    private static boolean endedFullRound(Fight _fight,
                                          Player _utilisateur,Difficulty _diff,DataBase _import) {
        while (true) {
            roundAllThrowersChooseActionsFoe(_fight, _diff, _utilisateur, _import);
            if (FightKo.endedFight(_fight, _diff)) {
                _fight.getTemp().setAcceptableChoices(win(_fight));
                return false;
            }
            if (_fight.getState() != FightState.SWITCH_APRES_ATTAQUE) {
                return true;
            }
        }
    }

    private static void beforeRound(Fight _fight, FightSimulationActions _fightSimulationActions, int _index, Difficulty _diff, DataBase _import, int _round) {
        CustList<ActionMove> actions_ = _fightSimulationActions.getActionsBeforeRound(_index).get(_round);
        int nbActions_ = actions_.size();
        for (byte i2_ = IndexConstants.FIRST_INDEX; i2_ < nbActions_; i2_++) {
            ActionMove action_ = actions_.get(i2_);
            chooseFrontFighter(_fight, i2_, _diff, _import);
            String move_ = action_.getFirstChosenMove();
            chooseMove(_fight, move_, _diff, _import);
            if (!_fight.getTemp().getChosableFoeTargets().isEmpty()) {
                //!_fight.getChosableFoeTargets().isEmpty() <==> !_fight.getChosablePlayerTargets().isEmpty()
                if (NumberUtil.eq(action_.getChosenTargets().first().getTeam(), Fight.CST_FOE)) {
                    setFirstChosenMoveFoeTarget(_fight, (byte) action_.getChosenTargets().first().getPosition());
                } else {
                    setFirstChosenMovePlayerTarget(_fight, (byte) action_.getChosenTargets().first().getPosition());
                }
            }
//                if (!_fight.getChosableFoeTargets().isEmpty()) {
//                    setFirstChosenMoveFoeTarget(_fight, (byte) action_.getChosenTargets().first().getPosition());
//                }
//                if (!_fight.getChosablePlayerTargets().isEmpty()) {
//                    setFirstChosenMovePlayerTarget(_fight, (byte) action_.getChosenTargets().first().getPosition());
//                }
            /*MoveData fAtt_=_import.getMove(move_);
            if (fAtt_.getTargetChoice().isWithChoice()) {
                setFirstChosenMoveFoeTarget(_fight, (byte) action_.getChosenTargets().first().getPosition());
            } else {
                setFirstChosenMove(_fight, i2, move_);
            }*/
        }
    }

    static boolean stopSimulation(Fight _fight) {
        return win(_fight) || !_fight.getTemp().getAcceptableChoices();
    }

    static boolean keepLoop(Fight _fight, boolean _test, DataBase _import) {
        if (existKoPlayer(_fight)) {
            return false;
        }
        if (_fight.getState() == FightState.ATTAQUES) {
            return false;
        }
        if(win(_fight)){
            return false;
        }
        if (_test) {
            _fight.getTemp().setIssue(IssueSimulation.TOO_HARD_SIMULATION);
            _fight.addIssueTooHardMessage(_import);
            _fight.getTemp().setAcceptableChoices(false);
            return false;
        }
        return true;
    }

    static boolean existKoPlayer(Fight _fight) {
        Team equipeUt_ = _fight.getUserTeam();
        boolean exist_ = false;
        for(byte c:equipeUt_.getMembers().getKeys()){
            Fighter creatureLanceur_=equipeUt_.getMembers().getVal(c);
            if(!creatureLanceur_.estKo()){
                continue;
            }
            _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            _fight.getTemp().setAcceptableChoices(false);
            exist_ = true;
        }
        return exist_;
    }

    public static Rate numberNecessaryPointsForGrowingLevel(String _name, long _niveau, DataBase _import) {
        return Fighter.numberNecessaryPointsForGrowingLevel(_name,_niveau,_import);
    }

    public static void initializeFromSavedGame(Fight _fight, Difficulty _diff,Player _user,DataBase _data) {
        _fight.getTemp().setAcceptableChoices(true);
        _fight.getTemp().setIssue(IssueSimulation.NOTHING);
        if (!_fight.getFightType().isExisting()) {
            return;
        }
        _fight.getTemp().setError(false);
        _fight.getTemp().setFullHealing(false);
        _fight.getTemp().setSuccessfulEffects(new NbEffectFighterCoordss());
        _fight.getTemp().setDamageByCurrentUser(new TeamPositionsRate());
        _fight.getTemp().setSufferingTargetStatus(new StringList());
        _fight.getTemp().setLettingUserAttackWithStatus(true);
        _fight.getTemp().setEndRound(false);
        _fight.getTemp().setSimulation(false);
        _fight.setEnabledMessages(true);
        _fight.getTemp().setInvokedMove(false);
        _fight.getTemp().setSending(false);
        _fight.getTemp().setKeepStatus(false);
        _fight.getTemp().setEnabledHealingPartner(false);
        _fight.getTemp().setChangeThrower(false);
        _fight.getTemp().setSuccessfulInvokation(false);
        _fight.getTemp().setSuccessfulUse(false);
        _fight.getTemp().setPutKo(false);
        _fight.getTemp().setDamage(new DamageMoveCountUser());
        _fight.getTemp().getDamage().setDamage(Rate.zero());
        _fight.getTemp().getDamage().setDamageClone(Rate.zero());
        _fight.getTemp().getDamage().setDamageCount(Rate.zero());
        _fight.getTemp().setDamageKo(Rate.zero());
        _fight.getTemp().setOrderedFighters(new TeamPositionList());
        _fight.getTemp().setRemainingFighters(new TeamPositionList());
        _fight.getTemp().setChosablePlayerTargets(new CustList<ChosableTargetName>());
        _fight.getTemp().setChosableFoeTargets(new CustList<ChosableTargetName>());
//        _fight.setChosenFoeTarget(Fighter.BACK);
//        _fight.setChosenPlayerTarget(Fighter.BACK);
        _fight.getTemp().setChosenIndexBack(Fighter.BACK);
        _fight.getTemp().setChosenIndexFront(Fighter.BACK);
        _fight.getTemp().setPossibleActionsCurFighter(new IdList<ActionType>());
        _fight.getTemp().setSelectedActionCurFighter(ActionType.NOTHING);
        _fight.getTemp().setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
        _fight.getTemp().setChosenMoveFront(DataBase.EMPTY_STRING);
        _fight.getTemp().setChosenHealingMove(DataBase.EMPTY_STRING);
        _fight.getTemp().setChosenSubstitute(Fighter.BACK);
        _fight.getTemp().setChosenIndex(Fighter.BACK);
        _fight.getTemp().setMoves(new NatStringTreeMap<BoolVal>());
        _fight.getTemp().setEvolutions(new EvolutionChoiceMap(new NaturalComparator()));
        _fight.getTemp().setAbilities(new StringList());
        _fight.getTemp().setAbility(DataBase.EMPTY_STRING);
        _fight.getTemp().setKeepRound(true);
        _fight.getTemp().setEndRoundFightKoPlayer(true);
        _fight.getTemp().setEffects(new CustList<AnimationInt>());
        _fight.setComment(new Comment());
        _fight.getTemp().setCurrentActivity(new ActivityOfMove());
        _fight.getTemp().setKos(new ByteMap<BoolVal>());
        _fight.getTemp().getKos().put(Fight.CST_PLAYER,BoolVal.FALSE);
        _fight.getTemp().getKos().put(Fight.CST_FOE,BoolVal.FALSE);
        for (Fighter f: _fight.getFoeTeam().getMembers().values()) {
            f.setIv(new IdMap<Statistic,Short>());
            boolean isCaught_ = _user.estAttrape(f.getName());
            if (isCaught_) {

                f.initIvAdv(_diff, _data.getBallDef());
            } else {
                f.initIvAdv(_diff, DataBase.EMPTY_STRING);
            }
        }
        for (Fighter f: _fight.getUserTeam().getMembers().values()) {
            f.setIv(new IdMap<Statistic,Short>());
            f.initIvUt(_diff);
            f.initHp();
        }
        fixUserTeamFront(_fight, _data);
    }

    private static void fixUserTeamFront(Fight _fight, DataBase _data) {
        if (_fight.getState() != FightState.ATTAQUES && _fight.getState() != FightState.SWITCH_APRES_ATTAQUE) {
            return;
        }
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f);
            AbstractAction action_ = fighter_.getAction();
            if (action_ instanceof ActionMove) {
                String move_ = ((ActionMove) action_).getFirstChosenMove();
                MoveData fAtt_ = _data.getMove(move_);
                if (fAtt_ == null) {
                    fighter_.cancelActions();
                    continue;
                }
                if (!fAtt_.getTargetChoice().isWithChoice()) {
                    fighter_.getChosenTargets().clear();
                }
            }
        }
    }

    public static Fight cpFight(Fight _currentFight) {
        Fight savedFight_ = new Fight();
        savedFight_.setFightType(_currentFight.getFightType());
        savedFight_.setEnvType(_currentFight.getEnvType());
        savedFight_.setMult(_currentFight.getMult());
        savedFight_.setPlayerMaxNumberFrontFighters(_currentFight.getPlayerMaxNumberFrontFighters());
        savedFight_.setEnabledMoves(_currentFight.getEnabledMoves());
        savedFight_.setStillEnabledMoves(_currentFight.getStillEnabledMoves());
        savedFight_.setTeams(new ByteMap<Team>());
        for (byte k: _currentFight.getTeams().getKeys()) {
            Team team_;
            team_ = saveTeam(_currentFight.getTeams().getVal(k));
            savedFight_.getTeams().put(k, team_);
        }
        savedFight_.setNbFleeAttempt(_currentFight.getNbFleeAttempt());
        savedFight_.setNbRounds(_currentFight.getNbRounds());
        savedFight_.setWinningMoney(_currentFight.getWinningMoney());
        savedFight_.setCurrentUser(_currentFight.getCurrentUser());
        savedFight_.setCurrentUserFlee(_currentFight.getCurrentUserFlee());
        savedFight_.setCatchingBalls(_currentFight.getCatchingBalls());
        savedFight_.setState(_currentFight.getState());
        savedFight_.setUsedItemsWhileRound(_currentFight.getUsedItemsWhileRound());
        savedFight_.setFirstPositPlayerFighters(_currentFight.getFirstPositPlayerFighters());
        savedFight_.setFirstPositFoeFighters(_currentFight.getFirstPositFoeFighters());
        savedFight_.setAllyChoice(_currentFight.getAllyChoice());
        savedFight_.setLostObjects(_currentFight.getLostObjects());
        savedFight_.setChoices(new ByteMap<ChoiceOfEvolutionAndMoves>());
        for (byte k: _currentFight.getChoices().getKeys()) {
            ChoiceOfEvolutionAndMoves choice_;
            choice_ = saveChoice(_currentFight.getChoices().getVal(k));
            savedFight_.getChoices().put(k, choice_);
        }
        return savedFight_;
    }

    public static ChoiceOfEvolutionAndMoves saveChoice(ChoiceOfEvolutionAndMoves _currentChoice) {
        ChoiceOfEvolutionAndMoves savedChoice_;
        savedChoice_ = new ChoiceOfEvolutionAndMoves();
        savedChoice_.setName(_currentChoice.getName());
        savedChoice_.setKeptMoves(_currentChoice.getKeptMoves());
        savedChoice_.setAbility(_currentChoice.getAbility());
        return savedChoice_;
    }

    public static Team saveTeam(Team _currentTeam) {
        Team savedTeam_;
        savedTeam_ = new Team();
        savedTeam_.setEnabledMovesByGroup(_currentTeam.getEnabledMovesByGroup());
        savedTeam_.setEnabledMoves(_currentTeam.getEnabledMoves());
        savedTeam_.setEnabledMovesWhileSendingFoe(_currentTeam.getEnabledMovesWhileSendingFoe());
        savedTeam_.setEnabledMovesWhileSendingFoeUses(_currentTeam.getEnabledMovesWhileSendingFoeUses());
        savedTeam_.setNbUsesMoves(_currentTeam.getNbUsesMoves());
        savedTeam_.setNbUsesMovesRound(_currentTeam.getNbUsesMovesRound());
        savedTeam_.setHealAfter(_currentTeam.getHealAfter());
        savedTeam_.setMovesAnticipation(_currentTeam.getMovesAnticipation());
        savedTeam_.setMembers(new ByteMap<Fighter>());
        for (byte k: _currentTeam.getMembers().getKeys()) {
            Fighter fighter_;
            fighter_ = saveFighter(_currentTeam.getMembers().getVal(k));
            savedTeam_.getMembers().put(k, fighter_);
        }
        savedTeam_.setPlayerFightersAgainstFoe(_currentTeam.getPlayerFightersAgainstFoe());
        savedTeam_.setNbKoRound(_currentTeam.getNbKoRound());
        savedTeam_.setNbKoPreviousRound(_currentTeam.getNbKoPreviousRound());
        savedTeam_.setSuccessfulMovesRound(_currentTeam.getSuccessfulMovesRound());
        return savedTeam_;
    }

    public static Fighter saveFighter(Fighter _currentFighter) {
        Fighter savedFighter_;
        savedFighter_ = new Fighter();
        savedFighter_.setName(_currentFighter.getName());
        savedFighter_.setNickname(_currentFighter.getNickname());
        savedFighter_.setGender(_currentFighter.getGender());
        savedFighter_.setWeight(_currentFighter.getWeight());
        savedFighter_.setHeight(_currentFighter.getHeight());
        savedFighter_.setCurrentName(_currentFighter.getCurrentName());
        savedFighter_.setCurrentGender(_currentFighter.getCurrentGender());
        savedFighter_.setLastUsedItem(_currentFighter.getLastUsedItem());
        savedFighter_.setItem(_currentFighter.getItem());
        savedFighter_.setExpItem(_currentFighter.getExpItem());
        savedFighter_.setAbility(_currentFighter.getAbility());
        savedFighter_.setCurrentAbility(_currentFighter.getCurrentAbility());
        savedFighter_.setStatus(_currentFighter.getStatus());
        savedFighter_.setStatusRelat(_currentFighter.getStatusRelat());
        savedFighter_.setNbRounds(_currentFighter.getNbRounds());
        savedFighter_.setTypes(_currentFighter.getTypes());
        savedFighter_.setMoves(_currentFighter.getMoves());
        savedFighter_.setCurrentMoves(_currentFighter.getCurrentMoves());
        savedFighter_.setEv(_currentFighter.getEv());
        savedFighter_.setStatisBase(_currentFighter.getStatisBase());
        savedFighter_.setStatisBoost(_currentFighter.getStatisBoost());
        savedFighter_.setRemainingHp(_currentFighter.getRemainingHp());
        savedFighter_.setClone(_currentFighter.getClone());
        savedFighter_.setEnabledMoves(_currentFighter.getEnabledMoves());
        savedFighter_.setEnabledMovesProt(_currentFighter.getEnabledMovesProt());
        savedFighter_.setProtectedAgainstMoveTypes(_currentFighter.getProtectedAgainstMoveTypes());
        savedFighter_.setEnabledMovesUnprot(_currentFighter.getEnabledMovesUnprot());
        savedFighter_.setEnabledMovesEndRound(_currentFighter.getEnabledMovesEndRound());
        savedFighter_.setEnabledMovesConstChoices(_currentFighter.getEnabledMovesConstChoices());
        savedFighter_.setEnabledMovesForAlly(_currentFighter.getEnabledMovesForAlly());
        savedFighter_.setDamageRateInflictedByType(_currentFighter.getDamageRateInflictedByType());
        savedFighter_.setDamageRateSufferedByType(_currentFighter.getDamageRateSufferedByType());
        savedFighter_.setActed(_currentFighter.isActed());
        savedFighter_.setGroundPlace(_currentFighter.getGroundPlace());
        savedFighter_.setGroundPlaceSubst(_currentFighter.getGroundPlaceSubst());
        savedFighter_.setWonExp(_currentFighter.getWonExp());
        savedFighter_.setWonExpSinceLastLevel(_currentFighter.getWonExpSinceLastLevel());
        savedFighter_.setLevel(_currentFighter.getLevel());
        savedFighter_.setHappiness(_currentFighter.getHappiness());
        savedFighter_.setUsedBallCatching(_currentFighter.getUsedBallCatching());
        savedFighter_.setIncrUserAccuracy(_currentFighter.getIncrUserAccuracy());
        savedFighter_.setNbUsesMoves(_currentFighter.getNbUsesMoves());
        savedFighter_.setNbPrepaRound(_currentFighter.getNbPrepaRound());
        savedFighter_.setDisappeared(_currentFighter.isDisappeared());
        savedFighter_.setNeedingToRecharge(_currentFighter.isNeedingToRecharge());
        savedFighter_.setTrackingMoves(_currentFighter.getTrackingMoves());
        savedFighter_.setTrappingMoves(_currentFighter.getTrappingMoves());
        savedFighter_.setLastSufferedMove(_currentFighter.getLastSufferedMove());
        savedFighter_.setLastSufferedMoveTypes(_currentFighter.getLastSufferedMoveTypes());
        savedFighter_.setDamageSufferedCateg(_currentFighter.getDamageSufferedCateg());
        savedFighter_.setDamageSufferedCategRound(_currentFighter.getDamageSufferedCategRound());
        savedFighter_.setLastUsedMove(_currentFighter.getLastUsedMove());
        savedFighter_.setUsedMoveLastRound(_currentFighter.getUsedMoveLastRound());
        savedFighter_.setAlreadyInvokedMovesRound(_currentFighter.getAlreadyInvokedMovesRound());
        savedFighter_.setLastSuccessfulMove(_currentFighter.getLastSuccessfulMove());
        savedFighter_.setCopiedMoves(_currentFighter.getCopiedMoves());
        savedFighter_.setNbRepeatingSuccessfulMoves(_currentFighter.getNbRepeatingSuccessfulMoves());
        savedFighter_.setUsingItem(_currentFighter.isUsingItem());
        savedFighter_.setSuccessfulMove(_currentFighter.isSuccessfulMove());
        savedFighter_.setChanged(_currentFighter.isChanged());
        savedFighter_.setEnabledImmuAbilities(_currentFighter.getEnabledImmuAbilities());
        savedFighter_.setPrivateMoves(_currentFighter.getPrivateMoves());
        savedFighter_.setBelongingToPlayer(_currentFighter.isBelongingToPlayer());
        savedFighter_.setAction(_currentFighter.getAction());
        savedFighter_.setMovesToBeLearnt(_currentFighter.getMovesToBeLearnt());
        savedFighter_.setMovesAbilitiesEvos(_currentFighter.getMovesAbilitiesEvos());
        return savedFighter_;
    }

    static void ajouterRelationAutre(DataBase _data, Fight _fight, TeamPosition _key, TeamPositionList _list) {
        for (TeamPosition t: _list) {
            _fight.getFighter(t).ajouterRelationAutre(_key, _data);
        }
    }
}
