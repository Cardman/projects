package cards.gui.animations;

/**Thread safe class*/
public final class SimulationRefreshHandPresidentGiftWinner extends Thread {

    private GoSimulatePresident demo;

    private int noDeal;

    public SimulationRefreshHandPresidentGiftWinner(GoSimulatePresident _demo, int _noDeal) {
        demo = _demo;
        noDeal = _noDeal;
    }

    @Override
    public void run() {
        demo.displayGivenCardsWinner(noDeal);
    }
}
