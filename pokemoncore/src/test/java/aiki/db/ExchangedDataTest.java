package aiki.db;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ExchangedDataTest extends InitializationDataBase {

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
        ExchangedData ex_ = new ExchangedData(_data_);
        StringList abilities_ = ex_.getAbilities();
        assertEq(106, abilities_.size());
        assertTrue(abilities_.containsObj(TOXITOUCHE));
        assertTrue(abilities_.containsObj(HYPER_CUTTER));
        assertTrue(abilities_.containsObj(TECHNICIEN));
        assertTrue(abilities_.containsObj(ANNULE_GARDE));
        assertTrue(abilities_.containsObj(COEUR_SOIN));
        assertTrue(abilities_.containsObj(TIRS));
        assertTrue(abilities_.containsObj(PIED_VELOCE));
        assertTrue(abilities_.containsObj(REGARD_VIF));
        assertTrue(abilities_.containsObj(SYNCHRO));
        assertTrue(abilities_.containsObj(IMPUDENCE));
        assertTrue(abilities_.containsObj(IGNIFUGE));
        assertTrue(abilities_.containsObj(CORPS_SAIN));
        assertTrue(abilities_.containsObj(GLISSADE));
        assertTrue(abilities_.containsObj(ATTENTION));
        assertTrue(abilities_.containsObj(COEUR_NOBLE));
        assertTrue(abilities_.containsObj(SUINTEMENT));
        assertTrue(abilities_.containsObj(ARMUROUILLEE));
        assertTrue(abilities_.containsObj(TERA_VOLTAGE));
        assertTrue(abilities_.containsObj(GLOUTONNERIE));
        assertTrue(abilities_.containsObj(TANT_PIS));
        assertTrue(abilities_.containsObj(DEGUISEMENT));
        assertTrue(abilities_.containsObj(ARMUMAGMA));
        assertTrue(abilities_.containsObj(SECHERESSE));
        assertTrue(abilities_.containsObj(AIR_LOCK));
        assertTrue(abilities_.containsObj(SYMBIOSE));
        assertTrue(abilities_.containsObj(COLERIQUE));
        assertTrue(abilities_.containsObj(LEVITATION));
        assertTrue(abilities_.containsObj(FERMETE));
        assertTrue(abilities_.containsObj(CONTRE));
        assertTrue(abilities_.containsObj(FOUR));
        assertTrue(abilities_.containsObj(PEAU_MIRACLE_QUATER));
        assertTrue(abilities_.containsObj(SOLIDE_ROC));
        assertTrue(abilities_.containsObj(POISSEUX));
        assertTrue(abilities_.containsObj(GARDE_MYSTIK));
        assertTrue(abilities_.containsObj(STATIK));
        assertTrue(abilities_.containsObj(TETE_DE_ROC));
        assertTrue(abilities_.containsObj(MULTITYPE));
        assertTrue(abilities_.containsObj(CRAN));
        assertTrue(abilities_.containsObj(ABRI_CAPE));
        assertTrue(abilities_.containsObj(PIED_RAPIDE));
        assertTrue(abilities_.containsObj(ARMURBASTON));
        assertTrue(abilities_.containsObj(NORMALISE));
        assertTrue(abilities_.containsObj(MAGNEPIEGE));
        assertTrue(abilities_.containsObj(MUE));
        assertTrue(abilities_.containsObj(PARATONNERRE));
        assertTrue(abilities_.containsObj(TELECHARGE));
        assertTrue(abilities_.containsObj(PEAU_DURE));
        assertTrue(abilities_.containsObj(PEAU_MIRACLE_TER));
        assertTrue(abilities_.containsObj(ALEA_STAT));
        assertTrue(abilities_.containsObj(REGE_FORCE));
        assertTrue(abilities_.containsObj(AROMA_VOILE));
        assertTrue(abilities_.containsObj(SANS_LIMITE));
        assertTrue(abilities_.containsObj(METEO));
        assertTrue(abilities_.containsObj(ACHARNE));
        assertTrue(abilities_.containsObj(SIMPLE));
        assertTrue(abilities_.containsObj(JOLI_SOURIRE));
        assertTrue(abilities_.containsObj(PEAU_FEERIQUE));
        assertTrue(abilities_.containsObj(TELEPATHE));
        assertTrue(abilities_.containsObj(MAUVAIS_REVE));
        assertTrue(abilities_.containsObj(CRACHIN));
        assertTrue(abilities_.containsObj(FREIN));
        assertTrue(abilities_.containsObj(BOOM_FINAL));
        assertTrue(abilities_.containsObj(MEDIC_NATURE));
        assertTrue(abilities_.containsObj(INFILTRATION));
        assertTrue(abilities_.containsObj(PEAU_MIRACLE_BIS));
        assertTrue(abilities_.containsObj(GARDE_AMIE));
        assertTrue(abilities_.containsObj(QUERELLEUR));
        assertTrue(abilities_.containsObj(OEIL_COMPOSE));
        assertTrue(abilities_.containsObj(ECRAN_POUDRE));
        assertTrue(abilities_.containsObj(PYROMANE));
        assertTrue(abilities_.containsObj(CHANCEUX));
        assertTrue(abilities_.containsObj(SERENITE));
        assertTrue(abilities_.containsObj(ABSORB_VOLT));
        assertTrue(abilities_.containsObj(CALQUE));
        assertTrue(abilities_.containsObj(ENVELOCAPE));
        assertTrue(abilities_.containsObj(MULTI_COUPS));
        assertTrue(abilities_.containsObj(GARDE));
        assertTrue(abilities_.containsObj(ESSAIM));
        assertTrue(abilities_.containsObj(ABSORB_EAU));
        assertTrue(abilities_.containsObj(GARDE_POUR_SOI));
        assertTrue(abilities_.containsObj(INCONSCIENT));
        assertTrue(abilities_.containsObj(FEUILLE_PETITE));
        assertTrue(abilities_.containsObj(GARDE_MAGIK));
        assertTrue(abilities_.containsObj(AURA_TENEBREUSE));
        assertTrue(abilities_.containsObj(FLORA_VOILE));
        assertTrue(abilities_.containsObj(AURA_INVERSEE));
        assertTrue(abilities_.containsObj(CONTRAIRE));
        assertTrue(abilities_.containsObj(SNIPER));
        assertTrue(abilities_.containsObj(MOITEUR));
        assertTrue(abilities_.containsObj(MAGICIEN));
        assertTrue(abilities_.containsObj(TURBO));
        assertTrue(abilities_.containsObj(BAJOUES));
        assertTrue(abilities_.containsObj(MATINAL));
        assertTrue(abilities_.containsObj(FEUILLE_GARDE));
        assertTrue(abilities_.containsObj(ANTI_BRUIT));
        assertTrue(abilities_.containsObj(PRESSION));
        assertTrue(abilities_.containsObj(AILES_BOURRASQUE));
        assertTrue(abilities_.containsObj(IMPASSIBLE));
        assertTrue(abilities_.containsObj(LENTITEINTEE));
        assertTrue(abilities_.containsObj(ADAPTABILITE));
        assertTrue(abilities_.containsObj(PROTEEN));
        assertTrue(abilities_.containsObj(POISSON));
        assertTrue(abilities_.containsObj(TENSION));
        assertTrue(abilities_.containsObj(FARCEUR));
        assertTrue(abilities_.containsObj(PEAU_MIRACLE));
        assertTrue(abilities_.containsObj(TERA_VOLT));
        StringList items_ = ex_.getItems();
        assertEq(100, items_.size());
        assertTrue(items_.containsObj(RAPPEL));
        assertTrue(items_.containsObj(BAIE_PITAYE));
        assertTrue(items_.containsObj(ROCHE_LISSE));
        assertTrue(items_.containsObj(BAIE_LAMPOU));
        assertTrue(items_.containsObj(POUDRE_VITE));
        assertTrue(items_.containsObj(BAIE_CERIZ));
        assertTrue(items_.containsObj(HAPPY_POTION));
        assertTrue(items_.containsObj(HYPER_BALL));
        assertTrue(items_.containsObj(CARTE_ROUGE));
        assertTrue(items_.containsObj(GRAIN_MIRACL));
        assertTrue(items_.containsObj(CEINTURE_PRO));
        assertTrue(items_.containsObj(EAU_FRAICHE));
        assertTrue(items_.containsObj(BLACK_BERRY));
        assertTrue(items_.containsObj(BOUE_NOIRE));
        assertTrue(items_.containsObj(BAIE_GOWAV));
        assertTrue(items_.containsObj(ENCENS_VAGUE));
        assertTrue(items_.containsObj(CEINT_FORCE));
        assertTrue(items_.containsObj(BOLT));
        assertTrue(items_.containsObj(PIERRE_STASE));
        assertTrue(items_.containsObj(PLAQUE_DRACO));
        assertTrue(items_.containsObj(PIERRALLEGEE));
        assertTrue(items_.containsObj(PIECE_RUNE));
        assertTrue(items_.containsObj(VIVE_GRIFFE));
        assertTrue(items_.containsObj(LAVA));
        assertTrue(items_.containsObj(BANDEAU_ETREINTE));
        assertTrue(items_.containsObj(MAX_ELIXIR));
        assertTrue(items_.containsObj(POKE_BALL));
        assertTrue(items_.containsObj(PIERRE_LUNE));
        assertTrue(items_.containsObj(OEUF_CHANCE));
        assertTrue(items_.containsObj(BAIE_JABOCA));
        assertTrue(items_.containsObj(CABLE));
        assertTrue(items_.containsObj(LENTILSCOPE));
        assertTrue(items_.containsObj(BAIE_ENIGMA));
        assertTrue(items_.containsObj(NOEUD_DESTIN));
        assertTrue(items_.containsObj(PETIT_RAPPEL));
        assertTrue(items_.containsObj(LUMARGILE));
        assertTrue(items_.containsObj(BAIE_CHERIM));
        assertTrue(items_.containsObj(CENDRESACREE));
        assertTrue(items_.containsObj(PIERRE_SOLEIL));
        assertTrue(items_.containsObj(PIERRE_EAU));
        assertTrue(items_.containsObj(HUILE));
        assertTrue(items_.containsObj(GRELOT));
        assertTrue(items_.containsObj(BANDEAU));
        assertTrue(items_.containsObj(BRAC_MACHO));
        assertTrue(items_.containsObj(ROCHE_ROYALE));
        assertTrue(items_.containsObj(GRELOT_ZEN));
        assertTrue(items_.containsObj(ORBE_FLAMME));
        assertTrue(items_.containsObj(ACCRO_GRIFFE));
        assertTrue(items_.containsObj(PEPITE));
        assertTrue(items_.containsObj(LICHEN_LUMINEUX));
        assertTrue(items_.containsObj(TOTAL_SOIN));
        assertTrue(items_.containsObj(ENCENS_PUR));
        assertTrue(items_.containsObj(ORBE_VIE));
        assertTrue(items_.containsObj(PIQUANTS));
        assertTrue(items_.containsObj(MAX_REPOUSSE));
        assertTrue(items_.containsObj(HERBE_MENTAL));
        assertTrue(items_.containsObj(SUPER_BALL));
        assertTrue(items_.containsObj(BAIE_ORAN));
        assertTrue(items_.containsObj(BAIE_MEPO));
        assertTrue(items_.containsObj(PP_PLUS_BIS));
        assertTrue(items_.containsObj(VIVE_GRIFFE_TRUE_FALSE));
        assertTrue(items_.containsObj(PP_PLUS));
        assertTrue(items_.containsObj(ENCENS_PLEIN));
        assertTrue(items_.containsObj(POTION));
        assertTrue(items_.containsObj(VIEIL_AMBRE));
        assertTrue(items_.containsObj(BAIE_MICLE));
        assertTrue(items_.containsObj(ELIXIR));
        assertTrue(items_.containsObj(REPOUSSE));
        assertTrue(items_.containsObj(MULTI_EXP));
        assertTrue(items_.containsObj(RASP_BERRY));
        assertTrue(items_.containsObj(BOUTON_FUITE));
        assertTrue(items_.containsObj(PV_PLUS));
        assertTrue(items_.containsObj(HUILE_MAX));
        assertTrue(items_.containsObj(POUDRE_ATTAQUE));
        assertTrue(items_.containsObj(GRELOT_COQUE));
        assertTrue(items_.containsObj(HERBE_POUV));
        assertTrue(items_.containsObj(BAIE_LANSAT));
        assertTrue(items_.containsObj(BOUE_BLANCHE));
        assertTrue(items_.containsObj(LUNETTES_FILTRE));
        assertTrue(items_.containsObj(LUXE_BALL));
        assertTrue(items_.containsObj(CEINT_POUV));
        assertTrue(items_.containsObj(BAIE_MANGA));
        assertTrue(items_.containsObj(METRONOME_OBJ));
        assertTrue(items_.containsObj(MAGNET));
        assertTrue(items_.containsObj(BALLON));
        assertTrue(items_.containsObj(GROSSERACINE));
        assertTrue(items_.containsObj(REVEIL));
        assertTrue(items_.containsObj(PIERRE_GLACE));
        assertTrue(items_.containsObj(HERBEBLANCHE));
        assertTrue(items_.containsObj(BATON));
        assertTrue(items_.containsObj(VIVE_GRIFFE_FALSE));
        assertTrue(items_.containsObj(VULNE_ASSURANCE));
        assertTrue(items_.containsObj(RESTES));
        assertTrue(items_.containsObj(POTION_MAX));
        assertTrue(items_.containsObj(MASTER_BALL));
        assertTrue(items_.containsObj(PT_DE_MIRE));
        assertTrue(items_.containsObj(VIVE_GRIFFE_TRUE));
        assertTrue(items_.containsObj(MUSCLE));
        assertTrue(items_.containsObj(GRAND_RAPPEL));
        assertTrue(items_.containsObj(PAS_DE_BALL));
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
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNotNull(ex_.getPokemon());
    }

    @Test
    public void check2Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, MULTI_EXP);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNotNull(ex_.getPokemon());
    }

    @Test
    public void check3Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PTITARD, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check4Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(NULL_REF, STATIK, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check5Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, INVALID_DATA_KEY);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check6Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, NULL_REF, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void check7Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, INVALID_DATA_KEY, Gender.NO_GENDER, NULL_REF);
        ex_.setPokemon(pk_);
        ex_.check();
        assertNull(ex_.getPokemon());
    }

    @Test
    public void getTeam1Test() {
        ExchangedData ex_ = new ExchangedData(_data_);
        CustList<UsablePokemon> list_ = new CustList<UsablePokemon>();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, INVALID_DATA_KEY, Gender.NO_GENDER, NULL_REF);
        list_.add(pk_);
        PokemonPlayer pkTwo_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, MULTI_EXP);
        list_.add(pkTwo_);
        PokemonPlayer pkThree_ = newPokemonPlayer(PIKACHU, STATIK, Gender.NO_GENDER, NULL_REF);
        list_.add(pkThree_);
        PokemonPlayer pkFour_ = newPokemonPlayer(PIKACHU, NULL_REF, Gender.NO_GENDER, NULL_REF);
        list_.add(pkFour_);
        NatTreeMap<Byte,PokemonPlayer> res_ = ex_.getTeam(list_);
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
