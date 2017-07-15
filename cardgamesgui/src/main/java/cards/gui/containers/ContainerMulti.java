package cards.gui.containers;
import cards.gameresults.ResultsGame;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;

public interface ContainerMulti {

    void updateFirst(PlayersNamePresent _players);

    void updatePlaces(ChoosenPlace _choosePlace);
    void updateReady(Ready _readyPlayer);
    void updateAfter(PlayersNamePresent _players);
    void pauseBetweenTrick();
    void setNoClient(int _noClient);
    int getNoClient();
    boolean hasCreatedServer();
    byte getIndexInGame();
    void endGame(ResultsGame _res);
    void changeRules();
    void changePlace();
    void changeReady();
    void delegateServer();
    void dealFirst();
    void dealNext();
}
