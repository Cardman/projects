package aiki.game.fight;
import aiki.db.DataBase;
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
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _fight.setEnabledMessages(false);
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_fighter.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(StringUtil.concat(varPref_, _import.fighterGenre()), creatureCbtLanceur_.getCurrentGender().getGenderName());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(StringUtil.concat(varPref_, _import.nbUtiliAttEqTour(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(StringUtil.concat(varPref_, _import.fighterPvRestants()),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.fighterPvMax()),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, _import.fighterNbUtilisation(), _import);
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(varPref_, _import.fighterStatis(),DataBase.SEP_BETWEEN_KEYS,c.getStatName()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, _import.fighterBoost(), _import.sommeBoostPosFighter(), _import);
        StringList attaquesGlobales_ = _import.getVarParamsMove(_import.nbTourGlobal());
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(varPref_, _import.nbTourGlobal(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(StringUtil.concat(varPref_, _import.fighterAttaqueChoisie()), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, _import.fighterAttaques(), _import, _fight, _fighter, _import.fighterAttaquesTypes());
        variables_.put(StringUtil.concat(varPref_, _import.fighterClone()),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, _import.fighterDegatsRecus(), _import.fighterDegatsRecusTotal(), _import.fighterDegatsRecusTour(), _import.fighterDegatsRecusTotalTour(), _import);

        stat(StringUtil.concat(varPref_, _import.fighterDisparait()), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(StringUtil.concat(varPref_, _import.fighterJoue()), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        if (_fight.getTemp().isComputingArtInt()) {
            addPlayed(_fight, _import, variables_, _import.fighterDerJoue(), null);
        } else {
            addPlayed(_fight, _import, variables_, _import.fighterDerJoue(), _fighter);
        }
        variables_.put(StringUtil.concat(varPref_, _import.fighterNom()),creatureCbtLanceur_.getCurrentName());
        variables_.put(StringUtil.concat(varPref_, _import.fighterMasse()),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.fighterTaille()),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.fighterObjet()),creatureCbtLanceur_.getItem());
        variables_.put(StringUtil.concat(varPref_, _import.fighterCapacite()),creatureCbtLanceur_.getCurrentAbility());
        StringList statutsLanceur_ = new StringList();
        for(String c:creatureCbtLanceur_.getStatusSet()){
            if(!NumberUtil.eq(creatureCbtLanceur_.getStatusNbRoundShort(c), 0)){
                statutsLanceur_.add(c);
            }
        }
        variables_.put(StringUtil.concat(varPref_, _import.fighterStatuts()), StringUtil.join(statutsLanceur_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(varPref_, _import.fighterTypes()), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_CLIMATS), StringUtil.join(FightMoves.climatsActifs(_fight,_import), _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, _import.fighterPp(), variables_);
        equipes(variables_, _fight, _fighter, _import.nbKoEquipeFighter(), _import.nbKoEquipeAdvFighter(), _import);
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_LIEU_COMBAT),_fight.getEnvType().getEnvName());
        variables_.put(StringUtil.concat(varPref_,_import.fighterNiveau()),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(varPref_, _import.fighterBonheur()),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, _import.coeffEffBaseTypesFighter(), variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void damage(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _degatsRecus, String _degatsRecusTotal, String _degatsRecusTour, String _degatsRecusTotalTour, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        Rate sommeLanceurDegatsSubis_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCateg().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCateg().getVal(c);
            sommeLanceurDegatsSubis_.addNb(deg_);
            _variables.put(StringUtil.concat(varPref_, _degatsRecus,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        _variables.put(StringUtil.concat(varPref_, _degatsRecusTotal),sommeLanceurDegatsSubis_.toNumberString());
        Rate sommeLanceurDegatsSubisTour_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCategRound().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCategRound().getVal(c);
            sommeLanceurDegatsSubisTour_.addNb(deg_);
            _variables.put(StringUtil.concat(varPref_, _degatsRecusTour,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        _variables.put(StringUtil.concat(varPref_, _degatsRecusTotalTour),sommeLanceurDegatsSubisTour_.toNumberString());
    }

    private static void enabled(Fighter _creature, StringMap<String> _variables, String _nbUtilisation, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        for(String c: _creature.getEnabledMoves().getKeys()){
            _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMoves().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesProt().getKeys()){
            _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesProt().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesUnprot().getKeys()){
            _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesUnprot().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesEndRound().getKeys()){
            _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesEndRound().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesConstChoices().getKeys()){
            _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesConstChoices().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(varPref_, _nbUtilisation,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getNbUsesMoves().getVal(c)));
        }
    }

    private static void moves(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _attaques, DataBase _import, Fight _fight, TeamPosition _fighter, String _attaquesTypes) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringList clesLanceurAttaquesActuelles_= _creatureCbtLanceur.attaquesUtilisables();
        clesLanceurAttaquesActuelles_.sort();
        _variables.put(StringUtil.concat(varPref_, _attaques), StringUtil.join(clesLanceurAttaquesActuelles_, _import.getSepartorSetChar()));
        StringList lanceurAttaquesActuellesTypes_ = new StringList();
        for(String c:clesLanceurAttaquesActuelles_){
            lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight, _fighter,c, _import));
        }
        lanceurAttaquesActuellesTypes_.removeDuplicates();
        lanceurAttaquesActuellesTypes_.sort();
        _variables.put(StringUtil.concat(varPref_, _attaquesTypes), StringUtil.join(lanceurAttaquesActuellesTypes_, _import.getSepartorSetChar()));
    }

    private static void sommeLanceurBoostsPositifs(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _boost, String _sommeBoostPos, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        Rate sommeLanceurBoostsPositifs_=Rate.zero();
        for(Statistic c: _creatureCbtLanceur.getStatisBoost().getKeys()){
            byte boost_= _creatureCbtLanceur.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeLanceurBoostsPositifs_.addNb(new Rate(boost_));
            }
            _variables.put(StringUtil.concat(varPref_, _boost,DataBase.SEP_BETWEEN_KEYS,c.getStatName()),Long.toString(boost_));
        }
        _variables.put(StringUtil.concat(varPref_, _sommeBoostPos),sommeLanceurBoostsPositifs_.toNumberString());
    }

    private static void equipes(StringMap<String> _variables, Fight _fight, TeamPosition _fighter, String _nbKoEquipe, String _nbKoEquipeAdv, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        for(String c: equipeLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(varPref_, _import.equipeNbUtilisation(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMoves().getVal(c)));
        }
        Team equipeAdvLanceur_= _fight.getTeams().getVal(Fight.foe(_fighter.getTeam()));
        for(String c:equipeAdvLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(varPref_, _import.equipeAdvNbUtilisation(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeAdvLanceur_.getNbUsesMoves().getVal(c)));
        }
        _variables.put(StringUtil.concat(varPref_, _nbKoEquipe),Long.toString((long) equipeLanceur_.getNbKoRound()+ equipeLanceur_.getNbKoPreviousRound()));
        _variables.put(StringUtil.concat(varPref_, _nbKoEquipeAdv),Long.toString((long)equipeAdvLanceur_.getNbKoRound()+equipeAdvLanceur_.getNbKoPreviousRound()));
    }

    static void completeValuesWithRemaining(StringMap<String> _variables, Rate _coeffEff, LgInt _nbRepeatingSuccessfulMoves, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_COEFF_EFF), _coeffEff.toNumberString());
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_NB_UTILISATION_CONSECUTIF), _nbRepeatingSuccessfulMoves.toNumberString());
    }

    static void completeValuesWithMoveInfo(String _attaqueLanceur, StringMap<String> _variables, Rate _basePower, DataBase _import, StringList _typeAtt, String _category) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_ATTAQUE_CATEGORIE), _category);
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_ATTAQUE_TYPES), StringUtil.join(_typeAtt, _import.getSepartorSetChar()));
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_ATTAQUE_NOM), _attaqueLanceur);
        _variables.put(StringUtil.concat(varPref_, DataBase.DEF_PUISSANCE_BASE), _basePower.toNumberString());
    }

    static void completeValuesWithThrower(Fight _fight, TeamPosition _lanceur, StringMap<String> _variables, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        Team equipeLanceur_= _fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureLanceur_=equipeLanceur_.getMembers().getVal(_lanceur.getPosition());
        String nomActuelLanceur_=creatureLanceur_.getCurrentName();
        _variables.put(StringUtil.concat(varPref_, _import.lanceurNom()), nomActuelLanceur_);
    }

    static StringMap<String> calculateValuesWithStat(StringMap<String> _variables, Rate _att, Rate _def, Rate _finalPower, DataBase _import) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringMap<String> varLocs_ = new StringMap<String>();
        varLocs_.put(StringUtil.concat(varPref_, _import.attack()), _att.toNumberString());
        varLocs_.put(StringUtil.concat(varPref_, _import.defense()), _def.toNumberString());
        varLocs_.put(StringUtil.concat(varPref_, _import.power()), _finalPower.toNumberString());
        varLocs_.putAllMap(_variables);
        return varLocs_;
    }
    static StringMap<String> calculateValues(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _fight.setEnabledMessages(false);
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter creatureCbtCible_=equipeCible_.getMembers().getVal(_cible.getPosition());
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_lanceur.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(StringUtil.concat(varPref_, _import.cibleGenre()), creatureCbtCible_.getCurrentGender().getGenderName());
        variables_.put(StringUtil.concat(varPref_, _import.ciblePvRestants()),creatureCbtCible_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.ciblePvMax()),creatureCbtCible_.pvMax().toNumberString());
        enabled(creatureCbtCible_, variables_, _import.cibleNbUtilisation(), _import);
        for(Statistic c:creatureCbtCible_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(varPref_, _import.cibleStatis(),DataBase.SEP_BETWEEN_KEYS,c.getStatName()),creatureCbtCible_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtCible_, variables_, _import.cibleBoost(), _import.sommeBoostPosCible(), _import);
        variables_.put(StringUtil.concat(varPref_, _import.cibleAttaqueChoisie()), creatureCbtCible_.getFinalChosenMove());
        moves(creatureCbtCible_, variables_, _import.cibleAttaques(), _import, _fight, _cible, _import.cibleAttaquesTypes());
        variables_.put(StringUtil.concat(varPref_, _import.cibleClone()),creatureCbtCible_.getClone().toNumberString());
        damage(creatureCbtCible_, variables_, _import.cibleDegatsRecus(), _import.cibleDegatsRecusTotal(), _import.cibleDegatsRecusTour(), _import.cibleDegatsRecusTotalTour(), _import);
        stat(StringUtil.concat(varPref_, _import.cibleDisparait()), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isDisappeared());
        stat(StringUtil.concat(varPref_, _import.cibleJoue()), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isActed());
        addPlayed(_fight, _import, variables_, _import.cibleDerJoue(), _cible);
        variables_.put(StringUtil.concat(varPref_, _import.cibleNom()),creatureCbtCible_.getCurrentName());
        variables_.put(StringUtil.concat(varPref_, _import.cibleMasse()),creatureCbtCible_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.cibleTaille()),creatureCbtCible_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.cibleCapacite()),creatureCbtCible_.getCurrentAbility());
        variables_.put(StringUtil.concat(varPref_, _import.cibleObjet()),creatureCbtCible_.getItem());
        statuts(_import, creatureCbtCible_, variables_, _import.cibleStatuts());
        StringList types_ = new StringList(creatureCbtCible_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(varPref_, _import.cibleTypes()), StringUtil.join(types_, _import.getSepartorSetChar()));
        varsPp(_import, creatureCbtCible_, _import.ciblePp(), variables_);
        Team equipeAdvCible_=_fight.getTeams().getVal(Fight.foe(_cible.getTeam()));
        variables_.put(StringUtil.concat(varPref_,_import.cibleNiveau()),Long.toString(creatureCbtCible_.getLevel()));
        variables_.put(StringUtil.concat(varPref_,_import.cibleBonheur()),Long.toString(creatureCbtCible_.getHappiness()));
        variables_.put(StringUtil.concat(varPref_, _import.nbKoEquipeCible()),Long.toString((long)equipeCible_.getNbKoRound()+equipeCible_.getNbKoPreviousRound()));
        variables_.put(StringUtil.concat(varPref_, _import.nbKoEquipeAdvCible()),Long.toString((long)equipeAdvCible_.getNbKoRound()+equipeAdvCible_.getNbKoPreviousRound()));
        rateEff(_import, _import.coeffEffBaseTypesCible(), variables_, creatureCbtCible_);
        variables_.put(StringUtil.concat(varPref_, _import.lanceurGenre()), creatureCbtLanceur_.getCurrentGender().getGenderName());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(StringUtil.concat(varPref_, _import.nbUtiliAttEqTour(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(StringUtil.concat(varPref_, _import.lanceurPvRestants()),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurPvMax()),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, _import.lanceurNbUtilisation(), _import);
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(varPref_, _import.lanceurStatis(),DataBase.SEP_BETWEEN_KEYS,c.getStatName()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, _import.lanceurBoost(), _import.sommeBoostPosLanceur(), _import);
        StringList attaquesGlobales_ = _import.getVarParamsMove(_import.nbTourGlobal());
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(varPref_, _import.nbTourGlobal(),DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(StringUtil.concat(varPref_, _import.lanceurAttaqueChoisie()), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, _import.lanceurAttaques(), _import, _fight, _lanceur, _import.lanceurAttaquesTypes());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurClone()),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, _import.lanceurDegatsRecus(), _import.lanceurDegatsRecusTotal(), _import.lanceurDegatsRecusTour(), _import.lanceurDegatsRecusTotalTour(), _import);

        stat(StringUtil.concat(varPref_, _import.lanceurDisparait()), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(StringUtil.concat(varPref_, _import.lanceurJoue()), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        addPlayed(_fight, _import, variables_, _import.lanceurDerJoue(), _lanceur);
        variables_.put(StringUtil.concat(varPref_, _import.lanceurNom()),creatureCbtLanceur_.getCurrentName());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurMasse()),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurTaille()),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurCapacite()),creatureCbtLanceur_.getCurrentAbility());
        variables_.put(StringUtil.concat(varPref_, _import.lanceurObjet()),creatureCbtLanceur_.getItem());
        statuts(_import, creatureCbtLanceur_, variables_, _import.lanceurStatuts());
        types_ = new StringList(creatureCbtLanceur_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(varPref_, _import.lanceurTypes()), StringUtil.join(types_, _import.getSepartorSetChar()));
        StringList weathers_ = FightMoves.climatsActifs(_fight,_import);
        weathers_.sort();
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_CLIMATS), StringUtil.join(weathers_, _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, _import.lanceurPp(), variables_);
        equipes(variables_, _fight, _lanceur, _import.nbKoEquipeLanceur(),  _import.nbKoEquipeAdvLanceur(), _import);
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_LIEU_COMBAT),_fight.getEnvType().getEnvName());
        variables_.put(StringUtil.concat(varPref_,_import.lanceurNiveau()),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(varPref_,_import.lanceurBonheur()),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_RATE_EFF_MOVE_AGAINST_TARGET),FightSuccess.rateEffAgainstTargetMove(_fight,_lanceur, _cible, _import).toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, _import.coeffEffBaseTypesLanceur(), variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void statuts(DataBase _import, Fighter _creature, StringMap<String> _variables, String _cibleStatuts) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
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
        _variables.put(StringUtil.concat(varPref_, _cibleStatuts), StringUtil.join(statutsCible_, _import.getSepartorSetChar()));
    }

    private static void rateEff(DataBase _import, String _coeffEffBaseTypes, StringMap<String> _variables, Fighter _creature) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringList coeffTypes_ = _import.getVarParamsMove(_coeffEffBaseTypes);
        for(String e:coeffTypes_){
            Rate coeffCible_=DataBase.defRateProduct();
            for(String e2_: _creature.getTypes()){
                coeffCible_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            _variables.put(StringUtil.concat(varPref_, _coeffEffBaseTypes,DataBase.SEP_BETWEEN_KEYS,e),coeffCible_.toNumberString());
        }
    }

    private static void varsPp(DataBase _import, Fighter _creatureCbtLanceur, String _key, StringMap<String> _variables) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringList attaquesPp_ = _import.getVarParamsMove(_key);
        for(String c:attaquesPp_){
            if(!StringUtil.contains(_creatureCbtLanceur.attaquesUtilisables(), c)){
                _variables.put(StringUtil.concat(varPref_, _key,DataBase.SEP_BETWEEN_KEYS,c),Fight.ZERO);
            }else{
                short ppActuel_= _creatureCbtLanceur.powerPointsMove(c);
                _variables.put(StringUtil.concat(varPref_, _key,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(ppActuel_));
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
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _fight.setEnabledMessages(false);
        Effect effet_ = _import.getMove(_move).getEffet(_noEffet);
        StringMap<String> variables_ = new StringMap<String>();
        StringList immuTypesIndiv_ = _import.getVarParamsMove(_import.immuTypeAttCombattantEntrant());
        if(effet_ instanceof EffectTeamWhileSendFoe){
            for(String e:immuTypesIndiv_){
                statBool(_import, StringUtil.concat(varPref_, _import.immuTypeAttCombattantEntrant(),DataBase.SEP_BETWEEN_KEYS,e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
            }
        }
        statBool(_import, StringUtil.concat(varPref_, DataBase.DEF_PAS_ATTAQUE_INVOC), variables_, effet_ instanceof EffectInvoke && FightInvoke.invokableMoves(_fight, _lanceur, _cible, ((EffectInvoke) effet_), _import).isEmpty());
        statBool(_import, StringUtil.concat(varPref_, DataBase.DEF_PAS_ATTAQUES_COPIABLES), variables_, effet_ instanceof EffectCopyMove && FightInvoke.copiableMoves(_fight, _lanceur, _cible, ((EffectCopyMove) effet_), _import).isEmpty());
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
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _fight.setEnabledMessages(false);
        Fighter creatureCbtCible_ = _fight.getFighter(_cible);
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        StringMap<String> variables_ = new StringMap<String>();
        statusCible(_import, creatureCbtCible_, variables_);
        enaCond(_import, creatureCbtCible_, variables_, _import.cibleEffet());
        statBool(_import, StringUtil.concat(varPref_, _import.pasPpAttaqueCible()), variables_, creatureCbtCible_.noPowerPointForLastUsedMove());
        boolean pasUtiliseAttaqueCible_=true;
        for(String c:creatureCbtCible_.getCurrentMovesSet()){
            UsesOfMove pps_=creatureCbtCible_.getCurrentMove(c);
            if(pps_.getCurrent()<pps_.getMax()){
                pasUtiliseAttaqueCible_=false;
                break;
            }
        }
        statBool(_import, StringUtil.concat(varPref_, _import.pasUtilisAttaqueCible()), variables_, pasUtiliseAttaqueCible_);
        StringList immuTypesIndiv_ = _import.getVarParamsMove(_import.immuTypeAttCible());
        for(String e:immuTypesIndiv_){
            statBool(_import, StringUtil.concat(varPref_, _import.immuTypeAttCible(),DataBase.SEP_BETWEEN_KEYS,e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
        }
        boolean aucunBoostPossible_ = aucunBoostPossible(_import, creatureCbtCible_);
        statBool(_import, StringUtil.concat(varPref_, _import.aucunBoostPossible()), variables_, aucunBoostPossible_);
        enaCond(_import, creatureCbtLanceur_, variables_, _import.lanceurEffet());
        statBool(_import, StringUtil.concat(varPref_, _import.typesAttaquesResVide()), variables_, creatureCbtLanceur_.resistingTypes(_import).isEmpty());
        int nbPartenairesArriere_ = FightOrder.nbBackPartners(_fight, _lanceur);
        int nbPartenairesTerrain_ = FightOrder.nbFrontPartners(_fight, _lanceur);
        int nbPartenaires_ = nbPartenairesArriere_ + nbPartenairesTerrain_;
        statBool(_import, StringUtil.concat(varPref_, _import.pasPartenaire()), variables_, nbPartenaires_==0);
        statBool(_import, StringUtil.concat(varPref_, _import.pasPartenaireArriere()), variables_, nbPartenairesArriere_==0);
        statBool(_import, StringUtil.concat(varPref_, _import.pasPartenaireTerrain()), variables_, nbPartenairesTerrain_==0);
        statBool(_import, StringUtil.concat(varPref_, _import.pasTourTerrain()), variables_, creatureCbtLanceur_.getNbRounds().isZero());
        statBool(_import, StringUtil.concat(varPref_, _import.existeGenreAssexue()), variables_, creatureCbtLanceur_.estAssexue()||creatureCbtCible_.estAssexue());
        statBool(_import, StringUtil.concat(varPref_, _import.genresEgaux()), variables_, creatureCbtLanceur_.getCurrentGender()==creatureCbtCible_.getCurrentGender());
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void statusCible(DataBase _import, Fighter _creatureCbtCible, StringMap<String> _variables) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
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
            statBool(_import, StringUtil.concat(varPref_, _import.ciblePossedeStatutRelation(),DataBase.SEP_BETWEEN_KEYS,m), _variables, enabled_.getVal(m) == BoolVal.TRUE);
        }
    }

    private static void enaCond(DataBase _import, Fighter _creature, StringMap<String> _variables, String _keyPart) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        StringList effetsIndiv_ = _import.getVarParamsMove(_keyPart);
        for(String c:effetsIndiv_){
            if (_creature.getEnabledMoves().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMoves().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesProt().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesProt().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesUnprot().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesUnprot().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesEndRound().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesEndRound().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesForAlly().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesForAlly().getVal(c) == BoolVal.TRUE);
            } else if (_creature.getEnabledMovesConstChoices().contains(c)) {
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesConstChoices().getVal(c).isEnabled());
            } else if (StringUtil.contains(_import.getMovesActingMoveUses(), c)) {
                boolean enabledMove_ = enabledMove(_creature, c);
                statBool(_import, StringUtil.concat(varPref_, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, enabledMove_);
            } else {
                statBool(_import, StringUtil.concat(varPref_, _keyPart, DataBase.SEP_BETWEEN_KEYS, c), _variables, StringUtil.quickEq(_creature.getLastSuccessfulMove(), c) && _creature.isSuccessfulMove());
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
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        _fight.setEnabledMessages(false);
        StringMap<String> variables_ = new StringMap<String>();
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        Team equipeAdvCbtEnvoye_=_fight.getTeams().getVal(Fight.foe(_lanceur.getTeam()));
        StringMap<LgInt> nbUtilisationsEntreeAdv_=equipeAdvCbtEnvoye_.getEnabledMovesWhileSendingFoeUses();
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_COMBATTANT_ENTRANT_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        variables_.put(StringUtil.concat(varPref_, DataBase.DEF_COMBATTANT_ENTRANT_TYPES), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        rateEff(_import, _import.coeffEffBaseTypesCombattantEntrant(), variables_, creatureCbtLanceur_);
        StringList nbUtAdv_=_import.getVarParamsMove(_import.equipeAdvCombattantEntrantNbUtilisation());
        for(String e:nbUtAdv_){
            variables_.put(StringUtil.concat(varPref_, _import.equipeAdvCombattantEntrantNbUtilisation(),DataBase.SEP_BETWEEN_KEYS,e),nbUtilisationsEntreeAdv_.getVal(e).toNumberString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void addPlayed(Fight _fight, DataBase _import, StringMap<String> _variables, String _fighterDerJoue, TeamPosition _tp) {
        String varPref_ = StringUtil.concat(_import.prefixVar(),DataBase.SEP_BETWEEN_KEYS);
        CustList<TeamPosition> cbts_ = FightOrder.fightersHavingToAct(_fight, true, _import);
        cbts_.addAllElts(FightOrder.fightersHavingToAct(_fight, false, _import));
        boolean onlyOne_;
        if (cbts_.isEmpty()) {
            onlyOne_ = false;
        } else {
            onlyOne_ = onlyOne(cbts_) && (_tp == null || cbts_.get(0).eq(_tp));
        }
        stat(StringUtil.concat(varPref_, _fighterDerJoue), _variables, Fight.ONE, Fight.ZERO, onlyOne_);
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
