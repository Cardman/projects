package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringMap;

public final class ResourcesStruct {

	private ResourcesStruct() {
	}
	public static Struct getResourceNames(ContextEl _contextEl) {
		String cl_ = _contextEl.getStandards().getAliasString();
		cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		Struct[] arr_ = new Struct[res_.size()];
		int i_ = 0;
		for (String s: res_.getKeys()) {
			arr_[i_] = new StringStruct(s);
			i_++;
		}
		return new ArrayStruct(arr_, cl_);
	}
	public static Struct getResourceNamesLength(ContextEl _contextEl) {
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		return new IntStruct(res_.size());
	}
	public static Struct getResource(ContextEl _contextEl,StringStruct _in) {
		String name_ = _in.getInstance();
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		String content_ = res_.getVal(name_);
		return Argument.wrapStr(content_);
	}
	public static Struct getResourceIndex(ContextEl _contextEl,Struct _in) {
		int name_ = ClassArgumentMatching.convertToNumber(_in).intStruct();
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		CustList<String> values_ = res_.getKeys();
		if (values_.isValidIndex(name_)) {
			return  Argument.wrapStr(values_.get(name_));
		}
		return NullStruct.NULL_VALUE;
	}

}
