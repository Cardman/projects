package aiki.game.fight;

import aiki.db.DataBase;
import aiki.db.EquallablePkUtil;
import aiki.db.PerCent;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.util.TypesDuo;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.DataMap;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelCave;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoordsList;
import aiki.tsts.TstsPerCentImpl;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteral.EvolvedMathFactory;
import code.maths.montecarlo.DefaultGenerator;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.AbsMap;
import code.util.*;

import code.util.StringMap;
import code.util.core.StringUtil;

public class InitializationDataBase extends EquallablePkUtil {


    protected static final String LANGUAGE = "en";
    //or LANGUAGE == fr?

    protected static final String INVALID_DATA_KEY = "NOT_FOUND_DATA";

    /*back to NICKNAME value*/
    protected static final String NICKNAME = "CARDTEAM";

    protected static final TeamPosition POKEMON_PLAYER_FIGHTER_ZERO = Fight.toUserFighter((byte) 0);
    protected static final TeamPosition POKEMON_PLAYER_FIGHTER_ONE = Fight.toUserFighter((byte) 1);
    protected static final TeamPosition POKEMON_PLAYER_FIGHTER_TWO = Fight.toUserFighter((byte) 2);
    protected static final TeamPosition POKEMON_PLAYER_FIGHTER_THREE = Fight.toUserFighter((byte) 3);
    protected static final TeamPosition POKEMON_PLAYER_FIGHTER_FOUR = Fight.toUserFighter((byte) 4);

    protected static final TeamPosition POKEMON_FOE_FIGHTER_ZERO = Fight.toFoeFighter((byte) 0);
    protected static final TeamPosition POKEMON_FOE_FIGHTER_ONE = Fight.toFoeFighter((byte) 1);
    protected static final TeamPosition POKEMON_FOE_FIGHTER_TWO = Fight.toFoeFighter((byte) 2);
    protected static final TeamPosition POKEMON_FOE_FIGHTER_THREE = Fight.toFoeFighter((byte) 3);
    protected static final TeamPosition POKEMON_FOE_FIGHTER_FOUR = Fight.toFoeFighter((byte) 4);

    protected static final TargetCoords POKEMON_PLAYER_TARGET_ZERO = TargetCoords.toUserTarget((byte) 0);
    protected static final TargetCoords POKEMON_PLAYER_TARGET_ONE = TargetCoords.toUserTarget((byte) 1);
    protected static final TargetCoords POKEMON_PLAYER_TARGET_TWO = TargetCoords.toUserTarget((byte) 2);
    protected static final TargetCoords POKEMON_PLAYER_TARGET_THREE = TargetCoords.toUserTarget((byte) 3);

    protected static final TargetCoords POKEMON_FOE_TARGET_ZERO = TargetCoords.toFoeTarget((byte) 0);
    protected static final TargetCoords POKEMON_FOE_TARGET_ONE = TargetCoords.toFoeTarget((byte) 1);
    protected static final TargetCoords POKEMON_FOE_TARGET_TWO = TargetCoords.toFoeTarget((byte) 2);
    protected static final TargetCoords POKEMON_FOE_TARGET_THREE = TargetCoords.toFoeTarget((byte) 3);

    protected static final String F = "F";

    protected static final String V = "V";

    protected static final String TENEBRE = "TENEBRE";

    protected static final String SPECTRE = "SPECTRE";

    protected static final String SOL = "SOL";

    protected static final String ROCHE = "ROCHE";

    protected static final String PSY = "PSY";

    protected static final String PLANTE = "PLANTE";

    protected static final String NORMAL = "NORMAL";

    protected static final String INSECTE = "INSECTE";

    protected static final String GLACE = "GLACE";

    protected static final String FEU = "FEU";

    protected static final String ELECTRIQUE = "ELECTRIQUE";

    protected static final String EAU = "EAU";

    protected static final String DRAGON = "DRAGON";

    protected static final String COMBAT = "COMBAT";

    protected static final String FEE = "FEE";

    protected static final String ACIER = "ACIER";

    protected static final String POISON = "POISON";

    protected static final String VOL = "VOL";

