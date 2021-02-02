package aiki.game;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;


public class HostPokemonDuoTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void validate1Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate2Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate3Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate4Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate5Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate6Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate7Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate8Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate9Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(host_.validate(data));
    }

    @Test
    public void validate10Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate11Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate12Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        host_.setSecondPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate13Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate14Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate15Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        host_.setFirstPokemon(new PokemonPlayer());
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate16Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate17Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(NUCLEOS);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate18Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate19Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate20Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate21Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate22Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate23Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate24Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        host_.setNbSteps(4);
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate25Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        host_.setNbSteps(-4);
        assertTrue(!host_.validate(data));
    }

    @Test
    public void validate26Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setFirstPokemon(newPokemonPlayer(pk_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        host_.setSecondPokemon(newPokemonPlayer(pk_));
        host_.setNbSteps(-4);
        assertTrue(!host_.validate(data));
    }

    private PokemonPlayer newPokemonPlayer(Pokemon _pokemon) {
        return new PokemonPlayer(_pokemon, data);
    }
}
