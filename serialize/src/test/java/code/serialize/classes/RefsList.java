package code.serialize.classes;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class RefsList {

    private CustList<Object> list;

    public CustList<Object> getList() {
        return list;
    }

    public void setList(CustList<Object> _list) {
        list = _list;
    }
}
