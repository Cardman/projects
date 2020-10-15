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
        return ((LgNamesGui)_contextEl.getStandards()).getGuiAliases().getAliasRender();
    }


    public Struct getHeight() {
        return height;
    }

    public void setHeight(Struct _height) {
        this.height = _height;
    }

    public Struct getWidth() {
        return width;
    }

    public void setWidth(Struct _width) {
        this.width = _width;
    }

    public Struct getPaint() {
        return paint;
    }

    public void setPaint(Struct _paint) {
        this.paint = _paint;
    }
}
