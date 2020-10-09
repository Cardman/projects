package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Before;
import org.junit.Test;

import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;


public class TeamTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void new_Team_DataBase_1Test() {
        Team team_ = new Team(data);
        assertEq(11, team_.getEnabledMoves().size());
        assertTrue(team_.getEnabledMoves().contains(VENT_ARRIERE));
        assertTrue(team_.getEnabledMoves().contains(VENT_ARRIERE_BIS));
        assertTrue(team_.getEnabledMoves().contains(REGARD_NOIR));
        assertTrue(team_.getEnabledMoves().contains(BRUME));
        assertTrue(team_.getEnabledMoves().contains(ANTI_SOIN));
        assertTrue(team_.getEnabledMoves().contains(TOUR_RAPIDE));
        assertTrue(team_.getEnabledMoves().contains(ANTI_BRUME));
        assertTrue(team_.getEnabledMoves().contains(RUNE_PROTECT));
        assertTrue(team_.getEnabledMoves().contains(AIR_VEINARD));
        assertTrue(team_.getEnabledMoves().contains(PROTECTION));
        assertTrue(team_.getEnabledMoves().contains(MUR_LUMIERE));
        ActivityOfMove activity_ = team_.getEnabledMoves().getVal(VENT_ARRIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(VENT_ARRIERE_BIS);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(REGARD_NOIR);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(ANTI_SOIN);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(TOUR_RAPIDE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(ANTI_BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(RUNE_PROTECT);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(AIR_VEINARD);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(PROTECTION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(5, team_.getEnabledMovesWhileSendingFoe().size());
        assertTrue(team_.getEnabledMovesWhileSendingFoe().contains(PIEGE_DE_ROC));
        assertTrue(team_.getEnabledMovesWhileSendingFoe().contains(PICS_TOXIK));
        assertTrue(team_.getEnabledMovesWhileSendingFoe().contains(PICOTS));
        assertTrue(team_.getEnabledMovesWhileSendingFoe().contains(PICOTS_BIS));
        assertTrue(team_.getEnabledMovesWhileSendingFoe().contains(TOILE_GLUANTE));
        assertTrue(!team_.getEnabledMovesWhileSendingFoe().getVal(PIEGE_DE_ROC));
        assertTrue(!team_.getEnabledMovesWhileSendingFoe().getVal(PICS_TOXIK));
        assertTrue(!team_.getEnabledMovesWhileSendingFoe().getVal(PICOTS));
        assertTrue(!team_.getEnabledMovesWhileSendingFoe().getVal(PICOTS_BIS));
        assertTrue(!team_.getEnabledMovesWhileSendingFoe().getVal(TOILE_GLUANTE));
        assertEq(5, team_.getEnabledMovesWhileSendingFoeUses().size());
        assertEq(LgInt.zero(), team_.getEnabledMovesWhileSendingFoeUses().getVal(PIEGE_DE_ROC));
        assertEq(LgInt.zero(), team_.getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(LgInt.zero(), team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS));
        assertEq(LgInt.zero(), team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS_BIS));
        assertEq(LgInt.zero(), team_.getEnabledMovesWhileSendingFoeUses().getVal(TOILE_GLUANTE));
//        assertTrue(team_.getEnabledMovesWhileSendingFoeUses().contains(PIEGE_DE_ROC));
//        assertTrue(team_.getEnabledMovesWhileSendingFoeUses().contains(PICS_TOXIK));
//        assertTrue(team_.getEnabledMovesWhileSendingFoeUses().contains(PICOTS));
//        assertTrue(team_.getEnabledMovesWhileSendingFoeUses().contains(PICOTS_BIS));
//        assertTrue(team_.getEnabledMovesWhileSendingFoeUses().contains(TOILE_GLUANTE));
//        assertEq(5, team_.getEnabledMovesWhileSendingFoeUses().getKeys(LgInt.zero()).size());
        assertEq(3, team_.getEnabledMovesByGroup().size());
        activity_ = team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_DE_FEU,AIRE_D_HERBE));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_DE_FEU,AIRE_D_EAU));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE));
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, team_.getSuccessfulMovesRound().size());
        StringMap<Integer> listUsesRound_ = team_.getNbUsesMovesRound();
