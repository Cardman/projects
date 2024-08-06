package code.maths.litteralcom;

public final class AdvWordSplit extends AbstractWordSplit {
    @Override
    protected boolean isWordChar(char _char) {
        return MathExpUtil.isDollarWordChar(_char);
    }
}
