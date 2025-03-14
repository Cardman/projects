package aiki.game.fight;
import aiki.db.DataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.WildPk;
import aiki.util.MoveTargets;
import aiki.util.TeamPositionList;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

final class FightInitialization {

    private FightInitialization() {
    }

    static void initFight(Fight _fight, Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import) {
        initUserTeam(_fight, _utilisateur, _diff, _import, _dresseur.getMultiplicityFight());
        initEquipeLeaderGym(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import) {
        initUserTeam(_fight, _utilisateur, _diff, _import, _dresseur.getMultiplicityFight());
        initEquipeTrainerGym(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        initUserTeam(_fight, _utilisateur, _diff, _import, _dresseur.getMultiplicityFight());
        initEquipeDresseurHorsLigue(_fight,_utilisateur,_diff,_dresseur,_numero,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        initUserTeam(_fight, _utilisateur, _diff, _import, _dresseur.getMultiplicityFight());
        initEquipeDresseurLigue(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _import){
        initDefaultFight(_fight, _dual.getFoeTrainer().getMultiplicityFight(),DataBase.ONE_POSSIBLE_CHOICE);
        initUserTeam(_fight,_utilisateur,_diff,_dual,_import);
        initEquipeTmpTrainer(_fight,_utilisateur,_diff,_dual,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,WildPk _pokemon, DataBase _import){
        CustList<WildPk> one_ = new CustList<WildPk>();
        one_.add(_pokemon);
        initFight(_fight,_utilisateur,_diff,one_,_import);
    }

    static void initFight(Fight _fight, Player _utilisateur, Difficulty _diff, CustList<WildPk> _pokemon, DataBase _import){
        int mult_ = _pokemon.size();
        initUserTeam(_fight, _utilisateur, _diff, _import, mult_);
        _fight.setCurrentUserFlee(_fight.getUserTeam().fighterTeamAtIndex(IndexConstants.FIRST_INDEX).first().getFirstPosit());
        initWildPokemon(_fight,_utilisateur,_diff,_pokemon,_import);
    }

    static void initUserTeam(Fight _fight, Player _utilisateur, Difficulty _diff, DataBase _import, int _mult) {
        initDefaultFight(_fight, _mult, _mult);
        initUserTeam(_fight, _utilisateur, _diff, _import);
    }

    static void initDefaultFight(Fight _fight, int _mult, int _playerMaxNumberFrontFighters) {
        initDefaultFight(_fight);
        initMultiplicity(_fight, _mult);
        _fight.setPlayerMaxNumberFrontFighters(_playerMaxNumberFrontFighters);
    }

    static void initDefaultFight(Fight _fight) {
        _fight.setWinningMoney(Rate.zero());
        _fight.getTemp().setSimulation(false);
        _fight.getTemp().setAcceptableChoices(false);
        _fight.setFightType(FightType.NOTHING);
        _fight.setUsedItemsWhileRound(new StringMap<Long>());
        _fight.setChoices(new IntMap<ChoiceOfEvolutionAndMoves>());
        _fight.setAllyChoice(new MoveTargets());
        _fight.setCaughtEvolutions(new StringList());
        _fight.clearComments();
    }

    static void initFight(Fight _fight,DataBase _import){
        initPositionsForUserTeam(_fight);
        initPositionsForFoeTeam(_fight);
        initGlobalMoves(_fight,_import);
        initRelationships(_fight,_import);
    }

    static void initPositionsForUserTeam(Fight _fight) {
        Team userTeam_=_fight.getUserTeam();
        _fight.setFirstPositPlayerFighters(new IntMap<Integer>());
        for(TeamPosition c: FightFacade.fightersBelongingToUser(_fight,true)){
            int pos_ = c.getPosition();
            _fight.getFirstPositPlayerFighters().put(pos_,userTeam_.getMembers().getVal(pos_).getGroundPlaceSubst());
        }
        for(TeamPosition c: FightFacade.fightersBelongingToUser(_fight,false)){
            int pos_ = c.getPosition();
            _fight.getFirstPositPlayerFighters().put(pos_,userTeam_.getMembers().getVal(pos_).getGroundPlaceSubst());
        }
    }

    static void initPositionsForFoeTeam(Fight _fight) {
        Team userTeam_=_fight.getFoeTeam();
        _fight.setFirstPositFoeFighters(new IntMap<Integer>());
        for (int k: userTeam_.getMembers().getKeys()) {
            Fighter f_ = userTeam_.getMembers().getVal(k);
            _fight.getFirstPositFoeFighters().put(k, f_.getGroundPlaceSubst());
        }
    }

    static void initGlobalMoves(Fight _fight,DataBase _import) {
        _fight.setStillEnabledMoves(new StringMap<BoolVal>());
        _fight.setEnabledMoves(new StringMap<ActivityOfMove>());
        //lanceursGlobaux = new Map<>();
        for(String e:_import.getMovesEffectGlobal()){
            _fight.getEnabledMoves().put(e,new ActivityOfMove());
            if(StringUtil.contains(_import.getMovesEffectGlobalWeather(), e)){
                _fight.getStillEnabledMoves().put(e,BoolVal.FALSE);
//                lanceursGlobaux.put(e,new TeamPosition());
            }
        }
    }

    static void initRelationships(Fight _fight,DataBase _import) {
        TeamPositionList cbts_= FightOrder.fighters(_fight);
        for(Team t:_fight.getTeams().values()){
            t.initRelationsCombattant(cbts_,_import);
        }
    }

    static void initMultiplicity(Fight _fight,int _multiplicity) {
        _fight.setTeams(new IntMap<Team>());
        _fight.getTemp().setSimulation(false);
        _fight.getTemp().setAcceptableChoices(true);
        _fight.getTemp().setIssue(IssueSimulation.NOTHING);
        _fight.setLostObjects(new StringList());
        _fight.setCurrentUser(new TeamPosition(0, Fighter.BACK));
        _fight.setCurrentUserFlee(Fighter.BACK);
        _fight.getTemp().setLettingUserAttackWithStatus(true);
        _fight.getTemp().setFullHealing(false);
        _fight.getTemp().setEndRound(false);
        _fight.setState(FightState.ATTAQUES);
        _fight.getTemp().setKos(new IntMap<BoolVal>());
        _fight.getTemp().getKos().put(Fight.CST_PLAYER,BoolVal.FALSE);
        _fight.getTemp().getKos().put(Fight.CST_FOE,BoolVal.FALSE);
        _fight.setNbFleeAttempt(0);
        _fight.setNbRounds(LgInt.zero());
        _fight.setCatchingBalls(new CustList<CatchingBallFoeAction>());
        _fight.setMult(_multiplicity);
        _fight.setPlayerMaxNumberFrontFighters(_multiplicity);
    }

    static void initUserTeam(Fight _fight,Player _utilisateur,Difficulty _diff,DataBase _import) {
        initEquipeUtilisateur(_fight, _utilisateur, _diff, _import);
    }

    static void initEquipeUtilisateur(Fight _fight, Player _utilisateur, Difficulty _diff, DataBase _d) {
        initEquipeUtilisateur(_d, _utilisateur, _diff, _fight.getMult(), _fight.getMult(), new CustList<PkTrainer>(), _fight);
    }

    static void initEquipeUtilisateur(DataBase _d, Player _utilisateur, Difficulty _diff, int _playerMaxNumberFrontFighters, int _mult, CustList<PkTrainer> _team, Fight _fight) {
        Team equipe_=new Team(_d);
        equipe_.initEquipeUtilisateur(_utilisateur, _diff, _playerMaxNumberFrontFighters, _mult, _d, _team);
        _fight.getTeams().put(Fight.CST_PLAYER,equipe_);
    }

    static void initUserTeam(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _d) {
        initEquipeUtilisateur(_d, _utilisateur, _diff, _fight.getPlayerMaxNumberFrontFighters(), _fight.getMult(), _dual.getAlly().getTeam(), _fight);
    }

    static void initEquipeDresseurHorsLigue(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR);
        initEquipeAdversaire(_import, _utilisateur, _dresseur.getTeamsRewards().get(_numero).getTeam(), _diff, _fight);
    }

    static void initEquipeTrainerGym(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR_GYM);
        initEquipeAdversaire(_import, _utilisateur, _dresseur.getTeam(), _diff, _fight);
    }

    static void initEquipeLeaderGym(Fight _fight,Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import){
        _fight.setFightType(FightType.GYM_LEADER);
        initEquipeAdversaire(_import, _utilisateur, _dresseur.getTeam(), _diff, _fight);
    }

    static void initEquipeDresseurLigue(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR_LIGUE);
        initEquipeAdversaire(_import, _utilisateur, _dresseur.getTeam(), _diff, _fight);
    }

    static void initEquipeTmpTrainer(Fight _fight,Player _utilisateur,Difficulty _diff,DualFight _dresseur,DataBase _import){
        _fight.setFightType(FightType.TMP_TRAINER);
        initEquipeAdversaire(_import, _utilisateur, _dresseur.getFoeTrainer().getTeam(), _diff, _fight);
    }

    static void initEquipeAdversaire(DataBase _import, Player _utilisateur, CustList<PkTrainer> _dresseur, Difficulty _diff, Fight _fight) {
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur, _dresseur, _diff, _fight.getMult(), _import);
        _fight.getTeams().put(Fight.CST_FOE,equipe_);
    }

    static void initWildPokemon(Fight _fight, Player _utilisateur, Difficulty _diff, CustList<WildPk> _pokemon, DataBase _import) {
        _fight.setFightType(FightType.SAUVAGE);
        Team equipe_=new Team(_import);
        equipe_.initPokemonSauvage(_utilisateur,_diff, _pokemon,_import);
        _fight.getTeams().put(Fight.CST_FOE,equipe_);
        _fight.getCatchingBalls().clear();
        int s_ = equipe_.getMembers().size();
        for (int e = 0; e < s_; e++) {
            CatchingBallFoeAction c_ = new CatchingBallFoeAction();
            c_.setCatchingBall(DataBase.EMPTY_STRING);
            c_.setNickname(DataBase.EMPTY_STRING);
            c_.setPlayer(_fight.getCurrentUserFlee());
            _fight.getCatchingBalls().add(c_);
        }
    }

}