//        assertEq(3, listUsesRound_.getKeys(0).size());
        assertEq(3, listUsesRound_.size());
        assertEq(0, listUsesRound_.getVal(AIRE_D_EAU));
        assertEq(0, listUsesRound_.getVal(AIRE_DE_FEU));
        assertEq(0, listUsesRound_.getVal(AIRE_D_HERBE));
//        assertTrue(listUsesRound_.contains(AIRE_D_EAU));
//        assertTrue(listUsesRound_.contains(AIRE_DE_FEU));
//        assertTrue(listUsesRound_.contains(AIRE_D_HERBE));
        assertEq(1, team_.getNbUsesMoves().size());
        assertEq(0, team_.getNbUsesMoves().getVal(CASSE));
        assertEq(0, team_.getHealAfterSet().size());
        assertEq(0, team_.getMovesAnticipationSet().size());
    }

    @Test
    public void initEquipeUtilisateur1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 1, data);
        assertEq(2, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        assertEq(1, team_.getHealAfterSet().size());
        assertEq(1, team_.getMovesAnticipationSet().size());
        assertTrue(StringUtil.contains(team_.getMovesAnticipationSet(), PRESCIENCE));
        assertTrue(StringUtil.contains(team_.getHealAfterSet(), VOEU));
    }

    @Test
    public void initEquipeUtilisateur2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().get(0);
        pk_.setRemainingHp(Rate.zero());
        team_.initEquipeUtilisateur(player_, diff_, (short) 1, data);
        assertEq(2, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeUtilisateur3Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        assertEq(2, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(1, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(2, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 1);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(2, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 1);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeUtilisateur4Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(ARTIKODIN);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, ally_);
        assertEq(4, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 0).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 1).isBelongingToPlayer());
        assertEq(1, team_.getMembers().getVal((byte) 2).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 2).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 3).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 3).isBelongingToPlayer());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(2, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 1);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(2, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 1);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeUtilisateur5Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(ARTIKODIN);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, ally_);
        assertEq(4, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 0).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 1).isBelongingToPlayer());
        assertEq(1, team_.getMembers().getVal((byte) 2).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 2).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 3).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 3).isBelongingToPlayer());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(2, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 1);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(2, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 1);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeUtilisateur6Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,Sex.GIRL,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PTITARD);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, ally_);
        assertEq(4, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 0).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 1).isBelongingToPlayer());
        assertEq(1, team_.getMembers().getVal((byte) 2).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 2).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 3).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 3).isBelongingToPlayer());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(2, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 1);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(2, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 1);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        assertEq(Gender.MALE, team_.getMembers().getVal((byte) 2).getGender());
        assertEq(Gender.MALE, team_.getMembers().getVal((byte) 2).getCurrentGender());
        assertEq(Gender.NO_GENDER, team_.getMembers().getVal((byte) 3).getGender());
        assertEq(Gender.NO_GENDER, team_.getMembers().getVal((byte) 3).getCurrentGender());
    }

    @Test
    public void initEquipeUtilisateur7Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,Sex.BOY,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PTITARD);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, ally_);
        assertEq(4, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 0).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertTrue(team_.getMembers().getVal((byte) 1).isBelongingToPlayer());
        assertEq(1, team_.getMembers().getVal((byte) 2).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 2).isBelongingToPlayer());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 3).getGroundPlace());
        assertTrue(!team_.getMembers().getVal((byte) 3).isBelongingToPlayer());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(2, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 1);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(2, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 1);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
        assertEq(Gender.FEMALE, team_.getMembers().getVal((byte) 2).getGender());
        assertEq(Gender.FEMALE, team_.getMembers().getVal((byte) 2).getCurrentGender());
        assertEq(Gender.NO_GENDER, team_.getMembers().getVal((byte) 3).getGender());
        assertEq(Gender.NO_GENDER, team_.getMembers().getVal((byte) 3).getCurrentGender());
    }

    @Test
    public void initEquipeAdversaire1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer pokemon_ = new PkTrainer();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(pokemon_);
        team_.initEquipeAdversaire(player_, foeTeam_, diff_, (short) 1, data);
        assertEq(1, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(POKE_BALL, data.getDefaultBall());
        assertEq(POKE_BALL, team_.getMembers().getVal((byte) 0).getUsedBallCatching());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeAdversaire2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer pokemon_ = new PkTrainer();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(pokemon_);
        team_.initEquipeAdversaire(player_, foeTeam_, diff_, (short) 1, data);
        assertEq(1, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getUsedBallCatching());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initEquipeAdversaire3Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer pokemon_ = new PkTrainer();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(pokemon_);
        pokemon_ = new PkTrainer();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        pokemon_.setMoves(new StringList(OEIL_MIRACLE));
        foeTeam_.add(pokemon_);
        team_.initEquipeAdversaire(player_, foeTeam_, diff_, (short) 1, data);
        assertEq(2, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(Fighter.BACK, team_.getMembers().getVal((byte) 1).getGroundPlace());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initPokemonSauvage1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        team_.initPokemonSauvage(player_, diff_, 0, pokemon_, data);
        assertEq(1, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(POKE_BALL, data.getDefaultBall());
        assertEq(POKE_BALL, team_.getMembers().getVal((byte) 0).getUsedBallCatching());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initPokemonSauvage2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        team_.initPokemonSauvage(player_, diff_, 0, pokemon_, data);
        assertEq(1, team_.getMembers().size());
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getUsedBallCatching());
        assertEq(1, team_.getMovesAnticipation().size());
        assertEq(1, team_.getMovesAnticipation().getVal(PRESCIENCE).size());
        Anticipation ant_ = team_.getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        assertEq(Rate.zero(), ant_.getDamage());
        assertEq(0, ant_.getNbRounds());
        assertTrue(!ant_.isIncrementing());
        assertEq(1, team_.getHealAfter().size());
        assertEq(1, team_.getHealAfter().getVal(VOEU).size());
        StacksOfUses stack_ = team_.getHealAfter().getVal(VOEU).getVal((byte) 0);
        assertEq(0, stack_.getNbRounds());
        assertTrue(!stack_.isFirstStacked());
        assertTrue(!stack_.isLastStacked());
    }

    @Test
    public void initRelationsCombattant1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        TeamPosition fighterCoordsOne_ = new TeamPosition(Fight.PLAYER, (byte)0);
        TeamPosition fighterCoordsTwo_ = new TeamPosition(Fight.PLAYER, (byte)1);
        EqList<TeamPosition> fightersCoords_ = new EqList<TeamPosition>();
        fightersCoords_.add(fighterCoordsOne_);
        fightersCoords_.add(fighterCoordsTwo_);
        team_.initRelationsCombattant(fightersCoords_, data);
        Fighter fighter_ = team_.getMembers().getVal((byte) 0);
        assertEq(24, fighter_.getStatusRelat().size());
//        assertEq(22, fighter_.getStatusRelat().getKeys((short) 0).size());
        assertEq(24, getNbStatusRelatByRounds(fighter_,(short) 0));
        assertEq(6, fighter_.getTrackingMoves().size());
        assertEq(2, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsOne_)).size());
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsTwo_)).size());
        assertEq(4, fighter_.getTrappingMoves().size());
        assertEq(4, fighter_.getIncrUserAccuracy().size());
        fighter_ = team_.getMembers().getVal((byte) 1);
        assertEq(24, fighter_.getStatusRelat().size());