    protected static final String CARABAFFE = InitializationPokedex.CARABAFFE;
    protected static final String CARAPUCE = InitializationPokedex.CARAPUCE;
    protected static final String LIMAGMA_M = InitializationPokedex.LIMAGMA_M;
    protected static final String LIMAGMA_F = InitializationPokedex.LIMAGMA_F;
    protected static final String PICHU = InitializationPokedex.PICHU;
    protected static final String MELODELFE_2 = InitializationPokedex.MELODELFE_2;
    protected static final String MELODELFE = InitializationPokedex.MELODELFE;
    protected static final String MELOFEE = InitializationPokedex.MELOFEE;
    protected static final String LIMAGMA = InitializationPokedex.LIMAGMA;
    protected static final String MEW = InitializationPokedex.MEW;
    protected static final String ARTIKODIN = InitializationPokedex.ARTIKODIN;
    protected static final String PIKACHU = InitializationPokedex.PIKACHU;
    protected static final String NINJASK = InitializationPokedex.NINJASK;
    protected static final String MUNJA = InitializationPokedex.MUNJA;
    protected static final String NINGALE = InitializationPokedex.NINGALE;
    protected static final String DEMANTA = InitializationPokedex.DEMANTA;
    protected static final String BABIMANTA = InitializationPokedex.BABIMANTA;
    protected static final String REMORAID = InitializationPokedex.REMORAID;
    protected static final String CHENISELLE = InitializationPokedex.CHENISELLE;
    protected static final String PAPILORD = InitializationPokedex.PAPILORD;
    protected static final String CHENITI = InitializationPokedex.CHENITI;
    protected static final String TARINORME = InitializationPokedex.TARINORME;
    protected static final String TARINOR = InitializationPokedex.TARINOR;
    protected static final String YANMEGA = InitializationPokedex.YANMEGA;
    protected static final String YANMA = InitializationPokedex.YANMA;
    protected static final String TARTARD = InitializationPokedex.TARTARD;
    protected static final String TARPAUD = InitializationPokedex.TARPAUD;
    protected static final String TETARTE = InitializationPokedex.TETARTE;
    protected static final String PTITARD = InitializationPokedex.PTITARD;
    protected static final String SYMBIOS = InitializationPokedex.SYMBIOS;
    protected static final String MEIOS = InitializationPokedex.MEIOS;
    protected static final String NUCLEOS = InitializationPokedex.NUCLEOS;
    protected static final String ONDE_VIDE = InitializationMoves.ONDE_VIDE;
    protected static final String ULTIMAWASHI = InitializationMoves.ULTIMAWASHI;
    protected static final String ULTIMAPOING = InitializationMoves.ULTIMAPOING;
    protected static final String POINGLACE = InitializationMoves.POINGLACE;
    protected static final String FRAPPE_ATLAS = InitializationMoves.FRAPPE_ATLAS;
    protected static final String TREMPETTE = InitializationMoves.TREMPETTE;
    protected static final String VENT_GLACE = InitializationMoves.VENT_GLACE;
    protected static final String DAMOCLES = InitializationMoves.DAMOCLES;
    protected static final String BALL_GLACE = InitializationMoves.BALL_GLACE;
    protected static final String ECHEC = InitializationMoves.ECHEC;
    protected static final String VENT_ARGENTE = InitializationMoves.VENT_ARGENTE;
    protected static final String UPPERCUT = InitializationMoves.UPPERCUT;
    protected static final String TRANCH_HERBE = InitializationMoves.TRANCH_HERBE;
    protected static final String TRANCHE_NUIT = InitializationMoves.TRANCHE_NUIT;
    protected static final String TRANCHE = InitializationMoves.TRANCHE;
    protected static final String TORNADE = InitializationMoves.TORNADE;
    protected static final String TEMPETEVERTE = InitializationMoves.TEMPETEVERTE;
    protected static final String TAILLADE = InitializationMoves.TAILLADE;
    protected static final String RAYON_SIGNAL = InitializationMoves.RAYON_SIGNAL;
    protected static final String RAFALE_PSY = InitializationMoves.RAFALE_PSY;
    protected static final String PLAIE_CROIX = InitializationMoves.PLAIE_CROIX;
    protected static final String OMBRE_PORTEE = InitializationMoves.OMBRE_PORTEE;
    protected static final String MEGAPHONE = InitializationMoves.MEGAPHONE;
    protected static final String FLEAU = InitializationMoves.FLEAU;
    protected static final String DYNAMOPOING = InitializationMoves.DYNAMOPOING;
    protected static final String CHOC_MENTAL = InitializationMoves.CHOC_MENTAL;
    protected static final String BOMBAIMANT = InitializationMoves.BOMBAIMANT;
    protected static final String BALLE_GRAINE = InitializationMoves.BALLE_GRAINE;
    protected static final String VIVE_ATTAQUE = InitializationMoves.VIVE_ATTAQUE;
    protected static final String VIBRAQUA = InitializationMoves.VIBRAQUA;
    protected static final String VAMPIRISME = InitializationMoves.VAMPIRISME;
    protected static final String VAGUE_PSY = InitializationMoves.VAGUE_PSY;
    protected static final String TORGNOLES = InitializationMoves.TORGNOLES;
    protected static final String TIR_DE_BOUE = InitializationMoves.TIR_DE_BOUE;
    protected static final String TELLURIFORCE = InitializationMoves.TELLURIFORCE;
    protected static final String SONICBOOM = InitializationMoves.SONICBOOM;
    protected static final String REVEIL_FORCE = InitializationMoves.REVEIL_FORCE;
    protected static final String RAYON_GEMME = InitializationMoves.RAYON_GEMME;
    protected static final String PUIS_CACHEE = InitializationMoves.PUIS_CACHEE;
    protected static final String PSYKO = InitializationMoves.PSYKO;
    protected static final String POURSUITE = InitializationMoves.POURSUITE;
    protected static final String PLAQUAGE = InitializationMoves.PLAQUAGE;
    protected static final String PISTOLET_A_O = InitializationMoves.PISTOLET_A_O;
    protected static final String PIQURE = InitializationMoves.PIQURE;
    protected static final String LAME_D_AIR = InitializationMoves.LAME_D_AIR;
    protected static final String LAME_DE_ROC = InitializationMoves.LAME_DE_ROC;
    protected static final String JET_PIERRES = InitializationMoves.JET_PIERRES;
    protected static final String GRIFFE_ACIER = InitializationMoves.GRIFFE_ACIER;
    protected static final String GRIFFE = InitializationMoves.GRIFFE;
    protected static final String ELECANON = InitializationMoves.ELECANON;
    protected static final String ECUME = InitializationMoves.ECUME;
    protected static final String CRU_AILE = InitializationMoves.CRU_AILE;
    protected static final String COUP_D_JUS = InitializationMoves.COUP_D_JUS;
    protected static final String COUP_D_BOULE = InitializationMoves.COUP_D_BOULE;
    protected static final String CHARGE = InitializationMoves.CHARGE;
    protected static final String BULLES_D_O = InitializationMoves.BULLES_D_O;
    protected static final String BOURDON = InitializationMoves.BOURDON;
    protected static final String BOUE_BOMBE = InitializationMoves.BOUE_BOMBE;
    protected static final String LOTO = InitializationMoves.LOTO;
    protected static final String CROC_FATAL = InitializationMoves.CROC_FATAL;
    protected static final String CALCINATION = InitializationMoves.CALCINATION;
    protected static final String POUV_ANTIQUE = InitializationMoves.POUV_ANTIQUE;
    protected static final String REBONDIFEU = InitializationMoves.REBONDIFEU;
    protected static final String BASTON = InitializationMoves.BASTON;
    protected static final String SURF = InitializationMoves.SURF;
    protected static final String EBULLITION = InitializationMoves.EBULLITION;
    protected static final String DARD_MORTEL = InitializationMoves.DARD_MORTEL;
    protected static final String RELACHE = InitializationMoves.RELACHE;
    protected static final String PICOTS_BIS = InitializationMoves.PICOTS_BIS;
    protected static final String VIGILANCE = InitializationMoves.VIGILANCE;
    protected static final String TATAMIGAESHI = InitializationMoves.TATAMIGAESHI;
    protected static final String PICORE = InitializationMoves.PICORE;
    protected static final String AIRE_D_HERBE = InitializationMoves.AIRE_D_HERBE;
    protected static final String AIRE_D_EAU = InitializationMoves.AIRE_D_EAU;
    protected static final String AIRE_DE_FEU = InitializationMoves.AIRE_DE_FEU;
    protected static final String VENT_ARRIERE_BIS = InitializationMoves.VENT_ARRIERE_BIS;
    protected static final String ASSISTANCE_BIS = InitializationMoves.ASSISTANCE_BIS;
    protected static final String RISQUE = InitializationMoves.RISQUE;
    protected static final String GUILLOTINE = InitializationMoves.GUILLOTINE;
    protected static final String GLACIATION = InitializationMoves.GLACIATION;
    protected static final String EMPAL_KORNE = InitializationMoves.EMPAL_KORNE;
    protected static final String ABIME = InitializationMoves.ABIME;
    protected static final String JACKPOT = InitializationMoves.JACKPOT;
    protected static final String BAIN_DE_SMOG = InitializationMoves.BAIN_DE_SMOG;
    protected static final String PLONGEE = InitializationMoves.PLONGEE;
    protected static final String BROUHAHA = InitializationMoves.BROUHAHA;
    protected static final String ECHANGE_BIS = InitializationMoves.ECHANGE_BIS;
    protected static final String DETEINTE = InitializationMoves.DETEINTE;
    protected static final String AIDE = InitializationMoves.AIDE;
    protected static final String TOILE_GLUANTE = InitializationMoves.TOILE_GLUANTE;
    protected static final String ANTI_CROISEUR = InitializationMoves.ANTI_CROISEUR;
    protected static final String ANTI_SOL = InitializationMoves.ANTI_SOL;
    protected static final String ANTI_AIR = InitializationMoves.ANTI_AIR;
    protected static final String REBOND = InitializationMoves.REBOND;
    protected static final String COUPE_VENT = InitializationMoves.COUPE_VENT;
    protected static final String ATTRITION = InitializationMoves.ATTRITION;
    protected static final String CHANT = InitializationMoves.CHANT;
    protected static final String CYCLE_V = InitializationMoves.CYCLE_V;
    protected static final String TROU_BIS = InitializationMoves.TROU_BIS;
    protected static final String TROU = InitializationMoves.TROU;
    protected static final String DESTRUCTION = InitializationMoves.DESTRUCTION;
    protected static final String BALL_ORAGE = InitializationMoves.BALL_ORAGE;
    protected static final String BALL_METEO = InitializationMoves.BALL_METEO;
    protected static final String DEMI_TOUR = InitializationMoves.DEMI_TOUR;
    protected static final String PERENITE_BIS = InitializationMoves.PERENITE_BIS;
    protected static final String PERENITE = InitializationMoves.PERENITE;
    protected static final String PSYKOUD_BOUL = InitializationMoves.PSYKOUD_BOUL;
    protected static final String COUD_BOUE = InitializationMoves.COUD_BOUE;
    protected static final String DRACO_RAGE = InitializationMoves.DRACO_RAGE;
    protected static final String BELIER = InitializationMoves.BELIER;
    protected static final String SACRIFICE = InitializationMoves.SACRIFICE;
    protected static final String GIGA_SANGSUE = InitializationMoves.GIGA_SANGSUE;
    protected static final String VAMPIPOING = InitializationMoves.VAMPIPOING;
    protected static final String DEVOREVE = InitializationMoves.DEVOREVE;
    protected static final String EXCUSE = InitializationMoves.EXCUSE;
    protected static final String POISSE = InitializationMoves.POISSE;
    protected static final String ROC_BOULET = InitializationMoves.ROC_BOULET;
    protected static final String PINCE_MASSE = InitializationMoves.PINCE_MASSE;
    protected static final String COUD_KRANE = InitializationMoves.COUD_KRANE;
    protected static final String HYDROCANON = InitializationMoves.HYDROCANON;
    protected static final String BALL_OMBRE = InitializationMoves.BALL_OMBRE;
    protected static final String EBOULEMENT = InitializationMoves.EBOULEMENT;
    protected static final String PRESCIENCE = InitializationMoves.PRESCIENCE;
    protected static final String LANCECROU = InitializationMoves.LANCECROU;
    protected static final String ORAGE_BIS = InitializationMoves.ORAGE_BIS;
    protected static final String PLANNEUR = InitializationMoves.PLANNEUR;
    protected static final String ZONE_MAGIQUE = InitializationMoves.ZONE_MAGIQUE;
    protected static final String ZONE_ETRANGE = InitializationMoves.ZONE_ETRANGE;
    protected static final String ZENITH = InitializationMoves.ZENITH;
    protected static final String VOL_MAGNETIK = InitializationMoves.VOL_MAGNETIK;
    protected static final String VOEU = InitializationMoves.VOEU;
    protected static final String VENT_ARRIERE = InitializationMoves.VENT_ARRIERE;
    protected static final String VANTARDISE = InitializationMoves.VANTARDISE;
    protected static final String TOURNIQUET = InitializationMoves.TOURNIQUET;
    protected static final String TOURMENTE = InitializationMoves.TOURMENTE;
    protected static final String TEMPETESABLE = InitializationMoves.TEMPETESABLE;
    protected static final String STOCKAGE = InitializationMoves.STOCKAGE;
    protected static final String RUNE_PROTECT = InitializationMoves.RUNE_PROTECT;
    protected static final String REPOS = InitializationMoves.REPOS;
    protected static final String REGENERATION = InitializationMoves.REGENERATION;
    protected static final String REGARD_NOIR = InitializationMoves.REGARD_NOIR;
    protected static final String REFLET = InitializationMoves.REFLET;
    protected static final String RECYCLAGE = InitializationMoves.RECYCLAGE;
    protected static final String RAYON_SIMPLE = InitializationMoves.RAYON_SIMPLE;
    protected static final String RAYON_UV = InitializationMoves.RAYON_UV;
    protected static final String RAYON_LUNE = InitializationMoves.RAYON_LUNE;
    protected static final String RACINES = InitializationMoves.RACINES;
    protected static final String PROVOC = InitializationMoves.PROVOC;
    protected static final String PROTECTION = InitializationMoves.PROTECTION;
    protected static final String POUDRE_TOXIK = InitializationMoves.POUDRE_TOXIK;
    protected static final String POSSESSIF = InitializationMoves.POSSESSIF;
    protected static final String PERMUCOEUR = InitializationMoves.PERMUCOEUR;
    protected static final String PARTAGE_GARDE = InitializationMoves.PARTAGE_GARDE;
    protected static final String ORAGE = InitializationMoves.ORAGE;
    protected static final String OEIL_MIRACLE = InitializationMoves.OEIL_MIRACLE;
    protected static final String MUR_LUMIERE = InitializationMoves.MUR_LUMIERE;
    protected static final String MUR_DE_FER = InitializationMoves.MUR_DE_FER;
    protected static final String MALEDICTION = InitializationMoves.MALEDICTION;
    protected static final String MALEDICTION_2 = InitializationMoves.MALEDICTION_2;
    protected static final String LEVIKINESIE = InitializationMoves.LEVIKINESIE;
    protected static final String JET_DE_SABLE = InitializationMoves.JET_DE_SABLE;
    protected static final String HATE = InitializationMoves.HATE;
    protected static final String GRINCEMENT = InitializationMoves.GRINCEMENT;
    protected static final String GLAS_DE_SOIN = InitializationMoves.GLAS_DE_SOIN;
    protected static final String ENTRAVE = InitializationMoves.ENTRAVE;
    protected static final String ENCORE = InitializationMoves.ENCORE;
    protected static final String EMBARGO = InitializationMoves.EMBARGO;
    protected static final String ECHANGE = InitializationMoves.ECHANGE;
    protected static final String ECHANGE_PSY = InitializationMoves.ECHANGE_PSY;
    protected static final String DOUX_PARFUM = InitializationMoves.DOUX_PARFUM;
    protected static final String DISTORSION = InitializationMoves.DISTORSION;
    protected static final String DETREMPAGE = InitializationMoves.DETREMPAGE;
    protected static final String DEPIT = InitializationMoves.DEPIT;
    protected static final String DANSE_PLUME = InitializationMoves.DANSE_PLUME;
    protected static final String DANSE_PLUIE = InitializationMoves.DANSE_PLUIE;
    protected static final String DANSE_LUNE = InitializationMoves.DANSE_LUNE;
    protected static final String DANSE_LAMES = InitializationMoves.DANSE_LAMES;
    protected static final String CYCLONE = InitializationMoves.CYCLONE;
    protected static final String CROISSANCE = InitializationMoves.CROISSANCE;
    protected static final String COGNOBIDON = InitializationMoves.COGNOBIDON;
    protected static final String CLONAGE = InitializationMoves.CLONAGE;
    protected static final String CLAIRVOYANCE = InitializationMoves.CLAIRVOYANCE;
    protected static final String CHARME = InitializationMoves.CHARME;
    protected static final String CHARGEUR = InitializationMoves.CHARGEUR;
    protected static final String CAMOUFLAGE = InitializationMoves.CAMOUFLAGE;
    protected static final String BRUME = InitializationMoves.BRUME;
    protected static final String BOUL_ARMURE = InitializationMoves.BOUL_ARMURE;
    protected static final String BOOST = InitializationMoves.BOOST;
    protected static final String BALANCE = InitializationMoves.BALANCE;
    protected static final String BAILLEMENT = InitializationMoves.BAILLEMENT;
    protected static final String ATTERRISSAGE = InitializationMoves.ATTERRISSAGE;
    protected static final String ARMURE = InitializationMoves.ARMURE;
    protected static final String ANTI_BRUME = InitializationMoves.ANTI_BRUME;
    protected static final String ANNEAU_HYDRO = InitializationMoves.ANNEAU_HYDRO;
    protected static final String AIR_VEINARD = InitializationMoves.AIR_VEINARD;
    protected static final String ADAPTATION = InitializationMoves.ADAPTATION;
    protected static final String ACUPRESSION = InitializationMoves.ACUPRESSION;
    protected static final String EFFORT = InitializationMoves.EFFORT;
    protected static final String FULMIFER = InitializationMoves.FULMIFER;
    protected static final String CASSE = InitializationMoves.CASSE;
    protected static final String PIEGE_DE_ROC = InitializationMoves.PIEGE_DE_ROC;
    protected static final String PICS_TOXIK = InitializationMoves.PICS_TOXIK;
    protected static final String PICOTS = InitializationMoves.PICOTS;
    protected static final String DEGOMMAGE = InitializationMoves.DEGOMMAGE;
    protected static final String CONVERSION_2 = InitializationMoves.CONVERSION_2;
    protected static final String BUEE_NOIRE = InitializationMoves.BUEE_NOIRE;
    protected static final String CHAMP_BRUMEUX = InitializationMoves.CHAMP_BRUMEUX;
    protected static final String TOUR_RAPIDE = InitializationMoves.TOUR_RAPIDE;
    protected static final String REQUIEM = InitializationMoves.REQUIEM;
    protected static final String GRAVITE = InitializationMoves.GRAVITE;
    protected static final String TIPHON = InitializationMoves.TIPHON;
    protected static final String SIPHON = InitializationMoves.SIPHON;
    protected static final String TEN_DANSE = InitializationMoves.TEN_DANSE;
    protected static final String INTERVERSION = InitializationMoves.INTERVERSION;
    protected static final String ECHANGE_TYPE = InitializationMoves.ECHANGE_TYPE;
    protected static final String FORCE_TYPE = InitializationMoves.FORCE_TYPE;
    protected static final String MALEFICE_SYLVAIN = InitializationMoves.MALEFICE_SYLVAIN;
    protected static final String COPIE_TYPE = InitializationMoves.COPIE_TYPE;
    protected static final String TENACITE = InitializationMoves.TENACITE;
    protected static final String SAISIE = InitializationMoves.SAISIE;
    protected static final String RUSE = InitializationMoves.RUSE;
    protected static final String RIPOSTE = InitializationMoves.RIPOSTE;
    protected static final String REFLET_MAGIK = InitializationMoves.REFLET_MAGIK;
    protected static final String PREVENTION = InitializationMoves.PREVENTION;
    protected static final String PHOTOCOPIE = InitializationMoves.PHOTOCOPIE;
    protected static final String PASSE_PASSE = InitializationMoves.PASSE_PASSE;
    protected static final String PASSE_CADEAU = InitializationMoves.PASSE_CADEAU;
    protected static final String PAR_ICI = InitializationMoves.PAR_ICI;
    protected static final String MORPHING = InitializationMoves.MORPHING;
    protected static final String MOI_D_ABORD = InitializationMoves.MOI_D_ABORD;
    protected static final String MIMIQUE = InitializationMoves.MIMIQUE;
    protected static final String LUTTE = InitializationMoves.LUTTE;
    protected static final String LARCIN = InitializationMoves.LARCIN;
    protected static final String GRIBOUILLE = InitializationMoves.GRIBOUILLE;
    protected static final String GARDE_LARGE = InitializationMoves.GARDE_LARGE;
    protected static final String FORCE_NATURE = InitializationMoves.FORCE_NATURE;
    protected static final String DETECTION = InitializationMoves.DETECTION;
    protected static final String COUP_D_MAIN = InitializationMoves.COUP_D_MAIN;
    protected static final String COUP_D_MAIN_2 = InitializationMoves.COUP_D_MAIN_2;
    protected static final String COPIE = InitializationMoves.COPIE;
    protected static final String BLABLA_DODO = InitializationMoves.BLABLA_DODO;
    protected static final String ASSISTANCE = InitializationMoves.ASSISTANCE;
    protected static final String APRES_VOUS = InitializationMoves.APRES_VOUS;
    protected static final String A_LA_QUEUE = InitializationMoves.A_LA_QUEUE;
    protected static final String IMITATION = InitializationMoves.IMITATION;
    protected static final String FAUX_CHAGE = InitializationMoves.FAUX_CHAGE;
    protected static final String SEDUCTION = InitializationMoves.SEDUCTION;
    protected static final String ATTRACTION = InitializationMoves.ATTRACTION;
    protected static final String ULTRASON = InitializationMoves.ULTRASON;
    protected static final String ONDE_FOLIE = InitializationMoves.ONDE_FOLIE;
    protected static final String CAGE_ECLAIR = InitializationMoves.CAGE_ECLAIR;
    protected static final String HYPNOSE = InitializationMoves.HYPNOSE;
    protected static final String DODO_PETIT = InitializationMoves.DODO_PETIT;
    protected static final String DODO = InitializationMoves.DODO;
    protected static final String BERCEUSE = InitializationMoves.BERCEUSE;
    protected static final String TONNERRE = InitializationMoves.TONNERRE;
    protected static final String ECLAIR = InitializationMoves.ECLAIR;
    protected static final String SEISME = InitializationMoves.SEISME;
    protected static final String TUNNEL = InitializationMoves.TUNNEL;
    protected static final String CHOC_PSY = InitializationMoves.CHOC_PSY;
    protected static final String IMPLORE = InitializationMoves.IMPLORE;
    protected static final String RONFLEMENT = InitializationMoves.RONFLEMENT;
    protected static final String FONCE = InitializationMoves.FONCE;
    protected static final String VAS_Y = InitializationMoves.VAS_Y;
    protected static final String ROUE_DE_FEU = InitializationMoves.ROUE_DE_FEU;
    protected static final String COMBO_GRIFFE = InitializationMoves.COMBO_GRIFFE;
    protected static final String DOUBLE_PIED = InitializationMoves.DOUBLE_PIED;
    protected static final String ROULADE = InitializationMoves.ROULADE;
    protected static final String ANTI_SOIN = InitializationMoves.ANTI_SOIN;
    protected static final String SOIN = InitializationMoves.SOIN;
    protected static final String LIRE_ESPRIT = InitializationMoves.LIRE_ESPRIT;
    protected static final String PICO_DEFENSE = InitializationMoves.PICO_DEFENSE;
    protected static final String BOUCLIER_ROYAL = InitializationMoves.BOUCLIER_ROYAL;
    protected static final String NUEE_DE_POUDRE = InitializationMoves.NUEE_DE_POUDRE;
    protected static final String DELUGE_GLACIAL = InitializationMoves.DELUGE_GLACIAL;
    protected static final String DELUGE_PLASMIQUE = InitializationMoves.DELUGE_PLASMIQUE;
    protected static final String ELECTRISATION = InitializationMoves.ELECTRISATION;
    protected static final String RELAIS = InitializationMoves.RELAIS;
    protected static final String DON_NATUREL = InitializationMoves.DON_NATUREL;
    protected static final String ABRI = InitializationMoves.ABRI;
    protected static final String METRONOME = InitializationMoves.METRONOME;
    protected static final String PARADOXE = InitializationMoves.PARADOXE;
    protected static final String BATAILLE = InitializationMoves.BATAILLE;
    protected static final String SPECIALE = InitializationMoves.SPECIALE;
    protected static final String PHYSIQUE = InitializationMoves.PHYSIQUE;
    protected static final String ENVOL = InitializationMoves.ENVOL;
    protected static final String ARMUROUILLEE = InitializationAbilities.ARMUROUILLEE;

