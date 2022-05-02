package code.player.gui;

public abstract class AbsStatusChange {
    private final WindowRecorder windowRecorder;

    protected AbsStatusChange(WindowRecorder _window) {
        this.windowRecorder = _window;
    }
    public void setState(){
        windowRecorder.setState();
    }
}