//        assertEq(22, fighter_.getStatusRelat().getKeys((short) 0).size());
        assertEq(24, getNbStatusRelatByRounds(fighter_,(short) 0));
        assertEq(6, fighter_.getTrackingMoves().size());
        assertEq(2, fighter_.getPrivateMoves().size());
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsOne_)).size());
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,fighterCoordsTwo_)).size());
        assertEq(4, fighter_.getTrappingMoves().size());
        assertEq(4, fighter_.getIncrUserAccuracy().size());
    }

    @Test
    public void ajouterCombattantsContreAdv1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.ajouterCombattantsContreAdv((byte) 1,(byte) 0);
        assertEq(2, team_.getPlayerFightersAgainstFoe().size());
        assertTrue(team_.getPlayerFightersAgainstFoe().contains((byte) 0));
        assertTrue(team_.getPlayerFightersAgainstFoe().contains((byte) 1));
        assertEq(0, team_.getPlayerFightersAgainstFoe().getVal((byte) 0).size());
        assertEq(1, team_.getPlayerFightersAgainstFoe().getVal((byte) 1).size());
        assertTrue(team_.getPlayerFightersAgainstFoe().getVal((byte) 1).containsObj((byte) 0));
        assertEq(2, team_.getPlayerFightersAgainstFoeSet().size());
        assertEq(0, team_.getPlayerFightersAgainstFoeVal((byte) 0).size());
        assertEq(1, team_.getPlayerFightersAgainstFoeVal((byte) 1).size());
        assertTrue(team_.getPlayerFightersAgainstFoeVal((byte) 1).containsObj((byte) 0));
    }

    @Test
    public void move1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setGroundPlace((byte) 2);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.move((byte) 0);
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(1, team_.getMembers().getVal((byte) 1).getGroundPlace());
    }

    @Test
    public void move2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.move((byte) 1);
        assertEq(1, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(2, team_.getMembers().getVal((byte) 1).getGroundPlace());
    }

    @Test
    public void move3Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.move((byte) 0);
        assertEq(0, team_.getMembers().getVal((byte) 0).getGroundPlace());
        assertEq(1, team_.getMembers().getVal((byte) 1).getGroundPlace());
    }

    @Test
    public void fightersAtCurrentPlace1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        Bytes positions_ = team_.fightersAtCurrentPlace((short) 0);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 1));
        positions_ = team_.fightersAtCurrentPlace((short) 1);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 0));
        positions_ = team_.fightersAtCurrentPlace(Fighter.BACK);
        assertEq(2, positions_.size());
        assertTrue(positions_.containsObj((byte) 2));
        assertTrue(positions_.containsObj((byte) 3));
    }

    @Test
    public void fightersAtCurrentPlaceIndex1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        Bytes positions_ = team_.fightersAtCurrentPlaceIndex((short) 0, true);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 1));
        positions_ = team_.fightersAtCurrentPlaceIndex((short) 1, true);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 0));
        positions_ = team_.fightersAtCurrentPlaceIndex(Fighter.BACK, true);
        assertEq(0, positions_.size());
    }

    @Test
    public void fightersAtCurrentPlaceIndex2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, allyTeam_);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlaceSubst((byte) 0);
        Bytes positions_ = team_.fightersAtCurrentPlaceIndex((short) 0, true);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 0));
        positions_ = team_.fightersAtCurrentPlaceIndex((short) 1, true);
        assertEq(0, positions_.size());
        positions_ = team_.fightersAtCurrentPlaceIndex(Fighter.BACK, true);
        assertEq(0, positions_.size());
    }

    @Test
    public void fightersAtCurrentPlaceIndex3Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        Bytes positions_ = team_.fightersAtCurrentPlaceIndex((short) 0, false);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 1));
        positions_ = team_.fightersAtCurrentPlaceIndex((short) 1, false);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 0));
        positions_ = team_.fightersAtCurrentPlaceIndex(Fighter.BACK, false);
        assertEq(0, positions_.size());
    }

    @Test
    public void fightersAtCurrentPlaceIndex4Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, allyTeam_);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlaceSubst((byte) 0);
        Bytes positions_ = team_.fightersAtCurrentPlaceIndex((short) 0, false);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 5));
        positions_ = team_.fightersAtCurrentPlaceIndex((short) 1, false);
        assertEq(1, positions_.size());
        assertTrue(positions_.containsObj((byte) 0));
        positions_ = team_.fightersAtCurrentPlaceIndex(Fighter.BACK, false);
        assertEq(0, positions_.size());
    }

    @Test
    public void substituteAtIndex1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlace(Fighter.BACK);
        team_.getMembers().getVal((byte) 2).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlaceSubst(Fighter.BACK);
        team_.getMembers().getVal((byte) 2).setGroundPlaceSubst((byte) 0);
        assertEq(1, team_.substituteAtIndex((short) 0));
        assertEq(3, team_.substituteAtIndex((short) 1));
        assertEq(Fighter.BACK, team_.substituteAtIndex((short) 2));
        assertEq(Fighter.BACK, team_.substituteAtIndex(Fighter.BACK));
    }

    @Test
    public void indexOfSubstitute1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlace(Fighter.BACK);
        team_.getMembers().getVal((byte) 2).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlaceSubst(Fighter.BACK);
        team_.getMembers().getVal((byte) 2).setGroundPlaceSubst((byte) 0);
        assertEq(0, team_.indexOfSubstitute((byte) 1));
        assertEq(1, team_.indexOfSubstitute((byte) 3));
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, team_.indexOfSubstitute((byte) 4));
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, team_.indexOfSubstitute(Fighter.BACK));
    }

    @Test
    public void fighterAtIndex1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlace(Fighter.BACK);
        team_.getMembers().getVal((byte) 3).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 1).setGroundPlaceSubst(Fighter.BACK);
        team_.getMembers().getVal((byte) 3).setGroundPlaceSubst((byte) 0);
        assertEq(0, team_.fighterAtIndex((short) 0));
        assertEq(1, team_.fighterAtIndex((short) 1));
        assertEq(3, team_.fighterAtIndex((short) 2));
        assertEq(4, team_.fighterAtIndex((short) 3));
        assertEq(Fighter.BACK, team_.fighterAtIndex((short) 4));
        assertEq(Fighter.BACK, team_.fighterAtIndex(Fighter.BACK));
    }

    @Test
    public void fighterAtIndex2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        allyTeam_.add(new PkTrainer(pokemon_, new StringList(PISTOLET_A_O)));
        team_.initEquipeUtilisateur(player_, diff_, (byte) 1, (byte) 2, data, allyTeam_);
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlace((byte) 0);
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.getMembers().getVal((byte) 5).setGroundPlaceSubst((byte) 0);
        assertEq(0, team_.fighterAtIndex((short) 0));
        assertEq(1, team_.fighterAtIndex((short) 1));
        assertEq(3, team_.fighterAtIndex((short) 2));
        assertEq(4, team_.fighterAtIndex((short) 3));
        assertEq(Fighter.BACK, team_.fighterAtIndex((short) 4));
        assertEq(Fighter.BACK, team_.fighterAtIndex(Fighter.BACK));
    }

    @Test
    public void clearSuccessfuleMovesRound1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.getNbUsesMovesRound().put(AIRE_DE_FEU, 1);
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, 2);
        team_.getMembers().getVal((byte) 0).setSuccessfulMove(true);
        team_.clearSuccessfuleMovesRound();
        assertEq(0, team_.getSuccessfulMovesRound().size());
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertTrue(!team_.getMembers().getVal((byte) 0).isSuccessfulMove());
    }

    @Test
    public void addSuccessfulMoveRound1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.addSuccessfulMoveRound(AIRE_D_EAU);
        ActivityOfMove activity_ = team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE));
        assertTrue(!activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
        team_.addSuccessfulMoveRound(AIRE_D_HERBE);
        activity_ = team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE));
        assertTrue(activity_.isEnabled());
        assertEq(0, activity_.getNbTurn());
    }

    @Test
    public void useItemsEndRound1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setUsingItem(true);
        team_.getMembers().getVal((byte) 1).setCurrentAbility(NULL_REF);
        team_.useItemsEndRound(data);
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getItem());
        assertEq(MAGNET, team_.getMembers().getVal((byte) 0).getLastUsedItem());
    }

    @Test
    public void useItemsEndRound2Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setUsingItem(true);
        team_.getMembers().getVal((byte) 1).setCurrentAbility(SYMBIOSE);
        team_.useItemsEndRound(data);
        assertEq(MAGNET, team_.getMembers().getVal((byte) 0).getItem());
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getLastUsedItem());
    }

    @Test
    public void useItemsEndRound3Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 0).setUsingItem(true);
        team_.getMembers().getVal((byte) 1).setCurrentAbility(SYMBIOSE);
        team_.useItemsEndRound(data);
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getItem());
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getLastUsedItem());
    }

    @Test
    public void useItemsEndRound4Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getMembers().getVal((byte) 1).setCurrentAbility(NULL_REF);
        team_.useItemsEndRound(data);
        assertEq(MAGNET, team_.getMembers().getVal((byte) 0).getItem());
        assertEq(NULL_REF, team_.getMembers().getVal((byte) 0).getLastUsedItem());
    }

    @Test
    public void setNbKoRound1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.setNbKoRound((byte) 2);
        team_.setNbKoPreviousRound((byte) 4);
        team_.setNbKoRound();
        assertEq(2, team_.getNbKoPreviousRound());
        assertEq(0, team_.getNbKoRound());
    }

    @Test
    public void chooseMoves1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        Fighter fighter_ = team_.getMembers().getVal((byte) 0);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        fighter_ = team_.getMembers().getVal((byte) 1);
        fighter_.setFirstChosenMove(OEIL_MIRACLE);
        team_.chooseMoves();
        fighter_ = team_.getMembers().getVal((byte) 0);
        assertEq(JACKPOT, fighter_.getFinalChosenMove());
        fighter_ = team_.getMembers().getVal((byte) 1);
        assertEq(OEIL_MIRACLE, fighter_.getFinalChosenMove());
    }

    @Test
    public void initRoundTeam1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 2, data);
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.getNbUsesMovesRound().put(AIRE_DE_FEU, 1);
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, 2);
        team_.setNbKoRound((byte) 2);
        team_.setNbKoPreviousRound((byte) 4);
        Fighter fighter_ = team_.getMembers().getVal((byte) 0);
        fighter_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        fighter_ = team_.getMembers().getVal((byte) 1);
        fighter_.setFirstChosenMove(OEIL_MIRACLE);
        team_.initRoundTeam();
        assertEq(0, team_.getSuccessfulMovesRound().size());
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_DE_FEU));
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_D_EAU));
        assertEq(0, team_.getNbUsesMovesRound().getVal(AIRE_D_HERBE));
        assertEq(2, team_.getNbKoPreviousRound());
        assertEq(0, team_.getNbKoRound());
        fighter_ = team_.getMembers().getVal((byte) 0);
        assertEq(JACKPOT, fighter_.getFinalChosenMove());
        fighter_ = team_.getMembers().getVal((byte) 1);
        assertEq(OEIL_MIRACLE, fighter_.getFinalChosenMove());
    }

    @Test
    public void notKoPartnersWithoutStatus1Test() {
        Difficulty diff_= new Difficulty();
        Team team_ = new Team(data);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setRemainingHp(Rate.zero());
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.getStatus().add(GEL);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        team_.initEquipeUtilisateur(player_, diff_, (short) 1, data);
        Bytes list_ = team_.notKoPartnersWithoutStatus((byte) 0);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj((byte) 3));
    }

    int getNbStatusRelatByRounds(Fighter _f, short _nbRounds) {
        int i_ = IndexConstants.SIZE_EMPTY;
        for (EntryCust<MoveTeamPosition, Short> e: _f.getStatusRelat().entryList()) {
            if (NumberUtil.eq(e.getValue(), _nbRounds)) {
                i_++;
            }
        }
        return i_;
    }
}
