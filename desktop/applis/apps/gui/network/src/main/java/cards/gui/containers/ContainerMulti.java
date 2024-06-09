package cards.gui.containers;
import cards.network.common.before.IndexOfArrivingCards;

public interface ContainerMulti extends ContainerPlayableGame {

    void updateFirst(IndexOfArrivingCards _players);

//    void updatePlaces(ChoosenPlace _choosePlace);
//    void updateReady(Ready _readyPlayer);
//    void updateAfter(PlayersNamePresent _players);
    void pauseBetweenTrick();
//    void setNoClient(int _noClient);
//    int getNoClient();
//    boolean hasCreatedServer();
//    byte getIndexInGame();
//    void changePlace();
//    void changeReady();
//    void delegateServer();
    void dealFirst();
    void dealNext();
    ContainerMultiContent getContainerMultiContent();
//    WindowNetWork window();
}
