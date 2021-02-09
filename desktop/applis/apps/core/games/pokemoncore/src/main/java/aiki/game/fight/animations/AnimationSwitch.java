package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.maths.LgInt;
import code.util.core.NumberUtil;

public class AnimationSwitch implements AnimationInt {

    private int index;

    private TargetCoords substituted;

    private String substituteName;

    private boolean ko;

    private short level;

    private LgInt rateRemainHp;

    private LgInt wonExpRate;

    public boolean isPlayer() {
        return NumberUtil.eq(substituted.getTeam(), Fight.CST_PLAYER);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index+getIndex();
    }

    public TargetCoords getSubstituted() {
        return substituted;
    }

    public void setSubstituted(TargetCoords _substituted) {
        substituted = _substituted;
    }

    public String getSubstituteName() {
        return substituteName;
    }

    public void setSubstituteName(String _substituteName) {
        substituteName = _substituteName;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean _ko) {
        ko = _ko;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public LgInt getRateRemainHp() {
        return rateRemainHp;
    }

    public void setRateRemainHp(LgInt _rateRemainHp) {
        rateRemainHp = _rateRemainHp;
    }

    public LgInt getWonExpRate() {
        return wonExpRate;
    }

    public void setWonExpRate(LgInt _wonExpRate) {
        wonExpRate = _wonExpRate;
    }
}
