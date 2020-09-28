package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private ExecSettableOperationContent settableFieldContent;

    private ExecRootBlock rootBlock;

    public RendSettableFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont,ExecRootBlock _rootBlock) {
        super(_opCont,_fieldCont);
        settableFieldContent = _setFieldCont;
        rootBlock = _rootBlock;
    }
    @Override
    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
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

        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, settableFieldContent.isCatString(), _cl.getNames(), _cast);
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
        if (!settableFieldContent.isStaticField()) {
            prev_ = getPreviousArg(this, _nodes, _conf);
        }
        getCommonSetting(prev_,_conf,_right);
    }

    public boolean isFinalField() {
        return settableFieldContent.isFinalField();
    }

    public boolean isStaticField() {
        return settableFieldContent.isStaticField();
    }

    public String getRealType() {
        return settableFieldContent.getRealType();
    }

    public ClassField getClassField() {
        return settableFieldContent.getClassField();
    }

    public int getAnc() {
        return settableFieldContent.getAnc();
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
