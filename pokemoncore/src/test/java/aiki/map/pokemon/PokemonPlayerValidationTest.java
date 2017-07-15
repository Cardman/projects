package aiki.map.pokemon;
import org.junit.BeforeClass;
import org.junit.Test;

import code.maths.Rate;
import aiki.DataBase;
import aiki.exceptions.GameLoadException;
import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;

@SuppressWarnings("static-method")
public class PokemonPlayerValidationTest extends InitializationDataBase {

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    @Test
    public void validate1Test() {
        pokemonPlayer(_data_, (short) 3).validate(_data_);
    }

    @Test
    public void validate2Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setItem(PETIT_RAPPEL);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate3Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setName(INVALID_DATA_KEY);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate4Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setItem(INVALID_DATA_KEY);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate5Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove((byte)5));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate6Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setAbility(INVALID_DATA_KEY);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate7Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().put(CHARGE, new UsesOfMove((byte)0));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate8Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().put(LUTTE, new UsesOfMove((byte)1));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate9Test() {
        pokemonPlayer(_data_, (short) -1).validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate10Test() {
        pokemonPlayer(_data_, (short) 1000).validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate11Test() {
        new PokemonPlayer().validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate12Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().clear();
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate13Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setHappiness((short) -1);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate14Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setHappiness((short) 1000);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate15Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().getVal(JACKPOT).setCurrent((short) -1);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate16Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().getVal(JACKPOT).setCurrent((short) 1000);
        pk_.validate(_data_);
    }

    @Test
    public void validate17Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getStatus().add(GEL);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate18Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setUsedBallCatching(INVALID_DATA_KEY);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate19Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getStatus().add(INVALID_DATA_KEY);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate20Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getStatus().add(CAUCHEMAR);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate21Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setUsedBallCatching(PETIT_RAPPEL);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate22Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getEv().clear();
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate23Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getEv().put(Statistic.ACCURACY, (short) 1);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate24Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getEv().put(Statistic.DEFENSE, (short) -1);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate25Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setRemainedHp(new Rate("-1"));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate26Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setNbStepsTeamLead((short) -1);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate27Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setNbStepsTeamLead((short) 1000);
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate28Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setRemainedHp(new Rate("1000"));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate29Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.setWonExpSinceLastLevel(new Rate("-1"));
        pk_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate30Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 15);
        pk_.validate(_data_);
        pk_.getMoves().put(DEMI_TOUR, new UsesOfMove((short) 15));
        pk_.validate(_data_);
        //because of too many moves
    }

    @Test(expected=GameLoadException.class)
    public void validate31Test() {
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 3);
        pk_.getMoves().put(CHARGE, new UsesOfMove((byte)100));
        pk_.validate(_data_);
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