    protected static final String SERENITE = InitializationAbilities.SERENITE;

    protected static final String TURBO = InitializationAbilities.TURBO;

    protected static final String STATIK = InitializationAbilities.STATIK;

    protected static final String PYROMANE = InitializationAbilities.PYROMANE;

    protected static final String TOXITOUCHE = InitializationAbilities.TOXITOUCHE;

    protected static final String SNIPER = InitializationAbilities.SNIPER;

    protected static final String TETE_DE_ROC = InitializationAbilities.TETE_DE_ROC;

    protected static final String PEAU_MIRACLE_QUATER = InitializationAbilities.PEAU_MIRACLE_QUATER;

    protected static final String PEAU_MIRACLE_TER = InitializationAbilities.PEAU_MIRACLE_TER;

    protected static final String PEAU_MIRACLE_BIS = InitializationAbilities.PEAU_MIRACLE_BIS;

    protected static final String PEAU_MIRACLE = InitializationAbilities.PEAU_MIRACLE;

    protected static final String POISSON = InitializationAbilities.POISSON;

    protected static final String MUE = InitializationAbilities.MUE;

    protected static final String TECHNICIEN = InitializationAbilities.TECHNICIEN;

    protected static final String ALEA_STAT = InitializationAbilities.ALEA_STAT;

    protected static final String TELECHARGE = InitializationAbilities.TELECHARGE;

    protected static final String DEGUISEMENT = InitializationAbilities.DEGUISEMENT;

    protected static final String PROTEEN = InitializationAbilities.PROTEEN;

    protected static final String AILES_BOURRASQUE = InitializationAbilities.AILES_BOURRASQUE;

    protected static final String AURA_INVERSEE = InitializationAbilities.AURA_INVERSEE;

    protected static final String AURA_TENEBREUSE = InitializationAbilities.AURA_TENEBREUSE;

    protected static final String TERA_VOLTAGE = InitializationAbilities.TERA_VOLTAGE;

    protected static final String TENSION = InitializationAbilities.TENSION;

    protected static final String REGARD_VIF = InitializationAbilities.REGARD_VIF;

    protected static final String CORPS_SAIN = InitializationAbilities.CORPS_SAIN;

    protected static final String ATTENTION = InitializationAbilities.ATTENTION;

    protected static final String ARMUMAGMA = InitializationAbilities.ARMUMAGMA;

    protected static final String ANTI_BRUIT = InitializationAbilities.ANTI_BRUIT;

    protected static final String CHANCEUX = InitializationAbilities.CHANCEUX;

    protected static final String SOLIDE_ROC = InitializationAbilities.SOLIDE_ROC;

    protected static final String INCONSCIENT = InitializationAbilities.INCONSCIENT;

    protected static final String MULTITYPE = InitializationAbilities.MULTITYPE;

    protected static final String COLERIQUE = InitializationAbilities.COLERIQUE;

    protected static final String INFILTRATION = InitializationAbilities.INFILTRATION;

    protected static final String MATINAL = InitializationAbilities.MATINAL;

    protected static final String AIR_LOCK = InitializationAbilities.AIR_LOCK;

    protected static final String COEUR_NOBLE = InitializationAbilities.COEUR_NOBLE;

    protected static final String TIRS = InitializationAbilities.TIRS;

    protected static final String MAGNEPIEGE = InitializationAbilities.MAGNEPIEGE;

    protected static final String BOOM_FINAL = InitializationAbilities.BOOM_FINAL;

    protected static final String LENTITEINTEE = InitializationAbilities.LENTITEINTEE;

    protected static final String ECRAN_POUDRE = InitializationAbilities.ECRAN_POUDRE;

    protected static final String REGE_FORCE = InitializationAbilities.REGE_FORCE;

    protected static final String ACHARNE = InitializationAbilities.ACHARNE;

    protected static final String IMPASSIBLE = InitializationAbilities.IMPASSIBLE;

    protected static final String HYPER_CUTTER = InitializationAbilities.HYPER_CUTTER;

    protected static final String SUINTEMENT = InitializationAbilities.SUINTEMENT;

    protected static final String TELEPATHE = InitializationAbilities.TELEPATHE;

    protected static final String MULTI_COUPS = InitializationAbilities.MULTI_COUPS;

    protected static final String ADAPTABILITE = InitializationAbilities.ADAPTABILITE;

    protected static final String IMPUDENCE = InitializationAbilities.IMPUDENCE;

    protected static final String GARDE_AMIE = InitializationAbilities.GARDE_AMIE;

    protected static final String GARDE_POUR_SOI = InitializationAbilities.GARDE_POUR_SOI;

    protected static final String GARDE_MYSTIK = InitializationAbilities.GARDE_MYSTIK;

    protected static final String IGNIFUGE = InitializationAbilities.IGNIFUGE;

    protected static final String CONTRAIRE = InitializationAbilities.CONTRAIRE;

    protected static final String SIMPLE = InitializationAbilities.SIMPLE;

    protected static final String FREIN = InitializationAbilities.FREIN;

    protected static final String SYNCHRO = InitializationAbilities.SYNCHRO;
    protected static final String ASYNCHRO = InitializationAbilities.ASYNCHRO;

    protected static final String ANNULE_GARDE = InitializationAbilities.ANNULE_GARDE;

    protected static final String METEO = InitializationAbilities.METEO;

    protected static final String ABSORB_VOLT = InitializationAbilities.ABSORB_VOLT;

    protected static final String ABSORB_EAU = InitializationAbilities.ABSORB_EAU;

    protected static final String CRAN = InitializationAbilities.CRAN;

    protected static final String PIED_RAPIDE = InitializationAbilities.PIED_RAPIDE;

    protected static final String PIED_VELOCE = InitializationAbilities.PIED_VELOCE;

    protected static final String GLOUTONNERIE = InitializationAbilities.GLOUTONNERIE;

    protected static final String SANS_LIMITE = InitializationAbilities.SANS_LIMITE;

