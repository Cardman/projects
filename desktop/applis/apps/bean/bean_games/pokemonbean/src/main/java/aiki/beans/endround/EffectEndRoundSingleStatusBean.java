package aiki.beans.endround;

public class EffectEndRoundSingleStatusBean extends EffectEndRoundStatusBean {

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        beforeDisplayingEffectEndRoundStatus();
    }

}