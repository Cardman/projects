package aiki.db;

import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import code.maths.Rate;
import code.util.ObjectMap;
import code.util.ShortMap;
import code.util.StringList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class DataBaseValidationTest extends DataBaseValidationCommon {

    @Test
    public void fail1Test() {
        PokemonData pkData_ = Instances.newPokemonData();
        AbilityData ab_ = Instances.newAbilityData();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower("INVALID");
        eff_.setFail("INVALID");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.getEffects().add(eff_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setTypes(new StringList(ELECTRICK));
        Ball ball_ = Instances.newBall();
        DataBase data_ =new DataBase();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        data_.completeMembers(ECLAIR,move_);
        pkData_.setTypes(new StringList(ELECTRICK));
        pkData_.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)0));
        pkData_.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)0));
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        data_.completeMembers(PIKACHU,pkData_);
        data_.completeMembers(POKE_BALL,ball_);
        data_.completeMembers(PARATONNERRE,ab_);
        data_.getTableTypes().put(new TypesDuo(ELECTRICK,ELECTRICK),new Rate("1"));
        data_.sortEndRound();
        data_.getMap().setPlaces(new ShortMap<Place>());
        data_.getMap().setMiniMap(new ObjectMap<MiniMapCoords,TileMiniMap>());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(2);
        DataMap map_ = data_.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 0));
        data_.completeVariables();
        initConstants(data_);
        data_.setRateBoost("-2");
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.initCombosTest();
        data_.initTypesByTable();
        data_.setCheckTranslation(false);
        CheckNumericStringsFight.validateNumericBooleanStrings(data_);
        assertTrue(data_.isError());
    }
}
