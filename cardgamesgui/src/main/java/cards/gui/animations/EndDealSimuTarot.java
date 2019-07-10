package cards.gui.animations;

public final class EndDealSimuTarot implements Runnable {
    private SimulatingTarotImpl simulatingTarot;

    public EndDealSimuTarot(SimulatingTarotImpl _simulatingTarot) {
        simulatingTarot = _simulatingTarot;
    }

    @Override
    public void run() {
        simulatingTarot.endGuiDeal();
    }
}
