package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusType;
import aiki.fight.util.StatisticStatus;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightAbilities {

    private FightAbilities() {
    }

    static boolean ignoreTargetAbility(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        return ignoredTargetAbility(_fight, _lanceur, _cible, _import) == null;
    }

    static AbilityData ignoredTargetAbility(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        Fighter creatureLanceur_=_fight.getFighter(_lanceur);
        Fighter creatureCible_=_fight.getFighter(_cible);
        AbilityData capacCible_ = creatureCible_.ficheCapaciteActuelle(_import);
        if(capacCible_ == null){
            return null;
        }
        AbilityData fCapac_=creatureLanceur_.ficheCapaciteActuelle(_import);
        if(fCapac_ == null){
            return capacCible_;
        }
        if (StringUtil.contains(fCapac_.getIgnAbility(), creatureCible_.getCurrentAbility())) {
            return null;
        }
        return capacCible_;
    }

    static void enableAbility(Fight _fight,TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        AbilityData fCapac_=creatureCbt_.ficheCapaciteActuelle(_import);
        if(fCapac_ == null){
            return;
        }
        enableAbility(_fight, _cbt, _import, creatureCbt_, fCapac_);
    }

    private static void enableAbility(Fight _fight, TeamPosition _cbt, DataBase _import, Fighter _creatureCbt, AbilityData _fCapac) {
        _fight.addEnabledAbilityMessage(_cbt, _import);
        long defStatistic_ = _import.getDefaultBoost();
        for(Statistic c:_fCapac.getImmuLowStat()){
            //remise a zero du boost si negatif
            restoreBoost(_creatureCbt, c, defStatistic_, _fight, _cbt, _import);
        }
        for(String c:_creatureCbt.getStatusSet()){
            if(NumberUtil.eq(_creatureCbt.getStatusNbRoundShort(c), 0)){
                continue;
            }
            for(StatisticStatus e:_fCapac.getImmuLowStatIfStatus()){
                if(StringUtil.quickEq(e.getStatus(),c)){
                    //remise a zero du boost si negatif en fonction du statut
                    restoreBoost(_creatureCbt, e.getStatistic(), defStatistic_, _fight, _cbt, _import);
                }
            }
        }
        enableAbilityByWeather(_fight, _cbt, _import);
        FightSending.effectPlate(_fight, _cbt, _import);
        enableAbilityStatus(_fight, _cbt, _import, _creatureCbt, _fCapac);
    }

    private static void restoreBoost(Fighter _creatureCbt, Statistic _s, long _defStatistic, Fight _fight, TeamPosition _cbt, DataBase _import) {
        byte boost_= _creatureCbt.getStatisBoost().getVal(_s);
        if(boost_< _defStatistic){
            _creatureCbt.variationBoostStatistique(_s,(byte) (_defStatistic -boost_));
            _fight.addStatisticMessage(_cbt, _s, _defStatistic -boost_, _import);
        }
    }

    private static void enableAbilityStatus(Fight _fight, TeamPosition _cbt, DataBase _import, Fighter _creatureCbt, AbilityData _fCapac) {
        for(String c: _creatureCbt.getStatusSet()){
            if (NumberUtil.eq(_creatureCbt.getStatusNbRoundShort(c), 0) || !_fCapac.getDivideStatusRound().contains(c)) {
                continue;
            }
            Status statut_=_import.getStatus().getVal(c);
            if (statut_ instanceof StatusBeginRound) {
                StatusBeginRound status_ = (StatusBeginRound) statut_;
                short nbTour_ = _creatureCbt.getStatusNbRoundShort(c);
                MonteCarloNumber loi_ = status_.getLawForUsingAMoveNbRound();
                Rate coeffDivision_ = _fCapac.getDivideStatusRound().getVal(c);
                if (Rate.strLower(Rate.divide(loi_.maximum(), coeffDivision_), new Rate(nbTour_))) {
                    _creatureCbt.supprimerStatut(c);
                    _fight.addDisabledStatusMessage(c, _cbt, _import);
                }
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
        if(!types_.isEmpty()){
            types_.removeDuplicates();
            creatureCbt_.affecterTypes(types_);
            _fight.addChangedTypesMessage(_cbt, types_, _import);
        }
    }

    static void disableAllStatusByEnabledWeather(Fight _fight,TeamPosition _cbt,String _weather, DataBase _data) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        AbilityData fCapac_ = creatureCbt_.ficheCapaciteActuelle(_data);
        if (fCapac_ == null) {
            return;
        }
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
        if(StringUtil.quickEq(creatureCbt_.getCurrentAbility(),_newAbility)){
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
