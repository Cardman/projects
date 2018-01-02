package aiki.game.fight;
import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.pokemon.PokemonData;
import aiki.game.params.Difficulty;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

final class FightKo {

    private FightKo() {
    }

    static void setKoMoveTeams(Fight _fight, TeamPosition _combattant,Difficulty _diff,DataBase _import){
        setKo(_fight,_combattant, _diff, _import);
        boolean existNotKoSubstitute_ = false;
        for (TeamPosition f: FightOrder.fighters(_fight)) {
            Fighter f_ = _fight.getFighter(f);
            if (!f_.estArriere()) {
                continue;
            }
            if (f_.estKo()) {
                continue;
            }
            existNotKoSubstitute_ = true;
            break;
        }
        if(!endedFight(_fight,_diff) && !existNotKoSubstitute_){
            moveTeams(_fight);
        }
    }

    /**not called in test except for the class*/
    static void setKo(Fight _fight, TeamPosition _combattant,Difficulty _diff,DataBase _import) {
        Team equipe_=_fight.getTeams().getVal(_combattant.getTeam());
        if(_fight.getFighter(_combattant).isBelongingToPlayer()){
            equipe_.toutSupprimerCombattantsContreAdvMembre(_combattant.getPosition());
            _fight.getFirstPositPlayerFighters().put(_combattant.getPosition(),Fighter.BACK);
        }else if(Numbers.eq(_combattant.getTeam(),Fight.FOE)){
            Team equipeUt_=_fight.getUserTeam();
            Numbers<Byte> liste_ = FightOrder.fightersBelongingToUserHavingBeaten(_fight,_combattant.getPosition());
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
        for(String c:creature_.getStatusSet()){
            creature_.supprimerStatut(c);
        }
        for(MoveTeamPosition c:creature_.getStatusRelatSet()){
            creature_.supprimerPseudoStatutCombattant(c.getTeamPosition(),c.getMove());
        }
        creature_.formeNormale(_import);
        creature_.initCreatureRelationsAutre(FightOrder.fighters(_fight), _import);
        FightSending.endRelations(_fight, _combattant, _import);
        _fight.getKos().put(_combattant.getTeam(),equipe_.estKo());
    }

    static boolean endedFight(Fight _fight, Difficulty _diff){
        if (_fight.getEndRound()) {
            return FightFacade.koTeam(_fight);
        }
        if (_diff.getEndFightIfOneTeamKo()) {
            return FightFacade.koTeam(_fight);
        }
        return false;
    }

    static void addExpEvsFighters(Fight _fight,Numbers<Byte> _membres,byte _adv,Difficulty _diff,DataBase _import){
        EqList<TeamPosition> fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(_fight,true);
        EqList<TeamPosition> porteursMultExp_ = FightOrder.fightersWearingExpObject(_fight,fightersBelongingToUser_, _import);
        Rate points_ = pointsFoe(_fight,_adv, _diff, _import);
        Rate sumMaxLevel_ = Rate.zero();
        short levelMax_ = (short) _import.getMaxLevel();
        short nbMax_ = 0;
        for (TeamPosition c: fightersBelongingToUser_) {
            Fighter fighter_ = _fight.getFighter(c);
            if (fighter_.getLevel() != levelMax_) {
                continue;
            }
            nbMax_++;
            addExp(_fight,c, _membres, porteursMultExp_, Fight.toFoeFighter(_adv), points_, _diff, false, _import);
            sumMaxLevel_.addNb(fighter_.getWonExp());
            fighter_.getWonExp().affectZero();
        }
        for(TeamPosition c: fightersBelongingToUser_){
            Fighter fighter_ = _fight.getFighter(c);
            if (fighter_.getLevel() == levelMax_) {
                continue;
            }
            if (nbMax_ != 0) {
                fighter_.variationGainExperience(Rate.divide(sumMaxLevel_, new Rate(nbMax_)), _import);
                _fight.addComment(fighter_.getComment());
            }
            addExp(_fight,c, _membres, porteursMultExp_, Fight.toFoeFighter(_adv), points_, _diff, true, _import);
        }
        for(TeamPosition c: fightersBelongingToUser_){
            addEv(_fight,c, _membres, _adv, _import);
        }
    }

    static void addEv(Fight _fight,
            TeamPosition _fighter,
            Numbers<Byte> _membres,
            byte _adv, DataBase _import) {
        Fighter membre_=_fight.getFighter(_fighter);
        Fighter creatureAdv_=_fight.getFoeTeam().getMembers().getVal(_adv);
        PokemonData fPk_=creatureAdv_.fichePokemon(_import);
        if(_membres.containsObj(_fighter.getPosition())){
            Rate gainEv_=DataBase.defRateProduct();
            if(membre_.hasExpObject()){
                ItemForBattle objet_=(ItemForBattle) membre_.dataExpObject(_import);
                if(!objet_.getMultWinningEv().isZero()){
                    gainEv_.multiplyBy(objet_.getMultWinningEv());
                }
            }
            addEvStatistics(_fight,_fighter, gainEv_, fPk_.getEvs(), _import);
        }
        if(membre_.hasExpObject()){
            ItemForBattle objet_=(ItemForBattle) membre_.dataExpObject(_import);
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
            EnumMap<Statistic,Short> _evs,
            DataBase _import) {
        Fighter membre_=_fight.getUserTeam().refPartMembres(_fighter.getPosition());
        for(Statistic c2_: _evs.getKeys()){
            Rate ev_=Rate.multiply(_rateEv,new Rate(_evs.getVal(c2_)));
            membre_.wonEvStatistic(c2_,(short)ev_.ll(),(short)_import.getMaxEv(), _import);
            _fight.addComment(membre_.getComment());
        }
    }

    static void addExp(Fight _fight,TeamPosition _fighter,
            Numbers<Byte> _members, EqList<TeamPosition> _porteursMultExp,
            TeamPosition _foe, Rate _points,
            Difficulty _diff, boolean _showMessage, DataBase _import) {
        //If the fighter _fighter is KO then it cannot win exp even by MULTI_EXP
        // the KO fighter checks !_porteursMultExp.containsObj(_fighter)
        // _members == FightOrder.fightersBelongingToUserHavingBeaten(_fight,_foe.getPosition())
        // The ref fought foe of A KO fighter are deleted
        // ==> the field wonExp does not vary
        byte nbPorteursMultExp_=(byte) _porteursMultExp.size();
        Team equipeUt_ = _fight.getUserTeam();
        Fighter membre_=equipeUt_.refPartMembres(_fighter.getPosition());
        byte presCbt_=0;
        if(_members.containsObj(_fighter.getPosition())){
            presCbt_=1;
        }
        byte portMultExp_=0;
        if(_porteursMultExp.containsObj(_fighter)){
            portMultExp_=1;
        }
        Rate a_;
        if(nbPorteursMultExp_>0){
            a_=new Rate(portMultExp_,2*nbPorteursMultExp_);
        } else {
            a_=Rate.zero();
        }
        Rate b_;
        if(!_members.isEmpty()){
            if(nbPorteursMultExp_>0){
                b_=new Rate(presCbt_,_members.size()*2);
            } else {
                b_=new Rate(presCbt_,_members.size());
            }
        } else {
            b_=Rate.zero();
        }
        Rate gainBase_=Rate.plus(a_,b_);
        gainBase_.multiplyBy(_points);
        Rate rate_ = rateWonPoint(_fight,_fighter, _foe, _diff, _import);
        gainBase_.multiplyBy(rate_);
        if (membre_.hasExpObject()) {
            ItemForBattle obj_ = (ItemForBattle) membre_.dataExpObject(_import);
            if (!obj_.getMultWinningExp().isZero()) {
                gainBase_.multiplyBy(obj_.getMultWinningExp());
            }
        }
        membre_.variationGainExperience(gainBase_, _import);
        if (_showMessage) {
            _fight.addComment(membre_.getComment());
        }
    }

    static Rate rateWonPoint(Fight _fight,TeamPosition _winner, TeamPosition _looser, Difficulty _diff, DataBase _import) {
        Fighter winner_ = _fight.getUserTeam().refPartMembres(_winner.getPosition());
        Fighter looser_ = _fight.getFoeTeam().refPartMembres(_looser.getPosition());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LEVEL_WINNER),Integer.toString(winner_.getLevel()));
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.LEVEL_LOOSER),Integer.toString(looser_.getLevel()));
        String exp_ = _import.getRates().getVal(_diff.getDiffWinningExpPtsFight());
        return _import.evaluatePositiveExp(exp_, vars_, Rate.one());
    }

    static Rate pointsFoe(Fight _fight,byte _adv, Difficulty _diff, DataBase _import) {
        Fighter creatureAdv_=_fight.getFoeTeam().getMembers().getVal(_adv);
        PokemonData fPk_=creatureAdv_.fichePokemon(_import);
        short niveauAdv_=creatureAdv_.getLevel();
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
        byte nbCombattantsAvantUt_=0;
        Team equipeUt_=_fight.getUserTeam();
        NumberMap<Byte,Fighter> membresEquipeUt_=equipeUt_.getMembers();
        for(byte c:membresEquipeUt_.getKeys()){
            Fighter membre_=membresEquipeUt_.getVal(c);
            if(!membre_.estArriere()){
                nbCombattantsAvantUt_++;
            }
        }
        byte nbCombattantsAvantAdv_=0;
        Team equipeAdv_=_fight.getFoeTeam();
        NumberMap<Byte,Fighter> membresEquipeAdv_=equipeAdv_.getMembers();
        for(byte c:membresEquipeAdv_.getKeys()){
            Fighter membre_=membresEquipeAdv_.getVal(c);
            if(!membre_.estArriere()){
                nbCombattantsAvantAdv_++;
            }
        }
        equipeUt_.move((byte) 0);
        equipeAdv_.move((byte) 0);
        //preliminaire deplacer tous les combattants vers la gauche
        byte a_;
        if(nbCombattantsAvantAdv_>nbCombattantsAvantUt_){
            a_ = nbCombattantsAvantAdv_;
        }else{
            a_ = nbCombattantsAvantUt_;
        }
        byte c_ = 0;
        byte d_ = 0;
        while (a_ - d_ > c_ + 1) {
            c_++;
            d_++;
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

    static boolean canBeHealed(Fight _fight,byte _equipe,DataBase _import){
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_equipe));
        for(String c:equipeAdv_.enabledTeamMoves()){
            MoveData fAttAdv_=_import.getMove(c);
            int nbEffets_=fAttAdv_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
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

}
