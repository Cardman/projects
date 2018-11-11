package cards.gui.animations;
import cards.gui.containers.ContainerPresident;
import cards.president.HandPresident;

/**Thread safe class*/
public final class SettingPresidentHand extends Thread {

    private ContainerPresident container;

    private HandPresident hand;

    public SettingPresidentHand(ContainerPresident _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisPresident().setTalonPresident(lg_,hand);
    }
}
