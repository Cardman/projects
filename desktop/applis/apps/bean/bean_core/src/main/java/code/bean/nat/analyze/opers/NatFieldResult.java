package code.bean.nat.analyze.opers;


import code.bean.nat.fwd.opers.NatAnaSettableOperationContent;

public final class NatFieldResult {

    private final NatAnaSettableOperationContent content = new NatAnaSettableOperationContent();
    private String type;

    public NatAnaSettableOperationContent getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

}