    protected static final String PEAU_DURE = InitializationAbilities.PEAU_DURE;

    protected static final String FARCEUR = InitializationAbilities.FARCEUR;

    protected static final String FEUILLE_PETITE = InitializationAbilities.FEUILLE_PETITE;

    protected static final String FEUILLE_GARDE = InitializationAbilities.FEUILLE_GARDE;

    protected static final String GARDE_MAGIK = InitializationAbilities.GARDE_MAGIK;

    protected static final String TERA_VOLT = InitializationAbilities.TERA_VOLT;

    protected static final String MAUVAIS_REVE = InitializationAbilities.MAUVAIS_REVE;

    protected static final String MEDIC_NATURE = InitializationAbilities.MEDIC_NATURE;

    protected static final String ARMURBASTON = InitializationAbilities.ARMURBASTON;

    protected static final String PRESSION = InitializationAbilities.PRESSION;

    protected static final String CALQUE = InitializationAbilities.CALQUE;

    protected static final String LEVITATION = InitializationAbilities.LEVITATION;

    protected static final String PARATONNERRE = InitializationAbilities.PARATONNERRE;

    protected static final String NORMALISE = InitializationAbilities.NORMALISE;

    protected static final String SECHERESSE = InitializationAbilities.SECHERESSE;

    protected static final String TANT_PIS = InitializationAbilities.TANT_PIS;

    protected static final String COEUR_SOIN = InitializationAbilities.COEUR_SOIN;

    protected static final String MAGICIEN = InitializationAbilities.MAGICIEN;

    protected static final String SYMBIOSE = InitializationAbilities.SYMBIOSE;

    protected static final String POISSEUX = InitializationAbilities.POISSEUX;

    protected static final String BAJOUES = InitializationAbilities.BAJOUES;

    protected static final String AROMA_VOILE = InitializationAbilities.AROMA_VOILE;

    protected static final String FLORA_VOILE = InitializationAbilities.FLORA_VOILE;

    protected static final String PEAU_FEERIQUE = InitializationAbilities.PEAU_FEERIQUE;

    protected static final String JOLI_SOURIRE = InitializationAbilities.JOLI_SOURIRE;

    protected static final String QUERELLEUR = InitializationAbilities.QUERELLEUR;

    protected static final String ESSAIM = InitializationAbilities.ESSAIM;

    protected static final String OEIL_COMPOSE = InitializationAbilities.OEIL_COMPOSE;

    protected static final String ENVELOCAPE = InitializationAbilities.ENVELOCAPE;

    protected static final String GLISSADE = InitializationAbilities.GLISSADE;

    protected static final String FERMETE = InitializationAbilities.FERMETE;

    protected static final String MOITEUR = InitializationAbilities.MOITEUR;

    protected static final String CONTRE = InitializationAbilities.CONTRE;

    protected static final String CRACHIN = InitializationAbilities.CRACHIN;

    protected static final String GARDE = InitializationAbilities.GARDE;

    protected static final String FOUR = InitializationAbilities.FOUR;

    protected static final String ABRI_CAPE = InitializationAbilities.ABRI_CAPE;

    protected static final String ROCHE_LISSE = InitializationItems.ROCHE_LISSE;
    protected static final String HERBEBLANCHE = InitializationItems.HERBEBLANCHE;
    protected static final String HUILE_MAX = InitializationItems.HUILE_MAX;
    protected static final String BALLON = InitializationItems.BALLON;
    protected static final String LAVA = InitializationItems.LAVA;
    protected static final String VIEIL_AMBRE = InitializationItems.VIEIL_AMBRE;
    protected static final String GRELOT_COQUE = InitializationItems.GRELOT_COQUE;
    protected static final String VIVE_GRIFFE_FALSE = InitializationItems.VIVE_GRIFFE_FALSE;
    protected static final String VIVE_GRIFFE_TRUE_FALSE = InitializationItems.VIVE_GRIFFE_TRUE_FALSE;
    protected static final String VIVE_GRIFFE_TRUE = InitializationItems.VIVE_GRIFFE_TRUE;
    protected static final String VIVE_GRIFFE = InitializationItems.VIVE_GRIFFE;
    protected static final String OEUF_CHANCE = InitializationItems.OEUF_CHANCE;
    protected static final String BAIE_JABOCA = InitializationItems.BAIE_JABOCA;
    protected static final String ENCENS_PUR = InitializationItems.ENCENS_PUR;
    protected static final String ACCRO_GRIFFE = InitializationItems.ACCRO_GRIFFE;
    protected static final String GRELOT_ZEN = InitializationItems.GRELOT_ZEN;
    protected static final String BAIE_CERIZ = InitializationItems.BAIE_CERIZ;
    protected static final String HERBE_POUV = InitializationItems.HERBE_POUV;
    protected static final String BAIE_LANSAT = InitializationItems.BAIE_LANSAT;
    protected static final String BAIE_PITAYE = InitializationItems.BAIE_PITAYE;
    protected static final String BANDEAU = InitializationItems.BANDEAU;
    protected static final String ELIXIR = InitializationItems.ELIXIR;
    protected static final String ENCENS_VAGUE = InitializationItems.ENCENS_VAGUE;
    protected static final String HERBE_MENTAL = InitializationItems.HERBE_MENTAL;
    protected static final String BOUE_BLANCHE = InitializationItems.BOUE_BLANCHE;
    protected static final String BOUE_NOIRE = InitializationItems.BOUE_NOIRE;
    protected static final String POUDRE_ATTAQUE = InitializationItems.POUDRE_ATTAQUE;
    protected static final String POUDRE_VITE = InitializationItems.POUDRE_VITE;
    protected static final String PIERRE_GLACE = InitializationItems.PIERRE_GLACE;
    protected static final String PIERRE_SOLEIL = InitializationItems.PIERRE_SOLEIL;
    protected static final String PIERRE_LUNE = InitializationItems.PIERRE_LUNE;
    protected static final String PIERRE_EAU = InitializationItems.PIERRE_EAU;
    protected static final String ROCHE_ROYALE = InitializationItems.ROCHE_ROYALE;
    protected static final String PAS_DE_BALL = InitializationItems.PAS_DE_BALL;
    protected static final String BANDEAU_ETREINTE = InitializationItems.BANDEAU_ETREINTE;
    protected static final String CARTE_ROUGE = InitializationItems.CARTE_ROUGE;
    protected static final String BATON = InitializationItems.BATON;
    protected static final String CEINT_POUV = InitializationItems.CEINT_POUV;
    protected static final String BAIE_CHERIM = InitializationItems.BAIE_CHERIM;
    protected static final String CABLE = InitializationItems.CABLE;
    protected static final String PIERRALLEGEE = InitializationItems.PIERRALLEGEE;
    protected static final String PIECE_RUNE = InitializationItems.PIECE_RUNE;
    protected static final String PIQUANTS = InitializationItems.PIQUANTS;
    protected static final String REVEIL = InitializationItems.REVEIL;
    protected static final String TOTAL_SOIN = InitializationItems.TOTAL_SOIN;
    protected static final String PT_DE_MIRE = InitializationItems.PT_DE_MIRE;
    protected static final String BAIE_MEPO = InitializationItems.BAIE_MEPO;
    protected static final String BOLT = InitializationItems.BOLT;
    protected static final String MUSCLE = InitializationItems.MUSCLE;
    protected static final String PP_PLUS_BIS = InitializationItems.PP_PLUS_BIS;
    protected static final String PP_PLUS = InitializationItems.PP_PLUS;
    protected static final String ORBE_FLAMME = InitializationItems.ORBE_FLAMME;
    protected static final String BAIE_GOWAV = InitializationItems.BAIE_GOWAV;
    protected static final String PV_PLUS = InitializationItems.PV_PLUS;
    protected static final String MAX_REPOUSSE = InitializationItems.MAX_REPOUSSE;
    protected static final String CEINTURE_PRO = InitializationItems.CEINTURE_PRO;
    protected static final String ENCENS_PLEIN = InitializationItems.ENCENS_PLEIN;
    protected static final String BAIE_ORAN = InitializationItems.BAIE_ORAN;
    protected static final String BAIE_MICLE = InitializationItems.BAIE_MICLE;
    protected static final String BRAC_MACHO = InitializationItems.BRAC_MACHO;
    protected static final String GRAIN_MIRACL = InitializationItems.GRAIN_MIRACL;
    protected static final String PIERRE_STASE = InitializationItems.PIERRE_STASE;
    protected static final String MULTI_EXP = InitializationItems.MULTI_EXP;
    protected static final String LENTILSCOPE = InitializationItems.LENTILSCOPE;
    protected static final String CEINT_FORCE = InitializationItems.CEINT_FORCE;
    protected static final String RESTES = InitializationItems.RESTES;
    protected static final String HAPPY_POTION = InitializationItems.HAPPY_POTION;
    protected static final String POTION = InitializationItems.POTION;
    protected static final String EAU_FRAICHE = InitializationItems.EAU_FRAICHE;
    protected static final String HYPER_BALL = InitializationItems.HYPER_BALL;
    protected static final String SUPER_BALL = InitializationItems.SUPER_BALL;
    protected static final String POKE_BALL = InitializationItems.POKE_BALL;
    protected static final String MASTER_BALL = InitializationItems.MASTER_BALL;
    protected static final String LUXE_BALL = InitializationItems.LUXE_BALL;
    protected static final String ORBE_VIE = InitializationItems.ORBE_VIE;
    protected static final String PLAQUE_DRACO = InitializationItems.PLAQUE_DRACO;
    protected static final String BOUTON_FUITE = InitializationItems.BOUTON_FUITE;
    protected static final String POTION_MAX = InitializationItems.POTION_MAX;
    protected static final String RAPPEL = InitializationItems.RAPPEL;
    protected static final String PETIT_RAPPEL = InitializationItems.PETIT_RAPPEL;
    protected static final String GRAND_RAPPEL = InitializationItems.GRAND_RAPPEL;
    protected static final String LUMARGILE = InitializationItems.LUMARGILE;
    protected static final String NOEUD_DESTIN = InitializationItems.NOEUD_DESTIN;
    protected static final String BAIE_MANGA = InitializationItems.BAIE_MANGA;
    protected static final String BAIE_LAMPOU = InitializationItems.BAIE_LAMPOU;
    protected static final String CENDRESACREE = InitializationItems.CENDRESACREE;
    protected static final String MAX_ELIXIR = InitializationItems.MAX_ELIXIR;
    protected static final String BAIE_ENIGMA = InitializationItems.BAIE_ENIGMA;
    protected static final String BLACK_BERRY = InitializationItems.BLACK_BERRY;
    protected static final String RASP_BERRY = InitializationItems.RASP_BERRY;
    protected static final String HUILE = InitializationItems.HUILE;
    protected static final String VULNE_ASSURANCE = InitializationItems.VULNE_ASSURANCE;
    protected static final String LICHEN_LUMINEUX = InitializationItems.LICHEN_LUMINEUX;
    protected static final String LUNETTES_FILTRE = InitializationItems.LUNETTES_FILTRE;
    protected static final String GRELOT = InitializationItems.GRELOT;
    protected static final String GROSSERACINE = InitializationItems.GROSSERACINE;
    protected static final String MAGNET = InitializationItems.MAGNET;
    protected static final String PEPITE = InitializationItems.PEPITE;
    protected static final String REPOUSSE = InitializationItems.REPOUSSE;
    protected static final String METRONOME_OBJ = InitializationItems.METRONOME_OBJ;


