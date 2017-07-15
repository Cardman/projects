package aiki.game;
import org.junit.BeforeClass;
import org.junit.Test;

import aiki.exceptions.GameLoadException;
import aiki.game.HostPokemonDuo;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;

@SuppressWarnings("static-method")
public class HostPokemonDuoTest extends InitializationDataBase {

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    @Test
    public void validate1Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate24Test() {
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        host_.setNbSteps(4);
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
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
        host_.validate(_data_);
    }

    private static PokemonPlayer newPokemonPlayer(Pokemon _pokemon) {
        return new PokemonPlayer(_pokemon, _data_);
    }
}
