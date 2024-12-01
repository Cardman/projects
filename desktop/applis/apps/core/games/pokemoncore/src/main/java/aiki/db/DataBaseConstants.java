package aiki.db;

import code.maths.litteralcom.*;
import code.util.*;

public final class DataBaseConstants {

    private String prefixVar;
    private String niveau;
    private String levelLooser;
    private String levelWinner;
    private String fighterNiveau;
    private String cibleNiveau;
    private String lanceurNiveau;
    private String pkSauvageNiveau;
    private String pkUtNiveau;
    private String boost;
    private String power;
    private String attack;
    private String defense;
    private String baseCaptPk;
    private String rateBallStatus;
    private String foePkMaxHp;
    private String foePkRemoteHp;
    private String cibleNbUtilisation;
    private String fighterNbUtilisation;
    private String lanceurNbUtilisation;
    private String ciblePp;
    private String fighterPp;
    private String lanceurPp;
    private String cibleEffet;
    private String lanceurEffet;
    private String ciblePvRestants;
    private String fighterPvRestants;
    private String lanceurPvRestants;
    private String ciblePvMax;
    private String fighterPvMax;
    private String lanceurPvMax;
    private String cibleStatis;
    private String fighterStatis;
    private String lanceurStatis;
    private String cibleBoost;
    private String fighterBoost;
    private String lanceurBoost;
    private String equipeNbUtilisation;
    private String equipeAdvNbUtilisation;
    private String coeffEffBaseTypesCombattantEntrant;
    private String coeffEffBaseTypesCible;
    private String coeffEffBaseTypesLanceur;
    private String coeffEffBaseTypesFighter;
    private String sommeBoostPosCible;
    private String sommeBoostPosLanceur;
    private String sommeBoostPosFighter;
    private String immuTypeAttCombattantEntrant;
    private String immuTypeAttCible;
    private String equipeAdvCombattantEntrantNbUtilisation;
    private String nbTourGlobal;
    private String nbUtiliAttEqTour;
    private String cibleAttaques;
    private String cibleAttaqueChoisie;
    private String cibleAttaquesTypes;
    private String cibleClone;
    private String cibleDegatsRecus;
    private String cibleDegatsRecusTotal;
    private String cibleDegatsRecusTour;
    private String cibleDegatsRecusTotalTour;
    private String cibleDisparait;
    private String cibleJoue;
    private String cibleMasse;
    private String cibleTaille;
    private String cibleCapacite;
    private String cibleObjet;
    private String cibleStatuts;
    private String cibleTypes;
    private String cibleGenre;
    private String cibleBonheur;
    private String cibleNom;
    private String cibleDerJoue;
    private String ciblePossedeStatutRelation;
    private String nbKoEquipeCible;
    private String nbKoEquipeAdvCible;
    private String pasPpAttaqueCible;
    private String pasUtilisAttaqueCible;
    private String lanceurAttaques;
    private String lanceurAttaqueChoisie;
    private String lanceurAttaquesTypes;
    private String lanceurClone;
    private String lanceurDegatsRecus;
    private String lanceurDegatsRecusTotal;
    private String lanceurDegatsRecusTour;
    private String lanceurDegatsRecusTotalTour;
    private String lanceurDisparait;
    private String lanceurJoue;
    private String lanceurMasse;
    private String lanceurTaille;
    private String lanceurCapacite;
    private String lanceurObjet;
    private String lanceurStatuts;
    private String lanceurTypes;
    private String lanceurGenre;
    private String lanceurBonheur;
    private String lanceurNom;
    private String lanceurDerJoue;
    private String nbKoEquipeLanceur;
    private String nbKoEquipeAdvLanceur;
    private String fighterAttaques;
    private String fighterAttaqueChoisie;
    private String fighterAttaquesTypes;
    private String fighterClone;
    private String fighterDegatsRecus;
    private String fighterDegatsRecusTotal;
    private String fighterDegatsRecusTour;
    private String fighterDegatsRecusTotalTour;
    private String fighterDisparait;
    private String fighterJoue;
    private String fighterMasse;
    private String fighterTaille;
    private String fighterCapacite;
    private String fighterObjet;
    private String fighterStatuts;
    private String fighterTypes;
    private String fighterGenre;
    private String fighterBonheur;
    private String fighterNom;
    private String fighterDerJoue;
    private String nbKoEquipeFighter;
    private String nbKoEquipeAdvFighter;
    private String pkSauvageGenre;
    private String pkSauvageMasse;
    private String pkSauvageVitesse;
    private String pkSauvageTypesBase;
    private String pkSauvagePierresEvos;
    private String pkUtGenre;
    private String pkUtMasse;
    private String pkUtVitesse;
    private String pkUtTypesBase;
    private String pkUtPierresEvos;
    private String combattantEntrantClone;
    private String combattantEntrantTypes;
    private String aucunBoostPossible;
    private String typesAttaquesResVide;
    private String pasPartenaire;
    private String pasPartenaireArriere;
    private String pasPartenaireTerrain;
    private String pasTourTerrain;
    private String existeGenreAssexue;
    private String genresEgaux;
    private String rateEffMoveAgainstTarget;
    private String coeffEff;
    private String nbUtilisationConsecutif;
    private String attaqueCategorie;
    private String attaqueTypes;
    private String attaqueNom;
    private String puissanceBase;
    private String pasAttaqueInvoc;
    private String pasAttaquesCopiables;
    private String nbTour;
    private String dejaCapture;
    private String nbFlees;
    private String masseMoyennePk;
    private String climats;
    private String nbCombattantsTerrain;
    private String lieuCombat;
    private String tempsTour;
    public void validateOtherConstants() {
        if (incorrectPrefix(prefixVar)) {
            prefixVar=MessagesDataBaseConstants.VAR_DEF;
        }
        checkGroups();
        checkKeys();
    }

