package cards.gui.animations;

public final class EndDealSimuPresident implements Runnable {
    private SimulatingPresidentImpl simulatingPresident;

    public EndDealSimuPresident(SimulatingPresidentImpl _simulatingPresident) {
        simulatingPresident = _simulatingPresident;
    }

    @Override
    public void run() {
        simulatingPresident.endGuiDeal();
    }
}
