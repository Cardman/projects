package aiki.game.fight;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.pokemon.WildPk;

final class FightInitialization {

    private FightInitialization() {
    }

    static void initFight(Fight _fight, Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import) {
        initDefaultFight(_fight);
        initMultiplicity(_fight,_dresseur.getMultiplicityFight());
        initUserTeam(_fight,_utilisateur, _diff, _import);
        initEquipeLeaderGym(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import) {
        initDefaultFight(_fight);
        initMultiplicity(_fight,_dresseur.getMultiplicityFight());
        initUserTeam(_fight,_utilisateur, _diff, _import);
        initEquipeTrainerGym(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        initDefaultFight(_fight);
        initMultiplicity(_fight,_dresseur.getMultiplicityFight());
        initUserTeam(_fight,_utilisateur, _diff, _import);
        initEquipeDresseurHorsLigue(_fight,_utilisateur,_diff,_dresseur,_numero,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        initDefaultFight(_fight);
        initMultiplicity(_fight,_dresseur.getMultiplicityFight());
        initUserTeam(_fight,_utilisateur, _diff, _import);
        initEquipeDresseurLigue(_fight,_utilisateur,_diff,_dresseur,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _import){
        initDefaultFight(_fight);
        initMultiplicity(_fight,_dual.getFoeTrainer().getMultiplicityFight());
        _fight.setPlayerMaxNumberFrontFighters((byte) DataBase.ONE_POSSIBLE_CHOICE);
        initUserTeam(_fight,_utilisateur,_diff,_dual,_import);
        initEquipeTmpTrainer(_fight,_utilisateur,_diff,_dual,_import);
    }

    static void initFight(Fight _fight,Player _utilisateur,Difficulty _diff,WildPk _pokemon, DataBase _import){
        initDefaultFight(_fight);
        initMultiplicity(_fight, (byte) DataBase.ONE_POSSIBLE_CHOICE);
        initUserTeam(_fight,_utilisateur, _diff, _import);
        initWildPokemon(_fight,_utilisateur,_diff,_pokemon,_import);
    }

    static void initDefaultFight(Fight _fight) {
        _fight.setWinningMoney(Rate.zero());
        _fight.setSimulation(false);
        _fight.setAcceptableChoices(false);
        _fight.setFightType(FightType.NOTHING);
        _fight.setUsedItemsWhileRound(new StringMap<Short>());
        _fight.setChoices(new NumberMap<Byte,ChoiceOfEvolutionAndMoves>());
        _fight.setAllyChoice(new ObjectMap<MoveTarget,MoveTarget>());
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
        _fight.setFirstPositPlayerFighters(new NumberMap<Byte,Byte>());
        for(TeamPosition c: FightFacade.fightersBelongingToUser(_fight,true)){
            byte pos_ = c.getPosition();
            _fight.getFirstPositPlayerFighters().put(pos_,userTeam_.getMembers().getVal(pos_).getGroundPlaceSubst());
        }
        for(TeamPosition c: FightFacade.fightersBelongingToUser(_fight,false)){
            byte pos_ = c.getPosition();
            _fight.getFirstPositPlayerFighters().put(pos_,userTeam_.getMembers().getVal(pos_).getGroundPlaceSubst());
        }
    }

    static void initPositionsForFoeTeam(Fight _fight) {
        Team userTeam_=_fight.getFoeTeam();
        _fight.setFirstPositFoeFighters(new NumberMap<Byte,Byte>());
        for (byte k: userTeam_.getMembers().getKeys()) {
            Fighter f_ = userTeam_.getMembers().getVal(k);
            _fight.getFirstPositFoeFighters().put(k, f_.getGroundPlaceSubst());
        }
    }

    static void initGlobalMoves(Fight _fight,DataBase _import) {
        _fight.setStillEnabledMoves(new StringMap<Boolean>());
        _fight.setEnabledMoves(new StringMap<ActivityOfMove>());
        //lanceursGlobaux = new Map<>();
        for(String e:_import.getMovesEffectGlobal()){
            _fight.getEnabledMoves().put(e,new ActivityOfMove());
            if(_import.getMovesEffectGlobalWeather().containsObj(e)){
                _fight.getStillEnabledMoves().put(e,false);
//                lanceursGlobaux.put(e,new TeamPosition());
            }
        }
    }

    static void initRelationships(Fight _fight,DataBase _import) {
        EqList<TeamPosition> cbts_= FightOrder.fighters(_fight);
        for(Team t:_fight.getTeams().values()){
            t.initRelationsCombattant(cbts_,_import);
        }
    }

    static void initMultiplicity(Fight _fight,byte _multiplicity) {
        _fight.setTeams(new NumberMap<Byte,Team>());
        _fight.setSimulation(false);
        _fight.setAcceptableChoices(true);
        _fight.setIssue(IssueSimulation.NOTHING);
        _fight.setLostObjects(new StringList());
        _fight.setCurrentUser(new TeamPosition((byte) 0, Fighter.BACK));
        _fight.setLettingUserAttackWithStatus(true);
        _fight.setBeginRound(true);
        _fight.setFullHealing(false);
        _fight.setEndRound(false);
        _fight.setState(FightState.ATTAQUES);
        _fight.setKos(new NumberMap<Byte,Boolean>());
        _fight.getKos().put(Fight.PLAYER,false);
        _fight.getKos().put(Fight.FOE,false);
        _fight.setNbFleeAttempt((short) 0);
        _fight.setNbRounds(LgInt.zero());
        _fight.setCatchingBall(DataBase.EMPTY_STRING);
        _fight.setMult(_multiplicity);
        _fight.setPlayerMaxNumberFrontFighters(_multiplicity);
    }

    static void initUserTeam(Fight _fight,Player _utilisateur,Difficulty _diff,DataBase _import) {
        Team equipe_=new Team(_import);
        equipe_.initEquipeUtilisateur(_utilisateur,_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.PLAYER,equipe_);
    }

    static void initUserTeam(Fight _fight,Player _utilisateur, Difficulty _diff, DualFight _dual,DataBase _d) {
        Team equipe_=new Team(_d);
        equipe_.initEquipeUtilisateur(_utilisateur,_diff,_fight.getPlayerMaxNumberFrontFighters(), _fight.getMult(), _d,_dual.getAlly().getTeam());
        _fight.getTeams().put(Fight.PLAYER,equipe_);
    }

    static void initEquipeDresseurHorsLigue(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerMultiFights _dresseur,int _numero,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR);
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur,_dresseur.getTeamsRewards().get(_numero).getTeam(),_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

    static void initEquipeTrainerGym(Fight _fight,Player _utilisateur,Difficulty _diff,GymTrainer _dresseur,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR_GYM);
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur,_dresseur.getTeam(),_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

    static void initEquipeLeaderGym(Fight _fight,Player _utilisateur,Difficulty _diff,GymLeader _dresseur,DataBase _import){
        _fight.setFightType(FightType.GYM_LEADER);
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur,_dresseur.getTeam(),_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

    static void initEquipeDresseurLigue(Fight _fight,Player _utilisateur,Difficulty _diff,TrainerLeague _dresseur,DataBase _import){
        _fight.setFightType(FightType.DRESSEUR_LIGUE);
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur,_dresseur.getTeam(),_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

    static void initEquipeTmpTrainer(Fight _fight,Player _utilisateur,Difficulty _diff,DualFight _dresseur,DataBase _import){
        _fight.setFightType(FightType.TMP_TRAINER);
        Team equipe_=new Team(_import);
        equipe_.initEquipeAdversaire(_utilisateur,_dresseur.getFoeTrainer().getTeam(),_diff,_fight.getMult(),_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

    static void initWildPokemon(Fight _fight,Player _utilisateur,Difficulty _diff,WildPk _pokemon,DataBase _import) {
        _fight.setFightType(FightType.SAUVAGE);
        Team equipe_=new Team(_import);
        equipe_.initPokemonSauvage(_utilisateur,_diff, CustList.FIRST_INDEX, _pokemon,_import);
        _fight.getTeams().put(Fight.FOE,equipe_);
    }

}
