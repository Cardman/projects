package cards.gui.animations;

import cards.facade.Games;

public interface SimulationGame {

    void setSimulationGui();

    void stopSimulation();
    Games getGames();
}
