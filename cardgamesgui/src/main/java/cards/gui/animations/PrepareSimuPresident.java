package cards.gui.animations;

public final class PrepareSimuPresident implements Runnable {
    private SimulatingPresidentImpl simulatingPresident;

    public PrepareSimuPresident(SimulatingPresidentImpl _simulatingPresident) {
        simulatingPresident = _simulatingPresident;
    }

    @Override
    public void run() {
        simulatingPresident.prepareGui();
    }
}
