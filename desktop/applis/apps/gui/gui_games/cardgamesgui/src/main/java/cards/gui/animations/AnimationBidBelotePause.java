package cards.gui.animations;

import cards.gui.containers.ContainerSingleBelote;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidBelotePause implements Runnable {

    private final ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationBidBelotePause(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        AnimationBidBelote.loopBid(container);
    }

}
