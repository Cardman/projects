package code.stream;

import code.threads.AbstractAtomicBoolean;

public interface AbsPlayBack {
    boolean prepare();
    int readBytes();
    void remainPrep();
    int remain();
    AbstractAtomicBoolean getState();
    AbstractAtomicBoolean getOk();
    void writeBytes();
    boolean drain();
    boolean finish();
}
