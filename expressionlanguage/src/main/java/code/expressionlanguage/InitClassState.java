package code.expressionlanguage;

public enum InitClassState {
NOT_YET,PROGRESSING,ERROR(true),SUCCESS(true);
    private final boolean finished;
    InitClassState() {
        this(false);
    }
    InitClassState(boolean _finished) {
        finished = _finished;
    }
    public boolean isFinished() {
        return finished;
    }
}
