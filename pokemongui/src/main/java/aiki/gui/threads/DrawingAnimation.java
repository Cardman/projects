package aiki.gui.threads;
import aiki.game.fight.animations.AnimationInt;
import aiki.gui.components.fight.Battle;

public final class DrawingAnimation implements Runnable {

    private Battle battle;

    private AnimationInt animation;

    private boolean initial;

    public DrawingAnimation(Battle _battle, AnimationInt _animation,
            boolean _initial) {
        battle = _battle;
        animation = _animation;
        initial = _initial;
    }

    @Override
    public void run() {
        if (initial) {
            battle.drawAnimationInstantInitial(animation);
        } else {
            battle.drawAnimationInstant(animation);
        }
    }
}
