package code.expressionlanguage.analyze.syntax;

public abstract class AbsSrcFileLocation implements SrcFileLocation {
    @Override
    public boolean match(SrcFileLocation _o){
        return getFile() == _o.getFile() && getIndex() == _o.getIndex();
    }
}
