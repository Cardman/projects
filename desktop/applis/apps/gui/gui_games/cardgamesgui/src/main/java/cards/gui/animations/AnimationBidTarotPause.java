package cards.gui.animations;

import cards.gui.containers.ContainerSingleTarot;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidTarotPause implements Runnable {

    private final ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationBidTarotPause(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        AnimationBidTarot.loopBid(container);
    }

}
