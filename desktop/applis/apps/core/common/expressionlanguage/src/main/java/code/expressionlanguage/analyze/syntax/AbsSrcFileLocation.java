package code.expressionlanguage.analyze.syntax;

public abstract class AbsSrcFileLocation implements SrcFileLocation {
    @Override
    public boolean match(SrcFileLocation _o){
        FileBlockCursor current_ = cursor();
        FileBlockCursor other_ = _o.cursor();
        return current_.getFile() == other_.getFile() && current_.getIndex() == other_.getIndex();
    }
}
