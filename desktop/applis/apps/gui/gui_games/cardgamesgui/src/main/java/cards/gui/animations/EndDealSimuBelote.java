package cards.gui.animations;

public final class EndDealSimuBelote implements Runnable {
    private SimulatingBeloteImpl simulatingBelote;

    public EndDealSimuBelote(SimulatingBeloteImpl _simulatingBelote) {
        simulatingBelote = _simulatingBelote;
    }

    @Override
    public void run() {
        simulatingBelote.endGuiDeal();
    }
}
