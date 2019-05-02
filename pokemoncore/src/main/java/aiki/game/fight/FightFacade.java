package aiki.game.fight;
import aiki.comments.Comment;
import aiki.comparators.ComparatorTrStrings;
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
import aiki.game.fight.util.NbEffectFighterCoords;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.DataMap;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.Block;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorBoolean;
import code.util.comparators.NaturalComparator;

public final class FightFacade {

    private FightFacade() {
    }

    public static Fight newFight() {
        Fight fight_ = new Fight();
        fight_.setSimulation(false);
        fight_.setAcceptableChoices(false);
        fight_.setIssue(IssueSimulation.NOTHING);
        fight_.setFightType(FightType.NOTHING);
        fight_.setWinningMoney(Rate.zero());
        fight_.setUsedItemsWhileRound(new StringMap<Short>());
        fight_.setChoices(new NumberMap<Byte,ChoiceOfEvolutionAndMoves>());
        fight_.setAllyChoice(new ObjectMap<MoveTarget,MoveTarget>());
        fight_.setTeams(new NumberMap<Byte,Team>());
        fight_.setLostObjects(new StringList());
        fight_.setFirstPositPlayerFighters(new NumberMap<Byte,Byte>());
        fight_.setFirstPositFoeFighters(new NumberMap<Byte,Byte>());
        fight_.setState(FightState.RIEN);
        fight_.setCurrentUser(new TeamPosition());
        fight_.setNbRounds(LgInt.zero());
        fight_.setCatchingBall(DataBase.EMPTY_STRING);
        fight_.setStillEnabledMoves(new StringMap<Boolean>());
        fight_.setEnabledMoves(new StringMap<ActivityOfMove>());
        fight_.setCaughtEvolutions(new StringList());
        fight_.setEnvType(EnvironmentType.NOTHING);
        return fight_;
    }

    public static void initFight(Fight _fight, Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import) {
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import) {
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _numero, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dresseur, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _dual, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,WildPk _pokemon, DataBase _import){
        FightInitialization.initFight(_fight, _utilisateur, _diff, _pokemon, _import);
        FightInitialization.initFight(_fight,_import);
        FightSending.sendBeginTeam(_fight,Fight.FOE, _import);
        FightSending.sendBeginTeam(_fight,Fight.PLAYER, _import);
    }

