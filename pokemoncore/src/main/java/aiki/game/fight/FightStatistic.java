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
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.StatisticPokemon;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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
        boolean peutUtiliserObjet_=FightItems.canUseItsObject(_fight,_cbt,_import);
        if(peutUtiliserObjet_){
            Item fObjet_=creatureCbt_.ficheObjet(_import);
            if(fObjet_ instanceof ItemForBattle){
                ItemForBattle fObjetCombat_=(ItemForBattle)fObjet_;
                if (fObjetCombat_.getMultStat().contains(_statistic)) {
                    String numericString_ = fObjetCombat_.getMultStat().getVal(_statistic);
                    statistic_.multiplyBy(multiplyStringFighterVariables(numericString_,_variables, _import));
                }
            }
        }
        for(String c: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttGlobal_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                for(String e:creatureCbt_.getTypes()){
                    if(effetGlobal_.getMultStatIfContainsType().contains(new StatisticType(_statistic,e))){
                        statistic_.multiplyBy(effetGlobal_.getMultStatIfContainsType().getVal(new StatisticType(_statistic,e)));
                    }
                }
            }
        }
        statistic_.multiplyBy(multiplyStatisticPartner(_fight, _statistic, _cbt.getTeam(), _import));
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_cbt.getTeam()));
        for(StringList c:equipeAdv_.enabledTeamGroupMoves()){
            EffectCombo effet_=_import.getCombos().getEffects().getVal(c);
            if(!effet_.estActifEquipe()){
                continue;
            }
            EffectTeam effetEquipe_=effet_.getTeamMove().first();
            if(!effetEquipe_.getMultStatisticFoe().contains(_statistic)){
                continue;
            }
            statistic_.multiplyBy(effetEquipe_.getMultStatisticFoe().getVal(_statistic));
            //MULT_STATISTIQUE_ADV
        }
        statistic_.multiplyBy(multiplyStatisticFoeTeamMoveEffect(_fight, _statistic, Fight.foe(_cbt.getTeam()), _import));
        statistic_.multiplyBy(multiplyStatisticTeamMoveEffect(_fight, _statistic, _cbt.getTeam(), _import));
        statistic_.multiplyBy(coeffStatisticStatusImmu(_fight, _cbt, _statistic, _import));
        if(creatureCbt_.capaciteActive()){
            AbilityData fCapacite_=creatureCbt_.ficheCapaciteActuelle(_import);
            if (fCapacite_.getMultStat().contains(_statistic)) {
                String numericString_ = fCapacite_.getMultStat().getVal(_statistic);
                statistic_.multiplyBy(multiplyStringFighterVariables(numericString_,_variables, _import));
            }
        }
        return statistic_;
    }

    static int criticalHit(Fight _fight, TeamPosition _fighter, int _rate, DataBase _import) {
        Fighter creatureLanceur_ = _fight.getFighter(_fighter);
        int boostCc_=creatureLanceur_.getStatisBoost().getVal(Statistic.CRITICAL_HIT);
        boostCc_ += FightStatistic.bonusBoost(_fight,Statistic.CRITICAL_HIT, _fighter, _import);
        boostCc_ += _rate;
        return boostCc_;
    }

    static int bonusBoost(Fight _fight, Statistic _statistic, TeamPosition _fighter, DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_fighter);
        int bonus_ = 0;
        boolean canUseItsObject_ = FightItems.canUseItsObject(_fight,_fighter, _import);
        if(canUseItsObject_){
            Item fObjet_=fighter_.ficheObjet(_import);
            if(fObjet_ instanceof ItemForBattle){
                ItemForBattle fObjetCombat_=(ItemForBattle)fObjet_;
                EnumMap<Statistic,Byte> multStatisCran_=fObjetCombat_.getMultStatRank();
                if(multStatisCran_.contains(_statistic)){
                    bonus_+=multStatisCran_.getVal(_statistic);
                }
                ObjectMap<StatisticPokemon,Byte> multStatisPkCran_=fObjetCombat_.getMultStatPokemonRank();
                if(multStatisPkCran_.contains(new StatisticPokemon(_statistic,fighter_.getCurrentName()))){
                    bonus_+=multStatisPkCran_.getVal(new StatisticPokemon(_statistic,fighter_.getCurrentName()));
                }
            }
        }
        if (fighter_.capaciteActive()) {
            AbilityData ability_ = fighter_.ficheCapaciteActuelle(_import);
            EnumMap<Statistic,Byte> multStatisCran_ = ability_.getBonusStatRank();
            if (multStatisCran_.contains(_statistic)) {
                bonus_+=multStatisCran_.getVal(_statistic);
            }
        }
        if(FightItems.canUseItsBerry(_fight, _fighter, _import)){
            Berry berry_=(Berry) fighter_.ficheObjet(_import);
            if(berry_.getMultStat().contains(_statistic)){
                BoostHpRate statis_=berry_.getMultStat().getVal(_statistic);
                if (Rate.lowerEq(fighter_.getRemainingHp(), Rate.multiply(statis_.getHpRate(), fighter_.pvMax()))) {
                    bonus_ += statis_.getBoost();
                }
            }
        }
        return bonus_;
    }

    static Rate multiplyStatisticPartner(Fight _fight, Statistic _statistic, byte _noTeam, DataBase _import) {
        Rate rate_ = Rate.one();
        Team team_ = _fight.getTeams().getVal(_noTeam);
        for(byte c:team_.getMembers().getKeys()){
            Fighter partenaire_=team_.getMembers().getVal(c);
            if(partenaire_.estArriere()){
                continue;
            }
            if(!partenaire_.capaciteActive()){
                continue;
            }
            AbilityData fCapacite_=partenaire_.ficheCapaciteActuelle(_import);
            if(fCapacite_.getMultStatAlly().contains(_statistic)){
                rate_.multiplyBy(fCapacite_.getMultStatAlly().getVal(_statistic));
            }
        }
        return rate_;
    }

    static Rate multiplyStatisticTeamMoveEffect(Fight _fight, Statistic _statistic, byte _noTeam, DataBase _import) {
        Rate rate_ = Rate.one();
        Team equipe_ = _fight.getTeams().getVal(_noTeam);
        for(String c:equipe_.enabledTeamMoves()){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(!effetEquipe_.getMultStatistic().contains(_statistic)){
                    continue;
                }
                rate_.multiplyBy(effetEquipe_.getMultStatistic().getVal(_statistic));
            }
        }
        return rate_;
    }

    static Rate multiplyStatisticFoeTeamMoveEffect(Fight _fight, Statistic _statistic, byte _noTeam, DataBase _import) {
        Rate rate_ = Rate.one();
        Team equipe_ = _fight.getTeams().getVal(_noTeam);
        for(String c:equipe_.enabledTeamMoves()){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectTeam)){
                    continue;
                }
                EffectTeam effetEquipe_=(EffectTeam)effet_;
                if(!effetEquipe_.getMultStatisticFoe().contains(_statistic)){
                    continue;
                }
                rate_.multiplyBy(effetEquipe_.getMultStatisticFoe().getVal(_statistic));
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
            if(Numbers.eq(fighter_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            Status statut_=_import.getStatus().getVal(c);
            if(!statut_.getMultStat().contains(_statistic)){
                continue;
            }
            if(!fighter_.capaciteActive()){
                rate_.multiplyBy(statut_.getMultStat().getVal(_statistic));
                continue;
            }
            AbilityData fCapac_=fighter_.ficheCapaciteActuelle(_import);
            boolean immuBaisse_=false;
            if(fCapac_.getImmuLowStatIfStatus().containsObj(new StatisticStatus(_statistic,c))){
                immuBaisse_=true;
            }
            Rate taux_=statut_.getMultStat().getVal(_statistic);
            if(!immuBaisse_ || taux_.greaterThanOne()){
                rate_.multiplyBy(taux_);
            }
        }
        return rate_;
    }

    static Rate rateBoost(byte _boost, DataBase _import) {
        //RATE_BOOST
        String numericExp_ = _import.getRateBoost();
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), Integer.toString(_boost));
        return _import.evaluatePositiveExp(numericExp_, variables_, Rate.one());
    }

    static Rate multiplyByLoveBetweenFighters(Fight _fight,DataBase _import) {
        Rate rate_ = Rate.one();
        for(String c2_: FightMoves.enabledGlobalMoves(_fight,_import)){
            MoveData fAttGlobal_=_import.getMove(c2_);
            int nbEffets_=fAttGlobal_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
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
