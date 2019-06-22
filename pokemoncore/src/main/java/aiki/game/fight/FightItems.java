package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.game.fight.util.NbEffectFighterCoords;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class FightItems {

    private FightItems() {
    }

    static boolean canUseItsObject(Fight _fight, TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if(!creatureCbt_.possedeObjet()){
            return false;
        }
        return canUseObjectIfPossible(_fight,_cbt, _import);
    }

    static boolean canUseItsBerry(Fight _fight,TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if (!creatureCbt_.possedeObjet()) {
            return false;
        }
        Item objet_=creatureCbt_.ficheObjet(_import);
        if(!(objet_ instanceof Berry)){
            return false;
        }
        return canUseBerry(_fight, _cbt, _import);
    }

    static boolean canUseBerry(Fight _fight,TeamPosition _cbt,DataBase _import) {
        if (!canUseObjectIfPossible(_fight,_cbt, _import)) {
            return false;
        }
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_cbt.getTeam()));
        for(byte c:equipeAdv_.getMembers().getKeys()){
            Fighter creatureAdv_=equipeAdv_.getMembers().getVal(c);
            if(creatureAdv_.estArriere()){
                continue;
            }
            if(!creatureAdv_.capaciteActive()){
                continue;
            }
            AbilityData fCapac_=creatureAdv_.ficheCapaciteActuelle(_import);
            if(!fCapac_.isForbidUseBerryAgainstFoes()){
                continue;
            }
            if(FightAbilities.ignoreTargetAbility(_fight,_cbt,new TeamPosition(Fight.foe(_cbt.getTeam()),c),_import)){
                continue;
            }
            return false;
        }
        return true;
    }

    static boolean canUseObjectIfPossible(Fight _fight,TeamPosition _cbt,DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        boolean prive_=false;
        for(String c:creatureCbt_.enabledIndividualMoves()){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectRestriction)){
                    continue;
                }
                EffectRestriction effetAntiChoix_=(EffectRestriction)effet_;
                if(effetAntiChoix_.getForbidTargetUsingItem()){
                    prive_=true;
                    break;
                }
            }
            if(prive_){
                break;
            }
        }
        if(prive_){
            return false;
        }
        for(String c:FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = CustList.FIRST_INDEX;i<nbEffets_;i++){
                Effect effet_=fAttaque_.getEffet(i);
                if(!(effet_ instanceof EffectGlobal)){
                    continue;
                }
                EffectGlobal effetGlobal_=(EffectGlobal)effet_;
                if(effetGlobal_.getUnusableItem()){
                    prive_=true;
                    break;
                }
            }
            if(prive_){
                break;
            }
        }
        return !prive_;
    }

    static void enableBerry(Fight _fight,TeamPosition _combattant,String _objet,DataBase _import){
        enableBerryHp(_fight, _combattant, _objet, false, false, _import);
        enableBerryPp(_fight, _combattant, _objet, false, _import);
        enableBerryStatus(_fight, _combattant, _objet, false, _import);
        enableBerryStatistic(_fight, _combattant, _objet, false, false, _import);
    }

    static void enableBerryHp(Fight _fight,TeamPosition _combattant,String _objectName,
            boolean _useObject, boolean _checkCondition, DataBase _import) {
        Fighter creature_=_fight.getFighter(_combattant);
        Berry berry_ = (Berry) _import.getItem(_objectName);
        Rate pvRestants_=new Rate(creature_.getRemainingHp());
        if(!berry_.getHealHp().isZero()){
            if (!_checkCondition) {
                creature_.variationLeftHp(berry_.getHealHp());
                _fight.addHpMessage(_combattant, _import);
            } else {
                Rate mult_ = Rate.one();
                if (creature_.capaciteActive()) {
                    Rate rate_ = creature_.ficheCapaciteActuelle(_import).getMaxHpForUsingBerry();
                    if (!rate_.isZero()) {
                        mult_.affect(rate_);
                    } else {
                        mult_.affect(berry_.getMaxHpHealingHp());
                    }
                } else {
                    mult_.affect(berry_.getMaxHpHealingHp());
                }
                if(Rate.greaterEq(Rate.multiply(mult_,creature_.pvMax()),creature_.getRemainingHp())){
                    creature_.variationLeftHp(berry_.getHealHp());
                    _fight.addHpMessage(_combattant, _import);
                }
            }
        }
        if(!berry_.getHealHpRate().isZero()){
            if (!_checkCondition) {
                creature_.variationLeftHp(Rate.multiply(berry_.getHealHpRate(),creature_.pvMax()));
                _fight.addHpMessage(_combattant, _import);
            } else {
                Rate mult_ = Rate.one();
                if (creature_.capaciteActive()) {
                    Rate rate_ = creature_.ficheCapaciteActuelle(_import).getMaxHpForUsingBerry();
                    if (!rate_.isZero()) {
                        mult_.affect(rate_);
                    } else {
                        mult_.affect(berry_.getMaxHpHealingHpRate());
                    }
                } else {
                    mult_.affect(berry_.getMaxHpHealingHpRate());
                }
                if(Rate.greaterEq(Rate.multiply(mult_,creature_.pvMax()),creature_.getRemainingHp())){
                    creature_.variationLeftHp(Rate.multiply(berry_.getHealHpRate(),creature_.pvMax()));
                    _fight.addHpMessage(_combattant, _import);
                }
            }
        }
        if(Rate.strLower(pvRestants_,creature_.getRemainingHp())){
            if (_useObject) {
                creature_.useObject();
                bonusHp(_fight, _combattant, _import);
            }
        }
    }

    static void enableBerryStatus(Fight _fight,TeamPosition _combattant,String _objectName, boolean _useObject, DataBase _import) {
        Fighter creature_=_fight.getFighter(_combattant);
        StringList statuts_=new StringList();
        Berry berry_ = (Berry) _import.getItem(_objectName);
        for(String c:creature_.getStatusSet()){
            if(Numbers.eq(creature_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            if(StringList.contains(berry_.getHealStatus(), c)){
                statuts_.add(c);
                creature_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _combattant, _import);
            }
        }
        if(!statuts_.isEmpty()){
            if (_useObject) {
                creature_.useObject();
                bonusHp(_fight, _combattant, _import);
            }
        }
    }

    static void enableBerryStatistic(Fight _fight,TeamPosition _combattant,String _objectName,
            boolean _useObject, boolean _checkCondition, DataBase _import) {
        Fighter creature_=_fight.getFighter(_combattant);
        Berry berry_ = (Berry) _import.getItem(_objectName);
        for(Statistic c:berry_.getMultStat().getKeys()){
            Rate taux_=berry_.getMultStat().getVal(c).getHpRate();
            if (_checkCondition) {
                if (Rate.strLower(Rate.multiply(taux_, creature_.pvMax()),creature_.getRemainingHp())){
                    continue;
                }
            }
            byte varBase_=berry_.getMultStat().getVal(c).getBoost();
            byte var_=FightEffects.deltaBoostStatistic(_fight, _combattant,c,varBase_,_import);
            byte boost_ = creature_.getStatisBoost().getVal(c);
            creature_.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_combattant, c, var_, _import);
            if(boost_<creature_.getStatisBoost().getVal(c)){
                if(_useObject) {
                    creature_.useObject();
                    bonusHp(_fight, _combattant, _import);
                }
            }
        }
    }

    static void enableBerryPp(Fight _fight,TeamPosition _combattant,String _objectName, boolean _useObject, DataBase _import) {
        Fighter creature_=_fight.getFighter(_combattant);
        for(String c:creature_.getCurrentMovesSet()){
            if(creature_.powerPointsMove(c) != 0){
                continue;
            }
            short var_=creature_.healedPpMove(c,_objectName,_import);
            if(var_>0){
                if (_useObject) {
                    creature_.useObject();
                    bonusHp(_fight, _combattant, _import);
                }
                creature_.healPowerPoints(c,var_);
                break;
            }
        }
    }

    static void enableBerryHpWhileSuperEffectiveMove(Fight _fight,TeamPosition _combattant,
            int _index, DataBase _import) {
        if (!canUseItsBerry(_fight, _combattant, _import)) {
            return;
        }
        Fighter creatureCible_=_fight.getFighter(_combattant);
        Berry berry_ = (Berry) creatureCible_.ficheObjet(_import);
        if(berry_.getHealHpBySuperEffMove().isZero()){
            return;
        }
        Rate leftHp_ = new Rate(creatureCible_.getRemainingHp());
        creatureCible_.variationLeftHp(Rate.multiply(berry_.getHealHpBySuperEffMove(),creatureCible_.pvMax()));
        _fight.addHpMessage(_combattant, _import);
        if (!Rate.strGreater(creatureCible_.getRemainingHp(), leftHp_)) {
            return;
        }
        creatureCible_.useObject();
        bonusHp(_fight, _combattant, _import);
        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,_combattant),false);
    }

    static void bonusHp(Fight _fight,TeamPosition _combattant,DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_combattant);
        if (fighter_.capaciteActive()) {
            AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
            fighter_.variationLeftHp(Rate.multiply(ab_.getHealHpWhileUsingBerry(),fighter_.pvMax()));
            _fight.addHpMessage(_combattant, _import);
        }
    }
}
