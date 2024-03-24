package cards.gui.animations;
import cards.gui.containers.ContainerPresident;
import cards.president.HandPresident;
import code.sml.util.TranslationsLg;

/**Thread safe class*/
public final class SettingPresidentHand implements Runnable {

    private final ContainerPresident container;

    private final HandPresident hand;

    public SettingPresidentHand(ContainerPresident _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisPresident().setTalonPresident(container.getWindow().getImageFactory(),lg_,hand);
        container.pack();
    }
}
