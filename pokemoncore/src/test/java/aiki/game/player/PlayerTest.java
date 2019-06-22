package aiki.game.player;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.db.ExchangedData;
import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class PlayerTest extends InitializationDataBase {

    private static final String SAMPLE_NICKNAME = "TOTO";
    private static final String TRUMP_BOY = "TRUMP_BOY";
    private static final String TRUMP_GIRL = "TRUMP_GIRL";

    @Test
    public void initTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player();
        player_.setSex(Sex.GIRL);
        player_.setNickname(NICKNAME);
        player_.setBox(new CustList<UsablePokemon>());
        player_.setTeam(new CustList<UsablePokemon>());
        player_.setCaughtPk(new StringMap<Boolean>());
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setAbility(GARDE_MAGIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setItem(NULL_REF);
        pkTwo_.setLevel((short) 2);
        pkTwo_.setName(NUCLEOS);
        player_.initTeam(player_.getSex(), diff_, pkTwo_, _data_);
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(NUCLEOS, pk_.getName());
        assertEq(2, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(VAGUE_PSY));
        assertEq(GARDE_MAGIK,pk_.getAbility());
        assertEq(Gender.FEMALE,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("721/50"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
    }

    @Test
    public void initTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player();
        player_.setSex(Sex.BOY);
        player_.setNickname(NICKNAME);
        player_.setBox(new CustList<UsablePokemon>());
        player_.setTeam(new CustList<UsablePokemon>());
        player_.setCaughtPk(new StringMap<Boolean>());
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setAbility(GARDE_MAGIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setItem(NULL_REF);
        pkTwo_.setLevel((short) 2);
        pkTwo_.setName(NUCLEOS);
        player_.initTeam(player_.getSex(), diff_, pkTwo_, _data_);
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(NUCLEOS, pk_.getName());
        assertEq(2, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(VAGUE_PSY));
        assertEq(GARDE_MAGIK,pk_.getAbility());
        assertEq(Gender.MALE,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("721/50"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
    }

    @Test
    public void initTeam3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player();
        player_.setSex(Sex.GIRL);
        player_.setNickname(NICKNAME);
        player_.setBox(new CustList<UsablePokemon>());
        player_.setTeam(new CustList<UsablePokemon>());
        player_.setCaughtPk(new StringMap<Boolean>());
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setAbility(GARDE_MAGIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setItem(NULL_REF);
        pkTwo_.setLevel((short) 2);
        pkTwo_.setName(PIKACHU);
        player_.initTeam(player_.getSex(), diff_, pkTwo_, _data_);
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(2, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertEq(GARDE_MAGIK,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("791/50"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
    }

    @Test
    public void initTeam4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player();
        player_.setSex(Sex.BOY);
        player_.setNickname(NICKNAME);
        player_.setBox(new CustList<UsablePokemon>());
        player_.setTeam(new CustList<UsablePokemon>());
        player_.setCaughtPk(new StringMap<Boolean>());
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setAbility(GARDE_MAGIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setItem(NULL_REF);
        pkTwo_.setLevel((short) 2);
        pkTwo_.setName(PIKACHU);
        player_.initTeam(player_.getSex(), diff_, pkTwo_, _data_);
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(2, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertEq(GARDE_MAGIK,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("791/50"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, false, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getCaughtPk().size());
        assertEq(0, player_.getEggsList().size());
        assertEq(0, player_.getPokemonPlayerList().size());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, false, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getCaughtPk().size());
        assertEq(0, player_.getEggsList().size());
        assertEq(0, player_.getPokemonPlayerList().size());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NULL_REF, Sex.GIRL, diff_, true, _data_);
        assertEq(TRUMP_GIRL, player_.getNickname());
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void new_Player_String_Sex_Difficulty_boolean_DataBase_6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NULL_REF, Sex.BOY, diff_, true, _data_);
        assertEq(TRUMP_BOY, player_.getNickname());
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(index_);
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(0, player_.getBox().size());
        assertEq(new LgInt("3000"), player_.getMoney());
        assertEq(100, player_.getInventory().getItemsKeys().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ACCRO_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_CHERIM));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_JABOCA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LAMPOU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_LANSAT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MANGA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MEPO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_MICLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BAIE_PITAYE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BALLON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BANDEAU_ETREINTE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BATON));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BLACK_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOLT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_BLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUE_NOIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BOUTON_FUITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(BRAC_MACHO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CABLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CARTE_ROUGE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINTURE_PRO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_FORCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CEINT_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(EAU_FRAICHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PLEIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_PUR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ENCENS_VAGUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAIN_MIRACL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRAND_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_COQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT_ZEN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GROSSERACINE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBEBLANCHE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_MENTAL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HERBE_POUV));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HYPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LAVA));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LENTILSCOPE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LICHEN_LUMINEUX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUMARGILE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUNETTES_FILTRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(LUXE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAGNET));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MASTER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MAX_REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(METRONOME_OBJ));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(NOEUD_DESTIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(OEUF_CHANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_FLAMME));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ORBE_VIE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PEPITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PETIT_RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIECE_RUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRALLEGEE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_EAU));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIERRE_STASE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PIQUANTS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PLAQUE_DRACO));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POTION_MAX));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_ATTAQUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(POUDRE_VITE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PT_DE_MIRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PV_PLUS));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RAPPEL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(RESTES));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REVEIL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_LISSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(ROCHE_ROYALE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(SUPER_BALL));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(TOTAL_SOIN));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIEIL_AMBRE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VIVE_GRIFFE_TRUE_FALSE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(VULNE_ASSURANCE));
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PAS_DE_BALL));
    }

    @Test
    public void initIv1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        Pokemon pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 7);
        pk_.setItem(NULL_REF);
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        egg_ = new Egg(PIKACHU);
        player_.getBox().add(egg_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        pkPlayer_.getIv().clear();
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        pkPlayer_.getIv().clear();
        player_.initIv(diff_, _data_);
        pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void estAttrape1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, _data_);
