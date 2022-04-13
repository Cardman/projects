package cards.gui.animations;

import cards.gui.containers.ContainerSingleTarot;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardTarotPause implements Runnable {

    private final ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationCardTarotPause(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        AnimationCardTarot.loopTrick(container);
    }
}
