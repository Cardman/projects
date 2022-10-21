package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.EndTurnType;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundGlobal;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusType;
import aiki.game.fight.animations.AnimationEffect;
import aiki.game.fight.animations.EffectKind;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.util.CommonParam;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightEndRound {

    private FightEndRound() {
    }

    static boolean proponedSwitchWhileKoPlayer(Fight _fight){
        if (proponedSwitchWhileKoPlayer(_fight, Fight.CST_FOE, _fight.getMult())) {
            return true;
        }
        int nbAlly_ = _fight.getMult() - _fight.getPlayerMaxNumberFrontFighters();
        return proponedSwitchWhileKoPlayer(_fight, Fight.CST_PLAYER, nbAlly_);
    }

    static void exitKoFighters(Fight _fight) {
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(f);
            if (f_.estKo()) {
                f_.exitFrontBattleForBeingSubstitued();
            }
        }
    }

    static boolean proponedSwitchWhileKoPlayer(Fight _fight, byte _team, int _max) {
        boolean proponed_ = false;
        boolean sub_ = false;
        for(TeamPosition c:FightOrder.fighters(_fight, _team)){
            Fighter creature_=_fight.getFighter(c);
            if (creature_.isBelongingToPlayer()) {
                continue;
            }
            if(!creature_.estKo()){
                if (creature_.estArriere()) {
                    sub_ = true;
                }
            } else if (!NumberUtil.eq(creature_.getGroundPlaceSubst(), Fighter.BACK)) {
                proponed_ = true;
            }
        }
        if (proponed_) {
            return sub_;
        }
        return proponedSwitchWhileKoPlayerMissing(_fight, _team, _max);
    }

    private static boolean proponedSwitchWhileKoPlayerMissing(Fight _fight, byte _team, int _max) {
        int nbFrontPk_ = 0;
        boolean notFullTeam_ = false;
        for(TeamPosition c:FightOrder.fighters(_fight, _team)){
            Fighter creature_= _fight.getFighter(c);
            if (creature_.isBelongingToPlayer() || creature_.estKo()) {
                continue;
            }
            if(!creature_.estArriere()){
                nbFrontPk_++;
            } else {
                notFullTeam_ = true;
            }
        }
        if (notFullTeam_) {
            return nbFrontPk_ < _max;
        }
        return false;
    }

    static void setPlacesForFighters(Fight _fight, boolean _switchWhileKoUserDone) {
        if (!existSubstitute(_fight)) {
            FightKo.moveTeams(_fight);
            for (TeamPosition c : FightOrder.fighters(_fight)) {
                Fighter creature_ = _fight.getFighter(c);
    //            if (creature_.estArriere()) {
    //                continue;
    //            }
                creature_.affectGroundPlaceSubst();
                chgFirstPosit(c, _fight, creature_);
            }
            return;
        }
        if (_fight.getState() != FightState.SWITCH_WHILE_KO_USER) {
            for (TeamPosition c : FightOrder.fighters(_fight)) {
                Fighter creature_ = _fight.getFighter(c);
                exitIfKo(creature_, c, _fight);
            }
            return;
        }
        for (TeamPosition c : FightOrder.fighters(_fight)) {
            Fighter creature_ = _fight.getFighter(c);
            if (!creature_.isBelongingToPlayer()) {
                if (_switchWhileKoUserDone) {
                    exitIfKo(creature_, c, _fight);
                }
                continue;
            }
            creature_.exitFrontBattleForBeingSubstitued();
            _fight.getFirstPositPlayerFighters().put(c.getPosition(), Fighter.BACK);
        }
    }

    private static void exitIfKo(Fighter _creature, TeamPosition _c, Fight _fight) {
        if (_creature.estKo()) {
            _creature.exitFrontBattleForBeingSubstitued();
            chgFirstPosit(_c, _fight, _creature);
        }
    }

    private static void chgFirstPosit(TeamPosition _c, Fight _fight, Fighter _creature) {
        if (_c.getTeam() == Fight.CST_PLAYER) {
            _fight.getFirstPositPlayerFighters().put(_c.getPosition(), _creature.getGroundPlaceSubst());
        } else {
            _fight.getFirstPositFoeFighters().put(_c.getPosition(), _creature.getGroundPlaceSubst());
        }
    }

    static boolean existSubstitute(Fight _fight) {
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if (!creature_.estKo() && creature_.estArriere()) {
                return true;
            }
        }
        return false;
    }

    static boolean proponedSwitch(Fight _fight){
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if (creature_.estKo() && !NumberUtil.eq(creature_.getGroundPlaceSubst(), Fighter.BACK)) {
                return true;
            }
        }
        if (missingFighterInTeam(_fight, Fight.CST_PLAYER)) {
            return true;
        }
        return missingFighterInTeam(_fight, Fight.CST_FOE);
    }

    static boolean missingFighterInTeam(Fight _fight, byte _team) {
        int nbFrontPk_ = 0;
        boolean notFullTeam_ = false;
        for(TeamPosition c:FightOrder.fighters(_fight, _team)){
            Fighter creature_=_fight.getFighter(c);
            if(creature_.estKo()){
                continue;
            }
            if(!creature_.estArriere()){
                nbFrontPk_++;
            } else {
                notFullTeam_ = true;
            }
        }
        if (notFullTeam_) {
            return nbFrontPk_ < _fight.getMult();
        }
        return false;
    }
    //static boolean endedRound(Fight _fight, DataBase _data) {
