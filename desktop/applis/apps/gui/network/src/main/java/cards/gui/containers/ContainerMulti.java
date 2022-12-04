package cards.gui.containers;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import code.network.WindowNetWork;

public interface ContainerMulti extends ContainerPlayableGame {

    void updateFirst(PlayersNamePresent _players);

    void updatePlaces(ChoosenPlace _choosePlace);
    void updateReady(Ready _readyPlayer);
    void updateAfter(PlayersNamePresent _players);
    void pauseBetweenTrick();
    void setNoClient(int _noClient);
    int getNoClient();
    boolean hasCreatedServer();
    byte getIndexInGame();
    void changeRules();
    void changePlace();
    void changeReady();
    void delegateServer();
    void dealFirst();
    void dealNext();
    WindowNetWork window();
}
