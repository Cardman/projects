package aiki.game.fight.animations;
import aiki.fight.enums.Statistic;

public class InfosAnimationStatistic {

    private Statistic statistic;

    private byte variation;

    private boolean swap;

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public byte getVariation() {
        return variation;
    }

    public void setVariation(byte _variation) {
        variation = _variation;
    }

    public boolean isSwap() {
        return swap;
    }

    public void setSwap(boolean _swap) {
        swap = _swap;
    }
}
