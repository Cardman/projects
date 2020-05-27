package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentStruct;

import java.awt.*;

public final class DimensionStruct extends WithoutParentStruct implements Struct {
    private Dimension dimension;

    public DimensionStruct(int _w,int _h) {
        dimension = new Dimension(_w,_h);
    }

    public DimensionStruct(DimensionStruct _d) {
        dimension = new Dimension(_d.dimension);
    }

    protected DimensionStruct(Dimension _d) {
        dimension = new Dimension(_d);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        LgNamesGui stds_ = (LgNamesGui) _contextEl.getStandards();
        return stds_.getAliasDimension();
    }

    public IntStruct getHeight() {
        return new IntStruct(dimension.height);
    }

    public IntStruct getWidth() {
        return new IntStruct(dimension.width);
    }

    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof DimensionStruct)) {
            return false;
        }
        DimensionStruct other_ = (DimensionStruct) _other;
        if (dimension.width != other_.dimension.width) {
            return false;
        }
        return dimension.height == other_.dimension.height;
    }
}
