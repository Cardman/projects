package aiki.db;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class ExchangedDataTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }

    @Test
    public void new_ExchangedData_1Test() {
        ExchangedData ex_ = new ExchangedData();
        assertNull(ex_.getAbilities());
        assertNull(ex_.getItems());
        assertNull(ex_.getPokemon());
        assertNull(ex_.getGenderRepartitions());
    }

    @Test
    public void new_ExchangedData_DataBase_1Test() {
        ExchangedData ex_ = new ExchangedData(data);
        StringList abilities_ = ex_.getAbilities();
        assertEq(107, abilities_.size());
        assertTrue(StringList.contains(abilities_, TOXITOUCHE));
        assertTrue(StringList.contains(abilities_, HYPER_CUTTER));
        assertTrue(StringList.contains(abilities_, TECHNICIEN));
        assertTrue(StringList.contains(abilities_, ANNULE_GARDE));
        assertTrue(StringList.contains(abilities_, COEUR_SOIN));
        assertTrue(StringList.contains(abilities_, TIRS));
        assertTrue(StringList.contains(abilities_, PIED_VELOCE));
        assertTrue(StringList.contains(abilities_, REGARD_VIF));
        assertTrue(StringList.contains(abilities_, SYNCHRO));
        assertTrue(StringList.contains(abilities_, ASYNCHRO));
        assertTrue(StringList.contains(abilities_, IMPUDENCE));
        assertTrue(StringList.contains(abilities_, IGNIFUGE));
        assertTrue(StringList.contains(abilities_, CORPS_SAIN));
        assertTrue(StringList.contains(abilities_, GLISSADE));
        assertTrue(StringList.contains(abilities_, ATTENTION));
        assertTrue(StringList.contains(abilities_, COEUR_NOBLE));
        assertTrue(StringList.contains(abilities_, SUINTEMENT));
        assertTrue(StringList.contains(abilities_, ARMUROUILLEE));
        assertTrue(StringList.contains(abilities_, TERA_VOLTAGE));
        assertTrue(StringList.contains(abilities_, GLOUTONNERIE));
        assertTrue(StringList.contains(abilities_, TANT_PIS));
        assertTrue(StringList.contains(abilities_, DEGUISEMENT));
        assertTrue(StringList.contains(abilities_, ARMUMAGMA));
        assertTrue(StringList.contains(abilities_, SECHERESSE));
        assertTrue(StringList.contains(abilities_, AIR_LOCK));
        assertTrue(StringList.contains(abilities_, SYMBIOSE));
        assertTrue(StringList.contains(abilities_, COLERIQUE));
        assertTrue(StringList.contains(abilities_, LEVITATION));
        assertTrue(StringList.contains(abilities_, FERMETE));
        assertTrue(StringList.contains(abilities_, CONTRE));
        assertTrue(StringList.contains(abilities_, FOUR));
        assertTrue(StringList.contains(abilities_, PEAU_MIRACLE_QUATER));
        assertTrue(StringList.contains(abilities_, SOLIDE_ROC));
        assertTrue(StringList.contains(abilities_, POISSEUX));
        assertTrue(StringList.contains(abilities_, GARDE_MYSTIK));
        assertTrue(StringList.contains(abilities_, STATIK));
        assertTrue(StringList.contains(abilities_, TETE_DE_ROC));
        assertTrue(StringList.contains(abilities_, MULTITYPE));
        assertTrue(StringList.contains(abilities_, CRAN));
        assertTrue(StringList.contains(abilities_, ABRI_CAPE));
        assertTrue(StringList.contains(abilities_, PIED_RAPIDE));
        assertTrue(StringList.contains(abilities_, ARMURBASTON));
        assertTrue(StringList.contains(abilities_, NORMALISE));
        assertTrue(StringList.contains(abilities_, MAGNEPIEGE));
        assertTrue(StringList.contains(abilities_, MUE));
        assertTrue(StringList.contains(abilities_, PARATONNERRE));
        assertTrue(StringList.contains(abilities_, TELECHARGE));
        assertTrue(StringList.contains(abilities_, PEAU_DURE));
        assertTrue(StringList.contains(abilities_, PEAU_MIRACLE_TER));
        assertTrue(StringList.contains(abilities_, ALEA_STAT));
        assertTrue(StringList.contains(abilities_, REGE_FORCE));
        assertTrue(StringList.contains(abilities_, AROMA_VOILE));
        assertTrue(StringList.contains(abilities_, SANS_LIMITE));
        assertTrue(StringList.contains(abilities_, METEO));
        assertTrue(StringList.contains(abilities_, ACHARNE));
        assertTrue(StringList.contains(abilities_, SIMPLE));
        assertTrue(StringList.contains(abilities_, JOLI_SOURIRE));
        assertTrue(StringList.contains(abilities_, PEAU_FEERIQUE));
        assertTrue(StringList.contains(abilities_, TELEPATHE));
        assertTrue(StringList.contains(abilities_, MAUVAIS_REVE));
        assertTrue(StringList.contains(abilities_, CRACHIN));
        assertTrue(StringList.contains(abilities_, FREIN));
        assertTrue(StringList.contains(abilities_, BOOM_FINAL));
        assertTrue(StringList.contains(abilities_, MEDIC_NATURE));
        assertTrue(StringList.contains(abilities_, INFILTRATION));
        assertTrue(StringList.contains(abilities_, PEAU_MIRACLE_BIS));
        assertTrue(StringList.contains(abilities_, GARDE_AMIE));
        assertTrue(StringList.contains(abilities_, QUERELLEUR));
        assertTrue(StringList.contains(abilities_, OEIL_COMPOSE));
        assertTrue(StringList.contains(abilities_, ECRAN_POUDRE));
        assertTrue(StringList.contains(abilities_, PYROMANE));
        assertTrue(StringList.contains(abilities_, CHANCEUX));
        assertTrue(StringList.contains(abilities_, SERENITE));
        assertTrue(StringList.contains(abilities_, ABSORB_VOLT));
        assertTrue(StringList.contains(abilities_, CALQUE));
        assertTrue(StringList.contains(abilities_, ENVELOCAPE));
        assertTrue(StringList.contains(abilities_, MULTI_COUPS));
        assertTrue(StringList.contains(abilities_, GARDE));
        assertTrue(StringList.contains(abilities_, ESSAIM));
        assertTrue(StringList.contains(abilities_, ABSORB_EAU));
        assertTrue(StringList.contains(abilities_, GARDE_POUR_SOI));
        assertTrue(StringList.contains(abilities_, INCONSCIENT));
        assertTrue(StringList.contains(abilities_, FEUILLE_PETITE));
        assertTrue(StringList.contains(abilities_, GARDE_MAGIK));
        assertTrue(StringList.contains(abilities_, AURA_TENEBREUSE));
        assertTrue(StringList.contains(abilities_, FLORA_VOILE));
        assertTrue(StringList.contains(abilities_, AURA_INVERSEE));
        assertTrue(StringList.contains(abilities_, CONTRAIRE));
        assertTrue(StringList.contains(abilities_, SNIPER));
        assertTrue(StringList.contains(abilities_, MOITEUR));
        assertTrue(StringList.contains(abilities_, MAGICIEN));
        assertTrue(StringList.contains(abilities_, TURBO));
        assertTrue(StringList.contains(abilities_, BAJOUES));
        assertTrue(StringList.contains(abilities_, MATINAL));
        assertTrue(StringList.contains(abilities_, FEUILLE_GARDE));
        assertTrue(StringList.contains(abilities_, ANTI_BRUIT));
        assertTrue(StringList.contains(abilities_, PRESSION));
        assertTrue(StringList.contains(abilities_, AILES_BOURRASQUE));
        assertTrue(StringList.contains(abilities_, IMPASSIBLE));
        assertTrue(StringList.contains(abilities_, LENTITEINTEE));
        assertTrue(StringList.contains(abilities_, ADAPTABILITE));
        assertTrue(StringList.contains(abilities_, PROTEEN));
        assertTrue(StringList.contains(abilities_, POISSON));
        assertTrue(StringList.contains(abilities_, TENSION));
        assertTrue(StringList.contains(abilities_, FARCEUR));
        assertTrue(StringList.contains(abilities_, PEAU_MIRACLE));
        assertTrue(StringList.contains(abilities_, TERA_VOLT));
        StringList items_ = ex_.getItems();
        assertEq(100, items_.size());
        assertTrue(StringList.contains(items_, RAPPEL));
        assertTrue(StringList.contains(items_, BAIE_PITAYE));
        assertTrue(StringList.contains(items_, ROCHE_LISSE));
        assertTrue(StringList.contains(items_, BAIE_LAMPOU));
        assertTrue(StringList.contains(items_, POUDRE_VITE));
        assertTrue(StringList.contains(items_, BAIE_CERIZ));
        assertTrue(StringList.contains(items_, HAPPY_POTION));
        assertTrue(StringList.contains(items_, HYPER_BALL));
        assertTrue(StringList.contains(items_, CARTE_ROUGE));
        assertTrue(StringList.contains(items_, GRAIN_MIRACL));
        assertTrue(StringList.contains(items_, CEINTURE_PRO));
        assertTrue(StringList.contains(items_, EAU_FRAICHE));
        assertTrue(StringList.contains(items_, BLACK_BERRY));
        assertTrue(StringList.contains(items_, BOUE_NOIRE));
        assertTrue(StringList.contains(items_, BAIE_GOWAV));
        assertTrue(StringList.contains(items_, ENCENS_VAGUE));
        assertTrue(StringList.contains(items_, CEINT_FORCE));
        assertTrue(StringList.contains(items_, BOLT));
        assertTrue(StringList.contains(items_, PIERRE_STASE));
        assertTrue(StringList.contains(items_, PLAQUE_DRACO));
        assertTrue(StringList.contains(items_, PIERRALLEGEE));
        assertTrue(StringList.contains(items_, PIECE_RUNE));
        assertTrue(StringList.contains(items_, VIVE_GRIFFE));
        assertTrue(StringList.contains(items_, LAVA));
        assertTrue(StringList.contains(items_, BANDEAU_ETREINTE));
        assertTrue(StringList.contains(items_, MAX_ELIXIR));
        assertTrue(StringList.contains(items_, POKE_BALL));
        assertTrue(StringList.contains(items_, PIERRE_LUNE));
        assertTrue(StringList.contains(items_, OEUF_CHANCE));
        assertTrue(StringList.contains(items_, BAIE_JABOCA));
        assertTrue(StringList.contains(items_, CABLE));
        assertTrue(StringList.contains(items_, LENTILSCOPE));
        assertTrue(StringList.contains(items_, BAIE_ENIGMA));
        assertTrue(StringList.contains(items_, NOEUD_DESTIN));
        assertTrue(StringList.contains(items_, PETIT_RAPPEL));
        assertTrue(StringList.contains(items_, LUMARGILE));
        assertTrue(StringList.contains(items_, BAIE_CHERIM));
        assertTrue(StringList.contains(items_, CENDRESACREE));
        assertTrue(StringList.contains(items_, PIERRE_SOLEIL));
        assertTrue(StringList.contains(items_, PIERRE_EAU));
        assertTrue(StringList.contains(items_, HUILE));
        assertTrue(StringList.contains(items_, GRELOT));
        assertTrue(StringList.contains(items_, BANDEAU));
        assertTrue(StringList.contains(items_, BRAC_MACHO));
        assertTrue(StringList.contains(items_, ROCHE_ROYALE));
        assertTrue(StringList.contains(items_, GRELOT_ZEN));
        assertTrue(StringList.contains(items_, ORBE_FLAMME));
        assertTrue(StringList.contains(items_, ACCRO_GRIFFE));
        assertTrue(StringList.contains(items_, PEPITE));
        assertTrue(StringList.contains(items_, LICHEN_LUMINEUX));
        assertTrue(StringList.contains(items_, TOTAL_SOIN));
        assertTrue(StringList.contains(items_, ENCENS_PUR));
        assertTrue(StringList.contains(items_, ORBE_VIE));
        assertTrue(StringList.contains(items_, PIQUANTS));
        assertTrue(StringList.contains(items_, MAX_REPOUSSE));
        assertTrue(StringList.contains(items_, HERBE_MENTAL));
        assertTrue(StringList.contains(items_, SUPER_BALL));
        assertTrue(StringList.contains(items_, BAIE_ORAN));
        assertTrue(StringList.contains(items_, BAIE_MEPO));
        assertTrue(StringList.contains(items_, PP_PLUS_BIS));
        assertTrue(StringList.contains(items_, VIVE_GRIFFE_TRUE_FALSE));
        assertTrue(StringList.contains(items_, PP_PLUS));
        assertTrue(StringList.contains(items_, ENCENS_PLEIN));
        assertTrue(StringList.contains(items_, POTION));
        assertTrue(StringList.contains(items_, VIEIL_AMBRE));
        assertTrue(StringList.contains(items_, BAIE_MICLE));
        assertTrue(StringList.contains(items_, ELIXIR));
        assertTrue(StringList.contains(items_, REPOUSSE));
        assertTrue(StringList.contains(items_, MULTI_EXP));
        assertTrue(StringList.contains(items_, RASP_BERRY));
        assertTrue(StringList.contains(items_, BOUTON_FUITE));
        assertTrue(StringList.contains(items_, PV_PLUS));
        assertTrue(StringList.contains(items_, HUILE_MAX));
        assertTrue(StringList.contains(items_, POUDRE_ATTAQUE));
        assertTrue(StringList.contains(items_, GRELOT_COQUE));
        assertTrue(StringList.contains(items_, HERBE_POUV));
        assertTrue(StringList.contains(items_, BAIE_LANSAT));
        assertTrue(StringList.contains(items_, BOUE_BLANCHE));
        assertTrue(StringList.contains(items_, LUNETTES_FILTRE));
        assertTrue(StringList.contains(items_, LUXE_BALL));
        assertTrue(StringList.contains(items_, CEINT_POUV));
        assertTrue(StringList.contains(items_, BAIE_MANGA));
        assertTrue(StringList.contains(items_, METRONOME_OBJ));
        assertTrue(StringList.contains(items_, MAGNET));
        assertTrue(StringList.contains(items_, BALLON));
        assertTrue(StringList.contains(items_, GROSSERACINE));
        assertTrue(StringList.contains(items_, REVEIL));
        assertTrue(StringList.contains(items_, PIERRE_GLACE));
        assertTrue(StringList.contains(items_, HERBEBLANCHE));
        assertTrue(StringList.contains(items_, BATON));
        assertTrue(StringList.contains(items_, VIVE_GRIFFE_FALSE));
        assertTrue(StringList.contains(items_, VULNE_ASSURANCE));
        assertTrue(StringList.contains(items_, RESTES));
        assertTrue(StringList.contains(items_, POTION_MAX));
        assertTrue(StringList.contains(items_, MASTER_BALL));
        assertTrue(StringList.contains(items_, PT_DE_MIRE));
        assertTrue(StringList.contains(items_, VIVE_GRIFFE_TRUE));
        assertTrue(StringList.contains(items_, MUSCLE));
        assertTrue(StringList.contains(items_, GRAND_RAPPEL));
        assertTrue(StringList.contains(items_, PAS_DE_BALL));
        assertNull(ex_.getPokemon());
        StringMap<GenderRepartition> pk_ = ex_.getGenderRepartitions();
        assertEq(32, pk_.size());
        assertEq(GenderRepartition.MIXED, pk_.getVal(NUCLEOS));
        assertEq(GenderRepartition.MIXED, pk_.getVal(TARINORME));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(MUNJA));
        assertEq(GenderRepartition.MIXED, pk_.getVal(YANMA));
        assertEq(GenderRepartition.MIXED, pk_.getVal(SYMBIOS));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(CARAPUCE));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(CARABAFFE));
        assertEq(GenderRepartition.MIXED, pk_.getVal(TARINOR));
        assertEq(GenderRepartition.FEMALE, pk_.getVal(LIMAGMA_F));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(PICHU));
        assertEq(GenderRepartition.MIXED, pk_.getVal(BABIMANTA));
        assertEq(GenderRepartition.MIXED, pk_.getVal(TARPAUD));
        assertEq(GenderRepartition.FEMALE, pk_.getVal(CHENISELLE));
        assertEq(GenderRepartition.MALE, pk_.getVal(LIMAGMA_M));
        assertEq(GenderRepartition.MIXED, pk_.getVal(DEMANTA));
        assertEq(GenderRepartition.MIXED, pk_.getVal(TETARTE));
        assertEq(GenderRepartition.MIXED, pk_.getVal(YANMEGA));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(REMORAID));
        assertEq(GenderRepartition.LEGENDARY, pk_.getVal(MEW));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(MELODELFE_2));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(LIMAGMA));
        assertEq(GenderRepartition.MALE, pk_.getVal(PAPILORD));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(MELODELFE));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(PIKACHU));
        assertEq(GenderRepartition.MIXED, pk_.getVal(PTITARD));
        assertEq(GenderRepartition.MIXED, pk_.getVal(CHENITI));
        assertEq(GenderRepartition.LEGENDARY, pk_.getVal(ARTIKODIN));
        assertEq(GenderRepartition.MIXED, pk_.getVal(NINGALE));
        assertEq(GenderRepartition.MIXED, pk_.getVal(MEIOS));
        assertEq(GenderRepartition.MIXED, pk_.getVal(NINJASK));
        assertEq(GenderRepartition.NO_GENDER, pk_.getVal(MELOFEE));
        assertEq(GenderRepartition.MIXED, pk_.getVal(TARTARD));
    }

    @Test
    public void check1Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNotNull(ex_.getPokemon());
    }

    @Test
    public void check2Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, MULTI_EXP);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNotNull(ex_.getPokemon());
    }

    @Test
    public void check3Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PTITARD, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check4Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(NULL_REF, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check5Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, INVALID_DATA_KEY);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check6Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, NULL_REF, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check7Test() {
        ExchangedData ex_ = new ExchangedData(data);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, INVALID_DATA_KEY, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void getTeam1Test() {
        ExchangedData ex_ = new ExchangedData(data);
        CustList<UsablePokemon> list_ = new CustList<UsablePokemon>();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, INVALID_DATA_KEY, Gender.NO_GENDER, NULL_REF);
        list_.add(pk_);
        PokemonPlayer pkTwo_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, MULTI_EXP);
        list_.add(pkTwo_);
        PokemonPlayer pkThree_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, NULL_REF);
        list_.add(pkThree_);
        PokemonPlayer pkFour_ = newPokemonPlayer(PIKACHU, NULL_REF, Gender.NO_GENDER, NULL_REF);
        list_.add(pkFour_);
        ByteTreeMap<PokemonPlayer> res_ = ex_.getTeam(list_);
        assertEq(2, res_.size());
        assertSame(pkTwo_, res_.getVal((byte) 1));
        assertSame(pkThree_, res_.getVal((byte) 2));
    }

    private static PokemonPlayer newPokemonPlayer(String _name, String _ability, Gender _gender, String _item) {
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(_name);
        sent_.setLevel((short) 1);
        sent_.setAbility(_ability);
        sent_.setItem(_item);
        sent_.setGender(_gender);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove((short) 10));
        sent_.setHappiness((short) 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        return sent_;
    }
}
