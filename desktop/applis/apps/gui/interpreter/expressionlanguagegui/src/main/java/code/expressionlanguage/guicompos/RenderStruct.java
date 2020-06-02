package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public class RenderStruct extends WithoutParentIdStruct implements Struct {
    private Struct height = NullStruct.NULL_VALUE;
    private Struct width = NullStruct.NULL_VALUE;
    private Struct paint = NullStruct.NULL_VALUE;

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getAliasRender();
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
