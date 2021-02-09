package aiki.game;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;


public class HostPokemonDuoTest extends InitializationDataBase {

    @Test
    public void validate1Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate2Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate3Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate4Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate5Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate6Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate7Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate8Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate9Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(host_.validate(data_));
    }

    @Test
    public void validate10Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate11Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate12Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate13Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate14Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate15Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate16Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate17Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate18Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate19Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate20Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate21Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate22Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate23Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate24Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        host_.setNbSteps(4);
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate25Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        host_.setNbSteps(-4);
        assertTrue(!host_.validate(data_));
    }

    @Test
    public void validate26Test() {
        DataBase data_ = initDb();
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_, data_));
        host_.setNbSteps(-4);
        assertTrue(!host_.validate(data_));
    }

    private static PokemonPlayer newPokemonPlayer(Pokemon _pokemon, DataBase _data) {
        return new PokemonPlayer(_pokemon, _data);
    }
}
