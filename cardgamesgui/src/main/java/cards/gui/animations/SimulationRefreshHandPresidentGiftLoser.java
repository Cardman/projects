package cards.gui.animations;

/**Thread safe class*/
public final class SimulationRefreshHandPresidentGiftLoser extends Thread {

    private GoSimulatePresident demo;

    private int noDeal;

    public SimulationRefreshHandPresidentGiftLoser(GoSimulatePresident _demo, int _noDeal) {
        demo = _demo;
        noDeal = _noDeal;
    }

    @Override
    public void run() {
        demo.displayGivenCardsLoser(noDeal);
    }
}