//        CustList<TeamPosition> ls_ = FightOrder.fightersHavingToAct(_fight, false, _data);
//        ls_.addAll(FightOrder.fightersHavingToAct(_fight, true, _data));
//        for (TeamPosition f: ls_) {
//            Fighter f_ = _fight.getFighter(f);
//            if (!f_.isActed()) {
//                return false;
//            }
//        }
//        return ls_.isEmpty();
//        return true;
        //return _fight.getBeginRound();
    //}

    static void processEndRound(Fight _fight,Difficulty _diff,DataBase _import){
        //_fight.getEffects().clear();
        CustList<EndRoundMainElements> liste_=_import.getEvtEndRound();
        int nb_=liste_.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nb_; i++){
            if (exitEndRound(_fight, _diff, _import, liste_, i)) {
                return;
            }
        }
        for(byte c:_fight.getTeams().getKeys()){
            Team equipe_=_fight.getTeams().getVal(c);
            equipe_.useItemsEndRound(_import);
            _fight.addComment(equipe_.getComment());
        }
    }

    private static boolean exitEndRound(Fight _fight, Difficulty _diff, DataBase _import, CustList<EndRoundMainElements> _liste, int _i) {
        EndRoundMainElements elt_= _liste.get(_i);
        if(elt_.isIncrementNumberOfRounds()){
            //Tirage du nombre de tour
            return exitEndRoundIncr(_fight, _import, elt_);
        }
        if (elt_.getEndRoundType() == EndTurnType.ATTAQUE) {
            if (exitEndRoundMoveGlobal(_fight, _diff, _import, elt_)) {
                return true;
            }
            if (exitEndRoundMoveIndividual(_fight, _diff, _import, elt_)) {
                return true;
            }
            effectEndRoundPositionRelationAllFighters(_fight, _import, elt_);
            if (exitEndRoundMoveSingleRelation(_fight, _diff, _import, elt_)) {
                return true;
            }
            return exitEndRoundMoveTargetRelations(_fight, _diff, _import, elt_);
        }
        if(elt_.getEndRoundType() == EndTurnType.OBJET){
            return exitEndRoundItem(_fight, _diff, _import, elt_);
        }
        if(elt_.getEndRoundType() == EndTurnType.CAPACITE){
            return exitEndRoundAbility(_fight, _diff, _import, elt_);
        }
        if(elt_.getEndRoundType() == EndTurnType.STATUT){
            return exitEndRoundStatus(_fight, _diff, _import, elt_);
        }
        //elt_.getEndRoundType() == EndTurnType.ATTAQUE_COMBI
        return exitEndRoundCombo(_fight, _diff, _import, elt_);
    }

    private static boolean exitEndRoundCombo(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        StringList key_ = StringUtil.splitStrings(_elt.getElement(), DataBase.SEPARATOR_MOVES);
        EffectCombo effet_ = _import.getCombos().getEffects().getVal(key_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        if (_fight.getUserTeam().getEnabledMovesByGroup().getVal(key_).isEnabled()) {
            for (TeamPosition c : FightOrder.frontFighters(_fight)) {
                if (NumberUtil.eq(c.getTeam(), Fight.CST_PLAYER)) {
                    continue;
                }
                effectEndRoundFoe(_fight, c, effetFinTour_, key_, _diff, _import);
                if (FightKo.endedFight(_fight, _diff)) {
                    return true;
                }
            }
        }
        return exitEndRoundComboFoe(_fight, _diff, _import, key_, effetFinTour_);
    }

    private static boolean exitEndRoundComboFoe(Fight _fight, Difficulty _diff, DataBase _import, StringList _key, EffectEndRoundFoe _effetFinTour) {
        if (_fight.getFoeTeam().getEnabledMovesByGroup().getVal(_key).isEnabled()) {
            for (TeamPosition c : FightOrder.frontFighters(_fight)) {
                if (NumberUtil.eq(c.getTeam(), Fight.CST_FOE)) {
                    continue;
                }
                effectEndRoundFoe(_fight, c, _effetFinTour, _key, _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exitEndRoundStatus(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        Status fObjet_ = _import.getStatus(_elt.getElement());
        if (fObjet_.getStatusType() == StatusType.INDIVIDUEL && exitEndRoundStatusIndividual(_fight, _diff, _import, _elt, fObjet_)) {
            return true;
        }
        if(fObjet_.getStatusType() == StatusType.RELATION_UNIQUE){
            return exitEndRoundStatusRelation(_fight, _diff, _import, _elt);
        }
        return false;
    }

    private static boolean exitEndRoundStatusRelation(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        effectEndRoundStatusRelationAll(_fight, _import, _elt);
        for(TeamPosition c: FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
            Fighter creature_= _fight.getFighter(c);
            for(MoveTeamPosition c2_:creature_.getStatusRelatSet()){
                if(!StringUtil.quickEq(c2_.getMove(), _elt.getElement())){
                    continue;
                }
                effectEndRoundStatusRelationHp(_fight,c,c2_.getTeamPosition(),c2_.getMove(), _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exitEndRoundStatusIndividual(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt, Status _fObjet) {
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
            effectEndRoundStatus(_fight,c, _fObjet, _elt.getElement(), _import);
        }
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
            effectEndRoundStatusHp(_fight,c, _fObjet, _elt.getElement(), _diff, _import);
            if (exitEndRound(_diff, _fight)) {
                return true;
            }
        }
        return false;
    }

    private static void effectEndRoundStatusRelationAll(Fight _fight, DataBase _import, EndRoundMainElements _elt) {
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
            Fighter creature_= _fight.getFighter(c);
            for(MoveTeamPosition c2_:creature_.getStatusRelatSet()){
                if(!StringUtil.quickEq(c2_.getMove(), _elt.getElement())){
                    continue;
                }
                effectEndRoundStatusRelation(_fight,c,c2_.getTeamPosition(),c2_.getMove(), _import);
            }
        }
    }

    private static boolean exitEndRoundAbility(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        AbilityData fCapac_ = _import.getAbility(_elt.getElement());
        EffectEndRound effect_ = fCapac_.getEffectEndRound().first();
        if(effect_ instanceof EffectEndRoundIndividual){
            EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) effect_;
            if (exitEndRoundAbilityIndividual(_fight, _diff, _import, _elt, effetFinTour_)) {
                return true;
            }
        }
        if(effect_ instanceof EffectEndRoundTeam) {
            EffectEndRoundTeam effetFinTour_ = (EffectEndRoundTeam) effect_;
            effectEndRoundTeamAll(_fight, _import, _elt, effetFinTour_);
        }
        if(effect_ instanceof EffectEndRoundMultiRelation){
            EffectEndRoundMultiRelation effetFinTour_ = (EffectEndRoundMultiRelation) effect_;
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                Fighter creature_= _fight.getFighter(c);
                if(!StringUtil.quickEq(creature_.getCurrentAbility(), _elt.getElement())){
                    continue;
                }
                effectEndRoundMultiRelation(_fight,c,effetFinTour_, _elt.getElement(), _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void effectEndRoundTeamAll(Fight _fight, DataBase _import, EndRoundMainElements _elt, EffectEndRoundTeam _effetFinTour) {
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
            Fighter creature_= _fight.getFighter(c);
            if(!StringUtil.quickEq(creature_.getCurrentAbility(), _elt.getElement())){
                continue;
            }
            effectEndRoundTeam(_fight,c, _effetFinTour, _elt.getElement(), _import);
        }
    }

    private static boolean exitEndRoundAbilityIndividual(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt, EffectEndRoundIndividual _effetFinTour) {
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
            Fighter creature_= _fight.getFighter(c);
            if(!StringUtil.quickEq(creature_.getCurrentAbility(), _elt.getElement())){
                continue;
            }
            _fight.addAbilityEndRoundMessage(_elt.getElement(), c, _import);
            effectEndRoundIndividual(_fight,c, _effetFinTour, _diff, _import);
            if (exitEndRound(_diff, _fight)) {
                return true;
            }
        }
        return false;
    }

    private static boolean exitEndRoundItem(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        ItemForBattle fObjet_=(ItemForBattle) _import.getItem(_elt.getElement());
        EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) fObjet_.getEffectEndRound().first();
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
            Fighter creature_= _fight.getFighter(c);
            if (!StringUtil.quickEq(creature_.getItem(), _elt.getElement()) || !FightItems.canUseItsObject(_fight, c, _import)) {
                continue;
            }
            _fight.addItemEndRoundMessage(_elt.getElement(), c, _import);
            effectEndRoundIndividual(_fight,c,effetFinTour_, _diff, _import);
            if (exitEndRound(_diff, _fight)) {
                return true;
            }
        }
        return false;
    }

    private static boolean exitEndRoundMoveTargetRelations(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        for (Effect e: _import.getMove(_elt.getElement()).getEffects()) {
            if (!(e instanceof EffectEndRoundPositionTargetRelation)) {
                continue;
            }
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                effectEndRoundPositionTargetRelation(_fight,c, _elt.getElement(), _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exitEndRoundMoveSingleRelation(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        for (Effect e: _import.getMove(_elt.getElement()).getEffects()) {
            if (!(e instanceof EffectEndRoundSingleRelation)) {
                continue;
            }
            EffectEndRoundSingleRelation effetFinTour_ = (EffectEndRoundSingleRelation) e;
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                effectEndRoundSingleRelation(_fight,c,effetFinTour_, _elt.getElement(), _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void effectEndRoundPositionRelationAllFighters(Fight _fight, DataBase _import, EndRoundMainElements _elt) {
        for (Effect e: _import.getMove(_elt.getElement()).getEffects()) {
            if (!(e instanceof EffectEndRoundPositionRelation)) {
                continue;
            }
            EffectEndRoundPositionRelation effetFinTour_ = (EffectEndRoundPositionRelation) e;
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                effectEndRoundPositionRelation(_fight,c,effetFinTour_, _elt.getElement(), _import);
            }
        }
    }

    private static boolean exitEndRoundMoveIndividual(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        for (Effect e: _import.getMove(_elt.getElement()).getEffects()) {
            if (!(e instanceof EffectEndRoundIndividual)) {
                continue;
            }
            EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) e;
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                Fighter creature_= _fight.getFighter(c);
                if(!creature_.getEnabledMovesEndRound().getVal(_elt.getElement()).isEnabled()){
                    continue;
                }
                _fight.addMoveEndRoundMessage(_elt.getElement(), c, _import);
                effectEndRoundIndividual(_fight,c,effetFinTour_, _diff, _import);
                if (exitEndRound(_diff, _fight)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exitEndRoundMoveGlobal(Fight _fight, Difficulty _diff, DataBase _import, EndRoundMainElements _elt) {
        for (Effect e: _import.getMove(_elt.getElement()).getEffects()) {
            if (!(e instanceof EffectEndRoundGlobal)) {
                continue;
            }
            effectEndRoundGlobal(_fight, _elt.getElement(), _diff, _import);
            if (exitEndRound(_diff, _fight)) {
                return true;
            }
        }
        return false;
    }

    private static boolean exitEndRoundIncr(Fight _fight, DataBase _import, EndRoundMainElements _elt) {
        if (_elt.getEndRoundType() == EndTurnType.ATTAQUE && exitEndRoundIncrMove(_fight, _import, _elt)) {
            return true;
        }
        if(_elt.getEndRoundType() == EndTurnType.ATTAQUE_COMBI){
            for(byte c: _fight.getTeams().getKeys()){
                Team equipe_= _fight.getTeams().getVal(c);
                for(StringList c2_:equipe_.getEnabledMovesByGroup().getKeys()){
                    if(!StringUtil.quickEq(StringUtil.join(c2_, DataBase.SEPARATOR_MOVES), _elt.getElement())){
                        continue;
                    }
                    incrementNumberRoundsTeamComboMoves(_fight, c, c2_, _import);
                }
            }
        }
        return false;
    }

    private static boolean exitEndRoundIncrMove(Fight _fight, DataBase _import, EndRoundMainElements _elt) {
        String move_ = _elt.getElement();
        for(TeamPosition c:FightOrder.frontFighters(_fight)){
            incrementNumberRounds(_fight,c, move_, _import);
        }
        for(byte c: _fight.getTeams().getKeys()){
            incrementNumberRoundsTeam(_fight, c, move_, _import);
        }
        incrementNumberRoundsGlobal(_fight, move_, _import);
        return !_fight.getAcceptableChoices();
    }

    private static boolean exitEndRound(Difficulty _diff, Fight _fight) {
        return !_fight.getAcceptableChoices() || FightKo.endedFight(_fight, _diff);
    }

    static void incrementNumberRounds(Fight _fight,TeamPosition _fighter, String _move, DataBase _import) {
        Fighter creature_=_fight.getFighter(_fighter);
        ActivityOfMove activity_;
        if (creature_.getEnabledMoves().contains(_move)) {
            activity_ = creature_.getEnabledMoves().getVal(_move);
        } else if (creature_.getEnabledMovesUnprot().contains(_move)) {
            activity_ = creature_.getEnabledMovesUnprot().getVal(_move);
        } else if (creature_.getEnabledMovesProt().contains(_move)) {
            activity_ = creature_.getEnabledMovesProt().getVal(_move);
        } else if (creature_.getEnabledMovesConstChoices().contains(_move)) {
            activity_ = creature_.getEnabledMovesConstChoices().getVal(_move);
        } else if (creature_.getEnabledMovesEndRound().contains(_move)) {
            activity_ = creature_.getEnabledMovesEndRound().getVal(_move);
        } else if (creature_.getEnabledChangingTypesMoves().contains(_move)) {
            activity_ = creature_.getEnabledChangingTypesMoves().getVal(_move);
        } else if (creature_.getEnabledCounteringMoves().contains(_move)) {
            activity_ = creature_.getEnabledCounteringMoves().getVal(_move);
        } else {
            for (MoveTeamPosition m: creature_.getTrackingMoves().getKeys()) {
                if (StringUtil.quickEq(m.getMove(), _move)) {
                    activity_ = creature_.getTrackingMoves().getVal(m).getActivity();
                    _fight.setCurrentActivity(activity_);
                    processActivity(_fight, _fighter, _move, m.getTeamPosition(), true, _import);
                }
            }
            return;
        }
        _fight.setCurrentActivity(activity_);
        processActivity(_fight, _fighter, _move, _fighter, false, _import);
    }

    static void processActivity(Fight _fight, TeamPosition _fighter, String _move, TeamPosition _other, boolean _relation, DataBase _import) {
        ActivityOfMove activity_ = _fight.getCurrentActivity();
        if (!activity_.isEnabled() || !activity_.isIncrementCount()) {
            return;
        }
        Fighter creature_=_fight.getFighter(_fighter);
        MoveData fAtt_=_import.getMove(_move);
        MonteCarloNumber loi_=fAtt_.getRepeatRoundLaw();
        boolean resterActif_ = resterActif(_fight, _import, activity_.getNbTurn(), loi_, _fighter.getTeam(), Fight.CST_FOE);
        activity_.keepEnabled(resterActif_);
        if (!resterActif_) {
            if (creature_.getEnabledMovesProt().contains(_move)) {
                creature_.desactiverAttaqueImmu(_move, _import);
            }
            _fight.addDisabledMoveMessage(_fighter, _move, _relation, _other, _import);
        }
    }

    static void incrementNumberRoundsTeam(Fight _fight,byte _team, String _move, DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_team);
        if (!equipe_.getEnabledMoves().contains(_move)) {
            return;
        }
        ActivityOfMove activity_ = equipe_.getEnabledMoves().getVal(_move);
        if(!activity_.isEnabled()){
            return;
        }
        boolean tirer_=true;
        Rate nbRounds_ = Rate.zero();
        for (TeamPosition f: FightOrder.fighters(_fight, _team)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estArriere()) {
                continue;
            }
            Item objet_ = FightItems.useItsObject(_fight, f, _import);
            if (objet_ instanceof ItemForBattle && ((ItemForBattle) objet_).getIncreasingMaxNbRoundTeamMove().contains(_move)) {
                tirer_ = false;
                nbRounds_.addNb(new Rate(((ItemForBattle) objet_).getIncreasingMaxNbRoundTeamMove().getVal(_move)));
            }
        }
        MoveData dataMove_ = _import.getMove(_move);
        MonteCarloNumber law_=dataMove_.getRepeatRoundLaw();
        if (!tirer_) {
            Rate max_ = law_.maximum();
            Rate taux_=Rate.plus(nbRounds_,max_);
            activity_.keepEnabled(activity_.getNbTurn() < taux_.ll());
            _fight.addDisabledTeamMoveMessage(_team, _move, activity_, _import);
            return;
        }
        boolean resterActif_ = resterActif(_fight, _import, activity_.getNbTurn(), law_, _team, Fight.CST_FOE);
        activity_.keepEnabled(resterActif_);
        _fight.addDisabledTeamMoveMessage(_team, _move, activity_, _import);
    }

    static void incrementNumberRoundsGlobal(Fight _fight, String _move, DataBase _import) {
        if (!_fight.getEnabledMoves().contains(_move)) {
            return;
        }
        if (_fight.getStillEnabledMoves().contains(_move) && _fight.getStillEnabledMoves().getVal(_move) == BoolVal.TRUE) {
            return;
        }
        ActivityOfMove activity_ = _fight.getEnabledMoves().getVal(_move);
        if(!activity_.isEnabled()){
            return;
        }
        MoveData dataMove_ = _import.getMove(_move);
        MonteCarloNumber law_=dataMove_.getRepeatRoundLaw();
        boolean tirer_=true;
        Rate nbRounds_ = Rate.zero();
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter fighter_ = _fight.getFighter(f);
            if (fighter_.estArriere()) {
                continue;
            }
            Item objet_ = FightItems.useItsObject(_fight, f, _import);
            if (objet_ instanceof ItemForBattle && ((ItemForBattle) objet_).getIncreasingMaxNbRoundGlobalMove().contains(_move)) {
                tirer_ = false;
                nbRounds_.addNb(new Rate(((ItemForBattle) objet_).getIncreasingMaxNbRoundGlobalMove().getVal(_move)));
            }
        }
        if (!tirer_) {
            Rate max_ = law_.maximum();
            Rate taux_=Rate.plus(nbRounds_,max_);
            activity_.keepEnabled(activity_.getNbTurn() < taux_.ll());
            _fight.addWeatherEndRoundMessage(_move, activity_, _import);
            return;
        }
        MonteCarloBoolean loiSachant_=law_.knowingGreater(new Rate(activity_.getNbTurn()));
        if (FightSuccess.isBadSimulation(_fight, loiSachant_)) {
            return;
        }
        boolean resterActif_ = FightSuccess.random(_import, loiSachant_);
        activity_.keepEnabled(resterActif_);
        _fight.addWeatherEndRoundMessage(_move, activity_, _import);
    }

    static void incrementNumberRoundsTeamComboMoves(Fight _fight, byte _team, StringList _key, DataBase _import) {
        Team equipe_ = _fight.getTeams().getVal(_team);
        ActivityOfMove activity_ = equipe_.getEnabledMovesByGroup().getVal(_key);
        if (!activity_.isEnabled()) {
            return;
        }
        EffectCombo effet_=_import.getCombos().getEffects().getVal(_key);
        MonteCarloNumber loi_ = effet_.getRepeatedRoundsLaw();
        boolean resterActif_ = resterActif(_fight, _import, activity_.getNbTurn(), loi_, _team, Fight.CST_FOE);
        activity_.keepEnabled(resterActif_);
    }

    static void effectEndRoundTeam(Fight _fight,TeamPosition _combattant,EffectEndRoundTeam _effet, String _ability, DataBase _import){
        if (effectEndRoundTeamExit(_fight, _combattant)) {
            return;
        }
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        boolean testPositif_= _fight.getSimulation();
        if(testPositif_||FightSuccess.tirage(_import, _effet.getDeleteAllStatusAlly())){
            for(byte c:equipe_.getMembers().getKeys()){
                if(NumberUtil.eq(c,_combattant.getPosition())){
                    continue;
                }
                Fighter creature_=equipe_.refPartMembres(c);
                _fight.addAbilityEndRoundMessage(_ability, new TeamPosition(_combattant.getTeam(), c), _import);
                for(String c2_:creature_.getStatusSet()){
                    creature_.supprimerStatut(c2_);
                    _fight.addDisabledStatusMessage(c2_, new TeamPosition(_combattant.getTeam(), c), _import);
                }
            }
        }
        if(testPositif_||FightSuccess.tirage(_import, _effet.getDeleteAllStatus())){
            Fighter creature_=equipe_.refPartMembres(_combattant.getPosition());
            _fight.addAbilityEndRoundMessage(_ability, _combattant, _import);
            for(String c:creature_.getStatusSet()){
                creature_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _combattant, _import);
            }
        }
    }

    private static boolean effectEndRoundTeamExit(Fight _fight, TeamPosition _combattant) {
        return _fight.getSimulation() && NumberUtil.eq(_combattant.getTeam(), Fight.CST_PLAYER);
    }

    static void effectEndRoundGlobal(Fight _fight,String _attaqueClimat,Difficulty _diff,DataBase _import){
        //classer les climats actifs par dominance
        if (!StringUtil.contains(FightMoves.enabledGlobalMoves(_fight, _import), _attaqueClimat)) {
            return;
        }
        _fight.addGlobalMoveEndRoundMessage(_attaqueClimat, _import);
        MoveData fAttClimat_=_import.getMove(_attaqueClimat);
        int nbEffets_=fAttClimat_.nbEffets();
        for(int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
            Effect effet_=fAttClimat_.getEffet(i);
            if(!(effet_ instanceof EffectGlobal)){
                continue;
            }
            EffectGlobal effetGl_=(EffectGlobal)effet_;
            if (effectEndRoundGlobalStop(_fight, _attaqueClimat, _diff, _import, effetGl_)) {
                return;
            }
        }
    }

    private static boolean effectEndRoundGlobalStop(Fight _fight, String _attaqueClimat, Difficulty _diff, DataBase _import, EffectGlobal _effetGl) {
        boolean puttingKo_ = _effetGl.getPuttingKo();
        for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
            Fighter creature_= _fight.getFighter(c);
            if (puttingKo_ && Rate.greaterEq(new Rate(_fight.getEnabledMoves().getVal(_attaqueClimat).getNbTurn()), _import.getMove(_attaqueClimat).getRepeatRoundLaw().maximum()) && notImmuKo(_attaqueClimat, _import, creature_)) {
                if (exitWhenKo(_fight, c, _import, _diff)) {
                    return true;
                }
                continue;
            }
            Rate varPv_ = varPvGlobal(_fight, _attaqueClimat, _import, _effetGl, c, creature_);
            _fight.addAnimationHealthPoints(c, varPv_);
            if (varLeftHp(creature_, varPv_.opposNb(), _fight, c, _import, _diff)) {
                return true;
            }
        }
        return false;
    }

    private static boolean notImmuKo(String _attaqueClimat, DataBase _import, Fighter _creature) {
        AbilityData fCapac_ = _creature.ficheCapaciteActuelle(_import);
        return fCapac_ == null || !StringUtil.contains(fCapac_.getImmuMove(), _attaqueClimat);
    }

    private static Rate varPvGlobal(Fight _fight, String _attaqueClimat, DataBase _import, EffectGlobal _effetGl, TeamPosition _c, Fighter _creature) {
        AbilityData fCapac_=_creature.ficheCapaciteActuelle(_import);
        boolean immu_ = fCapac_ != null && StringUtil.contains(fCapac_.getImmuWeather(), _attaqueClimat);
        Item it_ = FightItems.useItsObject(_fight, _c, _import);
        if (it_ instanceof ItemForBattle) {
            ItemForBattle itBattle_ = (ItemForBattle) it_;
            if (StringUtil.contains(itBattle_.getImmuWeather(), _attaqueClimat)) {
                immu_ = true;
            }
        }
        boolean soinType_ = false;
        for(String e: _creature.getTypes()){
            if(StringUtil.contains(_effetGl.getImmuneTypes(), e)){
                immu_=true;
                soinType_=true;
            }
        }
        Rate varPv_=Rate.zero();
        if(!immu_){
            Rate prod_ = Rate.multiply(_effetGl.getDamageEndRound(), _creature.pvMax());
            varPv_.addNb(prod_.opposNb());
        }
        if(soinType_){
            varPv_.addNb(Rate.multiply(_effetGl.getHealingEndRound(), _creature.pvMax()));
        }
        if (!_creature.isDisappeared()) {
            varPv_.addNb(Rate.multiply(_effetGl.getHealingEndRoundGround(), _creature.pvMax()));
        }
        if(fCapac_ != null && fCapac_.getHealHpByWeather().contains(_attaqueClimat)){
            varPv_.addNb(Rate.multiply(fCapac_.getHealHpByWeather().getVal(_attaqueClimat), _creature.pvMax()));
        }
        return varPv_;
    }

    private static boolean varLeftHp(Fighter _creature, Rate _varPv, Fight _fight, TeamPosition _c, DataBase _import, Difficulty _diff) {
        if (Rate.strGreater(_creature.getRemainingHp(), _varPv)) {
            _creature.variationLeftHp(_varPv.opposNb());
            _fight.addHpMessage(_c, _import);
        } else {
            return exitWhenKo(_fight, _c, _import, _diff);
        }
        return false;
    }

    private static boolean exitWhenKo(Fight _fight, TeamPosition _c, DataBase _import, Difficulty _diff) {
        FightKo.setKoMoveTeams(_fight, _c, _diff, _import);
        _fight.addAnimationKoFighter(_c);
        return exitAfterKo(_fight, _c, _diff);
    }

    private static boolean exitAfterKo(Fight _fight, TeamPosition _c, Difficulty _diff) {
        if(NumberUtil.eq(_c.getTeam(),Fight.CST_PLAYER)&& _fight.getSimulation()){
            _fight.setAcceptableChoices(false);
            _fight.setIssue(IssueSimulation.KO_PLAYER);
            return true;
        }
        return FightKo.endedFight(_fight, _diff);
    }

    static void effectEndRoundIndividual(Fight _fight,TeamPosition _combattant,EffectEndRoundIndividual _effet,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        //BAILLEMENT
        //ATTERRISSAGE
        Rate varPv_ = varPvIndiv(_effet, _import, creature_);
        if(_fight.getSimulation()){
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE)){
                for(String c:creature_.getStatusSet()){
                    creature_.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, _combattant, _import);
                }
            }
        }else if(FightSuccess.tirage(_import, _effet.getDeleteAllStatus())){
            for(String c:creature_.getStatusSet()){
                creature_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _combattant, _import);
            }
        }
        if(!_effet.getUserStatusEndRound().isEmpty()){
            String status_ = _effet.getUserStatusEndRound();
            processStatus(_fight, _combattant, status_, _import);
        }
        Rate sum_ = Rate.plus(varPv_, creature_.getRemainingHp());
        _fight.addAnimationHealthPoints(_combattant, varPv_);
        if (sum_.isZeroOrGt()) {
            creature_.variationLeftHp(varPv_);
            _fight.addHpMessage(_combattant, _import);
        } else {
            FightKo.setKoMoveTeams(_fight,_combattant,_diff,_import);
            _fight.addAnimationKoFighter(_combattant);
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }
    }

    private static Rate varPvIndiv(EffectEndRoundIndividual _effet, DataBase _import, Fighter _creature) {
        Rate varPv_=Rate.zero();
        if(!_effet.getHealHp().isZero()){
            varPv_.addNb(Rate.multiply(_creature.pvMax(), _effet.getHealHp()));
        }
        boolean gainPv_ = gainPv(_effet, _creature);
        if(gainPv_){
            for(String c: _creature.getTypes()){
                if(_effet.getHealHpByOwnerTypes().contains(c)){
                    varPv_.addNb(Rate.multiply(_creature.pvMax(), _effet.getHealHpByOwnerTypes().getVal(c)));
                }
            }
        }else{
            if(_effet.getHealHpByOwnerTypes().contains(DataBase.EMPTY_STRING)){
                varPv_.addNb(Rate.multiply(_creature.pvMax(), _effet.getHealHpByOwnerTypes().getVal(DataBase.EMPTY_STRING)));
            }
        }
        AbilityData ability_ = _creature.ficheCapaciteActuelle(_import);
        boolean recoil_ = ability_ == null || !ability_.isImmuDamageRecoil();
        if (recoil_ && !_effet.getRecoilDamage().isZero()) {
            varPv_.removeNb(Rate.multiply(_creature.pvMax(), _effet.getRecoilDamage()));
        }
        return varPv_;
    }

    private static boolean gainPv(EffectEndRoundIndividual _effet, Fighter _creature) {
        boolean gainPv_=false;
        for(String c: _creature.getTypes()){
            if(_effet.getHealHpByOwnerTypes().contains(c)){
                gainPv_=true;
                break;
            }
        }
        return gainPv_;
    }

    static void effectEndRoundMultiRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundMultiRelation _effet, String _ability,Difficulty _diff,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_combattant);
        for(TeamPosition c: FightOrder.targetsEffect(_fight,_combattant,_effet,_diff,_import)){
            Fighter creature_=_fight.getFighter(c);
            AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
            boolean immu_ = fCapac_ != null && StringUtil.contains(fCapac_.getImmuAbility(), creatureLanceur_.getCurrentAbility()) && !FightAbilities.ignoreTargetAbility(_fight, _combattant, c, _import);
            if(immu_){
                continue;
            }
            Rate varPv_ = effectEndRoundMultiRelationPv(_fight, _effet, _ability, _import, c, creature_);
            if (varLeftHp(creature_, varPv_, _fight, c, _import, _diff)) {
                return;
            }
        }
    }

    private static Rate effectEndRoundMultiRelationPv(Fight _fight, EffectEndRoundMultiRelation _effet, String _ability, DataBase _import, TeamPosition _c, Fighter _creature) {
        Rate varPv_=Rate.zero();
        boolean status_ = false;
        for(String c2_: _creature.getStatusSet()){
            if(NumberUtil.eq(_creature.getStatusNbRoundShort(c2_), 0)){
                continue;
            }
            status_ = true;
            if(_effet.getDamageByStatus().contains(c2_)){
                varPv_.addNb(Rate.multiply(_effet.getDamageByStatus().getVal(c2_), _creature.pvMax()));
            }
        }
        if (status_) {
            _fight.addAbilityEndRoundMessage(_ability, _c, _import);
        }
        if (!varPv_.isZero()) {
            _fight.addEffectRecoil(_c);
        }
        return varPv_;
    }

    static void effectEndRoundPositionRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundPositionRelation _effet,String _attaque, DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_combattant.getTeam());
        for(byte c:equipeLanceur_.getHealAfterSet(_attaque)){
            effectEndRoundPositionRelationIt(_fight, _combattant, _effet, _attaque, _import, equipeLanceur_, c);
        }
    }

    private static void effectEndRoundPositionRelationIt(Fight _fight, TeamPosition _combattant, EffectEndRoundPositionRelation _effet, String _attaque, DataBase _import, Team _equipeLanceur, byte _c) {
        StacksOfUses soinApres_= _equipeLanceur.getHealAfterVal(_attaque, _c);
        for(EntryCust<Byte, Fighter> e: _equipeLanceur.fightersListAtCurrentPlace(_c).entryList()){
            Fighter partenaire_= e.getValue();
            if(soinApres_.isLastStacked()){
                soinApres_.setLastStacked(false);
                if(soinApres_.getNbRounds()<1){
                    soinApres_.setNbRounds((byte) (soinApres_.getNbRounds()+1));
                }
                Rate varPv_=Rate.multiply(partenaire_.pvMax(), _effet.getHealHp());
                partenaire_.variationLeftHp(varPv_);
                _fight.addAnimationHealthPoints(new TeamPosition(_combattant.getTeam(), e.getKey()), varPv_);
                _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition(_combattant.getTeam(), e.getKey()), _combattant, _import);
                _fight.addHpMessage(new TeamPosition(_combattant.getTeam(), e.getKey()), _import);
            }else if(soinApres_.isFirstStacked()){
                if(soinApres_.getNbRounds()<1){
                    soinApres_.setNbRounds((byte) (soinApres_.getNbRounds()+1));
                }else{
                    soinApres_.setFirstStacked(false);
                    soinApres_.setNbRounds((byte) 0);
                    Rate varPv_=Rate.multiply(partenaire_.pvMax(), _effet.getHealHp());
                    partenaire_.variationLeftHp(varPv_);
                    _fight.addAnimationHealthPoints(new TeamPosition(_combattant.getTeam(), e.getKey()), varPv_);
                    _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition(_combattant.getTeam(), e.getKey()), _combattant, _import);
                    _fight.addHpMessage(new TeamPosition(_combattant.getTeam(), e.getKey()), _import);
                }
            }
        }
    }

    static void effectEndRoundPositionTargetRelation(Fight _fight,TeamPosition _combattant,String _attaque,Difficulty _diff,DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_combattant.getTeam());
        Fighter creatureLanceur_ = equipeLanceur_.refPartMembres(_combattant.getPosition());
        for(byte c:equipeLanceur_.getMovesAnticipationSet(_attaque)){
            Anticipation attaqueAnticipe_=equipeLanceur_.getMovesAnticipationVal(_attaque, c);
            if(!attaqueAnticipe_.isEnabled()){
                continue;
            }
            attaqueAnticipe_.increment();
            if (!attaqueAnticipe_.isIncrementing() && effectEndRoundPositionTargetRelationExit(_fight, _combattant, _attaque, _diff, _import, creatureLanceur_, attaqueAnticipe_)) {
                return;
            }
        }
    }

    private static boolean effectEndRoundPositionTargetRelationExit(Fight _fight, TeamPosition _combattant, String _attaque, Difficulty _diff, DataBase _import, Fighter _creatureLanceur, Anticipation _attaqueAnticipe) {
        TargetCoords target_ = _attaqueAnticipe.getTargetPosition();
        Team equipeCible_= _fight.getTeams().getVal((byte) target_.getTeam());
        for(EntryCust<Byte, Fighter> e:equipeCible_.fightersListAtCurrentPlace(target_).entryList()){
            _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition((byte) target_.getTeam(), e.getKey()), _combattant, _import);
            Fighter creatureCible_=e.getValue();
            AnimationEffect animation_;
            animation_ = new AnimationEffect();
            animation_.setIndex(_fight.getEffects().size());
            animation_.setFromFighter(new TargetCoords(_combattant.getTeam(), _creatureLanceur.getGroundPlaceSubst()));
            animation_.setToFighter(new TargetCoords(target_.getTeam(),creatureCible_.getGroundPlaceSubst()));
            if(creatureCible_.getClone().isZero()){
                if(Rate.greaterEq(_attaqueAnticipe.getDamage(),creatureCible_.getRemainingHp())){
                    animation_.setKoToFighter(true);
                    FightKo.setKoMoveTeams(_fight,new TeamPosition((byte) target_.getTeam(),e.getKey()), _diff, _import);
                    if (exitAfterKo(_fight,new TeamPosition((byte) target_.getTeam(),e.getKey()), _diff)) {
                        _fight.getEffects().add(animation_);
                        return true;
                    }
                }else{
                    creatureCible_.variationLeftHp(_attaqueAnticipe.getDamage().opposNb());
                    _fight.addHpMessage(new TeamPosition((byte) target_.getTeam(), e.getKey()), _import);
                }
            }else{
                creatureCible_.infligerDegatsClone(_attaqueAnticipe.getDamage());
                _fight.addHpCloneMessage(new TeamPosition((byte) target_.getTeam(), e.getKey()), _attaqueAnticipe.getDamage(), _import);
            }
            _fight.getEffects().add(animation_);
            _attaqueAnticipe.getDamage().affectZero();
            _attaqueAnticipe.setTargetPosition(TargetCoords.def());
        }
        return false;
    }

    static void effectEndRoundSingleRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundSingleRelation _effet,String _attaque,Difficulty _diff,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_combattant);
        for(CommonParam<MoveTeamPosition, ActivityOfMove> c:creatureLanceur_.enabledRelationsTraps().entryList()){
            if (effectEndRoundSingleRelationMoveExit(_fight,_combattant,_effet,_attaque,_diff,_import,c)) {
                return;
            }
//            if(!StringUtil.quickEq(c.getMove(),_attaque)){
//                continue;
//            }
//            ActivityOfMove actifNbTour_=creatureLanceur_.getTrappingMoves().getVal(c);
//            Fighter creatureCible_=_fight.getFighter(c.getTeamPosition());
//            AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
//            if (fCapac_ != null && fCapac_.isImmuDamageTrappingMoves() || !_effet.getFailEndRound().isEmpty() && _import.evaluateBoolean(_effet.getFailEndRound(), FightValues.calculateValues(_fight, _combattant, c.getTeamPosition(), _import), false)) {
//                actifNbTour_.disable();
//                actifNbTour_.reset();
//                _fight.addDisabledMoveRelMessage(c.getTeamPosition(), _attaque, _combattant, _import);
//                continue;
//            }
//            Item objet_ = effectEndRoundSingleRelationItem(_fight, _combattant, _effet, _attaque, _import, c, actifNbTour_);
//            if(actifNbTour_.getNbTurn()==0){
//                continue;
//            }
//            _fight.addMoveEndRoundRelMessage(_attaque, c.getTeamPosition(), _combattant, _import);
//            Rate tauxDeg_ = tauxDeg(_effet, actifNbTour_, objet_);
//            if(creatureCible_.getClone().isZero()){
//                if(Rate.greaterEq(Rate.multiply(tauxDeg_, creatureCible_.pvMax()), creatureCible_.getRemainingHp())){
//                    actifNbTour_.disable();
//                    actifNbTour_.reset();
//                    _fight.addDisabledMoveRelMessage(c.getTeamPosition(), _attaque, _combattant, _import);
//                    if (exitWhenKo(_fight,c.getTeamPosition(),_import,_diff)){
//                        return;
//                    }
////                    FightKo.setKoMoveTeams(_fight,c.getTeamPosition(),_diff,_import);
////                    _fight.addAnimationKoFighter(c.getTeamPosition());
////                    if(NumberUtil.eq(c.getTeamPosition().getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
////                        _fight.setAcceptableChoices(false);
////                        _fight.setIssue(IssueSimulation.KO_PLAYER);
////                        return;
////                    }
////                    if(FightKo.endedFight(_fight,_diff)){
////                        return;
////                    }
//                    continue;
//                }
//                creatureCible_.variationLeftHp(Rate.multiply(tauxDeg_, creatureCible_.pvMax()).opposNb());
//                _fight.addHpMessage(c.getTeamPosition(), _import);
//            }else{
//                Rate cloneDamage_ = Rate.multiply(tauxDeg_, creatureCible_.pvMax());
//                creatureCible_.infligerDegatsClone(cloneDamage_);
//                _fight.addHpCloneMessage(c.getTeamPosition(), cloneDamage_, _import);
//            }
//            if (!tauxDeg_.isZero()) {
//                _fight.addEffectRecoil(c.getTeamPosition());
//            }
        }
    }
    private static boolean effectEndRoundSingleRelationMoveExit(Fight _fight,TeamPosition _combattant,EffectEndRoundSingleRelation _effet,String _attaque,Difficulty _diff,DataBase _import,CommonParam<MoveTeamPosition, ActivityOfMove> _c) {
        MoveTeamPosition k_ = _c.getKey();
        if(!StringUtil.quickEq(k_.getMove(),_attaque)){
            return false;
        }
        ActivityOfMove actifNbTour_=_c.getValue();
        Fighter creatureCible_=_fight.getFighter(k_.getTeamPosition());
        AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
        if (fCapac_ != null && fCapac_.isImmuDamageTrappingMoves() || !_effet.getFailEndRound().isEmpty() && _import.evaluateBoolean(_effet.getFailEndRound(), FightValues.calculateValues(_fight, _combattant, k_.getTeamPosition(), _import), false)) {
            actifNbTour_.disable();
            actifNbTour_.reset();
            _fight.addDisabledMoveRelMessage(k_.getTeamPosition(), _attaque, _combattant, _import);
            return false;
        }
        Item objet_ = effectEndRoundSingleRelationItem(_fight, _combattant, _effet, _attaque, _import, k_, actifNbTour_);
        if(actifNbTour_.getNbTurn()==0){
            return false;
        }
        _fight.addMoveEndRoundRelMessage(_attaque, k_.getTeamPosition(), _combattant, _import);
        Rate tauxDeg_ = tauxDeg(_effet, actifNbTour_, objet_);
        if(creatureCible_.getClone().isZero()){
            if(Rate.greaterEq(Rate.multiply(tauxDeg_, creatureCible_.pvMax()), creatureCible_.getRemainingHp())){
                actifNbTour_.disable();
                actifNbTour_.reset();
                _fight.addDisabledMoveRelMessage(k_.getTeamPosition(), _attaque, _combattant, _import);
                return exitWhenKo(_fight,k_.getTeamPosition(),_import,_diff);
//                if (exitWhenKo(_fight,k_.getTeamPosition(),_import,_diff)){
//                    return true;
//                }
////                    FightKo.setKoMoveTeams(_fight,c.getTeamPosition(),_diff,_import);
////                    _fight.addAnimationKoFighter(c.getTeamPosition());
////                    if(NumberUtil.eq(c.getTeamPosition().getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
////                        _fight.setAcceptableChoices(false);
////                        _fight.setIssue(IssueSimulation.KO_PLAYER);
////                        return;
////                    }
////                    if(FightKo.endedFight(_fight,_diff)){
////                        return;
////                    }
//                return false;
            }
            creatureCible_.variationLeftHp(Rate.multiply(tauxDeg_, creatureCible_.pvMax()).opposNb());
            _fight.addHpMessage(k_.getTeamPosition(), _import);
        }else{
            Rate cloneDamage_ = Rate.multiply(tauxDeg_, creatureCible_.pvMax());
            creatureCible_.infligerDegatsClone(cloneDamage_);
            _fight.addHpCloneMessage(k_.getTeamPosition(), cloneDamage_, _import);
        }
        if (!tauxDeg_.isZero()) {
            _fight.addEffectRecoil(k_.getTeamPosition());
        }
        return false;
    }

    private static Rate tauxDeg(EffectEndRoundSingleRelation _effet, ActivityOfMove _actifNbTour, Item _objet) {
        Rate tauxDeg_=Rate.zero();
        if(_effet.getRateDamageFunctionOfNbRounds().contains((long) _actifNbTour.getNbTurn())){
            tauxDeg_.affect(_effet.getRateDamageFunctionOfNbRounds().getVal((long) _actifNbTour.getNbTurn()));
        }
        if(_objet instanceof ItemForBattle){
            ItemForBattle objetAttachable_=(ItemForBattle) _objet;
            if(!objetAttachable_.getMultTrappingDamage().isZero()){
                tauxDeg_.multiplyBy(objetAttachable_.getMultTrappingDamage());
            }
        }
        return tauxDeg_;
    }

    private static Item effectEndRoundSingleRelationItem(Fight _fight, TeamPosition _combattant, EffectEndRoundSingleRelation _effet, String _attaque, DataBase _import, MoveTeamPosition _c, ActivityOfMove _actifNbTour) {
        Item objet_ = FightItems.useItsObject(_fight, _combattant, _import);
        if(!(objet_ instanceof ItemForBattle) || !((ItemForBattle) objet_).getIncreasingMaxNbRoundTrap().contains(_c.getMove())){
            MonteCarloNumber loi_= _effet.getLawForEnablingEffect();
            boolean resterActif_ = resterActif(_fight, _import, _actifNbTour.getNbTurn(), loi_, _combattant.getTeam(), Fight.CST_FOE);
            _actifNbTour.keepEnabled(resterActif_);
            _fight.messageDisabling(_actifNbTour, _c.getTeamPosition(), _attaque, _combattant, _import);
        }else{
            Rate taux_=Rate.plus(new Rate(((ItemForBattle)objet_).getIncreasingMaxNbRoundTrap().getVal(_c.getMove())), _effet.getLawForEnablingEffect().maximum());
            _actifNbTour.keepEnabled(_actifNbTour.getNbTurn() < taux_.ll());
            _fight.messageDisabling(_actifNbTour, _c.getTeamPosition(), _attaque, _combattant, _import);
        }
        return objet_;
    }

    static void effectEndRoundFoe(Fight _fight,TeamPosition _combattant, EffectEndRoundFoe _effect, StringList _moves, Difficulty _diff, DataBase _import) {
        _fight.addComboMoveEndRoundMessage(Fight.foe(_combattant.getTeam()), _moves, _import);
        Fighter creature_=_fight.getFighter(_combattant);
        Rate taux_ = new Rate(_effect.getInflictedRateHpTarget());
        Rate lostHp_ = Rate.multiply(taux_, creature_.pvMax());
        if(Rate.greaterEq(lostHp_, creature_.getRemainingHp())){
            exitWhenKo(_fight,_combattant,_import,_diff);
//            FightKo.setKoMoveTeams(_fight,_combattant,_diff,_import);
//            _fight.addAnimationKoFighter(_combattant);
//            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
//                _fight.setAcceptableChoices(false);
//                _fight.setIssue(IssueSimulation.KO_PLAYER);
//                return;
//            }
        } else {
            _fight.addEffectRecoil(_combattant);
            creature_.variationLeftHp(lostHp_.opposNb());
            _fight.addHpMessage(_combattant, _import);
        }
    }

    static void effectEndRoundStatus(Fight _fight,TeamPosition _combattant,Status _statut,String _nomStatut, DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        if(!(_statut instanceof StatusBeginRound)){
            return;
        }
        if(!_statut.getIncrementingEndRound()){
            return;
        }
        short nbTour_=creature_.getStatusNbRoundShort(_nomStatut);
        if(nbTour_ <= 0){
            return;
        }
        MonteCarloNumber loi_=((StatusBeginRound)_statut).getLawForUsingAMoveNbRound();
        boolean resterActif_ = resterActif(_fight, _import, nbTour_, loi_, _combattant.getTeam(), Fight.CST_PLAYER);
        if(!resterActif_){
            creature_.supprimerStatut(_nomStatut);
            _fight.addDisabledStatusMessage(_nomStatut, _combattant, _import);
        } else {
            creature_.incrementRoundsStatus(_nomStatut);
            _fight.addIncrStatusMessage(_nomStatut, _combattant, _import);
        }
    }

    static void effectEndRoundStatusHp(Fight _fight,TeamPosition _combattant,Status _statut,String _nomStatut,Difficulty _diff,DataBase _import){
        CustList<EffectEndRoundStatus> effectEndRound_ = _statut.getEffectEndRound();
        if (effectEndRound_.isEmpty()) {
            return;
        }
        Fighter creature_=_fight.getFighter(_combattant);
        int nbRounds_ = creature_.getStatusNbRoundShort(_nomStatut);
        if(nbRounds_ <= 0){
            return;
        }
        _fight.addStatusEndRoundMessage(_nomStatut, _combattant, _import);
        EffectEndRoundSingleStatus effet_=(EffectEndRoundSingleStatus) effectEndRound_.first();
        Rate taux_=new Rate(effet_.getInflictedRateHpTarget());
        if (effet_.isIncrementingDamageByRounds()) {
            taux_.multiplyBy(new Rate(nbRounds_));
        }
        AbilityData abLoc_ = creature_.ficheCapaciteActuelle(_import);
        if (abLoc_ != null) {
            CustList<EffectEndRound> effs_ = abLoc_.getEffectEndRound();
            if (!effs_.isEmpty() && effs_.first() instanceof EffectEndRoundIndividual) {
                EffectEndRoundIndividual eff_;
                eff_ = (EffectEndRoundIndividual) effs_.first();
                if(eff_.getMultDamageStatus().contains(_nomStatut)){
                    Rate coeff_=eff_.getMultDamageStatus().getVal(_nomStatut);
                    taux_.multiplyBy(coeff_);
                }
            }
        }
        if(Rate.greaterEq(Rate.multiply(taux_, creature_.pvMax()), creature_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_combattant,_diff,_import);
            _fight.addAnimationKoFighter(_combattant);
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
            }
        }else{
            Rate var_ = Rate.multiply(taux_, creature_.pvMax()).opposNb();
            _fight.addAnimationHealthPoints(_combattant, var_);
            creature_.variationLeftHp(var_);
            _fight.addHpMessage(_combattant, _import);
        }
    }

    static void effectEndRoundStatusRelation(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _nomStatut, DataBase _import){
        Status status_ = _import.getStatus(_nomStatut);
        CustList<EffectEndRoundStatus> effectEndRound_ = status_.getEffectEndRound();
        if (effectEndRound_.isEmpty()) {
            return;
        }
        Fighter creature_=_fight.getFighter(_cible);
        short nbTour_=creature_.getStatusRelatNbRoundShort(new MoveTeamPosition(_nomStatut,_lanceur));
        if(nbTour_ <= 0){
            return;
        }
        EffectEndRoundStatus effetFinTour_ = effectEndRound_.first();
        boolean success_ = successEndRound(_fight, _lanceur, _cible, _import, effetFinTour_);
        if(!success_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            _fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, nbTour_, _import);
            return;
        }
        if (!(status_ instanceof StatusBeginRound) || !status_.getIncrementingEndRound()) {
            return;
        }
        MonteCarloNumber loi_=((StatusBeginRound)status_).getLawForUsingAMoveNbRound();
        boolean resterActif_ = resterActif(_fight, _import, nbTour_, loi_, _cible.getTeam(), Fight.CST_PLAYER);
        if(!resterActif_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            _fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, nbTour_, _import);
        } else {
            creature_.incrementPseudoStatutCombattant(_lanceur, _nomStatut);
            _fight.addIncrStatusRelMessage(_nomStatut, _cible, _lanceur, _import);
        }
    }

    private static boolean resterActif(Fight _fight, DataBase _import, short _nbTour, MonteCarloNumber _loi, byte _team, byte _cst) {
        MonteCarloBoolean loiSachant_= _loi.knowingGreater(new Rate(_nbTour));
        LgInt maxRd_ = _import.getMaxRd();
        boolean resterActif_;
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_= FightSuccess.tr(loiSachant_.editNumber(maxRd_, _import.getGenerator()));
            }else{
                resterActif_= NumberUtil.eq(_team, _cst);
            }
        }else{
            resterActif_=FightSuccess.tr(loiSachant_.editNumber(maxRd_, _import.getGenerator()));
        }
        return resterActif_;
    }

    static void effectEndRoundStatusRelationHp(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,String _nomStatut,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_cible);
        Status status_ = _import.getStatus(_nomStatut);
        CustList<EffectEndRoundStatus> effectEndRound_ = status_.getEffectEndRound();
        if (effectEndRound_.isEmpty()) {
            return;
        }
        if(creature_.getStatusRelatNbRoundShort(new MoveTeamPosition(_nomStatut,_lanceur)) <= 0){
            return;
        }
        EffectEndRoundStatus effetFinTour_ = effectEndRound_.first();
        boolean success_ = successEndRound(_fight, _lanceur, _cible, _import, effetFinTour_);
        if(!success_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            //_fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, _import);
            return;
        }
        _fight.addStatusRelEndRoundMessage(_nomStatut, _cible, _lanceur, _import);
        EffectEndRoundStatusRelation effet_=(EffectEndRoundStatusRelation) effectEndRound_.first();
        if (effet_.getThievedHpRateTargetToUser().isZero() || !FightKo.canBeHealed(_fight, _lanceur.getTeam(), _import)) {
            //target hp ==> user hp
            Rate taux_=effet_.getInflictedRateHpTarget();
            if(Rate.greaterEq(Rate.multiply(taux_, creature_.pvMax()), creature_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
                _fight.addAnimationKoFighter(_cible);
                exitAfterKo(_fight,_cible, _diff);
//                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
//                    _fight.setAcceptableChoices(false);
//                    _fight.setIssue(IssueSimulation.KO_PLAYER);
//                    return;
//                }
            }else{
                creature_.variationLeftHp(Rate.multiply(taux_,creature_.pvMax()).opposNb());
                _fight.addHpMessage(_cible, _import);
                _fight.addEffectRecoil(_cible);
            }
            return;
        }
        Rate tauxAbs_ = tauxAbsRelationHp(_fight, _lanceur, _cible, _import, effet_);
        Rate varPv_ = varPvRelationHp(creature_, tauxAbs_);
        if(Rate.eq(varPv_,creature_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
            if (exitAfterKo(_fight,_cible, _diff)) {
                _fight.addAnimationKoFighter(_cible);
                return;
            }
//                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
//                    _fight.setAcceptableChoices(false);
//                    _fight.setIssue(IssueSimulation.KO_PLAYER);
//                    return;
//                }
//                if(FightKo.endedFight(_fight,_diff)){
//                    _fight.addAnimationKoFighter(_cible);
//                    return;
//                }
        }else{
            creature_.variationLeftHp(Rate.multiply(tauxAbs_.absNb(), creature_.pvMax()).opposNb());
            _fight.addHpMessage(_cible, _import);
        }
        AnimationEffect animation_;
        animation_ = new AnimationEffect(EffectKind.ABSORB);
        animation_.setIndex(_fight.getEffects().size());
        animation_.setFromFighter(new TargetCoords(_cible.getTeam(), creature_.getGroundPlaceSubst()));
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        animation_.setToFighter(new TargetCoords(_lanceur.getTeam(), creatureLanceur_.getGroundPlaceSubst()));
        if(tauxAbs_.isZeroOrGt()){
            //target hp absorbed to user hp
            animation_.setKoFromFighter(creature_.estKo());
            _fight.getEffects().add(animation_);
            creatureLanceur_.variationLeftHp(varPv_);
            _fight.addHpMessage(_lanceur, _import);
        }else if(Rate.lowerEq(creatureLanceur_.getRemainingHp(),varPv_)){
            _fight.addEffectRecoil(_cible);
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            short nbTour_=creature_.getStatusRelatNbRoundShort(new MoveTeamPosition(_nomStatut,_lanceur));
            _fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, nbTour_, _import);
            FightKo.setKoMoveTeams(_fight,_lanceur,_diff,_import);
            _fight.addEffectRecoil(_lanceur);
            exitAfterKo(_fight,_lanceur, _diff);
//                if(NumberUtil.eq(_lanceur.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
//                    _fight.setAcceptableChoices(false);
//                    _fight.setIssue(IssueSimulation.KO_PLAYER);
//                    return;
//                }
//                if(FightKo.endedFight(_fight,_diff)){
//                    return;
//                }
        }else{
            creatureLanceur_.variationLeftHp(varPv_.opposNb());
            _fight.addHpMessage(_lanceur, _import);
            _fight.addEffectRecoil(_cible);
            _fight.addEffectRecoil(_lanceur);
        }
    }

    private static Rate varPvRelationHp(Fighter _creature, Rate _tauxAbs) {
        Rate varPv_;
        Rate prod_ = Rate.multiply(_tauxAbs.absNb(), _creature.pvMax());
        if(Rate.lowerEq(_creature.getRemainingHp(),prod_)){
            varPv_= _creature.getRemainingHp();
        }else{
            varPv_=prod_;
        }
        return varPv_;
    }

    private static Rate tauxAbsRelationHp(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, EffectEndRoundStatusRelation _effet) {
        Rate tauxAbs_=new Rate(_effet.getThievedHpRateTargetToUser());
        Item objet_ = FightItems.useItsObject(_fight, _lanceur, _import);
        if (objet_ instanceof ItemForBattle) {
            ItemForBattle objetAttachable_ = (ItemForBattle) objet_;
            if (!objetAttachable_.getMultDrainedHp().isZero()) {
                tauxAbs_.multiplyBy(objetAttachable_.getMultDrainedHp());
            }
        }
        AbilityData fCapac_ = FightAbilities.ignoredTargetAbility(_fight, _lanceur, _cible, _import);
        if (fCapac_ != null && fCapac_.isInflictingDamageInsteadOfSuffering()) {
            tauxAbs_.multiplyBy(DataBase.defRateProduct().opposNb());
        }
        return tauxAbs_;
    }

    private static boolean successEndRound(Fight _fight, TeamPosition _lanceur, TeamPosition _cible, DataBase _import, EffectEndRoundStatus _effetFinTour) {
        boolean success_ = true;
        if (!_effetFinTour.getFailEndRound().isEmpty()) {
            StringMap<String> values_;
            values_ = FightValues.calculateValues(_fight, _lanceur, _cible, _import);
            success_ = !_import.evaluateBoolean(_effetFinTour.getFailEndRound(), values_, false);
        }
        return success_;
    }

    static void processStatus(Fight _fight,TeamPosition _fighter,
            String _status,DataBase _import){
        String fail_ = _import.getStatus(_status).getFail();
        if (!fail_.isEmpty()) {
            StringMap<String> values_;
            values_ = FightValues.calculateValuesFighter(_fight, _fighter, _import);
            if (_import.evaluateBoolean(fail_, values_, false)) {
                return;
            }
        }
        if (!FightSuccess.successfulAffectedStatusProtect(_fight,_fighter,_status,false,new StringList(),_import)) {
            return;
        }
        Fighter creature_=_fight.getFighter(_fighter);
        if(creature_.isSingleStatus(_status)){
            creature_.affecterStatut(_status);
            _fight.addStatusMessage(_fighter, _status, _import);
        }
    }

    static void proponeMovesEvolutions(Fight _fight, Player _user,Difficulty _diff, DataBase _import){
        if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
            return;
        }
        calculateNewLevel(_fight, _user, _diff, _import);
        _fight.setChoices(FightFacade.defaultChoices(_fight));
        if(!_fight.getChoices().isEmpty()){
            _fight.setState(FightState.APPRENDRE_EVOLUER);
        }
    }

    static void calculateNewLevel(Fight _fight,Player _user, Difficulty _diff,DataBase _import) {
        StringList pkNames_ = new StringList();
        for (UsablePokemon u: _user.getTeam()) {
            if (!(u instanceof PokemonPlayer)) {
                continue;
            }
            pkNames_.add(((PokemonPlayer)u).getName());
        }
        Team equipeUt_=_fight.getUserTeam();
        for(Fighter f:equipeUt_.getMembers().values()){
            if (!f.isBelongingToPlayer()) {
                continue;
            }
            pkNames_.add(f.getName());
        }
        for(Fighter f:equipeUt_.getMembers().values()){
            if (!f.isBelongingToPlayer()) {
                continue;
            }
            f.calculateNewLevel(_diff,_import,pkNames_);
            _fight.addComment(f.getComment());
        }
    }

    static void learnAndEvolve(Fight _fight,DataBase _import) {
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        for (byte k: choices_.getKeys()) {
            ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(k);
            Fighter fighter_ = _fight.getUserTeam().refPartMembres(k);
            StringList oldMoves_ = new StringList(fighter_.getMovesSet());
            StringList keptMoves_ = new StringList(oldMoves_);
            StringUtil.retainAllElements(keptMoves_, choice_.getKeptMoves());
            StringList forgottenMoves_ = new StringList(oldMoves_);
            StringUtil.removeAllElements(forgottenMoves_, choice_.getKeptMoves());
            StringList learntMoves_ = new StringList(choice_.getKeptMoves());
            StringUtil.removeAllElements(learntMoves_, oldMoves_);
            for (String m: forgottenMoves_) {
                _fight.addForgetMoveEvolutionMessage(Fight.toUserFighter(k), m, _import);
            }
            for (String m: keptMoves_) {
                _fight.addKeepMoveEvolutionMessage(Fight.toUserFighter(k), m, _import);
            }
            for (String m: learntMoves_) {
                _fight.addLearnMoveEvolutionMessage(Fight.toUserFighter(k), m, _import);
            }
            if (choice_.getName().isEmpty()) {
                fighter_.learnMovesWithoutEvolving(choice_.getKeptMoves(), _import);
            } else if (fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getAbilities().size() <= DataBase.ONE_POSSIBLE_CHOICE) {
                _fight.getCaughtEvolutions().add(choice_.getName());
                _fight.addFightEvolutionMessage(Fight.toUserFighter(k), choice_.getName(), _import);
                fighter_.evoluerSansApprendreCapacite(choice_.getName(), choice_.getKeptMoves(), _import);
            } else {
                _fight.getCaughtEvolutions().add(choice_.getName());
                _fight.addFightEvolutionMessage(Fight.toUserFighter(k), choice_.getName(), _import);
                fighter_.evoluer(choice_.getName(), choice_.getAbility(), choice_.getKeptMoves(), _import);
            }
            fighter_.reinitAttaquesEvosCapacites();
        }
        _fight.getChoices().clear();
    }
}
