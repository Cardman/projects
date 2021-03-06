package aiki.gui.threads;
import aiki.gui.components.fight.Battle;

public final class DoneFightIntroThread implements Runnable {

    private Battle battle;

    public DoneFightIntroThread(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void run() {
        battle.setComments();
        battle.display();
    }
}
