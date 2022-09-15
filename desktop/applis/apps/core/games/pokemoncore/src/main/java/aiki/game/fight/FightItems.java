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
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightItems {

    private FightItems() {
    }

    static boolean canUseItsObject(Fight _fight, TeamPosition _cbt,DataBase _import){
        return useItsObject(_fight, _cbt, _import) != null;
    }

    static Item useItsObject(Fight _fight, TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if(!creatureCbt_.possedeObjet()){
            return null;
        }
        if (canUseObjectIfPossible(_fight,_cbt, _import)) {
            return creatureCbt_.ficheObjet(_import);
        }
        return null;
    }

    static boolean canUseItsBerry(Fight _fight,TeamPosition _cbt,DataBase _import){
        return useItsBerry(_fight, _cbt, _import) != null;
    }

    static Berry useItsBerry(Fight _fight,TeamPosition _cbt,DataBase _import){
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        if (!creatureCbt_.possedeObjet()) {
            return null;
        }
        Item objet_=creatureCbt_.ficheObjet(_import);
        if(!(objet_ instanceof Berry)){
            return null;
        }
        if (canUseBerry(_fight, _cbt, _import)) {
            return (Berry) objet_;
        }
        return null;
    }
    static boolean canUseBerry(Fight _fight,TeamPosition _cbt,DataBase _import) {
        if (!canUseObjectIfPossible(_fight,_cbt, _import)) {
            return false;
        }
        Team equipeAdv_=_fight.getTeams().getVal(Fight.foe(_cbt.getTeam()));
        for(byte c:equipeAdv_.getMembers().getKeys()){
            if (ignoreFoeBerry(_fight, _cbt, _import, equipeAdv_, c)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private static boolean ignoreFoeBerry(Fight _fight, TeamPosition _cbt, DataBase _import, Team _equipeAdv, byte _c) {
        Fighter creatureAdv_= _equipeAdv.getMembers().getVal(_c);
        if(creatureAdv_.estArriere()){
            return true;
        }
        AbilityData fCapac_=creatureAdv_.ficheCapaciteActuelle(_import);
        if (fCapac_ == null || !fCapac_.isForbidUseBerryAgainstFoes()) {
            return true;
        }
        return FightAbilities.ignoreTargetAbility(_fight, _cbt, new TeamPosition(Fight.foe(_cbt.getTeam()), _c), _import);
    }

    static boolean canUseObjectIfPossible(Fight _fight,TeamPosition _cbt,DataBase _import) {
        Fighter creatureCbt_=_fight.getFighter(_cbt);
        boolean prive_ = priveEffectRestriction(_import, creatureCbt_);
        if(prive_){
            return false;
        }
        for(String c:FightMoves.enabledGlobalMoves(_fight, _import)){
            MoveData fAttaque_=_import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if (effet_ instanceof EffectGlobal) {
                    EffectGlobal effetGlobal_ = (EffectGlobal) effet_;
                    if (effetGlobal_.getUnusableItem()) {
                        prive_ = true;
                        break;
                    }
                }
            }
            if(prive_){
                break;
            }
        }
        return !prive_;
    }

    private static boolean priveEffectRestriction(DataBase _import, Fighter _creatureCbt) {
        boolean prive_=false;
        for(String c: _creatureCbt.enabledIndividualMoves()){
            MoveData fAttaque_= _import.getMove(c);
            int nbEffets_=fAttaque_.nbEffets();
            for (int i = IndexConstants.FIRST_INDEX; i<nbEffets_; i++){
                Effect effet_=fAttaque_.getEffet(i);
                if (effet_ instanceof EffectRestriction) {
                    EffectRestriction effetAntiChoix_ = (EffectRestriction) effet_;
                    if (effetAntiChoix_.getForbidTargetUsingItem()) {
                        prive_ = true;
                        break;
                    }
                }
            }
            if(prive_){
                break;
            }
        }
        return prive_;
    }

    static void enableBerry(Fight _fight,TeamPosition _combattant,String _objet,DataBase _import, Berry _berry){
        enableBerryHp(_fight, _combattant, false, false, _import, _berry);
        enableBerryPp(_fight, _combattant, _objet, false, _import);
        enableBerryStatus(_fight, _combattant, false, _import, _berry);
        enableBerryStatistic(_fight, _combattant, false, false, _import, _berry);
    }

    static void enableBerryHp(Fight _fight, TeamPosition _combattant,
                              boolean _useObject, boolean _checkCondition, DataBase _import, Berry _berry) {
        Fighter creature_=_fight.getFighter(_combattant);
        Rate pvRestants_=new Rate(creature_.getRemainingHp());
        if(!_berry.getHealHp().isZero()){
            if (!_checkCondition) {
                creature_.variationLeftHp(_berry.getHealHp());
                _fight.addHpMessage(_combattant, _import);
            } else {
                Rate mult_ = mult(creature_, _import, _berry.getMaxHpHealingHp());
                if(Rate.greaterEq(Rate.multiply(mult_,creature_.pvMax()),creature_.getRemainingHp())){
                    creature_.variationLeftHp(_berry.getHealHp());
                    _fight.addHpMessage(_combattant, _import);
                }
            }
        }
        if(!_berry.getHealHpRate().isZero()){
            if (!_checkCondition) {
                creature_.variationLeftHp(Rate.multiply(_berry.getHealHpRate(),creature_.pvMax()));
                _fight.addHpMessage(_combattant, _import);
            } else {
                Rate mult_ = mult(creature_, _import, _berry.getMaxHpHealingHpRate());
                if(Rate.greaterEq(Rate.multiply(mult_,creature_.pvMax()),creature_.getRemainingHp())){
                    creature_.variationLeftHp(Rate.multiply(_berry.getHealHpRate(),creature_.pvMax()));
                    _fight.addHpMessage(_combattant, _import);
                }
            }
        }
        useIfAct(_fight, _combattant, _useObject, _import, creature_, pvRestants_);
    }

    private static void useIfAct(Fight _fight, TeamPosition _combattant, boolean _useObject, DataBase _import, Fighter _creature, Rate _pvRestants) {
        if (Rate.strLower(_pvRestants, _creature.getRemainingHp())) {
            useObj(_fight, _combattant, _useObject, _import, _creature);
        }
    }

    private static Rate mult(Fighter _creature, DataBase _import, Rate _rate) {
        Rate mult_ = Rate.one();
        AbilityData ab_ = _creature.ficheCapaciteActuelle(_import);
        if (ab_ != null) {
            Rate rate_ = ab_.getMaxHpForUsingBerry();
            if (!rate_.isZero()) {
                mult_.affect(rate_);
            } else {
                mult_.affect(_rate);
            }
        } else {
            mult_.affect(_rate);
        }
        return mult_;
    }

    static void enableBerryStatus(Fight _fight, TeamPosition _combattant, boolean _useObject, DataBase _import, Berry _berry) {
        Fighter creature_=_fight.getFighter(_combattant);
        StringList statuts_=new StringList();
        for(String c:creature_.getStatusSet()){
            if(NumberUtil.eq(creature_.getStatusNbRoundShort(c), 0)){
                continue;
            }
            if(StringUtil.contains(_berry.getHealStatus(), c)){
                statuts_.add(c);
                creature_.supprimerStatut(c);
                _fight.addDisabledStatusMessage(c, _combattant, _import);
            }
        }
        if (!statuts_.isEmpty()) {
            useObj(_fight, _combattant, _useObject, _import, creature_);
        }
    }

    static void enableBerryStatistic(Fight _fight, TeamPosition _combattant,
                                     boolean _useObject, boolean _checkCondition, DataBase _import, Berry _berry) {
        Fighter creature_=_fight.getFighter(_combattant);
        for(Statistic c: _berry.getMultStat().getKeys()){
            Rate taux_= _berry.getMultStat().getVal(c).getHpRate();
            if (_checkCondition && Rate.strLower(Rate.multiply(taux_, creature_.pvMax()), creature_.getRemainingHp())) {
                continue;
            }
            byte varBase_= _berry.getMultStat().getVal(c).getBoost();
            byte var_=FightEffects.deltaBoostStatistic(_fight, _combattant,c,varBase_,_import);
            byte boost_ = creature_.getStatisBoost().getVal(c);
            creature_.variationBoostStatistique(c,var_);
            _fight.addStatisticMessage(_combattant, c, var_, _import);
            if (boost_ < creature_.getStatisBoost().getVal(c)) {
                useObj(_fight, _combattant, _useObject, _import, creature_);
            }
        }
    }

    static void enableBerryPp(Fight _fight,TeamPosition _combattant,String _objectName, boolean _useObject, DataBase _import) {
        Fighter creature_=_fight.getFighter(_combattant);
        for(String c:creature_.getCurrentMovesSet()){
            if (creature_.powerPointsMove(c) == 0) {
                short var_ = creature_.healedPpMove(c, _objectName, _import);
                if (var_ > 0) {
                    useObj(_fight, _combattant, _useObject, _import, creature_);
                    creature_.healPowerPoints(c, var_);
                    break;
                }
            }
        }
    }

    private static void useObj(Fight _fight, TeamPosition _combattant, boolean _useObject, DataBase _import, Fighter _cr) {
        if (_useObject) {
            _cr.useObject();
            bonusHp(_fight, _combattant, _import);
        }
    }

    static void enableBerryHpWhileSuperEffectiveMove(Fight _fight,TeamPosition _combattant,
            int _index, DataBase _import) {
        Berry berry_ = useItsBerry(_fight, _combattant, _import);
        if (berry_ == null) {
            return;
        }
        if(berry_.getHealHpBySuperEffMove().isZero()){
            return;
        }
        Fighter creatureCible_=_fight.getFighter(_combattant);
        Rate leftHp_ = new Rate(creatureCible_.getRemainingHp());
        creatureCible_.variationLeftHp(Rate.multiply(berry_.getHealHpBySuperEffMove(),creatureCible_.pvMax()));
        _fight.addHpMessage(_combattant, _import);
        if (!Rate.strGreater(creatureCible_.getRemainingHp(), leftHp_)) {
            return;
        }
        creatureCible_.useObject();
        bonusHp(_fight, _combattant, _import);
        _fight.getSuccessfulEffects().put(new NbEffectFighterCoords(_index,_combattant),BoolVal.FALSE);
    }

    static void bonusHp(Fight _fight,TeamPosition _combattant,DataBase _import) {
        Fighter fighter_ = _fight.getFighter(_combattant);
        AbilityData ab_ = fighter_.ficheCapaciteActuelle(_import);
        if (ab_ != null) {
            fighter_.variationLeftHp(Rate.multiply(ab_.getHealHpWhileUsingBerry(),fighter_.pvMax()));
            _fight.addHpMessage(_combattant, _import);
        }
    }
}
