
package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAccuracy;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectClone;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.EffectVarPP;
import aiki.fight.moves.effects.EffectWinMoney;
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
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
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
        TeamPosition finalThrower_ = throwerTarget_.getUser();
        TeamPosition finalTarget_ = throwerTarget_.getTarget();
        Effect effet_ = _import.getMove(_move).getEffet(_index);
        Fighter creatureCible_=_fight.getFighter(finalTarget_);
        Fighter creatureLanceur_=_fight.getFighter(finalThrower_);
        creatureLanceur_.successUsingMove();
        _fight.addEffect(finalThrower_, finalTarget_, effet_);
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
        if(effet_ instanceof EffectEndRound){
            effectEndRound(_fight, finalThrower_, finalTarget_, _move, effet_, _import);
            return;
        }
        if(effet_ instanceof EffectWinMoney){
            EffectWinMoney effetLoc_=(EffectWinMoney)effet_;
            _fight.getWinningMoney().addNb(Rate.multiply(effetLoc_.getWinningRateBySumTargetUser(),new Rate(creatureCible_.getLevel()+creatureLanceur_.getLevel())));
            return;
        }
        if(effet_ instanceof EffectGlobal){
            EffectGlobal effetLoc_=(EffectGlobal)effet_;
            effectGlobal(_fight,effetLoc_,_move,_import);
            return;
        }
        if(effet_ instanceof EffectProtectFromTypes){
            creatureLanceur_.activerAttaqueImmu(_move, _import);
            _fight.addEnabledMoveMessage(finalThrower_, _move, _import);
            return;
        }
        if(effet_ instanceof EffectMultUsedMovePower){
            EffectMultUsedMovePower effetLoc_=(EffectMultUsedMovePower)effet_;
            effectMultiplyUsedMovePower(_fight,finalThrower_,effetLoc_, _move, _import);
            return;
        }
        if(effet_ instanceof EffectMultSufferedMovePower){
            EffectMultSufferedMovePower effetLoc_=(EffectMultSufferedMovePower)effet_;
            effectMultiplyUndergoneMovePower(_fight, finalTarget_, effetLoc_, _move, _import);
            return;
        }
        if(effet_ instanceof EffectAlly){
            //ex: COUP_D_MAIN
            creatureCible_.getEnabledMovesForAlly().put(_move, BoolVal.TRUE);
            _fight.addHelpAllyMessage(finalThrower_, finalTarget_, _import);
            return;
        }
        if(effet_ instanceof EffectAccuracy){
            creatureLanceur_.getIncrUserAccuracy().put(new MoveTeamPosition(_move,finalTarget_), BoolVal.TRUE);
            _fight.addEnabledMoveRelMessage(finalTarget_, _move, finalThrower_, _import);
            return;
        }
        if(effet_ instanceof EffectBatonPass){
            //remplacement du lanceur avec transfert
            effectBatonPass(_fight, finalThrower_, _diff, _import);
            return;
        }
        if(effet_ instanceof EffectStatistic){
            EffectStatistic effetLoc_=(EffectStatistic)effet_;
            EnumList<Statistic> statistics_ = FightSuccess.successfulChangedStatistics(_fight,finalThrower_, finalTarget_, effetLoc_, _import);
            if (statistics_.isEmpty()) {
                return;
            }
            effectStatisticRandom(_fight,finalThrower_,finalTarget_,effetLoc_,statistics_,effetLoc_.getEvtRate(),false,_import);
            return;
        }
        if(effet_ instanceof EffectStatus){
            EffectStatus effetLoc_=(EffectStatus)effet_;
            effectStatus(_fight,finalThrower_,finalTarget_,effetLoc_,_diff,_import);
            return;
        }
        if(effet_ instanceof EffectDamageRate){
            EffectDamageRate effetLoc_=(EffectDamageRate)effet_;
            effectDamageRate(_fight,finalTarget_,effetLoc_,_diff,_import);
            return;
        }
        if(effet_ instanceof EffectFullHpRate){
            EffectFullHpRate effetLoc_=(EffectFullHpRate)effet_;
            effectFullHpRate(_fight,finalTarget_,effetLoc_,_diff,_import);
            return;
        }
        if(effet_ instanceof EffectRemainedHpRate){
            EffectRemainedHpRate effetLoc_=(EffectRemainedHpRate)effet_;
            effectLeftHpRate(_fight,finalTarget_,effetLoc_,_diff,_import);
            return;
        }
        if(effet_ instanceof EffectVarPP){
            EffectVarPP effetLoc_=(EffectVarPP)effet_;
            effectVarPp(_fight,finalTarget_,effetLoc_,_diff,_import);
        }
    }

    static UserTarget pairNewThrowerTarget(Fight _fight,
            String _move,
            int _index,
            TeamPosition _initialThrower,
            TeamPosition _initialTarget,
            DataBase _import) {
        TeamPosition finalThrower_=_initialThrower;
        TeamPosition finalTarget_=_initialTarget;
        MoveData fAttFinal_ = _import.getMove(_move);
        if(fAttFinal_ instanceof StatusMoveData && !_fight.isChangeThrower()){
            StatusMoveData fAttNonOff_=(StatusMoveData)fAttFinal_;
            TeamPositionList takers_ = new TeamPositionList();
            if (fAttNonOff_.getThievableMove()) {
                takers_.addAllElts(FightRound.takers(_fight,_initialThrower, _import));
            }
            if (!takers_.isEmpty()) {
                _fight.getOrderedFighters().clear();
                _fight.getOrderedFighters().addAllElts(takers_);
                FightOrder.sortFightersUsingMoveAmongList(_fight, _import);
                takers_ = _fight.getOrderedFighters();
                finalThrower_ = takers_.first();
                _fight.addChangingWiewPointUserMessage(finalThrower_, _import);
                if (TeamPosition.eq(_initialThrower, _initialTarget)) {
                    finalTarget_ = takers_.first();
                }
            } else if (fAttNonOff_.getCounterableMove()){
                Effect eff_ = fAttNonOff_.getEffet(_index);
                if (eff_.getTargetChoice() != TargetChoice.LANCEUR) {
                    Fighter creatureCibleInit_=_fight.getFighter(finalTarget_);
                    String attaqueCible_=creatureCibleInit_.getFinalChosenMove();
                    if (attaqueCible_.isEmpty()) {
                        return new UserTarget(finalThrower_, finalTarget_);
                    }
                    if (FightOrder.getPointViewChangementType(attaqueCible_, _import) == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                        TeamPosition tmp_=finalThrower_;
                        finalThrower_=finalTarget_;
                        finalTarget_=tmp_;
                        _fight.addChangingWiewPointUserMessage(finalThrower_, _import);
                        _fight.addChangingWiewPointTargetMessage(finalTarget_, _import);
                    }
                } else {
                    byte foeTeam_ = Fight.foe(_initialThrower.getTeam());
                    for (byte f: _fight.getTeams().getVal(foeTeam_).getMembers().getKeys()) {
                        TeamPosition teamPos_ = new TeamPosition(foeTeam_, f);
                        Fighter f_ = _fight.getFighter(teamPos_);
                        String attaqueCible_=f_.getFinalChosenMove();
                        if (attaqueCible_.isEmpty()) {
                            continue;
                        }
                        if (FightOrder.getPointViewChangementType(attaqueCible_, _import) == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                            finalThrower_=teamPos_;
                            finalTarget_=teamPos_;
                            _fight.addChangingWiewPointUserMessage(teamPos_, _import);
                            break;
                        }
                    }
                }
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
        } else if (_effet.getChoiceRestriction() == MoveChoiceRestrictionType.LANCEUR_ATTAQUES) {
            for(String c:creatureCible_.attaquesUtilisables()){
                if(StringUtil.contains(creatureLanceur_.attaquesUtilisables(), c)){
                    creatureLanceur_.getPrivateMoves().getVal(new MoveTeamPosition(_attaqueLanceur,_cible)).add(c);
                }
            }
        } else {
            creatureCible_.activerAttaque(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
        }
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
        if(!_effet.getDisableImmuFromMoves().isEmpty()){
            //activer l'attaque _attaqueLanceur
            creatureCible_.activerAttaqueAntiImmu(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
        } else if(!_effet.getDisableImmuAgainstTypes().isEmpty()){
            //activer l'attaque _attaqueLanceur
            creatureCible_.activerAttaqueAntiImmu(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
        } else if(!_effet.getAttackTargetWithTypes().isEmpty()){
            //activer l'attaque _attaqueLanceur
            creatureCible_.activerAttaqueAntiImmu(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
        } else if(!_effet.getTypes().isEmpty()){
            creatureCible_.activerAttaqueAntiImmu(_attaqueLanceur);
            _fight.addEnabledMoveMessage(_cible, _attaqueLanceur, _import);
            //activer l'attaque _attaqueLanceur
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
            if(creatureCible_.possedeObjet()){
                String objetCible_ =creatureCible_.getItem();
                Item objet_=creatureCible_.ficheObjet(_import);
                if(objet_ instanceof Berry){
                    storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
                    creatureCible_.backUpObject(DataBase.EMPTY_STRING);
                    _fight.addSwitchItemsMessage(_cible, objetCible_, DataBase.EMPTY_STRING, _import);
                }
            }
        } else if(_effet.getMoveObject() == MoveItemType.USE_OBJECT_AS_POSSIBLE) {
            if (FightItems.canUseBerry(_fight,_lanceur,_import)) {
                if(creatureCible_.possedeObjet()){
                    Item objet_=creatureCible_.ficheObjet(_import);
                    if(objet_ instanceof Berry){
                        FightItems.enableBerry(_fight,_lanceur,creatureCible_.getItem(),_import);
                        creatureCible_.useObject();
                        storeTargetObject_ = NumberUtil.eq(_cible.getTeam(), Fight.CST_PLAYER);
                    }
                }
            }
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

    static void effectSwitchTypes(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectSwitchTypes _effet,DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        if(_effet.getConstValuesType() != ConstValuesType.NOTHING){
            //ADAPTATION,CONVERSION_2
            if(_effet.getConstValuesType() == ConstValuesType.LANCEUR_ATTAQUES_TYPES){
                StringList lanceurAttaquesActuellesTypes_=new StringList();
                for(String c:creatureLanceur_.attaquesUtilisables()){
                    lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight,_lanceur,c,_import));
                }
                StringUtil.removeAllElements(lanceurAttaquesActuellesTypes_, creatureLanceur_.getTypes());
                lanceurAttaquesActuellesTypes_.removeDuplicates();
                MonteCarloString types_=new MonteCarloString();
                for(String e:lanceurAttaquesActuellesTypes_){
                    types_.addQuickEvent(e,DataBase.defElementaryEvent());
                }
                if (!FightSuccess.isBadSimulation(_fight, types_)) {
                    String type_ = FightSuccess.random(_import, types_);
                    if (!type_.isEmpty()) {
                        creatureCible_.affecterTypes(type_);
                        _fight.addChangedTypesMessage(_cible, new StringList(type_), _import);
                    }
                }
            }else{
                StringList resistingTypes_ = creatureLanceur_.resistingTypes(_import);
                MonteCarloString types_ = new MonteCarloString();
                for(String e:resistingTypes_){
                    types_.addQuickEvent(e,DataBase.defElementaryEvent());
                }
                if (!FightSuccess.isBadSimulation(_fight, types_)) {
                    String type_ = FightSuccess.random(_import, types_);
                    if (!type_.isEmpty()) {
                        creatureCible_.affecterTypes(type_);
                        _fight.addChangedTypesMessage(_cible, new StringList(type_), _import);
                    }
                }
            }
        }else if(_effet.getChgtTypeByEnv().contains(_fight.getEnvType())){
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
                creatureCible_.affecterTypes(types_);
                _fight.addChangedTypesMessage(_cible, types_, _import);
            } else {
                String type_=_effet.getChgtTypeByEnv().getVal(_fight.getEnvType());
                creatureCible_.affecterTypes(type_);
                _fight.addChangedTypesMessage(_cible, new StringList(type_), _import);
            }
        }else if(!_effet.getAddedTypes().isEmpty()){
            creatureCible_.getTypes().addAllElts(_effet.getAddedTypes());
            creatureCible_.getTypes().removeDuplicates();
        }else{
            if (_effet.getExchangeTypes() == ExchangeType.EXCHANGE) {
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
    }

    static void effectSwitchMoveTypes(Fight _fight, String _move,TeamPosition _cible, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        creatureCible_.enableChangingMovesTypes(_move);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
    }

    static void effectCommonStatistics(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,EffectCommonStatistics _effet, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        EnumMap<Statistic,Rate> statisValeurs_=new EnumMap<Statistic,Rate>();
        EnumMap<Statistic,String> valCommunes_=_effet.getCommonValue();
        StringMap<String> values_ = FightValues.calculateValues(_fight, _lanceur, _cible, _import);
        for(Statistic c:valCommunes_.getKeys()){
            Rate rate_ = Rate.one();
            rate_ = _import.evaluatePositiveExp(valCommunes_.getVal(c), values_, rate_);
            statisValeurs_.put(c,rate_);
        }
        for(Statistic c:statisValeurs_.getKeys()){
            Rate valCommune_=statisValeurs_.getVal(c);
            if(c == Statistic.PV_RESTANTS){
                Rate diff_;
                if(Rate.strGreater(valCommune_,creatureCible_.pvMax())){
                    diff_=Rate.minus(creatureCible_.pvMax(),creatureCible_.getRemainingHp());
                }else{
                    diff_=Rate.minus(valCommune_,creatureCible_.getRemainingHp());
                }
                creatureCible_.variationLeftHp(diff_);
                _fight.addHpMessage(_cible, _import);
                if(Rate.strGreater(valCommune_,creatureLanceur_.pvMax())){
                    diff_.affect(Rate.minus(creatureLanceur_.pvMax(),creatureLanceur_.getRemainingHp()));
                }else{
                    diff_.affect(Rate.minus(valCommune_,creatureLanceur_.getRemainingHp()));
                }
                creatureLanceur_.variationLeftHp(diff_);
                _fight.addHpMessage(_lanceur, _import);
                continue;
            }
            creatureCible_.affecterBaseStatistique(c,valCommune_);
            creatureLanceur_.affecterBaseStatistique(c,valCommune_);
            _fight.addCommonStatisticMessage(c, _lanceur, _cible, valCommune_, _import);
        }
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
            if (StringUtil.quickEq(_import.getDefaultMove(), first_)) {
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
        short pp_=_effet.getPpForMoves();
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
        if(StringUtil.contains(_import.getMovesConstChoices(), _attaqueLanceur) &&!_fight.isInvokedMove()){
            if(!creatureLanceur_.getEnabledMovesConstChoices().getVal(_attaqueLanceur).isEnabled()){
                //utilisation attaque et mise a zero
                creatureLanceur_.activerAttaqueBlocantLanceur(_attaqueLanceur);
                _fight.addEnabledMoveMessage(_lanceur, _attaqueLanceur, _import);
            }
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
            attaqueAnticipe_.setNbRounds((byte) 0);
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
        if (FightItems.canUseItsObject(_fight, _cible, _import)) {
            Item it_ = creatureCible_.ficheObjet(_import);
            if (it_ instanceof ItemForBattle) {
                ItemForBattle item_ = (ItemForBattle) it_;
                if (FightSuccess.rateEffAgainstTargetMove(_fight, _lanceur, _attaqueLanceur, _cible, _import).greaterThanOne()) {
                    for (Statistic s: item_.getBoostStatisSuperEff().getKeys()) {
                        byte varBase_ = item_.getBoostStatisSuperEff().getVal(s);
                        creatureCible_.variationBoostStatistique(s, varBase_);
                        _fight.addStatisticMessage(_cible, s, varBase_, _import);
                    }
                }
                for (String t: FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import)) {
                    if (item_.getBoostStatisTypes().contains(t)) {
                        for (Statistic s: item_.getBoostStatisTypes().getVal(t).getKeys()) {
                            byte varBase_ = item_.getBoostStatisTypes().getVal(t).getVal(s);
                            creatureCible_.variationBoostStatistique(s, varBase_);
                            _fight.addStatisticMessage(_cible, s, varBase_, _import);
                        }
                    }
                }
            }
        }
        boolean coupCritique_=false;
        LgInt minHits_ = LgInt.zero();
        for (TeamPosition t: _throwerDamageLaws.getNumberHits().getKeys()) {
            Rate minHitsLoc_ = _throwerDamageLaws.getNumberHits().getVal(t).minimum();
            minHits_.addNb(minHitsLoc_.toLgInt());
        }
        boolean miseAKo_ = koTarget(_fight, _cible, _throwerDamageLaws);
        _fight.setPutKo(miseAKo_);
        Rate degats_=Rate.zero();
        MoveData fAttaqueLanceur_ = _import.getMove(_attaqueLanceur);
        Rate leftHp_ = new Rate(_fight.getFighter(_cible).getRemainingHp());
        boolean canReverseAbsorb_ = canReverseAbsorb(_fight, _lanceur, _cible, _import);
        byte nbCoupsTotal_=0;
        if (!miseAKo_) {
            _fight.getDamage().getDamage().affectZero();
            _fight.getDamage().getDamageClone().affectZero();
            _fight.getDamage().getDamageCount().affectZero();
            _fight.getDamage().setCriticalHit(false);
            _fight.getDamage().setHits((byte) 0);
            _fight.getDamage().setKeepProcessing(true);
            for (TeamPosition t: _throwerDamageLaws.getNumberHits().getKeys()) {
                inflictDamageToTargetByUserOfMove(_fight, t, _cible, _throwerDamageLaws, _import);
            }
            miseAKo_ = _fight.isPutKo();
            degats_ = _fight.getDamage().getDamage();
            coupCritique_ = _fight.getDamage().isCriticalHit();
            nbCoupsTotal_ += _fight.getDamage().getHits();
        } else {
            nbCoupsTotal_ = (byte) minHits_.ll();
        }
        _fight.addNbHitsMessage(nbCoupsTotal_, _cible, _import);
        _fight.setAnimationDamage(degats_, FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import));
        if (miseAKo_) {
            calculateDamageKo(_fight, _cible, _attaqueLanceur, new LgInt(nbCoupsTotal_), _diff, _import);
            if (!_fight.getAcceptableChoices()) {
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), _fight.getDamageKo());
                return;
            }
            degats_ = _fight.getDamageKo();
        } else {
            creatureCible_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_cible, _import);
        }
        Rate pvSoignes_ = healedLostHp(_fight, _lanceur, leftHp_, canReverseAbsorb_, degats_, _import);
        boolean capaciteActiveCible_;
        if(creatureCible_.estKo()){
            capaciteActiveCible_=false;
        }else{
            capaciteActiveCible_=!FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import);
        }
        if(capaciteActiveCible_){
            enableTargetAbility(
                    _fight,
                    _lanceur, _cible,
                    coupCritique_, nbCoupsTotal_,
                    _attaqueLanceur, _import);
        }
        boolean capaciteActiveLanceur_;
        if(creatureCible_.estKo()){
            capaciteActiveLanceur_=false;
        }else{
            capaciteActiveLanceur_=!FightAbilities.ignoreTargetAbility(_fight, _cible,_lanceur,_import);
        }
        //puanteur + //statut au contact de la part du lanceur (capacite ou objet cible)
        if(capaciteActiveLanceur_){
            enableFighterHavingToUseAbility(
                    _fight,
                    _lanceur, _cible,
                    capaciteActiveCible_,
                    _attaqueLanceur, _import);
        }
        _fight.getDamageKo().affect(degats_);
        recoilAgainstTarget(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        if (!_fight.getAcceptableChoices()) {
            return;
        }
        if (FightKo.endedFight(_fight, _diff)) {
            return;
        }
        degats_ = _fight.getDamageKo();
        creatureCible_.setLastSufferedMove(_attaqueLanceur);
        creatureCible_.setLastSufferedMoveTypes(FightMoves.moveTypes(_fight, _lanceur, _attaqueLanceur, _import));
        _fight.getDamageByCurrentUser().put(_cible,degats_);
        creatureCible_.getDamageSufferedCategRound().getVal(fAttaqueLanceur_.getCategory()).addNb(degats_);
        creatureCible_.getDamageSufferedCateg().getVal(fAttaqueLanceur_.getCategory()).addNb(degats_);
        //degats recul et soin
        if (pvSoignes_.isZeroOrGt()) {
            creatureLanceur_.variationLeftHp(pvSoignes_);
            _fight.addHpMessage(_lanceur, _import);
        } else {
            if (Rate.greaterEq(pvSoignes_.opposNb(), creatureLanceur_.getRemainingHp())) {
                FightKo.setKoMoveTeams(_fight, _lanceur, _diff, _import);
                if(NumberUtil.eq(_lanceur.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), Rate.zero());
                    return;
                }
            } else {
                creatureLanceur_.variationLeftHp(pvSoignes_);
                _fight.addHpMessage(_lanceur, _import);
            }
        }
        if(!creatureLanceur_.estKo()&&creatureCible_.estKo()){
            enableBoostEffectWhileKoTarget(_fight, _lanceur, _attaqueLanceur, _import);
            enableAbilityWhileKoTarget(_fight, _lanceur, _import);
        }
        if(!creatureLanceur_.estKo()&&FightItems.canUseItsBerry(_fight,_lanceur,_import)){
            FightItems.enableBerryHp(_fight, _lanceur, creatureLanceur_.getItem(), true, true, _import);
        }
        if(!creatureCible_.estKo()&&FightItems.canUseItsBerry(_fight,_cible,_import)){
            Berry berry_ = (Berry) creatureCible_.ficheObjet(_import);
            if (StringUtil.quickEq(berry_.getCategoryBoosting(), fAttaqueLanceur_.getCategory())) {
                for (Statistic s: berry_.getBoostStatis().getKeys()) {
                    creatureCible_.variationBoostStatistique(s, berry_.getBoostStatis().getVal(s));
                    _fight.addStatisticMessage(_cible, s, berry_.getBoostStatis().getVal(s), _import);
                }
            }
            FightItems.enableBerryHp(_fight,_cible, creatureCible_.getItem(), true, true, _import);
        }
        //piege en attaque en tenant compte du clone => fin de tour
        _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), _fight.getDamageKo());
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
        boolean peutUtiliserObjetLanceur_=FightItems.canUseItsObject(_fight,_lanceur,_import);
        Rate tauxPvAbsObj_=Rate.zero();
        if(peutUtiliserObjetLanceur_){
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            Item objet_=creatureLanceur_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                tauxPvAbsObj_.affect(objetAttachable_.getDrainedHpByDamageRate());
            }
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
        if(!FightItems.canUseItsBerry(_fight, _cible,_import)){
            return;
        }
        Berry baie_=(Berry)creatureCible_.ficheObjet(_import);
        for (String t:typeAtt_) {
            if(!baie_.getMultFoesDamage().contains(t)){
                continue;
            }
            Rate coeff_ = FightSuccess.rateEffAgainstTarget(_fight, _lanceur, _cible, t, _import);
            EfficiencyRate effRate_ = baie_.getMultFoesDamage().getVal(t);
            if (Rate.lowerEq(coeff_, effRate_.getEff())) {
                continue;
            }
            creatureCible_.useObject();
            break;
        }
    }

    static DamageMoveCountUser damageByUserOfMove(
            Fight _fight, DataBase _import,
            TeamPosition _fighter, TeamPosition _target,
            ThrowerDamageLaws _laws) {
        MonteCarloNumber loiRand_ = _laws.getRandomRate();
        MonteCarloNumber repetCoup_=_laws.getNumberHits().getVal(_fighter);
        Rate sommeCoups_ = Rate.zero();
        LgInt maxRd_ = _import.getMaxRd();
        byte nbCoups_ = (byte) randomRate(_fight, _import, repetCoup_, _target).ll();
        boolean coupCritique_ = false;
        for (int i = IndexConstants.FIRST_INDEX; i<nbCoups_; i++){
            Rate degatsBase_ = _laws.getBase().getVal(_fighter).editNumber(maxRd_,_import.getGenerator());
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
        if (_fight.isPutKo()) {
            return;
        }
        DamageMoveCountUser damage_ = _fight.getDamage();
        Rate degatsClone_ = new Rate(damage_.getDamageClone());
        Rate degats_ = new Rate(damage_.getDamage());
        Rate sommeCoups_ = new Rate(damage_.getDamageCount());
        byte previousHits_ = damage_.getHits();
        Fighter creatureCible_=_fight.getFighter(_target);
        MonteCarloNumber loiRand_ = _laws.getRandomRate();
        LgInt maxRd_ = _import.getMaxRd();
        byte nbCoups_ = (byte) _laws.getNumberHits().getVal(_fighter).editNumber(maxRd_,_import.getGenerator()).ll();
        boolean coupCritique_ = false;
        boolean keepProcessing_ = true;
        int nbHits_ = 0;
        for (int i = IndexConstants.FIRST_INDEX; i<nbCoups_; i++){
            Rate degatsBase_ = _laws.getBase().getVal(_fighter).editNumber(maxRd_,_import.getGenerator());
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
        if (!damage_.isCriticalHit()) {
            damage_.setCriticalHit(coupCritique_);
        }
        damage_.setDamageCount(sommeCoups_);
        damage_.setHits((byte) (previousHits_+nbHits_));
        damage_.setDamageClone(degatsClone_);
        damage_.setDamage(degats_);
        damage_.setKeepProcessing(keepProcessing_);
        if (!_fight.getDamage().isKeepProcessing()) {
            _fight.setPutKo(true);
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
        } else {
            if(Rate.greaterEq(min_,_cloneHp)){
                Rate minHits_ = Rate.zero();
                for (TeamPosition t: _laws.getNumberHits().getKeys()) {
                    minHits_.addNb(_laws.getNumberHits().getVal(t).minimum());
                }
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
        }
        return miseAKo_;
    }

    static void calculateDamageKo(Fight _fight, TeamPosition _cible, String _attaqueLanceur,
            LgInt _minHits,
            Difficulty _diff, DataBase _import) {
        Rate pv_=remainingHp(_fight, _cible, _attaqueLanceur, _import);
        Fighter creatureCible_ = _fight.getFighter(_cible);
        DamagingMoveData fAttaqueLanceur_ = (DamagingMoveData) _import.getMove(_attaqueLanceur);
        Rate degats_ = _fight.getDamageKo();
        if(fAttaqueLanceur_.getCannotKo()) {
            degats_.affect(Rate.minus(creatureCible_.getRemainingHp(),pv_));
            creatureCible_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_cible, _import);
        } else if (!pv_.isZero() && LgInt.lowerEq(_minHits, LgInt.one())) {
            degats_.affect(Rate.minus(creatureCible_.getRemainingHp(),pv_));
            creatureCible_.variationLeftHp(degats_.opposNb());
            _fight.addHpMessage(_cible, _import);
        } else {
            degats_.affect(creatureCible_.getRemainingHp());
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }
    }

    static Rate randomRate(Fight _fight, DataBase _import,MonteCarloNumber _lawCriticalHitRate,TeamPosition _target) {
        Rate cc_;
        LgInt maxRd_ = _import.getMaxRd();
        if(_fight.getSimulation()){
            if(NumberUtil.eq(_target.getTeam(),Fight.CST_FOE)){
                cc_=_lawCriticalHitRate.minimum();
            }else{
                cc_=_lawCriticalHitRate.maximum();
            }
        }else{
            cc_=_lawCriticalHitRate.editNumber(maxRd_,_import.getGenerator());
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
        } else if(FightItems.canUseItsObject(_fight,_cible,_import)){
            Item objet_=creatureCible_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(!objetAttachable_.getProtectAgainstKo().isZero()){
                    pv_.affect(objetAttachable_.getProtectAgainstKo());
                }
                if(!objetAttachable_.getProtectAgainstKoIfFullHp().isZero()&&Rate.eq(creatureCible_.getRemainingHp(),creatureCible_.pvMax())){
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
        StringMap<String> variables_;
        variables_ = FightValues.calculateValues(_fight, _lanceur,_cible,_import);
        Rate basePower_;
        basePower_ = _import.evaluatePositiveExp(effect_.getPower(), variables_, DataBase.getDefaultPower());
        StringList typeAtt_=FightMoves.moveTypes(_fight, _lanceur,_attaqueLanceur,_import);
        String nomActuelLanceur_=creatureLanceur_.getCurrentName();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_CATEGORIE), fAtt_.getCategory());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_NOM), nomActuelLanceur_);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_TYPES), StringUtil.join(typeAtt_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_NOM), _attaqueLanceur);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PUISSANCE_BASE), basePower_.toNumberString());
        Rate finalPower_ = new Rate(basePower_);
        finalPower_.multiplyBy(rateObjectPower(_fight, _lanceur, variables_, _import));
        finalPower_.multiplyBy(rateTypesPower(_fight, _lanceur, _cible, typeAtt_));
        finalPower_.multiplyBy(rateAbilityPower(_fight, _lanceur, variables_, _import));
        Rate att_ = attack(_fight, _lanceur, _cible, effect_, variables_, _import);
        Rate def_ = defense(_fight, _lanceur, _cible, effect_, variables_, _import);
        Rate degats_;
        StringMap<String> varLocs_ = new StringMap<String>();
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTACK), att_.toNumberString());
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.DEFENSE), def_.toNumberString());
        varLocs_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.POWER), finalPower_.toNumberString());
        varLocs_.putAllMap(variables_);
        String damageFormula_ = _import.getDamageFormula();
        degats_ = _import.evaluatePositiveExp(damageFormula_, varLocs_, finalPower_);
        degats_.multiplyBy(ratePartnerMove(creatureLanceur_, _import));
        Rate coeffEff_= FightSuccess.rateEffAgainstTargetMove(_fight, _lanceur, _attaqueLanceur, _cible, _import);
        degats_.multiplyBy(rateDamageTargetAbility(_fight, _lanceur, _cible, typeAtt_, coeffEff_, _import));
        degats_.multiplyBy(rateDamageTargetTeamMoves(_fight, _lanceur, _cible, _attaqueLanceur, _import));
        degats_.multiplyBy(rateDamageGlobalAbilities(_fight, typeAtt_, _import));
        degats_.multiplyBy(multBaseDamage(_fight, _attaqueLanceur, _import));
        degats_.multiplyBy(rateDamageGlobalMoves(_fight, typeAtt_, _import));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF), coeffEff_.toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_UTILISATION_CONSECUTIF), creatureLanceur_.getNbRepeatingSuccessfulMoves().toNumberString());
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
        Fighter fighter_ = _fight.getFighter(_fighter);
        Rate rate_ = DataBase.defRateProduct();
        if(FightItems.canUseItsObject(_fight, _fighter,_import)){
            Item objet_=fighter_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                StringMap<String> vars_ = new StringMap<String>(_variables);
                vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _fighter, _import));
                rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(objetAttachable_.getMultPower(), vars_, _import));
            }
        }
        return rate_;
    }

    static Rate rateAbilityPower(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_fighter);
        Rate rate_ = DataBase.defRateProduct();
        if(fighter_.capaciteActive()){
            AbilityData ab_=fighter_.ficheCapaciteActuelle(_import);
            StringMap<String> vars_ = new StringMap<String>(_variables);
            vars_.putAllMap(FightValues.calculateValuesFighter(_fight, _fighter, _import));
            rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(ab_.getMultPower(), vars_, _import));
            for (String t: StringUtil.splitChars(_variables.getVal(StringUtil.concat(DataBase.VAR_PREFIX,Fight.ATTAQUE_TYPES)), _import.getSepartorSetChar())) {
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
        byte baseBoost_=(byte)_import.getDefaultBoost();
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
        boolean priseEnCompteCapaciteCible_=!FightAbilities.ignoreTargetAbility(_fight, _thrower,_target,_import);
        byte boost_=thrower_.getStatisBoost().getVal(statis_);
        boost_ += FightStatistic.bonusBoost(_fight, statis_, _thrower, _import);
        if(boost_<=baseBoost_){
            if(!_effect.getIgnVarStatUserNeg().containsObj(statis_)){
                att_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
            }
        }else if(!priseEnCompteCapaciteCible_){
            att_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
        }else{
            AbilityData fCapaciteCible_=target_.ficheCapaciteActuelle(_import);
            if(!fCapaciteCible_.isIgnFoeStatisBoost()){
                att_.multiplyBy(FightStatistic.rateBoost(boost_,_import));
            }
        }
        return att_;
    }

    static Rate defense(Fight _fight, TeamPosition _thrower, TeamPosition _target, EffectDamage _effect, StringMap<String> _variables, DataBase _import) {
        Fighter thrower_ = _fight.getFighter(_thrower);
        Fighter target_ = _fight.getFighter(_target);
        byte baseBoost_=(byte)_import.getDefaultBoost();
        Statistic statis_=_effect.getStatisDef();
        Rate def_;
        if(_effect.isTargetDefense()){
            def_=target_.statistiqueGlobaleEvIv(statis_);
        }else{
            def_=thrower_.statistiqueGlobaleEvIv(statis_);
        }
        byte boost_=target_.getStatisBoost().getVal(statis_);
        boost_ += FightStatistic.bonusBoost(_fight, statis_, _target, _import);
        if(boost_>=baseBoost_){
            if(!_effect.getIgnVarStatTargetPos().containsObj(statis_)){
                boolean priseEnCompteBoost_=true;
                if(!FightAbilities.ignoreTargetAbility(_fight, _target,_thrower,_import)){
                    AbilityData fCapaciteLanceur_=thrower_.ficheCapaciteActuelle(_import);
                    if(fCapaciteLanceur_.isIgnFoeStatisBoost()){
                        priseEnCompteBoost_=false;
                    }
                }
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
        Fighter target_ = _fight.getFighter(_target);
        Rate rate_ = DataBase.defRateProduct();
        boolean priseEnCompteCapaciteCible_=!FightAbilities.ignoreTargetAbility(_fight, _thrower,_target,_import);
        if(priseEnCompteCapaciteCible_){
            AbilityData fCapaciteCible_=target_.ficheCapaciteActuelle(_import);
            for (String t: _moveTypes) {
                if(fCapaciteCible_.getMultDamageFoe().contains(t)){
                    rate_.multiplyBy(fCapaciteCible_.getMultDamageFoe().getVal(t));
                }
            }
            if(_efficiency.greaterThanOne()){
                if(!fCapaciteCible_.getMultSufferedDamageSuperEff().isZero()){
                    rate_.multiplyBy(fCapaciteCible_.getMultSufferedDamageSuperEff());
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetTeamMoves(Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        Team equipeCible_=_fight.getTeams().getVal(_target.getTeam());
        MoveData fMove_ = _import.getMove(_move);
        String cat_ = fMove_.getCategory();
        short mult_ = _fight.getMult();
        CategoryMult p_ = new CategoryMult(cat_,mult_);
        for(String c:equipeCible_.enabledTeamMoves()){
            boolean priseEnCompteEquipeCible_=true;
            if(thrower_.capaciteActive()){
                AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
                if(StringUtil.contains(fCapac_.getIgnFoeTeamMove(), c)){
                    priseEnCompteEquipeCible_=false;
                }
            }
            if(!priseEnCompteEquipeCible_){
                continue;
            }
            MoveData fAttEquipe_=_import.getMove(c);
            int nbEffets_=fAttEquipe_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttEquipe_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(effetEquipe_.getMultDamage().contains(p_)){
                    rate_.multiplyBy(effetEquipe_.getMultDamage().getVal(p_));
                }
            }
        }
        return rate_;
    }

    static Rate rateDamageGlobalAbilities(Fight _fight, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        for (TeamPosition f_: FightOrder.frontFighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f_);
            if (!fighter_.capaciteActive()) {
                continue;
            }
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            for (String t: _moveTypes) {
                if(ab_.getMultPowerMovesTypesGlobal().contains(t)){
                    rate_.multiplyBy(ab_.getMultPowerMovesTypesGlobal().getVal(t));
                }
            }
        }
        boolean reverse_ = false;
        for (TeamPosition f_: FightOrder.frontFighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f_);
            if (!fighter_.capaciteActive()) {
                continue;
            }
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            if (ab_.isReverseEffectsPowerMovesTypesGlobal()) {
                reverse_ = true;
                break;
            }
        }
        if (reverse_) {
            if (rate_.isZero()) {
                rate_ = Rate.one();
            } else {
                rate_.invertNb();
            }
        }
        return rate_;
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
        Fighter thrower_ = _fight.getFighter(_fighter);
        boolean peutUtiliserObjetLanceur_=FightItems.canUseItsObject(_fight, _fighter,_import);
        if(peutUtiliserObjetLanceur_){
            Item objet_=thrower_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                rate_.multiplyBy(FightStatistic.multiplyStringFighterVariables(objetAttachable_.getMultDamage(), _variables, _import));
            }
        }
        return rate_;
    }

    static Rate rateDamageThrowerAbility(Fight _fight, TeamPosition _fighter, StringMap<String> _variables, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_fighter);
        if(thrower_.capaciteActive()){
            AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
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
                if(effetInvoque_.getRateInvokationMove().isZero()){
                    continue;
                }
                //MOI_D_ABORD
                rate_.multiplyBy(effetInvoque_.getRateInvokationMove());
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetBerry(Fight _fight, TeamPosition _thrower, TeamPosition _target, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter target_ = _fight.getFighter(_target);
        if(FightItems.canUseItsBerry(_fight, _target,_import)){
            Berry baie_=(Berry)target_.ficheObjet(_import);
            for (String t:_moveTypes) {
                if(!baie_.getMultFoesDamage().contains(t)){
                    continue;
                }
                Rate coeff_ = FightSuccess.rateEffAgainstTarget(_fight, _thrower, _target, t, _import);
                EfficiencyRate effRate_ = baie_.getMultFoesDamage().getVal(t);
                if (Rate.lowerEq(coeff_, effRate_.getEff())) {
                    continue;
                }
                rate_.multiplyBy(effRate_.getHpRate());
            }
        }
        return rate_;
    }

    static Rate rateDamageTargetBeforeUsingMove(Fight _fight, TeamPosition _target, StringList _moveTypes, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter target_ = _fight.getFighter(_target);
        if(target_.getNbPrepaRound()>0){
            for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
                MoveData fAttGlobal_=_import.getMove(c);
                int nbEffets_=fAttGlobal_.nbEffets();
                for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                    Effect effet_=fAttGlobal_.getEffet(i);
                    if(!(effet_ instanceof EffectGlobal)){
                        continue;
                    }
                    EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                    if(StringUtil.contains(effetGlobal_.getMovesUsedByTargetedFighters(), target_.getFinalChosenMove())){
                        for (String t:_moveTypes) {
                            if(effetGlobal_.getMultDamagePrepaRound().contains(t)){
                                rate_.multiplyBy(effetGlobal_.getMultDamagePrepaRound().getVal(t));
                            }
                        }
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
            if(!NumberUtil.eq(_thrower.getTeam(),c.getTeamPosition().getTeam())) {
                continue;
            }
            if(TeamPosition.eq(_thrower,c.getTeamPosition())){
                continue;
            }
            if(NumberUtil.eq(thrower_.getStatusRelatNbRoundShort(c), 0)){
                continue;
            }
            Status statutLoc_=_import.getStatus().getVal(c.getMove());
            if(!statutLoc_.estActifPartenaire()){
                continue;
            }
            EffectPartnerStatus effetPart_=statutLoc_.getEffectsPartner().first();
            if(!effetPart_.getWeddingAlly()){
                continue;
            }
            if (NumberUtil.eq(_thrower.getTeam(), _target.getTeam())) {
                continue;
            }
            rate_.multiplyBy(effetPart_.getMultDamageAgainstFoe());
            rate_.multiplyBy(FightStatistic.multiplyByLoveBetweenFighters(_fight,_import));
        }
        return rate_;
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
            if(!thrower_.capaciteActive()){
                continue;
            }
            AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
            if(!fCapac_.getMultStab().isZero()){
                rate_.multiplyBy(fCapac_.getMultStab());
            }
        }
        return rate_;
    }

    static Rate rateDamageThrowerTargetAbility(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter thrower_ = _fight.getFighter(_thrower);
        if(NumberUtil.eq(_thrower.getTeam(),_target.getTeam())){
            if(thrower_.capaciteActive()){
                AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
                if(!fCapac_.getMultAllyDamage().isZero()){
                    rate_.multiplyBy(fCapac_.getMultAllyDamage());
                }
            }
        }
        return rate_;
    }

    //The fight is a bean with forms
    //not necessary from data (trainer or wild pokemon)
    //add a user/target lists for calculating
    //add a message with order of fighters (because each one has an action)
    static EnumMap<UsefulValueLaw,Rate> calculateMinMaxAvgVarForDamage(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,String _attaqueLanceur, Difficulty _diff,DataBase _import){
        ThrowerDamageLaws throwerDamageLaws_ = calculateLawsForDamageByTeam(_fight, _lanceur, _cible, _attaqueLanceur, _diff, _import);
        EnumMap<UsefulValueLaw,Rate> degatsUnCoup_=new EnumMap<UsefulValueLaw,Rate>();
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
            for(byte c:equipeLanceur_.notKoPartnersWithoutStatus(_lanceur.getPosition())){
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
        if (thrower_.capaciteActive()) {
            AbilityData ab_ = thrower_.ficheCapaciteActuelle(_import);
            if (ab_.isNbHits()) {
                MonteCarloNumber law_ = new MonteCarloNumber();
                Rate max_ = effect_.getHitsLaw().maximum();
                law_.addQuickEvent(max_, DataBase.defElementaryEvent());
                throwerDamageLaws_.getNumberHits().put(_lanceur, law_);
            } else {
                MonteCarloNumber law_ = effect_.getHitsLaw().copy();
                throwerDamageLaws_.getNumberHits().put(_lanceur, law_);
            }
        } else {
            MonteCarloNumber law_ = effect_.getHitsLaw().copy();
            throwerDamageLaws_.getNumberHits().put(_lanceur, law_);
        }
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
            law_.addQuickEvent(degats_, LgInt.one());
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
                loiNumerique_.addQuickEvent(valeur_,effect_.getDamageLaw().getFreq(i));
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
            law_.addQuickEvent(somme_, LgInt.one());
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
        boolean criticalHitCanHappen_;
        criticalHitCanHappen_ = criticalHitCanHappen(_fight, _lanceur, _cible, _import);
        MonteCarloNumber loiCc_ = new MonteCarloNumber();
        if(criticalHitCanHappen_){
            byte boostCc_ = (byte) FightStatistic.criticalHit(_fight, _lanceur, effect_.getChRate(), _import);
            Rate probaCc_ = FightSuccess.rateCriticalHit(_fight, _lanceur, boostCc_, _import);
            Rate minCc_=effect_.getChLaw().minimum();
            Rate maxCc_ = effect_.getChLaw().maximum();
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
        MonteCarloNumber law_;
        throwerDamageLaws_.getCriticalHit().put(_lanceur,loiCc_);
        law_ = new MonteCarloNumber();
        law_.addQuickEvent(degats_, LgInt.one());
        throwerDamageLaws_.getBase().put(_lanceur,law_);
        return throwerDamageLaws_;
    }

    static boolean criticalHitCanHappen(Fight _fight, TeamPosition _lanceur,TeamPosition _cible,
            DataBase _import) {
        boolean priseEnCompteCc_=true;
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter creatureCible_ = _fight.getFighter(_cible);
        Fighter thrower_ = _fight.getFighter(_lanceur);
        for(String c:equipeCible_.enabledTeamMoves()){
            boolean priseEnCompteEquipeCible_=true;
            if(thrower_.capaciteActive()){
                AbilityData fCapac_=thrower_.ficheCapaciteActuelle(_import);
                if(StringUtil.contains(fCapac_.getIgnFoeTeamMove(), c)){
                    priseEnCompteEquipeCible_=false;
                }
            }
            if(!priseEnCompteEquipeCible_){
                continue;
            }
            MoveData fAttEquipe_=_import.getMove(c);
            int nbEffets_=fAttEquipe_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttEquipe_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(effetEquipe_.getProtectAgainstCh()){
                    priseEnCompteCc_=false;
                    break;
                }
            }
            if(!priseEnCompteCc_){
                break;
            }
        }
        if(!FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import)){
            AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
            if(fCapac_.isImmuCh()){
                priseEnCompteCc_=false;
            }
        }
        return priseEnCompteCc_;
    }

    static void enableTargetAbility(
            Fight _fight,
            TeamPosition _lanceur, TeamPosition _cible,
            boolean _criticalHit, byte _hitsCount,
            String _attaqueLanceur, DataBase _import) {
        byte maxBoost_=(byte)_import.getMaxBoost();
        Fighter creatureCible_=_fight.getFighter(_cible);
        AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
        StringList typeAttaque_=FightMoves.moveTypes(_fight, _lanceur,_attaqueLanceur,_import);
        MoveData fAttaqueLanceur_ = _import.getMove(_attaqueLanceur);
        String categorie_=fAttaqueLanceur_.getCategory();
        for(Statistic c:creatureCible_.getStatisBoost().getKeys()){
            if(_criticalHit&&fCapac_.getMaxStatisticsIfCh().containsObj(c)){
                byte boostActuel_=creatureCible_.getStatisBoost().getVal(c);
                creatureCible_.variationBoostStatistique(c,(byte) (maxBoost_-boostActuel_));
                _fight.addStatisticMessage(_cible, c, (long)maxBoost_-boostActuel_, _import);
                continue;
            }
            byte var_=0;
            for (String t: typeAttaque_) {
                if(fCapac_.getMultStatIfDamgeType().contains(new StatisticType(c,t))){
                    var_+=fCapac_.getMultStatIfDamgeType().getVal(new StatisticType(c,t));
                }
            }
            if(fCapac_.getMultStatIfDamageCat().contains(new StatisticCategory(c,categorie_))){
                var_+=fCapac_.getMultStatIfDamageCat().getVal(new StatisticCategory(c,categorie_));
            }
            var_=deltaBoostStatistic(_fight, _cible,c,(byte) (var_*_hitsCount),_import);
            if(var_==0){
                continue;
            }
            creatureCible_.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_cible, c, var_, _import);
        }
        if(fCapac_.isChgtTypeByDamage()){
            creatureCible_.affecterTypes(typeAttaque_);
            _fight.addChangedTypesMessage(_cible, typeAttaque_, _import);
        }
    }

    static void enableFighterHavingToUseAbility(
            Fight _fight,
            TeamPosition _lanceur, TeamPosition _cible,
            boolean _enabledTargetAbility,
            String _attaqueLanceur, DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        AbilityData fCapacLanceur_=creatureLanceur_.ficheCapaciteActuelle(_import);
        if (fCapacLanceur_.isTakeItemByDamagingMove() && !creatureLanceur_.possedeObjet()) {
            creatureLanceur_.backUpObject(creatureCible_.getItem());
            _fight.addSwitchItemsMessage(_lanceur, DataBase.EMPTY_STRING, creatureCible_.getItem(), _import);
            _fight.addSwitchItemsMessage(_cible, creatureCible_.getItem(), DataBase.EMPTY_STRING, _import);
            creatureCible_.useObject();
        }
        if (creatureCible_.estKo()) {
            return;
        }
        for (Statistic s: fCapacLanceur_.getLowStatFoeHit().getKeys()) {
            byte varBase_ = fCapacLanceur_.getLowStatFoeHit().getVal(s);
            if (!FightSuccess.successChangedStatistic(_fight, _lanceur, _cible, s, varBase_, _import)) {
                continue;
            }
            byte var_ = deltaBoostStatistic(_fight, _cible, s, varBase_, _import);
            creatureCible_.variationBoostStatistique(s, var_);
            _fight.addStatisticMessage(_cible, s, var_, _import);
        }
        DamagingMoveData fAttaqueLanceur_ = (DamagingMoveData) _import.getMove(_attaqueLanceur);
        if(!fCapacLanceur_.getSingleStatus().events().isEmpty()&&fAttaqueLanceur_.isDirect()){
            MonteCarloString loi_=fCapacLanceur_.getSingleStatus();
            _fight.getSufferingTargetStatus().clear();
            _fight.addEffectStatus(_lanceur, _cible);
            processStatusLaw(_fight,_lanceur,_cible,loi_,fCapacLanceur_.getFailStatus(),_import);
            if(!_fight.getSufferingTargetStatus().isEmpty()){
                StringMap<String> fails_ = new StringMap<String>();
                if (creatureCible_.capaciteActive()) {
                    fails_ = creatureCible_.ficheCapaciteActuelle(_import).getFailStatus();
                }
                StringMap<String> failsObj_ = new StringMap<String>();
                if (FightItems.canUseItsObject(_fight, _cible, _import)) {
                    Item obj_ = creatureCible_.ficheObjet(_import);
                    if (obj_ instanceof ItemForBattle) {
                        ItemForBattle o_ = (ItemForBattle) obj_;
                        failsObj_.putAllMap(o_.getFailStatus());
                    }
                }
                synchronizeStatus(_fight,_lanceur,_cible,failsObj_,fails_,_import);
            }
        }
        if(fCapacLanceur_.isMumy()&&fAttaqueLanceur_.isDirect()){
            if(_enabledTargetAbility){
                AbilityData fCapacCible_=creatureCible_.ficheCapaciteActuelle(_import);
                if(!fCapacCible_.isPlate()){
                    FightAbilities.disableAbility(_fight, _cible, creatureLanceur_.getCurrentAbility(), _import);
                    FightAbilities.enableAbility(_fight, _cible, _import);
                }
            }else{
                FightAbilities.disableAbility(_fight, _cible, creatureLanceur_.getCurrentAbility(), _import);
                FightAbilities.enableAbility(_fight, _cible, _import);
            }
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
        Rate degatsReculContreCible_=Rate.zero();
        if(creatureLanceur_.capaciteActive()){
            AbilityData fCapacLanceur_=creatureLanceur_.ficheCapaciteActuelle(_import);
            if(!fCapacLanceur_.getRecoilDamageFoe().isZero()&&fAttaqueLanceur_.isDirect()){
                degatsReculContreCible_.addNb(Rate.multiply(fCapacLanceur_.getRecoilDamageFoe(),creatureCible_.pvMax()));
            }
        }
        boolean peutUtiliserObjetLanceur_=FightItems.canUseItsObject(_fight,_lanceur,_import);
        if(peutUtiliserObjetLanceur_){
            Item objet_=creatureLanceur_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                if(!objetAttachable_.getDamageRecoil().isZero()&&fAttaqueLanceur_.isDirect()){
                    degatsReculContreCible_.addNb(Rate.multiply(objetAttachable_.getDamageRecoil(),creatureCible_.pvMax()));
                }
            }
        }
        boolean utilisationBaieLanceur_=false;
        if(FightItems.canUseItsBerry(_fight,_lanceur,_import)){
            Berry baie_=(Berry)creatureLanceur_.ficheObjet(_import);
            if(baie_.getDamageRateRecoilFoe().contains(fAttaqueLanceur_.getCategory())){
                degatsReculContreCible_.addNb(Rate.multiply(baie_.getDamageRateRecoilFoe().getVal(fAttaqueLanceur_.getCategory()),creatureCible_.pvMax()));
                utilisationBaieLanceur_=true;
            }
        }
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
                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    _fight.setAnimationKoFighter(creatureLanceur_.estKo(), creatureCible_.estKo(), remainHp_);
                    return;
                }
                utilisationBaieLanceur_=false;
            } else {
                creatureCible_.variationLeftHp(degatsReculContreCible_.opposNb());
                _fight.addHpMessage(_cible, _import);
            }
            _fight.getDamageKo().addNb(degatsReculContreCible_);
        }
        if(utilisationBaieLanceur_){
            creatureLanceur_.useObject();
        }
    }

    static void enableBoostEffectWhileKoTarget(Fight _fight, TeamPosition _lanceur, String _move, DataBase _import) {
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        MoveData fAttaqueLanceur_ = _import.getMove(_move);
        EffectDamage eff_ = (EffectDamage) fAttaqueLanceur_.getEffet(fAttaqueLanceur_.indexOfPrimaryEffect());
        for (Statistic s: eff_.getBoostStatisOnceKoFoe().getKeys()) {
            byte varBoost_ = eff_.getBoostStatisOnceKoFoe().getVal(s);
            byte var_ = deltaBoostStatistic(_fight,_lanceur,s,varBoost_,_import);
            creatureLanceur_.variationBoostStatistique(s,var_);
            _fight.addStatisticMessage(_lanceur, s, var_, _import);
        }
    }
    static void enableAbilityWhileKoTarget(Fight _fight, TeamPosition _lanceur, DataBase _import) {
        Fighter creatureLanceur_ = _fight.getFighter(_lanceur);
        if (!creatureLanceur_.capaciteActive()) {
            return;
        }
        AbilityData fCapacLanceur_=creatureLanceur_.ficheCapaciteActuelle(_import);
        for(Statistic c:creatureLanceur_.getStatisBoost().getKeys()){
            if(!fCapacLanceur_.getMultStatIfKoFoe().contains(c)){
                continue;
            }
            byte varBoost_=fCapacLanceur_.getMultStatIfKoFoe().getVal(c);
            byte var_=deltaBoostStatistic(_fight,_lanceur,c,varBoost_,_import);
            creatureLanceur_.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_lanceur, c, var_, _import);
        }
    }

    static void effectSwitchPosition(Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        byte placeTerrainLanceur_=creatureLanceur_.getGroundPlace();
        byte placeTerrainLanceurPourRemplacement_=creatureLanceur_.getGroundPlaceSubst();
        byte placeTerrainCible_=creatureCible_.getGroundPlace();
        byte placeTerrainCiblePourRemplacement_=creatureCible_.getGroundPlaceSubst();
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
        for(String c:equipeAdv_.getEnabledMoves().getKeys()){
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //desactiver attaques adv
                equipeAdv_.desactiverEffetEquipe(c);
                _fight.addDisabledTeamMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
        for(String c:equipeAdv_.getEnabledMovesWhileSendingFoe().getKeys()){
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //disable team foe moves sending
                equipeAdv_.supprimerEffetEquipeEntreeAdv(c);
                _fight.addDisabledTeamUsesMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
        for (String c:equipeAdv_.getNbUsesMoves().getKeys()) {
            if(StringUtil.contains(_effet.getDisableFoeTeamEffects(), c)){
                //disable team foe moves sending
                equipeAdv_.getNbUsesMoves().put(c, 0);
                _fight.addDisabledTeamUsesMoveMessage(Fight.foe(_combattant.getTeam()), c, _import);
            }
        }
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
            for (byte f: equipeAdv_.getMembers().getKeys()) {
                Fighter f_ = equipeAdv_.getMembers().getVal(f);
                f_.getTrappingMoves().getVal(new MoveTeamPosition(k.getMove(), _combattant)).keepEnabled(false);
                _fight.addDisabledMoveRelMessage(_combattant, k.getMove(), new TeamPosition(Fight.foe(_combattant.getTeam()), f), _import);
            }
        }
        //activer attaque
        equipe_.activerEffetEquipe(_attaque);
        _fight.addEnabledTeamMoveMessage(_combattant.getTeam(), _attaque, _import);
        for(String c:_effet.getProtectAgainstStatus()){
            for(byte c2_:equipe_.getMembers().getKeys()){
                Fighter creature_=equipe_.refPartMembres(c2_);
                if(creature_.isSingleStatus(c)){
                    creature_.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, new TeamPosition(_combattant.getTeam(), c2_), _import);
                }else{
                    creature_.supprimerPseudoStatut(c);
                    _fight.addDisabledStatusOtherRelMessage(c, new TeamPosition(_combattant.getTeam(), c2_), _import);
                }
            }
        }
        byte baseBoost_=(byte)_import.getDefaultBoost();
        for(Statistic c:_effet.getProtectAgainstLowStat()){
            for(byte c2_:equipe_.getMembers().getKeys()){
                Fighter creature_=equipe_.refPartMembres(c2_);
                byte boost_=creature_.getStatisBoost().getVal(c);
                if(boost_<baseBoost_){
                    creature_.variationBoostStatistique(c,(byte) (baseBoost_-boost_));
                    _fight.addStatisticMessage(new TeamPosition(_combattant.getTeam(), c2_), c, (long)baseBoost_-boost_, _import);
                }
            }
        }
        for(Statistic c:_effet.getCancelChgtStatFoeTeam()){
            for(byte c2_:equipeAdv_.getMembers().getKeys()){
                Fighter creature_=equipeAdv_.refPartMembres(c2_);
                byte boost_=creature_.getStatisBoost().getVal(c);
                creature_.variationBoostStatistique(c,(byte) (baseBoost_-boost_));
                _fight.addStatisticMessage(new TeamPosition(Fight.foe(_combattant.getTeam()), c2_), c, (long)baseBoost_-boost_, _import);
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
            soinApres_.setNbRounds((byte) 0);
            soinApres_.setLastStacked(soinApres_.isFirstStacked());
            soinApres_.setFirstStacked(true);
        }
        if(effetLoc_ instanceof EffectEndRoundSingleRelation){
            ActivityOfMove actifNbTour_=creatureLanceur_.getTrappingMoves().getVal(new MoveTeamPosition(_move,_finalTarget));
            if(creatureCible_.capaciteActive()){
                AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
                if(fCapac_.isImmuDamageTrappingMoves()){
                    return;
                }
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
        byte base_ = (byte) _import.getDefaultBoost();
        for (Statistic s: _effet.getCancelChgtStat()) {
            for(TeamPosition c:FightOrder.frontFighters(_fight)){
                Fighter creature_= _fight.getFighter(c);
                byte boost_=creature_.getStatisBoost().getVal(s);
                creature_.variationBoostStatistique(s, (byte) (base_ - boost_));
                _fight.addStatisticMessage(c, s, (long)base_ - boost_, _import);
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
        byte remplacant_=creatureLanceur_.getSubstistute();
        if (NumberUtil.eq(remplacant_, Fighter.BACK)) {
            return;
        }
        Fighter partenaire_=equipeLanceur_.refPartMembres(remplacant_);
        partenaire_.effectBatonPass(creatureLanceur_);
        byte placeTerrain_=creatureLanceur_.getGroundPlace();
        byte placeTerrainPourRemplacement_=creatureLanceur_.getGroundPlaceSubst();
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

    static void effectStatisticRandom(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,
            EnumList<Statistic> _statistiques,Rate _rate,boolean _begin, DataBase _import){
        Rate proba_=FightSuccess.probaEffectStatistic(_fight,_lanceur,_rate,_begin,_import);
        if (!randomRate(_fight,_import, proba_, _lanceur)) {
            return;
        }
        effectStatistic(_fight,_lanceur, _cible, _effet, _statistiques, _import);
    }
    static boolean randomRate(Fight _fight, DataBase _import, Rate _rate, TeamPosition _thrower) {
        if(_fight.getSimulation()){
            if(NumberUtil.eq(_thrower.getTeam(),Fight.CST_PLAYER)){
                if(Rate.strLower(_rate, DataBase.determinatedRate())){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.RANDOM);
                    return false;
                }
            }
        }
        return FightSuccess.tirage(_import, _rate);
    }

    static void effectStatistic(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatistic _effet,EnumList<Statistic> _statistiques,DataBase _import){
        EnumMap<Statistic,Byte> varStatisCran_=_effet.getStatisVarRank();
        Fighter creatureCible_= _fight.getFighter(_cible);
        if(!_effet.getLawBoost().events().isEmpty()){
            MonteCarloEnum<Statistic> loi_ = new MonteCarloEnum<Statistic>();
            int nbEvts_ = _effet.getLawBoost().nbEvents();
            for (int i = 0; i < nbEvts_; i++) {
                Statistic e_ = _effet.getLawBoost().getEvent(i);
                if (Statistic.containsStatistic(_statistiques,e_)) {
                    loi_.addQuickEvent(e_,_effet.getLawBoost().getFreq(i));
                }
            }
//            for (Statistic e:_effet.getLawBoost().events()) {
//                if (Statistic.containsStatistic(_statistiques,e)) {
//                    loi_.addQuickEvent(e,_effet.getLawBoost().rate(e));
//                }
//            }
            if (!FightSuccess.isBadSimulation(_fight, loi_)) {
                Statistic statistique_= FightSuccess.random(_import, loi_);
                byte delta_=deltaBoostStatistic(_fight,_cible,statistique_,varStatisCran_.getVal(statistique_),_import);
                creatureCible_.variationBoostStatistique(statistique_,delta_);
                _fight.addAnimationStatistic(statistique_, delta_, false);
                _fight.addStatisticMessage(_cible, statistique_, delta_, _import);
            }
        }
        if(!varStatisCran_.isEmpty()){
            EnumMap<Statistic,Byte> vars_ = new EnumMap<Statistic,Byte>();
            for (EntryCust<Statistic,Byte> e: varStatisCran_.entryList()) {
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
            if (FightItems.canUseItsBerry(_fight,_cible, _import)) {
                FightItems.enableBerryStatistic(_fight,_cible, creatureCible_.getItem(), true, true, _import);
            }
        }
        if(!_effet.getCopyBoost().isEmpty()){
            //copieBoost
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            for(Statistic e:_statistiques){
                byte boostLanceur_=creatureLanceur_.getStatisBoost().getVal(e);
                byte boostCible_=creatureCible_.getStatisBoost().getVal(e);
                byte delta_=(byte) (boostCible_ - boostLanceur_);
                creatureLanceur_.variationBoostStatistique(e,delta_);
                _fight.addAnimationStatistic(e, delta_, false);
                _fight.addStatisticMessage(_lanceur, e, delta_, _import);
            }
        }
        if(!_effet.getCancelLowStat().isEmpty()||!_effet.getCancelChgtStat().isEmpty()){
            for(Statistic e:_statistiques){
                byte boost_=creatureCible_.getStatisBoost().getVal(e);
                creatureCible_.variationBoostStatistique(e,(byte) -boost_);
                _fight.addAnimationStatistic(e, (byte) -boost_, false);
                _fight.addStatisticMessage(_cible, e, -boost_, _import);
            }
        }
        if(!_effet.getSwapBoostStatis().isEmpty()){
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            for(Statistic e:_statistiques){
                byte boostLanceur_=creatureLanceur_.getStatisBoost().getVal(e);
                byte boostCible_=creatureCible_.getStatisBoost().getVal(e);
                byte varCible_=(byte) (boostLanceur_-boostCible_);
                byte varLanceur_=(byte) (boostCible_-boostLanceur_);
                creatureCible_.variationBoostStatistique(e,varCible_);
                _fight.addStatisticMessage(_cible, e, varCible_, _import);
                creatureLanceur_.variationBoostStatistique(e,varLanceur_);
                _fight.addStatisticMessage(_lanceur, e, varLanceur_, _import);
                _fight.addAnimationStatistic(e, IndexConstants.FIRST_INDEX, true);
            }
        }
    }

    static void effectStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,EffectStatus _effet,Difficulty _diff,DataBase _import){
        _fight.getSufferingTargetStatus().clear();
        if(_effet.getStatusFromUser()){
            StringList statutsTranferes_=new StringList();
            Fighter creatureCible_=_fight.getFighter(_cible);
            Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
            for(String c:creatureCbtLanceur_.getStatusSet()){
                if(FightSuccess.successfulAffectedStatus(_fight,_lanceur,_cible,c,_import)){
                    statutsTranferes_.add(c);
                }
            }
            _fight.getSufferingTargetStatus().addAllElts(statutsTranferes_);
            for(String e:statutsTranferes_){
                creatureCible_.affecterStatut(e);
                _fight.addStatusMessage(_cible, e, _import);
                creatureCbtLanceur_.supprimerStatut(e);
                _fight.addDisabledStatusMessage(e, _lanceur, _import);
            }
        }
        if(!_effet.getDeletedStatus().isEmpty()){
            Fighter creatureCible_=_fight.getFighter(_cible);
            for(String e:_effet.getDeletedStatus()){
                if(creatureCible_.isSingleStatus(e)){
                    creatureCible_.supprimerStatut(e);
                    _fight.addDisabledStatusMessage(e, _cible, _import);
                }else{
                    creatureCible_.supprimerPseudoStatut(e);
                    _fight.addDisabledStatusOtherRelMessage(e, _cible, _import);
                }
                _fight.getSufferingTargetStatus().removeString(e);
            }
        }
        if(_effet.getKoUserHealSubst()){
            Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
            Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
            if (NumberUtil.eq(creatureCbtLanceur_.getSubstistute(), Fighter.BACK)) {
                return;
            }
            byte place_ = creatureCbtLanceur_.getGroundPlace();
            byte placeSub_ = creatureCbtLanceur_.getGroundPlaceSubst();
            TeamPosition remplacant_=new TeamPosition(_lanceur.getTeam(),creatureCbtLanceur_.getSubstistute());
            FightKo.setKo(_fight,_lanceur,_diff,_import);
            _fight.addAnimationKoFighter(_lanceur);
            creatureCbtLanceur_.exitFrontBattleForBeingSubstitued();
            if(NumberUtil.eq(_lanceur.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
                return;
            }
            //A not knocked out substitute must be selected before using the move
            //the substitute is not knocked out while sent
            //==> it is impossible that the fight is finished
            _fight.setFullHealing(true);
            FightSending.sending(_fight,remplacant_,_diff,_import);
            Fighter creatureCbtRemplacant_=equipeLanceur_.refPartMembres(remplacant_.getPosition());
            //soin du remplacant pv,pp,statuts
            creatureCbtRemplacant_.clearMessages();
            creatureCbtRemplacant_.fullHeal(_import);
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
            _fight.addComment(creatureCbtRemplacant_.getComment());
            _fight.setFullHealing(false);
            return;
        }
        MonteCarloString loiStatuts_=_effet.getLawStatus();
        StringMap<String> echecStatuts_=_effet.getLocalFailStatus();
        if(!loiStatuts_.events().isEmpty()){
            processStatusLaw(_fight,_lanceur,_cible,loiStatuts_,echecStatuts_,_import);
        }
        if(_fight.getSufferingTargetStatus().isEmpty()){
            return;
        }
        synchronizeStatus(_fight,_lanceur,_cible,echecStatuts_,echecStatuts_,_import);
    }

    static void effectDamageRate(Fight _fight,TeamPosition _cible,EffectDamageRate _effet,Difficulty _diff,DataBase _import){
        Rate coeff_=DataBase.defRateProduct();
        Rate varPv_=Rate.zero();
        Fighter creatureCible_=_fight.getFighter(_cible);
        if(_effet.getRateDamage().isZeroOrGt()){
            if(!FightKo.canBeHealed(_fight,_cible.getTeam(),_import)){
                return;
            }
            if(FightItems.canUseItsObject(_fight,_cible,_import)){
                Item objet_=creatureCible_.ficheObjet(_import);
                if(objet_ instanceof ItemForBattle){
                    ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                    if(!objetAttachable_.getMultDrainedHp().isZero()){
                        coeff_.multiplyBy(objetAttachable_.getMultDrainedHp());
                    }
                }
            }
            coeff_.multiplyBy(_effet.getRateDamage());
            Rate somme_=Rate.zero();
            for(TeamPosition c:_fight.getDamageByCurrentUser().getKeys()){
                Fighter creatureCibleLoc_=_fight.getFighter(c);
                if (!creatureCibleLoc_.estKo()) {
                    if(canReverseAbsorb(_fight, _cible, c, _import)){
                        somme_.removeNb(_fight.getDamageByCurrentUser().getVal(c));
                        continue;
                    }
                }
                somme_.addNb(_fight.getDamageByCurrentUser().getVal(c));
                _fight.addEffectAbsorb(c, _cible);
            }
            varPv_.affect(Rate.multiply(somme_,coeff_));
        }else{
            if(creatureCible_.capaciteActive()){
                AbilityData fCapacLanceur_=creatureCible_.ficheCapaciteActuelle(_import);
                if(fCapacLanceur_.isImmuDamageRecoil()){
                    return;
                }
            }
            coeff_.multiplyBy(_effet.getRateDamage());
            Rate somme_=Rate.zero();
            for(TeamPosition c:_fight.getDamageByCurrentUser().getKeys()){
                somme_.addNb(_fight.getDamageByCurrentUser().getVal(c));
            }
            varPv_.affect(Rate.multiply(somme_,coeff_));
        }
        if(varPv_.isZeroOrGt()){
            creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import);
        }else if(Rate.greaterEq(varPv_.absNb(),creatureCible_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            _fight.addEffectRecoil(_cible, _effet);
            _fight.setAnimationKoFighterEffectDamage(_cible, _effet);
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            _fight.addEffectRecoil(_cible);
            creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import);
        }
    }

    static void effectMultiplyUsedMovePower(Fight _fight,TeamPosition _cible,EffectMultUsedMovePower _effet, String _move, DataBase _import){
        Fighter creatureCible_=_fight.getFighter(_cible);
        _fight.addEnabledMoveMessage(_cible, _move, _import);
        for(String c:_effet.getMultMovePowerFctType().getKeys()){
            creatureCible_.getDamageRateInflictedByType().getVal(c).multiplyBy(_effet.getMultMovePowerFctType().getVal(c));
        }
    }

    static void effectMultiplyUndergoneMovePower(Fight _fight,TeamPosition _cible,EffectMultSufferedMovePower _effet, String _move, DataBase _import){
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
            creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import);
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
            if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
                return;
            }
            if(FightKo.endedFight(_fight,_diff)){
                return;
            }
        }else{
            creatureCible_.variationLeftHp(varPv_);
            _fight.addHpMessage(_cible, _import);
            _fight.getEffects().removeLast();
            _fight.addEffectRecoil(_cible);
        }
        //ex: REBONDIFEU
        Rate varPvAdj_=_effet.getClosestFoeDamageRateHp();
        for(TeamPosition c:FightOrder.closestFigthersSameTeam(_fight,_cible,_diff)){
            Fighter partenaire_=equipeCible_.refPartMembres(c.getPosition());
            Rate varPvMembresAdj_=Rate.multiply(varPvAdj_,partenaire_.pvMax());
            if (varPvMembresAdj_.isZero()) {
                continue;
            }
            if(Rate.strGreater(varPvMembresAdj_, partenaire_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,c,_diff,_import);
                _fight.addAnimationKoFighter(c);
                if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
            }else{
                partenaire_.variationLeftHp(varPvMembresAdj_.opposNb());
                _fight.addEffectRecoil(c);
                _fight.addHpMessage(c, _import);
            }
        }
    }

    static void effectLeftHpRate(Fight _fight,TeamPosition _target,EffectRemainedHpRate _effet,Difficulty _diff,DataBase _import){
        Rate coeff_=_effet.getRateHp();
        Fighter creatureLanceur_=_fight.getFighter(_target);
        Rate varPv_=Rate.multiply(creatureLanceur_.getRemainingHp(),coeff_);
        if(varPv_.isZeroOrGt()){
            creatureLanceur_.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import);
            _fight.getEffects().removeLast();
            AnimationHealing animationHeal_;
            animationHeal_ = new AnimationHealing();
            animationHeal_.setIndex(_fight.getEffects().size());
            animationHeal_.setHealed(new TargetCoords(_target.getTeam(), creatureLanceur_.getGroundPlaceSubst()));
            _fight.getEffects().add(animationHeal_);
        }else if(Rate.greaterEq(varPv_.absNb(),creatureLanceur_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_target,_diff,_import);
            _fight.addAnimationKoFighter(_target);
            if(NumberUtil.eq(_target.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            creatureLanceur_.variationLeftHp(varPv_);
            _fight.addHpMessage(_target, _import);
            _fight.addEffectRecoil(_target);
        }
    }

    static void effectVarPp(Fight _fight,TeamPosition _cible,EffectVarPP _effet,Difficulty _diff, DataBase _import){
        //derniere attaque de la cible
        Fighter creatureCible_=_fight.getFighter(_cible);
        String attaque_=creatureCible_.getFirstChosenMove();
        short var_=_effet.getDeletePp();
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
        String statut_=FightSuccess.random(_import, loiGeneree_);
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
            if(e_.isEmpty()){
                LgInt proba_ = rateNoStatus(loiTmp_);
                proba_.addNb(_statuts.getFreq(i));
                loiTmp_.put(DataBase.EMPTY_STRING,proba_);
                continue;
            }
            if(_echecStatuts.contains(e_) && !_echecStatuts.getVal(e_).isEmpty()){
                StringMap<String> values_ = FightValues.calculateValues(_fight,_lanceur,_cible,_import);
                if (_import.evaluateBoolean(_echecStatuts.getVal(e_), values_, false)) {
                    LgInt proba_ = rateNoStatus(loiTmp_);
                    proba_.addNb(_statuts.getFreq(i));
                    loiTmp_.put(DataBase.EMPTY_STRING,proba_);
                    continue;
                }
            }
            if(FightSuccess.successfulAffectedStatus(_fight,_lanceur,_cible,e_,_import)){
                loiTmp_.put(e_,new LgInt(_statuts.getFreq(i)));
            }else{
                LgInt proba_ = rateNoStatus(loiTmp_);
                proba_.addNb(_statuts.getFreq(i));
                loiTmp_.put(DataBase.EMPTY_STRING,proba_);
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
                _fight.getSufferingTargetStatus().add(_statut);
            }
            creatureCible_.affecterStatut(_statut);
            _fight.addStatusMessage(_cible, _statut, _import);
        }else{
            if (NumberUtil.eq(creatureCible_.getStatusRelatNbRoundShort(new MoveTeamPosition(_statut, _lanceur)), 0)) {
                _fight.getSufferingTargetStatus().add(_statut);
            }
            creatureCible_.affecterPseudoStatut(_lanceur,_statut);
            _fight.addStatusRelMessage(_cible, _statut, _lanceur, _import);
        }
        if (creatureCible_.capaciteActive()) {
            AbilityData fCapacCible_=creatureCible_.ficheCapaciteActuelle(_import);
            for(String e:_fight.getSufferingTargetStatus()){
                for(Statistic c:creatureCible_.getStatisBoost().getKeys()){
                    if(fCapacCible_.getMultStatIfStatutRank().contains(new StatisticStatus(c,e))){
                        byte varBoost_=fCapacCible_.getMultStatIfStatutRank().getVal(new StatisticStatus(c,e));
                        byte var_=deltaBoostStatistic(_fight,_cible,c,varBoost_,_import);
                        creatureCible_.variationBoostStatistique(c,var_);
                        _fight.addStatisticMessage(_cible, c, var_, _import);
                    }
                }
            }
        }
        if (FightItems.canUseItsBerry(_fight,_cible, _import)) {
            FightItems.enableBerryStatus(_fight,_cible, creatureCible_.getItem(), true, _import);
        }
    }

    static void synchronizeStatus(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,StringMap<String> _echecStatuts,StringMap<String> _echecStatutsAb,DataBase _import){
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        for(String e:_fight.getSufferingTargetStatus()){
            Status statutLoc_=_import.getStatus().getVal(e);
            if (!creatureCbtLanceur_.isStatusRelat(new MoveTeamPosition(e, _cible))) {
                continue;
            }
            if(!statutLoc_.estActifPartenaire()){
                continue;
            }
            if(!NumberUtil.eq(_lanceur.getTeam(),_cible.getTeam())){
                continue;
            }
            EffectPartnerStatus effet_=statutLoc_.getEffectsPartner().first();
            if(effet_.getWeddingAlly()){
                creatureCbtLanceur_.affecterPseudoStatut(_cible,e);
                _fight.addStatusRelMessage(_lanceur, e, _cible, _import);
            }
        }
        if (FightItems.canUseItsObject(_fight, _cible, _import)) {
            Fighter creatureCbtCible_=_fight.getFighter(_cible);
            Item obj_ = creatureCbtCible_.ficheObjet(_import);
            if (obj_ instanceof ItemForBattle) {
                ItemForBattle o_ = (ItemForBattle) obj_;
                for(String e:_fight.getSufferingTargetStatus()){
                    if(!StringUtil.contains(o_.getSynchroStatus(), e)){
                        continue;
                    }
                    affectStatusToThrower(_fight, _lanceur, e, _cible, _echecStatuts, _import);
                }
            }
        }
        if(FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import)){
            return;
        }
        Fighter creatureCbtCible_=_fight.getFighter(_cible);
        AbilityData fCapacCible_=creatureCbtCible_.ficheCapaciteActuelle(_import);
        //capacite SYNCHRO... (capacite ou objet)
        for(String e:_fight.getSufferingTargetStatus()){
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

    static EnumMap<Statistic,Byte> deltaBoostStatisticMap(Fight _fight, TeamPosition _combattant,EnumMap<Statistic,Byte> _varBase,DataBase _import) {
        EnumMap<Statistic,Byte> map_ = new EnumMap<Statistic,Byte>();
        for (Statistic s: _varBase.getKeys()) {
            byte var_ = deltaBoostStatistic(_fight, _combattant, s, _varBase.getVal(s), _import);
            map_.put(s, var_);
        }
        if (map_.isEmpty()) {
            return map_;
        }
        Bytes values_ = new Bytes(map_.values());
        if (values_.getMinimum((byte) (_import.getMaxBoost()+1)) < 0) {
            Fighter fighter_ = _fight.getFighter(_combattant);
            if (fighter_.capaciteActive()) {
                AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
                if (!ab_.getMultStatIfLowStat().isEmpty()) {
                    for (Statistic s: _varBase.getKeys()) {
                        if (_varBase.getVal(s) < 0) {
                            map_.put(s, (byte)0);
                        }
                    }
                    for (Statistic s: ab_.getMultStatIfLowStat().getKeys()) {
                        if (!map_.contains(s)) {
                            map_.put(s, ab_.getMultStatIfLowStat().getVal(s));
                        } else {
                            map_.put(s, (byte)(map_.getVal(s)+ab_.getMultStatIfLowStat().getVal(s)));
                        }
                    }
                }
            }
        }
        return map_;
    }

    static byte deltaBoostStatistic(Fight _fight, TeamPosition _combattant,Statistic _stat,byte _varBase,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        Rate var_=new Rate(_varBase);
        if(creature_.capaciteActive()){
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
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
        byte maxBoost_=(byte)_import.getMaxBoost();
        byte minBoost_=(byte)_import.getMinBoost();
        byte boostActuel_=creature_.getStatisBoost().getVal(_stat);
        if(var_.isZeroOrGt()){
            if(boostActuel_+var_.ll()>maxBoost_){
                return (byte)(maxBoost_-boostActuel_);
            }
            return (byte)var_.ll();
        }
        if(boostActuel_+var_.ll()<minBoost_){
            return (byte)(minBoost_-boostActuel_);
        }
        return (byte)var_.ll();
    }

    static Rate criticalHitEvent(Fight _fight, TeamPosition _thrower, Rate _max, DataBase _import) {
        Fighter user_ = _fight.getFighter(_thrower);
        Rate event_ = new Rate(_max);
        Rate rate_ = FightStatistic.multiplyStatisticPartner(_fight,Statistic.CRITICAL_HIT, _thrower.getTeam(), _import);
        if (rate_.greaterThanOne()) {
            event_.multiplyBy(rate_);
        }
        if(user_.capaciteActive()){
            AbilityData fCapac_=user_.ficheCapaciteActuelle(_import);
            if(!fCapac_.getMultDamageCh().isZero()){
                event_.multiplyBy(fCapac_.getMultDamageCh());
            }
        }
        return event_;
    }

    static Rate remainingHp(String _move, DataBase _import) {
        Rate pv_ = Rate.zero();
        MoveData fAttaque_=_import.getMove(_move);
        int nbEffets_=fAttaque_.nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttaque_.getEffet(i);
            if(!(effet_ instanceof EffectProtection)){
                continue;
            }
            EffectProtection effetProt_=(EffectProtection)effet_;
            pv_.affect(effetProt_.getProtSingleAgainstKo());
            break;
        }
        return pv_;
    }

    static boolean canReverseAbsorb(Fight _fight, TeamPosition _thrower, TeamPosition _target, DataBase _import) {
        Fighter creatureCible_=_fight.getFighter(_target);
        if (!FightAbilities.ignoreTargetAbility(_fight, _thrower, _target, _import)) {
            AbilityData ab_ = creatureCible_.ficheCapaciteActuelle(_import);
            return ab_.isInflictingDamageInsteadOfSuffering();
        }
        return false;
    }
}
