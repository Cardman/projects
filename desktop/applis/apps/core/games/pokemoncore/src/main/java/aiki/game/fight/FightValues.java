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
        _fight.setEnabledMessages(false);
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_fighter.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_GENRE), creatureCbtLanceur_.getCurrentGender().name());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.NB_UTILI_ATT_EQ_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_PV_RESTANTS),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_PV_MAX),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, Fighter.FIGHTER_NB_UTILISATION);
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_STATIS,DataBase.SEP_BETWEEN_KEYS,c.name()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, Fight.FIGHTER_BOOST, Fight.SOMME_BOOST_POS_FIGHTER);
        StringList attaquesGlobales_ = _import.getVarParamsMove(Fight.NB_TOUR_GLOBAL);
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR_GLOBAL,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX, Fight.FIGHTER_ATTAQUE_CHOISIE), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, Fight.FIGHTER_ATTAQUES, _import, _fight, _fighter, Fight.FIGHTER_ATTAQUES_TYPES);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, Fight.FIGHTER_DEGATS_RECUS, Fight.FIGHTER_DEGATS_RECUS_TOTAL, Fight.FIGHTER_DEGATS_RECUS_TOUR, Fight.FIGHTER_DEGATS_RECUS_TOTAL_TOUR);

        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.FIGHTER_DISPARAIT), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.FIGHTER_JOUE), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        addPlayed(_fight, _import, variables_, Fight.FIGHTER_DER_JOUE);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_NOM),creatureCbtLanceur_.getCurrentName());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_MASSE),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_TAILLE),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_OBJET),creatureCbtLanceur_.getItem());
        StringList statutsLanceur_ = new StringList();
        for(String c:creatureCbtLanceur_.getStatusSet()){
            if(!NumberUtil.eq(creatureCbtLanceur_.getStatusNbRoundShort(c), 0)){
                statutsLanceur_.add(c);
            }
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_STATUTS), StringUtil.join(statutsLanceur_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_TYPES), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CLIMATS), StringUtil.join(FightMoves.climatsActifs(_fight,_import), _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, Fight.FIGHTER_PP, variables_);
        equipes(variables_, _fight, _fighter, Fight.NB_KO_EQUIPE_FIGHTER, Fight.NB_KO_EQUIPE_ADV_FIGHTER);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_NIVEAU),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_BONHEUR),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, Fight.COEFF_EFF_BASE_TYPES_FIGHTER, variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void damage(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _degatsRecus, String _degatsRecusTotal, String _degatsRecusTour, String _degatsRecusTotalTour) {
        Rate sommeLanceurDegatsSubis_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCateg().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCateg().getVal(c);
            sommeLanceurDegatsSubis_.addNb(deg_);
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _degatsRecus,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _degatsRecusTotal),sommeLanceurDegatsSubis_.toNumberString());
        Rate sommeLanceurDegatsSubisTour_=Rate.zero();
        for(String c: _creatureCbtLanceur.getDamageSufferedCategRound().getKeys()){
            Rate deg_= _creatureCbtLanceur.getDamageSufferedCategRound().getVal(c);
            sommeLanceurDegatsSubisTour_.addNb(deg_);
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _degatsRecusTour,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _degatsRecusTotalTour),sommeLanceurDegatsSubisTour_.toNumberString());
    }

    private static void enabled(Fighter _creature, StringMap<String> _variables, String _nbUtilisation) {
        for(String c: _creature.getEnabledMoves().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMoves().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesProt().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesProt().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesUnprot().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesUnprot().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesEndRound().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesEndRound().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getEnabledMovesConstChoices().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getEnabledMovesConstChoices().getVal(c).getNbTurn()));
        }
        for(String c: _creature.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _nbUtilisation,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_creature.getNbUsesMoves().getVal(c)));
        }
    }

    private static void moves(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _attaques, DataBase _import, Fight _fight, TeamPosition _fighter, String _attaquesTypes) {
        StringList clesLanceurAttaquesActuelles_= _creatureCbtLanceur.attaquesUtilisables();
        clesLanceurAttaquesActuelles_.sort();
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _attaques), StringUtil.join(clesLanceurAttaquesActuelles_, _import.getSepartorSetChar()));
        StringList lanceurAttaquesActuellesTypes_ = new StringList();
        for(String c:clesLanceurAttaquesActuelles_){
            lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight, _fighter,c, _import));
        }
        lanceurAttaquesActuellesTypes_.removeDuplicates();
        lanceurAttaquesActuellesTypes_.sort();
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _attaquesTypes), StringUtil.join(lanceurAttaquesActuellesTypes_, _import.getSepartorSetChar()));
    }

    private static void sommeLanceurBoostsPositifs(Fighter _creatureCbtLanceur, StringMap<String> _variables, String _boost, String _sommeBoostPos) {
        Rate sommeLanceurBoostsPositifs_=Rate.zero();
        for(Statistic c: _creatureCbtLanceur.getStatisBoost().getKeys()){
            byte boost_= _creatureCbtLanceur.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeLanceurBoostsPositifs_.addNb(new Rate(boost_));
            }
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _boost,DataBase.SEP_BETWEEN_KEYS,c.name()),Long.toString(boost_));
        }
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _sommeBoostPos),sommeLanceurBoostsPositifs_.toNumberString());
    }

    private static void equipes(StringMap<String> _variables, Fight _fight, TeamPosition _fighter, String _nbKoEquipe, String _nbKoEquipeAdv) {
        Team equipeLanceur_=_fight.getTeams().getVal(_fighter.getTeam());
        for(String c: equipeLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMoves().getVal(c)));
        }
        Team equipeAdvLanceur_= _fight.getTeams().getVal(Fight.foe(_fighter.getTeam()));
        for(String c:equipeAdvLanceur_.getNbUsesMoves().getKeys()){
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_ADV_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeAdvLanceur_.getNbUsesMoves().getVal(c)));
        }
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _nbKoEquipe),Long.toString((long) equipeLanceur_.getNbKoRound()+ equipeLanceur_.getNbKoPreviousRound()));
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _nbKoEquipeAdv),Long.toString((long)equipeAdvLanceur_.getNbKoRound()+equipeAdvLanceur_.getNbKoPreviousRound()));
    }

    static StringMap<String> calculateValues(Fight _fight,TeamPosition _lanceur,TeamPosition _cible,DataBase _import){
        _fight.setEnabledMessages(false);
        Team equipeCible_=_fight.getTeams().getVal(_cible.getTeam());
        Fighter creatureCbtCible_=equipeCible_.getMembers().getVal(_cible.getPosition());
        Team equipeLanceur_=_fight.getTeams().getVal(_lanceur.getTeam());
        Fighter creatureCbtLanceur_=equipeLanceur_.getMembers().getVal(_lanceur.getPosition());
        StringMap<String> variables_ = new StringMap<String>();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_GENRE), creatureCbtCible_.getCurrentGender().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_PV_RESTANTS),creatureCbtCible_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_PV_MAX),creatureCbtCible_.pvMax().toNumberString());
        for(String c:creatureCbtCible_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.CIBLE_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtCible_.getNbUsesMoves().getVal(c)));
        }
        for(Statistic c:creatureCbtCible_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_STATIS,DataBase.SEP_BETWEEN_KEYS,c.name()),creatureCbtCible_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtCible_, variables_, Fight.CIBLE_BOOST, Fight.SOMME_BOOST_POS_CIBLE);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX, Fight.CIBLE_ATTAQUE_CHOISIE), creatureCbtCible_.getFinalChosenMove());
        moves(creatureCbtCible_, variables_, Fight.CIBLE_ATTAQUES, _import, _fight, _cible, Fight.CIBLE_ATTAQUES_TYPES);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_CLONE),creatureCbtCible_.getClone().toNumberString());
        damage(creatureCbtCible_, variables_, Fight.CIBLE_DEGATS_RECUS, Fight.CIBLE_DEGATS_RECUS_TOTAL, Fight.CIBLE_DEGATS_RECUS_TOUR, Fight.CIBLE_DEGATS_RECUS_TOTAL_TOUR);
        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.CIBLE_DISPARAIT), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isDisappeared());
        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.CIBLE_JOUE), variables_, Fight.ONE, Fight.ZERO, creatureCbtCible_.isActed());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_MASSE),creatureCbtCible_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_TAILLE),creatureCbtCible_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_CAPACITE),creatureCbtCible_.getCurrentAbility());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_OBJET),creatureCbtCible_.getItem());
        statuts(_import, creatureCbtCible_, variables_, Fight.CIBLE_STATUTS);
        StringList types_ = new StringList(creatureCbtCible_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_TYPES), StringUtil.join(types_, _import.getSepartorSetChar()));
        varsPp(_import, creatureCbtCible_, Fight.CIBLE_PP, variables_);
        Team equipeAdvCible_=_fight.getTeams().getVal(Fight.foe(_cible.getTeam()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_NIVEAU),Long.toString(creatureCbtCible_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_CIBLE),Long.toString((long)equipeCible_.getNbKoRound()+equipeCible_.getNbKoPreviousRound()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_ADV_CIBLE),Long.toString((long)equipeAdvCible_.getNbKoRound()+equipeAdvCible_.getNbKoPreviousRound()));
        rateEff(_import, Fight.COEFF_EFF_BASE_TYPES_CIBLE, variables_, creatureCbtCible_);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_GENRE), creatureCbtLanceur_.getCurrentGender().name());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.NB_UTILI_ATT_EQ_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PV_RESTANTS),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PV_MAX),creatureCbtLanceur_.pvMax().toNumberString());
        enabled(creatureCbtLanceur_, variables_, Fighter.LANCEUR_NB_UTILISATION);
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_STATIS,DataBase.SEP_BETWEEN_KEYS,c.name()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        sommeLanceurBoostsPositifs(creatureCbtLanceur_, variables_, Fight.LANCEUR_BOOST, Fight.SOMME_BOOST_POS_LANCEUR);
        StringList attaquesGlobales_ = _import.getVarParamsMove(Fight.NB_TOUR_GLOBAL);
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR_GLOBAL,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX, Fight.LANCEUR_ATTAQUE_CHOISIE), creatureCbtLanceur_.getFinalChosenMove());
        moves(creatureCbtLanceur_, variables_, Fight.LANCEUR_ATTAQUES, _import, _fight, _lanceur, Fight.LANCEUR_ATTAQUES_TYPES);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        damage(creatureCbtLanceur_, variables_, Fight.LANCEUR_DEGATS_RECUS, Fight.LANCEUR_DEGATS_RECUS_TOTAL, Fight.LANCEUR_DEGATS_RECUS_TOUR, Fight.LANCEUR_DEGATS_RECUS_TOTAL_TOUR);

        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.LANCEUR_DISPARAIT), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isDisappeared());
        stat(StringUtil.concat(DataBase.VAR_PREFIX, Fight.LANCEUR_JOUE), variables_, Fight.ONE, Fight.ZERO, creatureCbtLanceur_.isActed());
        addPlayed(_fight, _import, variables_, Fight.LANCEUR_DER_JOUE);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_MASSE),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_TAILLE),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_CAPACITE),creatureCbtLanceur_.getCurrentAbility());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_OBJET),creatureCbtLanceur_.getItem());
        statuts(_import, creatureCbtLanceur_, variables_, Fight.LANCEUR_STATUTS);
        types_ = new StringList(creatureCbtLanceur_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_TYPES), StringUtil.join(types_, _import.getSepartorSetChar()));
        StringList weathers_ = FightMoves.climatsActifs(_fight,_import);
        weathers_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CLIMATS), StringUtil.join(weathers_, _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        varsPp(_import, creatureCbtLanceur_, Fight.LANCEUR_PP, variables_);
        equipes(variables_, _fight, _lanceur, Fight.NB_KO_EQUIPE_LANCEUR, Fight.NB_KO_EQUIPE_ADV_LANCEUR);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_NIVEAU),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_BONHEUR),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.RATE_EFF_MOVE_AGAINST_TARGET),FightSuccess.rateEffAgainstTargetMove(_fight,_lanceur, _cible, _import).toNumberString());
        int nbCombattantsTerrain_ = nbCombattantsTerrain(_fight);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        rateEff(_import, Fight.COEFF_EFF_BASE_TYPES_LANCEUR, variables_, creatureCbtLanceur_);
        _fight.setEnabledMessages(true);
        return variables_;
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
        _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _cibleStatuts), StringUtil.join(statutsCible_, _import.getSepartorSetChar()));
    }

    private static void rateEff(DataBase _import, String _coeffEffBaseTypes, StringMap<String> _variables, Fighter _creature) {
        StringList coeffTypes_ = _import.getVarParamsMove(_coeffEffBaseTypes);
        for(String e:coeffTypes_){
            Rate coeffCible_=DataBase.defRateProduct();
            for(String e2_: _creature.getTypes()){
                coeffCible_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _coeffEffBaseTypes,DataBase.SEP_BETWEEN_KEYS,e),coeffCible_.toNumberString());
        }
    }

    private static void varsPp(DataBase _import, Fighter _creatureCbtLanceur, String _key, StringMap<String> _variables) {
        StringList attaquesPp_ = _import.getVarParamsMove(_key);
        for(String c:attaquesPp_){
            if(!StringUtil.contains(_creatureCbtLanceur.attaquesUtilisables(), c)){
                _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _key,DataBase.SEP_BETWEEN_KEYS,c),Fight.ZERO);
            }else{
                short ppActuel_= _creatureCbtLanceur.powerPointsMove(c);
                _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _key,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(ppActuel_));
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
        StringList immuTypesIndiv_ = _import.getVarParamsMove(Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT);
        if(effet_ instanceof EffectTeamWhileSendFoe){
            for(String e:immuTypesIndiv_){
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT,DataBase.SEP_BETWEEN_KEYS,e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
            }
        }
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_ATTAQUE_INVOC), variables_, effet_ instanceof EffectInvoke && FightInvoke.invokableMoves(_fight, _lanceur, _cible, ((EffectInvoke) effet_), _import).isEmpty());
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_ATTAQUES_COPIABLES), variables_, effet_ instanceof EffectCopyMove && FightInvoke.copiableMoves(_fight, _lanceur, _cible, ((EffectCopyMove) effet_), _import).isEmpty());
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
        enaCond(_import, creatureCbtCible_, variables_, Fight.CIBLE_EFFET);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_PP_ATTAQUE_CIBLE), variables_, creatureCbtCible_.noPowerPointForLastUsedMove());
        boolean pasUtiliseAttaqueCible_=true;
        for(String c:creatureCbtCible_.getCurrentMovesSet()){
            UsesOfMove pps_=creatureCbtCible_.getCurrentMove(c);
            if(pps_.getCurrent()<pps_.getMax()){
                pasUtiliseAttaqueCible_=false;
                break;
            }
        }
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_UTILIS_ATTAQUE_CIBLE), variables_, pasUtiliseAttaqueCible_);
        StringList immuTypesIndiv_ = _import.getVarParamsMove(Fight.IMMU_TYPE_ATT_CIBLE);
        for(String e:immuTypesIndiv_){
            statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.IMMU_TYPE_ATT_CIBLE,DataBase.SEP_BETWEEN_KEYS,e), variables_, FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import));
        }
        boolean aucunBoostPossible_ = aucunBoostPossible(_import, creatureCbtCible_);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.AUCUN_BOOST_POSSIBLE), variables_, aucunBoostPossible_);
        enaCond(_import, creatureCbtLanceur_, variables_, Fight.LANCEUR_EFFET);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.TYPES_ATTAQUES_RES_VIDE), variables_, creatureCbtLanceur_.resistingTypes(_import).isEmpty());
        int nbPartenairesArriere_ = FightOrder.nbBackPartners(_fight, _lanceur);
        int nbPartenairesTerrain_ = FightOrder.nbFrontPartners(_fight, _lanceur);
        int nbPartenaires_ = nbPartenairesArriere_ + nbPartenairesTerrain_;
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_PARTENAIRE), variables_, nbPartenaires_==0);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_PARTENAIRE_ARRIERE), variables_, nbPartenairesArriere_==0);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_PARTENAIRE_TERRAIN), variables_, nbPartenairesTerrain_==0);
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.PAS_TOUR_TERRAIN), variables_, creatureCbtLanceur_.getNbRounds().isZero());
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.EXISTE_GENRE_ASSEXUE), variables_, creatureCbtLanceur_.estAssexue()||creatureCbtCible_.estAssexue());
        statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.GENRES_EGAUX), variables_, creatureCbtLanceur_.getCurrentGender()==creatureCbtCible_.getCurrentGender());
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
            statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, Fight.CIBLE_POSSEDE_STATUT_RELATION,DataBase.SEP_BETWEEN_KEYS,m), _variables, enabled_.getVal(m) == BoolVal.TRUE);
        }
    }

    private static void enaCond(DataBase _import, Fighter _creature, StringMap<String> _variables, String _keyPart) {
        StringList effetsIndiv_ = _import.getVarParamsMove(_keyPart);
        for(String c:effetsIndiv_){
            if (_creature.getEnabledMoves().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMoves().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesProt().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesProt().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesUnprot().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesUnprot().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesEndRound().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesEndRound().getVal(c).isEnabled());
            } else if (_creature.getEnabledMovesForAlly().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesForAlly().getVal(c) == BoolVal.TRUE);
            } else if (_creature.getEnabledMovesConstChoices().contains(c)) {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, _creature.getEnabledMovesConstChoices().getVal(c).isEnabled());
            } else if (StringUtil.contains(_import.getMovesActingMoveUses(), c)) {
                boolean enabledMove_ = enabledMove(_creature, c);
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart,DataBase.SEP_BETWEEN_KEYS,c), _variables, enabledMove_);
            } else {
                statBool(_import, StringUtil.concat(DataBase.VAR_PREFIX, _keyPart, DataBase.SEP_BETWEEN_KEYS, c), _variables, StringUtil.quickEq(_creature.getLastSuccessfulMove(), c) && _creature.isSuccessfulMove());
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
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COMBATTANT_ENTRANT_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COMBATTANT_ENTRANT_TYPES), StringUtil.join(creatureCbtLanceur_.getTypes(), _import.getSepartorSetChar()));
        rateEff(_import, Fight.COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT, variables_, creatureCbtLanceur_);
        StringList nbUtAdv_=_import.getVarParamsMove(Fight.EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION);
        for(String e:nbUtAdv_){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,e),nbUtilisationsEntreeAdv_.getVal(e).toNumberString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
    }

    private static void addPlayed(Fight _fight, DataBase _import, StringMap<String> _variables, String _fighterDerJoue) {
        CustList<TeamPosition> cbts_ = FightOrder.fightersHavingToAct(_fight, true, _import);
        cbts_.addAllElts(FightOrder.fightersHavingToAct(_fight, false, _import));
        boolean onlyOne_;
        if (cbts_.isEmpty()) {
            onlyOne_ = false;
        } else {
            onlyOne_ = onlyOne(cbts_);
        }
        stat(StringUtil.concat(DataBase.VAR_PREFIX, _fighterDerJoue), _variables, Fight.ONE, Fight.ZERO, onlyOne_);
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
