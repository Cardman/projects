package aiki.gui.threads;
import aiki.facade.FacadeGame;
import aiki.game.fight.animations.AnimationInt;
import aiki.gui.components.fight.Battle;
import code.threads.ThreadUtil;
import code.util.CustList;

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
        while (facade.getFight().getTemp().isKeepRound()) {
            facade.roundUser();
            appendComments();
            animate();
        }
    }

    private void appendComments() {
//        ThreadInvoker.invokeNow(new AppendComments(facade, battle));
        if (facade.getFight().getTemp().isKeepRound()) {
            battle.appendComments();
        }
    }

    protected void animate() {
        CustList<AnimationInt> effects_ = facade.getFight().getEffects();
        battle.setCountAnim(effects_.size());
        for (AnimationInt a: effects_) {
//            ThreadInvoker.invokeNow(new DrawingAnimation(battle, a, true));
            battle.drawAnimationInstantInitial(a);
            while (battle.isKeepAnimation()) {
                ThreadUtil.sleep(getBattle().getWindow().getThreadFactory(),5l);
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
