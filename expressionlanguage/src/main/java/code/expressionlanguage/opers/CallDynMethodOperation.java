package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadNumberArgMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.LambdaMethodStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class CallDynMethodOperation extends InvokingOperation {

    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    boolean isCallMethodCtor() {
        return true;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
//        int off_ = StringList.getFirstPrintableCharIndex(methodName);
//        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getNames().first();
        StringList all_ = Templates.getAllTypes(fct_);
        String ret_ = all_.last();
        StringList param_ = all_.mid(1, all_.size() - 2);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: chidren_) {
            firstArgs_.add(o.getResultClass());
        }
//        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf))
        if (hasVoidArguments(chidren_, firstArgs_, 0, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (chidren_.size() != param_.size()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                classesNames_.add(c.getNames().join(""));
            }
            BadNumberArgMethod undefined_ = new BadNumberArgMethod();
            undefined_.setNbVars(chidren_.size());
            undefined_.setNbTypes(param_.size());
            undefined_.setId(fct_);
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(undefined_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        int nb_ = param_.size();
        StringMap<StringList> map_ = new StringMap<StringList>();
        String glClass_ = _conf.getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        for (int i = 0; i < nb_; i++) {
            ClassArgumentMatching a_ = firstArgs_.get(i);
            ClassArgumentMatching p_ = new ClassArgumentMatching(param_.get(i));
            Mapping m_ = new Mapping();
            m_.setArg(a_);
            m_.setParam(p_);
            m_.setMapping(map_);
            if (!Templates.isCorrect(m_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(m_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
        }
        setResultClass(new ClassArgumentMatching(ret_));
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        // TODO Auto-generated method stub
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument argres_ = prepareCallDyn(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getException() != null) {
                return;
            }
            argres_ = prepareCallDyn(previous_, arguments_, _conf);
        }
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        CustomFoundMethod method_ = _conf.getContextEl().getCallMethod();
        CustomReflectMethod ref_ = _conf.getContextEl().getReflectMethod();
        Argument res_;
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else if (method_ != null) {
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContextEl());
        } else if (ref_ != null) {
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContextEl(), ref_.getReflect());
        } else {
            res_ = argres_;
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        // TODO Auto-generated method stub
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        Argument res_ = prepareCallDyn(previous_, arguments_, _conf);
        if (_conf.callsOrException()) {
            return res_;
        }
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }

    static Argument prepareCallDyn(Argument _previous, CustList<Argument> _values, ExecutableCode _conf) {
        LambdaMethodStruct l_ =  (LambdaMethodStruct) _previous.getStruct();
        String id_ = Templates.getIdFromAllTypes(l_.getFormClassName());
        MethodId fid_ = l_.getFid();
        GeneType t_ = _conf.getClassBody(id_);
        CustList<GeneMethod> g_ = new CustList<GeneMethod>();
        for (GeneMethod g: ContextEl.getMethodBlocks(t_)) {
            if (g.getId().eq(fid_)) {
                g_.add(g);
            }
        }
        GeneMethod met_ = g_.first();
        MethodMetaInfo m_ = new MethodMetaInfo(met_.getAccess(), id_, fid_, met_.getModifier(), "", fid_, "", "");
        Argument pr_ = new Argument();
        pr_.setStruct(m_);
        String cl_ = m_.getClassName(_conf);
        MethodId nMid_ = null;
        for (GeneMethod g: ContextEl.getMethodBlocks(_conf.getClassBody(cl_))) {
            if (StringList.quickEq(g.getName(), _conf.getStandards().getAliasInvoke())) {
                nMid_ = g.getId();
            }
        }
        Argument instance_ = l_.getInstanceCall();
        String obj_ = _conf.getStandards().getAliasObject();
        obj_ = PrimitiveTypeUtil.getPrettyArrayType(obj_);
        if (!l_.isShiftInstance()) {
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()],obj_);
            int i_ = 0;
            for (Argument v: _values) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            nList_.add(instance_);
            nList_.add(new Argument(arr_));
             return InvokingOperation.callPrepare(_conf, cl_, nMid_, pr_, nList_, -1);
        }
        ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()-1],obj_);
        int i_ = 0;
        for (Argument v: _values.mid(0, _values.size() - 1)) {
            arr_.getInstance()[i_] = v.getStruct();
            i_++;
        }
        CustList<Argument> nList_ = new CustList<Argument>();
        nList_.add(_values.first());
        nList_.add(new Argument(arr_));
        return InvokingOperation.callPrepare(_conf, cl_, nMid_, pr_, nList_, -1);
    }
    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
