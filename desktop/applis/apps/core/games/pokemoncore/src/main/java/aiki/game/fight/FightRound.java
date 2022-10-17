package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.util.WeatherTypeRate;
import aiki.fight.util.WeatherTypes;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationSwitch;
import aiki.game.fight.enums.AccurayResult;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.fight.util.NextUsers;
import aiki.game.fight.util.RandomBoolResults;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.util.TargetCoordsList;
import aiki.util.TeamPositionList;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;

final class FightRound {

    private FightRound() {
    }

    static void roundAllThrowers(Fight _fight,Difficulty _diff,Player _user, DataBase _import){
        beginRound(_fight, _diff, _import);
        while (_fight.isKeepRound()) {
            roundUser(_fight, _diff, _import);
        }
        endRoundFight(_fight, _diff, _user, _import);
    }

    static void setAllyChoices(Fight _fight,DataBase _import) {
        Listable<MoveTarget> values_ = _fight.getAllyChoiceValuesSet();
        if (values_.isEmpty()) {
            return;
        }
        MoveTarget choice_ = values_.first();
        for (TeamPosition partner_: FightOrder.notKoFrontFightersBelongingToUser(_fight,false)) {
            TeamPositionList playerList_ = FightOrder.notKoFrontFightersBelongingToUser(_fight, true);
            boolean foundCombo_ = foundComboSpecTarget(_fight, _import, partner_, playerList_);
            if (!foundCombo_) {
                foundCombo_ = foundComboGeneTarget(_fight, _import, partner_, playerList_);
            }
            if (!foundCombo_) {
                String move_ = choice_.getMove();
                if (move_.isEmpty()) {
                    continue;
                }
                TargetCoords c = choice_.getTarget();
                FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, partner_, move_, c, _import);
                FightArtificialIntelligence.setBatonPassAlly(_fight, partner_, move_, _import);
            }
        }
    }

    private static boolean foundComboGeneTarget(Fight _fight, DataBase _import, TeamPosition _partner, TeamPositionList _playerList) {
        for (MoveTarget p: _fight.getAllyChoiceSet()) {
            if (foundComboGeneTarget(_fight, _import, _partner, _playerList,p)) {
                return true;
            }
        }
        return false;
    }
    private static boolean foundComboGeneTarget(Fight _fight, DataBase _import, TeamPosition _partner, TeamPositionList _playerList, MoveTarget _p) {
        if (_playerList.isEmpty()) {
            return false;
        }
        Fighter fighter_ = _fight.getFighter(_playerList.first());
        if (!StringUtil.quickEq(fighter_.getFirstChosenMove(), _p.getMove())) {
            return false;
        }
        String allyMove_ = _fight.getAllyChoiceVal(_p).getMove();
        if (allyMove_.isEmpty()) {
            return false;
        }
        TargetCoords allyTarget_ = _fight.getAllyChoiceVal(_p).getTarget();
        FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, _partner, allyMove_, allyTarget_, _import);
        FightArtificialIntelligence.setBatonPassAlly(_fight, _partner, allyMove_, _import);
        return true;
    }

    private static boolean foundComboSpecTarget(Fight _fight, DataBase _import, TeamPosition _partner, TeamPositionList _playerList) {
        for (MoveTarget p: _fight.getAllyChoiceSet()) {
            if (foundComboSpecTarget(_fight, _import, _partner, _playerList,p)) {
                return true;
            }
        }
        return false;
    }
    private static boolean foundComboSpecTarget(Fight _fight, DataBase _import, TeamPosition _partner, TeamPositionList _playerList, MoveTarget _p) {
        if (_playerList.isEmpty()) {
            return false;
        }
        Fighter fighter_ = _fight.getFighter(_playerList.first());
        if (!StringUtil.quickEq(fighter_.getFirstChosenMove(), _p.getMove())) {
            return false;
        }
        TargetCoordsList list_ = fighter_.getChosenTargets();
        if (list_.isEmpty() || !TargetCoords.eq(list_.first(), _p.getTarget())) {
            return false;
        }
        String allyMove_ = _fight.getAllyChoiceVal(_p).getMove();
        if (allyMove_.isEmpty()) {
            return false;
        }
        TargetCoords allyTarget_ = _fight.getAllyChoiceVal(_p).getTarget();
        FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, _partner, allyMove_, allyTarget_, _import);
        FightArtificialIntelligence.setBatonPassAlly(_fight, _partner, allyMove_, _import);
        return true;
    }

    static void calculateNextFighters(Fight _fight, TeamPositionList _fighters, DataBase _import) {
        _fight.getRemainingFighters().clear();
        NextUsers cbtsNonJoue_;
        cbtsNonJoue_ = nextFighters(_fight, _fighters, _import);
        if(!_fight.getAcceptableChoices()){
            _fight.setKeepRound(false);
            return;
        }
        if (cbtsNonJoue_.getNextFighters().isEmpty()) {
            _fight.setKeepRound(false);
            return;
        }
        _fight.getRemainingFighters().addAllElts(cbtsNonJoue_.getNextFighters());
        _fight.setCurrentUser(cbtsNonJoue_.getNextFighters().first());
        for(TeamPosition e:cbtsNonJoue_.getItemUsers()){
            Fighter creature_=_fight.getFighter(e);
            creature_.useObject();
        }
    }

    static NextUsers nextFighters(Fight _fight, TeamPositionList _fighters,DataBase _import) {
        NextUsers nextFighters_;
        nextFighters_ = new NextUsers(new TeamPositionList(),new TeamPositionList());
        TeamPositionList cbts_;
        if (_fighters.isEmpty()) {
            cbts_ = FightOrder.fightersHavingToAct(_fight,false,_import);
            cbts_ = FightOrder.fightersBeingHealed(_fight, cbts_);
            if (!cbts_.isEmpty()) {
                _fight.getOrderedFighters().clear();
                _fight.getOrderedFighters().addAllElts(cbts_);
                FightOrder.sortFightersBeingHealedAmongList(_fight);
                cbts_ = _fight.getOrderedFighters();
                nextFighters_.getNextFighters().add(cbts_.first());
                return nextFighters_;
            }
            cbts_ = FightOrder.fightersHavingToAct(_fight,false,_import);
            cbts_ = FightOrder.fightersSwitching(_fight, cbts_);
            if (!cbts_.isEmpty()) {
                _fight.getOrderedFighters().clear();
                _fight.getOrderedFighters().addAllElts(cbts_);
                FightOrder.sortFightersSwitchingAmongList(_fight, _import);
                cbts_ = _fight.getOrderedFighters();
                nextFighters_.getNextFighters().add(cbts_.first());
                return nextFighters_;
            }
            cbts_ = FightOrder.fightersHavingToAct(_fight,false,_import);
            cbts_ = FightOrder.fightersUsingMove(_fight, cbts_);
            _fight.getOrderedFighters().clear();
            _fight.getOrderedFighters().addAllElts(cbts_);
            FightOrder.sortFightersUsingMoveAmongList(_fight,_import);
            cbts_ = _fight.getOrderedFighters();
        } else {
            nextFighters_ = new NextUsers(_fighters, nextFighters_.getItemUsers());
            return nextFighters_;
        }
        if(cbts_.isEmpty()){
            cbts_ = FightOrder.fightersHavingToAct(_fight,true,_import);
            cbts_ = FightOrder.fightersUsingMove(_fight, cbts_);
            _fight.getOrderedFighters().clear();
            _fight.getOrderedFighters().addAllElts(cbts_);
            FightOrder.sortFightersUsingMoveAmongList(_fight,_import);
            cbts_ = _fight.getOrderedFighters();
        }
        if(cbts_.isEmpty()){
            return nextFighters_;
        }
        nextFighters_=FightOrder.sortFightersByWornBerry(_fight,cbts_,_import);
        cbts_=nextFighters_.getNextFighters();
        TeamPositionList currentUsers_;
        currentUsers_ = FightOrder.randomFigtherHavingToAct(_fight,cbts_,_import);
        if (!currentUsers_.isEmpty()) {
            nextFighters_.getNextFighters().clear();
            nextFighters_.getNextFighters().add(currentUsers_.first());
        } else {
            nextFighters_ = new NextUsers(cbts_, nextFighters_.getItemUsers());
        }
        return nextFighters_;
    }

    static void initRound(Fight _fight) {
        for(byte e: _fight.getTeams().getKeys()){
            Team equipe_=_fight.getTeams().getVal(e);
            equipe_.initRoundTeam();
        }
        for(TeamPosition e: FightOrder.frontFighters(_fight)){
            Fighter creature_=_fight.getFighter(e);
            creature_.initRoundFrontFighter();
        }
        _fight.getNbRounds().increment();
    }

    static void beginRound(Fight _fight, Difficulty _diff, DataBase _import) {
        TeamPositionList cbts_=new TeamPositionList();
        _fight.setKeepRound(true);
        _fight.getEffects().clear();
        if(_fight.getBeginRound()){
            setAllyChoices(_fight, _import);
            initRound(_fight);
        }else{
            TeamPosition currentUser_ = _fight.getCurrentUser();
            Fighter creature_=_fight.getFighter(currentUser_);
            if(!NumberUtil.eq(creature_.getSubstistute(),Fighter.BACK)){
                roundThrowerSwitch(_fight, currentUser_, _diff, _import);
                if(!_fight.getAcceptableChoices()){
                    _fight.setKeepRound(false);
                    return;
                }
            }
            _fight.setState(FightState.ATTAQUES);
            //switch du dernier lanceur d'un joueur avec son remplacant
            cbts_=selectTargetHavingToPlayAfterThrower(_fight,currentUser_,_import);
            _fight.getOrderedFighters().clear();
            _fight.getOrderedFighters().addAllElts(cbts_);
            FightOrder.sortFightersUsingMoveAmongList(_fight,_import);
            cbts_ = _fight.getOrderedFighters();
        }
        calculateNextFighters(_fight, cbts_, _import);
        if (_fight.getRemainingFighters().isEmpty()) {
            _fight.setKeepRound(false);
        }
    }

    static void roundUser(Fight _fight, Difficulty _diff, DataBase _import) {
        _fight.getEffects().clear();
        Fighter creature_=_fight.getFighter(_fight.getCurrentUser());
        if(creature_.getAction() instanceof ActionMove){
            FightRound.roundThrowerMove(_fight, _fight.getCurrentUser(),_diff,_import);
            if(!_fight.getAcceptableChoices()){
                _fight.setKeepRound(false);
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                //proposition d'attaques et d'evos
                _fight.setKeepRound(false);
                return;
            }
            if (substituingAfterRoundThrowerMove(_fight, _fight.getCurrentUser(), _diff, _import)) {
                _fight.setKeepRound(false);
                return;
            }
        } else if(creature_.getAction() instanceof ActionSwitch) {
            FightRound.roundThrowerSwitch(_fight,_fight.getCurrentUser(),_diff,_import);
            if(!_fight.getAcceptableChoices()){
                _fight.setKeepRound(false);
                return;
            }
        } else {
            //if(creature_.getAction() instanceof ActionHeal)
            FightRound.roundThrowerHealing(_fight,_fight.getCurrentUser(),_diff,_import);
        }
        creature_.setActed(true);
        //tri des lanceurs
        TeamPositionList cbts_ = selectTargetHavingToPlayAfterThrower(_fight,_fight.getCurrentUser(),_import);
        _fight.getOrderedFighters().clear();
        _fight.getOrderedFighters().addAllElts(cbts_);
        FightOrder.sortFightersUsingMoveAmongList(_fight,_import);
        cbts_ = _fight.getOrderedFighters();
        calculateNextFighters(_fight, cbts_, _import);
        if (_fight.getRemainingFighters().isEmpty()) {
            _fight.setKeepRound(false);
        }
    }

    static void endRoundFight(Fight _fight,Difficulty _diff,Player _user, DataBase _import) {
        _fight.getEffects().clear();
        if (FightKo.endedFight(_fight,_diff) || _fight.getRemainingFighters().isEmpty()) {
            endRoundShowActions(_fight,_diff, _user, _import);
        }
    }

    static void roundThrowerMove(Fight _fight, TeamPosition _lanceur,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_lanceur);
        _fight.setLettingUserAttackWithStatus(true);
        _fight.setKeepStatus(true);
        String attaqueLanceur_=creature_.getFinalChosenMove();
        if (exitStatusBegin(_fight, _lanceur, _diff, _import, creature_)) {
            return;
        }
        affectNoUsesMove(creature_);
        if (exitCannotUseMove(_fight, _lanceur, _import, creature_)) {
            return;
        }
        FightInvoke.processInvokingMove(_fight,_lanceur,_diff,_import);
        if (!_fight.isSuccessfulInvokation()) {
            endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
            return;
        }
        spendPowerPoint(_diff, _import, creature_, attaqueLanceur_);
        attaqueLanceur_=creature_.getFinalChosenMove();
        if (FightOrder.nbBackPartners(_fight, _lanceur) == 0 && StringUtil.contains(_import.getMovesFullHeal(), attaqueLanceur_)) {
            endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
            return;
        }
        loopEffects(_fight, _lanceur, _diff, _import, creature_);
    }

    private static void loopEffects(Fight _fight, TeamPosition _lanceur, Difficulty _diff, DataBase _import, Fighter _creature) {
        String attaqueLanceur_=_creature.getFinalChosenMove();
        MoveData fAttFinal_= _import.getMove(attaqueLanceur_);
        copyMovesType(_fight, _lanceur, _import, _creature, fAttFinal_);
        _fight.addMoveTypesMessage(_lanceur, FightMoves.moveTypes(_fight, _lanceur, attaqueLanceur_, _import), attaqueLanceur_, _import);
        int nbEffets_=fAttFinal_.nbEffets();
        boolean preliminaire_=true;
        boolean primaire_=false;

        TeamPosition lanceur_ = possibleChangeUser(_fight, _lanceur, _import, fAttFinal_);
        String firstMove_ = firstMove(_fight, _creature);
        Ints previousEffects_;
        previousEffects_ = new Ints();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttFinal_.getEffet(i);
            if(preliminaire_) {
                if(effet_.getTargetChoice() == fAttFinal_.getTargetChoice()){
                    primaire_=true;
                    preliminaire_=false;
                }
            } else if(primaire_) {
                primaire_=false;
            }
            if (!primaire_ && !fAttFinal_.getSecEffectsByItem().isEmpty() && (!FightItems.canUseItsObject(_fight, _lanceur, _import) || !fAttFinal_.getSecEffectsByItem().contains(_creature.getItem()) || !fAttFinal_.getSecEffectsByItem().getVal(_creature.getItem()).contains(i))) {
                continue;
            }
            processEffectTargets(_fight, _lanceur, lanceur_,new FightEffectState(primaire_,i,previousEffects_), _diff, _import);
            previousEffects_.add(i);
            if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff)) {
                return;
            }
        }
        endRoundThrower(_fight, _lanceur, attaqueLanceur_, _import.getMove(firstMove_).getRechargeRound(), _import);
    }

    private static TeamPosition possibleChangeUser(Fight _fight, TeamPosition _lanceur, DataBase _import, MoveData _fAttFinal) {
        TeamPosition lanceur_= _lanceur;
        _fight.setChangeThrower(false);
        if (_fAttFinal.canBoostAllies()) {
            StatusMoveData fAttNonOff_=(StatusMoveData) _fAttFinal;
            if (fAttNonOff_.getThievableMove()) {
                TeamPositionList takers_ = takers(_fight,lanceur_, _import);
                if (!takers_.isEmpty()) {
                    _fight.getOrderedFighters().clear();
                    _fight.getOrderedFighters().addAllElts(takers_);
                    FightOrder.sortFightersUsingMoveAmongList(_fight, _import);
                    takers_ = _fight.getOrderedFighters();
                    lanceur_ = takers_.first();
                    _fight.addChangingWiewPointUserMessage(lanceur_, _import);
                    _fight.setChangeThrower(true);
                }
            }
        }
        return lanceur_;
    }

    private static void copyMovesType(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creature, MoveData _fAttFinal) {
        AbilityData ab_ = _creature.ficheCapaciteActuelle(_import);
        if (ab_ != null && ab_.isCopyMovesTypes()) {
            _creature.affecterTypes(_fAttFinal.getTypes());
            _fight.addChangedTypesMessage(_lanceur, _fAttFinal.getTypes(), _import);
        }
    }

    private static void spendPowerPoint(Difficulty _diff, DataBase _import, Fighter _creature, String _attaqueLanceur) {
        if (_creature.spendPowerPoint(_attaqueLanceur, _import)) {
            _creature.usePowerPointsByMove(_diff, _attaqueLanceur,(short) 1);
        }
    }

    private static void affectNoUsesMove(Fighter _creature) {
        if(!StringUtil.quickEq(_creature.getLastUsedMove(), _creature.getFinalChosenMove())){
            _creature.affectNoUsesMove();
        }
    }

    private static boolean exitCannotUseMove(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creature) {
        String attaqueLanceur_=_creature.getFinalChosenMove();
        if(!FightSuccess.canUseDirectlyMove(_fight, _lanceur, _import)){
            //increment prepa_tour
            _fight.addPrepaRoundMessage(_lanceur, attaqueLanceur_, _import);
            MoveData fAtt_= _import.getMove(attaqueLanceur_);
            _creature.incrementRoundBeforeUsingMove();
            //disparait eventuel
            _creature.setDisappeared(fAtt_.getDisappearBeforeUse());
            if (_creature.isDisappeared()) {
                _fight.addDisappearedMessage(_lanceur, _import);
            }
            endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
            return true;
        }
        if(_creature.isNeedingToRecharge()){
            _creature.setNeedingToRecharge(false);
            if(!FightSuccess.canSkipRecharge(_fight, _lanceur, _import)){
                _fight.addRechargeRoundMessage(_lanceur, _import);
                endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
                return true;
            }
        }
        return false;
    }

    private static boolean exitStatusBegin(Fight _fight, TeamPosition _lanceur, Difficulty _diff, DataBase _import, Fighter _creature) {
        String attaqueLanceur_=_creature.getFinalChosenMove();
        for(String c: _creature.getStatusSet()){
            if(NumberUtil.eq(_creature.getStatusNbRoundShort(c), 0)){
                continue;
            }
            statusBeginRoundAttack(_fight, _lanceur,c, _diff, _import);
            if (_fight.getSimulation() && !_fight.getLettingUserAttackWithStatus()) {
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.CANNOT_USE);
                return true;
            }
            if(!_fight.isKeepStatus()){
                _fight.addStatusBeginRoundMessage(_lanceur, c, _import);
                endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
                return true;
            }
        }
        return false;
    }

    static void processEffectTargets(Fight _fight, TeamPosition _initialThrower, TeamPosition _finalThrower,
                                     FightEffectState _status,Difficulty _diff,DataBase _import) {
        Rate tauxMultPv_ = FightStatistic.multiplyByLoveBetweenFighters(_fight,_import);
        _status.setTauxMultPv(tauxMultPv_);
        Fighter creature_=_fight.getFighter(_initialThrower);
        _status.setCreature(creature_);
        String attaqueLanceur_=creature_.getFinalChosenMove();
        _status.setAttaqueLanceur(attaqueLanceur_);
        MoveData fAttFinal_=_import.getMove(attaqueLanceur_);
        _status.setfAttFinal(fAttFinal_);
        Effect effet_=fAttFinal_.getEffet(_status.getIndex());
        _status.setEffet(effet_);
        for(TeamPosition e:FightOrder.targetsEffect(_fight,_finalThrower,effet_,_diff,_import)){
            if (exitEffectTarget(_fight, _finalThrower,_status,_diff,_import,e)) {
                return;
            }
//            if (!_status.getPreviousEffect().isEmpty() && !_fight.getSuccessfulEffects().contains(new NbEffectFighterCoords((int) _status.getPreviousEffect().getMaximum(-1), e))) {
//                continue;
//            }
//            if(!NumberUtil.eq(e.getTeam(),_finalThrower.getTeam()) && !_fight.isChangeThrower()){
//                if (exitStatusRelat(_fight, _finalThrower, _diff, _import, _status.getCreature(), e)) {
//                    return;
//                }
//                if(!_fight.getLettingUserAttackWithStatus()){
//                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),e),BoolVal.FALSE);
//                    continue;
//                }
//                if (_status.isFirstEffect() && _import.getMove(firstMove(_fight, _status.getCreature())) instanceof DamagingMoveData) {
//                    pressure(_fight, _finalThrower, e, attaqueLanceur_, _diff, _import);
//                }
//            }
//            _fight.setSending(false);
//            RandomBoolResults resultatsReussite_=FightSuccess.successfulMove(_fight,_finalThrower,e,attaqueLanceur_, _status.getIndex(),true,_import);
//            if(!resultatsReussite_.isSuccessful()){
//                _fight.addFailMoveMessage(attaqueLanceur_, e, _import);
//                //precision
//                _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),e),BoolVal.FALSE);
//                if(resultatsReussite_.isEffectIfFail() && !_fight.isChangeThrower()){
//                    effectWhileFail(_fight,_finalThrower, e, attaqueLanceur_, _diff, _import);
//                    if (!_fight.getAcceptableChoices()) {
//                        return;
//                    }
//                    if(FightKo.endedFight(_fight,_diff)){
//                        return;
//                    }
//                    Rate efficiency_ = FightSuccess.rateEffAgainstTargetMove(_fight,_finalThrower, e, _import);
//                    if (efficiency_.greaterThanOne()) {
//                        FightItems.enableBerryHpWhileSuperEffectiveMove(_fight, e, _status.getIndex(), _import);
//                    }
//                }
//                continue;
//            }
//            if(_status.getEffet() instanceof EffectDamage){
//                boolean continueLoop_ = continueLoopTarget(_fight, _finalThrower, _import, _status.getTauxMultPv(), e);
//                if (continueLoop_) {
//                    _fight.addSuccessfulMoveButNoDamageMessage(attaqueLanceur_, e, _import);
//                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),e),ComparatorBoolean.of(fAttFinal_.getSecEffectIfNoDamage()));
//                    if (fAttFinal_.getSecEffectIfNoDamage()) {
//                        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),_finalThrower),BoolVal.TRUE);
//                    }
//                    continue;
//                }
//            }
//            if(_status.isFirstEffect() && !_fight.isChangeThrower()){
//                processAccurracy(_fight,attaqueLanceur_, _finalThrower, e, _import);
//                if (!_fight.getAcceptableChoices()) {
//                    return;
//                }
//                if (!_fight.isSuccessfulUse()) {
//                    _fight.addFailMoveMessage(attaqueLanceur_, e, _import);
//                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),e),BoolVal.FALSE);
//                    continue;
//                }
//            }
//            _fight.addSuccessfulMoveMessage(attaqueLanceur_, e, _import);
//            _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),e),BoolVal.TRUE);
//            FightEffects.processEffectTarget(_fight,attaqueLanceur_, _status.getIndex(),
//                    _finalThrower, e, _diff, _import);
//            _status.achieveTar();
//            if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff)) {
//                return;
//            }
            //            if(fAttFinal_.getSwitchType() == SwitchType.CIBLE){
//                envoiDeForce(cible_,_diff,_import);
//            }
            //attaque reussie
        }
        if (!_status.isAchieveTarget()) {
            _fight.addNoAchieveTargetMessage(attaqueLanceur_, _finalThrower, _import);
        } else {
            int nbEffets_=fAttFinal_.nbEffets();
            if (nbEffets_ > _status.getIndex() + 1) {
                Effect eff_ = fAttFinal_.getEffet(_status.getIndex() + 1);
                if (eff_.getTargetChoice() == TargetChoice.LANCEUR) {
                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _finalThrower),BoolVal.TRUE);
                }
            }
        }
    }
    private static boolean exitEffectTarget(Fight _fight, TeamPosition _finalThrower,
                                            FightEffectState _status, Difficulty _diff, DataBase _import, TeamPosition _e) {
        if (!_status.getPreviousEffect().isEmpty() && !_fight.getSuccessfulEffects().contains(new NbEffectFighterCoords((int) _status.getPreviousEffect().getMaximum(-1), _e))) {
            return false;
        }
        if(!NumberUtil.eq(_e.getTeam(),_finalThrower.getTeam()) && !_fight.isChangeThrower()){
            if (exitStatusRelat(_fight, _finalThrower, _diff, _import, _status.getCreature(), _e)) {
                return true;
            }
            if(!_fight.getLettingUserAttackWithStatus()){
                _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(),_e),BoolVal.FALSE);
                return false;
            }
            if (_status.isFirstEffect() && _import.getMove(firstMove(_fight, _status.getCreature())) instanceof DamagingMoveData) {
                pressure(_fight, _finalThrower, _e, _status.getAttaqueLanceur(), _diff, _import);
            }
        }
        _fight.setSending(false);
        RandomBoolResults resultatsReussite_=FightSuccess.successfulMove(_fight,_finalThrower,_e,_status.getAttaqueLanceur(), _status.getIndex(),true,_import);
        if(!resultatsReussite_.isSuccessful()){
            return exitEffectTargetBecauseKoMove(_fight, _finalThrower, _status, _diff, _import, _e, resultatsReussite_);
        }
        if (_status.getEffet() instanceof EffectDamage && continueLoopTarget(_fight, _finalThrower, _import, _status.getTauxMultPv(), _e)) {
            noDamage(_fight, _finalThrower, _status, _import, _e);
            return false;
        }
        return exitAccuracyOrDone(_fight, _finalThrower, _status, _diff, _import, _e);
    }

    private static void noDamage(Fight _fight, TeamPosition _finalThrower, FightEffectState _status, DataBase _import, TeamPosition _e) {
        _fight.addSuccessfulMoveButNoDamageMessage(_status.getAttaqueLanceur(), _e, _import);
        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _e), ComparatorBoolean.of(_status.getfAttFinal().getSecEffectIfNoDamage()));
        if (_status.getfAttFinal().getSecEffectIfNoDamage()) {
            _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _finalThrower), BoolVal.TRUE);
        }
    }

    private static boolean exitAccuracyOrDone(Fight _fight, TeamPosition _finalThrower, FightEffectState _status, Difficulty _diff, DataBase _import, TeamPosition _e) {
        if(_status.isFirstEffect() && !_fight.isChangeThrower()){
            processAccurracy(_fight, _status.getAttaqueLanceur(), _finalThrower, _e, _import);
            if (!_fight.getAcceptableChoices()) {
                return true;
            }
            if (!_fight.isSuccessfulUse()) {
                _fight.addFailMoveMessage(_status.getAttaqueLanceur(), _e, _import);
                _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _e),BoolVal.FALSE);
                return false;
            }
        }
        _fight.addSuccessfulMoveMessage(_status.getAttaqueLanceur(), _e, _import);
        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _e),BoolVal.TRUE);
        FightEffects.processEffectTarget(_fight, _status.getAttaqueLanceur(), _status.getIndex(),
                _finalThrower, _e, _diff, _import);
        _status.achieveTar();
        return !_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff);
    }

    private static boolean exitEffectTargetBecauseKoMove(Fight _fight, TeamPosition _finalThrower, FightEffectState _status, Difficulty _diff, DataBase _import, TeamPosition _e, RandomBoolResults _resultatsReussite) {
        _fight.addFailMoveMessage(_status.getAttaqueLanceur(), _e, _import);
        //precision
        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_status.getIndex(), _e),BoolVal.FALSE);
        if(_resultatsReussite.isEffectIfFail() && !_fight.isChangeThrower()){
            effectWhileFail(_fight, _finalThrower, _e, _status.getAttaqueLanceur(), _diff, _import);
            if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff)) {
                return true;
            }
            Rate efficiency_ = FightSuccess.rateEffAgainstTargetMove(_fight, _finalThrower, _e, _import);
            if (efficiency_.greaterThanOne()) {
                FightItems.enableBerryHpWhileSuperEffectiveMove(_fight, _e, _status.getIndex(), _import);
            }
        }
        return false;
    }

    private static boolean continueLoopTarget(Fight _fight, TeamPosition _finalThrower, DataBase _import, Rate _tauxMultPv, TeamPosition _e) {
        boolean continueLoop_ = false;
        if (NumberUtil.eq(_e.getTeam(), _finalThrower.getTeam())) {
            //ally
            healPartner(_fight, _finalThrower, _e, _tauxMultPv, _import);
            if (_fight.isEnabledHealingPartner()) {
                continueLoop_ = true;
            }
        }
        if (!continueLoop_) {
            Rate efficiency_ = FightSuccess.rateEffAgainstTargetMove(_fight, _finalThrower, _e, _import);
            if(efficiency_.isZero()){
                continueLoop_ = true;
            }
        }
        return continueLoop_;
    }

    private static boolean exitStatusRelat(Fight _fight, TeamPosition _finalThrower, Difficulty _diff, DataBase _import, Fighter _creature, TeamPosition _e) {
        _fight.setLettingUserAttackWithStatus(true);
        _fight.setKeepStatus(true);
        for(MoveTeamPosition c: _creature.getStatusRelatSet()){
            if (!TeamPosition.eq(c.getTeamPosition(), _e) || NumberUtil.eq(_creature.getStatusRelatNbRoundShort(c), 0)) {
                continue;
            }
            statusBeginRoundAttack(_fight, _finalThrower,c.getMove(), _diff, _import);
            _fight.addStatusBeginRoundRelMessage(_finalThrower, c.getMove(), _e, _import);
            if (!_fight.getLettingUserAttackWithStatus() && _fight.getSimulation()) {
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.CANNOT_USE);
                return true;
            }
            if(!_fight.isKeepStatus()){
                return FightKo.endedFight(_fight, _diff);
            }
        }
        return false;
    }

    private static String firstMove(Fight _fight, Fighter _creature) {
        String firstMove_;
        if (_fight.isInvokedMove()) {
            firstMove_ = _creature.getAlreadyInvokedMovesRound().first();
        } else {
            firstMove_ = _creature.getFinalChosenMove();
        }
        return firstMove_;
    }

    static void statusBeginRoundAttack(Fight _fight,TeamPosition _combattant,String _nomStatut,Difficulty _diff,DataBase _import) {
        Status statut_=_import.getStatus().getVal(_nomStatut);
        if(!(statut_ instanceof StatusBeginRound)){
            return;
        }
        effectBeginRoundAttack(_fight,_combattant,_nomStatut,_diff,_import);
        if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff) || !_fight.getLettingUserAttackWithStatus()) {
            _fight.setKeepStatus(false);
        }
    }

    static void effectBeginRoundAttack(Fight _fight,TeamPosition _combattant,String _nomStatut,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        StatusBeginRound status_ = (StatusBeginRound) _import.getStatus(_nomStatut);
        MonteCarloBoolean lawUseMove_ = status_.getLawForUsingAMove();
        MonteCarloBoolean lawUseMoveIfFoe_ = status_.getLawForUsingAMoveIfFoe();
        LgInt maxRd_ = _import.getMaxRd();
        boolean tirageGuerison_ = tirageGuerison(_fight, _combattant, _nomStatut, _import, creature_, status_);
        boolean attaquer_=true;
        if(!tirageGuerison_&&!lawUseMove_.events().isEmpty()){
            String moveName_ = creature_.getFinalChosenMove();
            MoveData move_ = _import.getMove(moveName_);
            if (StringUtil.contains(move_.getRequiredStatus(), _nomStatut) || StringUtil.contains(move_.getDeletedStatus(), _nomStatut) || tirageGuerison(_fight, _combattant, _import, lawUseMove_, maxRd_)) {
                tirageGuerison_ = true;
            } else {
                attaquer_ = false;
            }
            //simulation == false ==> attaquer_ == tirageGuerison_
        }
        boolean attaquerAdv_ = attaquerAdv(_fight, _combattant, _import, lawUseMoveIfFoe_);
        MonteCarloBoolean lawFullHeal_ = status_.getLawForFullHealIfMove();
        if (creature_.isSingleStatus(_nomStatut)) {
            disableRandomlyStatus(_fight,lawFullHeal_,_combattant,_nomStatut, !tirageGuerison_, _import);
        }
        if(!attaquer_ && status_ instanceof StatusBeginRoundAutoDamage){
            StatusBeginRoundAutoDamage autoDamage_ = (StatusBeginRoundAutoDamage) status_;
            autoDamage(_fight,_combattant,autoDamage_.getPower(),autoDamage_.getAttack(),autoDamage_.getDefense(),_diff,_import);
            if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff)) {
                return;
            }
        }
        _fight.setLettingUserAttackWithStatus(attaquer_&&attaquerAdv_);
    }
    private static boolean tirageGuerison(Fight _fight,TeamPosition _combattant,DataBase _import,MonteCarloBoolean _lawUseMove, LgInt _maxRd) {
        if (_fight.getSimulation()) {
            return !NumberUtil.eq(_combattant.getTeam(), Fight.CST_PLAYER);
        }
        return FightSuccess.tr(_lawUseMove.editNumber(_maxRd, _import.getGenerator()));
    }

    private static boolean attaquerAdv(Fight _fight, TeamPosition _combattant, DataBase _import, MonteCarloBoolean _lawUseMoveIfFoe) {
        LgInt maxRd_ = _import.getMaxRd();
        boolean attaquerAdv_=true;
        if(!_lawUseMoveIfFoe.events().isEmpty()){
            if(_fight.getSimulation()){
                if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)){
                    attaquerAdv_=false;
                }
            } else {
                attaquerAdv_ = FightSuccess.tr(_lawUseMoveIfFoe.editNumber(maxRd_, _import.getGenerator()));
            }
        }
        return attaquerAdv_;
    }

    private static boolean tirageGuerison(Fight _fight, TeamPosition _combattant, String _nomStatut, DataBase _import, Fighter _creature, StatusBeginRound _status) {
        MonteCarloNumber lawNbRound_ = _status.getLawForUsingAMoveNbRound();
        if (lawNbRound_.events().isEmpty() || _status.getIncrementingEndRound()) {
            //immu "paralysie totale"
            return immuStatusBeginRound(_nomStatut, _import, _creature);
        }
        if (_creature.isSingleStatus(_nomStatut)) {
            short nbTour_= _creature.getStatusNbRoundShort(_nomStatut);
            MonteCarloBoolean loiModif_= lawNbRound_.knowingGreater(new Rate(nbTour_));
            String moveName_ = _creature.getFinalChosenMove();
            MoveData move_ = _import.getMove(moveName_);
            boolean fini_ = finiStatut(_fight, _combattant, _nomStatut, _import, loiModif_, move_);
            boolean tirageGuerison_ = false;
            if(fini_){
                _creature.supprimerStatut(_nomStatut);
                _fight.addDisabledStatusMessage(_nomStatut, _combattant, _import);
                tirageGuerison_=true;
            }else{
                //incrementer le nombre de tour
                _creature.incrementRoundsStatus(_nomStatut);
                _fight.addIncrStatusMessage(_nomStatut, _combattant, _import);
            }
            //immu "paralysie totale"
            if (immuStatusBeginRound(_nomStatut, _import, _creature)) {
                tirageGuerison_ = true;
            }
            return tirageGuerison_;
        }
        for (MoveTeamPosition s: _creature.getStatusRelatSet()) {
            if (!StringUtil.quickEq(_nomStatut, s.getMove())) {
                continue;
            }
            short nbTour_= _creature.getStatusRelatNbRoundShort(s);
            MonteCarloBoolean loiModif_= lawNbRound_.knowingGreater(new Rate(nbTour_));
            String moveName_ = _creature.getFinalChosenMove();
            MoveData move_ = _import.getMove(moveName_);
            boolean fini_ = finiStatut(_fight, _combattant, _nomStatut, _import, loiModif_, move_);
            if(fini_){
                _creature.supprimerPseudoStatutCombattant(s.getTeamPosition(), _nomStatut);
                _fight.addDisabledStatusRelMessage(_nomStatut, _combattant, s.getTeamPosition(), nbTour_, _import);
            }else{
                //incrementer le nombre de tour
//                        creature_.getStatusRelat().put(s, (short) (nbTour_ + 1));
                _creature.incrementPseudoStatutCombattant(s.getTeamPosition(), s.getMove());
                _fight.addIncrStatusRelMessage(_nomStatut, _combattant, s.getTeamPosition(), _import);
            }
        }
        //immu "paralysie totale"
        return immuStatusBeginRound(_nomStatut, _import, _creature);

    }

    private static boolean finiStatut(Fight _fight, TeamPosition _combattant, String _nomStatut, DataBase _import, MonteCarloBoolean _loiModif, MoveData _move) {
        LgInt maxRd_ = _import.getMaxRd();
        boolean fini_;
        if (StringUtil.contains(_move.getDeletedStatus(), _nomStatut)) {
            fini_= true;
        } else {
            if(_fight.getSimulation()){
                fini_= NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE);
            } else {
                fini_=!FightSuccess.tr(_loiModif.editNumber(maxRd_, _import.getGenerator()));
            }
        }
        return fini_;
    }

    private static boolean immuStatusBeginRound(String _nomStatut, DataBase _import, Fighter _creature) {
        AbilityData fCapac_= _creature.ficheCapaciteActuelle(_import);
        return fCapac_ != null && StringUtil.contains(fCapac_.getImmuStatusBeginRound(), _nomStatut);
    }

    static void disableRandomlyStatus(Fight _fight,MonteCarloBoolean _lawFullHeal, TeamPosition _combattant, String _nomStatut, boolean _return, DataBase _import) {
        if (_lawFullHeal.events().isEmpty()) {
            return;
        }
        if (_return) {
            return;
        }
        LgInt maxRd_ = _import.getMaxRd();
        Fighter creature_=_fight.getFighter(_combattant);
        if(_fight.getSimulation()){
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE)){
                creature_.supprimerStatut(_nomStatut);
                _fight.addDisabledStatusMessage(_nomStatut, _combattant, _import);
            }
        }else if(FightSuccess.tr(_lawFullHeal.editNumber(maxRd_,_import.getGenerator()))){
            creature_.supprimerStatut(_nomStatut);
            _fight.addDisabledStatusMessage(_nomStatut, _combattant, _import);
        }
    }

    static void autoDamage(Fight _fight,TeamPosition _combattant,Rate _puissance,Statistic _statAtt,Statistic _statDef,Difficulty _diff,DataBase _import){
        Fighter creature_ = _fight.getFighter(_combattant);
        Rate att_=creature_.statistiqueGlobaleEvIv(_statAtt);
        byte maxBoost_=(byte) _import.getMaxBoost();
        byte cran_=creature_.getStatisBoost().getVal(_statAtt);
        //boolean peutUtiliserObjet_= FightItems.canUseItsObject(_fight,_combattant,_import);
        Rate boost_ = FightStatistic.rateBoost((byte) NumberUtil.min(cran_, maxBoost_), _import);
//        if(creature_.capaciteActive()){
//            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
//            if (fCapac_.getMultStat().contains(_statAtt)) {
//                String numericString_ = fCapac_.getMultStat().getVal(_statAtt);
//                att_.multiplyBy(FightStatistic.multiplyStringFighter(_fight,numericString_, _combattant, _import));
//            }
//        }
//        if(peutUtiliserObjet_){
//            Item objet_=creature_.ficheObjet(_import);
//            if(objet_ instanceof ItemForBattle){
//                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
//                if (objetAttachable_.getMultStat().contains(_statAtt)) {
//                    String numericString_ = objetAttachable_.getMultStat().getVal(_statAtt);
//                    att_.multiplyBy(FightStatistic.multiplyStringFighter(_fight,numericString_, _combattant, _import));
//                }
//            }
//        }
        att_.multiplyBy(boost_);
        Rate def_=creature_.statistiqueGlobaleEvIv(_statDef);
        cran_=creature_.getStatisBoost().getVal(_statDef);
        boost_ = FightStatistic.rateBoost((byte) NumberUtil.min(cran_, maxBoost_), _import);
        def_.multiplyBy(boost_);
        StringMap<String> varLocs_ = new StringMap<String>();
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTACK), att_.toNumberString());
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEFENSE), def_.toNumberString());
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_NIVEAU), Long.toString(creature_.getLevel()));
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.POWER), _puissance.toNumberString());
        String damageFormula_ = _import.getDamageFormula();
        Rate degats_ = _import.evaluatePositiveExp(damageFormula_, varLocs_, _puissance);
        if(Rate.greaterEq(degats_,creature_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_combattant,_diff,_import);
            _fight.addAnimationKoFighter(_combattant);
            if (NumberUtil.eq(_combattant.getTeam(), Fight.CST_PLAYER) && _fight.getSimulation()) {
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            creature_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_combattant, _import);
        }
    }

    static TeamPositionList takers(Fight _fight, TeamPosition _fighter, DataBase _data) {
        TeamPositionList takers_ = new TeamPositionList();
        for (TeamPosition t: FightOrder.frontFighters(_fight)) {
            if (NumberUtil.eq(t.getTeam(), _fighter.getTeam())) {
                continue;
            }
            byte pos_ = _fight.getFighter(_fighter).getGroundPlace();
            Fighter fighter_ = _fight.getFighter(t);
            String attaqueCible_=fighter_.getFinalChosenMove();
            if (!attaqueCible_.isEmpty() && fighter_.getChosenTargets().containsObj(new TargetCoords(_fighter.getTeam(), pos_)) && FightOrder.getPointViewChangementType(attaqueCible_, _data) == PointViewChangementType.THIEF_BONUSES) {
                takers_.add(t);
            }
        }
        return takers_;
    }

    static void pressure(Fight _fight,
            TeamPosition _thrower, TeamPosition _target,
            String _move,
            Difficulty _diff, DataBase _import) {
        Fighter creature_=_fight.getFighter(_thrower);
        if(NumberUtil.eq(_thrower.getTeam(),_target.getTeam())){
            return;
        }
        short ppSuppl_=0;
        AbilityData fCapacCible_ = FightAbilities.ignoredTargetAbility(_fight, _thrower, _target, _import);
        if(fCapacCible_ != null){
            ppSuppl_+=fCapacCible_.getNbUsedPp();
        }
        if(StringUtil.quickEq(creature_.getFirstChosenMove(),_move)){
            creature_.usePowerPointsByMove(_diff,_move,ppSuppl_);
        }
    }

    static void effectWhileFail(Fight _fight,
            TeamPosition _thrower,
            TeamPosition _target,
            String _move,
            Difficulty _diff,
            DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_target);
        if (!NumberUtil.eq(_thrower.getTeam(), _target.getTeam()) && exitEffectWhileFailFoe(_fight, _thrower, _target, _move, _diff, _import, creatureCible_)) {
            return;
        }
        AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
        if(fCapac_ == null){
            return;
        }
        for(Statistic c:fCapac_.getBoostStatRankProtected().getKeys()){
            byte boost_=fCapac_.getBoostStatRankProtected().getVal(c);
            byte delta_ = FightEffects.deltaBoostStatistic(_fight,_target,c,boost_,_import);
            creatureCible_.variationBoostStatistique(c,delta_);
            _fight.addStatisticMessage(_target, c, delta_, _import);
        }
        StringList typeAttaque_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        WeatherTypes healHpByTypeIfWeather_ = fCapac_.getHealHpByTypeIfWeather();
        effectWhileFailHealByWeather(_fight, _target, _import, creatureCible_, typeAttaque_, healHpByTypeIfWeather_);
    }

    private static void effectWhileFailHealByWeather(Fight _fight, TeamPosition _target, DataBase _import, Fighter _creatureCible, StringList _typeAttaque, WeatherTypes _healHpByTypeIfWeather) {
        StringList activeWeathers_ = FightMoves.climatsActifs(_fight,_import);
        for(WeatherTypeRate k: _healHpByTypeIfWeather.entryList()){
            if (!StringUtil.contains(_typeAttaque, k.getStat().getType())) {
                continue;
            }
            effectWhileFailHealByWeatherType(_fight, _target, _import, _creatureCible, activeWeathers_, k);
        }
    }

    private static void effectWhileFailHealByWeatherType(Fight _fight, TeamPosition _target, DataBase _import, Fighter _creatureCible, StringList _activeWeathers, WeatherTypeRate _k) {
        for (String w: _activeWeathers) {
            if(!StringUtil.quickEq(_k.getStat().getWeather(),w)){
                continue;
            }
            Rate varPv_=new Rate(_k.getValue());
            varPv_.multiplyBy(_creatureCible.pvMax());
            _creatureCible.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import);
        }
        if (_activeWeathers.isEmpty()) {
            if(!StringUtil.quickEq(_k.getStat().getWeather(),DataBase.EMPTY_STRING)){
                return;
            }
            //ABSORB_VOLT, ABSORB_EAU
            Rate varPv_=new Rate(_k.getValue());
            varPv_.multiplyBy(_creatureCible.pvMax());
            _creatureCible.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import);
        }
    }

    private static boolean exitEffectWhileFailFoe(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, Difficulty _diff, DataBase _import, Fighter _creatureCible) {
        Fighter user_ = _fight.getFighter(_thrower);
        for (String m : _creatureCible.getEnabledCounteringMoves().getKeys()) {
            if (!_creatureCible.getEnabledCounteringMoves().getVal(m).isEnabled()) {
                continue;
            }
            MoveData fMove_ = _import.getMove(m);
            int nbEffects_ = fMove_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
                Effect eff_ = fMove_.getEffet(i);
                if (!(eff_ instanceof EffectCounterAttack) || skipCounter(_fight, _thrower, _target, _import, (EffectCounterAttack) eff_)) {
                    continue;
                }
                effectWhileFailFoeDamage(_fight, _thrower, _target, _move, _import, user_, ((EffectCounterAttack) eff_));
                if (exitEffect(_fight, _thrower, _diff, _import, user_)) {
                    return true;
                }
                effectWhileFailFoeStatis(_fight, _thrower, _target, _move, _import, user_, (EffectCounterAttack) eff_);
            }
        }
        return false;
    }

    private static boolean exitEffect(Fight _fight, TeamPosition _thrower, Difficulty _diff, DataBase _import, Fighter _user) {
        if (_user.getRemainingHp().isZero()) {
            FightKo.setKoMoveTeams(_fight, _thrower, _diff, _import);
            _fight.addAnimationKoFighter(_thrower);
            if (_fight.getSimulation()) {
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
                return true;
            }
        }
        return false;
    }

    private static boolean skipCounter(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import, EffectCounterAttack _effectLoc) {
        String fail_ = _effectLoc.getCounterFail();
        return !fail_.isEmpty() && _import.evaluateBoolean(fail_, valuesFail(_fight, _thrower, _target, _import), false);
    }

    private static StringMap<String> valuesFail(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        StringMap<String> values_ = new StringMap<String>();
        values_.putAllMap(FightValues.calculateValues(_fight, _target, _thrower, _import));
        values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight, _target, _thrower, _import));
        return values_;
    }

    private static void effectWhileFailFoeDamage(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, DataBase _import, Fighter _user, EffectCounterAttack _effectLoc) {
        StringMap<Rate> damage_;
        damage_ = FightSuccess.sufferingDamageTypes(_fight, _thrower, _target, _move, _import);
        for (String type_: damage_.getKeys()) {
            _user.variationLeftHp(Rate.multiply(damage_.getVal(type_).opposNb(), _user.pvMax()));
            _fight.addHpMessage(_thrower, _import);
        }
        if (FightSuccess.sufferingDirectMoves(_fight, _thrower, _target, _move, false, _import)) {
            _user.variationLeftHp(Rate.multiply(_effectLoc.getSufferingDamageDirectMove().opposNb(), _user.pvMax()));
            _fight.addHpMessage(_thrower, _import);
        }
        if (!_user.getRemainingHp().isZeroOrGt()) {
            _user.getRemainingHp().affectZero();
        }
    }

    private static void effectWhileFailFoeStatis(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, DataBase _import, Fighter _user, EffectCounterAttack _effectLoc) {
        if (FightSuccess.droppedStatis(_fight, _thrower, _target, _move, false, _import)) {
            for (Statistic s: _effectLoc.getDroppedStatDirectMove().getKeys()) {
                byte varBase_ = _effectLoc.getDroppedStatDirectMove().getVal(s);
                if (!FightSuccess.successChangedStatistic(_fight, _target, _thrower,s, varBase_, _import)) {
                    continue;
                }
                byte delta_ = FightEffects.deltaBoostStatistic(_fight, _target,s,varBase_, _import);
                _user.variationBoostStatistique(s, delta_);
                _fight.addStatisticMessage(_thrower, s, delta_, _import);
            }
        }
    }

    static void healPartner(Fight _fight,
            TeamPosition _fighter,
            TeamPosition _partner,
            Rate _rateHealingGlobalMoves,
            DataBase _import) {
        _fight.setEnabledHealingPartner(false);
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        Fighter creatureLanceur_=_fight.getFighter(_fighter);
        for(MoveTeamPosition c:creatureLanceur_.getStatusRelatSet()){
            if (!TeamPosition.eq(c.getTeamPosition(), _partner) || NumberUtil.eq(creatureLanceur_.getStatusRelatNbRoundShort(c), 0)) {
                continue;
            }
            Rate rateHealtHpPartner_ = _import.getStatus().getVal(c.getMove()).rateHealtHpPartner();
            if (!rateHealtHpPartner_.isZero()) {
                Fighter creatureCible_ = equipeLanceur_.refPartMembres(_partner.getPosition());
                Rate pv_ = creatureCible_.pvMax();
                pv_.multiplyBy(rateHealtHpPartner_);
                pv_.multiplyBy(_rateHealingGlobalMoves);
                creatureCible_.variationLeftHp(pv_);
                _fight.addHpMessage(_partner, _import);
                _fight.setEnabledHealingPartner(true);
            }
        }
    }

    static void processAccurracy(Fight _fight,
            String _move,
            TeamPosition _thrower,
            TeamPosition _target, DataBase _import) {
        boolean precisionMaxCible_=false;
        String attaquePrecision_ = DataBase.EMPTY_STRING;
        Fighter creature_=_fight.getFighter(_thrower);
        for(MoveTeamPosition c:creature_.getIncrUserAccuracy().getKeys()){
            if (TeamPosition.eq(c.getTeamPosition(), _target) && creature_.getIncrUserAccuracy().getVal(c) == BoolVal.TRUE) {
                precisionMaxCible_ = true;
                attaquePrecision_ = c.getMove();
                break;
            }
        }
        boolean sansEchec_ = sansEchec(_fight, _thrower, _import);
        Rate precision_= FightSuccess.accuracy(_fight,_thrower,_target,_move,_import);
        boolean precisionEstPartielle_=Rate.strLower(precision_, DataBase.determinatedRate());
        boolean randomReturn_ = false;
        _fight.setSuccessfulUse(true);
        if (!precisionMaxCible_&&!sansEchec_) {
            MonteCarloBoolean law_ = MonteCarloUtil.booleanLaw(precision_);
            boolean success_;
            if (FightSuccess.isBadSimulation(_fight, law_)) {
                if(NumberUtil.eq(_target.getTeam(),Fight.CST_FOE)){
                    return;
                }
                success_ = true;
            } else {
                success_ = FightSuccess.random(_import, law_);
            }
            if (!success_) {
                _fight.setSuccessfulUse(false);
                randomReturn_ = true;
            }
        }
        if (randomReturn_) {
            return;
        }
        useBoostForAccuracy(_fight, _thrower, _target, attaquePrecision_, _import, result(precisionMaxCible_, sansEchec_, precisionEstPartielle_));
    }

    private static boolean sansEchec(Fight _fight, TeamPosition _thrower, DataBase _import) {
        Berry baie_ = FightItems.useItsBerry(_fight, _thrower, _import);
        return baie_ != null && baie_.getWithoutFail();
    }

    static void useBoostForAccuracy(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _accuracyMove, DataBase _import, AccurayResult _result) {
        Fighter creature_= _fight.getFighter(_thrower);
        if(_result == AccurayResult.USED_MOVE){
            creature_.getIncrUserAccuracy().put(new MoveTeamPosition(_accuracyMove, _target),BoolVal.FALSE);
            _fight.addDisabledMoveRelMessage(_target, _accuracyMove, _thrower, _import);
        }else if(_result == AccurayResult.USED_ITEM){
            creature_.useObject();
        }
        //other cases: neither berry nor move that boost once accuracy of a move
        //so nothing is done
    }

    static AccurayResult result(boolean _maxAccuracy, boolean _withoutFailObject, boolean _partialAccuracy) {
        AccurayResult result_ = AccurayResult.NOTHING;
        if(_partialAccuracy){
            if(_maxAccuracy){
                result_ = AccurayResult.USED_MOVE;
            }else if(_withoutFailObject){
                result_ = AccurayResult.USED_ITEM;
            }
        }
        return result_;
    }

    static void endRoundThrower(Fight _fight,TeamPosition _lanceur, String _throwerMove,
            boolean _reloadMove,
            DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creature_=_fight.getFighter(_lanceur);
        _fight.getDamageByCurrentUser().clear();
        if(creature_.isSuccessfulMove()){
            equipe_.addSuccessfulMoveRound(_throwerMove);
            if(equipe_.getNbUsesMoves().contains(_throwerMove)){
                int nb_ = equipe_.getNbUsesMoves().getVal(_throwerMove);
                nb_++;
                equipe_.getNbUsesMoves().put(_throwerMove,nb_);
                _fight.addIncrTeamUsesMoveMessage(_lanceur.getTeam(), _throwerMove, _import);
            }
            if(equipe_.getNbUsesMovesRound().contains(_throwerMove)){
                int nb_ = equipe_.getNbUsesMovesRound().getVal(_throwerMove);
                nb_++;
                equipe_.getNbUsesMovesRound().put(_throwerMove,nb_);
                _fight.addIncrTeamUsesMoveMessage(_lanceur.getTeam(), _throwerMove, _import);
            }
        }
        if (creature_.estKo()) {
            return;
        }
        boostStatRankEndRound(_fight, _lanceur, _import, creature_);
        if(creature_.isSuccessfulMove()){
            if(creature_.getNbUsesMoves().contains(_throwerMove)){
                int nb_ = creature_.getNbUsesMoves().getVal(_throwerMove);
                nb_++;
                creature_.getNbUsesMoves().put(_throwerMove,nb_);
            }
            creature_.incrementConsecutiveUsesMove();
            //rechargment de l'attaque si le lanceur n'est pas KO et l'attaque est reussie et demande un rechargement
            if(!_fight.isInvokedMove()&&_reloadMove&&creature_.powerPointsMove(_throwerMove) > 0){
                creature_.setNeedingToRecharge(true);
            }
        }
        if (FightItems.canUseItsBerry(_fight,_lanceur, _import)) {
            FightItems.enableBerryPp(_fight,_lanceur, creature_.getItem(), true, _import);
        }
    }

    private static void boostStatRankEndRound(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creature) {
        AbilityData fCapac_= _creature.ficheCapaciteActuelle(_import);
        if(fCapac_ != null){
            for(Statistic c:fCapac_.getBoostStatRankEndRound().getKeys()){
                byte boost_=fCapac_.getBoostStatRankEndRound().getVal(c);
                byte delta_ = FightEffects.deltaBoostStatistic(_fight, _lanceur,c,boost_, _import);
                _creature.variationBoostStatistique(c,delta_);
                _fight.addStatisticMessage(_lanceur, c, delta_, _import);
            }
        }
    }

    static TeamPositionList selectTargetHavingToPlayAfterThrower(Fight _fight,TeamPosition _lanceur,DataBase _import){
        Fighter creature_=_fight.getFighter(_lanceur);
        if(creature_.estArriere()){
            return new TeamPositionList();
        }
        if(!creature_.isSuccessfulMove()){
            return new TeamPositionList();
        }
        if(creature_.getChosenTargets().isEmpty()){
            return new TeamPositionList();
        }
        TargetCoords targetCoords_ = creature_.getChosenTargets().first();
        Team equipeCbt_=_fight.getTeams().getVal((byte) targetCoords_.getTeam());
        Bytes ciblesEquipe_=equipeCbt_.fightersAtCurrentPlace(targetCoords_);
        TeamPositionList fighters_ = new TeamPositionList();
        fighters_.addAllElts(FightOrder.fightersHavingToAct(_fight, false, _import));
        fighters_.addAllElts(FightOrder.fightersHavingToAct(_fight, true, _import));
        fighters_ = FightOrder.fightersUsingMove(_fight, fighters_);
        TeamPositionList cibles_=new TeamPositionList();
        for(byte c:ciblesEquipe_){
            TeamPosition teamPos_ = new TeamPosition((byte) targetCoords_.getTeam(),c);
            if (!fighters_.containsObj(teamPos_)) {
                continue;
            }
            cibles_.add(teamPos_);
        }
        MoveData fAttaque_=_import.getMove(creature_.getFinalChosenMove());
        int nbEffets_=fAttaque_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttaque_.getEffet(i);
            if(!(effet_ instanceof EffectOrder)){
                continue;
            }
            EffectOrder effetOrdre_=(EffectOrder)effet_;
            if(!effetOrdre_.getTargetAttacksLast()){
                return cibles_;
            }
        }
        return new TeamPositionList();
    }

    static void roundThrowerHealing(Fight _fight,TeamPosition _lanceur, Difficulty _diff,DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        String objet_=creatureLanceur_.getChosenHealingItem();
        usedItemsWhileRound(_fight, creatureLanceur_, objet_);
        String attaque_=creatureLanceur_.getFirstChosenMove();
        ActionHeal ac_ = (ActionHeal) creatureLanceur_.getAction();
        if (ac_.isTeam()) {
            healTeam(_fight, _import, equipeLanceur_);
            //No real animation for healing a team
            AnimationHealing animationHeal_;
            animationHeal_ = new AnimationHealing();
            animationHeal_.setIndex(_fight.getEffects().size());
            animationHeal_.setHealed(new TargetCoords(_lanceur.getTeam(), Fighter.BACK));
            _fight.getEffects().add(animationHeal_);
            if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER && NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER)) {
                //As for all team, is exists a "no ko fighter" at the front, so there a single free place
                TeamPositionList pkPlayers_;
                pkPlayers_ = FightOrder.fightersBelongingToUser(_fight, true);
                _fight.setFullHealing(true);
                Bytes ally_ = new Bytes();
                int mult_ = _fight.getMult();
                for (byte m = IndexConstants.FIRST_INDEX; m < mult_; m++) {
                    ally_.addAllElts(_fight.getUserTeam().fightersAtCurrentPlace(m));
                }
                Fighter allyPk_ = _fight.getFighter(Fight.toUserFighter(ally_.first()));
                allyPk_.affectGroundPlaceSubst();
                _fight.getFirstPositPlayerFighters().put(ally_.first(), allyPk_.getGroundPlace());
                //_fight.getPlayerMaxNumberFrontFighters() == 1, but it is better to store in a variable
                //allyPk_.getGroundPlace() == 0 or allyPk_.getGroundPlace() == 1
                byte pl_ = (byte) (_fight.getPlayerMaxNumberFrontFighters() - allyPk_.getGroundPlace());
                Fighter fighter_ = _fight.getFighter(pkPlayers_.first());
                FightSending.sending(_fight, pkPlayers_.first(), _diff, _import);
                fighter_.fullHeal(_import);
                fighter_.groundPlaceSubst(pl_);
                _fight.getFirstPositPlayerFighters().put(pkPlayers_.first().getPosition(), pl_);
                _fight.setFullHealing(false);
                AnimationSwitch animation_;
                animation_ = new AnimationSwitch();
                animation_.setIndex(_fight.getEffects().size());
                animation_.setSubstituted(new TargetCoords(pkPlayers_.first().getTeam(), pl_));
                animation_.setSubstituteName(fighter_.getName());
                animation_.setLevel(fighter_.getLevel());
                animation_.setRateRemainHp(fighter_.rateRemainHp());
                animation_.setWonExpRate(fighter_.wonExpRate(_import));
                _fight.getEffects().add(animation_);
                _fight.addComment(fighter_.getComment());
                _fight.setState(FightState.ATTAQUES);
            }
            return;
        }
        Item ficheObjet_=_import.getItem(objet_);
        if(ficheObjet_ instanceof Berry){
            Berry berry_ = (Berry) ficheObjet_;
            roundThrowerHealingBerry(_fight, _lanceur, _import, creatureLanceur_, objet_, attaque_, berry_);
        }
        if (ficheObjet_ instanceof HealingItem) {
            HealingItem soin_=(HealingItem)ficheObjet_;
            roundThrowerHealingItem(_fight, _lanceur, _import, creatureLanceur_, objet_, attaque_, soin_);
        }
        healEffect(_fight, _lanceur, creatureLanceur_);
    }

    private static void roundThrowerHealingItem(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creatureLanceur, String _objet, String _attaque, HealingItem _soin) {
        short coeff_=1;
        if(_soin.getHappiness().contains(_creatureLanceur.getUsedBallCatching())){
            coeff_= _soin.getHappiness().getVal(_creatureLanceur.getUsedBallCatching());
        }
        _creatureLanceur.clearMessages();
        _creatureLanceur.winHappinessByGrowingLevel(coeff_, _import);
        _fight.addComment(_creatureLanceur.getComment());
        if(_soin instanceof HealingPp){
            HealingPp soinPp_=(HealingPp) _soin;
            roundThrowerHealingItemPp(_import, _creatureLanceur, _objet, _attaque, soinPp_);
        }
        if(_soin instanceof HealingHp){
            HealingHp soinPv_=(HealingHp) _soin;
            _creatureLanceur.variationLeftHp(soinPv_.getHp());
            _fight.addHpMessage(_lanceur, _import);
        }
        if(_soin instanceof HealingHpStatus){
            HealingHpStatus soinPvStatut_=(HealingHpStatus) _soin;
            _creatureLanceur.variationLeftHp(Rate.multiply(soinPvStatut_.getHealedHpRate(), _creatureLanceur.pvMax()));
            _fight.addHpMessage(_lanceur, _import);
            for(String e:soinPvStatut_.getStatus()){
                _creatureLanceur.supprimerStatut(e);
                _fight.addDisabledStatusMessage(e, _lanceur, _import);
            }
        }
        if(_soin instanceof HealingStatus){
            HealingStatus soinStatut_=(HealingStatus) _soin;
            for(String e:soinStatut_.getStatus()){
                _creatureLanceur.supprimerStatut(e);
                _fight.addDisabledStatusMessage(e, _lanceur, _import);
            }
        }
    }

    private static void roundThrowerHealingItemPp(DataBase _import, Fighter _creatureLanceur, String _objet, String _attaque, HealingPp _soinPp) {
        if(_soinPp.isHealingAllMovesPp()|| _soinPp.getHealingAllMovesFullpp()>0){
            for(String c: _creatureLanceur.getCurrentMovesSet()){
                short var_= _creatureLanceur.healedPpMove(c, _objet, _import);
                if (var_ != 0) {
                    _creatureLanceur.healPowerPoints(c,var_);
                }
            }
        } else {
            short var_= _creatureLanceur.healedPpMove(_attaque, _objet, _import);
            if (var_ != 0) {
                _creatureLanceur.healPowerPoints(_attaque,var_);
            }
        }
    }

    private static void roundThrowerHealingBerry(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creatureLanceur, String _objet, String _attaque, Berry _berry) {
        _creatureLanceur.clearMessages();
        _creatureLanceur.winHappinessByGrowingLevel((short) 1, _import);
        _fight.addComment(_creatureLanceur.getComment());
        if(_berry.getHealPp()!=0){
            short var_= _creatureLanceur.healedPpMove(_attaque, _objet, _import);
            if (var_ != 0) {
                _creatureLanceur.healPowerPoints(_attaque,var_);
            }
        }
        for(Statistic c: _berry.getMultStat().getKeys()){
            byte varBase_ = _berry.getMultStat().getVal(c).getBoost();
            byte var_=FightEffects.deltaBoostStatistic(_fight, _lanceur,c,varBase_, _import);
            _creatureLanceur.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_lanceur, c, var_, _import);
        }
        if(!_berry.getHealHp().isZero()){
            _creatureLanceur.variationLeftHp(_berry.getHealHp());
            _fight.addHpMessage(_lanceur, _import);
        }
        for(String c: _creatureLanceur.getStatusSet()){
            if(NumberUtil.eq(_creatureLanceur.getStatusNbRoundShort(c), 0)){
                continue;
            }
            if(StringUtil.contains(_berry.getHealStatus(), c)){
                _creatureLanceur.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _lanceur, _import);
            }
        }
        if(!_berry.getHealHpRate().isZero()){
            _creatureLanceur.variationLeftHp(Rate.multiply(_berry.getHealHpRate(), _creatureLanceur.pvMax()));
            _fight.addHpMessage(_lanceur, _import);
        }
        if(!_berry.getHealHpBySuperEffMove().isZero()){
            _creatureLanceur.variationLeftHp(Rate.multiply(_berry.getHealHpBySuperEffMove(), _creatureLanceur.pvMax()));
            _fight.addHpMessage(_lanceur, _import);
        }
    }

    private static void healTeam(Fight _fight, DataBase _import, Team _equipeLanceur) {
        for(byte c: _equipeLanceur.getMembers().getKeys()){
            Fighter membre_= _equipeLanceur.refPartMembres(c);
            membre_.clearMessages();
            membre_.fullHeal(_import);
            _fight.addComment(membre_.getComment());
        }
    }

    private static void usedItemsWhileRound(Fight _fight, Fighter _creatureLanceur, String _objet) {
        if (_creatureLanceur.isBelongingToPlayer()) {
            if(_fight.getUsedItemsWhileRound().contains(_objet)){
                short nb_ = _fight.getUsedItemsWhileRound().getVal(_objet);
                nb_++;
                _fight.getUsedItemsWhileRound().put(_objet,nb_);
            }else{
                _fight.getUsedItemsWhileRound().put(_objet,(short)1);
            }
        }
    }

    private static void healEffect(Fight _fight, TeamPosition _lanceur, Fighter _creatureLanceur) {
        //the user may be back even if not ko then no real animation if back
        AnimationHealing animationHeal_;
        animationHeal_ = new AnimationHealing();
        animationHeal_.setIndex(_fight.getEffects().size());
        if (!_creatureLanceur.estArriere()) {
            animationHeal_.setHealed(new TargetCoords(_lanceur.getTeam(), _creatureLanceur.getGroundPlaceSubst()));
        } else {
            animationHeal_.setHealed(new TargetCoords(_lanceur.getTeam(), Fighter.BACK));
        }
        _fight.getEffects().add(animationHeal_);
    }

    static void roundThrowerSwitch(Fight _fight,TeamPosition _lanceur,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_lanceur);
        byte remplacant_=creature_.getSubstistute();
        TeamPosition envoye_=new TeamPosition(_lanceur.getTeam(),remplacant_);
        Fighter partenaire_=_fight.getFighter(envoye_);
        byte placeTerrain_=creature_.getGroundPlace();
        AnimationSwitch animation_;
        animation_ = new AnimationSwitch();
        animation_.setIndex(_fight.getEffects().size());
        byte placeTerrainPourRemplacement_=creature_.getGroundPlaceSubst();
        animation_.setSubstituted(new TargetCoords(_lanceur.getTeam(), placeTerrainPourRemplacement_));
        animation_.setSubstituteName(partenaire_.getName());
        animation_.setLevel(partenaire_.getLevel());
        animation_.setRateRemainHp(partenaire_.rateRemainHp());
        animation_.setWonExpRate(partenaire_.wonExpRate(_import));
        partenaire_.setGroundPlace(placeTerrain_);
        partenaire_.setGroundPlaceSubst(placeTerrainPourRemplacement_);
        creature_.exitFrontBattle();
        creature_.exitFrontBattleForBeingSubstitued();
        FightSending.withdrawal(_fight,_lanceur,_import);
        FightSending.sending(_fight,envoye_,_diff,_import);
        if(NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER)){
            _fight.getFirstPositPlayerFighters().put(_lanceur.getPosition(),Fighter.BACK);
            _fight.getFirstPositPlayerFighters().put(remplacant_,placeTerrainPourRemplacement_);
        } else {
            _fight.getFirstPositFoeFighters().put(_lanceur.getPosition(),Fighter.BACK);
            _fight.getFirstPositFoeFighters().put(remplacant_,placeTerrainPourRemplacement_);
        }
        animation_.setKo(partenaire_.estKo());
        _fight.getEffects().add(animation_);
    }

    static boolean substituingAfterRoundThrowerMove(Fight _fight, TeamPosition _thrower, Difficulty _diff, DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_thrower.getTeam());
        Fighter creature_=_fight.getFighter(_thrower);
        if (!creature_.isSuccessfulMove()) {
            return false;
        }
        MoveData fAtt_=_import.getMove(creature_.getFinalChosenMove());
        if(fAtt_.getSwitchType() != SwitchType.LANCEUR){
            return false;
        }
        if(creature_.isBelongingToPlayer()){
            for(byte c:equipe_.getMembers().getKeys()){
                Fighter partenaire_=equipe_.getMembers().getVal(c);
                if (partenaire_.estArriere() && !partenaire_.estKo() && partenaire_.isBelongingToPlayer()) {
                    _fight.setState(FightState.SWITCH_APRES_ATTAQUE);
                    creature_.setActed(true);
                    return true;
                }
            }
        } else if(!NumberUtil.eq(creature_.getSubstistute(),Fighter.BACK)) {
            roundThrowerSwitch(_fight, _thrower, _diff, _import);
        }
        return false;
    }

    static void endRoundShowActions(Fight _fight,Difficulty _diff,Player _user, DataBase _import) {
        _fight.setEndRound(true);
        if(FightKo.endedFight(_fight,_diff)){
            _fight.setEndRound(false);
            if (!FightFacade.win(_fight)) {
                return;
            }
            FightEndRound.proponeMovesEvolutions(_fight,_user,_diff,_import);
            return;
        }
        FightEndRound.processEndRound(_fight,_diff,_import);
        _fight.setEndRound(false);
        if (!_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff) && !FightFacade.win(_fight)) {
            return;
        }
        boolean allKo_ = allPlayerFightersKo(_fight, _import);
        if (allKo_) {
            if(FightEndRound.proponedSwitch(_fight)){
                FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
            }
            _fight.setState(FightState.SWITCH_WHILE_KO_USER);
            FightEndRound.setPlacesForFighters(_fight, false);
            return;
        }
        FightEndRound.proponeMovesEvolutions(_fight,_user,_diff,_import);
        if(_fight.getState()!=FightState.APPRENDRE_EVOLUER){
            if (FightKo.endedFight(_fight,_diff)) {
                return;
                //////fighter_.fullHeal(_import);
            }
            if(FightEndRound.proponedSwitch(_fight)){
                FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
                //for switching places
                _fight.setState(FightState.SWITCH_PROPOSE);
            }else{
                _fight.setState(FightState.ATTAQUES);
                FightEndRound.setPlacesForFighters(_fight, true);
                //init places if no substitute for achieving far targets
            }
        }
    }

    private static boolean allPlayerFightersKo(Fight _fight, DataBase _import) {
        boolean allKo_ = true;
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            if (!_fight.getFighter(f).estKo()) {
                allKo_ = false;
                break;
            }
        }
        allKo_ = changeKo(_fight, _import, allKo_);
        return allKo_;
    }

    static boolean changeKo(Fight _fight, DataBase _import, boolean _allKo) {
        boolean allKo_ = _allKo;
        if (FightFacade.win(_fight)&& allKo_) {
            for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
                Fighter fighter_ = _fight.getFighter(f);
                fighter_.fullHeal(_import);
            }
            allKo_ = false;
        }
        return allKo_;
    }

    static Rate calculateCatchingRate(Fight _fight,String _nomBall,boolean _dejaCapture,Difficulty _diff,DataBase _import){
        Fighter creatureSauvage_=_fight.wildPokemon();
        if(creatureSauvage_.estKo()){
            return DataBase.determinatedRate();
        }
        Ball ball_=(Ball)_import.getItem(_nomBall);
        //CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        //Fighter creatureUt_=_fight.getUserTeam().refPartMembres(cbts_.first());
        StringMap<String> variables_=calculateCatchingVariables(_fight, _dejaCapture, _import);
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toString());
//        if(_dejaCapture){
//            variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ONE);
//        }else{
//            variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ZERO);
//        }
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.MASSE_MOYENNE_PK),_import.getAvgWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_GENRE),creatureUt_.getGender().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_MASSE),creatureUt_.getWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_NIVEAU),Integer.toString(creatureUt_.getLevel()));
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_VITESSE),FightOrder.speed(_fight,Fight.toUserFighter(cbts_.first()),_import).toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_GENRE),creatureSauvage_.getGender().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_MASSE),creatureSauvage_.getWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_NIVEAU),Integer.toString(creatureSauvage_.getLevel()));
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_VITESSE),FightOrder.speed(_fight,Fight.toFoeFighter((byte) CustList.FIRST_INDEX),_import).toString());
        PokemonData fPk_=creatureSauvage_.fichePokemon(_import);
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_TYPES_BASE),fPk_.getTypes().join(NumericString.SEPARATOR_SET));
//        StringList pierresEvo_ = new StringList();
//        for(String c:fPk_.getEvolutions().getKeys()){
//            Evolution evo_=fPk_.getEvolution(c);
//            if(!(evo_ instanceof EvolutionStone)){
//                continue;
//            }
//            EvolutionStone pierreEvo_=(EvolutionStone)evo_;
//            pierresEvo_.add(pierreEvo_.getStone());
//        }
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_PIERRES_EVOS),pierresEvo_.join(NumericString.SEPARATOR_SET));
        Rate taux_;
        if (!ball_.getCatchingRate().isEmpty()) {
            taux_ = _import.evaluatePositiveOrZeroExp(ball_.getCatchingRate(), variables_, Rate.zero());
            if (taux_.isZero()) {
                return DataBase.determinatedRate();
            }
        } else {
            return DataBase.determinatedRate();
        }
        for(String c:creatureSauvage_.getStatusSet()){
            if(creatureSauvage_.getStatusNbRoundShort(c) == 0){
                continue;
            }
            Status statut_=_import.getStatus().getVal(c);
            // !statut_.getCatchingRate().isZero() => taux_ > 0
            taux_.multiplyBy(statut_.getCatchingRate());
        }
        if (!_diff.getAllowCatchingKo() && Rate.lowerEq(creatureSauvage_.getRemainingHp(), _import.getMinHp())) {
            return DataBase.determinatedRate();
        }
        variables_.clear();
        variables_.put(Fight.BASE_CAPT_PK, Long.toString(fPk_.getCatchingRate()));
        variables_.put(Fight.RATE_BALL_STATUS, taux_.toNumberString());
        variables_.put(Fight.FOE_PK_MAX_HP, creatureSauvage_.pvMax().toNumberString());
        variables_.put(Fight.FOE_PK_REMOTE_HP, creatureSauvage_.getRemainingHp().toNumberString());

        String numericExp_ = _import.getRateCatching();
        return _import.evaluateNumericable(numericExp_, variables_, Rate.one());
    }

    static StringMap<String> calculateCatchingVariables(Fight _fight,boolean _dejaCapture,DataBase _import) {
        Fighter creatureSauvage_=_fight.wildPokemon();
//        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        CustList<FighterPosition> cbts_=_fight.getUserTeam().playerFighterAtIndex(IndexConstants.FIRST_INDEX);
        Fighter creatureUt_=cbts_.first().getFighter();
        StringMap<String> variables_=new StringMap<String>();
        if(_dejaCapture){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ZERO);
        }
        return varsCatchFlee(_fight, _import, creatureUt_, cbts_, creatureSauvage_, variables_);
    }

    static Rate calculateFleeingRate(Fight _fight,Difficulty _diff,DataBase _import){
        if(_diff.getStillPossibleFlee()){
            return DataBase.determinatedRate();
        }
        //Fighter creatureSauvage_=_fight.wildPokemon();
        //CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        //Fighter creatureUt_=_fight.getUserTeam().refPartMembres(cbts_.first());
        StringMap<String> variables_= calculateFleeingVariable(_fight, _import);
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.NB_FLEES),Short.toString(_fight.getNbFleeAttempt()));
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.MASSE_MOYENNE_PK),_import.getAvgWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_GENRE),creatureUt_.getGender().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_MASSE),creatureUt_.getWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_NIVEAU),Integer.toString(creatureUt_.getLevel()));
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_UT_VITESSE),FightOrder.speed(_fight,Fight.toUserFighter(cbts_.first()),_import).toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_GENRE),creatureSauvage_.getGender().name());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_MASSE),creatureSauvage_.getWeight().toString());
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_NIVEAU),Integer.toString(creatureSauvage_.getLevel()));
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_VITESSE),FightOrder.speed(_fight,Fight.toFoeFighter((byte) CustList.FIRST_INDEX),_import).toString());
//        PokemonData fPk_=creatureSauvage_.fichePokemon(_import);
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_TYPES_BASE),fPk_.getTypes().join(NumericString.SEPARATOR_SET));
//        StringList pierresEvo_ = new StringList();
//        for(String c:fPk_.getEvolutions().getKeys()){
//            Evolution evo_=fPk_.getEvolution(c);
//            if(!(evo_ instanceof EvolutionStone)){
//                continue;
//            }
//            EvolutionStone pierreEvo_=(EvolutionStone)evo_;
//            pierresEvo_.add(pierreEvo_.getStone());
//        }
//        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_PIERRES_EVOS),pierresEvo_.join(NumericString.SEPARATOR_SET));

        String flee_ = _import.getRateFleeing();
        return _import.evaluateNumericable(flee_, variables_, Rate.one());
    }

    static StringMap<String> calculateFleeingVariable(Fight _fight, DataBase _import){
        Fighter creatureSauvage_=_fight.wildPokemon();
//        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        CustList<FighterPosition> cbts_=_fight.getUserTeam().playerFighterAtIndex(IndexConstants.FIRST_INDEX);
        Fighter creatureUt_=cbts_.first().getFighter();
        StringMap<String> variables_=new StringMap<String>();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_FLEES),Long.toString(_fight.getNbFleeAttempt()));
        return varsCatchFlee(_fight, _import, creatureUt_, cbts_, creatureSauvage_, variables_);
    }

    private static StringMap<String> varsCatchFlee(Fight _fight, DataBase _import, Fighter _creatureUt, CustList<FighterPosition> _cbts, Fighter _creatureSauvage, StringMap<String> _variables) {
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT), _fight.getEnvType().getEnvName());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR), _fight.getNbRounds().toNumberString());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.MASSE_MOYENNE_PK), _import.getAvgWeight().toNumberString());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_GENRE), _creatureUt.getGender().getGenderName());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_MASSE), _creatureUt.getWeight().toNumberString());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_NIVEAU),Long.toString(_creatureUt.getLevel()));
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_VITESSE),FightOrder.speed(_fight,Fight.toUserFighter(_cbts.first().getFirstPosit()), _import).toNumberString());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_GENRE), _creatureSauvage.getGender().getGenderName());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_MASSE), _creatureSauvage.getWeight().toNumberString());
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_NIVEAU),Long.toString(_creatureSauvage.getLevel()));
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_VITESSE),FightOrder.speed(_fight,Fight.toFoeFighter(IndexConstants.FIRST_INDEX), _import).toNumberString());
        PokemonData fPk_= _creatureSauvage.fichePokemon(_import);
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_TYPES_BASE), StringUtil.join(fPk_.getTypes(), _import.getSepartorSetChar()));
        StringList pierresEvo_ = new StringList();
        for(String c:fPk_.getEvolutions().getKeys()){
            Evolution evo_=fPk_.getEvolution(c);
            if(!(evo_ instanceof EvolutionStone)){
                continue;
            }
            EvolutionStone pierreEvo_=(EvolutionStone)evo_;
            pierresEvo_.add(pierreEvo_.getStone());
        }
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_PIERRES_EVOS), StringUtil.join(pierresEvo_, _import.getSepartorSetChar()));
        return _variables;
    }
}
