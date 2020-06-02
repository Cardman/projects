package cards.gui.animations;

public final class PrepareSimuTarot implements Runnable {
    private SimulatingTarotImpl simulatingTarot;

    public PrepareSimuTarot(SimulatingTarotImpl _simulatingTarot) {
        simulatingTarot = _simulatingTarot;
    }

    @Override
    public void run() {
        simulatingTarot.prepareGui();
    }
}
