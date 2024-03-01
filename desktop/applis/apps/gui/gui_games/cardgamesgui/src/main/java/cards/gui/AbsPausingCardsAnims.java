package cards.gui;

import cards.gui.containers.ContainerSin;

public interface AbsPausingCardsAnims {
    int complement(ContainerSin _csi);
    int state(ContainerSin _csi);
    int stateChecked(ContainerSin _csi);
    int alive(ContainerSin _csi);
}
