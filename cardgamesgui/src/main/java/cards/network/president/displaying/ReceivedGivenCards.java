package cards.network.president.displaying;
import cards.president.HandPresident;


public final class ReceivedGivenCards {

    private HandPresident received;

    private HandPresident given;

    private HandPresident newHand;

    public HandPresident getReceived() {
        return received;
    }

    public void setReceived(HandPresident _received) {
        received = _received;
    }

    public HandPresident getGiven() {
        return given;
    }

    public void setGiven(HandPresident _given) {
        given = _given;
    }

    public HandPresident getNewHand() {
        return newHand;
    }

    public void setNewHand(HandPresident _newHand) {
        newHand = _newHand;
    }
}
