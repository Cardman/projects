package aiki.instances;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class InstancesTest extends EquallablePkUtil {
    @Test
    public void test() {
        assertNotNull(Instances.newFight());
        assertNotNull(Instances.newFighter());
        assertNotNull(Instances.newGame());
        assertNotNull(Instances.newTeam());
        assertNotNull(Instances.newStatusBeginRoundSimple());
        assertNotNull(Instances.newPokemonPlayer());
        assertNotNull(Instances.newDataMap());
        assertNotNull(Instances.newPlayer());
        assertNotNull(Instances.newInventory());
        assertNotNull(Instances.newChoiceOfEvolutionAndMoves());
        assertNotNull(Instances.newActionMove());
        assertNotNull(Instances.newActionHealMove());
        assertNotNull(Instances.newActionSimpleHeal());
        assertNotNull(Instances.newActionSwitch());
        assertNotNull(Instances.newMovesAbilities());
        assertNotNull(Instances.newCombos());
        assertNotNull(Instances.newHealingSimpleItem());
        assertNotNull(Instances.newEffectWhileSendingWithStatistic());
        assertNotNull(Instances.newHostPokemonDuo());
        assertNotNull(Instances.newGerantPokemon());
        assertNotNull(Instances.newTileMiniMap());
        assertNotNull(Instances.newDifficulty());
        assertNotNull(Instances.newCondition());
        assertNotNull(Instances.newSellingItem());
    }
}
