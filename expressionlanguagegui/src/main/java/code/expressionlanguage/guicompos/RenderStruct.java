package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public class RenderStruct implements Struct {
    private Struct height = NullStruct.NULL_VALUE;
    private Struct width = NullStruct.NULL_VALUE;
    private Struct paint = NullStruct.NULL_VALUE;

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getAliasRender();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    public Struct getHeight() {
        return height;
    }

    public void setHeight(Struct height) {
        this.height = height;
    }

    public Struct getWidth() {
        return width;
    }

    public void setWidth(Struct width) {
        this.width = width;
    }

    public Struct getPaint() {
        return paint;
    }

    public void setPaint(Struct paint) {
        this.paint = paint;
    }
}
