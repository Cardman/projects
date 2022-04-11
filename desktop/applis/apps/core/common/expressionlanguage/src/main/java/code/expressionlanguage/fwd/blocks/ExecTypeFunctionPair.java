package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.fwd.opers.ExecInstFctContent;

public final class ExecTypeFunctionPair {
    private final ExecInstFctContent instRead;
    private final ExecTypeFunction read;
    private final ExecInstFctContent instWrite;
    private final ExecTypeFunction write;

    public ExecTypeFunctionPair(ExecTypeFunction _read, ExecInstFctContent _iRead, ExecTypeFunction _write, ExecInstFctContent _iWrite) {
        this.read = _read;
        instRead = _iRead;
        this.write = _write;
        instWrite = _iWrite;
    }

    public ExecTypeFunction getRead() {
        return read;
    }

    public ExecInstFctContent getInstRead() {
        return instRead;
    }

    public ExecTypeFunction getWrite() {
        return write;
    }

    public ExecInstFctContent getInstWrite() {
        return instWrite;
    }
}
