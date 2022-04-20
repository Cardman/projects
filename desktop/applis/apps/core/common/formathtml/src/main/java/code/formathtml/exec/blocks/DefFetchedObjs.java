package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.LongTreeMap;

public final class DefFetchedObjs extends FetchedObjs {
    private final String rad;
    private final CustList<Struct> allObj;

    private final AbstractWrapper input;
    private final CustList<LongTreeMap<DefNodeContainer>> stack;

    private final String inputName;
    public DefFetchedObjs(String _idRad, AbstractWrapper _input, CustList<Struct> _allObj, CustList<LongTreeMap<DefNodeContainer>> _stack, Argument _arg, String _inp) {
        super(_arg);
        rad = _idRad;
        input = _input;
        allObj = _allObj;
        stack = _stack;
        inputName = _inp;
    }

    public String getRad() {
        return rad;
    }

    public CustList<Struct> getAllObj() {
        return allObj;
    }

    public AbstractWrapper getInput() {
        return input;
    }

    public CustList<LongTreeMap<DefNodeContainer>> getStack() {
        return stack;
    }

    public String getInputName() {
        return inputName;
    }
}
