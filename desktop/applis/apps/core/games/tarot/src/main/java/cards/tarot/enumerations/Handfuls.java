package cards.tarot.enumerations;
import code.util.EnumList;
import code.util.*;

/**Poignees utilisees au tarot*/
public enum Handfuls {
    NO,ONE(20),TWO(30),THREE(40),FOUR(50);
    private final boolean declarable;
    private final int points;
    Handfuls(){
        declarable = false;
        points = 0;
    }
    Handfuls(int _points){
        points = _points;
        declarable = true;
    }
    public int getPoints(){
        return points;
    }
    public static EnumList<Handfuls> getDeclarableHandFuls() {
        return getPoigneesValidesParDefaut();
    }
    public static EnumList<Handfuls> getNonDeclarableHandFuls() {
        EnumList<Handfuls> liste_ = new EnumList<Handfuls>();
        for(Handfuls p: Handfuls.values()) {
            if(p.declarable){
                continue;
            }
            liste_.add(p);
        }
        return liste_;
    }
    public static EnumList<Handfuls> getPoigneesValidesParDefaut() {
        EnumList<Handfuls> liste_ = new EnumList<Handfuls>();
        for(Handfuls p: Handfuls.values()) {
            if(!p.declarable){
                continue;
            }
            liste_.add(p);
        }
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
