package cards.gui.animations;

public final class EndDealSimuPresident implements Runnable {
    private final SimulatingPresidentImpl simulatingPresident;
    private final int number;

    public EndDealSimuPresident(SimulatingPresidentImpl _simulatingPresident, int _no) {
        simulatingPresident = _simulatingPresident;
        number = _no;
    }

    @Override
    public void run() {
        simulatingPresident.endGuiDeal(number);
    }
}
