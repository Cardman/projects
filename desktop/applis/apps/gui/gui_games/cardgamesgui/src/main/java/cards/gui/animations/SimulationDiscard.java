package cards.gui.animations;

import cards.gui.containers.ContainerSingUtil;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.labels.IntCardConverter;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationDiscard<T> implements Runnable {

    private final ContainerSingleImpl container;

    private final int total;
    private final AbsPanel centerDeck;
    private final IntCardConverter<T> converter;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationDiscard(ContainerSingleImpl _container, AbsPanel _center, IntCardConverter<T> _conv, int _total) {
        container = _container;
        total = _total;
        centerDeck = _center;
        converter = _conv;
    }

    @Override
    public void run() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        new ContainerSingUtil<T>(converter).setTalon(lg_,container.getOwner(),total,centerDeck);
    }
}
