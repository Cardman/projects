package code.expressionlanguage.exec;

public final class BreakPointInputInfo {
    private StepDbgActionEnum step;
    private boolean mute;

    public StepDbgActionEnum getStep() {
        return step;
    }

    public void setStep(StepDbgActionEnum _s) {
        this.step = _s;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean _m) {
        this.mute = _m;
    }

}
