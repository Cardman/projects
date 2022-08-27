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
        for(String c:creatureCbtLanceur_.getEnabledMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMoves().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesProt().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesProt().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesUnprot().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesUnprot().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesEndRound().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesEndRound().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesConstChoices().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesConstChoices().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.FIGHTER_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getNbUsesMoves().getVal(c)));
        }
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_STATIS,DataBase.SEP_BETWEEN_KEYS,c.name()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        Rate sommeLanceurBoostsPositifs_=Rate.zero();
        for(Statistic c:creatureCbtLanceur_.getStatisBoost().getKeys()){
            byte boost_=creatureCbtLanceur_.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeLanceurBoostsPositifs_.addNb(new Rate(boost_));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_BOOST,DataBase.SEP_BETWEEN_KEYS,c.name()),Long.toString(boost_));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.SOMME_BOOST_POS_FIGHTER),sommeLanceurBoostsPositifs_.toNumberString());
        StringList attaquesGlobales_ = _import.getVarParamsMove(Fight.NB_TOUR_GLOBAL);
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR_GLOBAL,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        StringList clesLanceurAttaquesActuelles_=creatureCbtLanceur_.attaquesUtilisables();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_ATTAQUES), StringUtil.join(clesLanceurAttaquesActuelles_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_ATTAQUE_CHOISIE),creatureCbtLanceur_.getFinalChosenMove());
        StringList lanceurAttaquesActuellesTypes_ = new StringList();
        for(String c:clesLanceurAttaquesActuelles_){
            lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight, _fighter,c,_import));
        }
        lanceurAttaquesActuellesTypes_.removeDuplicates();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_ATTAQUES_TYPES), StringUtil.join(lanceurAttaquesActuellesTypes_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        Rate sommeLanceurDegatsSubis_=Rate.zero();
        for(String c:creatureCbtLanceur_.getDamageSufferedCateg().getKeys()){
            Rate deg_=creatureCbtLanceur_.getDamageSufferedCateg().getVal(c);
            sommeLanceurDegatsSubis_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DEGATS_RECUS,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DEGATS_RECUS_TOTAL),sommeLanceurDegatsSubis_.toNumberString());
        Rate sommeLanceurDegatsSubisTour_=Rate.zero();
        for(String c:creatureCbtLanceur_.getDamageSufferedCategRound().getKeys()){
            Rate deg_=creatureCbtLanceur_.getDamageSufferedCategRound().getVal(c);
            sommeLanceurDegatsSubisTour_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DEGATS_RECUS_TOUR,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DEGATS_RECUS_TOTAL_TOUR),sommeLanceurDegatsSubisTour_.toNumberString());

        if(creatureCbtLanceur_.isDisappeared()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DISPARAIT),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_DISPARAIT),Fight.ZERO);
        }
        if(creatureCbtLanceur_.isActed()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_JOUE),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_JOUE),Fight.ZERO);
        }
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
        StringList attaquesPp_ = _import.getVarParamsMove(Fight.FIGHTER_PP);
        for(String c:attaquesPp_){
            if(!StringUtil.contains(creatureCbtLanceur_.attaquesUtilisables(), c)){
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_PP,DataBase.SEP_BETWEEN_KEYS,c),Fight.ZERO);
            }else{
                short ppActuel_=creatureCbtLanceur_.powerPointsMove(c);
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_PP,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(ppActuel_));
            }
        }
        for(String c:equipeLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMoves().getVal(c)));
        }
        Team equipeAdvLanceur_=_fight.getTeams().getVal(Fight.foe(_fighter.getTeam()));
        for(String c:equipeAdvLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_ADV_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeAdvLanceur_.getNbUsesMoves().getVal(c)));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_NIVEAU),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.FIGHTER_BONHEUR),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        int nbCombattantsTerrain_=0;
        for(byte c:_fight.getTeams().getKeys()){
            for(byte c2_:_fight.getTeams().getVal(c).getMembers().getKeys()){
                Fighter cbtEquipe_=_fight.getTeams().getVal(c).getMembers().getVal(c2_);
                if(cbtEquipe_.estKo()){
                    continue;
                }
                if(!cbtEquipe_.estArriere()){
                    nbCombattantsTerrain_++;
                }
            }
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_FIGHTER),Long.toString((long)equipeLanceur_.getNbKoRound()+equipeLanceur_.getNbKoPreviousRound()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_ADV_FIGHTER),Long.toString((long)equipeAdvLanceur_.getNbKoRound()+equipeAdvLanceur_.getNbKoPreviousRound()));
        StringList coeffTypes_ = _import.getVarParamsMove(Fight.COEFF_EFF_BASE_TYPES_FIGHTER);
        for(String e:coeffTypes_){
            Rate coeffLanceur_=DataBase.defRateProduct();
            for(String e2_:creatureCbtLanceur_.getTypes()){
                coeffLanceur_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF_BASE_TYPES_FIGHTER,DataBase.SEP_BETWEEN_KEYS,e),coeffLanceur_.toNumberString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
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
        Rate sommeCibleBoostsPositifs_=Rate.zero();
        for(Statistic c:creatureCbtCible_.getStatisBoost().getKeys()){
            byte boost_=creatureCbtCible_.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeCibleBoostsPositifs_.addNb(new Rate(boost_));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_BOOST,DataBase.SEP_BETWEEN_KEYS,c.name()),Long.toString(boost_));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.SOMME_BOOST_POS_CIBLE),sommeCibleBoostsPositifs_.toNumberString());
        StringList clesCibleAttaquesActuelles_=creatureCbtCible_.attaquesUtilisables();
        clesCibleAttaquesActuelles_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_ATTAQUES), StringUtil.join(clesCibleAttaquesActuelles_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_ATTAQUE_CHOISIE),creatureCbtCible_.getFinalChosenMove());
        StringList cibleAttaquesActuellesTypes_ = new StringList();
        for(String c:clesCibleAttaquesActuelles_){
            cibleAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight,_cible,c,_import));
        }
        cibleAttaquesActuellesTypes_.removeDuplicates();
        cibleAttaquesActuellesTypes_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_ATTAQUES_TYPES), StringUtil.join(cibleAttaquesActuellesTypes_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_CLONE),creatureCbtCible_.getClone().toNumberString());
        Rate sommeCibleDegatsSubis_=Rate.zero();
        for(String c:creatureCbtCible_.getDamageSufferedCateg().getKeys()){
            Rate deg_=creatureCbtCible_.getDamageSufferedCateg().getVal(c);
            sommeCibleDegatsSubis_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DEGATS_RECUS,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DEGATS_RECUS_TOTAL),sommeCibleDegatsSubis_.toNumberString());
        Rate sommeCibleDegatsSubisTour_=Rate.zero();
        for(String c:creatureCbtCible_.getDamageSufferedCategRound().getKeys()){
            Rate deg_=creatureCbtCible_.getDamageSufferedCategRound().getVal(c);
            sommeCibleDegatsSubisTour_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DEGATS_RECUS_TOUR,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DEGATS_RECUS_TOTAL_TOUR),sommeCibleDegatsSubisTour_.toNumberString());
        if(creatureCbtCible_.isDisappeared()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DISPARAIT),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_DISPARAIT),Fight.ZERO);
        }
        if(creatureCbtCible_.isActed()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_JOUE),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_JOUE),Fight.ZERO);
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_MASSE),creatureCbtCible_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_TAILLE),creatureCbtCible_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_CAPACITE),creatureCbtCible_.getCurrentAbility());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_OBJET),creatureCbtCible_.getItem());
        StringList statutsCible_ = new StringList();
        for(String c:creatureCbtCible_.getStatusSet()){
            if(!NumberUtil.eq(creatureCbtCible_.getStatusNbRoundShort(c), 0)){
                statutsCible_.add(c);
            }
        }
        for (MoveTeamPosition s: creatureCbtCible_.getStatusRelatSet()) {
            if(NumberUtil.eq(creatureCbtCible_.getStatusRelatNbRoundShort(s), 0)){
                continue;
            }
            statutsCible_.add(s.getMove());
        }
        statutsCible_.removeDuplicates();
        statutsCible_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_STATUTS), StringUtil.join(statutsCible_, _import.getSepartorSetChar()));
        StringList types_ = new StringList(creatureCbtCible_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_TYPES), StringUtil.join(types_, _import.getSepartorSetChar()));
        StringList attaquesPp_ = _import.getVarParamsMove(Fight.CIBLE_PP);
        for(String c:attaquesPp_){
            if(!StringUtil.contains(creatureCbtCible_.attaquesUtilisables(), c)){
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_PP,DataBase.SEP_BETWEEN_KEYS,c),Fight.ZERO);
            }else{
                short ppActuel_=creatureCbtCible_.powerPointsMove(c);
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_PP,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(ppActuel_));
            }
        }
        Team equipeAdvCible_=_fight.getTeams().getVal(Fight.foe(_cible.getTeam()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_NIVEAU),Long.toString(creatureCbtCible_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_CIBLE),Long.toString((long)equipeCible_.getNbKoRound()+equipeCible_.getNbKoPreviousRound()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_ADV_CIBLE),Long.toString((long)equipeAdvCible_.getNbKoRound()+equipeAdvCible_.getNbKoPreviousRound()));
        StringList coeffTypes_ = _import.getVarParamsMove(Fight.COEFF_EFF_BASE_TYPES_CIBLE);
        for(String e:coeffTypes_){
            Rate coeffCible_=DataBase.defRateProduct();
            for(String e2_:creatureCbtCible_.getTypes()){
                coeffCible_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF_BASE_TYPES_CIBLE,DataBase.SEP_BETWEEN_KEYS,e),coeffCible_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_GENRE), creatureCbtLanceur_.getCurrentGender().name());
        for(String c:equipeLanceur_.getNbUsesMovesRound().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.NB_UTILI_ATT_EQ_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMovesRound().getVal(c)));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PV_RESTANTS),creatureCbtLanceur_.getRemainingHp().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PV_MAX),creatureCbtLanceur_.pvMax().toNumberString());
        for(String c:creatureCbtLanceur_.getEnabledMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMoves().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesProt().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesProt().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesUnprot().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesUnprot().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesEndRound().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesEndRound().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getEnabledMovesConstChoices().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getEnabledMovesConstChoices().getVal(c).getNbTurn()));
        }
        for(String c:creatureCbtLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.LANCEUR_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(creatureCbtLanceur_.getNbUsesMoves().getVal(c)));
        }
        for(Statistic c:creatureCbtLanceur_.getStatisBase().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_STATIS,DataBase.SEP_BETWEEN_KEYS,c.name()),creatureCbtLanceur_.getStatisBase().getVal(c).toNumberString());
        }
        Rate sommeLanceurBoostsPositifs_=Rate.zero();
        for(Statistic c:creatureCbtLanceur_.getStatisBoost().getKeys()){
            byte boost_=creatureCbtLanceur_.getStatisBoost().getVal(c);
            if(boost_>0){
                sommeLanceurBoostsPositifs_.addNb(new Rate(boost_));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_BOOST,DataBase.SEP_BETWEEN_KEYS,c.name()),Long.toString(boost_));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.SOMME_BOOST_POS_LANCEUR),sommeLanceurBoostsPositifs_.toNumberString());
        StringList attaquesGlobales_ = _import.getVarParamsMove(Fight.NB_TOUR_GLOBAL);
        for(String c:attaquesGlobales_){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_TOUR_GLOBAL,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(_fight.getEnabledMoves().getVal(c).getNbTurn()));
        }
        StringList clesLanceurAttaquesActuelles_=creatureCbtLanceur_.attaquesUtilisables();
        clesLanceurAttaquesActuelles_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_ATTAQUES), StringUtil.join(clesLanceurAttaquesActuelles_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_ATTAQUE_CHOISIE),creatureCbtLanceur_.getFinalChosenMove());
        StringList lanceurAttaquesActuellesTypes_ = new StringList();
        for(String c:clesLanceurAttaquesActuelles_){
            lanceurAttaquesActuellesTypes_.addAllElts(FightMoves.moveTypes(_fight, _lanceur,c,_import));
        }
        lanceurAttaquesActuellesTypes_.removeDuplicates();
        lanceurAttaquesActuellesTypes_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_ATTAQUES_TYPES), StringUtil.join(lanceurAttaquesActuellesTypes_, _import.getSepartorSetChar()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_CLONE),creatureCbtLanceur_.getClone().toNumberString());
        Rate sommeLanceurDegatsSubis_=Rate.zero();
        for(String c:creatureCbtLanceur_.getDamageSufferedCateg().getKeys()){
            Rate deg_=creatureCbtLanceur_.getDamageSufferedCateg().getVal(c);
            sommeLanceurDegatsSubis_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DEGATS_RECUS,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DEGATS_RECUS_TOTAL),sommeLanceurDegatsSubis_.toNumberString());
        Rate sommeLanceurDegatsSubisTour_=Rate.zero();
        for(String c:creatureCbtLanceur_.getDamageSufferedCategRound().getKeys()){
            Rate deg_=creatureCbtLanceur_.getDamageSufferedCategRound().getVal(c);
            sommeLanceurDegatsSubisTour_.addNb(deg_);
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DEGATS_RECUS_TOUR,DataBase.SEP_BETWEEN_KEYS,c),deg_.toNumberString());
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DEGATS_RECUS_TOTAL_TOUR),sommeLanceurDegatsSubisTour_.toNumberString());

        if(creatureCbtLanceur_.isDisappeared()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DISPARAIT),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_DISPARAIT),Fight.ZERO);
        }
        if(creatureCbtLanceur_.isActed()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_JOUE),Fight.ONE);
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_JOUE),Fight.ZERO);
        }
        addPlayed(_fight, _import, variables_, Fight.LANCEUR_DER_JOUE);
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_MASSE),creatureCbtLanceur_.getWeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_TAILLE),creatureCbtLanceur_.getHeight().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_CAPACITE),creatureCbtLanceur_.getCurrentAbility());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_OBJET),creatureCbtLanceur_.getItem());
        StringList statutsLanceur_ = new StringList();
        for(String c:creatureCbtLanceur_.getStatusSet()){
            if(creatureCbtLanceur_.getStatusNbRoundShort(c) != 0){
                statutsLanceur_.add(c);
            }
        }
        for (MoveTeamPosition s: creatureCbtLanceur_.getStatusRelatSet()) {
            if(NumberUtil.eq(creatureCbtLanceur_.getStatusRelatNbRoundShort(s), 0)){
                continue;
            }
            statutsLanceur_.add(s.getMove());
        }
        statutsLanceur_.removeDuplicates();
        statutsLanceur_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_STATUTS), StringUtil.join(statutsLanceur_, _import.getSepartorSetChar()));
        types_ = new StringList(creatureCbtLanceur_.getTypes());
        types_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_TYPES), StringUtil.join(types_, _import.getSepartorSetChar()));
        StringList weathers_ = FightMoves.climatsActifs(_fight,_import);
        weathers_.sort();
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CLIMATS), StringUtil.join(weathers_, _import.getSepartorSetChar()));
        //variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.CLIMAT_DOMINANT),climatDominant(_import));
        attaquesPp_ = _import.getVarParamsMove(Fight.LANCEUR_PP);
        for(String c:attaquesPp_){
            if(!StringUtil.contains(creatureCbtLanceur_.attaquesUtilisables(), c)){
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PP,DataBase.SEP_BETWEEN_KEYS,c),Fight.ZERO);
            }else{
                short ppActuel_=creatureCbtLanceur_.powerPointsMove(c);
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_PP,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(ppActuel_));
            }
        }
        for(String c:equipeLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeLanceur_.getNbUsesMoves().getVal(c)));
        }
        Team equipeAdvLanceur_=_fight.getTeams().getVal(Fight.foe(_lanceur.getTeam()));
        for(String c:equipeAdvLanceur_.getNbUsesMoves().getKeys()){
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Team.EQUIPE_ADV_NB_UTILISATION,DataBase.SEP_BETWEEN_KEYS,c),Long.toString(equipeAdvLanceur_.getNbUsesMoves().getVal(c)));
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LIEU_COMBAT),_fight.getEnvType().name());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_NIVEAU),Long.toString(creatureCbtLanceur_.getLevel()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_BONHEUR),Long.toString(creatureCbtLanceur_.getHappiness()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TEMPS_TOUR),_fight.getNbRounds().toNumberString());
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.RATE_EFF_MOVE_AGAINST_TARGET),FightSuccess.rateEffAgainstTargetMove(_fight,_lanceur, _cible, _import).toNumberString());
        int nbCombattantsTerrain_=0;
        for(byte c:_fight.getTeams().getKeys()){
            for(byte c2_:_fight.getTeams().getVal(c).getMembers().getKeys()){
                Fighter cbtEquipe_=_fight.getTeams().getVal(c).getMembers().getVal(c2_);
                if(cbtEquipe_.estKo()){
                    continue;
                }
                if(!cbtEquipe_.estArriere()){
                    nbCombattantsTerrain_++;
                }
            }
        }
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_COMBATTANTS_TERRAIN),Long.toString(nbCombattantsTerrain_));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_LANCEUR),Long.toString((long)equipeLanceur_.getNbKoRound()+equipeLanceur_.getNbKoPreviousRound()));
        variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.NB_KO_EQUIPE_ADV_LANCEUR),Long.toString((long)equipeAdvLanceur_.getNbKoRound()+equipeAdvLanceur_.getNbKoPreviousRound()));
        coeffTypes_ = _import.getVarParamsMove(Fight.COEFF_EFF_BASE_TYPES_LANCEUR);
        for(String e:coeffTypes_){
            Rate coeffLanceur_=DataBase.defRateProduct();
            for(String e2_:creatureCbtLanceur_.getTypes()){
                coeffLanceur_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF_BASE_TYPES_LANCEUR,DataBase.SEP_BETWEEN_KEYS,e),coeffLanceur_.toNumberString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
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
                if(FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import)){
                    //protected against
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT,DataBase.SEP_BETWEEN_KEYS,e),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.IMMU_TYPE_ATT_COMBATTANT_ENTRANT,DataBase.SEP_BETWEEN_KEYS,e),_import.getFalseString());
                }
            }
        }
        if(effet_ instanceof EffectInvoke){
            EffectInvoke effetInvoque_=(EffectInvoke)effet_;
            StringList attaquesInvocables_=FightInvoke.invokableMoves(_fight,_lanceur,_cible,effetInvoque_,_import);
            if(attaquesInvocables_.isEmpty()){
                //invoke of attacks
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUE_INVOC),_import.getTrueString());
            }else{
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUE_INVOC),_import.getFalseString());
            }
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUE_INVOC),_import.getFalseString());
        }
        if(effet_ instanceof EffectCopyMove){
            EffectCopyMove effetCopieAtt_=(EffectCopyMove)effet_;
            if(FightInvoke.copiableMoves(_fight, _lanceur,_cible, effetCopieAtt_, _import).isEmpty()){
                //copy moves
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUES_COPIABLES),_import.getTrueString());
            }else{
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUES_COPIABLES),_import.getFalseString());
            }
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_ATTAQUES_COPIABLES),_import.getFalseString());
        }
        variables_.putAllMap(calculateBasicBooleanValues(_fight,_lanceur,_cible,_import));
        _fight.setEnabledMessages(true);
        return variables_;
    }

    static StringMap<String> calculateBasicBooleanValues(
            Fight _fight,
            TeamPosition _lanceur,TeamPosition _cible,
            DataBase _import) {
        _fight.setEnabledMessages(false);
        Fighter creatureCbtCible_ = _fight.getFighter(_cible);
        Fighter creatureCbtLanceur_= _fight.getFighter(_lanceur);
        StringMap<String> variables_ = new StringMap<String>();
        StringMap<Boolean> enabled_ = new StringMap<Boolean>();
        StringList moves_ = new StringList();
        for (MoveTeamPosition s: creatureCbtCible_.getStatusRelatSet()) {
            moves_.add(s.getMove());
        }
        moves_.removeDuplicates();
        for (String m: moves_) {
            boolean enabledLoc_ = false;
            for (MoveTeamPosition s: creatureCbtCible_.getStatusRelatSet()) {
                if (!StringUtil.quickEq(s.getMove(), m)) {
                    continue;
                }
                if (creatureCbtCible_.getStatusRelatNbRoundShort(s) > 0) {
                    enabledLoc_ = true;
                    break;
                }
            }
            enabled_.put(m, enabledLoc_);
        }
        for (String m: enabled_.getKeys()) {
            if(enabled_.getVal(m)){
                //enabled status relation
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_POSSEDE_STATUT_RELATION,DataBase.SEP_BETWEEN_KEYS,m),_import.getTrueString());
            }else{
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_POSSEDE_STATUT_RELATION,DataBase.SEP_BETWEEN_KEYS,m),_import.getFalseString());
            }
        }
        StringList effetsIndiv_ = _import.getVarParamsMove(Fight.CIBLE_EFFET);
        /*
        ENCORE,ATTERRISSAGE,ENTRAVE,REFLET_MAGIK
        */
        for(String c:effetsIndiv_){
            if (creatureCbtCible_.getEnabledMoves().contains(c)) {
                if(creatureCbtCible_.getEnabledMoves().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtCible_.getEnabledMovesProt().contains(c)) {
                if(creatureCbtCible_.getEnabledMovesProt().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtCible_.getEnabledMovesUnprot().contains(c)) {
                if(creatureCbtCible_.getEnabledMovesUnprot().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtCible_.getEnabledMovesEndRound().contains(c)) {
                if(creatureCbtCible_.getEnabledMovesEndRound().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtCible_.getEnabledMovesForAlly().contains(c)) {
                if(creatureCbtCible_.getEnabledMovesForAlly().getVal(c) == BoolVal.TRUE){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtCible_.getEnabledMovesConstChoices().contains(c)) {
                if(creatureCbtCible_.getEnabledMovesConstChoices().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (StringUtil.contains(_import.getMovesActingMoveUses(), c)) {
                boolean enabledMove_ = false;
                for (MoveTeamPosition m: creatureCbtCible_.getTrackingMoves().getKeys()) {
                    if (!StringUtil.quickEq(m.getMove(), c)) {
                        continue;
                    }
                    if (!creatureCbtCible_.getTrackingMoves().getVal(m).getActivity().isEnabled()) {
                        continue;
                    }
                    enabledMove_ = true;
                }
                if (enabledMove_) {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                } else {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (StringUtil.quickEq(creatureCbtCible_.getLastSuccessfulMove(), c)) {
                if (creatureCbtCible_.isSuccessfulMove()) {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                } else {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
            } else {
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.CIBLE_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
            }
        }
        if(creatureCbtCible_.noPowerPointForLastUsedMove()){
            //without pp
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PP_ATTAQUE_CIBLE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PP_ATTAQUE_CIBLE),_import.getFalseString());
        }
        boolean pasUtiliseAttaqueCible_=true;
        for(String c:creatureCbtCible_.getCurrentMovesSet()){
            UsesOfMove pps_=creatureCbtCible_.getCurrentMove(c);
            if(pps_.getCurrent()<pps_.getMax()){
                pasUtiliseAttaqueCible_=false;
                break;
            }
        }
        if(pasUtiliseAttaqueCible_){
            //without use of pp
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_UTILIS_ATTAQUE_CIBLE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_UTILIS_ATTAQUE_CIBLE),_import.getFalseString());
        }
        StringList immuTypesIndiv_ = _import.getVarParamsMove(Fight.IMMU_TYPE_ATT_CIBLE);
        for(String e:immuTypesIndiv_){
            if(FightSuccess.isProtectedAgainstMoveType(_fight,_lanceur,_cible,e,_import)){
                //protected against
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.IMMU_TYPE_ATT_CIBLE,DataBase.SEP_BETWEEN_KEYS,e),_import.getTrueString());
            }else{
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.IMMU_TYPE_ATT_CIBLE,DataBase.SEP_BETWEEN_KEYS,e),_import.getFalseString());
            }
        }
        boolean aucunBoostPossible_=true;
        byte maxBoost_=(byte)_import.getMaxBoost();
        for(Statistic c:creatureCbtCible_.getStatisBoost().getKeys()){
            byte boost_=creatureCbtCible_.getStatisBoost().getVal(c);
            if(boost_<maxBoost_){
                aucunBoostPossible_=false;
                break;
            }
        }
        if(aucunBoostPossible_){
            //no possible boost
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.AUCUN_BOOST_POSSIBLE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.AUCUN_BOOST_POSSIBLE),_import.getFalseString());
        }
        effetsIndiv_ = _import.getVarParamsMove(Fight.LANCEUR_EFFET);
        for(String c:effetsIndiv_){
            if (creatureCbtLanceur_.getEnabledMoves().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMoves().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtLanceur_.getEnabledMovesProt().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMovesProt().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtLanceur_.getEnabledMovesUnprot().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMovesUnprot().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtLanceur_.getEnabledMovesEndRound().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMovesEndRound().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtLanceur_.getEnabledMovesForAlly().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMovesForAlly().getVal(c) == BoolVal.TRUE){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (creatureCbtLanceur_.getEnabledMovesConstChoices().contains(c)) {
                if(creatureCbtLanceur_.getEnabledMovesConstChoices().getVal(c).isEnabled()){
                    //enabled effect
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                }else{
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (StringUtil.contains(_import.getMovesActingMoveUses(), c)) {
                boolean enabledMove_ = false;
                for (MoveTeamPosition m: creatureCbtLanceur_.getTrackingMoves().getKeys()) {
                    if (!StringUtil.quickEq(m.getMove(), c)) {
                        continue;
                    }
                    if (!creatureCbtLanceur_.getTrackingMoves().getVal(m).getActivity().isEnabled()) {
                        continue;
                    }
                    enabledMove_ = true;
                }
                if (enabledMove_) {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                } else {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
                continue;
            }
            if (StringUtil.quickEq(creatureCbtLanceur_.getLastSuccessfulMove(), c)) {
                if (creatureCbtLanceur_.isSuccessfulMove()) {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getTrueString());
                } else {
                    variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
                }
            } else {
                variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.LANCEUR_EFFET,DataBase.SEP_BETWEEN_KEYS,c),_import.getFalseString());
            }
        }
        if (!creatureCbtLanceur_.resistingTypes(_import).isEmpty()) {
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TYPES_ATTAQUES_RES_VIDE),_import.getFalseString());
        } else {
            //types of moves
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.TYPES_ATTAQUES_RES_VIDE),_import.getTrueString());
        }
        int nbPartenairesArriere_ = FightOrder.nbBackPartners(_fight, _lanceur);
        int nbPartenairesTerrain_ = FightOrder.nbFrontPartners(_fight, _lanceur);
        int nbPartenaires_ = nbPartenairesArriere_ + nbPartenairesTerrain_;
        if(nbPartenaires_==0){
            //not partner
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE),_import.getFalseString());
        }
        if(nbPartenairesArriere_==0){
            //not back partner
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE_ARRIERE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE_ARRIERE),_import.getFalseString());
        }
        if(nbPartenairesTerrain_==0){
            //not front partner
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE_TERRAIN),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_PARTENAIRE_TERRAIN),_import.getFalseString());
        }
        if(creatureCbtLanceur_.getNbRounds().isZero()){
            //back
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_TOUR_TERRAIN),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.PAS_TOUR_TERRAIN),_import.getFalseString());
        }
        if(creatureCbtLanceur_.estAssexue()||creatureCbtCible_.estAssexue()){
            //opposite genders
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.EXISTE_GENRE_ASSEXUE),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.EXISTE_GENRE_ASSEXUE),_import.getFalseString());
        }
        if(creatureCbtLanceur_.getCurrentGender()==creatureCbtCible_.getCurrentGender()){
            //same genders
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.GENRES_EGAUX),_import.getTrueString());
        }else{
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.GENRES_EGAUX),_import.getFalseString());
        }
        _fight.setEnabledMessages(true);
        return variables_;
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
        StringList coeffTypes_=_import.getVarParamsMove(Fight.COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT);
        for(String e:coeffTypes_){
            Rate coeffEnvoye_=DataBase.defRateProduct();
            for(String e2_:creatureCbtLanceur_.getTypes()){
                coeffEnvoye_.multiplyBy(_import.getTableTypes().getVal(new TypesDuo(e,e2_)));
            }
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT,DataBase.SEP_BETWEEN_KEYS,e),coeffEnvoye_.toNumberString());
        }
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
        if (onlyOne_) {
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _fighterDerJoue), Fight.ONE);
        } else {
            _variables.put(StringUtil.concat(DataBase.VAR_PREFIX, _fighterDerJoue), Fight.ZERO);
        }
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