    protected static final String SOMMEIL_REPOS = InitializationStatus.SOMMEIL_REPOS;
    protected static final String SOMMEIL = InitializationStatus.SOMMEIL;
    protected static final String PARALYSIE_FORTE = InitializationStatus.PARALYSIE_FORTE;
    protected static final String PARALYSIE = InitializationStatus.PARALYSIE;
    protected static final String CRAME_BIS = InitializationStatus.CRAME_BIS;
    protected static final String CRAME = InitializationStatus.CRAME;
    protected static final String BRULURE = InitializationStatus.BRULURE;
    protected static final String FEU_GEL = InitializationStatus.FEU_GEL;
    protected static final String ERE_GEL = InitializationStatus.ERE_GEL;
    protected static final String GEL = InitializationStatus.GEL;
    protected static final String NUIT_NOIRE = InitializationStatus.NUIT_NOIRE;
    protected static final String NUIT_GRISE = InitializationStatus.NUIT_GRISE;
    protected static final String NUIT_BLANCHE_BIS = InitializationStatus.NUIT_BLANCHE_BIS;
    protected static final String NUIT_BLANCHE = InitializationStatus.NUIT_BLANCHE;
    protected static final String CAUCHEMAR = InitializationStatus.CAUCHEMAR;
    protected static final String AMOUR_TRES_MOU = InitializationStatus.AMOUR_TRES_MOU;
    protected static final String AMOUR_MOU = InitializationStatus.AMOUR_MOU;
    protected static final String AMOUR_FOU = InitializationStatus.AMOUR_FOU;
    protected static final String AMOUR = InitializationStatus.AMOUR;
    protected static final String VAMPIGRAINE = InitializationStatus.VAMPIGRAINE;
    protected static final String POISON_GRAVE = InitializationStatus.POISON_GRAVE;
    protected static final String TROUILLE = InitializationStatus.TROUILLE;
    protected static final String PEUR = InitializationStatus.PEUR;
    protected static final String COUP_DE_BEC = InitializationStatus.COUP_DE_BEC;
    protected static final String PRISE_DE_TETE = InitializationStatus.PRISE_DE_TETE;
    protected static final String LONGUE_CONFUSION_SANS_DOMMAGE = InitializationStatus.LONGUE_CONFUSION_SANS_DOMMAGE;
    protected static final String LONGUE_CONFUSION_DOMMAGE = InitializationStatus.LONGUE_CONFUSION_DOMMAGE;
    protected static final String CONFUSION = InitializationStatus.CONFUSION;
    protected static final String POISON_ST = InitializationStatus.POISON_ST;

    protected static final String MOTORWAY_9 = InitializationMap.MOTORWAY_9;
    protected static final String CITY_8 = InitializationMap.CITY_8;
    protected static final String CITY_7 = InitializationMap.CITY_7;
    protected static final String LEAGUE_TR_TWO = InitializationMap.LEAGUE_TR_TWO;
    protected static final String LEAGUE_TR_ONE = InitializationMap.LEAGUE_TR_ONE;
    protected static final String LIGUE = InitializationMap.LIGUE;
    protected static final String DUAL_THREE_TR_TWO = InitializationMap.DUAL_THREE_TR_TWO;
    protected static final String DUAL_THREE_TR_ONE = InitializationMap.DUAL_THREE_TR_ONE;
    protected static final String GYM_TR_TWO = InitializationMap.GYM_TR_TWO;
    protected static final String DUAL_TWO_TR_TWO = InitializationMap.DUAL_TWO_TR_TWO;
    protected static final String DUAL_TWO_TR_ONE = InitializationMap.DUAL_TWO_TR_ONE;
    protected static final String DUAL_ONE_TR_TWO = InitializationMap.DUAL_ONE_TR_TWO;
    protected static final String DUAL_ONE_TR_ONE = InitializationMap.DUAL_ONE_TR_ONE;
    protected static final String GYM_TR_ONE = InitializationMap.GYM_TR_ONE;
    protected static final String CAVE = InitializationMap.CAVE;
    protected static final String R_4 = InitializationMap.R_4;
    protected static final String R_2 = InitializationMap.R_2;
    protected static final String R_1 = InitializationMap.R_1;
    protected static final String CITY_TWO = InitializationMap.CITY_TWO;
    protected static final String CITY = InitializationMap.CITY;

    protected static final String ALLY = InitializationMap.ALLY;
    protected static final String SNOW = InitializationMap.SNOW;
    protected static final String GRASS = InitializationMap.GRASS;
    protected static final String DAFAULT = InitializationMap.DAFAULT;
    protected static final String GERANT = InitializationMap.GERANT;
    protected static final String LINK = InitializationMap.LINK;
    protected static final String TRAINER_TWO = InitializationMap.TRAINER_TWO;
    protected static final String TRAINER_ONE = InitializationMap.TRAINER_ONE;
    protected static final String PERSON = InitializationMap.PERSON;
    protected static final String TRAINER = InitializationMap.TRAINER;

    protected static final String NULL_REF = DataBase.EMPTY_STRING;
    protected static final String TAB = "\t";
    protected static final String MOVE_FORMULA = "move";
    protected static final String CAT_FORMULA = "cat";
    protected static final String STATIS_FORMULA = "statis";
    protected static final String STATUS_FORMULA = "status";
    protected static final String TYPE_FORMULA = "type";

    protected InitializationDataBase() {
    }

    public static DataBase initDataBase() {
        DataBase data_ = coreDataBase();
        data_.sortEndRound();
        initPlaces(data_);
        data_.getMap().setMiniMap(new MiniMapCoordsList());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(2);
        //data_.getMap().setSpritesGirlBoy(new Map<EnvironmentType,String>());

        //InitializationMap.initPlaces(data_);
        InitializationMap.initBegin(data_);

        InitializationMap.initBlockFirstRoad(data_);
        InitializationMap.initTrainersFirstRoad(data_);
        InitializationMap.initFirstRoadAreas(data_);
        InitializationMap.initOtherCharactersFirstRoad(data_);

        InitializationMap.initBlockFirstCity(data_);
        InitializationMap.initBuildingsFirstCity(data_);
        InitializationMap.initTrainersFirstCity(data_);
        InitializationMap.initPokemonCenterFirstCity(data_);

        InitializationMap.initBlockSecondRoad(data_);
        InitializationMap.initTrainersSecondRoad(data_);
        InitializationMap.initSecondRoadAreas(data_);
        InitializationMap.initItemsSecondRoad(data_);

        InitializationMap.initBlockSecondCity(data_);
        InitializationMap.initBuildingsSecondCity(data_);
        InitializationMap.initTrainersSecondCity(data_);
        InitializationMap.initPokemonCenterSecondCity(data_);

        InitializationMap.initBlockThirdRoad(data_);

        InitializationMap.initBlockCave(data_);
        InitializationMap.initTrainersCave(data_);
        InitializationMap.initCaveAreas(data_);

        //InitializationMap.initBuildings(data_);
        //InitializationMap.initRoadAreas(data_);


        //data_.initializeWildPokemon();
        //InitializationMap.initObjects(data_);
        //InitializationMap.joinPlaces(data_);

        InitializationMap.initLeague(data_);
        InitializationMap.initBlockLeague(data_);
        InitializationMap.initLeagueTrainers(data_);

        InitializationMap.initBlockThirdCity(data_);
        InitializationMap.initBuildingsThirdCity(data_);

        InitializationMap.initBlockFourthCity(data_);
        InitializationMap.initBuildingsFourthCity(data_);

        InitializationMap.initBlockFourthRoad(data_);
        InitializationMap.initTrainersFourthRoad(data_);

        InitializationMap.joinPlaces(data_);

        InitializationMap.initMiniMap(data_);

        InitializationMap.initImages(data_);
        data_.getMap().initializeLinks();
        data_.initializeWildPokemon();
        data_.getMap().initInteractiveElements();
        data_.completeVariables();
        initConstants(data_);
        data_.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        initRandomLaws(data_);
        initExpPoints(data_);
        initTmHm(data_);
        data_.initTypesByTable();
        initTranslations(data_);
        //OK data, no homonyms
        PerCent ai_ = new TstsPerCentImpl();
        data_.validateCore(ai_);
        data_.validateConstants();
        data_.setCheckTranslation(false);
        CheckNumericStringsFight.validateNumericBooleanStrings(data_);
        data_.getMap().validate(data_);
        data_.validateImages();
        data_.setupPseudoImages();
        data_.validateTranslations();
        return data_;
    }

    private static void initPlaces(DataBase _data) {
        _data.getMap().setPlaces(new CustList<Place>());
    }

    public static DataBase initDb() {
        DataBase data_ = coreDataBase();
        data_.sortEndRound();
        initPlaces(data_);
        data_.getMap().setMiniMap(new MiniMapCoordsList());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(2);
        //data_.getMap().setSpritesGirlBoy(new Map<EnvironmentType,String>());

        //InitializationMap.initPlaces(data_);
        InitializationMap.initBegin(data_);

        InitializationMap.initBlockFirstRoad(data_);
        InitializationMap.initTrainersFirstRoad(data_);
        InitializationMap.initFirstRoadAreas(data_);
        InitializationMap.initOtherCharactersFirstRoad(data_);

        InitializationMap.initBlockFirstCity(data_);
        InitializationMap.initBuildingsFirstCity(data_);
        InitializationMap.initTrainersFirstCity(data_);
        InitializationMap.initPokemonCenterFirstCity(data_);

        InitializationMap.initBlockSecondRoad(data_);
        InitializationMap.initTrainersSecondRoad(data_);
        InitializationMap.initSecondRoadAreas(data_);
        InitializationMap.initItemsSecondRoad(data_);

        InitializationMap.initBlockSecondCity(data_);
        InitializationMap.initBuildingsSecondCity(data_);
        InitializationMap.initTrainersSecondCity(data_);
        InitializationMap.initPokemonCenterSecondCity(data_);

        InitializationMap.initBlockThirdRoad(data_);

        InitializationMap.initBlockCave(data_);
        InitializationMap.initTrainersCave(data_);
        InitializationMap.initCaveAreas(data_);

        //InitializationMap.initBuildings(data_);
        //InitializationMap.initRoadAreas(data_);


        //data_.initializeWildPokemon();
        //InitializationMap.initObjects(data_);
        //InitializationMap.joinPlaces(data_);

        InitializationMap.initLeague(data_);
        InitializationMap.initBlockLeague(data_);
        InitializationMap.initLeagueTrainers(data_);

        InitializationMap.initBlockThirdCity(data_);
        InitializationMap.initBuildingsThirdCity(data_);

        InitializationMap.initBlockFourthCity(data_);
        InitializationMap.initBuildingsFourthCity(data_);

        InitializationMap.initBlockFourthRoad(data_);
        InitializationMap.initTrainersFourthRoad(data_);

        InitializationMap.joinPlaces(data_);

        InitializationMap.initMiniMap(data_);

        InitializationMap.initImages(data_);
        data_.getMap().initializeLinks();
        data_.initializeWildPokemon();
        data_.getMap().initInteractiveElements();
        data_.getMap().initializeTree();
        data_.getMap().initializeAccessibility();
        data_.completeVariables();
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        initTmHm(data_);
        data_.initTypesByTable();
        initTranslations(data_);
        data_.initFamilies();
        return data_;
    }
    protected static DataBase coreDataBase() {
        DataBase data_ = new DataBase(new DefaultGenerator());
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        InitializationPokedex.initAllPokemon(data_);
        InitializationPokedex.initAllSecondPokemons(data_);
        InitializationAbilities.initAllAbilities(data_);
        InitializationItems.initAllItems(data_);
        InitializationStatus.initAllStatus(data_);
        InitializationMoves.initAllMoves(data_);
        initTableTypes(data_);
        return data_;
    }

