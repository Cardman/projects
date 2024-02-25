package cards.gui;

import cards.gui.containers.ContainerSingleImpl;

public interface AbsPausingCardsAnims {
    int complement(ContainerSingleImpl _csi);
    int state(ContainerSingleImpl _csi);
    int alive(ContainerSingleImpl _csi);
}