    private void checkGroups() {
        if (gr1().hasDuplicates()) {
            levelLooser=DataBase.EMPTY_STRING;
            levelWinner=DataBase.EMPTY_STRING;
        }
        if (gr2().hasDuplicates()) {
            cibleNiveau=DataBase.EMPTY_STRING;
            lanceurNiveau=DataBase.EMPTY_STRING;
        }
        if (gr3().hasDuplicates()) {
            pkSauvageNiveau=DataBase.EMPTY_STRING;
            pkUtNiveau=DataBase.EMPTY_STRING;
            dejaCapture=DataBase.EMPTY_STRING;
            nbFlees=DataBase.EMPTY_STRING;
            lieuCombat=DataBase.EMPTY_STRING;
            tempsTour=DataBase.EMPTY_STRING;
            masseMoyennePk=DataBase.EMPTY_STRING;
            pkUtGenre=DataBase.EMPTY_STRING;
            pkUtMasse=DataBase.EMPTY_STRING;
            pkUtVitesse=DataBase.EMPTY_STRING;
            pkSauvageGenre=DataBase.EMPTY_STRING;
            pkSauvageMasse=DataBase.EMPTY_STRING;
            pkSauvageVitesse=DataBase.EMPTY_STRING;
            pkSauvageTypesBase=DataBase.EMPTY_STRING;
            pkSauvagePierresEvos=DataBase.EMPTY_STRING;
            pkUtTypesBase=DataBase.EMPTY_STRING;
            pkUtPierresEvos=DataBase.EMPTY_STRING;
        }
        if (gr4().hasDuplicates()) {
            lanceurNiveau=DataBase.EMPTY_STRING;
            attack=DataBase.EMPTY_STRING;
            defense=DataBase.EMPTY_STRING;
            power=DataBase.EMPTY_STRING;
        }
        if (gr5().hasDuplicates()) {
            baseCaptPk=DataBase.EMPTY_STRING;
            rateBallStatus=DataBase.EMPTY_STRING;
            foePkMaxHp=DataBase.EMPTY_STRING;
            foePkRemoteHp=DataBase.EMPTY_STRING;
        }
        if (gr6().hasDuplicates()) {
            immuTypeAttCombattantEntrant=DataBase.EMPTY_STRING;
            pasAttaqueInvoc=DataBase.EMPTY_STRING;
            pasAttaquesCopiables=DataBase.EMPTY_STRING;
            ciblePossedeStatutRelation=DataBase.EMPTY_STRING;
            cibleEffet=DataBase.EMPTY_STRING;
            pasPpAttaqueCible=DataBase.EMPTY_STRING;
            pasUtilisAttaqueCible=DataBase.EMPTY_STRING;
            immuTypeAttCible=DataBase.EMPTY_STRING;
            aucunBoostPossible=DataBase.EMPTY_STRING;
            lanceurEffet=DataBase.EMPTY_STRING;
            typesAttaquesResVide=DataBase.EMPTY_STRING;
            pasPartenaire=DataBase.EMPTY_STRING;
            pasPartenaireArriere=DataBase.EMPTY_STRING;
            pasPartenaireTerrain=DataBase.EMPTY_STRING;
            pasTourTerrain=DataBase.EMPTY_STRING;
            existeGenreAssexue=DataBase.EMPTY_STRING;
            genresEgaux=DataBase.EMPTY_STRING;
            cibleGenre=DataBase.EMPTY_STRING;
            ciblePvRestants=DataBase.EMPTY_STRING;
            ciblePvMax=DataBase.EMPTY_STRING;
            cibleNbUtilisation=DataBase.EMPTY_STRING;
            cibleStatis=DataBase.EMPTY_STRING;
            cibleBoost=DataBase.EMPTY_STRING;
            sommeBoostPosCible=DataBase.EMPTY_STRING;
            cibleAttaqueChoisie=DataBase.EMPTY_STRING;
            cibleAttaques=DataBase.EMPTY_STRING;
            cibleAttaquesTypes=DataBase.EMPTY_STRING;
            cibleClone=DataBase.EMPTY_STRING;
            cibleDegatsRecus=DataBase.EMPTY_STRING;
            cibleDegatsRecusTotal=DataBase.EMPTY_STRING;
            cibleDegatsRecusTour=DataBase.EMPTY_STRING;
            cibleDegatsRecusTotalTour=DataBase.EMPTY_STRING;
            cibleDisparait=DataBase.EMPTY_STRING;
            cibleJoue=DataBase.EMPTY_STRING;
            cibleDerJoue=DataBase.EMPTY_STRING;
            cibleNom=DataBase.EMPTY_STRING;
            cibleMasse=DataBase.EMPTY_STRING;
            cibleTaille=DataBase.EMPTY_STRING;
            cibleCapacite=DataBase.EMPTY_STRING;
            cibleObjet=DataBase.EMPTY_STRING;
            cibleStatuts=DataBase.EMPTY_STRING;
            cibleTypes=DataBase.EMPTY_STRING;
            ciblePp=DataBase.EMPTY_STRING;
            cibleNiveau=DataBase.EMPTY_STRING;
            cibleBonheur=DataBase.EMPTY_STRING;
            nbKoEquipeCible=DataBase.EMPTY_STRING;
            nbKoEquipeAdvCible=DataBase.EMPTY_STRING;
            coeffEffBaseTypesCible=DataBase.EMPTY_STRING;
            lanceurGenre=DataBase.EMPTY_STRING;
            nbUtiliAttEqTour=DataBase.EMPTY_STRING;
            lanceurPvRestants=DataBase.EMPTY_STRING;
            lanceurPvMax=DataBase.EMPTY_STRING;
            lanceurNbUtilisation=DataBase.EMPTY_STRING;
            lanceurStatis=DataBase.EMPTY_STRING;
            lanceurBoost=DataBase.EMPTY_STRING;
            sommeBoostPosLanceur=DataBase.EMPTY_STRING;
            lanceurAttaqueChoisie=DataBase.EMPTY_STRING;
            lanceurAttaques=DataBase.EMPTY_STRING;
            lanceurAttaquesTypes=DataBase.EMPTY_STRING;
            lanceurClone=DataBase.EMPTY_STRING;
            lanceurDegatsRecus=DataBase.EMPTY_STRING;
            lanceurDegatsRecusTotal=DataBase.EMPTY_STRING;
            lanceurDegatsRecusTour=DataBase.EMPTY_STRING;
            lanceurDegatsRecusTotalTour=DataBase.EMPTY_STRING;
            lanceurDisparait=DataBase.EMPTY_STRING;
            lanceurJoue=DataBase.EMPTY_STRING;
            lanceurDerJoue=DataBase.EMPTY_STRING;
            lanceurNom=DataBase.EMPTY_STRING;
            lanceurMasse=DataBase.EMPTY_STRING;
            lanceurTaille=DataBase.EMPTY_STRING;
            lanceurCapacite=DataBase.EMPTY_STRING;
            lanceurObjet=DataBase.EMPTY_STRING;
            lanceurStatuts=DataBase.EMPTY_STRING;
            lanceurTypes=DataBase.EMPTY_STRING;
            lanceurPp=DataBase.EMPTY_STRING;
            lanceurNiveau=DataBase.EMPTY_STRING;
            lanceurBonheur=DataBase.EMPTY_STRING;
            nbKoEquipeLanceur=DataBase.EMPTY_STRING;
            nbKoEquipeAdvLanceur=DataBase.EMPTY_STRING;
            coeffEffBaseTypesLanceur=DataBase.EMPTY_STRING;
            tempsTour=DataBase.EMPTY_STRING;
            nbTour=DataBase.EMPTY_STRING;
            rateEffMoveAgainstTarget=DataBase.EMPTY_STRING;
            nbCombattantsTerrain=DataBase.EMPTY_STRING;
            lieuCombat=DataBase.EMPTY_STRING;
            climats=DataBase.EMPTY_STRING;
            nbTourGlobal=DataBase.EMPTY_STRING;
            combattantEntrantClone=DataBase.EMPTY_STRING;
            combattantEntrantTypes=DataBase.EMPTY_STRING;
            coeffEffBaseTypesCombattantEntrant=DataBase.EMPTY_STRING;
            equipeAdvCombattantEntrantNbUtilisation=DataBase.EMPTY_STRING;
            attaqueCategorie=DataBase.EMPTY_STRING;
            attaqueTypes=DataBase.EMPTY_STRING;
            attaqueNom=DataBase.EMPTY_STRING;
            puissanceBase=DataBase.EMPTY_STRING;
            fighterGenre=DataBase.EMPTY_STRING;
            fighterPvRestants=DataBase.EMPTY_STRING;
            fighterPvMax=DataBase.EMPTY_STRING;
            fighterNbUtilisation=DataBase.EMPTY_STRING;
            fighterStatis=DataBase.EMPTY_STRING;
            fighterBoost=DataBase.EMPTY_STRING;
            sommeBoostPosFighter=DataBase.EMPTY_STRING;
            fighterAttaqueChoisie=DataBase.EMPTY_STRING;
            fighterAttaques=DataBase.EMPTY_STRING;
            fighterAttaquesTypes=DataBase.EMPTY_STRING;
            fighterClone=DataBase.EMPTY_STRING;
            fighterDegatsRecus=DataBase.EMPTY_STRING;
            fighterDegatsRecusTotal=DataBase.EMPTY_STRING;
            fighterDegatsRecusTour=DataBase.EMPTY_STRING;
            fighterDegatsRecusTotalTour=DataBase.EMPTY_STRING;
            fighterDisparait=DataBase.EMPTY_STRING;
            fighterJoue=DataBase.EMPTY_STRING;
            fighterDerJoue=DataBase.EMPTY_STRING;
            fighterNom=DataBase.EMPTY_STRING;
            fighterMasse=DataBase.EMPTY_STRING;
            fighterTaille=DataBase.EMPTY_STRING;
            fighterCapacite=DataBase.EMPTY_STRING;
            fighterObjet=DataBase.EMPTY_STRING;
            fighterStatuts=DataBase.EMPTY_STRING;
            fighterTypes=DataBase.EMPTY_STRING;
            fighterPp=DataBase.EMPTY_STRING;
            fighterNiveau=DataBase.EMPTY_STRING;
            fighterBonheur=DataBase.EMPTY_STRING;
            nbKoEquipeFighter=DataBase.EMPTY_STRING;
            nbKoEquipeAdvFighter=DataBase.EMPTY_STRING;
            coeffEffBaseTypesFighter=DataBase.EMPTY_STRING;
            coeffEff=DataBase.EMPTY_STRING;
            nbUtilisationConsecutif=DataBase.EMPTY_STRING;
            equipeNbUtilisation=DataBase.EMPTY_STRING;
            equipeAdvNbUtilisation=DataBase.EMPTY_STRING;
        }
    }

    private void checkKeys() {
        if (incorrectKey(niveau)) {
            niveau=MessagesDataBaseConstants.DEF_NIVEAU;
        }
        if (incorrectKey(levelLooser)) {
            levelLooser=MessagesDataBaseConstants.DEF_LEVEL_LOOSER;
        }
        if (incorrectKey(levelWinner)) {
            levelWinner=MessagesDataBaseConstants.DEF_LEVEL_WINNER;
        }
        if (incorrectKey(fighterNiveau)) {
            fighterNiveau=MessagesDataBaseConstants.DEF_FIGHTER_NIVEAU;
        }
        if (incorrectKey(cibleNiveau)) {
            cibleNiveau=MessagesDataBaseConstants.DEF_CIBLE_NIVEAU;
        }
        if (incorrectKey(lanceurNiveau)) {
            lanceurNiveau=MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU;
        }
        catching();
        if (incorrectKey(boost)) {
            boost=MessagesDataBaseConstants.DEF_BOOST;
        }
        if (incorrectKey(power)) {
            power=MessagesDataBaseConstants.DEF_POWER;
        }
        if (incorrectKey(attack)) {
            attack=MessagesDataBaseConstants.DEF_ATTACK;
        }
        if (incorrectKey(defense)) {
            defense=MessagesDataBaseConstants.DEF_DEFENSE;
        }
        capt();
        cible();
        lanceur();
        fighter();
        other();
    }

