package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;

public final class EmptyPartType extends LeafPartType {

    public EmptyPartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void checkExistence(Analyzable _an, RootBlock _rooted,RowCol _location) {
        UnknownClassName un_ = new UnknownClassName();
        un_.setClassName(EMPTY_STRING);
        un_.setFileName(_rooted.getFile().getFileName());
        un_.setRc(_location);
        _an.getClasses().addError(un_);
        String object_ = _an.getStandards().getAliasObject();
        setImportedTypeName(object_);
    }

    @Override
    public void checkDynExistence(Analyzable _an) {
    }

}
