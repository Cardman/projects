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
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
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
                f_.setGroundPlaceSubst(Fighter.BACK);
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
                continue;
            }
            if(NumberUtil.eq(creature_.getGroundPlaceSubst(),Fighter.BACK)){
                continue;
            }
            proponed_ = true;
        }
        if (proponed_) {
            return sub_;
        }
        int nbFrontPk_ = 0;
        boolean notFullTeam_ = false;
        for(TeamPosition c:FightOrder.fighters(_fight, _team)){
            Fighter creature_=_fight.getFighter(c);
            if (creature_.isBelongingToPlayer()) {
                continue;
            }
            if(creature_.estKo()){
                continue;
            }
            if(!creature_.estArriere()){
                nbFrontPk_++;
                continue;
            }
            notFullTeam_ = true;
        }
        if (notFullTeam_) {
            return nbFrontPk_ < _max;
        }
        return false;
    }

    static void setPlacesForFighters(Fight _fight, boolean _switchDone) {
        if (existSubstitute(_fight)) {
            if (_fight.getState() != FightState.SWITCH_WHILE_KO_USER) {
                for(TeamPosition c:FightOrder.fighters(_fight)){
                    Fighter creature_=_fight.getFighter(c);
                    if (creature_.estKo()) {
                        creature_.exitFrontBattleForBeingSubstitued();
                        if (c.getTeam() == Fight.CST_PLAYER) {
                            _fight.getFirstPositPlayerFighters().put(c.getPosition(), Fighter.BACK);
                        } else {
                            _fight.getFirstPositFoeFighters().put(c.getPosition(), Fighter.BACK);
                        }
                    }
                }
            } else {
                for(TeamPosition c:FightOrder.fighters(_fight)){
                    Fighter creature_=_fight.getFighter(c);
                    if (!creature_.isBelongingToPlayer()) {
                        if (_switchDone) {
                            if (creature_.estKo()) {
                                creature_.exitFrontBattleForBeingSubstitued();
                                if (c.getTeam() == Fight.CST_PLAYER) {
                                    _fight.getFirstPositPlayerFighters().put(c.getPosition(), Fighter.BACK);
                                } else {
                                    _fight.getFirstPositFoeFighters().put(c.getPosition(), Fighter.BACK);
                                }
                            }
                        }
                        continue;
                    }
                    creature_.exitFrontBattleForBeingSubstitued();
                    _fight.getFirstPositPlayerFighters().put(c.getPosition(), Fighter.BACK);
                }
            }
            return;
        }
        FightKo.moveTeams(_fight);
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
//            if (creature_.estArriere()) {
//                continue;
//            }
            creature_.setGroundPlaceSubst(creature_.getGroundPlace());
            if (c.getTeam() == Fight.CST_PLAYER) {
                _fight.getFirstPositPlayerFighters().put(c.getPosition(), creature_.getGroundPlace());
            } else {
                _fight.getFirstPositFoeFighters().put(c.getPosition(), creature_.getGroundPlace());
            }
        }
    }

    static boolean existSubstitute(Fight _fight) {
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if(creature_.estKo()){
                continue;
            }
            if (!creature_.estArriere()) {
                continue;
            }
            return true;
        }
        return false;
    }

    static boolean proponedSwitch(Fight _fight){
        for(TeamPosition c:FightOrder.fighters(_fight)){
            Fighter creature_=_fight.getFighter(c);
            if(!creature_.estKo()){
                continue;
            }
            if(NumberUtil.eq(creature_.getGroundPlaceSubst(),Fighter.BACK)){
                continue;
            }
            return true;
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
                continue;
            }
            notFullTeam_ = true;
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
            EndRoundMainElements elt_=liste_.get(i);
            if(elt_.isIncrementNumberOfRounds()){
                //Tirage du nombre de tour
                if(elt_.getEndRoundType() == EndTurnType.ATTAQUE){
                    String move_ = elt_.getElement();
                    for(TeamPosition c:FightOrder.frontFighters(_fight)){
                        incrementNumberRounds(_fight,c, move_, _import);
                    }
                    for(byte c:_fight.getTeams().getKeys()){
                        incrementNumberRoundsTeam(_fight, c, move_, _import);
                    }
                    incrementNumberRoundsGlobal(_fight, move_, _import);
                    if (!_fight.getAcceptableChoices()) {
                        return;
                    }
                }
                if(elt_.getEndRoundType() == EndTurnType.ATTAQUE_COMBI){
                    for(byte c:_fight.getTeams().getKeys()){
                        Team equipe_=_fight.getTeams().getVal(c);
                        for(StringList c2_:equipe_.getEnabledMovesByGroup().getKeys()){
                            if(!StringUtil.quickEq(StringUtil.join(c2_, DataBase.SEPARATOR_MOVES),elt_.getElement())){
                                continue;
                            }
                            incrementNumberRoundsTeamComboMoves(_fight, c, c2_, _import);
                        }
                    }
                }
                continue;
            }
            if (elt_.getEndRoundType() == EndTurnType.ATTAQUE) {
                for (Effect e: _import.getMove(elt_.getElement()).getEffects()) {
                    if (!(e instanceof EffectEndRoundGlobal)) {
                        continue;
                    }
                    effectEndRoundGlobal(_fight,elt_.getElement(),_diff,_import);
                    if(!_fight.getAcceptableChoices()){
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                }
                for (Effect e: _import.getMove(elt_.getElement()).getEffects()) {
                    if (!(e instanceof EffectEndRoundIndividual)) {
                        continue;
                    }
                    EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) e;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        if(!creature_.getEnabledMovesEndRound().getVal(elt_.getElement()).isEnabled()){
                            continue;
                        }
                        _fight.addMoveEndRoundMessage(elt_.getElement(), c, _import);
                        effectEndRoundIndividual(_fight,c,effetFinTour_,_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                for (Effect e: _import.getMove(elt_.getElement()).getEffects()) {
                    if (!(e instanceof EffectEndRoundPositionRelation)) {
                        continue;
                    }
                    EffectEndRoundPositionRelation effetFinTour_ = (EffectEndRoundPositionRelation) e;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        effectEndRoundPositionRelation(_fight,c,effetFinTour_,elt_.getElement(), _import);
                    }
                }
                for (Effect e: _import.getMove(elt_.getElement()).getEffects()) {
                    if (!(e instanceof EffectEndRoundSingleRelation)) {
                        continue;
                    }
                    EffectEndRoundSingleRelation effetFinTour_ = (EffectEndRoundSingleRelation) e;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        effectEndRoundSingleRelation(_fight,c,effetFinTour_,elt_.getElement(),_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                for (Effect e: _import.getMove(elt_.getElement()).getEffects()) {
                    if (!(e instanceof EffectEndRoundPositionTargetRelation)) {
                        continue;
                    }
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        effectEndRoundPositionTargetRelation(_fight,c,elt_.getElement(),_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                continue;
            }
            if(elt_.getEndRoundType() == EndTurnType.OBJET){
                ItemForBattle fObjet_=(ItemForBattle)_import.getItem(elt_.getElement());
                EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) fObjet_.getEffectEndRound().first();
                for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                    Fighter creature_=_fight.getFighter(c);
                    if(!StringUtil.quickEq(creature_.getItem(),elt_.getElement())) {
                        continue;
                    }
                    if(!FightItems.canUseItsObject(_fight,c,_import)){
                        continue;
                    }
                    _fight.addItemEndRoundMessage(elt_.getElement(), c, _import);
                    effectEndRoundIndividual(_fight,c,effetFinTour_,_diff,_import);
                    if(!_fight.getAcceptableChoices()){
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                }
                continue;
            }
            if(elt_.getEndRoundType() == EndTurnType.CAPACITE){
                AbilityData fCapac_ = _import.getAbility(elt_.getElement());
                EffectEndRound effect_ = fCapac_.getEffectEndRound().first();
                if(effect_ instanceof EffectEndRoundIndividual){
                    EffectEndRoundIndividual effetFinTour_ = (EffectEndRoundIndividual) effect_;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        if(!StringUtil.quickEq(creature_.getCurrentAbility(),elt_.getElement())){
                            continue;
                        }
                        _fight.addAbilityEndRoundMessage(elt_.getElement(), c, _import);
                        effectEndRoundIndividual(_fight,c,effetFinTour_,_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                if(effect_ instanceof EffectEndRoundTeam) {
                    EffectEndRoundTeam effetFinTour_ = (EffectEndRoundTeam) effect_;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        if(!StringUtil.quickEq(creature_.getCurrentAbility(),elt_.getElement())){
                            continue;
                        }
                        effectEndRoundTeam(_fight,c,effetFinTour_, elt_.getElement(), _import);
                    }
                }
                if(effect_ instanceof EffectEndRoundMultiRelation){
                    EffectEndRoundMultiRelation effetFinTour_ = (EffectEndRoundMultiRelation) effect_;
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        if(!StringUtil.quickEq(creature_.getCurrentAbility(),elt_.getElement())){
                            continue;
                        }
                        effectEndRoundMultiRelation(_fight,c,effetFinTour_, elt_.getElement(),_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                continue;
            }
            if(elt_.getEndRoundType() == EndTurnType.STATUT){
                Status fObjet_ = _import.getStatus(elt_.getElement());
                if(fObjet_.getStatusType() == StatusType.INDIVIDUEL){
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        Status statut_=_import.getStatus().getVal(elt_.getElement());
                        effectEndRoundStatus(_fight,c,statut_,elt_.getElement(), _import);
                    }
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                        Status statut_=_import.getStatus().getVal(elt_.getElement());
                        effectEndRoundStatusHp(_fight,c,statut_,elt_.getElement(),_diff,_import);
                        if(!_fight.getAcceptableChoices()){
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            return;
                        }
                    }
                }
                if(fObjet_.getStatusType() == StatusType.RELATION_UNIQUE){
                    for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        for(MoveTeamPosition c2_:creature_.getStatusRelatSet()){
                            if(!StringUtil.quickEq(c2_.getMove(),elt_.getElement())){
                                continue;
                            }
                            effectEndRoundStatusRelation(_fight,c,c2_.getTeamPosition(),c2_.getMove(),_import);
                        }
                    }
                    for(TeamPosition c: FightOrder.sortedFightersAmongListEndRound(_fight,false, _import)){
                        Fighter creature_=_fight.getFighter(c);
                        for(MoveTeamPosition c2_:creature_.getStatusRelatSet()){
                            if(!StringUtil.quickEq(c2_.getMove(),elt_.getElement())){
                                continue;
                            }
                            effectEndRoundStatusRelationHp(_fight,c,c2_.getTeamPosition(),c2_.getMove(),_diff,_import);
                            if(!_fight.getAcceptableChoices()){
                                return;
                            }
                            if(FightKo.endedFight(_fight,_diff)){
                                return;
                            }
                        }
                    }
                }
                continue;
            }
            //elt_.getEndRoundType() == EndTurnType.ATTAQUE_COMBI
            StringList key_ = StringUtil.splitStrings(elt_.getElement(), DataBase.SEPARATOR_MOVES);
            EffectCombo effet_ = _import.getCombos().getEffects().getVal(key_);
            EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
            if (_fight.getUserTeam().getEnabledMovesByGroup().getVal(key_).isEnabled()) {
                for(TeamPosition c: FightOrder.frontFighters(_fight)){
                    if (NumberUtil.eq(c.getTeam(), Fight.CST_PLAYER)) {
                        continue;
                    }
                    effectEndRoundFoe(_fight,c, effetFinTour_, key_, _diff, _import);
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                }
            }
            if (_fight.getFoeTeam().getEnabledMovesByGroup().getVal(key_).isEnabled()) {
                for(TeamPosition c: FightOrder.frontFighters(_fight)){
                    if (NumberUtil.eq(c.getTeam(), Fight.CST_FOE)) {
                        continue;
                    }
                    effectEndRoundFoe(_fight,c, effetFinTour_, key_, _diff, _import);
                    if(!_fight.getAcceptableChoices()){
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                }
            }
        }
        for(byte c:_fight.getTeams().getKeys()){
            Team equipe_=_fight.getTeams().getVal(c);
            equipe_.useItemsEndRound(_import);
            _fight.addComment(equipe_.getComment());
        }
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
        if(!activity_.isEnabled()){
            return;
        }
        if(!activity_.isIncrementCount()){
            return;
        }
        Fighter creature_=_fight.getFighter(_fighter);
        MoveData fAtt_=_import.getMove(_move);
        MonteCarloNumber loi_=fAtt_.getRepeatRoundLaw();
        MonteCarloBoolean loiSachant_=loi_.knowingGreater(new Rate(activity_.getNbTurn()));
        boolean resterActif_;
        LgInt maxRd_ = _import.getMaxRd();
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
            }else{
                resterActif_= NumberUtil.eq(_fighter.getTeam(), Fight.CST_FOE);
            }
        }else{
            resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
        }
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
            if (!FightItems.canUseItsObject(_fight,f,_import)) {
                continue;
            }
            Item objet_ = fighter_.ficheObjet(_import);
            if (!(objet_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle objetAttachable_=(ItemForBattle)objet_;
            if (!objetAttachable_.getIncreasingMaxNbRoundTeamMove().contains(_move)) {
                continue;
            }
            tirer_=false;
            nbRounds_.addNb(new Rate(objetAttachable_.getIncreasingMaxNbRoundTeamMove().getVal(_move)));
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
        LgInt maxRd_ = _import.getMaxRd();
        MonteCarloBoolean loiSachant_=law_.knowingGreater(new Rate(activity_.getNbTurn()));
        boolean resterActif_;
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
            }else{
                resterActif_= NumberUtil.eq(_team,Fight.CST_FOE);
            }
        }else{
            resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
        }
        activity_.keepEnabled(resterActif_);
        _fight.addDisabledTeamMoveMessage(_team, _move, activity_, _import);
    }

    static void incrementNumberRoundsGlobal(Fight _fight, String _move, DataBase _import) {
        if (!_fight.getEnabledMoves().contains(_move)) {
            return;
        }
        if (_fight.getStillEnabledMoves().contains(_move)){
            if (_fight.getStillEnabledMoves().getVal(_move)){
                return;
            }
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
            if (!FightItems.canUseItsObject(_fight,f,_import)) {
                continue;
            }
            Item objet_ = fighter_.ficheObjet(_import);
            if (!(objet_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle objetAttachable_=(ItemForBattle)objet_;
            if (!objetAttachable_.getIncreasingMaxNbRoundGlobalMove().contains(_move)) {
                continue;
            }
            tirer_=false;
            nbRounds_.addNb(new Rate(objetAttachable_.getIncreasingMaxNbRoundGlobalMove().getVal(_move)));
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
        LgInt maxRd_ = _import.getMaxRd();
        EffectCombo effet_=_import.getCombos().getEffects().getVal(_key);
        MonteCarloNumber loi_ = effet_.getRepeatedRoundsLaw();
        MonteCarloBoolean loiSachant_=loi_.knowingGreater(new Rate(activity_.getNbTurn()));
        boolean resterActif_;
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
            }else{
                resterActif_= NumberUtil.eq(_team,Fight.CST_FOE);
            }
        }else{
            resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
        }
        activity_.keepEnabled(resterActif_);
    }

    static void effectEndRoundTeam(Fight _fight,TeamPosition _combattant,EffectEndRoundTeam _effet, String _ability, DataBase _import){
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        boolean testPositif_=false;
        if(_fight.getSimulation()){
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)){
                return;
            }
            testPositif_=true;
        }
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
            boolean puttingKo_ = effetGl_.getPuttingKo();
            for(TeamPosition c:FightOrder.sortedFightersAmongListEndRound(_fight,true, _import)){
                Fighter creature_=_fight.getFighter(c);
                boolean immu_ = false;
                boolean ko_ = true;
                boolean soinCapacite_=false;
                boolean soinType_=false;
                if(creature_.capaciteActive()){
                    AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
                    if(StringUtil.contains(fCapac_.getImmuWeather(), _attaqueClimat)){
                        immu_=true;
                    }
                    ko_ = !StringUtil.contains(fCapac_.getImmuMove(), _attaqueClimat);
                    if(fCapac_.getHealHpByWeather().contains(_attaqueClimat)){
                        soinCapacite_=true;
                    }
                }
                if (puttingKo_) {
                    MoveData dataMove_ = _import.getMove(_attaqueClimat);
                    MonteCarloNumber law_ = dataMove_.getRepeatRoundLaw();
                    ActivityOfMove activity_ = _fight.getEnabledMoves().getVal(_attaqueClimat);
                    MonteCarloBoolean loiSachant_ = law_.knowingGreater(new Rate(activity_.getNbTurn()));
                    if (!loiSachant_.containsEvent(true)) {
                        if (ko_) {
                            FightKo.setKoMoveTeams(_fight,c,_diff,_import);
                            _fight.addAnimationKoFighter(c);
                            if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                                _fight.setAcceptableChoices(false);
                                _fight.setIssue(IssueSimulation.KO_PLAYER);
                                return;
                            }
                            if(FightKo.endedFight(_fight,_diff)){
                                return;
                            }
                            continue;
                        }
                    }
                }
                if (FightItems.canUseItsObject(_fight, c, _import)) {
                    Item it_ = creature_.ficheObjet(_import);
                    if (it_ instanceof ItemForBattle) {
                        ItemForBattle itBattle_ = (ItemForBattle) it_;
                        if (StringUtil.contains(itBattle_.getImmuWeather(), _attaqueClimat)) {
                            immu_=true;
                        }
                    }
                }
                for(String e:creature_.getTypes()){
                    if(StringUtil.contains(effetGl_.getImmuneTypes(), e)){
                        immu_=true;
                        soinType_=true;
                    }
                }
                Rate varPv_=Rate.zero();
                Rate prod_ = Rate.multiply(effetGl_.getDamageEndRound(), creature_.pvMax());
                if(!immu_){
                    varPv_.addNb(prod_.opposNb());
                }
                if(soinType_){
                    varPv_.addNb(Rate.multiply(effetGl_.getHealingEndRound(), creature_.pvMax()));
                }
                if (!creature_.isDisappeared()) {
                    varPv_.addNb(Rate.multiply(effetGl_.getHealingEndRoundGround(), creature_.pvMax()));
                }
                if(soinCapacite_){
                    AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
                    varPv_.addNb(Rate.multiply(fCapac_.getHealHpByWeather().getVal(_attaqueClimat),creature_.pvMax()));
                }
                _fight.addAnimationHealthPoints(c, varPv_);
                if (Rate.strGreater(creature_.getRemainingHp(), varPv_.opposNb())) {
                    creature_.variationLeftHp(varPv_);
                    _fight.addHpMessage(c, _import);
                } else {
                    FightKo.setKoMoveTeams(_fight,c,_diff,_import);
                    _fight.addAnimationKoFighter(c);
                    if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                        _fight.setAcceptableChoices(false);
                        _fight.setIssue(IssueSimulation.KO_PLAYER);
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                }
            }
        }
    }

    static void effectEndRoundIndividual(Fight _fight,TeamPosition _combattant,EffectEndRoundIndividual _effet,Difficulty _diff,DataBase _import){
        Fighter creature_=_fight.getFighter(_combattant);
        //BAILLEMENT
        //ATTERRISSAGE
        Rate varPv_=Rate.zero();
        if(!_effet.getHealHp().isZero()){
            varPv_.addNb(Rate.multiply(creature_.pvMax(),_effet.getHealHp()));
        }
        if(!_effet.getHealHpByOwnerTypes().isEmpty()){
            boolean gainPv_=false;
            for(String c:creature_.getTypes()){
                if(_effet.getHealHpByOwnerTypes().contains(c)){
                    gainPv_=true;
                    break;
                }
            }
            if(gainPv_){
                for(String c:creature_.getTypes()){
                    if(_effet.getHealHpByOwnerTypes().contains(c)){
                        varPv_.addNb(Rate.multiply(creature_.pvMax(),_effet.getHealHpByOwnerTypes().getVal(c)));
                    }
                }
            }else{
                if(_effet.getHealHpByOwnerTypes().contains(DataBase.EMPTY_STRING)){
                    varPv_.addNb(Rate.multiply(creature_.pvMax(),_effet.getHealHpByOwnerTypes().getVal(DataBase.EMPTY_STRING)));
                }
            }
        }
        boolean recoil_ = true;
        if (creature_.capaciteActive()) {
            AbilityData ability_ = creature_.ficheCapaciteActuelle(_import);
            if (ability_.isImmuDamageRecoil()) {
                recoil_ = false;
            }
        }
        if (recoil_) {
            if(!_effet.getRecoilDamage().isZero()){
                varPv_.removeNb(Rate.multiply(creature_.pvMax(),_effet.getRecoilDamage()));
            }
        }
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

    static void effectEndRoundMultiRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundMultiRelation _effet, String _ability,Difficulty _diff,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_combattant);
        for(TeamPosition c: FightOrder.targetsEffect(_fight,_combattant,_effet,_diff,_import)){
            Fighter creature_=_fight.getFighter(c);
            boolean immu_=false;
            if(creature_.capaciteActive()){
                AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
                if(StringUtil.contains(fCapac_.getImmuAbility(), creatureLanceur_.getCurrentAbility())){
                    immu_=!FightAbilities.ignoreTargetAbility(_fight,_combattant,c,_import);
                }
            }
            if(immu_){
                continue;
            }
            Rate varPv_=Rate.zero();
            boolean status_ = false;
            for(String c2_:creature_.getStatusSet()){
                if(NumberUtil.eq(creature_.getStatusNbRoundShort(c2_), 0)){
                    continue;
                }
                status_ = true;
                if(_effet.getDamageByStatus().contains(c2_)){
                    varPv_.addNb(Rate.multiply(_effet.getDamageByStatus().getVal(c2_),creature_.pvMax()));
                }
            }
            if (status_) {
                _fight.addAbilityEndRoundMessage(_ability, c, _import);
            }
            if (!varPv_.isZero()) {
                _fight.addEffectRecoil(c);
            }
            if (Rate.strGreater(creature_.getRemainingHp(), varPv_)) {
                creature_.variationLeftHp(varPv_.opposNb());
                _fight.addHpMessage(c, _import);
            } else {
                FightKo.setKoMoveTeams(_fight,c,_diff,_import);
                _fight.addAnimationKoFighter(c);
                if(NumberUtil.eq(c.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    return;
                }
            }
        }
    }

    static void effectEndRoundPositionRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundPositionRelation _effet,String _attaque, DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_combattant.getTeam());
        for(byte c:equipeLanceur_.getHealAfterSet(_attaque)){
            StacksOfUses soinApres_=equipeLanceur_.getHealAfterVal(_attaque, c);
            for(byte e:equipeLanceur_.fightersAtCurrentPlace(c)){
                Fighter partenaire_=equipeLanceur_.refPartMembres(e);
                if(soinApres_.isLastStacked()){
                    soinApres_.setLastStacked(false);
                    if(soinApres_.getNbRounds()<1){
                        soinApres_.setNbRounds((byte) (soinApres_.getNbRounds()+1));
                    }
                    Rate varPv_=Rate.multiply(partenaire_.pvMax(),_effet.getHealHp());
                    partenaire_.variationLeftHp(varPv_);
                    _fight.addAnimationHealthPoints(new TeamPosition(_combattant.getTeam(), e), varPv_);
                    _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition(_combattant.getTeam(), e), _combattant, _import);
                    _fight.addHpMessage(new TeamPosition(_combattant.getTeam(), e), _import);
                }else if(soinApres_.isFirstStacked()){
                    if(soinApres_.getNbRounds()<1){
                        soinApres_.setNbRounds((byte) (soinApres_.getNbRounds()+1));
                    }else{
                        soinApres_.setFirstStacked(false);
                        soinApres_.setNbRounds((byte) 0);
                        Rate varPv_=Rate.multiply(partenaire_.pvMax(),_effet.getHealHp());
                        partenaire_.variationLeftHp(varPv_);
                        _fight.addAnimationHealthPoints(new TeamPosition(_combattant.getTeam(), e), varPv_);
                        _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition(_combattant.getTeam(), e), _combattant, _import);
                        _fight.addHpMessage(new TeamPosition(_combattant.getTeam(), e), _import);
                    }
                }
            }
        }
    }

    static void effectEndRoundPositionTargetRelation(Fight _fight,TeamPosition _combattant,String _attaque,Difficulty _diff,DataBase _import){
        Team equipeLanceur_=_fight.getTeams().getVal(_combattant.getTeam());
        for(byte c:equipeLanceur_.getMovesAnticipationSet(_attaque)){
            Anticipation attaqueAnticipe_=equipeLanceur_.getMovesAnticipationVal(_attaque, c);
            if(NumberUtil.eq(attaqueAnticipe_.getTargetPosition().getPosition(),Fighter.BACK)){
                continue;
            }
            attaqueAnticipe_.increment();
            if (attaqueAnticipe_.isIncrementing()) {
                continue;
            }
            TargetCoords target_ = attaqueAnticipe_.getTargetPosition();
            Team equipeCible_=_fight.getTeams().getVal((byte) target_.getTeam());
            for(byte e:equipeCible_.fightersAtCurrentPlace(target_.getPosition())){
                _fight.addMoveEndRoundRelMessage(_attaque, new TeamPosition((byte) target_.getTeam(), e), _combattant, _import);
                Fighter creatureCible_=equipeCible_.refPartMembres(e);
                AnimationEffect animation_;
                animation_ = new AnimationEffect();
                animation_.setIndex(_fight.getEffects().size());
                animation_.setFromFighter(new TargetCoords(_combattant.getTeam(), c));
                animation_.setToFighter(target_);
                if(creatureCible_.getClone().isZero()){
                    if(Rate.greaterEq(attaqueAnticipe_.getDamage(),creatureCible_.getRemainingHp())){
                        animation_.setKoToFighter(true);
                        FightKo.setKoMoveTeams(_fight,new TeamPosition((byte) target_.getTeam(),e),_diff,_import);
                        if(NumberUtil.eq(target_.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                            _fight.setAcceptableChoices(false);
                            _fight.setIssue(IssueSimulation.KO_PLAYER);
                            _fight.getEffects().add(animation_);
                            return;
                        }
                        if(FightKo.endedFight(_fight,_diff)){
                            _fight.getEffects().add(animation_);
                            return;
                        }
                    }else{
                        creatureCible_.variationLeftHp(attaqueAnticipe_.getDamage().opposNb());
                        _fight.addHpMessage(new TeamPosition((byte) target_.getTeam(), e), _import);
                    }
                }else{
                    creatureCible_.infligerDegatsClone(attaqueAnticipe_.getDamage());
                    _fight.addHpCloneMessage(new TeamPosition((byte) target_.getTeam(), e), attaqueAnticipe_.getDamage(), _import);
                }
                _fight.getEffects().add(animation_);
                attaqueAnticipe_.getDamage().affectZero();
                attaqueAnticipe_.setTargetPosition(new TargetCoords((short) 0,Fighter.BACK));
            }
        }
    }

    static void effectEndRoundSingleRelation(Fight _fight,TeamPosition _combattant,EffectEndRoundSingleRelation _effet,String _attaque,Difficulty _diff,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_combattant);
        LgInt maxRd_ = _import.getMaxRd();
        for(MoveTeamPosition c:creatureLanceur_.enabledRelationsTraps()){
            if(!StringUtil.quickEq(c.getMove(),_attaque)){
                continue;
            }
            ActivityOfMove actifNbTour_=creatureLanceur_.getTrappingMoves().getVal(c);
            Fighter creatureCible_=_fight.getFighter(c.getTeamPosition());
            if(creatureCible_.capaciteActive()){
                AbilityData fCapac_=creatureCible_.ficheCapaciteActuelle(_import);
                if(fCapac_.isImmuDamageTrappingMoves()){
                    actifNbTour_.disable();
                    actifNbTour_.reset();
                    _fight.addDisabledMoveRelMessage(c.getTeamPosition(), _attaque, _combattant, _import);
                    continue;
                }
            }
            if (!_effet.getFailEndRound().isEmpty()) {
                StringMap<String> values_;
                values_ = FightValues.calculateValues(_fight,_combattant,c.getTeamPosition(),_import);
                if (_import.evaluateBoolean(_effet.getFailEndRound(), values_, false)) {
                    actifNbTour_.disable();
                    actifNbTour_.reset();
                    _fight.addDisabledMoveRelMessage(c.getTeamPosition(), _attaque, _combattant, _import);
                    continue;
                }
            }
            boolean tirer_=true;
            if(FightItems.canUseItsObject(_fight,_combattant,_import)){
                Item objet_=creatureLanceur_.ficheObjet(_import);
                if(objet_ instanceof ItemForBattle){
                    ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                    if(objetAttachable_.getIncreasingMaxNbRoundTrap().contains(c.getMove())){
                        tirer_=false;
                    }
                }
            }
            if(tirer_){
                MonteCarloNumber loi_=_effet.getLawForEnablingEffect();
                MonteCarloBoolean loiSachant_=loi_.knowingGreater(new Rate(actifNbTour_.getNbTurn()));
                boolean resterActif_;
                if(_fight.getSimulation()){
                    if(loiSachant_.events().size()==1){
                        resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
                    }else{
                        resterActif_= NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE);
                    }
                }else{
                    resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
                }
                actifNbTour_.keepEnabled(resterActif_);
                _fight.messageDisabling(actifNbTour_, c.getTeamPosition(), _attaque, _combattant, _import);
            }else{
                ItemForBattle objet_=(ItemForBattle)creatureLanceur_.ficheObjet(_import);
                Rate taux_=Rate.plus(new Rate(objet_.getIncreasingMaxNbRoundTrap().getVal(c.getMove())),_effet.getLawForEnablingEffect().maximum());
                actifNbTour_.keepEnabled(actifNbTour_.getNbTurn() < taux_.ll());
                _fight.messageDisabling(actifNbTour_, c.getTeamPosition(), _attaque, _combattant, _import);
            }
            if(actifNbTour_.getNbTurn()==0){
                continue;
            }
            _fight.addMoveEndRoundRelMessage(_attaque, c.getTeamPosition(), _combattant, _import);
            Rate tauxDeg_=Rate.zero();
            if(_effet.getRateDamageFunctionOfNbRounds().contains((long)actifNbTour_.getNbTurn())){
                tauxDeg_.affect(_effet.getRateDamageFunctionOfNbRounds().getVal((long)actifNbTour_.getNbTurn()));
            }
            if(FightItems.canUseItsObject(_fight,_combattant,_import)){
                Item objet_=creatureLanceur_.ficheObjet(_import);
                if(objet_ instanceof ItemForBattle){
                    ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                    if(!objetAttachable_.getMultTrappingDamage().isZero()){
                        tauxDeg_.multiplyBy(objetAttachable_.getMultTrappingDamage());
                    }
                }
            }
            if(creatureCible_.getClone().isZero()){
                if(Rate.greaterEq(Rate.multiply(tauxDeg_, creatureCible_.pvMax()), creatureCible_.getRemainingHp())){
                    actifNbTour_.disable();
                    actifNbTour_.reset();
                    _fight.addDisabledMoveRelMessage(c.getTeamPosition(), _attaque, _combattant, _import);
                    FightKo.setKoMoveTeams(_fight,c.getTeamPosition(),_diff,_import);
                    _fight.addAnimationKoFighter(c.getTeamPosition());
                    if(NumberUtil.eq(c.getTeamPosition().getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                        _fight.setAcceptableChoices(false);
                        _fight.setIssue(IssueSimulation.KO_PLAYER);
                        return;
                    }
                    if(FightKo.endedFight(_fight,_diff)){
                        return;
                    }
                    continue;
                }
                creatureCible_.variationLeftHp(Rate.multiply(tauxDeg_, creatureCible_.pvMax()).opposNb());
                _fight.addHpMessage(c.getTeamPosition(), _import);
            }else{
                Rate cloneDamage_ = Rate.multiply(tauxDeg_, creatureCible_.pvMax());
                creatureCible_.infligerDegatsClone(cloneDamage_);
                _fight.addHpCloneMessage(c.getTeamPosition(), cloneDamage_, _import);
            }
            if (!tauxDeg_.isZero()) {
                _fight.addEffectRecoil(c.getTeamPosition());
            }
        }
    }

    static void effectEndRoundFoe(Fight _fight,TeamPosition _combattant, EffectEndRoundFoe _effect, StringList _moves, Difficulty _diff, DataBase _import) {
        _fight.addComboMoveEndRoundMessage(Fight.foe(_combattant.getTeam()), _moves, _import);
        Fighter creature_=_fight.getFighter(_combattant);
        Rate taux_ = new Rate(_effect.getInflictedRateHpTarget());
        Rate lostHp_ = Rate.multiply(taux_, creature_.pvMax());
        if(Rate.greaterEq(lostHp_, creature_.getRemainingHp())){
            FightKo.setKoMoveTeams(_fight,_combattant,_diff,_import);
            _fight.addAnimationKoFighter(_combattant);
            if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                _fight.setAcceptableChoices(false);
                _fight.setIssue(IssueSimulation.KO_PLAYER);
                return;
            }
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
        MonteCarloBoolean loiSachant_=loi_.knowingGreater(new Rate(nbTour_));
        boolean resterActif_;
        LgInt maxRd_ = _import.getMaxRd();
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
            }else{
                resterActif_= NumberUtil.eq(_combattant.getTeam(),Fight.CST_PLAYER);
            }
        }else{
            resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
        }
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
        if (creature_.capaciteActive()) {
            CustList<EffectEndRound> effs_ = creature_.ficheCapaciteActuelle(_import).getEffectEndRound();
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
        boolean success_ = true;
        if (!effetFinTour_.getFailEndRound().isEmpty()) {
            StringMap<String> values_;
            values_ = FightValues.calculateValues(_fight,_lanceur,_cible,_import);
            success_ = !_import.evaluateBoolean(effetFinTour_.getFailEndRound(), values_, false);
        }
        if(!success_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            _fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, nbTour_, _import);
            return;
        }
        if(!(status_ instanceof StatusBeginRound)){
            return;
        }
        if(!status_.getIncrementingEndRound()){
            return;
        }
        MonteCarloNumber loi_=((StatusBeginRound)status_).getLawForUsingAMoveNbRound();
        MonteCarloBoolean loiSachant_=loi_.knowingGreater(new Rate(nbTour_));
        boolean resterActif_;
        LgInt maxRd_ = _import.getMaxRd();
        if(_fight.getSimulation()){
            if(loiSachant_.events().size()==1){
                resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
            }else{
                resterActif_= NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER);
            }
        }else{
            resterActif_=loiSachant_.editNumber(maxRd_,_import.getGenerator());
        }
        if(!resterActif_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            _fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, nbTour_, _import);
        } else {
            creature_.incrementPseudoStatutCombattant(_lanceur, _nomStatut);
            _fight.addIncrStatusRelMessage(_nomStatut, _cible, _lanceur, _import);
        }
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
        boolean success_ = true;
        if (!effetFinTour_.getFailEndRound().isEmpty()) {
            StringMap<String> values_;
            values_ = FightValues.calculateValues(_fight,_lanceur,_cible,_import);
            success_ = !_import.evaluateBoolean(effetFinTour_.getFailEndRound(), values_, false);
        }
        if(!success_){
            creature_.supprimerPseudoStatutCombattant(_lanceur,_nomStatut);
            //_fight.addDisabledStatusRelMessage(_nomStatut, _cible, _lanceur, _import);
            return;
        }
        _fight.addStatusRelEndRoundMessage(_nomStatut, _cible, _lanceur, _import);
        EffectEndRoundStatusRelation effet_=(EffectEndRoundStatusRelation) effectEndRound_.first();
        Rate tauxAbs_=new Rate(effet_.getThievedHpRateTargetToUser());
        if(!tauxAbs_.isZero() && FightKo.canBeHealed(_fight,_lanceur.getTeam(),_import)){
            Fighter creatureLanceur_=_fight.getFighter(_lanceur);
            if(FightItems.canUseItsObject(_fight,_lanceur,_import)){
                Item objet_=creatureLanceur_.ficheObjet(_import);
                if(objet_ instanceof ItemForBattle){
                    ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                    if(!objetAttachable_.getMultDrainedHp().isZero()){
                        tauxAbs_.multiplyBy(objetAttachable_.getMultDrainedHp());
                    }
                }
            }
            if(!FightAbilities.ignoreTargetAbility(_fight,_lanceur,_cible,_import)){
                AbilityData fCapac_=creature_.ficheCapaciteActuelle(_import);
                if(fCapac_.isInflictingDamageInsteadOfSuffering()){
                    tauxAbs_.multiplyBy(DataBase.defRateProduct().opposNb());
                }
            }
            Rate varPv_;
            Rate prod_ = Rate.multiply(tauxAbs_.absNb(), creature_.pvMax());
            if(Rate.lowerEq(creature_.getRemainingHp(),prod_)){
                varPv_=creature_.getRemainingHp();
            }else{
                varPv_=prod_;
            }
            byte groundPlaceTarget_ = creature_.getGroundPlace();
            if(Rate.eq(varPv_,creature_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    _fight.addAnimationKoFighter(_cible);
                    return;
                }
            }else{
                creature_.variationLeftHp(Rate.multiply(tauxAbs_.absNb(), creature_.pvMax()).opposNb());
                _fight.addHpMessage(_cible, _import);
            }
            AnimationEffect animation_;
            animation_ = new AnimationEffect(EffectKind.ABSORB);
            animation_.setIndex(_fight.getEffects().size());
            animation_.setFromFighter(new TargetCoords(_cible.getTeam(), groundPlaceTarget_));
            animation_.setToFighter(new TargetCoords(_lanceur.getTeam(), creatureLanceur_.getGroundPlace()));
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
                if(NumberUtil.eq(_lanceur.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
                if(FightKo.endedFight(_fight,_diff)){
                    return;
                }
            }else{
                creatureLanceur_.variationLeftHp(varPv_.opposNb());
                _fight.addHpMessage(_lanceur, _import);
                _fight.addEffectRecoil(_cible);
                _fight.addEffectRecoil(_lanceur);
            }
        } else {
            //target hp ==> user hp
            Rate taux_=effet_.getInflictedRateHpTarget();
            if(Rate.greaterEq(Rate.multiply(taux_, creature_.pvMax()), creature_.getRemainingHp())){
                FightKo.setKoMoveTeams(_fight,_cible,_diff,_import);
                _fight.addAnimationKoFighter(_cible);
                if(NumberUtil.eq(_cible.getTeam(),Fight.CST_PLAYER)&&_fight.getSimulation()){
                    _fight.setAcceptableChoices(false);
                    _fight.setIssue(IssueSimulation.KO_PLAYER);
                    return;
                }
            }else{
                creature_.variationLeftHp(Rate.multiply(taux_,creature_.pvMax()).opposNb());
                _fight.addHpMessage(_cible, _import);
                _fight.addEffectRecoil(_cible);
            }
        }
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