    private void catching() {
        if (incorrectKey(dejaCapture)) {
            dejaCapture=MessagesDataBaseConstants.DEF_DEJA_CAPTURE;
        }
        if (incorrectKey(nbFlees)) {
            nbFlees=MessagesDataBaseConstants.DEF_NB_FLEES;
        }
        if (incorrectKey(pkSauvageNiveau)) {
            pkSauvageNiveau=MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU;
        }
        if (incorrectKey(pkUtNiveau)) {
            pkUtNiveau=MessagesDataBaseConstants.DEF_PK_UT_NIVEAU;
        }
        if (incorrectKey(masseMoyennePk)) {
            masseMoyennePk=MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK;
        }
        if (incorrectKey(pkUtGenre)) {
            pkUtGenre=MessagesDataBaseConstants.DEF_PK_UT_GENRE;
        }
        if (incorrectKey(pkUtMasse)) {
            pkUtMasse=MessagesDataBaseConstants.DEF_PK_UT_MASSE;
        }
        if (incorrectKey(pkUtVitesse)) {
            pkUtVitesse=MessagesDataBaseConstants.DEF_PK_UT_VITESSE;
        }
        if (incorrectKey(pkUtTypesBase)) {
            pkUtTypesBase=MessagesDataBaseConstants.DEF_PK_UT_TYPES_BASE;
        }
        if (incorrectKey(pkUtPierresEvos)) {
            pkUtPierresEvos=MessagesDataBaseConstants.DEF_PK_UT_PIERRES_EVOS;
        }
        if (incorrectKey(pkSauvageGenre)) {
            pkSauvageGenre=MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE;
        }
        if (incorrectKey(pkSauvageMasse)) {
            pkSauvageMasse=MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE;
        }
        if (incorrectKey(pkSauvageVitesse)) {
            pkSauvageVitesse=MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE;
        }
        if (incorrectKey(pkSauvageTypesBase)) {
            pkSauvageTypesBase=MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE;
        }
        if (incorrectKey(pkSauvagePierresEvos)) {
            pkSauvagePierresEvos=MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS;
        }
    }

    private void capt() {
        if (incorrectKey(baseCaptPk)) {
            baseCaptPk=MessagesDataBaseConstants.DEF_BASE_CAPT_PK;
        }
        if (incorrectKey(rateBallStatus)) {
            rateBallStatus=MessagesDataBaseConstants.DEF_RATE_BALL_STATUS;
        }
        if (incorrectKey(foePkMaxHp)) {
            foePkMaxHp=MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP;
        }
        if (incorrectKey(foePkRemoteHp)) {
            foePkRemoteHp=MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP;
        }
    }
    private void cible() {
        cible1();
        cible2();
        if (incorrectKey(cibleMasse)) {
            cibleMasse=MessagesDataBaseConstants.DEF_CIBLE_MASSE;
        }
        if (incorrectKey(cibleTaille)) {
            cibleTaille=MessagesDataBaseConstants.DEF_CIBLE_TAILLE;
        }
        if (incorrectKey(cibleCapacite)) {
            cibleCapacite=MessagesDataBaseConstants.DEF_CIBLE_CAPACITE;
        }
        if (incorrectKey(cibleObjet)) {
            cibleObjet=MessagesDataBaseConstants.DEF_CIBLE_OBJET;
        }
        if (incorrectKey(cibleStatuts)) {
            cibleStatuts=MessagesDataBaseConstants.DEF_CIBLE_STATUTS;
        }
        if (incorrectKey(cibleTypes)) {
            cibleTypes=MessagesDataBaseConstants.DEF_CIBLE_TYPES;
        }
        if (incorrectKey(ciblePp)) {
            ciblePp=MessagesDataBaseConstants.DEF_CIBLE_PP;
        }
        if (incorrectKey(cibleBonheur)) {
            cibleBonheur=MessagesDataBaseConstants.DEF_CIBLE_BONHEUR;
        }
        if (incorrectKey(nbKoEquipeCible)) {
            nbKoEquipeCible=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_CIBLE;
        }
        if (incorrectKey(nbKoEquipeAdvCible)) {
            nbKoEquipeAdvCible=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_CIBLE;
        }
        if (incorrectKey(coeffEffBaseTypesCible)) {
            coeffEffBaseTypesCible=MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_CIBLE;
        }
    }

    private void cible2() {
        if (incorrectKey(cibleClone)) {
            cibleClone=MessagesDataBaseConstants.DEF_CIBLE_CLONE;
        }
        if (incorrectKey(cibleDegatsRecus)) {
            cibleDegatsRecus=MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS;
        }
        if (incorrectKey(cibleDegatsRecusTotal)) {
            cibleDegatsRecusTotal=MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL;
        }
        if (incorrectKey(cibleDegatsRecusTour)) {
            cibleDegatsRecusTour=MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOUR;
        }
        if (incorrectKey(cibleDegatsRecusTotalTour)) {
            cibleDegatsRecusTotalTour=MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR;
        }
        if (incorrectKey(cibleDisparait)) {
            cibleDisparait=MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT;
        }
        if (incorrectKey(cibleJoue)) {
            cibleJoue=MessagesDataBaseConstants.DEF_CIBLE_JOUE;
        }
        if (incorrectKey(cibleDerJoue)) {
            cibleDerJoue=MessagesDataBaseConstants.DEF_CIBLE_DER_JOUE;
        }
        if (incorrectKey(cibleNom)) {
            cibleNom=MessagesDataBaseConstants.DEF_CIBLE_NOM;
        }
    }

    private void cible1() {
        if (incorrectKey(ciblePossedeStatutRelation)) {
            ciblePossedeStatutRelation=MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION;
        }
        if (incorrectKey(cibleEffet)) {
            cibleEffet=MessagesDataBaseConstants.DEF_CIBLE_EFFET;
        }
        if (incorrectKey(pasPpAttaqueCible)) {
            pasPpAttaqueCible=MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE;
        }
        if (incorrectKey(pasUtilisAttaqueCible)) {
            pasUtilisAttaqueCible=MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE;
        }
        if (incorrectKey(immuTypeAttCible)) {
            immuTypeAttCible=MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE;
        }
        if (incorrectKey(cibleGenre)) {
            cibleGenre=MessagesDataBaseConstants.DEF_CIBLE_GENRE;
        }
        if (incorrectKey(ciblePvRestants)) {
            ciblePvRestants=MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS;
        }
        if (incorrectKey(ciblePvMax)) {
            ciblePvMax=MessagesDataBaseConstants.DEF_CIBLE_PV_MAX;
        }
        if (incorrectKey(cibleNbUtilisation)) {
            cibleNbUtilisation=MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION;
        }
        if (incorrectKey(cibleStatis)) {
            cibleStatis=MessagesDataBaseConstants.DEF_CIBLE_STATIS;
        }
        if (incorrectKey(cibleBoost)) {
            cibleBoost=MessagesDataBaseConstants.DEF_CIBLE_BOOST;
        }
        if (incorrectKey(sommeBoostPosCible)) {
            sommeBoostPosCible=MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE;
        }
        if (incorrectKey(cibleAttaqueChoisie)) {
            cibleAttaqueChoisie=MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE;
        }
        if (incorrectKey(cibleAttaques)) {
            cibleAttaques=MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES;
        }
        if (incorrectKey(cibleAttaquesTypes)) {
            cibleAttaquesTypes=MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES_TYPES;
        }
    }

    private void lanceur() {
        lanceur1();
        lanceur2();
        if (incorrectKey(lanceurPp)) {
            lanceurPp=MessagesDataBaseConstants.DEF_LANCEUR_PP;
        }
        if (incorrectKey(lanceurBonheur)) {
            lanceurBonheur=MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR;
        }
        if (incorrectKey(nbKoEquipeLanceur)) {
            nbKoEquipeLanceur=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR;
        }
        if (incorrectKey(nbKoEquipeAdvLanceur)) {
            nbKoEquipeAdvLanceur=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_LANCEUR;
        }
        if (incorrectKey(coeffEffBaseTypesLanceur)) {
            coeffEffBaseTypesLanceur=MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_LANCEUR;
        }
    }

    private void lanceur2() {
        if (incorrectKey(lanceurDegatsRecusTotalTour)) {
            lanceurDegatsRecusTotalTour=MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR;
        }
        if (incorrectKey(lanceurDisparait)) {
            lanceurDisparait=MessagesDataBaseConstants.DEF_LANCEUR_DISPARAIT;
        }
        if (incorrectKey(lanceurJoue)) {
            lanceurJoue=MessagesDataBaseConstants.DEF_LANCEUR_JOUE;
        }
        if (incorrectKey(lanceurDerJoue)) {
            lanceurDerJoue=MessagesDataBaseConstants.DEF_LANCEUR_DER_JOUE;
        }
        if (incorrectKey(lanceurNom)) {
            lanceurNom=MessagesDataBaseConstants.DEF_LANCEUR_NOM;
        }
        if (incorrectKey(lanceurMasse)) {
            lanceurMasse=MessagesDataBaseConstants.DEF_LANCEUR_MASSE;
        }
        if (incorrectKey(lanceurTaille)) {
            lanceurTaille=MessagesDataBaseConstants.DEF_LANCEUR_TAILLE;
        }
        if (incorrectKey(lanceurCapacite)) {
            lanceurCapacite=MessagesDataBaseConstants.DEF_LANCEUR_CAPACITE;
        }
        if (incorrectKey(lanceurObjet)) {
            lanceurObjet=MessagesDataBaseConstants.DEF_LANCEUR_OBJET;
        }
        if (incorrectKey(lanceurStatuts)) {
            lanceurStatuts=MessagesDataBaseConstants.DEF_LANCEUR_STATUTS;
        }
        if (incorrectKey(lanceurTypes)) {
            lanceurTypes=MessagesDataBaseConstants.DEF_LANCEUR_TYPES;
        }
    }

