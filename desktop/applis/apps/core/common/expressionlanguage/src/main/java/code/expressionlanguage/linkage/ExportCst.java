package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.MessagesCdmBase;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExportCst {
    public static final String JOIN_ERR = "\n\n";
    public static final String JOIN_TYPES = "&";
    public static final String JOIN_BLOCK = "|";
    public static final String JOIN_OPERANDS = ";";
    public static final String SEP_TYPE_MEMBER = ".";
    public static final String SEP_CHAR = ",";
    public static final String FOUND_COVERAGE = ",";
    public static final String RATIO_COVERAGE = "/";
    public static final String IMPLICIT = " ";
    public static final String BEGIN_TAG = "<";
    public static final String END = ">";
    public static final String END_PARENT = BEGIN_TAG+"/";
    public static final String BEGIN_ANCHOR = BEGIN_TAG+ MessagesCdmBase.CST_ANCHOR;
    public static final String BEGIN_BOLD = BEGIN_TAG+ MessagesCdmBase.CST_BOLD;
    public static final String HEAD_BOLD = BEGIN_BOLD+END;
    public static final String HEAD_ITALIC = BEGIN_TAG+MessagesCdmBase.CST_ITALIC+END;
    public static final String FOOT_ITALIC = END_PARENT+MessagesCdmBase.CST_ITALIC+END;
    public static final String SEP_ATTR = " ";
    public static final String SEP_KEY_VALUE = "=";
    public static final String TITLE_ATTR = MessagesCdmBase.TITLE +SEP_KEY_VALUE;
    public static final String HREF_ATTR = MessagesCdmBase.HREF +SEP_KEY_VALUE;
    public static final String NAME_ATTR = MessagesCdmBase.NAME +SEP_KEY_VALUE;
    public static final String PREF_REF = "_";
    public static final String LOC_REF = "#";
    public static final String CLASS_ATTR = MessagesCdmBase.CLASS +SEP_KEY_VALUE;
    public static final String DEL_ATTR = "\"";
    public static final String END_ANCHOR = END_PARENT+ MessagesCdmBase.CST_ANCHOR +END;
    public static final String END_BOLD = END_PARENT+ MessagesCdmBase.CST_BOLD +END;
    public static final String PREF_CLASS = ".";
    public static final String STRING = MessagesCdmBase.CLASS_STRING;
    public static final String COMMENT = MessagesCdmBase.CLASS_COMMENT;
    public static final String IMPORT = MessagesCdmBase.CLASS_IMPORT;
    public static final String TYPE = MessagesCdmBase.CLASS_TYPE;
    public static final String END_SPAN = END_PARENT+ MessagesCdmBase.CST_SPAN +END;
    public static final String CLASS_ERR = CLASS_ATTR+DEL_ATTR+ MessagesCdmBase.CLASS_ERROR+DEL_ATTR;
    public static final String SEP_ATTR_CLASS_ERR = SEP_ATTR+CLASS_ERR;
    public static final String CLASS_WAR = CLASS_ATTR+DEL_ATTR+ MessagesCdmBase.CLASS_WARNING+DEL_ATTR;
    public static final String BEG_SET = "{";
    public static final String END_SET = "}";
    public static final String MID_ATTR = ":";
    public static final String END_ATTR = ";";
    public static final String BEGIN_HEAD = BEGIN_TAG + MessagesCdmBase.HEAD + END;
    public static final String END_HEAD = END_PARENT + MessagesCdmBase.HEAD + END;
    public static final String BEGIN_BODY = BEGIN_TAG + MessagesCdmBase.BODY + END;
    public static final String BEGIN_BODY_PRE = BEGIN_BODY+ BEGIN_TAG + MessagesCdmBase.PRE + END;
    public static final String END_BODY = END_PARENT + MessagesCdmBase.BODY + END;
    public static final String END_BODY_PRE = END_PARENT + MessagesCdmBase.PRE + END +END_BODY;
    public static final String TR = BEGIN_TAG + MessagesCdmBase.TAG_TR + END;
    public static final String TD = BEGIN_TAG + MessagesCdmBase.TAG_TD + END;
    public static final String ETD = END_PARENT + MessagesCdmBase.TAG_TD + END;
    public static final String ETR = END_PARENT + MessagesCdmBase.TAG_TR + END;
    public static final String BEGIN_DOC = BEGIN_TAG + MessagesCdmBase.HTML + END;
    public static final String END_DOC = END_PARENT + MessagesCdmBase.HTML + END;
    public static final String EMPTY = "";
    public static final char QUOT_CHAR = '\"';
    public static final char LEFT_TAG = '<';
    public static final char RIGHT_TAG = '>';
    public static final char SPEC_CHAR = '&';
    public static final char SPACE_CH = ' ';
    public static final char SEP_DIR = '/';
    public static final String PARENT = "..";
    public static final char LINE_RET_CHAR = '\n';
    public static final char TAB_CHAR = '\t';
    public static final String LINE_RET = "\n";
    public static final String TAB = "\t";
    public static final String DEF_SPACE = " ";
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";

    private ExportCst() {
    }
    public static String span(String _cl) {
        return BEGIN_TAG+ MessagesCdmBase.CST_SPAN +SEP_ATTR+CLASS_ATTR+DEL_ATTR+_cl+DEL_ATTR+END;
    }
    public static String anchorNameErr(int _offset,String _title) {
        return BEGIN_ANCHOR+SEP_ATTR+name(_offset)+SEP_ATTR
                +title(_title)
                +SEP_ATTR_CLASS_ERR+END;
    }
    public static String anchorNameWar(int _offset,String _title) {
        return BEGIN_ANCHOR+SEP_ATTR+title(_title)+SEP_ATTR
                +name(_offset)+SEP_ATTR
                +CLASS_WAR+END;
    }
    public static String anchorName(int _offset,String _title) {
        return BEGIN_ANCHOR+SEP_ATTR+title(_title)+SEP_ATTR+name(_offset)+END;
    }
    public static String anchorName(int _offset) {
        return BEGIN_ANCHOR+SEP_ATTR+name(_offset)+END;
    }
    public static String anchorErr(String _title) {
        return BEGIN_ANCHOR+SEP_ATTR
                +title(_title)
                +SEP_ATTR_CLASS_ERR+END;
    }
    public static String anchorWar(String _title) {
        return BEGIN_ANCHOR+SEP_ATTR
                +title(_title)+SEP_ATTR
                +CLASS_WAR+END;
    }
    public static String anchor(String _title) {
        return BEGIN_ANCHOR+SEP_ATTR
                +title(_title)
                +END;
    }
    public static String bold(String _title) {
        return BEGIN_BOLD+SEP_ATTR
                +title(_title)
                +END;
    }

    public static String anchorRef(int _ref) {
        return BEGIN_ANCHOR+SEP_ATTR
                + href(_ref)+END;
    }
    public static String anchorRef(FileBlock _currentFileName, FileBlock _file, int _offset) {
        return BEGIN_ANCHOR+SEP_ATTR
                +href(_currentFileName, _file, _offset)+END;
    }
    public static String name(int _name) {
        return NAME_ATTR+DEL_ATTR
                +PREF_REF+_name+DEL_ATTR;
    }
    public static String title(String _title) {
        return TITLE_ATTR+DEL_ATTR+LinkageUtil.transform(_title)+DEL_ATTR;
    }
    public static String href(FileBlock _currentFileName, FileBlock _file, int _offset) {
        return href(relativize(_currentFileName, _file, _offset));
    }
    public static String href(String _href) {
        return HREF_ATTR+DEL_ATTR+_href+DEL_ATTR;
    }
    public static String href(int _ref) {
        return HREF_ATTR+DEL_ATTR+link(_ref)+DEL_ATTR;
    }
    public static String link(FileBlock _file, int _ref) {
        return link(_file.getRenderFileName(),_ref);
    }
    public static String link(String _file, int _ref) {
        return _file+link(_ref);
    }
    public static String link(int _ref) {
        return LOC_REF+PREF_REF+_ref;
    }

    public static String title(StringList _errors, String _link) {
        StringList list_ = new StringList();
        list_.addAllElts(_errors);
        if (!_link.isEmpty()) {
            list_.add(_link);
        }
        return title(StringUtil.join(list_,JOIN_ERR));
    }

    public static String relativize(FileBlock _currentFileName, FileBlock _file, int _offset) {
        return RelativePathUtil.relativize(_currentFileName, link(_file,_offset));
    }
}
