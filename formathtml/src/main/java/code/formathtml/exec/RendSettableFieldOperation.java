package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.exec.ExecNumericOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
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
        Argument arg_ = getCommonSetting(previous_, _conf, _right);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return arg_;
            }
            arg_ = getCommonSetting(previous_, _conf, _right);
        }
        return arg_;
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
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
        return endCalculate(_nodes,_conf, false, null, _right);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument prev_ = Argument.createVoid();
        if (!fieldMetaInfo.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
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
}
