package code.gui;

import code.util.CustList;

public class GraphicStringList extends GraphicList {

    public GraphicStringList(boolean _simple, CustList<String> _objects) {
        super(_simple, _objects.toArray());
    }

    @Override
    protected void buildList() {
        setRender(new CustCellRender());
        super.buildList();
    }
}
