package aiki.beans.simulation;

import org.junit.Test;

public final class SimulationBeanTest extends InitDbSimulation {
    @Test
    public void isDiffState() {
        assertTrue(callSimulationBeanIsDiffState(dispSimu()));
    }
}
