package aiki.game.fight.animations;
import code.util.CustList;

public class AnimationEffectStatistic extends AnimationEffect {

    private CustList<InfosAnimationStatistic> infos = new CustList<InfosAnimationStatistic>();

    public CustList<InfosAnimationStatistic> getInfos() {
        return infos;
    }

    public void setInfos(CustList<InfosAnimationStatistic> _infos) {
        infos = _infos;
    }
}
