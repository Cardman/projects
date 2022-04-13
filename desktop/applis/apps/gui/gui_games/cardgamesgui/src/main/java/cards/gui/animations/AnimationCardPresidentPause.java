package cards.gui.animations;

import cards.gui.containers.ContainerSinglePresident;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardPresidentPause implements Runnable {

    private final ContainerSinglePresident container;

    /**This class thread is independant from EDT*/
    public AnimationCardPresidentPause(ContainerSinglePresident _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        AnimationCardPresident.loopTrick(container);
    }

}
