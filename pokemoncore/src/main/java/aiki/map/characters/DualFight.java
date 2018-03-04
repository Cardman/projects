package aiki.map.characters;
import aiki.DataBase;
import aiki.util.Point;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public final class DualFight implements Fightable {

    private Ally ally;

    private TempTrainer foeTrainer;

    private StringList names;

    @CheckedData
    private Point pt;

    public void validate(DataBase _data) {
        ally.validate(_data);
        foeTrainer.validate(_data);
        names.removeDuplicates();
    }

    public Ally getAlly() {
        return ally;
    }

    public void setAlly(Ally _ally) {
        ally = _ally;
    }

    public TempTrainer getFoeTrainer() {
        return foeTrainer;
    }

    public void setFoeTrainer(TempTrainer _foeTrainer) {
        foeTrainer = _foeTrainer;
    }

    public Point getPt() {
        return pt;
    }

    public void setPt(Point _pt) {
        pt = _pt;
    }

    public StringList getNames() {
        return names;
    }

    public void setNames(StringList _names) {
        names = _names;
    }

    @Override
    public int getMult() {
        return foeTrainer.getMultiplicityFight();
    }
}
