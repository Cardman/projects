package aiki.game.fight;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.pokemon.PokemonData;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.util.TeamPositionList;
import code.maths.Rate;
import code.util.IdMap;
import code.util.*;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class FightKo {

    private FightKo() {
    }

    static void setKoMoveTeams(Fight _fight, TeamPosition _combattant,Difficulty _diff,DataBase _import){
        setKo(_fight,_combattant, _diff, _import);
        if(!endedFight(_fight,_diff) && !FightEndRound.existSubstitute(_fight)){
            moveTeams(_fight);
        }
    }

    /**not called in test except for the class*/
    static void setKo(Fight _fight, TeamPosition _combattant,Difficulty _diff,DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        if(_fight.getFighter(_combattant).isBelongingToPlayer()){
            equipe_.toutSupprimerCombattantsContreAdvMembre(_combattant.getPosition());
            _fight.getFirstPositPlayerFighters().put(_combattant.getPosition(),Fighter.BACK);
        }else if(NumberUtil.eq(_combattant.getTeam(),Fight.CST_FOE)){
            Team equipeUt_=_fight.getUserTeam();
            Ints liste_ = FightOrder.fightersBelongingToUserHavingBeaten(_fight,_combattant.getPosition());
            addExpEvsFighters(_fight,liste_,_combattant.getPosition(),_diff,_import);
            equipeUt_.toutSupprimerCombattantsContreAdv(_combattant.getPosition());
        }
        setFighterKo(_fight, _combattant, _import);
    }

    /**not called in test except for the class*/
    static void setFighterKo(Fight _fight, TeamPosition _combattant,DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        Fighter creature_=_fight.getFighter(_combattant);
        creature_.variationLeftHp(creature_.getRemainingHp().opposNb());
        _fight.addKoFighterMessage(_combattant, _import);
        creature_.exitFrontBattle();
        creature_.cancelActions();
        for(String c:creature_.getStatusSet()){
            creature_.supprimerStatut(c);
        }
        for(MoveTeamPosition c:creature_.getStatusRelatSet()){
            creature_.supprimerPseudoStatutCombattant(c.getTeamPosition(),c.getMove());
        }
        creature_.formeNormale(_import);
        creature_.initCreatureRelationsAutre(FightOrder.fighters(_fight), _import);
        FightSending.endRelations(_fight, _combattant, _import);
        updateKoStatus(_fight, _combattant.getTeam(), equipe_);
    }

    static void updateKoStatus(Fight _fight, int _combattant, Team _equipe) {
        if (_combattant == Fight.CST_FOE) {
            boolean ko_ = true;
            for (EntryCust<Integer, Fighter> e: _equipe.getMembers().entryList()) {
                if (!e.getValue().estKo() && FightOrder.notCaught(_fight,Fight.toFoeFighter(e.getKey()))) {
                    ko_ = false;
                    break;
                }
            }
            _fight.getTemp().getKos().put(_combattant,ComparatorBoolean.of(ko_));
            return;
        }
        _fight.getTemp().getKos().put(_combattant,ComparatorBoolean.of(_equipe.estKo()));
    }

    static boolean endedFight(Fight _fight, Difficulty _diff){
        if (_fight.getTemp().getEndRound() || _diff.getEndFightIfOneTeamKo()) {
            return FightFacade.koTeam(_fight);
        }
        return false;
    }

    static void addExpEvsFighters(Fight _fight,Ints _membres,int _adv,Difficulty _diff,DataBase _import){
        TeamPositionList fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(_fight,true);
        Ints porteursMultExp_ = FightOrder.fightersWearingExpObject(_fight,fightersBelongingToUser_, _import);
        Rate points_ = pointsFoe(_fight,_adv, _diff, _import);
        PointFoeExpObject pointFoeExpObject_ = new PointFoeExpObject(_membres,porteursMultExp_,points_,_adv);
        Rate sumMaxLevel_ = Rate.zero();
        long levelMax_ = _import.getMaxLevel();
        int nbMax_ = 0;
        for (TeamPosition c: fightersBelongingToUser_) {
            Fighter fighter_ = _fight.getFighter(c);
            if (fighter_.getLevel() != levelMax_) {
                continue;
            }
            nbMax_++;
            addExp(_fight,c, pointFoeExpObject_, _diff, false, _import);
            sumMaxLevel_.addNb(fighter_.getWonExp());
            fighter_.getWonExp().affectZero();
        }
        for(TeamPosition c: fightersBelongingToUser_){
            Fighter fighter_ = _fight.getFighter(c);
            if (fighter_.getLevel() == levelMax_) {
                continue;
            }
            if (nbMax_ != 0) {
                Rate v_ = Rate.divide(sumMaxLevel_, new Rate(nbMax_));
                fighter_.variationGainExperience(v_);
                _fight.addComment(fighter_.variationGainExperienceMessage(v_, _import));
            }
            addExp(_fight,c, pointFoeExpObject_, _diff, true, _import);
        }
        for(TeamPosition c: fightersBelongingToUser_){
            addEv(_fight,c, _membres, _adv, _import);
        }
    }

    static void addEv(Fight _fight,
            TeamPosition _fighter,
            Ints _membres,
                      int _adv, DataBase _import) {
        Fighter membre_=_fight.getFighter(_fighter);
        Fighter creatureAdv_=_fight.getFoeTeam().getMembers().getVal(_adv);
        PokemonData fPk_=creatureAdv_.fichePokemon(_import);
        if(_membres.containsObj(_fighter.getPosition())){
            Rate gainEv_=DataBase.defRateProduct();
            ItemForBattle objet_ = membre_.dataExpObject(_import);
            if (objet_ != null && !objet_.getMultWinningEv().isZero()) {
                gainEv_.multiplyBy(objet_.getMultWinningEv());
            }
            addEvStatistics(_fight,_fighter, gainEv_, fPk_.getEvs(), _import);
        }
        ItemForBattle objet_ = membre_.dataExpObject(_import);
        if(objet_ != null){
            Rate gainEv_=DataBase.defRateProduct();
            if(!objet_.getMultWinningEv().isZero()){
                gainEv_.multiplyBy(objet_.getMultWinningEv());
            }
            addEvStatistics(_fight,_fighter, gainEv_, objet_.getWinEvFight(), _import);
        }
    }

    static void addEvStatistics(Fight _fight,
            TeamPosition _fighter,
            Rate _rateEv,
            IdMap<Statistic,Long> _evs,
            DataBase _import) {
        Fighter membre_=_fight.getUserTeam().refPartMembres(_fighter.getPosition());
        for(Statistic c2_: _evs.getKeys()){
            Rate ev_=Rate.multiply(_rateEv,new Rate(_evs.getVal(c2_)));
            _fight.addComment(membre_.wonEvStatistic(c2_,ev_.ll(),_import.getMaxEv(), _import));
        }
    }

    static void addExp(Fight _fight, TeamPosition _fighter,
                       PointFoeExpObject _pointsFoeExp,
                       Difficulty _diff, boolean _showMessage, DataBase _import) {
        //If the fighter _fighter is KO then it cannot win exp even by MULTI_EXP
        // the KO fighter checks !_porteursMultExp.containsObj(_fighter)
        // _members == FightOrder.fightersBelongingToUserHavingBeaten(_fight,_foe.getPosition())
        // The ref fought foe of A KO fighter are deleted
        // ==> the field wonExp does not vary
        Team equipeUt_ = _fight.getUserTeam();
        Fighter membre_=equipeUt_.refPartMembres(_fighter.getPosition());
        String expItem_ = membre_.getExpItem();
        Rate gainBase_ = gainBase(_pointsFoeExp, _diff, _import, expItem_, _fight.getUserTeam().refPartMembres(_fighter.getPosition()).getLevel(), _fight.getFoeTeam().refPartMembres(_pointsFoeExp.getFoe().getPosition()).getLevel(), _fighter.getPosition());
        membre_.variationGainExperience(gainBase_);
        Comment c_ = membre_.variationGainExperienceMessage(gainBase_, _import);
        if (_showMessage) {
            _fight.addComment(c_);
        }
    }

    static Rate gainBase(PointFoeExpObject _pointsFoeExp, Difficulty _diff, DataBase _import, String _expItem, long _winner, long _looser, int _position) {
        Rate gainBase_=rateExp(_position, _pointsFoeExp.getMembers(), _pointsFoeExp.getPorteursMultExp());
        gainBase_.multiplyBy(_pointsFoeExp.getPoints());
        Rate rate_ = rateWonPoint(_diff, _import, _winner, _looser);
        gainBase_.multiplyBy(rate_);
        ItemForBattle objet_ = _import.usedObjectUsedForExp(_expItem);
        if (objet_ != null && !objet_.getMultWinningExp().isZero()) {
            gainBase_.multiplyBy(objet_.getMultWinningExp());
        }
        return gainBase_;
    }

    private static Rate rateExp(int _fighter,
                        Ints _members, Ints _porteursMultExp) {
        int nbPorteursMultExp_=_porteursMultExp.size();
        int presCbt_=0;
        if(_members.containsObj(_fighter)){
            presCbt_=1;
        }
        int portMultExp_=0;
        if(_porteursMultExp.containsObj(_fighter)){
            portMultExp_=1;
        }
        Rate a_;
        if(nbPorteursMultExp_>0){
            a_=new Rate(portMultExp_,2L*nbPorteursMultExp_);
        } else {
            a_=Rate.zero();
        }
        Rate b_;
        if(!_members.isEmpty()){
            if(nbPorteursMultExp_>0){
                b_=new Rate(presCbt_,_members.size()*2L);
            } else {
                b_=new Rate(presCbt_,_members.size());
            }
        } else {
            b_=Rate.zero();
        }
        return Rate.plus(a_,b_);
    }

    static Rate rateWonPoint(Difficulty _diff, DataBase _import, Fighter _fighterWinner, Fighter _fighterLooser) {
        return rateWonPoint(_diff, _import, _fighterWinner.getLevel(), _fighterLooser.getLevel());
    }

    static Rate rateWonPoint(Difficulty _diff, DataBase _import, long _winner, long _looser) {
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(_import.prefixLevelWinner(),Long.toString(_winner));
        vars_.put(_import.prefixLevelLooser(),Long.toString(_looser));
        String exp_ = _import.getRates().getVal(_diff.getDiffWinningExpPtsFight());
        return _import.evaluatePositiveExp(exp_, vars_, Rate.one());
    }

    static Rate pointsFoe(Fight _fight,int _adv, Difficulty _diff, DataBase _import) {
        Fighter creatureAdv_=_fight.getFoeTeam().getMembers().getVal(_adv);
        PokemonData fPk_=creatureAdv_.fichePokemon(_import);
        long niveauAdv_=creatureAdv_.getLevel();
        Rate points_=new Rate(fPk_.getExpRate()*niveauAdv_);
        if(!_fight.getFightType().isWild()){
            points_.multiplyBy(_diff.getWinTrainerExp());
            points_.multiplyBy(new Rate(_fight.getMult()));
        }
        points_.multiplyBy(_diff.getRateWinningExpPtsFight());
        return points_;
    }

    /**no effect if mult == 1*/
    static void moveTeams(Fight _fight){
        //Apres chaque ko
        int nbCombattantsAvantUt_=0;
        Team equipeUt_=_fight.getUserTeam();
        IntMap<Fighter> membresEquipeUt_=equipeUt_.getMembers();
        for(int c:membresEquipeUt_.getKeys()){
            Fighter membre_=membresEquipeUt_.getVal(c);
            if(!membre_.estArriere()){
                nbCombattantsAvantUt_++;
            }
        }
        int nbCombattantsAvantAdv_=0;
        Team equipeAdv_=_fight.getFoeTeam();
        IntMap<Fighter> membresEquipeAdv_=equipeAdv_.getMembers();
        for(int c:membresEquipeAdv_.getKeys()){
            Fighter membre_=membresEquipeAdv_.getVal(c);
            if(!membre_.estArriere()){
                nbCombattantsAvantAdv_++;
            }
        }
        equipeUt_.move(0);
        equipeAdv_.move(0);
        //preliminaire deplacer tous les combattants vers la gauche
        int a_;
        int b_;
        if(nbCombattantsAvantAdv_>nbCombattantsAvantUt_){
            a_ = nbCombattantsAvantAdv_;
            b_ = nbCombattantsAvantUt_;
        }else{
            a_ = nbCombattantsAvantUt_;
            b_ = nbCombattantsAvantAdv_;
        }
        int c_ = 0;
        while (a_ > 2 * c_ + b_) {
            c_++;
        }
        if(nbCombattantsAvantAdv_>nbCombattantsAvantUt_){
            equipeUt_.move(c_);
            //decaler cbt ut vers droite de c_ places
        }
        if(nbCombattantsAvantAdv_<nbCombattantsAvantUt_){
            equipeAdv_.move(c_);
            //decaler cbt adv vers droite de c_ places
        }
    }

    static boolean canBeHealed(Fight _fight,int _equipe,DataBase _import){
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_equipe));
        for(String c:equipeAdv_.enabledTeamMoves()){
            MoveData fAttAdv_=_import.getMove(c);
            int nbEffets_=fAttAdv_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttAdv_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEq_=(EffectTeam)effet_;
                if(effetEq_.getForbiddingHealing()){
                    return false;
                }
            }
        }
        return true;
    }

    static void putKoPlayer(Difficulty _diff, DataBase _data, Fight _fight) {
        for (TeamPosition f: FightOrder.fighters(_fight, Fight.CST_PLAYER)) {
            Fighter f_ = _fight.getFighter(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            setKoMoveTeams(_fight, f, _diff, _data);
        }
        _fight.setState(FightState.SWITCH_WHILE_KO_USER);
    }
}