    private void lanceur1() {
        if (incorrectKey(lanceurEffet)) {
            lanceurEffet=MessagesDataBaseConstants.DEF_LANCEUR_EFFET;
        }
        if (incorrectKey(lanceurGenre)) {
            lanceurGenre=MessagesDataBaseConstants.DEF_LANCEUR_GENRE;
        }
        if (incorrectKey(lanceurPvRestants)) {
            lanceurPvRestants=MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS;
        }
        if (incorrectKey(lanceurPvMax)) {
            lanceurPvMax=MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX;
        }
        if (incorrectKey(lanceurNbUtilisation)) {
            lanceurNbUtilisation=MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION;
        }
        if (incorrectKey(lanceurStatis)) {
            lanceurStatis=MessagesDataBaseConstants.DEF_LANCEUR_STATIS;
        }
        if (incorrectKey(lanceurBoost)) {
            lanceurBoost=MessagesDataBaseConstants.DEF_LANCEUR_BOOST;
        }
        if (incorrectKey(sommeBoostPosLanceur)) {
            sommeBoostPosLanceur=MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR;
        }
        if (incorrectKey(lanceurAttaqueChoisie)) {
            lanceurAttaqueChoisie=MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUE_CHOISIE;
        }
        if (incorrectKey(lanceurAttaques)) {
            lanceurAttaques=MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES;
        }
        if (incorrectKey(lanceurAttaquesTypes)) {
            lanceurAttaquesTypes=MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES;
        }
        if (incorrectKey(lanceurClone)) {
            lanceurClone=MessagesDataBaseConstants.DEF_LANCEUR_CLONE;
        }
        if (incorrectKey(lanceurDegatsRecus)) {
            lanceurDegatsRecus=MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS;
        }
        if (incorrectKey(lanceurDegatsRecusTotal)) {
            lanceurDegatsRecusTotal=MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL;
        }
        if (incorrectKey(lanceurDegatsRecusTour)) {
            lanceurDegatsRecusTour=MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR;
        }
    }

    private void fighter() {
        fighter1();
        fighter2();
        if (incorrectKey(fighterTypes)) {
            fighterTypes=MessagesDataBaseConstants.DEF_FIGHTER_TYPES;
        }
        if (incorrectKey(fighterPp)) {
            fighterPp=MessagesDataBaseConstants.DEF_FIGHTER_PP;
        }
        if (incorrectKey(fighterBonheur)) {
            fighterBonheur=MessagesDataBaseConstants.DEF_FIGHTER_BONHEUR;
        }
        if (incorrectKey(nbKoEquipeFighter)) {
            nbKoEquipeFighter=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_FIGHTER;
        }
        if (incorrectKey(nbKoEquipeAdvFighter)) {
            nbKoEquipeAdvFighter=MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_FIGHTER;
        }
        if (incorrectKey(coeffEffBaseTypesFighter)) {
            coeffEffBaseTypesFighter=MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_FIGHTER;
        }
    }

    private void fighter2() {
        if (incorrectKey(fighterDisparait)) {
            fighterDisparait=MessagesDataBaseConstants.DEF_FIGHTER_DISPARAIT;
        }
        if (incorrectKey(fighterJoue)) {
            fighterJoue=MessagesDataBaseConstants.DEF_FIGHTER_JOUE;
        }
        if (incorrectKey(fighterDerJoue)) {
            fighterDerJoue=MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE;
        }
        if (incorrectKey(fighterNom)) {
            fighterNom=MessagesDataBaseConstants.DEF_FIGHTER_NOM;
        }
        if (incorrectKey(fighterMasse)) {
            fighterMasse=MessagesDataBaseConstants.DEF_FIGHTER_MASSE;
        }
        if (incorrectKey(fighterTaille)) {
            fighterTaille=MessagesDataBaseConstants.DEF_FIGHTER_TAILLE;
        }
        if (incorrectKey(fighterCapacite)) {
            fighterCapacite=MessagesDataBaseConstants.DEF_FIGHTER_CAPACITE;
        }
        if (incorrectKey(fighterObjet)) {
            fighterObjet=MessagesDataBaseConstants.DEF_FIGHTER_OBJET;
        }
        if (incorrectKey(fighterStatuts)) {
            fighterStatuts=MessagesDataBaseConstants.DEF_FIGHTER_STATUTS;
        }
    }

    private void fighter1() {
        if (incorrectKey(fighterGenre)) {
            fighterGenre=MessagesDataBaseConstants.DEF_FIGHTER_GENRE;
        }
        if (incorrectKey(fighterPvRestants)) {
            fighterPvRestants=MessagesDataBaseConstants.DEF_FIGHTER_PV_RESTANTS;
        }
        if (incorrectKey(fighterPvMax)) {
            fighterPvMax=MessagesDataBaseConstants.DEF_FIGHTER_PV_MAX;
        }
        if (incorrectKey(fighterNbUtilisation)) {
            fighterNbUtilisation=MessagesDataBaseConstants.DEF_FIGHTER_NB_UTILISATION;
        }
        if (incorrectKey(fighterStatis)) {
            fighterStatis=MessagesDataBaseConstants.DEF_FIGHTER_STATIS;
        }
        if (incorrectKey(fighterBoost)) {
            fighterBoost=MessagesDataBaseConstants.DEF_FIGHTER_BOOST;
        }
        if (incorrectKey(sommeBoostPosFighter)) {
            sommeBoostPosFighter=MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_FIGHTER;
        }
        if (incorrectKey(fighterAttaqueChoisie)) {
            fighterAttaqueChoisie=MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUE_CHOISIE;
        }
        if (incorrectKey(fighterAttaques)) {
            fighterAttaques=MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUES;
        }
        if (incorrectKey(fighterAttaquesTypes)) {
            fighterAttaquesTypes=MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUES_TYPES;
        }
        if (incorrectKey(fighterClone)) {
            fighterClone=MessagesDataBaseConstants.DEF_FIGHTER_CLONE;
        }
        if (incorrectKey(fighterDegatsRecus)) {
            fighterDegatsRecus=MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS;
        }
        if (incorrectKey(fighterDegatsRecusTotal)) {
            fighterDegatsRecusTotal=MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOTAL;
        }
        if (incorrectKey(fighterDegatsRecusTour)) {
            fighterDegatsRecusTour=MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOUR;
        }
        if (incorrectKey(fighterDegatsRecusTotalTour)) {
            fighterDegatsRecusTotalTour=MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOTAL_TOUR;
        }
    }

    private void other() {
        other1();
        other2();
        if (incorrectKey(puissanceBase)) {
            puissanceBase=MessagesDataBaseConstants.DEF_PUISSANCE_BASE;
        }
        if (incorrectKey(coeffEff)) {
            coeffEff=MessagesDataBaseConstants.DEF_COEFF_EFF;
        }
        if (incorrectKey(nbUtilisationConsecutif)) {
            nbUtilisationConsecutif=MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF;
        }
        if (incorrectKey(equipeNbUtilisation)) {
            equipeNbUtilisation=MessagesDataBaseConstants.DEF_EQUIPE_NB_UTILISATION;
        }
        if (incorrectKey(equipeAdvNbUtilisation)) {
            equipeAdvNbUtilisation=MessagesDataBaseConstants.DEF_EQUIPE_ADV_NB_UTILISATION;
        }
    }

    private void other2() {
        if (incorrectKey(nbCombattantsTerrain)) {
            nbCombattantsTerrain=MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN;
        }
        if (incorrectKey(lieuCombat)) {
            lieuCombat=MessagesDataBaseConstants.DEF_LIEU_COMBAT;
        }
        if (incorrectKey(climats)) {
            climats=MessagesDataBaseConstants.DEF_CLIMATS;
        }
        if (incorrectKey(nbTourGlobal)) {
            nbTourGlobal=MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL;
        }
        if (incorrectKey(combattantEntrantClone)) {
            combattantEntrantClone=MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE;
        }
        if (incorrectKey(combattantEntrantTypes)) {
            combattantEntrantTypes=MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES;
        }
        if (incorrectKey(coeffEffBaseTypesCombattantEntrant)) {
            coeffEffBaseTypesCombattantEntrant=MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT;
        }
        if (incorrectKey(equipeAdvCombattantEntrantNbUtilisation)) {
            equipeAdvCombattantEntrantNbUtilisation=MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION;
        }
        if (incorrectKey(attaqueCategorie)) {
            attaqueCategorie=MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE;
        }
        if (incorrectKey(attaqueTypes)) {
            attaqueTypes=MessagesDataBaseConstants.DEF_ATTAQUE_TYPES;
        }
        if (incorrectKey(attaqueNom)) {
            attaqueNom=MessagesDataBaseConstants.DEF_ATTAQUE_NOM;
        }
    }

