
package aiki.game.fight;
import aiki.db.*;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.MoveItemType;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.Status;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.EfficiencyRate;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypeDamageBoost;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationSwitch;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.UserTarget;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.util.TeamPositionList;
import aiki.util.TeamPositionsMonteCarloNumber;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightEffects {

    private FightEffects() {
    }

    static void processEffectTarget(Fight _fight,String _move,
            int _index,
            TeamPosition _initialThrower,
            TeamPosition _initialTarget,
            Difficulty _diff,
            DataBase _import) {
        UserTarget throwerTarget_ = pairNewThrowerTarget(_fight,_move,_index,_initialThrower, _initialTarget, _import);
        Effect effet_ = _import.getMove(_move).getEffet(_index);
        successUsingMove(_fight,throwerTarget_, effet_);
        TeamPosition finalThrower_ = throwerTarget_.getUser();
        TeamPosition finalTarget_ = throwerTarget_.getTarget();
        if(effet_ instanceof EffectRestriction){
            EffectRestriction effetLoc_=(EffectRestriction)effet_;
            effectRestriction(_fight,finalThrower_,finalTarget_,effetLoc_,_move, _import);
            return;
        }
        if(effet_ instanceof EffectUnprotectFromTypes){
            EffectUnprotectFromTypes effetLoc_=(EffectUnprotectFromTypes)effet_;
            effectUnprotectFromMoveTypes(_fight,finalTarget_,effetLoc_,_move,_import);
            return;
        }
        if(effet_ instanceof EffectSwitchAbilities){
            EffectSwitchAbilities effetLoc_=(EffectSwitchAbilities)effet_;
            effectSwitchAbilities(_fight,finalThrower_,finalTarget_,effetLoc_,_import);
            return;
        }
        if(effet_ instanceof EffectSwitchItems){
            EffectSwitchItems effetLoc_=(EffectSwitchItems)effet_;
            effectSwitchObjects(_fight,finalThrower_,finalTarget_,effetLoc_,_import);
            return;
        }
        if(effet_ instanceof EffectSwitchTypes){
            EffectSwitchTypes effetLoc_=(EffectSwitchTypes)effet_;
            effectSwitchTypes(_fight,finalThrower_,finalTarget_,effetLoc_,_import);
            return;
        }
        if (effet_ instanceof EffectSwitchMoveTypes) {
            effectSwitchMoveTypes(_fight, _move, finalTarget_, _import);
            return;
        }
        if(effet_ instanceof EffectClone){
            EffectClone effetLoc_=(EffectClone)effet_;
            Fighter creatureLanceur_=_fight.getFighter(finalThrower_);
            creatureLanceur_.creerClone(effetLoc_.getHpRateClone());
            _fight.addCreateCloneMessage(finalThrower_, _import);
            return;
        }
        if(effet_ instanceof EffectCommonStatistics){
            EffectCommonStatistics effetLoc_=(EffectCommonStatistics)effet_;
            effectCommonStatistics(_fight,finalThrower_,finalTarget_,effetLoc_,_import);
            return;
        }
        if(effet_ instanceof EffectCopyMove){
            EffectCopyMove effetLoc_=(EffectCopyMove)effet_;
            effectCopyMove(_fight,finalThrower_,finalTarget_,effetLoc_,_import);
            return;
        }
        if(effet_ instanceof EffectCopyFighter){
            EffectCopyFighter effetLoc_=(EffectCopyFighter)effet_;
            effectCopyFighter(_fight,finalThrower_,finalTarget_,effetLoc_, _import);
            return;
        }
        if (effet_ instanceof EffectCounterAttack) {
            effectCounterAttack(_fight, _move, finalTarget_, _import);
            return;
        }
        if(effet_ instanceof EffectDamage){
            effectDamage(_fight,finalThrower_,finalTarget_,_move,_diff,_import);
            return;
        }
        if(effet_ instanceof EffectSwitchPosition){
            effectSwitchPosition(_fight, finalThrower_, finalTarget_, _import);
            return;
        }
        if(effet_ instanceof EffectTeam){
            EffectTeam effetLoc_=(EffectTeam)effet_;
            effectTeam(_fight,finalThrower_,effetLoc_,_move,_import);
            return;
        }
        if(effet_ instanceof EffectTeamWhileSendFoe){
            Team equipeLanceur_=_fight.getTeams().getVal(finalThrower_.getTeam());
            equipeLanceur_.ajouterEffetEquipeEntreeAdv(_move);
            _fight.addIncrTeamUsesMoveMessage(finalThrower_.getTeam(), _move, _import);
            return;
        }
        processEffectTargetDefault(_fight, _move, _diff, _import, effet_, throwerTarget_);
    }

    private static void processEffectTargetDefault(Fight _fight, String _move, Difficulty _diff, DataBase _import, Effect _effet, UserTarget _throwerTarget) {
        TeamPosition finalThrower_ = _throwerTarget.getUser();
        TeamPosition finalTarget_ = _throwerTarget.getTarget();
        if(_effet instanceof EffectEndRound){
            effectEndRound(_fight, finalThrower_, finalTarget_, _move, _effet, _import);
            return;
        }
        if(_effet instanceof EffectWinMoney){
            EffectWinMoney effetLoc_=(EffectWinMoney) _effet;
            Fighter creatureCible_= _fight.getFighter(finalTarget_);
            Fighter creatureLanceur_= _fight.getFighter(finalThrower_);
            _fight.getWinningMoney().addNb(Rate.multiply(effetLoc_.getWinningRateBySumTargetUser(), sum(creatureCible_.getLevel(), creatureLanceur_.getLevel())));
            return;
        }
        if(_effet instanceof EffectGlobal){
            EffectGlobal effetLoc_=(EffectGlobal) _effet;
            effectGlobal(_fight,effetLoc_, _move, _import);
            return;
        }
        if(_effet instanceof EffectProtectFromTypes){
            Fighter creatureLanceur_= _fight.getFighter(finalThrower_);
            creatureLanceur_.activerAttaqueImmu(_move, _import);
            _fight.addEnabledMoveMessage(finalThrower_, _move, _import);
            return;
        }
        if(_effet instanceof EffectMultMovePower){
            EffectMultMovePower effetLoc_=(EffectMultMovePower) _effet;
            effectMultiplyMovePoser(_fight, _move, _import, finalThrower_, finalTarget_, effetLoc_);
            return;
        }
        if(_effet instanceof EffectAlly){
            //ex: COUP_D_MAIN
            Fighter creatureCible_= _fight.getFighter(finalTarget_);
            creatureCible_.getEnabledMovesForAlly().put(_move, BoolVal.TRUE);
            _fight.addHelpAllyMessage(finalThrower_, finalTarget_, _import);
            return;
        }
        if(_effet instanceof EffectAccuracy){
            Fighter creatureLanceur_= _fight.getFighter(finalThrower_);
            creatureLanceur_.getIncrUserAccuracy().put(new MoveTeamPosition(_move, finalTarget_), BoolVal.TRUE);
            _fight.addEnabledMoveRelMessage(finalTarget_, _move, finalThrower_, _import);
            return;
        }
        if(_effet instanceof EffectBatonPass){
            //remplacement du lanceur avec transfert
            effectBatonPass(_fight, finalThrower_, _diff, _import);
            return;
        }
        if(_effet instanceof EffectStatistic){
            EffectStatistic effetLoc_=(EffectStatistic) _effet;
            effectStatistic(_fight, _import, finalThrower_, finalTarget_, effetLoc_);
            return;
        }
        if(_effet instanceof EffectStatus){
            EffectStatus effetLoc_=(EffectStatus) _effet;
            effectStatus(_fight, finalThrower_, finalTarget_,effetLoc_, _diff, _import);
            return;
        }
        if(_effet instanceof EffectDamageRate){
            EffectDamageRate effetLoc_=(EffectDamageRate) _effet;
            effectDamageRate(_fight, finalTarget_,effetLoc_, _diff, _import);
            return;
        }
        if(_effet instanceof EffectFullHpRate){
            EffectFullHpRate effetLoc_=(EffectFullHpRate) _effet;
            effectFullHpRate(_fight, finalTarget_,effetLoc_, _diff, _import);
            return;
        }
        if(_effet instanceof EffectRemainedHpRate){
            EffectRemainedHpRate effetLoc_=(EffectRemainedHpRate) _effet;
            effectLeftHpRate(_fight, finalTarget_,effetLoc_, _diff, _import);
            return;
        }
        if(_effet instanceof EffectVarPP){
            EffectVarPP effetLoc_=(EffectVarPP) _effet;
            effectVarPp(_fight, finalTarget_,effetLoc_, _diff, _import);
        }
    }

    private static void effectMultiplyMovePoser(Fight _fight, String _move, DataBase _import, TeamPosition _finalThrower, TeamPosition _finalTarget, EffectMultMovePower _effetLoc) {
        if(_effetLoc.isUsed()){
            effectMultiplyUsedMovePower(_fight, _finalThrower, _effetLoc, _move, _import);
            return;
        }
        effectMultiplyUndergoneMovePower(_fight, _finalTarget, _effetLoc, _move, _import);
    }

    private static void successUsingMove(Fight _fight, UserTarget _throwerTarget, Effect _effet) {
        TeamPosition finalThrower_ = _throwerTarget.getUser();
        TeamPosition finalTarget_ = _throwerTarget.getTarget();
        Fighter creatureLanceur_=_fight.getFighter(finalThrower_);
        creatureLanceur_.successUsingMove();
        _fight.addEffect(finalThrower_, finalTarget_, _effet);
    }

    private static void effectStatistic(Fight _fight, DataBase _import, TeamPosition _finalThrower, TeamPosition _finalTarget, EffectStatistic _effetLoc) {
        IdList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(_fight, _finalThrower, _finalTarget, _effetLoc, _import);
        if (statistics_.isEmpty()) {
            return;
        }
        effectStatisticRandom(_fight, _finalThrower, _finalTarget, _effetLoc,statistics_, _import, FightSuccess.probaEffectStatistic(_fight, _finalThrower, _effetLoc.getEvtRate(), false, _import));
    }

    private static Rate sum(long _one, long _two) {
        return new Rate(_one + _two);
    }

    static UserTarget pairNewThrowerTarget(Fight _fight,
            String _move,
            int _index,
            TeamPosition _initialThrower,
            TeamPosition _initialTarget,
            DataBase _import) {
        MoveData fAttFinal_ = _import.getMove(_move);
        if (!(fAttFinal_ instanceof StatusMoveData) || _fight.getTemp().isChangeThrower()) {
            return new UserTarget(_initialThrower, _initialTarget);
        }
        return pairNewThrowerTargetStatus(_fight, _index, _initialThrower, _initialTarget, _import, (StatusMoveData) fAttFinal_);
    }

    private static UserTarget pairNewThrowerTargetStatus(Fight _fight, int _index, TeamPosition _initialThrower, TeamPosition _initialTarget, DataBase _import, StatusMoveData _fAttFinal) {
        TeamPosition finalThrower_=_initialThrower;
        TeamPosition finalTarget_=_initialTarget;
        TeamPositionList takers_ = new TeamPositionList();
        if (_fAttFinal.getThievableMove()) {
            takers_.addAllElts(FightRound.takers(_fight, _initialThrower, _import));
        }
        if (!takers_.isEmpty()) {
            _fight.getTemp().getOrderedFighters().clear();
            _fight.getTemp().getOrderedFighters().addAllElts(takers_);
            FightOrder.sortFightersUsingMoveAmongList(_fight, _import);
            takers_ = _fight.getTemp().getOrderedFighters();
            finalThrower_ = takers_.first();
            _fight.addChangingWiewPointUserMessage(finalThrower_, _import);
            if (TeamPosition.eq(_initialThrower, _initialTarget)) {
                finalTarget_ = takers_.first();
            }
            return new UserTarget(finalThrower_, finalTarget_);
        }
        if (!_fAttFinal.getCounterableMove()) {
            return new UserTarget(finalThrower_, finalTarget_);
        }
        Effect eff_ = _fAttFinal.getEffet(_index);
        if (eff_.getTargetChoice() != TargetChoice.LANCEUR) {
            Fighter creatureCibleInit_= _fight.getFighter(finalTarget_);
            String attaqueCible_=creatureCibleInit_.getFinalChosenMove();
            if (attaqueCible_.isEmpty()) {
                return new UserTarget(finalThrower_, finalTarget_);
            }
            if (FightOrder.getPointViewChangementType(attaqueCible_, _import) == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                TeamPosition tmp_= finalThrower_;
                finalThrower_ = finalTarget_;
                finalTarget_ =tmp_;
                _fight.addChangingWiewPointUserMessage(finalThrower_, _import);
                _fight.addChangingWiewPointTargetMessage(finalTarget_, _import);
            }
            return new UserTarget(finalThrower_, finalTarget_);
        }
        int foeTeam_ = Fight.foe(_initialThrower.getTeam());
        for (int f: _fight.getTeams().getVal(foeTeam_).getMembers().getKeys()) {
            TeamPosition teamPos_ = new TeamPosition(foeTeam_, f);
            Fighter f_ = _fight.getFighter(teamPos_);
            String attaqueCible_=f_.getFinalChosenMove();
            if (!attaqueCible_.isEmpty() && FightOrder.getPointViewChangementType(attaqueCible_, _import) == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                finalThrower_ = teamPos_;
                finalTarget_ = teamPos_;
                _fight.addChangingWiewPointUserMessage(teamPos_, _import);
                break;
            }
        }
        return new UserTarget(finalThrower_, finalTarget_);
    }

    static void effectRestriction(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectRestriction _effet,String _attaqueLanceur, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        if (_effet.getChoiceRestriction() == MoveChoiceRestrictionType.FORCE || _effet.getChoiceRestriction() == MoveChoiceRestrictionType.FORBIDDEN) {
            if (creatureCible_.getLastSuccessfulMove().isEmpty()) {
                return;
            }
            for(MoveTeamPosition c:creatureCible_.getTrackingMoves().getKeys()){
                if(!StringUtil.quickEq(c.getMove(),_attaqueLanceur)){
                    continue;
                }
                AffectedMove attaqueViseeActif_=creatureCible_.refPartAttaquesSurCombatAtt(c);
                attaqueViseeActif_.setMove(creatureCible_.getLastSuccessfulMove());
                attaqueViseeActif_.getActivity().enableReset();
                _fight.addEnabledMoveRelMessage(_cible, _attaqueLanceur, _lanceur, _import);
            }
            return;
        }
        if (_effet.getChoiceRestriction() == MoveChoiceRestrictionType.LANCEUR_ATTAQUES) {
            for(String c:creatureCible_.attaquesUtilisables()){
                if(StringUtil.contains(creatureLanceur_.attaquesUtilisables(), c)){
                    creatureLanceur_.getPrivateMoves().getVal(new MoveTeamPosition(_attaqueLanceur,_cible)).add(c);
                }
            }
            return;
        }
        creatureCible_.activerAttaque(_attaqueLanceur);
        _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
    }

    static void effectUnprotectFromMoveTypes(Fight _fight, TeamPosition _cible,EffectUnprotectFromTypes _effet,String _attaqueLanceur,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        for (String m: _effet.getDisableImmuFromMoves()) {
            //disable protection move
            creatureCible_.desactiverAttaqueImmu(m, _import);
            _fight.addDisabledMoveMessage(_cible, m, _import);
        }
        for (String t: _effet.getDisableImmuAgainstTypes()) {
            //disable protection against moves of type
            StringUtil.removeObj(creatureCible_.getProtectedAgainstMoveTypes(), t);
        }
        for (String t: _effet.getAttackTargetWithTypes()) {
            //disable protection against moves of type
            StringUtil.removeObj(creatureCible_.getProtectedAgainstMoveTypes(), t);
        }
        //activer l'attaque _attaqueLanceur
        //activer l'attaque _attaqueLanceur
        if (!_effet.getDisableImmuFromMoves().isEmpty() || !_effet.getDisableImmuAgainstTypes().isEmpty() || !_effet.getAttackTargetWithTypes().isEmpty() || !_effet.getTypes().isEmpty()) {
            //activer l'attaque _attaqueLanceur
            creatureCible_.activerAttaqueAntiImmu(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
        }
    }

    static void effectSwitchAbilities(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectSwitchAbilities _effet,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        if (_effet.getExchangeAbility() == ExchangeType.EXCHANGE) {
            //echange
            String capaciteLanceur_=creatureLanceur_.getCurrentAbility();
            String capaciteCible_=creatureCible_.getCurrentAbility();
            FightAbilities.disableAbility(_fight,_lanceur,capaciteCible_,_import);
            FightAbilities.disableAbility(_fight,_cible,capaciteLanceur_,_import);
            FightAbilities.enableAbility(_fight, _lanceur, _import);
            FightAbilities.enableAbility(_fight, _cible, _import);
        } else if (_effet.getExchangeAbility() == ExchangeType.GIVE_TO_TARGET) {
            String capaciteLanceur_=creatureLanceur_.getCurrentAbility();
            FightAbilities.disableAbility(_fight,_cible,capaciteLanceur_,_import);
            FightAbilities.enableAbility(_fight,_cible,_import);
        } else if (_effet.getExchangeAbility() == ExchangeType.GIVE_TO_THROWER) {
            String capaciteCible_=creatureCible_.getCurrentAbility();
            FightAbilities.disableAbility(_fight,_lanceur,capaciteCible_,_import);
            FightAbilities.enableAbility(_fight,_lanceur,_import);
        } else if (_effet.getExchangeAbility() == ExchangeType.GIVE_CONST) {
            FightAbilities.disableAbility(_fight,_cible,_effet.getConstAbility(),_import);
            FightAbilities.enableAbility(_fight,_cible,_import);
        }
    }

    static void effectSwitchObjects(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectSwitchItems _effet,DataBase _import){
        boolean storeTargetObject_ = false;
        boolean storeThrowerObject_ = false;
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        String storedObjetCible_=creatureCible_.getItem();
        String storedObjetLanceur_=creatureLanceur_.getItem();
        //stocker dans inv ut
        if(_effet.getMoveObject() == MoveItemType.EXCHANGE_OBJECTS) {
            String objetCible_=creatureCible_.getItem();
            String objetLanceur_=creatureLanceur_.getItem();
            creatureLanceur_.backUpObject(objetCible_);
            _fight.addSwitchItemsMessage(_lanceur, objetLanceur_, objetCible_, _import);
            creatureCible_.backUpObject(objetLanceur_);
            _fight.addSwitchItemsMessage(_cible, objetCible_, objetLanceur_, _import);
            FightSending.effectPlate(_fight, _lanceur, _import);
            FightSending.effectPlate(_fight, _cible, _import);
            storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
            storeThrowerObject_ = NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER);
        } else if(_effet.getMoveObject() == MoveItemType.GIVE_OBJECT_TARGET) {
            String objetLanceur_=creatureLanceur_.getItem();
            creatureCible_.backUpObject(objetLanceur_);
            _fight.addSwitchItemsMessage(_cible, DataBase.EMPTY_STRING, objetLanceur_, _import);
            creatureLanceur_.backUpObject(DataBase.EMPTY_STRING);
            _fight.addSwitchItemsMessage(_lanceur, objetLanceur_, DataBase.EMPTY_STRING, _import);
            FightSending.effectPlate(_fight, _cible, _import);
            storeThrowerObject_ = NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER);
        } else if(_effet.getMoveObject() == MoveItemType.DELETE_DEF_TARGET_BERRY) {
            storeTargetObject_ = effectSwitchObjectsDeleteDefTargetBerry(_fight, _cible, _import, creatureCible_);
        } else if(_effet.getMoveObject() == MoveItemType.USE_OBJECT_AS_POSSIBLE) {
            storeTargetObject_ = effectSwitchObjectsUseObjectAsPossible(_fight, _lanceur, _cible, _import, creatureCible_);
        } else if(_effet.getMoveObject() == MoveItemType.REUSE_LAST_OBJECT) {
            creatureCible_.restoreLastObject();
        } else if(_effet.getMoveObject() == MoveItemType.TAKE_OBJET) {
            String objetCible_=creatureCible_.getItem();
            creatureLanceur_.backUpObject(objetCible_);
            _fight.addSwitchItemsMessage(_lanceur, DataBase.EMPTY_STRING, objetCible_, _import);
            creatureCible_.backUpObject(DataBase.EMPTY_STRING);
            _fight.addSwitchItemsMessage(_cible, objetCible_, DataBase.EMPTY_STRING, _import);
            FightSending.effectPlate(_fight, _lanceur, _import);
            storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
        } else {
            String objetCible_=creatureCible_.getItem();
            creatureCible_.backUpObject(DataBase.EMPTY_STRING);
            _fight.addSwitchItemsMessage(_cible, objetCible_, DataBase.EMPTY_STRING, _import);
            storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
        }
        if (NumberUtil.eq(_cible.getTeam(), _lanceur.getTeam())) {
            return;
        }
        if (storeTargetObject_) {
            _fight.getLostObjects().add(storedObjetCible_);
        }
        if (storeThrowerObject_) {
            _fight.getLostObjects().add(storedObjetLanceur_);
        }
    }

    private static boolean effectSwitchObjectsUseObjectAsPossible(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, Fighter _creatureCible) {
        boolean storeTargetObject_ = false;
        if (FightItems.canUseBerry(_fight, _lanceur, _import)) {
            Item objet_ = _creatureCible.ficheObjet(_import);
            if (objet_ instanceof Berry) {
                FightItems.enableBerry(_fight, _lanceur, _creatureCible.getItem(), _import, (Berry) objet_);
                _creatureCible.useObject();
                storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
            }
        }
        return storeTargetObject_;
    }

    private static boolean effectSwitchObjectsDeleteDefTargetBerry(Fight _fight, TeamPosition _cible, DataBase _import, Fighter _creatureCible) {
        boolean storeTargetObject_ = false;
        if(_creatureCible.possedeObjet()){
            String objetCible_ = _creatureCible.getItem();
            Item objet_= _creatureCible.ficheObjet(_import);
            if(objet_ instanceof Berry){
                storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
                _creatureCible.backUpObject(DataBase.EMPTY_STRING);
                _fight.addSwitchItemsMessage(_cible, objetCible_, DataBase.EMPTY_STRING, _import);
            }
        }
        return storeTargetObject_;
    }

    static void effectSwitchTypes(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectSwitchTypes _effet,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        //ADAPTATION,CONVERSION_2
        if(_effet.getConstValuesType() == ConstValuesType.LANCEUR_ATTAQUES_TYPES){
            StringList lanceurAttaquesActuellesTypes_=new StringList();
            for(String c:creatureLanceur_.attaquesUtilisables()){
                lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight,_lanceur,c,_import));
            }
            StringUtil.removeAllElements(lanceurAttaquesActuellesTypes_, creatureLanceur_.getTypes());
            lanceurAttaquesActuellesTypes_.removeDuplicates();
            randomType(uniform(lanceurAttaquesActuellesTypes_), _fight, _import, creatureCible_, _cible);
        }else if(_effet.getConstValuesType() != ConstValuesType.NOTHING){
            StringList resistingTypes_ = creatureLanceur_.resistingTypes(_import);
            randomType(uniform(resistingTypes_), _fight, _import, creatureCible_, _cible);
        } else if(_effet.getChgtTypeByEnv().contains(_fight.getEnvType())){
            typesEnv(_fight, _cible, _effet, _import, creatureCible_);
        }else if(!_effet.getAddedTypes().isEmpty()){
            creatureCible_.getTypes().addAllElts(_effet.getAddedTypes());
            creatureCible_.getTypes().removeDuplicates();
        }else if (_effet.getExchangeTypes() == ExchangeType.EXCHANGE) {
            StringList targetTypes_ = creatureCible_.getTypes();
            creatureCible_.affecterTypes(creatureLanceur_.getTypes());
            _fight.addChangedTypesMessage(_cible, creatureLanceur_.getTypes(), _import);
            creatureLanceur_.affecterTypes(targetTypes_);
            _fight.addChangedTypesMessage(_lanceur, targetTypes_, _import);
        } else if (_effet.getExchangeTypes() == ExchangeType.GIVE_TO_THROWER) {
            creatureLanceur_.affecterTypes(creatureCible_.getTypes());
            _fight.addChangedTypesMessage(_lanceur, creatureCible_.getTypes(), _import);
        } else if (_effet.getExchangeTypes() == ExchangeType.GIVE_TO_TARGET) {
            creatureCible_.affecterTypes(creatureLanceur_.getTypes());
            _fight.addChangedTypesMessage(_cible, creatureLanceur_.getTypes(), _import);
        } else if (_effet.getExchangeTypes() == ExchangeType.GIVE_CONST) {
            creatureCible_.affecterTypes(_effet.getConstTypes());
            _fight.addChangedTypesMessage(_cible, _effet.getConstTypes(), _import);
        }
    }

    private static void typesEnv(Fight _fight, TeamPosition _cible, EffectSwitchTypes _effet, DataBase _import, Fighter _creatureCible) {
        StringList types_ = new StringList();
        for (String m: FightMoves.enabledGlobalMoves(_fight, _import)) {
            MoveData moveDta_ = _import.getMove(m);
            int nbEffects_ = moveDta_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
                Effect eff_ = moveDta_.getEffet(i);
                if (!(eff_ instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal effectLoc_ = (EffectGlobal) eff_;
                types_.addAllElts(effectLoc_.getChangedTypesTerrain());
            }
        }
        if (!types_.isEmpty()) {
            _creatureCible.affecterTypes(types_);
            _fight.addChangedTypesMessage(_cible, types_, _import);
        } else {
            String type_= _effet.getChgtTypeByEnv().getVal(_fight.getEnvType());
            _creatureCible.affecterTypes(type_);
            _fight.addChangedTypesMessage(_cible, new StringList(type_), _import);
        }
    }

    private static void randomType(MonteCarloString _law, Fight _fight, DataBase _import, Fighter _creatureCible, TeamPosition _cible) {
        if (!FightSuccess.isBadSimulation(_fight, _law)) {
            String type_ = FightSuccess.random(_import, _law, _fight.getTemp().getEvts());
            if (!type_.isEmpty()) {
                _creatureCible.affecterTypes(type_);
                _fight.addChangedTypesMessage(_cible, new StringList(type_), _import);
            }
        }
    }

    private static MonteCarloString uniform(StringList _list) {
        MonteCarloString types_=new MonteCarloString();
        for(String e: _list){
            types_.addQuickEvent(e,DataBase.defElementaryEvent());
        }
        return types_;
    }

    static void effectSwitchMoveTypes(Fight _fight, String _move,TeamPosition _cible, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        creatureCible_.enableChangingMovesTypes(_move);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
    }

    static void effectCommonStatistics(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectCommonStatistics _effet, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        IdMap<Statistic,Rate> statisValeurs_=new IdMap<Statistic,Rate>();
        IdMap<Statistic,String> valCommunes_=_effet.getCommonValue();
        StringMap<String> values_ = FightValues.calculateValues(_fight, _lanceur, _cible, _import);
        for(Statistic c:valCommunes_.getKeys()){
            Rate rate_ = Rate.one();
            rate_ = _import.evaluatePositiveExp(valCommunes_.getVal(c), values_, rate_);
            statisValeurs_.put(c,rate_);
        }
        for(Statistic c:statisValeurs_.getKeys()){
            Rate valCommune_=statisValeurs_.getVal(c);
            if(c == Statistic.PV_RESTANTS){
                commonHp(_fight, _cible, _import, valCommune_, creatureCible_);
                commonHp(_fight, _lanceur, _import, valCommune_, creatureLanceur_);
                continue;
            }
            creatureCible_.affecterBaseStatistique(c,valCommune_);
            creatureLanceur_.affecterBaseStatistique(c,valCommune_);
            _fight.addCommonStatisticMessage(c, _lanceur, _cible, valCommune_, _import);
        }
    }

    private static void commonHp(Fight _fight, TeamPosition _f, DataBase _import, Rate _valCommune, Fighter _fighter) {
        Rate diff_;
        if(Rate.strGreater(_valCommune, _fighter.pvMax())){
            diff_= Rate.minus(_fighter.pvMax(), _fighter.getRemainingHp());
        }else{
            diff_=Rate.minus(_valCommune, _fighter.getRemainingHp());
        }
        Rate r_ = _fighter.variationLeftHp(diff_);
        _fight.addHpMessage(_f, _import,r_);
    }

    static void effectCopyMove(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectCopyMove _effet,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        String derAttaqueCible_=creatureCible_.getLastUsedMove();
        if (derAttaqueCible_.isEmpty()) {
            return;
        }
        if (!StringUtil.contains(FightInvoke.copiableMoves(_fight, _lanceur, _cible, _effet, _import), derAttaqueCible_)) {
            return;
        }
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        String first_ = creatureLanceur_.getFirstChosenMove();
        if(_effet.getCopyingMoveForUserDef()){
            if (StringUtil.quickEq(_import.getDefMove(), first_)) {
                return;
            }
            creatureLanceur_.apprendreAttaqueEcrasantDef(derAttaqueCible_,first_,_import);
            _fight.addLearnMoveRoundDefMessage(_lanceur, first_, _import);
            return;
        }
        creatureLanceur_.apprendreAttaqueEcrasant(derAttaqueCible_,first_,_import);
        _fight.addLearnMoveRoundMessage(_lanceur, first_, _import);
    }

    static void effectCopyFighter(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectCopyFighter _effet,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        long pp_=_effet.getPpForMoves();
        creatureLanceur_.transformer(creatureCible_,pp_);
        _fight.addCopyFighterMessage(_lanceur, _cible, _import);
    }

    static void effectCounterAttack(Fight _fight, String _move,TeamPosition _cible, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        creatureCible_.enableCounteringMoves(_move);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
    }

    static void effectDamage(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,
            String _attaqueLanceur,
            Difficulty _diff,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        if (StringUtil.contains(_import.getMovesConstChoices(), _attaqueLanceur) && !_fight.getTemp().isInvokedMove() && !creatureLanceur_.getEnabledMovesConstChoices().getVal(_attaqueLanceur).isEnabled()) {
            //utilisation attaque et mise a zero
            creatureLanceur_.activerAttaqueBlocantLanceur(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_lanceur, _attaqueLanceur, _import);
        }
        ThrowerDamageLaws throwerDamageLaws_ = calculateLawsForDamageByTeam(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        if(StringUtil.contains(_import.getMovesAnticipation(), _attaqueLanceur)){
            Rate sommeCoups_=Rate.zero();
            for (TeamPosition t: throwerDamageLaws_.getNumberHits().getKeys()) {
                DamageMoveCountUser damage_ = damageByUserOfMove(_fight, _import,t, _cible, throwerDamageLaws_);
                sommeCoups_.addNb(damage_.getDamageCount());
            }
            Team equipeLanceur_ = _fight.getTeams().getVal(_lanceur.getTeam());
            Anticipation attaqueAnticipe_=equipeLanceur_.getMovesAnticipationVal(_attaqueLanceur, creatureLanceur_.getGroundPlace());
            attaqueAnticipe_.setDamage(sommeCoups_);
            attaqueAnticipe_.setNbRounds(0);
            attaqueAnticipe_.setIncrementing(true);
            attaqueAnticipe_.setTargetPosition(new TargetCoords(_cible.getTeam(),creatureCible_.getGroundPlace()));
            return;
        }
        effectDamage(_fight, throwerDamageLaws_, _lanceur, _cible, _attaqueLanceur, _diff, _import);
    }

    static void effectDamage(Fight _fight, ThrowerDamageLaws _throwerDamageLaws, TeamPosition _lanceur,TeamPosition _cible,
            String _attaqueLanceur,
            Difficulty _diff,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        useBerryAgainstEfficiencyMoves(_fight, _lanceur, _cible, _attaqueLanceur, _import);
        boostTargetStats(_fight, _lanceur, _cible, _attaqueLanceur, _import, creatureCible_);
        LgInt minHits_ = minHitsAsRate(_throwerDamageLaws).toLgInt();
        boolean miseAKo_ = koTarget(_fight, _cible, _throwerDamageLaws);
        _fight.getTemp().setPutKo(miseAKo_);
        MoveData fAttaqueLanceur_ = _import.getMove(_attaqueLanceur);
        Rate leftHp_ = new Rate(_fight.getFighter(_cible).getRemainingHp());
        miseAKo_ = damageCloneResults(_fight, _throwerDamageLaws, _cible, _import, minHits_);
        Rate degats_ = _fight.getTemp().getDamage().getDamage();
        long nbCoupsTotal_ = _fight.getTemp().getDamage().getHits();
        _fight.addNbHitsMessage(nbCoupsTotal_, _cible, _import);
        _fight.setAnimationDamage(degats_, FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import));
        if (miseAKo_) {
            calculateDamageKo(_fight, _cible, _attaqueLanceur, new LgInt(nbCoupsTotal_), _diff, _import);
            if (exitKoTarget(_fight, _diff, creatureCible_, creatureLanceur_)) {
                return;
            }
            degats_ = _fight.getTemp().getDamageKo();
        } else {
            Rate r_ = creatureCible_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_cible, _import,r_);
        }
        boolean canReverseAbsorb_ = canReverseAbsorb(_fight, _lanceur, _cible, _import);
        Rate pvSoignes_ = healedLostHp(_fight, _lanceur, leftHp_, canReverseAbsorb_, degats_, _import);
        enableAbilties(_fight, _lanceur, _cible, _attaqueLanceur, _import, creatureCible_);
        _fight.getTemp().getDamageKo().affect(degats_);
        recoilAgainstTarget(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        if (!_fight.getTemp().getAcceptableChoices() || FightKo.endedFight(_fight, _diff)) {
            return;
        }
        degats_ = _fight.getTemp().getDamageKo();
        creatureCible_.setLastSufferedMove(_attaqueLanceur);
        creatureCible_.setLastSufferedMoveTypes(FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import));
        _fight.getTemp().getDamageByCurrentUser().put(_cible,degats_);
        creatureCible_.getDamageSufferedCategRound().getVal(_import.getCategory(fAttaqueLanceur_)).addNb(degats_);
        creatureCible_.getDamageSufferedCateg().getVal(_import.getCategory(fAttaqueLanceur_)).addNb(degats_);
        //degats recul et soin
        if (pvSoignes_.isZeroOrGt() || !Rate.greaterEq(pvSoignes_.opposNb(), creatureLanceur_.getRemainingHp())) {
            Rate r_ = creatureLanceur_.variationLeftHp(pvSoignes_);
            _fight.addHpMessage(_lanceur, _import,r_);
        } else {
            FightKo.setKoMoveTeams(_fight, _lanceur, _diff, _import);
            if (exitKoThrower(_fight, _lanceur, _diff, creatureCible_, creatureLanceur_)) {
                return;
            }
        }
        if(!creatureLanceur_.estKo()&&creatureCible_.estKo()){
            enableBoostEffectWhileKoTarget(_fight, _lanceur, _attaqueLanceur, _import);
            enableAbilityWhileKoTarget(_fight, _lanceur, _import);
        }
        notKoThrowerBerryIfPossible(_fight, _lanceur, _import, creatureLanceur_);
        notKoTargetBerryIfPossible(_fight, _cible, _import, creatureCible_, fAttaqueLanceur_);
        //piege en attaque en tenant compte du clone => fin de tour
        _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), _fight.getTemp().getDamageKo());
    }

    private static void notKoThrowerBerryIfPossible(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creatureLanceur) {
        if (!_creatureLanceur.estKo()) {
            Berry berry_ = FightItems.useItsBerry(_fight, _lanceur, _import);
            if (berry_ != null) {
                FightItems.enableBerryHp(_fight, _lanceur, true, true, _import, berry_);
            }
        }
    }

    private static void notKoTargetBerryIfPossible(Fight _fight, TeamPosition _cible, DataBase _import, Fighter _creatureCible, MoveData _fAttaqueLanceur) {
        if (!_creatureCible.estKo()) {
            Berry berry_ = FightItems.useItsBerry(_fight, _cible, _import);
            if (berry_ != null) {
                if (StringUtil.quickEq(berry_.getCategoryBoosting(), _import.getCategory(_fAttaqueLanceur))) {
                    boostTarget(_fight, _cible, _import, _creatureCible, berry_.getBoostStatis());
                }
                FightItems.enableBerryHp(_fight, _cible, true, true, _import, berry_);
            }
        }
    }

    private static boolean exitKoThrower(Fight _fight, TeamPosition _lanceur, Difficulty _diff, Fighter _creatureCible, Fighter _creatureLanceur) {
        if (NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER) && _fight.getTemp().getSimulation()) {
            _fight.getTemp().setAcceptableChoices(false);
            _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            return true;
        }
        if (FightKo.endedFight(_fight, _diff)) {
            _fight.setAnimationKoFighter(_creatureLanceur.estKo(), _creatureCible.estKo(), Rate.zero());
            return true;
        }
        return false;
    }

    private static boolean exitKoTarget(Fight _fight, Difficulty _diff, Fighter _creatureCible, Fighter _creatureLanceur) {
        if (!_fight.getTemp().getAcceptableChoices()) {
            return true;
        }
        if(FightKo.endedFight(_fight, _diff)){
            _fight.setAnimationKoFighter(_creatureLanceur.estKo(), _creatureCible.estKo(), _fight.getTemp().getDamageKo());
            return true;
        }
        return false;
    }

    private static boolean damageCloneResults(Fight _fight, ThrowerDamageLaws _throwerDamageLaws, TeamPosition _cible, DataBase _import, LgInt _minHits) {
        boolean miseAKo_ = _fight.getTemp().isPutKo();
        if (!miseAKo_) {
            _fight.getTemp().getDamage().getDamage().affectZero();
            _fight.getTemp().getDamage().getDamageClone().affectZero();
            _fight.getTemp().getDamage().getDamageCount().affectZero();
            _fight.getTemp().getDamage().setCriticalHit(false);
            _fight.getTemp().getDamage().setHits(0);
            _fight.getTemp().getDamage().setKeepProcessing(true);
            for (TeamPosition t: _throwerDamageLaws.getNumberHits().getKeys()) {
                inflictDamageToTargetByUserOfMove(_fight, t, _cible, _throwerDamageLaws, _import);
            }
            miseAKo_ = _fight.getTemp().isPutKo();
        } else {
            _fight.getTemp().getDamage().setCriticalHit(false);
            long nbCoupsTotal_ = _minHits.ll();
            _fight.getTemp().getDamage().setHits(nbCoupsTotal_);
            _fight.getTemp().getDamage().setDamage(Rate.zero());
        }
        return miseAKo_;
    }

    private static void enableAbilties(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, String _attaqueLanceur, DataBase _import, Fighter _creatureCible) {
        boolean coupCritique_ = _fight.getTemp().getDamage().isCriticalHit();
        long nbCoupsTotal_ = _fight.getTemp().getDamage().getHits();
        AbilityData capaciteActiveCible_ = capaciteActiveCible(_fight, _lanceur, _cible, _import, _creatureCible);
        enableTargetAbility(
                _fight,
                _lanceur, _cible,
                coupCritique_, nbCoupsTotal_,
                _attaqueLanceur, _import);
        //puanteur + //statut au contact de la part du lanceur (capacite ou objet cible)
        enableFighterHavingToUseAbility(
                _fight,
                _lanceur, _cible,
                capaciteActiveCible_,
                _attaqueLanceur, _import);
    }

    static AbilityData capaciteActiveCible(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, Fighter _creatureCible) {
        AbilityData capaciteActiveCible_;
        if(_creatureCible.estKo()){
            capaciteActiveCible_=null;
        }else{
            capaciteActiveCible_= FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        }
        return capaciteActiveCible_;
    }

    private static void boostTargetStats(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, String _attaqueLanceur, DataBase _import, Fighter _creatureCible) {
        Item it_ = FightItems.useItsObject(_fight, _cible, _import);
        if (it_ instanceof ItemForBattle) {
            ItemForBattle item_ = (ItemForBattle) it_;
            if (FightSuccess.rateEffAgainstTargetMove(_fight, _lanceur, _attaqueLanceur, _cible, _import).greaterThanOne()) {
                boostTarget(_fight, _cible, _import, _creatureCible, item_.getBoostStatisSuperEff());
            }
            for (String t : FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import)) {
                if (item_.getBoostStatisTypes().contains(t)) {
                    boostTarget(_fight, _cible, _import, _creatureCible, item_.getBoostStatisTypes().getVal(t));
                }
            }
        }
    }

    private static void boostTarget(Fight _fight, TeamPosition _cible, DataBase _import, Fighter _creatureCible, IdMap<Statistic,Long> _map) {
        for (Statistic s: _map.getKeys()) {
            long varBase_ = _map.getVal(s);
            _creatureCible.variationBoostStatistique(s, varBase_);
            _fight.addStatisticMessage(_cible, s, varBase_, _import);
        }
    }

    static Rate healedLostHp(Fight _fight, TeamPosition _lanceur, Rate _leftHp, boolean _canReverseAbsorb, Rate _damage, DataBase _import) {
        Rate pvSoignes_=Rate.zero();
        Rate tauxPvAbsObj_=rateAbsorb(_fight, _lanceur, _import);
        if (!tauxPvAbsObj_.isZero()) {
            Rate prod_ = Rate.multiply(tauxPvAbsObj_, _damage);
            if (Rate.greaterEq(_damage, _leftHp)) {
                pvSoignes_.affect(Rate.multiply(tauxPvAbsObj_, _leftHp));
            } else {
                if (_canReverseAbsorb) {
                    pvSoignes_.affect(prod_.opposNb());
                } else {
                    pvSoignes_.affect(prod_);
                }
            }
        }
        return pvSoignes_;
    }

    static Rate rateAbsorb(Fight _fight, TeamPosition _lanceur, DataBase _import) {
        Item objet_ = FightItems.useItsObject(_fight, _lanceur, _import);
        Rate tauxPvAbsObj_=Rate.zero();
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            tauxPvAbsObj_.affect(objetAttachable_.getDrainedHpByDamageRate());
        }
        if(tauxPvAbsObj_.isZero()){
            return Rate.zero();
        }
        if(!FightKo.canBeHealed(_fight,_lanceur.getTeam(),_import)){
            return Rate.zero();
        }
        return tauxPvAbsObj_;
    }

    static void useBerryAgainstEfficiencyMoves(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,
            String _attaqueLanceur,
            DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_cible);
        StringList typeAtt_=FightMoves.moveTypes(_fight, _lanceur,_attaqueLanceur,_import);
        Berry baie_ = FightItems.useItsBerry(_fight, _cible, _import);
        if(baie_ == null){
            return;
        }
        for (String t:typeAtt_) {
            if (baie_.getMultFoesDamage().contains(t) && !Rate.lowerEq(FightSuccess.rateEffAgainstTarget(_fight, _lanceur, _cible, t, _import), baie_.getMultFoesDamage().getVal(t).getEff())) {
                creatureCible_.useObject();
                break;
            }
        }
    }

    static DamageMoveCountUser damageByUserOfMove(
            Fight _fight, DataBase _import,
            TeamPosition _fighter, TeamPosition _target,
            ThrowerDamageLaws _laws) {
        MonteCarloNumber loiRand_ = _laws.getRandomRate();
        MonteCarloNumber repetCoup_=_laws.getNumberHits().getVal(_fighter);
        Rate sommeCoups_ = Rate.zero();
        long nbCoups_ = NumberUtil.max(1,randomRate(_fight, _import, repetCoup_, _target).ll());
        boolean coupCritique_ = false;
        for (long i = IndexConstants.FIRST_INDEX; i<nbCoups_; i++){
            Rate degatsBase_ = new PkMonteCarlo<Rate>(_import,_laws.getBase().getVal(_fighter),_fight.getTemp().getEvts()).editNumber();
            Rate cc_ = randomRate(_fight, _import,_laws.getCriticalHit().getVal(_fighter), _target);
            if(cc_.greaterThanOne()){
                coupCritique_ = true;
            }
            Rate rand_ = randomRate(_fight, _import, loiRand_, _target);
            sommeCoups_.addNb(Rate.multiply(Rate.multiply(degatsBase_,cc_),rand_));
        }
        DamageMoveCountUser damage_ = new DamageMoveCountUser();
        damage_.setCriticalHit(coupCritique_);
        damage_.setDamageCount(sommeCoups_);
        damage_.setHits(nbCoups_);
        return damage_;
    }

    static void inflictDamageToTargetByUserOfMove(
            Fight _fight,
            TeamPosition _fighter, TeamPosition _target,
            ThrowerDamageLaws _laws, DataBase _import) {
        if (_fight.getTemp().isPutKo()) {
            return;
        }
        inflictDamageToTargetByUserOfMoveNotKo(_fight, _fighter, _target, _laws, _import);
    }

    private static void inflictDamageToTargetByUserOfMoveNotKo(Fight _fight, TeamPosition _fighter, TeamPosition _target, ThrowerDamageLaws _laws, DataBase _import) {
        DamageMoveCountUser damage_ = _fight.getTemp().getDamage();
        Rate degatsClone_ = new Rate(damage_.getDamageClone());
        Rate degats_ = new Rate(damage_.getDamage());
        Rate sommeCoups_ = new Rate(damage_.getDamageCount());
        long previousHits_ = damage_.getHits();
        Fighter creatureCible_=_fight.getFighter(_target);
        MonteCarloNumber loiRand_ = _laws.getRandomRate();
        long nbCoups_ = NumberUtil.max(1, new PkMonteCarlo<Rate>(_import,_laws.getNumberHits().getVal(_fighter),_fight.getTemp().getEvts()).editNumber().ll());
        boolean coupCritique_ = false;
        boolean keepProcessing_ = true;
        long nbHits_ = 0;
        for (long i = IndexConstants.FIRST_INDEX; i<nbCoups_; i++){
            Rate degatsBase_ = new PkMonteCarlo<Rate>(_import,_laws.getBase().getVal(_fighter),_fight.getTemp().getEvts()).editNumber();
            Rate cc_ = randomRate(_fight, _import, _laws.getCriticalHit().getVal(_fighter), _target);
            if(cc_.greaterThanOne()){
                coupCritique_=true;
                _fight.addCriticalHitMessage(_import);
            }
            Rate rand_ = randomRate(_fight, _import, loiRand_, _target);
            Rate hit_ = Rate.multiply(Rate.multiply(degatsBase_,cc_),rand_);
            sommeCoups_.addNb(hit_);
            if(!creatureCible_.getClone().isZero()){
                if(Rate.strLower(hit_,creatureCible_.getClone())){
                    degatsClone_.addNb(hit_);
                    creatureCible_.infligerDegatsClone(hit_);
                    _fight.addHpCloneMessage(_target, hit_, _import);
                }else{
                    degatsClone_.addNb(creatureCible_.getClone());
                    creatureCible_.infligerDegatsClone(creatureCible_.getClone());
                    _fight.addDeletedCloneMessage(_target, _import);
                }
                if(creatureCible_.getClone().isZero()){
                    sommeCoups_.affectZero();
                }
            }else if(Rate.greaterEq(sommeCoups_,creatureCible_.getRemainingHp())){
                nbHits_++;
                degats_.affect(creatureCible_.getRemainingHp());
                //FightKo.setKoMoveTeams(_fight,_target,_diff,_import);
                keepProcessing_ = false;
                break;
            }
            if(creatureCible_.getClone().isZero()){
                degats_.affect(sommeCoups_);
            }
            nbHits_++;
        }
        criticalHit(damage_, coupCritique_);
        damage_.setDamageCount(sommeCoups_);
        damage_.setHits(previousHits_+nbHits_);
        damage_.setDamageClone(degatsClone_);
        damage_.setDamage(degats_);
        damage_.setKeepProcessing(keepProcessing_);
        checkProcessing(_fight);
    }

    private static void checkProcessing(Fight _fight) {
        if (!_fight.getTemp().getDamage().isKeepProcessing()) {
            _fight.getTemp().setPutKo(true);
        }
    }

    private static void criticalHit(DamageMoveCountUser _damage, boolean _coupCritique) {
        if (!_damage.isCriticalHit()) {
            _damage.setCriticalHit(_coupCritique);
        }
    }

    static boolean koTarget(Fight _fight, TeamPosition _cible, ThrowerDamageLaws _laws) {
        Fighter creatureCible_=_fight.getFighter(_cible);
        return koTarget(creatureCible_.getRemainingHp(), creatureCible_.getClone(), _laws);
    }

    static boolean koTarget(Rate _leftHp, Rate _cloneHp, ThrowerDamageLaws _laws) {
        boolean miseAKo_=false;
        Rate min_ = Rate.zero();
        for (TeamPosition t: _laws.getNumberHits().getKeys()) {
            min_.addNb(_laws.min(t));
        }
        if(_cloneHp.isZero()){
            if(Rate.greaterEq(min_,_leftHp)){
                miseAKo_=true;
            }
            return miseAKo_;
        }
        if(Rate.greaterEq(min_,_cloneHp)){
            Rate minHits_ = minHitsAsRate(_laws);
            Rate minDamagePerHit_ = Rate.divide(min_, minHits_);
            Rate div_ = Rate.divide(_cloneHp, minDamagePerHit_);
            LgInt nbHitsClone_ = div_.intPart();
            if (!div_.isInteger()) {
                nbHitsClone_.increment();
            }
            LgInt nbHitsNonClone_ = LgInt.minus(minHits_.toLgInt(), nbHitsClone_);
            if (Rate.greaterEq(Rate.multiply(new Rate(nbHitsNonClone_), minDamagePerHit_),_leftHp)) {
                miseAKo_=true;
            }
        }
        return miseAKo_;
    }

    private static Rate minHitsAsRate(ThrowerDamageLaws _laws) {
        Rate minHits_ = Rate.zero();
        for (TeamPosition t: _laws.getNumberHits().getKeys()) {
            minHits_.addNb(_laws.getNumberHits().getVal(t).minimum());
        }
        return minHits_;
    }

    static void calculateDamageKo(Fight _fight, TeamPosition _cible, String _attaqueLanceur,
            LgInt _minHits,
            Difficulty _diff, DataBase _import) {
        Rate pv_=remainingHp(_fight, _cible, _attaqueLanceur, _import);
        Fighter creatureCible_ = _fight.getFighter(_cible);
        DamagingMoveData fAttaqueLanceur_ = (DamagingMoveData) _import.getMove(_attaqueLanceur);
        Rate degats_ = _fight.getTemp().getDamageKo();
        if(fAttaqueLanceur_.getCannotKo() || !pv_.isZero() && LgInt.lowerEq(_minHits, LgInt.one())) {
            degats_.affect(Rate.minus(creatureCible_.getRemainingHp(),pv_));
            Rate v_ = creatureCible_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_cible, _import,v_);
        } else {
            degats_.affect(creatureCible_.getRemainingHp());
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                _fight.getTemp().setAcceptableChoices(false);
                _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            }
        }
    }

    static Rate randomRate(Fight _fight, DataBase _import,MonteCarloNumber _lawCriticalHitRate,TeamPosition _target) {
        Rate cc_;
        if(_fight.getTemp().getSimulation()){
            if(NumberUtil.eq(_target.getTeam(),Fight.CST_FOE)){
                cc_=_lawCriticalHitRate.minimum();
            }else{
                cc_=_lawCriticalHitRate.maximum();
            }
        }else{
            cc_=new PkMonteCarlo<Rate>(_import,_lawCriticalHitRate,_fight.getTemp().getEvts()).editNumber();
        }
        return cc_;
    }

    static Rate remainingHp(Fight _fight,
            TeamPosition _cible, String _move, DataBase _import) {
        Rate pv_=Rate.zero();
        Fighter creatureCible_=_fight.getFighter(_cible);
        String finalMove_ = creatureCible_.getFinalChosenMove();
        DamagingMoveData move_ = (DamagingMoveData) _import.getMove(_move);
        if(StringUtil.contains(_import.getMovesProtSingleTargetAgainstKo(), finalMove_) &&creatureCible_.isSuccessfulMove() && move_.getStoppableMoveKoSingle()){
            pv_.affect(remainingHp(finalMove_, _import));
        } else if(move_.getCannotKo()){
            pv_.affect(_import.getMinHp());
        } else {
            Item objet_ = FightItems.useItsObject(_fight, _cible, _import);
            if (objet_ instanceof ItemForBattle) {
                ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
                if (!objetAttachable_.getProtectAgainstKo().isZero()) {
                    pv_.affect(objetAttachable_.getProtectAgainstKo());
                }
                if (!objetAttachable_.getProtectAgainstKoIfFullHp().isZero() && Rate.eq(creatureCible_.getRemainingHp(), creatureCible_.pvMax())) {
                    pv_.affect(objetAttachable_.getProtectAgainstKoIfFullHp());
                }
            }
        }
        return pv_;
    }

    static Rate calculateDamageBaseWithoutRandom(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,String _attaqueLanceur,DataBase _import){
        MoveData fAtt_ = _import.getMove(_attaqueLanceur);
        EffectDamage effect_ = (EffectDamage) fAtt_.getEffet(fAtt_.indexOfPrimaryEffect());
        if(effect_.getConstDamage()){
            return _import.evaluatePositiveExp(effect_.getPower(), new StringMap<String>(), DataBase.getDefaultPower());
        }
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        if(!effect_.getDamageLaw().events().isEmpty()){
            return DataBase.defRateProduct();
        }
        if(!effect_.getMultDamageAgainst().isEmpty()){
            StringMap<Rate> degatsSubis_=creatureLanceur_.getDamageSufferedCateg();
            Rate somme_=Rate.zero();
            for(String c:degatsSubis_.getKeys()){
                if(effect_.getMultDamageAgainst().contains(c)){
                    somme_.addNb(Rate.multiply(degatsSubis_.getVal(c), effect_.getMultDamageAgainst().getVal(c)));
                }
            }
            return somme_;
        }
        StringMap<String> variables_ = FightValues.calculateValues(_fight, _lanceur, _cible, _import);
        Rate basePower_ = _import.evaluatePositiveExp(effect_.getPower(), variables_, DataBase.getDefaultPower());
        StringList typeAtt_=FightMoves.moveTypes(_fight, _lanceur,_attaqueLanceur,_import);
        FightValues.completeValuesWithThrower(_fight, _lanceur, variables_,_import);
        FightValues.completeValuesWithMoveInfo(_attaqueLanceur, variables_, basePower_, _import, typeAtt_, _import.getCategory(fAtt_));
        Rate finalPower_ = new Rate(basePower_);
        finalPower_.multiplyBy(rateObjectPower(_fight, _lanceur, variables_, _import));
        finalPower_.multiplyBy(rateTypesPower(_fight, _lanceur, _cible, typeAtt_));
        finalPower_.multiplyBy(rateAbilityPower(_fight, _lanceur, variables_, _import));
        Rate att_ = attack(_fight, _lanceur, _cible, effect_, variables_, _import);
        Rate def_ = defense(_fight, _lanceur, _cible, effect_, variables_, _import);
        StringMap<String> varLocs_ = FightValues.calculateValuesWithStat(variables_,att_,def_,finalPower_,_import);
        String damageFormula_ = _import.getDamageFormula();
        Rate degats_ = _import.evaluatePositiveExp(damageFormula_, varLocs_, finalPower_);
        degats_.multiplyBy(ratePartnerMove(creatureLanceur_, _import));
        Rate coeffEff_= FightSuccess.rateEffAgainstTargetMove(_fight, _lanceur, _attaqueLanceur, _cible, _import);
        degats_.multiplyBy(rateDamageTargetAbility(_fight, _lanceur, _cible, typeAtt_, coeffEff_, _import));
        degats_.multiplyBy(rateDamageTargetTeamMoves(_fight, _lanceur, _cible, _attaqueLanceur, _import));
        degats_.multiplyBy(rateDamageGlobalAbilities(_fight, typeAtt_, _import));
        degats_.multiplyBy(multBaseDamage(_fight, _attaqueLanceur, _import));
        degats_.multiplyBy(rateDamageGlobalMoves(_fight, typeAtt_, _import));
        FightValues.completeValuesWithRemaining(variables_,coeffEff_,creatureLanceur_.getNbRepeatingSuccessfulMoves(),_import);
        degats_.multiplyBy(rateDamageThrowerObject(_fight, _lanceur, variables_, _import));
        degats_.multiplyBy(rateDamageThrowerAbility(_fight, _lanceur, variables_, _import));
        degats_.multiplyBy(rateDamageInvokedMove(_fight, _lanceur, _cible, _import));
        degats_.multiplyBy(rateDamageTargetBerry(_fight, _lanceur, _cible, typeAtt_, _import));
        degats_.multiplyBy(rateDamageTargetBeforeUsingMove(_fight, _cible, typeAtt_, _import));
        degats_.multiplyBy(rateDamageThrowerTarget(_fight, _lanceur, _cible, _import));
        degats_.multiplyBy(rateDamageBoostedTypes(_fight, _lanceur, _attaqueLanceur, _import));
        degats_.multiplyBy(rateDamageThrowerTargetAbility(_fight, _lanceur, _cible, _import));
        degats_.multiplyBy(coeffEff_);
        return degats_;
    }

    static Rate multBaseDamage(Fight _fight, String _move, DataBase _import) {
        Rate multBasePower_ = DataBase.defRateProduct();
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                if(effetGlobal_.getMultPowerMoves().contains(_move)){
                    multBasePower_.multiplyBy(effetGlobal_.getMultPowerMoves().getVal(_move));
                }
            }
        }
        return multBasePower_;
    }

    static Rate ratePartnerMove(Fighter _fighter, DataBase _import) {
        Rate ratePartnerMove_=DataBase.defRateProduct();
        for(String c:_fighter.getEnabledMovesForAlly().getKeys()){
            if(_fighter.getEnabledMovesForAlly().getVal(c) != BoolVal.TRUE){
                continue;
            }
            MoveData fAttPart_=_import.getMove(c);
            int nbEffets_=fAttPart_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttPart_.getEffet(i);
                if(!(effet_ instanceof EffectAlly)){
                    continue;
                }
                EffectAlly effetPart_=(EffectAlly)effet_;
                if(!effetPart_.getMultAllyDamage().isZero()){
                    ratePartnerMove_.multiplyBy(effetPart_.getMultAllyDamage());
                }
            }
        }
        return ratePartnerMove_;
    }

    static Rate rateObjectPower(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Item objet_ = FightItems.useItsObject(_fight, _fighter, _import);
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            StringMap<String> vars_ = new StringMap<String>(_variables);
            vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _fighter, _import));
            rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(objetAttachable_.getMultPower(), vars_, _import));
        }
        return rate_;
    }

    static Rate rateAbilityPower(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_fighter);
        Rate rate_ = DataBase.defRateProduct();
        AbilityData ab_=fighter_.ficheCapaciteActuelle(_import);
        if(ab_ != null){
            StringMap<String> vars_ = new StringMap<String>(_variables);
            vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _fighter, _import));
            rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(ab_.getMultPower(), vars_, _import));
            for (String t: StringUtil.splitChars(StringUtil.nullToEmpty(_variables.getVal(_import.prefixAttaqueTypes())), _import.getSepartorSetChar())) {
                for (TypeDamageBoost tDamage_: ab_.getChangingBoostTypes().values()) {
                    if (!StringUtil.quickEq(tDamage_.getType(), t)) {
                        continue;
                    }
                    rate_.multiplyBy(tDamage_.getBoost());
                }
            }
        }
        return rate_;
    }

    static Rate rateTypesPower(Fight _fight, TeamPosition _thrower, TeamPosition _target, StringList _moveTypes) {
        Fighter thrower_ = _fight.getFighter(_thrower);
        Fighter target_ = _fight.getFighter(_target);
        Rate rate_ = DataBase.defRateProduct();
        for (String t: _moveTypes) {
            rate_.multiplyBy(thrower_.getDamageRateInflictedByType().getVal(t));
        }
        for (String t: _moveTypes) {
            rate_.multiplyBy(target_.getDamageRateSufferedByType().getVal(t));
        }
        return rate_;
    }

    static Rate attack(Fight _fight, TeamPosition _thrower, TeamPosition _target, EffectDamage _effect, StringMap<String> _variables, DataBase _import) {
        Fighter thrower_ = _fight.getFighter(_thrower);
        Fighter target_ = _fight.getFighter(_target);
        long baseBoost_=_import.getDefaultBoost();
        Statistic statis_=_effect.getStatisAtt();
        Rate att_;
        if(_effect.isUserAttack()){
            att_=thrower_.statistiqueGlobaleEvIv(statis_);
        }else{
            att_=target_.statistiqueGlobaleEvIv(statis_);
        }
        //Added
        StringMap<String> vars_ = new StringMap<String>(_variables);
        vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _thrower, _import));
        att_.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _thrower, statis_, vars_, _import));
        AbilityData ab_ = FightAbilities.ignoredTargetAbility(_fight, _thrower, _target, _import);
        long boost_=thrower_.getStatisBoost().getVal(statis_);
        boost_ += FightStatistic.bonusBoost(_fight, statis_, _thrower, _import);
        if(boost_<=baseBoost_){
            if(!_effect.getIgnVarStatUserNeg().containsObj(statis_)){
                att_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
            }
        }else if (ab_ == null || !ab_.isIgnFoeStatisBoost()) {
            att_.multiplyBy(FightStatistic.rateBoost(boost_, _import));
        }
        return att_;
    }

    static Rate defense(Fight _fight, TeamPosition _thrower, TeamPosition _target, EffectDamage _effect, StringMap<String> _variables, DataBase _import) {
        Fighter thrower_ = _fight.getFighter(_thrower);
        Fighter target_ = _fight.getFighter(_target);
        long baseBoost_=_import.getDefaultBoost();
        Statistic statis_=_effect.getStatisDef();
        Rate def_;
        if(_effect.isTargetDefense()){
            def_=target_.statistiqueGlobaleEvIv(statis_);
        }else{
            def_=thrower_.statistiqueGlobaleEvIv(statis_);
        }
        long boost_=target_.getStatisBoost().getVal(statis_);
        boost_ += FightStatistic.bonusBoost(_fight, statis_, _target, _import);
        if(boost_>=baseBoost_){
            if(!_effect.getIgnVarStatTargetPos().containsObj(statis_)){
                AbilityData ab_ = FightAbilities.ignoredTargetAbility(_fight, _target, _thrower, _import);
                boolean priseEnCompteBoost_= ab_ == null || !ab_.isIgnFoeStatisBoost();
                if(priseEnCompteBoost_){
                    def_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
                }
            }
        }else{
            def_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
        }
        //Added
        StringMap<String> vars_ = new StringMap<String>(_variables);
        vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _target, _import));
        def_.multiplyBy(FightStatistic.statisticWithoutBase(_fight, _target, statis_, vars_, _import));
        return def_;
    }

    static Rate rateDamageTargetAbility(Fight _fight, TeamPosition _thrower, TeamPosition _target, StringList _moveTypes, Rate _efficiency, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        AbilityData fCapaciteCible_ = FightAbilities.ignoredTargetAbility(_fight, _thrower, _target, _import);
        boolean priseEnCompteCapaciteCible_= fCapaciteCible_ != null;
        if(priseEnCompteCapaciteCible_){
            for (String t: _moveTypes) {
                if(fCapaciteCible_.getMultDamageFoe().contains(t)){
                    rate_.multiplyBy(fCapaciteCible_.getMultDamageFoe().getVal(t));
                }
            }
            if (_efficiency.greaterThanOne() && !fCapaciteCible_.getMultSufferedDamageSuperEff().isZero()) {
                rate_.multiplyBy(fCapaciteCible_.getMultSufferedDamageSuperEff());
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetTeamMoves(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        Team equipeCible_=_fight.getTeams().getVal(_target.getTeam());
        MoveData fMove_ = _import.getMove(_move);
        String cat_ = _import.getCategory(fMove_);
        int mult_ = _fight.getMult();
        CategoryMult p_ = new CategoryMult(cat_,mult_);
        for(String c:equipeCible_.enabledTeamMoves()){
            CustList<EffectTeam> list_ = effectsTeam(_import, thrower_, c);
            int nbEffets_=list_.size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                EffectTeam effet_=list_.get(i);
                if(effet_.getMultDamage().contains(p_)){
                    rate_.multiplyBy(effet_.getMultDamage().getVal(p_));
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageGlobalAbilities(Fight _fight, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        for (TeamPosition f_: FightOrder.frontFighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f_);
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            if (ab_ == null) {
                continue;
            }
            for (String t: _moveTypes) {
                if(ab_.getMultPowerMovesTypesGlobal().contains(t)){
                    rate_.multiplyBy(ab_.getMultPowerMovesTypesGlobal().getVal(t));
                }
            }
        }
        boolean reverse_ = reverseRate(_fight, _import);
        if (reverse_) {
            if (rate_.isZero()) {
                rate_ = Rate.one();
            } else {
                rate_.invertNb();
            }
        }
        return rate_;
    }

    private static boolean reverseRate(Fight _fight, DataBase _import) {
        boolean reverse_ = false;
        for (TeamPosition f_: FightOrder.frontFighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f_);
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            if (ab_ != null && ab_.isReverseEffectsPowerMovesTypesGlobal()) {
                reverse_ = true;
                break;
            }
        }
        return reverse_;
    }

    static Rate rateDamageGlobalMoves(Fight _fight, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                for (String t: _moveTypes) {
                    if(effetGlobal_.getMultDamageTypesMoves().contains(t)){
                        rate_.multiplyBy(effetGlobal_.getMultDamageTypesMoves().getVal(t));
                    }
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageThrowerObject(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Item objet_ = FightItems.useItsObject(_fight, _fighter, _import);
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(objetAttachable_.getMultDamage(), _variables, _import));
        }
        return rate_;
    }

    static Rate rateDamageThrowerAbility(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_fighter);
        AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
        if(fCapac_ != null){
            rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(fCapac_.getMultDamage(), _variables, _import));
        }
        return rate_;
    }

    static Rate rateDamageInvokedMove(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        Fighter target_ = _fight.getFighter(_target);
        if(!target_.isActed()){
            for(String e:thrower_.getAlreadyInvokedMovesRound()){
                if (!StringUtil.contains(_import.getMovesInvoking(), e)) {
                    continue;
                }
                MoveData fAttInvoque_=_import.getMove(e);
                EffectInvoke effetInvoque_=(EffectInvoke)fAttInvoque_.getEffet(IndexConstants.FIRST_INDEX);
                if (!effetInvoque_.getRateInvokationMove().isZero()) {
                    //MOI_D_ABORD
                    rate_.multiplyBy(effetInvoque_.getRateInvokationMove());
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetBerry(Fight _fight, TeamPosition _thrower, TeamPosition _target, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Berry baie_ = FightItems.useItsBerry(_fight, _target, _import);
        if(baie_ != null){
            for (String t:_moveTypes) {
                if(!baie_.getMultFoesDamage().contains(t)){
                    continue;
                }
                Rate coeff_ = FightSuccess.rateEffAgainstTarget(_fight, _thrower, _target, t, _import);
                EfficiencyRate effRate_ = baie_.getMultFoesDamage().getVal(t);
                if (!Rate.lowerEq(coeff_, effRate_.getEff())) {
                    rate_.multiplyBy(effRate_.getHpRate());
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetBeforeUsingMove(Fight _fight, TeamPosition _target, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter target_ = _fight.getFighter(_target);
        if (target_.getNbPrepaRound() <= 0) {
            return rate_;
        }
        return rateDamageTargetBeforeUsingMovePrepa(_fight, _moveTypes, _import, target_);
    }

    private static Rate rateDamageTargetBeforeUsingMovePrepa(Fight _fight, StringList _moveTypes, DataBase _import, Fighter _target) {
        Rate rate_ = DataBase.defRateProduct();
        for(String c: FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAttGlobal_= _import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if (!(effet_ instanceof EffectGlobal) || !StringUtil.contains(((EffectGlobal) effet_).getMovesUsedByTargetedFighters(), _target.getFinalChosenMove())) {
                    continue;
                }
                StringMap<Rate> multDamagePrepaRound_ = ((EffectGlobal)effet_).getMultDamagePrepaRound();
                for (String t: _moveTypes) {
                    if(multDamagePrepaRound_.contains(t)){
                        rate_.multiplyBy(multDamagePrepaRound_.getVal(t));
                    }
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageThrowerTarget(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        for(MoveTeamPosition c:thrower_.getStatusRelatSet()){
            EffectPartnerStatus eff_ = effectPartner(_thrower, _target, _import, thrower_, c);
            if (eff_ == null) {
                continue;
            }
            rate_.multiplyBy(eff_.getMultDamageAgainstFoe());
            rate_.multiplyBy(FightStatistic.multiplyByLoveBetweenFighters(_fight,_import));
        }
        return rate_;
    }
    private static EffectPartnerStatus effectPartner(TeamPosition _thrower, TeamPosition _target, DataBase _import,Fighter _th,MoveTeamPosition _c) {
        if(!NumberUtil.eq(_thrower.getTeam(),_c.getTeamPosition().getTeam())) {
            return null;
        }
        if(TeamPosition.eq(_thrower,_c.getTeamPosition())){
            return null;
        }
        if(NumberUtil.eq(_th.getStatusRelatNbRoundShort(_c), 0)){
            return null;
        }
        Status statutLoc_=_import.getStatus().getVal(_c.getMove());
        if(!statutLoc_.estActifPartenaire()){
            return null;
        }
        EffectPartnerStatus effetPart_=statutLoc_.getEffectsPartner().first();
        if(!effetPart_.getWeddingAlly()){
            return null;
        }
        if (NumberUtil.eq(_thrower.getTeam(), _target.getTeam())) {
            return null;
        }
        return effetPart_;
    }

    static Rate rateDamageBoostedTypes(Fight _fight, TeamPosition _thrower, String _move, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        MoveData fAtt_ = _import.getMove(_move);
        for (String t: fAtt_.getBoostedTypes()) {
            if(!StringUtil.contains(thrower_.getTypes(), t)){
                continue;
            }
            rate_.multiplyBy(_import.getStab());
            AbilityData fCapac_ = thrower_.ficheCapaciteActuelle(_import);
            if (fCapac_ != null && !fCapac_.getMultStab().isZero()) {
                rate_.multiplyBy(fCapac_.getMultStab());
            }
        }
        return rate_;
    }

    static Rate rateDamageThrowerTargetAbility(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        AbilityData fCapac_ = thrower_.ficheCapaciteActuelle(_import);
        if (NumberUtil.eq(_thrower.getTeam(), _target.getTeam()) && fCapac_ != null && !fCapac_.getMultAllyDamage().isZero()) {
            rate_.multiplyBy(fCapac_.getMultAllyDamage());
        }
        return rate_;
    }

    //The fight is a bean with forms
    //not necessary from data (trainer or wild pokemon)
    //add a user/target lists for calculating
    //add a message with order of fighters (because each one has an action)
    static IdMap<UsefulValueLaw,Rate> calculateMinMaxAvgVarForDamage(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,String _attaqueLanceur, Difficulty _diff,DataBase _import){
        ThrowerDamageLaws throwerDamageLaws_ = calculateLawsForDamageByTeam(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        IdMap<UsefulValueLaw,Rate> degatsUnCoup_=new IdMap<UsefulValueLaw,Rate>();
        degatsUnCoup_.put(UsefulValueLaw.MINI, Rate.zero());
        degatsUnCoup_.put(UsefulValueLaw.MAXI, Rate.zero());
        degatsUnCoup_.put(UsefulValueLaw.MOY, Rate.zero());
        degatsUnCoup_.put(UsefulValueLaw.VAR, Rate.zero());
        for(TeamPosition c:throwerDamageLaws_.getNumberHits().getKeys()){
            degatsUnCoup_.getVal(UsefulValueLaw.MINI).addNb(throwerDamageLaws_.min(c));
            degatsUnCoup_.getVal(UsefulValueLaw.MAXI).addNb(throwerDamageLaws_.max(c));
            degatsUnCoup_.getVal(UsefulValueLaw.MOY).addNb(throwerDamageLaws_.avg(c));
            degatsUnCoup_.getVal(UsefulValueLaw.VAR).addNb(throwerDamageLaws_.vr(c));
        }
        return degatsUnCoup_;
    }

    static ThrowerDamageLaws calculateLawsForDamageByTeam(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,String _attaqueLanceur, Difficulty _diff,DataBase _import){
        DamagingMoveData fAtt_=(DamagingMoveData)_import.getMove(_attaqueLanceur);
        EffectDamage effect_ = (EffectDamage) fAtt_.getEffet(fAtt_.indexOfPrimaryEffect());
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        ThrowerDamageLaws throwerDamageLaws_ = calculateLawsForDamage(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        if(effect_.getSummingUserTeamOkFighter()){
            for(int c:equipeLanceur_.notKoPartnersWithoutStatus(_lanceur.getPosition())){
                TeamPosition fighter_ = new TeamPosition(_lanceur.getTeam(),c);
                ThrowerDamageLaws throwerDamageLawsLoc_ = calculateLawsForDamage(_fight, fighter_, _cible, _attaqueLanceur, _diff, _import);
                throwerDamageLaws_.getCriticalHit().put(fighter_, throwerDamageLawsLoc_.getCriticalHit().getVal(fighter_));
                throwerDamageLaws_.getNumberHits().put(fighter_, throwerDamageLawsLoc_.getNumberHits().getVal(fighter_));
                throwerDamageLaws_.getBase().put(fighter_, throwerDamageLawsLoc_.getBase().getVal(fighter_));
            }
        }
        return throwerDamageLaws_;
    }

    static ThrowerDamageLaws calculateLawsForDamage(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,String _attaqueLanceur, Difficulty _diff,DataBase _import){
        DamagingMoveData fAtt_=(DamagingMoveData)_import.getMove(_attaqueLanceur);
        EffectDamage effect_ = (EffectDamage) fAtt_.getEffet(fAtt_.indexOfPrimaryEffect());
        ThrowerDamageLaws throwerDamageLaws_;
        throwerDamageLaws_ = new ThrowerDamageLaws();
        throwerDamageLaws_.setBase(new TeamPositionsMonteCarloNumber());
        throwerDamageLaws_.setCriticalHit(new TeamPositionsMonteCarloNumber());
        throwerDamageLaws_.setNumberHits(new TeamPositionsMonteCarloNumber());
        Fighter thrower_ = _fight.getFighter(_lanceur);
        MonteCarloNumber lawHits_ = lawHits(_import, effect_, thrower_);
        throwerDamageLaws_.getNumberHits().put(_lanceur, lawHits_);
        if(effect_.getConstDamage()){
            Rate degats_=calculateDamageBaseWithoutRandom(_fight, _lanceur,_cible,_attaqueLanceur,_import);
            MonteCarloNumber law_;
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.getCriticalHit().put(_lanceur,law_);
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.setRandomRate(law_);
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(value(_import,degats_), LgInt.one());
            throwerDamageLaws_.getBase().put(_lanceur,law_);
            return throwerDamageLaws_;
        }
        if(!effect_.getDamageLaw().events().isEmpty()){
//            ObjectMap<Rate,LgInt> loiTemp_=new ObjectMap<Rate,LgInt>();
            MonteCarloNumber loiNumerique_=new MonteCarloNumber();
            StringMap<String> values_;
            values_ = FightValues.calculateValues(_fight, _lanceur,_cible,_import);
            int nbEvts_ = effect_.getDamageLaw().nbEvents();
            for (int i = 0; i < nbEvts_; i++) {
                String e_ = effect_.getDamageLaw().getEvent(i);
                Rate valeur_;
                if (!e_.isEmpty()) {
                    valeur_ = _import.evaluatePositiveExp(e_, values_, DataBase.getDefaultPower());
                } else {
                    valeur_ = Rate.zero();
                }
                loiNumerique_.addQuickEvent(value(_import,valeur_),effect_.getDamageLaw().getFreq(i));
            }
//            for(String e:effect_.getDamageLaw().events()){
////                LgInt effectif_=LgInt.zero();
//                Rate valeur_;
//                if (!e.isEmpty()) {
//                    valeur_ = _import.evaluatePositiveExp(e, values_, DataBase.getDefaultPower());
//                } else {
//                    valeur_ = Rate.zero();
//                }
//                loiNumerique_.addQuickEvent(valeur_,effect_.getDamageLaw().rate(e));
////                if(loiTemp_.contains(valeur_)){
////                    effectif_=loiTemp_.getVal(valeur_);
////                }
////                effectif_.addNb(effect_.getDamageLaw().rate(e));
////                loiTemp_.put(valeur_,effectif_);
//            }
//            for(Rate c:loiTemp_.getKeys()){
//                loiNumerique_.addQuickEvent(c,loiTemp_.getVal(c));
//            }
            MonteCarloNumber law_;
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.getCriticalHit().put(_lanceur,law_);
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.setRandomRate(law_);
            throwerDamageLaws_.getBase().put(_lanceur,loiNumerique_);
            return throwerDamageLaws_;
        }
        if(!effect_.getMultDamageAgainst().isEmpty()){
            Rate somme_=calculateDamageBaseWithoutRandom(_fight, _lanceur,_cible,_attaqueLanceur,_import);
            MonteCarloNumber law_;
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.getCriticalHit().put(_lanceur,law_);
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(Rate.one(), LgInt.one());
            throwerDamageLaws_.setRandomRate(law_);
            law_ = new MonteCarloNumber();
            law_.addQuickEvent(value(_import,somme_), LgInt.one());
            throwerDamageLaws_.getBase().put(_lanceur,law_);
            return throwerDamageLaws_;
        }
        MonteCarloNumber loiRand_;
        if(effect_.getRandMax()){
            loiRand_=_import.getLawsDamageRate().getVal(DifficultyModelLaw.CONSTANT_MAX).getLaw();
        }else{
            loiRand_= _diff.loi(_lanceur.getTeam(), Fight.CST_PLAYER, _import);
        }
        MonteCarloNumber copyRand_ = loiRand_.copy();
        throwerDamageLaws_.setRandomRate(copyRand_);
        Rate degats_=calculateDamageBaseWithoutRandom(_fight,_lanceur,_cible,_attaqueLanceur,_import);
        MonteCarloNumber loiCc_ = loiCc(_fight, _lanceur, _cible, _import, effect_);
        MonteCarloNumber law_;
        throwerDamageLaws_.getCriticalHit().put(_lanceur,loiCc_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(value(_import,degats_), LgInt.one());
        throwerDamageLaws_.getBase().put(_lanceur,law_);
        return throwerDamageLaws_;
    }

    private static MonteCarloNumber loiCc(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, EffectDamage _effect) {
        boolean criticalHitCanHappen_;
        criticalHitCanHappen_ = criticalHitCanHappen(_fight, _lanceur, _cible, _import);
        MonteCarloNumber loiCc_ = new MonteCarloNumber();
        if(criticalHitCanHappen_){
            long boostCc_ = FightStatistic.criticalHit(_fight, _lanceur, _effect.getChRate(), _import);
            Rate probaCc_ = FightSuccess.rateCriticalHit(_fight, _lanceur, boostCc_, _import);
            Rate minCc_= _effect.getChLaw().minimum();
            Rate maxCc_ = _effect.getChLaw().maximum();
            Rate event_ = criticalHitEvent(_fight, _lanceur, maxCc_, _import);
            if (probaCc_.greaterOrEqualsOne()) {
                loiCc_.addQuickEvent(event_,DataBase.defElementaryEvent());
            } else {
                NumDiffDenNum p_ = probaCc_.getNumDiffDenNum();
                loiCc_.addQuickEvent(minCc_,p_.getDiffDenNumerator());
                if (!Rate.eq(minCc_,event_)) {
                    loiCc_.addQuickEvent(event_,p_.getNumerator());
                }
            }
        } else {
            loiCc_.addQuickEvent(Rate.one(),DataBase.defElementaryEvent());
        }
        return loiCc_;
    }

    private static Rate value(DataBase _import, Rate _value) {
        if (_value.isZeroOrLt()) {
            return _import.getDefBaseMove();
        }
        return _value;
    }

    private static MonteCarloNumber lawHits(DataBase _import, EffectDamage _effect, Fighter _thrower) {
        MonteCarloNumber law_;
        AbilityData ab_ = _thrower.ficheCapaciteActuelle(_import);
        if (ab_ != null) {
            if (ab_.isNbHits()) {
                law_ = new MonteCarloNumber();
                Rate max_ = _effect.getHitsLaw().maximum();
                law_.addQuickEvent(max_, DataBase.defElementaryEvent());
            } else {
                law_ = _effect.getHitsLaw().copy();
            }
        } else {
            law_ = _effect.getHitsLaw().copy();
        }
        return law_;
    }

    static boolean criticalHitCanHappen(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,
            DataBase _import) {
        boolean priseEnCompteCc_=true;
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter thrower_ = _fight.getFighter(_lanceur);
        for(String c:equipeCible_.enabledTeamMoves()){
            CustList<EffectTeam> list_ = effectsTeam(_import, thrower_, c);
            boolean priseEnCompteCcAtt_ =true;
            int nbEffets_=list_.size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                EffectTeam effet_=list_.get(i);
                if (effet_.getProtectAgainstCh()) {
                    priseEnCompteCcAtt_ = false;
                    break;
                }
            }
            if (!priseEnCompteCcAtt_) {
                priseEnCompteCc_ = false;
                break;
            }
        }
        AbilityData fCapac_ = FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        if (fCapac_ != null && fCapac_.isImmuCh()) {
            priseEnCompteCc_ = false;
        }
        return priseEnCompteCc_;
    }

    private static CustList<EffectTeam> effectsTeam(DataBase _import, Fighter _thrower, String _c) {
        boolean priseEnCompteEquipeCible_ = priseEnCompteEquipeCible(_import, _thrower, _c);
        if(!priseEnCompteEquipeCible_){
            return new CustList<EffectTeam>();
        }
        return FightSuccess.effectsTeamMove(_import, _c);
    }

    private static boolean priseEnCompteEquipeCible(DataBase _import, Fighter _thrower, String _c) {
        boolean priseEnCompteEquipeCible_=true;
        AbilityData fCapac_= _thrower.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && StringUtil.contains(fCapac_.getIgnFoeTeamMove(), _c)) {
            priseEnCompteEquipeCible_ = false;
        }
        return priseEnCompteEquipeCible_;
    }

    static void enableTargetAbility(
            Fight _fight,
            TeamPosition _lanceur, TeamPosition _cible,
            boolean _criticalHit, long _hitsCount,
            String _attaqueLanceur, DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_cible);
        AbilityData fCapac_ = capaciteActiveCible(_fight, _lanceur, _cible, _import, creatureCible_);
        if (fCapac_ == null) {
            return;
        }
        long maxBoost_=_import.getMaxBoost();
        StringList typeAttaque_=FightMoves.moveTypes(_fight, _lanceur,_attaqueLanceur,_import);
        MoveData fAttaqueLanceur_ = _import.getMove(_attaqueLanceur);
        String categorie_=_import.getCategory(fAttaqueLanceur_);
        for(Statistic c:creatureCible_.getStatisBoost().getKeys()){
            if(_criticalHit&&fCapac_.getMaxStatisticsIfCh().containsObj(c)){
                long boostActuel_=creatureCible_.getStatisBoost().getVal(c);
                creatureCible_.variationBoostStatistique(c, maxBoost_-boostActuel_);
                _fight.addStatisticMessage(_cible, c, maxBoost_-boostActuel_, _import);
                continue;
            }
            long var_=0;
            for (String t: typeAttaque_) {
                if(fCapac_.getMultStatIfDamgeType().contains(new StatisticType(c,t))){
                    var_+=fCapac_.getMultStatIfDamgeType().getVal(new StatisticType(c,t));
                }
            }
            if(fCapac_.getMultStatIfDamageCat().contains(new StatisticCategory(c,categorie_))){
                var_+=fCapac_.getMultStatIfDamageCat().getVal(new StatisticCategory(c,categorie_));
            }
            var_=deltaBoostStatistic(_fight, _cible,c, var_*_hitsCount,_import);
            if (var_ != 0) {
                creatureCible_.variationBoostStatistique(c, var_);
                _fight.addStatisticMessage(_cible, c, var_, _import);
            }
        }
        if(fCapac_.isChgtTypeByDamage()){
            creatureCible_.affecterTypes(typeAttaque_);
            _fight.addChangedTypesMessage(_cible, typeAttaque_, _import);
        }
    }

    static void enableFighterHavingToUseAbility(
            Fight _fight,
            TeamPosition _lanceur, TeamPosition _cible,
            AbilityData _enabledTargetAbility,
            String _attaqueLanceur, DataBase _import) {
        AbilityData capaciteActiveLanceur_ = FightAbilities.ignoredTargetAbility(_fight, _cible, _lanceur, _import);
        if (capaciteActiveLanceur_ == null) {
            return;
        }
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        if (capaciteActiveLanceur_.isTakeItemByDamagingMove() && !creatureLanceur_.possedeObjet()) {
            creatureLanceur_.backUpObject(creatureCible_.getItem());
            _fight.addSwitchItemsMessage(_lanceur, DataBase.EMPTY_STRING, creatureCible_.getItem(), _import);
            _fight.addSwitchItemsMessage(_cible, creatureCible_.getItem(), DataBase.EMPTY_STRING, _import);
            creatureCible_.useObject();
        }
        if (creatureCible_.estKo()) {
            return;
        }
        lowStatFoeHit(_fight, _lanceur, _cible, _import, creatureCible_, capaciteActiveLanceur_);
        DamagingMoveData fAttaqueLanceur_ = (DamagingMoveData) _import.getMove(_attaqueLanceur);
        if(!capaciteActiveLanceur_.getSingleStatus().events().isEmpty()&&fAttaqueLanceur_.isDirect()){
            MonteCarloString loi_=capaciteActiveLanceur_.getSingleStatus();
            _fight.getTemp().getSufferingTargetStatus().clear();
            _fight.addEffectStatus(_lanceur, _cible);
            processStatusLaw(_fight,_lanceur,_cible,loi_,capaciteActiveLanceur_.getFailStatus(),_import);
            if(!_fight.getTemp().getSufferingTargetStatus().isEmpty()){
                synchronizeStatusDamage(_fight, _lanceur, _cible, _import, creatureCible_);
            }
        }
        if (capaciteActiveLanceur_.isMumy() && fAttaqueLanceur_.isDirect() && (_enabledTargetAbility == null || !_enabledTargetAbility.isPlate())) {
            FightAbilities.disableAbility(_fight, _cible, creatureLanceur_.getCurrentAbility(), _import);
            FightAbilities.enableAbility(_fight, _cible, _import);
        }
    }

    private static void synchronizeStatusDamage(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, Fighter _creatureCible) {
        StringMap<String> fails_ = new StringMap<String>();
        AbilityData ab_ = _creatureCible.ficheCapaciteActuelle(_import);
        if (ab_ != null) {
            fails_ = ab_.getFailStatus();
        }
        StringMap<String> failsObj_ = new StringMap<String>();
        Item obj_ = FightItems.useItsObject(_fight, _cible, _import);
        if (obj_ instanceof ItemForBattle) {
            ItemForBattle o_ = (ItemForBattle) obj_;
            failsObj_.putAllMap(o_.getFailStatus());
        }
        synchronizeStatus(_fight, _lanceur, _cible,failsObj_,fails_, _import);
    }

    private static void lowStatFoeHit(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, Fighter _creatureCible, AbilityData _fCapacLanceur) {
        for (Statistic s: _fCapacLanceur.getLowStatFoeHit().getKeys()) {
            long varBase_ = _fCapacLanceur.getLowStatFoeHit().getVal(s);
            if (!FightSuccess.successChangedStatistic(_fight, _lanceur, _cible, s, varBase_, _import)) {
                continue;
            }
            long var_ = deltaBoostStatistic(_fight, _cible, s, varBase_, _import);
            _creatureCible.variationBoostStatistique(s, var_);
            _fight.addStatisticMessage(_cible, s, var_, _import);
        }
    }

    static void recoilAgainstTarget(Fight _fight,
            TeamPosition _lanceur, TeamPosition _cible,
            String _move, Difficulty _diff, DataBase _import) {
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        Fighter creatureCible_ = _fight.getFighter(_cible);
        if (creatureCible_.estKo()) {
            return;
        }
        DamagingMoveData fAttaqueLanceur_ = (DamagingMoveData) _import.getMove(_move);
        Rate degatsReculContreCible_ = degatsReculContreCible(_fight, _lanceur, _import, creatureLanceur_, creatureCible_, fAttaqueLanceur_);
        if (degatsReculContreCible_.isZero()) {
            return;
        }
        if(!creatureCible_.getClone().isZero()){
            creatureCible_.infligerDegatsClone(degatsReculContreCible_);
            _fight.addHpCloneMessage(_cible, degatsReculContreCible_, _import);
        }else{
            if (Rate.greaterEq(degatsReculContreCible_, creatureCible_.getRemainingHp())) {
                Rate remainHp_ = new Rate(creatureCible_.getRemainingHp());
                FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                    _fight.getTemp().setAcceptableChoices(false);
                    _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), remainHp_);
                    return;
                }
                _fight.getTemp().setUtilisationBaieLanceur(false);
            } else {
                Rate r_ = creatureCible_.variationLeftHp(degatsReculContreCible_.opposNb());
                _fight.addHpMessage(_cible, _import,r_);
            }
            _fight.getTemp().getDamageKo().addNb(degatsReculContreCible_);
        }
        if(_fight.getTemp().isUtilisationBaieLanceur()){
            creatureLanceur_.useObject();
        }
    }

    private static Rate degatsReculContreCible(Fight _fight, TeamPosition _lanceur, DataBase _import, Fighter _creatureLanceur, Fighter _creatureCible, DamagingMoveData _fAttaqueLanceur) {
        Rate degatsReculContreCible_=Rate.zero();
        AbilityData fCapacLanceur_= _creatureLanceur.ficheCapaciteActuelle(_import);
        if (fCapacLanceur_ != null && !fCapacLanceur_.getRecoilDamageFoe().isZero() && _fAttaqueLanceur.isDirect()) {
            degatsReculContreCible_.addNb(Rate.multiply(fCapacLanceur_.getRecoilDamageFoe(), _creatureCible.pvMax()));
        }
        Item objet_ = FightItems.useItsObject(_fight, _lanceur, _import);
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            if (!objetAttachable_.getDamageRecoil().isZero() && _fAttaqueLanceur.isDirect()) {
                degatsReculContreCible_.addNb(Rate.multiply(objetAttachable_.getDamageRecoil(), _creatureCible.pvMax()));
            }
        }
        _fight.getTemp().setUtilisationBaieLanceur(false);
        Berry baie_ = FightItems.useItsBerry(_fight, _lanceur, _import);
        if (baie_ != null && baie_.getDamageRateRecoilFoe().contains(_fAttaqueLanceur.getCategory())) {
            degatsReculContreCible_.addNb(Rate.multiply(baie_.getDamageRateRecoilFoe().getVal(_fAttaqueLanceur.getCategory()), _creatureCible.pvMax()));
            _fight.getTemp().setUtilisationBaieLanceur(true);
        }
        return degatsReculContreCible_;
    }

    static void enableBoostEffectWhileKoTarget(Fight _fight, TeamPosition _lanceur, String _move, DataBase _import) {
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        MoveData fAttaqueLanceur_ = _import.getMove(_move);
        EffectDamage eff_ = (EffectDamage) fAttaqueLanceur_.getEffet(fAttaqueLanceur_.indexOfPrimaryEffect());
        for (Statistic s: eff_.getBoostStatisOnceKoFoe().getKeys()) {
            long varBoost_ = eff_.getBoostStatisOnceKoFoe().getVal(s);
            long var_ = deltaBoostStatistic(_fight,_lanceur,s,varBoost_,_import);
            creatureLanceur_.variationBoostStatistique(s,var_);
            _fight.addStatisticMessage(_lanceur, s, var_, _import);
        }
    }
    static void enableAbilityWhileKoTarget(Fight _fight, TeamPosition _lanceur, DataBase _import) {
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        AbilityData fCapacLanceur_=creatureLanceur_.ficheCapaciteActuelle(_import);
        if (fCapacLanceur_ == null) {
            return;
        }
        for(Statistic c:creatureLanceur_.getStatisBoost().getKeys()){
            if(!fCapacLanceur_.getMultStatIfKoFoe().contains(c)){
                continue;
            }
            long varBoost_=fCapacLanceur_.getMultStatIfKoFoe().getVal(c);
            long var_=deltaBoostStatistic(_fight,_lanceur,c,varBoost_,_import);
            creatureLanceur_.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_lanceur, c, var_, _import);
        }
    }

    static void effectSwitchPosition(Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        int placeTerrainLanceur_=creatureLanceur_.getGroundPlace();
        int placeTerrainLanceurPourRemplacement_=creatureLanceur_.getGroundPlaceSubst();
        int placeTerrainCible_=creatureCible_.getGroundPlace();
        int placeTerrainCiblePourRemplacement_=creatureCible_.getGroundPlaceSubst();
        creatureCible_.setGroundPlace(placeTerrainLanceur_);
        creatureCible_.setGroundPlaceSubst(placeTerrainLanceurPourRemplacement_);
        creatureLanceur_.setGroundPlace(placeTerrainCible_);
        creatureLanceur_.setGroundPlaceSubst(placeTerrainCiblePourRemplacement_);
        _fight.addSwitchPlacesMessage(_lanceur, _cible, _import);
        if(NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER)){
            _fight.getFirstPositPlayerFighters().put(_lanceur.getPosition(),placeTerrainCiblePourRemplacement_);
        } else {
            _fight.getFirstPositFoeFighters().put(_lanceur.getPosition(),placeTerrainCiblePourRemplacement_);
        }
        if(NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER)){
            _fight.getFirstPositPlayerFighters().put(_cible.getPosition(),placeTerrainLanceurPourRemplacement_);
        } else {
            _fight.getFirstPositFoeFighters().put(_cible.getPosition(),placeTerrainLanceurPourRemplacement_);
        }
    }

    static void effectTeam(Fight _fight,
            TeamPosition _combattant,EffectTeam _effet,String _attaque,DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_combattant.getTeam()));
        effectTeamEnabled(_fight, _combattant, _effet, _import, equipeAdv_);
        effectTeamSending(_fight, _combattant, _effet, _import, equipeAdv_);
        effectTeamNbUses(_fight, _combattant, _effet, _import, equipeAdv_);
        effectTeamFighter(_fight, _combattant, _effet, _import, equipeAdv_);
        //activer attaque
        equipe_.activerEffetEquipe(_attaque);
        _fight.addEnabledTeamMoveMessage(_combattant.getTeam(), _attaque, _import);
        effectTeamProtectStatus(_fight, _combattant, _effet, _import, equipe_);
        long baseBoost_=_import.getDefaultBoost();
        for(Statistic c:_effet.getProtectAgainstLowStat()){
            for(int c2_:equipe_.getMembers().getKeys()){
                Fighter creature_=equipe_.refPartMembres(c2_);
                long boost_=creature_.getStatisBoost().getVal(c);
                if(boost_<baseBoost_){
                    creature_.variationBoostStatistique(c, baseBoost_-boost_);
                    _fight.addStatisticMessage(new TeamPosition(_combattant.getTeam(), c2_), c, baseBoost_-boost_, _import);
                }
            }
        }
        for(Statistic c:_effet.getCancelChgtStatFoeTeam()){
            for(int c2_:equipeAdv_.getMembers().getKeys()){
                Fighter creature_=equipeAdv_.refPartMembres(c2_);
                long boost_=creature_.getStatisBoost().getVal(c);
                creature_.variationBoostStatistique(c, baseBoost_-boost_);
                _fight.addStatisticMessage(new TeamPosition(Fight.foe(_combattant.getTeam()), c2_), c, baseBoost_-boost_, _import);
            }
        }
    }

    private static void effectTeamProtectStatus(Fight _fight, TeamPosition _combattant, EffectTeam _effet, DataBase _import, Team _equipe) {
        for(String c: _effet.getProtectAgainstStatus()){
            for(int c2_: _equipe.getMembers().getKeys()){
                Fighter creature_= _equipe.refPartMembres(c2_);
                if(creature_.isSingleStatus(c)){
                    creature_.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, new TeamPosition(_combattant.getTeam(), c2_), _import);
                }else{
                    creature_.supprimerPseudoStatut(c);
                    _fight.addDisabledStatusOtherRelMessage(c, new TeamPosition(_combattant.getTeam(), c2_), _import);
                }
            }
        }
    }

    private static void effectTeamFighter(Fight _fight, TeamPosition _combattant, EffectTeam _effet, DataBase _import, Team _equipeAdv) {
        Fighter fighter_ = _fight.getFighter(_combattant);
        for (MoveTeamPosition k: fighter_.getStatusRelatSet()) {
            if (!StringUtil.contains(_effet.getDisableFoeTeamStatus(), k.getMove())) {
                continue;
            }
            fighter_.supprimerPseudoStatut(k.getMove());
            _fight.addDisabledStatusOtherRelMessage(k.getMove(), _combattant, _import);
        }
        for (MoveTeamPosition k: fighter_.getTrappingMoves().getKeys()) {
            if (!StringUtil.contains(_effet.getDisableFoeTeamEffects(), k.getMove())) {
                continue;
            }
            for (int f: _equipeAdv.getMembers().getKeys()) {
                Fighter f_ = _equipeAdv.getMembers().getVal(f);
                f_.getTrappingMoves().getVal(new MoveTeamPosition(k.getMove(), _combattant)).keepEnabled(false);
                _fight.addDisabledMoveRelMessage(_combattant, k.getMove(), new TeamPosition(Fight.foe(_combattant.getTeam()), f), _import);
            }
        }
    }

    private static void effectTeamNbUses(Fight _fight, TeamPosition _combattant, EffectTeam _effet, DataBase _import, Team _equipeAdv) {
        for (String c: _equipeAdv.getNbUsesMoves().getKeys()) {
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //disable team foe moves sending
                _equipeAdv.getNbUsesMoves().put(c, 0L);
                _fight.addDisabledTeamUsesMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
    }

    private static void effectTeamSending(Fight _fight, TeamPosition _combattant, EffectTeam _effet, DataBase _import, Team _equipeAdv) {
        for(String c: _equipeAdv.getEnabledMovesWhileSendingFoe().getKeys()){
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //disable team foe moves sending
                _equipeAdv.supprimerEffetEquipeEntreeAdv(c);
                _fight.addDisabledTeamUsesMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
    }

    private static void effectTeamEnabled(Fight _fight, TeamPosition _combattant, EffectTeam _effet, DataBase _import, Team _equipeAdv) {
        for(String c: _equipeAdv.getEnabledMoves().getKeys()){
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //desactiver attaques adv
                _equipeAdv.desactiverEffetEquipe(c);
                _fight.addDisabledTeamMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
    }

    static void effectEndRound(Fight _fight,
            TeamPosition _finalThrower,TeamPosition _finalTarget,
            String _move, Effect _effect, DataBase _import) {
        EffectEndRound effetLoc_=(EffectEndRound)_effect;
        Fighter creatureCible_=_fight.getFighter(_finalTarget);
        Fighter creatureLanceur_=_fight.getFighter(_finalThrower);
        if(effetLoc_ instanceof EffectEndRoundPositionRelation){
            Team equipeLanceur_=_fight.getTeams().getVal(_finalThrower.getTeam());
            StacksOfUses soinApres_=equipeLanceur_.getHealAfterVal(_move, creatureLanceur_.getGroundPlace());
            soinApres_.setNbRounds(0);
            soinApres_.setLastStacked(soinApres_.isFirstStacked());
            soinApres_.setFirstStacked(true);
        }
        if(effetLoc_ instanceof EffectEndRoundSingleRelation){
            ActivityOfMove actifNbTour_=creatureLanceur_.getTrappingMoves().getVal(new MoveTeamPosition(_move,_finalTarget));
            AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
            if (fCapac_ != null && fCapac_.isImmuDamageTrappingMoves()) {
                return;
            }
            if (!effetLoc_.getFail().isEmpty()) {
                StringMap<String> values_;
                values_ = FightValues.calculateValues(_fight,_finalThrower,_finalTarget,_import);
                if (_import.evaluateBoolean(effetLoc_.getFail(), values_, false)) {
                    return;
                }
            }
            actifNbTour_.enableReset();
            _fight.addEnabledMoveRelMessage(_finalTarget, _move, _finalThrower, _import);
        }
        if (!creatureLanceur_.getEnabledMovesEndRound().contains(_move)) {
            return;
        }
        creatureLanceur_.activerAttaqueFinTourIndividuel(_move);
        _fight.addEnabledMoveMessage(_finalThrower, _move, _import);
    }

    static void effectGlobal(Fight _fight, EffectGlobal _effet,String _nomAttaque,DataBase _import){
        if(_effet.getCanceledIfUsed()){
            _fight.getEnabledMoves().getVal(_nomAttaque).setEnabled(!_fight.getEnabledMoves().getVal(_nomAttaque).isEnabled());
            if(!_fight.getEnabledMoves().getVal(_nomAttaque).isEnabled()){
                _fight.getEnabledMoves().getVal(_nomAttaque).reset();
                _fight.addDisabledWeatherMessage(_nomAttaque, _import);
            }
        }else{
            _fight.getEnabledMoves().getVal(_nomAttaque).enableReset();
            _fight.addEnabledWeatherMessage(_nomAttaque, _import);
        }
        if(!_fight.getEnabledMoves().getVal(_nomAttaque).isEnabled()){
            return;
        }
        for(String e:_effet.getCancelEffects()){
            _fight.getEnabledMoves().getVal(e).disable();
            _fight.getEnabledMoves().getVal(e).reset();
            _fight.addDisabledWeatherMessage(e, _import);
            if (_fight.getStillEnabledMoves().contains(e)) {
                _fight.getStillEnabledMoves().put(e, BoolVal.FALSE);
            }
        }
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            FightAbilities.enableAbilityByWeather(_fight,c, _import);
        }
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            disableStatus(_fight, c, _import);
        }
        long base_ = _import.getDefaultBoost();
        for (Statistic s: _effet.getCancelChgtStat()) {
            for(TeamPosition c:FightOrder.frontFighters(_fight)){
                Fighter creature_= _fight.getFighter(c);
                long boost_=creature_.getStatisBoost().getVal(s);
                creature_.variationBoostStatistique(s, base_ - boost_);
                _fight.addStatisticMessage(c, s, base_ - boost_, _import);
            }
        }
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            Fighter creature_ = _fight.getFighter(c);
            StringUtil.removeAllElements(creature_.getProtectedAgainstMoveTypes(), _effet.getDisableImmuAgainstTypes());
        }
    }

    static void disableStatus(Fight _fight, TeamPosition _fighter, DataBase _import) {
        for(String e: FightSuccess.forbiddenStatus(_fight, _import)){
            Fighter creature_= _fight.getFighter(_fighter);
            if(creature_.isSingleStatus(e)){
                creature_.supprimerStatut(e);
                _fight.addDisabledStatusMessage(e, _fighter, _import);
            }else{
                creature_.supprimerPseudoStatut(e);
                _fight.addDisabledStatusOtherRelMessage(e, _fighter, _import);
            }
        }
    }

    static void effectBatonPass(Fight _fight,
            TeamPosition _user,
            Difficulty _diff, DataBase _import) {
        Fighter creatureLanceur_=_fight.getFighter(_user);
        Team equipeLanceur_=_fight.getTeams().getVal(_user.getTeam());
        int remplacant_=creatureLanceur_.getSubstistute();
        if (NumberUtil.eq(remplacant_, Fighter.BACK)) {
            return;
        }
        Fighter partenaire_=equipeLanceur_.refPartMembres(remplacant_);
        partenaire_.effectBatonPass(creatureLanceur_);
        int placeTerrain_=creatureLanceur_.getGroundPlace();
        int placeTerrainPourRemplacement_=creatureLanceur_.getGroundPlaceSubst();
        partenaire_.setGroundPlace(placeTerrain_);
        partenaire_.setGroundPlaceSubst(placeTerrainPourRemplacement_);
        creatureLanceur_.exitFrontBattle();
        creatureLanceur_.exitFrontBattleForBeingSubstitued();
        _fight.addBatonPassMessage(_user, new TeamPosition(_user.getTeam(),remplacant_), _import);
        FightSending.withdrawal(_fight,_user,_import);
        FightSending.sending(_fight,new TeamPosition(_user.getTeam(),remplacant_),_diff,_import);
        if(NumberUtil.eq(_user.getTeam(), Fight.CST_PLAYER)){
            _fight.getFirstPositPlayerFighters().put(_user.getPosition(),Fighter.BACK);
            _fight.getFirstPositPlayerFighters().put(remplacant_,placeTerrainPourRemplacement_);
        } else {
            _fight.getFirstPositFoeFighters().put(_user.getPosition(),Fighter.BACK);
            _fight.getFirstPositFoeFighters().put(remplacant_,placeTerrainPourRemplacement_);
        }
        AnimationSwitch animation_;
        animation_ = new AnimationSwitch();
        animation_.setIndex(_fight.getEffects().size());
        animation_.setSubstituted(new TargetCoords(_user.getTeam(), placeTerrainPourRemplacement_));
        animation_.setSubstituteName(partenaire_.getName());
        animation_.setKo(partenaire_.estKo());
        animation_.setLevel(partenaire_.getLevel());
        animation_.setRateRemainHp(partenaire_.rateRemainHp());
        animation_.setWonExpRate(partenaire_.wonExpRate(_import));
        _fight.getEffects().add(animation_);
    }

    static void effectStatisticRandom(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatistic _effet,
                                      IdList<Statistic> _statistiques, DataBase _import, Rate _rate){
        if (!randomRate(_fight,_import, _rate, _lanceur)) {
            return;
        }
        effectStatistic(_fight,_lanceur, _cible, _effet, _statistiques, _import);
    }
    static boolean randomRate(Fight _fight, DataBase _import, Rate _rate, TeamPosition _thrower) {
        if (_fight.getTemp().getSimulation() && NumberUtil.eq(_thrower.getTeam(), Fight.CST_PLAYER) && Rate.strLower(_rate, DataBase.determinatedRate())) {
            _fight.getTemp().setAcceptableChoices(false);
            _fight.getTemp().setIssue(IssueSimulation.RANDOM);
            _fight.addIssueRandomMessage(_import);
            return false;
        }
        return FightSuccess.tirage(_import, _rate, _fight.getTemp().getEvts());
    }

    static void effectStatistic(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,IdList<Statistic> _statistiques,DataBase _import){
        IdMap<Statistic,Long> varStatisCran_=_effet.getStatisVarRank();
        Fighter creatureCible_= _fight.getFighter(_cible);
        MonteCarloEnum<Statistic> loi_ = lawBoost(_effet, _statistiques);
        if (!loi_.events().isEmpty() && !FightSuccess.isBadSimulation(_fight, loi_)) {
//            for (Statistic e:_effet.getLawBoost().events()) {
//                if (Statistic.containsStatistic(_statistiques,e)) {
//                    loi_.addQuickEvent(e,_effet.getLawBoost().rate(e));
//                }
//            }
            Statistic statistique_ = FightSuccess.random(_import, loi_,_fight.getTemp().getEvts());
            long delta_ = deltaBoostStatistic(_fight, _cible, statistique_, varStatisCran_.getVal(statistique_), _import);
            creatureCible_.variationBoostStatistique(statistique_, delta_);
            _fight.addAnimationStatistic(statistique_, delta_, false);
            _fight.addStatisticMessage(_cible, statistique_, delta_, _import);
        }
        if(!varStatisCran_.isEmpty()){
            IdMap<Statistic,Long> vars_ = new IdMap<Statistic,Long>();
            for (EntryCust<Statistic,Long> e: varStatisCran_.entryList()) {
                if (Statistic.containsStatistic(_statistiques,e.getKey())) {
                    vars_.put(e.getKey(), e.getValue());
                }
            }
            vars_ = deltaBoostStatisticMap(_fight, _cible, vars_, _import);
            for(Statistic e:vars_.getKeys()){
                creatureCible_.variationBoostStatistique(e,vars_.getVal(e));
                _fight.addAnimationStatistic(e, vars_.getVal(e), false);
                _fight.addStatisticMessage(_cible, e, vars_.getVal(e), _import);
            }
            Berry berry_ = FightItems.useItsBerry(_fight, _cible, _import);
            if (berry_ != null) {
                FightItems.enableBerryStatistic(_fight,_cible, true, true, _import, berry_);
            }
        }
        otherChanges(_fight, _lanceur, _cible, _effet, _statistiques, _import, creatureCible_);
    }

    private static void otherChanges(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatistic _effet, IdList<Statistic> _statistiques, DataBase _import, Fighter _creatureCible) {
        if(!_effet.getCopyBoost().isEmpty()){
            //copieBoost
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            for(Statistic e:_statistiques){
                long boostLanceur_=creatureLanceur_.getStatisBoost().getVal(e);
                long boostCible_= _creatureCible.getStatisBoost().getVal(e);
                long delta_= boostCible_ - boostLanceur_;
                creatureLanceur_.variationBoostStatistique(e,delta_);
                _fight.addAnimationStatistic(e, delta_, false);
                _fight.addStatisticMessage(_lanceur, e, delta_, _import);
            }
        }
        if(!_effet.getCancelLowStat().isEmpty()||!_effet.getCancelChgtStat().isEmpty()){
            for(Statistic e:_statistiques){
                long boost_= _creatureCible.getStatisBoost().getVal(e);
                _creatureCible.variationBoostStatistique(e,-boost_);
                _fight.addAnimationStatistic(e, -boost_, false);
                _fight.addStatisticMessage(_cible, e, -boost_, _import);
            }
        }
        if(!_effet.getSwapBoostStatis().isEmpty()){
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            for(Statistic e:_statistiques){
                long boostLanceur_=creatureLanceur_.getStatisBoost().getVal(e);
                long boostCible_= _creatureCible.getStatisBoost().getVal(e);
                long varCible_= boostLanceur_-boostCible_;
                long varLanceur_= boostCible_-boostLanceur_;
                _creatureCible.variationBoostStatistique(e,varCible_);
                _fight.addStatisticMessage(_cible, e, varCible_, _import);
                creatureLanceur_.variationBoostStatistique(e,varLanceur_);
                _fight.addStatisticMessage(_lanceur, e, varLanceur_, _import);
                _fight.addAnimationStatistic(e, IndexConstants.FIRST_INDEX, true);
            }
        }
    }

    private static MonteCarloEnum<Statistic> lawBoost(EffectStatistic _effet, IdList<Statistic> _statistiques) {
        MonteCarloEnum<Statistic> loi_ = new MonteCarloEnum<Statistic>();
        int nbEvts_ = _effet.getLawBoost().nbEvents();
        for (int i = 0; i < nbEvts_; i++) {
            Statistic e_ = _effet.getLawBoost().getEvent(i);
            if (Statistic.containsStatistic(_statistiques,e_)) {
                loi_.addQuickEvent(e_, _effet.getLawBoost().getFreq(i));
            }
        }
        return loi_;
    }

    static void effectStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatus _effet,Difficulty _diff,DataBase _import){
        sufferingTargetStatus(_fight, _lanceur, _cible, _effet, _import);
        if(_effet.getKoUserHealSubst()){
            statusKoUserHealSubst(_fight, _lanceur, _diff, _import);
            return;
        }
        defaultStatus(_fight, _lanceur, _cible, _effet, _import);
    }

    private static void sufferingTargetStatus(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatus _effet, DataBase _import) {
        _fight.getTemp().getSufferingTargetStatus().clear();
        if(_effet.getStatusFromUser()){
            StringList statutsTranferes_=new StringList();
            Fighter creatureCible_= _fight.getFighter(_cible);
            Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
            for(String c:creatureCbtLanceur_.getStatusSet()){
                if(FightSuccess.successfulAffectedStatus(_fight, _lanceur, _cible,c, _import)){
                    statutsTranferes_.add(c);
                }
            }
            _fight.getTemp().getSufferingTargetStatus().addAllElts(statutsTranferes_);
            for(String e:statutsTranferes_){
                creatureCible_.affecterStatut(e);
                _fight.addStatusMessage(_cible, e, _import);
                creatureCbtLanceur_.supprimerStatut(e);
                _fight.addDisabledStatusMessage(e, _lanceur, _import);
            }
        }
        if(!_effet.getDeletedStatus().isEmpty()){
            Fighter creatureCible_= _fight.getFighter(_cible);
            for(String e: _effet.getDeletedStatus()){
                if(creatureCible_.isSingleStatus(e)){
                    creatureCible_.supprimerStatut(e);
                    _fight.addDisabledStatusMessage(e, _cible, _import);
                }else{
                    creatureCible_.supprimerPseudoStatut(e);
                    _fight.addDisabledStatusOtherRelMessage(e, _cible, _import);
                }
                _fight.getTemp().getSufferingTargetStatus().removeString(e);
            }
        }
    }

    private static void defaultStatus(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, EffectStatus _effet, DataBase _import) {
        MonteCarloString loiStatuts_=_effet.getLawStatus();
        StringMap<String> echecStatuts_=_effet.getLocalFailStatus();
        if(!loiStatuts_.events().isEmpty()){
            processStatusLaw(_fight,_lanceur,_cible,loiStatuts_,echecStatuts_,_import);
        }
        if(_fight.getTemp().getSufferingTargetStatus().isEmpty()){
            return;
        }
        synchronizeStatus(_fight,_lanceur,_cible,echecStatuts_,echecStatuts_,_import);
    }

    private static void statusKoUserHealSubst(Fight _fight, TeamPosition _lanceur, Difficulty _diff, DataBase _import) {
        Team equipeLanceur_= _fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        if (NumberUtil.eq(creatureCbtLanceur_.getSubstistute(), Fighter.BACK)) {
            return;
        }
        int place_ = creatureCbtLanceur_.getGroundPlace();
        int placeSub_ = creatureCbtLanceur_.getGroundPlaceSubst();
        TeamPosition remplacant_=new TeamPosition(_lanceur.getTeam(),creatureCbtLanceur_.getSubstistute());
        FightKo.setKo(_fight, _lanceur, _diff, _import);
        _fight.addAnimationKoFighter(_lanceur);
        creatureCbtLanceur_.exitFrontBattleForBeingSubstitued();
        if(NumberUtil.eq(_lanceur.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
            _fight.getTemp().setAcceptableChoices(false);
            _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            return;
        }
        //A not knocked out substitute must be selected before using the move
        //the substitute is not knocked out while sent
        //==> it is impossible that the fight is finished
        _fight.getTemp().setFullHealing(true);
        Fighter creatureCbtRemplacant_=equipeLanceur_.refPartMembres(remplacant_.getPosition());
        creatureCbtRemplacant_.setGroundPlace(place_);
        creatureCbtRemplacant_.setGroundPlaceSubst(placeSub_);
        FightSending.sending(_fight,remplacant_, _diff, _import);
        //soin du remplacant pv,pp,statuts
        creatureCbtRemplacant_.fullHeal();
        creatureCbtRemplacant_.fullHealMessage(_import,_fight.getTemp());
        creatureCbtRemplacant_.setGroundPlace(place_);
        creatureCbtRemplacant_.setGroundPlaceSubst(placeSub_);
        if(NumberUtil.eq(_lanceur.getTeam(), Fight.CST_PLAYER)){
            _fight.getFirstPositPlayerFighters().put(_lanceur.getPosition(),Fighter.BACK);
            _fight.getFirstPositPlayerFighters().put(remplacant_.getPosition(),placeSub_);
        } else {
            _fight.getFirstPositFoeFighters().put(_lanceur.getPosition(),Fighter.BACK);
            _fight.getFirstPositFoeFighters().put(remplacant_.getPosition(),placeSub_);
        }
        AnimationSwitch animation_;
        animation_ = new AnimationSwitch();
        animation_.setIndex(_fight.getEffects().size());
        animation_.setSubstituted(new TargetCoords(_lanceur.getTeam(), placeSub_));
        animation_.setSubstituteName(creatureCbtRemplacant_.getName());
        animation_.setLevel(creatureCbtRemplacant_.getLevel());
        animation_.setRateRemainHp(creatureCbtRemplacant_.rateRemainHp());
        animation_.setWonExpRate(creatureCbtRemplacant_.wonExpRate(_import));
        _fight.getEffects().add(animation_);
        _fight.getTemp().setFullHealing(false);
    }

    static void effectDamageRate(Fight _fight,TeamPosition _cible,EffectDamageRate _effet,Difficulty _diff,DataBase _import){
        Rate varPv_=Rate.zero();
        Fighter creatureCible_=_fight.getFighter(_cible);
        if(_effet.getRateDamage().isZeroOrGt()){
            if(!FightKo.canBeHealed(_fight,_cible.getTeam(),_import)){
                return;
            }
            varPv_.affect(varPvPositive(_fight, _cible, _effet, _import));
        }else{
            AbilityData ab_ = creatureCible_.ficheCapaciteActuelle(_import);
            if (ab_ != null && ab_.isImmuDamageRecoil()) {
                return;
            }
            varPv_.affect(varPvNegative(_fight, _effet));
        }
        if(varPv_.isZeroOrGt()){
            Rate r_ = creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import,r_);
        }else if(Rate.greaterEq(varPv_.absNb(),creatureCible_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            _fight.addEffectRecoil(_cible, _effet);
            _fight.setAnimationKoFighterEffectDamage(_cible, _effet);
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                _fight.getTemp().setAcceptableChoices(false);
                _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            _fight.addEffectRecoil(_cible);
            Rate r_ = creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import,r_);
        }
    }

    private static Rate varPvNegative(Fight _fight, EffectDamageRate _effet) {
        Rate coeff_=DataBase.defRateProduct();
        coeff_.multiplyBy(_effet.getRateDamage());
        Rate somme_=Rate.zero();
        for(TeamPosition c: _fight.getTemp().getDamageByCurrentUser().getKeys()){
            somme_.addNb(_fight.getTemp().getDamageByCurrentUser().getVal(c));
        }
        return Rate.multiply(somme_, coeff_);
    }

    private static Rate varPvPositive(Fight _fight, TeamPosition _cible, EffectDamageRate _effet, DataBase _import) {
        Rate coeff_=DataBase.defRateProduct();
        Item objet_ = FightItems.useItsObject(_fight, _cible, _import);
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            if (!objetAttachable_.getMultDrainedHp().isZero()) {
                coeff_.multiplyBy(objetAttachable_.getMultDrainedHp());
            }
        }
        coeff_.multiplyBy(_effet.getRateDamage());
        Rate somme_=Rate.zero();
        for(TeamPosition c: _fight.getTemp().getDamageByCurrentUser().getKeys()){
            if(canReverseAbsorb(_fight, _cible, c, _import)){
                somme_.removeNb(_fight.getTemp().getDamageByCurrentUser().getVal(c));
                continue;
            }
            somme_.addNb(_fight.getTemp().getDamageByCurrentUser().getVal(c));
            _fight.addEffectAbsorb(c, _cible);
        }
        return Rate.multiply(somme_, coeff_);
    }

    static void effectMultiplyUsedMovePower(Fight _fight, TeamPosition _cible, EffectMultMovePower _effet, String _move, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
        for(String c:_effet.getMultMovePowerFctType().getKeys()){
            creatureCible_.getDamageRateInflictedByType().getVal(c).multiplyBy(_effet.getMultMovePowerFctType().getVal(c));
        }
    }

    static void effectMultiplyUndergoneMovePower(Fight _fight,TeamPosition _cible,EffectMultMovePower _effet, String _move, DataBase _import){
        Fighter creatureCible_ = _fight.getFighter(_cible);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
        for(String c:_effet.getMultMovePowerFctType().getKeys()){
            creatureCible_.getDamageRateSufferedByType().getVal(c).multiplyBy(_effet.getMultMovePowerFctType().getVal(c));
        }
    }


    static void effectFullHpRate(Fight _fight,TeamPosition _cible,EffectFullHpRate _effet,Difficulty _diff,DataBase _import){
        Rate coeff_=Rate.zero();
        Rate varPv_=Rate.zero();
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter creatureCible_=_fight.getFighter(_cible);
        if(!_effet.getRestoredHp().isEmpty()){
            StringMap<String> values_;
            values_ = FightValues.calculateValues(_fight,_cible,_cible,_import);
            coeff_.addNb(_import.evaluatePositiveExp(_effet.getRestoredHp(), values_, DataBase.getDefaultHealRate()));
        }
        if(!_effet.getLeftUserHp().isZero()){
            coeff_.addNb(_effet.getLeftUserHp().opposNb());
        }
        varPv_.affect(Rate.multiply(creatureCible_.pvMax(),coeff_));
        if(varPv_.isZeroOrGt()){
            Rate r_ = creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import,r_);
            _fight.getEffects().removeLast();
            if (!varPv_.isZero()) {
                AnimationHealing animationHeal_;
                animationHeal_ = new AnimationHealing();
                animationHeal_.setIndex(_fight.getEffects().size());
                animationHeal_.setHealed(new TargetCoords(_cible.getTeam(), creatureCible_.getGroundPlaceSubst()));
                _fight.getEffects().add(animationHeal_);
            }
        }else if(Rate.greaterEq(varPv_.absNb(),creatureCible_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            _fight.addAnimationKoFighter(_cible);
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                _fight.getTemp().setAcceptableChoices(false);
                _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                return;
            }
        }else{
            Rate r_ = creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import,r_);
            _fight.getEffects().removeLast();
            _fight.addEffectRecoil(_cible);
        }
        //ex: REBONDIFEU
        effectFullHpRateClosest(_fight, _cible, _effet, _diff, _import, equipeCible_);
    }

    private static void effectFullHpRateClosest(Fight _fight, TeamPosition _cible, EffectFullHpRate _effet, Difficulty _diff, DataBase _import, Team _equipeCible) {
        Rate varPvAdj_=_effet.getClosestFoeDamageRateHp();
        for(TeamPosition c:FightOrder.closestFigthersSameTeam(_fight,_cible,_diff)){
            Fighter partenaire_= _equipeCible.refPartMembres(c.getPosition());
            Rate varPvMembresAdj_=Rate.multiply(varPvAdj_,partenaire_.pvMax());
            if (varPvMembresAdj_.isZero()) {
                continue;
            }
            if(Rate.strGreater(varPvMembresAdj_, partenaire_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,c,_diff,_import);
                _fight.addAnimationKoFighter(c);
                if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                    _fight.getTemp().setAcceptableChoices(false);
                    _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
            }else{
                Rate r_ = partenaire_.variationLeftHp(varPvMembresAdj_.opposNb());
                _fight.addEffectRecoil(c);
                _fight.addHpMessage(c, _import,r_);
            }
        }
    }

    static void effectLeftHpRate(Fight _fight,TeamPosition _target,EffectRemainedHpRate _effet,Difficulty _diff,DataBase _import){
        Rate coeff_=_effet.getRateHp();
        Fighter creatureLanceur_=_fight.getFighter(_target);
        Rate varPv_=Rate.multiply(creatureLanceur_.getRemainingHp(),coeff_);
        if(varPv_.isZeroOrGt()){
            Rate r_ = creatureLanceur_.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import,r_);
            _fight.getEffects().removeLast();
            AnimationHealing animationHeal_;
            animationHeal_ = new AnimationHealing();
            animationHeal_.setIndex(_fight.getEffects().size());
            animationHeal_.setHealed(new TargetCoords(_target.getTeam(), creatureLanceur_.getGroundPlaceSubst()));
            _fight.getEffects().add(animationHeal_);
        }else if(Rate.greaterEq(varPv_.absNb(),creatureLanceur_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_target,_diff,_import);
            _fight.addAnimationKoFighter(_target);
            if(NumberUtil.eq(_target.getTeam(),Fight.CST_PLAYER)&& _fight.getTemp().getSimulation()){
                _fight.getTemp().setAcceptableChoices(false);
                _fight.getTemp().setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            Rate r_ = creatureLanceur_.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import,r_);
            _fight.addEffectRecoil(_target);
        }
    }

    static void effectVarPp(Fight _fight,TeamPosition _cible,EffectVarPP _effet,Difficulty _diff, DataBase _import){
        //derniere attaque de la cible
        Fighter creatureCible_=_fight.getFighter(_cible);
        String attaque_=creatureCible_.getFirstChosenMove();
        long var_=_effet.getDeletePp();
        creatureCible_.usePowerPointsByMove(_diff,attaque_,var_);
        _fight.addVarPpEffectMessage(attaque_, _cible, var_, _import);
        if (FightItems.canUseItsBerry(_fight,_cible, _import)) {
            FightItems.enableBerryPp(_fight,_cible, creatureCible_.getItem(), true, _import);
        }
    }

    static void processStatusLaw(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,
                                 MonteCarloString _statuts,StringMap<String> _echecStatuts,DataBase _import){
        MonteCarloString loiGeneree_ = generatedStatusLaw(_fight, _lanceur, _cible, _statuts, _echecStatuts, _import);
        if (FightSuccess.isBadSimulation(_fight, loiGeneree_)) {
            return;
        }
        String statut_=FightSuccess.random(_import, loiGeneree_, _fight.getTemp().getEvts());
        if(!statut_.isEmpty()){
            setStatus(_fight, _lanceur, _cible, statut_, _import);
            _fight.setAnimationStatus(statut_);
        }
    }

    static MonteCarloString generatedStatusLaw(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,
                                               MonteCarloString _statuts,StringMap<String> _echecStatuts,DataBase _import) {
        StringMap<LgInt> loiTmp_=new StringMap<LgInt>();
        MonteCarloString loiGeneree_ = new MonteCarloString();
        int nb_ = _statuts.nbEvents();
        for (int i = 0; i < nb_; i++) {
            String e_ = _statuts.getEvent(i);
            if(e_.isEmpty() || _echecStatuts.contains(e_) && !_echecStatuts.getVal(e_).isEmpty() && _import.evaluateBoolean(_echecStatuts.getVal(e_), FightValues.calculateValues(_fight, _lanceur, _cible, _import), false) || !FightSuccess.successfulAffectedStatus(_fight, _lanceur, _cible, e_, _import)){
                LgInt proba_ = rateNoStatus(loiTmp_);
                proba_.addNb(_statuts.getFreq(i));
                loiTmp_.put(DataBase.EMPTY_STRING,proba_);
            } else {
                loiTmp_.put(e_,new LgInt(_statuts.getFreq(i)));
            }
        }
//        for(String c:_statuts.events()){
//            if(c.isEmpty()){
//                LgInt proba_ = rateNoStatus(loiTmp_);
//                proba_.addNb(_statuts.rate(c));
//                loiTmp_.put(DataBase.EMPTY_STRING,proba_);
//                continue;
//            }
//            if(_echecStatuts.contains(c) && !_echecStatuts.getVal(c).isEmpty()){
//                StringMap<String> values_ = FightValues.calculateValues(_fight,_lanceur,_cible,_import);
//                if (_import.evaluateBoolean(_echecStatuts.getVal(c), values_, false)) {
//                    LgInt proba_ = rateNoStatus(loiTmp_);
//                    proba_.addNb(_statuts.rate(c));
//                    loiTmp_.put(DataBase.EMPTY_STRING,proba_);
//                    continue;
//                }
//            }
//            if(FightSuccess.successfulAffectedStatus(_fight,_lanceur,_cible,c,_import)){
//                loiTmp_.put(c,new LgInt(_statuts.rate(c)));
//            }else{
//                LgInt proba_ = rateNoStatus(loiTmp_);
//                proba_.addNb(_statuts.rate(c));
//                loiTmp_.put(DataBase.EMPTY_STRING,proba_);
//            }
//        }
        for(String c:loiTmp_.getKeys()){
            loiGeneree_.addQuickEvent(c,loiTmp_.getVal(c));
        }
        return FightSuccess.probaEffectStatus(_fight,_lanceur,loiGeneree_,_import);
    }

    static LgInt rateNoStatus(StringMap<LgInt> _law) {
        if(_law.contains(DataBase.EMPTY_STRING)){
            return _law.getVal(DataBase.EMPTY_STRING);
        }
        return LgInt.zero();
    }

    static void setStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,
            String _statut, DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_cible);
        if(creatureCible_.isSingleStatus(_statut)){
            if (NumberUtil.eq(creatureCible_.getStatusNbRoundShort(_statut), 0)) {
                _fight.getTemp().getSufferingTargetStatus().add(_statut);
            }
            creatureCible_.affecterStatut(_statut);
            _fight.addStatusMessage(_cible, _statut, _import);
        }else{
            if (NumberUtil.eq(creatureCible_.getStatusRelatNbRoundShort(new MoveTeamPosition(_statut, _lanceur)), 0)) {
                _fight.getTemp().getSufferingTargetStatus().add(_statut);
            }
            creatureCible_.affecterPseudoStatut(_lanceur,_statut);
            _fight.addStatusRelMessage(_cible, _statut, _lanceur, _import);
        }
        setStatusAbility(_fight, _cible, _import, creatureCible_);
        Berry berry_ = FightItems.useItsBerry(_fight, _cible, _import);
        if (berry_ != null) {
            FightItems.enableBerryStatus(_fight,_cible, true, _import, berry_);
        }
    }

    private static void setStatusAbility(Fight _fight, TeamPosition _cible, DataBase _import, Fighter _creatureCible) {
        AbilityData fCapacCible_= _creatureCible.ficheCapaciteActuelle(_import);
        if (fCapacCible_ != null) {
            for(String e: _fight.getTemp().getSufferingTargetStatus()){
                for(Statistic c: _creatureCible.getStatisBoost().getKeys()){
                    if(fCapacCible_.getMultStatIfStatutRank().contains(new StatisticStatus(c,e))){
                        long varBoost_=fCapacCible_.getMultStatIfStatutRank().getVal(new StatisticStatus(c,e));
                        long var_=deltaBoostStatistic(_fight,_cible,c,varBoost_,_import);
                        _creatureCible.variationBoostStatistique(c,var_);
                        _fight.addStatisticMessage(_cible, c, var_, _import);
                    }
                }
            }
        }
    }

    static void synchronizeStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,StringMap<String> _echecStatuts,StringMap<String> _echecStatutsAb,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        for(String e: _fight.getTemp().getSufferingTargetStatus()){
            Status statutLoc_=_import.getStatus().getVal(e);
            if (!creatureCbtLanceur_.isStatusRelat(new MoveTeamPosition(e, _cible)) || !statutLoc_.estActifPartenaire() || !NumberUtil.eq(_lanceur.getTeam(), _cible.getTeam())) {
                continue;
            }
            EffectPartnerStatus effet_=statutLoc_.getEffectsPartner().first();
            if(effet_.getWeddingAlly()){
                creatureCbtLanceur_.affecterPseudoStatut(_cible,e);
                _fight.addStatusRelMessage(_lanceur, e, _cible, _import);
            }
        }
        synchronizeStatusIteam(_fight, _lanceur, _cible, _echecStatuts, _import);
        synchronizeStatusAbility(_fight, _lanceur, _cible, _echecStatutsAb, _import);
    }

    private static void synchronizeStatusIteam(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, StringMap<String> _echecStatuts, DataBase _import) {
        Item obj_ = FightItems.useItsObject(_fight, _cible, _import);
        if (obj_ instanceof ItemForBattle) {
            ItemForBattle o_ = (ItemForBattle) obj_;
            for (String e : _fight.getTemp().getSufferingTargetStatus()) {
                if (!StringUtil.contains(o_.getSynchroStatus(), e)) {
                    continue;
                }
                affectStatusToThrower(_fight, _lanceur, e, _cible, _echecStatuts, _import);
            }
        }
    }

    private static void synchronizeStatusAbility(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, StringMap<String> _echecStatutsAb, DataBase _import) {
        AbilityData fCapacCible_ = FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        if(fCapacCible_ == null){
            return;
        }
        //capacite SYNCHRO... (capacite ou objet)
        for(String e: _fight.getTemp().getSufferingTargetStatus()){
            if(!fCapacCible_.getForwardStatus().contains(e)){
                continue;
            }
            String statut_=fCapacCible_.getForwardStatus().getVal(e);
            affectStatusToThrower(_fight, _lanceur, statut_, _cible, _echecStatutsAb, _import);
        }
    }

    static void affectStatusToThrower(Fight _fight,TeamPosition _lanceur, String _status,TeamPosition _cible,StringMap<String> _echecStatuts,DataBase _import) {
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        if(_echecStatuts.contains(_status) && !_echecStatuts.getVal(_status).isEmpty()){
            StringMap<String> variables_=FightValues.calculateBasicBooleanValues(_fight,_cible,_lanceur,_import);
            variables_.putAllMap(FightValues.calculateValues(_fight, _cible, _lanceur, _import));
            if (_import.evaluateBoolean(_echecStatuts.getVal(_status), variables_, false)) {
                return;
            }
        }
        if(FightSuccess.successfulAffectedStatus(_fight,_cible,_lanceur,_status,_import)){
            if (creatureCbtLanceur_.isSingleStatus(_status)) {
                creatureCbtLanceur_.affecterStatut(_status);
                _fight.addStatusMessage(_lanceur, _status, _import);
            } else {
                creatureCbtLanceur_.affecterPseudoStatut(_cible, _status);
                _fight.addStatusRelMessage(_lanceur, _status, _cible, _import);
            }
        }
    }

    static IdMap<Statistic,Long> deltaBoostStatisticMap(Fight _fight, TeamPosition _combattant,IdMap<Statistic,Long> _varBase,DataBase _import) {
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        for (Statistic s: _varBase.getKeys()) {
            long var_ = deltaBoostStatistic(_fight, _combattant, s, _varBase.getVal(s), _import);
            map_.put(s, var_);
        }
        if (map_.isEmpty()) {
            return map_;
        }
        Longs values_ = new Longs(map_.values());
        if (values_.getMinimum(_import.getMaxBoost() + 1L) >= 0) {
            return map_;
        }
        Fighter fighter_ = _fight.getFighter(_combattant);
        AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
        if (ab_ == null || ab_.getMultStatIfLowStat().isEmpty()) {
            return map_;
        }
        for (Statistic s: _varBase.getKeys()) {
            if (_varBase.getVal(s) < 0) {
                map_.put(s, 0L);
            }
        }
        for (Statistic s: ab_.getMultStatIfLowStat().getKeys()) {
            if (!map_.contains(s)) {
                map_.put(s, ab_.getMultStatIfLowStat().getVal(s));
            } else {
                map_.put(s, map_.getVal(s)+ab_.getMultStatIfLowStat().getVal(s));
            }
        }
        return map_;
    }

    static long deltaBoostStatistic(Fight _fight, TeamPosition _combattant,Statistic _stat,long _varBase,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        Rate var_=new Rate(_varBase);
        AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
        if(fCapac_ != null){
            Rate rate_ = fCapac_.getMultVarBoost();
            if(!rate_.isZero()){
                if(rate_.isZeroOrGt()){
                    var_.multiplyBy(rate_);
                }else if(!var_.isZeroOrGt()){
                    var_.multiplyBy(rate_);
                }else{
                    var_.multiplyBy(rate_.opposNb());
                }
            }
        }
        long maxBoost_=_import.getMaxBoost();
        long minBoost_=_import.getMinBoost();
        long boostActuel_=creature_.getStatisBoost().getVal(_stat);
        if(var_.isZeroOrGt()){
            if(boostActuel_+var_.ll()>maxBoost_){
                return maxBoost_-boostActuel_;
            }
            return var_.ll();
        }
        if(boostActuel_+var_.ll()<minBoost_){
            return minBoost_-boostActuel_;
        }
        return var_.ll();
    }

    static Rate criticalHitEvent(Fight _fight, TeamPosition _thrower, Rate _max, DataBase _import) {
        Fighter user_ = _fight.getFighter(_thrower);
        Rate event_ = new Rate(_max);
        Rate rate_ = FightStatistic.multiplyStatisticPartner(_fight,Statistic.CRITICAL_HIT, _thrower.getTeam(), _import);
        if (rate_.greaterThanOne()) {
            event_.multiplyBy(rate_);
        }
        AbilityData fCapac_=user_.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && !fCapac_.getMultDamageCh().isZero()) {
            event_.multiplyBy(fCapac_.getMultDamageCh());
        }
        return event_;
    }

    static Rate remainingHp(String _move, DataBase _import) {
        Rate pv_ = Rate.zero();
        MoveData fAttaque_=_import.getMove(_move);
        int nbEffets_=fAttaque_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttaque_.getEffet(i);
            if (effet_ instanceof EffectProtection) {
                EffectProtection effetProt_ = (EffectProtection) effet_;
                pv_.affect(effetProt_.getProtSingleAgainstKo());
                break;
            }
        }
        return pv_;
    }

    static boolean canReverseAbsorb(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_target);
        if (creatureCible_.estKo()) {
            return false;
        }
        AbilityData ab_ = FightAbilities.ignoredTargetAbility(_fight, _thrower, _target, _import);
        if (ab_ != null) {
            return ab_.isInflictingDamageInsteadOfSuffering();
        }
        return false;
    }
}
