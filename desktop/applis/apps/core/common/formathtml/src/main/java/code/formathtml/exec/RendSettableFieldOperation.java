package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private boolean variable;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private int anc;

    private ClassArgumentMatching previous;
    public RendSettableFieldOperation(SettableAbstractFieldOperation _s) {
        super(_s);
        variable = _s.isVariable();
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.isCatString();
        anc = _s.getAnc();
        previous = _s.getPreviousResultClass();
    }
    public RendSettableFieldOperation(RendSettableFieldOperation _s,int _indexChild, ClassArgumentMatching _res, int _order, boolean _int) {
        super(_s.getPreviousArgument(),_indexChild,_res,_order,_int);
        variable = _s.variable;
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.catString;
        anc = _s.anc;
        previous = _s.previous;
    }
    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    Argument getCommonArgument(Argument _previous, Configuration _conf) {
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return _conf.getAdvStandards().getCommonArgument(this,_previous,_conf);
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        return processCall(this, this, previous_, new CustList<Argument>(), _conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Struct store_ = _store.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post);
    }

    private Argument getCommonSetting(Argument _previous, Configuration _conf, Argument _right) {
        return _conf.getAdvStandards().getCommonSetting(this,_previous,_conf,_right);
    }
    private Argument getCommonCompoundSetting(Argument _previous, Struct _store, Configuration _conf, String _op, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        ClassArgumentMatching cl_ = getResultClass();
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        return getCommonSetting(_previous,_conf,res_);
    }
    private Argument getCommonSemiSetting(Argument _previous, Struct _store, Configuration _conf, String _op, boolean _post) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        ClassArgumentMatching cl_ = getResultClass();
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf.getContext(), _op, cl_);
        getCommonSetting(_previous,_conf,res_);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        processField(_nodes, _conf, _right);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        processField(_nodes, _conf, _right);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument prev_ = Argument.createVoid();
        if (!fieldMetaInfo.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
    }

    public FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    public int getAnc() {
        return anc;
    }

    public ClassArgumentMatching getPrevious() {
        return previous;
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        if (_right != null) {
            return getCommonSetting(_previous,_conf,_right);
        }
        return getCommonArgument(_previous,_conf);
    }
}
