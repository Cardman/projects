package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.status.Status;
import aiki.fight.util.*;
import code.maths.Rate;
import code.util.IdMap;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class FightStatistic {

    private FightStatistic() {
    }

    static Rate multiplyStringFighter(Fight _fight, String _numericString, TeamPosition _combattant, DataBase _import) {
        if (_numericString.isEmpty()) {
            return Rate.one();
        }
        return multiplyStringFighterVariables(_numericString,FightValues.calculateValuesFighter(_fight, _combattant, _import), _import);
    }

    static Rate statisticWithoutBase(Fight _fight, TeamPosition _cbt, Statistic _statistic, StringMap<String> _variables, DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        Rate statistic_ = Rate.one();
        Item fObjet_ = FightItems.useItsObject(_fight, _cbt, _import);
        if (fObjet_ instanceof ItemForBattle) {
            ItemForBattle fObjetCombat_ = (ItemForBattle) fObjet_;
            if (fObjetCombat_.getMultStat().contains(_statistic)) {
                String numericString_ = fObjetCombat_.getMultStat().getVal(_statistic);
                statistic_.multiplyBy(multiplyStringFighterVariables(numericString_, _variables, _import));
            }
        }
        statisticWithoutBaseGlobal(_fight, _statistic, _import, creatureCbt_, statistic_);
        statistic_.multiplyBy(multiplyStatisticPartner(_fight, _statistic, _cbt.getTeam(), _import));
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_cbt.getTeam()));
        for(StringList c:equipeAdv_.enabledTeamGroupMoves()){
            EffectCombo effet_=_import.getCombos().getEffects().getVal(c);
            if(!effet_.estActifEquipe()){
                continue;
            }
            EffectTeam effetEquipe_=effet_.getTeamMove().first();
            if (effetEquipe_.getMultStatisticFoe().contains(_statistic)) {
                statistic_.multiplyBy(effetEquipe_.getMultStatisticFoe().getVal(_statistic));
                //MULT_STATISTIQUE_ADV
            }
        }
        statistic_.multiplyBy(multiplyStatisticFoeTeamMoveEffect(_fight, _statistic, Fight.foe(_cbt.getTeam()), _import));
        statistic_.multiplyBy(multiplyStatisticTeamMoveEffect(_fight, _statistic, _cbt.getTeam(), _import));
        statistic_.multiplyBy(coeffStatisticStatusImmu(_fight, _cbt, _statistic, _import));
        AbilityData fCapacite_=creatureCbt_.ficheCapaciteActuelle(_import);
        if (fCapacite_ != null && fCapacite_.getMultStat().contains(_statistic)) {
            String numericString_ = fCapacite_.getMultStat().getVal(_statistic);
            statistic_.multiplyBy(multiplyStringFighterVariables(numericString_, _variables, _import));
        }
        return statistic_;
    }

    private static void statisticWithoutBaseGlobal(Fight _fight, Statistic _statistic, DataBase _import, Fighter _creatureCbt, Rate _rate) {
        for(String c: FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAttGlobal_= _import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                for(String e: _creatureCbt.getTypes()){
                    if(effetGlobal_.getMultStatIfContainsType().contains(new StatisticType(_statistic,e))){
                        _rate.multiplyBy(effetGlobal_.getMultStatIfContainsType().getVal(new StatisticType(_statistic,e)));
                    }
                }
            }
        }
    }

    static long criticalHit(Fight _fight, TeamPosition _fighter, long _rate, DataBase _import) {
        Fighter creatureLanceur_ = _fight.getFighter(_fighter);
        long boostCc_=creatureLanceur_.getStatisBoost().getVal(Statistic.CRITICAL_HIT);
        boostCc_ += FightStatistic.bonusBoost(_fight,Statistic.CRITICAL_HIT, _fighter, _import);
        boostCc_ += _rate;
        return boostCc_;
    }

    static long bonusBoost(Fight _fight, Statistic _statistic, TeamPosition _fighter, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_fighter);
        long bonus_ = 0;
        Item fObjet_ = FightItems.useItsObject(_fight, _fighter, _import);
        if (fObjet_ instanceof ItemForBattle) {
            ItemForBattle fObjetCombat_ = (ItemForBattle) fObjet_;
            IdMap<Statistic,Long> multStatisCran_ = fObjetCombat_.getMultStatRank();
            if (multStatisCran_.contains(_statistic)) {
                bonus_ += multStatisCran_.getVal(_statistic);
            }
            StatisticPokemons multStatisPkCran_ = fObjetCombat_.getMultStatPokemonRank();
            if (multStatisPkCran_.contains(new StatisticPokemon(_statistic, fighter_.getCurrentName()))) {
                bonus_ += multStatisPkCran_.getVal(new StatisticPokemon(_statistic, fighter_.getCurrentName()));
            }
        }
        AbilityData ability_ = fighter_.ficheCapaciteActuelle(_import);
        if (ability_ != null) {
            IdMap<Statistic,Long> multStatisCran_ = ability_.getBonusStatRank();
            if (multStatisCran_.contains(_statistic)) {
                bonus_+=multStatisCran_.getVal(_statistic);
            }
        }
        Berry berry_ = FightItems.useItsBerry(_fight, _fighter, _import);
        if (berry_ != null && berry_.getMultStat().contains(_statistic)) {
            BoostHpRate statis_ = berry_.getMultStat().getVal(_statistic);
            if (Rate.lowerEq(fighter_.getRemainingHp(), Rate.multiply(statis_.getHpRate(), fighter_.pvMax()))) {
                bonus_ += statis_.getBoost();
            }
        }
        return bonus_;
    }

    static Rate multiplyStatisticPartner(Fight _fight, Statistic _statistic, int _noTeam, DataBase _import) {
        Rate rate_ = Rate.one();
        Team team_ = _fight.getTeams().getVal(_noTeam);
        for(int c:team_.getMembers().getKeys()){
            Fighter partenaire_=team_.getMembers().getVal(c);
            if(partenaire_.estArriere()){
                continue;
            }
            AbilityData fCapacite_=partenaire_.ficheCapaciteActuelle(_import);
            if (fCapacite_ != null && fCapacite_.getMultStatAlly().contains(_statistic)) {
                rate_.multiplyBy(fCapacite_.getMultStatAlly().getVal(_statistic));
            }
        }
        return rate_;
    }

    static Rate multiplyStatisticTeamMoveEffect(Fight _fight, Statistic _statistic, int _noTeam, DataBase _import) {
        return multiplyStatisticFoeTeamMoveEffectRet(_fight, _statistic, _noTeam, _import,new EffectTeamMultStatRetrieverTeam());
    }

    static Rate multiplyStatisticFoeTeamMoveEffect(Fight _fight, Statistic _statistic, int _noTeam, DataBase _import) {
        return multiplyStatisticFoeTeamMoveEffectRet(_fight, _statistic, _noTeam, _import,new EffectTeamMultStatRetrieverFoeTeam());
    }

    static Rate multiplyStatisticFoeTeamMoveEffectRet(Fight _fight, Statistic _statistic, int _noTeam, DataBase _import, AbsEffectTeamMultStatRetriever _retr) {
        Rate rate_ = Rate.one();
        Team equipe_ = _fight.getTeams().getVal(_noTeam);
        for(String c:equipe_.enabledTeamMoves()){
            CustList<EffectTeam> list_ = FightSuccess.effectsTeamMove(_import,c);
            int nbEffets_=list_.size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                EffectTeam effetEquipe_=list_.get(i);
                rate_.multiplyBy(_retr.retrieve(effetEquipe_,_statistic));
            }
        }
        return rate_;
    }
    static Rate multiplyStringFighterVariables(String _numericString, StringMap<String> _variables, DataBase _import) {
        if (_numericString.isEmpty()) {
            return Rate.one();
        }
        Rate default_ = Rate.one();
        return _import.evaluatePositiveExp(_numericString, _variables, default_);
    }

    static Rate coeffStatisticStatusImmu(Fight _fight, TeamPosition _fighter, Statistic _statistic, DataBase _import) {
        Rate rate_ = DataBase.defRateProduct();
        Fighter fighter_ = _fight.getFighter(_fighter);
        for(String c:fighter_.getStatusSet()){
            if(NumberUtil.eq(fighter_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            Status statut_=_import.getStatus().getVal(c);
            if (statut_.getMultStat().contains(_statistic)) {
                AbilityData fCapac_ = fighter_.ficheCapaciteActuelle(_import);
                Rate taux_ = statut_.getMultStat().getVal(_statistic);
                if (fCapac_ == null || !fCapac_.containsStatisticStatus(new StatisticStatus(_statistic, c)) || taux_.greaterThanOne()) {
                    rate_.multiplyBy(taux_);
                }
            }
        }
        return rate_;
    }

    static Rate rateBoost(long _boost, DataBase _import) {
        //RATE_BOOST
        String numericExp_ = _import.getRateBoost();
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(_import.prefixBoost(), Long.toString(_boost));
        return _import.evaluatePositiveExp(numericExp_, variables_, Rate.one());
    }

    static Rate multiplyByLoveBetweenFighters(Fight _fight,DataBase _import) {
        Rate rate_ = Rate.one();
        for(String c2_: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c2_);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                if(!effetGlobal_.getMultEffectLovingAlly().isZero()){
                    rate_.multiplyBy(effetGlobal_.getMultEffectLovingAlly());
                }
            }
        }
        return rate_;
    }
}
