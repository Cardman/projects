package cards.gui.animations;

public final class PrepareSimuBelote implements Runnable {
    private SimulatingBeloteImpl simulatingBelote;

    public PrepareSimuBelote(SimulatingBeloteImpl _simulatingBelote) {
        simulatingBelote = _simulatingBelote;
    }

    @Override
    public void run() {
        simulatingBelote.prepareGui();
    }
}
