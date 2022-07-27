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
import aiki.fight.util.WeatherType;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.fight.util.NextUsers;
import aiki.game.fight.util.RandomBoolResults;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.EqList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

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
        EqList<TeamPosition> partners_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,false);
        TeamPosition partner_ = partners_.first();
        boolean foundCombo_ = false;
        for (MoveTarget p: _fight.getAllyChoiceSet()) {
            if (FightOrder.notKoFrontFightersBelongingToUser(_fight,true).isEmpty()) {
                continue;
            }
            TeamPosition found_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,true).first();
            Fighter fighter_ = _fight.getFighter(found_);
            String move_ = fighter_.getFirstChosenMove();
            if (!StringUtil.quickEq(move_, p.getMove())) {
                continue;
            }
            EqList<TargetCoords> list_ = fighter_.getChosenTargets();
            if (!list_.isEmpty()) {
                if (!TargetCoords.eq(list_.first(),p.getTarget())) {
                    continue;
                }
                String allyMove_ = _fight.getAllyChoiceVal(p).getMove();
                if (allyMove_.isEmpty()) {
                    continue;
                }
                TargetCoords allyTarget_ = _fight.getAllyChoiceVal(p).getTarget();
                foundCombo_ = true;
                FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, partner_, allyMove_, allyTarget_, _import);
                FightArtificialIntelligence.setBatonPassAlly(_fight, partner_, allyMove_, _import);
                break;
            }
        }
        if (!foundCombo_) {
            for (MoveTarget p: _fight.getAllyChoiceSet()) {
                if (FightOrder.notKoFrontFightersBelongingToUser(_fight,true).isEmpty()) {
                    continue;
                }
                TeamPosition found_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,true).first();
                Fighter fighter_ = _fight.getFighter(found_);
                String move_ = fighter_.getFirstChosenMove();
                if (!StringUtil.quickEq(move_, p.getMove())) {
                    continue;
                }
                String allyMove_ = _fight.getAllyChoiceVal(p).getMove();
                if (allyMove_.isEmpty()) {
                    continue;
                }
                TargetCoords allyTarget_ = _fight.getAllyChoiceVal(p).getTarget();
                foundCombo_ = true;
                FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, partner_, allyMove_, allyTarget_, _import);
                FightArtificialIntelligence.setBatonPassAlly(_fight, partner_, allyMove_, _import);
            }
        }
        if (!foundCombo_) {
            MoveTarget choice_ = _fight.getAllyChoiceValuesSet().first();
            String move_ = choice_.getMove();
            if (move_.isEmpty()) {
                return;
            }
            TargetCoords c = choice_.getTarget();
            partners_ = FightOrder.notKoFrontFightersBelongingToUser(_fight,false);
            partner_ = partners_.first();
            FightArtificialIntelligence.setFirstChosenMoveAlly(_fight, partner_, move_, c, _import);
            FightArtificialIntelligence.setBatonPassAlly(_fight, partner_, move_, _import);
        }
    }

    static void calculateNextFighters(Fight _fight, EqList<TeamPosition> _fighters, DataBase _import) {
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

    static NextUsers nextFighters(Fight _fight, EqList<TeamPosition> _fighters,DataBase _import) {
        NextUsers nextFighters_;
        nextFighters_ = new NextUsers(new EqList<TeamPosition>(),new EqList<TeamPosition>());
        EqList<TeamPosition> cbts_;
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
        EqList<TeamPosition> currentUsers_;
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
        EqList<TeamPosition> cbts_=new EqList<TeamPosition>();
        _fight.setKeepRound(true);
        _fight.getEffects().clear();
        if(_fight.getBeginRound()){
            if (!_fight.getAllyChoiceSet().isEmpty()) {
                setAllyChoices(_fight, _import);
            }
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
        EqList<TeamPosition> cbts_ = selectTargetHavingToPlayAfterThrower(_fight,_fight.getCurrentUser(),_import);
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
        for(String c:creature_.getStatusSet()){
            if(NumberUtil.eq(creature_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            statusBeginRoundAttack(_fight,_lanceur,c,_diff,_import);
            if (_fight.getSimulation()) {
                if (!_fight.getLettingUserAttackWithStatus()) {
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.CANNOT_USE);
                    return;
                }
            }
            if(!_fight.isKeepStatus()){
                _fight.addStatusBeginRoundMessage(_lanceur, c, _import);
                endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
                return;
            }
        }
        if(!StringUtil.quickEq(creature_.getLastUsedMove(),creature_.getFinalChosenMove())){
            creature_.affectNoUsesMove();
        }
        if(!FightSuccess.canUseDirectlyMove(_fight,_lanceur, _import)){
            //increment prepa_tour
            _fight.addPrepaRoundMessage(_lanceur, attaqueLanceur_, _import);
            MoveData fAtt_=_import.getMove(attaqueLanceur_);
            creature_.incrementRoundBeforeUsingMove();
            //disparait eventuel
            creature_.setDisappeared(fAtt_.getDisappearBeforeUse());
            if (creature_.isDisappeared()) {
                _fight.addDisappearedMessage(_lanceur, _import);
            }
            endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
            return;
        }
        if(creature_.isNeedingToRecharge()){
            creature_.setNeedingToRecharge(false);
            if(!FightSuccess.canSkipRecharge(_fight,_lanceur, _import)){
                _fight.addRechargeRoundMessage(_lanceur, _import);
                endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
                return;
            }
        }
        FightInvoke.processInvokingMove(_fight,_lanceur,_diff,_import);
        if (!_fight.isSuccessfulInvokation()) {
            endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
            return;
        }
        if (creature_.spendPowerPoint(attaqueLanceur_, _import)) {
            creature_.usePowerPointsByMove(_diff,attaqueLanceur_,(short) 1);
        }
        attaqueLanceur_=creature_.getFinalChosenMove();
        if (FightOrder.nbBackPartners(_fight,_lanceur) == 0) {
            if (StringUtil.contains(_import.getMovesFullHeal(), attaqueLanceur_)) {
                endRoundThrower(_fight, _lanceur, attaqueLanceur_, false, _import);
                return;
            }
        }
        MoveData fAttFinal_=_import.getMove(attaqueLanceur_);
        if (creature_.capaciteActive()) {
            AbilityData ab_ = creature_.ficheCapaciteActuelle(_import);
            if (ab_.isCopyMovesTypes()) {
                creature_.affecterTypes(fAttFinal_.getTypes());
                _fight.addChangedTypesMessage(_lanceur, fAttFinal_.getTypes(), _import);
            }
        }
        _fight.addMoveTypesMessage(_lanceur, FightMoves.moveTypes(_fight, _lanceur, attaqueLanceur_, _import), attaqueLanceur_, _import);
        int nbEffets_=fAttFinal_.nbEffets();
        boolean preliminaire_=true;
        boolean primaire_=false;

        TeamPosition lanceur_=_lanceur;
        _fight.setChangeThrower(false);
        if (fAttFinal_.canBoostAllies()) {
            StatusMoveData fAttNonOff_=(StatusMoveData)fAttFinal_;
            if (fAttNonOff_.getThievableMove()) {
                EqList<TeamPosition> takers_ = takers(_fight,lanceur_, _import);
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
        String firstMove_;
        if (_fight.isInvokedMove()) {
            firstMove_ = creature_.getAlreadyInvokedMovesRound().first();
        } else {
            firstMove_ = creature_.getFinalChosenMove();
        }
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
            if (!primaire_) {
                if (!fAttFinal_.getSecEffectsByItem().isEmpty()) {
                    if (!FightItems.canUseItsObject(_fight, _lanceur, _import)) {
                        continue;
                    }
                    if (!fAttFinal_.getSecEffectsByItem().contains(creature_.getItem())) {
                        continue;
                    }
                    if (!fAttFinal_.getSecEffectsByItem().getVal(creature_.getItem()).contains(i)) {
                        continue;
                    }
                }
            }
            processEffectTargets(_fight, _lanceur, lanceur_, primaire_, i, previousEffects_, _diff, _import);
            previousEffects_.add(i);
            if(!_fight.getAcceptableChoices()){
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                return;
            }
        }
        endRoundThrower(_fight,_lanceur, attaqueLanceur_, _import.getMove(firstMove_).getRechargeRound(), _import);
    }

    static void processEffectTargets(Fight _fight, TeamPosition _initialThrower, TeamPosition _finalThrower,
            boolean _firstEffect, int _index, Ints _previousEffect,Difficulty _diff,DataBase _import) {
        Rate tauxMultPv_ = FightStatistic.multiplyByLoveBetweenFighters(_fight,_import);
        Fighter creature_=_fight.getFighter(_initialThrower);
        String attaqueLanceur_=creature_.getFinalChosenMove();
        MoveData fAttFinal_=_import.getMove(attaqueLanceur_);
        Effect effet_=fAttFinal_.getEffet(_index);
        boolean achieveTarget_ = false;
        for(TeamPosition e:FightOrder.targetsEffect(_fight,_finalThrower,effet_,_diff,_import)){
            if (!_previousEffect.isEmpty()) {
                if (!_fight.getSuccessfulEffects().contains(new NbEffectFighterCoords((int) _previousEffect.getMaximum(-1), e))) {
                    continue;
                }
            }
            if(!NumberUtil.eq(e.getTeam(),_finalThrower.getTeam()) && !_fight.isChangeThrower()){
                _fight.setLettingUserAttackWithStatus(true);
                _fight.setKeepStatus(true);
                for(MoveTeamPosition c:creature_.getStatusRelatSet()){
                    if(!TeamPosition.eq(c.getTeamPosition(),e)){
                        continue;
                    }
                    if(NumberUtil.eq(creature_.getStatusRelatNbRoundShort(c), 0)){
                        continue;
                    }
                    statusBeginRoundAttack(_fight,_finalThrower,c.getMove(),_diff,_import);
                    _fight.addStatusBeginRoundRelMessage(_finalThrower, c.getMove(), e, _import);
                    if(!_fight.getLettingUserAttackWithStatus()){
                        if (_fight.getSimulation()) {
                            _fight.setAcceptableChoices(false);
                            _fight.setIssue(IssueSimulation.CANNOT_USE);
                            return;
                        }
                    }
                    if(!_fight.isKeepStatus()){
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                        break;
                    }
                }
                if(!_fight.getLettingUserAttackWithStatus()){
                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,e),false);
                    continue;
                }
                if(_firstEffect){
                    String firstMove_;
                    if (_fight.isInvokedMove()) {
                        firstMove_ = creature_.getAlreadyInvokedMovesRound().first();
                    } else {
                        firstMove_ = creature_.getFinalChosenMove();
                    }
                    if(_import.getMove(firstMove_) instanceof DamagingMoveData){
                        pressure(_fight,_finalThrower, e, attaqueLanceur_, _diff, _import);
                    }
                }
            }
            _fight.setSending(false);
            RandomBoolResults resultatsReussite_=FightSuccess.successfulMove(_fight,_finalThrower,e,attaqueLanceur_,_index,true,_import);
            if(!resultatsReussite_.isSuccessful()){
                _fight.addFailMoveMessage(attaqueLanceur_, e, _import);
                //precision
                _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,e),false);
                if(resultatsReussite_.isEffectIfFail() && !_fight.isChangeThrower()){
                    effectWhileFail(_fight,_finalThrower, e, attaqueLanceur_, _diff, _import);
                    if (!_fight.getAcceptableChoices()) {
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                    Rate efficiency_ = FightSuccess.rateEffAgainstTargetMove(_fight,_finalThrower, e, _import);
                    if (efficiency_.greaterThanOne()) {
                        FightItems.enableBerryHpWhileSuperEffectiveMove(_fight, e, _index, _import);
                    }
                }
                continue;
            }
            if(effet_ instanceof EffectDamage){
                boolean continueLoop_ = false;
                if (NumberUtil.eq(e.getTeam(),_finalThrower.getTeam())) {
                    //ally
                    healPartner(_fight,_finalThrower, e, tauxMultPv_, _import);
                    if (_fight.isEnabledHealingPartner()) {
                        continueLoop_ = true;
                    }
                }
                if (!continueLoop_) {
                    Rate efficiency_ = FightSuccess.rateEffAgainstTargetMove(_fight,_finalThrower, e, _import);
                    if(efficiency_.isZero()){
                        continueLoop_ = true;
                    }
                }
                if (continueLoop_) {
                    _fight.addSuccessfulMoveButNoDamageMessage(attaqueLanceur_, e, _import);
                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,e), fAttFinal_.getSecEffectIfNoDamage());
                    if (fAttFinal_.getSecEffectIfNoDamage()) {
                        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,_finalThrower), true);
                    }
                    continue;
                }
            }
            if(_firstEffect && !_fight.isChangeThrower()){
                processAccurracy(_fight,attaqueLanceur_, _finalThrower, e, _import);
                if (!_fight.getAcceptableChoices()) {
                    return;
                }
                if (!_fight.isSuccessfulUse()) {
                    _fight.addFailMoveMessage(attaqueLanceur_, e, _import);
                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,e), false);
                    continue;
                }
            }
            _fight.addSuccessfulMoveMessage(attaqueLanceur_, e, _import);
            _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,e),true);
            FightEffects.processEffectTarget(_fight,attaqueLanceur_,_index,
                    _finalThrower, e, _diff, _import);
            achieveTarget_ = true;
            if(!_fight.getAcceptableChoices()){
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                return;
            }
