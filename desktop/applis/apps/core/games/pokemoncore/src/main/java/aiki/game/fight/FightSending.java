package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.status.Status;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.RandomBoolResults;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EnumList;
import code.util.AbsMap;
import code.util.EqList;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightSending {

    private FightSending() {
    }

    static void sendBeginTeam(Fight _fight,byte _noTeam,DataBase _import) {
        for(TeamPosition e: FightOrder.fighters(_fight,_noTeam)){
            Fighter creatureCbt_=_fight.getFighter(e);
            if(creatureCbt_.estArriere()){
                //si arriere passe a la suite
                continue;
            }
            sendBegin(_fight,e,_import);
        }
    }

    static void firstEffectWhileSendingTeams(Fight _fight,Difficulty _diff,DataBase _import) {
        firstEffectWhileSendingTeam(_fight,Fight.CST_FOE,_diff,_import);
        firstEffectWhileSendingTeam(_fight,Fight.CST_PLAYER,_diff,_import);
    }

    static void firstEffectWhileSendingTeam(Fight _fight,byte _noTeam, Difficulty _diff,DataBase _import) {
        for(TeamPosition e: FightOrder.fighters(_fight,_noTeam)){
            Fighter creatureCbt_=_fight.getFighter(e);
            if(creatureCbt_.estArriere()){
                //si arriere passe a la suite
                continue;
            }
            FightAbilities.enableAbilityByWeather(_fight, e, _import);
            AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
            if(!fCapac_.enabledSending()){
//                FightAbilities.enableAbility(_fight, e, _import);
                continue;
            }
            EffectWhileSendingWithStatistic effetEnvoi_=fCapac_.getEffectSending().first();
            FightSending.effectWhileSendingAbility(_fight,e,effetEnvoi_,_diff,_import);
        }
    }

    static void sendBegin(Fight _fight,TeamPosition _cbtEnvoye,DataBase _import) {
        Team equipeCbtEnvoye_=_fight.getTeams().getVal(_cbtEnvoye.getTeam());
        Team equipeAdvCbtEnvoye_=_fight.getTeams().getVal(Fight.foe(_cbtEnvoye.getTeam()));
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        creatureCbt_.formeNormale(_import);
        if(creatureCbt_.isBelongingToPlayer()){
            for(byte c:equipeAdvCbtEnvoye_.getMembers().getKeys()){
                Fighter fighter_= equipeAdvCbtEnvoye_.getMembers().getVal(c);
                if (fighter_.estArriere()) {
                    continue;
                }
                equipeCbtEnvoye_.ajouterCombattantsContreAdv(_cbtEnvoye.getPosition(),c);
            }
        } else if (NumberUtil.eq(_cbtEnvoye.getTeam(), Fight.CST_FOE)){
            Team team_=_fight.getUserTeam();
            for(byte c:team_.getMembers().getKeys()){
                Fighter creature_=team_.getMembers().getVal(c);
                if(creature_.estArriere()){
                    continue;
                }
                if(!creature_.isBelongingToPlayer()){
                    continue;
                }
                _fight.getUserTeam().ajouterCombattantsContreAdv(c,_cbtEnvoye.getPosition());
            }
        }
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        if(fCapac_.enabledSending()){
            EffectWhileSendingWithStatistic effetEnvoi_=fCapac_.getEffectSending().first();
            effectWhileSendingBegin(_fight,_cbtEnvoye,effetEnvoi_,_import);
        }

        effectSendingObjectBegin(_fight,_cbtEnvoye,_import);
        effectPlate(_fight,_cbtEnvoye, _import);
        //effetstatistique
        FightEffects.disableStatus(_fight, _cbtEnvoye, _import);
    }

    static void effectWhileSendingBegin(Fight _fight,TeamPosition _cbtEnvoye,EffectWhileSendingWithStatistic _effet,DataBase _import){
        String climat_=_effet.getEnabledWeather();
        if(!climat_.isEmpty()){
            _fight.getStillEnabledMoves().put(climat_,BoolVal.TRUE);
            _fight.getEnabledMoves().getVal(climat_).setEnabled(true);
            _fight.getEnabledMoves().getVal(climat_).reset();
            _fight.addEnabledWeatherMessage(climat_, _import);
            if(!FightMoves.existenceAntiClimatActif(_fight,_import)){
                MoveData fAttGl_=_import.getMove(climat_);
                int nbEffets_=fAttGl_.nbEffets();
                for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                    Effect effet_=fAttGl_.getEffet(i);
                    if(!(effet_ instanceof EffectGlobal)){
                        continue;
                    }
                    EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                    for(String c:effetGlobal_.getCancelEffects()){
                        _fight.getEnabledMoves().getVal(c).disable();
                        _fight.getEnabledMoves().getVal(c).reset();
                        _fight.addDisabledWeatherMessage(c, _import);
                        if(_fight.getStillEnabledMoves().contains(c)){
                            _fight.getStillEnabledMoves().put(c,BoolVal.FALSE);
                        }
                    }
//                    if(effetGlobal_.getPriseEnComptePkLanceur()){
//                        lanceursGlobaux.put(climat_,_cbtEnvoye);
//                    }
                }
            }
        }
        if (!_effet.getMultWeight().isZero()) {
            Fighter creatureCbt_ = _fight.getFighter(_cbtEnvoye);
            creatureCbt_.getWeight().multiplyBy(_effet.getMultWeight());
        }
    }

    static void effectPlate(Fight _fight,TeamPosition _cbtEnvoye,DataBase _import) {
        //copie du type de la plaque
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        if(creatureCbt_.possedeObjet()){
            Item objet_=creatureCbt_.ficheObjet(_import);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle plaque_=(ItemForBattle)objet_;
                if (!plaque_.getTypesPk().isEmpty()) {
                    if (creatureCbt_.capaciteActive()) {
                        if (creatureCbt_.ficheCapaciteActuelle(_import).isPlate()) {
                            creatureCbt_.affecterTypes(plaque_.getTypesPk());
                            _fight.addChangedTypesMessage(_cbtEnvoye, plaque_.getTypesPk(), _import);
                        }
                    }
                }
            }
        }
    }

    static void effectSendingObjectBegin(Fight _fight,TeamPosition _cbtEnvoye,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        if (!creatureCbt_.hasObjectEnabledBeingSent(_import)) {
            return;
        }
        Item objet_=creatureCbt_.ficheObjet(_import);
        ItemForBattle objetAttachableCombat_=(ItemForBattle)objet_;
        EffectWhileSendingWithStatistic effetEnvoi_=objetAttachableCombat_.getEffectSending().first();
        effectWhileSendingBegin(_fight,_cbtEnvoye,effetEnvoi_,_import);
    }

    static void sendSubstitutes(Fight _fight, Difficulty _diff,Player _user,DataBase _import){
        _fight.setError(false);
        if (_fight.getState() != FightState.SWITCH_WHILE_KO_USER) {
            if(!FightRules.substitutable(_fight,_diff, _import)){
                _fight.setError(true);
                return;
            }
        }
        boolean tombeKo_=false;
        Team equipeAdv_=_fight.getFoeTeam();
        for(byte c:equipeAdv_.getMembers().getKeys()){
            Fighter membre_=equipeAdv_.refPartMembres(c);
            if (!membre_.estKo()) {
                membre_.setGroundPlace(membre_.getGroundPlaceSubst());
            }
        }
        for(byte c:equipeAdv_.getMembers().getKeys()){
            Fighter membre_=equipeAdv_.refPartMembres(c);
            byte pos_ = _fight.getFirstPositFoeFighters().getVal(c);
            if (!NumberUtil.eq(pos_,Fighter.BACK)) {
                //must stay at front
                continue;
            }
            membre_.exitFrontBattleForBeingSubstitued();
            membre_.exitFrontBattle();
            withdrawal(_fight, Fight.toFoeFighter(c),_import);
        }
        for(byte c:equipeAdv_.getMembers().getKeys()){
            Fighter membre_=equipeAdv_.refPartMembres(c);
            byte pos_ = _fight.getFirstPositFoeFighters().getVal(c);
            if (NumberUtil.eq(pos_,Fighter.BACK)) {
                //not sent
                continue;
            }
            membre_.setGroundPlaceSubst(pos_);
            membre_.setGroundPlace(pos_);
            sending(_fight,Fight.toFoeFighter(c),_diff,_import);
            if (FightKo.endedFight(_fight,_diff)) {
                FightEndRound.proponeMovesEvolutions(_fight,_user,_diff,_import);
                return;
            }
            if (membre_.estKo()) {
                tombeKo_=true;
            }
        }
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,false)){
            Fighter membre_=_fight.getFighter(c);
            if (!membre_.estKo()) {
                membre_.setGroundPlace(membre_.getGroundPlaceSubst());
            }
        }

        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,false)){
            Fighter membre_=_fight.getFighter(c);
            byte pos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if (!NumberUtil.eq(pos_,Fighter.BACK)) {
                //must stay at front
                continue;
            }
            membre_.exitFrontBattleForBeingSubstitued();
            membre_.exitFrontBattle();
            withdrawal(_fight, c,_import);
        }
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,false)){
            Fighter membre_=_fight.getFighter(c);
            byte pos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if (NumberUtil.eq(pos_,Fighter.BACK)) {
                //not sent
                continue;
            }
            membre_.setGroundPlaceSubst(pos_);
            membre_.setGroundPlace(pos_);
            sending(_fight,c,_diff,_import);
            if (FightKo.endedFight(_fight,_diff)) {
                return;
            }
            if (membre_.estKo()) {
                tombeKo_=true;
            }
        }
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter membre_=_fight.getFighter(c);
            byte pos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if (!NumberUtil.eq(pos_,Fighter.BACK)) {
                //must stay at front
                continue;
            }
            if (membre_.estArriere()) {
                membre_.exitFrontBattleForBeingSubstitued();
                continue;
            }
            membre_.exitFrontBattleForBeingSubstitued();
            membre_.exitFrontBattle();
            withdrawal(_fight, c,_import);
        }
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter membre_=_fight.getFighter(c);
            byte pos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if (NumberUtil.eq(pos_,Fighter.BACK)) {
                continue;
            }
            if (membre_.estArriere()) {
                continue;
            }
            membre_.setGroundPlace(pos_);
            membre_.setGroundPlaceSubst(pos_);
        }
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter membre_=_fight.getFighter(c);
            byte pos_ = _fight.getFirstPositPlayerFighters().getVal(c.getPosition());
            if (NumberUtil.eq(pos_,Fighter.BACK)) {
                continue;
            }
            if (!membre_.estArriere()) {
                continue;
            }
            membre_.setGroundPlaceSubst(pos_);
            membre_.setGroundPlace(pos_);
            sending(_fight,c,_diff,_import);
            if (FightKo.endedFight(_fight,_diff)) {
                return;
            }
            if (membre_.estKo()) {
                tombeKo_=true;
            }
        }
        boolean allKoPlayer_ = true;
        for(TeamPosition c: FightOrder.fightersBelongingToUser(_fight,true)){
            Fighter membre_=_fight.getFighter(c);
            if (!membre_.estKo()) {
                allKoPlayer_ = false;
                break;
            }
        }
        if(tombeKo_){
            if (allKoPlayer_) {
                _fight.setState(FightState.SWITCH_WHILE_KO_USER);
                FightEndRound.setPlacesForFighters(_fight, false);
                if (FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
                    FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
                } else {
                    FightEndRound.setPlacesForFighters(_fight, true);
                }
                return;
            }
            FightEndRound.proponeMovesEvolutions(_fight,_user,_diff,_import);
            if (_fight.getState() != FightState.APPRENDRE_EVOLUER) {
                FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
            }
            return;
        }
        if (!allKoPlayer_) {
            _fight.setState(FightState.ATTAQUES);
        }
        FightEndRound.setPlacesForFighters(_fight, true);
    }

    static void withdrawal(Fight _fight, TeamPosition _cbtRetire,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbtRetire);
        _fight.addWithdrawMessage(_cbtRetire, _import);
        if(creatureCbt_.capaciteActive()){
            AbilityData fCapacite_=creatureCbt_.ficheCapaciteActuelle(_import);
            if(fCapacite_.isHealedStatusBySwitch()){
                for(String c:creatureCbt_.getStatusSet()){
                    creatureCbt_.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, _cbtRetire, _import);
                }
            }
            if(!fCapacite_.getHealedHpRateBySwitch().isZero()){
                Rate pvMax_=creatureCbt_.pvMax();
                Rate pvSoignes_=Rate.multiply(fCapacite_.getHealedHpRateBySwitch(),pvMax_);
                creatureCbt_.variationLeftHp(pvSoignes_);
                _fight.addHpMessage(_cbtRetire, _import);
            }
        }
        disableEffectsExceptHp(_fight, _cbtRetire, _import);
    }

    static void disableEffectsExceptHp(Fight _fight, TeamPosition _cbtRetire,DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbtRetire);
        FightAbilities.disableAbility(_fight,_cbtRetire,creatureCbt_.getAbility(),_import);
        for(String c:creatureCbt_.getStatusSet()){
            Status statut_=_import.getStatus().getVal(c);
            if(statut_.getDisabledEffIfSwitch()){
                creatureCbt_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _cbtRetire, _import);
            }
        }
        creatureCbt_.setNeedingToRecharge(false);
        creatureCbt_.affectNoRoundBeforeUsingMove();
        creatureCbt_.setDisappeared(false);
        creatureCbt_.formeNormale(_import);
        creatureCbt_.initCreatureRelationsAutre(FightOrder.fighters(_fight), _import);
        endRelations(_fight, _cbtRetire, _import);
    }

    static void endRelations(Fight _fight, TeamPosition _cbtRetire, DataBase _import) {
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(t);
            for (MoveTeamPosition m: fighter_.getStatusRelatSet()) {
                if (TeamPosition.eq(m.getTeamPosition(), _cbtRetire)) {
                    short r_ = fighter_.getStatusRelatNbRoundShort(m);
                    fighter_.supprimerPseudoStatutCombattant(_cbtRetire, m.getMove());
                    _fight.addDisabledStatusRelMessage(m.getMove(), t, _cbtRetire, r_, _import);
                }
            }
            MoveTeamPositionsStringList privateMoves_ = fighter_.getPrivateMoves();
            for (MoveTeamPosition m: privateMoves_.getKeys()) {
                if (TeamPosition.eq(m.getTeamPosition(), _cbtRetire)) {
                    boolean wasEnabled_ = !privateMoves_.getVal(m).isEmpty();
                    privateMoves_.getVal(m).clear();
                    _fight.addDisabledMoveRelMessage(_cbtRetire, m.getMove(), t, wasEnabled_, _import);
                }
            }
            MoveTeamPositionsAffectedMove tracking_ = fighter_.getTrackingMoves();
            for (MoveTeamPosition m: tracking_.getKeys()) {
                if (TeamPosition.eq(m.getTeamPosition(), _cbtRetire)) {
                    boolean wasEnabled_ = tracking_.getVal(m).getActivity().isEnabled();
                    tracking_.getVal(m).getActivity().disable();
                    tracking_.getVal(m).setMove(DataBase.EMPTY_STRING);
                    _fight.addDisabledMoveRelMessage(_cbtRetire, m.getMove(), t, wasEnabled_, _import);
                }
            }
            MoveTeamPositionsActivityOfMove trapping_ = fighter_.getTrappingMoves();
            for (MoveTeamPosition m: trapping_.getKeys()) {
                if (TeamPosition.eq(m.getTeamPosition(), _cbtRetire)) {
                    boolean wasEnabled_ = trapping_.getVal(m).isEnabled();
                    trapping_.getVal(m).disable();
                    _fight.addDisabledMoveRelMessage(_cbtRetire, m.getMove(), t, wasEnabled_, _import);
                }
            }
            MoveTeamPositionsBoolVal accuracy_ = fighter_.getIncrUserAccuracy();
            for (MoveTeamPosition m: accuracy_.getKeys()) {
                if (TeamPosition.eq(m.getTeamPosition(), _cbtRetire)) {
                    boolean wasEnabled_ = accuracy_.getVal(m) == BoolVal.TRUE;
                    accuracy_.put(m, BoolVal.FALSE);
                    _fight.addDisabledMoveRelMessage(_cbtRetire, m.getMove(), t, wasEnabled_, _import);
                }
            }
        }
    }

    static void sending(Fight _fight, TeamPosition _cbtEnvoye,Difficulty _diff,DataBase _import){
        _fight.addSendMessage(_cbtEnvoye, _import);
        addFighterAgainstFoeTeam(_fight, _cbtEnvoye, _import);
        effectsTeamWhileSendingFoeFighter(_fight, _cbtEnvoye, _diff, _import);
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        if(fCapac_.enabledSending()){
            EffectWhileSendingWithStatistic effetEnvoi_=fCapac_.getEffectSending().first();
            effectWhileSending(_fight,_cbtEnvoye,effetEnvoi_,_diff,_import);
        }

        effectWhileSendingObject(_fight,_cbtEnvoye,_diff,_import);
        effectPlate(_fight,_cbtEnvoye, _import);
        //effetstatistique
        FightEffects.disableStatus(_fight, _cbtEnvoye, _import);
    }

    static void addFighterAgainstFoeTeam(Fight _fight, TeamPosition _cbtEnvoye,DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        creatureCbt_.initCreatureRelationsAutre(FightOrder.fighters(_fight), _import);
        if(creatureCbt_.isBelongingToPlayer()){
            Team team_=_fight.getFoeTeam();
            for(byte c: team_.getMembers().getKeys()){
                Fighter creature_=team_.getMembers().getVal(c);
                if(creature_.estArriere()){
                    continue;
                }
                _fight.getUserTeam().ajouterCombattantsContreAdv(_cbtEnvoye.getPosition(),c);
            }
        }else if (NumberUtil.eq(_cbtEnvoye.getTeam(), Fight.CST_FOE)){
            Team team_=_fight.getUserTeam();
            for(byte c:team_.getMembers().getKeys()){
                Fighter creature_=team_.getMembers().getVal(c);
                if(creature_.estArriere()){
                    continue;
                }
                if(!creature_.isBelongingToPlayer()){
                    continue;
                }
                _fight.getUserTeam().ajouterCombattantsContreAdv(c,_cbtEnvoye.getPosition());
            }
        }
    }

    static void effectWhileSending(Fight _fight, TeamPosition _cbtEnvoye,EffectWhileSendingWithStatistic _effet,Difficulty _diff,DataBase _import){
        effectWhileSendingBegin(_fight,_cbtEnvoye, _effet, _import);
//        if(!entres){
//            return;
//        }
        effectWhileSendingAbility(_fight,_cbtEnvoye,_effet,_diff,_import);
    }

    static void effectWhileSendingAbility(Fight _fight, TeamPosition _cbtEnvoye,EffectWhileSendingWithStatistic _effect,Difficulty _diff,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        EffectWhileSendingWithStatistic effect_ = _effect;
        if(effect_.getCopyingAbility()){
            TeamPositionList foes_ = FightOrder.closestFoeFighter(_fight,_cbtEnvoye);
            if (foes_.isEmpty()) {
                return;
            }
            String ability_ = _fight.getFighter(foes_.first()).getCurrentAbility();
            //copie de la capac du cbt adv le plus proche
            FightAbilities.disableAbility(_fight, _cbtEnvoye, ability_, _import);
            FightAbilities.enableAbility(_fight,_cbtEnvoye,_import);
            //y compris INTIMIDATION...
            if(!creatureCbt_.capaciteActive()){
                return;
            }
            AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
            if (!fCapac_.enabledSending()) {
                return;
            }
            effect_ = fCapac_.getEffectSending().first();
        }
        EffectStatistic effetStatis_ = effect_.getEffect();
        if (effetStatis_ == null) {
            return;
        }
        for(TeamPosition c:FightOrder.targetsEffect(_fight,_cbtEnvoye,effetStatis_,_diff,_import)){
            EnumList<Statistic> statistiques_=FightSuccess.successfulChangedStatistics(_fight,_cbtEnvoye,c,effetStatis_,_import);
            if(statistiques_.isEmpty()){
                //raisons echec
                continue;
            }
            FightEffects.effectStatisticRandom(_fight,_cbtEnvoye,c,effetStatis_,statistiques_,effetStatis_.getEvtRate(),true,_import);
            if (!_fight.getAcceptableChoices()) {
                return;
            }
        }
    }

    static void effectWhileSendingObject(Fight _fight, TeamPosition _cbtEnvoye,Difficulty _diff,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbtEnvoye);
        if (!FightItems.canUseItsObject(_fight,_cbtEnvoye,_import)) {
            return;
        }
        Item objet_=creatureCbt_.ficheObjet(_import);
        if (!(objet_ instanceof ItemForBattle)) {
            return;
        }
        ItemForBattle objetAttachableCombat_=(ItemForBattle)objet_;
        if(!objetAttachableCombat_.enabledSending()){
            return;
        }
        EffectWhileSendingWithStatistic effetEnvoi_=objetAttachableCombat_.getEffectSending().first();
        effectWhileSending(_fight,_cbtEnvoye,effetEnvoi_,_diff,_import);
    }

    static void effectsTeamWhileSendingFoeFighter(Fight _fight, TeamPosition _cbt,Difficulty _diff,DataBase _import) {
        byte noTeam_ = _cbt.getTeam();
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        Team equipeAdvCbtEnvoye_=_fight.getTeams().getVal(Fight.foe(noTeam_));
        StringMap<LgInt> nbUtilisationsEntreeAdv_=equipeAdvCbtEnvoye_.getEnabledMovesWhileSendingFoeUses();
        //effetentreeadv
        for(String c:nbUtilisationsEntreeAdv_.getKeys()){
            if(nbUtilisationsEntreeAdv_.getVal(c).isZero()){
                continue;
            }
            //if(effetEquipeEntreeAdv_.getDeletedByFoeTypes()) ==> supprimer l'effet et passer a la suite
            MoveData fAtt_=_import.getMove(c);
            int nbEffets_=fAtt_.nbEffets();
            for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAtt_.getEffet(i);
                if(!(effet_ instanceof EffectTeamWhileSendFoe)){
                    continue;
                }
                EffectTeamWhileSendFoe effetEquipeEntreeAdv_=(EffectTeamWhileSendFoe)effet_;
                boolean passerReussiteEffet_=false;
                for(String e:creatureCbt_.getTypes()){
                    if(StringUtil.contains(effetEquipeEntreeAdv_.getDeletedByFoeTypes(), e)){
                        equipeAdvCbtEnvoye_.supprimerEffetEquipeEntreeAdv(c);
                        passerReussiteEffet_=true;
                        break;
                    }
                }
                if(passerReussiteEffet_){
                    continue;
                }
                _fight.setSending(true);
                RandomBoolResults res_=FightSuccess.successfulMove(_fight,_cbt,_cbt,c,i,true,_import);
                if(!res_.isSuccessful()){
                    continue;
                }
                //action picots,piege de roc,pics toxiks
                effectTeamWhileSendingFoeFighter(_fight,_cbt,c,effetEquipeEntreeAdv_,_diff,_import);
                if(!_fight.getAcceptableChoices()){
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    return;
                }
            }
        }
    }
    static void effectTeamWhileSendingFoeFighter(Fight _fight, TeamPosition _cbt,String _attaque,EffectTeamWhileSendFoe _effet,Difficulty _diff,DataBase _import){
        if(_fight.getFullHealing()){
            return;
        }
        //successChangedStatistic
        Team equipeAdvCbtEnvoye_=_fight.getTeams().getVal(Fight.foe(_cbt.getTeam()));
        StringMap<LgInt> nbUtilisationsEntreeAdv_=equipeAdvCbtEnvoye_.getEnabledMovesWhileSendingFoeUses();
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        AbsMap<Statistic,Byte> vars_ = new IdMap<Statistic,Byte>();
        for (Statistic s: _effet.getStatistics().getKeys()) {
            byte varBase_ = _effet.getStatistics().getVal(s);
            if (!FightSuccess.successChangedStatisticProtect(_fight, _cbt, s, varBase_, false, new StringList(), _import)) {
                continue;
            }
            vars_.put(s, varBase_);
        }
        vars_ = FightEffects.deltaBoostStatisticMap(_fight, _cbt, vars_, _import);
        for (Statistic s: vars_.getKeys()) {
            creatureCbt_.variationBoostStatistique(s, vars_.getVal(s));
            _fight.addStatisticMessage(_cbt, s, vars_.getVal(s), _import);
        }
        String taux_=_effet.getDamageRateAgainstFoe();
        if(!taux_.isEmpty()){
            StringMap<String> variables_=FightValues.calculateValues(_fight,_cbt,_cbt,_import);
            variables_.putAllMap(FightValues.calculateSendingVariables(_fight,_cbt, _import));
            //pv a infliger
            Rate pvInfliges_;
            pvInfliges_ = _import.evaluatePositiveExp(taux_, variables_, DataBase.getDefaultInflictedRate());
            Rate inflictedHp_ = Rate.multiply(pvInfliges_,creatureCbt_.pvMax());
            if(Rate.greaterEq(inflictedHp_,creatureCbt_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,_cbt,_diff,_import);
                if(NumberUtil.eq(_cbt.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    return;
                }
            }else{
                creatureCbt_.variationLeftHp(inflictedHp_.opposNb());
                _fight.addHpMessage(_cbt, _import);
            }
        }
        if(!_effet.getStatusByNbUses().isEmpty()){
            LgInt utilisation_=nbUtilisationsEntreeAdv_.getVal(_attaque);
            ShortMap<String> statutSiNb_=_effet.getStatusByNbUses();
            short utilisationBis_=(short)utilisation_.ll();
            if(statutSiNb_.contains(utilisationBis_)){
                String statut_=statutSiNb_.getVal(utilisationBis_);
                if(!FightSuccess.successfulAffectedStatusProtect(_fight,_cbt,statut_,false,new StringList(),_import)){
                    return;
                }
                creatureCbt_.affecterStatut(statut_);
                _fight.addStatusMessage(_cbt, statut_, _import);
                return;
            }
            short indice_=utilisationBis_;
            for(short i = utilisationBis_; i> IndexConstants.SIZE_EMPTY; i--){
                if(statutSiNb_.contains(i)){
                    indice_=i;
                    break;
                }
            }
            if(indice_==utilisationBis_){
                return;
            }
            String statut_=_effet.getStatusByNbUses().getVal(indice_);
            if(!FightSuccess.successfulAffectedStatusProtect(_fight,_cbt,statut_,false,new StringList(),_import)){
                return;
            }
            creatureCbt_.affecterStatut(statut_);
            _fight.addStatusMessage(_cbt, statut_, _import);
        }
    }
}
