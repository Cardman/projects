package code.expressionlanguage.fwd.blocks;

public final class ExecTypeFunctionPair {
    private final ExecTypeFunctionInst instRead;
    private final ExecTypeFunctionInst instWrite;

    public ExecTypeFunctionPair(ExecTypeFunctionInst _instRead, ExecTypeFunctionInst _instWrite) {
        instRead = _instRead;
        instWrite = _instWrite;
    }

    public ExecTypeFunctionInst getInstRead() {
        return instRead;
    }

    public ExecTypeFunctionInst getInstWrite() {
        return instWrite;
    }
}