    protected static void initTableTypes(DataBase _data) {
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,FEE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,ACIER),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEE,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,TENEBRE),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,PSY),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,ROCHE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,INSECTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,ROCHE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,FEU),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,NORMAL),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,PLANTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,EAU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,POISON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,POISON),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,VOL),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,INSECTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,ACIER),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,INSECTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,POISON),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,GLACE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,VOL),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,COMBAT),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,POISON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,ROCHE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,TENEBRE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,DRAGON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,GLACE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,PSY),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,ROCHE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,ROCHE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,TENEBRE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,SOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,PSY),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,SPECTRE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,FEU),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,DRAGON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,SOL),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,VOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,PLANTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,SPECTRE),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,DRAGON),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,FEU),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,ACIER),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,INSECTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,FEU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,VOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,POISON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,FEU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,PLANTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,EAU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,GLACE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,ACIER),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,ROCHE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,EAU),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,ROCHE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,DRAGON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,DRAGON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,SOL),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,PSY),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,NORMAL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,SPECTRE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,TENEBRE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,FEU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,ROCHE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,EAU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,DRAGON),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,PLANTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,VOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,ACIER),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,SOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,SPECTRE),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,SPECTRE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,VOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,NORMAL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,TENEBRE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,SPECTRE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,ACIER),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,EAU),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,INSECTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,SOL),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,PLANTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,EAU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,PLANTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,COMBAT),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,FEU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,POISON),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,PLANTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(GLACE,FEU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,GLACE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,VOL),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,ROCHE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,PSY),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,INSECTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(COMBAT,ACIER),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(POISON,ACIER),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,PSY),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,PLANTE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(PSY,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,SPECTRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SPECTRE,ROCHE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,VOL),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,GLACE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,TENEBRE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(EAU,COMBAT),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(DRAGON,EAU),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ACIER,PLANTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,INSECTE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ROCHE,GLACE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,PLANTE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,SOL),new Rate("0"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,FEU),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(TENEBRE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(SOL,ELECTRIQUE),new Rate("2"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(ELECTRIQUE,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().addEntry(new TypesDuo(INSECTE,SOL),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(VOL,DRAGON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(PLANTE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(NORMAL,POISON),new Rate("1"));
        _data.getTableTypes().addEntry(new TypesDuo(FEU,PSY),new Rate("1"));
    }

    protected static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest("DEF_MAX_ATT", new Rate(4));
        _data.addConstNumTest(DataBase.DEF_PKEQ, new Rate(6));
        _data.addConstNumTest(DataBase.ARGENT, new Rate(3000));
        _data.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(1));
        _data.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(100));
        _data.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(110));
        _data.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(170));
        _data.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        _data.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(10));
        _data.addConstNumTest(DataBase.PP_MAX, new Rate(80));
        _data.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        _data.addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        _data.addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        _data.addConstNumTest(DataBase.MIN_HP, new Rate(1));
        _data.addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        _data.addConstNumTest(DataBase.MAX_STEPS, new Rate("1024"));
        _data.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("256"));
        _data.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("0"));
        initDefaultConsts(POKE_BALL,
                "caracdroiteferme(div(FOE_PK_MAX_HP,FOE_PK_REMOTE_HP),2)",
                "caracdroiteferme(div(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)",
                "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)",
                "puis(2,VAR__BOOST-4)",
                "div((5+VAR__LANCEUR_NIVEAU)*VAR__ATTACK*VAR__POWER,(125*VAR__DEFENSE))",
                LUTTE,
                "METAMORPH", _data);
