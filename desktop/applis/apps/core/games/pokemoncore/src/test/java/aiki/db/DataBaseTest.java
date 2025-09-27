package aiki.db;

import aiki.fight.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.status.*;
import aiki.fight.util.*;
import aiki.game.fight.*;
import aiki.game.params.enums.*;
import aiki.instances.*;
import aiki.util.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.*;
import org.junit.Test;


public class DataBaseTest extends EquallablePkUtil {

    @Test
    public void initBase1() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEF_MOVE,"_");
        assertEq("_",data_.getDefMove());
    }

    @Test
    public void initBase2() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_BOOST,"_");
        assertEq("_",data_.getRateBoost());
    }

    @Test
    public void initBase3() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_BOOST_CRITICAL_HIT,"_");
        assertEq("_",data_.getRateBoostCriticalHit());
    }

    @Test
    public void initBase4() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_FLEEING,"_");
        assertEq("_",data_.getRateFleeing());
    }

    @Test
    public void initBase5() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_CATCHING,"_");
        assertEq("_",data_.getRateCatching());
    }

    @Test
    public void initBase6() {
        DataBase data_ = newData();
        data_.initValue(DataBase.BALL_DEF,"_");
        assertEq("_",data_.getBallDef());
    }

    @Test
    public void initBase7() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEFAULT_EGG_GROUP,"_");
        assertEq("_",data_.getDefaultEggGroup());
    }

    @Test
    public void initBase8() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DAMAGE_FORMULA,"_");
        assertEq("_",data_.getDamageFormula());
    }

    @Test
    public void initBase9() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEF_CAT,"_");
        assertEq("_",data_.getDefCategory());
    }

    @Test
    public void initBase10() {
        DataBase data_ = newData();
        data_.initValue("","_");
        assertEq("",data_.getDefMove());
        assertEq("",data_.getRateBoost());
        assertEq("",data_.getRateBoostCriticalHit());
        assertEq("",data_.getRateCatching());
        assertEq("",data_.getRateFleeing());
        assertEq("",data_.getBallDef());
        assertEq("",data_.getDefaultEggGroup());
        assertEq("",data_.getDamageFormula());
        assertEq("",data_.getDefCategory());
    }


    @Test
    public void initBase11() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.PREFIX_KEY,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.PREFIX_KEY));
        assertEq("0",data_.prefixVar());
    }

    @Test
    public void initBase12() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NIVEAU));
        assertEq("0",data_.niveau());
    }

    @Test
    public void initBase13() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LEVEL_LOOSER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LEVEL_LOOSER));
        assertEq("0",data_.levelLooser());
    }

    @Test
    public void initBase14() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LEVEL_WINNER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LEVEL_WINNER));
        assertEq("0",data_.levelWinner());
    }

    @Test
    public void initBase15() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_NIVEAU));
        assertEq("0",data_.fighterNiveau());
    }

    @Test
    public void initBase16() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_NIVEAU));
        assertEq("0",data_.lanceurNiveau());
    }

    @Test
    public void initBase17() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_NIVEAU));
        assertEq("0",data_.cibleNiveau());
    }

    @Test
    public void initBase18() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_NIVEAU));
        assertEq("0",data_.pkSauvageNiveau());
    }

    @Test
    public void initBase19() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_NIVEAU,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_NIVEAU));
        assertEq("0",data_.pkUtNiveau());
    }

    @Test
    public void initBase20() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_BOOST,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_BOOST));
        assertEq("0",data_.boost());
    }

    @Test
    public void initBase21() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_POWER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_POWER));
        assertEq("0",data_.power());
    }

    @Test
    public void initBase22() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_ATTACK,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_ATTACK));
        assertEq("0",data_.attack());
    }

    @Test
    public void initBase23() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_DEFENSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_DEFENSE));
        assertEq("0",data_.defense());
    }

    @Test
    public void initBase24() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_BASE_CAPT_PK,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_BASE_CAPT_PK));
        assertEq("0",data_.baseCaptPk());
    }

    @Test
    public void initBase25() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_RATE_BALL_STATUS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_RATE_BALL_STATUS));
        assertEq("0",data_.rateBallStatus());
    }

    @Test
    public void initBase26() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FOE_PK_MAX_HP,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FOE_PK_MAX_HP));
        assertEq("0",data_.foePkMaxHp());
    }

    @Test
    public void initBase27() {
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FOE_PK_REMOTE_HP,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FOE_PK_REMOTE_HP));
        assertEq("0",data_.foePkRemoteHp());
    }
    @Test
    public void initBase28(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_NB_UTILISATION));
        assertEq("0",data_.cibleNbUtilisation());
    }

    @Test
    public void initBase29(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_NB_UTILISATION));
        assertEq("0",data_.lanceurNbUtilisation());
    }

    @Test
    public void initBase30(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_NB_UTILISATION));
        assertEq("0",data_.fighterNbUtilisation());
    }

    @Test
    public void initBase31(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_EQUIPE_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_EQUIPE_NB_UTILISATION));
        assertEq("0",data_.equipeNbUtilisation());
    }

    @Test
    public void initBase32(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_EQUIPE_ADV_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_EQUIPE_ADV_NB_UTILISATION));
        assertEq("0",data_.equipeAdvNbUtilisation());
    }

    @Test
    public void initBase33(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_UTILI_ATT_EQ_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_UTILI_ATT_EQ_TOUR));
        assertEq("0",data_.nbUtiliAttEqTour());
    }

    @Test
    public void initBase34(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_PV_RESTANTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_PV_RESTANTS));
        assertEq("0",data_.lanceurPvRestants());
    }

    @Test
    public void initBase35(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_PV_RESTANTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_PV_RESTANTS));
        assertEq("0",data_.ciblePvRestants());
    }

    @Test
    public void initBase36(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_PV_MAX,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_PV_MAX));
        assertEq("0",data_.lanceurPvMax());
    }

    @Test
    public void initBase37(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_PV_MAX,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_PV_MAX));
        assertEq("0",data_.ciblePvMax());
    }

    @Test
    public void initBase38(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_STATIS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_STATIS));
        assertEq("0",data_.lanceurStatis());
    }

    @Test
    public void initBase39(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_STATIS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_STATIS));
        assertEq("0",data_.cibleStatis());
    }

    @Test
    public void initBase40(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_BOOST,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_BOOST));
        assertEq("0",data_.lanceurBoost());
    }

    @Test
    public void initBase41(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_BOOST,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_BOOST));
        assertEq("0",data_.cibleBoost());
    }

    @Test
    public void initBase42(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_SOMME_BOOST_POS_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_SOMME_BOOST_POS_CIBLE));
        assertEq("0",data_.sommeBoostPosCible());
    }

    @Test
    public void initBase43(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_ATTAQUES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_ATTAQUES));
        assertEq("0",data_.cibleAttaques());
    }

    @Test
    public void initBase44(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_ATTAQUE_CHOISIE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_ATTAQUE_CHOISIE));
        assertEq("0",data_.cibleAttaqueChoisie());
    }

    @Test
    public void initBase45(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_ATTAQUES_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_ATTAQUES_TYPES));
        assertEq("0",data_.cibleAttaquesTypes());
    }

    @Test
    public void initBase46(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_CLONE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_CLONE));
        assertEq("0",data_.cibleClone());
    }

    @Test
    public void initBase47(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS));
        assertEq("0",data_.cibleDegatsRecus());
    }

    @Test
    public void initBase48(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOTAL,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOTAL));
        assertEq("0",data_.cibleDegatsRecusTotal());
    }

    @Test
    public void initBase49(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOUR));
        assertEq("0",data_.cibleDegatsRecusTour());
    }

    @Test
    public void initBase50(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOTAL_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DEGATS_RECUS_TOTAL_TOUR));
        assertEq("0",data_.cibleDegatsRecusTotalTour());
    }

    @Test
    public void initBase51(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DISPARAIT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DISPARAIT));
        assertEq("0",data_.cibleDisparait());
    }

    @Test
    public void initBase52(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_DER_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_DER_JOUE));
        assertEq("0",data_.cibleDerJoue());
    }

    @Test
    public void initBase53(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_JOUE));
        assertEq("0",data_.cibleJoue());
    }

    @Test
    public void initBase54(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_MASSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_MASSE));
        assertEq("0",data_.cibleMasse());
    }

    @Test
    public void initBase55(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_TAILLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_TAILLE));
        assertEq("0",data_.cibleTaille());
    }

    @Test
    public void initBase56(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_CAPACITE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_CAPACITE));
        assertEq("0",data_.cibleCapacite());
    }

    @Test
    public void initBase57(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_OBJET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_OBJET));
        assertEq("0",data_.cibleObjet());
    }

    @Test
    public void initBase58(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_STATUTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_STATUTS));
        assertEq("0",data_.cibleStatuts());
    }

    @Test
    public void initBase59(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_TYPES));
        assertEq("0",data_.cibleTypes());
    }

    @Test
    public void initBase60(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_NOM,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_NOM));
        assertEq("0",data_.cibleNom());
    }

    @Test
    public void initBase61(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_PP,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_PP));
        assertEq("0",data_.ciblePp());
    }

    @Test
    public void initBase62(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_CIBLE));
        assertEq("0",data_.nbKoEquipeCible());
    }

    @Test
    public void initBase63(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_CIBLE));
        assertEq("0",data_.nbKoEquipeAdvCible());
    }

    @Test
    public void initBase64(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_CIBLE));
        assertEq("0",data_.coeffEffBaseTypesCible());
    }

    @Test
    public void initBase65(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_TOUR));
        assertEq("0",data_.nbTour());
    }

    @Test
    public void initBase66(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_SOMME_BOOST_POS_LANCEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_SOMME_BOOST_POS_LANCEUR));
        assertEq("0",data_.sommeBoostPosLanceur());
    }

    @Test
    public void initBase67(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_TOUR_GLOBAL,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_TOUR_GLOBAL));
        assertEq("0",data_.nbTourGlobal());
    }

    @Test
    public void initBase68(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_ATTAQUES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_ATTAQUES));
        assertEq("0",data_.lanceurAttaques());
    }

    @Test
    public void initBase69(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_ATTAQUE_CHOISIE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_ATTAQUE_CHOISIE));
        assertEq("0",data_.lanceurAttaqueChoisie());
    }

    @Test
    public void initBase70(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_ATTAQUES_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_ATTAQUES_TYPES));
        assertEq("0",data_.lanceurAttaquesTypes());
    }

    @Test
    public void initBase71(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_CLONE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_CLONE));
        assertEq("0",data_.lanceurClone());
    }

    @Test
    public void initBase72(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS));
        assertEq("0",data_.lanceurDegatsRecus());
    }

    @Test
    public void initBase73(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOTAL,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOTAL));
        assertEq("0",data_.lanceurDegatsRecusTotal());
    }

    @Test
    public void initBase74(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOUR));
        assertEq("0",data_.lanceurDegatsRecusTour());
    }

    @Test
    public void initBase75(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOTAL_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DEGATS_RECUS_TOTAL_TOUR));
        assertEq("0",data_.lanceurDegatsRecusTotalTour());
    }

    @Test
    public void initBase76(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DISPARAIT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DISPARAIT));
        assertEq("0",data_.lanceurDisparait());
    }

    @Test
    public void initBase77(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_DER_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_DER_JOUE));
        assertEq("0",data_.lanceurDerJoue());
    }

    @Test
    public void initBase78(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_JOUE));
        assertEq("0",data_.lanceurJoue());
    }

    @Test
    public void initBase79(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_MASSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_MASSE));
        assertEq("0",data_.lanceurMasse());
    }

    @Test
    public void initBase80(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_TAILLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_TAILLE));
        assertEq("0",data_.lanceurTaille());
    }

    @Test
    public void initBase81(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_CAPACITE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_CAPACITE));
        assertEq("0",data_.lanceurCapacite());
    }

    @Test
    public void initBase82(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_OBJET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_OBJET));
        assertEq("0",data_.lanceurObjet());
    }

    @Test
    public void initBase83(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_STATUTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_STATUTS));
        assertEq("0",data_.lanceurStatuts());
    }

    @Test
    public void initBase84(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_TYPES));
        assertEq("0",data_.lanceurTypes());
    }

    @Test
    public void initBase85(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_NOM,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_NOM));
        assertEq("0",data_.lanceurNom());
    }

    @Test
    public void initBase86(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_LANCEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_LANCEUR));
        assertEq("0",data_.coeffEffBaseTypesLanceur());
    }

    @Test
    public void initBase87(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_PP,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_PP));
        assertEq("0",data_.lanceurPp());
    }

    @Test
    public void initBase88(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_GENRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_GENRE));
        assertEq("0",data_.cibleGenre());
    }

    @Test
    public void initBase89(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_GENRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_GENRE));
        assertEq("0",data_.lanceurGenre());
    }

    @Test
    public void initBase90(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_FIGHTER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_FIGHTER));
        assertEq("0",data_.coeffEffBaseTypesFighter());
    }

    @Test
    public void initBase91(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_BONHEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_BONHEUR));
        assertEq("0",data_.fighterBonheur());
    }

    @Test
    public void initBase92(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_PP,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_PP));
        assertEq("0",data_.fighterPp());
    }

    @Test
    public void initBase93(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_GENRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_GENRE));
        assertEq("0",data_.fighterGenre());
    }

    @Test
    public void initBase94(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_PV_RESTANTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_PV_RESTANTS));
        assertEq("0",data_.fighterPvRestants());
    }

    @Test
    public void initBase95(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_PV_MAX,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_PV_MAX));
        assertEq("0",data_.fighterPvMax());
    }

    @Test
    public void initBase96(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_STATIS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_STATIS));
        assertEq("0",data_.fighterStatis());
    }

    @Test
    public void initBase97(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_BOOST,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_BOOST));
        assertEq("0",data_.fighterBoost());
    }

    @Test
    public void initBase98(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_SOMME_BOOST_POS_FIGHTER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_SOMME_BOOST_POS_FIGHTER));
        assertEq("0",data_.sommeBoostPosFighter());
    }

    @Test
    public void initBase99(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_ATTAQUES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_ATTAQUES));
        assertEq("0",data_.fighterAttaques());
    }

    @Test
    public void initBase100(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_ATTAQUE_CHOISIE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_ATTAQUE_CHOISIE));
        assertEq("0",data_.fighterAttaqueChoisie());
    }

    @Test
    public void initBase101(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_ATTAQUES_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_ATTAQUES_TYPES));
        assertEq("0",data_.fighterAttaquesTypes());
    }

    @Test
    public void initBase102(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_CLONE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_CLONE));
        assertEq("0",data_.fighterClone());
    }

    @Test
    public void initBase103(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS));
        assertEq("0",data_.fighterDegatsRecus());
    }

    @Test
    public void initBase104(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOTAL,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOTAL));
        assertEq("0",data_.fighterDegatsRecusTotal());
    }

    @Test
    public void initBase105(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOUR));
        assertEq("0",data_.fighterDegatsRecusTour());
    }

    @Test
    public void initBase106(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOTAL_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DEGATS_RECUS_TOTAL_TOUR));
        assertEq("0",data_.fighterDegatsRecusTotalTour());
    }

    @Test
    public void initBase107(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DISPARAIT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DISPARAIT));
        assertEq("0",data_.fighterDisparait());
    }

    @Test
    public void initBase108(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_DER_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_DER_JOUE));
        assertEq("0",data_.fighterDerJoue());
    }

    @Test
    public void initBase109(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_JOUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_JOUE));
        assertEq("0",data_.fighterJoue());
    }

    @Test
    public void initBase110(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_MASSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_MASSE));
        assertEq("0",data_.fighterMasse());
    }

    @Test
    public void initBase111(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_TAILLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_TAILLE));
        assertEq("0",data_.fighterTaille());
    }

    @Test
    public void initBase112(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_CAPACITE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_CAPACITE));
        assertEq("0",data_.fighterCapacite());
    }

    @Test
    public void initBase113(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_OBJET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_OBJET));
        assertEq("0",data_.fighterObjet());
    }

    @Test
    public void initBase114(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_STATUTS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_STATUTS));
        assertEq("0",data_.fighterStatuts());
    }

    @Test
    public void initBase115(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_TYPES));
        assertEq("0",data_.fighterTypes());
    }

    @Test
    public void initBase116(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_FIGHTER_NOM,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_FIGHTER_NOM));
        assertEq("0",data_.fighterNom());
    }

    @Test
    public void initBase117(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_ATTAQUE_CATEGORIE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_ATTAQUE_CATEGORIE));
        assertEq("0",data_.attaqueCategorie());
    }

    @Test
    public void initBase118(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_ATTAQUE_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_ATTAQUE_TYPES));
        assertEq("0",data_.attaqueTypes());
    }

    @Test
    public void initBase119(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_ATTAQUE_NOM,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_ATTAQUE_NOM));
        assertEq("0",data_.attaqueNom());
    }

    @Test
    public void initBase120(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PUISSANCE_BASE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PUISSANCE_BASE));
        assertEq("0",data_.puissanceBase());
    }

    @Test
    public void initBase121(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COEFF_EFF,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COEFF_EFF));
        assertEq("0",data_.coeffEff());
    }

    @Test
    public void initBase122(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_UTILISATION_CONSECUTIF,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_UTILISATION_CONSECUTIF));
        assertEq("0",data_.nbUtilisationConsecutif());
    }

    @Test
    public void initBase123(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CLIMATS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CLIMATS));
        assertEq("0",data_.climats());
    }

    @Test
    public void initBase124(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LIEU_COMBAT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LIEU_COMBAT));
        assertEq("0",data_.lieuCombat());
    }

    @Test
    public void initBase125(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_FLEES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_FLEES));
        assertEq("0",data_.nbFlees());
    }

    @Test
    public void initBase126(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_BONHEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_BONHEUR));
        assertEq("0",data_.cibleBonheur());
    }

    @Test
    public void initBase127(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_BONHEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_BONHEUR));
        assertEq("0",data_.lanceurBonheur());
    }

    @Test
    public void initBase128(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_TEMPS_TOUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_TEMPS_TOUR));
        assertEq("0",data_.tempsTour());
    }

    @Test
    public void initBase129(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_RATE_EFF_MOVE_AGAINST_TARGET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_RATE_EFF_MOVE_AGAINST_TARGET));
        assertEq("0",data_.rateEffMoveAgainstTarget());
    }

    @Test
    public void initBase130(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_COMBATTANTS_TERRAIN,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_COMBATTANTS_TERRAIN));
        assertEq("0",data_.nbCombattantsTerrain());
    }

    @Test
    public void initBase131(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_LANCEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_LANCEUR));
        assertEq("0",data_.nbKoEquipeLanceur());
    }

    @Test
    public void initBase132(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_LANCEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_LANCEUR));
        assertEq("0",data_.nbKoEquipeAdvLanceur());
    }

    @Test
    public void initBase133(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_FIGHTER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_FIGHTER));
        assertEq("0",data_.nbKoEquipeFighter());
    }

    @Test
    public void initBase134(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_FIGHTER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_NB_KO_EQUIPE_ADV_FIGHTER));
        assertEq("0",data_.nbKoEquipeAdvFighter());
    }

    @Test
    public void initBase135(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_POSSEDE_STATUT_RELATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_POSSEDE_STATUT_RELATION));
        assertEq("0",data_.ciblePossedeStatutRelation());
    }

    @Test
    public void initBase136(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_IMMU_TYPE_ATT_COMBATTANT_ENTRANT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_IMMU_TYPE_ATT_COMBATTANT_ENTRANT));
        assertEq("0",data_.immuTypeAttCombattantEntrant());
    }

    @Test
    public void initBase137(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_CIBLE_EFFET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_CIBLE_EFFET));
        assertEq("0",data_.cibleEffet());
    }

    @Test
    public void initBase138(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_PP_ATTAQUE_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_PP_ATTAQUE_CIBLE));
        assertEq("0",data_.pasPpAttaqueCible());
    }

    @Test
    public void initBase139(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_UTILIS_ATTAQUE_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_UTILIS_ATTAQUE_CIBLE));
        assertEq("0",data_.pasUtilisAttaqueCible());
    }

    @Test
    public void initBase140(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_IMMU_TYPE_ATT_CIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_IMMU_TYPE_ATT_CIBLE));
        assertEq("0",data_.immuTypeAttCible());
    }

    @Test
    public void initBase141(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_AUCUN_BOOST_POSSIBLE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_AUCUN_BOOST_POSSIBLE));
        assertEq("0",data_.aucunBoostPossible());
    }

    @Test
    public void initBase142(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_LANCEUR_EFFET,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_LANCEUR_EFFET));
        assertEq("0",data_.lanceurEffet());
    }

    @Test
    public void initBase143(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_TYPES_ATTAQUES_RES_VIDE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_TYPES_ATTAQUES_RES_VIDE));
        assertEq("0",data_.typesAttaquesResVide());
    }

    @Test
    public void initBase144(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_ATTAQUE_INVOC,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_ATTAQUE_INVOC));
        assertEq("0",data_.pasAttaqueInvoc());
    }

    @Test
    public void initBase145(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_ATTAQUES_COPIABLES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_ATTAQUES_COPIABLES));
        assertEq("0",data_.pasAttaquesCopiables());
    }

    @Test
    public void initBase146(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_PARTENAIRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_PARTENAIRE));
        assertEq("0",data_.pasPartenaire());
    }

    @Test
    public void initBase147(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_PARTENAIRE_ARRIERE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_PARTENAIRE_ARRIERE));
        assertEq("0",data_.pasPartenaireArriere());
    }

    @Test
    public void initBase148(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_PARTENAIRE_TERRAIN,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_PARTENAIRE_TERRAIN));
        assertEq("0",data_.pasPartenaireTerrain());
    }

    @Test
    public void initBase149(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PAS_TOUR_TERRAIN,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PAS_TOUR_TERRAIN));
        assertEq("0",data_.pasTourTerrain());
    }

    @Test
    public void initBase150(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_EXISTE_GENRE_ASSEXUE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_EXISTE_GENRE_ASSEXUE));
        assertEq("0",data_.existeGenreAssexue());
    }

    @Test
    public void initBase151(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_GENRES_EGAUX,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_GENRES_EGAUX));
        assertEq("0",data_.genresEgaux());
    }

    @Test
    public void initBase152(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_DEJA_CAPTURE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_DEJA_CAPTURE));
        assertEq("0",data_.dejaCapture());
    }

    @Test
    public void initBase153(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_MASSE_MOYENNE_PK,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_MASSE_MOYENNE_PK));
        assertEq("0",data_.masseMoyennePk());
    }

    @Test
    public void initBase154(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_GENRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_GENRE));
        assertEq("0",data_.pkUtGenre());
    }

    @Test
    public void initBase155(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_MASSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_MASSE));
        assertEq("0",data_.pkUtMasse());
    }

    @Test
    public void initBase156(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_VITESSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_VITESSE));
        assertEq("0",data_.pkUtVitesse());
    }

    @Test
    public void initBase157(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_TYPES_BASE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_TYPES_BASE));
        assertEq("0",data_.pkUtTypesBase());
    }

    @Test
    public void initBase158(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_UT_PIERRES_EVOS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_UT_PIERRES_EVOS));
        assertEq("0",data_.pkUtPierresEvos());
    }

    @Test
    public void initBase159(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_GENRE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_GENRE));
        assertEq("0",data_.pkSauvageGenre());
    }

    @Test
    public void initBase160(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_MASSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_MASSE));
        assertEq("0",data_.pkSauvageMasse());
    }

    @Test
    public void initBase161(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_VITESSE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_VITESSE));
        assertEq("0",data_.pkSauvageVitesse());
    }

    @Test
    public void initBase162(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_TYPES_BASE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_TYPES_BASE));
        assertEq("0",data_.pkSauvageTypesBase());
    }

    @Test
    public void initBase163(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_PK_SAUVAGE_PIERRES_EVOS,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_PK_SAUVAGE_PIERRES_EVOS));
        assertEq("0",data_.pkSauvagePierresEvos());
    }

    @Test
    public void initBase164(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COMBATTANT_ENTRANT_CLONE,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COMBATTANT_ENTRANT_CLONE));
        assertEq("0",data_.combattantEntrantClone());
    }

    @Test
    public void initBase165(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COMBATTANT_ENTRANT_TYPES,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COMBATTANT_ENTRANT_TYPES));
        assertEq("0",data_.combattantEntrantTypes());
    }

    @Test
    public void initBase166(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT));
        assertEq("0",data_.coeffEffBaseTypesCombattantEntrant());
    }

    @Test
    public void initBase167(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION));
        assertEq("0",data_.equipeAdvCombattantEntrantNbUtilisation());
    }

    @Test
    public void initBase168(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_IMMU_TYPE_ATT_LANCEUR,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_IMMU_TYPE_ATT_LANCEUR));
        assertEq("0",data_.immuTypeAttLanceur());
    }

    @Test
    public void initBase169(){
        DataBase data_ = newData();
        data_.initValue(DataBaseConstants.KEY_IMMU_TYPE_ATT_FIGHTER,"0");
        assertEq("0",data_.retValueOther(DataBaseConstants.KEY_IMMU_TYPE_ATT_FIGHTER));
        assertEq("0",data_.immuTypeAttFighter());
    }
    @Test
    public void test() {
        DataBase data_ = InitializationDataBase.initDataBase();
        assertTrue(!data_.isError());
        assertEq(0,data_.getSafeAbility("").getEffectEndRound().size());
        assertEq(0,data_.getSafeAbility("").getEffectSending().size());
        assertEq(1,MessagesDataBaseConstants.trTargets(tr(DataBase.DEF_TARGET_NOTHING,"")).size());
        assertEq(1,MessagesDataBaseConstants.trStat(tr(DataBase.DEF_STAT_SPEED,"")).size());
        assertEq(1,MessagesDataBaseConstants.trEnv(tr(DataBase.DEF_ENV_NOTHING,"")).size());
        assertEq(1,MessagesDataBaseConstants.trDiffLaw(tr(DataBase.DEF_UNIFORME,"")).size());
        assertEq(1,MessagesDataBaseConstants.trDiffWinPts(tr(DataBase.DEF_FACILE,"")).size());
        assertEq(1,MessagesDataBaseConstants.trBooleans(tr(DataBase.DEF_SEL_BOOL_YES_AND_NO,"")).size());
        assertEq(1,MessagesDataBaseConstants.trGenders(tr(DataBase.DEF_GENDER_NO_GENDER,"")).size());
        data_.completeMoveTutors();
        data_.setView(data_.computeLearn());
        assertFalse(data_.getView().isEmpty());
    }

    @Test
    public void test0() {
        DataBase data_ = newData();
        data_.initTranslations();
        data_.setLanguages(new StringList());
        data_.setLanguage("");
        data_.setMiniPk(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setMaxiPkBack(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setMaxiPkFront(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setMiniItems(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setFrontHeros(new ImageHeroKeys());
        data_.setBackHeros(new ImageHeroKeys());
        data_.setOverWorldHeros(new ImageHeroKeys());
        data_.setTrainers(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setPeople(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setTypesColors(new StringMap<String>());
        data_.setTypesImages(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setLinks(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setImages(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setImagesTiles(new StringMap<ScreenCoordssInt>());
        data_.setMiniMap(new StringMap<ImageArrayBaseSixtyFour>());
        data_.setCombos(new Combos());
        data_.setConstNum(new StringMap<Rate>());
        data_.setRateBoostCriticalHit("");
        data_.setRateBoost("");
        data_.setRateCatching("");
        data_.setRateFleeing("");
        data_.setRates(new IdMap<DifficultyWinPointsFight, String>());
        data_.setBallDef("");
        data_.setDefaultEggGroup("");
        data_.setDefMove("");
        data_.setDamageFormula("");
        data_.setDefCategory("");
        data_.setExpGrowth(new IdMap<ExpType, String>());
        data_.setTableTypes(new TypesDuos());
        data_.setTypes(new StringList());
        data_.setLawsDamageRate(new IdMap<DifficultyModelLaw, LawNumber>());
        data_.setMaxHeightPk(0);
        data_.setMaxWidthPk(0);
        data_.setDisplayLanguages(new StringMap<String>());
        data_.setMessagesFight(new StringMap<String>());
        data_.setMessagesFighter(new StringMap<String>());
        data_.setMessagesGame(new StringMap<String>());
        data_.setMessagesPlayer(new StringMap<String>());
        data_.setMessagesPokemonPlayer(new StringMap<String>());
        data_.setMessagesTeam(new StringMap<String>());
        data_.setMessages(data_);
        data_.getExpGrowth(ExpType.P);
        assertEq(DataBase.EMPTY_STRING,data_.getRateCatching());
        assertEq(DataBase.EMPTY_STRING,data_.getRateFleeing());
        assertEq(DataBase.EMPTY_STRING,data_.getDefaultEggGroup());
        assertEq(DataBase.EMPTY_STRING,data_.getBallDef());

        assertEq(DataBase.EMPTY_STRING, data_.getBallDef());
        assertEq(DataBase.EMPTY_STRING,data_.getDefMove());
        assertEq(0,data_.getImagesTiles().size());
        assertEq(0,data_.getDisplayLanguages().size());
        assertEq(0,data_.getLanguages().size());
        data_.getEndGameImage().getBase();
        data_.getAnimAbsorb();
        assertTrue(!data_.isError());
    }
//
//    @Test
//    public void toUpperCaseTest() {
//        assertEq("Aa!<>_{}[]?".length(), DataBase.toUpperCase("Aa!<>_{}[]?").length());
//    }
//    @Test
//    public void checkCaseOfFiles1Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
//    }
//
//    @Test
//    public void checkCaseOfFiles2Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        files_.add("file_one");
//        files_.add("file_TWO");
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
//    }
//
//    @Test
//    public void checkCaseOfFiles3Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        files_.add("file_one");
//        files_.add("file_ONE");
//        files_.add("file_TWO");
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
//        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
//    }
//
//    @Test
//    public void checkCaseOfFiles4Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        files_.add("file_one");
//        files_.add("file_ONE");
//        files_.add("File_ONE");
//        files_.add("file_TWO");
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
//        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
//    }
//
//    @Test
//    public void checkCaseOfFiles5Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        files_.add("!file_one");
//        files_.add("!file_ONE");
//        files_.add("!File_ONE");
//        files_.add("!file_TWO");
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
//        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/!FILE_ONE"));
//    }
//
//    @Test
//    public void checkCaseOfFiles6Test() {
//        DataBase data_ = newData();
//        data_.initializeMembers();
//        StringList files_ = new StringList();
//        files_.add("<file_one");
//        files_.add("<file_ONE");
//        files_.add("<File_ONE");
//        files_.add("<file_TWO");
//        data_.checkCaseOfFiles("folder", files_);
//        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
//        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/<FILE_ONE"));
//        DataBase.toUpperCase("Aa!<>{}[]?");
//    }

    @Test
    public void isVariableTest() {
        assertTrue(!DataBase.isVariable(VAR_PREFIX, VAR_PREFIX));
    }
    @Test
    public void validateOtherConstants1(){
        DataBase data_ = newData();
        data_.prefixVar("MY_VAR_");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.VAR_DEF,data_.prefixVar());
    }
    @Test
    public void validateOtherConstants2(){
        DataBase data_ = newData();
        data_.prefixVar("_MY_VAR");
        data_.validateOtherConstants();
        assertEq("_MY_VAR",data_.prefixVar());
    }
    @Test
    public void validateOtherConstants3(){
        DataBase data_ = newData();
        data_.prefixVar("0");
        data_.validateOtherConstants();
        data_.prefixVar("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.VAR_DEF,data_.prefixVar());
    }
    @Test
    public void validateOtherConstants4(){
        DataBase data_ = newData();
        data_.prefixVar("MY_VAR");
        data_.validateOtherConstants();
        assertEq("MY_VAR",data_.prefixVar());
    }
    @Test
    public void validateOtherConstants5(){
        DataBase data_ = newData();
        data_.niveau("_0");
        data_.levelLooser("_1");
        data_.levelWinner("_2");
        data_.validateOtherConstants();
        data_.niveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_NIVEAU,data_.niveau());
    }
    @Test
    public void validateOtherConstants6(){
        DataBase data_ = newData();
        data_.levelLooser("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_LEVEL_LOOSER,data_.levelLooser());
    }
    @Test
    public void validateOtherConstants7(){
        DataBase data_ = newData();
        data_.levelWinner("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_LEVEL_WINNER,data_.levelWinner());
    }
    @Test
    public void validateOtherConstants8(){
        DataBase data_ = newData();
        data_.fighterNiveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_FIGHTER_NIVEAU,data_.fighterNiveau());
    }
    @Test
    public void validateOtherConstants9(){
        DataBase data_ = newData();
        data_.cibleNiveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_CIBLE_NIVEAU,data_.cibleNiveau());
    }
    @Test
    public void validateOtherConstants10(){
        DataBase data_ = newData();
        data_.lanceurNiveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU,data_.lanceurNiveau());
    }
    @Test
    public void validateOtherConstants11(){
        DataBase data_ = newData();
        data_.pkSauvageNiveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU,data_.pkSauvageNiveau());
    }
    @Test
    public void validateOtherConstants12(){
        DataBase data_ = newData();
        data_.pkUtNiveau("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_PK_UT_NIVEAU,data_.pkUtNiveau());
    }
    @Test
    public void validateOtherConstants13(){
        DataBase data_ = newData();
        data_.levelLooser("_0");
        data_.levelWinner("_0");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_LEVEL_LOOSER,data_.levelLooser());
        assertEq(MessagesDataBaseConstants.DEF_LEVEL_WINNER,data_.levelWinner());
    }
    @Test
    public void validateOtherConstants14(){
        DataBase data_ = newData();
        data_.cibleNiveau("_0");
        data_.lanceurNiveau("_0");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_CIBLE_NIVEAU,data_.cibleNiveau());
        assertEq(MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU,data_.lanceurNiveau());
    }
    @Test
    public void validateOtherConstants15(){
        DataBase data_ = newData();
        data_.pkSauvageNiveau("_0");
        data_.pkUtNiveau("_0");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU,data_.pkSauvageNiveau());
        assertEq(MessagesDataBaseConstants.DEF_PK_UT_NIVEAU,data_.pkUtNiveau());
    }
    @Test
    public void validateOtherConstants16(){
        DataBase data_ = newData();
        data_.boost("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_BOOST,data_.boost());
    }
    @Test
    public void validateOtherConstants17(){
        DataBase data_ = newData();
        data_.power("");
        data_.attack("");
        data_.defense("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_ATTACK,data_.attack());
        assertEq(MessagesDataBaseConstants.DEF_DEFENSE,data_.defense());
        assertEq(MessagesDataBaseConstants.DEF_POWER,data_.power());
    }
    @Test
    public void validateOtherConstants18(){
        DataBase data_ = newData();
        data_.baseCaptPk("");
        data_.rateBallStatus("");
        data_.foePkMaxHp("");
        data_.foePkRemoteHp("");
        data_.validateOtherConstants();
        assertEq(MessagesDataBaseConstants.DEF_BASE_CAPT_PK,data_.baseCaptPk());
        assertEq(MessagesDataBaseConstants.DEF_RATE_BALL_STATUS,data_.rateBallStatus());
        assertEq(MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP,data_.foePkMaxHp());
        assertEq(MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP,data_.foePkRemoteHp());
    }
    @Test
    public void completeMembers1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("SPECIAL");
        moveDamage_.setDirect(false);
        moveDamage_.setPp( 40);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "ECLAIR", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringUtil.contains(data_.getCategories(), "SPECIAL"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), "SPECIAL"));
    }

    @Test
    public void completeMembers2Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("PHYSIQUE");
        moveDamage_.setDirect(false);
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "TONNERRE", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringUtil.contains(data_.getCategories(), "PHYSIQUE"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), "PHYSIQUE"));
    }

    @Test
    public void completeMembers3Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        data_.initValue(DataBase.DEF_CAT,AUTRE);
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), data_.getDefCategory()));
    }

    @Test
    public void completeMembers4Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getVariables().size());
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers5Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers6Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory(AUTRE);
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "CHARGE", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers7Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory(AUTRE);
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers8Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory(AUTRE);
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers9Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory(AUTRE);
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        damage_.getDamageLaw().addQuickEvent(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"), LgInt.one());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveTwo(data_, "CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers10Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers11Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.getLocalFailStatis().put(Statistic.ATTACK, StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers12Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        effStatis_.getLocalFailSwapBoostStatis().put(Statistic.ATTACK, StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers13Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers14Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        effStatus_.getLocalFailStatus().put("PSN", StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers15Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers16Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new IdMap<Statistic,String>());
        effStatus_.getCommonValue().put(Statistic.ATTACK, StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers17Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers18Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers19Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setDamageRateAgainstFoe("");
        effStatus_.setFailSending("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getVariables().size());
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers20Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending("");
        effStatus_.setDamageRateAgainstFoe(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
        assertEq(0, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers21Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser( 0);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesCopyingTemp().size());
        assertEq(0, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers22Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser( 1);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesCopyingTemp().size());
        assertEq(1, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers23Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setFail("");
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers24Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstPrio(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers25Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtSingle(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(1, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers26Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstMultTargets(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(1, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers27Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.one());
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(1, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers28Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAccuracy effStatus_;
        effStatus_ = new EffectAccuracy();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesAccuracy().size());
    }

    @Test
    public void completeMembers29Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAlly effStatus_;
        effStatus_ = new EffectAlly();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectAlly().size());
    }

    @Test
    public void completeMembers30Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeam effStatus_;
        effStatus_ = new EffectTeam();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectTeam().size());
    }

    @Test
    public void completeMembers31Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectUnprotectFromTypes effStatus_;
        effStatus_ = new EffectUnprotectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectUnprot().size());
    }

    @Test
    public void completeMembers32Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers33Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        effStatus_.setPreventStatusFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(0, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers34Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        effStatus_.setPreventStatusFail("");
        effStatus_.setWeather(true);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(1, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers35Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORCE);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers36Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORBIDDEN);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers37Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.LANCEUR_ATTAQUES);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(1, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers38Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers39Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers40Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers41Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRankIncrementNbRound( 1);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(1, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers42Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundGlobal effect_;
        effect_ = new EffectEndRoundGlobal();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers43Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionTargetRelation effect_;
        effect_ = new EffectEndRoundPositionTargetRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(1, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers44Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionRelation effect_;
        effect_ = new EffectEndRoundPositionRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(1, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers45Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers46Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers47Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers48Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundSingleRelation effect_;
        effect_ = new EffectEndRoundSingleRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        effect_.setRateDamageFunctionOfNbRounds(new LongMap<Rate>());
        effect_.setLawForEnablingEffect(new MonteCarloNumber());
        effect_.getLawForEnablingEffect().addQuickEvent(Rate.one(), LgInt.zero());
        effect_.getRateDamageFunctionOfNbRounds().put(1L, new Rate(1,2));
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(1, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers49Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.setConstUserChoice(true);
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers50Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers51Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        effect_.setKoUserHealSubst(true);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(1, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers52Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectInvoke effect_ = new EffectInvoke();
        effect_.setFail("");
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(1, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers53Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Berry berry_;
        berry_ = new Berry();
        completeMembersItem(data_, berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers54Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new IdMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        completeMembersItem(data_, berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers55Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new IdMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setEndRoundRank(3);
        effect_.setHealHp(new Rate(1,2));
        berry_.getEffectEndRound().add(effect_);
        completeMembersItem(data_, berry_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.OBJET, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("STRAW_BERRY", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.INDIVIDUEL, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers56Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new IdMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        completeAbility(data_, ability_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers57Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new IdMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        EffectEndRoundTeam effect_;
        effect_ = new EffectEndRoundTeam();
        effect_.setEndRoundRank(3);
        effect_.setDeleteAllStatusAlly(new Rate(1,2));
        ability_.getEffectEndRound().add(effect_);
        completeAbility(data_, ability_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.CAPACITE, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers58Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        completeMembersStatus(data_,status_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers59Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundSingleStatus eff_ = new EffectEndRoundSingleStatus();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        completeMembersStatus(data_, status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers60Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundStatusRelation eff_ = new EffectEndRoundStatusRelation();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        completeMembersStatus(data_, status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers61Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.INDIVIDUEL);
        completeMembersStatus(data_, status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers62Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.RELATION_UNIQUE);
        completeMembersStatus(data_, status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers63Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers64Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        eff_.setRankIncrementNbRound( 2);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers65Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers66Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending(StringUtil.concat(VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setDamageRateAgainstFoe("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers67Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCounterAttack effStatus_;
        effStatus_ = new EffectCounterAttack();
        effStatus_.setFail("");
        effStatus_.setCounterFail(StringUtil.concat(VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setProtectFail(StringUtil.concat(VAR_PREFIX,"USED_MOVE=TACKLE"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "NB_TURN")));
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(VAR_PREFIX, "USED_MOVE")));
        assertEq(1, data_.getMovesCountering().size());
        assertTrue(StringUtil.contains(data_.getMovesCountering(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers68Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectSwitchMoveTypes effStatus_;
        effStatus_ = new EffectSwitchMoveTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesChangingTypes().size());
        assertTrue(StringUtil.contains(data_.getMovesChangingTypes(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers69Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstStatusMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembers70Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp( 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstDamageMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        completeMembersMoveOne(data_, moveDamage_);
        assertEq(1, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembersCombos1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.initCombosTest();
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList("FLYING","SURF"), eff_));
        data_.completeMembersCombos();
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeVariables1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory(AUTRE);
        moveDamage_.setPp( 20);
        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringUtil.concat("1+",VAR_PREFIX,"NB_TURN__CHARGE*100+",VAR_PREFIX,"NB_TURN__FLYING*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.getMoves().addEntry("CHARGE", moveDamage_);
        data_.updateInfos();
        data_.completeVariables();
        assertEq(1, data_.getVarParamsMove().size());
        assertTrue(data_.getVarParamsMove().contains("NB_TURN"));
        assertEq(2, data_.getVarParamsMove().getVal("NB_TURN").size());
        assertTrue(StringUtil.contains(data_.getVarParamsMove().getVal("NB_TURN"), "CHARGE"));
        assertTrue(StringUtil.contains(data_.getVarParamsMove().getVal("NB_TURN"), "FLYING"));
    }
    @Test
    public void calculateAvgPound1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        data_.calculateAvgPound();
        assertEq(Rate.newRate("0"),data_.getAvgWeight());
    }
    @Test
    public void calculateAvgPoun2Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        PokemonData pokemon_ = Instances.newPokemonData();
        pokemon_.setWeight(Rate.one());
        data_.getPokedex().addEntry("QUEUE_DE_CHEVAL", pokemon_);
        data_.updateInfos();
        data_.calculateAvgPound();
        assertEq(Rate.newRate("1"),data_.getAvgWeight());
    }

    private void completeMembersMoveTwo(DataBase _data, String _n, MoveData _m) {
        _data.getMoves().addEntry(_n, _m);
        _data.updateInfos();
    }

    private void completeMembersMoveOne(DataBase _data, MoveData _m) {
        _data.getMoves().addEntry("QUEUE_DE_CHEVAL", _m);
        _data.updateInfos();
    }

    private void completeMembersItem(DataBase _data, Item _i) {
        _data.getItems().addEntry("STRAW_BERRY", _i);
        _data.updateInfos();
    }

    private void completeAbility(DataBase _data, AbilityData _a) {
        _data.getAbilities().addEntry("FLYING", _a);
        _data.updateInfos();
    }

    private void completeMembersStatus(DataBase _data, Status _s) {
        _data.getStatus().addEntry("PAR", _s);
        _data.updateInfos();
    }

    private static DataBase newData() {
        DataBase db_ = new DataBase(DefaultGenerator.oneElt());
        db_.defValues();
        return db_;
    }

    private static TranslationsFile tr(String _k, String _v) {
        TranslationsFile tf_ = new TranslationsFile();
        tf_.add(_k, _v);
        return tf_;
    }
}
