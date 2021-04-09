package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class InstanceBlock extends InitBlock {

    private int instanceNb;
    public InstanceBlock(int _offset) {
        super(_offset);
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
    public int getInstanceNb() {
        return instanceNb;
    }
    public void setInstanceNb(int _instanceNb){
        instanceNb = _instanceNb;
    }
}
