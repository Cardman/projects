package aiki.game.fight;
import aiki.db.*;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.util.TypesDuo;
import aiki.game.UsesOfMove;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

final class FightValues {

    private FightValues() {
    }

    static StringMap<String> calculateValuesFighter(Fight _fight,TeamPosition _fighter,DataBase _import){
        _fight.setEnabledMessages(false);
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_fighter.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(_import.prefixFighterGenre(), creatureCbtLanceur_.getCurrentGender().getGenderName());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(_import.prefixNbUtiliAttEqTour(c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(_import.prefixFighterPvRestants(),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(_import.prefixFighterPvMax(),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, new PkVariableSuffixFighterNbUtilisation(_import), _import);
        statBase(creatureCbtLanceur_, variables_, new PkVariableSuffixFighterStatis(_import));
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, new PkVariableSuffixFighterBoost(_import), _import.prefixSommeBoostPosFighter());
        StringList attaquesGlobales_ = _import.getVarParamsMove(_import.nbTourGlobal());
        for(String c:attaquesGlobales_){
            variables_.put(_import.prefixNbTourGlobal(c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(_import.prefixFighterAttaqueChoisie(), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, _import.prefixFighterAttaques(), _import, _fight, _fighter, _import.prefixFighterAttaquesTypes());
        variables_.put(_import.prefixFighterClone(),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, new PkVariableSuffixFighterDegatsRecus(_import), _import.prefixFighterDegatsRecusTotal(), new PkVariableSuffixFighterDegatsRecusTour(_import), _import.prefixFighterDegatsRecusTotalTour());

        stat(_import.prefixFighterDisparait(), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(_import.prefixFighterJoue(), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        if (_fight.getTemp().isComputingArtInt()) {
            addPlayed(_fight, _import, variables_, _import.prefixFighterDerJoue(), null);
        } else {
            addPlayed(_fight, _import, variables_, _import.prefixFighterDerJoue(), _fighter);
        }
        variables_.put(_import.prefixFighterNom(),creatureCbtLanceur_.getCurrentName());
        variables_.put(_import.prefixFighterMasse(),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(_import.prefixFighterTaille(),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(_import.prefixFighterObjet(),creatureCbtLanceur_.getItem());
        variables_.put(_import.prefixFighterCapacite(),creatureCbtLanceur_.getCurrentAbility());
        StringList statutsLanceur_ = new StringList();
        for(String c:creatureCbtLanceur_.getStatusSet()){
            if(!NumberUtil.eq(creatureCbtLanceur_.getStatusNbRoundShort(c), 0)){
                statutsLanceur_.add(c);
            }
        }
        variables_.put(_import.prefixFighterStatuts(), StringUtil.join(statutsLanceur_, _import.getSepartorSetChar()));
        variables_.put(_import.prefixFighterTypes(), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        variables_.put(_import.prefixClimats(), StringUtil.join(FightMoves.climatsActifs(_fight,_import), _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, new PkVariableSuffixFighterPp(_import), variables_);
        equipes(variables_, _fight, _fighter, _import.prefixNbKoEquipeFighter(), _import.prefixNbKoEquipeAdvFighter(), _import);
        variables_.put(_import.prefixLieuCombat(),_fight.getEnvType().getEnvName());
        variables_.put(_import.prefixFighterNiveau(),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(_import.prefixFighterBonheur(),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(_import.prefixTempsTour(),_fight.getNbRounds().toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(_import.prefixNbCombattantsTerrain(),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, new PkVariableSuffixCoeffEffBaseTypesFighter(_import), variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void damage(Fighter _creatureCbtLanceur, StringMap<String> _variables, AbsVariableSuffixArg _degatsRecus, String _degatsRecusTotal, AbsVariableSuffixArg _degatsRecusTour, String _degatsRecusTotalTour) {
        Rate sommeLanceurDegatsSubis_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCateg().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCateg().getVal(c);
            sommeLanceurDegatsSubis_.addNb(deg_);
            _variables.put(_degatsRecus.value(c),deg_.toNumberString());
        }
        _variables.put(_degatsRecusTotal,sommeLanceurDegatsSubis_.toNumberString());
        Rate sommeLanceurDegatsSubisTour_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCategRound().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCategRound().getVal(c);
            sommeLanceurDegatsSubisTour_.addNb(deg_);
            _variables.put(_degatsRecusTour.value(c),deg_.toNumberString());
        }
        _variables.put(_degatsRecusTotalTour,sommeLanceurDegatsSubisTour_.toNumberString());
    }

    private static void enabled(Fighter _creature, StringMap<String> _variables, AbsVariableSuffixArg _nbUtilisation, DataBase _import) {
        for(String c: _creature.getEnabledMoves().getKeys()){
            _variables.put(_import.prefixNbTour(c),Long.toString(_creature.getEnabledMoves().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesProt().getKeys()){
            _variables.put(_import.prefixNbTour(c),Long.toString(_creature.getEnabledMovesProt().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesUnprot().getKeys()){
            _variables.put(_import.prefixNbTour(c),Long.toString(_creature.getEnabledMovesUnprot().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesEndRound().getKeys()){
            _variables.put(_import.prefixNbTour(c),Long.toString(_creature.getEnabledMovesEndRound().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesConstChoices().getKeys()){
            _variables.put(_import.prefixNbTour(c),Long.toString(_creature.getEnabledMovesConstChoices().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getNbUsesMoves().getKeys()){
            _variables.put(_nbUtilisation.value(c),Long.toString(_creature.getNbUsesMoves().getVal(c)));
        }
    }
    private static void moves(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _attaques, DataBase _import, Fight _fight, TeamPosition _fighter, String _attaquesTypes) {
        StringList clesLanceurAttaquesActuelles_= _creatureCbtLanceur.attaquesUtilisables();
        clesLanceurAttaquesActuelles_.sort();
        _variables.put(_attaques, StringUtil.join(clesLanceurAttaquesActuelles_, _import.getSepartorSetChar()));
        StringList lanceurAttaquesActuellesTypes_ = new StringList();
        for(String c:clesLanceurAttaquesActuelles_){
            lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight, _fighter,c, _import));
        }
        lanceurAttaquesActuellesTypes_.removeDuplicates();
        lanceurAttaquesActuellesTypes_.sort();
        _variables.put(_attaquesTypes, StringUtil.join(lanceurAttaquesActuellesTypes_, _import.getSepartorSetChar()));
    }

    private static void sommeLanceurBoostsPositifs(Fighter _creatureCbtLanceur, StringMap<String> _variables, AbsVariableSuffixStatArg _boost, String _sommeBoostPos) {
        Rate sommeLanceurBoostsPositifs_=Rate.zero();
        for(Statistic c: _creatureCbtLanceur.getStatisBoost().getKeys()){
            byte boost_= _creatureCbtLanceur.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeLanceurBoostsPositifs_.addNb(new Rate(boost_));
            }
            _variables.put(_boost.value(c),Long.toString(boost_));
        }
        _variables.put(_sommeBoostPos,sommeLanceurBoostsPositifs_.toNumberString());
    }

    private static void equipes(StringMap<String> _variables, Fight _fight, TeamPosition _fighter, String _nbKoEquipe, String _nbKoEquipeAdv, DataBase _import) {
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        for(String c: equipeLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(_import.prefixEquipeNbUtilisation(c),Long.toString(equipeLanceur_.getNbUsesMoves().getVal(c)));
        }
        Team equipeAdvLanceur_= _fight.getTeams().getVal(Fight.foe(_fighter.getTeam()));
        for(String c:equipeAdvLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(_import.prefixEquipeAdvNbUtilisation(c),Long.toString(equipeAdvLanceur_.getNbUsesMoves().getVal(c)));
        }
        _variables.put(_nbKoEquipe,Long.toString((long) equipeLanceur_.getNbKoRound()+ equipeLanceur_.getNbKoPreviousRound()));
        _variables.put(_nbKoEquipeAdv,Long.toString((long)equipeAdvLanceur_.getNbKoRound()+equipeAdvLanceur_.getNbKoPreviousRound()));
    }

    static void completeValuesWithRemaining(StringMap<String> _variables, Rate _coeffEff, LgInt _nbRepeatingSuccessfulMoves, DataBase _import) {
        _variables.put(_import.prefixCoeffEff(), _coeffEff.toNumberString());
        _variables.put(_import.prefixNbUtilisationConsecutif(), _nbRepeatingSuccessfulMoves.toNumberString());
    }

    static void completeValuesWithMoveInfo(String _attaqueLanceur, StringMap<String> _variables, Rate _basePower, DataBase _import, StringList _typeAtt, String _category) {
        _variables.put(_import.prefixAttaqueCategorie(), _category);
        _variables.put(_import.prefixAttaqueTypes(), StringUtil.join(_typeAtt, _import.getSepartorSetChar()));
        _variables.put(_import.prefixAttaqueNom(), _attaqueLanceur);
        _variables.put(_import.prefixPuissanceBase(), _basePower.toNumberString());
    }

    static void completeValuesWithThrower(Fight _fight, TeamPosition _lanceur, StringMap<String> _variables, DataBase _import) {
        Team equipeLanceur_= _fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureLanceur_=equipeLanceur_.getMembers().getVal(_lanceur.getPosition());
        String nomActuelLanceur_=creatureLanceur_.getCurrentName();
        _variables.put(_import.prefixLanceurNom(), nomActuelLanceur_);
    }

    static StringMap<String> calculateValuesWithStat(StringMap<String> _variables, Rate _att, Rate _def, Rate _finalPower, DataBase _import) {
        StringMap<String> varLocs_ = new StringMap<String>();
        varLocs_.put(_import.prefixAttack(), _att.toNumberString());
        varLocs_.put(_import.prefixDefense(), _def.toNumberString());
        varLocs_.put(_import.prefixPower(), _finalPower.toNumberString());
        varLocs_.putAllMap(_variables);
        return varLocs_;
    }
    static StringMap<String> calculateValues(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        _fight.setEnabledMessages(false);
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter creatureCbtCible_=equipeCible_.getMembers().getVal(_cible.getPosition());
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_lanceur.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(_import.prefixCibleGenre(), creatureCbtCible_.getCurrentGender().getGenderName());
        variables_.put(_import.prefixCiblePvRestants(),creatureCbtCible_.getRemainingHp().toNumberString());
        variables_.put(_import.prefixCiblePvMax(),creatureCbtCible_.pvMax().toNumberString());
        enabled(creatureCbtCible_, variables_, new PkVariableSuffixCibleNbUtilisation(_import), _import);
        statBase(creatureCbtCible_, variables_, new PkVariableSuffixCibleStatis(_import));
        sommeLanceurBoostsPositifs(creatureCbtCible_, variables_, new PkVariableSuffixCibleBoost(_import), _import.prefixSommeBoostPosCible());
        variables_.put(_import.prefixCibleAttaqueChoisie(), creatureCbtCible_.getFinalChosenMove());
        moves(creatureCbtCible_, variables_, _import.prefixCibleAttaques(), _import, _fight, _cible, _import.prefixCibleAttaquesTypes());
        variables_.put(_import.prefixCibleClone(),creatureCbtCible_.getClone().toNumberString());
        damage(creatureCbtCible_, variables_, new PkVariableSuffixCibleDegatsRecus(_import), _import.prefixCibleDegatsRecusTotal(), new PkVariableSuffixCibleDegatsRecusTour(_import), _import.prefixCibleDegatsRecusTotalTour());
        stat(_import.prefixCibleDisparait(), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isDisappeared());
        stat(_import.prefixCibleJoue(), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isActed());
        addPlayed(_fight, _import, variables_, _import.prefixCibleDerJoue(), _cible);
        variables_.put(_import.prefixCibleNom(),creatureCbtCible_.getCurrentName());
        variables_.put(_import.prefixCibleMasse(),creatureCbtCible_.getWeight().toNumberString());
        variables_.put(_import.prefixCibleTaille(),creatureCbtCible_.getHeight().toNumberString());
        variables_.put(_import.prefixCibleCapacite(),creatureCbtCible_.getCurrentAbility());
        variables_.put(_import.prefixCibleObjet(),creatureCbtCible_.getItem());
        statuts(_import, creatureCbtCible_, variables_, _import.prefixCibleStatuts());
        StringList types_ = new StringList(creatureCbtCible_.getTypes());
        types_.sort();
        variables_.put(_import.prefixCibleTypes(), StringUtil.join(types_, _import.getSepartorSetChar()));
        varsPp(_import, creatureCbtCible_, new PkVariableSuffixCiblePp(_import), variables_);
        Team equipeAdvCible_=_fight.getTeams().getVal(Fight.foe(_cible.getTeam()));
        variables_.put(_import.prefixCibleNiveau(),Long.toString(creatureCbtCible_.getLevel()));
        variables_.put(_import.prefixCibleBonheur(),Long.toString(creatureCbtCible_.getHappiness()));
        variables_.put(_import.prefixNbKoEquipeCible(),Long.toString((long)equipeCible_.getNbKoRound()+equipeCible_.getNbKoPreviousRound()));
        variables_.put(_import.prefixNbKoEquipeAdvCible(),Long.toString((long)equipeAdvCible_.getNbKoRound()+equipeAdvCible_.getNbKoPreviousRound()));
        rateEff(_import, new PkVariableSuffixCoeffEffBaseTypesCible(_import), variables_, creatureCbtCible_);
        variables_.put(_import.prefixLanceurGenre(), creatureCbtLanceur_.getCurrentGender().getGenderName());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(_import.prefixNbUtiliAttEqTour(c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(_import.prefixLanceurPvRestants(),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(_import.prefixLanceurPvMax(),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, new PkVariableSuffixLanceurNbUtilisation(_import), _import);
        statBase(creatureCbtLanceur_, variables_, new PkVariableSuffixLanceurStatis(_import));
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, new PkVariableSuffixLanceurBoost(_import), _import.prefixSommeBoostPosLanceur());
        StringList attaquesGlobales_ = _import.getVarParamsMove(_import.nbTourGlobal());
        for(String c:attaquesGlobales_){
            variables_.put(_import.prefixNbTourGlobal(c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(_import.prefixLanceurAttaqueChoisie(), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, _import.prefixLanceurAttaques(), _import, _fight, _lanceur, _import.prefixLanceurAttaquesTypes());
        variables_.put(_import.prefixLanceurClone(),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, new PkVariableSuffixLanceurDegatsRecus(_import), _import.prefixLanceurDegatsRecusTotal(), new PkVariableSuffixLanceurDegatsRecusTour(_import), _import.prefixLanceurDegatsRecusTotalTour());

        stat(_import.prefixLanceurDisparait(), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(_import.prefixLanceurJoue(), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        addPlayed(_fight, _import, variables_, _import.prefixLanceurDerJoue(), _lanceur);
        variables_.put(_import.prefixLanceurNom(),creatureCbtLanceur_.getCurrentName());
        variables_.put(_import.prefixLanceurMasse(),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(_import.prefixLanceurTaille(),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(_import.prefixLanceurCapacite(),creatureCbtLanceur_.getCurrentAbility());
        variables_.put(_import.prefixLanceurObjet(),creatureCbtLanceur_.getItem());
        statuts(_import, creatureCbtLanceur_, variables_, _import.prefixLanceurStatuts());
        types_ = new StringList(creatureCbtLanceur_.getTypes());
        types_.sort();
        variables_.put(_import.prefixLanceurTypes(), StringUtil.join(types_, _import.getSepartorSetChar()));
        StringList weathers_ = FightMoves.climatsActifs(_fight,_import);
        weathers_.sort();
        variables_.put(_import.prefixClimats(), StringUtil.join(weathers_, _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, new PkVariableSuffixLanceurPp(_import), variables_);
        equipes(variables_, _fight, _lanceur, _import.prefixNbKoEquipeLanceur(),  _import.prefixNbKoEquipeAdvLanceur(), _import);
        variables_.put(_import.prefixLieuCombat(),_fight.getEnvType().getEnvName());
        variables_.put(_import.prefixLanceurNiveau(),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(_import.prefixLanceurBonheur(),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(_import.prefixTempsTour(),_fight.getNbRounds().toNumberString());
        variables_.put(_import.prefixRateEffMoveAgainstTarget(),FightSuccess.rateEffAgainstTargetMove(_fight,_lanceur, _cible, _import).toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(_import.prefixNbCombattantsTerrain(),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, new PkVariableSuffixCoeffEffBaseTypesLanceur(_import), variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void statBase(Fighter _creature, StringMap<String> _variables, AbsVariableSuffixStatArg _key) {
        for(Statistic c: _creature.getStatisBase().getKeys()){
            _variables.put(_key.value(c), _creature.getStatisBase().getVal(c).toNumberString());
        }
    }

    private static void statuts(DataBase _import, Fighter _creature, StringMap<String> _variables, String _cibleStatuts) {
        StringList statutsCible_ = new StringList();
        for(String c: _creature.getStatusSet()){
            if(!NumberUtil.eq(_creature.getStatusNbRoundShort(c), 0)){
                statutsCible_.add(c);
            }
        }
        for (MoveTeamPosition s: _creature.getStatusRelatSet()) {
            if(NumberUtil.eq(_creature.getStatusRelatNbRoundShort(s), 0)){
                continue;
            }
            statutsCible_.add(s.getMove());
        }
        statutsCible_.removeDuplicates();
        statutsCible_.sort();
        _variables.put(_cibleStatuts, StringUtil.join(statutsCible_, _import.getSepartorSetChar()));
    }

    private static void rateEff(DataBase _import, AbsVariableSuffix _coeffEffBaseTypes, StringMap<String> _variables, Fighter _creature) {
        StringList coeffTypes_ = _import.getVarParamsMove(_coeffEffBaseTypes.value());
        for(String e:coeffTypes_){
            Rate coeffCible_=DataBase.defRateProduct();
            for(String e2_: _creature.getTypes()){
                coeffCible_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            _variables.put(_coeffEffBaseTypes.value(e),coeffCible_.toNumberString());
        }
    }

    private static void varsPp(DataBase _import, Fighter _creatureCbtLanceur, AbsVariableSuffix _key, StringMap<String> _variables) {
        StringList attaquesPp_ = _import.getVarParamsMove(_key.value());
        for(String c:attaquesPp_){
            if(!StringUtil.contains(_creatureCbtLanceur.attaquesUtilisables(), c)){
                _variables.put(_key.value(c),Fight.ZERO);
            }else{
                short ppActuel_= _creatureCbtLanceur.powerPointsMove(c);
                _variables.put(_key.value(c),Long.toString(ppActuel_));
            }
        }
    }

    private static int nbCombattantsTerrain(Fight _fight) {
        int nbCombattantsTerrain_=0;
        for(byte c: _fight.getTeams().getKeys()){
            for(byte c2_: _fight.getTeams().getVal(c).getMembers().getKeys()){
                Fighter cbtEquipe_= _fight.getTeams().getVal(c).getMembers().getVal(c2_);
                if(cbtEquipe_.estKo()){
                    continue;
                }
                if(!cbtEquipe_.estArriere()){
                    nbCombattantsTerrain_++;
                }
            }
        }
        return nbCombattantsTerrain_;
    }

    static StringMap<String> calculateBooleanValues(
            Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible,
            String _move,int _noEffet,DataBase _import) {
        _fight.setEnabledMessages(false);
        Effect effet_ = _import.getMove(_move).getEffet(_noEffet);
        StringMap<String> variables_ = new StringMap<String>();
        StringList immuTypesIndiv_ = _import.getVarParamsMove(_import.immuTypeAttCombattantEntrant());
        if(effet_ instanceof EffectTeamWhileSendFoe){
            for(String e:immuTypesIndiv_){
                statBool(_import, _import.prefixImmuTypeAttCombattantEntrant(e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
            }
        }
        statBool(_import, _import.prefixPasAttaqueInvoc(), variables_, effet_ instanceof EffectInvoke && FightInvoke.invokableMoves(_fight, _lanceur, _cible, ((EffectInvoke) effet_), _import).isEmpty());
        statBool(_import, _import.prefixPasAttaquesCopiables(), variables_, effet_ instanceof EffectCopyMove && FightInvoke.copiableMoves(_fight, _lanceur, _cible, ((EffectCopyMove) effet_), _import).isEmpty());
        variables_.putAllMap(calculateBasicBooleanValues(_fight,_lanceur,_cible,_import));
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void statBool(DataBase _import, String _key, StringMap<String> _variables, boolean _condition) {
        stat(_key, _variables, _import.getTrueString(), _import.getFalseString(), _condition);
    }

    private static void stat(String _key, StringMap<String> _variables, String _trueStr, String _falseStr, boolean _condition) {
        if (_condition) {
            _variables.put(_key, _trueStr);
        } else {
            _variables.put(_key, _falseStr);
        }
    }

    static StringMap<String> calculateBasicBooleanValues(
            Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible,
            DataBase _import) {
        _fight.setEnabledMessages(false);
        Fighter creatureCbtCible_ = _fight.getFighter(_cible);
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        StringMap<String> variables_ = new StringMap<String>();
        statusCible(_import, creatureCbtCible_, variables_);
        enaCond(_import, creatureCbtCible_, variables_, new PkVariableSuffixCibleEffet(_import));
        statBool(_import, _import.prefixPasPpAttaqueCible(), variables_, creatureCbtCible_.noPowerPointForLastUsedMove());
        boolean pasUtiliseAttaqueCible_=true;
        for(String c:creatureCbtCible_.getCurrentMovesSet()){
            UsesOfMove pps_=creatureCbtCible_.getCurrentMove(c);
            if(pps_.getCurrent()<pps_.getMax()){
                pasUtiliseAttaqueCible_=false;
                break;
            }
        }
        statBool(_import, _import.prefixPasUtilisAttaqueCible(), variables_, pasUtiliseAttaqueCible_);
        StringList immuTypesIndiv_ = _import.getVarParamsMove(_import.immuTypeAttCible());
        for(String e:immuTypesIndiv_){
            statBool(_import, _import.prefixImmuTypeAttCible(e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
        }
        boolean aucunBoostPossible_ = aucunBoostPossible(_import, creatureCbtCible_);
        statBool(_import, _import.prefixAucunBoostPossible(), variables_, aucunBoostPossible_);
        enaCond(_import, creatureCbtLanceur_, variables_, new PkVariableSuffixLanceurEffet(_import));
        statBool(_import,  _import.prefixTypesAttaquesResVide(), variables_, creatureCbtLanceur_.resistingTypes(_import).isEmpty());
        int nbPartenairesArriere_ = FightOrder.nbBackPartners(_fight, _lanceur);
        int nbPartenairesTerrain_ = FightOrder.nbFrontPartners(_fight, _lanceur);
        int nbPartenaires_ = nbPartenairesArriere_ + nbPartenairesTerrain_;
        statBool(_import, _import.prefixPasPartenaire(), variables_, nbPartenaires_==0);
        statBool(_import, _import.prefixPasPartenaireArriere(), variables_, nbPartenairesArriere_==0);
        statBool(_import, _import.prefixPasPartenaireTerrain(), variables_, nbPartenairesTerrain_==0);
        statBool(_import, _import.prefixPasTourTerrain(), variables_, creatureCbtLanceur_.getNbRounds().isZero());
        statBool(_import, _import.prefixExisteGenreAssexue(), variables_, creatureCbtLanceur_.estAssexue()||creatureCbtCible_.estAssexue());
        statBool(_import, _import.prefixGenresEgaux(), variables_, creatureCbtLanceur_.getCurrentGender()==creatureCbtCible_.getCurrentGender());
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void statusCible(DataBase _import, Fighter _creatureCbtCible, StringMap<String> _variables) {
        StringMap<BoolVal> enabled_ = new StringMap<BoolVal>();
        StringList moves_ = new StringList();
        for (MoveTeamPosition s: _creatureCbtCible.getStatusRelatSet()) {
            moves_.add(s.getMove());
        }
        moves_.removeDuplicates();
        for (String m: moves_) {
            BoolVal enabledLoc_ = BoolVal.FALSE;
            for (MoveTeamPosition s: _creatureCbtCible.getStatusRelatSet()) {
                if (StringUtil.quickEq(s.getMove(), m) && _creatureCbtCible.getStatusRelatNbRoundShort(s) > 0) {
                    enabledLoc_ = BoolVal.TRUE;
                    break;
                }
            }
            enabled_.put(m, enabledLoc_);
        }
        for (String m: enabled_.getKeys()) {
            statBool(_import, _import.prefixCiblePossedeStatutRelation(m), _variables, enabled_.getVal(m) == BoolVal.TRUE);
        }
    }

    private static void enaCond(DataBase _import, Fighter _creature, StringMap<String> _variables, AbsVariableSuffix _keyPart) {
        StringList effetsIndiv_ = _import.getVarParamsMove(_keyPart.value());
        for(String c:effetsIndiv_){
            if (_creature.getEnabledMoves().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMoves().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesProt().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMovesProt().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesUnprot().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMovesUnprot().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesEndRound().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMovesEndRound().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesForAlly().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMovesForAlly().getVal(c) == BoolVal.TRUE);
            } else if (_creature.getEnabledMovesConstChoices().contains(c)) {
                statBool(_import, _keyPart.value(c), _variables, _creature.getEnabledMovesConstChoices().getVal(c).isEnabled());
            } else if (StringUtil.contains(_import.getMovesActingMoveUses(), c)) {
                boolean enabledMove_ = enabledMove(_creature, c);
                statBool(_import, _keyPart.value(c), _variables, enabledMove_);
            } else {
                statBool(_import, _keyPart.value(c), _variables, StringUtil.quickEq(_creature.getLastSuccessfulMove(), c) && _creature.isSuccessfulMove());
            }
        }
    }

    private static boolean enabledMove(Fighter _creatureCbtLanceur, String _c) {
        boolean enabledMove_ = false;
        for (MoveTeamPosition m: _creatureCbtLanceur.getTrackingMoves().getKeys()) {
            if (StringUtil.quickEq(m.getMove(), _c) && _creatureCbtLanceur.getTrackingMoves().getVal(m).getActivity().isEnabled()) {
                enabledMove_ = true;
            }
        }
        return enabledMove_;
    }

    private static boolean aucunBoostPossible(DataBase _import, Fighter _creatureCbtCible) {
        boolean aucunBoostPossible_=true;
        byte maxBoost_=(byte) _import.getMaxBoost();
        for(Statistic c: _creatureCbtCible.getStatisBoost().getKeys()){
            byte boost_= _creatureCbtCible.getStatisBoost().getVal(c);
            if(boost_<maxBoost_){
                aucunBoostPossible_=false;
                break;
            }
        }
        return aucunBoostPossible_;
    }

    static StringMap<String> calculateSendingVariables(
            Fight _fight,
            TeamPosition _lanceur,DataBase _import) {
        _fight.setEnabledMessages(false);
        StringMap<String> variables_ = new StringMap<String>();
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        Team equipeAdvCbtEnvoye_=_fight.getTeams().getVal(Fight.foe(_lanceur.getTeam()));
        StringMap<LgInt> nbUtilisationsEntreeAdv_=equipeAdvCbtEnvoye_.getEnabledMovesWhileSendingFoeUses();
        variables_.put(_import.prefixCombattantEntrantClone(),creatureCbtLanceur_.getClone().toNumberString());
        variables_.put(_import.prefixCombattantEntrantTypes(), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        rateEff(_import, new PkVariableSuffixCoeffEffBaseTypesCombattantEntrant(_import), variables_, creatureCbtLanceur_);
        StringList nbUtAdv_=_import.getVarParamsMove(_import.equipeAdvCombattantEntrantNbUtilisation());
        for(String e:nbUtAdv_){
            variables_.put(_import.prefixEquipeAdvCombattantEntrantNbUtilisation(e),nbUtilisationsEntreeAdv_.getVal(e).toNumberString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void addPlayed(Fight _fight, DataBase _import, StringMap<String> _variables, String _fighterDerJoue, TeamPosition _tp) {
        CustList<TeamPosition> cbts_ = FightOrder.fightersHavingToAct(_fight, true, _import);
        cbts_.addAllElts(FightOrder.fightersHavingToAct(_fight, false, _import));
        boolean onlyOne_;
        if (cbts_.isEmpty()) {
            onlyOne_ = false;
        } else {
            onlyOne_ = onlyOne(cbts_) && (_tp == null || cbts_.get(0).eq(_tp));
        }
        stat(_fighterDerJoue, _variables, Fight.ONE, Fight.ZERO, onlyOne_);
    }

    static boolean onlyOne(CustList<TeamPosition> _cbts) {
        boolean onlyOne_;
        onlyOne_ = true;
        TeamPosition e_ = _cbts.first();
        for (int i = 1; i < _cbts.size(); i++) {
            if (!e_.eq(_cbts.get(i))) {
                onlyOne_ = false;
                break;
            }
        }
        return onlyOne_;
    }
}
