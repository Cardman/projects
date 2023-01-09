package code.expressionlanguage.utilcompo;

import code.gui.*;
import code.mock.*;
import code.stream.core.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class FileSystemParameterizingTest extends EquallableElIntUtil {
    @Test
    public void init1() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("other=","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("other=","file=",0,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("d",res_.getFolderPrefix());
        assertEq("f",res_.getFilePrefix());
    }
    @Test
    public void init2() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("folder=custom","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=custom","file=",0,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("custom",res_.getFolderPrefix());
        assertEq("f-----",res_.getFilePrefix());
    }
    @Test
    public void init3() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=custom","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=custom","file=",0,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("d-----",res_.getFolderPrefix());
        assertEq("custom",res_.getFilePrefix());
    }
    @Test
    public void init4() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=file","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=file","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("folder=dire","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=dire","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("dire",res_.getFolderPrefix());
        assertEq("file",res_.getFilePrefix());
    }
    @Test
    public void init5() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "d-", file_, indexesFile_);
        assertEq("d_",res_.getFolderPrefix());
        assertEq("d-",res_.getFilePrefix());
    }
    @Test
    public void init6() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "d_", file_, indexesFile_);
        assertEq("d-",res_.getFolderPrefix());
        assertEq("d_",res_.getFilePrefix());
    }
    @Test
    public void init7() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing res_ = new FileSystemParameterizing("d-", folder_, indexesFolder_, "d", file_, indexesFile_);
        assertEq("d-",res_.getFolderPrefix());
        assertEq("d_",res_.getFilePrefix());
    }
    @Test
    public void init8() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing res_ = new FileSystemParameterizing("d_", folder_, indexesFolder_, "d", file_, indexesFile_);
        assertEq("d_",res_.getFolderPrefix());
        assertEq("d-",res_.getFilePrefix());
    }
    @Test
    public void init9() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=:","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=:","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("folder=dire","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=dire","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("dire",res_.getFolderPrefix());
        assertEq("f---",res_.getFilePrefix());
    }
    @Test
    public void init10() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=file","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=file","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("folder=:","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=:","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("d---",res_.getFolderPrefix());
        assertEq("file",res_.getFilePrefix());
    }
    @Test
    public void init11() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=_","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=_","file=",0,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("_", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("_",res_.getFolderPrefix());
        assertEq("-",res_.getFilePrefix());
    }
    @Test
    public void init12() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("folder=_","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=_","file=",0,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "_", file_, indexesFile_);
        assertEq("-",res_.getFolderPrefix());
        assertEq("_",res_.getFilePrefix());
    }
    @Test
    public void init13() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=_","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=_","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("folder=_","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=_","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("_",res_.getFolderPrefix());
        assertEq("-",res_.getFilePrefix());
    }
    @Test
    public void init14() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("folder=_","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=_","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("file=_","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=_","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("-",res_.getFolderPrefix());
        assertEq("_",res_.getFilePrefix());
    }
    @Test
    public void init15() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("file=-","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=-","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("folder=-","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=-","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("-",res_.getFolderPrefix());
        assertEq("_",res_.getFilePrefix());
    }
    @Test
    public void init16() {
        StringBuilder folder_ = new StringBuilder();
        Ints indexesFolder_ = new Ints();
        StringBuilder file_ = new StringBuilder();
        Ints indexesFile_ = new Ints();
        FileSystemParameterizing.addIndex("folder=-","folder=",0,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("folder=-","file=",0,file_,indexesFile_);
        FileSystemParameterizing.addIndex("file=-","folder=",1,folder_,indexesFolder_);
        FileSystemParameterizing.addIndex("file=-","file=",1,file_,indexesFile_);
        FileSystemParameterizing res_ = new FileSystemParameterizing("d", folder_, indexesFolder_, "f", file_, indexesFile_);
        assertEq("_",res_.getFolderPrefix());
        assertEq("-",res_.getFilePrefix());
    }
}
