package code.gui;

/**This thread is safe
This class thread is independant from EDT,
Thread safe class*/
public final class PaintingLabel extends Thread {

    private AnimatedLabel anim;

    /**This class thread is independant from EDT*/
    public PaintingLabel(AnimatedLabel _anim) {
        anim = _anim;
    }

    @Override
    public void run() {
        anim.increment();
    }
}
