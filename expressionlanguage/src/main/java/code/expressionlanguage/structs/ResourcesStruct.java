package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.util.StringMap;

public final class ResourcesStruct {

	private ResourcesStruct() {
	}
	public static Struct getResourceNames(Analyzable _contextEl) {
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
	public static Struct getResource(Analyzable _contextEl,StringStruct _in) {
		String name_ = _in.getInstance();
		StringMap<String> res_ = _contextEl.getClasses().getResources();
		String content_ = res_.getVal(name_);
		if (content_ == null) {
			return NullStruct.NULL_VALUE;
		}
		return new StringStruct(content_);
	}

}