//        assertEq(63,player_.getCaughtPk().size());
        assertEq(32,player_.getCaughtPk().size());
        assertTrue(!player_.estAttrape(ARTIKODIN));
        assertTrue(!player_.estAttrape(BABIMANTA));
        assertTrue(!player_.estAttrape(CARABAFFE));
        assertTrue(!player_.estAttrape(CARAPUCE));
        assertTrue(!player_.estAttrape(CHENISELLE));
        assertTrue(!player_.estAttrape(CHENITI));
        assertTrue(!player_.estAttrape(DEMANTA));
        assertTrue(!player_.estAttrape(LIMAGMA));
        assertTrue(!player_.estAttrape(LIMAGMA_F));
        assertTrue(!player_.estAttrape(LIMAGMA_M));
        assertTrue(!player_.estAttrape(MEIOS));
        assertTrue(!player_.estAttrape(MELODELFE));
        assertTrue(!player_.estAttrape(MELODELFE_2));
        assertTrue(!player_.estAttrape(MELOFEE));
        assertTrue(!player_.estAttrape(MEW));
        assertTrue(!player_.estAttrape(MUNJA));
        assertTrue(!player_.estAttrape(NINGALE));
        assertTrue(!player_.estAttrape(NINJASK));
        assertTrue(!player_.estAttrape(NUCLEOS));
        assertTrue(!player_.estAttrape(PAPILORD));
        assertTrue(!player_.estAttrape(PICHU));
        assertTrue(player_.estAttrape(PIKACHU));
        assertTrue(!player_.estAttrape(PTITARD));
        assertTrue(!player_.estAttrape(REMORAID));
        assertTrue(!player_.estAttrape(SYMBIOS));
        assertTrue(!player_.estAttrape(TARINOR));
        assertTrue(!player_.estAttrape(TARINORME));
        assertTrue(!player_.estAttrape(TARPAUD));
        assertTrue(!player_.estAttrape(TARTARD));
        assertTrue(!player_.estAttrape(TETARTE));
        assertTrue(!player_.estAttrape(YANMA));
        assertTrue(!player_.estAttrape(YANMEGA));
    }

    @Test
    public void estAttrape2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, false, _data_);
        assertEq(0, player_.getCaughtPk().size());
        assertTrue(!player_.estAttrape(ARTIKODIN));
        assertTrue(!player_.estAttrape(BABIMANTA));
        assertTrue(!player_.estAttrape(CARABAFFE));
        assertTrue(!player_.estAttrape(CARAPUCE));
        assertTrue(!player_.estAttrape(CHENISELLE));
        assertTrue(!player_.estAttrape(CHENITI));
        assertTrue(!player_.estAttrape(DEMANTA));
        assertTrue(!player_.estAttrape(LIMAGMA));
        assertTrue(!player_.estAttrape(LIMAGMA_F));
        assertTrue(!player_.estAttrape(LIMAGMA_M));
        assertTrue(!player_.estAttrape(MEIOS));
        assertTrue(!player_.estAttrape(MELODELFE));
        assertTrue(!player_.estAttrape(MELODELFE_2));
        assertTrue(!player_.estAttrape(MELOFEE));
        assertTrue(!player_.estAttrape(MEW));
        assertTrue(!player_.estAttrape(MUNJA));
        assertTrue(!player_.estAttrape(NINGALE));
        assertTrue(!player_.estAttrape(NINJASK));
        assertTrue(!player_.estAttrape(NUCLEOS));
        assertTrue(!player_.estAttrape(PAPILORD));
        assertTrue(!player_.estAttrape(PICHU));
        assertTrue(!player_.estAttrape(PIKACHU));
        assertTrue(!player_.estAttrape(PTITARD));
        assertTrue(!player_.estAttrape(SYMBIOS));
        assertTrue(!player_.estAttrape(TARINOR));
        assertTrue(!player_.estAttrape(TARINORME));
        assertTrue(!player_.estAttrape(TARPAUD));
        assertTrue(!player_.estAttrape(TARTARD));
        assertTrue(!player_.estAttrape(TETARTE));
        assertTrue(!player_.estAttrape(YANMA));
        assertTrue(!player_.estAttrape(YANMEGA));
    }

    @Test
    public void recupererOeufPensions1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PTITARD);
        player_.recupererOeufPensions(egg_);
        assertEq(2, player_.getTeam().size());
        assertSame(egg_, player_.getTeam().get(1));
    }

    @Test
    public void takeHostedPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pkFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pkMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        player_.takeHostedPokemon(pkFemale_, pkMale_);
        assertEq(3, player_.getTeam().size());
        assertSame(pkFemale_, player_.getTeam().get(1));
        assertSame(pkMale_, player_.getTeam().get(2));
    }

    @Test
    public void nouveauxNes1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        assertEq(0, player_.nouveauxNes(_data_).size());
    }

    @Test
    public void nouveauxNes2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(PIKACHU));
        assertEq(0, player_.nouveauxNes(_data_).size());
    }

    @Test
    public void nouveauxNes3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        egg_.versEclosion((short) 200);
        player_.getTeam().add(egg_);
        StringList newPks_ = player_.nouveauxNes(_data_);
        assertEq(1, newPks_.size());
        assertEq(PIKACHU, newPks_.first());
    }

    @Test
    public void eclosionOeuf1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.eclosionOeuf(diff_, _data_);
        assertEq(0, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
    }

    @Test
    public void eclosionOeuf2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(PIKACHU));
        player_.eclosionOeuf(diff_, _data_);
        assertEq(1, player_.getEggsList().size());
        assertEq(1, player_.getPokemonPlayerList().size());
        Bytes keys_ = new Bytes(player_.getPokemonPlayerList().getKeys());
        int index_ = keys_.first();
        assertEq(0, index_);
        keys_ = new Bytes(player_.getEggsList().getKeys());
        index_ = keys_.first();
        assertEq(1, index_);
    }

    @Test
    public void eclosionOeuf3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(MELOFEE);
        egg_.versEclosion((short) 200);
        player_.getTeam().add(egg_);
        assertTrue(!player_.estAttrape(MELOFEE));
        player_.eclosionOeuf(diff_, _data_);
        assertTrue(player_.estAttrape(MELOFEE));
        assertEq(0, player_.getEggsList().size());
        assertEq(2, player_.getPokemonPlayerList().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(MELOFEE, pk_.getName());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertEq(STATIK,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("1291/100"), pk_.getRemainingHp());
        assertEq(140, pk_.getHappiness());
    }

    @Test
    public void recevoirPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        assertTrue(!player_.estAttrape(LIMAGMA));
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        assertTrue(player_.estAttrape(LIMAGMA));
        assertEq(0, player_.getEggsList().size());
        assertEq(2, player_.getPokemonPlayerList().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(PIKACHU, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(LIMAGMA, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(FOUR,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(70, pk_.getHappiness());
    }

    @Test
    public void recevoirPokemon2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        assertEq(1, player_.getBox().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getBox().first();
        assertEq(LIMAGMA, pk_.getName());
        assertEq(7, pk_.getLevel());
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertTrue(pk_.getMoves().contains(PASSE_PASSE));
        assertEq(FOUR,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }

    @Test
    public void getObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        assertEq(LgInt.one(), player_.getInventory().getNumber(REPOUSSE));
    }

    @Test
    public void getHm1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getHm((short) 1);
        assertEq(1, player_.getInventory().gotHm().size());
        assertTrue(player_.getInventory().gotHm().containsObj((short)1));
    }

    @Test
    public void getTm1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTm((short) 2);
        assertEq(1, player_.getInventory().gotTm().size());
        assertTrue(player_.getInventory().gotTm().containsObj((short)2));
    }

    @Test
    public void useInInventory1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.useInInventory(REPOUSSE);
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
    }

    @Test
    public void chooseObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseObject(REPOUSSE);
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void chooseObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(LAVA);
        player_.chooseObject(REPOUSSE);
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void chooseObject3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        assertEq(REPOUSSE, player_.getSelectedObject());
    }

    @Test
    public void cancelUseObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.cancelUseObject();
        assertEq(CustList.INDEX_NOT_FOUND_ELT, player_.getChosenTeamPokemon());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
    }

    @Test
    public void setPokemonAbleToHoldObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(PIKACHU));
        player_.getItem(MULTI_EXP);
        player_.chooseObject(MULTI_EXP);
        player_.setPokemonAbleToHoldObject();
        assertEq(1,player_.getIndexesOfPokemonTeam().size());
        assertEq(0,player_.getIndexesOfPokemonTeam().first());
    }

    @Test
    public void setPokemonAbleToHoldObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setItem(BAIE_ORAN);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short)1);
        player_.recevoirPokemon(pk_, diff_, _data_);
        player_.getItem(MULTI_EXP);
        player_.chooseObject(MULTI_EXP);
        player_.setPokemonAbleToHoldObject();
        assertEq(1,player_.getIndexesOfPokemonTeam().size());
        assertEq(0,player_.getIndexesOfPokemonTeam().first());
    }

    @Test
    public void giveObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(MULTI_EXP);
        player_.chooseObject(MULTI_EXP);
        player_.setPokemonAbleToHoldObject();
        player_.giveObject((short) 0);
        PokemonPlayer pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(MULTI_EXP, pkPl_.getItem());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void giveObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(MULTI_EXP);
        player_.getItem(BAIE_ORAN);
        player_.chooseObject(MULTI_EXP);
        player_.setPokemonAbleToHoldObject();
        player_.giveObject((short) 0);
        player_.chooseObject(BAIE_ORAN);
        player_.setPokemonAbleToHoldObject();
        player_.giveObject((short) 0);
        PokemonPlayer pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(MULTI_EXP, pkPl_.getItem());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MULTI_EXP));
        assertEq(LgInt.one(), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(BAIE_ORAN, player_.getSelectedObject());
    }

    @Test
    public void activerRepousse1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertTrue(player_.getRepousseActif());
        assertEq(100, player_.getRemainingRepelSteps());
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void activerRepousse2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        assertEq(LgInt.one(), player_.getInventory().getNumber(REPOUSSE));
        assertTrue(player_.getRepousseActif());
        assertEq(100, player_.getRemainingRepelSteps());
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void deplacement1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        Egg egg_ = new Egg(MELOFEE);
        player_.getTeam().add(egg_);
        player_.deplacement(diff_, _data_);
        egg_ = (Egg) player_.getTeam().get(2);
        assertEq(6, egg_.getSteps());
    }


    @Test
    public void deplacement2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        player_.deplacement(diff_, _data_);
        assertTrue(player_.getRepousseActif());
        assertEq(99, player_.getRemainingRepelSteps());
    }

    @Test
    public void deplacement3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        player_.deplacement(diff_, _data_);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        assertEq(new LgInt("1"), player_.getInventory().getNumber(REPOUSSE));
        assertTrue(player_.getRepousseActif());
        assertEq(99, player_.getRemainingRepelSteps());
    }

    @Test
    public void deplacement4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.setRemainingRepelSteps(1);
        player_.deplacement(diff_, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void moveLoop1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.moveLoop(0, diff_, _data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(70, pk_.getHappiness());
        assertEq(0, pk_.getNbStepsTeamLead());
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void moveLoop2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.moveLoop(3, diff_, _data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(70, pk_.getHappiness());
        assertEq(3, pk_.getNbStepsTeamLead());
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void moveLoop3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.moveLoop(12, diff_, _data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(71, pk_.getHappiness());
        assertEq(2, pk_.getNbStepsTeamLead());
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void moveLoop4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.moveLoop(12, diff_, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(71, pk_.getHappiness());
        assertEq(2, pk_.getNbStepsTeamLead());
        pk_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(70, pk_.getHappiness());
        assertEq(0, pk_.getNbStepsTeamLead());
    }

    @Test
    public void moveLoop5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(MELOFEE);
        player_.getTeam().add(egg_);
        player_.moveLoop(10, diff_, _data_);
        egg_ = (Egg) player_.getTeam().get(1);
        assertEq(10, egg_.getSteps());
    }

    @Test
    public void moveLoop6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        Egg egg_ = new Egg(MELOFEE);
        player_.getTeam().add(egg_);
        player_.moveLoop(5, diff_, _data_);
        egg_ = (Egg) player_.getTeam().get(2);
        assertEq(30, egg_.getSteps());
    }

    @Test
    public void moveLoop7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        Egg egg_ = new Egg(MELOFEE);
        player_.getTeam().add(egg_);
        assertTrue(!player_.estAttrape(MELOFEE));
        player_.moveLoop(34, diff_, _data_);
        assertTrue(player_.estAttrape(MELOFEE));
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(MELOFEE, pk_.getName());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertEq(STATIK,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(NULL_REF,pk_.getItem());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("1291/100"), pk_.getRemainingHp());
        assertEq(140, pk_.getHappiness());
    }

    @Test
    public void moveLoop8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        player_.moveLoop(100, diff_, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void moveLoop9Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.activerRepousse(_data_);
        player_.moveLoop(102, diff_, _data_);
        assertTrue(!player_.getRepousseActif());
        assertEq(0, player_.getRemainingRepelSteps());
    }

    @Test
    public void enabledSwitchObjectsTeamBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
    }

    @Test
    public void enabledSwitchObjectsTeamBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchObjectsTeamBox());
    }

    @Test
    public void enabledSwitchObjectsTeamBox3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
    }

    @Test
    public void enabledSwitchObjectsTeamBox4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchObjectsTeamBox());
    }

    @Test
    public void enabledSwitchObjectsTeamBox5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getTeam().add(new Egg(PIKACHU));
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 5);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
    }

    @Test
    public void switchObjectsTeamBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(GRELOT);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 1);
        player_.switchObjectsTeamBox(0);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(PP_PLUS, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(GRELOT, pkUser_.getItem());
    }

    @Test
    public void switchObjectsTeamBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 1);
        player_.switchObjectsTeamBox(0);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(PP_PLUS, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(NULL_REF, pkUser_.getItem());
    }

    @Test
    public void switchObjectsTeamBox3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 1);
        player_.switchObjectsTeamBox(0);
        assertTrue(!player_.enabledSwitchObjectsTeamBox());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(NULL_REF, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(PP_PLUS, pkUser_.getItem());
    }

    private static void addPokemonToUser1(Player _user, Difficulty _diff) {
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        _user.recevoirPokemon(givPk_, _diff, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        _user.recevoirPokemon(givPk_, _diff, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(GRELOT);
        _user.recevoirPokemon(givPk_, _diff, _data_);
        givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        _user.recevoirPokemon(givPk_, _diff, _data_);
    }


    @Test
    public void doRevivingFossil1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.doRevivingFossil(LAVA, diff_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(1, player_.getTeam().size());
    }

    @Test
    public void doRevivingFossil2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(LAVA);
        player_.doRevivingFossil(LAVA, diff_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(2, player_.getTeam().size());
    }

    @Test
    public void doRevivingFossil3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(LAVA);
        assertEq(3, player_.getBox().size());
        player_.doRevivingFossil(LAVA, diff_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(4, player_.getBox().size());
    }

    @Test
    public void doRevivingFossil4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(LAVA);
        player_.getItem(LAVA);
        assertEq(1, player_.getTeam().size());
        player_.doRevivingFossil(LAVA, diff_, _data_);
        player_.doRevivingFossil(LAVA, diff_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(3, player_.getTeam().size());
    }

    @Test
    public void doRevivingFossil5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.recevoirPokemon(_data_.getMap().getFirstPokemon(), diff_, _data_);
        player_.recevoirPokemon(_data_.getMap().getFirstPokemon(), diff_, _data_);
        player_.recevoirPokemon(_data_.getMap().getFirstPokemon(), diff_, _data_);
        player_.recevoirPokemon(_data_.getMap().getFirstPokemon(), diff_, _data_);
        player_.recevoirPokemon(_data_.getMap().getFirstPokemon(), diff_, _data_);
        player_.getItem(LAVA);
        assertEq(0, player_.getBox().size());
        player_.doRevivingFossil(LAVA, diff_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(1, player_.getBox().size());
    }

    @Test
    public void switchObjectsBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(GRELOT);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.switchObjectsBox(0, 1);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(PP_PLUS, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(GRELOT, pkUser_.getItem());
    }

    @Test
    public void switchObjectsBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(GRELOT);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.switchObjectsBox(-1, 1);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(GRELOT, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(PP_PLUS, pkUser_.getItem());
    }

    @Test
    public void switchObjectsBox3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(NULL_REF);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(LIMAGMA);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(FOUR);
        givPk_.setLevel((short) 7);
        givPk_.setItem(GRELOT);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.switchObjectsBox(0, -1);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(GRELOT, pkUser_.getItem());
        pkUser_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(PP_PLUS, pkUser_.getItem());
    }

    @Test
    public void enabledSwitchPokemonBoxTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledSwitchPokemonBoxTeam());
    }

    @Test
    public void enabledSwitchPokemonBoxTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchPokemonBoxTeam());
    }

    @Test
    public void enabledSwitchPokemonBoxTeam3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledSwitchPokemonBoxTeam());
    }

    @Test
    public void enabledSwitchPokemonBoxTeam4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchPokemonBoxTeam());
    }

    @Test
    public void switchPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        PokemonPlayer pkUserTeamOne_ = (PokemonPlayer) player_.getTeam().first();
        PokemonPlayer pkUserBoxOne_ = (PokemonPlayer) player_.getBox().first();
        player_.switchPokemon(0, _data_);
        assertTrue(!player_.enabledSwitchPokemonBoxTeam());
        PokemonPlayer pkUserBoxTwo_ = (PokemonPlayer) player_.getBox().first();
        assertEq(PIKACHU, pkUserBoxTwo_.getName());
        assertSame(pkUserBoxTwo_, pkUserTeamOne_);
        PokemonPlayer pkUserTeamTwo_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(LIMAGMA, pkUserTeamTwo_.getName());
        assertSame(pkUserTeamTwo_, pkUserBoxOne_);
    }

    @Test
    public void switchPokemon2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(LIMAGMA));
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 1);
        Egg pkUserTeamOne_ = (Egg) player_.getTeam().get(1);
        PokemonPlayer pkUserBoxOne_ = (PokemonPlayer) player_.getBox().first();
        player_.switchPokemon(0, _data_);
        assertTrue(!player_.enabledSwitchPokemonBoxTeam());
        Egg pkUserBoxTwo_ = (Egg) player_.getBox().first();
        assertEq(LIMAGMA, pkUserBoxTwo_.getName());
        assertSame(pkUserBoxTwo_, pkUserTeamOne_);
        PokemonPlayer pkUserTeamTwo_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(MELOFEE, pkUserTeamTwo_.getName());
        assertSame(pkUserTeamTwo_, pkUserBoxOne_);
    }

    @Test
    public void enabledStorePokemonBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledStorePokemonBox());
    }

    @Test
    public void enabledStorePokemonBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledStorePokemonBox());
    }

    @Test
    public void storeIntoBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertEq(5, player_.getTeam().size());
        player_.storeIntoBox(_data_);
        assertTrue(!player_.enabledStorePokemonBox());
        assertEq(4, player_.getTeam().size());
        assertEq(1, player_.getBox().size());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getBox().first();
        assertEq(PIKACHU, pkUser_.getName());
    }

    @Test
    public void storeIntoBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(LIMAGMA));
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 1);
        assertEq(6, player_.getTeam().size());
        player_.storeIntoBox(_data_);
        assertTrue(!player_.enabledStorePokemonBox());
        assertEq(5, player_.getTeam().size());
        assertEq(1, player_.getBox().size());
        Egg pkUser_ = (Egg) player_.getBox().first();
        assertEq(LIMAGMA, pkUser_.getName());
    }

    @Test
    public void takePokemonFromBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        assertEq(3, player_.getBox().size());
        player_.takePokemonFromBox(0,_data_);
        assertEq(3, player_.getBox().size());
    }

    @Test
    public void takePokemonFromBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.setChosenTeamPokemon((short) 2);
        player_.storeIntoBox(_data_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().first();
        assertEq(1, player_.getBox().size());
        player_.takePokemonFromBox(0, _data_);
        PokemonPlayer pkUserTeam_ = (PokemonPlayer) player_.getTeam().last();
        assertEq(0, player_.getBox().size());
        assertSame(pkUserTeam_, pkUserBox_);
    }

    @Test
    public void takeObjectFromBox1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        player_.takeObjectFromBox(1, _data_);
        assertEq(NULL_REF, pkUserBox_.getItem());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void takeObjectFromBox2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        player_.takeObjectFromBox(1, _data_);
        player_.takeObjectFromBox(1, _data_);
        assertEq(NULL_REF, pkUserBox_.getItem());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void takeObjectFromBox3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pk_ = new PokemonPlayer(_data_.getMap().getFirstPokemon(), _data_);
        player_.getTeam().add(pk_);
        player_.getBox().add(new Egg(PIKACHU));
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().get(3);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        player_.takeObjectFromBox(3, _data_);
        assertEq(NULL_REF, pkUserBox_.getItem());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void enabledTakeObjectTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledTakeObjectTeam());
    }

    @Test
    public void enabledTakeObjectTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledTakeObjectTeam());
    }

    @Test
    public void takeObjectFromTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 1);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(PP_PLUS, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PP_PLUS));
        player_.takeObjectFromTeam(_data_);
        assertTrue(player_.enabledTakeObjectTeam());
        assertEq(NULL_REF, pkUserBox_.getItem());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void takeObjectFromTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 2);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(NULL_REF, pkUserBox_.getItem());
        player_.takeObjectFromTeam(_data_);
        assertTrue(player_.enabledTakeObjectTeam());
        assertEq(NULL_REF, pkUserBox_.getItem());
    }

    @Test
    public void switchItemsTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 1);
        player_.switchTeamOrder((short) 3);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        pkUserBox_ = (PokemonPlayer) player_.getTeam().get(3);
        assertEq(PP_PLUS, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void nickname1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserTeam_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(LIMAGMA, pkUserTeam_.getNickname());
        assertTrue(!player_.isValidPkPlayerChoice());
        player_.setChosenTeamPokemon((short) 2);
        assertTrue(player_.isValidPkPlayerChoice());
        player_.nickname(NULL_REF, _data_);
        pkUserTeam_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(LIMAGMA, pkUserTeam_.getNickname());
    }

    @Test
    public void nickname2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserTeam_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(LIMAGMA, pkUserTeam_.getNickname());
        player_.setChosenTeamPokemon((short) 2);
        player_.nickname(SAMPLE_NICKNAME, _data_);
        pkUserTeam_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(SAMPLE_NICKNAME, pkUserTeam_.getNickname());
    }

    @Test
    public void enabledSwitchTeamOrder1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(!player_.enabledSwitchTeamOrder());
    }

    @Test
    public void enabledSwitchTeamOrder2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledSwitchTeamOrder());
    }

    @Test
    public void enabledSwitchTeamOrder3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchTeamOrder());
    }

    @Test
    public void switchTeamOrder1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        UsablePokemon usablePkFirstBefore_ = player_.getTeam().first();
        UsablePokemon usablePkSecondBefore_ = player_.getTeam().get(1);
        player_.switchTeamOrder((short) 0);
        UsablePokemon usablePkFirstAfter_ = player_.getTeam().first();
        UsablePokemon usablePkSecondAfter_ = player_.getTeam().get(1);
        assertSame(usablePkFirstBefore_, usablePkFirstAfter_);
        assertSame(usablePkSecondBefore_, usablePkSecondAfter_);
        assertEq(0 , player_.getChosenTeamPokemon());
    }

    @Test
    public void switchTeamOrder2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        UsablePokemon usablePkFirstBefore_ = player_.getTeam().first();
        UsablePokemon usablePkSecondBefore_ = player_.getTeam().get(1);
        player_.switchTeamOrder((short) 1);
        UsablePokemon usablePkFirstAfter_ = player_.getTeam().first();
        UsablePokemon usablePkSecondAfter_ = player_.getTeam().get(1);
        assertSame(usablePkFirstBefore_, usablePkSecondAfter_);
        assertSame(usablePkSecondBefore_, usablePkFirstAfter_);
        assertEq(-1 , player_.getChosenTeamPokemon());
    }

    @Test
    public void enabledSwitchObjectsTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(!player_.enabledSwitchObjectsTeam());
    }

    @Test
    public void enabledSwitchObjectsTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.enabledSwitchObjectsTeam());
    }

    @Test
    public void enabledSwitchObjectsTeam3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        assertTrue(player_.enabledSwitchObjectsTeam());
    }

    @Test
    public void enabledSwitchObjectsTeam4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTeam().add(new Egg(PIKACHU));
        player_.setChosenTeamPokemon((short) 1);
        assertTrue(!player_.isValidPkPlayerChoice());
        assertTrue(!player_.enabledSwitchObjectsTeam());
    }

    @Test
    public void switchObjectsTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        player_.switchObjectsTeam((short) 0);
        UsablePokemon usablePkFirstAfter_ = player_.getTeam().first();
        UsablePokemon usablePkSecondAfter_ = player_.getTeam().get(1);
        assertEq(NULL_REF, ((PokemonPlayer)usablePkFirstAfter_).getItem());
        assertEq(PP_PLUS, ((PokemonPlayer)usablePkSecondAfter_).getItem());
        assertEq(0 , player_.getChosenTeamPokemon());
    }

    @Test
    public void switchObjectsTeam2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.setChosenTeamPokemon((short) 0);
        player_.switchObjectsTeam((short) 1);
        UsablePokemon usablePkFirstAfter_ = player_.getTeam().first();
        UsablePokemon usablePkSecondAfter_ = player_.getTeam().get(1);
        assertEq(PP_PLUS, ((PokemonPlayer)usablePkFirstAfter_).getItem());
        assertEq(NULL_REF, ((PokemonPlayer)usablePkSecondAfter_).getItem());
        assertEq(0, player_.getChosenTeamPokemon());
    }

    @Test
    public void switchObjectsTeam3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getTeam().add(new Egg(PIKACHU));
        player_.setChosenTeamPokemon((short) 0);
        player_.switchObjectsTeam((short) 5);
        UsablePokemon usablePkFirstAfter_ = player_.getTeam().first();
        assertEq(NULL_REF, ((PokemonPlayer)usablePkFirstAfter_).getItem());
        assertEq(0, player_.getChosenTeamPokemon());
    }

    @Test
    public void isReleasable1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        assertEq(3, player_.getBox().size());
        assertTrue(player_.isReleasable(1, _data_));
    }

    @Test
    public void isReleasable2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        player_.getBox().add(new Egg(PIKACHU));
        assertEq(4, player_.getBox().size());
        assertTrue(player_.isReleasable(3, _data_));
    }

    @Test
    public void isReleasable3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 1);
        pk_.setItem(GRELOT);
        pk_.setGender(Gender.NO_GENDER);
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        assertEq(4, player_.getBox().size());
        assertTrue(!player_.isReleasable(3, _data_));
    }

    @Test
    public void releasePokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUserBox_ = (PokemonPlayer) player_.getBox().get(1);
        assertEq(GRELOT, pkUserBox_.getItem());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        assertEq(3, player_.getBox().size());
        player_.releasePokemon(1, _data_);
        assertEq(2, player_.getBox().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void releasePokemon2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        player_.getBox().add(new Egg(PIKACHU));
        assertEq(4, player_.getBox().size());
        player_.releasePokemon(3, _data_);
        assertEq(3, player_.getBox().size());
    }


    @Test
    public void releasePokemon3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 1);
        pk_.setItem(GRELOT);
        pk_.setGender(Gender.NO_GENDER);
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        assertEq(4, player_.getBox().size());
        player_.releasePokemon(3, _data_);
        assertEq(4, player_.getBox().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void releasePokemon4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        addPokemonToUser1(player_, diff_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 1);
        pk_.setItem(NULL_REF);
        pk_.setGender(Gender.NO_GENDER);
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        assertEq(4, player_.getBox().size());
        player_.releasePokemon(3, _data_);
        assertEq(3, player_.getBox().size());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(GRELOT));
    }

    @Test
    public void healTeamWithoutUsingObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getTeam().add(new Egg(PIKACHU));
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(1);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(2);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(3);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(4);
        pkPlayer_.setRemainingHp(Rate.zero());
        player_.healTeamWithoutUsingObject(_data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
    }

    @Test
    public void healTeam1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getTeam().add(new Egg(PIKACHU));
        player_.getItem(CENDRESACREE);
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(1);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(2);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(3);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(4);
        pkPlayer_.setRemainingHp(Rate.zero());
        player_.healTeam(CENDRESACREE,_data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
    }

    @Test
    public void usedObjectForRepel1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForRepel(_data_));
    }

    @Test
    public void usedObjectForRepel2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(ELIXIR);
        player_.chooseObject(ELIXIR);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForRepel(_data_));
    }

    @Test
    public void usedObjectForRepel3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        addPokemonToUser1(player_, diff_);
        assertTrue(player_.usedObjectForRepel(_data_));
    }

    @Test
    public void usedObjectForHealing1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForHealing(_data_));
    }

    @Test
    public void usedObjectForHealing2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(CENDRESACREE);
        player_.chooseObject(CENDRESACREE);
        assertTrue(player_.usedObjectForHealing(_data_));
    }

    @Test
    public void usedObjectForHealing3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        assertTrue(!player_.usedObjectForHealing(_data_));
    }

    @Test
    public void usedObjectForHealing4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(RASP_BERRY);
        player_.chooseObject(RASP_BERRY);
        assertTrue(player_.usedObjectForHealing(_data_));
    }

    @Test
    public void useObjectForHealing1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(1);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(2);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(3);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(4);
        pkPlayer_.setRemainingHp(Rate.zero());
        player_.getItem(CENDRESACREE);
        player_.chooseObject(CENDRESACREE);
        player_.useObjectForHealing(_data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(new Rate("3037/100"), pkUser_.pvMax(_data_));
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
    }

    @Test
    public void useObjectForHealing2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(POTION);
        player_.chooseObject(POTION);
        player_.useObjectForHealing(_data_);
        assertEq(POTION, player_.getSelectedObject());
        assertEq(5, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(2));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(3));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
    }

    @Test
    public void useObjectForHealing3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(RASP_BERRY);
        player_.chooseObject(RASP_BERRY);
        player_.useObjectForHealing(_data_);
        assertEq(RASP_BERRY, player_.getSelectedObject());
        assertEq(5, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(2));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(3));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
    }

    @Test
    public void usedObjectForHealingAmove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        assertTrue(player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        assertTrue(player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(ELIXIR);
        player_.chooseObject(ELIXIR);
        assertTrue(!player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        assertTrue(!player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(BAIE_ORAN);
        player_.chooseObject(BAIE_ORAN);
        assertTrue(!player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForHealingAmove7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(BAIE_MEPO);
        player_.chooseObject(BAIE_MEPO);
        assertTrue(player_.usedObjectForHealingAmove(_data_));
    }

    @Test
    public void usedObjectForEvolving1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForEvolving(_data_));
    }

    @Test
    public void usedObjectForEvolving2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        assertTrue(!player_.usedObjectForEvolving(_data_));
    }

    @Test
    public void usedObjectForEvolving3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        assertTrue(player_.usedObjectForEvolving(_data_));
    }

    @Test
    public void usedObjectForBoosting1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForBoosting(_data_));
    }

    @Test
    public void usedObjectForBoosting2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        assertTrue(player_.usedObjectForBoosting(_data_));
    }

    @Test
    public void usedObjectForBoosting3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        assertTrue(!player_.usedObjectForBoosting(_data_));
    }

    @Test
    public void usedObjectForBoostingMove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usedObjectForBoostingMove(_data_));
    }

    @Test
    public void usedObjectForBoostingMove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        assertTrue(player_.usedObjectForBoostingMove(_data_));
    }

    @Test
    public void usedObjectForBoostingMove3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        assertTrue(!player_.usedObjectForBoostingMove(_data_));
    }

    @Test
    public void usedObjectForBoostingMove4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        assertTrue(player_.usedObjectForBoosting(_data_));
        assertTrue(!player_.usedObjectForBoostingMove(_data_));
    }

    @Test
    public void usableObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        assertTrue(!player_.usableObject(_data_));
    }

    @Test
    public void usableObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        assertTrue(!player_.usableObject(_data_));
    }

    @Test
    public void usableObject3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        assertTrue(player_.usableObject(_data_));
    }

    @Test
    public void usableObject4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        assertTrue(player_.usableObject(_data_));
    }

    @Test
    public void usableObject5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(ELIXIR);
        player_.chooseObject(ELIXIR);
        assertTrue(player_.usableObject(_data_));
    }

    @Test
    public void heal1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(POTION);
        player_.chooseObject(POTION);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(0, pkUser_.getStatus().size());
        assertEq(new Rate("20"), pkUser_.getRemainingHp());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(POTION));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(POTION);
        player_.chooseObject(POTION);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(0, pkUser_.getStatus().size());
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(POTION, player_.getSelectedObject());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(POTION));
        assertEq(POTION, player_.getSelectedObject());
    }

    @Test
    public void heal3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(HAPPY_POTION);
        player_.chooseObject(HAPPY_POTION);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("20"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(72, pkUser_.getHappiness());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(HAPPY_POTION));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(ELIXIR);
        player_.chooseObject(ELIXIR);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(10, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(10, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(ELIXIR));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(MAX_ELIXIR);
        player_.chooseObject(MAX_ELIXIR);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(RASP_BERRY);
        player_.chooseObject(RASP_BERRY);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("20"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(RASP_BERRY));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getStatus().add(SOMMEIL);
        player_.getItem(REVEIL);
        player_.chooseObject(REVEIL);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(REVEIL));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(REVEIL);
        player_.chooseObject(REVEIL);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(REVEIL));
        assertEq(REVEIL, player_.getSelectedObject());
    }

    @Test
    public void heal9Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MAX_ELIXIR);
        player_.chooseObject(MAX_ELIXIR);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(MAX_ELIXIR));
        assertEq(MAX_ELIXIR, player_.getSelectedObject());
    }

    @Test
    public void heal10Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getStatus().add(PARALYSIE);
        player_.getItem(BAIE_CERIZ);
        player_.chooseObject(BAIE_CERIZ);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(BAIE_CERIZ));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal11Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(BAIE_ORAN);
        player_.chooseObject(BAIE_ORAN);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(BAIE_ORAN));
        assertEq(BAIE_ORAN, player_.getSelectedObject());
    }

    @Test
    public void heal12Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(BAIE_ENIGMA);
        player_.chooseObject(BAIE_ENIGMA);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/400"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(BAIE_ENIGMA));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal13Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(BAIE_GOWAV);
        player_.chooseObject(BAIE_GOWAV);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/800"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(BAIE_GOWAV));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal14Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(RAPPEL);
        player_.chooseObject(RAPPEL);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/200"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(RAPPEL));
        assertEq(NULL_REF, player_.getSelectedObject());
    }

    @Test
    public void heal15Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(RAPPEL);
        player_.chooseObject(RAPPEL);
        player_.useObjectForHealing(_data_);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(RAPPEL));
        assertEq(RAPPEL, player_.getSelectedObject());
    }

    @Test
    public void heal16Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.setSelectedObject(HUILE_MAX);
        player_.getIndexesOfPokemonTeam().add((byte)0);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(HUILE_MAX, player_.getSelectedObject());
    }

    @Test
    public void heal17Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.setSelectedObject(HUILE_MAX);
        player_.heal((short) 0, _data_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(0, pkUser_.getStatus().size());
        assertEq(20, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(HUILE_MAX));
        assertEq(HUILE_MAX, player_.getSelectedObject());
    }

    @Test
    public void initializeMovesToBeHealed1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
    }

    @Test
    public void initializeMovesToBeHealed2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(2, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(10, player_.getChosenMoves().getVal(JACKPOT));
        assertEq(10, player_.getChosenMoves().getVal(OEIL_MIRACLE));
    }

    @Test
    public void initializeMovesToBeHealed3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 15);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(2, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertEq(10, player_.getChosenMoves().getVal(OEIL_MIRACLE));
        assertEq(5, player_.getChosenMoves().getVal(JACKPOT));
    }

    @Test
    public void initializeMovesToBeHealed4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(10, player_.getChosenMoves().getVal(OEIL_MIRACLE));
    }

    @Test
    public void initializeMovesToBeHealed5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE_MAX, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(2, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertEq(40, player_.getChosenMoves().getVal(OEIL_MIRACLE));
        assertEq(20, player_.getChosenMoves().getVal(JACKPOT));
    }

    @Test
    public void initializeMovesToBeHealed6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 10);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE_MAX, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(2, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertEq(40, player_.getChosenMoves().getVal(OEIL_MIRACLE));
        assertEq(10, player_.getChosenMoves().getVal(JACKPOT));
    }

    @Test
    public void initializeMovesToBeHealed7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(HUILE_MAX, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(40, player_.getChosenMoves().getVal(OEIL_MIRACLE));
    }

    @Test
    public void initializeMovesToBeHealed8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(BLACK_BERRY);
        player_.chooseObject(BLACK_BERRY);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(BLACK_BERRY, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(7, player_.getChosenMoves().getVal(OEIL_MIRACLE));
    }

    @Test
    public void initializeMovesToBeHealed9Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        //player_.getTeam().add(new Egg())
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(BLACK_BERRY);
        player_.chooseObject(BLACK_BERRY);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        assertEq(BLACK_BERRY, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(7, player_.getChosenMoves().getVal(OEIL_MIRACLE));
    }

    @Test
    public void healMove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        player_.healMove(NULL_REF, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(0, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(0, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(HUILE));
    }

    @Test
    public void healMove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        player_.healMove(OEIL_MIRACLE, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(10, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(0, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(HUILE));
    }

    @Test
    public void healMove3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        player_.getItem(HUILE_MAX);
        player_.chooseObject(HUILE_MAX);
        player_.useObjectForHealing(_data_);
        player_.initializeMovesToBeHealed((short) 0, _data_);
        player_.healMove(OEIL_MIRACLE, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(40, pkUser_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(0, pkUser_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(HUILE_MAX));
    }

    @Test
    public void useObjectForBoosting1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        player_.useObjectForBoosting();
        assertEq(5, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(2));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(3));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
    }

    @Test
    public void boostStatistique1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        player_.useObjectForBoosting();
        player_.boostStatistique((short) 0, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(2, pkUser_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pkUser_.getEv().getVal(Statistic.HP));
        assertEq(71, pkUser_.getHappiness());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
    }

    @Test
    public void boostStatistique2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getEv().put(Statistic.ATTACK, (short) 20);
        pkUser_.setHappiness((short) 170);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        player_.useObjectForBoosting();
        player_.boostStatistique((short) 0, _data_);
        assertEq(MUSCLE, player_.getSelectedObject());
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(20, pkUser_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pkUser_.getEv().getVal(Statistic.HP));
        assertEq(170, pkUser_.getHappiness());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(MUSCLE));
    }

    @Test
    public void boostStatistique3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getEv().put(Statistic.SPEED, (short) 20);
        player_.getItem(BOLT);
        player_.chooseObject(BOLT);
        player_.useObjectForBoosting();
        player_.boostStatistique((short) 0, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(0, pkUser_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pkUser_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pkUser_.getEv().getVal(Statistic.HP));
        assertEq(72, pkUser_.getHappiness());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(MUSCLE));
    }

    @Test
    public void initializeMovesToBeBoosted1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) _data_.getMaxPp());
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        assertEq(PP_PLUS, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
    }

    @Test
    public void initializeMovesToBeBoosted2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) 78);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        assertEq(PP_PLUS, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(3, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertTrue(player_.getChosenMoves().contains(PASSE_PASSE));
        assertEq(2, player_.getChosenMoves().getVal(JACKPOT));
        assertEq(3, player_.getChosenMoves().getVal(OEIL_MIRACLE));
        assertEq(3, player_.getChosenMoves().getVal(PASSE_PASSE));
    }

    @Test
    public void initializeMovesToBeBoosted3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        assertEq(PP_PLUS, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(PASSE_PASSE));
        assertEq(2, player_.getChosenMoves().getVal(PASSE_PASSE));
    }

    @Test
    public void initializeMovesToBeBoosted4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        player_.getItem(PP_PLUS_BIS);
        player_.chooseObject(PP_PLUS_BIS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        assertEq(PP_PLUS_BIS, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(PASSE_PASSE));
        assertEq(0, player_.getChosenMoves().getVal(PASSE_PASSE));
    }

    @Test
    public void gainPpMax1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        player_.gainPpMax(NULL_REF, _data_);
        assertEq(80, pkUser_.getMoves().getVal(JACKPOT).getMax());
        assertEq(80, pkUser_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(78, pkUser_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(70, pkUser_.getHappiness());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void gainPpMax2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        player_.gainPpMax(PASSE_PASSE, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(80, pkUser_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(80, pkUser_.getMoves().getVal(JACKPOT).getMax());
        assertEq(80, pkUser_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(72, pkUser_.getHappiness());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void gainPpMax3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        pkUser_.setHappiness((short) 170);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        player_.gainPpMax(PASSE_PASSE, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(80, pkUser_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(80, pkUser_.getMoves().getVal(JACKPOT).getMax());
        assertEq(80, pkUser_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(170, pkUser_.getHappiness());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void gainPpMax4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        pkUser_.setHappiness((short) 170);
        player_.getItem(PP_PLUS_BIS);
        player_.chooseObject(PP_PLUS_BIS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        player_.gainPpMax(PASSE_PASSE, _data_);
        assertEq(PP_PLUS_BIS, player_.getSelectedObject());
        assertEq(78, pkUser_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(80, pkUser_.getMoves().getVal(JACKPOT).getMax());
        assertEq(80, pkUser_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(PASSE_PASSE));
        assertEq(0, player_.getChosenMoves().getVal(PASSE_PASSE));
        assertEq(170, pkUser_.getHappiness());
        assertEq(LgInt.one(), player_.getInventory().getNumber(PP_PLUS_BIS));
    }

    @Test
    public void gainPpMax5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) 78);
        player_.getItem(PP_PLUS_BIS);
        player_.chooseObject(PP_PLUS_BIS);
        player_.useObjectForBoosting();
        player_.initializeMovesToBeBoosted((short) 0, _data_);
        player_.gainPpMax(PASSE_PASSE, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(78, pkUser_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(80, pkUser_.getMoves().getVal(JACKPOT).getMax());
        assertEq(80, pkUser_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(72, pkUser_.getHappiness());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(PP_PLUS_BIS));
    }

    @Test
    public void useObjectForEvolving1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_GLACE);
        player_.chooseObject(PIERRE_GLACE);
        player_.useObjectForEvolving(_data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF ,player_.getSelectedObject());
    }

    @Test
    public void useObjectForEvolving2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
    }

    @Test
    public void useObjectForEvolving3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
        assertEq(LgInt.one(), player_.getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void useObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(REPOUSSE);
        player_.chooseObject(REPOUSSE);
        player_.useObject(_data_);
        assertEq(LgInt.zero(), player_.getInventory().getNumber(REPOUSSE));
        assertTrue(player_.getRepousseActif());
        assertEq(100, player_.getRemainingRepelSteps());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertTrue(!player_.usedObject(_data_));
    }

    @Test
    public void useObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(1);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(2);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(3);
        pkPlayer_.setRemainingHp(Rate.zero());
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(4);
        pkPlayer_.setRemainingHp(Rate.zero());
        player_.getItem(CENDRESACREE);
        player_.chooseObject(CENDRESACREE);
        player_.useObject(_data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(1);
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        pkUser_ = (PokemonPlayer) player_.getTeam().get(2);
        assertEq(new Rate("3037/100"), pkUser_.getRemainingHp());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(CENDRESACREE));
        assertTrue(!player_.usedObject(_data_));
    }

    @Test
    public void useObject3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.setRemainingHp(Rate.one());
        player_.getItem(POTION);
        player_.chooseObject(POTION);
        player_.useObject(_data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeam().get(0));
        assertEq(POTION, player_.getSelectedObject());
        assertEq(LgInt.one(), player_.getInventory().getNumber(POTION));
        assertTrue(player_.usedObject(_data_));
    }

    @Test
    public void useObject4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        PokemonPlayer pkPlayer_;
        pkPlayer_ = (PokemonPlayer) player_.getTeam().get(0);
        pkPlayer_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObject(_data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeam().get(0));
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(LgInt.one(), player_.getInventory().getNumber(HUILE));
        assertTrue(player_.usedObject(_data_));
    }

    @Test
    public void useObject5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        player_.useObject(_data_);
        assertEq(5, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(2));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(3));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
        assertEq(LgInt.one(), player_.getInventory().getNumber(MUSCLE));
        assertTrue(player_.usedObject(_data_));
    }

    @Test
    public void useObject6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObject(_data_);
        assertEq(5, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(2));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(3));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
        assertEq(LgInt.one(), player_.getInventory().getNumber(PP_PLUS));
        assertTrue(player_.usedObject(_data_));
    }

    @Test
    public void useObject7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_GLACE);
        player_.chooseObject(PIERRE_GLACE);
        player_.useObject(_data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF ,player_.getSelectedObject());
        assertEq(LgInt.one(), player_.getInventory().getNumber(PIERRE_GLACE));
        assertTrue(!player_.usedObject(_data_));
    }

    @Test
    public void useObject8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObject(_data_);
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertTrue(player_.getIndexesOfPokemonTeam().contains(4));
        assertEq(LgInt.one(), player_.getInventory().getNumber(PIERRE_LUNE));
        assertTrue(player_.usedObject(_data_));
    }

    @Test
    public void choosePokemonForEvolution1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 0, _data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void choosePokemonForEvolution2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
        assertEq(5, ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().size());
        assertEq(0, ((PokemonPlayer)player_.getTeam().get(1)).getNewAbilitiesToBeChosen().size());
        StringList abs_ = player_.getNewAbilitiesToBeChosen();
        assertEq(0, abs_.size());
    }
    @Test
    public void choosePokemonForEvolution3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 5);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_SOLEIL);
        player_.chooseObject(PIERRE_SOLEIL);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().size());
        assertEq(2, ((PokemonPlayer)player_.getTeam().get(1)).getNewAbilitiesToBeChosen().size());
        assertEq(PIERRE_SOLEIL, player_.getSelectedObject());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
        StringList abs_ = player_.getNewAbilitiesToBeChosen();
        assertEq(2, abs_.size());
    }

    @Test
    public void choosePokemonForEvolution4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 5);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELODELFE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void choosePokemonForUsingObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(JACKPOT).setCurrent((short) 0);
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setCurrent((short) 0);
        player_.getItem(HUILE);
        player_.chooseObject(HUILE);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 0, _data_);
        assertEq(HUILE, player_.getSelectedObject());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(2, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(JACKPOT));
        assertTrue(player_.getChosenMoves().contains(OEIL_MIRACLE));
        assertEq(10, player_.getChosenMoves().getVal(JACKPOT));
        assertEq(10, player_.getChosenMoves().getVal(OEIL_MIRACLE));
        assertEq(LgInt.one(), player_.getInventory().getNumber(HUILE));
    }

    @Test
    public void choosePokemonForUsingObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.setRemainingHp(Rate.zero());
        player_.getItem(POTION);
        player_.chooseObject(POTION);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 0, _data_);
        pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(new Rate("20"), pkUser_.getRemainingHp());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(HUILE));
    }

    @Test
    public void choosePokemonForUsingObject3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 1, _data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
        assertEq(5, ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().size());
        assertEq(0, ((PokemonPlayer)player_.getTeam().get(1)).getNewAbilitiesToBeChosen().size());
        assertEq(LgInt.one(), player_.getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void choosePokemonForUsingObject4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        pkUser_.getMoves().getVal(OEIL_MIRACLE).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(JACKPOT).setMax((short) _data_.getMaxPp());
        pkUser_.getMoves().getVal(PASSE_PASSE).setMax((short) _data_.getMaxPp());
        player_.getItem(PP_PLUS);
        player_.chooseObject(PP_PLUS);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 0, _data_);
        assertEq(PP_PLUS, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(LgInt.one(), player_.getInventory().getNumber(PP_PLUS));
    }

    @Test
    public void choosePokemonForUsingObject5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(MUSCLE);
        player_.chooseObject(MUSCLE);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 0, _data_);
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        PokemonPlayer pkUser_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(2, pkUser_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pkUser_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pkUser_.getEv().getVal(Statistic.HP));
        assertEq(71, pkUser_.getHappiness());
        assertEq(LgInt.zero(), player_.getInventory().getNumber(MUSCLE));
    }

    @Test
    public void cancelUsingObjectOnPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObject(_data_);
        player_.choosePokemonForUsingObject((short) 1, _data_);
        player_.cancelUsingObjectOnPokemon();
        assertEq(PIERRE_LUNE, player_.getSelectedObject());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(2, player_.getIndexesOfPokemonTeam().size());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
        assertEq(5, ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().size());
        assertEq(0, ((PokemonPlayer)player_.getTeam().get(1)).getNewAbilitiesToBeChosen().size());
        assertEq(LgInt.one(), player_.getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void evolvePokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().put(OEIL_MIRACLE, true);
        player_.evolvePokemon(_data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertEq(PIERRE_LUNE, player_.getSelectedObject());
        assertEq(new LgInt("1"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void evolvePokemon2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        addPokemonToUser1(player_, diff_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        player_.evolvePokemon(_data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELODELFE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void evolvePokemon3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 5);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_SOLEIL);
        player_.chooseObject(PIERRE_SOLEIL);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        player_.setChosenAbilityForEvolution(GARDE);
        player_.evolvePokemon(_data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(MELODELFE_2, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void evolvePokemon4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_SOLEIL);
        player_.chooseObject(PIERRE_SOLEIL);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        player_.setChosenAbilityForEvolution(GARDE);
        player_.evolvePokemon(_data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PIERRE_SOLEIL));
        assertEq(MELODELFE_2, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void evolvePokemon5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        player_.recevoirPokemon(givPk_, diff_, _data_);
        player_.getItem(PIERRE_LUNE);
        player_.chooseObject(PIERRE_LUNE);
        player_.useObjectForEvolving(_data_);
        player_.choosePokemonForEvolution((short) 1, _data_);
        ((PokemonPlayer)player_.getTeam().get(1)).getMovesToBeKeptEvo().clear();
        player_.evolvePokemon(_data_);
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedObject());
        assertEq(new LgInt("0"), player_.getInventory().getNumber(PIERRE_LUNE));
        assertEq(MELOFEE, ((PokemonPlayer)player_.getTeam().get(1)).getName());
    }

    @Test
    public void choosePokemonForMoveTutors1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        Pokemon pk_ = _data_.getMap().getFirstPokemon();
        PokemonPlayer pkPlayer_ = new PokemonPlayer(pk_, _data_);
        player_.getTeam().add(pkPlayer_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(4, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(OEIL_MIRACLE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        assertTrue(player_.getSelectedMoves().getVal(PASSE_PASSE));
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
    }

    @Test
    public void choosePokemonForMoveTutors2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(4, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(OEIL_MIRACLE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        assertTrue(player_.getSelectedMoves().getVal(PASSE_PASSE));
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
    }

    @Test
    public void canKeepAllOldMoves1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertTrue(player_.canKeepAllOldMoves(_data_));
    }

    @Test
    public void canKeepAllOldMoves2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertTrue(!player_.canKeepAllOldMoves(_data_));
    }

    @Test
    public void currentMovesPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        CustList<String> moves_ = player_.currentMovesPokemon();
        assertEq(3, moves_.size());
        assertTrue(StringList.contains(moves_, JACKPOT));
        assertTrue(StringList.contains(moves_, PASSE_PASSE));
        assertTrue(StringList.contains(moves_, OEIL_MIRACLE));
    }

    @Test
    public void learnMovesByMoveTutor1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(4, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(OEIL_MIRACLE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        assertTrue(player_.getSelectedMoves().getVal(PASSE_PASSE));
        player_.learnMovesByMoveTutor(_data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(3, pk_.getMoves().size());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(4, player_.getSelectedMoves().size());
    }

    @Test
    public void learnMovesByMoveTutor2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 1);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(2, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        player_.learnMovesByMoveTutor(_data_);
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(1, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertEq(2, player_.getSelectedMoves().size());
    }

    @Test
    public void learnMovesByMoveTutor3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(4, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(OEIL_MIRACLE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        assertTrue(player_.getSelectedMoves().getVal(PASSE_PASSE));
        player_.getSelectedMoves().put(VIVE_ATTAQUE, true);
        player_.learnMovesByMoveTutor(_data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(4, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(VIVE_ATTAQUE).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getSelectedMoves().size());
    }

    @Test
    public void learnMovesByMoveTutor4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 1);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(2, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        player_.getSelectedMoves().put(VIVE_ATTAQUE, true);
        player_.learnMovesByMoveTutor(_data_);
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(2, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(20, pkPl_.getMoves().getVal(VIVE_ATTAQUE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getSelectedMoves().size());
    }

    @Test
    public void learnMovesByMoveTutor5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 30);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(5, player_.getSelectedMoves().size());
        assertTrue(!player_.getSelectedMoves().getVal(VIVE_ATTAQUE));
        assertTrue(player_.getSelectedMoves().getVal(JACKPOT));
        assertTrue(player_.getSelectedMoves().getVal(PASSE_PASSE));
        assertTrue(player_.getSelectedMoves().getVal(OEIL_MIRACLE));
        assertTrue(player_.getSelectedMoves().getVal(ORAGE));
        player_.getSelectedMoves().put(VIVE_ATTAQUE, true);
        player_.getSelectedMoves().put(PASSE_PASSE, false);
        player_.learnMovesByMoveTutor(_data_);
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(4, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(20, pkPl_.getMoves().getVal(VIVE_ATTAQUE).getMax());
        assertEq(40, pkPl_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(5, pkPl_.getMoves().getVal(ORAGE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getSelectedMoves().size());
    }

    @Test
    public void chooseMoveByObject1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseMoveByObject(JACKPOT, _data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseMoveByObject(TONNERRE, _data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(!player_.getIndexesOfPokemonTeamMoves().getVal((byte) 0));
        assertEq(TONNERRE, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(!player_.getIndexesOfPokemonTeamMoves().getVal((byte) 0));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(player_.getIndexesOfPokemonTeamMoves().getVal((byte) 0));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        pk_.getMoves().put(ECLAIR, new UsesOfMove((short) 15));
        assertEq(4, pk_.getMoves().size());
        player_.chooseMoveByObject(ECLAIR, _data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(DEMANTA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject7Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(DEMANTA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(TONNERRE, _data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject8Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        player_.getTeam().add(new Egg(DEMANTA));
        Pokemon pk_ = new WildPk();
        pk_.setName(DEMANTA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(TONNERRE, _data_);
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void chooseMoveByObject9Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        player_.getTeam().add(new Egg(DEMANTA));
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(player_.getIndexesOfPokemonTeamMoves().getVal((byte) 1));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void choosePokemonForLearningMove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(4, pkPl_.getMoves().size());
        assertEq(40, pkPl_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(50, pkPl_.getMoves().getVal(ECLAIR).getMax());
        assertEq(10, pkPl_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void choosePokemonForLearningMove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(4, player_.getChosenMoves().size());
        assertEq(20, player_.getChosenMoves().getVal(PISTOLET_A_O));
        assertEq(20, player_.getChosenMoves().getVal(TORGNOLES));
        assertEq(5, player_.getChosenMoves().getVal(DANSE_PLUIE));
        assertEq(15, player_.getChosenMoves().getVal(HYPNOSE));
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(0));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(player_.getIndexesOfPokemonTeamMoves().getVal((byte) 0));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void choosePokemonForLearningMove3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        player_.getTeam().add(new Egg(PTITARD));
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(4, player_.getChosenMoves().size());
        assertEq(20, player_.getChosenMoves().getVal(PISTOLET_A_O));
        assertEq(20, player_.getChosenMoves().getVal(TORGNOLES));
        assertEq(5, player_.getChosenMoves().getVal(DANSE_PLUIE));
        assertEq(15, player_.getChosenMoves().getVal(HYPNOSE));
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(player_.getIndexesOfPokemonTeamMoves().getVal((byte) 1));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void choosePokemonForLearningMove4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(ECLAIR, (short) 10);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_, moves_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        assertEq(1, player_.getChosenTeamPokemon());
        assertEq(4, player_.getChosenMoves().size());
        assertEq(20, player_.getChosenMoves().getVal(PISTOLET_A_O));
        assertEq(20, player_.getChosenMoves().getVal(TORGNOLES));
        assertEq(5, player_.getChosenMoves().getVal(DANSE_PLUIE));
        assertEq(15, player_.getChosenMoves().getVal(HYPNOSE));
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertTrue(player_.getIndexesOfPokemonTeam().contains(1));
        assertEq(1, player_.getIndexesOfPokemonTeamMoves().size());
        assertTrue(player_.getIndexesOfPokemonTeamMoves().getVal((byte) 1));
        assertEq(ECLAIR, player_.getSelectedMove());
    }

    @Test
    public void learnMove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        player_.learnMove(DANSE_PLUIE, _data_);
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(4, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pkPl_.getMoves().getVal(TORGNOLES).getMax());
        assertEq(50, pkPl_.getMoves().getVal(ECLAIR).getMax());
        assertEq(15, pkPl_.getMoves().getVal(HYPNOSE).getMax());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void learnMove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        player_.getTeam().add(new Egg(PTITARD));
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        player_.learnMove(DANSE_PLUIE, _data_);
        pkPl_ = (PokemonPlayer) player_.getTeam().last();
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(4, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pkPl_.getMoves().getVal(TORGNOLES).getMax());
        assertEq(50, pkPl_.getMoves().getVal(ECLAIR).getMax());
        assertEq(15, pkPl_.getMoves().getVal(HYPNOSE).getMax());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void cancelLearningMoveOnPokemon1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        player_.cancelLearningMoveOnPokemon();
        assertEq(1, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeam().first());
        assertEq(ECLAIR, player_.getSelectedMove());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getSelectedMoves().size());
    }

    @Test
    public void cancelLearningMoveOnPokemon2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.choosePokemonForMoveTutors((byte) 0, _data_);
        player_.cancelLearningMoveOnPokemon();
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(NULL_REF, player_.getSelectedMove());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getSelectedMoves().size());
    }

    @Test
    public void cancelLearningMove1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        player_.cancelLearningMove();
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(3, pk_.getMoves().size());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
    }

    @Test
    public void cancelLearningMove2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        player_.choosePokemonForMoveTutors((short) 0, _data_);
        assertEq(0, player_.getChosenTeamPokemon());
        assertEq(1, player_.getChosenMoves().size());
        assertTrue(player_.getChosenMoves().contains(VIVE_ATTAQUE));
        assertEq(4, pkPl_.getMoves().size());
        player_.cancelLearningMove();
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(4, pkPl_.getMoves().size());
        assertEq(5, pkPl_.getMoves().getVal(ORAGE).getMax());
        assertEq(40, pkPl_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(10, pkPl_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
    }

    @Test
    public void cancelLearningMove3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, false, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 18);
        PokemonPlayer pkPl_ = new PokemonPlayer(pk_, _data_);
        pkPl_.initIv(diff_);
        pkPl_.initPvRestants(_data_);
        player_.getTeam().add(pkPl_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.choosePokemonForLearningMove((byte) 0, _data_);
        player_.cancelLearningMove();
        pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(-1, player_.getChosenTeamPokemon());
        assertEq(4, pkPl_.getMoves().size());
        assertEq(20, pkPl_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pkPl_.getMoves().getVal(TORGNOLES).getMax());
        assertEq(5, pkPl_.getMoves().getVal(DANSE_PLUIE).getMax());
        assertEq(15, pkPl_.getMoves().getVal(HYPNOSE).getMax());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void cancelLearningMove4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.chooseMoveByObject(ECLAIR, _data_);
        player_.cancelLearningMove();
        PokemonPlayer pkPl_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(3, pkPl_.getMoves().size());
        assertEq(40, pkPl_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(20, pkPl_.getMoves().getVal(JACKPOT).getMax());
        assertEq(10, pkPl_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, player_.getChosenTeamPokemon());
        assertEq(0, player_.getChosenMoves().size());
        assertEq(0, player_.getIndexesOfPokemonTeam().size());
        assertEq(0, player_.getIndexesOfPokemonTeamMoves().size());
        assertEq(NULL_REF, player_.getSelectedMove());
    }

    @Test
    public void canBeBought1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        StringMap<LgInt> objects_ = new StringMap<LgInt>();
        objects_.put(GRELOT, new LgInt(250));
        objects_.put(LAVA, new LgInt(10));
        assertTrue(!player_.canBeBought(objects_, _data_));
    }

    @Test
    public void canBeBought2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        StringMap<LgInt> objects_ = new StringMap<LgInt>();
        objects_.put(GRELOT, new LgInt(1));
        objects_.put(LAVA, new LgInt(1));
        assertTrue(player_.canBeBought(objects_, _data_));
    }

    @Test
    public void canBeBought3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Shorts tm_ = new Shorts();
        tm_.add((short)2);
        assertTrue(!player_.canBeBought(tm_, _data_));
    }

    @Test
    public void canBeBought4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Shorts tm_ = new Shorts();
        tm_.add((short)3);
        assertTrue(player_.canBeBought(tm_, _data_));
    }

    @Test
    public void canBeBought5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Shorts tm_ = new Shorts();
        tm_.add((short)4);
        assertTrue(player_.canBeBought(tm_, _data_));
    }

    @Test
    public void achatObjets1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        StringMap<LgInt> objects_ = new StringMap<LgInt>();
        objects_.put(GRELOT, new LgInt(2));
        objects_.put(LAVA, new LgInt(1));
        player_.achatObjets(objects_, _data_);
        assertEq(new LgInt("1"), player_.getInventory().getNumber(LAVA));
        assertEq(new LgInt("2"), player_.getInventory().getNumber(GRELOT));
        assertEq(new LgInt("900"), player_.getMoney());
    }

    @Test
    public void venteObjets1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        StringMap<LgInt> objects_ = new StringMap<LgInt>();
        objects_.put(GRELOT, new LgInt(180));
        objects_.put(LAVA, new LgInt(10));
        player_.venteObjets(objects_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(new LgInt("0"), player_.getInventory().getNumber(GRELOT));
        assertEq(new LgInt("3000"), player_.getMoney());
    }

    @Test
    public void venteObjets2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(GRELOT);
        player_.getItem(GRELOT);
        StringMap<LgInt> objects_ = new StringMap<LgInt>();
        objects_.put(GRELOT, new LgInt(1));
        objects_.put(LAVA, new LgInt(10));
        player_.venteObjets(objects_, _data_);
        assertEq(new LgInt("0"), player_.getInventory().getNumber(LAVA));
        assertEq(new LgInt("1"), player_.getInventory().getNumber(GRELOT));
        assertEq(new LgInt("4000"), player_.getMoney());
    }

    @Test
    public void achatCt1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTm((short) 4);
        player_.achatCt((short) 4, _data_);
        assertEq(new LgInt("3000"), player_.getMoney());
    }

    @Test
    public void achatCt2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTm((short) 4);
        player_.achatCt((short) 3, _data_);
        assertEq(new LgInt("1000"), player_.getMoney());
    }

    @Test
    public void achatCts1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getTm((short) 4);
        Shorts tm_ = new Shorts();
        tm_.add((short) 3);
        player_.achatCts(tm_, _data_);
        assertEq(new LgInt("1000"), player_.getMoney());
    }

    @Test
    public void reindexAfterStoringToHost1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        Object first_ = player_.getTeam().get(0);
        Object second_ = player_.getTeam().get(2);
        Object third_ = player_.getTeam().get(4);
        player_.reindexAfterStoringToHost((short) 1,(short) 3);
        assertEq(3, player_.getTeam().size());
        assertSame(first_, player_.getTeam().get(0));
        assertSame(second_, player_.getTeam().get(1));
        assertSame(third_, player_.getTeam().get(2));
    }

    @Test
    public void reindexAfterStoringToHost2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        player_.recevoirPokemon(pokemonDonne_, diff_, _data_);
        Object first_ = player_.getTeam().get(0);
        Object second_ = player_.getTeam().get(2);
        Object third_ = player_.getTeam().get(4);
        player_.reindexAfterStoringToHost((short) 3,(short) 1);
        assertEq(3, player_.getTeam().size());
        assertSame(first_, player_.getTeam().get(0));
        assertSame(second_, player_.getTeam().get(1));
        assertSame(third_, player_.getTeam().get(2));
    }

    @Test
    public void existBall1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        assertTrue(!player_.existBall(_data_));
    }

    @Test
    public void existBall2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(BAIE_ORAN);
        assertTrue(!player_.existBall(_data_));
    }

    @Test
    public void existBall3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        player_.getItem(MASTER_BALL);
        assertTrue(player_.existBall(_data_));
    }

    @Test
    public void setTeamAfterTrading1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        ExchangedData ex_ = new ExchangedData(_data_);
        Player player_ = new Player(NICKNAME, null, diff_, true, _data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 7);
        pk_.setItem(NULL_REF);
        player_.recevoirPokemon(pk_, diff_, _data_);
        pk_ = new WildPk();
        pk_.setName(MELOFEE);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 7);
        pk_.setItem(NULL_REF);
        ex_.setPokemon(new PokemonPlayer(pk_, _data_));
        ex_.setIndexTeam(1);
        String nameBef_ = ((PokemonPlayer)player_.getTeam().get(1)).getName();
        assertEq(LIMAGMA, nameBef_);
        assertTrue(!player_.estAttrape(MELOFEE));
        player_.setTeamAfterTrading(ex_);
        assertSame(ex_.getPokemon(), player_.getTeam().get(1));
        assertEq(2, player_.getTeam().size());
        String name_ = ((PokemonPlayer)player_.getTeam().get(1)).getName();
        assertEq(MELOFEE, name_);
        assertTrue(player_.estAttrape(MELOFEE));
    }
}
