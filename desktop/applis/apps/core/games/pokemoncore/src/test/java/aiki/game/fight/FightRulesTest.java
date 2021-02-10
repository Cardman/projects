package aiki.game.fight;

import aiki.db.DataBase;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
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
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public class FightRulesTest extends InitializationDataBase {

    private static Fight substitutable(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _user,
            Difficulty _diff, DataBase _data,
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
            FightFacade.initFight(fight_,_user, _diff, dual_, _data);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_user, _diff, leader_, _data);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void substitutable1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)0);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, (byte) 2);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, Fighter.BACK);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, Fighter.BACK);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, (byte)0);
        fight_.getFirstPositPlayerFighters().put((byte)1, Fighter.BACK);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(thrower_).setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getFighter(thrower_).isSuccessfulMove());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(thrower_).setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getFighter(thrower_).isSuccessfulMove());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte)1);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
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
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 4);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 4);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        Fighter fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1,(byte) 1);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 4);
        FightRound.initRound(fight_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1,(byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(INTERVERSION)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = substitutable(partnersMoves_, foesMoves_, player_, diff_, data_, 4);
        FightRound.initRound(fight_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void allowedMoves1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        StringList moves_ = FightRules.allowedMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.setNeedingToRecharge(true);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.setNbPrepaRound((short) 1);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,f_)).setMove(PISTOLET_A_O);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,f_)).getActivity().enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,f_)).setMove(PISTOLET_A_O);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,f_)).getActivity().enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,f_)).enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,f_)).add(ECUME);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(ROULADE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(ROULADE, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, ROULADE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(ROULADE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(ROULADE, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, ROULADE));
    }

    @Test
    public void allowedMoves12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(SOIN, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(EMBARGO);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(TOURMENTE);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
    }

    @Test
    public void allowedMoves15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(PROVOC);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
    }

    @Test
    public void allowedMoves16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(VOL_MAGNETIK, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.enableGlobalMove(GRAVITE);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(PRESCIENCE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, PRESCIENCE));
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PRESCIENCE, POKEMON_FOE_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, DANSE_LUNE));
    }

    @Test
    public void allowedMoves20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void allowedMoves21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        fight_.getUserTeam().activerEffetEquipe(TOUR_RAPIDE);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(f_).activerAttaque(CYCLE_V);
        AffectedMove attaqueViseeActif_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).refPartAttaquesSurCombatAtt(new MoveTeamPosition(CHANT,f_));
        attaqueViseeActif_.getActivity().enable();
        attaqueViseeActif_.setMove(CHARGE);
        //fight_.getFighter(f_).a
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(0, moves_.size());
    }

    private static Fight rulesSending(CustList<LevelMoves> _partnerMoves, CustList<LevelMoves> _foeMoves, Player _user, Difficulty _diff, DataBase _data, int[] _mult) {
        Fight fight_ = substitutable(_partnerMoves, _foeMoves, _user, _diff, _data, _mult);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data);
        return fight_;
    }

    @Test
    public void playable1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(ECUME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SEISME, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(EAU_FRAICHE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(TOTAL_SOIN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(TOTAL_SOIN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(RAPPEL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(RAPPEL, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(MAX_ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(ELIXIR, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE_MAX, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ENIGMA);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ENIGMA, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_GOWAV);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_GOWAV, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_MEPO);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(BAIE_MEPO, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(PARALYSIE);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_PITAYE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_PITAYE, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{3});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{3});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable27Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable28Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable29Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable30Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable31Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable32Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_FOE_TARGET_TWO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable33Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable34Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable35Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{2});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable36Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable37Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(EAU_FRAICHE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable38Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(TOTAL_SOIN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(TOTAL_SOIN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable39Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(REVEIL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(REVEIL, data_);
        fighter_.affecterStatut(PARALYSIE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable40Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(RAPPEL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(RAPPEL, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable41Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(MAX_ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable42Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObjectMove(ELIXIR, SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable43Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE, DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable44Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE_MAX, DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable45Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(HUILE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable46Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(HUILE_MAX, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable47Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable48Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ENIGMA);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_ENIGMA, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable49Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_GOWAV);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_GOWAV, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable50Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_MEPO);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObjectMove(BAIE_MEPO, SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable51Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable52Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable53Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_PITAYE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_PITAYE, data_);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable54Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{2});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable55Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{2});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable56Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{2});
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable57Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(f_).setFirstChosenMoveTarget(LUTTE, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable58Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{4});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable59Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{4});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable60Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{4});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable62Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{4});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable63Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{4});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_THREE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable64Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{2});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        f_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable65Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable66Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(PETIT_RAPPEL, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable67Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(NULL_REF, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable68Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable69Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setSubstitute((byte) 0);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable70Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setSubstitute(Fighter.BACK);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable71Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    /*@Test
    public void playable72Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        Map<String,Short> map_ = new Map<String,Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = playable(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }*/

    @Test
    public void playable73Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable74Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable75Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable76Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 3);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable77Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable78Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable79Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable80Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable81Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 3);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable82Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = rulesSending(partnersMoves_, foesMoves_, player_, diff_, data_, new int[]{});
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }
}
