package aiki.map.pokemon;

import code.maths.LgInt;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingPp;
import aiki.fight.items.ItemForBattle;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class PokemonPlayerTest extends InitializationDataBase {

    private static final String SEPARATOR = PokemonPlayer.SEPARATOR;

    static PokemonPlayer pokemonPlayer(DataBase _data, int _level) {
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel(_level);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        return pkMoves(_data,new Difficulty(), base_);
    }
    @Test
    public void new_PokemonPlayer_Base1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PIKACHU);
        pk_.setLevel( 5);
        pk_.initAttaques(data_, true, data_.getMaxIv());
        assertEq(2, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }
    @Test
    public void new_PokemonPlayer_Base2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PIKACHU);
        pk_.setLevel( 5);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        assertEq(2, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }
    @Test
    public void new_PokemonPlayer_Base3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PIKACHU);
        pk_.setLevel( 2);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }
    @Test
    public void new_PokemonPlayer_Base4Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PIKACHU);
        pk_.setLevel( 7);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        assertEq(3, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getCurrent());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void new_PokemonPlayer_Base5Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PIKACHU);
        pk_.setLevel( 1);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void new_PokemonPlayer_Base6Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        assertEq(1, pk_.getMoves().size());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getCurrent());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void obtention1Test() {
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.obtention();
        assertEq(Rate.zero(), pk_.getWonExpSinceLastLevel());
        assertEq(0, pk_.getNbStepsTeamLead());
    }

    @Test
    public void getMovesAtLevel1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        StringMap<Long> moves_ = PokemonPlayer.getMovesAtLevel(PICHU, 1,data_);
        assertEq(1, moves_.size());
        assertEq(50, moves_.getVal(ECLAIR));
    }

    @Test
    public void initMoves1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        StringMap<Long> moves_ = PokemonPlayer.getMovesAtLevel(PICHU, 1,data_);
        pk_.initMoves(moves_);
        assertEq(1, pk_.getMoves().size());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getCurrent());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getMax());
    }

    @Test
    public void initEvIv1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        StringMap<Long> moves_ = PokemonPlayer.getMovesAtLevel(PICHU, 1,data_);
        pk_.initMoves(moves_);
        pk_.initEvIv(true, data_.getMaxIv());
        pk_.initIv(new Difficulty());
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
    }

    @Test
    public void initEvIv2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        StringMap<Long> moves_ = PokemonPlayer.getMovesAtLevel(PICHU, 1,data_);
        pk_.initMoves(moves_);
        pk_.initEvIv(false, data_.getMaxIv());
        pk_.initIv(new Difficulty());
        assertEq(0, pk_.getEv().size());
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void initAttaques1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        pk_.initAttaques(data_, false, data_.getMaxIv());
        pk_.initIv(new Difficulty());
        assertEq(1, pk_.getMoves().size());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getCurrent());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getMax());
        assertEq(0, pk_.getEv().size());
        assertEq(6, pk_.getIv().size());
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void initAttaques2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = new PokemonPlayer();
        pk_.setName(PICHU);
        pk_.setLevel( 1);
        pk_.initAttaques(data_, true, data_.getMaxIv());
        pk_.initIv(new Difficulty());
        assertEq(1, pk_.getMoves().size());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getCurrent());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getMax());
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
    }

    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Given1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(3, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getCurrent());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        PokemonData fPk_ = data_.getPokemon(pk_.getName());
        assertEq(70, pk_.getHappiness());
        assertEq(Rate.zero(), pk_.getWonExpSinceLastLevel());
        assertEq(0, pk_.getNbStepsTeamLead());

        assertEq(POKE_BALL, data_.getBallDef());
        assertEq(POKE_BALL, pk_.getUsedBallCatching());
        assertEq(new Rate("3037/100"), fPk_.stat(pk_.getLevel(), Statistic.HP, pk_.getEv().getVal(Statistic.HP), pk_.getIv().getVal(Statistic.HP)));
        assertEq(new Rate("3037/100"), fPk_.statHp(pk_.getLevel(), pk_.getEv(), pk_.getIv()));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(new LgInt(100), pk_.rateRemainHp(data_));
    }

    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Map_GivenMoves1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(VIVE_ATTAQUE,  40L);
        moves_.put(CHARGE,  50L);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_, moves_);
        assertEq(2, pk_.getMoves().size());
        assertEq(40, pk_.getMoves().getVal(VIVE_ATTAQUE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(VIVE_ATTAQUE).getMax());
        assertEq(50, pk_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(50, pk_.getMoves().getVal(CHARGE).getMax());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        PokemonData fPk_ = data_.getPokemon(pk_.getName());
        assertEq(70, pk_.getHappiness());
        assertEq(Rate.zero(), pk_.getWonExpSinceLastLevel());
        assertEq(0, pk_.getNbStepsTeamLead());

        assertEq(POKE_BALL, data_.getBallDef());
        assertEq(POKE_BALL, pk_.getUsedBallCatching());
        assertEq(new Rate("3037/100"), fPk_.stat(pk_.getLevel(), Statistic.HP, pk_.getEv().getVal(Statistic.HP), pk_.getIv().getVal(Statistic.HP)));
        assertEq(new Rate("3037/100"), fPk_.statHp(pk_.getLevel(), pk_.getEv(), pk_.getIv()));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }

    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Gender_GivenNoGender1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        PokemonPlayer pk_ = new PokemonPlayer(base_,data_,Gender.NO_GENDER, new Difficulty());
        assertEq(Gender.NO_GENDER, pk_.getGender());
    }
    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Gender_GivenFemale2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        PokemonPlayer pk_ = new PokemonPlayer(base_,data_,Gender.FEMALE, new Difficulty());
        assertEq(Gender.FEMALE, pk_.getGender());
    }
    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Gender_GivenMale3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        PokemonPlayer pk_ = new PokemonPlayer(base_,data_,Gender.MALE, new Difficulty());
        assertEq(Gender.MALE, pk_.getGender());
    }
    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Sex_GivenSexFemale1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        PokemonPlayer pk_ = new PokemonPlayer(base_,data_,Sex.GIRL, new Difficulty());
        assertEq(Gender.FEMALE, pk_.getGender());
    }
    @Test
    public void new_PokemonPlayer_Pokemon_DataBase_Sex_GivenSexMale2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        PokemonPlayer pk_ = new PokemonPlayer(base_,data_,Sex.BOY, new Difficulty());
        assertEq(Gender.MALE, pk_.getGender());
    }

    @Test
    public void initAleaCapaciteGenre1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setItem(NULL_REF);
        PokemonData fPk_ = data_.getPokemon(PIKACHU);
        assertEq(1, fPk_.getGenderRep().getPossibleGenders().size());
        assertEq(1, fPk_.getAbilities().size());
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.initAleaCapaciteGenre(data_);
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
    }

    @Test
    public void new_PokemonPlayer_Fossile_DataBase_1Test() {
        DataBase data_ = initDb();
        Fossil fos_;
        fos_ = new Fossil();
        fos_.setPokemon(PIKACHU);
        fos_.setLevel( 7);
        PokemonData fPk_ = data_.getPokemon(PIKACHU);
        assertEq(1, fPk_.getGenderRep().getPossibleGenders().size());
        assertEq(1, fPk_.getAbilities().size());
        PokemonPlayer pk_ = new PokemonPlayer(fos_,data_, new Difficulty());
        assertEq(PIKACHU, pk_.getName());
        assertEq(3, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getCurrent());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(70, pk_.getHappiness());
        assertEq(Rate.zero(), pk_.getWonExpSinceLastLevel());
        assertEq(0, pk_.getNbStepsTeamLead());

        assertEq(POKE_BALL, data_.getBallDef());
        assertEq(POKE_BALL, pk_.getUsedBallCatching());
        assertEq(new Rate("3037/100"), fPk_.statHp(pk_.getLevel(), pk_.getEv(), pk_.getIv()));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(7,pk_.getLevel());
        assertEq(NULL_REF,pk_.getItem());
    }
    @Test
    public void new_PokemonPlayer_Egg_DataBase_1Test() {
        DataBase data_ = initDb();
        PokemonData fPk_ = data_.getPokemon(PIKACHU);
        assertEq(1, fPk_.getGenderRep().getPossibleGenders().size());
        assertEq(1, fPk_.getAbilities().size());
        PokemonPlayer pk_ = new PokemonPlayer(new Egg(PIKACHU),data_, new Difficulty());
        assertEq(PIKACHU, pk_.getName());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(31, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pk_.getIv().getVal(Statistic.HP));
        assertEq(140, pk_.getHappiness());
        assertEq(Rate.zero(), pk_.getWonExpSinceLastLevel());
        assertEq(0, pk_.getNbStepsTeamLead());

        assertEq(POKE_BALL, data_.getBallDef());
        assertEq(POKE_BALL, pk_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), fPk_.statHp(pk_.getLevel(), pk_.getEv(), pk_.getIv()));
        assertEq(new Rate("1291/100"), pk_.getRemainingHp());
        assertEq(PARATONNERRE,pk_.getAbility());
        assertEq(Gender.NO_GENDER,pk_.getGender());
        assertEq(1,pk_.getLevel());
        assertEq(NULL_REF,pk_.getItem());
    }

    @Test
    public void initIv1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        Difficulty diff_;
        diff_ = new Difficulty();
        diff_.setIvPlayer( 20);
        pk_.initIv(diff_);
        assertEq(20, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void initIv2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.setIv(null);
        Difficulty diff_;
        diff_ = new Difficulty();
        diff_.setIvPlayer( 20);
        pk_.initIv(diff_);
        assertEq(20, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, pk_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void initHp1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        Difficulty diff_;
        diff_ = new Difficulty();
        diff_.setIvPlayer( 20);
        pk_.initIv(diff_);
        pk_.initHp(data_);
        assertEq(20, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("148/5"), pk_.getRemainingHp());
        assertEq(new Rate("148/5"), pk_.pvMax(data_));
    }

    @Test
    public void initHp2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.getRemainingHp().affect(new Rate("100"));
        Difficulty diff_;
        diff_ = new Difficulty();
        diff_.setIvPlayer( 20);
        pk_.initIv(diff_);
        pk_.initHp(data_);
        assertEq(20, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("148/5"), pk_.getRemainingHp());
        assertEq(new Rate("148/5"), pk_.pvMax(data_));
    }

    @Test
    public void initHp3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.getRemainingHp().affect(new Rate("1"));
        Difficulty diff_;
        diff_ = new Difficulty();
        diff_.setIvPlayer( 20);
        pk_.initIv(diff_);
        pk_.initHp(data_);
        assertEq(20, pk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, pk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, pk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, pk_.getIv().getVal(Statistic.HP));
        assertEq(new Rate("1"), pk_.getRemainingHp());
        assertEq(new Rate("148/5"), pk_.pvMax(data_));
    }

    @Test
    public void isKo1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertTrue(!pk_.isKo());
    }

    @Test
    public void isKo2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.setRemainingHp(Rate.zero());
        assertTrue(pk_.isKo());
    }

    @Test
    public void evGagnes1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        long increment_ = ((Boost) data_.getItem(MUSCLE)).getEvs().getVal(Statistic.ATTACK);
        assertEq(2, pk_.evGagnes(increment_, Statistic.ATTACK , 20));
        assertEq(1, pk_.evGagnes(increment_, Statistic.ATTACK , 1));
    }

    @Test
    public void pointBonheurGagnesSansObjet1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        assertEq(1, pk_.pointBonheurGagnesSansObjet(data_));
    }

    @Test
    public void pointBonheurGagnesSansObjet2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(GRELOT);
        ItemForBattle obj_ = (ItemForBattle) data_.getItem(GRELOT);
        assertEq(new Rate("2"), obj_.getMultWinningHappiness());
        assertEq(2, pk_.pointBonheurGagnesSansObjet(data_));
    }

    @Test
    public void pointBonheurGagnesSansObjet3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setHappiness( data_.getHappinessMax());
        pk_.setItem(NULL_REF);
        assertEq(0, pk_.pointBonheurGagnesSansObjet(data_));
    }
    @Test
    public void pointBonheurGagnesSansObjet4Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setHappiness( data_.getHappinessMax());
        pk_.setItem(GRELOT);
        assertEq(0, pk_.pointBonheurGagnesSansObjet(data_));
    }

    @Test
    public void pointBonheurGagnesSansObjet5Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(POTION);
        ItemForBattle obj_ = (ItemForBattle) data_.getItem(GRELOT);
        assertEq(new Rate("2"), obj_.getMultWinningHappiness());
        assertEq(1, pk_.pointBonheurGagnesSansObjet(data_));
    }

    @Test
    public void pointBonheurGagnesSansObjet6Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(ORBE_VIE);
        ItemForBattle obj_ = (ItemForBattle) data_.getItem(GRELOT);
        assertEq(new Rate("2"), obj_.getMultWinningHappiness());
        assertEq(1, pk_.pointBonheurGagnesSansObjet(data_));
    }

    @Test
    public void moveTutors1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        StringList moveTutors_ = pk_.moveTutors(data_);
        assertEq(1, moveTutors_.size());
        assertEq(VIVE_ATTAQUE, moveTutors_.first());
    }
    @Test
    public void moveTutors2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,5);
        StringList moveTutors_ = pk_.moveTutors(data_);
        assertEq(1, moveTutors_.size());
        assertEq(VIVE_ATTAQUE, moveTutors_.first());
    }
    @Test
    public void moveTutors3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,2);
        StringList moveTutors_ = pk_.moveTutors(data_);
        assertEq(1, moveTutors_.size());
        assertEq(VIVE_ATTAQUE, moveTutors_.first());
    }

    @Test
    public void pointBonheurGagnes1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(GRELOT);
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHappiness(new StringMap<Long>());
        assertEq(2, pk_.pointBonheurGagnes(healingObject_,data_));
    }
    @Test
    public void pointBonheurGagnes2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(GRELOT);
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(6, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(NULL_REF);
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHappiness(new StringMap<Long>());
        assertEq(1, pk_.pointBonheurGagnes(healingObject_,data_));
    }
    @Test
    public void pointBonheurGagnes4Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(NULL_REF);
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(3, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes5Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(GRELOT);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());
        assertEq(2, pk_.pointBonheurGagnes(healingObject_,data_));
    }
    @Test
    public void pointBonheurGagnes6Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(GRELOT);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(6, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes7Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(NULL_REF);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());
        assertEq(1, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes8Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(NULL_REF);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(3, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes9Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(NULL_REF);
        pk_.setHappiness( 170);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(0, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes10Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(BAIE_ENIGMA);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(3, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void pointBonheurGagnes11Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,  7);
        pk_.setItem(PT_DE_MIRE);
        Boost healingObject_ = new Boost();
        healingObject_.setHappiness(new StringMap<Long>());

        healingObject_.getHappiness().put(data_.getBallDef(),  3L);
        assertEq(3, pk_.pointBonheurGagnes(healingObject_,data_));
    }

    @Test
    public void deplacement1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.deplacement(data_);
        assertEq(1, pk_.getNbStepsTeamLead());
        assertEq(70, pk_.getHappiness());
    }

    @Test
    public void deplacement2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        assertEq(5, pk_.getNbStepsTeamLead());
        assertEq(70, pk_.getHappiness());
    }

    @Test
    public void deplacement3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        assertEq(0, pk_.getNbStepsTeamLead());
        assertEq(71, pk_.getHappiness());
    }

    @Test
    public void deplacement4Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        pk_.deplacement(data_);
        assertEq(4, pk_.getNbStepsTeamLead());
        assertEq(71, pk_.getHappiness());
    }

    @Test
    public void deplacement5Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        loopMoving(pk_, 1000, data_);
        assertEq(0, pk_.getNbStepsTeamLead());
        assertEq(170, pk_.getHappiness());
    }

    @Test
    public void deplacement6Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        loopMoving(pk_, 1500, data_);
        assertEq(0, pk_.getNbStepsTeamLead());
        assertEq(170, pk_.getHappiness());
    }

    @Test
    public void variationBonheur1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.variationBonheur( 10, data_);
        assertEq(80, pk_.getHappiness());
    }

    @Test
    public void possibleEvolutions1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_LUNE, data_);
        assertEq(0, possibleEvolutions_.size());
    }
    @Test
    public void possibleEvolutions2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.FEMALE);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_SOLEIL, data_);
        assertEq(0, possibleEvolutions_.size());
    }
    @Test
    public void possibleEvolutions3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.MALE);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_SOLEIL, data_);
        assertEq(0, possibleEvolutions_.size());
    }
    @Test
    public void possibleEvolutions4Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_LUNE, data_);
        assertEq(1, possibleEvolutions_.size());
        assertEq(MELODELFE, possibleEvolutions_.first());
    }
    @Test
    public void possibleEvolutions5Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_SOLEIL, data_);
        assertEq(1, possibleEvolutions_.size());
        assertEq(MELODELFE_2, possibleEvolutions_.first());
    }

    @Test
    public void possibleEvolutions6Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PTITARD);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList possibleEvolutions_ = pk_.possibleEvolutions(PIERRE_LUNE, data_);
        assertEq(0, possibleEvolutions_.size());
    }

    @Test
    public void directEvolutionsByStone1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PTITARD);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList list_ = pk_.directEvolutionsByStone(PTITARD, data_);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, PTITARD));
    }

    @Test
    public void directEvolutionsByStone2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList list_ = pk_.directEvolutionsByStone(MELOFEE, data_);
        assertEq(3, list_.size());
        assertTrue(StringUtil.contains(list_, MELOFEE));
        assertTrue(StringUtil.contains(list_, MELODELFE));
        assertTrue(StringUtil.contains(list_, MELODELFE_2));
    }

    @Test
    public void getMovesForEvolution1Test() {
        DataBase data_ = initDb();
        StringMap<BoolVal> map_ = PokemonPlayer.getMovesForEvolution( 12, new StringList(CHARGE), MELODELFE, data_);
        assertEq(4, map_.size());
        assertSame(BoolVal.TRUE,map_.getVal(CHARGE));
        assertSame(BoolVal.TRUE,map_.getVal(ECLAIR));
        assertSame(BoolVal.TRUE,map_.getVal(OEIL_MIRACLE));
        assertSame(BoolVal.TRUE,map_.getVal(VIVE_ATTAQUE));
    }

    @Test
    public void getMovesForEvolution2Test() {
        DataBase data_ = initDb();
        StringMap<BoolVal> map_ = PokemonPlayer.getMovesForEvolution( 1, new StringList(CHARGE), TARTARD, data_);
        assertEq(5, map_.size());
        assertSame(BoolVal.TRUE,map_.getVal(CHARGE));
        assertSame(BoolVal.FALSE,map_.getVal(BULLES_D_O));
        assertSame(BoolVal.FALSE,map_.getVal(HYPNOSE));
        assertSame(BoolVal.FALSE,map_.getVal(SACRIFICE));
        assertSame(BoolVal.FALSE,map_.getVal(TORGNOLES));
    }

    @Test
    public void getMovesForEvolution3Test() {
        DataBase data_ = initDb();
        StringMap<BoolVal> map_ = PokemonPlayer.getMovesForEvolution( 100, new StringList(CHARGE), TARTARD, data_);
        assertEq(7, map_.size());
        assertSame(BoolVal.TRUE,map_.getVal(CHARGE));
        assertSame(BoolVal.FALSE,map_.getVal(BULLES_D_O));
        assertSame(BoolVal.FALSE,map_.getVal(HYPNOSE));
        assertSame(BoolVal.FALSE,map_.getVal(SACRIFICE));
        assertSame(BoolVal.FALSE,map_.getVal(TORGNOLES));
        assertSame(BoolVal.FALSE,map_.getVal(DYNAMOPOING));
        assertSame(BoolVal.FALSE,map_.getVal(LIRE_ESPRIT));
    }

    @Test
    public void evolve1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.evolve(PIERRE_LUNE, data_);
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
        assertEq(PIKACHU, pk_.getName());
    }
    @Test
    public void evolve2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(5, pk_.getMovesToBeKeptEvo().size());
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(OEIL_MIRACLE));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(JACKPOT));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(ECLAIR));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(CHARGE));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(VIVE_ATTAQUE));
        assertEq(MELODELFE, pk_.getPossibleEvolution());
        assertEq(MELOFEE, pk_.getName());
        assertEq(STATIK, pk_.getAbility());
    }

    @Test
    public void evolve3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("1873/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
        assertEq(MELODELFE, pk_.getName());
        assertEq(GARDE_MAGIK, pk_.getAbility());
        assertEq(new Rate("1993/100"), pk_.getRemainingHp());
    }
    @Test
    public void evolve4Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("1873/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_SOLEIL, data_);
        assertEq(2, pk_.getNewAbilitiesToBeChosen().size());
        assertTrue(StringUtil.contains(pk_.getNewAbilitiesToBeChosen(), GARDE_MYSTIK));
        assertTrue(StringUtil.contains(pk_.getNewAbilitiesToBeChosen(), GARDE_MAGIK));
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(MELODELFE_2, pk_.getPossibleEvolution());
        assertEq(MELOFEE, pk_.getName());
        assertEq(STATIK, pk_.getAbility());
        assertEq(new Rate("1873/100"), pk_.getRemainingHp());
    }

    @Test
    public void evolve5Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 4);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("541/25"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        assertEq(3, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertTrue(pk_.getMoves().contains(CHARGE));
        assertTrue(pk_.getMoves().contains(OEIL_MIRACLE));
        assertEq(MELODELFE, pk_.getName());
        assertEq(GARDE_MAGIK, pk_.getAbility());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(new Rate("581/25"), pk_.getRemainingHp());
    }

    @Test
    public void obtainAbilityAfterEvolving1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("1873/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_SOLEIL, data_);
        pk_.obtainAbilityAfterEvolving(GARDE, data_);
        assertEq(MELODELFE_2, pk_.getName());
        assertEq(GARDE, pk_.getAbility());
        assertEq(new Rate("1993/100"), pk_.getRemainingHp());
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
    }

    @Test
    public void learnMovesAfterEvolving1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_SOLEIL, data_);
        pk_.getMovesToBeKeptEvo().put(ECLAIR, BoolVal.TRUE);
        pk_.getMovesToBeKeptEvo().put(OEIL_MIRACLE, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(VIVE_ATTAQUE, BoolVal.TRUE);
        pk_.learnMovesAfterEvolving(GARDE_MAGIK,data_);
        assertEq(0, pk_.getNewAbilitiesToBeChosen().size());
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
        assertEq(4, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertTrue(pk_.getMoves().contains(VIVE_ATTAQUE));
        assertTrue(pk_.getMoves().contains(CHARGE));
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertEq(MELODELFE_2, pk_.getName());
        assertEq(new Rate("3317/100"), pk_.getRemainingHp());
    }

    @Test
    public void learnMovesAfterEvolving2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_SOLEIL, data_);
        pk_.getMovesToBeKeptEvo().put(ECLAIR, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(OEIL_MIRACLE, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(VIVE_ATTAQUE, BoolVal.FALSE);
        pk_.learnMovesAfterEvolving(GARDE_MAGIK,data_);
        assertEq(2, pk_.getNewAbilitiesToBeChosen().size());
        assertTrue(StringUtil.contains(pk_.getNewAbilitiesToBeChosen(), GARDE_MAGIK));
        assertTrue(StringUtil.contains(pk_.getNewAbilitiesToBeChosen(), GARDE_MYSTIK));
        assertEq(5, pk_.getMovesToBeKeptEvo().size());
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(JACKPOT));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(ECLAIR));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(CHARGE));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(VIVE_ATTAQUE));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(OEIL_MIRACLE));
        assertEq(MELODELFE_2, pk_.getPossibleEvolution());
        assertEq(4, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertTrue(pk_.getMoves().contains(VIVE_ATTAQUE));
        assertTrue(pk_.getMoves().contains(CHARGE));
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertEq(MELOFEE, pk_.getName());
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }

    @Test
    public void learnMovesAfterEvolvingWithOneAbility1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        pk_.getMovesToBeKeptEvo().put(ECLAIR, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(CHARGE, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(VIVE_ATTAQUE, BoolVal.FALSE);
        pk_.learnMovesAfterEvolvingWithOneAbility(data_);
        assertEq(5, pk_.getMovesToBeKeptEvo().size());
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(ECLAIR));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(OEIL_MIRACLE));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(VIVE_ATTAQUE));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(JACKPOT));
        assertSame(BoolVal.FALSE,pk_.getMovesToBeKeptEvo().getVal(CHARGE));
        assertEq(MELODELFE, pk_.getPossibleEvolution());
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }

    @Test
    public void learnMovesAfterEvolvingWithOneAbility2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        pk_.getMovesToBeKeptEvo().put(ECLAIR, BoolVal.TRUE);
        pk_.getMovesToBeKeptEvo().put(OEIL_MIRACLE, BoolVal.TRUE);
        pk_.getMovesToBeKeptEvo().put(VIVE_ATTAQUE, BoolVal.TRUE);
        pk_.learnMovesAfterEvolvingWithOneAbility(data_);
        assertEq(5, pk_.getMovesToBeKeptEvo().size());
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(ECLAIR));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(OEIL_MIRACLE));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(VIVE_ATTAQUE));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(JACKPOT));
        assertSame(BoolVal.TRUE,pk_.getMovesToBeKeptEvo().getVal(CHARGE));
        assertEq(MELOFEE, pk_.getName());
        assertEq(MELODELFE, pk_.getPossibleEvolution());
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }

    @Test
    public void learnMovesAfterEvolvingWithOneAbility3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(MELOFEE);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        pk_.evolve(PIERRE_LUNE, data_);
        pk_.getMovesToBeKeptEvo().put(ECLAIR, BoolVal.TRUE);
        pk_.getMovesToBeKeptEvo().put(OEIL_MIRACLE, BoolVal.FALSE);
        pk_.getMovesToBeKeptEvo().put(VIVE_ATTAQUE, BoolVal.TRUE);
        pk_.learnMovesAfterEvolvingWithOneAbility(data_);
        assertEq(0, pk_.getMovesToBeKeptEvo().size());
        assertEq(NULL_REF, pk_.getPossibleEvolution());
        assertEq(4, pk_.getMoves().size());
        assertTrue(pk_.getMoves().contains(ECLAIR));
        assertTrue(pk_.getMoves().contains(VIVE_ATTAQUE));
        assertTrue(pk_.getMoves().contains(CHARGE));
        assertTrue(pk_.getMoves().contains(JACKPOT));
        assertEq(MELODELFE, pk_.getName());
        assertEq(new Rate("3317/100"), pk_.getRemainingHp());
    }

    @Test
    public void variationPvRestants1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.setRemainingHp(new Rate("10"));
        pk_.variationPvRestants(new Rate("15"));
        assertEq(new Rate("25"), pk_.getRemainingHp());
    }

    @Test
    public void fullHeal1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        pk_.setRemainingHp(Rate.divide(pk_.getRemainingHp(), new Rate("2")));
        pk_.getMoves().getVal(PASSE_PASSE).setCurrent( 0);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        pk_.getMoves().getVal(OEIL_MIRACLE).setCurrent( 0);
        pk_.setStatus(new StringList(SOMMEIL));
        pk_.fullHeal(data_);
        assertEq(new Rate("3037/100"), pk_.pvMax(data_));
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(0, pk_.getStatus().size());
    }

    @Test
    public void pvSoignesBaie1Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_ORAN);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("12"));
        assertEq(new Rate("10"),pk_.pvSoignesBaie(berry_, data_));
    }
    @Test
    public void pvSoignesBaie2Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_ORAN);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(new Rate("837/100"),pk_.pvSoignesBaie(berry_, data_));
    }
    @Test
    public void pvSoignesBaie3Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_ENIGMA);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("12"));
        assertEq(new Rate("3037/400"),pk_.pvSoignesBaie(berry_, data_));
    }

    @Test
    public void pvSoignesBaie4Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_GOWAV);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("12"));
        assertEq(new Rate("3037/800"),pk_.pvSoignesBaie(berry_, data_));
    }

    @Test
    public void pvSoignesBaie5Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_CERIZ);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("12"));
        assertEq(Rate.zero(), pk_.pvSoignesBaie(berry_, data_));
    }
    @Test
    public void pvSoignesBaie6Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_CERIZ);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(Rate.zero(), pk_.pvSoignesBaie(berry_, data_));
    }

    @Test
    public void pvSoignesBaie7Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_ENIGMA);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("25"));
        assertEq(new Rate("537/100"),pk_.pvSoignesBaie(berry_, data_));
    }

    @Test
    public void pvSoignesBaie8Test() {
        DataBase data_ = initDb();
        Berry berry_ = (Berry) data_.getItem(BAIE_GOWAV);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("30"));
        assertEq(new Rate("37/100"),pk_.pvSoignesBaie(berry_, data_));
    }

    @Test
    public void pvSoignesAvecStatut1Test() {
        DataBase data_ = initDb();
        HealingHpStatus healingObject_ = new HealingHpStatus();
        healingObject_.setHealedHpRate(new Rate("1/4"));
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(new Rate("3037/400"),pk_.pvSoignesAvecStatut(healingObject_, data_));
    }
    @Test
    public void pvSoignesAvecStatut2Test() {
        DataBase data_ = initDb();
        HealingHpStatus healingObject_ = new HealingHpStatus();
        healingObject_.setHealedHpRate(new Rate("1/2"));
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(new Rate("837/100"),pk_.pvSoignesAvecStatut(healingObject_, data_));
    }
    @Test
    public void pvSoignesAvecStatut3Test() {
        DataBase data_ = initDb();
        HealingHpStatus healingObject_ = new HealingHpStatus();
        healingObject_.setHealedHpRate(Rate.zero());
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(Rate.zero(),pk_.pvSoignesAvecStatut(healingObject_, data_));
    }
    @Test
    public void pvSoignesSansStatut1Test() {
        DataBase data_ = initDb();
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHp(new Rate("10"));
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("12"));
        assertEq(new Rate("10"),pk_.pvSoignesSansStatut(healingObject_, data_));
    }
    @Test
    public void pvSoignesSansStatut2Test() {
        DataBase data_ = initDb();
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHp(new Rate("10"));
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.initIv(new Difficulty());
        pk_.initPvRestants(data_);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(new Rate("837/100"),pk_.pvSoignesSansStatut(healingObject_, data_));
    }

    @Test
    public void pvSoignesSansStatut3Test() {
        DataBase data_ = initDb();
        HealingHp healingObject_ = new HealingHp();
        healingObject_.setHp(Rate.zero());
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.setRemainingHp(new Rate("22"));
        assertEq(Rate.zero(), pk_.pvSoignesSansStatut(healingObject_, data_));
    }

    @Test
    public void ppSoignesAttaques1Test() {
        DataBase data_ = initDb();
        HealingPp healingObject_ = new HealingPp();
        healingObject_.setHealingAllMovesPp(true);
        healingObject_.setHealingAllMovesFullpp(0);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(PASSE_PASSE).setCurrent( 0);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        pk_.getMoves().getVal(OEIL_MIRACLE).setCurrent( 0);
        StringMap<Long> healedMoves_ = pk_.ppSoignesAttaques(healingObject_);
        assertEq(10, healedMoves_.getVal(PASSE_PASSE));
        assertEq(20, healedMoves_.getVal(JACKPOT));
        assertEq(40, healedMoves_.getVal(OEIL_MIRACLE));
    }

    @Test
    public void ppSoignesAttaques2Test() {
        DataBase data_ = initDb();
        HealingPp healingObject_ = new HealingPp();
        healingObject_.setHealingAllMovesPp(false);
        healingObject_.setHealingAllMovesFullpp(10);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(PASSE_PASSE).setCurrent( 0);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        pk_.getMoves().getVal(OEIL_MIRACLE).setCurrent( 0);
        StringMap<Long> healedMoves_ = pk_.ppSoignesAttaques(healingObject_);
        assertEq(10, healedMoves_.getVal(PASSE_PASSE));
        assertEq(10, healedMoves_.getVal(JACKPOT));
        assertEq(10, healedMoves_.getVal(OEIL_MIRACLE));
    }

    @Test
    public void ppSoignesAttaqueBaie1Test() {
        DataBase data_ = initDb();
        Berry healingObject_ = new Berry();
        healingObject_.setHealPp(5);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        assertEq(5,pk_.ppSoignesAttaqueBaie(healingObject_,JACKPOT));
    }
    @Test
    public void ppSoignesAttaqueBaie2Test() {
        DataBase data_ = initDb();
        Berry healingObject_ = new Berry();
        healingObject_.setHealPp(10);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 14);
        assertEq(6,pk_.ppSoignesAttaqueBaie(healingObject_,JACKPOT));
    }
    @Test
    public void ppSoignesAttaque1Test() {
        DataBase data_ = initDb();
        HealingPp healingObject_ = new HealingPp();
        healingObject_.setHealingMoveFullpp(true);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        assertEq(20,pk_.ppSoignesAttaque(healingObject_,JACKPOT));
    }
    @Test
    public void ppSoignesAttaque2Test() {
        DataBase data_ = initDb();
        HealingPp healingObject_ = new HealingPp();
        healingObject_.setHealingMoveFullpp(false);
        healingObject_.setHealedMovePp(5);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        assertEq(5,pk_.ppSoignesAttaque(healingObject_,JACKPOT));
    }
    @Test
    public void ppSoignesAttaque3Test() {
        DataBase data_ = initDb();
        HealingPp healingObject_ = new HealingPp();
        healingObject_.setHealingMoveFullpp(false);
        healingObject_.setHealedMovePp(10);
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 14);
        assertEq(6,pk_.ppSoignesAttaque(healingObject_,JACKPOT));
    }

    @Test
    public void getAllEvolutions1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PTITARD);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringMap<Long> evos_ = pk_.getAllEvolutions(data_);
        assertEq(3, evos_.size());
        assertEq(25, evos_.getVal(TETARTE));
        assertEq(25, evos_.getVal(TARTARD));
        assertEq(25, evos_.getVal(TARPAUD));
    }

    @Test
    public void getAllEvolutions2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(NINGALE);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringMap<Long> evos_ = pk_.getAllEvolutions(data_);
        assertEq(2, evos_.size());
        assertEq(20, evos_.getVal(NINJASK));
        assertEq(20, evos_.getVal(MUNJA));
    }

    @Test
    public void getAllEvolutions3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(NUCLEOS);
        base_.setLevel( 3);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringMap<Long> evos_ = pk_.getAllEvolutions(data_);
        assertEq(2, evos_.size());
        assertEq(32, evos_.getVal(MEIOS));
        assertEq(41, evos_.getVal(SYMBIOS));
    }

    @Test
    public void getAllEvolutions4Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(TETARTE);
        base_.setLevel( 20);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringMap<Long> evos_ = pk_.getAllEvolutions(data_);
        assertEq(2, evos_.size());
        assertEq(20, evos_.getVal(TARTARD));
        assertEq(20, evos_.getVal(TARPAUD));
    }

    @Test
    public void getAllEvolutions5Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(TARTARD);
        base_.setLevel( 20);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringMap<Long> evos_ = pk_.getAllEvolutions(data_);
        assertEq(0, evos_.size());
    }

    @Test
    public void getAllEvolutions6Test() {
        DataBase data_ = initDb();
        StringMap<Long> evos_ = PokemonPlayer.getAllEvolutions(PTITARD,  3, true, data_);
        assertEq(3, evos_.size());
        assertEq(25, evos_.getVal(StringUtil.concat(PTITARD,SEPARATOR,TETARTE)));
        assertEq(25, evos_.getVal(StringUtil.concat(PTITARD,SEPARATOR,TETARTE,SEPARATOR,TARTARD)));
        assertEq(25, evos_.getVal(StringUtil.concat(PTITARD,SEPARATOR,TETARTE,SEPARATOR,TARPAUD)));
    }

    @Test
    public void getAllEvolutions7Test() {
        DataBase data_ = initDb();
        StringMap<Long> evos_ = PokemonPlayer.getAllEvolutions(NINGALE,  3, true, data_);
        assertEq(2, evos_.size());
        assertEq(20, evos_.getVal(StringUtil.concat(NINGALE,SEPARATOR,NINJASK)));
        assertEq(20, evos_.getVal(StringUtil.concat(NINGALE,SEPARATOR,MUNJA)));
    }

    @Test
    public void getAllEvolutions8Test() {
        DataBase data_ = initDb();
        StringMap<Long> evos_ = PokemonPlayer.getAllEvolutions(NUCLEOS,  3, true, data_);
        assertEq(2, evos_.size());
        assertEq(32, evos_.getVal(StringUtil.concat(NUCLEOS,SEPARATOR,MEIOS)));
        assertEq(41, evos_.getVal(StringUtil.concat(NUCLEOS,SEPARATOR,MEIOS,SEPARATOR,SYMBIOS)));
    }

    @Test
    public void getAllEvolutions9Test() {
        DataBase data_ = initDb();
        StringMap<Long> evos_ = PokemonPlayer.getAllEvolutions(TETARTE,  20, true, data_);
        assertEq(2, evos_.size());
        assertEq(20, evos_.getVal(StringUtil.concat(TETARTE,SEPARATOR,TARTARD)));
        assertEq(20, evos_.getVal(StringUtil.concat(TETARTE,SEPARATOR,TARPAUD)));
    }

    @Test
    public void getAllEvolutions10Test() {
        DataBase data_ = initDb();
        StringMap<Long> evos_ = PokemonPlayer.getAllEvolutions(TARTARD,  20, true, data_);
        assertEq(0, evos_.size());
    }

    @Test
    public void getDirectEvolutions1Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList evos_ = pk_.getDirectEvolutions(data_);
        assertEq(0, evos_.size());
    }

    @Test
    public void getDirectEvolutions2Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(CHENITI);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.FEMALE);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList evos_ = pk_.getDirectEvolutions(data_);
        assertEq(1, evos_.size());
        assertEq(CHENISELLE, evos_.first());
    }

    @Test
    public void getDirectEvolutions3Test() {
        DataBase data_ = initDb();
        Pokemon base_ = new WildPk();
        base_.setName(CHENITI);
        base_.setLevel( 7);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.MALE);
        PokemonPlayer pk_ = pkMoves(data_, new Difficulty(), base_);
        StringList evos_ = pk_.getDirectEvolutions(data_);
        assertEq(1, evos_.size());
        assertEq(PAPILORD, evos_.first());
    }

    @Test
    public void boostPp1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.boostPp(JACKPOT,  7);
        assertEq(27, pk_.getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void gainEv1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.gainEv(MUSCLE,  0, data_);
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
    }

    @Test
    public void gainEv2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        pk_.gainEv(MUSCLE,  1, data_);
        assertEq(1, pk_.getEv().getVal(Statistic.ATTACK));
    }

    @Test
    public void learntMove1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        assertTrue(pk_.learntMove(JACKPOT));
    }

    @Test
    public void learntMove2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,7);
        assertTrue(!pk_.learntMove(PISTOLET_A_O));
    }

    @Test
    public void learnMove1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        pk_.learnMove(PISTOLET_A_O, data_);
        assertEq(2, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void learnMove2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,16);
        pk_.learnMove(PISTOLET_A_O, JACKPOT, data_);
        assertEq(4, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getCurrent());
        assertEq(10, pk_.getMoves().getVal(PASSE_PASSE).getMax());
        assertEq(5, pk_.getMoves().getVal(ORAGE).getCurrent());
        assertEq(5, pk_.getMoves().getVal(ORAGE).getMax());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getCurrent());
        assertEq(40, pk_.getMoves().getVal(OEIL_MIRACLE).getMax());
    }

    @Test
    public void learnMove3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        pk_.learnMove(PISTOLET_A_O, JACKPOT, data_);
        assertEq(2, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void learnMovesAfterForgettingAll1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,16);
        StringList moves_ = new StringList();
        moves_.add(JACKPOT);
        moves_.add(ECLAIR);
        moves_.add(PISTOLET_A_O);
        moves_.add(CHARGE);
        pk_.learnMovesAfterForgettingAll(moves_, data_);
        assertEq(4, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getCurrent());
        assertEq(20, pk_.getMoves().getVal(PISTOLET_A_O).getMax());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getCurrent());
        assertEq(50, pk_.getMoves().getVal(ECLAIR).getMax());
        assertEq(30, pk_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(30, pk_.getMoves().getVal(CHARGE).getMax());
    }

    @Test
    public void soinPpAttaques1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        pk_.getMoves().getVal(JACKPOT).setCurrent( 0);
        StringMap<Long> map_;
        map_ = new StringMap<Long>();
        map_.put(JACKPOT,  20L);
        pk_.soinPpAttaques(map_);
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
    }

    @Test
    public void soinStatuts1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        pk_.getStatus().add(SOMMEIL);
        pk_.soinStatuts(new StringList(SOMMEIL));
        assertEq(0, pk_.getStatus().size());
    }

    @Test
    public void wonPp1Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        Boost boost_ = (Boost) data_.getItem(PP_PLUS);
        long pp_ = pk_.wonPp(boost_, JACKPOT,  50);
        assertEq(3, pp_);
    }

    @Test
    public void wonPp2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_,1);
        Boost boost_ = (Boost) data_.getItem(PP_PLUS);
        long pp_ = pk_.wonPp(boost_, JACKPOT,  22);
        assertEq(2, pp_);
    }

    @Test
    public void initilializeFromExchange1Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getMax());
        assertTrue(sent_.getEv().contains(Statistic.ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPEED));
        assertTrue(sent_.getEv().contains(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertEq(0, n_.getMaximum( 0));
        assertEq(0, n_.getMinimum( 0));
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange2Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove( 10));
        sent_.getMoves().put(LUTTE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.setEv(new IdMap<Statistic,Long>());
        sent_.getEv().put(Statistic.ATTACK,  0L);
        sent_.getEv().put(Statistic.DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPECIAL_ATTACK,  0L);
        sent_.getEv().put(Statistic.SPECIAL_DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPEED,  1L);
        sent_.getEv().put(Statistic.HP,  0L);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(20, sent_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, sent_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, sent_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(1, sent_.getEv().getVal(Statistic.SPEED));
        assertEq(0, sent_.getEv().getVal(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange3Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.getMoves().put(PISTOLET_A_O, new UsesOfMove( 10));
        sent_.getMoves().put(JACKPOT, new UsesOfMove( 10));
        sent_.getMoves().put(ECLAIR, new UsesOfMove( 10));
        sent_.getMoves().put(ORAGE, new UsesOfMove( 10));
        StringList list_ = new StringList(sent_.getMoves().getKeys());
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(4, sent_.getMoves().size());
        assertTrue(list_.containsAllObj(sent_.getMoves().getKeys()));
        assertTrue(data_.getMoves().containsAllAsKeys(sent_.getMoves().getKeys()));
        assertTrue(sent_.getEv().contains(Statistic.ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPEED));
        assertTrue(sent_.getEv().contains(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertEq(0, n_.getMaximum( 0));
        assertEq(0, n_.getMinimum( 0));
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange4Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove( 10));
        sent_.getMoves().put(LUTTE, new UsesOfMove( 10));
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getMax());
        assertTrue(sent_.getEv().contains(Statistic.ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getEv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getEv().contains(Statistic.SPEED));
        assertTrue(sent_.getEv().contains(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertEq(0, n_.getMaximum( 0));
        assertEq(0, n_.getMinimum( 0));
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange5Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(null);
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.setEv(null);
        sent_.setIv(new IdMap<Statistic,Long>());
        sent_.setStatus(null);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(20, sent_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, sent_.getMoves().getVal(JACKPOT).getMax());
        assertEq(0, sent_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPEED));
        assertEq(0, sent_.getEv().getVal(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange6Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove( 10));
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.setEv(new IdMap<Statistic,Long>());
        sent_.getEv().put(Statistic.ATTACK,  0L);
        sent_.getEv().put(Statistic.DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPECIAL_ATTACK,  0L);
        sent_.getEv().put(Statistic.SPECIAL_DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPEED,  1L);
        sent_.getEv().put(Statistic.HP,  0L);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getMax());
        assertEq(0, sent_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(1, sent_.getEv().getVal(Statistic.SPEED));
        assertEq(0, sent_.getEv().getVal(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1291/100"), sent_.pvMax(data_));
        assertEq(new Rate("1291/100"), sent_.getRemainingHp());
    }

    @Test
    public void initilializeFromExchange7Test() {
        DataBase data_ = initDb();
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(PIKACHU);
        sent_.setLevel( 1);
        sent_.setAbility(STATIK);
        sent_.setItem(NULL_REF);
        sent_.setGender(Gender.NO_GENDER);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        sent_.setEv(new IdMap<Statistic,Long>());
        sent_.getEv().put(Statistic.ATTACK,  0L);
        sent_.getEv().put(Statistic.DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPECIAL_ATTACK,  0L);
        sent_.getEv().put(Statistic.SPECIAL_DEFENSE,  0L);
        sent_.getEv().put(Statistic.SPEED,  0L);
        sent_.getEv().put(Statistic.HP,  1L);
        sent_.initilializeFromExchange(data_);
        assertEq(PIKACHU, sent_.getName());
        assertEq(1, sent_.getLevel());
        assertEq(STATIK, sent_.getAbility());
        assertEq(NULL_REF, sent_.getItem());
        assertEq(Gender.NO_GENDER, sent_.getGender());
        assertEq(1, sent_.getMoves().size());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getCurrent());
        assertEq(30, sent_.getMoves().getVal(CHARGE).getMax());
        assertEq(0, sent_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, sent_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, sent_.getEv().getVal(Statistic.SPEED));
        assertEq(1, sent_.getEv().getVal(Statistic.HP));
        Longs n_ = new Longs(sent_.getEv().values());
        assertTrue(sent_.getIv().contains(Statistic.ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_ATTACK));
        assertTrue(sent_.getIv().contains(Statistic.SPECIAL_DEFENSE));
        assertTrue(sent_.getIv().contains(Statistic.SPEED));
        assertTrue(sent_.getIv().contains(Statistic.HP));
        n_ = new Longs(sent_.getIv().values());
        assertEq(31, n_.getMaximum( 31));
        assertEq(31, n_.getMinimum( 31));
        assertEq(70, sent_.getHappiness());
        assertEq(Rate.one(), sent_.getWonExpSinceLastLevel());
        assertEq(0, sent_.getNbStepsTeamLead());
        assertEq(POKE_BALL, sent_.getUsedBallCatching());
        assertEq(new Rate("1033/80"), sent_.pvMax(data_));
        assertEq(new Rate("1033/80"), sent_.getRemainingHp());
    }

    private static void loopMoving(PokemonPlayer _pk, int _nb, DataBase _data) {
        PokemonPlayer.deplacement(_pk, _nb, _data);
    }

}
