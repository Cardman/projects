package aiki.gui.threads;
import aiki.facade.FacadeGame;
import aiki.game.fight.animations.AnimationInt;
import aiki.gui.components.fight.Battle;
import code.gui.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public abstract class RoundThread implements Runnable {

    private FacadeGame facade;

    private Battle battle;

    public RoundThread(FacadeGame _facade, Battle _battle) {
        facade = _facade;
        battle = _battle;
    }

    protected void round() {
        appendComments();
        animate();
        while (facade.getFight().isKeepRound()) {
            facade.roundUser();
            appendComments();
            animate();
        }
    }

    private void appendComments() {
//        ThreadInvoker.invokeNow(new AppendComments(facade, battle));
        if (facade.getFight().isKeepRound()) {
            battle.appendComments();
        }
    }

    protected void animate() {
        for (AnimationInt a: facade.getFight().getEffects()) {
//            ThreadInvoker.invokeNow(new DrawingAnimation(battle, a, true));
            battle.drawAnimationInstantInitial(a);
            while (battle.isKeepAnimation()) {
                ThreadUtil.sleep(5l);
//                ThreadInvoker.invokeNow(new DrawingAnimation(battle, a, false));
                battle.drawAnimationInstant(a);
            }
        }
    }

    protected FacadeGame getFacade() {
        return facade;
    }

    protected Battle getBattle() {
        return battle;
    }

//    public boolean isAlive() {
//        return !isDone() && !isCancelled();
//    }
}