//            if(fAttFinal_.getSwitchType() == SwitchType.CIBLE){
//                envoiDeForce(cible_,_diff,_import);
//            }
            //attaque reussie
        }
        if (!achieveTarget_) {
            _fight.addNoAchieveTargetMessage(attaqueLanceur_, _finalThrower, _import);
        } else {
            int nbEffets_=fAttFinal_.nbEffets();
            if (nbEffets_ > _index + 1) {
                Effect eff_ = fAttFinal_.getEffet(_index + 1);
                if (eff_.getTargetChoice() == TargetChoice.LANCEUR) {
                    _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index, _finalThrower), true);
                }
            }
        }
    }

    static void statusBeginRoundAttack(Fight _fight,TeamPosition _combattant,String _nomStatut,Difficulty _diff,DataBase _import) {
        Status statut_=_import.getStatus().getVal(_nomStatut);
        if(!(statut_ instanceof StatusBeginRound)){
            return;
        }
        effectBeginRoundAttack(_fight,_combattant,_nomStatut,_diff,_import);
        if(!_fight.getAcceptableChoices()){
            _fight.setKeepStatus(false);
            return;
        }
        if(FightKo.endedFight(_fight,_diff)){
            _fight.setKeepStatus(false);
            return;
        }
        if(!_fight.getLettingUserAttackWithStatus()){
            _fight.setKeepStatus(false);
            return;
        }
    }

    static void effectBeginRoundAttack(Fight _fight,TeamPosition _combattant,String _nomStatut,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        StatusBeginRound status_ = (StatusBeginRound) _import.getStatus(_nomStatut);
        MonteCarloNumber lawNbRound_ = status_.getLawForUsingAMoveNbRound();
        MonteCarloBoolean lawUseMove_ = status_.getLawForUsingAMove();
        MonteCarloBoolean lawUseMoveIfFoe_ = status_.getLawForUsingAMoveIfFoe();
        LgInt maxRd_ = _import.getMaxRd();
        boolean tirageGuerison_=false;
        if(!lawNbRound_.events().isEmpty()&&!status_.getIncrementingEndRound()){
            if (creature_.isSingleStatus(_nomStatut)) {
                boolean fini_;
                short nbTour_=creature_.getStatusNbRoundShort(_nomStatut);
                MonteCarloBoolean loiModif_=lawNbRound_.knowingGreater(new Rate(nbTour_));
                String moveName_ = creature_.getFinalChosenMove();
                MoveData move_ = _import.getMove(moveName_);
                if (StringUtil.contains(move_.getDeletedStatus(), _nomStatut)) {
                    fini_= true;
                } else {
                    if(_fight.getSimulation()){
                        fini_= NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE);
                    } else {
                        fini_=!FightSuccess.tr(loiModif_.editNumber(maxRd_,_import.getGenerator()));
                    }
                }
                if(fini_){
                    creature_.supprimerStatut(_nomStatut);
                    _fight.addDisabledStatusMessage(_nomStatut, _combattant, _import);
                    tirageGuerison_=true;
                }else{
                    //incrementer le nombre de tour
                    creature_.incrementRoundsStatus(_nomStatut);
                    _fight.addIncrStatusMessage(_nomStatut, _combattant, _import);
                }
            } else {
                for (MoveTeamPosition s: creature_.getStatusRelatSet()) {
                    if (!StringUtil.quickEq(_nomStatut, s.getMove())) {
                        continue;
                    }
                    short nbTour_=creature_.getStatusRelatNbRoundShort(s);
                    MonteCarloBoolean loiModif_=lawNbRound_.knowingGreater(new Rate(nbTour_));
                    String moveName_ = creature_.getFinalChosenMove();
                    MoveData move_ = _import.getMove(moveName_);
                    boolean fini_;
                    if (StringUtil.contains(move_.getDeletedStatus(), _nomStatut)) {
                        fini_= true;
                    } else {
                        if(_fight.getSimulation()){
                            fini_= NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE);
                        } else {
                            fini_=!FightSuccess.tr(loiModif_.editNumber(maxRd_,_import.getGenerator()));
                        }
                    }
                    if(fini_){
                        creature_.supprimerPseudoStatutCombattant(s.getTeamPosition(), _nomStatut);
                        _fight.addDisabledStatusRelMessage(_nomStatut, _combattant, s.getTeamPosition(), nbTour_, _import);
                    }else{
                        //incrementer le nombre de tour
//                        creature_.getStatusRelat().put(s, (short) (nbTour_ + 1));
                        creature_.incrementPseudoStatutCombattant(s.getTeamPosition(), s.getMove());
                        _fight.addIncrStatusRelMessage(_nomStatut, _combattant, s.getTeamPosition(), _import);
                    }
                }
            }
        }
        //immu "paralysie totale"
        if(creature_.capaciteActive()){
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
            if(StringUtil.contains(fCapac_.getImmuStatusBeginRound(), _nomStatut)){
                tirageGuerison_=true;
            }
        }
        boolean attaquer_=true;
        if(!tirageGuerison_&&!lawUseMove_.events().isEmpty()){
            String moveName_ = creature_.getFinalChosenMove();
            MoveData move_ = _import.getMove(moveName_);
            if (StringUtil.contains(move_.getRequiredStatus(), _nomStatut)) {
                tirageGuerison_=true;
            } else if (StringUtil.contains(move_.getDeletedStatus(), _nomStatut)) {
                tirageGuerison_=true;
            } else if(_fight.getSimulation()){
                if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)){
                    attaquer_=false;
                }else{
                    tirageGuerison_=true;
                }
            }else if(FightSuccess.tr(lawUseMove_.editNumber(maxRd_,_import.getGenerator()))){
                //attaquer_ == true
                tirageGuerison_=true;
            }else{
                //ne pas attaquer
                //tirageGuerison_ == false
                attaquer_=false;
            }
            //simulation == false ==> attaquer_ == tirageGuerison_
        }
        boolean attaquerAdv_=true;
        if(!lawUseMoveIfFoe_.events().isEmpty()){
            if(_fight.getSimulation()){
                if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)){
                    attaquerAdv_=false;
                }
            } else {
                attaquerAdv_ = FightSuccess.tr(lawUseMoveIfFoe_.editNumber(maxRd_,_import.getGenerator()));
            }
        }
        MonteCarloBoolean lawFullHeal_ = status_.getLawForFullHealIfMove();
        if (creature_.isSingleStatus(_nomStatut)) {
            disableRandomlyStatus(_fight,lawFullHeal_,_combattant,_nomStatut, !tirageGuerison_, _import);
        }
        if(!attaquer_ && status_ instanceof StatusBeginRoundAutoDamage){
            StatusBeginRoundAutoDamage autoDamage_ = (StatusBeginRoundAutoDamage) status_;
            autoDamage(_fight,_combattant,autoDamage_.getPower(),autoDamage_.getAttack(),autoDamage_.getDefense(),_diff,_import);
            if(!_fight.getAcceptableChoices()){
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                return;
            }
        }
        _fight.setLettingUserAttackWithStatus(attaquer_&&attaquerAdv_);
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
        Rate boost_ = FightStatistic.rateBoost((byte) Math.min(cran_, maxBoost_), _import);
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
        boost_ = FightStatistic.rateBoost((byte) Math.min(cran_, maxBoost_), _import);
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
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)){
                if (_fight.getSimulation()) {
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
            }
        }else{
            creature_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_combattant, _import);
        }
    }

    static EqList<TeamPosition> takers(Fight _fight, TeamPosition _fighter, DataBase _data) {
        EqList<TeamPosition> takers_ = new EqList<TeamPosition>();
        for (TeamPosition t: FightOrder.frontFighters(_fight)) {
            if (NumberUtil.eq(t.getTeam(), _fighter.getTeam())) {
                continue;
            }
            byte pos_ = _fight.getFighter(_fighter).getGroundPlace();
            Fighter fighter_ = _fight.getFighter(t);
            String attaqueCible_=fighter_.getFinalChosenMove();
            if (attaqueCible_.isEmpty()) {
                continue;
            }
            if (!fighter_.getChosenTargets().containsObj(new TargetCoords(_fighter.getTeam(), pos_))) {
                continue;
            }
            if (FightOrder.getPointViewChangementType(attaqueCible_, _data) == PointViewChangementType.THIEF_BONUSES) {
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
        if(!FightAbilities.ignoreTargetAbility(_fight,_thrower,_target,_import)){
            Fighter creatureCible_=_fight.getFighter(_target);
            AbilityData fCapacCible_=creatureCible_.ficheCapaciteActuelle(_import);
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
        Fighter user_ = _fight.getFighter(_thrower);
        StringList typeAttaque_ = FightMoves.moveTypes(_fight,_thrower, _move, _import);
        if (!NumberUtil.eq(_thrower.getTeam(), _target.getTeam())) {
            for (String m :creatureCible_.getEnabledCounteringMoves().getKeys()) {
                if (!creatureCible_.getEnabledCounteringMoves().getVal(m).isEnabled()) {
                    continue;
                }
                MoveData fMove_ = _import.getMove(m);
                int nbEffects_ = fMove_.nbEffets();
                for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
                    Effect eff_ = fMove_.getEffet(i);
                    if (!(eff_ instanceof EffectCounterAttack)) {
                        continue;
                    }
                    EffectCounterAttack effectLoc_ = (EffectCounterAttack) eff_;
                    String fail_ = effectLoc_.getCounterFail();
                    if (!fail_.isEmpty()) {
                        StringMap<String> values_ = new StringMap<String>();
                        values_.putAllMap(FightValues.calculateValues(_fight,_target,_thrower,_import));
                        values_.putAllMap(FightValues.calculateBasicBooleanValues(_fight,_target, _thrower, _import));
                        if (_import.evaluateBoolean(fail_, values_, false)) {
                            continue;
                        }
                    }
                    StringMap<Rate> damage_;
                    damage_ = FightSuccess.sufferingDamageTypes(_fight, _thrower,_target, _move, _import);
                    for (String type_: damage_.getKeys()) {
                        user_.variationLeftHp(Rate.multiply(damage_.getVal(type_).opposNb(), user_.pvMax()));
                        _fight.addHpMessage(_thrower, _import);
                    }
                    if (FightSuccess.sufferingDirectMoves(_fight, _thrower, _target, _move, false, _import)) {
                        user_.variationLeftHp(Rate.multiply(effectLoc_.getSufferingDamageDirectMove().opposNb(), user_.pvMax()));
                        _fight.addHpMessage(_thrower, _import);
                    }
                    if (!user_.getRemainingHp().isZeroOrGt()) {
                        user_.getRemainingHp().affectZero();
                    }
                    if (user_.getRemainingHp().isZero()) {
                        FightKo.setKoMoveTeams(_fight, _thrower, _diff, _import);
                        _fight.addAnimationKoFighter(_thrower);
                        if (_fight.getSimulation()) {
                            _fight.setAcceptableChoices(false);
                            _fight.setIssue(IssueSimulation.KO_PLAYER);
                            return;
                        }
                    }
                    if (FightSuccess.droppedStatis(_fight, _thrower, _target, _move, false, _import)) {
                        for (Statistic s: effectLoc_.getDroppedStatDirectMove().getKeys()) {
                            byte varBase_ = effectLoc_.getDroppedStatDirectMove().getVal(s);
                            if (!FightSuccess.successChangedStatistic(_fight,_target,_thrower,s, varBase_,_import)) {
                                continue;
                            }
                            byte delta_ = FightEffects.deltaBoostStatistic(_fight,_target,s,varBase_,_import);
                            user_.variationBoostStatistique(s, delta_);
                            _fight.addStatisticMessage(_thrower, s, delta_, _import);
                        }
                    }
                }
            }
        }
        if(!creatureCible_.capaciteActive()){
            return;
        }
        AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
        for(Statistic c:fCapac_.getBoostStatRankProtected().getKeys()){
            byte boost_=fCapac_.getBoostStatRankProtected().getVal(c);
            byte delta_ = FightEffects.deltaBoostStatistic(_fight,_target,c,boost_,_import);
            creatureCible_.variationBoostStatistique(c,delta_);
            _fight.addStatisticMessage(_target, c, delta_, _import);
        }
        StringList activeWeathers_ = FightMoves.climatsActifs(_fight,_import);
        for(WeatherType k: fCapac_.getHealHpByTypeIfWeather().getKeys()){
            if (!StringUtil.contains(typeAttaque_, k.getType())) {
                continue;
            }
            for (String w: activeWeathers_) {
                if(!StringUtil.quickEq(k.getWeather(),w)){
                    continue;
                }
                Rate varPv_=new Rate(fCapac_.getHealHpByTypeIfWeather().getVal(k));
                varPv_.multiplyBy(creatureCible_.pvMax());
                creatureCible_.variationLeftHp(varPv_);
                _fight.addHpMessage(_target, _import);
            }
            if (activeWeathers_.isEmpty()) {
                if(!StringUtil.quickEq(k.getWeather(),DataBase.EMPTY_STRING)){
                    continue;
                }
                //ABSORB_VOLT, ABSORB_EAU
                Rate varPv_=new Rate(fCapac_.getHealHpByTypeIfWeather().getVal(k));
                varPv_.multiplyBy(creatureCible_.pvMax());
                creatureCible_.variationLeftHp(varPv_);
                _fight.addHpMessage(_target, _import);
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
            if(!TeamPosition.eq(c.getTeamPosition(),_partner)){
                continue;
            }
            if(NumberUtil.eq(creatureLanceur_.getStatusRelatNbRoundShort(c), 0)){
                continue;
            }
            Status statut_=_import.getStatus().getVal(c.getMove());
            Rate rateHealtHpPartner_ = statut_.rateHealtHpPartner();
            if(rateHealtHpPartner_.isZero()){
                continue;
            }
            Fighter creatureCible_=equipeLanceur_.refPartMembres(_partner.getPosition());
            Rate pv_=creatureCible_.pvMax();
            pv_.multiplyBy(rateHealtHpPartner_);
            pv_.multiplyBy(_rateHealingGlobalMoves);
            creatureCible_.variationLeftHp(pv_);
            _fight.addHpMessage(_partner, _import);
            _fight.setEnabledHealingPartner(true);
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
            if(!TeamPosition.eq(c.getTeamPosition(),_target)){
                continue;
            }
            if(!creature_.getIncrUserAccuracy().getVal(c)){
                continue;
            }
            precisionMaxCible_=true;
            attaquePrecision_=c.getMove();
            break;
        }
        boolean sansEchec_=false;
        if(FightItems.canUseItsBerry(_fight,_thrower,_import)){
            Berry baie_=(Berry)creature_.ficheObjet(_import);
            sansEchec_=baie_.getWithoutFail();
        }
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
        useBoostForAccuracy(_fight, _thrower, _target, attaquePrecision_, precisionMaxCible_, sansEchec_, precisionEstPartielle_, _import);
    }

    static void useBoostForAccuracy(Fight _fight,
            TeamPosition _thrower,
            TeamPosition _target,
            String _accuracyMove,
            boolean _maxAccuracy,
            boolean _withoutFailObject,
            boolean _partialAccuracy, DataBase _import) {
        Fighter creature_=_fight.getFighter(_thrower);
        if(_partialAccuracy){
            if(_maxAccuracy){
                creature_.getIncrUserAccuracy().put(new MoveTeamPosition(_accuracyMove,_target), false);
                _fight.addDisabledMoveRelMessage(_target, _accuracyMove, _thrower, _import);
            }else if(_withoutFailObject){
                creature_.useObject();
            }
            //other cases: neither berry nor move that boost once accuracy of a move
            //so nothing is done
        }
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
        if (!creature_.estKo()) {
            if(creature_.capaciteActive()){
                AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
                for(Statistic c:fCapac_.getBoostStatRankEndRound().getKeys()){
                    byte boost_=fCapac_.getBoostStatRankEndRound().getVal(c);
                    byte delta_ = FightEffects.deltaBoostStatistic(_fight,_lanceur,c,boost_,_import);
                    creature_.variationBoostStatistique(c,delta_);
                    _fight.addStatisticMessage(_lanceur, c, delta_, _import);
                }
            }
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
    }

    static EqList<TeamPosition> selectTargetHavingToPlayAfterThrower(Fight _fight,TeamPosition _lanceur,DataBase _import){
        Fighter creature_=_fight.getFighter(_lanceur);
        if(creature_.estArriere()){
            return new EqList<TeamPosition>();
        }
        if(!creature_.isSuccessfulMove()){
            return new EqList<TeamPosition>();
        }
        if(creature_.getChosenTargets().isEmpty()){
            return new EqList<TeamPosition>();
        }
        TargetCoords targetCoords_ = creature_.getChosenTargets().first();
        Team equipeCbt_=_fight.getTeams().getVal((byte) targetCoords_.getTeam());
        Bytes ciblesEquipe_=equipeCbt_.fightersAtCurrentPlace(targetCoords_.getPosition());
        EqList<TeamPosition> fighters_ = new EqList<TeamPosition>();
        fighters_.addAllElts(FightOrder.fightersHavingToAct(_fight, false, _import));
        fighters_.addAllElts(FightOrder.fightersHavingToAct(_fight, true, _import));
        fighters_ = FightOrder.fightersUsingMove(_fight, fighters_);
        EqList<TeamPosition> cibles_=new EqList<TeamPosition>();
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
        return new EqList<TeamPosition>();
    }

    static void roundThrowerHealing(Fight _fight,TeamPosition _lanceur, Difficulty _diff,DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        String objet_=creatureLanceur_.getChosenHealingItem();
        if (creatureLanceur_.isBelongingToPlayer()) {
            if(_fight.getUsedItemsWhileRound().contains(objet_)){
                short nb_ = _fight.getUsedItemsWhileRound().getVal(objet_);
                nb_++;
                _fight.getUsedItemsWhileRound().put(objet_,nb_);
            }else{
                _fight.getUsedItemsWhileRound().put(objet_,(short)1);
            }
        }
        String attaque_=creatureLanceur_.getFirstChosenMove();
        ActionHeal ac_ = (ActionHeal) creatureLanceur_.getAction();
        if (ac_.isTeam()) {
            for(byte c:equipeLanceur_.getMembers().getKeys()){
                Fighter membre_=equipeLanceur_.refPartMembres(c);
                membre_.clearMessages();
                membre_.fullHeal(_import);
                _fight.addComment(membre_.getComment());
            }
            //No real animation for healing a team
            AnimationHealing animationHeal_;
            animationHeal_ = new AnimationHealing();
            animationHeal_.setIndex(_fight.getEffects().size());
            animationHeal_.setHealed(new TargetCoords(_lanceur.getTeam(), Fighter.BACK));
            _fight.getEffects().add(animationHeal_);
            if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
                if (NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER)) {
                    //As for all team, is exists a "no ko fighter" at the front, so there a single free place
                    EqList<TeamPosition> pkPlayers_;
                    pkPlayers_ = FightOrder.fightersBelongingToUser(_fight, true);
                    _fight.setFullHealing(true);
                    Bytes ally_ = new Bytes();
                    int mult_ = _fight.getMult();
                    for (byte m = IndexConstants.FIRST_INDEX; m < mult_; m++) {
                        ally_.addAllElts(_fight.getUserTeam().fightersAtCurrentPlace(m));
                    }
                    Fighter allyPk_ = _fight.getFighter(Fight.toUserFighter(ally_.first()));
                    allyPk_.setGroundPlaceSubst(allyPk_.getGroundPlace());
                    _fight.getFirstPositPlayerFighters().put(ally_.first(), allyPk_.getGroundPlace());
                    //_fight.getPlayerMaxNumberFrontFighters() == 1, but it is better to store in a variable
                    //allyPk_.getGroundPlace() == 0 or allyPk_.getGroundPlace() == 1
                    byte pl_ = (byte) (_fight.getPlayerMaxNumberFrontFighters() - allyPk_.getGroundPlace());
                    Fighter fighter_ = _fight.getFighter(pkPlayers_.first());
                    FightSending.sending(_fight, pkPlayers_.first(), _diff, _import);
                    fighter_.fullHeal(_import);
                    fighter_.setGroundPlace(pl_);
                    fighter_.setGroundPlaceSubst(pl_);
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
            }
            return;
        }
        Item ficheObjet_=_import.getItem(objet_);
        if(ficheObjet_ instanceof Berry){
            creatureLanceur_.clearMessages();
            creatureLanceur_.winHappinessByGrowingLevel((short) 1,_import);
            _fight.addComment(creatureLanceur_.getComment());
            Berry berry_ = (Berry) ficheObjet_;
            if(berry_.getHealPp()!=0){
                short var_=creatureLanceur_.healedPpMove(attaque_,objet_,_import);
                if (var_ != 0) {
                    creatureLanceur_.healPowerPoints(attaque_,var_);
                }
            }
            for(Statistic c: berry_.getMultStat().getKeys()){
                byte varBase_ = berry_.getMultStat().getVal(c).getBoost();
                byte var_=FightEffects.deltaBoostStatistic(_fight,_lanceur,c,varBase_,_import);
                creatureLanceur_.variationBoostStatistique(c,var_);
                _fight.addStatisticMessage(_lanceur, c, var_, _import);
            }
            if(!berry_.getHealHp().isZero()){
                creatureLanceur_.variationLeftHp(berry_.getHealHp());
                _fight.addHpMessage(_lanceur, _import);
            }
            for(String c:creatureLanceur_.getStatusSet()){
                if(NumberUtil.eq(creatureLanceur_.getStatusNbRoundShort(c), 0)){
                    continue;
                }
                if(StringUtil.contains(berry_.getHealStatus(), c)){
                    creatureLanceur_.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, _lanceur, _import);
                }
            }
            if(!berry_.getHealHpRate().isZero()){
                creatureLanceur_.variationLeftHp(Rate.multiply(berry_.getHealHpRate(),creatureLanceur_.pvMax()));
                _fight.addHpMessage(_lanceur, _import);
            }
            if(!berry_.getHealHpBySuperEffMove().isZero()){
                creatureLanceur_.variationLeftHp(Rate.multiply(berry_.getHealHpBySuperEffMove(),creatureLanceur_.pvMax()));
                _fight.addHpMessage(_lanceur, _import);
            }
        }
        if (ficheObjet_ instanceof HealingItem) {
            HealingItem soin_=(HealingItem)ficheObjet_;
            short coeff_=1;
            if(soin_.getHappiness().contains(creatureLanceur_.getUsedBallCatching())){
                coeff_=soin_.getHappiness().getVal(creatureLanceur_.getUsedBallCatching());
            }
            creatureLanceur_.clearMessages();
            creatureLanceur_.winHappinessByGrowingLevel(coeff_,_import);
            _fight.addComment(creatureLanceur_.getComment());
            if(soin_ instanceof HealingPp){
                HealingPp soinPp_=(HealingPp)soin_;
                if(soinPp_.isHealingAllMovesPp()||soinPp_.getHealingAllMovesFullpp()>0){
                    for(String c:creatureLanceur_.getCurrentMovesSet()){
                        short var_=creatureLanceur_.healedPpMove(c,objet_,_import);
                        if (var_ != 0) {
                            creatureLanceur_.healPowerPoints(c,var_);
                        }
                    }
                } else {
                    short var_=creatureLanceur_.healedPpMove(attaque_,objet_,_import);
                    if (var_ != 0) {
                        creatureLanceur_.healPowerPoints(attaque_,var_);
                    }
                }
            }
            if(soin_ instanceof HealingHp){
                HealingHp soinPv_=(HealingHp)soin_;
                creatureLanceur_.variationLeftHp(soinPv_.getHp());
                _fight.addHpMessage(_lanceur, _import);
            }
            if(soin_ instanceof HealingHpStatus){
                HealingHpStatus soinPvStatut_=(HealingHpStatus)soin_;
                creatureLanceur_.variationLeftHp(Rate.multiply(soinPvStatut_.getHealedHpRate(),creatureLanceur_.pvMax()));
                _fight.addHpMessage(_lanceur, _import);
                for(String e:soinPvStatut_.getStatus()){
                    creatureLanceur_.supprimerStatut(e);
                    _fight.addDisabledStatusMessage(e, _lanceur, _import);
                }
            }
            if(soin_ instanceof HealingStatus){
                HealingStatus soinStatut_=(HealingStatus)soin_;
                for(String e:soinStatut_.getStatus()){
                    creatureLanceur_.supprimerStatut(e);
                    _fight.addDisabledStatusMessage(e, _lanceur, _import);
                }
            }
        }
        healEffect(_fight, _lanceur, creatureLanceur_);
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
                if (!partenaire_.estArriere()) {
                    continue;
                }
                if (partenaire_.estKo()) {
                    continue;
                }
                if (!partenaire_.isBelongingToPlayer()) {
                    continue;
                }
                _fight.setState(FightState.SWITCH_APRES_ATTAQUE);
                creature_.setActed(true);
                return true;
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
        if(!_fight.getAcceptableChoices()){
            return;
        }
        if(FightKo.endedFight(_fight,_diff)&&!FightFacade.win(_fight)){
            return;
        }
        boolean allKo_ = true;
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            if (!_fight.getFighter(f).estKo()) {
                allKo_ = false;
                break;
            }
        }
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
        if (!_diff.getAllowCatchingKo()) {
            if (Rate.lowerEq(creatureSauvage_.getRemainingHp(), _import.getMinHp())) {
                return DataBase.determinatedRate();
            }
        }
        variables_.clear();
        variables_.put(Fight.BASE_CAPT_PK, Long.toString(fPk_.getCatchingRate()));
        variables_.put(Fight.RATE_BALL_STATUS, taux_.toNumberString());
        variables_.put(Fight.FOE_PK_MAX_HP, creatureSauvage_.pvMax().toNumberString());
        variables_.put(Fight.FOE_PK_REMOTE_HP, creatureSauvage_.getRemainingHp().toNumberString());
        String numericExp_ = _import.getCatchingFormula();
        return _import.evaluateNumericable(numericExp_, variables_, Rate.one());
    }

    static StringMap<String> calculateCatchingVariables(Fight _fight,boolean _dejaCapture,DataBase _import) {
        Fighter creatureSauvage_=_fight.wildPokemon();
//        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        CustList<FighterPosition> cbts_=_fight.getUserTeam().playerFighterAtIndex(IndexConstants.FIRST_INDEX);
        Fighter creatureUt_=cbts_.first().getFighter();
        StringMap<String> variables_=new StringMap<String>();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        if(_dejaCapture){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEJA_CAPTURE),Fight.ZERO);
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.MASSE_MOYENNE_PK),_import.getAvgWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_GENRE),creatureUt_.getGender().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_MASSE),creatureUt_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_NIVEAU),Long.toString(creatureUt_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_VITESSE),FightOrder.speed(_fight,Fight.toUserFighter(cbts_.first().getFirstPosit()),_import).toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_GENRE),creatureSauvage_.getGender().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_MASSE),creatureSauvage_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_NIVEAU),Long.toString(creatureSauvage_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_VITESSE),FightOrder.speed(_fight,Fight.toFoeFighter(IndexConstants.FIRST_INDEX),_import).toNumberString());
        PokemonData fPk_=creatureSauvage_.fichePokemon(_import);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_TYPES_BASE), StringUtil.join(fPk_.getTypes(), _import.getSepartorSetChar()));
        StringList pierresEvo_ = new StringList();
        for(String c:fPk_.getEvolutions().getKeys()){
            Evolution evo_=fPk_.getEvolution(c);
            if(!(evo_ instanceof EvolutionStone)){
                continue;
            }
            EvolutionStone pierreEvo_=(EvolutionStone)evo_;
            pierresEvo_.add(pierreEvo_.getStone());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_PIERRES_EVOS), StringUtil.join(pierresEvo_, _import.getSepartorSetChar()));
        return variables_;
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
        String flee_ = _import.getFleeingFormula();
        return _import.evaluateNumericable(flee_, variables_, Rate.one());
    }

    static StringMap<String> calculateFleeingVariable(Fight _fight, DataBase _import){
        Fighter creatureSauvage_=_fight.wildPokemon();
//        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        CustList<FighterPosition> cbts_=_fight.getUserTeam().playerFighterAtIndex(IndexConstants.FIRST_INDEX);
        Fighter creatureUt_=cbts_.first().getFighter();
        StringMap<String> variables_=new StringMap<String>();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_FLEES),Long.toString(_fight.getNbFleeAttempt()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.MASSE_MOYENNE_PK),_import.getAvgWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_GENRE),creatureUt_.getGender().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_MASSE),creatureUt_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_NIVEAU),Long.toString(creatureUt_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_UT_VITESSE),FightOrder.speed(_fight,Fight.toUserFighter(cbts_.first().getFirstPosit()),_import).toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_GENRE),creatureSauvage_.getGender().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_MASSE),creatureSauvage_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_NIVEAU),Long.toString(creatureSauvage_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_VITESSE),FightOrder.speed(_fight,Fight.toFoeFighter(IndexConstants.FIRST_INDEX),_import).toNumberString());
        PokemonData fPk_=creatureSauvage_.fichePokemon(_import);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_TYPES_BASE), StringUtil.join(fPk_.getTypes(), _import.getSepartorSetChar()));
        StringList pierresEvo_ = new StringList();
        for(String c:fPk_.getEvolutions().getKeys()){
            Evolution evo_=fPk_.getEvolution(c);
            if(!(evo_ instanceof EvolutionStone)){
                continue;
            }
            EvolutionStone pierreEvo_=(EvolutionStone)evo_;
            pierresEvo_.add(pierreEvo_.getStone());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PK_SAUVAGE_PIERRES_EVOS), StringUtil.join(pierresEvo_, _import.getSepartorSetChar()));
        return variables_;
    }
}
