package aiki.game.fight;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.Numbers;
import code.util.StringList;
import aiki.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusType;
import aiki.fight.util.StatisticStatus;

final class FightAbilities {

    private FightAbilities() {
    }

    static boolean ignoreTargetAbility(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCible_=_fight.getFighter(_cible);
        if(!creatureCible_.capaciteActive()){
            return true;
        }
        if(!creatureLanceur_.capaciteActive()){
            return false;
        }
        AbilityData fCapac_=creatureLanceur_.ficheCapaciteActuelle(_import);
        return fCapac_.getIgnAbility().containsObj(creatureCible_.getCurrentAbility());
    }

    static void enableAbility(Fight _fight,TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if(!creatureCbt_.capaciteActive()){
            return;
        }
        _fight.addEnabledAbilityMessage(_cbt, _import);
        long defStatistic_ = _import.getDefaultBoost();
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        for(Statistic c:fCapac_.getImmuLowStat()){
            //remise a zero du boost si negatif
            byte boost_=creatureCbt_.getStatisBoost().getVal(c);
            if(boost_<defStatistic_){
                creatureCbt_.variationBoostStatistique(c,(byte) (defStatistic_-boost_));
                _fight.addStatisticMessage(_cbt, c, defStatistic_-boost_, _import);
            }
        }
        for(String c:creatureCbt_.getStatusSet()){
            if(Numbers.eq(creatureCbt_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            for(StatisticStatus e:fCapac_.getImmuLowStatIfStatus()){
                if(StringList.quickEq(e.getStatus(),c)){
                    //remise a zero du boost si negatif en fonction du statut
                    byte boost_=creatureCbt_.getStatisBoost().getVal(e.getStatistic());
                    if(boost_<defStatistic_){
                        creatureCbt_.variationBoostStatistique(e.getStatistic(),(byte) (defStatistic_-boost_));
                        _fight.addStatisticMessage(_cbt, e.getStatistic(), defStatistic_-boost_, _import);
                    }
                }
            }
        }
        enableAbilityByWeather(_fight, _cbt, _import);
        FightSending.effectPlate(_fight, _cbt, _import);
        for(String c:creatureCbt_.getStatusSet()){
            if(Numbers.eq(creatureCbt_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            if(!fCapac_.getDivideStatusRound().contains(c)){
                continue;
            }
            Status statut_=_import.getStatus().getVal(c);
            if(!(statut_ instanceof StatusBeginRound)){
                continue;
            }
            StatusBeginRound status_ = (StatusBeginRound) statut_;
            short nbTour_=creatureCbt_.getStatusNbRoundShort(c);
            MonteCarloNumber loi_=status_.getLawForUsingAMoveNbRound();
            Rate coeffDivision_=fCapac_.getDivideStatusRound().getVal(c);
            if(Rate.strLower(Rate.divide(loi_.maximum(),coeffDivision_),new Rate(nbTour_))){
                creatureCbt_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _cbt, _import);
            }
        }
    }

    static void enableAbilityByWeather(Fight _fight,TeamPosition _cbt,DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        StringList climatsActifs_= FightMoves.enabledGlobalMoves(_fight, _import);
        StringList types_=new StringList();
        for(String c:climatsActifs_){
            disableAllStatusByEnabledWeather(_fight, _cbt, c, _import);
            types_.addAllElts(creatureCbt_.getAddedTypesByEnabledWeather(c, _import));
        }
        if(climatsActifs_.isEmpty()){
            disableAllStatusByEnabledWeather(_fight, _cbt, DataBase.EMPTY_STRING, _import);
            types_.addAllElts(creatureCbt_.getAddedTypesByEnabledWeather(DataBase.EMPTY_STRING, _import));
        }
        types_.removeDuplicates();
        if(!types_.isEmpty()){
            creatureCbt_.affecterTypes(types_);
            _fight.addChangedTypesMessage(_cbt, types_, _import);
        }
    }

    static void disableAllStatusByEnabledWeather(Fight _fight,TeamPosition _cbt,String _weather, DataBase _data) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if (!creatureCbt_.capaciteActive()) {
            return;
        }
        AbilityData fCapac_ = creatureCbt_.ficheCapaciteActuelle(_data);
        if (fCapac_.getImmuStatus().contains(_weather)) {
            for(String e:fCapac_.getImmuStatus().getVal(_weather)){
                if(_data.getStatus(e).getStatusType() == StatusType.INDIVIDUEL){
                    creatureCbt_.supprimerStatut(e);
                    _fight.addDisabledStatusMessage(e, _cbt, _data);
                }else{
                    creatureCbt_.supprimerPseudoStatut(e);
                    _fight.addDisabledStatusOtherRelMessage(e, _cbt, _data);
                }
            }
        }
    }

    static void disableAbility(Fight _fight,TeamPosition _cbt,String _newAbility,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if(StringList.quickEq(creatureCbt_.getCurrentAbility(),_newAbility)){
            return;
        }
        creatureCbt_.setCurrentAbility(_newAbility);
        _fight.addChangedAbilityMessage(_cbt, _newAbility, _import);
        AbilityData fCapacBase_=creatureCbt_.ficheCapacite(_import);
        if (fCapacBase_.isPlate()) {
            StringList types_ = creatureCbt_.fichePokemon(_import).getTypes();
            creatureCbt_.affecterTypes(types_);
            _fight.addChangedTypesMessage(_cbt, types_, _import);
        }
        if(!fCapacBase_.getChgtTypeByWeather().isEmpty()||fCapacBase_.isChgtTypeByDamage()){
            StringList types_ = creatureCbt_.fichePokemon(_import).getTypes();
            creatureCbt_.affecterTypes(types_);
            _fight.addChangedTypesMessage(_cbt, types_, _import);
        }
    }
}
