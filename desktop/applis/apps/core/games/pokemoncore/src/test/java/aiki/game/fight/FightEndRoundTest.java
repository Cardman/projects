package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import aiki.game.fight.actions.Action;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.status.Status;
import aiki.game.fight.animations.AnimationAbsorb;
import aiki.game.fight.animations.AnimationEffect;
import aiki.game.fight.animations.AnimationHealing;
import aiki.game.fight.animations.AnimationKo;
import aiki.game.fight.animations.AnimationRecoil;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.MovesAbilities;
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
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public class FightEndRoundTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    private Fight processActivity(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void processActivity1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, EMBARGO, thrower_, false, data);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        activity_.enable();
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, EMBARGO, thrower_, false, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        activity_.enable();
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, ANNEAU_HYDRO, thrower_, false, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
    }

    @Test
    public void processActivity4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        activity_.enable();
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(VOL_MAGNETIK, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(VOL_MAGNETIK);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(1, protected_.size());
        assertTrue(StringUtil.contains(protected_, SOL));
    }

    @Test
    public void processActivity6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(VOL_MAGNETIK, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(VOL_MAGNETIK);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, VOL_MAGNETIK, thrower_, false, data);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(0, protected_.size());
    }

    @Test
    public void processActivity7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(TROU, data);
        fight_.getFighter(thrower_).activerAttaqueAntiImmu(RACINES);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        FightEndRound.processActivity(fight_, thrower_, RACINES, thrower_, false, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(1, protected_.size());
        assertTrue(StringUtil.contains(protected_, VOL));
        activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueBlocantLanceur(ROULADE);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesConstChoices().getVal(ROULADE);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, ROULADE, thrower_, false, data);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(thrower_).enableCounteringMoves(NUEE_DE_POUDRE);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, NUEE_DE_POUDRE, thrower_, false, data);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(thrower_).enableChangingMovesTypes(ELECTRISATION);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledChangingTypesMoves().getVal(ELECTRISATION);
        fight_.setCurrentActivity(activity_);
        FightEndRound.processActivity(fight_, thrower_, ELECTRISATION, thrower_, false, data);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        AffectedMove affected_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO));
        affected_.setMove(SEISME);
        affected_.getActivity().enableReset();
        fight_.setCurrentActivity(affected_.getActivity());
        FightEndRound.processActivity(fight_, thrower_, ENCORE, POKEMON_PLAYER_FIGHTER_ZERO, true, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO)).getActivity();
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ONE)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        AffectedMove affected_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO));
        affected_.setMove(SEISME);
        affected_.getActivity().enableReset();
        fight_.setCurrentActivity(affected_.getActivity());
        affected_.getActivity().setNbTurn((short)8);
        FightEndRound.processActivity(fight_, thrower_, ENCORE, POKEMON_PLAYER_FIGHTER_ZERO, true, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ONE)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void processActivity13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processActivity(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        AffectedMove affected_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO));
        affected_.setMove(SEISME);
        affected_.getActivity().enableReset();
        fight_.setCurrentActivity(affected_.getActivity());
        affected_.getActivity().setNbTurn((short)7);
        FightEndRound.processActivity(fight_, thrower_, ENCORE, POKEMON_PLAYER_FIGHTER_ZERO, true, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO)).getActivity();
        assertEq(8, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ONE)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }
    private Fight incrementNumberRounds(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void incrementNumberRounds1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(DANSE_PLUIE);
        FightEndRound.incrementNumberRounds(fight_, POKEMON_PLAYER_FIGHTER_ZERO, DANSE_PLUIE, data);
        assertEq(0, fight_.getEnabledMoves().getVal(DANSE_PLUIE).getNbTurn());
    }

    @Test
    public void incrementNumberRounds2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightEndRound.incrementNumberRounds(fight_, thrower_, EMBARGO, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        activity_.enable();
        FightEndRound.incrementNumberRounds(fight_, thrower_, EMBARGO, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMoves().getVal(EMBARGO);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        activity_.enable();
        FightEndRound.incrementNumberRounds(fight_, thrower_, ANNEAU_HYDRO, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMovesEndRound().getVal(ANNEAU_HYDRO);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(!activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        activity_.enable();
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(1, protected_.size());
        assertTrue(StringUtil.contains(protected_, SOL));
    }

    @Test
    public void incrementNumberRounds7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, VOL_MAGNETIK, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(VOL_MAGNETIK);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(0, protected_.size());
    }

    @Test
    public void incrementNumberRounds8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueImmu(TROU, data);
        fight_.getFighter(thrower_).activerAttaqueAntiImmu(RACINES);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        FightEndRound.incrementNumberRounds(fight_, thrower_, RACINES, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesProt().getVal(TROU);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        StringList protected_ = fight_.getFighter(thrower_).getProtectedAgainstMoveTypes();
        assertEq(1, protected_.size());
        assertTrue(StringUtil.contains(protected_, VOL));
        activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(RACINES);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueBlocantLanceur(ROULADE);
        FightEndRound.incrementNumberRounds(fight_, thrower_, ROULADE, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesConstChoices().getVal(ROULADE);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(thrower_).enableCounteringMoves(NUEE_DE_POUDRE);
        FightEndRound.incrementNumberRounds(fight_, thrower_, NUEE_DE_POUDRE, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(thrower_).enableChangingMovesTypes(ELECTRISATION);
        FightEndRound.incrementNumberRounds(fight_, thrower_, ELECTRISATION, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledChangingTypesMoves().getVal(ELECTRISATION);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        AffectedMove affected_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO));
        affected_.setMove(SEISME);
        affected_.getActivity().enableReset();
        FightEndRound.incrementNumberRounds(fight_, thrower_, ENCORE, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ZERO)).getActivity();
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        activity_ = fight_.getFighter(thrower_).refPartAttaquesSurCombatAtt(new MoveTeamPosition(ENCORE, POKEMON_PLAYER_FIGHTER_ONE)).getActivity();
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueBlocantLanceur(ROULADE);
        FightEndRound.incrementNumberRounds(fight_, thrower_, ROULADE, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesConstChoices().getVal(ROULADE);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueAntiImmu(ANTI_CROISEUR);
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        FightEndRound.incrementNumberRounds(fight_, thrower_, ANTI_CROISEUR, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRounds3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRounds(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(thrower_).activerAttaqueAntiImmu(ANTI_CROISEUR);
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR).increment();
        FightEndRound.incrementNumberRounds(fight_, thrower_, ANTI_CROISEUR, data);
        ActivityOfMove activity_ = fight_.getFighter(thrower_).getEnabledMovesUnprot().getVal(ANTI_CROISEUR);
        assertEq(5, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    private Fight incrementNumberRoundsTeam(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void incrementNumberRoundsTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(DANSE_PLUIE);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, DANSE_PLUIE, data);
        assertEq(0, fight_.getEnabledMoves().getVal(DANSE_PLUIE).getNbTurn());
    }

    @Test
    public void incrementNumberRoundsTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(BRUME);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(BRUME);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, BRUME, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(BRUME);
        fight_.getUserTeam().addSuccessfulMoveRound(NULL_REF);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) 5);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(NULL_REF);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, MUR_LUMIERE, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) 5);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(BAIE_MEPO);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, MUR_LUMIERE, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) 5);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(LUMARGILE);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, MUR_LUMIERE, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(6, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) 8);
        TeamPosition teamPosition_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(teamPosition_);
        fighter_.backUpObject(LUMARGILE);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, MUR_LUMIERE, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.getUserTeam().activerEffetEquipe(MUR_LUMIERE);
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, MUR_LUMIERE, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(MUR_LUMIERE);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.getUserTeam().activerEffetEquipe(VENT_ARRIERE_BIS);
        fight_.getUserTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS).increment();
        fight_.getUserTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS).increment();
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.PLAYER, VENT_ARRIERE_BIS, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeam3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.getFoeTeam().activerEffetEquipe(VENT_ARRIERE_BIS);
        fight_.getFoeTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS).increment();
        fight_.getFoeTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS).increment();
        FightEndRound.incrementNumberRoundsTeam(fight_, Fight.FOE, VENT_ARRIERE_BIS, data);
        ActivityOfMove activity_ = fight_.getFoeTeam().getEnabledMoves().getVal(VENT_ARRIERE_BIS);
        assertEq(3, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    private Fight incrementNumberRoundsTeamComboMoves(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void incrementNumberRoundsTeamComboMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.PLAYER, movesGroup_, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeamComboMoves2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.PLAYER, movesGroup_, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeamComboMoves3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        activity_.setNbTurn((byte)3);
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.PLAYER, movesGroup_, data);
        activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeamComboMoves1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.PLAYER, movesGroup_, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeamComboMoves2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_EAU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_).increment();
        fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_).increment();
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.PLAYER, movesGroup_, data);
        ActivityOfMove activity_ = fight_.getUserTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    @Test
    public void incrementNumberRoundsTeamComboMoves3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsTeamComboMoves(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_EAU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getFoeTeam().getEnabledMovesByGroup().getVal(movesGroup_).increment();
        fight_.getFoeTeam().getEnabledMovesByGroup().getVal(movesGroup_).increment();
        FightEndRound.incrementNumberRoundsTeamComboMoves(fight_, Fight.FOE, movesGroup_, data);
        ActivityOfMove activity_ = fight_.getFoeTeam().getEnabledMovesByGroup().getVal(movesGroup_);
        assertEq(3, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
    }

    private Fight incrementNumberRoundsGlobal(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void incrementNumberRoundsGlobal1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getUserTeam().activerEffetEquipe(BRUME);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        FightEndRound.incrementNumberRoundsGlobal(fight_, BRUME, data);
        assertEq(0, fight_.getFoeTeam().getEnabledMoves().getVal(BRUME).getNbTurn());
        assertEq(0, fight_.getUserTeam().getEnabledMoves().getVal(BRUME).getNbTurn());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        FightEndRound.incrementNumberRoundsGlobal(fight_, ZENITH, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setAbility(SECHERESSE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(SECHERESSE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        FightRound.initRound(fight_);
        FightRound.roundThrowerSwitch(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        assertTrue(fight_.getStillEnabledMoves().getVal(ZENITH));
        FightEndRound.incrementNumberRoundsGlobal(fight_, ZENITH, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(ZENITH);
        assertEq(0, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(GRAVITE);
        FightEndRound.incrementNumberRoundsGlobal(fight_, GRAVITE, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(GRAVITE);
        assertEq(1, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(ROCHE_LISSE);
        fight_.getEnabledMoves().getVal(TEMPETESABLE).setNbTurn((short) 5);
        FightEndRound.incrementNumberRoundsGlobal(fight_, TEMPETESABLE, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(TEMPETESABLE);
        assertEq(6, activity_.getNbTurn());
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(ROCHE_LISSE);
        fight_.getEnabledMoves().getVal(TEMPETESABLE).setNbTurn((short) 8);
        FightEndRound.incrementNumberRoundsGlobal(fight_, TEMPETESABLE, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(TEMPETESABLE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getEnabledMoves().getVal(TEMPETESABLE).setNbTurn((short) 5);
        FightEndRound.incrementNumberRoundsGlobal(fight_, TEMPETESABLE, data);
        ActivityOfMove activity_ = fight_.getEnabledMoves().getVal(TEMPETESABLE);
        assertEq(0, activity_.getNbTurn());
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void incrementNumberRoundsGlobal1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = incrementNumberRoundsGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.enableGlobalMove(ORAGE_BIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getEnabledMoves().getVal(ORAGE_BIS).setNbTurn((short)4);
        FightEndRound.incrementNumberRoundsGlobal(fight_, ORAGE_BIS, data);
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight processStatus(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void processStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processStatus(fight_, thrower_, SOMMEIL, data);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void processStatus2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processStatus(fight_, thrower_, BRULURE, data);
        assertEq(1, fighter_.getStatusNbRound(BRULURE));
    }

    @Test
    public void processStatus3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.creerClone(new Rate("1/2"));
        FightEndRound.processStatus(fight_, thrower_, BRULURE, data);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
    }

    @Test
    public void processStatus4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.setCurrentAbility(NULL_REF);
        fight_.getUserTeam().activerEffetEquipe(RUNE_PROTECT);
        FightEndRound.processStatus(fight_, thrower_, BRULURE, data);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
    }

    @Test
    public void processStatus5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processStatus(fight_, thrower_, CAUCHEMAR, data);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    private Fight effectEndRoundSingleRelation(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundSingleRelation1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(TIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(TIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(TIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, TIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(TIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_.setClone(Rate.one());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundSingleRelation4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        activity_.increment();
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(2, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundSingleRelation5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(ACCRO_GRIFFE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        activity_.increment();
        activity_.increment();
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(3, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(ACCRO_GRIFFE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        activity_.increment();
        activity_.increment();
        activity_.increment();
        activity_.increment();
        activity_.increment();
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundSingleRelation7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BANDEAU_ETREINTE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("98399/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(NULL_REF);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_.setCurrentAbility(GARDE_MAGIK);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundSingleRelation12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_.setCurrentAbility(GARDE_MAGIK);
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, ANNEAU_HYDRO, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundSingleRelation13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2"));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("249/3250"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2"));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("249/3250"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("46/5"));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("53549/6500"),fighter_.getRemainingHp());
        fighter_.setClone(Rate.one());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("53549/6500"),fighter_.getRemainingHp());
        assertEq(Rate.zero(),fighter_.getClone());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(SIPHON);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2"));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        activity_.enable();
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundSingleRelation17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(CHARGE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("46/5"));
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("17466/2675"),fighter_.getRemainingHp());
        fighter_.setClone(Rate.one());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("17466/2675"),fighter_.getRemainingHp());
        assertEq(Rate.one(),fighter_.getClone());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }
    @Test
    public void effectEndRoundSingleRelation1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("52937/3250"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(1, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundSingleRelation2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        activity_.increment();
        activity_.increment();
        activity_.increment();
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertEq(new Rate("113349/6500"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        //104000 52937/3250
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundSingleRelation3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2"));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("249/3250"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundSingleRelation4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(SIPHON);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundSingleRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_PLAYER_TARGET_ZERO);
        fighter_.getIncrUserAccuracy().put(new MoveTeamPosition(LIRE_ESPRIT, target_), true);
        fighter_.variationBoostStatistique(Statistic.CRITICAL_HIT, (byte) 6);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(new Rate("2"));
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.isSuccessfulMove());
        ActivityOfMove activity_;
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        activity_.enable();
        assertTrue(activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        EffectEndRoundSingleRelation eff_;
        eff_ = (EffectEndRoundSingleRelation) data.getMove(SIPHON).getEffects().last();
        FightEndRound.effectEndRoundSingleRelation(fight_, thrower_, eff_, SIPHON, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        fighter_ = fight_.getFighter(thrower_);
        activity_ = fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, target_));
        assertTrue(!activity_.isEnabled());
        assertTrue(activity_.isIncrementCount());
        assertEq(0, activity_.getNbTurn());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundStatus(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundStatus1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = PEUR;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(0, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(1, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = PEUR;
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(0, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = TROUILLE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(2, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = TROUILLE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(2, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = TROUILLE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(2, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = CRAME_BIS;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(1, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = PEUR;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(0, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = TROUILLE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(2, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = CRAME;
        fighter_.affecterStatut(st_);
        fighter_.incrementRoundsStatus(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(3, fighter_.getStatusNbRound(st_));
    }

    @Test
    public void effectEndRoundStatus4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = CRAME;
        fighter_.affecterStatut(st_);
        fighter_.incrementRoundsStatus(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatus(fight_, f_, status_, st_, data);
        assertEq(0, fighter_.getStatusNbRound(st_));
    }

    private Fight effectEndRoundStatusHp(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundStatusHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundStatusHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setCurrentAbility(NULL_REF);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setCurrentAbility(IGNIFUGE);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("2273/400"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }


    @Test
    public void effectEndRoundStatusHp55Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("0"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
    }

    @Test
    public void effectEndRoundStatusHp6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(POISON_ST);
        fighter_.setCurrentAbility(IGNIFUGE);
        Status status_ = data.getStatus(POISON_ST);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, POISON_ST, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = POISON_GRAVE;
        fighter_.affecterStatut(st_);
        fighter_.incrementRoundsStatus(st_);
        fighter_.setCurrentAbility(IGNIFUGE);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("1873/200"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(POISON_ST);
        fighter_.incrementRoundsStatus(POISON_ST);
        fighter_.setCurrentAbility(IGNIFUGE);
        Status status_ = data.getStatus(POISON_ST);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, POISON_ST, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = POISON_GRAVE;
        fighter_.affecterStatut(st_);
        fighter_.setCurrentAbility(IGNIFUGE);
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertEq(new Rate("5619/400"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(POISON_ST);
        fighter_.setCurrentAbility(IGNIFUGE);
        Status status_ = data.getStatus(POISON_ST);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, POISON_ST, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusHp1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundStatusHp2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        String st_ = BRULURE;
        fighter_.affecterStatut(st_);
        fighter_.setRemainedHp(Rate.one());
        Status status_ = data.getStatus(st_);
        FightEndRound.effectEndRoundStatusHp(fight_, f_, status_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundStatusRelation(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundStatusRelation1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(2, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE;
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE_BIS;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(2, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_NOIRE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(2, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE_BIS;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    @Test
    public void effectEndRoundStatusRelation3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_BLANCHE_BIS;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        FightEndRound.effectEndRoundStatusRelation(fight_, thrower_, target_, st_, data);
        assertEq(5, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
    }

    private Fight effectEndRoundStatusRelationHp(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundStatusRelationHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundStatusRelationHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("33/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("69/5"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundStatusRelationHp6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(GROSSERACINE);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("69/5"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("28/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(NULL_REF);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("33/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("33/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(!anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        anim_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1643/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        anim_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getToFighter());
        assertTrue(anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        anim_ = (AnimationRecoil) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationAbsorb anim_ = (AnimationAbsorb) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getToFighter());
        assertTrue(anim_.isKoFromFighter());
        assertTrue(!anim_.isKoToFighter());
    }

    @Test
    public void effectEndRoundStatusRelationHp18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundStatusRelationHp19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = NUIT_GRISE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }
    @Test
    public void effectEndRoundStatusRelationHp1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertEq(new Rate("92/5"), fighter_.getRemainingHp());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundStatusRelationHp2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.backUpObject(BAIE_MEPO);
        fighter_ = fight_.getFighter(target_);
        String st_ = VAMPIGRAINE;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.setCurrentAbility(SUINTEMENT);
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        fighter_ = fight_.getFighter(thrower_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundStatusRelationHp3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundStatusRelationHp(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(target_);
        String st_ = CAUCHEMAR;
        fighter_.affecterPseudoStatut(thrower_, st_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundStatusRelationHp(fight_, thrower_, target_, st_, diff_, data);
        assertTrue(fighter_.estKo());
        assertEq(0, fighter_.getStatusRelatNbRound(new MoveTeamPosition(st_, thrower_)));
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundIndividual(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundIndividual1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(Rate.one());
        String object_ = RESTES;
        fighter_.backUpObject(object_);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("3473/1600"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundIndividual2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = BOUE_NOIRE;
        fighter_.backUpObject(object_);
        fighter_.affecterTypes(NORMAL);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("13111/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundIndividual3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = BOUE_BLANCHE;
        fighter_.backUpObject(object_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterTypes(NORMAL);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = BOUE_NOIRE;
        fighter_.backUpObject(object_);
        fighter_.affecterTypes(new StringList(POISON, VOL));
        fighter_.setRemainedHp(Rate.one());
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("2673/800"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundIndividual5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = MUE;
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setCurrentAbility(object_);
        fighter_.setRemainedHp(Rate.one());
        AbilityData obj_;
        obj_ = data.getAbility(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = IGNIFUGE;
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setCurrentAbility(object_);
        fighter_.setRemainedHp(Rate.one());
        AbilityData obj_;
        obj_ = data.getAbility(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setRemainedHp(Rate.one());
        String object_ = ANNEAU_HYDRO;
        fighter_.backUpObject(NULL_REF);
        fighter_.activerAttaqueFinTourIndividuel(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) data.getMove(object_).getEffects().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("3473/1600"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundIndividual8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setCurrentAbility(NULL_REF);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("16857/1000"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundIndividual9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setCurrentAbility(METEO);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("16857/1000"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundIndividual10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1873/100"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundIndividual12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(NORMAL);
        fighter_.setCurrentAbility(NULL_REF);
        String object_ = ORBE_FLAMME;
        fighter_.backUpObject(object_);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(1, fighter_.getStatusNbRound(BRULURE));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processStatus(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterTypes(FEU);
        fighter_.setCurrentAbility(NULL_REF);
        String object_ = ORBE_FLAMME;
        fighter_.backUpObject(object_);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(0, fighter_.getStatusNbRound(BRULURE));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundIndividual14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundIndividual1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = MUE;
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setCurrentAbility(object_);
        fighter_.setRemainedHp(Rate.one());
        AbilityData obj_;
        obj_ = data.getAbility(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundIndividual2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = MUE;
        fighter_.backUpObject(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setCurrentAbility(object_);
        fighter_.setRemainedHp(Rate.one());
        AbilityData obj_;
        obj_ = data.getAbility(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundIndividual3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundIndividual4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundIndividual(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        String object_ = ORBE_VIE;
        fighter_.backUpObject(object_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        ItemForBattle obj_;
        obj_ = (ItemForBattle) data.getItem(object_);
        EffectEndRoundIndividual eff_;
        eff_ = (EffectEndRoundIndividual) obj_.getEffectEndRound().first();
        FightEndRound.effectEndRoundIndividual(fight_, thrower_, eff_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundTeam(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = COEUR_SOIN;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void effectEndRoundTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = TANT_PIS;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void effectEndRoundTeam1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = COEUR_SOIN;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void effectEndRoundTeam2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_PLAYER_FIGHTER_TWO;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = TANT_PIS;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void effectEndRoundTeam3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = COEUR_SOIN;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    @Test
    public void effectEndRoundTeam4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundTeam(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition partner_ = POKEMON_FOE_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(partner_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(thrower_);
        String object_ = TANT_PIS;
        fighter_.setCurrentAbility(object_);
        EffectEndRoundTeam eff_;
        eff_ = (EffectEndRoundTeam) data.getAbility(object_).getEffectEndRound().first();
        FightEndRound.effectEndRoundTeam(fight_, thrower_, eff_, object_, data);
        fighter_ = fight_.getFighter(thrower_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
        fighter_ = fight_.getFighter(partner_);
        assertEq(0, fighter_.getStatusNbRound(SOMMEIL));
    }

    private Fight effectEndRoundFoe(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
    public void effectEndRoundFoe1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundFoe(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        EffectCombo effet_ = data.getCombos().getEffects().getVal(movesGroup_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        FightEndRound.effectEndRoundFoe(fight_, f_, effetFinTour_, movesGroup_, diff_, data);
        assertEq(new Rate("161/10"), fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        assertTrue(!anim_.isPlayer());
    }

    @Test
    public void effectEndRoundFoe2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundFoe(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        EffectCombo effet_ = data.getCombos().getEffects().getVal(movesGroup_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundFoe(fight_, f_, effetFinTour_, movesGroup_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundFoe3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundFoe(userMoves_, partnersMoves_, foesMoves_, diff_);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        EffectCombo effet_ = data.getCombos().getEffects().getVal(movesGroup_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundFoe(fight_, f_, effetFinTour_, movesGroup_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundFoe1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundFoe(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        EffectCombo effet_ = data.getCombos().getEffects().getVal(movesGroup_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        TeamPosition f_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundFoe(fight_, f_, effetFinTour_, movesGroup_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundFoe2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundFoe(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        StringList movesGroup_ = new StringList(AIRE_DE_FEU,AIRE_D_HERBE);
        EffectCombo effet_ = data.getCombos().getEffects().getVal(movesGroup_);
        EffectEndRoundFoe effetFinTour_ = effet_.getEffectEndRound().first();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundFoe(fight_, f_, effetFinTour_, movesGroup_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundPositionRelation(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff,
            int... _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
            FightFacade.initFight(fight_,player_, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,player_, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectEndRoundPositionRelation1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1873/200"));
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        assertEq(0, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundPositionRelation2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(VOEU, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1873/200"));
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundPositionRelation3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(VOEU, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1873/200"));
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(0, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundPositionRelation4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(VOEU, (short) 10);
        moves_.put(COUD_BOUE, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1873/200"));
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(new Rate("1873/200"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_.setFirstChosenMoveTarget(COUD_BOUE, POKEMON_FOE_TARGET_ZERO);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(1, soinApres_.getNbRounds());
        fight_.getEffects().clear();
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(!soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundPositionRelation5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(VOEU, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertTrue(fighter_.isSuccessfulMove());
        StacksOfUses soinApres_=fight_.getUserTeam().getHealAfter().getVal(move_).getVal(fighter_.getGroundPlace());
        assertTrue(soinApres_.isFirstStacked());
        assertTrue(!soinApres_.isLastStacked());
        assertEq(0, soinApres_.getNbRounds());
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        fighter_.setFirstChosenMove(move_);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, f_, diff_, data);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("2073/200"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertEq(1, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundPositionRelation6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        String move_ = VOEU;
        MoveData dataMove_ = data.getMove(move_);
        EffectEndRoundPositionRelation eff_;
        eff_ = (EffectEndRoundPositionRelation) dataMove_.getEffects().last();
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(new Rate("1873/200"));
        StacksOfUses st_;
        st_ = fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0);
        st_.setLastStacked(true);
        st_.setNbRounds((byte) 1);
        FightEndRound.effectEndRoundPositionRelation(fight_, f_, eff_, move_, data);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertEq(1, fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).getNbRounds());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isFirstStacked());
        assertTrue(!fight_.getUserTeam().getHealAfter().getVal(move_).getVal((byte) 0).isLastStacked());
        assertEq(1, fight_.getEffects().size());
    }

    private Fight effectEndRoundPositionTargetRelation(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff,
            int... _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
            FightFacade.initFight(fight_,player_, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,player_, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectEndRoundPositionTargetRelation1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundPositionTargetRelation2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertEq(POKEMON_FOE_TARGET_ZERO,attaqueAnticipe_.getTargetPosition());
        assertTrue(attaqueAnticipe_.isIncrementing());
        assertEq(1, attaqueAnticipe_.getNbRounds());
        assertEq(new Rate("2"), attaqueAnticipe_.getDamage());
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundPositionTargetRelation3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertEq(POKEMON_FOE_TARGET_ZERO,attaqueAnticipe_.getTargetPosition());
        assertTrue(attaqueAnticipe_.isIncrementing());
        assertEq(2, attaqueAnticipe_.getNbRounds());
        assertEq(new Rate("2"), attaqueAnticipe_.getDamage());
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundPositionTargetRelation4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        assertEq(Rate.zero(), attaqueAnticipe_.getDamage());
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        assertEq(new Rate("82/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animation_ = (AnimationEffect) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectEndRoundPositionTargetRelation5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.creerClone(new Rate("1/2"));
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Rate.zero(), attaqueAnticipe_.getDamage());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        assertEq(new Rate("46/5"),fighter_.getRemainingHp());
        assertEq(new Rate("36/5"),fighter_.getClone());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animation_ = (AnimationEffect) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(!animation_.isKoToFighter());
    }

    @Test
    public void effectEndRoundPositionTargetRelation6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Rate.zero(), attaqueAnticipe_.getDamage());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animation_ = (AnimationEffect) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectEndRoundPositionTargetRelation7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animation_ = (AnimationEffect) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getToFighter());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectEndRoundPositionTargetRelation8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getFoeTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        fight_.getEffects().clear();
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Rate.zero(), attaqueAnticipe_.getDamage());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationEffect animation_ = (AnimationEffect) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animation_.getFromFighter());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animation_.getToFighter());
        assertTrue(!animation_.isKoFromFighter());
        assertTrue(animation_.isKoToFighter());
    }

    @Test
    public void effectEndRoundPositionTargetRelation1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getUserTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(Rate.zero(), attaqueAnticipe_.getDamage());
        assertEq(Fighter.BACK,attaqueAnticipe_.getTargetPosition().getPosition());
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundPositionTargetRelation2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundPositionTargetRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        String move_ = PRESCIENCE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        Anticipation attaqueAnticipe_ = fight_.getFoeTeam().getMovesAnticipation().getVal(move_).getVal(fighter_.getGroundPlace());
        attaqueAnticipe_.setDamage(new Rate("2"));
        attaqueAnticipe_.setNbRounds((byte) 0);
        attaqueAnticipe_.setIncrementing(true);
        attaqueAnticipe_.setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundPositionTargetRelation(fight_, thrower_, move_, diff_, data);
        assertTrue(!attaqueAnticipe_.isIncrementing());
        assertEq(0, attaqueAnticipe_.getNbRounds());
        assertEq(new Rate("2"), attaqueAnticipe_.getDamage());
        assertEq(0,attaqueAnticipe_.getTargetPosition().getPosition());
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundMultiRelation(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff,
            int... _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
            FightFacade.initFight(fight_,player_, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,player_, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectEndRoundMultiRelation1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.affecterStatut(BRULURE);
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundMultiRelation2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundMultiRelation3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundMultiRelation4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertEq(new Rate("161/10"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectEndRoundMultiRelation5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundMultiRelation6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundMultiRelation7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundMultiRelation8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(TERA_VOLT);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertEq(new Rate("161/10"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil animRecoil_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, animRecoil_.getUser());
        assertTrue(!animRecoil_.isKoUser());
    }

    @Test
    public void effectEndRoundMultiRelation1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundMultiRelation2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundMultiRelation(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition thrower_ = POKEMON_FOE_FIGHTER_ZERO;
        String ability_ = MAUVAIS_REVE;
        Fighter fighter_ = fight_.getFighter(thrower_);
        fighter_.setCurrentAbility(ability_);
        AbilityData ab_ = data.getAbility(ability_);
        EffectEndRoundMultiRelation eff_;
        eff_ = (EffectEndRoundMultiRelation) ab_.getEffectEndRound().last();
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fighter_ = fight_.getFighter(target_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.effectEndRoundMultiRelation(fight_, thrower_, eff_, ability_, diff_, data);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight effectEndRoundGlobal(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff,
            int... _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
            FightFacade.initFight(fight_,player_, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,player_, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void effectEndRoundGlobal1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(GRAVITE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, GRAVITE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundGlobal2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundGlobal3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(ORAGE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(ABRI_CAPE);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(ABSORB_VOLT);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, ORAGE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("69/4"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("2673/800"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("3533/1600"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationHealing animHealing_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animHealing_.getHealed());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        animHealing_ = (AnimationHealing) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animHealing_.getHealed());
    }

    @Test
    public void effectEndRoundGlobal4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(ORAGE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ELECTRIQUE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(ABRI_CAPE);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, ORAGE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("69/4"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("3473/1600"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("3533/1600"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(3, fight_.getEffects().size());
        AnimationHealing animHealing_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, animHealing_.getHealed());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        animHealing_ = (AnimationHealing) fight_.getEffects().last();
        assertEq(POKEMON_PLAYER_TARGET_ONE, animHealing_.getHealed());
    }

    @Test
    public void effectEndRoundGlobal5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("69/4"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)2,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        foe_ = POKEMON_FOE_FIGHTER_ONE;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1/2"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
        assertEq(4, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_FOE_TARGET_ONE, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ONE, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
        anim_ = (AnimationRecoil) fight_.getEffects().get(2);
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        animKo_ = (AnimationKo) fight_.getEffects().last();
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertTrue(fighter_.estKo());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ONE, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
        assertEq(POKEMON_PLAYER_TARGET_ONE, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(BROUHAHA);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, BROUHAHA, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundGlobal10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(CHAMP_BRUMEUX);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(foe_).setRemainedHp(Rate.one());
        fight_.getFighter(ally_).setRemainedHp(Rate.one());
        fight_.getFighter(ally_).setDisappeared(true);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, CHAMP_BRUMEUX, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("51/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationHealing anim_ = (AnimationHealing) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getHealed());
        anim_ = (AnimationHealing) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getHealed());
    }

    @Test
    public void effectEndRoundGlobal11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setItem(LUNETTES_FILTRE);
        fight_.getFighter(foe_).setItem(BAIE_MEPO);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("69/4"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("5619/320"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationRecoil anim_ = (AnimationRecoil) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
        anim_ = (AnimationRecoil) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, anim_.getUser());
        assertTrue(!anim_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(REQUIEM);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setItem(LUNETTES_FILTRE);
        fight_.getFighter(foe_).setItem(BAIE_MEPO);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, REQUIEM, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundGlobal13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(REQUIEM);
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(foe_).setItem(BAIE_MEPO);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, REQUIEM, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(2, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
        AnimationKo animKo_ = (AnimationKo) fight_.getEffects().get(1);
        assertEq(POKEMON_FOE_TARGET_ZERO, animKo_.getUser());
        assertTrue(animKo_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(REQUIEM);
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(foe_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, REQUIEM, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(1, fight_.getEffects().size());
        AnimationKo anim_ = (AnimationKo) fight_.getEffects().first();
        assertEq(POKEMON_PLAYER_TARGET_ZERO, anim_.getUser());
        assertTrue(anim_.isKoUser());
    }

    @Test
    public void effectEndRoundGlobal15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.enableGlobalMove(REQUIEM);
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(foe_).setItem(BAIE_MEPO);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, REQUIEM, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
        assertEq(0, fight_.getEffects().size());
    }

    @Test
    public void effectEndRoundGlobal1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertTrue(fighter_.estKo());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundGlobal2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.enableGlobalMove(TEMPETESABLE);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(GARDE_MAGIK);
        fighter_.affecterTypes(DRAGON);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(ROCHE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(new Rate("1"));
        fighter_.affecterTypes(DRAGON);
        FightEndRound.effectEndRoundGlobal(fight_, TEMPETESABLE, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertTrue(fighter_.estKo());
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void effectEndRoundGlobal3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = effectEndRoundGlobal(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.enableGlobalMove(REQUIEM);
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        fight_.getEnabledMoves().getVal(REQUIEM).increment();
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(ally_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(foe_).setCurrentAbility(ANTI_BRUIT);
        fight_.getFighter(player_).setItem(NULL_REF);
        Fighter fighter_;
        FightEndRound.effectEndRoundGlobal(fight_, REQUIEM, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("0"),fighter_.getRemainingHp());
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight processEndRound(
            CustList<LevelMoves> _userMoves,
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Difficulty _diff,
            int... _mult) {
        Player player_ = new Player(NICKNAME,null,_diff,false,data);
        for (int i = IndexConstants.FIRST_INDEX; i < _userMoves.size(); i++) {
            Pokemon pokemon_ = new WildPk();
            pokemon_.setName(ARTIKODIN);
            pokemon_.setItem(PLAQUE_DRACO);
            pokemon_.setAbility(METEO);
            pokemon_.setGender(Gender.NO_GENDER);
            pokemon_.setLevel(_userMoves.get(i).getFirst());
            PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data, _userMoves.get(i).getMovesPp());
            lasPk_.initIv(_diff);
            lasPk_.initPvRestants(data);
            player_.getTeam().add(lasPk_);
        }
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
            FightFacade.initFight(fight_,player_, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,player_, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void processEndRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(MUE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(TANT_PIS);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setRemainedHp(Rate.one());
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("28095/1600"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.activerAttaque(EMBARGO);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setRemainedHp(Rate.one());
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("1"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("28095/1600"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setCurrentAbility(MAUVAIS_REVE);
        fighter_ = fight_.getFighter(foe_);
        fighter_.affecterStatut(SOMMEIL);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("161/10"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.creerClone(new Rate("1/16"));
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getUserTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("161/10"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertEq(new Rate("1873/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
    }
    @Test
    public void processEndRound12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(foe_);
        fight_.enableGlobalMove(TEMPETESABLE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(foe_);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(foe_);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, player_)).enable();
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(foe_);
        Anticipation ant_;
        ant_ = fight_.getFoeTeam().getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        ant_.setDamage(Rate.one());
        ant_.setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        ant_.setNbRounds((byte) 2);
        ant_.setIncrementing(true);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }
    @Test
    public void processEndRound17Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(MAUVAIS_REVE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound18Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound19Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        fighter_.affecterPseudoStatut(foe_, CAUCHEMAR);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound20Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(POISSON);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound21Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.activerAttaqueFinTourIndividuel(POISSE);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(fight_.getAcceptableChoices());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void processEndRound1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound2SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fight_.enableGlobalMove(TEMPETESABLE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound3SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BOUE_NOIRE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound4SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, player_)).enable();
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound5SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        Anticipation ant_;
        ant_ = fight_.getFoeTeam().getMovesAnticipation().getVal(PRESCIENCE).getVal((byte) 0);
        ant_.setDamage(Rate.one());
        ant_.setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        ant_.setNbRounds((byte) 2);
        ant_.setIncrementing(true);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound6SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(MAUVAIS_REVE);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound7SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(BRULURE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound8SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.affecterStatut(SOMMEIL);
        fighter_.affecterPseudoStatut(foe_, CAUCHEMAR);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_ = fight_.getFighter(foe_);
        fighter_.setCurrentAbility(NULL_REF);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound9SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        fight_.enableGlobalMove(ORAGE_BIS);
        fight_.getEnabledMoves().getVal(ORAGE_BIS).setNbTurn((short) 4);
        FightEndRound.processEndRound(fight_, diff_, data);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound10SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(POISSON);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    @Test
    public void processEndRound11SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SIPHON, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        CustList<LevelMoves> userMoves_ = new CustList<LevelMoves>();
        userMoves_.add(new LevelMoves((short)3,moves_));
        userMoves_.add(new LevelMoves((short)4,moves_));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = processEndRound(userMoves_, partnersMoves_, foesMoves_, diff_);
        fight_.setSimulation(true);
        TeamPosition player_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_;
        fighter_ = fight_.getFighter(player_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.activerAttaqueFinTourIndividuel(POISSE);
        fighter_ = fight_.getFighter(ally_);
        fighter_.activerAttaqueFinTourIndividuel(ANNEAU_HYDRO);
        fighter_.backUpObject(RESTES);
        fighter_.setCurrentAbility(COEUR_SOIN);
        fighter_ = fight_.getFighter(foe_);
        FightEndRound.processEndRound(fight_, diff_, data);
        fighter_ = fight_.getFighter(foe_);
        assertEq(new Rate("92/5"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(player_);
        assertTrue(fighter_.estKo());
        assertTrue(fighter_.getAction() instanceof Action);
        fighter_ = fight_.getFighter(ally_);
        assertEq(new Rate("1933/100"),fighter_.getRemainingHp());
        assertTrue(fighter_.getAction() instanceof Action);
        assertTrue(!fight_.getAcceptableChoices());
    }

    private Fight calculateNewLevel(
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
    public void calculateNewLevel1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNewLevel(partnersMoves_, foesMoves_, player_, diff_);
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringMap<MovesAbilities> map_;
        map_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, map_.size());
        assertEq(0,fighter_.getMovesToBeLearnt().size());
    }

    @Test
    public void calculateNewLevel2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNewLevel(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringMap<MovesAbilities> map_;
        map_ = fighter_.getMovesAbilitiesEvos();
        assertEq(0, map_.size());
        StringList moves_ = fighter_.getMovesToBeLearnt();
        assertEq(1,moves_.size());
        assertTrue(StringUtil.contains(moves_, DANSE_PLUIE));
    }

    @Test
    public void calculateNewLevel3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = calculateNewLevel(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        StringMap<MovesAbilities> map_;
        map_ = fighter_.getMovesAbilitiesEvos();
        assertEq(1, map_.size());
        assertTrue(map_.contains(TETARTE));
        StringList evoMoves_ = map_.getVal(TETARTE).getMoves();
        assertEq(4, evoMoves_.size());
        assertTrue(StringUtil.contains(evoMoves_, TOURNIQUET));
        assertTrue(StringUtil.contains(evoMoves_, ECUME));
        assertTrue(StringUtil.contains(evoMoves_, HYPNOSE));
        assertTrue(StringUtil.contains(evoMoves_, BULLES_D_O));
        StringList abilities_ = map_.getVal(TETARTE).getAbilities();
        assertTrue(StringUtil.contains(abilities_, MOITEUR));
        assertTrue(StringUtil.contains(abilities_, ABSORB_EAU));
        StringList moves_ = fighter_.getMovesToBeLearnt();
        assertEq(1,moves_.size());
        assertTrue(StringUtil.contains(moves_, BULLES_D_O));
    }

    private Fight proponeMovesEvolutions(
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
    public void proponeMovesEvolutions1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponeMovesEvolutions(partnersMoves_, foesMoves_, player_, diff_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void proponeMovesEvolutions2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponeMovesEvolutions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
    }

    @Test
    public void proponeMovesEvolutions3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponeMovesEvolutions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
    }

    private Fight missingFighterInTeam(
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
    public void missingFighterInTeam1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.FOE));
    }

    @Test
    public void missingFighterInTeam2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.PLAYER));
    }

    @Test
    public void missingFighterInTeam3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.FOE));
    }

    @Test
    public void missingFighterInTeam4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.PLAYER));
    }

    @Test
    public void missingFighterInTeam5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.getItem(RAPPEL);
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
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, data);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(FightEndRound.missingFighterInTeam(fight_, Fight.PLAYER));
    }

    @Test
    public void missingFighterInTeam6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.getItem(RAPPEL);
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
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightRound.initRound(fight_);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.PLAYER));
    }

    @Test
    public void missingFighterInTeam7Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.FOE));
    }

    @Test
    public void missingFighterInTeam8Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = missingFighterInTeam(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.missingFighterInTeam(fight_, Fight.PLAYER));
    }

    private Fight proponedSwitch(
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
    public void proponedSwitch1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        assertTrue(!FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        assertTrue(FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.getItem(RAPPEL);
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
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, data);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.getItem(RAPPEL);
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
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightRound.initRound(fight_);
        assertTrue(!FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).fullHeal(data);
        assertTrue(FightEndRound.proponedSwitch(fight_));
    }

    @Test
    public void proponedSwitch7Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = proponedSwitch(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).fullHeal(data);
        assertTrue(FightEndRound.proponedSwitch(fight_));
    }

    private Fight existSubstitute(
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
    public void existSubstitute1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = existSubstitute(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.existSubstitute(fight_));
    }

    @Test
    public void existSubstitute2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = existSubstitute(partnersMoves_, foesMoves_, player_, diff_, 1);
        assertTrue(FightEndRound.existSubstitute(fight_));
    }

    @Test
    public void existSubstitute3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = existSubstitute(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        assertTrue(FightEndRound.existSubstitute(fight_));
    }

    private Fight setPlacesForFighters(
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
    public void setPlacesForFighters1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_, 1);
        FightEndRound.setPlacesForFighters(fight_, true);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
    }

    @Test
    public void setPlacesForFighters2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        FightEndRound.setPlacesForFighters(fight_, true);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlaceSubst());
    }

    @Test
    public void setPlacesForFighters3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        //After a switch, ko pokemon have their place for substituting set to back
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst(Fighter.BACK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setGroundPlaceSubst(Fighter.BACK);
        FightEndRound.setPlacesForFighters(fight_, true);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
    }

    @Test
    public void setPlacesForFighters4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        //After a switch, ko pokemon have their place for substituting set to back
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst(Fighter.BACK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setGroundPlaceSubst(Fighter.BACK);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightEndRound.setPlacesForFighters(fight_, true);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
    }

    @Test
    public void setPlacesForFighters5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        //After a switch, ko pokemon have their place for substituting set to back
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst(Fighter.BACK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setGroundPlaceSubst(Fighter.BACK);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightEndRound.setPlacesForFighters(fight_, false);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
    }

    @Test
    public void setPlacesForFighters6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
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
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setPlacesForFighters(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        //After a switch, ko pokemon have their place for substituting set to back
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst(Fighter.BACK);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst(Fighter.BACK);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setGroundPlaceSubst(Fighter.BACK);
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        FightEndRound.setPlacesForFighters(fight_, true);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlaceSubst());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlaceSubst());
    }

    private Fight learnAndEvolve(
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
    public void learnAndEvolve1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolve(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        FightEndRound.learnAndEvolve(fight_, data);
        assertEq(0, fight_.getChoices().size());
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(PTITARD, fighter_.getName());
        assertEq(PTITARD, fighter_.getCurrentName());
        assertEq(METEO, fighter_.getAbility());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(4, fighter_.getMoves().size());
        assertEq(4, fighter_.getCurrentMoves().size());
        assertEq(25, fighter_.getMoves().getVal(ECUME).getCurrent());
        assertEq(25, fighter_.getCurrentMoves().getVal(ECUME).getMax());
        assertEq(15, fighter_.getMoves().getVal(HYPNOSE).getCurrent());
        assertEq(15, fighter_.getCurrentMoves().getVal(HYPNOSE).getMax());
        assertEq(20, fighter_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, fighter_.getMoves().getVal(TORGNOLES).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(TORGNOLES).getMax());
        assertEq(0, fight_.getCaughtEvolutions().size());
    }

    @Test
    public void learnAndEvolve2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolve(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        StringUtil.removeObj(fight_.getChoices().getVal((byte) 0).getKeptMoves(), HYPNOSE);
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(DANSE_PLUIE);
        assertTrue(FightFacade.possibleChoices(fight_, data));
        FightEndRound.learnAndEvolve(fight_, data);
        assertEq(0, fight_.getChoices().size());
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(PTITARD, fighter_.getName());
        assertEq(PTITARD, fighter_.getCurrentName());
        assertEq(METEO, fighter_.getAbility());
        assertEq(METEO, fighter_.getCurrentAbility());
        assertEq(4, fighter_.getMoves().size());
        assertEq(4, fighter_.getCurrentMoves().size());
        assertEq(25, fighter_.getMoves().getVal(ECUME).getCurrent());
        assertEq(25, fighter_.getCurrentMoves().getVal(ECUME).getMax());
        assertEq(5, fighter_.getMoves().getVal(DANSE_PLUIE).getCurrent());
        assertEq(5, fighter_.getCurrentMoves().getVal(DANSE_PLUIE).getMax());
        assertEq(20, fighter_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, fighter_.getMoves().getVal(TORGNOLES).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(TORGNOLES).getMax());
        assertEq(0, fight_.getCaughtEvolutions().size());
    }

    @Test
    public void learnAndEvolve3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolve(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        choice_.setAbility(ABSORB_EAU);
        choice_.getKeptMoves().clear();
        choice_.getKeptMoves().add(BULLES_D_O);
        choice_.getKeptMoves().add(ECUME);
        choice_.getKeptMoves().add(PISTOLET_A_O);
        choice_.getKeptMoves().add(TORGNOLES);
        assertTrue(FightFacade.possibleChoices(fight_, data));
        FightEndRound.learnAndEvolve(fight_, data);
        assertEq(0, fight_.getChoices().size());
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(TETARTE, fighter_.getName());
        assertEq(TETARTE, fighter_.getCurrentName());
        assertEq(ABSORB_EAU, fighter_.getAbility());
        assertEq(ABSORB_EAU, fighter_.getCurrentAbility());
        assertEq(4, fighter_.getMoves().size());
        assertEq(4, fighter_.getCurrentMoves().size());
        assertEq(25, fighter_.getMoves().getVal(ECUME).getCurrent());
        assertEq(25, fighter_.getCurrentMoves().getVal(ECUME).getMax());
        assertEq(20, fighter_.getMoves().getVal(BULLES_D_O).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(BULLES_D_O).getMax());
        assertEq(20, fighter_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, fighter_.getMoves().getVal(TORGNOLES).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(TORGNOLES).getMax());
        assertEq(1, fight_.getCaughtEvolutions().size());
        assertEq(TETARTE, fight_.getCaughtEvolutions().first());
    }

    @Test
    public void learnAndEvolve4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolve(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(MUNJA);
        choice_.getKeptMoves().clear();
        choice_.getKeptMoves().add(TUNNEL);
        choice_.getKeptMoves().add(OMBRE_PORTEE);
        choice_.getKeptMoves().add(GRIFFE);
        choice_.getKeptMoves().add(GRIFFE_ACIER);
        assertTrue(FightFacade.possibleChoices(fight_, data));
        FightEndRound.learnAndEvolve(fight_, data);
        assertEq(0, fight_.getChoices().size());
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(MUNJA, fighter_.getName());
        assertEq(MUNJA, fighter_.getCurrentName());
        assertEq(GARDE_MYSTIK, fighter_.getAbility());
        assertEq(GARDE_MYSTIK, fighter_.getCurrentAbility());
        assertEq(4, fighter_.getMoves().size());
        assertEq(4, fighter_.getCurrentMoves().size());
        assertEq(20, fighter_.getMoves().getVal(TUNNEL).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(TUNNEL).getMax());
        assertEq(20, fighter_.getMoves().getVal(OMBRE_PORTEE).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(OMBRE_PORTEE).getMax());
        assertEq(25, fighter_.getMoves().getVal(GRIFFE).getCurrent());
        assertEq(25, fighter_.getCurrentMoves().getVal(GRIFFE).getMax());
        assertEq(20, fighter_.getMoves().getVal(GRIFFE_ACIER).getCurrent());
        assertEq(20, fighter_.getCurrentMoves().getVal(GRIFFE_ACIER).getMax());
        assertEq(1, fight_.getCaughtEvolutions().size());
        assertEq(MUNJA, fight_.getCaughtEvolutions().first());
    }

    private Fight proponedSwitchWhileKoPlayer(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
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
            FightFacade.initFight(fight_,_player, _diff, dual_, data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        for (TeamPosition f: FightOrder.fighters(fight_, Fight.PLAYER)) {
            Fighter f_ = fight_.getFighter(f);
            if (!f_.isBelongingToPlayer()) {
                continue;
            }
            FightKo.setKoMoveTeams(fight_, f, _diff, data);
        }
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        return fight_;
    }

    @Test
    public void proponedSwitchWhileKoPlayer1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.PLAYER, fight_.getMult() - fight_.getPlayerMaxNumberFrontFighters()));
    }

    @Test
    public void proponedSwitchWhileKoPlayer2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.PLAYER, fight_.getMult() - fight_.getPlayerMaxNumberFrontFighters()));
    }

    @Test
    public void proponedSwitchWhileKoPlayer3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data);
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.PLAYER, fight_.getMult() - fight_.getPlayerMaxNumberFrontFighters()));
    }

    @Test
    public void proponedSwitchWhileKoPlayer4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.FOE, fight_.getMult()));
    }

    @Test
    public void proponedSwitchWhileKoPlayer5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void proponedSwitchWhileKoPlayer6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.FOE, fight_.getMult()));
    }

    @Test
    public void proponedSwitchWhileKoPlayer7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data);
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void proponedSwitchWhileKoPlayer8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void proponedSwitchWhileKoPlayer9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = proponedSwitchWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).fullHeal(data);
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_, Fight.FOE, fight_.getMult()));
    }

    private Fight exitKoFighters(
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
    public void exitKoFighters1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = exitKoFighters(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightEndRound.exitKoFighters(fight_);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
    }
}
