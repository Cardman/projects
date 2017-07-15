package code.music.core;

public class PitchExecption extends RuntimeException {

    public PitchExecption(int _pitch) {
        super(String.valueOf(_pitch));
    }
}
