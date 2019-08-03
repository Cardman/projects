package minirts;

import jm.music.data.Score;
import jm.util.Play;

public class ThreadSound implements Runnable {

    private Score score;
    public ThreadSound(Score _score) {
        score = _score;
    }

    @Override
    public void run() {
        Play.midi(score);
    }
}
