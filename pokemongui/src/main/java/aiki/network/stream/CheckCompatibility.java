package aiki.network.stream;
import code.util.CustList;
import code.util.annot.RwXml;
import aiki.ExchangedData;
import aiki.map.pokemon.UsablePokemon;

@RwXml
public class CheckCompatibility {

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
