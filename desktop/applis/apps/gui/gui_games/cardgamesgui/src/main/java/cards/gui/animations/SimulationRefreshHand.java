package cards.gui.animations;
import cards.gui.containers.ContainerSingUtil;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;
import code.util.IdList;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHand<T> implements Runnable {

    private final ContainerSingleImpl container;

    private final IntCardConverter<T> converter;
    private final IdList<T> hand;
    private final AbsPanel destination;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHand(ContainerSingleImpl _container,
                                 IntCardConverter<T> _conv,
                                 IdList<T> _c, AbsPanel _dest) {
        container = _container;
        destination = _dest;
        converter = _conv;
        hand = _c;
    }

    void updateCardsInPanel() {
        destination.removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        for (GraphicCard<T> c: new ContainerSingUtil<T>(converter).getGraphicCardsGene(container.getOwner().getFrames(),lg_,hand)) {
            destination.add(c.getPaintableLabel());
        }
        destination.setSize(destination.getPreferredSizeValue());
        container.pack();
    }

    @Override
    public void run() {
        updateCardsInPanel();
    }
}
