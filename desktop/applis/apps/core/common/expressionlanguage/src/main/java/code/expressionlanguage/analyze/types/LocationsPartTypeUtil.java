package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.syntax.*;
import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;

public final class LocationsPartTypeUtil {
    private LocationsPartTypeUtil() {
    }
    public static CustList<SrcFileLocation> processAnalyzeConstraintsRepParts(AnaResultPartTypeDtoInt _className, int _caret){
        return processAnalyzeConstraintsRepParts(_className.build(), _caret);
    }
    public static CustList<AbsSrcFileLocationType> processAnalyzeConstraintsRepParts(AnaResultPartTypeDtoInt _className, AbsTypeSegmentFilter _caret){
        return processAnalyzeConstraintsRepParts(_className.build(), _caret);
    }

    public static CustList<SrcFileLocation> processAnalyzeConstraintsRepParts(AnaResultPartType _b, int _caret) {
        CustList<SrcFileLocation> l_ = new CustList<SrcFileLocation>();
        for (AbsSrcFileLocationType s: processAnalyzeConstraintsRepParts(_b,new DefTypeSegmentFilter(_caret))) {
            l_.add(s);
        }
        return l_;
    }

    public static CustList<AbsSrcFileLocationType> processAnalyzeConstraintsRepParts(AnaResultPartType _b, AbsTypeSegmentFilter _caret) {
        CustList<AbsSrcFileLocationType> out_ = new CustList<AbsSrcFileLocationType>();
        lookForTypes(_b, _caret, out_);
        return out_;
    }

    private static void lookForTypes(AnaResultPartType _className, AbsTypeSegmentFilter _caret, CustList<AbsSrcFileLocationType> _dest){
        AnaPartType root_ = _className.getPartType();
        FileBlock file_ = _className.getRooted();
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                processLeafOffsets(current_, file_,_caret, _dest);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    processLeafOffsets(root_, file_,_caret, _dest);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void processLeafOffsets(AnaPartType _current, FileBlock _fileName, AbsTypeSegmentFilter _caret, CustList<AbsSrcFileLocationType> _dest) {
        if (!_caret.inRange(_current.getFull(),_current.getFull()+_current.getLength())) {
            return;
        }
        if (_current instanceof AnaNamePartType) {
            AnaGeneType f_ = _current.getFoundType();
            if (f_ instanceof RootBlock) {
                _dest.add(new SrcFileLocationType(new SimpleSegType(_current.getFull(),_current.getFull()+_current.getLength()),(RootBlock) f_));
            } else if (!_current.getAnalyzedType().isEmpty()){
                _dest.add(new SrcFileLocationStdType(new SimpleSegType(_current.getFull(),_current.getFull()+_current.getLength()),_current.getAnalyzedType()));
            }
        }
        if (_current instanceof AnaVariablePartType) {
            String v_ = ((AnaVariablePartType) _current).getTypeName().trim();
            int o_ = ((AnaVariablePartType) _current).getValue();
            FileBlock r_ = ((AnaVariablePartType) _current).getRefFileName();
            if (r_ != null) {
                _dest.add(new SrcFileLocationTypeVar(new SimpleSegType(_current.getFull(),_current.getFull()+_current.getLength()),v_,o_,r_));
            } else {
                _dest.add(new SrcFileLocationTypeVar(new SimpleSegType(_current.getFull(),_current.getFull()+_current.getLength()),v_,o_,_fileName));
            }
        }
    }
}