    private void other1() {
        if (incorrectKey(immuTypeAttCombattantEntrant)) {
            immuTypeAttCombattantEntrant=MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT;
        }
        if (incorrectKey(pasAttaqueInvoc)) {
            pasAttaqueInvoc=MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC;
        }
        if (incorrectKey(pasAttaquesCopiables)) {
            pasAttaquesCopiables=MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES;
        }
        if (incorrectKey(aucunBoostPossible)) {
            aucunBoostPossible=MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE;
        }
        if (incorrectKey(typesAttaquesResVide)) {
            typesAttaquesResVide=MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE;
        }
        if (incorrectKey(pasPartenaire)) {
            pasPartenaire=MessagesDataBaseConstants.DEF_PAS_PARTENAIRE;
        }
        if (incorrectKey(pasPartenaireArriere)) {
            pasPartenaireArriere=MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE;
        }
        if (incorrectKey(pasPartenaireTerrain)) {
            pasPartenaireTerrain=MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN;
        }
        if (incorrectKey(pasTourTerrain)) {
            pasTourTerrain=MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN;
        }
        if (incorrectKey(existeGenreAssexue)) {
            existeGenreAssexue=MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE;
        }
        if (incorrectKey(genresEgaux)) {
            genresEgaux=MessagesDataBaseConstants.DEF_GENRES_EGAUX;
        }
        if (incorrectKey(nbUtiliAttEqTour)) {
            nbUtiliAttEqTour=MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR;
        }
        if (incorrectKey(tempsTour)) {
            tempsTour=MessagesDataBaseConstants.DEF_TEMPS_TOUR;
        }
        if (incorrectKey(nbTour)) {
            nbTour=MessagesDataBaseConstants.DEF_NB_TOUR;
        }
        if (incorrectKey(rateEffMoveAgainstTarget)) {
            rateEffMoveAgainstTarget=MessagesDataBaseConstants.DEF_RATE_EFF_MOVE_AGAINST_TARGET;
        }
    }

    private StringList gr1() {
        StringList str_ = new StringList();
        str_.add(levelLooser);
        str_.add(levelWinner);
        return str_;
    }

    private StringList gr2() {
        StringList str_ = new StringList();
        str_.add(cibleNiveau);
        str_.add(lanceurNiveau);
        return str_;
    }

    private StringList gr3() {
        StringList str_ = new StringList();
        str_.add(pkSauvageNiveau);
        str_.add(pkUtNiveau);
        str_.add(dejaCapture);
        str_.add(nbFlees);
        str_.add(lieuCombat);
        str_.add(tempsTour);
        str_.add(masseMoyennePk);
        str_.add(pkUtGenre);
        str_.add(pkUtMasse);
        str_.add(pkUtVitesse);
        str_.add(pkSauvageGenre);
        str_.add(pkSauvageMasse);
        str_.add(pkSauvageVitesse);
        str_.add(pkSauvageTypesBase);
        str_.add(pkSauvagePierresEvos);
        str_.add(pkUtTypesBase);
        str_.add(pkUtPierresEvos);
        return str_;
    }

    private StringList gr4() {
        StringList str_ = new StringList();
        str_.add(lanceurNiveau);
        stat(str_);
        return str_;
    }

    private StringList gr5() {
        StringList str_ = new StringList();
        str_.add(baseCaptPk);
        str_.add(rateBallStatus);
        str_.add(foePkMaxHp);
        str_.add(foePkRemoteHp);
        return str_;
    }

    private StringList gr6() {
        StringList str_ = new StringList();
        str_.add(immuTypeAttCombattantEntrant);
        str_.add(pasAttaqueInvoc);
        str_.add(pasAttaquesCopiables);
        feedExpBool(str_);
        str_.add(cibleGenre);
        str_.add(ciblePvRestants);
        str_.add(ciblePvMax);
        str_.add(cibleNbUtilisation);
        str_.add(cibleStatis);
        str_.add(cibleBoost);
        str_.add(sommeBoostPosCible);
        str_.add(cibleAttaqueChoisie);
        str_.add(cibleAttaques);
        str_.add(cibleAttaquesTypes);
        str_.add(cibleClone);
        str_.add(cibleDegatsRecus);
        str_.add(cibleDegatsRecusTotal);
        str_.add(cibleDegatsRecusTour);
        str_.add(cibleDegatsRecusTotalTour);
        str_.add(cibleDisparait);
        str_.add(cibleJoue);
        str_.add(cibleDerJoue);
        str_.add(cibleNom);
        str_.add(cibleMasse);
        str_.add(cibleTaille);
        str_.add(cibleCapacite);
        str_.add(cibleObjet);
        str_.add(cibleStatuts);
        str_.add(cibleTypes);
        str_.add(ciblePp);
        str_.add(cibleNiveau);
        str_.add(cibleBonheur);
        str_.add(nbKoEquipeCible);
        str_.add(nbKoEquipeAdvCible);
        str_.add(coeffEffBaseTypesCible);
        str_.add(lanceurGenre);
        str_.add(nbUtiliAttEqTour);
        str_.add(lanceurPvRestants);
        str_.add(lanceurPvMax);
        str_.add(lanceurNbUtilisation);
        str_.add(lanceurStatis);
        str_.add(lanceurBoost);
        str_.add(sommeBoostPosLanceur);
        str_.add(lanceurAttaqueChoisie);
        str_.add(lanceurAttaques);
        str_.add(lanceurAttaquesTypes);
        str_.add(lanceurClone);
        str_.add(lanceurDegatsRecus);
        str_.add(lanceurDegatsRecusTotal);
        str_.add(lanceurDegatsRecusTour);
        str_.add(lanceurDegatsRecusTotalTour);
        str_.add(lanceurDisparait);
        str_.add(lanceurJoue);
        str_.add(lanceurDerJoue);
        str_.add(lanceurNom);
        str_.add(lanceurMasse);
        str_.add(lanceurTaille);
        str_.add(lanceurCapacite);
        str_.add(lanceurObjet);
        str_.add(lanceurStatuts);
        str_.add(lanceurTypes);
        str_.add(lanceurPp);
        str_.add(lanceurNiveau);
        str_.add(lanceurBonheur);
        str_.add(nbKoEquipeLanceur);
        str_.add(nbKoEquipeAdvLanceur);
        str_.add(coeffEffBaseTypesLanceur);
        str_.add(tempsTour);
        str_.add(nbTour);
        str_.add(rateEffMoveAgainstTarget);
        str_.add(nbCombattantsTerrain);
        str_.add(lieuCombat);
        str_.add(climats);
        str_.add(nbTourGlobal);
        str_.add(combattantEntrantClone);
        str_.add(combattantEntrantTypes);
        str_.add(coeffEffBaseTypesCombattantEntrant);
        str_.add(equipeAdvCombattantEntrantNbUtilisation);
        str_.add(attaqueCategorie);
        str_.add(attaqueTypes);
        str_.add(attaqueNom);
        str_.add(puissanceBase);
        str_.add(fighterGenre);
        str_.add(fighterPvRestants);
        str_.add(fighterPvMax);
        str_.add(fighterNbUtilisation);
        str_.add(fighterStatis);
        str_.add(fighterBoost);
        str_.add(sommeBoostPosFighter);
        str_.add(fighterAttaqueChoisie);
        str_.add(fighterAttaques);
        str_.add(fighterAttaquesTypes);
        str_.add(fighterClone);
        str_.add(fighterDegatsRecus);
        str_.add(fighterDegatsRecusTotal);
        str_.add(fighterDegatsRecusTour);
        str_.add(fighterDegatsRecusTotalTour);
        str_.add(fighterDisparait);
        str_.add(fighterJoue);
        str_.add(fighterDerJoue);
        str_.add(fighterNom);
        str_.add(fighterMasse);
        str_.add(fighterTaille);
        str_.add(fighterCapacite);
        str_.add(fighterObjet);
        str_.add(fighterStatuts);
        str_.add(fighterTypes);
        str_.add(fighterPp);
        str_.add(fighterNiveau);
        str_.add(fighterBonheur);
        str_.add(nbKoEquipeFighter);
        str_.add(nbKoEquipeAdvFighter);
        str_.add(coeffEffBaseTypesFighter);
        str_.add(coeffEff);
        str_.add(nbUtilisationConsecutif);
        str_.add(equipeNbUtilisation);
        str_.add(equipeAdvNbUtilisation);
        stat(str_);
        return str_;
    }

    private void stat(StringList _str) {
        _str.add(attack);
        _str.add(defense);
        _str.add(power);
    }

    private void feedExpBool(StringList _str) {
        _str.add(ciblePossedeStatutRelation);
        _str.add(cibleEffet);
        _str.add(pasPpAttaqueCible);
        _str.add(pasUtilisAttaqueCible);
        _str.add(immuTypeAttCible);
        _str.add(aucunBoostPossible);
        _str.add(lanceurEffet);
        _str.add(typesAttaquesResVide);
        _str.add(pasPartenaire);
        _str.add(pasPartenaireArriere);
        _str.add(pasPartenaireTerrain);
        _str.add(pasTourTerrain);
        _str.add(existeGenreAssexue);
        _str.add(genresEgaux);
    }
    private static boolean incorrectPrefix(String _pref) {
        return !_pref.isEmpty() && MathExpUtil.isDigit(_pref.charAt(0)) || incorrectCommonPart(_pref);
    }

