package cards.gui;

import cards.gui.containers.ContainerSingle;

public interface AbsPausingCardsAnims {
    int complement(ContainerSingle _csi);
    int state(ContainerSingle _csi);
    int stateChecked(ContainerSingle _csi);
    int alive(ContainerSingle _csi);
}
