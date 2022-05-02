package code.vi.sys.impl;

import javazoom.jl.player.advanced.AdvancedPlayer;

public final class RunnableMp3 implements Runnable {
    private final AdvancedPlayer player;
    private final int frames;
    private boolean finished;

    public RunnableMp3(AdvancedPlayer _pl, int _frames) {
        this.player = _pl;
        this.frames = _frames;
    }

    @Override
    public void run() {
        finished = ClipStreamMp3.play(player, frames);
    }

    public boolean isFinished() {
        return finished;
    }
}
