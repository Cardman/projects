package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private boolean variable;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private int anc;

    private ExecClassArgumentMatching previous;
    private ExecRootBlock rootBlock;
    public RendSettableFieldOperation(SettableAbstractFieldOperation _s, ExecRootBlock _rootBlock) {
        super(_s);
        variable = _s.getSettableFieldContent().isVariable();
        fieldMetaInfo = _s.getFieldMetaInfo();
        catString = _s.getSettableFieldContent().isCatString();
        anc = _s.getSettableFieldContent().getAnc();
        previous = PrimitiveTypeUtil.toExec(_s.getPreviousResultClass());
        rootBlock = _rootBlock;
    }
    public RendSettableFieldOperation(RendSettableFieldOperation _s, int _indexChild, ExecClassArgumentMatching _res, int _order, boolean _int) {
        super(_indexChild,_res,_order,_int);
        variable = _s.variable;
        rootBlock = _s.rootBlock;
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
        return processCall(this, this, previous_,_nodes, _conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument current_ = getArgument(_nodes,this);
        Struct store_ = current_.getStruct();
        return getCommonCompoundSetting(previous_, store_, _conf, _op, _right, _cl, _cast);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Struct store_ = _store.getStruct();
        return getCommonSemiSetting(previous_, store_, _conf, _op, _post, _cast);
    }

    private Argument getCommonSetting(Argument _previous, Configuration _conf, Argument _right) {
        return _conf.getAdvStandards().getCommonSetting(this,_previous,_conf,_right);
    }
    private Argument getCommonCompoundSetting(Argument _previous, Struct _store, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _cl.getNames(), _cast);
        return getCommonSetting(_previous,_conf,res_);
    }
    private Argument getCommonSemiSetting(Argument _previous, Struct _store, Configuration _conf, String _op, boolean _post, byte _cast) {
        int off_ = getOff();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;

        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
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

    public ExecClassArgumentMatching getPrevious() {
        return previous;
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        if (_right != null) {
            return getCommonSetting(_previous,_conf,_right);
        }
        return getCommonArgument(_previous,_conf);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
