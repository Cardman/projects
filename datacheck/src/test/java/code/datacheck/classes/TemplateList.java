package code.datacheck.classes;
import code.util.CustList;

public class TemplateList extends TemplateString<Integer> {

    private CustList<Pair<Boolean,String>> list;

    public CustList<Pair<Boolean,String>> getList() {
        return list;
    }

    public void setList(CustList<Pair<Boolean,String>> _list) {
        list = _list;
    }
}
