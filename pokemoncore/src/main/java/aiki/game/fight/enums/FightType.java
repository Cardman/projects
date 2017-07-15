package aiki.game.fight.enums;
import code.datacheck.CheckedData;


@CheckedData
public enum FightType {
    NOTHING(true,false),SAUVAGE(true,true),DRESSEUR,DRESSEUR_GYM,GYM_LEADER,DRESSEUR_LIGUE,TMP_TRAINER;
    private final boolean wild;
    private final boolean existing;
    FightType(){
        wild=false;
        existing=true;
    }
    FightType(boolean _wild,boolean _existing){
        wild=_wild;
        existing=_existing;
    }
    public boolean isWild() {
        return wild;
    }
    public boolean isExisting() {
        return existing;
    }
}
