package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExportCst {
    public static final String EXT = ".html";
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
    public static final String CST_ANCHOR = "a";
    public static final String CST_BOLD = "b";
    public static final String BEGIN_ANCHOR = BEGIN_TAG+ CST_ANCHOR;
    public static final String BEGIN_BOLD = BEGIN_TAG+ CST_BOLD;
    public static final String HEAD_BOLD = BEGIN_BOLD+END;
    public static final String HEAD_ITALIC = "<i>";
    public static final String FOOT_ITALIC = "</i>";
    public static final String SEP_ATTR = " ";
    public static final String TITLE_ATTR = "title=";
    public static final String HREF_ATTR = "href=";
    public static final String NAME_ATTR = "name=";
    public static final String PREF_REF = "m";
    public static final String LOC_REF = "#";
    public static final String CLASS_ATTR = "class=";
    public static final String DEL_ATTR = "\"";
    public static final String END_ANCHOR = END_PARENT+ CST_ANCHOR +END;
    public static final String END_BOLD = END_PARENT+ CST_BOLD +END;
    public static final String CST_SPAN = "span";
    public static final String END_SPAN = END_PARENT+ CST_SPAN +END;
    public static final String CLASS_ERR = CLASS_ATTR+DEL_ATTR+"e"+DEL_ATTR;
    public static final String SEP_ATTR_CLASS_ERR = SEP_ATTR+CLASS_ERR;
    public static final String CLASS_WAR = CLASS_ATTR+DEL_ATTR+"w"+DEL_ATTR;
    private ExportCst() {
    }
    public static String span(String _cl) {
        return BEGIN_TAG+ CST_SPAN +SEP_ATTR+CLASS_ATTR+DEL_ATTR+_cl+DEL_ATTR+END;
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
    public static String anchorRef(String _title, String _file, int _ref) {
        return BEGIN_ANCHOR+SEP_ATTR
                +title(_title)+SEP_ATTR
                +href(href(_file,_ref))+END;
    }
    public static String anchorRef(int _ref) {
        return anchorRef("",_ref);
    }
    public static String anchorRef(String _file, int _ref) {
        return BEGIN_ANCHOR+SEP_ATTR
                +href(href(_file,_ref))+END;
    }
    public static String anchorRef(String _ref) {
        return BEGIN_ANCHOR+SEP_ATTR
                +href(_ref)+END;
    }
    public static String name(int _name) {
        return NAME_ATTR+DEL_ATTR
                +PREF_REF+_name+DEL_ATTR;
    }
    public static String title(String _title) {
        return TITLE_ATTR+DEL_ATTR+LinkageUtil.transform(_title)+DEL_ATTR;
    }
    public static String href(String _href) {
        return HREF_ATTR+DEL_ATTR+_href+DEL_ATTR;
    }
    public static String href(FileBlock _file, int _ref) {
        return href(_file.getRenderFileName(),_ref);
    }
    public static String href(String _file, int _ref) {
        return _file+LOC_REF+PREF_REF+_ref;
    }

    public static String title(StringList _errors, String _link) {
        StringList list_ = new StringList();
        list_.addAllElts(_errors);
        if (!_link.isEmpty()) {
            list_.add(_link);
        }
        return title(StringUtil.join(list_,JOIN_ERR));
    }
}
