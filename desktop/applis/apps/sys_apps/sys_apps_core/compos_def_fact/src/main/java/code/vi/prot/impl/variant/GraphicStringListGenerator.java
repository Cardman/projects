package code.vi.prot.impl.variant;

import code.gui.AbsGraphicList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class GraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        return new GraphicStringList(_fact,_compo,_objects);
    }

    @Override
    public AbsGraphicList<String> createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        return new GraphicStringListMult(_fact,_objects, _selectedIndexes, _visibleRows);
    }
}
