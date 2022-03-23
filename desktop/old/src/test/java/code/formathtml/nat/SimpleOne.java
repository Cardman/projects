package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.PairStruct;
import code.bean.nat.StringMapObjectSample;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public class SimpleOne extends Bean {
    private final StringList list = new StringList();

    private final NatStringTreeMap<Integer> tree = new NatStringTreeMap< Integer>();

    private String typedString = "";
    private final CustList<SampleInput> typedStrings = new CustList<SampleInput>();
    private StringMapObjectSample forms = new StringMapObjectSample();

    public StringMapObjectSample getForms() {
        return forms;
    }

    public void setForms(StringMapObjectSample _forms) {
        forms = _forms;
    }
    @Override
    public void beforeDisplaying() {
        list.add("FIRST");
        list.add("SECOND");
        tree.put("ONE", 1);
        tree.put("TWO", 2);
        if (typedString.isEmpty()) {
            typedString = "TYPED_STRING";
        }
        if (typedStrings.isEmpty()) {
            SampleInput s_ = new SampleInput();
            s_.setTypedString("ONE");
            typedStrings.add(s_);
            s_ = new SampleInput();
            s_.setTypedString("TWO");
            typedStrings.add(s_);
        }
    }

    public ArrayStruct getList(ContextEl _cont) {
        return getList(_cont, list);
    }

    private static ArrayStruct getList(ContextEl _cont, StringList _list) {
        ArrayStruct arr_ = new ArrayStruct(_list.size(),StringExpUtil.getPrettyArrayType(_cont.getStandards().getCoreNames().getAliasObject()));
        int i_ = 0;
        for (String s:_list) {
            arr_.set(i_,new StringStruct(StringUtil.nullToEmpty(s)));
            i_++;
        }
        return arr_;
    }

    public ArrayStruct getTypedStrings(ContextEl _cont) {
        return getTypedStrings(_cont, typedStrings);
    }

    private static ArrayStruct getTypedStrings(ContextEl _cont, CustList<SampleInput> _list) {
        ArrayStruct arr_ = new ArrayStruct(_list.size(),StringExpUtil.getPrettyArrayType(_cont.getStandards().getCoreNames().getAliasObject()));
        int i_ = 0;
        for (SampleInput s: _list) {
            arr_.set(i_,new SampleInputStruct(s));
            i_++;
        }
        return arr_;

    }
    public ArrayStruct getTree(ContextEl _cont) {
        ArrayStruct arr_ = new ArrayStruct(tree.size(),StringExpUtil.getPrettyArrayType(_cont.getStandards().getCoreNames().getAliasObject()));
        int i_ = 0;
        for (EntryCust<String,Integer> e: tree.entryList()){
            PairStruct p_ = new PairStruct(_cont.getStandards().getCoreNames().getAliasObject(),new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public CustList<SampleInput> getTypedStrings() {
        return typedStrings;
    }

    public String getTypedString() {
        return typedString;
    }

    public void setTypedString(String _typedString) {
        typedString = _typedString;
    }
}
