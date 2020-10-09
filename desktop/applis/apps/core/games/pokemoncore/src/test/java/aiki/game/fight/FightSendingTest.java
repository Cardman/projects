package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.util.AffectedMove;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightSendingTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void effectWhileSendingBegin1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Item obj_ = fighter_.ficheObjet(data);
        EffectWhileSending effect_ = ((ItemForBattle)obj_).getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertEq(new Rate("3/2"), fighter_.getWeight());
    }

    @Test
    public void effectWhileSendingBegin2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(AIR_LOCK);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(ZENITH);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(!StringUtil.contains(FightMoves.climatsActifs(fight_, data), ZENITH));
        assertTrue(FightMoves.existenceAntiClimatActif(fight_, data));
    }

    @Test
    public void effectWhileSendingBegin3Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
    }

    @Test
    public void effectWhileSendingBegin4Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(ORAGE);
        fight_.enableGlobalMove(TEMPETESABLE);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(fight_.getEnabledMoves().getVal(ORAGE).isEnabled());
        assertTrue(!fight_.getEnabledMoves().getVal(TEMPETESABLE).isEnabled());
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
        assertTrue(!fight_.getStillEnabledMoves().getVal(TEMPETESABLE));
    }

    @Test
    public void effectWhileSendingBegin5Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(AIR_LOCK);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(!StringUtil.contains(FightMoves.climatsActifs(fight_, data), ZENITH));
        assertTrue(FightMoves.existenceAntiClimatActif(fight_, data));
    }

    @Test
    public void effectWhileSendingBegin6Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(FEUILLE_GARDE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affecterStatut(PEUR);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getStatusNbRound(PEUR));
        FightAbilities.enableAbilityByWeather(fight_,POKEMON_FOE_FIGHTER_ONE, data);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(0, fighter_.getStatusNbRound(PEUR));
    }

    @Test
    public void effectWhileSendingBegin7Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(AIR_LOCK);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(FEUILLE_GARDE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affecterStatut(PEUR);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        AbilityData obj_ = fighter_.ficheCapaciteActuelle(data);
        EffectWhileSending effect_ = obj_.getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertTrue(!StringUtil.contains(FightMoves.climatsActifs(fight_, data), ZENITH));
        assertTrue(FightMoves.existenceAntiClimatActif(fight_, data));
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getStatusNbRound(PEUR));
    }

    @Test
    public void effectWhileSendingBegin8Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(CABLE);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(GRAVITE);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Item obj_ = fighter_.ficheObjet(data);
        EffectWhileSending effect_ = ((ItemForBattle)obj_).getEffectSending().first();
        FightSending.effectWhileSendingBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, effect_, data);
        assertTrue(!fight_.getEnabledMoves().getVal(GRAVITE).isEnabled());
    }

    @Test
    public void effectSendingObjectBegin1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PIERRALLEGEE);
        pokemon_.setAbility(SECHERESSE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(AIR_LOCK);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(FEUILLE_GARDE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        FightSending.effectSendingObjectBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(new Rate("3/2"),fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWeight());
    }

    @Test
    public void effectPlate1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        FightSending.effectPlate(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
    }

    @Test
    public void effectPlate2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(BAIE_ENIGMA);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        FightSending.effectPlate(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
    }

    @Test
    public void effectPlate3Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PIECE_RUNE);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        FightSending.effectPlate(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
    }

    @Test
    public void effectPlate4Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        FightSending.effectPlate(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), DRAGON));
    }

    @Test
    public void effectPlate5Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setCurrentAbility(NULL_REF);
        FightSending.effectPlate(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), ELECTRIQUE));
    }

    @Test
    public void sendBegin1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        FightSending.sendBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        assertEq(2, fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1).size());
        assertTrue(fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1).containsObj((byte) 0));
        assertTrue(fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1).containsObj((byte) 1));
    }

    @Test
    public void sendBegin2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        FightSending.sendBegin(fight_,POKEMON_FOE_FIGHTER_ONE, data);
        assertEq(0, fight_.getFoeTeam().getPlayerFightersAgainstFoe().size());
        assertEq(2, fight_.getUserTeam().getPlayerFightersAgainstFoe().size());
        assertEq(1, fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 0).size());
        assertTrue(fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 0).containsObj((byte) 1));
        assertEq(1, fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1).size());
        assertTrue(fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1).containsObj((byte) 1));
    }

    @Test
    public void sendBegin3Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PIERRALLEGEE);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).affecterStatut(GEL);
        FightSending.sendBegin(fight_,POKEMON_FOE_FIGHTER_ONE, data);
        assertTrue(fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getStatusNbRound(GEL));
        assertEq(new Rate("3/2"), fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getWeight());
    }

    @Test
    public void sendBegin4Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).affecterStatut(GEL);
        FightSending.sendBegin(fight_,POKEMON_PLAYER_FIGHTER_ONE, data);
        StringList list_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getTypes();
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, DRAGON));
    }

    @Test
    public void sendBeginTeam1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        FightSending.sendBeginTeam(fight_,Fight.FOE, data);
        assertTrue(!fight_.getEnabledMoves().getVal(ZENITH).isEnabled());
        StringList list_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getTypes();
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, DRAGON));
    }

    private Fight endRelations(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void endRelations1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = endRelations(diff_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        fighter_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ONE, VAMPIGRAINE);
        fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ZERO)).add(JACKPOT);
        fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ONE)).add(JACKPOT);
        AffectedMove aff_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO));
        aff_.getActivity().enable();
        aff_.setMove(JACKPOT);
        aff_ = fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ONE));
        aff_.getActivity().enable();
        aff_.setMove(JACKPOT);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).enable();
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ONE)).enable();
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO), true);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ONE), true);
        FightSending.endRelations(fight_, POKEMON_FOE_FIGHTER_ZERO, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ONE)));
        assertEq(0, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ZERO)).size());
        assertEq(1, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ONE)).size());
        assertEq(JACKPOT, fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ONE)).first());
        assertTrue(!fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).getActivity().isEnabled());
        assertTrue(fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ONE)).getActivity().isEnabled());
        assertTrue(!fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).isEnabled());
        assertTrue(fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ONE)).isEnabled());
        assertTrue(!fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ZERO)));
        assertTrue(fighter_.getIncrUserAccuracy().getVal(new MoveTeamPosition(LIRE_ESPRIT, POKEMON_FOE_FIGHTER_ONE)));
    }

    private Fight disableEffectsExceptHp(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void disableEffectsExceptHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        FightAbilities.disableAbility(fight_, fighter_, NULL_REF, data);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void disableEffectsExceptHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void disableEffectsExceptHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(Rate.one());
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(MEDIC_NATURE);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        assertEq(new Rate("1"), f_.getRemainingHp());
        StringList types_ = f_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void disableEffectsExceptHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.setRemainedHp(Rate.one());
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(REGE_FORCE);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("1"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void disableEffectsExceptHp5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.setRemainedHp(new Rate("1872/100"));
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(REGE_FORCE);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("468/25"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void disableEffectsExceptHp6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = disableEffectsExceptHp(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.transformer(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO), (short) 5);
        FightSending.disableEffectsExceptHp(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(ARTIKODIN, f_.getCurrentName());
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0,f_.getStatusNbRound(SOMMEIL));
    }

    private Fight withdrawal(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void withdrawal1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        FightAbilities.disableAbility(fight_, fighter_, NULL_REF, data);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void withdrawal2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void withdrawal3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(Rate.one());
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(MEDIC_NATURE);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        assertEq(new Rate("1"), f_.getRemainingHp());
        StringList types_ = f_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void withdrawal4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.setRemainedHp(Rate.one());
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(REGE_FORCE);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("2173/300"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void withdrawal5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setDisappeared(true);
        f_.setNbPrepaRound((short) 1);
        f_.setNeedingToRecharge(true);
        f_.setRemainedHp(new Rate("1872/100"));
        f_.affecterStatut(SOMMEIL);
        f_.affecterStatut(LONGUE_CONFUSION_DOMMAGE);
        f_.affecterPseudoStatut(POKEMON_FOE_FIGHTER_ZERO, VAMPIGRAINE);
        f_.setCurrentAbility(REGE_FORCE);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(1,f_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void withdrawal6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = withdrawal(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.transformer(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO), (short) 5);
        FightSending.withdrawal(fight_, fighter_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(ARTIKODIN, f_.getCurrentName());
        assertEq(METEO, f_.getCurrentAbility());
        StringList types_ = f_.getTypes();
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
        assertTrue(!f_.isDisappeared());
        assertTrue(!f_.isNeedingToRecharge());
        assertEq(0, f_.getNbPrepaRound());
        assertEq(0,f_.getStatusRelatNbRound(new MoveTeamPosition(VAMPIGRAINE, POKEMON_FOE_FIGHTER_ZERO)));
        assertEq(0,f_.getStatusNbRound(LONGUE_CONFUSION_DOMMAGE));
        assertEq(0,f_.getStatusNbRound(SOMMEIL));
    }

    private Fight addFighterAgainstFoeTeam(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        DualFight dual_ = new DualFight();
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void addFighterAgainstFoeTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = addFighterAgainstFoeTeam(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightSending.addFighterAgainstFoeTeam(fight_, fighter_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 2).size());
        assertTrue(map_.getVal((byte) 2).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 2).containsObj((byte) 1));
    }

    @Test
    public void addFighterAgainstFoeTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = addFighterAgainstFoeTeam(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_FOUR;
        FightSending.addFighterAgainstFoeTeam(fight_, fighter_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertEq(2, map_.getVal((byte) 0).size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertEq(0, map_.getVal((byte) 1).size());
        assertEq(0, map_.getVal((byte) 2).size());
    }

    @Test
    public void addFighterAgainstFoeTeam3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = addFighterAgainstFoeTeam(diff_);
        TeamPosition fighter_ = POKEMON_FOE_FIGHTER_TWO;
        FightSending.addFighterAgainstFoeTeam(fight_, fighter_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(3, map_.size());
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 1));
        assertTrue(map_.getVal((byte) 0).containsObj((byte) 2));
        assertTrue(!map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(!map_.getVal((byte) 1).containsObj((byte) 1));
        assertTrue(!map_.getVal((byte) 1).containsObj((byte) 2));
        assertTrue(!map_.getVal((byte) 2).containsObj((byte) 0));
        assertTrue(!map_.getVal((byte) 2).containsObj((byte) 1));
        assertTrue(!map_.getVal((byte) 2).containsObj((byte) 2));
    }

    private Fight effectTeamWhileSendingFoeFighter(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectTeamWhileSendingFoeFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("13111/800"), f_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertTrue(f_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertTrue(f_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(1, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(1, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(1, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        //1873*7/800 = 13111/800
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        fight_.setFullHealing(true);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("2"),f_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = TOILE_GLUANTE;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("2"),f_.getRemainingHp());
        assertEq(-1, f_.getStatisBoost().getVal(Statistic.SPEED));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        String move_ = TOILE_GLUANTE;
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("2"),f_.getRemainingHp());
        assertEq(0, f_.getStatisBoost().getVal(Statistic.SPEED));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectTeamWhileSendingFoeFighter1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectTeamWhileSendingFoeFighter(diff_);
        fight_.setSimulation(true);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        CustList<Effect> effects_ = data.getMove(move_).getEffects();
        EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) effects_.last();
        fight_.setSending(true);
        assertTrue(FightSuccess.successfulMove(fight_,fighter_,fighter_,move_,effects_.size()-1,true,data).isSuccessful());
        FightSending.effectTeamWhileSendingFoeFighter(fight_, fighter_, move_, eff_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertTrue(f_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectsTeamWhileSendingFoeFighter(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("13111/800"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertTrue(f_.estKo());
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(GARDE_MAGIK);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(1, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(1, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.affecterTypes(ACIER);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICS_TOXIK;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.affecterTypes(POISON);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        assertEq(new Rate("1873/100"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.zero(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        String move_ = PICOTS_BIS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setCurrentAbility(GARDE_MAGIK);
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertEq(new Rate("5619/320"), f_.getRemainingHp());
        assertEq(0, f_.getStatusNbRound(POISON_ST));
        assertEq(0, f_.getStatusNbRound(POISON_GRAVE));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(move_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectsTeamWhileSendingFoeFighter1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectsTeamWhileSendingFoeFighter(diff_);
        fight_.setSimulation(true);
        String move_ = PICOTS;
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(move_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.setRemainedHp(new Rate("2"));
        FightSending.effectsTeamWhileSendingFoeFighter(fight_, fighter_, diff_, data);
        f_ = fight_.getFighter(fighter_);
        assertTrue(f_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectWhileSendingAbility(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(TELECHARGE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private Fight effectWhileSendingAbility2(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(CALQUE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectWhileSendingAbility1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = TELECHARGE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(TELECHARGE, fighter_.getCurrentAbility());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(TELECHARGE, fighter_.getCurrentAbility());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility2(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(CALQUE, fighter_.getCurrentAbility());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility2(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setCurrentAbility(NULL_REF);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(NULL_REF, fighter_.getCurrentAbility());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility2(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.setCurrentAbility(MULTITYPE);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(MULTITYPE, fighter_.getCurrentAbility());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(TELECHARGE, fighter_.getCurrentAbility());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlace(Fighter.BACK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setGroundPlace(Fighter.BACK);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 6);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(CALQUE, fighter_.getCurrentAbility());
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectWhileSendingAbility1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingAbility(diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = ALEA_STAT;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        fighter_.variationBoostStatistique(Statistic.ATTACK, (byte) 5);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 5);
        FightSending.effectWhileSendingAbility(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(ALEA_STAT, fighter_.getCurrentAbility());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectWhileSending(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(TELECHARGE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectWhileSending1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSending(diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = fighter_.getCurrentAbility();
        FightSending.effectWhileSending(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
    }

    @Test
    public void effectWhileSending2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = TELECHARGE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSending(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertTrue(!fight_.getStillEnabledMoves().getVal(ZENITH));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
    }

    @Test
    public void effectWhileSending3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = PIERRALLEGEE;
        fighter_.backUpObject(object_);
        ItemForBattle o_ = (ItemForBattle) data.getItem(object_);
        FightSending.effectWhileSending(fight_, thrower_, o_.getEffectSending().first(), diff_, data);
        assertEq(new Rate("3/2"), fighter_.getWeight());
    }

    @Test
    public void effectWhileSending4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String ab_ = CALQUE;
        fighter_.setAbility(ab_);
        fighter_.setCurrentAbility(ab_);
        FightSending.effectWhileSending(fight_, thrower_, data.getAbility(ab_).getEffectSending().first(), diff_, data);
        assertEq(TELECHARGE, fighter_.getCurrentAbility());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
    }

    private Fight effectWhileSendingObject(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(TELECHARGE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectWhileSendingObject1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingObject(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        FightSending.effectWhileSendingObject(fight_, thrower_, diff_, data);
        assertEq(new Rate("3"), fighter_.getWeight());
    }

    @Test
    public void effectWhileSendingObject2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingObject(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        FightSending.effectWhileSendingObject(fight_, thrower_, diff_, data);
        assertEq(new Rate("3"), fighter_.getWeight());
    }

    @Test
    public void effectWhileSendingObject3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingObject(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BOUE_NOIRE);
        FightSending.effectWhileSendingObject(fight_, thrower_, diff_, data);
        assertEq(new Rate("3"), fighter_.getWeight());
    }

    @Test
    public void effectWhileSendingObject4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = effectWhileSendingObject(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(PIERRALLEGEE);
        FightSending.effectWhileSendingObject(fight_, thrower_, diff_, data);
        assertEq(new Rate("3/2"), fighter_.getWeight());
    }

    private Fight sending(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(COPIE, (short) 10);
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data, moves_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(MAGNET);
        allyPokemon_.setAbility(SECHERESSE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 4);
        allyPokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(TELECHARGE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void sending1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = sending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setCurrentAbility(PRESSION);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        FightSending.sending(fight_, thrower_, diff_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(1, fighter_.getStatusNbRound(POISON_ST));
        assertEq(new Rate("3"), fighter_.getWeight());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void sending2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = sending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(PIERRALLEGEE);
        fighter_.setCurrentAbility(TELECHARGE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        FightSending.sending(fight_, thrower_, diff_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(1, fighter_.getStatusNbRound(POISON_ST));
        assertEq(new Rate("3/2"), fighter_.getWeight());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void sending3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = sending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(PLAQUE_DRACO);
        fighter_.setCurrentAbility(MULTITYPE);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        FightSending.sending(fight_, thrower_, diff_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(1, fighter_.getStatusNbRound(POISON_ST));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(new Rate("3"), fighter_.getWeight());
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, DRAGON));
    }

    @Test
    public void sending4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Fight fight_ = sending(diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(PLAQUE_DRACO);
        fighter_.setCurrentAbility(SECHERESSE);
        fighter_.affecterStatut(GEL);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICS_TOXIK);
        FightSending.sending(fight_, thrower_, diff_, data);
        Team userTeam_ = fight_.getUserTeam();
        ByteMap<Bytes> map_ = userTeam_.getPlayerFightersAgainstFoe();
        assertEq(2, map_.getVal((byte) 1).size());
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 0));
        assertTrue(map_.getVal((byte) 1).containsObj((byte) 1));
        assertEq(LgInt.one(), fight_.getFoeTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICS_TOXIK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(new Rate("3"), fighter_.getWeight());
        assertEq(1, fighter_.getStatusNbRound(POISON_ST));
        assertEq(0, fighter_.getStatusNbRound(GEL));
        StringList types_ = fighter_.getTypes();
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    private Fight sendSubstitutes(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void sendSubstitutes1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(44, fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        assertEq(new Rate("18"), fighter_.getWonExpSinceLastLevel());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
    }

    @Test
    public void sendSubstitutes7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(100, fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
    }

    @Test
    public void sendSubstitutes8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(2, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(100, fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutes9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(100, fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void sendSubstitutes10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TETARTE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(100, fighter_.getLevel());
        assertEq(new Rate("0"), fighter_.getWonExp());
        assertEq(new Rate("0"), fighter_.getWonExpSinceLastLevel());
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void sendSubstitutes11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte)0, Fighter.BACK);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(1, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutes12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutes13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17,new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutes14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void sendSubstitutes15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutes16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void sendSubstitutes17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
    }

    @Test
    public void sendSubstitutes18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_THREE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_FOUR);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes22Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
//        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_FOUR);
//        assertEq(1, fighter_.getGroundPlace());
//        assertEq(1, fighter_.getGroundPlaceSubst());
//        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes23Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
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
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_THREE);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
//        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_FOUR);
//        assertEq(1, fighter_.getGroundPlace());
//        assertEq(1, fighter_.getGroundPlaceSubst());
//        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(2, fighter_.getGroundPlace());
        assertEq(2, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutes24Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.getRemainingHp().affect(Rate.one());
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
    }

    @Test
    public void sendSubstitutes25Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.getRemainingHp().affect(Rate.one());
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = sendSubstitutes(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getRemainingHp().affect(Rate.one());
        FightArtificialIntelligence.choiceForSubstituing(fight_, data);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightSending.sendSubstitutes(fight_, diff_, player_, data);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(0, fighter_.getGroundPlace());
        assertEq(0, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertEq(1, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(1, fighter_.getGroundPlaceSubst());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        assertEq(Fighter.BACK, fighter_.getGroundPlace());
        assertEq(Fighter.BACK, fighter_.getGroundPlaceSubst());
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
    }

    private Fight firstEffectWhileSendingTeam(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = IndexConstants.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = IndexConstants.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_user, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void firstEffectWhileSendingTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(MULTITYPE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = firstEffectWhileSendingTeam(partnersMoves_, foesMoves_, player_, diff_);
        FightSending.firstEffectWhileSendingTeam(fight_, Fight.PLAYER, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), DRAGON));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
    }

    @Test
    public void firstEffectWhileSendingTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(TELECHARGE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = firstEffectWhileSendingTeam(partnersMoves_, foesMoves_, player_, diff_);
        FightSending.firstEffectWhileSendingTeam(fight_, Fight.PLAYER, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), EAU));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
    }

    @Test
    public void firstEffectWhileSendingTeams1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(TELECHARGE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)17, new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = firstEffectWhileSendingTeam(partnersMoves_, foesMoves_, player_, diff_);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getTypes().size());
        assertTrue(StringUtil.contains(fighter_.getTypes(), EAU));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
    }
}
