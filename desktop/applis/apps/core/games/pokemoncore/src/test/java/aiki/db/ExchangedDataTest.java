package aiki.db;

import code.util.core.StringUtil;
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
        assertTrue(StringUtil.contains(abilities_, TOXITOUCHE));
        assertTrue(StringUtil.contains(abilities_, HYPER_CUTTER));
        assertTrue(StringUtil.contains(abilities_, TECHNICIEN));
        assertTrue(StringUtil.contains(abilities_, ANNULE_GARDE));
        assertTrue(StringUtil.contains(abilities_, COEUR_SOIN));
        assertTrue(StringUtil.contains(abilities_, TIRS));
        assertTrue(StringUtil.contains(abilities_, PIED_VELOCE));
        assertTrue(StringUtil.contains(abilities_, REGARD_VIF));
        assertTrue(StringUtil.contains(abilities_, SYNCHRO));
        assertTrue(StringUtil.contains(abilities_, ASYNCHRO));
        assertTrue(StringUtil.contains(abilities_, IMPUDENCE));
        assertTrue(StringUtil.contains(abilities_, IGNIFUGE));
        assertTrue(StringUtil.contains(abilities_, CORPS_SAIN));
        assertTrue(StringUtil.contains(abilities_, GLISSADE));
        assertTrue(StringUtil.contains(abilities_, ATTENTION));
        assertTrue(StringUtil.contains(abilities_, COEUR_NOBLE));
        assertTrue(StringUtil.contains(abilities_, SUINTEMENT));
        assertTrue(StringUtil.contains(abilities_, ARMUROUILLEE));
        assertTrue(StringUtil.contains(abilities_, TERA_VOLTAGE));
        assertTrue(StringUtil.contains(abilities_, GLOUTONNERIE));
        assertTrue(StringUtil.contains(abilities_, TANT_PIS));
        assertTrue(StringUtil.contains(abilities_, DEGUISEMENT));
        assertTrue(StringUtil.contains(abilities_, ARMUMAGMA));
        assertTrue(StringUtil.contains(abilities_, SECHERESSE));
        assertTrue(StringUtil.contains(abilities_, AIR_LOCK));
        assertTrue(StringUtil.contains(abilities_, SYMBIOSE));
        assertTrue(StringUtil.contains(abilities_, COLERIQUE));
        assertTrue(StringUtil.contains(abilities_, LEVITATION));
        assertTrue(StringUtil.contains(abilities_, FERMETE));
        assertTrue(StringUtil.contains(abilities_, CONTRE));
        assertTrue(StringUtil.contains(abilities_, FOUR));
        assertTrue(StringUtil.contains(abilities_, PEAU_MIRACLE_QUATER));
        assertTrue(StringUtil.contains(abilities_, SOLIDE_ROC));
        assertTrue(StringUtil.contains(abilities_, POISSEUX));
        assertTrue(StringUtil.contains(abilities_, GARDE_MYSTIK));
        assertTrue(StringUtil.contains(abilities_, STATIK));
        assertTrue(StringUtil.contains(abilities_, TETE_DE_ROC));
        assertTrue(StringUtil.contains(abilities_, MULTITYPE));
        assertTrue(StringUtil.contains(abilities_, CRAN));
        assertTrue(StringUtil.contains(abilities_, ABRI_CAPE));
        assertTrue(StringUtil.contains(abilities_, PIED_RAPIDE));
        assertTrue(StringUtil.contains(abilities_, ARMURBASTON));
        assertTrue(StringUtil.contains(abilities_, NORMALISE));
        assertTrue(StringUtil.contains(abilities_, MAGNEPIEGE));
        assertTrue(StringUtil.contains(abilities_, MUE));
        assertTrue(StringUtil.contains(abilities_, PARATONNERRE));
        assertTrue(StringUtil.contains(abilities_, TELECHARGE));
        assertTrue(StringUtil.contains(abilities_, PEAU_DURE));
        assertTrue(StringUtil.contains(abilities_, PEAU_MIRACLE_TER));
        assertTrue(StringUtil.contains(abilities_, ALEA_STAT));
        assertTrue(StringUtil.contains(abilities_, REGE_FORCE));
        assertTrue(StringUtil.contains(abilities_, AROMA_VOILE));
        assertTrue(StringUtil.contains(abilities_, SANS_LIMITE));
        assertTrue(StringUtil.contains(abilities_, METEO));
        assertTrue(StringUtil.contains(abilities_, ACHARNE));
        assertTrue(StringUtil.contains(abilities_, SIMPLE));
        assertTrue(StringUtil.contains(abilities_, JOLI_SOURIRE));
        assertTrue(StringUtil.contains(abilities_, PEAU_FEERIQUE));
        assertTrue(StringUtil.contains(abilities_, TELEPATHE));
        assertTrue(StringUtil.contains(abilities_, MAUVAIS_REVE));
        assertTrue(StringUtil.contains(abilities_, CRACHIN));
        assertTrue(StringUtil.contains(abilities_, FREIN));
        assertTrue(StringUtil.contains(abilities_, BOOM_FINAL));
        assertTrue(StringUtil.contains(abilities_, MEDIC_NATURE));
        assertTrue(StringUtil.contains(abilities_, INFILTRATION));
        assertTrue(StringUtil.contains(abilities_, PEAU_MIRACLE_BIS));
        assertTrue(StringUtil.contains(abilities_, GARDE_AMIE));
        assertTrue(StringUtil.contains(abilities_, QUERELLEUR));
        assertTrue(StringUtil.contains(abilities_, OEIL_COMPOSE));
        assertTrue(StringUtil.contains(abilities_, ECRAN_POUDRE));
        assertTrue(StringUtil.contains(abilities_, PYROMANE));
        assertTrue(StringUtil.contains(abilities_, CHANCEUX));
        assertTrue(StringUtil.contains(abilities_, SERENITE));
        assertTrue(StringUtil.contains(abilities_, ABSORB_VOLT));
        assertTrue(StringUtil.contains(abilities_, CALQUE));
        assertTrue(StringUtil.contains(abilities_, ENVELOCAPE));
        assertTrue(StringUtil.contains(abilities_, MULTI_COUPS));
        assertTrue(StringUtil.contains(abilities_, GARDE));
        assertTrue(StringUtil.contains(abilities_, ESSAIM));
        assertTrue(StringUtil.contains(abilities_, ABSORB_EAU));
        assertTrue(StringUtil.contains(abilities_, GARDE_POUR_SOI));
        assertTrue(StringUtil.contains(abilities_, INCONSCIENT));
        assertTrue(StringUtil.contains(abilities_, FEUILLE_PETITE));
        assertTrue(StringUtil.contains(abilities_, GARDE_MAGIK));
        assertTrue(StringUtil.contains(abilities_, AURA_TENEBREUSE));
        assertTrue(StringUtil.contains(abilities_, FLORA_VOILE));
        assertTrue(StringUtil.contains(abilities_, AURA_INVERSEE));
        assertTrue(StringUtil.contains(abilities_, CONTRAIRE));
        assertTrue(StringUtil.contains(abilities_, SNIPER));
        assertTrue(StringUtil.contains(abilities_, MOITEUR));
        assertTrue(StringUtil.contains(abilities_, MAGICIEN));
        assertTrue(StringUtil.contains(abilities_, TURBO));
        assertTrue(StringUtil.contains(abilities_, BAJOUES));
        assertTrue(StringUtil.contains(abilities_, MATINAL));
        assertTrue(StringUtil.contains(abilities_, FEUILLE_GARDE));
        assertTrue(StringUtil.contains(abilities_, ANTI_BRUIT));
        assertTrue(StringUtil.contains(abilities_, PRESSION));
        assertTrue(StringUtil.contains(abilities_, AILES_BOURRASQUE));
        assertTrue(StringUtil.contains(abilities_, IMPASSIBLE));
        assertTrue(StringUtil.contains(abilities_, LENTITEINTEE));
        assertTrue(StringUtil.contains(abilities_, ADAPTABILITE));
        assertTrue(StringUtil.contains(abilities_, PROTEEN));
        assertTrue(StringUtil.contains(abilities_, POISSON));
        assertTrue(StringUtil.contains(abilities_, TENSION));
        assertTrue(StringUtil.contains(abilities_, FARCEUR));
        assertTrue(StringUtil.contains(abilities_, PEAU_MIRACLE));
        assertTrue(StringUtil.contains(abilities_, TERA_VOLT));
        StringList items_ = ex_.getItems();
        assertEq(100, items_.size());
        assertTrue(StringUtil.contains(items_, RAPPEL));
        assertTrue(StringUtil.contains(items_, BAIE_PITAYE));
        assertTrue(StringUtil.contains(items_, ROCHE_LISSE));
        assertTrue(StringUtil.contains(items_, BAIE_LAMPOU));
        assertTrue(StringUtil.contains(items_, POUDRE_VITE));
        assertTrue(StringUtil.contains(items_, BAIE_CERIZ));
        assertTrue(StringUtil.contains(items_, HAPPY_POTION));
        assertTrue(StringUtil.contains(items_, HYPER_BALL));
        assertTrue(StringUtil.contains(items_, CARTE_ROUGE));
        assertTrue(StringUtil.contains(items_, GRAIN_MIRACL));
        assertTrue(StringUtil.contains(items_, CEINTURE_PRO));
        assertTrue(StringUtil.contains(items_, EAU_FRAICHE));
        assertTrue(StringUtil.contains(items_, BLACK_BERRY));
        assertTrue(StringUtil.contains(items_, BOUE_NOIRE));
        assertTrue(StringUtil.contains(items_, BAIE_GOWAV));
        assertTrue(StringUtil.contains(items_, ENCENS_VAGUE));
        assertTrue(StringUtil.contains(items_, CEINT_FORCE));
        assertTrue(StringUtil.contains(items_, BOLT));
        assertTrue(StringUtil.contains(items_, PIERRE_STASE));
        assertTrue(StringUtil.contains(items_, PLAQUE_DRACO));
        assertTrue(StringUtil.contains(items_, PIERRALLEGEE));
        assertTrue(StringUtil.contains(items_, PIECE_RUNE));
        assertTrue(StringUtil.contains(items_, VIVE_GRIFFE));
        assertTrue(StringUtil.contains(items_, LAVA));
        assertTrue(StringUtil.contains(items_, BANDEAU_ETREINTE));
        assertTrue(StringUtil.contains(items_, MAX_ELIXIR));
        assertTrue(StringUtil.contains(items_, POKE_BALL));
        assertTrue(StringUtil.contains(items_, PIERRE_LUNE));
        assertTrue(StringUtil.contains(items_, OEUF_CHANCE));
        assertTrue(StringUtil.contains(items_, BAIE_JABOCA));
        assertTrue(StringUtil.contains(items_, CABLE));
        assertTrue(StringUtil.contains(items_, LENTILSCOPE));
        assertTrue(StringUtil.contains(items_, BAIE_ENIGMA));
        assertTrue(StringUtil.contains(items_, NOEUD_DESTIN));
        assertTrue(StringUtil.contains(items_, PETIT_RAPPEL));
        assertTrue(StringUtil.contains(items_, LUMARGILE));
        assertTrue(StringUtil.contains(items_, BAIE_CHERIM));
        assertTrue(StringUtil.contains(items_, CENDRESACREE));
        assertTrue(StringUtil.contains(items_, PIERRE_SOLEIL));
        assertTrue(StringUtil.contains(items_, PIERRE_EAU));
        assertTrue(StringUtil.contains(items_, HUILE));
        assertTrue(StringUtil.contains(items_, GRELOT));
        assertTrue(StringUtil.contains(items_, BANDEAU));
        assertTrue(StringUtil.contains(items_, BRAC_MACHO));
        assertTrue(StringUtil.contains(items_, ROCHE_ROYALE));
        assertTrue(StringUtil.contains(items_, GRELOT_ZEN));
        assertTrue(StringUtil.contains(items_, ORBE_FLAMME));
        assertTrue(StringUtil.contains(items_, ACCRO_GRIFFE));
        assertTrue(StringUtil.contains(items_, PEPITE));
        assertTrue(StringUtil.contains(items_, LICHEN_LUMINEUX));
        assertTrue(StringUtil.contains(items_, TOTAL_SOIN));
        assertTrue(StringUtil.contains(items_, ENCENS_PUR));
        assertTrue(StringUtil.contains(items_, ORBE_VIE));
        assertTrue(StringUtil.contains(items_, PIQUANTS));
        assertTrue(StringUtil.contains(items_, MAX_REPOUSSE));
        assertTrue(StringUtil.contains(items_, HERBE_MENTAL));
        assertTrue(StringUtil.contains(items_, SUPER_BALL));
        assertTrue(StringUtil.contains(items_, BAIE_ORAN));
        assertTrue(StringUtil.contains(items_, BAIE_MEPO));
        assertTrue(StringUtil.contains(items_, PP_PLUS_BIS));
        assertTrue(StringUtil.contains(items_, VIVE_GRIFFE_TRUE_FALSE));
        assertTrue(StringUtil.contains(items_, PP_PLUS));
        assertTrue(StringUtil.contains(items_, ENCENS_PLEIN));
        assertTrue(StringUtil.contains(items_, POTION));
        assertTrue(StringUtil.contains(items_, VIEIL_AMBRE));
        assertTrue(StringUtil.contains(items_, BAIE_MICLE));
        assertTrue(StringUtil.contains(items_, ELIXIR));
        assertTrue(StringUtil.contains(items_, REPOUSSE));
        assertTrue(StringUtil.contains(items_, MULTI_EXP));
        assertTrue(StringUtil.contains(items_, RASP_BERRY));
        assertTrue(StringUtil.contains(items_, BOUTON_FUITE));
        assertTrue(StringUtil.contains(items_, PV_PLUS));
        assertTrue(StringUtil.contains(items_, HUILE_MAX));
        assertTrue(StringUtil.contains(items_, POUDRE_ATTAQUE));
        assertTrue(StringUtil.contains(items_, GRELOT_COQUE));
        assertTrue(StringUtil.contains(items_, HERBE_POUV));
        assertTrue(StringUtil.contains(items_, BAIE_LANSAT));
        assertTrue(StringUtil.contains(items_, BOUE_BLANCHE));
        assertTrue(StringUtil.contains(items_, LUNETTES_FILTRE));
        assertTrue(StringUtil.contains(items_, LUXE_BALL));
        assertTrue(StringUtil.contains(items_, CEINT_POUV));
        assertTrue(StringUtil.contains(items_, BAIE_MANGA));
        assertTrue(StringUtil.contains(items_, METRONOME_OBJ));
        assertTrue(StringUtil.contains(items_, MAGNET));
        assertTrue(StringUtil.contains(items_, BALLON));
        assertTrue(StringUtil.contains(items_, GROSSERACINE));
        assertTrue(StringUtil.contains(items_, REVEIL));
        assertTrue(StringUtil.contains(items_, PIERRE_GLACE));
        assertTrue(StringUtil.contains(items_, HERBEBLANCHE));
        assertTrue(StringUtil.contains(items_, BATON));
        assertTrue(StringUtil.contains(items_, VIVE_GRIFFE_FALSE));
        assertTrue(StringUtil.contains(items_, VULNE_ASSURANCE));
        assertTrue(StringUtil.contains(items_, RESTES));
        assertTrue(StringUtil.contains(items_, POTION_MAX));
        assertTrue(StringUtil.contains(items_, MASTER_BALL));
        assertTrue(StringUtil.contains(items_, PT_DE_MIRE));
        assertTrue(StringUtil.contains(items_, VIVE_GRIFFE_TRUE));
        assertTrue(StringUtil.contains(items_, MUSCLE));
        assertTrue(StringUtil.contains(items_, GRAND_RAPPEL));
        assertTrue(StringUtil.contains(items_, PAS_DE_BALL));
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
