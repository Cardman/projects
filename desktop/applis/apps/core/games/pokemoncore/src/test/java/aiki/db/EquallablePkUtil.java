package aiki.db;

import aiki.facade.enums.*;
import aiki.fight.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.status.*;
import aiki.game.*;
import aiki.game.enums.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.animations.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
import aiki.game.params.*;
import aiki.game.params.enums.*;
import aiki.game.player.*;
import aiki.game.player.enums.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.tree.*;
import aiki.map.tree.util.*;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.*;
import code.maths.litteral.MbOperationNode;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;
import org.junit.Assert;

public abstract class EquallablePkUtil {
    protected static final String A_ABS = MbOperationNode.ABS;
    protected static final String A_CARACDROITEFERME = MbOperationNode.CARAC_DROITE_FERME;
    protected static final String A_CARACDROITEOUVERT = MbOperationNode.CARAC_DROITE_OUVERT;
    protected static final String A_CARACFERME = MbOperationNode.CARAC_FERME;
    protected static final String A_CARACGAUCHEFERME = MbOperationNode.CARAC_GAUCHE_FERME;
    protected static final String A_CARACGAUCHEOUVERT = MbOperationNode.CARAC_GAUCHE_OUVERT;
    protected static final String A_CARACSEMIOUVERTD = MbOperationNode.CARAC_SEMI_OUVERT_D;
    protected static final String A_CARDINAL = MbOperationNode.CARD;
    protected static final String A_COMPLEMENTAIRE = MbOperationNode.COMPL;
    protected static final String A_DIFFERENTNUM = MbOperationNode.NON_EQ_NUM;
    protected static final String A_DIV = MbOperationNode.DIV_FCT;
    protected static final String A_EGALNUM = MbOperationNode.EQ_NUM;
    protected static final String A_ENT = MbOperationNode.ENT;
    protected static final String A_INCLUSNUM = MbOperationNode.INCL;
    protected static final String A_INTER = MbOperationNode.INTER;
    protected static final String A_MAX = MbOperationNode.MAX;
    protected static final String A_MIN = MbOperationNode.MIN;
    protected static final String A_MOY = MbOperationNode.MOY;
    protected static final String A_PUIS = MbOperationNode.PUIS;
    protected static final String A_UNION = MbOperationNode.UNION;
    protected static final String CF = MbOperationNode.FALSE_STRING;
    protected static final String ES = "";
    protected static final String VD = MessagesDataBaseConstants.VAR_DEF;
    protected static final String SE = DataBase.SEP_BETWEEN_KEYS;
    protected static final String V_ATTAQUE_CATEGORIE = VD+SE+ MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE;
    protected static final String V_ATTAQUE_NOM = VD+SE+ MessagesDataBaseConstants.DEF_ATTAQUE_NOM;
    protected static final String V_ATTAQUE_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_ATTAQUE_TYPES;
    protected static final String V_AUCUN_BOOST_POSSIBLE = VD+SE+ MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE;
    protected static final String V_CIBLE_ATTAQUES = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES;
    protected static final String V_CIBLE_ATTAQUE_CHOISIE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE;
    protected static final String V_CIBLE_BOOST = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_BOOST;
    protected static final String V_CIBLE_CAPACITE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_CAPACITE;
    protected static final String V_CIBLE_CLONE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_CLONE;
    protected static final String V_CIBLE_DEGATS_RECUS_TOTAL_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR;
    protected static final String V_CIBLE_DISPARAIT = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT;
    protected static final String V_CIBLE_EFFET = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_EFFET;
    protected static final String V_CIBLE_GENRE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_GENRE;
    protected static final String V_CIBLE_JOUE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_JOUE;
    protected static final String V_CIBLE_MASSE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_MASSE;
    protected static final String V_CIBLE_NB_UTILISATION = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION;
    protected static final String V_CIBLE_NIVEAU = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_NIVEAU;
    protected static final String V_CIBLE_OBJET = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_OBJET;
    protected static final String V_CIBLE_POSSEDE_STATUT_RELATION = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION;
    protected static final String V_CIBLE_PV_MAX = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_PV_MAX;
    protected static final String V_CIBLE_PV_RESTANTS = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS;
    protected static final String V_CIBLE_STATIS = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_STATIS;
    protected static final String V_CIBLE_STATUTS = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_STATUTS;
    protected static final String V_CIBLE_TAILLE = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_TAILLE;
    protected static final String V_CIBLE_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_CIBLE_TYPES;
    protected static final String V_CLIMATS = VD+SE+ MessagesDataBaseConstants.DEF_CLIMATS;
    protected static final String V_COEFF_EFF = VD+SE+ MessagesDataBaseConstants.DEF_COEFF_EFF;
    protected static final String V_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT = VD+SE+ MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT;
    protected static final String V_COMBATTANT_ENTRANT_CLONE = VD+SE+ MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE;
    protected static final String V_COMBATTANT_ENTRANT_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES;
    protected static final String V_DEJA_CAPTURE = VD+SE+ MessagesDataBaseConstants.DEF_DEJA_CAPTURE;
    protected static final String V_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION = VD+SE+ MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION;
    protected static final String V_EXISTE_GENRE_ASSEXUE = VD+SE+ MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE;
    protected static final String V_FIGHTER_CLONE = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_CLONE;
    protected static final String V_FIGHTER_DER_JOUE = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE;
    protected static final String V_FIGHTER_NOM = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_NOM;
    protected static final String V_FIGHTER_OBJET = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_OBJET;
    protected static final String V_FIGHTER_STATUTS = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_STATUTS;
    protected static final String V_FIGHTER_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_FIGHTER_TYPES;
    protected static final String V_GENRES_EGAUX = VD+SE+ MessagesDataBaseConstants.DEF_GENRES_EGAUX;
    protected static final String V_IMMU_TYPE_ATT_CIBLE = VD+SE+ MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE;
    protected static final String V_IMMU_TYPE_ATT_COMBATTANT_ENTRANT = VD+SE+ MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT;
    protected static final String V_LANCEUR_ATTAQUES = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES;
    protected static final String V_LANCEUR_ATTAQUES_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES;
    protected static final String V_LANCEUR_BONHEUR = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR;
    protected static final String V_LANCEUR_BOOST = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_BOOST;
    protected static final String V_LANCEUR_CLONE = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_CLONE;
    protected static final String V_LANCEUR_DEGATS_RECUS_TOTAL = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL;
    protected static final String V_LANCEUR_DEGATS_RECUS_TOTAL_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR;
    protected static final String V_LANCEUR_DEGATS_RECUS_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR;
    protected static final String V_LANCEUR_EFFET = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_EFFET;
    protected static final String V_LANCEUR_GENRE = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_GENRE;
    protected static final String V_LANCEUR_MASSE = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_MASSE;
    protected static final String V_LANCEUR_NB_UTILISATION = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION;
    protected static final String V_LANCEUR_NIVEAU = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU;
    protected static final String V_LANCEUR_NOM = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_NOM;
    protected static final String V_LANCEUR_OBJET = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_OBJET;
    protected static final String V_LANCEUR_PP = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_PP;
    protected static final String V_LANCEUR_PV_MAX = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX;
    protected static final String V_LANCEUR_PV_RESTANTS = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS;
    protected static final String V_LANCEUR_STATIS = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_STATIS;
    protected static final String V_LANCEUR_STATUTS = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_STATUTS;
    protected static final String V_LANCEUR_TAILLE = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_TAILLE;
    protected static final String V_LANCEUR_TYPES = VD+SE+ MessagesDataBaseConstants.DEF_LANCEUR_TYPES;
    protected static final String V_LIEU_COMBAT = VD+SE+ MessagesDataBaseConstants.DEF_LIEU_COMBAT;
    protected static final String V_MASSE_MOYENNE_PK = VD+SE+ MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK;
    protected static final String V_NB_COMBATTANTS_TERRAIN = VD+SE+ MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN;
    protected static final String V_NB_KO_EQUIPE_LANCEUR = VD+SE+ MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR;
    protected static final String V_NB_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_NB_TOUR;
    protected static final String V_NB_TOUR_GLOBAL = VD+SE+ MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL;
    protected static final String V_NB_UTILISATION_CONSECUTIF = VD+SE+ MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF;
    protected static final String V_NB_UTILI_ATT_EQ_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR;
    protected static final String V_PAS_ATTAQUES_COPIABLES = VD+SE+ MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES;
    protected static final String V_PAS_ATTAQUE_INVOC = VD+SE+ MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC;
    protected static final String V_PAS_PARTENAIRE_ARRIERE = VD+SE+ MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE;
    protected static final String V_PAS_PARTENAIRE_TERRAIN = VD+SE+ MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN;
    protected static final String V_PAS_PP_ATTAQUE_CIBLE = VD+SE+ MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE;
    protected static final String V_PAS_TOUR_TERRAIN = VD+SE+ MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN;
    protected static final String V_PAS_UTILIS_ATTAQUE_CIBLE = VD+SE+ MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE;
    protected static final String V_PK_SAUVAGE_GENRE = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE;
    protected static final String V_PK_SAUVAGE_MASSE = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE;
    protected static final String V_PK_SAUVAGE_NIVEAU = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU;
    protected static final String V_PK_SAUVAGE_PIERRES_EVOS = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS;
    protected static final String V_PK_SAUVAGE_TYPES_BASE = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE;
    protected static final String V_PK_SAUVAGE_VITESSE = VD+SE+ MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE;
    protected static final String V_PK_UT_GENRE = VD+SE+ MessagesDataBaseConstants.DEF_PK_UT_GENRE;
    protected static final String V_PK_UT_NIVEAU = VD+SE+ MessagesDataBaseConstants.DEF_PK_UT_NIVEAU;
    protected static final String V_PUISSANCE_BASE = VD+SE+ MessagesDataBaseConstants.DEF_PUISSANCE_BASE;
    protected static final String V_SOMME_BOOST_POS_CIBLE = VD+SE+ MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE;
    protected static final String V_SOMME_BOOST_POS_LANCEUR = VD+SE+ MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR;
    protected static final String V_TEMPS_TOUR = VD+SE+ MessagesDataBaseConstants.DEF_TEMPS_TOUR;
    protected static final String V_TYPES_ATTAQUES_RES_VIDE = VD+SE+ MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE;
    protected static final char GT = '>';
    protected static final char LB = '{';
    protected static final char LP = '(';
    protected static final char LT = '<';
    protected static final char OA = '&';
    protected static final char OC = ',';
    protected static final char OD = '-';
    protected static final char OE = '=';
    protected static final char OM = '*';
    protected static final char OO = '|';
    protected static final char OP = '+';
    protected static final char OQ = ':';
    protected static final char OS = ';';
    protected static final char RB = '}';
    protected static final char RP = ')';
    protected static final String VAR_PREFIX = MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS;
    protected static final String AUTRE = "AUTRE";
    private static final String DIFF = " != ";
    public static void assertNotNull(Dims _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Sex _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MoveTarget _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Link _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(HostPokemonDuo _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(int[][] _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TrainerMultiFights _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Person _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CharacterInRoadCave _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(EffectWhileSending _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Fighter _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Fight _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Team _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Player _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Difficulty _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Game _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(DataMap _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Item _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Inventory _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Combos _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MovesAbilities _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ChoiceOfEvolutionAndMoves _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Building _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(DualFight _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Point _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ActivityOfMove _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Block _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(AbstractAction _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(AffectedMove _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(IntMonteCarlo _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(Countable _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TeamPosition _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(BoolVal _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(PkTrainer _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(WildPk _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Coords _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TileMiniMap _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Rate _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ExchangedData _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MoveData _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(BuildingArea _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Status _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(UsablePokemon _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TargetCoords _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Place _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Level _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Countable _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(TargetCoords _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(AbstractAction _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(UsablePokemon _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Sex _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(LevelPoint _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Place _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Level _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(ExpType _expected, ExpType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(DifficultyWinPointsFight _expected, DifficultyWinPointsFight _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(DifficultyModelLaw _expected, DifficultyModelLaw _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SelectedBoolean _expected, SelectedBoolean _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SearchingMode _expected, SearchingMode _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(EnvironmentType _expected, EnvironmentType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ConstValuesType _expected, ConstValuesType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(StatusType _expected, StatusType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(FightState _expected, FightState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(FightType _expected, FightType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GeranceType _expected, GeranceType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SellType _expected, SellType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SwitchType _expected, SwitchType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(PointViewChangementType _expected, PointViewChangementType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MoveItemType _expected, MoveItemType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MoveChoiceRestrictionType _expected, MoveChoiceRestrictionType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ExchangeType _expected, ExchangeType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(TargetChoice _expected, TargetChoice _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GenderRepartition _expected, GenderRepartition _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Gender _expected, Gender _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Sex _expected, Sex _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertSame(Block _expected, Block _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(IssueSimulation _expected, IssueSimulation _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AbsAreaApparition _expected, AbsAreaApparition _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(UsablePokemon _expected, UsablePokemon _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Fighter _expected, Fighter _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AutoEffectKind _expected, AutoEffectKind _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(EffectKind _expected, EffectKind _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Gender _expected, Gender _result) {
        Assert.assertNotSame(_expected, _result);
    }


    public static void assertEq(int[][] _expected, int[][] _result) {
        Assert.assertNotNull(_result);
        Assert.assertArrayEquals(_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, short _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, byte _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(byte _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(byte _expected, short _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(byte _expected, byte _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getList(), _result.getList());
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    public static void assertEq(TeamPosition _expected, TeamPosition _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getTeam()+";"+_expected.getPosition(),_result.getTeam()+";"+_result.getPosition());
    }
    public static void assertEq(TargetCoords _expected, TargetCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getTeam()+";"+_expected.getPosition(),_result.getTeam()+";"+_result.getPosition());
    }
    public static void assertEq(Coords _expected, Coords _result) {
        Assert.assertNotNull(_result);
        assertTrue(_expected.eq(_result));
//        Assert.assertEquals(_expected.getNumberPlace(),_result.getNumberPlace());
//        Assert.assertEquals(_expected.isInside(),_result.isInside());
//        if (_expected.isInside()) {
//            Assert.assertEquals(_expected.getInsideBuilding().getx(),_result.getInsideBuilding().getx());
//            Assert.assertEquals(_expected.getInsideBuilding().gety(),_result.getInsideBuilding().gety());
//        }
//        Assert.assertEquals(_expected.getLevel().getLevelIndex(),_result.getLevel().getLevelIndex());
//        Assert.assertEquals(_expected.getLevel().getPoint().getx(),_result.getLevel().getPoint().getx());
//        Assert.assertEquals(_expected.getLevel().getPoint().gety(),_result.getLevel().getPoint().gety());
    }
    public static void assertEq(Point _expected, Point _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getx(),_result.getx());
        Assert.assertEquals(_expected.gety(),_result.gety());
    }
    
    public static void assertEq(Dims _expected, Dims _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getHeight(),_result.getHeight());
        Assert.assertEquals(_expected.getWidth(),_result.getWidth());
    }

    public static void assertEq(ScreenCoords _expected, ScreenCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getXcoords(),_result.getXcoords());
        Assert.assertEquals(_expected.getYcoords(),_result.getYcoords());
    }

    public static void assertEq(EndTurnType _expected, EndTurnType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(RelationType _expected, RelationType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(GenderRepartition _expected, GenderRepartition _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Gender _expected, Gender _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(TargetChoice _expected, TargetChoice _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(InterfaceType _expected, InterfaceType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Direction _expected, Direction _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(FightState _expected, FightState _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(ActionType _expected, ActionType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(FightType _expected, FightType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Statistic _expected, Statistic _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(IssueSimulation _expected, IssueSimulation _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(EnvironmentType _expected, EnvironmentType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(PointViewChangementType _expected, PointViewChangementType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static TeamPosition tp(int _t, int _p) {
        return new TeamPosition((byte) _t,(byte) _p);
    }

    public static TargetCoords tc(int _t, int _p) {
        return new TargetCoords((byte) _t,(byte) _p);
    }
    public static void initDefaultConsts(String _ballDef, String _rateCatching,
                                  String _rateFleeing, String _rateBoost,
                                  String _rateBoostCriticalHit, String _damageFormula,
                                  String _defMove, String _defaultEggGoup, DataBase _db) {
        _db.setBallDef(_ballDef);
        _db.setRateCatching(_rateCatching);
        _db.setRateFleeing(_rateFleeing);
        _db.setRateBoost(_rateBoost);
        _db.setRateBoostCriticalHit(_rateBoostCriticalHit);
        _db.setDamageFormula(_damageFormula);
        _db.setDefMove(_defMove);
        _db.setDefaultEggGroup(_defaultEggGoup);
        _db.setDefCategory(AUTRE);
        _db.defValues();
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,"");
    }
}
