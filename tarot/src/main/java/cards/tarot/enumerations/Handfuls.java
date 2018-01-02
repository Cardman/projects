package cards.tarot.enumerations;
import code.format.Format;
import code.util.EnumList;
import code.util.NumberMap;
import code.util.consts.Constants;
import code.util.ints.Displayable;
import cards.tarot.HandTarot;

/**Poignees utilisees au tarot*/
public enum Handfuls implements Displayable {
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
        EnumList<Handfuls> liste_ = new EnumList<Handfuls>();
        for(Handfuls p: Handfuls.values()) {
            if(!p.declarable){
                continue;
            }
            liste_.add(p);
        }
        return liste_;
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
            if(!configurationParDefautValide(p)){
                continue;
            }
            liste_.add(p);
        }
        return liste_;
    }
    private static boolean configurationParDefautValide(Handfuls _poignee) {
        NumberMap<Integer,Integer> configuration_ =
                    getConfigurationParDefautAnnoncePoignee(_poignee);
        int nbAtoutsExcuse_ = HandTarot.atoutsSansExcuse().total()+1;
        for(DealingTarot r: DealingTarot.values()) {
            int nbCartesParJoueur_ = r.getNombreCartesParJoueur();
            if(!configuration_.contains(nbCartesParJoueur_)) {
                return false;
            }
            int nbAtoutsNecessaires_ = configuration_.getVal(nbCartesParJoueur_);
            if(nbAtoutsNecessaires_ <= 0){
                return false;
            }
            if(nbCartesParJoueur_ < nbAtoutsNecessaires_) {
                return false;
            }
            if(nbAtoutsExcuse_ < nbAtoutsNecessaires_){
                return false;
            }
        }
        for(int c: configuration_.getKeys()) {
            boolean cleValide_ = false;
            for(DealingTarot r: DealingTarot.values()) {
                int nbCartesParJoueur_ = r.getNombreCartesParJoueur();
                if(nbCartesParJoueur_ == c) {
                    cleValide_ = true;
                    break;
                }
            }
            if(!cleValide_) {
                return false;
            }
        }
        return true;
    }
    public static NumberMap<Integer,Integer> getConfigurationParDefautAnnoncePoignee(Handfuls _poignee){
        NumberMap<Integer,Integer> configuration_ = new NumberMap<Integer,Integer>();
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

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResoucesAccess.NOM_DOSSIER,ResoucesAccess.NOM_FICHIER, _locale, ResoucesAccess.TAROT_HANDFULS,name());
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
}
