package cards.gui.animations;

import cards.gui.containers.ContainerSingleTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.HandTarot;

public final class DisplaySimuDiscardedTarotTrumpCards implements Runnable {
    private final ContainerTarot containerTarot;
    private final HandTarot handTarot;

    public DisplaySimuDiscardedTarotTrumpCards(ContainerTarot _c, HandTarot _h) {
        this.containerTarot = _c;
        this.handTarot = _h;
    }

    @Override
    public void run() {
        ContainerSingleTarot.discardedTrumps(containerTarot,handTarot);
    }
}
