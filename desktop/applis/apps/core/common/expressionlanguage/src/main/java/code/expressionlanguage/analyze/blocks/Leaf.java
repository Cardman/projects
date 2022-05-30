package code.expressionlanguage.analyze.blocks;

public abstract class Leaf extends AbsBk {

    Leaf(int _offset) {
        super(_offset);
    }

    @Override
    public final AbsBk getFirstChild() {
        return null;
    }

}