    private static boolean incorrectKey(String _pref) {
        return _pref.startsWith("_") || incorrectCommonPart(_pref);
    }

    private static boolean incorrectCommonPart(String _pref) {
        return _pref.endsWith("_") || !DataBase.isCorrectIdentifier(_pref);
    }

    public String getPrefixVar() {
        return prefixVar;
    }

    public void setPrefixVar(String _p) {
        this.prefixVar = _p;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String _p) {
        this.niveau = _p;
    }

    public String getLevelLooser() {
        return levelLooser;
    }

    public void setLevelLooser(String _p) {
        this.levelLooser = _p;
    }

    public String getLevelWinner() {
        return levelWinner;
    }

    public void setLevelWinner(String _p) {
        this.levelWinner = _p;
    }

    public String getFighterNiveau() {
        return fighterNiveau;
    }

    public void setFighterNiveau(String _p) {
        this.fighterNiveau = _p;
    }

    public String getCibleNiveau() {
        return cibleNiveau;
    }

    public void setCibleNiveau(String _p) {
        this.cibleNiveau = _p;
    }

    public String getLanceurNiveau() {
        return lanceurNiveau;
    }

    public void setLanceurNiveau(String _p) {
        this.lanceurNiveau = _p;
    }

    public String getPkSauvageNiveau() {
        return pkSauvageNiveau;
    }

    public void setPkSauvageNiveau(String _p) {
        this.pkSauvageNiveau = _p;
    }

    public String getPkUtNiveau() {
        return pkUtNiveau;
    }

    public void setPkUtNiveau(String _p) {
        this.pkUtNiveau = _p;
    }

    public String getBoost() {
        return boost;
    }

    public void setBoost(String _b) {
        this.boost = _b;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String _p) {
        this.power = _p;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String _p) {
        this.attack = _p;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String _p) {
        this.defense = _p;
    }

    public String getBaseCaptPk() {
        return baseCaptPk;
    }

    public void setBaseCaptPk(String _p) {
        this.baseCaptPk = _p;
    }

    public String getRateBallStatus() {
        return rateBallStatus;
    }

    public void setRateBallStatus(String _p) {
        this.rateBallStatus = _p;
    }

    public String getFoePkMaxHp() {
        return foePkMaxHp;
    }

    public void setFoePkMaxHp(String _p) {
        this.foePkMaxHp = _p;
    }

    public String getFoePkRemoteHp() {
        return foePkRemoteHp;
    }

    public void setFoePkRemoteHp(String _p) {
        this.foePkRemoteHp = _p;
    }

    public String getCibleNbUtilisation() {
        return cibleNbUtilisation;
    }

    public void setCibleNbUtilisation(String _p) {
        this.cibleNbUtilisation = _p;
    }

    public String getFighterNbUtilisation() {
        return fighterNbUtilisation;
    }

    public void setFighterNbUtilisation(String _p) {
        this.fighterNbUtilisation = _p;
    }

    public String getLanceurNbUtilisation() {
        return lanceurNbUtilisation;
    }

    public void setLanceurNbUtilisation(String _p) {
        this.lanceurNbUtilisation = _p;
    }

    public String getCiblePp() {
        return ciblePp;
    }

    public void setCiblePp(String _p) {
        this.ciblePp = _p;
    }

    public String getFighterPp() {
        return fighterPp;
    }

    public void setFighterPp(String _p) {
        this.fighterPp = _p;
    }

    public String getLanceurPp() {
        return lanceurPp;
    }

    public void setLanceurPp(String _p) {
        this.lanceurPp = _p;
    }

    public String getCibleEffet() {
        return cibleEffet;
    }

    public void setCibleEffet(String _p) {
        this.cibleEffet = _p;
    }

    public String getLanceurEffet() {
        return lanceurEffet;
    }

    public void setLanceurEffet(String _p) {
        this.lanceurEffet = _p;
    }

    public String getCiblePvRestants() {
        return ciblePvRestants;
    }

    public void setCiblePvRestants(String _p) {
        this.ciblePvRestants = _p;
    }

    public String getFighterPvRestants() {
        return fighterPvRestants;
    }

    public void setFighterPvRestants(String _p) {
        this.fighterPvRestants = _p;
    }

    public String getLanceurPvRestants() {
        return lanceurPvRestants;
    }

    public void setLanceurPvRestants(String _p) {
        this.lanceurPvRestants = _p;
    }

    public String getCiblePvMax() {
        return ciblePvMax;
    }

    public void setCiblePvMax(String _p) {
        this.ciblePvMax = _p;
    }

    public String getFighterPvMax() {
        return fighterPvMax;
    }

    public void setFighterPvMax(String _p) {
        this.fighterPvMax = _p;
    }

    public String getLanceurPvMax() {
        return lanceurPvMax;
    }

    public void setLanceurPvMax(String _p) {
        this.lanceurPvMax = _p;
    }

    public String getCibleStatis() {
        return cibleStatis;
    }

    public void setCibleStatis(String _p) {
        this.cibleStatis = _p;
    }

    public String getFighterStatis() {
        return fighterStatis;
    }

    public void setFighterStatis(String _p) {
        this.fighterStatis = _p;
    }

    public String getLanceurStatis() {
        return lanceurStatis;
    }

    public void setLanceurStatis(String _p) {
        this.lanceurStatis = _p;
    }

    public String getCibleBoost() {
        return cibleBoost;
    }

    public void setCibleBoost(String _p) {
        this.cibleBoost = _p;
    }

    public String getFighterBoost() {
        return fighterBoost;
    }

    public void setFighterBoost(String _p) {
        this.fighterBoost = _p;
    }

    public String getLanceurBoost() {
        return lanceurBoost;
    }

    public void setLanceurBoost(String _p) {
        this.lanceurBoost = _p;
    }

    public String getEquipeNbUtilisation() {
        return equipeNbUtilisation;
    }

    public void setEquipeNbUtilisation(String _p) {
        this.equipeNbUtilisation = _p;
    }

    public String getEquipeAdvNbUtilisation() {
        return equipeAdvNbUtilisation;
    }

    public void setEquipeAdvNbUtilisation(String _p) {
        this.equipeAdvNbUtilisation = _p;
    }

    public String getCoeffEffBaseTypesCombattantEntrant() {
        return coeffEffBaseTypesCombattantEntrant;
    }

    public void setCoeffEffBaseTypesCombattantEntrant(String _p) {
        this.coeffEffBaseTypesCombattantEntrant = _p;
    }

    public String getCoeffEffBaseTypesCible() {
        return coeffEffBaseTypesCible;
    }

    public void setCoeffEffBaseTypesCible(String _p) {
        this.coeffEffBaseTypesCible = _p;
    }

    public String getCoeffEffBaseTypesLanceur() {
        return coeffEffBaseTypesLanceur;
    }

    public void setCoeffEffBaseTypesLanceur(String _p) {
        this.coeffEffBaseTypesLanceur = _p;
    }

    public String getCoeffEffBaseTypesFighter() {
        return coeffEffBaseTypesFighter;
    }

    public void setCoeffEffBaseTypesFighter(String _p) {
        this.coeffEffBaseTypesFighter = _p;
    }

    public String getSommeBoostPosCible() {
        return sommeBoostPosCible;
    }

    public void setSommeBoostPosCible(String _p) {
        this.sommeBoostPosCible = _p;
    }

    public String getSommeBoostPosLanceur() {
        return sommeBoostPosLanceur;
    }

    public void setSommeBoostPosLanceur(String _p) {
        this.sommeBoostPosLanceur = _p;
    }

    public String getSommeBoostPosFighter() {
        return sommeBoostPosFighter;
    }

    public void setSommeBoostPosFighter(String _p) {
        this.sommeBoostPosFighter = _p;
    }

    public String getImmuTypeAttCombattantEntrant() {
        return immuTypeAttCombattantEntrant;
    }

    public void setImmuTypeAttCombattantEntrant(String _p) {
        this.immuTypeAttCombattantEntrant = _p;
    }

    public String getImmuTypeAttCible() {
        return immuTypeAttCible;
    }

    public void setImmuTypeAttCible(String _p) {
        this.immuTypeAttCible = _p;
    }

    public String getEquipeAdvCombattantEntrantNbUtilisation() {
        return equipeAdvCombattantEntrantNbUtilisation;
    }

    public void setEquipeAdvCombattantEntrantNbUtilisation(String _p) {
        this.equipeAdvCombattantEntrantNbUtilisation = _p;
    }

    public String getNbTourGlobal() {
        return nbTourGlobal;
    }

    public void setNbTourGlobal(String _p) {
        this.nbTourGlobal = _p;
    }

    public String getNbUtiliAttEqTour() {
        return nbUtiliAttEqTour;
    }

    public void setNbUtiliAttEqTour(String _p) {
        this.nbUtiliAttEqTour = _p;
    }

    public String getCibleAttaques() {
        return cibleAttaques;
    }

    public void setCibleAttaques(String _p) {
        this.cibleAttaques = _p;
    }

    public String getCibleAttaqueChoisie() {
        return cibleAttaqueChoisie;
    }

    public void setCibleAttaqueChoisie(String _p) {
        this.cibleAttaqueChoisie = _p;
    }

