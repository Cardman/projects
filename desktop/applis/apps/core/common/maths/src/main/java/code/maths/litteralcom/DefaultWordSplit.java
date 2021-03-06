package code.maths.litteralcom;

public final class DefaultWordSplit extends AbstractWordSplit {
    @Override
    protected boolean isWordChar(char _char) {
        return MathExpUtil.isWordChar(_char);
    }
}
