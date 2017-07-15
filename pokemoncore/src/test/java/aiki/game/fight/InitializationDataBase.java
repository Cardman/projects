package aiki.game.fight;
import javax.imageio.ImageIO;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.util.TypesDuo;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.LawNumber;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.serialize.SerializeXmlObject;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringMap;
import code.util.consts.Constants;

public class InitializationDataBase {


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

    protected static DataBase _data_;

    protected InitializationDataBase() {
    }

    protected static void initDataBase() {
        if (_data_ != null) {
            //it is a single instance
            return;
        }
        ImageIO.setUseCache(false);
        DataBase data_ = coreDataBase();
        data_.sortEndRound();
        data_.getMap().setPlaces(new NumberMap<Short,Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
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
        data_.toBaseSixtyFour();
        data_.completeVariables();
        initTableTypes(data_);
        initConstants(data_);
        initRandomLaws(data_);
        initExpPoints(data_);
        initTmHm(data_);
        data_.initTypesByTable();
        initTranslations(data_);
        data_.setEndGameImage(NULL_REF);
        //OK data, no homonyms
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.checkNullPointers(data_.getPokedex());
        SerializeXmlObject.checkNullPointers(data_.getMoves());
        SerializeXmlObject.checkNullPointers(data_.getItems());
        SerializeXmlObject.checkNullPointers(data_.getAbilities());
        SerializeXmlObject.checkNullPointers(data_.getStatus());
        SerializeXmlObject.checkNullPointers(data_.getMap());
        SerializeXmlObject.checkNullPointers(data_.getCombos());
        data_.validateCore(false);
        ItemForBattle it_ = (ItemForBattle) data_.getItem(VIVE_GRIFFE_TRUE_FALSE);
        it_.getLawForAttackFirst().addEvent(false,new LgInt("0"));
        data_.validateConstants();
        data_.setCheckTranslation(false);
        CheckNumericStringsFight.validateNumericBooleanStrings(data_, false);
        data_.getMap().validate(data_);
        _data_ = data_;
    }

    protected static DataBase coreDataBase() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        InitializationPokedex.initAllPokemon(data_);
        InitializationPokedex.initAllSecondPokemons(data_);
        InitializationAbilities.initAllAbilities(data_);
        InitializationItems.initAllItems(data_);
        InitializationStatus.initAllStatus(data_);
        InitializationMoves.initAllMoves(data_);
        return data_;
    }