    public String getCibleAttaquesTypes() {
        return cibleAttaquesTypes;
    }

    public void setCibleAttaquesTypes(String _p) {
        this.cibleAttaquesTypes = _p;
    }

    public String getCibleClone() {
        return cibleClone;
    }

    public void setCibleClone(String _p) {
        this.cibleClone = _p;
    }

    public String getCibleDegatsRecus() {
        return cibleDegatsRecus;
    }

    public void setCibleDegatsRecus(String _p) {
        this.cibleDegatsRecus = _p;
    }

    public String getCibleDegatsRecusTotal() {
        return cibleDegatsRecusTotal;
    }

    public void setCibleDegatsRecusTotal(String _p) {
        this.cibleDegatsRecusTotal = _p;
    }

    public String getCibleDegatsRecusTour() {
        return cibleDegatsRecusTour;
    }

    public void setCibleDegatsRecusTour(String _p) {
        this.cibleDegatsRecusTour = _p;
    }

    public String getCibleDegatsRecusTotalTour() {
        return cibleDegatsRecusTotalTour;
    }

    public void setCibleDegatsRecusTotalTour(String _p) {
        this.cibleDegatsRecusTotalTour = _p;
    }

    public String getCibleDisparait() {
        return cibleDisparait;
    }

    public void setCibleDisparait(String _p) {
        this.cibleDisparait = _p;
    }

    public String getCibleJoue() {
        return cibleJoue;
    }

    public void setCibleJoue(String _p) {
        this.cibleJoue = _p;
    }

    public String getCibleMasse() {
        return cibleMasse;
    }

    public void setCibleMasse(String _p) {
        this.cibleMasse = _p;
    }

    public String getCibleTaille() {
        return cibleTaille;
    }

    public void setCibleTaille(String _p) {
        this.cibleTaille = _p;
    }

    public String getCibleCapacite() {
        return cibleCapacite;
    }

    public void setCibleCapacite(String _p) {
        this.cibleCapacite = _p;
    }

    public String getCibleObjet() {
        return cibleObjet;
    }

    public void setCibleObjet(String _p) {
        this.cibleObjet = _p;
    }

    public String getCibleStatuts() {
        return cibleStatuts;
    }

    public void setCibleStatuts(String _p) {
        this.cibleStatuts = _p;
    }

    public String getCibleTypes() {
        return cibleTypes;
    }

    public void setCibleTypes(String _p) {
        this.cibleTypes = _p;
    }

    public String getCibleGenre() {
        return cibleGenre;
    }

    public void setCibleGenre(String _p) {
        this.cibleGenre = _p;
    }

    public String getCibleBonheur() {
        return cibleBonheur;
    }

    public void setCibleBonheur(String _p) {
        this.cibleBonheur = _p;
    }

    public String getCibleNom() {
        return cibleNom;
    }

    public void setCibleNom(String _p) {
        this.cibleNom = _p;
    }

    public String getCibleDerJoue() {
        return cibleDerJoue;
    }

    public void setCibleDerJoue(String _p) {
        this.cibleDerJoue = _p;
    }

    public String getCiblePossedeStatutRelation() {
        return ciblePossedeStatutRelation;
    }

    public void setCiblePossedeStatutRelation(String _p) {
        this.ciblePossedeStatutRelation = _p;
    }

    public String getNbKoEquipeCible() {
        return nbKoEquipeCible;
    }

    public void setNbKoEquipeCible(String _p) {
        this.nbKoEquipeCible = _p;
    }

    public String getNbKoEquipeAdvCible() {
        return nbKoEquipeAdvCible;
    }

    public void setNbKoEquipeAdvCible(String _p) {
        this.nbKoEquipeAdvCible = _p;
    }

    public String getPasPpAttaqueCible() {
        return pasPpAttaqueCible;
    }

    public void setPasPpAttaqueCible(String _p) {
        this.pasPpAttaqueCible = _p;
    }

    public String getPasUtilisAttaqueCible() {
        return pasUtilisAttaqueCible;
    }

    public void setPasUtilisAttaqueCible(String _p) {
        this.pasUtilisAttaqueCible = _p;
    }

    public String getLanceurAttaques() {
        return lanceurAttaques;
    }

    public void setLanceurAttaques(String _p) {
        this.lanceurAttaques = _p;
    }

    public String getLanceurAttaqueChoisie() {
        return lanceurAttaqueChoisie;
    }

    public void setLanceurAttaqueChoisie(String _p) {
        this.lanceurAttaqueChoisie = _p;
    }

    public String getLanceurAttaquesTypes() {
        return lanceurAttaquesTypes;
    }

    public void setLanceurAttaquesTypes(String _p) {
        this.lanceurAttaquesTypes = _p;
    }

    public String getLanceurClone() {
        return lanceurClone;
    }

    public void setLanceurClone(String _p) {
        this.lanceurClone = _p;
    }

    public String getLanceurDegatsRecus() {
        return lanceurDegatsRecus;
    }

    public void setLanceurDegatsRecus(String _p) {
        this.lanceurDegatsRecus = _p;
    }

    public String getLanceurDegatsRecusTotal() {
        return lanceurDegatsRecusTotal;
    }

    public void setLanceurDegatsRecusTotal(String _p) {
        this.lanceurDegatsRecusTotal = _p;
    }

    public String getLanceurDegatsRecusTour() {
        return lanceurDegatsRecusTour;
    }

    public void setLanceurDegatsRecusTour(String _p) {
        this.lanceurDegatsRecusTour = _p;
    }

    public String getLanceurDegatsRecusTotalTour() {
        return lanceurDegatsRecusTotalTour;
    }

    public void setLanceurDegatsRecusTotalTour(String _p) {
        this.lanceurDegatsRecusTotalTour = _p;
    }

    public String getLanceurDisparait() {
        return lanceurDisparait;
    }

    public void setLanceurDisparait(String _p) {
        this.lanceurDisparait = _p;
    }

    public String getLanceurJoue() {
        return lanceurJoue;
    }

    public void setLanceurJoue(String _p) {
        this.lanceurJoue = _p;
    }

    public String getLanceurMasse() {
        return lanceurMasse;
    }

    public void setLanceurMasse(String _p) {
        this.lanceurMasse = _p;
    }

    public String getLanceurTaille() {
        return lanceurTaille;
    }

    public void setLanceurTaille(String _p) {
        this.lanceurTaille = _p;
    }

    public String getLanceurCapacite() {
        return lanceurCapacite;
    }

    public void setLanceurCapacite(String _p) {
        this.lanceurCapacite = _p;
    }

    public String getLanceurObjet() {
        return lanceurObjet;
    }

    public void setLanceurObjet(String _p) {
        this.lanceurObjet = _p;
    }

    public String getLanceurStatuts() {
        return lanceurStatuts;
    }

    public void setLanceurStatuts(String _p) {
        this.lanceurStatuts = _p;
    }

    public String getLanceurTypes() {
        return lanceurTypes;
    }

    public void setLanceurTypes(String _p) {
        this.lanceurTypes = _p;
    }

    public String getLanceurGenre() {
        return lanceurGenre;
    }

    public void setLanceurGenre(String _p) {
        this.lanceurGenre = _p;
    }

    public String getLanceurBonheur() {
        return lanceurBonheur;
    }

    public void setLanceurBonheur(String _p) {
        this.lanceurBonheur = _p;
    }

    public String getLanceurNom() {
        return lanceurNom;
    }

    public void setLanceurNom(String _p) {
        this.lanceurNom = _p;
    }

    public String getLanceurDerJoue() {
        return lanceurDerJoue;
    }

    public void setLanceurDerJoue(String _p) {
        this.lanceurDerJoue = _p;
    }

    public String getNbKoEquipeLanceur() {
        return nbKoEquipeLanceur;
    }

    public void setNbKoEquipeLanceur(String _p) {
        this.nbKoEquipeLanceur = _p;
    }

    public String getNbKoEquipeAdvLanceur() {
        return nbKoEquipeAdvLanceur;
    }

    public void setNbKoEquipeAdvLanceur(String _p) {
        this.nbKoEquipeAdvLanceur = _p;
    }

    public String getFighterAttaques() {
        return fighterAttaques;
    }

    public void setFighterAttaques(String _p) {
        this.fighterAttaques = _p;
    }

    public String getFighterAttaqueChoisie() {
        return fighterAttaqueChoisie;
    }

    public void setFighterAttaqueChoisie(String _p) {
        this.fighterAttaqueChoisie = _p;
    }

    public String getFighterAttaquesTypes() {
        return fighterAttaquesTypes;
    }

    public void setFighterAttaquesTypes(String _p) {
        this.fighterAttaquesTypes = _p;
    }

    public String getFighterClone() {
        return fighterClone;
    }

    public void setFighterClone(String _p) {
        this.fighterClone = _p;
    }

    public String getFighterDegatsRecus() {
        return fighterDegatsRecus;
    }

    public void setFighterDegatsRecus(String _p) {
        this.fighterDegatsRecus = _p;
    }

    public String getFighterDegatsRecusTotal() {
        return fighterDegatsRecusTotal;
    }

