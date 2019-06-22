package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


final class FightMoves {

    private FightMoves() {
    }

    static StringList enabledGlobalMoves(Fight _fight,DataBase _import) {
        StringList list_ = climatsActifs(_fight, _import);
        list_.addAllElts(enabledGlobalNonWeatherMove(_fight, _import));
        return list_;
    }

    static StringList climatsActifs(Fight _fight,DataBase _import){
        StringList climats_ = new StringList();
        if (existenceAntiClimatActif(_fight, _import)) {
            return climats_;
        }
        for(String c:_fight.getEnabledMoves().getKeys()){
            if(!_fight.getEnabledMoves().getVal(c).isEnabled()){
                continue;
            }
            if(!StringList.contains(_import.getMovesEffectGlobalWeather(), c)){
                continue;
            }
            climats_.add(c);
        }
        return climats_;
    }

    static boolean existenceAntiClimatActif(Fight _fight,DataBase _import){
        for(byte c:_fight.getTeams().getKeys()){
            ByteMap<Fighter> membres_=_fight.getTeams().getVal(c).getMembers();
            for(byte c2_:membres_.getKeys()){
                Fighter membre_=membres_.getVal(c2_);
                if (membre_.canDisableWeather(_import)) {
                    return true;
                }
            }
        }
        return false;
    }

    static StringList enabledGlobalNonWeatherMove(Fight _fight,DataBase _import) {
        StringList list_ = new StringList();
        for(String m:_fight.getEnabledMoves().getKeys()){
            if(!_fight.getEnabledMoves().getVal(m).isEnabled()){
                continue;
            }
            if(StringList.contains(_import.getMovesEffectGlobalWeather(), m)){
                continue;
            }
            list_.add(m);
        }
        return list_;
    }

    /**The result is not empty and valid*/
    static StringList moveTypes(Fight _fight, TeamPosition _lanceur,String _attaque,DataBase _import){
        MoveData fAtt_=_import.getMove(_attaque);
        Fighter creatureCbtLanceur_=_fight.getFighter(_lanceur);
        if(FightItems.canUseItsObject(_fight,_lanceur,_import)){
            String objet_=creatureCbtLanceur_.getItem();
            if(fAtt_.getTypesByOwnedItem().contains(objet_)){
                return new StringList(fAtt_.getTypesByOwnedItem().getVal(objet_));
            }
            if(fAtt_.getTypesByOwnedItem().contains(DataBase.EMPTY_STRING)){
                return new StringList(fAtt_.getTypesByOwnedItem().getVal(DataBase.EMPTY_STRING));
            }
        }
        StringMap<String> typeAttaqueClimat_=fAtt_.getTypesByWeather();
        StringList types_ = new StringList();
        for (String w: climatsActifs(_fight,_import)) {
            if(!typeAttaqueClimat_.contains(w)){
                continue;
            }
            types_.add(typeAttaqueClimat_.getVal(w));
        }
        if (!types_.isEmpty()) {
            types_.removeDuplicates();
            return types_;
        }
        if (typeAttaqueClimat_.contains(DataBase.EMPTY_STRING)) {
            types_.add(typeAttaqueClimat_.getVal(DataBase.EMPTY_STRING));
        }
        if (!types_.isEmpty()) {
            return types_;
        }
        if(!creatureCbtLanceur_.capaciteActive()){
            types_.addAllElts(fAtt_.getTypes());
        } else {
            AbilityData fCapac_=creatureCbtLanceur_.ficheCapaciteActuelle(_import);
            String type_=fCapac_.getTypeForMoves();
            if(!type_.isEmpty()){
                types_.add(type_);
            } else {
                StringList changedTypes_ = new StringList();
                for (String t: fAtt_.getTypes()) {
                    if (!fCapac_.getChangingBoostTypes().contains(t)) {
                        continue;
                    }
                    changedTypes_.add(fCapac_.getChangingBoostTypes().getVal(t).getType());
                }
                changedTypes_.removeDuplicates();
                if (!changedTypes_.isEmpty()) {
                    types_.addAllElts(changedTypes_);
                } else {
                    types_.addAllElts(fAtt_.getTypes());
                }
            }
        }
        for (String m: creatureCbtLanceur_.getEnabledChangingTypesMoves().getKeys()) {
            if (!creatureCbtLanceur_.getEnabledChangingTypesMoves().getVal(m).isEnabled()) {
                continue;
            }
            MoveData moveDta_ = _import.getMove(m);
            for (Effect eff_: moveDta_.getEffects()) {
                if (!(eff_ instanceof EffectSwitchMoveTypes)) {
                    continue;
                }
                EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) eff_;
                if (!effect_.getReplacingTypes().isEmpty()) {
                    types_.clear();
                    types_.addAllElts(effect_.getReplacingTypes());
                }
            }
        }
        for (String m: creatureCbtLanceur_.getEnabledChangingTypesMoves().getKeys()) {
            if (!creatureCbtLanceur_.getEnabledChangingTypesMoves().getVal(m).isEnabled()) {
                continue;
            }
            MoveData moveDta_ = _import.getMove(m);
            for (Effect eff_: moveDta_.getEffects()) {
                if (!(eff_ instanceof EffectSwitchMoveTypes)) {
                    continue;
                }
                EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) eff_;
                StringList newTypes_ = new StringList();
                for (String t: types_) {
                    if (effect_.getChangeTypes().contains(t)) {
                        newTypes_.add(effect_.getChangeTypes().getVal(t));
                    } else {
                        newTypes_.add(t);
                    }
                }
                types_.clear();
                types_.addAllElts(newTypes_);
            }
        }
        types_.removeDuplicates();
        return types_;
    }
}
