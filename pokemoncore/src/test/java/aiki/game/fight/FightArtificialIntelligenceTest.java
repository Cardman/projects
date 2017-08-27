package aiki.game.fight;
import static aiki.EquallablePkUtil.assertEq;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.util.MoveTarget;
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
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class FightArtificialIntelligenceTest extends InitializationDataBase {

    private static Fight choiceForSubstituing(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void choiceForSubstituing1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(1, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void choiceForSubstituing2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(1, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
    }

    @Test
    public void choiceForSubstituing3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(2, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void choiceForSubstituing4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void choiceForSubstituing5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 4).intValue());
    }

    @Test
    public void choiceForSubstituing6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void choiceForSubstituing7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
    }

    @Test
    public void choiceForSubstituing8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(4, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
    }

    @Test
    public void choiceForSubstituing9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
    }

    @Test
    public void choiceForSubstituing10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
    }

    @Test
    public void choiceForSubstituing11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(2, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
    }

    @Test
    public void choiceForSubstituing12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).fullHeal(_data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
    }


    @Test
    public void choiceForSubstituing13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).fullHeal(_data_);
        fight_.getFirstPositFoeFighters().put((byte) 1, Fighter.BACK);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2).intValue());
    }

    @Test
    public void choiceForSubstituing14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).fullHeal(_data_);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4).intValue());
    }

    @Test
    public void choiceForSubstituing15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceForSubstituing(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    private static Fight setFirstChosenMoveAlly(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void setFirstChosenMoveAlly1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMoveAlly(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, POKEMON_PLAYER_FIGHTER_TWO, INTERVERSION, POKEMON_PLAYER_TARGET_ZERO, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMoveAlly2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMoveAlly(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, POKEMON_PLAYER_FIGHTER_TWO, SEISME, POKEMON_FOE_TARGET_ZERO, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    private static Fight setBatonPassAlly(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void setBatonPassAlly1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, INTERVERSION, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, INTERVERSION, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, SEISME, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, SEISME, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(BERCEUSE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, BERCEUSE, POKEMON_FOE_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, BERCEUSE, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(RELAIS);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, _data_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, RELAIS, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, RELAIS, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(4, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_FOUR, diff_, _data_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, DANSE_LUNE, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, DANSE_LUNE, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(3, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        //FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_FOUR, diff_, data);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, DANSE_LUNE, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, DANSE_LUNE, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(RELAIS);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPassAlly(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, RELAIS, POKEMON_PLAYER_TARGET_ZERO, _data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, RELAIS, _data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    private static Fight reachable(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void reachable1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SACRIFICE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = reachable(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightArtificialIntelligence.reachable(fight_, thrower_, target_, SACRIFICE, diff_, _data_));
    }

    @Test
    public void reachable2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = reachable(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightArtificialIntelligence.reachable(fight_, thrower_, target_, SIPHON, diff_, _data_));
    }

    @Test
    public void reachable3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = reachable(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightArtificialIntelligence.reachable(fight_, thrower_, target_, SEISME, diff_, _data_));
    }

    @Test
    public void reachable4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = reachable(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.reachable(fight_, thrower_, target_, SIPHON, diff_, _data_));
    }

    @Test
    public void reachable6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COUPE_VENT, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = reachable(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.reachable(fight_, thrower_, target_, COUPE_VENT, diff_, _data_));
    }

    private static Fight remainingFoeTargetHp(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void remainingFoeTargetHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(Rate.zero(), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    @Test
    public void remainingFoeTargetHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_VOLT);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    @Test
    public void remainingFoeTargetHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_VOLT);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("375506/8375"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        ObjectMap<TargetCoords,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    private static Fight untouchablePartners(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void untouchablePartners1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SACRIFICE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, SACRIFICE, diff_, _data_);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(thrower_));
    }

    @Test
    public void untouchablePartners3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COUPE_VENT, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, COUPE_VENT, diff_, _data_);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BROUHAHA, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, BROUHAHA, diff_, _data_);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PICORE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, PICORE, diff_, _data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void untouchablePartners7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PICORE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        foesMoves_.add(new LevelMoves((short)15,foeMoves_));
        Fight fight_ = remainingFoeTargetHp(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, PICORE, diff_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(thrower_));
    }

    @Test
    public void untouchablePartners8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).affecterTypes(SOL);
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void untouchablePartners9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = untouchablePartners(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        EqList<TeamPosition> list_ = FightArtificialIntelligence.untouchablePartners(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
    }

    private static Fight remainingPartnerTargetHp(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void remainingPartnerTargetHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingPartnerTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ObjectMap<TeamPosition,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, SIPHON, diff_, _data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_));
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO));
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE));
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void remainingPartnerTargetHp2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingPartnerTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ObjectMap<TeamPosition,Rate> damages_;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_));
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO));
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE));
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void remainingPartnerTargetHp3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingPartnerTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ObjectMap<TeamPosition,Rate> damages_;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, TONNERRE, diff_, _data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_));
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO));
        assertEq(new Rate("2586941/111950"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE));
        assertEq(new Rate("2586941/111950"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void remainingPartnerTargetHp4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(ABIME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DANSE_LUNE);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)1,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        Fight fight_ = remainingPartnerTargetHp(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        ObjectMap<TeamPosition,Rate> damages_;
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, ABIME, diff_, _data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_));
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        assertEq(new Rate("1311/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO));
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE));
        assertEq(new Rate("6287/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    private static Fight usableMove(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void usableMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = usableMove(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, SIPHON, diff_, _data_));
    }

    @Test
    public void usableMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = usableMove(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.usableMove(fight_, thrower_, target_, false, SIPHON, diff_, _data_));
    }

    @Test
    public void usableMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = usableMove(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(target_).affecterTypes(SOL);
        assertTrue(FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, TONNERRE, diff_, _data_));
    }

    @Test
    public void usableMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = usableMove(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, TONNERRE, diff_, _data_));
    }

    private static Fight koFoeFighter(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void koFoeFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighter(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, _data_));
    }

    @Test
    public void koFoeFighter2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighter(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, _data_));
    }

    @Test
    public void koFoeFighter3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighter(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, _data_));
    }

    @Test
    public void koFoeFighter4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighter(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).setCurrentAbility(ABSORB_VOLT);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, _data_));
    }

    @Test
    public void koFoeFighter5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighter(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).affecterTypes(SOL);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, _data_));
    }

    private static Fight koFoeFighters(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void koFoeFighters1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = koFoeFighters(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        StringMap<EqList<TargetCoords>> mapTargets_;
        mapTargets_ = new StringMap<EqList<TargetCoords>>();
        mapTargets_.put(TONNERRE, new EqList<TargetCoords>(POKEMON_PLAYER_TARGET_ZERO,POKEMON_PLAYER_TARGET_ONE));
        EqList<TargetCoords> kos_ = FightArtificialIntelligence.koFoeFighters(fight_, thrower_, TONNERRE, mapTargets_, diff_, _data_);
        assertEq(1, kos_.size());
        assertTrue(kos_.containsObj(POKEMON_PLAYER_TARGET_ONE));
    }

    private static Fight choiceAllyArtificialIntelligenceWithoutUser(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligenceWithoutUser(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<EqList<TargetCoords>> targets_ = new StringMap<EqList<TargetCoords>>();
        targets_.put(COUPE_VENT, new EqList<TargetCoords>(POKEMON_FOE_TARGET_ZERO,POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligenceWithoutUser(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) -2);
        StringMap<EqList<TargetCoords>> targets_ = new StringMap<EqList<TargetCoords>>();
        targets_.put(COUPE_VENT, new EqList<TargetCoords>(POKEMON_FOE_TARGET_ZERO,POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligenceWithoutUser(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) -2);
        StringMap<EqList<TargetCoords>> targets_ = new StringMap<EqList<TargetCoords>>();
        targets_.put(SIPHON, new EqList<TargetCoords>(POKEMON_FOE_TARGET_ZERO));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(SIPHON,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligenceWithoutUser(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<EqList<TargetCoords>> targets_ = new StringMap<EqList<TargetCoords>>();
        targets_.put(SIPHON, new EqList<TargetCoords>(POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(SIPHON,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligenceWithoutUser(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<EqList<TargetCoords>> targets_ = new StringMap<EqList<TargetCoords>>();
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(0, choices_.size());
    }

    private static Fight choiceAllyArtificialIntelligence(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void choiceAllyArtificialIntelligence1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TONNERRE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DOUBLE_PIED);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DOUBLE_PIED);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)10,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(PICORE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)10,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(PICORE);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)13,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION);
        partnersMoves_.add(new LevelMoves((short)17,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)13,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
    }

    @Test
    public void choiceAllyArtificialIntelligence11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(3, choices_.size());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(PLAQUAGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PLAQUAGE,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_EAU);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(CHARGE,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(CHARGE,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SIPHON);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("1"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().put(Statistic.ATTACK, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(SIPHON,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ABSORB_EAU);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().put(Statistic.ATTACK, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence15Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(NULL_REF,new TargetCoords())).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence16Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(COUPE_VENT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceAllyArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ONE, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, _data_);
        ObjectMap<MoveTarget,MoveTarget> choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(0, choices_.size());
    }

    private static Fight setFirstChosenMove(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void setFirstChosenMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(INTERVERSION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMove(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(SEISME);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMove(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, SEISME, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(PISTOLET_A_O);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMove(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
//        StringList partnerMoves_ = new StringList(INTERVERSION);
//        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(INTERVERSION);
        //foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setFirstChosenMove(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        //assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    private static Fight setBatonPass(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void setBatonPass1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(INTERVERSION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(RELAIS);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(2, action_.getSubstitute());
    }

    @Test
    public void setBatonPass3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DANSE_LUNE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(3, action_.getSubstitute());
    }

    @Test
    public void setBatonPass4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BERCEUSE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DANSE_LUNE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        //FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(RELAIS);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = setBatonPass(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, diff_, _data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    private static Fight choiceFoeArtificialIntelligence(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
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
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
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
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void choiceFoeArtificialIntelligence1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(INTERVERSION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(BERCEUSE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(INTERVERSION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(SIPHON,BERCEUSE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(PISTOLET_A_O,SIPHON);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(PISTOLET_A_O,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(PISTOLET_A_O,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(PISTOLET_A_O,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, CHARGE, (short) 50);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(LUTTE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertTrue(fighter_.getAction().isEmpty());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertTrue(fighter_.getAction().isEmpty());
    }

    @Test
    public void choiceFoeArtificialIntelligence8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(JACKPOT, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(EMPAL_KORNE,SIPHON);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(EMPAL_KORNE,GLACIATION);
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(GUILLOTINE,GLACIATION);
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(GUILLOTINE,RISQUE);
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ONDE_VIDE,STOCKAGE);
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        foesMoves_.add(new LevelMoves((short)18,foeMoves_));
        Fight fight_ = choiceFoeArtificialIntelligence(partnersMoves_, foesMoves_, player_, diff_, 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(SOL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }
}
