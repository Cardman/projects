package code.expressionlanguage.analyze.types;

public final class AnaResultPartTypeDirectDto implements AnaResultPartTypeDtoInt {
    private final AnaResultPartType element;

    public AnaResultPartTypeDirectDto() {
        this(new AnaResultPartType());
    }

    public AnaResultPartTypeDirectDto(AnaResultPartType _e) {
        this.element = _e;
    }

    @Override
    public AnaResultPartType build() {
        return element;
    }
}
