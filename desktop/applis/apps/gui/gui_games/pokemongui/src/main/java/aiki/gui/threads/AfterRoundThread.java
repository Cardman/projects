package aiki.gui.threads;
import aiki.gui.components.fight.Battle;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterRoundThread implements Runnable {

    private Battle battle;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterRoundThread(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void run() {
        battle.afterRound();
    }
}