//        _data.addConstNotNumTest("BALL_DEF", "POKE_BALL");
//        _data.addConstNotNumTest(DataBase.RATE_CATCHING, "caracdroiteferme(div(FOE_PK_MAX_HP,FOE_PK_REMOTE_HP),2)");
//        _data.addConstNotNumTest(DataBase.RATE_FLEEING, "caracdroiteferme(div(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
//        //_data.addConstNotNumTest(DataBase.RATE_BOOST, "2:max(2-VAR__BOOST,1)*caracgaucheouvert(VAR__BOOST,0)+(2+VAR__BOOST):2*caracdroiteferme(VAR__BOOST,0)");
////        _data.addConstNotNumTest(DataBase.RATE_BOOST, "2*caracgaucheouvert(VAR__BOOST,0):max(2-VAR__BOOST,1)+(2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0):2");
//        _data.addConstNotNumTest(DataBase.RATE_BOOST, "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)");
//        _data.addConstNotNumTest(DataBase.RATE_BOOST_CRITICAL_HIT, "puis(2,VAR__BOOST-4)");
////        _data.addConstNotNumTest(DataBase.DAMAGE_FORMULA, "(5+VAR__LANCEUR_NIVEAU):125*VAR__ATTACK*VAR__POWER:VAR__DEFENSE");
//        _data.addConstNotNumTest(DataBase.DAMAGE_FORMULA, "div((5+VAR__LANCEUR_NIVEAU)*VAR__ATTACK*VAR__POWER,(125*VAR__DEFENSE))");
//        _data.addConstNotNumTest(DataBase.DEF_MOVE, "LUTTE");
//        _data.addConstNotNumTest(DataBase.DEFAULT_EGG_GROUP, "METAMORPH");
    }

    protected static void initExpPoints(DataBase _data) {
        _data.getExpGrowth().addEntry(ExpType.E,"2*VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().addEntry(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().addEntry(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().addEntry(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().addEntry(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().addEntry(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }

    protected static void initTmHm(DataBase _data) {
        _data.getHm().addEntry((short) 1, ECLAIR);
        _data.getTm().addEntry((short) 2, TONNERRE);
        _data.getTm().addEntry((short) 3, CHARGE);
        _data.getTm().addEntry((short) 4, VIVE_ATTAQUE);
        _data.getTm().addEntry((short) 5, JACKPOT);
        _data.getTmPrice().addEntry((short) 2, new LgInt(4000));
        _data.getTmPrice().addEntry((short) 3, new LgInt(2000));
        _data.getTmPrice().addEntry((short) 4, new LgInt(3000));
    }

    protected static void initTranslations(DataBase _data) {
        AbsMap<SelectedBoolean,String> bools_;
        bools_ = new IdMap<SelectedBoolean,String>();
        bools_.addEntry(SelectedBoolean.YES, SelectedBoolean.YES.name());
        bools_.addEntry(SelectedBoolean.NO, SelectedBoolean.NO.name());
        bools_.addEntry(SelectedBoolean.YES_AND_NO, SelectedBoolean.YES_AND_NO.name());
        _data.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        AbsMap<DifficultyWinPointsFight,String> diffsWin_;
        diffsWin_ = new IdMap<DifficultyWinPointsFight,String>();
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_FACILE, DifficultyWinPointsFight.TRES_FACILE.name());
        diffsWin_.addEntry(DifficultyWinPointsFight.FACILE, DifficultyWinPointsFight.FACILE.name());
        diffsWin_.addEntry(DifficultyWinPointsFight.DIFFICILE, DifficultyWinPointsFight.DIFFICILE.name());
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, DifficultyWinPointsFight.TRES_DIFFICILE.name());
        _data.getTranslatedDiffWinPts().addEntry(LANGUAGE, diffsWin_);
        AbsMap<DifficultyModelLaw,String> diffsLaw_;
        diffsLaw_ = new IdMap<DifficultyModelLaw,String>();
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MIN, DifficultyModelLaw.CONSTANT_MIN.name());
        diffsLaw_.addEntry(DifficultyModelLaw.CROISSANT, DifficultyModelLaw.CROISSANT.name());
        diffsLaw_.addEntry(DifficultyModelLaw.UNIFORME, DifficultyModelLaw.UNIFORME.name());
        diffsLaw_.addEntry(DifficultyModelLaw.DECROISSANT, DifficultyModelLaw.DECROISSANT.name());
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MAX, DifficultyModelLaw.CONSTANT_MAX.name());
        _data.getTranslatedDiffModelLaw().addEntry(LANGUAGE, diffsLaw_);
        AbsMap<EnvironmentType,String> envs_;
        envs_ = new IdMap<EnvironmentType,String>();
        envs_.addEntry(EnvironmentType.NOTHING, EnvironmentType.NOTHING.name());
        envs_.addEntry(EnvironmentType.ROAD, EnvironmentType.ROAD.name());
        envs_.addEntry(EnvironmentType.DESERT, EnvironmentType.DESERT.name());
        envs_.addEntry(EnvironmentType.ROCK, EnvironmentType.ROCK.name());
        envs_.addEntry(EnvironmentType.BUILDING, EnvironmentType.BUILDING.name());
        envs_.addEntry(EnvironmentType.WATER, EnvironmentType.WATER.name());
        envs_.addEntry(EnvironmentType.GRASS, EnvironmentType.GRASS.name());
        envs_.addEntry(EnvironmentType.SNOW, EnvironmentType.SNOW.name());
        envs_.addEntry(EnvironmentType.ICE, EnvironmentType.ICE.name());
        _data.getTranslatedEnvironment().addEntry(LANGUAGE, envs_);
        AbsMap<Gender,String> genders_;
        genders_ = new IdMap<Gender,String>();
        genders_.addEntry(Gender.FEMALE, Gender.FEMALE.name());
        genders_.addEntry(Gender.NO_GENDER, Gender.NO_GENDER.name());
        genders_.addEntry(Gender.MALE, Gender.MALE.name());
        _data.getTranslatedGenders().addEntry(LANGUAGE, genders_);
        AbsMap<Statistic,String> statistics_;
        statistics_ = new IdMap<Statistic,String>();
        statistics_.addEntry(Statistic.ATTACK, Statistic.ATTACK.name());
        statistics_.addEntry(Statistic.DEFENSE, Statistic.DEFENSE.name());
        statistics_.addEntry(Statistic.SPECIAL_ATTACK, Statistic.SPECIAL_ATTACK.name());
        statistics_.addEntry(Statistic.SPECIAL_DEFENSE, Statistic.SPECIAL_DEFENSE.name());
        statistics_.addEntry(Statistic.SPEED, Statistic.SPEED.name());
        statistics_.addEntry(Statistic.CRITICAL_HIT, Statistic.CRITICAL_HIT.name());
        statistics_.addEntry(Statistic.ACCURACY, Statistic.ACCURACY.name());
        statistics_.addEntry(Statistic.EVASINESS, Statistic.EVASINESS.name());
        statistics_.addEntry(Statistic.PV_RESTANTS, Statistic.PV_RESTANTS.name());
        statistics_.addEntry(Statistic.HP, Statistic.HP.name());
        _data.getTranslatedStatistics().addEntry(LANGUAGE, statistics_);
        AbsMap<TargetChoice,String> targets_;
        targets_ = new IdMap<TargetChoice,String>();
        targets_.addEntry(TargetChoice.ADJ_ADV, TargetChoice.ADJ_ADV.name());
        targets_.addEntry(TargetChoice.ADJ_MULT, TargetChoice.ADJ_MULT.name());
        targets_.addEntry(TargetChoice.ADJ_UNIQ, TargetChoice.ADJ_UNIQ.name());
        targets_.addEntry(TargetChoice.ALLIE, TargetChoice.ALLIE.name());
        targets_.addEntry(TargetChoice.ALLIES, TargetChoice.ALLIES.name());
        targets_.addEntry(TargetChoice.ANY_FOE, TargetChoice.ANY_FOE.name());
        targets_.addEntry(TargetChoice.AUTRE_UNIQ, TargetChoice.AUTRE_UNIQ.name());
        targets_.addEntry(TargetChoice.GLOBALE, TargetChoice.GLOBALE.name());
        targets_.addEntry(TargetChoice.LANCEUR, TargetChoice.LANCEUR.name());
        targets_.addEntry(TargetChoice.PSEUDO_GLOBALE, TargetChoice.PSEUDO_GLOBALE.name());
        targets_.addEntry(TargetChoice.TOUS_ADV, TargetChoice.TOUS_ADV.name());
        targets_.addEntry(TargetChoice.UNIQUE_IMPORTE, TargetChoice.UNIQUE_IMPORTE.name());
        targets_.addEntry(TargetChoice.NOTHING, TargetChoice.NOTHING.name());
        _data.getTranslatedTargets().addEntry(LANGUAGE, targets_);
        StringMap<String> words_ = DataBase.basicTranslation(_data.getPokedex().getKeys());
        _data.getTranslatedPokemon().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getMoves().getKeys());
        _data.getTranslatedMoves().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getItems().getKeys());
        _data.getTranslatedItems().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAbilities().getKeys());
        _data.getTranslatedAbilities().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getStatus().getKeys());
        _data.getTranslatedStatus().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslationItemsType(_data);
        _data.getTranslatedClassesDescriptions().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getTypes());
        _data.getTranslatedTypes().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAllCategories());
        _data.getTranslatedCategories().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(EvolvedMathFactory.getFunctions());
        _data.getTranslatedFctMath().addEntry(LANGUAGE, words_);
        StringMap<String> litteral_ = new StringMap<String>();
        litteral_.addEntry("CIBLE_EFFET",StringUtil.concat(MOVE_FORMULA,TAB,"targ_eff__{0}",TAB,"The target is under the effect of the move {0}"));
        litteral_.addEntry("GENRES_EGAUX", StringUtil.concat(MOVE_FORMULA,TAB,"gr_eg",TAB,"The genders equal to themselves."));
        litteral_.addEntry("PK_SAUVAGE_GENRE", StringUtil.concat("item",TAB,"wild_pk_gr",TAB,"Gender of the wild pokemon"));
        litteral_.addEntry("LANCEUR_GENRE", StringUtil.concat(MOVE_FORMULA,TAB,"user_gr",TAB,"Genre of the user"));
        litteral_.addEntry("LANCEUR_PV_MAX", StringUtil.concat(MOVE_FORMULA,TAB,"usr_max_hp",TAB,"Full health points of the user"));
        litteral_.addEntry("EXISTE_GENRE_ASSEXUE", StringUtil.concat(MOVE_FORMULA,TAB,"exist_no_gr",TAB,"It exists a no-sexual gender."));
        litteral_.addEntry("LANCEUR_EFFET", StringUtil.concat(MOVE_FORMULA,TAB,"usr_eff__{0}",TAB,"The user is under the effect of the move {0}"));
        litteral_.addEntry("CIBLE_STATUTS", StringUtil.concat(MOVE_FORMULA,TAB,"targ_status",TAB,"status of the target"));
        litteral_.addEntry("CIBLE_TAILLE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_height",TAB,"Height of the target"));
        litteral_.addEntry("CIBLE_OBJET", StringUtil.concat(MOVE_FORMULA,TAB,"targ_it",TAB,"Item of the target"));
        litteral_.addEntry("IMMU_TYPE_ATT_COMBATTANT_ENTRANT", StringUtil.concat(TYPE_FORMULA,TAB,"immu_move_type_fighter__{0}",TAB,"The sent fighter is protected against the moves of type {0}"));
        litteral_.addEntry("LANCEUR_DEGATS_RECUS_TOTAL_TOUR", StringUtil.concat(MOVE_FORMULA,TAB,"usr_suffered_damg_rd",TAB,"Suffered damage against the user while the round"));
        litteral_.addEntry("DEJA_CAPTURE", StringUtil.concat("item",TAB,"already_caught",TAB,"Already caught pokemon"));
        litteral_.addEntry("CIBLE_TYPES", StringUtil.concat(MOVE_FORMULA,TAB,"targ_t",TAB,"types of the targets"));
        litteral_.addEntry("COMBATTANT_ENTRANT_TYPES", StringUtil.concat(MOVE_FORMULA,TAB,"sent_fighter_type",TAB,"Types of the sent fighter"));
        litteral_.addEntry("LANCEUR_STATUTS", StringUtil.concat(MOVE_FORMULA,TAB,"usr_status",TAB,"status of the user"));
        litteral_.addEntry("PAS_PP_ATTAQUE_CIBLE", StringUtil.concat(MOVE_FORMULA,TAB,"no_pp_target",TAB,"The target has no power point for the move that the target owns"));
        litteral_.addEntry("SOMME_BOOST_POS_CIBLE", StringUtil.concat(MOVE_FORMULA,TAB,"sum_boost_pos_tar",TAB,"Sum of the positive boosts of the target"));
        litteral_.addEntry("ATTAQUE_NOM", StringUtil.concat(MOVE_FORMULA,TAB,"move_name",TAB,"Name of the move"));
        litteral_.addEntry("LANCEUR_BOOST", StringUtil.concat(STATIS_FORMULA,TAB,"usr_boost__{0}",TAB,"{0} of terms of boost of the user"));
        litteral_.addEntry("ATTACK", StringUtil.concat(MOVE_FORMULA,TAB,"att",TAB,"Value of the move of the user"));
        litteral_.addEntry("CIBLE_PV_RESTANTS", StringUtil.concat(MOVE_FORMULA,TAB,"targ_left_hp",TAB,"Remaining health points of the target"));
        litteral_.addEntry("CIBLE_POSSEDE_STATUT_RELATION", StringUtil.concat(STATUS_FORMULA,TAB,"target_pseudo_st__{0}",TAB,"The target is victim of the pseudo status {0}"));
        litteral_.addEntry("ATTAQUE_TYPES", StringUtil.concat(MOVE_FORMULA,TAB,"move_types",TAB,"Types of the move"));
        litteral_.addEntry("FIGHTER_OBJET", StringUtil.concat("item",TAB,"fighter_it",TAB,"Item of the owner"));
        litteral_.addEntry("LANCEUR_CLONE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_clone",TAB,"PV of the clone of the user"));
        litteral_.addEntry("POWER", StringUtil.concat(MOVE_FORMULA,TAB,"pow",TAB,"Value of the power of the move of the user"));
        litteral_.addEntry("PUISSANCE_BASE", StringUtil.concat("item",TAB,"power_base",TAB,"Power of base of the move of the owner"));
        litteral_.addEntry("MASSE_MOYENNE_PK", StringUtil.concat("item",TAB,"avg_pk_weight",TAB,"Average weight of the pokemon"));
        litteral_.addEntry("LANCEUR_ATTAQUES_TYPES", StringUtil.concat(MOVE_FORMULA,TAB,"usr_moves_types",TAB,"Types of the moves of the user"));
        litteral_.addEntry("PK_UT_NIVEAU", StringUtil.concat("item",TAB,"pk_player_lev",TAB,"Level of the pokemon of the player"));
        litteral_.addEntry("DEFENSE", StringUtil.concat(MOVE_FORMULA,TAB,"def",TAB,"Value of the defence of the user"));
        litteral_.addEntry("PK_SAUVAGE_TYPES_BASE", StringUtil.concat("item",TAB,"wild_pk_types",TAB,"Types de base of the wild pokemon"));
        litteral_.addEntry("LANCEUR_CAPACITE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_ab",TAB,"Current ability of the user"));
        litteral_.addEntry("FIGHTER_NOM", StringUtil.concat("item",TAB,"fighter_nom",TAB,"Name of the owner"));
        litteral_.addEntry("NB_UTILISATION_CONSECUTIF", StringUtil.concat("item",TAB,"nb_uses_cons",TAB,"Number of consecutive uses of the move."));
        litteral_.addEntry("FIGHTER_DER_JOUE", StringUtil.concat("item",TAB,"fighter_last_acted",TAB,"The owner has played the last one"));
        litteral_.addEntry("TEMPS_TOUR", StringUtil.concat("item",TAB,"tps_rd",TAB,"Time of round"));
        litteral_.addEntry("LANCEUR_ATTAQUES", StringUtil.concat(MOVE_FORMULA,TAB,"usr_att",TAB,"Moves of the user"));
        litteral_.addEntry("LANCEUR_BONHEUR", StringUtil.concat(MOVE_FORMULA,TAB,"usr_hap",TAB,"Happiness of the user"));
        litteral_.addEntry("PK_SAUVAGE_PIERRES_EVOS", StringUtil.concat("item",TAB,"wild_pk_ev",TAB,"Evolution stones of the wild pokemon"));
        litteral_.addEntry("CIBLE_BOOST", StringUtil.concat(STATIS_FORMULA,TAB,"targ_boost__{0}",TAB,"{0} in terms of boost of the target"));
        litteral_.addEntry("PAS_PARTENAIRE_ARRIERE", StringUtil.concat(MOVE_FORMULA,TAB,"no_back_part",TAB,"No back partner"));
        litteral_.addEntry("LANCEUR_DISPARAIT", StringUtil.concat(MOVE_FORMULA,TAB,"usr_disap",TAB,"The user disappears while the round"));
        litteral_.addEntry("LANCEUR_JOUE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_acted",TAB,"The user has acted"));
        litteral_.addEntry("LANCEUR_DEGATS_RECUS_TOTAL", StringUtil.concat(MOVE_FORMULA,TAB,"usr_amt_suff_damg",TAB,"Full suffered damage against the user"));
        litteral_.addEntry("LANCEUR_NB_UTILISATION", StringUtil.concat(MOVE_FORMULA,TAB,"usr_nb_uses__{0}",TAB,"Number of uses of the move {0} of the user"));
        litteral_.addEntry("CIBLE_STATIS", StringUtil.concat(STATIS_FORMULA,TAB,"targ_statis__{0}",TAB,"{0} in terms of base of statistic of the target"));
        litteral_.addEntry("NB_UTILI_ATT_EQ_TOUR", StringUtil.concat(MOVE_FORMULA,TAB,"nb_uses_team_move_rd__{0}",TAB,"Number of uses of the move {0} by the team"));
        litteral_.addEntry("NIVEAU", StringUtil.concat("level",TAB,"l",TAB,"The level of the Pokemon"));
        litteral_.addEntry("EV", StringUtil.concat("ev",TAB,"ev",TAB,"The ev of a statistic of the pokemon"));
        litteral_.addEntry("IV", StringUtil.concat("iv",TAB,"iv",TAB,"The iv of a statistic of the pokemon"));
        litteral_.addEntry("BASE", StringUtil.concat("base",TAB,"base",TAB,"The base value of a statistic of the pokemon"));
        litteral_.addEntry("LANCEUR_TAILLE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_height",TAB,"height of the user"));
        litteral_.addEntry("LIEU_COMBAT", StringUtil.concat("item",TAB,"fight_env",TAB,"Fight environment"));
        litteral_.addEntry("CIBLE_GENRE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_gr",TAB,"Genre of the target"));
        litteral_.addEntry("CIBLE_ATTAQUE_CHOISIE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_chosen_move",TAB,"Chosen move of the target"));
        litteral_.addEntry("CIBLE_NB_UTILISATION", StringUtil.concat(MOVE_FORMULA,TAB,"targ_nb_uses__{0}",TAB,"Number of uses of the move {0} of the target"));
        litteral_.addEntry("IMMU_TYPE_ATT_CIBLE", StringUtil.concat(TYPE_FORMULA,TAB,"immu_move_type_targ__{0}",TAB,"The target is protected against the moves of type {0}"));
        litteral_.addEntry("CLIMATS", StringUtil.concat(MOVE_FORMULA,TAB,"weathers",TAB,"Enabled weathers"));
        litteral_.addEntry("CIBLE_CLONE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_clone",TAB,"PV of the clone of the target"));
        litteral_.addEntry("CIBLE_ATTAQUES", StringUtil.concat(MOVE_FORMULA,TAB,"targ_moves",TAB,"Moves of the target"));
        litteral_.addEntry("CIBLE_PV_MAX", StringUtil.concat(MOVE_FORMULA,TAB,"targ_max_hp",TAB,"Full health points of the target"));
        litteral_.addEntry("LANCEUR_TYPES", StringUtil.concat(MOVE_FORMULA,TAB,"usr_t",TAB,"types of the user"));
        litteral_.addEntry("FIGHTER_TYPES", StringUtil.concat(STATUS_FORMULA,TAB,"fighter_types",TAB,"Types of the fighter"));
        litteral_.addEntry("PAS_UTILIS_ATTAQUE_CIBLE", StringUtil.concat(MOVE_FORMULA,TAB,"no_use_tar",TAB,"No used move by the target"));
        litteral_.addEntry("ATTAQUE_CATEGORIE", StringUtil.concat(MOVE_FORMULA,TAB,"move_cat",TAB,"Category of the move of the owner"));
        litteral_.addEntry("NB_KO_EQUIPE_LANCEUR", StringUtil.concat(MOVE_FORMULA,TAB,"nb_ko_user",TAB,"Number of ko pokemon of the team of the user"));
        litteral_.addEntry("CIBLE_CAPACITE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_ab",TAB,"Current ability of the target"));
        litteral_.addEntry("CIBLE_DEGATS_RECUS_TOTAL_TOUR", StringUtil.concat(MOVE_FORMULA,TAB,"targ_suffered_damg_rd",TAB,"Suffered damage against the target while the round"));
        litteral_.addEntry("FIGHTER_PP", StringUtil.concat(MOVE_FORMULA,TAB,"usr_pp__{0}",TAB,"Remaining power points of the move {0} of the user"));
        litteral_.addEntry("LANCEUR_PP", StringUtil.concat(MOVE_FORMULA,TAB,"usr_pp__{0}",TAB,"Remaining power points of the move {0} of the user"));
        litteral_.addEntry("CIBLE_PP", StringUtil.concat(MOVE_FORMULA,TAB,"usr_pp__{0}",TAB,"Remaining power points of the move {0} of the user"));
        litteral_.addEntry("PAS_TOUR_TERRAIN", StringUtil.concat(MOVE_FORMULA,TAB,"no_rd",TAB,"Has just been sent on the ground."));
        litteral_.addEntry("FIGHTER_CLONE", StringUtil.concat(STATUS_FORMULA,TAB,"fighter_clone",TAB,"PV of the clone of the fighter"));
        litteral_.addEntry("CIBLE_NIVEAU", StringUtil.concat(MOVE_FORMULA,TAB,"targ_lev",TAB,"Level of the target"));
        litteral_.addEntry("FIGHTER_STATUTS", StringUtil.concat("item",TAB,"fighter_st",TAB,"Status of the owner"));
        litteral_.addEntry("PK_SAUVAGE_MASSE", StringUtil.concat("item",TAB,"wild_pk_wt",TAB,"Weight of wild pokemon"));
        litteral_.addEntry("TYPES_ATTAQUES_RES_VIDE", StringUtil.concat(MOVE_FORMULA,TAB,"res_types_move_empty",TAB,"No resisting type against the last suffered move."));
        litteral_.addEntry("LANCEUR_STATIS", StringUtil.concat(STATIS_FORMULA,TAB,"usr_statis__{0}",TAB,"{0} in terms of base of statistic of the user"));
        litteral_.addEntry("CIBLE_DISPARAIT", StringUtil.concat(MOVE_FORMULA,TAB,"targ_disap",TAB,"The target disappears while the round"));
        litteral_.addEntry("LANCEUR_DEGATS_RECUS_TOUR", StringUtil.concat(CAT_FORMULA,TAB,"usr_suffered_damg_rd__{0}",TAB,"Suffered damage against the user by moves of category {0}."));
        litteral_.addEntry("LANCEUR_MASSE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_weight",TAB,"Weight of the user"));
        litteral_.addEntry("COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT", StringUtil.concat(TYPE_FORMULA,TAB,"eff_base_sent_fighter__{0}",TAB,"Rate of efficiency of the type {0} against the types of the sent fighter"));
        litteral_.addEntry("COEFF_EFF_BASE_TYPES_FIGHTER", StringUtil.concat(TYPE_FORMULA,TAB,"eff_base_sent_fighter__{0}",TAB,"Rate of efficiency of the type {0} against the types of the sent fighter"));
        litteral_.addEntry("COEFF_EFF_BASE_TYPES_CIBLE", StringUtil.concat(TYPE_FORMULA,TAB,"eff_base_sent_fighter__{0}",TAB,"Rate of efficiency of the type {0} against the types of the sent fighter"));
        litteral_.addEntry("COEFF_EFF_BASE_TYPES_LANCEUR", StringUtil.concat(TYPE_FORMULA,TAB,"eff_base_sent_fighter__{0}",TAB,"Rate of efficiency of the type {0} against the types of the sent fighter"));
        litteral_.addEntry("LANCEUR_NOM", StringUtil.concat(MOVE_FORMULA,TAB,"user_name",TAB,"Name of the user"));
        litteral_.addEntry("PK_UT_GENRE", StringUtil.concat("item",TAB,"pk_player_gr",TAB,"Gender of the pokemon of the player"));
        litteral_.addEntry("FIGHTER_NB_UTILISATION", StringUtil.concat(MOVE_FORMULA,TAB,"cbt_nb_uses__{0}",TAB,"Number of uses of the move {0} of the fighter"));
        litteral_.addEntry("EQUIPE_NB_UTILISATION", StringUtil.concat(MOVE_FORMULA,TAB,"cbt_nb_uses__{0}",TAB,"Number of uses of the move {0} of the fighter"));
        litteral_.addEntry("NB_TOUR", StringUtil.concat(MOVE_FORMULA,TAB,"nb_rd__{0}",TAB,"number of round of the move {0}"));
        litteral_.addEntry("PAS_ATTAQUE_INVOC", StringUtil.concat(MOVE_FORMULA,TAB,"no_invok_move",TAB,"No invokable move"));
        litteral_.addEntry("CIBLE_JOUE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_acted",TAB,"The target has acted"));
        litteral_.addEntry("CIBLE_MASSE", StringUtil.concat(MOVE_FORMULA,TAB,"targ_weight",TAB,"Weight of the target"));
        litteral_.addEntry("EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION", StringUtil.concat(MOVE_FORMULA,TAB,"sent_foe_team_nb_uses__{0}",TAB,"Number of uses of the move {0} by the members of the foe team of the sent fighter."));
        litteral_.addEntry("LANCEUR_OBJET", StringUtil.concat(MOVE_FORMULA,TAB,"usr_obj",TAB,"Item of the user"));
        litteral_.addEntry("LANCEUR_ATTAQUE_CHOISIE", StringUtil.concat(MOVE_FORMULA,TAB,"usr_chosen_move",TAB,"Chosen move of the user"));
        litteral_.addEntry("PK_SAUVAGE_VITESSE", StringUtil.concat("item",TAB,"wild_pk_speed",TAB,"Base speed of the wild pokemon"));
        litteral_.addEntry("PAS_ATTAQUES_COPIABLES", StringUtil.concat(MOVE_FORMULA,TAB,"no_copy_moves",TAB,"No move can be copied"));
        litteral_.addEntry("NB_COMBATTANTS_TERRAIN", StringUtil.concat(MOVE_FORMULA,TAB,"nb_fighters",TAB,"Number of fighters on the ground"));
        litteral_.addEntry("PK_SAUVAGE_NIVEAU", StringUtil.concat("item",TAB,"wild_pk_lev",TAB,"Level of the wild pokemon"));
        litteral_.addEntry("LANCEUR_PV_RESTANTS", StringUtil.concat(MOVE_FORMULA,TAB,"usr_left_hp",TAB,"Remaining points de vie of the user"));
        litteral_.addEntry("LANCEUR_NIVEAU", StringUtil.concat(MOVE_FORMULA,TAB,"usr_lev",TAB,"Level of the user"));
        litteral_.addEntry("COEFF_EFF", StringUtil.concat("item",TAB,"rate_eff",TAB,"Rate of efficiency of the move of the owner"));
        litteral_.addEntry("LANCEUR_POSSEDE_STATUT_RELATION", StringUtil.concat(STATUS_FORMULA,TAB,"target_pseudo_st__{0}",TAB,"The user suffers of the pseudo status {0}"));
        litteral_.addEntry("NB_TOUR_GLOBAL", StringUtil.concat(MOVE_FORMULA,TAB,"nb_gl_move__{0}",TAB,"Number of tour of the move {0}"));
        litteral_.addEntry("SOMME_BOOST_POS_LANCEUR", StringUtil.concat(MOVE_FORMULA,TAB,"sum_boost_pos_user",TAB,"Sum of the positive boosts of the user"));
        litteral_.addEntry("PAS_PARTENAIRE_TERRAIN", StringUtil.concat(MOVE_FORMULA,TAB,"no_part",TAB,"No partner on the ground"));
        litteral_.addEntry("COMBATTANT_ENTRANT_CLONE", StringUtil.concat(MOVE_FORMULA,TAB,"sent_fighter_clone",TAB,"Remaining health points of the clone of the sent fighter"));
        litteral_.addEntry("AUCUN_BOOST_POSSIBLE", StringUtil.concat(MOVE_FORMULA,TAB,"no_boost",TAB,"No boost is possible pour the target"));
        litteral_.addEntry("BASE_CAPT_PK", StringUtil.concat(MOVE_FORMULA,TAB,"catch_base",TAB,"Base catching rate of the wild pokemon"));
        litteral_.addEntry("RATE_BALL_STATUS", StringUtil.concat(MOVE_FORMULA,TAB,"ball_status_rate",TAB,"Product between catching rate of the ball and rates of the status of the wild pokemon."));
        litteral_.addEntry("FOE_PK_MAX_HP", StringUtil.concat(MOVE_FORMULA,TAB,"full_hp",TAB,"Full health points of the wild pokemon."));
        litteral_.addEntry("FOE_PK_REMOTE_HP", StringUtil.concat(MOVE_FORMULA,TAB,"rem_hp",TAB,"Remaining health points of the wild pokemon."));
        litteral_.addEntry("PK_UT_VITESSE", StringUtil.concat(MOVE_FORMULA,TAB,"pk_pl_speed",TAB,"Speed of the first pokemon of the player."));
        litteral_.addEntry("PK_SAUVAGE_VITESSE", StringUtil.concat(MOVE_FORMULA,TAB,"pk_foe_speed",TAB,"Speed of the wild pokemon."));
        litteral_.addEntry("NB_FLEES", StringUtil.concat(MOVE_FORMULA,TAB,"nb_flees",TAB,"Number of attempts of flee."));
        litteral_.addEntry("LEVEL_WINNER", StringUtil.concat(MOVE_FORMULA,TAB,"lvl_l",TAB,"Level of the KO fighter"));
        litteral_.addEntry("LEVEL_LOOSER", StringUtil.concat(MOVE_FORMULA,TAB,"lvl_w",TAB,"Level of the player fighter which wins points"));
        _data.getLitterals().addEntry(LANGUAGE,litteral_);
    }

    protected static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }

    public static void addCave(DataMap _dataMap) {
        Cave cave_ = new Cave();
        cave_.setLevels(new CustList< LevelCave>());
        cave_.setLinksWithOtherPlaces(new LevelPoints());
        addNewLevel(cave_);
        _dataMap.addPlace(cave_);
    }

    public static void addLevelCave(DataMap _dataMap, short _cave) {
        Cave cave_ = (Cave) _dataMap.getPlace(_cave);
        addNewLevel(cave_);
    }

    public static void addNewLevel(Cave _cave) {
        LevelCave level_ = new LevelCave();
        level_.setBlocks(new PointsBlock());
        level_.setCharacters(new PointsCharacterInRoadCave());
        level_.setDualFights(new PointsDualFight());
        level_.setHm(new PointsShort());
        level_.setTm(new PointsShort());
        level_.setItems(new PointsString());
        level_.setLegendaryPks(new PointsWildPk());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        level_.setLinksOtherLevels(new PointsLink());
        _cave.getLevels().add(level_);
    }

}