    public void setFighterDegatsRecusTotal(String _p) {
        this.fighterDegatsRecusTotal = _p;
    }

    public String getFighterDegatsRecusTour() {
        return fighterDegatsRecusTour;
    }

    public void setFighterDegatsRecusTour(String _p) {
        this.fighterDegatsRecusTour = _p;
    }

    public String getFighterDegatsRecusTotalTour() {
        return fighterDegatsRecusTotalTour;
    }

    public void setFighterDegatsRecusTotalTour(String _p) {
        this.fighterDegatsRecusTotalTour = _p;
    }

    public String getFighterDisparait() {
        return fighterDisparait;
    }

    public void setFighterDisparait(String _p) {
        this.fighterDisparait = _p;
    }

    public String getFighterJoue() {
        return fighterJoue;
    }

    public void setFighterJoue(String _p) {
        this.fighterJoue = _p;
    }

    public String getFighterMasse() {
        return fighterMasse;
    }

    public void setFighterMasse(String _p) {
        this.fighterMasse = _p;
    }

    public String getFighterTaille() {
        return fighterTaille;
    }

    public void setFighterTaille(String _p) {
        this.fighterTaille = _p;
    }

    public String getFighterCapacite() {
        return fighterCapacite;
    }

    public void setFighterCapacite(String _p) {
        this.fighterCapacite = _p;
    }

    public String getFighterObjet() {
        return fighterObjet;
    }

    public void setFighterObjet(String _p) {
        this.fighterObjet = _p;
    }

    public String getFighterStatuts() {
        return fighterStatuts;
    }

    public void setFighterStatuts(String _p) {
        this.fighterStatuts = _p;
    }

    public String getFighterTypes() {
        return fighterTypes;
    }

    public void setFighterTypes(String _p) {
        this.fighterTypes = _p;
    }

    public String getFighterGenre() {
        return fighterGenre;
    }

    public void setFighterGenre(String _p) {
        this.fighterGenre = _p;
    }

    public String getFighterBonheur() {
        return fighterBonheur;
    }

    public void setFighterBonheur(String _p) {
        this.fighterBonheur = _p;
    }

    public String getFighterNom() {
        return fighterNom;
    }

    public void setFighterNom(String _p) {
        this.fighterNom = _p;
    }

    public String getFighterDerJoue() {
        return fighterDerJoue;
    }

    public void setFighterDerJoue(String _p) {
        this.fighterDerJoue = _p;
    }

    public String getNbKoEquipeFighter() {
        return nbKoEquipeFighter;
    }

    public void setNbKoEquipeFighter(String _p) {
        this.nbKoEquipeFighter = _p;
    }

    public String getNbKoEquipeAdvFighter() {
        return nbKoEquipeAdvFighter;
    }

    public void setNbKoEquipeAdvFighter(String _p) {
        this.nbKoEquipeAdvFighter = _p;
    }

    public String getPkSauvageGenre() {
        return pkSauvageGenre;
    }

    public void setPkSauvageGenre(String _p) {
        this.pkSauvageGenre = _p;
    }

    public String getPkSauvageMasse() {
        return pkSauvageMasse;
    }

    public void setPkSauvageMasse(String _p) {
        this.pkSauvageMasse = _p;
    }

    public String getPkSauvageVitesse() {
        return pkSauvageVitesse;
    }

    public void setPkSauvageVitesse(String _p) {
        this.pkSauvageVitesse = _p;
    }

    public String getPkSauvageTypesBase() {
        return pkSauvageTypesBase;
    }

    public void setPkSauvageTypesBase(String _p) {
        this.pkSauvageTypesBase = _p;
    }

    public String getPkSauvagePierresEvos() {
        return pkSauvagePierresEvos;
    }

    public void setPkSauvagePierresEvos(String _p) {
        this.pkSauvagePierresEvos = _p;
    }

    public String getPkUtGenre() {
        return pkUtGenre;
    }

    public void setPkUtGenre(String _p) {
        this.pkUtGenre = _p;
    }

    public String getPkUtMasse() {
        return pkUtMasse;
    }

    public void setPkUtMasse(String _p) {
        this.pkUtMasse = _p;
    }

    public String getPkUtVitesse() {
        return pkUtVitesse;
    }

    public void setPkUtVitesse(String _p) {
        this.pkUtVitesse = _p;
    }

    public String getPkUtTypesBase() {
        return pkUtTypesBase;
    }

    public void setPkUtTypesBase(String _p) {
        this.pkUtTypesBase = _p;
    }

    public String getPkUtPierresEvos() {
        return pkUtPierresEvos;
    }

    public void setPkUtPierresEvos(String _p) {
        this.pkUtPierresEvos = _p;
    }

    public String getCombattantEntrantClone() {
        return combattantEntrantClone;
    }

    public void setCombattantEntrantClone(String _p) {
        this.combattantEntrantClone = _p;
    }

    public String getCombattantEntrantTypes() {
        return combattantEntrantTypes;
    }

    public void setCombattantEntrantTypes(String _p) {
        this.combattantEntrantTypes = _p;
    }

    public String getAucunBoostPossible() {
        return aucunBoostPossible;
    }

    public void setAucunBoostPossible(String _p) {
        this.aucunBoostPossible = _p;
    }

    public String getTypesAttaquesResVide() {
        return typesAttaquesResVide;
    }

    public void setTypesAttaquesResVide(String _p) {
        this.typesAttaquesResVide = _p;
    }

    public String getPasPartenaire() {
        return pasPartenaire;
    }

    public void setPasPartenaire(String _p) {
        this.pasPartenaire = _p;
    }

    public String getPasPartenaireArriere() {
        return pasPartenaireArriere;
    }

    public void setPasPartenaireArriere(String _p) {
        this.pasPartenaireArriere = _p;
    }

    public String getPasPartenaireTerrain() {
        return pasPartenaireTerrain;
    }

    public void setPasPartenaireTerrain(String _p) {
        this.pasPartenaireTerrain = _p;
    }

    public String getPasTourTerrain() {
        return pasTourTerrain;
    }

    public void setPasTourTerrain(String _p) {
        this.pasTourTerrain = _p;
    }

    public String getExisteGenreAssexue() {
        return existeGenreAssexue;
    }

    public void setExisteGenreAssexue(String _p) {
        this.existeGenreAssexue = _p;
    }

    public String getGenresEgaux() {
        return genresEgaux;
    }

    public void setGenresEgaux(String _p) {
        this.genresEgaux = _p;
    }

    public String getRateEffMoveAgainstTarget() {
        return rateEffMoveAgainstTarget;
    }

    public void setRateEffMoveAgainstTarget(String _p) {
        this.rateEffMoveAgainstTarget = _p;
    }

    public String getCoeffEff() {
        return coeffEff;
    }

    public void setCoeffEff(String _p) {
        this.coeffEff = _p;
    }

    public String getNbUtilisationConsecutif() {
        return nbUtilisationConsecutif;
    }

    public void setNbUtilisationConsecutif(String _p) {
        this.nbUtilisationConsecutif = _p;
    }

    public String getAttaqueCategorie() {
        return attaqueCategorie;
    }

    public void setAttaqueCategorie(String _p) {
        this.attaqueCategorie = _p;
    }

    public String getAttaqueTypes() {
        return attaqueTypes;
    }

    public void setAttaqueTypes(String _p) {
        this.attaqueTypes = _p;
    }

    public String getAttaqueNom() {
        return attaqueNom;
    }

    public void setAttaqueNom(String _p) {
        this.attaqueNom = _p;
    }

    public String getPuissanceBase() {
        return puissanceBase;
    }

    public void setPuissanceBase(String _p) {
        this.puissanceBase = _p;
    }

    public String getPasAttaqueInvoc() {
        return pasAttaqueInvoc;
    }

    public void setPasAttaqueInvoc(String _p) {
        this.pasAttaqueInvoc = _p;
    }

    public String getPasAttaquesCopiables() {
        return pasAttaquesCopiables;
    }

    public void setPasAttaquesCopiables(String _p) {
        this.pasAttaquesCopiables = _p;
    }

    public String getNbTour() {
        return nbTour;
    }

    public void setNbTour(String _p) {
        this.nbTour = _p;
    }

    public String getDejaCapture() {
        return dejaCapture;
    }

    public void setDejaCapture(String _p) {
        this.dejaCapture = _p;
    }

    public String getNbFlees() {
        return nbFlees;
    }

    public void setNbFlees(String _p) {
        this.nbFlees = _p;
    }

    public String getMasseMoyennePk() {
        return masseMoyennePk;
    }

    public void setMasseMoyennePk(String _p) {
        this.masseMoyennePk = _p;
    }

    public String getClimats() {
        return climats;
    }

    public void setClimats(String _p) {
        this.climats = _p;
    }

    public String getNbCombattantsTerrain() {
        return nbCombattantsTerrain;
    }

    public void setNbCombattantsTerrain(String _p) {
        this.nbCombattantsTerrain = _p;
    }

    public String getLieuCombat() {
        return lieuCombat;
    }

    public void setLieuCombat(String _p) {
        this.lieuCombat = _p;
    }

    public String getTempsTour() {
        return tempsTour;
    }

    public void setTempsTour(String _p) {
        this.tempsTour = _p;
    }
}