    protected static void initTableTypes(DataBase _data) {
        _data.getTableTypes().put(new TypesDuo(ACIER,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,FEE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,ACIER),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEE,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,TENEBRE),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,PSY),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,ROCHE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,INSECTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,ROCHE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(EAU,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,FEU),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(VOL,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,NORMAL),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,PLANTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,EAU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,POISON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SOL,POISON),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,VOL),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,INSECTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(SOL,ACIER),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,INSECTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,POISON),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,GLACE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,VOL),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(VOL,COMBAT),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,POISON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,ROCHE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,TENEBRE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(FEU,DRAGON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(VOL,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,GLACE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,PSY),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,ROCHE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(FEU,ROCHE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(POISON,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,TENEBRE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(EAU,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,SOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(PSY,PSY),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,SPECTRE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,FEU),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SOL,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,DRAGON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,SOL),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PSY,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,VOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,PLANTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,SPECTRE),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,DRAGON),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(SOL,FEU),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,ACIER),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,INSECTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,FEU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PSY,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,VOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(VOL,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,POISON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,FEU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,PLANTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,EAU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,GLACE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PSY,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,ACIER),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,ROCHE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,EAU),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,ROCHE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,DRAGON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(EAU,DRAGON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,SOL),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(FEU,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,PSY),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,NORMAL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,SPECTRE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SOL,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,TENEBRE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,FEU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SOL,ROCHE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(FEU,EAU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,DRAGON),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(FEU,PLANTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,VOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(PSY,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,ACIER),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PSY,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,SOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,SPECTRE),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(VOL,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,SPECTRE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,VOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,NORMAL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,TENEBRE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ACIER,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,SPECTRE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(VOL,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,ACIER),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,EAU),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,INSECTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PSY,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,SOL),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(SOL,PLANTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,EAU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(POISON,PLANTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(PSY,COMBAT),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,FEU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,POISON),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,PLANTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(GLACE,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(GLACE,FEU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(EAU,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,GLACE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,VOL),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(VOL,ROCHE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,PSY),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(VOL,INSECTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(COMBAT,ACIER),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(POISON,ACIER),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(PSY,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,PSY),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(EAU,PLANTE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(PSY,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,SPECTRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SPECTRE,ROCHE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,VOL),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(EAU,GLACE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,TENEBRE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(EAU,COMBAT),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(DRAGON,EAU),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ACIER,PLANTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,INSECTE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ROCHE,GLACE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(VOL,PLANTE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,SOL),new Rate("0"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,FEU),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(TENEBRE,COMBAT),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(SOL,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(SOL,ELECTRIQUE),new Rate("2"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(ELECTRIQUE,ELECTRIQUE),new Rate("1/2"));
        _data.getTableTypes().put(new TypesDuo(INSECTE,SOL),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(VOL,DRAGON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(PLANTE,ELECTRIQUE),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(NORMAL,POISON),new Rate("1"));
        _data.getTableTypes().put(new TypesDuo(FEU,PSY),new Rate("1"));
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
        _data.initDefaultConsts(POKE_BALL,
            "caracdroiteferme(div(FOE_PK_MAX_HP,FOE_PK_REMOTE_HP),2)",
            "caracdroiteferme(div(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)",
            "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)",
            "puis(2,VAR__BOOST-4)",
            "div((5+VAR__LANCEUR_NIVEAU)*VAR__ATTACK*VAR__POWER,(125*VAR__DEFENSE))",
            LUTTE,
            "METAMORPH");
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
        _data.getExpGrowth().put(ExpType.E,"2*VAR__NIVEAU");
        _data.getExpGrowth().put(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().put(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().put(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().put(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().put(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }

    protected static void initTmHm(DataBase _data) {
        _data.getHm().put((short) 1, "ECLAIR");
        _data.getTm().put((short) 2, "TONNERRE");
        _data.getTm().put((short) 3, "CHARGE");
        _data.getTm().put((short) 4, "VIVE_ATTAQUE");
        _data.getTm().put((short) 5, "JACKPOT");
        _data.getTmPrice().put((short) 2, new LgInt(4000));
        _data.getTmPrice().put((short) 3, new LgInt(2000));
        _data.getTmPrice().put((short) 4, new LgInt(3000));
    }

    protected static void initTranslations(DataBase _data) {
        EnumMap<Gender,String> genders_;
        genders_ = new EnumMap<Gender,String>();
        genders_.put(Gender.FEMALE, Gender.FEMALE.name());
        genders_.put(Gender.NO_GENDER, Gender.NO_GENDER.name());
        genders_.put(Gender.MALE, Gender.MALE.name());
        _data.getTranslatedGenders().put(LANGUAGE, genders_);
        EnumMap<Statistic,String> statistics_;
        statistics_ = new EnumMap<Statistic,String>();
        statistics_.put(Statistic.ATTACK, Statistic.ATTACK.name());
        statistics_.put(Statistic.DEFENSE, Statistic.DEFENSE.name());
        statistics_.put(Statistic.SPECIAL_ATTACK, Statistic.SPECIAL_ATTACK.name());
        statistics_.put(Statistic.SPECIAL_DEFENSE, Statistic.SPECIAL_DEFENSE.name());
        statistics_.put(Statistic.SPEED, Statistic.SPEED.name());
        statistics_.put(Statistic.CRITICAL_HIT, Statistic.CRITICAL_HIT.name());
        statistics_.put(Statistic.ACCURACY, Statistic.ACCURACY.name());
        statistics_.put(Statistic.EVASINESS, Statistic.EVASINESS.name());
        _data.getTranslatedStatistics().put(LANGUAGE, statistics_);
        EnumMap<TargetChoice,String> targets_;
        targets_ = new EnumMap<TargetChoice,String>();
        targets_.put(TargetChoice.ADJ_ADV, TargetChoice.ADJ_ADV.name());
        targets_.put(TargetChoice.ADJ_MULT, TargetChoice.ADJ_MULT.name());
        targets_.put(TargetChoice.ADJ_UNIQ, TargetChoice.ADJ_UNIQ.name());
        targets_.put(TargetChoice.ALLIE, TargetChoice.ALLIE.name());
        targets_.put(TargetChoice.ALLIES, TargetChoice.ALLIES.name());
        targets_.put(TargetChoice.ANY_FOE, TargetChoice.ANY_FOE.name());
        targets_.put(TargetChoice.AUTRE_UNIQ, TargetChoice.AUTRE_UNIQ.name());
        targets_.put(TargetChoice.GLOBALE, TargetChoice.GLOBALE.name());
        targets_.put(TargetChoice.LANCEUR, TargetChoice.LANCEUR.name());
        targets_.put(TargetChoice.PSEUDO_GLOBALE, TargetChoice.PSEUDO_GLOBALE.name());
        targets_.put(TargetChoice.TOUS_ADV, TargetChoice.TOUS_ADV.name());
        targets_.put(TargetChoice.UNIQUE_IMPORTE, TargetChoice.UNIQUE_IMPORTE.name());
        _data.getTranslatedTargets().put(LANGUAGE, targets_);
        StringMap<String> words_;
        words_ = new StringMap<String>();
        for (String p: _data.getPokedex().getKeys()) {
            words_.put(p, p);
        }
        _data.getTranslatedPokemon().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (String p: _data.getMoves().getKeys()) {
            words_.put(p, p);
        }
        _data.getTranslatedMoves().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (String p: _data.getItems().getKeys()) {
            words_.put(p, p);
        }
        _data.getTranslatedItems().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (String p: _data.getAbilities().getKeys()) {
            words_.put(p, p);
        }
        _data.getTranslatedAbilities().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (String p: _data.getStatus().getKeys()) {
            words_.put(p, p);
        }
        _data.getTranslatedStatus().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (Item p: _data.getItems().values()) {
            words_.put(p.getItemType(), p.getItemType());
        }
        _data.getTranslatedClassesDescriptions().put(LANGUAGE, words_);
        words_ = new StringMap<String>();
        for (String p: _data.getTypes()) {
            words_.put(p, p);
        }
        _data.getTranslatedTypes().put(LANGUAGE, words_);
        Constants.setLanguage(LANGUAGE);
    }

    protected static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }
}