    public static void initTypeEnv(Fight _fight,EnvironmentType _env, Difficulty _diff, DataBase _d){
        _fight.setEnvType(_env);
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _d);
        FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_d);
    }

    public static void initTypeEnv(Fight _fight,Coords _coords, Difficulty _diff, DataBase _d){
        DataMap d_ = _d.getMap();
        if (_coords.isValid()) {
            LevelPoint lp_ = _coords.getLevel();
            Block bl_ = d_.getPlaces().getVal(_coords.getNumberPlace()).getLevelByCoords(_coords).getBlockByPoint(lp_.getPoint());
            if (bl_.isValid()) {
                _fight.setEnvType(bl_.getType());
            } else {
                _fight.setEnvType(EnvironmentType.ROAD);
            }
        } else {
            _fight.setEnvType(EnvironmentType.ROAD);
        }
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _d);
        if(!_fight.getFightType().isWild()){
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_d);
        }
    }

    public static boolean validate(Fight _fight,DataBase _data,Player _user,Difficulty _diff) {
        if (!_fight.getFightType().isExisting()) {
            return true;
        }
        if (!_fight.getFightType().isWild()) {
            if (_fight.getState() == FightState.SURNOM) {
                return false;
            }
            if (_fight.getState() == FightState.CAPTURE_KO) {
                return false;
            }
        }
        if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
            if (_fight.getFightType() != FightType.TMP_TRAINER) {
                return false;
            }
        }
        if (_fight.getMult() < 1) {
            return false;
        }
        if (_fight.getFightType().isWild()) {
            if (_fight.getMult() != 1) {
                return false;
            }
        }
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            if (_fight.getMult() != 2) {
                return false;
            }
            if (_fight.getPlayerMaxNumberFrontFighters() != 1) {
                return false;
            }
        } else {
            if (_fight.getPlayerMaxNumberFrontFighters() != _fight.getMult()) {
                return false;
            }
        }
        Numbers<Byte> noTeams_ = new Numbers<Byte>();
        noTeams_.add(Fight.FOE);
        noTeams_.add(Fight.PLAYER);
        if (!Numbers.equalsSetBytes(_fight.getTeams().getKeys(), noTeams_)) {
            return false;
        }
        if (_fight.getFightType().isWild()) {
            if (!Numbers.eq(_fight.getFoeTeam().getMembers().size(), DataBase.ONE_POSSIBLE_CHOICE)) {
                return false;
            }
        }
        if (_fight.getNbFleeAttempt() < 0) {
            return false;
        }
        if (!_fight.getNbRounds().isZeroOrGt()) {
            return false;
        }
        if (!_fight.getWinningMoney().isZeroOrGt()) {
            return false;
        }
        if (!_data.getPokedex().containsAllAsKeys(_fight.getCaughtEvolutions())) {
            return false;
        }
        if (!_data.getItems().containsAllAsKeys(_fight.getLostObjects())) {
            return false;
        }
        if (!_data.getItems().containsAllAsKeys(_fight.getUsedItemsWhileRound().getKeys())) {
            return false;
        }
        for (Short v: _fight.getUsedItemsWhileRound().values()) {
            if (v < 0) {
                return false;
            }
        }
        for (byte t: _fight.getTeams().getKeys()) {
            if (!_fight.getTeams().getVal(t).validate(_data, t, _fight)) {
                return false;
            }
            if (Numbers.eq(t, Fight.FOE)) {
                continue;
            }
            Numbers<Byte> posInit_ = new Numbers<Byte>();
            int nbMembers_ = _user.getTeam().size();
            for(byte i=CustList.FIRST_INDEX;i<nbMembers_;i++){
                if (!(_user.getTeam().get(i) instanceof PokemonPlayer)) {
                    continue;
                }
                posInit_.add(i);
            }
            Numbers<Byte> pos_ = new Numbers<Byte>();
            for (byte b: _fight.getTeams().getVal(t).getMembers().getKeys()) {
                Fighter f_ = _fight.getTeams().getVal(t).refPartMembres(b);
                if (!f_.isBelongingToPlayer()) {
                    continue;
                }
                pos_.add(b);
            }
            if (!Numbers.equalsSetBytes(pos_, posInit_)) {
                return false;
            }
            if (!Numbers.equalsSetBytes(_fight.getTeams().getVal(t).getPlayerFightersAgainstFoe().getKeys(), posInit_)) {
                return false;
            }
        }
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(f);
            if (f_.estArriere()) {
                FightSending.disableEffectsExceptHp(_fight, f, _data);
            }
        }
        if (!Numbers.equalsSetBytes(_fight.getFirstPositPlayerFighters().getKeys(), _fight.getUserTeam().getMembers().getKeys())) {
            return false;
        }
        Numbers<Byte> possiblePlaces_ = new Numbers<Byte>();
        int m_ = _fight.getMult();
        for (byte i = CustList.FIRST_INDEX; i < m_; i++) {
            possiblePlaces_.add(i);
        }
        for (byte p: _fight.getFirstPositPlayerFighters().values()) {
            if (!Numbers.eq(p, Fighter.BACK)) {
                if (!possiblePlaces_.containsObj(p)) {
                    return false;
                }
            }
        }
        if (!Numbers.equalsSetBytes(_fight.getFirstPositFoeFighters().getKeys(), _fight.getFoeTeam().getMembers().getKeys())) {
            return false;
        }
        for (byte p: _fight.getFirstPositFoeFighters().values()) {
            if (!Numbers.eq(p, Fighter.BACK)) {
                if (!possiblePlaces_.containsObj(p)) {
                    return false;
                }
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectGlobal(), _fight.getEnabledMoves().getKeys())) {
            return false;
        }
        for (String m: _fight.getEnabledMoves().getKeys()) {
            if (_fight.getEnabledMoves().getVal(m).getNbTurn() < 0) {
                return false;
            }
        }
        if (!StringList.equalsSet(_data.getMovesEffectGlobalWeather(), _fight.getStillEnabledMoves().getKeys())) {
            return false;
        }
        //never mind places for catching a wild pokemon at the moment of using a ball
        boolean distinctPlacesGroundCheck_ = false;
        boolean distinctPlacesGroundSubtCheck_ = false;
        boolean onlyDistinctFoeCheckSubst_ = false;
        boolean atLeastOneFrontPk_ = false;
        boolean validSwitchTeam_ = true;
        if (_fight.getState() == FightState.ATTAQUES) {
            distinctPlacesGroundCheck_ = true;
            distinctPlacesGroundSubtCheck_ = true;
            atLeastOneFrontPk_ = true;
        } else if (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            distinctPlacesGroundCheck_ = true;
            distinctPlacesGroundSubtCheck_ = true;
            atLeastOneFrontPk_ = true;
        } else if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
            if (!FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
                distinctPlacesGroundCheck_ = true;
                atLeastOneFrontPk_ = true;
            } else {
                validSwitchTeam_ = false;
            }
            distinctPlacesGroundSubtCheck_ = true;
            onlyDistinctFoeCheckSubst_ = true;
        } else if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            distinctPlacesGroundSubtCheck_ = true;
            validSwitchTeam_ = false;
        } else if (_fight.getState() == FightState.APPRENDRE_EVOLUER) {
            distinctPlacesGroundSubtCheck_ = true;
        }
        if (atLeastOneFrontPk_) {
            Numbers<Byte> fighters_ = new Numbers<Byte>();
            int mult_ = _fight.getMult();
            for (short i = CustList.FIRST_INDEX; i < mult_; i++) {
                fighters_.addAllElts(_fight.getUserTeam().fightersAtCurrentPlace(i));
            }
            if (fighters_.isEmpty()) {
                return false;
            }
            fighters_.clear();
            for (short i = CustList.FIRST_INDEX; i < mult_; i++) {
                fighters_.addAllElts(_fight.getFoeTeam().fightersAtCurrentPlace(i));
            }
            if (fighters_.isEmpty()) {
                return false;
            }
        }
        if (_fight.getFightType() == FightType.TMP_TRAINER) {
            int nbFrontPl_ = 0;
            int nbFrontAlly_ = 0;
            for (TeamPosition f: FightOrder.frontFighters(_fight)) {
                if (f.getTeam() != Fight.PLAYER) {
                    continue;
                }
                if (_fight.getFighter(f).isBelongingToPlayer()) {
                    nbFrontPl_++;
                } else {
                    nbFrontAlly_++;
                }
            }
            if (nbFrontPl_ > DataBase.ONE_POSSIBLE_CHOICE) {
                return false;
            }
            if (nbFrontAlly_ > DataBase.ONE_POSSIBLE_CHOICE) {
                return false;
            }
        }
        if (validSwitchTeam_) {
            EqList<TeamPosition> team_;
            team_ = FightOrder.fighters(_fight, Fight.FOE);
            for (TeamPosition t: team_) {
                Fighter f_ = _fight.getFighter(t);
                _fight.getFirstPositFoeFighters().put(t.getPosition(), f_.getGroundPlaceSubst());
            }
            team_ = FightOrder.fighters(_fight, Fight.PLAYER);
            for (TeamPosition t: team_) {
                Fighter f_ = _fight.getFighter(t);
                _fight.getFirstPositPlayerFighters().put(t.getPosition(), f_.getGroundPlaceSubst());
            }
            if (!validSwitchTeam(_fight, Fight.PLAYER)) {
                return false;
            }
            if (!validSwitchTeam(_fight, Fight.FOE)) {
                return false;
            }
        }
        if (distinctPlacesGroundSubtCheck_) {
            if (!FightEndRound.existSubstitute(_fight)) {
                FightEndRound.exitKoFighters(_fight);
            }
        }
        if (distinctPlacesGroundCheck_) {
            if (!FightEndRound.existSubstitute(_fight)) {
                FightKo.moveTeams(_fight);
                //never mind for matching ground places and ground places substitute
            }
            if (!validPlaces(_fight, Fight.FOE)) {
                return false;
            }
            if (!validPlaces(_fight, Fight.PLAYER)) {
                return false;
            }
        }
        if (distinctPlacesGroundSubtCheck_) {
            if (!validPlacesSubst(_fight, Fight.FOE, false)) {
                return false;
            }
            if (!validPlacesSubst(_fight, Fight.PLAYER, onlyDistinctFoeCheckSubst_)) {
                return false;
            }
        }
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(t);
            AbstractAction action_ = f_.getAction();
            if (!(action_ instanceof ActionMove)) {
                continue;
            }
            EqList<TargetCoords> targets_ = ((ActionMove)action_).getChosenTargets();
            if (targets_.isEmpty()) {
                continue;
            }
            short pos_ = ((ActionMove)action_).getChosenTargets().first().getPosition();
            if (pos_ < 0) {
                return false;
            }
            if (pos_ >= _fight.getMult()) {
                return false;
            }
        }
        if (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            //The substituted can be KO
            _fight.getChoices().clear();
            if (!Numbers.eq(_fight.getCurrentUser().getTeam(), Fight.PLAYER)) {
                return false;
            }
            if (!validAllyChoices(_fight, _data)) {
                return false;
            }
            Team equipe_=_fight.getUserTeam();
            Fighter creature_=equipe_.refPartMembres(_fight.getCurrentUser().getPosition());
            if (creature_ == null) {
                return false;
            }
            MoveData fAtt_=_data.getMove(creature_.getFinalChosenMove());
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
            if (!FightOrder.notKoBackFightersBelongingToUser(_fight, true).isEmpty()) {
                return true;
            }
            return false;
        } else if (_fight.getState() == FightState.APPRENDRE_EVOLUER) {
            if (_fight.getChoices().isEmpty()) {
                return false;
            }
            Numbers<Byte> list_ = new Numbers<Byte>();
            for (byte b: _fight.getUserTeam().getMembers().getKeys()) {
                Fighter fighter_ = _fight.getUserTeam().refPartMembres(b);
                if (!fighter_.isBelongingToPlayer()) {
                    continue;
                }
                if (fighter_.getMovesToBeLearnt().isEmpty()) {
                    if (fighter_.getMovesAbilitiesEvos().isEmpty()) {
                        continue;
                    }
                }
                list_.add(b);
            }
            if (!Numbers.equalsSetBytes(list_, _fight.getChoices().getKeys())) {
                return false;
            }
            for (byte b: _fight.getChoices().getKeys()) {
                Fighter fighter_ = _fight.getUserTeam().refPartMembres(b);
                ChoiceOfEvolutionAndMoves choice_ = _fight.getChoices().getVal(b);
                if (choice_.getName().isEmpty()) {
                    StringList possible_ = new StringList();
                    possible_.addAllElts(fighter_.getMovesToBeLearnt());
                    possible_.addAllElts(fighter_.getMovesSet());
                    for (String m: choice_.getKeptMoves()) {
                        if (!possible_.containsObj(m)) {
                            return false;
                        }
                    }
                } else {
                    if (!fighter_.getMovesAbilitiesEvos().contains(choice_.getName())) {
                        return false;
                    }
                    StringList possible_ = new StringList();
                    possible_.addAllElts(fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getMoves());
                    possible_.addAllElts(fighter_.getMovesSet());
                    for (String m: choice_.getKeptMoves()) {
                        if (!possible_.containsObj(m)) {
                            return false;
                        }
                    }
                    possible_.clear();
                    possible_.addAllElts(fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getAbilities());
                    if (possible_.size() > DataBase.ONE_POSSIBLE_CHOICE) {
                        if (!possible_.containsObj(choice_.getAbility())) {
                            return false;
                        }
                    }
                }
            }
            if(!FightFacade.win(_fight)&&FightKo.endedFight(_fight,_diff)){
                return false;
            }
            return true;
        } else if (_fight.getState() == FightState.ATTAQUES) {
            _fight.getChoices().clear();
            if(FightKo.endedFight(_fight,_diff)){
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
                byte subst_ = f_.getSubstistute();
                if (subst_ == Fighter.BACK) {
                    continue;
                }
                Fighter part_ = _fight.getTeams().getVal(t.getTeam()).getMembers().getVal(subst_);
                if (part_.estKo()) {
                    return false;
                }
//                if (part_.isBelongingToPlayer() != f_.isBelongingToPlayer()) {
//                    return false;
//                }
                if (ComparatorBoolean.diff(part_.isBelongingToPlayer(), f_.isBelongingToPlayer())) {
                    return false;
                }
                if (!part_.estArriere()) {
                    return false;
                }
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
                if (!FightFacade.allowedMovesNotEmpty(_fight,Fight.toFoeFighter(b), _data).containsObj(move_)) {
                    return false;
                }
            }
            return true;
        } else if (_fight.getState() == FightState.SWITCH_WHILE_KO_USER) {
            _fight.getChoices().clear();
            for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
                if (!_fight.getFighter(f).estKo()) {
                    return false;
                }
            }
            if(koTeam(_fight)){
                return false;
            }
            if (FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
                EqList<TeamPosition> team_;
                team_ = FightOrder.fighters(_fight, Fight.FOE);
                if (!validSubstitutingTeam(_fight, team_)) {
                    return false;
                }
                team_ = FightOrder.fighters(_fight, Fight.PLAYER);
                if (!validSubstitutingTeam(_fight, team_)) {
                    return false;
                }
            }
            return true;
        } else if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            _fight.getChoices().clear();
            if (koTeam(_fight)) {
                return false;
            }
            if (!FightEndRound.proponedSwitch(_fight)) {
                return false;
            }
            if (!_fight.getFightType().isWild()) {
                EqList<TeamPosition> team_;
                team_ = FightOrder.fighters(_fight, Fight.FOE);
                if (!validSubstitutingTeam(_fight, team_)) {
                    return false;
                }
                team_ = FightOrder.fighters(_fight, Fight.PLAYER);
                if (!validSubstitutingTeam(_fight, team_)) {
                    return false;
                }
            }
            return true;
        } else if (_fight.getState() == FightState.SURNOM) {
            _fight.getChoices().clear();
            if (!_data.getItems().contains(_fight.getCatchingBall())) {
                return false;
            }
            if (!(_data.getItem(_fight.getCatchingBall()) instanceof Ball)) {
                return false;
            }
            if (_fight.getKos().getVal(Fight.PLAYER)) {
                return false;
            }
            return true;
        } else if (_fight.getState() == FightState.CAPTURE_KO) {
            _fight.getChoices().clear();
            if (!_user.existBall(_data)) {
                return false;
            }
            if (!FightFacade.win(_fight)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean validAllyChoices(Fight _fight, DataBase _data) {
        Numbers<Byte> noTeams_ = new Numbers<Byte>();
        noTeams_.add(Fight.PLAYER);
        noTeams_.add(Fight.FOE);
        if (_fight.getAllyChoiceSet().size() != DataBase.ONE_POSSIBLE_CHOICE) {
            for (MoveTarget p: _fight.getAllyChoiceSet()) {
                if (!_data.getMoves().contains(p.getMove())) {
                    return false;
                }
                if (p.getTarget().getPosition() == Fighter.BACK) {
                    return false;
                }
                if (p.getTarget().getPosition() < 0) {
                    return false;
                }
                if (!noTeams_.containsObj((byte) p.getTarget().getTeam())) {
                    return false;
                }
                if (p.getTarget().getPosition() >= _fight.getMult()) {
                    return false;
                }
            }
        }
        for (MoveTarget p: _fight.getAllyChoiceValuesSet()) {
            if (!p.getMove().isEmpty()) {
                if (!_data.getMoves().contains(p.getMove())) {
                    return false;
                }
                if (_data.getMove(p.getMove()).getTargetChoice().isWithChoice()) {
                    if (p.getTarget().getPosition() == Fighter.BACK) {
                        return false;
                    }
                    if (p.getTarget().getPosition() < 0) {
                        return false;
                    }
                    if (!noTeams_.containsObj((byte) p.getTarget().getTeam())) {
                        return false;
                    }
                    if (p.getTarget().getPosition() >= _fight.getMult()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean validPlacesSubst(Fight _fight, byte _team, boolean _onlyDistinctFoeCheckSubst) {
        Numbers<Byte> distinct_ = new Numbers<Byte>();
        int nbNotKo_;
        nbNotKo_ = 0;
        for (Fighter t: _fight.getTeams().getVal(_team).getMembers().values()) {
            if (!t.estKo()) {
                nbNotKo_++;
            }
            if (Numbers.eq(t.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            if (t.getGroundPlaceSubst() >= _fight.getMult()) {
                return false;
            }
            distinct_.add(t.getGroundPlaceSubst());
        }
        int size_ = distinct_.size();
        distinct_.removeDuplicates();
        if (!Numbers.eq(size_, distinct_.size())) {
            return false;
        }
        if (!_onlyDistinctFoeCheckSubst) {
            if (!FightEndRound.existSubstitute(_fight)) {
                if (distinct_.size() != nbNotKo_) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean validPlaces(Fight _fight, byte _team) {
        Numbers<Byte> distinct_ = new Numbers<Byte>();
        for (Fighter t: _fight.getTeams().getVal(_team).getMembers().values()) {
            if (Numbers.eq(t.getGroundPlace(), Fighter.BACK)) {
                continue;
            }
            if (t.getGroundPlace() >= _fight.getMult()) {
                return false;
            }
            distinct_.add(t.getGroundPlace());
        }
        int size_ = distinct_.size();
        distinct_.removeDuplicates();
        if (!Numbers.eq(size_, distinct_.size())) {
            return false;
        }
        return true;
    }
    static boolean validSwitchTeam(Fight _fight, byte _team) {
        Numbers<Byte> replace_ = new Numbers<Byte>();
        for (TeamPosition c:FightOrder.fighters(_fight, _team)) {
            Fighter membre_= _fight.getFighter(c);
            if (!Numbers.eq(membre_.getSubstistute(), Fighter.BACK)) {
                replace_.add(membre_.getSubstistute());
            }
        }
        int nb_ = replace_.size();
        replace_.removeDuplicates();
        if (nb_ != replace_.size()) {
            return false;
        }
        return true;
    }
    static boolean validSubstitutingTeam(Fight _fight, EqList<TeamPosition> _pseusoTeam) {
        Numbers<Byte> replace_ = new Numbers<Byte>();
        Numbers<Byte> replaceNoPlayer_ = new Numbers<Byte>();
        byte teamNo_ = _pseusoTeam.first().getTeam();
        NumberMap<Byte,Byte> subst_;
        if (Numbers.eq(teamNo_, Fight.FOE)) {
            subst_ = _fight.getFirstPositFoeFighters();
        } else {
            subst_ = _fight.getFirstPositPlayerFighters();
        }
        int nbNotKo_ = 0;
        int nbNotKoNpc_ = 0;
        for(TeamPosition c:_pseusoTeam){
            Fighter membre_= _fight.getFighter(c);
            byte substLoc_ = subst_.getVal(c.getPosition());
            if(membre_.estKo()) {
                if (!Numbers.eq(substLoc_, Fighter.BACK)) {
                    return false;
                }
            } else {
                nbNotKo_++;
                if (!membre_.isBelongingToPlayer()) {
                    nbNotKoNpc_++;
                }
                if (!Numbers.eq(substLoc_, Fighter.BACK)) {
                    replace_.add(substLoc_);
                    if (!membre_.isBelongingToPlayer()) {
                        replaceNoPlayer_.add(substLoc_);
                    }
                }
            }
        }
        int nb_ = replaceNoPlayer_.size();
        replaceNoPlayer_.removeDuplicates();
        if (nb_ != replaceNoPlayer_.size()) {
            return false;
        }
        if (nb_ > _fight.getMult()) {
            return false;
        }
        if (Numbers.eq(teamNo_, Fight.FOE)) {
            if (nbNotKo_ > _fight.getMult()) {
                return nb_ == _fight.getMult();
            }
            return nb_ == nbNotKo_;
        }
        int diff_ = _fight.getMult() - _fight.getPlayerMaxNumberFrontFighters();
        if (nbNotKoNpc_ > diff_) {
            return replaceNoPlayer_.size() == diff_;
        }
        return replaceNoPlayer_.size() == nbNotKoNpc_;
    }

    public static void chooseFrontFighter(Fight _fight, byte _place, Difficulty _diff, DataBase _import) {
        _fight.setChosenIndexFront(_place);
        _fight.setChosenIndexBack(Fighter.BACK);
        _fight.getPossibleActionsCurFighter().clear();
        if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            Team equipe_=_fight.getUserTeam();
            byte substitute_ = Fighter.BACK;
            Numbers<Byte> listAll_ = new Numbers<Byte>(equipe_.getMembers().getKeys());
            listAll_.sort();
            int i_ = CustList.FIRST_INDEX;
            for (byte b: listAll_) {
                Fighter fighter_ = equipe_.getMembers().getVal(b);
                if (Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
                    continue;
                }
                if (!fighter_.isBelongingToPlayer()) {
                    continue;
                }
                if (i_ == _place) {
                    substitute_ = b;
                    break;
                }
                i_++;
            }
            if (substitute_ == Fighter.BACK) {
                _fight.setPossibleActionsCurFighter(new EnumList<ActionType>());
                _fight.setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
                _fight.setSelectedActionCurFighter(ActionType.NOTHING);
                _fight.setChosableFoeTargets(new BooleanList());
                _fight.setChosablePlayerTargets(new BooleanList());
                _fight.setChosenIndexFront(Fighter.BACK);
                _fight.setChosenSubstitute(Fighter.BACK);
                _fight.setChosenFoeTarget(Fighter.BACK);
                _fight.setChosenPlayerTarget(Fighter.BACK);
                _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
                _fight.setChosenHealingMove(DataBase.EMPTY_STRING);
                return;
            }
            _fight.setChosenSubstitute(_fight.getFirstPositPlayerFighters().getVal(substitute_));
            return;
        }
        _fight.getPossibleActionsCurFighter().add(ActionType.MOVE);
        _fight.getPossibleActionsCurFighter().add(ActionType.SWITCH);
        _fight.getPossibleActionsCurFighter().add(ActionType.HEALING);
        _fight.getPossibleActionsCurFighter().add(ActionType.NOTHING);
        Team playerTeam_ = _fight.getUserTeam();
        Numbers<Byte> fighters_ = playerTeam_.fightersAtCurrentPlaceIndex(_place, true);
        if (fighters_.isEmpty()) {
            _fight.setPossibleActionsCurFighter(new EnumList<ActionType>());
            _fight.setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
            _fight.setSelectedActionCurFighter(ActionType.NOTHING);
            _fight.setChosenIndexFront(Fighter.BACK);
            _fight.setChosenSubstitute(Fighter.BACK);
            _fight.setChosenFoeTarget(Fighter.BACK);
            _fight.setChosenPlayerTarget(Fighter.BACK);
            _fight.setChosableFoeTargets(new BooleanList());
            _fight.setChosablePlayerTargets(new BooleanList());
            _fight.getPossibleActionsCurFighter().clear();
            _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
            _fight.setChosenHealingMove(DataBase.EMPTY_STRING);
            return;
        }
        Fighter fighter_ = playerTeam_.getMembers().getVal(fighters_.first());
        AbstractAction action_ = fighter_.getAction();
        if (action_ instanceof ActionMove) {
            _fight.setCurrentFighterMoves(frontFighterMoves(_fight, _place, _import));
            _fight.setSelectedActionCurFighter(ActionType.MOVE);
            ActionMove actionMove_ = (ActionMove) action_;
            initChosableTargets(_fight, _place, actionMove_.getFirstChosenMove(), _diff, _import);
            if (!actionMove_.getChosenTargets().isEmpty()) {
                if (Numbers.eq(actionMove_.getChosenTargets().first().getTeam(), Fight.PLAYER)) {
                    _fight.setChosenPlayerTarget((byte) actionMove_.getChosenTargets().first().getPosition());
                    _fight.setChosenFoeTarget(Fighter.BACK);
                } else {
                    _fight.setChosenFoeTarget((byte) actionMove_.getChosenTargets().first().getPosition());
                    _fight.setChosenPlayerTarget(Fighter.BACK);
                }
            }
            return;
        }
        if (action_ instanceof ActionSwitch) {
            _fight.getCurrentFighterMoves().clear();
            _fight.setSelectedActionCurFighter(ActionType.SWITCH);
            ActionSwitch actionSwitch_ = (ActionSwitch) action_;
            int ind_ = _fight.getUserTeam().indexOfSubstitute(actionSwitch_.getSubstitute());
            _fight.setChosenSubstitute((byte) ind_);
            return;
        }
        if (action_ instanceof ActionHeal) {
            _fight.setSelectedActionCurFighter(ActionType.HEALING);
            ActionHeal actionHeal_ = (ActionHeal) action_;
            _fight.setChosenHealingMove(actionHeal_.getChosenHealingItem());
            if (actionHeal_ instanceof ActionHealMove) {
                _fight.setCurrentFighterMoves(frontFighterMoves(_fight, _place, _import));
                _fight.setChosenMoveFront(((ActionHealMove)actionHeal_).getFirstChosenMove());
            } else {
                _fight.getCurrentFighterMoves().clear();
            }
            return;
        }
        _fight.setSelectedActionCurFighter(ActionType.MOVE);
        _fight.setCurrentFighterMoves(frontFighterMoves(_fight, _place, _import));
    }

    public static void chooseBackFighter(Fight _fight, byte _place, DataBase _import) {
        _fight.clearComments();
        if (_fight.getState() == FightState.SWITCH_PROPOSE) {
            _fight.setChosenIndexFront(Fighter.BACK);
            Team equipe_=_fight.getUserTeam();
            byte substitute_ = Fighter.BACK;
            byte i_ = CustList.FIRST_INDEX;
            Numbers<Byte> list_ = new Numbers<Byte>(equipe_.getMembers().getKeys());
//            list_.sort(new NaturalComparator<Byte>() {
//                @Override
//                public int compare(Byte _o1, Byte _o2) {
//                    return _o1.compareTo(_o2);
//                }
//            });
            list_.sort();
            for (byte b: list_) {
                Fighter fighter_ = equipe_.getMembers().getVal(b);
                if (!Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
                    continue;
                }
                if (i_ == _place) {
                    substitute_ = b;
                    break;
                }
                i_++;
            }
            if (Numbers.eq(substitute_, Fighter.BACK)) {
                _fight.setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
                _fight.setSelectedActionCurFighter(ActionType.NOTHING);
                _fight.setChosableFoeTargets(new BooleanList());
                _fight.setChosablePlayerTargets(new BooleanList());
                _fight.setPossibleActionsCurFighter(new EnumList<ActionType>());
                _fight.setChosenIndexBack(Fighter.BACK);
                _fight.setChosenSubstitute(Fighter.BACK);
                _fight.setChosenFoeTarget(Fighter.BACK);
                _fight.setChosenPlayerTarget(Fighter.BACK);
                _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
                _fight.setChosenHealingMove(DataBase.EMPTY_STRING);
                return;
            }
            _fight.setChosenIndexBack(_place);
            _fight.setChosenSubstitute(_fight.getFirstPositPlayerFighters().getVal(substitute_));
            return;
        }
        if (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            chooseBackFighterWhileRound(_fight, _place, _import);
            return;
        }
        if (_fight.getSelectedActionCurFighter() == ActionType.SWITCH) {
            setSubstituteSwitch(_fight, _place);
            validateSwitch(_fight);
            return;
        }
        if (_import.isBatonPassMove(_fight.getChosenMoveFront())) {
            chooseBackFighterAddon(_fight, _place, _import);
            validateSwitch(_fight);
            return;
        }
        if (_import.getMovesFullHeal().containsObj(_fight.getChosenMoveFront())) {
            chooseBackFighterAddon(_fight, _place, _import);
            validateSwitch(_fight);
            return;
        }
        _fight.setChosenIndexBack(_place);
        _fight.setChosenIndexFront(Fighter.BACK);
        _fight.getPossibleActionsCurFighter().clear();
        _fight.getPossibleActionsCurFighter().add(ActionType.NOTHING);
        _fight.getPossibleActionsCurFighter().add(ActionType.HEALING);
        _fight.setSelectedActionCurFighter(ActionType.NOTHING);
        _fight.getCurrentFighterMoves().clear();
        Team playerTeam_ = _fight.getUserTeam();
        byte sub_ = playerTeam_.substituteAtIndex(_place);
        if (sub_ == Fighter.BACK) {
            _fight.setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
            _fight.setSelectedActionCurFighter(ActionType.NOTHING);
            _fight.setChosableFoeTargets(new BooleanList());
            _fight.setChosablePlayerTargets(new BooleanList());
            _fight.setPossibleActionsCurFighter(new EnumList<ActionType>());
            _fight.setChosenIndexBack(Fighter.BACK);
            _fight.setChosenSubstitute(Fighter.BACK);
            _fight.setChosenFoeTarget(Fighter.BACK);
            _fight.setChosenPlayerTarget(Fighter.BACK);
            _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
            _fight.setChosenHealingMove(DataBase.EMPTY_STRING);
            return;
        }
        AbstractAction action_ = playerTeam_.getMembers().getVal(sub_).getAction();
        if (action_ instanceof ActionHeal) {
            _fight.setSelectedActionCurFighter(ActionType.HEALING);
            ActionHeal actionHeal_ = (ActionHeal) action_;
            _fight.setChosenHealingMove(actionHeal_.getChosenHealingItem());
            if (actionHeal_ instanceof ActionHealMove) {
                _fight.setCurrentFighterMoves(backFighterMoves(_fight, _place, _import));
                _fight.setChosenMoveFront(((ActionHealMove)actionHeal_).getFirstChosenMove());
            }
        }
    }

    public static void deselect(Fight _fight) {
        _fight.setChosenIndexBack(Fighter.BACK);
        _fight.setChosenIndexFront(Fighter.BACK);
    }

    static void validateSwitch(Fight _fight) {
        if (!_fight.isError()) {
            _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
        }
        _fight.setChosenIndexBack(Fighter.BACK);
        _fight.setChosenIndexFront(Fighter.BACK);
        _fight.getCurrentFighterMoves().clear();
        _fight.getPossibleActionsCurFighter().clear();
        _fight.setSelectedActionCurFighter(ActionType.NOTHING);
    }

    public static void changeAction(Fight _fight, ActionType _action, DataBase _import) {
        _fight.setSelectedActionCurFighter(_action);
        if (_action == ActionType.MOVE) {
            byte index_ = _fight.getChosenIndexFront();
            _fight.setCurrentFighterMoves(frontFighterMoves(_fight, index_, _import));
            return;
        }
        byte index_ = _fight.getChosenIndexFront();
        if (!Numbers.eq(index_, Fighter.BACK)) {
            Team equipe_=_fight.getUserTeam();
//            CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
            Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
            Fighter creature_=equipe_.refPartMembres(list_.first());
            creature_.cancelActions();
        } else {
            index_ = _fight.getChosenIndexBack();
            if (!Numbers.eq(index_, Fighter.BACK)) {
                Team equipe_=_fight.getUserTeam();
                byte substitute_ = equipe_.substituteAtIndex(index_);
                Fighter creature_=equipe_.refPartMembres(substitute_);
                creature_.cancelActions();
            }
        }
        _fight.getCurrentFighterMoves().clear();
    }

    static NatStringTreeMap<ChosenMoveInfos> frontFighterMoves(Fight _fight, byte _place, DataBase _import) {
        Team playerTeam_ = _fight.getUserTeam();
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(_place);
        Numbers<Byte> fighters_ = playerTeam_.fightersAtCurrentPlaceIndex(_place, true);
        if (fighters_.isEmpty()) {
            return new NatStringTreeMap<ChosenMoveInfos>();
        }
        TeamPosition f_ = Fight.toUserFighter(fighters_.first());
        return fighterMoves(_fight, f_, _import);
    }

    static NatStringTreeMap<ChosenMoveInfos> backFighterMoves(Fight _fight, byte _place, DataBase _import) {
        Team playerTeam_ = _fight.getUserTeam();
        byte substitute_ = playerTeam_.substituteAtIndex(_place);
        if (Numbers.eq(substitute_, Fighter.BACK)) {
            return new NatStringTreeMap<ChosenMoveInfos>();
        }
        TeamPosition f_ = Fight.toUserFighter(substitute_);
        return fighterMoves(_fight, f_, _import);
    }

    static NatStringTreeMap<ChosenMoveInfos> fighterMoves(Fight _fight, TeamPosition _f, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_f);
        if (!fighter_.isBelongingToPlayer()) {
            return new NatStringTreeMap<ChosenMoveInfos>();
        }
        StringList attaquesAutorisees_ = FightRules.allowedMoves(_fight,_f,_import);
        if (attaquesAutorisees_.isEmpty()) {
            NatStringTreeMap<ChosenMoveInfos> map_;
            String move_ = _import.getDefaultMove();
            map_ = new NatStringTreeMap<ChosenMoveInfos>();
            ChosenMoveInfos chosen_;
            chosen_ = new ChosenMoveInfos();
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
        allMoves_.removeDuplicates();
        NatStringTreeMap<ChosenMoveInfos> map_;
        map_ = new NatStringTreeMap<ChosenMoveInfos>();
        for (String m: allMoves_) {
            ChosenMoveInfos chosen_;
            chosen_ = new ChosenMoveInfos();
            chosen_.setName(m);
            chosen_.setTypes(_import.getMove(m).getTypes());
            chosen_.setUsable(attaquesAutorisees_.containsObj(m));
            short max_ = fighter_.maxPowerPointsMove(m, _import);
            short current_ = fighter_.powerPointsMove(m);
            UsesOfMove uses_ = new UsesOfMove(current_,max_);
            chosen_.setUses(uses_);
            map_.put(_import.translateMove(m), chosen_);
        }
        return map_;
    }

    public static void chooseMove(Fight _fight, String _move, Difficulty _diff, DataBase _import) {
        if (_fight.getSelectedActionCurFighter() == ActionType.HEALING) {
            _fight.setChosenMoveFront(_move);
            setChosenHealingItemMove(_fight, _move);
            return;
        }
        if (_fight.getSelectedActionCurFighter() != ActionType.MOVE) {
            return;
        }
        _fight.getChosableFoeTargets().clear();
        _fight.getChosablePlayerTargets().clear();
        Team playerTeam_ = _fight.getUserTeam();
        byte index_ = _fight.getChosenIndexFront();
//        CustList<Byte> fighters_ = playerTeam_.fightersAtCurrentPlace(index_);
        Numbers<Byte> fighters_ = playerTeam_.fightersAtCurrentPlaceIndex(index_, true);
        if (fighters_.isEmpty()) {
            return;
        }
        BooleanList playerTargets_;
        BooleanList foeTargets_;
        initChosableTargets(_fight, index_, _move, _diff, _import);
        playerTargets_ = _fight.getChosablePlayerTargets();
        foeTargets_ = _fight.getChosableFoeTargets();
        if (foeTargets_.isEmpty()) {
            //playerTargets_.size() == foeTargets_.size()
            // ==> playerTargets_.isEmpty()
            setFirstChosenMove(_fight, index_, _move);
            return;
        }
        Numbers<Integer> possiblePlayerChoices_;
        possiblePlayerChoices_ = playerTargets_.indexesOfObj(true);
        Numbers<Integer> possibleFoeChoices_;
        possibleFoeChoices_ = foeTargets_.indexesOfObj(true);
        _fight.setChosenMoveFront(_move);
        if (possiblePlayerChoices_.isEmpty() && possibleFoeChoices_.isEmpty()) {
            setFirstChosenMove(_fight, index_, _move);
            foeTargets_.clear();
            playerTargets_.clear();
            return;
        }
        if (possiblePlayerChoices_.size() + possibleFoeChoices_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            if (!possibleFoeChoices_.isEmpty()) {
                Team equipe_=_fight.getUserTeam();
//                CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
                Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
                Fighter creature_=equipe_.refPartMembres(list_.first());
                creature_.setFirstChosenMoveTarget(_move, TargetCoords.toFoeTarget(possibleFoeChoices_.first().shortValue()));
            } else {
                Team equipe_=_fight.getUserTeam();
//                CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
                Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
                Fighter creature_=equipe_.refPartMembres(list_.first());
                creature_.setFirstChosenMoveTarget(_move, TargetCoords.toUserTarget(possiblePlayerChoices_.first().shortValue()));
            }
            foeTargets_.clear();
            playerTargets_.clear();
            return;
        }
    }

    static void initChosableTargets(Fight _fight, byte _index, String _move, Difficulty _diff, DataBase _import) {
        MoveData move_ = _import.getMove(_move);
        if (!move_.getTargetChoice().isWithChoice()) {
            BooleanList playerTargets_;
            playerTargets_ = new BooleanList();
            BooleanList foeTargets_;
            foeTargets_ = new BooleanList();
            _fight.setChosenMoveFront(_move);
            _fight.setChosableFoeTargets(foeTargets_);
            _fight.setChosablePlayerTargets(playerTargets_);
            return;
        }
//        CustList<Byte> fighters_ = _fight.getUserTeam().fightersAtCurrentPlace(_index);
        Numbers<Byte> fighters_ = _fight.getUserTeam().fightersAtCurrentPlaceIndex(_index, true);
        TeamPosition f_ = Fight.toUserFighter(fighters_.first());
        byte groundPlace_ = _fight.getFighter(f_).getGroundPlace();
        BooleanList playerTargets_;
        playerTargets_ = new BooleanList();
        BooleanList foeTargets_;
        foeTargets_ = new BooleanList();
        Numbers<Byte> playerFightersTakenPlace_;
        playerFightersTakenPlace_ = new Numbers<Byte>();
        NumberMap<Byte,Boolean> playerFightersPlace_;
        playerFightersPlace_ = new NumberMap<Byte,Boolean>();
        int mult_ = _fight.getMult();
        for (byte b = CustList.FIRST_INDEX; b < mult_; b++) {
//            CustList<Byte> fightersKeys_ = _fight.getUserTeam().fightersAtCurrentPlace(b);
            Numbers<Byte> fightersKeys_ = _fight.getUserTeam().fightersAtCurrentPlaceIndex(b, false);
            boolean used_ = !fightersKeys_.isEmpty();
            playerFightersPlace_.put(b, used_);
            playerTargets_.add(false);
            if (used_) {
                playerFightersTakenPlace_.add(b);
            }
        }
        Numbers<Byte> foeFightersTakenPlace_;
        foeFightersTakenPlace_ = new Numbers<Byte>();
        NumberMap<Byte,Boolean> foeFightersPlace_;
        foeFightersPlace_ = new NumberMap<Byte,Boolean>();
        for (byte b = CustList.FIRST_INDEX; b < mult_; b++) {
//            CustList<Byte> fightersKeys_ = _fight.getFoeTeam().fightersAtCurrentPlace(b);
            Numbers<Byte> fightersKeys_ = _fight.getFoeTeam().fightersAtCurrentPlaceIndex(b, false);
            boolean used_ = !fightersKeys_.isEmpty();
            foeFightersPlace_.put(b, used_);
            foeTargets_.add(false);
            if (used_) {
                foeFightersTakenPlace_.add(b);
            }
        }
        if (move_.getTargetChoice() == TargetChoice.ALLIE) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            for (byte k: playerFightersTakenPlace_) {
//                playerTargets_.set(k,!Numbers.eq(k, _index));
                playerTargets_.set(k,!Numbers.eq(k, groundPlace_));
            }
        } else if (move_.getTargetChoice() == TargetChoice.ADJ_UNIQ) {
            EqList<TeamPosition> list_;
            list_ = FightOrder.closestFigthersSameTeam(_fight, f_, _diff);
            for (TeamPosition f: list_) {
                Fighter partner_ = _fight.getFighter(f);
                byte place_ = partner_.getGroundPlace();
                playerTargets_.set(place_, true);
            }
            list_ = FightOrder.closestFigthersFoeTeam(_fight, f_, _diff);
            for (TeamPosition f: list_) {
                Fighter partner_ = _fight.getFighter(f);
                byte place_ = partner_.getGroundPlace();
                foeTargets_.set(place_, true);
            }
        } else if (move_.getTargetChoice() == TargetChoice.UNIQUE_IMPORTE) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            for (byte k: playerFightersTakenPlace_) {
                playerTargets_.set(k, true);
            }
//            for (byte k: foeFightersPlace_.getKeys(true))
            for (byte k: foeFightersTakenPlace_) {
                foeTargets_.set(k, true);
            }
        } else if (move_.getTargetChoice() == TargetChoice.AUTRE_UNIQ) {
//            for (byte k: playerFightersPlace_.getKeys(true))
            for (byte k: playerFightersTakenPlace_) {
//                playerTargets_.set(k,!Numbers.eq(k, _index));
                playerTargets_.set(k,!Numbers.eq(k, groundPlace_));
            }
//            for (byte k: foeFightersPlace_.getKeys(true))
            for (byte k: foeFightersTakenPlace_) {
                foeTargets_.set(k, true);
            }
        } else {
            //ANY_FOE
//            for (byte k: foeFightersPlace_.getKeys(true))
            for (byte k: foeFightersTakenPlace_) {
                foeTargets_.set(k, true);
            }
        }
        _fight.setChosenMoveFront(_move);
        _fight.setChosableFoeTargets(foeTargets_);
        _fight.setChosablePlayerTargets(playerTargets_);
    }

    /** After chosing a fighter among the user's team,
    if the chosen fighter can use a move not being the default move,
    the user has to choose a move for the fighter,
    else
    the default move is automatically chosen*/
    static StringList allowedMovesNotEmpty(Fight _fight, TeamPosition _combattant, DataBase _import){
        StringList attaquesAutorisees_= FightRules.allowedMoves(_fight,_combattant,_import);
        if(attaquesAutorisees_.isEmpty()){
            attaquesAutorisees_.add(_import.getDefaultMove());
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
        _fight.setError(false);
        if (_fight.getState() == FightState.ATTAQUES) {
            if (!FightRules.playable(_fight, _user, _diff, _import)) {
                _fight.setError(true);
                return;
            }
        }
        _fight.getUsedItemsWhileRound().clear();
        if (_enableAnimation) {
            beginRound(_fight, _diff, _import);
        } else {
            roundAllThrowersChooseActionsFoe(_fight, _diff, _user, _import);
        }
    }

    static void roundAllThrowersChooseActionsFoe(Fight _fight,Difficulty _diff,Player _user, DataBase _import){
        if(_fight.getBeginRound()){
            if(_fight.getFightType().isWild()){
                FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
            }
        }
        FightRound.roundAllThrowers(_fight,_diff,_user,_import);
        if(!_fight.getAcceptableChoices()){
            return;
        }
        if (koTeam(_fight)) {
            return;
        }
        if (_fight.getState() != FightState.ATTAQUES) {
            return;
        }
        if (_fight.getFightType().isWild()) {
            return;
        }
        //&& _fight.getState() != FightState.SWITCH_WHILE_KO_USER
        //ia adv
        FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
    }

    static void beginRound(Fight _fight, Difficulty _diff, DataBase _import) {
        if(_fight.getBeginRound()){
            if(_fight.getFightType().isWild()){
                FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
            }
        }
        FightRound.beginRound(_fight, _diff, _import);
    }

    public static void roundUser(Fight _fight, Difficulty _diff, DataBase _import) {
        _fight.clearComments();
        FightRound.roundUser(_fight, _diff, _import);
    }

    public static void endRoundFightBasic(Fight _fight, Difficulty _diff, Player _user, DataBase _import) {
        FightRound.endRoundFight(_fight, _diff, _user, _import);
        if (koTeam(_fight)) {
            return;
        }
//        if (!_fight.getRemainingFighters().isEmpty()) {
//            return;
//        }
        if(!_fight.getAcceptableChoices()){
            return;
        }
        if (_fight.getState() != FightState.ATTAQUES) {
            return;
        }
        if (_fight.getFightType().isWild()) {
            return;
        }
        //&& _fight.getState() != FightState.SWITCH_WHILE_KO_USER
        //foe art. int.
        FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
    }

//    public static Numbers<Byte> getKoPlayerFrontFightersPlaces(Fight _fight) {
//        Numbers<Byte> places_ = new Numbers<Byte>();
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

    public static NatStringTreeMap<BallNumberRate> calculateCatchingRates(Fight _fight,Difficulty _diff,Player _player,DataBase _import) {
        NatStringTreeMap<BallNumberRate> tree_ = new NatStringTreeMap<BallNumberRate>();
        boolean present_ = _player.estAttrape(_fight.wildPokemon().getName());
        for (String o: _import.getItems().getKeys()) {
            Item obj_ = _import.getItem(o);
            if (!(obj_ instanceof Ball)) {
                continue;
            }
            LgInt eff_ = _player.getInventory().getNumber(o);
            if (eff_.isZero()) {
                continue;
            }
            Rate rate_ = FightRound.calculateCatchingRate(_fight, o, present_, _diff, _import);
            BallNumberRate info_ = new BallNumberRate(eff_, rate_, o);
            tree_.put(_import.translateItem(o), info_);
        }
        return tree_;
    }

    public static void attemptCatching(Fight _fight,String _ball,boolean _dejaCapture,Difficulty _diff,Player _user,DataBase _import, boolean _enableAnimation){
        Rate proba_=FightRound.calculateCatchingRate(_fight,_ball,_dejaCapture,_diff,_import);
        _fight.setKeepRound(true);
        if(FightSuccess.tirage(_import, proba_)){
            _fight.setKeepRound(false);
            _fight.setCatchingBall(_ball);
            _fight.setState(FightState.SURNOM);
            //le pokemon est capture
            return;
        }
        frontFighterChoiceFleeingCatching(_fight);
        if (_enableAnimation) {
            beginRound(_fight, _diff, _import);
        } else {
            roundAllThrowersChooseActionsFoe(_fight,_diff,_user,_import);
        }
    }

    public static void attemptFlee(Fight _fight,Difficulty _diff,Player _user,DataBase _import, boolean _enableAnimation){
        Rate proba_= calculateFleeingRate(_fight,_diff,_import);
        if(_fight.getNbFleeAttempt()<255){
            _fight.setNbFleeAttempt((short) (_fight.getNbFleeAttempt()+1));
        }
        _fight.setKeepRound(true);
        if(FightSuccess.tirage(_import, proba_)){
            //la fuite est reussie ==> fin de combat
            _fight.setKeepRound(false);
            _fight.setState(FightState.REDESSIN_SCENE);
            return;
        }
        frontFighterChoiceFleeingCatching(_fight);
        if (_enableAnimation) {
            beginRound(_fight, _diff, _import);
        } else {
            roundAllThrowersChooseActionsFoe(_fight,_diff,_user,_import);
        }
    }

    public static Rate calculateFleeingRate(Fight _fight,Difficulty _diff,DataBase _import) {
        return FightRound.calculateFleeingRate(_fight,_diff,_import);
    }

    public static void roundWhileKoPlayer(Fight _fight,Difficulty _diff, Player _user,DataBase _import, boolean _enableAnimation) {
        _fight.clearComments();
        if (FightEndRound.proponedSwitchWhileKoPlayer(_fight)) {
            _fight.setKeepRound(false);
            _fight.setEndRoundFightKoPlayer(false);
            FightSending.sendSubstitutes(_fight, _diff, _user, _import);
        } else {
            _fight.setKeepRound(true);
            _fight.setEndRoundFightKoPlayer(true);
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
            if (_enableAnimation) {
                beginRound(_fight, _diff, _import);
            } else {
                roundAllThrowersChooseActionsFoe(_fight, _diff, _user, _import);
            }
        }
    }

    public static void sendSubstitutesChooseActions(Fight _fight,Difficulty _diff, Player _user,DataBase _import){
        _fight.clearComments();
        FightSending.sendSubstitutes(_fight, _diff, _user, _import);
        if (_fight.getState() == FightState.ATTAQUES) {
            //||_fight.getState() == FightState.SWITCH_WHILE_KO_USER
            //ia adv
            if(!_fight.getFightType().isWild()){
                FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
            }
        }
    }

    public static boolean koTeam(Fight _fight){
        return _fight.getKos().getVal(Fight.PLAYER)||_fight.getKos().getVal(Fight.FOE);
    }

    public static void endFight(Fight _fight){
        _fight.setFightType(FightType.NOTHING);
        _fight.getEffects().clear();
        for(TeamPosition c: fightersBelongingToUser(_fight,true)){
            _fight.getUserTeam().toutSupprimerCombattantsContreAdvMembre(c.getPosition());
        }
    }

    public static boolean win(Fight _fight){
        return !_fight.getKos().getVal(Fight.PLAYER)&&_fight.getKos().getVal(Fight.FOE);
    }

    public static boolean equality(Fight _fight){
        return _fight.getKos().getVal(Fight.PLAYER)&&_fight.getKos().getVal(Fight.FOE);
    }

    public static boolean loose(Fight _fight){
        return _fight.getKos().getVal(Fight.PLAYER)&&!_fight.getKos().getVal(Fight.FOE);
    }

    public static FightState fightStatement(Fight _fight, boolean _existBall,Difficulty _diff){
        if(_fight.getFightType().isWild()){
            if(loose(_fight)||equality(_fight)){
                return FightState.FIN_CBT_SAUVAGE;
            }
            if(win(_fight)){
                if(_diff.getAllowCatchingKo()){
                    if (_existBall) {
                        return FightState.CAPTURE_KO;
                    }
                }
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
        if(!_diff.getRestoredMovesEndFight()){
            if(!membre_.isChanged()){
                for(String m:moves_.getKeys()){
                    if (!membre_.getCurrentMovesSet().containsObj(m)) {
                        continue;
                    }
                    moves_.getVal(m).setCurrent(membre_.getCurrentMove(m).getCurrent());
                }
            }
        }
        return moves_;
    }

    public static NatTreeMap<Byte,Fighter> getPlayerTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        NatTreeMap<Byte,Byte> keys_ = new NatTreeMap<Byte,Byte>();
        byte index_ = CustList.FIRST_INDEX;
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            keys_.put(index_,k);
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        for (byte k: keys_.values()) {
            Fighter f_ = team_.getMembers().getVal(k);
            tree_.put(index_, f_);
            index_++;
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getFoeFrontTeam(Fight _fight) {
        Team team_ = _fight.getFoeTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            tree_.put(f_.getGroundPlaceSubst(), f_);
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getUnionFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            tree_.put(f_.getGroundPlaceSubst(), f_);
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getPlayerFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            if (Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
//            if (f_.estArriere()) {
//                continue;
//            }
//            tree_.put(f_.getGroundPlace(), f_);
            tree_.put(f_.getGroundPlaceSubst(), f_);
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getPlayerBackTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        NatTreeMap<Byte,Byte> keys_ = new NatTreeMap<Byte,Byte>();
        byte index_ = CustList.FIRST_INDEX;
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            if (!Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
//            if (!f_.estArriere()) {
//                continue;
//            }
            keys_.put(index_,k);
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        for (byte k: keys_.values()) {
            Fighter f_ = team_.getMembers().getVal(k);
            tree_.put(index_, f_);
            index_++;
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getPlayerFrontTeamForSubstituting(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            if (Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            tree_.put(f_.getGroundPlaceSubst(), f_);
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getPlayerBackTeamForSubstituting(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        NatTreeMap<Byte,Byte> keys_ = new NatTreeMap<Byte,Byte>();
        byte index_ = CustList.FIRST_INDEX;
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            if (!Numbers.eq(f_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            keys_.put(index_,k);
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        for (byte k: keys_.values()) {
            Fighter f_ = team_.getMembers().getVal(k);
            tree_.put(index_, f_);
            index_++;
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getAllyFrontTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (f_.isBelongingToPlayer()) {
                continue;
            }
            if (f_.estArriere()) {
                continue;
            }
            tree_.put(f_.getGroundPlace(), f_);
        }
        return tree_;
    }

    public static NatTreeMap<Byte,Fighter> getAllyBackTeam(Fight _fight) {
        Team team_ = _fight.getUserTeam();
        NatTreeMap<Byte,Fighter> tree_ = new NatTreeMap<Byte,Fighter>();
        NatTreeMap<Byte,Byte> keys_ = new NatTreeMap<Byte,Byte>();
        byte index_ = CustList.FIRST_INDEX;
        for (byte k: team_.getMembers().getKeys()) {
            Fighter f_ = team_.getMembers().getVal(k);
            if (f_.isBelongingToPlayer()) {
                continue;
            }
            if (!f_.estArriere()) {
                continue;
            }
            keys_.put(index_,k);
            index_++;
        }
        index_ = CustList.FIRST_INDEX;
        for (byte k: keys_.values()) {
            Fighter f_ = team_.getMembers().getVal(k);
            tree_.put(index_, f_);
            index_++;
        }
        return tree_;
    }

    public static boolean isChosableForLearningAndEvolving(Fight _fight, byte _key) {
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        if (Numbers.eq(key_, Fighter.BACK)) {
            return false;
        }
        return _fight.getChoices().contains(key_);
    }

    public static void choosePokemonForLearningAndEvolving(Fight _fight, byte _key, DataBase _d) {
        _fight.setChosenIndex(_key);
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        if (Numbers.eq(key_, Fighter.BACK)) {
            _fight.setMoves(new NatStringTreeMap<Boolean>());
            _fight.setEvolutions(new TreeMap<String,Boolean>(new NaturalComparator()));
            _fight.setAbilities(new StringList());
            _fight.setAbility(DataBase.EMPTY_STRING);
            _fight.setChosenIndex(CustList.INDEX_NOT_FOUND_ELT);
            return;
        }
        if(_fight.getChoices().contains(key_)) {
            _fight.setMoves(getMoves(_fight, _key, DataBase.EMPTY_STRING));
            _fight.setEvolutions(getEvolutions(_fight, _key, _d));
            _fight.setAbilities(getAbilities(_fight, _key, DataBase.EMPTY_STRING));
            _fight.setAbility(_fight.getAbilities().first());
            _fight.getEvolutions().put(DataBase.EMPTY_STRING, false);
            String name_ = _fight.getChoices().getVal(key_).getName();
            _fight.getEvolutions().put(name_, true);
            _fight.setAbilities(getAbilities(_fight, _key, name_));
            _fight.setAbility(_fight.getChoices().getVal(key_).getAbility());
            NatStringTreeMap<Boolean> tree_ = getMoves(_fight, _key, name_);
            for (String m : tree_.getKeys()) {
                tree_.put(m, false);
            }
            for (String m : _fight.getChoices().getVal(key_).getKeptMoves()) {
                tree_.put(m, true);
            }
            _fight.setMoves(tree_);
        } else {
            _fight.setChosenIndex(CustList.INDEX_NOT_FOUND_ELT);
            _fight.setMoves(new NatStringTreeMap<Boolean>());
            _fight.setEvolutions(new TreeMap<String, Boolean>(new NaturalComparator()));
            _fight.setAbilities(new StringList());
            _fight.setAbility(DataBase.EMPTY_STRING);
        }
    }

    static NatStringTreeMap<Boolean> getMoves(Fight _fight, byte _key,String _evo) {
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(key_);
        NatStringTreeMap<Boolean> map_ = new NatStringTreeMap<Boolean>();
        map_.putAllTreeMap(fighter_.getMoves(_evo));
        return map_;
    }

    static TreeMap<String,Boolean> getEvolutions(Fight _fight, byte _key, DataBase _d) {
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(key_);
        String lg_ = _d.getLanguage();
        StringMap<String> m_ = _d.getTranslatedPokemonCurLanguage(lg_);
        TreeMap<String,Boolean> map_;
        map_ = new TreeMap<String, Boolean>(new ComparatorTrStrings(m_));
        map_.put(DataBase.EMPTY_STRING, true);
        for (String e: fighter_.getMovesAbilitiesEvos().getKeys()) {
            map_.put(e, false);
        }
        return map_;
    }

    static StringList getAbilities(Fight _fight, byte _key,String _evo) {
        byte key_ = _fight.getUserTeam().fighterAtIndex(_key);
        Fighter fighter_ = _fight.getUserTeam().getMembers().getVal(key_);
        return fighter_.getAbilities(_evo);
    }

    static NumberMap<Byte,ChoiceOfEvolutionAndMoves> defaultChoices(Fight _fight) {
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = new NumberMap<Byte,ChoiceOfEvolutionAndMoves>();
        for (byte k: _fight.getUserTeam().getMembers().getKeys()) {
            Fighter fighter_ = _fight.getUserTeam().refPartMembres(k);
            if (!fighter_.isBelongingToPlayer()) {
                continue;
            }
            if (fighter_.getMovesAbilitiesEvos().isEmpty()) {
                if (fighter_.getMovesToBeLearnt().isEmpty()) {
                    continue;
                }
            }
            ChoiceOfEvolutionAndMoves defaultChoice_ = new ChoiceOfEvolutionAndMoves();
            StringList keptMoves_ = new StringList();
            NatStringTreeMap< Boolean> map_ = fighter_.getMoves(DataBase.EMPTY_STRING);
            for (String m: map_.getKeys()) {
                if (!map_.getVal(m)) {
                    continue;
                }
                keptMoves_.add(m);
            }
            keptMoves_.sort();
            defaultChoice_.setKeptMoves(keptMoves_);
            choices_.put(k, defaultChoice_);
        }
        return choices_;
    }

    public static void addOrForgetMove(Fight _fight, String _move) {
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(_fight.getChosenIndex());
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        if (choice_.getKeptMoves().containsObj(_move)) {
            choice_.getKeptMoves().removeString(_move);
            _fight.getMoves().put(_move, false);
        } else {
            choice_.getKeptMoves().add(_move);
            _fight.getMoves().put(_move, true);
        }
    }

    public static void setAbility(Fight _fight, String _ability) {
        byte index_ = _fight.getChosenIndex();
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(index_);
        if (!choices_.contains(key_)) {
            return;
        }
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        choice_.setAbility(_ability);
        _fight.setAbility(_ability);
    }

    public static void setEvolution(Fight _fight, String _evo) {
        byte index_ = _fight.getChosenIndex();
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
        byte key_ = _fight.getUserTeam().fighterAtIndex(index_);
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal(key_);
        choice_.getKeptMoves().clear();
        String backEvo_ = choice_.getName();
        //set _fight.getEvolutions() map for displaying
        _fight.getEvolutions().put(backEvo_, false);
        _fight.getEvolutions().put(_evo, true);
        choice_.setName(_evo);
        StringList abilities_ = getAbilities(_fight, index_, _evo);
        _fight.setAbilities(abilities_);
        _fight.setAbility(abilities_.first());
        choice_.setAbility(abilities_.first());
        NatStringTreeMap<Boolean> moves_ = getMoves(_fight, index_, _evo);
        _fight.setMoves(moves_);
        StringList movesList_ = new StringList();
        for (String m: moves_.getKeys()) {
            if (!moves_.getVal(m)) {
                continue;
            }
            movesList_.add(m);
        }
        choice_.getKeptMoves().addAllElts(movesList_);
    }

    public static boolean possibleChoices(Fight _fight,DataBase _import) {
        _fight.clearComments();
        boolean valid_ = true;
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
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
                _fight.addMessage(_import,Fight.ERR_EVOLVING, name_,Integer.toString(min_), Integer.toString(max_), Integer.toString(nbChosen_));
            }
            if (choice_.getName().isEmpty()) {
                continue;
            }
            if (fighter_.getMovesAbilitiesEvos().getVal(choice_.getName()).getAbilities().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                if (choice_.getAbility().isEmpty()) {
                    valid_ = false;
                    _fight.addMessage(_import,Fight.ERR_EVOLVING_AB, name_);
                }
            }
        }
        _fight.setError(!valid_);
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
            fighter_.fullHeal(_import);
            fighter_.setGroundPlace(fighter_.getGroundPlaceSubst());
        }
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, false)) {
            Fighter fighter_ = _fight.getFighter(f);
//            if (!fighter_.estKo()) {
//                continue;
//            }
//            if (Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
//                continue;
//            }
            fighter_.fullHeal(_import);
            fighter_.setGroundPlace(fighter_.getGroundPlaceSubst());
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
        if(!_fight.getFightType().isWild()){
            FightArtificialIntelligence.choiceArtificialIntelligence(_fight,_diff,_import);
        }
    }

    public static EqList<TeamPosition> fightersBelongingToUser(Fight _fight,boolean _user) {
        EqList<TeamPosition> list_ = new EqList<TeamPosition>();
        NumberMap<Byte,Fighter> map_ = _fight.getUserTeam().getMembers();
        for(byte c:map_.getKeys()){
            if (ComparatorBoolean.diff(map_.getVal(c).isBelongingToPlayer(), _user)) {
                continue;
            }
            list_.add(Fight.toUserFighter(c));
        }
        return list_;
    }

    static void frontFighterChoiceFleeingCatching(Fight _fight){
        Team equipe_=_fight.getUserTeam();
//        CustList<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlace((short) CustList.FIRST_INDEX);
        Numbers<Byte> cbts_=_fight.getUserTeam().fightersAtCurrentPlaceIndex(CustList.FIRST_INDEX, true);
        Fighter creatureLanceur_=equipe_.refPartMembres(cbts_.first());
        creatureLanceur_.cancelActions();
    }

    public static ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>>
            remainingThrowersTargetsHp(Fight _fight, Difficulty _diff, DataBase _import) {
        ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>> map_;
        map_ = new ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>>();
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, true)) {
            StringList moves_;
            moves_ = allowedMovesNotEmpty(_fight, f, _import);
            StringMap<ObjectMap<TeamPosition,Rate>> mapMovesTargets_;
            mapMovesTargets_ = new StringMap<ObjectMap<TeamPosition,Rate>>();
            for (String m: moves_) {
                if (!(_import.getMove(m) instanceof DamagingMoveData)) {
                    continue;
                }
                ObjectMap<TeamPosition,Rate> fighters_;
                fighters_ = new ObjectMap<TeamPosition,Rate>();
                ObjectMap<TargetCoords,Rate> mapTargets_;
                mapTargets_ = FightArtificialIntelligence.remainingFoeTargetHp(_fight, f, m, _diff, _import);
                for (TargetCoords t: mapTargets_.getKeys()) {
                    Team team_ = _fight.getTeams().getVal((byte) t.getTeam());
                    for (byte f2_: team_.fightersAtCurrentPlace(t.getPosition())) {
                        fighters_.put(new TeamPosition((byte) t.getTeam(), f2_), mapTargets_.getVal(t));
                    }
                }
                ObjectMap<TeamPosition,Rate> mapFighters_;
                mapFighters_ = FightArtificialIntelligence.remainingPartnerTargetHp(_fight, f, m, _diff, _import);
                fighters_.putAllMap(mapFighters_);
                mapMovesTargets_.put(m, fighters_);
            }
            map_.put(f, mapMovesTargets_);
        }
        return map_;
    }

    public static NatStringTreeMap<EqList<TeamPosition>> sortedFightersBeginRoundWildFight(Fight _fight, DataBase _data) {
        NatStringTreeMap<EqList<TeamPosition>> tree_;
        tree_ = new NatStringTreeMap<EqList<TeamPosition>>();
        StringList moves_ = allowedMovesNotEmpty(_fight, Fight.toFoeFighter((byte) 0), _data);
        for (String m: moves_) {
            Fighter wildPk_ = _fight.wildPokemon();
            MoveData move_ = _data.getMove(m);
            if(move_.getTargetChoice().isWithChoice()){
                EqList<TeamPosition> cibles_= FightOrder.closestFoeFighter(_fight, Fight.toFoeFighter((byte) 0));
                Fighter cible_ = _fight.getFighter(cibles_.first());
                wildPk_.setFirstChosenMoveTarget(m,TargetCoords.toUserTarget(cible_.getGroundPlace()));
            }else{
                wildPk_.setFirstChosenMove(m);
            }
            EqList<TeamPosition> fightersUsingMove_;
            fightersUsingMove_ = FightOrder.fightersUsingMove(_fight, FightOrder.fighters(_fight));
            _fight.getOrderedFighters().clear();
            _fight.getOrderedFighters().addAllElts(fightersUsingMove_);
            for (TeamPosition f: fightersUsingMove_) {
                _fight.getFighter(f).choisirAttaqueFin();
            }
            FightOrder.sortFightersUsingMoveAmongList(_fight, _data);
            tree_.put(_data.translateMove(m), new EqList<TeamPosition>(_fight.getOrderedFighters()));
            for (TeamPosition f: fightersUsingMove_) {
                ((ActionMove) _fight.getFighter(f).getAction()).setFinalChosenMove(DataBase.EMPTY_STRING);
            }
            wildPk_.cancelActions();
        }
        return tree_;
    }

    public static EqList<TeamPosition> sortedFightersBeginRound(Fight _fight, DataBase _data) {
        if (!_fight.getAllyChoiceSet().isEmpty()) {
            FightRound.setAllyChoices(_fight, _data);
        }
        EqList<TeamPosition> fightersUsingMove_;
        fightersUsingMove_ = FightOrder.fightersUsingMove(_fight, FightOrder.fighters(_fight));
        _fight.getOrderedFighters().clear();
        _fight.getOrderedFighters().addAllElts(fightersUsingMove_);
        for (TeamPosition f: fightersUsingMove_) {
            _fight.getFighter(f).choisirAttaqueFin();
        }
        FightOrder.sortFightersUsingMoveAmongList(_fight, _data);
        for (TeamPosition f: fightersUsingMove_) {
            ((ActionMove) _fight.getFighter(f).getAction()).setFinalChosenMove(DataBase.EMPTY_STRING);
        }
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, false)) {
            Fighter fighter_ = _fight.getFighter(f);
            fighter_.cancelActions();
        }
        return _fight.getOrderedFighters();
    }

    public static TreeMap<TeamPosition,ActionMove>
            sortedFightersUsingMoveDependingOnPlayerChoices(
                    Fight _fight, DataBase _data) {
        if (!_fight.getAllyChoiceSet().isEmpty()) {
            FightRound.setAllyChoices(_fight, _data);
        }
        EqList<TeamPosition> fightersUsingMove_;
        fightersUsingMove_ = FightOrder.fightersUsingMove(_fight, FightOrder.fighters(_fight));
        _fight.getOrderedFighters().clear();
        _fight.getOrderedFighters().addAllElts(fightersUsingMove_);
        for (TeamPosition f: fightersUsingMove_) {
            _fight.getFighter(f).choisirAttaqueFin();
        }
        FightOrder.sortFightersUsingMoveAmongList(_fight, _data);
        TreeMap<TeamPosition,ActionMove> tree_;
        tree_ = new TreeMap<TeamPosition, ActionMove>(new SortedFighterActsComparator(_fight));
        for (TeamPosition f: _fight.getOrderedFighters()) {
            tree_.put(f, (ActionMove) _fight.getFighter(f).getAction());
        }
        for (TeamPosition f: fightersUsingMove_) {
            ((ActionMove) _fight.getFighter(f).getAction()).setFinalChosenMove(DataBase.EMPTY_STRING);
        }
        for (TeamPosition f: FightOrder.fightersBelongingToUser(_fight, false)) {
            Fighter fighter_ = _fight.getFighter(f);
            fighter_.cancelActions();
        }
        return tree_;
    }

    public static void setFirstChosenMoveFoeTarget(Fight _fight,byte _foeTarget){
        Team equipe_=_fight.getUserTeam();
        byte index_ = _fight.getChosenIndexFront();
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
        Fighter creature_=equipe_.refPartMembres(list_.first());
        creature_.setFirstChosenMoveTarget(_fight.getChosenMoveFront(),TargetCoords.toFoeTarget(_foeTarget));
    }

    public static void setFirstChosenMovePlayerTarget(Fight _fight,byte _playerTarget){
        Team equipe_=_fight.getUserTeam();
        byte index_ = _fight.getChosenIndexFront();
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
        Fighter creature_=equipe_.refPartMembres(list_.first());
        creature_.setFirstChosenMoveTarget(_fight.getChosenMoveFront(),TargetCoords.toUserTarget(_playerTarget));
    }

    static void setFirstChosenMove(Fight _fight,byte _pos,String _attaque){
        Team equipe_=_fight.getUserTeam();
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(_pos);
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(_pos, true);
        if (list_.isEmpty()) {
            return;
        }
        Fighter creature_=equipe_.refPartMembres(list_.first());
        creature_.setFirstChosenMove(_attaque);
    }

    public static void setSubstituteEndRound(Fight _fight,byte _newPlace) {
        byte sub_ = _fight.getChosenIndexFront();
        if (!Numbers.eq(sub_, Fighter.BACK)) {
            setSubstituteFront(_fight, _newPlace);
        } else {
            setSubstituteBack(_fight, _newPlace);
        }
        _fight.setChosenIndexFront(Fighter.BACK);
        _fight.setChosenIndexBack(Fighter.BACK);
        _fight.setChosenSubstitute(_newPlace);
    }

    static void setSubstituteFront(Fight _fight,byte _newPlace){
        byte sub_ = _fight.getChosenIndexFront();
        Team equipe_=_fight.getUserTeam();
//        CustList<Byte> list_ = new CustList<>();
        //MOD
        byte substitute_ = Fighter.BACK;
        Numbers<Byte> listAll_ = new Numbers<Byte>(equipe_.getMembers().getKeys());
        listAll_.sort();
        int i_ = CustList.FIRST_INDEX;
        for (byte b: listAll_) {
            Fighter fighter_ = equipe_.getMembers().getVal(b);
            if (Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            if (i_ == sub_) {
                substitute_ = b;
                break;
            }
            i_++;
        }
        if (substitute_ == Fighter.BACK) {
            return;
        }
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
        _fight.getFirstPositPlayerFighters().put(substitute_, _newPlace);
    }

    static void setSubstituteBack(Fight _fight,byte _newPlace){
        byte sub_ = _fight.getChosenIndexBack();
        Team equipe_=_fight.getUserTeam();
        byte substitute_ = Fighter.BACK;
        byte i_ = CustList.FIRST_INDEX;
        Numbers<Byte> list_ = new Numbers<Byte>(equipe_.getMembers().getKeys());
//        list_.sort(new NaturalComparator<Byte>() {
//            @Override
//            public int compare(Byte _o1, Byte _o2) {
//                return _o1.compareTo(_o2);
//            }
//        });
        list_.sort();
        for (byte b: list_) {
            Fighter fighter_ = equipe_.getMembers().getVal(b);
            if (!Numbers.eq(fighter_.getGroundPlaceSubst(), Fighter.BACK)) {
                continue;
            }
            if (i_ == sub_) {
                substitute_ = b;
                break;
            }
            i_++;
        }
        if (substitute_ == Fighter.BACK) {
            return;
        }
        _fight.getFirstPositPlayerFighters().put(substitute_, _newPlace);
    }

    static void setSubstituteSwitch(Fight _fight,byte _remplacant){
        //en:_fight.getSelectedActionCurFighter() is ActionType.SWITCH
        //fr:_fight.getSelectedActionCurFighter() vaut ActionType.SWITCH
        byte index_ = _fight.getChosenIndexFront();
        Team equipe_=_fight.getUserTeam();
//        CustList<Byte> list_ = equipe_.fightersAtCurrentPlace(index_);
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
        if (list_.isEmpty()) {
            return;
        }
        Fighter creature_=equipe_.refPartMembres(list_.first());
        byte substitute_ = equipe_.substituteAtIndex(_remplacant);
        if (substitute_ == Fighter.BACK) {
            return;
        }
        creature_.setSubstitute(substitute_);
    }

    static void chooseBackFighterWhileRound(Fight _fight,byte _substitute, DataBase _data) {
        Team team_ = _fight.getUserTeam();
        _fight.setError(false);
        byte substitute_ = team_.substituteAtIndex(_substitute);
        if (Numbers.eq(substitute_, Fighter.BACK)) {
            cancelChooseBackFighterWhileRound(_fight);
            return;
        }
        Fighter fighter_ = team_.getMembers().getVal(substitute_);
        if (fighter_.estKo()) {
            String name_ = _data.translatePokemon(fighter_.getName());
            _fight.addMessage(_data,Fight.ERR_KO_SUBSTITUTE, name_);
            _fight.setError(true);
            return;
        }
        fighter_ = _fight.getFighter(_fight.getCurrentUser());
        byte index_ = fighter_.getGroundPlace();
        setSubstituteForMove(_fight, index_, _substitute);
    }

    static void chooseBackFighterAddon(Fight _fight,byte _substitute, DataBase _data) {
        Team team_ = _fight.getUserTeam();
        _fight.setError(false);
        byte substitute_ = team_.substituteAtIndex(_substitute);
        byte index_ = _fight.getChosenIndexFront();
        Team equipe_=_fight.getUserTeam();
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
        Fighter creature_=equipe_.refPartMembres(list_.first());
        if (Numbers.eq(substitute_, Fighter.BACK)) {
            creature_.setSubstituteForMove(Fighter.BACK);
            return;
        }
        Fighter fighter_ = team_.getMembers().getVal(substitute_);
        if (fighter_.estKo()) {
            String name_ = _data.translatePokemon(fighter_.getName());
            _fight.addMessage(_data,Fight.ERR_KO_SUBSTITUTE, name_);
            _fight.setError(true);
            return;
        }
        //the window does not give the possibility to choose an ally pokemon
        if (!fighter_.isBelongingToPlayer()) {
            _fight.setError(true);
            return;
        }
        setSubstituteForMove(_fight, index_, _substitute);
    }

    static void setSubstituteForMove(Fight _fight,byte _index, byte _substitute) {
        Team team_=_fight.getUserTeam();
        byte substitute_ = team_.substituteAtIndex(_substitute);
//        CustList<Byte> list_ = team_.fightersAtCurrentPlace(_index);
        Numbers<Byte> list_ = team_.fightersAtCurrentPlaceIndex(_index, true);
        Fighter creature_=team_.refPartMembres(list_.first());
        creature_.setSubstituteForMove(substitute_);
    }

    public static void cancelChooseBackFighterWhileRound(Fight _fight) {
        Fighter fighter_ = _fight.getFighter(_fight.getCurrentUser());
        fighter_.setSubstituteForMove(Fighter.BACK);
    }

    public static void setChosenHealingItem(Fight _fight, String _item,DataBase _import) {
        byte index_ = _fight.getChosenIndexFront();
        if (!Numbers.eq(index_, Fighter.BACK)) {
            setChosenHealingItemFront(_fight, _item, _import);
        } else {
            setChosenHealingItemBack(_fight, _item, _import);
        }
    }

    static void setChosenHealingItemFront(Fight _fight,String _objet,DataBase _import){
        byte index_ = _fight.getChosenIndexFront();
        Team equipe_=_fight.getUserTeam();
        Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
        if (list_.isEmpty()) {
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
        _fight.getCurrentFighterMoves().clear();
        if (chooseMove_) {
            _fight.setChosenHealingMove(_objet);
            _fight.setCurrentFighterMoves(frontFighterMoves(_fight, index_, _import));
            return;
        }
        Fighter creature_=equipe_.refPartMembres(list_.first());
        creature_.setChosenHealingObject(_objet,_import);
    }

    static void setChosenHealingItemBack(Fight _fight,String _objet,DataBase _import){
        byte index_ = _fight.getChosenIndexBack();
        Team equipe_=_fight.getUserTeam();
        byte substitute_ = equipe_.substituteAtIndex(index_);
        if (substitute_ == Fighter.BACK) {
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
        _fight.getCurrentFighterMoves().clear();
        if (chooseMove_) {
            _fight.setChosenHealingMove(_objet);
            _fight.setCurrentFighterMoves(backFighterMoves(_fight, index_, _import));
            return;
        }
        Fighter creature_=equipe_.refPartMembres(substitute_);
        creature_.setChosenHealingObject(_objet,_import);
    }

    static void setChosenHealingItemMove(Fight _fight, String _move) {
        byte index_ = _fight.getChosenIndexFront();
        if (!Numbers.eq(index_, Fighter.BACK)) {
            Team equipe_=_fight.getUserTeam();
            Numbers<Byte> list_ = equipe_.fightersAtCurrentPlaceIndex(index_, true);
            Fighter creature_=equipe_.refPartMembres(list_.first());
            creature_.setChosenHealingObjectMove(_fight.getChosenHealingMove(),_move);
        } else {
            index_ = _fight.getChosenIndexBack();
            Team equipe_=_fight.getUserTeam();
            byte substitute_ = equipe_.substituteAtIndex(index_);
            Fighter creature_=equipe_.refPartMembres(substitute_);
            creature_.setChosenHealingObjectMove(_fight.getChosenHealingMove(),_move);
        }
    }

    public static void simulate(Fight _fight,
            CustList<CustList<ActionMove>> _actionsRound,
            CustList<CustList<ActionSwitch>> _actionsSubstitutingFront,
            CustList<CustList<ActionSwitch>> _actionsSubstitutingBack,
            CustList<NumberMap<Byte,ChoiceOfEvolutionAndMoves>> _evolutions,
            Player _utilisateur,Difficulty _diff,DataBase _import){
        _fight.setSimulation(true);
        int round_ = CustList.FIRST_INDEX;
        while (true) {
            if (round_ >= _actionsRound.size()) {
                break;
            }
            if (stopSimulation(_fight)) {
                return;
            }
            CustList<ActionMove> actions_=_actionsRound.get(round_);
            NumberMap<Byte,ChoiceOfEvolutionAndMoves> evolutions_ = _evolutions.get(round_);
            int nbActions_=actions_.size();
            for(byte i2_=CustList.FIRST_INDEX;i2_<nbActions_;i2_++){
                ActionMove action_=actions_.get(i2_);
                chooseFrontFighter(_fight, i2_, _diff, _import);
                String move_ = action_.getFirstChosenMove();
                chooseMove(_fight, move_, _diff, _import);
                if (!_fight.getChosableFoeTargets().isEmpty()) {
                    //!_fight.getChosableFoeTargets().isEmpty() <==> !_fight.getChosablePlayerTargets().isEmpty()
                    if (Numbers.eq(action_.getChosenTargets().first().getTeam(), Fight.FOE)) {
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
            if(!FightRules.playable(_fight,_utilisateur,_diff,_import)){
                _fight.setIssue(IssueSimulation.RULES_MOVES);
                _fight.setAcceptableChoices(false);
                return;
            }
            while (true) {
                roundAllThrowersChooseActionsFoe(_fight,_diff,_utilisateur,_import);
                if(FightKo.endedFight(_fight,_diff)){
                    _fight.setAcceptableChoices(win(_fight));
                    return;
                }
                if (_fight.getState() != FightState.SWITCH_APRES_ATTAQUE) {
                    break;
                }
            }
//            do {
//                roundAllThrowersChooseActionsFoe(_fight,_diff,_utilisateur,_import);
//                if(FightKo.endedFight(_fight,_diff)){
//                    _fight.setAcceptableChoices(win(_fight));
//                    return;
//                }
//            } while (_fight.getState() == FightState.SWITCH_APRES_ATTAQUE);
            if(_fight.getState()==FightState.APPRENDRE_EVOLUER){
                NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = _fight.getChoices();
                for(byte c: _fight.getChoices().getKeys()){
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
                    if (!evolutions_.getVal(c).getName().isEmpty()) {
                        choice_.setName(evolutions_.getVal(c).getName());
                        choice_.setAbility(evolutions_.getVal(c).getAbility());
                    }
                    choice_.getKeptMoves().clear();
                    choice_.getKeptMoves().addAllElts(evolutions_.getVal(c).getKeptMoves());
//                    for (String m: evolutions_.getVal(c).getKeptMoves()) {
//                        choice_.getKeptMoves().add(m);
//                    }
                }
                if (!possibleChoices(_fight, _import)) {
                    _fight.setIssue(IssueSimulation.RULES_LEARN);
                    _fight.setAcceptableChoices(false);
                    return;
                }
                learnAndEvolveAttack(_fight, _diff, _import);
            }
            Team equipeUt_=_fight.getUserTeam();
            for(byte c:equipeUt_.getMembers().getKeys()){
                Fighter creatureLanceur_=equipeUt_.getMembers().getVal(c);
                if(!creatureLanceur_.estKo()){
                    continue;
                }
                _fight.setIssue(IssueSimulation.KO_PLAYER);
                _fight.setAcceptableChoices(false);
                return;
            }
            Team equipeAdv_=_fight.getFoeTeam();
            for(byte c:equipeAdv_.getMembers().getKeys()){
                Fighter creatureLanceur_=equipeAdv_.getMembers().getVal(c);
                if(creatureLanceur_.estKo()){
                    continue;
                }
                if(creatureLanceur_.estArriere()){
                    continue;
                }
                _fight.setIssue(IssueSimulation.NOT_KO_FOE);
                _fight.setAcceptableChoices(false);
                return;
            }
//            if(win(_fight)){
//                return;
//            }
            FightArtificialIntelligence.choiceForSubstituing(_fight, _import);
            _fight.setState(FightState.SWITCH_PROPOSE);
            boolean test_ = false;
            //_fight.getState() != FightState.ATTAQUES
            while (true) {
                if (!keepLoop(_fight, test_)) {
                    break;
                }
                CustList<ActionSwitch> backActions_ = _actionsSubstitutingBack.get(round_);
                CustList<ActionSwitch> frontActions_ = _actionsSubstitutingFront.get(round_);
                int nbActionsLen_;
                nbActionsLen_ = frontActions_.size();
                for (byte f = CustList.FIRST_INDEX; f < nbActionsLen_; f++) {
                    chooseFrontFighter(_fight, f, _diff, _import);
                    setSubstituteFront(_fight, frontActions_.get(f).getSubstitute());
                }
                nbActionsLen_ = backActions_.size();
                for (byte f = CustList.FIRST_INDEX; f < nbActionsLen_; f++) {
                    chooseBackFighter(_fight, f, _import);
                    setSubstituteBack(_fight, backActions_.get(f).getSubstitute());
                }
                if (!FightRules.substitutable(_fight, _diff, _import)) {
                    _fight.setIssue(IssueSimulation.RULES_SWITCH);
                    _fight.setAcceptableChoices(false);
                    return;
                }
                FightSending.sendSubstitutes(_fight,_diff, _utilisateur,_import);
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
            round_++;
        }
    }

    static boolean stopSimulation(Fight _fight) {
        return win(_fight) || !_fight.getAcceptableChoices();
    }

    static boolean keepLoop(Fight _fight, boolean _test) {
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
            _fight.setIssue(IssueSimulation.TOO_HARD_SIMULATION);
            _fight.setAcceptableChoices(false);
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
            _fight.setIssue(IssueSimulation.KO_PLAYER);
            _fight.setAcceptableChoices(false);
            exist_ = true;
        }
        return exist_;
    }
    public static void initializeFromSavedGame(Fight _fight, Difficulty _diff,Player _user,DataBase _data) {
        _fight.setAcceptableChoices(true);
        _fight.setIssue(IssueSimulation.NOTHING);
        if (!_fight.getFightType().isExisting()) {
            return;
        }
        _fight.setError(false);
        _fight.setFullHealing(false);
        _fight.setSuccessfulEffects(new ObjectMap<NbEffectFighterCoords,Boolean>());
        _fight.setDamageByCurrentUser(new ObjectMap<TeamPosition,Rate>());
        _fight.setSufferingTargetStatus(new StringList());
        _fight.setLettingUserAttackWithStatus(true);
        _fight.setEndRound(false);
        _fight.setSimulation(false);
        _fight.setEnabledMessages(true);
        _fight.setInvokedMove(false);
        _fight.setSending(false);
        _fight.setKeepStatus(false);
        _fight.setEnabledHealingPartner(false);
        _fight.setChangeThrower(false);
        _fight.setSuccessfulInvokation(false);
        _fight.setSuccessfulUse(false);
        _fight.setPutKo(false);
        _fight.setDamage(new DamageMoveCountUser());
        _fight.getDamage().setDamage(Rate.zero());
        _fight.getDamage().setDamageClone(Rate.zero());
        _fight.getDamage().setDamageCount(Rate.zero());
        _fight.setDamageKo(Rate.zero());
        _fight.setOrderedFighters(new EqList<TeamPosition>());
        _fight.setRemainingFighters(new EqList<TeamPosition>());
        _fight.setChosablePlayerTargets(new BooleanList());
        _fight.setChosableFoeTargets(new BooleanList());
        _fight.setChosenFoeTarget(Fighter.BACK);
        _fight.setChosenPlayerTarget(Fighter.BACK);
        _fight.setChosenIndexBack(Fighter.BACK);
        _fight.setChosenIndexFront(Fighter.BACK);
        _fight.setPossibleActionsCurFighter(new EnumList<ActionType>());
        _fight.setSelectedActionCurFighter(ActionType.NOTHING);
        _fight.setCurrentFighterMoves(new NatStringTreeMap<ChosenMoveInfos>());
        _fight.setChosenMoveFront(DataBase.EMPTY_STRING);
        _fight.setChosenHealingMove(DataBase.EMPTY_STRING);
        _fight.setChosenSubstitute(Fighter.BACK);
        _fight.setChosenIndex(Fighter.BACK);
        _fight.setMoves(new NatStringTreeMap<Boolean>());
        _fight.setEvolutions(new TreeMap<String,Boolean>(new NaturalComparator()));
        _fight.setAbilities(new StringList());
        _fight.setAbility(DataBase.EMPTY_STRING);
        _fight.setKeepRound(true);
        _fight.setEndRoundFightKoPlayer(true);
        _fight.setEffects(new CustList<AnimationInt>());
        _fight.setComment(new Comment());
        _fight.setCurrentActivity(new ActivityOfMove());
        _fight.setKos(new NumberMap<Byte,Boolean>());
        _fight.getKos().put(Fight.PLAYER,false);
        _fight.getKos().put(Fight.FOE,false);
        _fight.getFoeTeam().setComment(new Comment());
        _fight.getUserTeam().setComment(new Comment());
        for (Fighter f: _fight.getFoeTeam().getMembers().values()) {
            f.setVarHp(Rate.zero());
            f.setComment(new Comment());
            f.setIv(new EnumMap<Statistic,Short>());
            boolean isCaught_ = _user.estAttrape(f.getName());
            if (isCaught_) {
                f.initIvAdv(_diff, _data.getDefaultBall());
            } else {
                f.initIvAdv(_diff, DataBase.EMPTY_STRING);
            }
        }
        for (Fighter f: _fight.getUserTeam().getMembers().values()) {
            f.setVarHp(Rate.zero());
            f.setComment(new Comment());
            f.setIv(new EnumMap<Statistic,Short>());
            f.initIvUt(_diff);
            f.initHp();
        }
        if (_fight.getState() == FightState.ATTAQUES || _fight.getState() == FightState.SWITCH_APRES_ATTAQUE) {
            for (TeamPosition f: FightOrder.fighters(_fight)) {
                Fighter fighter_ = _fight.getFighter(f);
                AbstractAction action_ = fighter_.getAction();
                if (!(action_ instanceof ActionMove)) {
                    continue;
                }
                String move_ = ((ActionMove)action_).getFirstChosenMove();
                if (move_.isEmpty()) {
                    fighter_.cancelActions();
                    continue;
                }
                MoveData fAtt_=_data.getMove(move_);
                if (fAtt_.getTargetChoice().isWithChoice()) {
                    continue;
                }
                fighter_.getChosenTargets().clear();
            }
        }
    }

    //visible to the project only

    public static boolean canUseItsObject(Fight _fight, TeamPosition _cbt,DataBase _import){
        return FightItems.canUseItsObject(_fight,_cbt, _import);
    }

    public static Rate speed(Fight _fight, TeamPosition _cbt,DataBase _import){
        return FightOrder.speed(_fight, _cbt, _import);
    }

    public static StringList moveTypes(Fight _fight, TeamPosition _lanceur,String _attaque,DataBase _import){
        return FightMoves.moveTypes(_fight, _lanceur, _attaque, _import);
    }
}
