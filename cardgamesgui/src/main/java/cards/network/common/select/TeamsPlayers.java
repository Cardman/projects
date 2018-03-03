package cards.network.common.select;
import code.util.EqList;
import code.util.Numbers;
import code.util.annot.RwXml;

@RwXml
public final class TeamsPlayers {

    private EqList<Numbers<Byte>> teams;

    public EqList<Numbers<Byte>> getTeams() {
        return teams;
    }

    public void setTeams(EqList<Numbers<Byte>> _teams) {
        teams = _teams;
    }
}
