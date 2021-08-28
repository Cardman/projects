package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentStruct;
import code.gui.images.MetaDimension;

import java.awt.Dimension;

public final class DimensionStruct extends WithoutParentStruct implements Struct {
    private MetaDimension dimension;

    public DimensionStruct(int _w,int _h) {
        dimension = new MetaDimension(_w,_h);
    }

    public DimensionStruct(DimensionStruct _d) {
        dimension = new MetaDimension(_d.dimension);
    }

    protected DimensionStruct(MetaDimension _d) {
        dimension = _d;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        LgNamesGui stds_ = (LgNamesGui) _contextEl.getStandards();
        return stds_.getGuiAliases().getAliasDimension();
    }

    public IntStruct getHeight() {
        return new IntStruct(dimension.getHeight());
    }

    public IntStruct getWidth() {
        return new IntStruct(dimension.getWidth());
    }

    public MetaDimension getDimension() {
        return dimension;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof DimensionStruct)) {
            return false;
        }
        DimensionStruct other_ = (DimensionStruct) _other;
        if (dimension.getWidth() != other_.dimension.getWidth()) {
            return false;
        }
        return dimension.getHeight() == other_.dimension.getHeight();
    }
    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(dimension.getWidth()));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(dimension.getHeight()));
        return r_;
    }
}
