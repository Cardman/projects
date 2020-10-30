package code.formathtml.nat;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public class SimpleOne extends Bean {
    private StringList list = new StringList();

    private NatStringTreeMap<Integer> tree = new NatStringTreeMap< Integer>();

    @Override
    public void beforeDisplaying() {
        list.add("FIRST");
        list.add("SECOND");
        tree.put("ONE", 1);
        tree.put("TWO", 2);
    }

    public ArrayStruct getList(ContextEl _cont) {
        ArrayStruct arr_ = new ArrayStruct(list.size(),StringExpUtil.getPrettyArrayType(_cont.getStandards().getCoreNames().getAliasObject()));
        int i_ = 0;
        for (String s:list) {
            arr_.set(i_,new StringStruct(StringUtil.nullToEmpty(s)));
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
}
