package cards.tarot.enumerations;
import code.util.IdList;
import code.util.*;

/**Poignees utilisees au tarot*/
public enum Handfuls {
    NO,ONE(20, "0"),TWO(30, "1"),THREE(40, "2"),FOUR(50, "3");
    private final long points;
    private final String st;
    Handfuls(){
        this(0, "");
    }
    Handfuls(int _points, String _s){
        points = _points;
        st = _s;
    }

    public String getSt() {
        return st;
    }

    public long getPoints(){
        return points;
    }
    public static IdList<Handfuls> getDeclarableHandFuls() {
        return getPoigneesValidesParDefaut();
    }
    public static IdList<Handfuls> getNonDeclarableHandFuls() {
        IdList<Handfuls> liste_ = new IdList<Handfuls>();
        liste_.add(NO);
        return liste_;
    }
    public static IdList<Handfuls> getPoigneesValidesParDefaut() {
        IdList<Handfuls> liste_ = new IdList<Handfuls>();
        liste_.add(ONE);
        liste_.add(TWO);
        liste_.add(THREE);
        liste_.add(FOUR);
        return liste_;
    }

    public static IntMap<Integer> getConfigurationParDefautAnnoncePoignee(Handfuls _poignee){
        IntMap<Integer> configuration_ = new IntMap<Integer>();
        if (_poignee == Handfuls.ONE) {
            configuration_.put(12,6);
            configuration_.put(14,8);
            configuration_.put(15,8);
            configuration_.put(18,10);
            configuration_.put(24,13);
        } else if (_poignee == Handfuls.TWO) {
            configuration_.put(12,8);
            configuration_.put(14,10);
            configuration_.put(15,10);
            configuration_.put(18,13);
            configuration_.put(24,15);
        } else if (_poignee == Handfuls.THREE) {
            configuration_.put(12,10);
            configuration_.put(14,13);
            configuration_.put(15,13);
            configuration_.put(18,15);
            configuration_.put(24,18);
        } else if (_poignee == Handfuls.FOUR) {
            configuration_.put(12,12);
            configuration_.put(14,14);
            configuration_.put(15,15);
            configuration_.put(18,18);
            configuration_.put(24,20);
        } else {
            configuration_.put(12,0);
            configuration_.put(14,0);
            configuration_.put(15,0);
            configuration_.put(18,0);
            configuration_.put(24,0);
        }
        return configuration_;
    }

}
