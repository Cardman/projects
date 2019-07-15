package aiki.map.characters;
import aiki.db.DataBase;
import aiki.util.Point;
import code.util.StringList;


public final class DualFight {

    private Ally ally;

    private TempTrainer foeTrainer;

    private StringList names;

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

}
