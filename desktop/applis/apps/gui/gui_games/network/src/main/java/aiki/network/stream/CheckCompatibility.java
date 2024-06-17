package aiki.network.stream;
import aiki.db.ExchangedData;
import aiki.map.pokemon.UsablePokemon;
import code.util.CustList;


public final class CheckCompatibility {

    private int index;

    private ExchangedData data;

    private CustList<UsablePokemon> team;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public ExchangedData getData() {
        return data;
    }

    public void setData(ExchangedData _data) {
        data = _data;
    }

    public CustList<UsablePokemon> getTeam() {
        return team;
    }

    public void setTeam(CustList<UsablePokemon> _team) {
        team = _team;
    }
}
