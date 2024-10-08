package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.util.CustList;
import code.util.StringMap;

public final class ResourcesStruct {

	private ResourcesStruct() {
	}
	public static Struct getResourceNames(ContextEl _contextEl) {
        String cl_ = _contextEl.getStandards().getContent().getCharSeq().getAliasString();
		cl_ = StringExpUtil.getPrettyArrayType(cl_);
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		ArrayStruct a_ = new ArrayStruct(res_.size(),cl_);
		int i_ = 0;
		for (String s: res_.getKeys()) {
			a_.set(i_,new StringStruct(s));
			i_++;
		}
		return a_;
	}
	public static Struct getResourceNamesLength(AnalyzedPageEl _contextEl) {
		StringMap<String> res_ = _contextEl.getResources();
		return new IntStruct(res_.size());
	}
	public static Struct getResourceNamesLength(ContextEl _contextEl) {
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		return new IntStruct(res_.size());
	}
	public static Struct getResource(AnalyzedPageEl _contextEl,StringStruct _in) {
		String name_ = _in.getInstance();
		StringMap<String> res_ = _contextEl.getResources();
		String content_ = res_.getVal(name_);
		return ArgumentListCall.wrapStr(content_);
	}
	public static Struct getResource(ContextEl _contextEl,StringStruct _in) {
		String name_ = _in.getInstance();
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		String content_ = res_.getVal(name_);
		return ArgumentListCall.wrapStr(content_);
	}
	public static Struct getResourceIndex(AnalyzedPageEl _contextEl,Struct _in) {
		StringMap<String> res_ = _contextEl.getResources();
		return getResourceIndex(_in, res_);
	}
	public static Struct getResourceIndex(ContextEl _contextEl,Struct _in) {
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		return getResourceIndex(_in, res_);
	}

	private static Struct getResourceIndex(Struct _in, StringMap<String> _res) {
		int name_ = NumParsers.convertToNumber(_in).intStruct();
		CustList<String> values_ = _res.getKeys();
		if (values_.isValidIndex(name_)) {
			return  ArgumentListCall.wrapStr(values_.get(name_));
		}
		return NullStruct.NULL_VALUE;
	}

}
