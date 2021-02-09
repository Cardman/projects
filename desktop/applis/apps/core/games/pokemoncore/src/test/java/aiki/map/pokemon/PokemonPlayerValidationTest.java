package aiki.map.pokemon;

import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;


public class PokemonPlayerValidationTest extends InitializationDataBase {

    @Test
    public void validate1Test() {
        DataBase data_ = initDb();
        assertTrue(pokemonPlayer(data_, (short) 3).validate(data_));
    }

    @Test
    public void validate2Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setItem(PETIT_RAPPEL);
        assertTrue(pk_.validate(data_));
    }

    @Test
    public void validate3Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setName(INVALID_DATA_KEY);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate4Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setItem(INVALID_DATA_KEY);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate5Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove((byte)5));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate6Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setAbility(INVALID_DATA_KEY);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate7Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().put(CHARGE, new UsesOfMove((byte)0));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate8Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().put(LUTTE, new UsesOfMove((byte)1));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate9Test() {
        DataBase data_ = initDb();
        assertTrue(!pokemonPlayer(data_, (short) -1).validate(data_));
    }

    @Test
    public void validate10Test() {
        DataBase data_ = initDb();
        assertTrue(!pokemonPlayer(data_, (short) 1000).validate(data_));
    }

    @Test
    public void validate11Test() {
        DataBase data_ = initDb();
        assertTrue(!new PokemonPlayer().validate(data_));
    }

    @Test
    public void validate12Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().clear();
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate13Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setHappiness((short) -1);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate14Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setHappiness((short) 1000);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate15Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().getVal(JACKPOT).setCurrent((short) -1);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate16Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().getVal(JACKPOT).setCurrent((short) 1000);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate17Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getStatus().add(GEL);
        assertTrue(pk_.validate(data_));
    }

    @Test
    public void validate18Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setUsedBallCatching(INVALID_DATA_KEY);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate19Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getStatus().add(INVALID_DATA_KEY);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate20Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getStatus().add(CAUCHEMAR);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate21Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setUsedBallCatching(PETIT_RAPPEL);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate22Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getEv().clear();
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate23Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getEv().put(Statistic.ACCURACY, (short) 1);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate24Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getEv().put(Statistic.DEFENSE, (short) -1);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate25Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setRemainedHp(new Rate("-1"));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate26Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setNbStepsTeamLead((short) -1);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate27Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setNbStepsTeamLead((short) 1000);
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate28Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setRemainedHp(new Rate("1000"));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate29Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.setWonExpSinceLastLevel(new Rate("-1"));
        assertTrue(!pk_.validate(data_));
    }

    @Test
    public void validate30Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 15);
        assertTrue(pk_.validate(data_));
        pk_.getMoves().put(DEMI_TOUR, new UsesOfMove((short) 15));
        assertTrue(!pk_.validate(data_));
        //because of too many moves
    }

    @Test
    public void validate31Test() {
        DataBase data_ = initDb();
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 3);
        pk_.getMoves().put(CHARGE, new UsesOfMove((byte)100));
        assertTrue(!pk_.validate(data_));
    }

//    @Test
//    public void validate30Test() {
//        PokemonPlayer pk_ = pokemonPlayer(data, (short) 3);
//        pk_.setGender(Gender.FEMALE);
//        pk_.validate(data);
//    }
//
//    @Test
//    public void validate31Test() {
//        PokemonPlayer pk_ = pokemonPlayer(data, (short) 3);
//        pk_.setGender(Gender.MALE);
//        pk_.validate(data);
//    }

    static PokemonPlayer pokemonPlayer(DataBase _data, short _level) {
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel(_level);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        return new PokemonPlayer(base_,_data);
    }
}
