package code.bean.nat;

public final class SpecNatMethod {

    private final String name;

    private final String returnType;

    private final NatCaller caller;

    public SpecNatMethod(String _name,
                         String _returnType, NatCaller _caller) {
        name = _name;
        returnType = _returnType;
        caller = _caller;
    }

    public NatCaller getCaller() {
        return caller;
    }

    public String getName() {
        return name;
    }

    public String getImportedReturnType() {
        return returnType;
    }

}
