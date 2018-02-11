package code.formathtml;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import aiki.beans.validators.PositiveRateValidator;
import aiki.beans.validators.RateValidator;
import aiki.beans.validators.ShortValidator;
import aiki.beans.validators.UnselectedRadio;
import code.bean.Accessible;
import code.bean.Bean;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustElUtil;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.util.BeanLgNames;
import code.maths.LgInt;
import code.maths.Rate;
import code.serialize.ConstClasses;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.stream.StreamTextFile;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Displayable;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.pagination.SelectedBoolean;

@SuppressWarnings("static-method")
public class CheckGene {

    @Test
    public void confPkTest() {
        String resPk = "resources_pk/rom/";
        String web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_fight/";
        String webtwo = "C:/Users/cardman/git/pokemonbean/";
        String conf = "faces.xml";
        test(conf, web, webtwo, resPk, null, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web/";
        test(conf, web, webtwo, resPk, null, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_game/";
        test(conf, web, webtwo, resPk, null, null);
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_prog/";
        test(conf, web, webtwo, resPk, null, null);
        conf = "faces_pokemon.xml";
        web = "C:/Users/cardman/git/pokemonbean/resources_pk/rom/web_pk/";
        test(conf, web, webtwo, resPk, null, null);
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() == 1) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
                continue;
            }
            if (e.getValue().first()) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
                continue;
            }
            if (!e.getValue().first()) {
                continue;
            }
            System.out.println(e.getKey());
        }
        System.out.println();
        CustElUtil.CALLS.removeDuplicates();
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (e.getClassName().endsWith(">")) {
                continue;
            }
//            String clName_ = e.getClassName();
//            String mName_ = e.getConstraints().getName();
//            try {
////                Class<?> cl_ = ConstClasses.classForNameNotInit(clName_);
////                EqList<ClassMethodId> homonymsMethods_ = new EqList<ClassMethodId>();
////                for (ClassMethodId f: CustElUtil.CALLS) {
////                    if (f.getClassName().endsWith(">")) {
////                        continue;
////                    }
////                    Class<?> cltwo_ = ConstClasses.classForNameNotInit(f.getClassName());
////                    if (!cltwo_.isAssignableFrom(cl_)) {
////                        continue;
////                    }
////                    if (cltwo_.isInterface()) {
////                        continue;
////                    }
////                    if (StringList.quickEq(f.getConstraints().getName(), mName_)) {
////                        homonymsMethods_.add(f);
////                    }
////                }
////                if (homonymsMethods_.size() > 1) {
////                    System.out.println(clName_+"."+e.getConstraints().getSignature());
////                    for (ClassMethodId c: homonymsMethods_) {
////                        System.out.println("\t"+c.getClassName()+"."+c.getConstraints().getSignature());
////                    }
////                }
//                Class<?> cl_ = ConstClasses.classForNameNotInit(clName_);
//                CustList<java.lang.reflect.Method> methods_ = new CustList<java.lang.reflect.Method>();
//                while (cl_ != null) {
//                    for (java.lang.reflect.Method m: cl_.getDeclaredMethods()) {
//                        if (Modifier.isAbstract(m.getModifiers())) {
//                            continue;
//                        }
//                        if (m.getAnnotation(Accessible.class) == null && !Modifier.isPublic(m.getModifiers())) {
//                            continue;
//                        }
//                        if (StringList.quickEq(m.getName(), mName_)) {
//                            methods_.add(m);
//                        }
//                    }
//                    cl_ = cl_.getSuperclass();
//                }
//                if (methods_.size() > 1) {
//                    System.out.println(clName_+"."+e.getConstraints().getSignature());
//                    for (java.lang.reflect.Method m: methods_) {
//                        System.out.println("\t"+m.toGenericString());
//                    }
//                }
//            } catch (Exception _0_) {
//            }
            System.out.println(e.getClassName()+"."+e.getConstraints().getSignature());
        }
        System.out.println();
        FormatHtmlLookFor.FIELDS_NAMES.removeDuplicates();
        for (String e: FormatHtmlLookFor.FIELDS_NAMES) {
            System.out.println(e);
        }
    }
    @Ignore
    @Test
    public void readSrcFiles() {
        String absolute = System.getProperty("absolute");
        String out = System.getProperty("outdir");
        if (out == null) {
            Assert.fail("no input use -Doutdir='mydir'");
        }
        if (absolute == null) {
            Assert.fail("no input use -Dabsolute='mydir'");
        }
        String resPk = "resources_pk/rom/";
        String web = absolute+"pokemonbean/resources_pk/rom/web_fight/";
        String webtwo = absolute+"pokemonbean/";
        String conf = "faces.xml";
        test(conf, web, webtwo, resPk, null, null);
        web = absolute+"pokemonbean/resources_pk/rom/web/";
        test(conf, web, webtwo, resPk, null, null);
        web = absolute+"pokemonbean/resources_pk/rom/web_game/";
        test(conf, web, webtwo, resPk, null, null);
        web = absolute+"pokemonbean/resources_pk/rom/web_prog/";
        test(conf, web, webtwo, resPk, null, null);
        conf = "faces_pokemon.xml";
        web = absolute+"pokemonbean/resources_pk/rom/web_pk/";
        test(conf, web, webtwo, resPk, null, null);
        System.out.println();
        String folder = absolute+"pokemonbean/src/main/java";
        StringList packages_ = new StringList();
        StringList allTypes_ = new StringList();
        LgNames lgNames_ = new LgNames();
        DefaultInitialization.basicStandards(lgNames_);
        ContextEl context_ = new ContextEl();
        lgNames_.setContext(context_);
        context_.setStandards(lgNames_);
        StringMap<StringList> types_ = new StringMap<StringList>();
        for (String f: StreamTextFile.allSortedFiles(folder)) {
            if (!f.endsWith(".java")) {
                continue;
            }
            String content_ = StreamTextFile.contentsOfFile(f);
            String relative_ = f.substring(folder.length() + 1);
            String exp_ = "/"+relative_;
            relative_ = relative_.substring(0, relative_.length() - ".java".length());
            relative_ = relative_.replace('/', '.');
            packages_.add(relative_.substring(0, relative_.lastIndexOf('.')));
            if (types_.contains(relative_.substring(0, relative_.lastIndexOf('.')))) {
                types_.getVal(relative_.substring(0, relative_.lastIndexOf('.'))).add(relative_);
            } else {
                types_.put(relative_.substring(0, relative_.lastIndexOf('.')), new StringList(relative_));
            }
            allTypes_.add(relative_);
            new File(out+exp_).getParentFile().mkdirs();
            //convert file and add getters and setters
            String output_ = Converter.convertFile(content_, relative_, CustElUtil.GETTERS_SETTERS_FIELDS);
            StreamTextFile.saveTextFile(out+exp_, output_);
        }
        lgNames_.build();
        StringList remainClasses_ = new StringList();
        for (ClassMethodId c: CustElUtil.CALLS) {
            if (allTypes_.containsStr(c.getClassName())) {
                continue;
            }
            if (c.getClassName().contains("<")) {
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(c.getClassName())) {
                continue;
            }
            Class<?> cl_ = ConstClasses.classForNameNotInit(c.getClassName());
            if (cl_.getTypeParameters().length > 0) {
                continue;
            }
            if (cl_ == SelectedBoolean.class) {
                continue;
            }
            if (cl_ == StringList.class) {
                continue;
            }
            if (cl_ == Enum.class) {
                continue;
            }
            if (lgNames_.getStandards().contains(c.getClassName())) {
                continue;
            }
            remainClasses_.add(c.getClassName());
            while (cl_ != Object.class) {
                remainClasses_.add(cl_.getName());
                cl_ = cl_.getSuperclass();
            }
        }
        for (String s: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
            String clPart_ = s.substring(0, s.lastIndexOf('.'));
            if (allTypes_.containsStr(clPart_)) {
                continue;
            }
            if (StringList.quickEq(clPart_, SelectedBoolean.class.getName())) {
                continue;
            }
            if (StringList.quickEq(clPart_, StringList.class.getName())) {
                continue;
            }
            if (StringList.quickEq(clPart_, Enum.class.getName())) {
                continue;
            }
            Class<?> cl_ = ConstClasses.classForNameNotInit(clPart_);
            remainClasses_.add(clPart_);
            while (cl_ != Object.class) {
                remainClasses_.add(cl_.getName());
                cl_ = cl_.getSuperclass();
            }
        }
        for (String c: CustElUtil.RES_CLASSES) {
            if (allTypes_.containsStr(c)) {
                continue;
            }
            if (c.contains("<")) {
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(c)) {
                continue;
            }
            if (StringList.quickEq(c, "$void")) {
                continue;
            }
            Class<?> cl_ = ConstClasses.classForNameNotInit(c);
            if (cl_.getTypeParameters().length > 0) {
                continue;
            }
            if (lgNames_.getStandards().contains(c)) {
                continue;
            }
            if (cl_ == SelectedBoolean.class) {
                continue;
            }
            if (cl_ == StringList.class) {
                continue;
            }
            if (cl_ == Enum.class) {
                continue;
            }
            remainClasses_.add(c);
            while (cl_ != Object.class) {
                remainClasses_.add(cl_.getName());
                cl_ = cl_.getSuperclass();
            }
        }
        remainClasses_.removeDuplicates();
        packages_.removeDuplicates();
        StringList stdsCl_ = new StringList();
        for (String p: packages_) {
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleName_ = newPart_.toString();
            stdsCl_.add(p + "."+simpleName_);
        }
        for (String p: packages_) {
            StringList classes_ = types_.getVal(p);
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleName_ = newPart_.toString();
            String fullName_ = p + "."+simpleName_;
            fullName_ = fullName_.replace('.', '/');
            fullName_ += ".java";
            System.out.println(out+"/"+fullName_);
            StringBuilder body_ = new StringBuilder();
            body_.append("package ");
            body_.append(p);
            body_.append(";\n");
            body_.append("import code.expressionlanguage.ContextEl;\n");
            body_.append("import code.expressionlanguage.opers.util.BooleanStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.ClassField;\n");
            body_.append("import code.expressionlanguage.opers.util.ClassMethodId;\n");
            body_.append("import code.expressionlanguage.opers.util.ConstructorId;\n");
            body_.append("import code.expressionlanguage.opers.util.IntStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.MethodId;\n");
            body_.append("import code.expressionlanguage.opers.util.MethodModifier;\n");
            body_.append("import code.expressionlanguage.opers.util.StdStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.ByteStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.ShortStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.LongStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.IntStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.StringStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.NullStruct;\n");
            body_.append("import code.expressionlanguage.opers.util.Struct;\n");
            body_.append("import code.expressionlanguage.stds.ResultErrorStd;\n");
            body_.append("import code.expressionlanguage.stds.StandardClass;\n");
            body_.append("import code.expressionlanguage.stds.StandardConstructor;\n");
            body_.append("import code.expressionlanguage.stds.StandardField;\n");
            body_.append("import code.expressionlanguage.stds.StandardMethod;\n");
            body_.append("import code.formathtml.DefaultInitialization;\n");
            body_.append("import code.formathtml.util.BeanLgNames;\n");
            body_.append("import code.util.CustList;\n");
            body_.append("import code.util.ObjectMap;\n");
            body_.append("import code.util.StringList;\n");
            body_.append("import code.util.StringMap;\n");
            for (String i: stdsCl_) {
                body_.append("import "+i+";\n");
            }
            body_.append("import aiki.beans.PokemonStandards;\n");
            int len_ = body_.length();
            body_.append("\n");
            body_.append("public final class ");
            body_.append(simpleName_);
            body_.append(" {\n");
            for (String c: classes_) {
                if (StringList.quickEq(c, LgInt.class.getName())) {
                    body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \"li\";\n");
                } else if (StringList.quickEq(c, Rate.class.getName())) {
                    body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \"r\";\n");
                } else {
                    body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \""+c+"\";\n");
                }
            }
            body_.append("\n");
            StringList methodsNames_ = new StringList();
            for (ClassMethodId e: CustElUtil.CALLS) {
                for (String c: classes_) {
                    if (StringList.quickEq(c, e.getClassName())) {
                        methodsNames_.add(e.getConstraints().getName());
                    }
                }
            }
            for (String g: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
                String cPart_ = g.substring(0, g.lastIndexOf('.'));
                String fPart_ = g.substring(g.lastIndexOf('.')+1);
                for (String c: classes_) {
                    if (StringList.quickEq(c, cPart_)) {
                        methodsNames_.add(fPart_);
                    }
                }
            }
            methodsNames_.removeDuplicates();
            for (String m: methodsNames_) {
                body_.append("    private static final String "+convertToUnderscore(m)+" = \""+m+"\";\n");
            }
            body_.append("\n");
            body_.append("    public static void build(BeanLgNames _std) {\n");
            for (String c: classes_) {
                body_.append("        build");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(_std);\n");
            }
            body_.append("    }\n");
            for (String c: classes_) {
                appendBuilder(body_, c);
            }
            for (String c: classes_) {
                appendGetter(body_, c);
            }
            for (String c: classes_) {
                appendSetter(body_, len_, c);
            }
            for (String c: classes_) {
                appendMethod(body_, len_, c);
            }
            body_.append("}\n");
            StreamTextFile.saveTextFile(out+"/"+fullName_, body_.toString());
        }
        String stds_ = "PokemonStandards";
        String pkg_ = "aiki.beans";
        String fullName_ = pkg_;
        StringBuilder body_ = new StringBuilder();
        body_.append("package ");
        body_.append(pkg_);
        body_.append(";\n");
        for (String p: packages_) {
            body_.append("import ");
            body_.append(p);
            body_.append(".");
            StringList baseNames_ = StringList.splitChars(p, '.');
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                body_.append(Character.toUpperCase(f_));
                body_.append(next_);
            }
            body_.append("Std;\n");
        }
        body_.append("import code.expressionlanguage.ContextEl;\n");
        body_.append("import code.expressionlanguage.opers.util.BooleanStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.ClassField;\n");
        body_.append("import code.expressionlanguage.opers.util.ClassMethodId;\n");
        body_.append("import code.expressionlanguage.opers.util.ConstructorId;\n");
        body_.append("import code.expressionlanguage.opers.util.IntStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.MethodId;\n");
        body_.append("import code.expressionlanguage.opers.util.MethodModifier;\n");
        body_.append("import code.expressionlanguage.opers.util.StdStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.ByteStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.ShortStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.LongStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.IntStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.StringStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.NullStruct;\n");
        body_.append("import code.expressionlanguage.opers.util.Struct;\n");
        body_.append("import code.expressionlanguage.stds.ResultErrorStd;\n");
        body_.append("import code.expressionlanguage.stds.StandardClass;\n");
        body_.append("import code.expressionlanguage.stds.StandardConstructor;\n");
        body_.append("import code.expressionlanguage.stds.StandardField;\n");
        body_.append("import code.expressionlanguage.stds.StandardMethod;\n");
        body_.append("import code.formathtml.DefaultInitialization;\n");
        body_.append("import code.formathtml.util.BeanLgNames;\n");
        body_.append("import code.formathtml.util.BeanStruct;\n");
        body_.append("import code.sml.Element;\n");
        body_.append("import code.bean.validator.Validator;\n");
        body_.append("import code.util.CustList;\n");
        body_.append("import code.util.ObjectMap;\n");
        body_.append("import code.util.StringList;\n");
        body_.append("import code.util.StringMap;\n");
        for (String r: allTypes_) {
            Class<?> clInfo_ = ConstClasses.classForNameNotInit(r);
            if (!Modifier.isPublic(clInfo_.getModifiers())) {
                continue;
            }
            body_.append("import "+r+";\n");
        }
        for (String r: remainClasses_) {
            body_.append("import "+r+";\n");
        }
        int len_ = body_.length();
        body_.append("\n");
        body_.append("public final class ");
        fullName_+="."+stds_;
        body_.append(stds_);
        body_.append(" extends BeanLgNames {\n");
        for (String c: remainClasses_) {
            if (StringList.quickEq(c, LgInt.class.getName())) {
                body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \"li\";\n");
            } else if (StringList.quickEq(c, Rate.class.getName())) {
                body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \"r\";\n");
            } else {
                body_.append("    public static final String TYPE"+convertToUnderscore(c.substring(c.lastIndexOf('.')+1))+" = \""+c+"\";\n");
            }
        }
        body_.append("    public static final String TYPE_FULL_RATE_VALIDATOR = \""+RateValidator.class.getName()+"\";\n");
        body_.append("    public static final String TYPE_FULL_POSITIVE_RATE_VALIDATOR = \""+PositiveRateValidator.class.getName()+"\";\n");
        body_.append("    public static final String TYPE_FULL_SHORT_VALIDATOR = \""+ShortValidator.class.getName()+"\";\n");
        body_.append("    public static final String TYPE_FULL_UNSELECTED_RADIO = \""+UnselectedRadio.class.getName()+"\";\n");
        body_.append("    public static final String TYPE_RATE_VALIDATOR = \""+RateValidator.class.getSimpleName()+"\";\n");
        body_.append("    public static final String TYPE_POSITIVE_RATE_VALIDATOR = \""+PositiveRateValidator.class.getSimpleName()+"\";\n");
        body_.append("    public static final String TYPE_SHORT_VALIDATOR = \""+ShortValidator.class.getSimpleName()+"\";\n");
        body_.append("    public static final String TYPE_UNSELECTED_RADIO = \""+UnselectedRadio.class.getSimpleName()+"\";\n");
        body_.append("\n");
        StringList methodsNames_ = new StringList();
        for (ClassMethodId e: CustElUtil.CALLS) {
            for (String c: remainClasses_) {
                if (StringList.quickEq(c, e.getClassName())) {
                    methodsNames_.add(e.getConstraints().getName());
                }
            }
        }
        for (String g: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
            String cPart_ = g.substring(0, g.lastIndexOf('.'));
            String fPart_ = g.substring(g.lastIndexOf('.')+1);
            for (String c: remainClasses_) {
                if (StringList.quickEq(c, cPart_)) {
                    methodsNames_.add(fPart_);
                }
            }
        }
        methodsNames_.removeDuplicates();
        for (String m: methodsNames_) {
            body_.append("    private static final String "+convertToUnderscore(m)+" = \""+m+"\";\n");
        }
        body_.append("    ");
        body_.append("public ");
        body_.append(stds_);
        body_.append("() {\n");
        body_.append("        setSelectedBoolean(\"sb\");\n");
        body_.append("        setCustList(\"ls\");\n");
        body_.append("        setCustMap(\"lse\");\n");
        body_.append("        DefaultInitialization.basicStandards(this);\n");
        body_.append("    }\n");
        body_.append("    public void buildOther() {\n");
        body_.append("        buildBeans();\n");
        for (String p: packages_) {
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleName_ = newPart_.toString();
            body_.append("        ");
//            String fullName_ = p + "."+simpleName_;
            body_.append(simpleName_+".build(this);\n");
        }
        for (String r: remainClasses_) {
            body_.append("        ");
            body_.append("build");
            String simpleName_ = r.substring(r.lastIndexOf('.')+1);
            body_.append(simpleName_+"(this);\n");
        }
        for (String v: new String[]{"RateValidator","PositiveRateValidator","ShortValidator","UnselectedRadio"}) {
            body_.append("        ");
            body_.append("build");
            body_.append(v+"(this);\n");
        }
        body_.append("    }\n");
        for (String r: remainClasses_) {
            appendBuilder(body_, r);
        }
        for (String v: new String[]{"RateValidator","PositiveRateValidator","ShortValidator","UnselectedRadio"}) {
            appendEmptyBuilder(body_, v);
        }
        body_.append("\n");
        body_.append("    public Validator buildValidator(Element _element) {\n");
        body_.append("        String clName_ = _element.getTagName();\n");
        body_.append("        if (StringList.quickEq(clName_, TYPE_FULL_RATE_VALIDATOR)){\n");
        body_.append("            RateValidator v_ = new RateValidator();\n");
        body_.append("            return v_;\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(clName_, TYPE_FULL_POSITIVE_RATE_VALIDATOR)){\n");
        body_.append("            PositiveRateValidator v_ = new PositiveRateValidator();\n");
        body_.append("            return v_;\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(clName_, TYPE_FULL_SHORT_VALIDATOR)){\n");
        body_.append("            ShortValidator v_ = new ShortValidator();\n");
        body_.append("            return v_;\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(clName_, TYPE_FULL_UNSELECTED_RADIO)){\n");
        body_.append("            UnselectedRadio v_ = new UnselectedRadio();\n");
        body_.append("            return v_;\n");
        body_.append("        }\n");
        body_.append("        return null;\n");
        body_.append("    }\n");
        StringList allInheritingTypes_ = new StringList();
        for (String p: packages_) {
            allInheritingTypes_.addAllElts(types_.getVal(p));
        }
        allInheritingTypes_.addAllElts(remainClasses_);
        allInheritingTypes_.removeDuplicates();
        int lenInh_ = allInheritingTypes_.size();
        for (int i = CustList.FIRST_INDEX; i < lenInh_; i++) {
            for (int j = i + 1; j < lenInh_; j++) {
                String typeOne_ = allInheritingTypes_.get(j);
                String typeTwo_ = allInheritingTypes_.get(i);
                boolean inheritOne_ = false;
                String type_ = typeOne_;
                while (!StringList.quickEq(type_, typeTwo_)) {
                    Class<?> st_ = ConstClasses.classForNameNotInit(type_);
                    if (st_.isInterface()) {
                        break;
                    }
                    if (st_.getSuperclass() == null) {
                        break;
                    }
                    type_ = st_.getSuperclass().getName();
                }
                if (StringList.quickEq(type_, typeTwo_)) {
                    inheritOne_ = true;
                }
                if (inheritOne_) {
                    allInheritingTypes_.swapIndexes(i, j);
                }
            }
        }
        body_.append("    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {\n");
        body_.append("        Object instance_ = _instance.getInstance();\n");
        for (String c: allInheritingTypes_) {
            Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
            if (!Modifier.isPublic(clInfo_.getModifiers())) {
                continue;
            }
            if (!hasReadField(c)) {
                continue;
            }
            String p = c.substring(0, c.lastIndexOf('.'));
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleNamePkg_ = newPart_.toString();
            String simpleName_ = c.substring(c.lastIndexOf('.')+1);
            body_.append("        if (instance_ instanceof ");
            body_.append(simpleName_);
            body_.append(") {\n");
            body_.append("            return ");
            body_.append(simpleNamePkg_);
            body_.append(".getResult");
            body_.append(simpleName_);
            body_.append("(_cont, _classField, _instance);\n");
            body_.append("        }\n");
        }
//        for (String p: packages_) {
//            StringList classes_ = types_.getVal(p);
//            StringList baseNames_ = StringList.splitChars(p, '.');
//            StringBuilder newPart_ = new StringBuilder();
//            for (String a: baseNames_) {
//                char f_ = a.charAt(0);
//                String next_ = a.substring(1);
//                newPart_.append(Character.toUpperCase(f_));
//                newPart_.append(next_);
//            }
//            newPart_.append("Std");
//            String simpleNamePkg_ = newPart_.toString();
//            for (String c: classes_) {
//                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
//                if (!Modifier.isPublic(clInfo_.getModifiers())) {
//                    continue;
//                }
//                if (!hasReadField(c)) {
//                    continue;
//                }
//                String simpleName_ = c.substring(c.lastIndexOf('.')+1);
//                body_.append("        if (instance_ instanceof ");
//                body_.append(simpleName_);
//                body_.append(") {\n");
//                body_.append("            return ");
//                body_.append(simpleNamePkg_);
//                body_.append(".getResult");
//                body_.append(simpleName_);
//                body_.append("(_cont, _classField, _instance);\n");
//                body_.append("        }\n");
//            }
//        }
        body_.append("        return new ResultErrorStd();\n");
        body_.append("    }\n\n");
        body_.append("    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {\n");
        body_.append("        Object instance_ = _instance.getInstance();\n");
        for (String c: allInheritingTypes_) {
            Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
            if (!Modifier.isPublic(clInfo_.getModifiers())) {
                continue;
            }
            if (!hasWrittenField(c)) {
                continue;
            }
            String p = c.substring(0, c.lastIndexOf('.'));
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleNamePkg_ = newPart_.toString();
            String simpleName_ = c.substring(c.lastIndexOf('.')+1);
            body_.append("        if (instance_ instanceof ");
            body_.append(simpleName_);
            body_.append(") {\n");
            body_.append("            return ");
            body_.append(simpleNamePkg_);
            body_.append(".setResult");
            body_.append(simpleName_);
            body_.append("(_cont, _classField, _instance, _value);\n");
            body_.append("        }\n");
        }
//        for (String p: packages_) {
//            StringList classes_ = types_.getVal(p);
//            StringList baseNames_ = StringList.splitChars(p, '.');
//            StringBuilder newPart_ = new StringBuilder();
//            for (String a: baseNames_) {
//                char f_ = a.charAt(0);
//                String next_ = a.substring(1);
//                newPart_.append(Character.toUpperCase(f_));
//                newPart_.append(next_);
//            }
//            newPart_.append("Std");
//            String simpleNamePkg_ = newPart_.toString();
//            for (String c: classes_) {
//                if (!hasWrittenField(c)) {
//                    continue;
//                }
//                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
//                if (!Modifier.isPublic(clInfo_.getModifiers())) {
//                    continue;
//                }
//                String simpleName_ = c.substring(c.lastIndexOf('.')+1);
//                body_.append("        if (instance_ instanceof ");
//                body_.append(simpleName_);
//                body_.append(") {\n");
//                body_.append("            return ");
//                body_.append(simpleNamePkg_);
//                body_.append(".setResult");
//                body_.append(simpleName_);
//                body_.append("(_cont, _classField, _instance, _value);\n");
//                body_.append("        }\n");
//            }
//        }
        body_.append("        return new ResultErrorStd();\n");
        body_.append("    }\n\n");
        body_.append("    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {\n");
        body_.append("        Object instance_ = _instance.getInstance();\n");
        for (String c: allInheritingTypes_) {
            Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
            if (!Modifier.isPublic(clInfo_.getModifiers())) {
                continue;
            }
            if (!hasMethod(c)) {
                continue;
            }
            String p = c.substring(0, c.lastIndexOf('.'));
            StringList baseNames_ = StringList.splitChars(p, '.');
            StringBuilder newPart_ = new StringBuilder();
            for (String a: baseNames_) {
                char f_ = a.charAt(0);
                String next_ = a.substring(1);
                newPart_.append(Character.toUpperCase(f_));
                newPart_.append(next_);
            }
            newPart_.append("Std");
            String simpleNamePkg_ = newPart_.toString();
            String simpleName_ = c.substring(c.lastIndexOf('.')+1);
            body_.append("        if (instance_ instanceof ");
            body_.append(simpleName_);
            body_.append(") {\n");
            body_.append("            return ");
            if (simpleNamePkg_.startsWith("AikiBeans")) {
                body_.append(simpleNamePkg_+".");
            } else {
                body_.append("PokemonStandards.");
            }
            body_.append("invokeMethod");
            body_.append(simpleName_);
            body_.append("(_cont, _instance, _method, _args);\n");
            body_.append("        }\n");
        }
//        for (String p: packages_) {
//            StringList classes_ = types_.getVal(p);
//            StringList baseNames_ = StringList.splitChars(p, '.');
//            StringBuilder newPart_ = new StringBuilder();
//            for (String a: baseNames_) {
//                char f_ = a.charAt(0);
//                String next_ = a.substring(1);
//                newPart_.append(Character.toUpperCase(f_));
//                newPart_.append(next_);
//            }
//            newPart_.append("Std");
//            String simpleNamePkg_ = newPart_.toString();
//            for (String c: classes_) {
//                if (!hasMethod(c)) {
//                    continue;
//                }
//                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
//                if (!Modifier.isPublic(clInfo_.getModifiers())) {
//                    continue;
//                }
//                String simpleName_ = c.substring(c.lastIndexOf('.')+1);
//                body_.append("        if (instance_ instanceof ");
//                body_.append(simpleName_);
//                body_.append(") {\n");
//                body_.append("            return ");
//                body_.append(simpleNamePkg_);
//                body_.append(".invokeMethod");
//                body_.append(simpleName_);
//                body_.append("(_cont, _instance, _method, _args);\n");
//                body_.append("        }\n");
//            }
//        }
//        for (String c: remainClasses_) {
//            if (!hasMethod(c)) {
//                continue;
//            }
//            String simpleName_ = c.substring(c.lastIndexOf('.')+1);
//            body_.append("        if (instance_ instanceof ");
//            body_.append(simpleName_);
//            body_.append(") {\n");
//            body_.append("            return invokeMethod");
//            body_.append(simpleName_);
//            body_.append("(_cont, _instance, _method, _args);\n");
//            body_.append("        }\n");
//        }
        body_.append("        return new ResultErrorStd();\n");
        body_.append("    }\n\n");
        body_.append("    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {\n");
        body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        body_.append("        String name_ = _method.getName();\n");
        for (String t: allTypes_) {
            Class<?> cl_ = ConstClasses.classForNameNotInit(t);
            if (!Bean.class.isAssignableFrom(cl_)) {
                continue;
            }
            if (Modifier.isAbstract(cl_.getModifiers())) {
                continue;
            }
            body_.append("        if (StringList.quickEq(name_,"+importedType(t)+")) {\n");
            body_.append("            "+cl_.getSimpleName()+" bean_ = new "+cl_.getSimpleName()+"();\n");
            body_.append("            bean_.setClassName("+importedType(t)+");\n");
            body_.append("            res_.setResult(new BeanStruct(bean_));\n");
            body_.append("            return res_;\n");
            body_.append("        }\n");
        }
        body_.append("        return res_;\n");
        body_.append("    }\n\n");
        body_.append("    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {\n");
        for (String t: allTypes_) {
            Class<?> cl_ = ConstClasses.classForNameNotInit(t);
            if (Bean.class.isAssignableFrom(cl_)) {
                continue;
            }
            if (Validator.class.isAssignableFrom(cl_)) {
                continue;
            }
            if (!Modifier.isPublic(cl_.getModifiers())) {
                continue;
            }
            if (cl_ == SelectedBoolean.class) {
                continue;
            }
            if (!Modifier.isFinal(cl_.getModifiers())) {
                continue;
            }
//            if (cl_ == Rate.class) {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return RATE;\n");
//                body_.append("        }\n");
//            } else if (cl_ == LgInt.class) {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return LG_INT;\n");
//                body_.append("        }\n");
//            } else {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return "+importedType(t)+";\n");
//                body_.append("        }\n");
//            }
            body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
            body_.append("            return "+importedType(t)+";\n");
            body_.append("        }\n");
        }
        for (String t: remainClasses_) {
            Class<?> cl_ = ConstClasses.classForNameNotInit(t);
            if (cl_ == SelectedBoolean.class) {
                continue;
            }
            if (!Modifier.isPublic(cl_.getModifiers())) {
                continue;
            }
            if (!Modifier.isFinal(cl_.getModifiers())) {
                continue;
            }
//            if (cl_ == Rate.class) {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return RATE;\n");
//                body_.append("        }\n");
//            } else if (cl_ == LgInt.class) {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return LG_INT;\n");
//                body_.append("        }\n");
//            } else {
//                body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
//                body_.append("            return "+importedType(t)+";\n");
//                body_.append("        }\n");
//            }
            body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
            body_.append("            return "+importedType(t)+";\n");
            body_.append("        }\n");
        }
        for (String t: allTypes_) {
            Class<?> cl_ = ConstClasses.classForNameNotInit(t);
            if (Bean.class.isAssignableFrom(cl_)) {
                continue;
            }
            if (!Modifier.isPublic(cl_.getModifiers())) {
                continue;
            }
            if (Validator.class.isAssignableFrom(cl_)) {
                continue;
            }
            if (Modifier.isFinal(cl_.getModifiers())) {
                continue;
            }
            body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
            body_.append("            return "+importedType(t)+";\n");
            body_.append("        }\n");
        }
        for (String t: remainClasses_) {
            Class<?> cl_ = ConstClasses.classForNameNotInit(t);
            if (Modifier.isFinal(cl_.getModifiers())) {
                continue;
            }
            if (!Modifier.isPublic(cl_.getModifiers())) {
                continue;
            }
            body_.append("        if (_struct instanceof "+cl_.getSimpleName()+") {\n");
            body_.append("            return "+importedType(t)+";\n");
            body_.append("        }\n");
        }
        body_.append("        return getAliasObject();\n");
        body_.append("    }\n");
        FormatHtmlLookFor.SELECT_CLASSES.removeDuplicates();
        body_.append("    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {\n");
        body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        body_.append("        Object instance_ = _instance.getInstance();\n");
        for (String c: FormatHtmlLookFor.SELECT_CLASSES) {
            if (StringList.quickEq(c, "sb")) {
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(c)) {
                continue;
            }
            Class<?> cl_ = ConstClasses.classForNameNotInit(c);
            if (!cl_.isEnum()) {
                continue;
            }
            if (cl_ == SelectedBoolean.class) {
                continue;
            }
            String s_ = c.substring(c.lastIndexOf('.')+1);
            body_.append("        if (instance_ instanceof "+s_+") {\n");
            body_.append("            res_.setResult(new StringStruct((("+s_+")instance_).name()));\n");
            body_.append("            return res_;\n");
            body_.append("        }\n");
        }
        body_.append("        return res_;\n");
        body_.append("    }\n");
        FormatHtmlLookFor.INPUT_CLASSES.removeDuplicates();
        body_.append("    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context) {\n");
        body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        body_.append("        if (_values.isEmpty()) {\n");
        body_.append("            res_.setError(getAliasError());\n");
        body_.append("            return res_;\n");
        body_.append("        }\n");
        body_.append("        String value_ = _values.first();\n");
        body_.append("        if (StringList.quickEq(_className,TYPE_RATE)){\n");
        body_.append("            if (!Rate.isValid(value_)) {\n");
        body_.append("                res_.setError(getAliasError());\n");
        body_.append("                return res_;\n");
        body_.append("            }\n");
        body_.append("            res_.setResult(new StdStruct(new Rate(value_),TYPE_RATE));\n");
        body_.append("            return res_;\n");
        body_.append("        }\n");
        body_.append("        Object instance_ = null;\n");
        body_.append("        if (StringList.quickEq(_className,AikiBeansFacadeSimulationEnumsStd.TYPE_TEAM_CRUD)){\n");
        body_.append("            instance_ = TeamCrud.getTeamCrudByName(value_);\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(_className,TYPE_GENDER)){\n");
        body_.append("            instance_ = Gender.getGenderByName(value_);\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(_className,TYPE_DIFFICULTY_WIN_POINTS_FIGHT)){\n");
        body_.append("            instance_ = DifficultyWinPointsFight.getDiffWonPtsByName(value_);\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(_className,TYPE_DIFFICULTY_MODEL_LAW)){\n");
        body_.append("            instance_ = DifficultyModelLaw.getModelByName(value_);\n");
        body_.append("        }\n");
        body_.append("        if (StringList.quickEq(_className,TYPE_ENVIRONMENT_TYPE)){\n");
        body_.append("            instance_ = EnvironmentType.getEnvByName(value_);\n");
        body_.append("        }\n");
        body_.append("        if (instance_ == null) {\n");
        body_.append("            res_.setError(getAliasError());\n");
        body_.append("        } else {\n");
        body_.append("            res_.setResult(new StdStruct(instance_, _className));\n");
        body_.append("        }\n");
        body_.append("        return res_;\n");
        body_.append("    }\n");
        for (String c: remainClasses_) {
            appendGetter(body_, c);
        }
        for (String c: remainClasses_) {
            appendSetter(body_, len_, c);
        }
        for (String c: remainClasses_) {
            appendMethod(body_, len_, c);
        }
        //public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        body_.append("}\n");
        StreamTextFile.saveTextFile(out+"/"+ fullName_.replace('.', '/')+".java", body_.toString());
        //add standards
    }
    private static void appendEmptyBuilder(StringBuilder _body, String _class) {
        _body.append("    private static void build");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("(BeanLgNames _std) {\n");
        _body.append("        StandardClass type_;\n");
        _body.append("        StringMap<StandardField> fields_;\n");
        _body.append("        CustList<StandardConstructor> constructors_;\n");
        _body.append("        ObjectMap<MethodId, StandardMethod> methods_;\n");
        _body.append("        methods_ = new ObjectMap<MethodId, StandardMethod>();\n");
        _body.append("        constructors_ = new CustList<StandardConstructor>();\n");
        _body.append("        fields_ = new StringMap<StandardField>();\n");
        String alias_ = "TYPE"+convertToUnderscore(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("        type_ = new StandardClass(").append(alias_).append(", fields_, constructors_, methods_, ");
        _body.append("_std.getValidator()").append(",");
        _body.append(" MethodModifier.NORMAL);\n");
        _body.append("        _std.getStandards().put(").append(alias_).append(", type_);\n");
        _body.append("    }\n");
    }
    private static void appendBuilder(StringBuilder _body, String _class) {
        _body.append("    private static void build");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("(BeanLgNames _std) {\n");
        _body.append("        StandardClass type_;\n");
        _body.append("        StringMap<StandardField> fields_;\n");
//        for (String s: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
//            String clPart_ = s.substring(0, s.lastIndexOf('.'));
//            if (StringList.quickEq(clPart_, _class)) {
//                _body.append("        StandardField field_;\n");
//                break;
//            }
//        }
        _body.append("        CustList<StandardConstructor> constructors_;\n");
        _body.append("        ObjectMap<MethodId, StandardMethod> methods_;\n");
        if (hasMethod(_class)) {
            _body.append("        StandardMethod method_;\n");
            _body.append("        StringList params_;\n");
        }
        Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
        boolean abs_ = Modifier.isAbstract(clInfo_.getModifiers());
        String superClass_ = clInfo_.getSuperclass().getName();
        _body.append("        methods_ = new ObjectMap<MethodId, StandardMethod>();\n");
        _body.append("        constructors_ = new CustList<StandardConstructor>();\n");
        _body.append("        fields_ = new StringMap<StandardField>();\n");
        String alias_ = "TYPE"+convertToUnderscore(_class.substring(_class.lastIndexOf('.')+1));
//        String alias_ = _class;
//        if (StringList.quickEq(_class, Rate.class.getName())) {
//            alias_ = "r";
//        } else if (StringList.quickEq(_class, LgInt.class.getName())) {
//            alias_ = "li";
//        }
//        _body.append("        type_ = new StandardClass(\"").append(alias_).append("\", fields_, constructors_, methods_, ");
        _body.append("        type_ = new StandardClass(").append(alias_).append(", fields_, constructors_, methods_, ");
        if (StringList.quickEq(superClass_, Object.class.getName()) || StringList.quickEq(superClass_, Enum.class.getName())) {
            _body.append("_std.getAliasObject()").append(",");
        } else if (StringList.quickEq(superClass_, Validator.class.getName())) {
            _body.append("_std.getValidator()").append(",");
        } else if (StringList.quickEq(superClass_, Bean.class.getName())) {
            _body.append("_std.getBean()").append(",");
        } else {
//            _body.append("\"").append(superClass_).append("\",");
            _body.append(importedType(superClass_)).append(",");
        }
        if (abs_) {
            _body.append(" MethodModifier.ABSTRACT);\n");
        } else {
            _body.append(" MethodModifier.NORMAL);\n");
        }
        if (Displayable.class.isAssignableFrom(clInfo_)) {
            _body.append("        type_.getDirectInterfaces().add(_std.getAliasDisplayable());\n");
        }
        for (String s: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
            String clPart_ = s.substring(0, s.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            String fPart_ = s.substring(s.lastIndexOf('.')+1);
            try {
                Field info_ = clInfo_.getDeclaredField(fPart_);
                String aField_ = convertToUnderscore(fPart_);
                if (info_.getType() == boolean.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasPrimBoolean(),false,false,type_));\n");
                } else if (info_.getType() == Boolean.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasBoolean(),false,false,type_));\n");
                } else if (info_.getType() == int.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasPrimInteger(),false,false,type_));\n");
                } else if (info_.getType() == long.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasPrimLong(),false,false,type_));\n");
                } else if (info_.getType() == byte.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasPrimByte(),false,false,type_));\n");
                } else if (info_.getType() == short.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasPrimShort(),false,false,type_));\n");
                } else if (info_.getType() == Integer.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasInteger(),false,false,type_));\n");
                } else if (info_.getType() == Long.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasLong(),false,false,type_));\n");
                } else if (info_.getType() == Byte.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasByte(),false,false,type_));\n");
                } else if (info_.getType() == Short.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasShort(),false,false,type_));\n");
                } else if (info_.getType() == String.class) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getAliasString(),false,false,type_));\n");
                } else if (Listable.class.isAssignableFrom(info_.getType())) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getCustList(),false,false,type_));\n");
                } else if (ListableEntries.class.isAssignableFrom(info_.getType())) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getCustMap(),false,false,type_));\n");
                } else if (SelectedBoolean.class.isAssignableFrom(info_.getType())) {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",_std.getSelectedBoolean(),false,false,type_));\n");
//                } else if (Rate.class.isAssignableFrom(info_.getType())) {
//                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",r,false,false,type_));\n");
//                } else if (LgInt.class.isAssignableFrom(info_.getType())) {
//                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",li,false,false,type_));\n");
                } else {
                    _body.append("        fields_.put(").append(aField_).append(",new StandardField(").append(aField_).append(",").append(importedType(info_.getType().getName())).append(",false,false,type_));\n");
                }
            } catch (Exception _0) {
                _0.printStackTrace();
            }
        }
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (!StringList.quickEq(e.getClassName(), _class)) {
                continue;
            }
            for (Method m: clInfo_.getDeclaredMethods()) {
                if (!StringList.quickEq(e.getConstraints().getName(), m.getName()) || e.getConstraints().getParametersTypes().size() != m.getParameterTypes().length) {
                    continue;
                }
                if (m.getAnnotation(Accessible.class) == null && !Modifier.isPublic(m.getModifiers())) {
                    continue;
                }
                _body.append("        params_ = new StringList(");
                StringList params_ = new StringList();
                for (Class<?> t: m.getParameterTypes()) {
                    if (t == int.class) {
                        params_.add("_std.getAliasPrimInteger()");
                    } else if (t == long.class) {
                        params_.add("_std.getAliasPrimLong()");
                    } else if (t == byte.class) {
                        params_.add("_std.getAliasPrimByte()");
                    } else if (t == short.class) {
                        params_.add("_std.getAliasPrimShort()");
                    } else if (t == Integer.class) {
                        params_.add("_std.getAliasInteger()");
                    } else if (t == Long.class) {
                        params_.add("_std.getAliasLong()");
                    } else if (t == Byte.class) {
                        params_.add("_std.getAliasByte()");
                    } else if (t == Short.class) {
                        params_.add("_std.getAliasShort()");
                    } else {
                        params_.add("_std.getAliasString()");
                    }
                }
                _body.append(params_.join(","));
                _body.append(");\n");
                _body.append("        method_ = new StandardMethod(");
                _body.append(convertToUnderscore(m.getName()));
                _body.append(",params_,");
                if (m.getReturnType() == boolean.class) {
                    _body.append("_std.getAliasPrimBoolean()");
                } else if (m.getReturnType() == Boolean.class) {
                    _body.append("_std.getAliasBoolean()");
                } else if (m.getReturnType() == int.class) {
                    _body.append("_std.getAliasPrimInteger()");
                } else if (m.getReturnType() == long.class) {
                    _body.append("_std.getAliasPrimLong()");
                } else if (m.getReturnType() == byte.class) {
                    _body.append("_std.getAliasPrimByte()");
                } else if (m.getReturnType() == short.class) {
                    _body.append("_std.getAliasPrimShort()");
                } else if (m.getReturnType() == Integer.class) {
                    _body.append("_std.getAliasInteger()");
                } else if (m.getReturnType() == Long.class) {
                    _body.append("_std.getAliasLong()");
                } else if (m.getReturnType() == Byte.class) {
                    _body.append("_std.getAliasByte()");
                } else if (m.getReturnType() == Short.class) {
                    _body.append("_std.getAliasShort()");
                } else if (m.getReturnType() == String.class) {
                    _body.append("_std.getAliasString()");
                } else if (m.getReturnType() == void.class) {
                    _body.append("_std.getAliasVoid()");
                } else if (Listable.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("_std.getCustList()");
                } else if (ListableEntries.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("_std.getCustMap()");
                } else if (SelectedBoolean.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("_std.getSelectedBoolean()");
//                } else if (Rate.class.isAssignableFrom(m.getReturnType())) {
//                    _body.append("\"r\"");
//                } else if (LgInt.class.isAssignableFrom(m.getReturnType())) {
//                    _body.append("\"li\"");
                } else {
                    _body.append(importedType(m.getReturnType().getName()));
                }
                _body.append(", false, MethodModifier.NORMAL,type_);\n");
                _body.append("        methods_.put(method_.getId(), method_);\n");
                break;
            }
        }
//        _body.append("        _std.getStandards().put(\"").append(alias_).append("\", type_);\n");
        _body.append("        _std.getStandards().put(").append(alias_).append(", type_);\n");
        _body.append("    }\n");
    }
    private static void appendGetter(StringBuilder _body, String _class) {
        if (!hasReadField(_class)) {
            return;
        }
        Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
        Class<?> firstSuperClass_ = clInfo_.getSuperclass();
        while (firstSuperClass_ != Object.class) {
            if (hasReadField(firstSuperClass_.getName())) {
                break;
            }
            firstSuperClass_ = firstSuperClass_.getSuperclass();
        }
        _body.append("    public static ResultErrorStd getResult");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("(ContextEl _cont, ClassField _classField, Struct _instance) {\n");
        if (hasReadFieldStd(_class)) {
            _body.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
        }
        _body.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        _body.append("        ");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(" instance_ = (");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(") _instance.getInstance();\n");
        //public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        for (EntryCust<String, BooleanList> s: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            boolean get_ = false;
            for (boolean w: s.getValue()) {
                if (!w) {
                    get_ = true;
                    break;
                }
            }
            if (!get_) {
                continue;
            }
            String key_ = s.getKey();
            String clPart_ = key_.substring(0, key_.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            String fPart_ = key_.substring(key_.lastIndexOf('.')+1);
            char first_ = fPart_.charAt(0);
            _body.append("        if (StringList.quickEq(_classField.getFieldName(),").append(convertToUnderscore(fPart_)).append(")) {\n");
            _body.append("            res_.setResult(");
            String getter_ = "get"+Character.toUpperCase(first_)+fPart_.substring(1);
            boolean end_ = true;
            Field info_;
            try {
                info_ = clInfo_.getDeclaredField(fPart_);
                String out_ = getSimpleOutput(info_.getType());
                if (out_ != null) {
                    _body.append(out_);
                } else {
                    end_ = false;
                }
            } catch (Exception _0) {
                info_ = null;
                _0.printStackTrace();
            }
            if (end_) {
                _body.append(getter_);
                _body.append("()));\n");
            } else if (Listable.class.isAssignableFrom(info_.getType())) {
                _body.append("new StdStruct(instance_.").append(getter_).append("(),std_.getCustList()));\n");
            } else if (ListableEntries.class.isAssignableFrom(info_.getType())) {
                _body.append("new StdStruct(instance_.").append(getter_).append("(),std_.getCustMap()));\n");
            } else if (SelectedBoolean.class.isAssignableFrom(info_.getType())) {
                _body.append("new StdStruct(instance_.").append(getter_).append("(),std_.getSelectedBoolean()));\n");
//            } else if (Rate.class.isAssignableFrom(info_.getType())) {
//                _body.append("new StdStruct(instance_.").append(getter_).append("(),\"r\"));\n");
//            } else if (LgInt.class.isAssignableFrom(info_.getType())) {
//                _body.append("new StdStruct(instance_.").append(getter_).append("(),\"li\"));\n");
            } else {
                _body.append("new StdStruct(instance_.").append(getter_).append("(),").append(importedType(info_.getType().getName())).append("));\n");
            }
            _body.append("            return res_;\n");
            _body.append("        }\n");
        }
        if (firstSuperClass_ == Object.class) {
            _body.append("        return res_;\n");
        } else {
            _body.append("        return "+getStandardType(firstSuperClass_.getName(), "getResult")+"(_cont, _classField, _instance);\n");
        }
        _body.append("    }\n");
    }
    private static void appendSetter(StringBuilder _body, int _index, String _class) {
        if (!hasWrittenField(_class)) {
            return;
        }
        Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
        Class<?> firstSuperClass_ = clInfo_.getSuperclass();
        while (firstSuperClass_ != Object.class) {
            if (hasWrittenField(firstSuperClass_.getName())) {
                break;
            }
            firstSuperClass_ = firstSuperClass_.getSuperclass();
        }
        _body.append("    public static ResultErrorStd setResult");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {\n");
        //_body.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
        _body.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        _body.append("        ");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(" instance_ = (");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(") _instance.getInstance();\n");
        _body.append("        Object value_ = _value.getInstance();\n");
        //public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        for (EntryCust<String, BooleanList> s: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            boolean set_ = false;
            for (boolean w: s.getValue()) {
                if (w) {
                    set_ = true;
                    break;
                }
            }
            if (!set_) {
                continue;
            }
            String key_ = s.getKey();
            String clPart_ = key_.substring(0, key_.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            String fPart_ = key_.substring(key_.lastIndexOf('.')+1);
            char first_ = fPart_.charAt(0);
            _body.append("        if (StringList.quickEq(_classField.getFieldName(),").append(convertToUnderscore(fPart_)).append(")) {\n");
            _body.append("            instance_.");
            String setter_ = "set"+Character.toUpperCase(first_)+fPart_.substring(1)+"(";
            _body.append(setter_);
            Field info_;
            try {
                info_ = clInfo_.getDeclaredField(fPart_);
                if (info_.getType() == int.class || info_.getType() == Integer.class) {
                    _body.append("(Integer)");
                } else if (info_.getType() == long.class || info_.getType() == Long.class) {
                    _body.append("(Long)");
                } else if (info_.getType() == byte.class || info_.getType() == Byte.class) {
                    _body.append("(Byte)");
                } else if (info_.getType() == short.class || info_.getType() == Short.class) {
                    _body.append("(Short)");
                } else if (info_.getType() == boolean.class || info_.getType() == Boolean.class) {
                    _body.append("(Boolean)");
                } else if (info_.getType() == String.class) {
                    _body.append("(String)");
                } else {
                    _body.append("(");
                    _body.insert(_index, "import "+info_.getType().getName()+";\n");
                    _body.append(info_.getType().getSimpleName());
                    _body.append(")");
                }
            } catch (Exception _0) {
                info_ = null;
                _0.printStackTrace();
            }
            _body.append(" value_");
            _body.append(");\n");
            _body.append("            res_.setResult(NullStruct.NULL_VALUE);\n");
            _body.append("            return res_;\n");
            _body.append("        }\n");
        }
        if (firstSuperClass_ == Object.class) {
            _body.append("        return res_;\n");
        } else {
            _body.append("        return "+getStandardType(firstSuperClass_.getName(), "setResult")+"(_cont, _classField, _instance, _value);\n");
        }
        _body.append("    }\n");
    }
    private static void appendMethod(StringBuilder _body, int _index, String _class) {
        if (!hasMethod(_class)) {
            return;
        }
        Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
        if (!Modifier.isPublic(clInfo_.getModifiers())) {
            return;
        }
        Class<?> firstSuperClass_ = clInfo_.getSuperclass();
        while (firstSuperClass_ != Object.class) {
            if (hasMethod(firstSuperClass_.getName())) {
                break;
            }
            firstSuperClass_ = firstSuperClass_.getSuperclass();
        }
        _body.append("    public static ResultErrorStd invokeMethod");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append("(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {\n");
        if (hasMethodStd(_class)) {
            _body.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
        }
        _body.append("        ");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(" instance_ = (");
        _body.append(_class.substring(_class.lastIndexOf('.')+1));
        _body.append(") _instance.getInstance();\n");
        _body.append("        String methodName_ = _method.getConstraints().getName();\n");
        _body.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (!StringList.quickEq(e.getClassName(), _class)) {
                continue;
            }
            for (Method m: clInfo_.getDeclaredMethods()) {
                if (!StringList.quickEq(e.getConstraints().getName(), m.getName()) || e.getConstraints().getParametersTypes().size() != m.getParameterTypes().length) {
                    continue;
                }
                _body.append("        if (StringList.quickEq(methodName_,").append(convertToUnderscore(m.getName())).append(")) {\n");
                if (m.getReturnType() == void.class) {
                    StringList params_ = getParametersList(m, _index, _body);
                    _body.append("            instance_.").append(m.getName()).append("("+params_.join(",")+");\n");
                    _body.append("            res_.setResult(NullStruct.NULL_VALUE);\n");
                    _body.append("            return res_;\n");
                    _body.append("        }\n");
                    continue;
                } else {
                    _body.append("            res_.setResult(");
                }
                boolean end_ = true;
                try {
                    String out_ = getSimpleOutput(m.getReturnType());
                    if (out_ != null) {
                        _body.append(out_);
                    } else {
                        end_ = false;
                    }
                } catch (Exception _0) {
                    _0.printStackTrace();
                }
                StringList params_ = getParametersList(m, _index, _body);
                if (end_) {
                    _body.append(m.getName());
                    _body.append("(");
                    _body.append(params_.join(","));
                    _body.append(")));\n");
                } else if (Listable.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),std_.getCustList()));\n");
                } else if (ListableEntries.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),std_.getCustMap()));\n");
                } else if (SelectedBoolean.class.isAssignableFrom(m.getReturnType())) {
                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),std_.getSelectedBoolean()));\n");
//                } else if (Rate.class.isAssignableFrom(m.getReturnType())) {
//                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),\"r\"));\n");
//                } else if (LgInt.class.isAssignableFrom(m.getReturnType())) {
//                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),\"li\"));\n");
                } else {
                    _body.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),").append(importedType(m.getReturnType().getName())).append("));\n");
                }
                _body.append("            return res_;\n");
                _body.append("        }\n");
                break;
            }
        }
        if (firstSuperClass_ == Object.class) {
            _body.append("        return res_;\n");
        } else {
            _body.append("        return "+getStandardType(firstSuperClass_.getName(), "invokeMethod")+"(_cont, _instance, _method, _args);\n");
        }
        _body.append("    }\n");
    }
    private static boolean hasWrittenField(String _class) {
        for (EntryCust<String, BooleanList> s: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            boolean set_ = false;
            for (boolean w: s.getValue()) {
                if (w) {
                    set_ = true;
                    break;
                }
            }
            if (!set_) {
                continue;
            }
            String key_ = s.getKey();
            String clPart_ = key_.substring(0, key_.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            return true;
        }
        return false;
    }
    private static boolean hasReadFieldStd(String _class) {
        for (EntryCust<String, BooleanList> s: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            boolean get_ = false;
            for (boolean w: s.getValue()) {
                if (!w) {
                    get_ = true;
                    break;
                }
            }
            if (!get_) {
                continue;
            }
            String key_ = s.getKey();
            String clPart_ = key_.substring(0, key_.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            String fPart_ = key_.substring(key_.lastIndexOf('.')+1);
            Field info_;
            try {
                Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
                info_ = clInfo_.getDeclaredField(fPart_);
                if (Listable.class.isAssignableFrom(info_.getType())) {
                    return true;
                }
                if (ListableEntries.class.isAssignableFrom(info_.getType())) {
                    return true;
                }
                if (SelectedBoolean.class.isAssignableFrom(info_.getType())) {
                    return true;
                }
            } catch (Exception _0) {
            }
        }
        return false;
    }
    private static boolean hasReadField(String _class) {
        for (EntryCust<String, BooleanList> s: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            boolean get_ = false;
            for (boolean w: s.getValue()) {
                if (!w) {
                    get_ = true;
                    break;
                }
            }
            if (!get_) {
                continue;
            }
            String key_ = s.getKey();
            String clPart_ = key_.substring(0, key_.lastIndexOf('.'));
            if (!StringList.quickEq(clPart_, _class)) {
                continue;
            }
            return true;
        }
        return false;
    }
    private static boolean hasMethodStd(String _class) {
        Class<?> clInfo_ = ConstClasses.classForNameNotInit(_class);
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (!StringList.quickEq(e.getClassName(), _class)) {
                continue;
            }
            for (Method m: clInfo_.getDeclaredMethods()) {
                if (m.getAnnotation(Accessible.class) == null && !Modifier.isPublic(m.getModifiers())) {
                    continue;
                }
                if (!StringList.quickEq(e.getConstraints().getName(), m.getName()) || e.getConstraints().getParametersTypes().size() != m.getParameterTypes().length) {
                    continue;
                }
                if (m.getReturnType() == void.class) {
                    continue;
                }
                if (Listable.class.isAssignableFrom(m.getReturnType())) {
                    return true;
                }
                if (ListableEntries.class.isAssignableFrom(m.getReturnType())) {
                    return true;
                }
                if (SelectedBoolean.class.isAssignableFrom(m.getReturnType())) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean hasMethod(String _class) {
        for (ClassMethodId e: CustElUtil.CALLS) {
            if (!StringList.quickEq(e.getClassName(), _class)) {
                continue;
            }
            return true;
        }
        return false;
    }
    private static String getSimpleOutput(Class<?> _class) {
        if (_class == int.class || _class == Integer.class) {
            return "new IntStruct(instance_.";
        }
        if (_class == long.class || _class == Long.class) {
            return "new LongStruct(instance_.";
        }
        if (_class == byte.class || _class == Byte.class) {
            return "new ByteStruct(instance_.";
        }
        if (_class == short.class || _class == Short.class) {
            return "new ShortStruct(instance_.";
        }
        if (_class == boolean.class || _class == Boolean.class) {
            return "new BooleanStruct(instance_.";
        }
        if (_class == String.class) {
            return "new StringStruct(instance_.";
        }
        return null;
    }
    private static StringList getParametersList(Method m, int _index, StringBuilder _body) {
        StringList params_ = new StringList();
        int i_ = 0;
        for (Class<?> a: m.getParameterTypes()) {
            StringBuilder pBody_ = new StringBuilder();
            if (a == int.class || a == Integer.class) {
                pBody_.append("(Integer)");
            } else if (a == long.class || a == Long.class) {
                pBody_.append("(Long)");
            } else if (a == byte.class || a == Byte.class) {
                pBody_.append("(Byte)");
            } else if (a == short.class || a == Short.class) {
                pBody_.append("(Short)");
            } else if (a == boolean.class || a == Boolean.class) {
                pBody_.append("(Boolean)");
            } else if (a == String.class) {
                pBody_.append("(String)");
            } else {
                pBody_.append("(");
                _body.insert(_index, "import "+a.getName()+";\n");
                pBody_.append(a.getSimpleName());
                pBody_.append(")");
            }
            pBody_.append("_args[");
            pBody_.append(i_);
            pBody_.append("]");
            params_.add(pBody_.toString());
            i_++;
        }
        return params_;
    }
    private static String getStandardType(String _string, String _method) {
        if (!_string.startsWith("aiki.beans.")) {
            return "PokemonStandards."+_method+_string.substring(_string.lastIndexOf('.')+1);
        }
        StringList baseNames_ = StringList.splitChars(_string.substring(0, _string.lastIndexOf('.')), '.');
        StringBuilder newPart_ = new StringBuilder();
        for (String a: baseNames_) {
            char f_ = a.charAt(0);
            String next_ = a.substring(1);
            newPart_.append(Character.toUpperCase(f_));
            newPart_.append(next_);
        }
        newPart_.append("Std");
        return newPart_.toString() + "."+_method+_string.substring(_string.lastIndexOf('.')+1);
    }
    private static String importedType(String _string) {
        if (!_string.startsWith("aiki.beans.")) {
            return "PokemonStandards.TYPE"+convertToUnderscore(_string.substring(_string.lastIndexOf('.')+1));
        }
        StringList baseNames_ = StringList.splitChars(_string.substring(0, _string.lastIndexOf('.')), '.');
        StringBuilder newPart_ = new StringBuilder();
        for (String a: baseNames_) {
            char f_ = a.charAt(0);
            String next_ = a.substring(1);
            newPart_.append(Character.toUpperCase(f_));
            newPart_.append(next_);
        }
        newPart_.append("Std");
        return newPart_.toString() + ".TYPE"+convertToUnderscore(_string.substring(_string.lastIndexOf('.')+1));
    }
    private static String convertToUnderscore(String _string) {
        StringBuilder ret_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c == '.') {
                ret_.append("_");
            } else if (Character.isLowerCase(c)){
                ret_.append(Character.toUpperCase(c));
            } else {
                ret_.append("_");
                ret_.append(c);
            }
        }
        return ret_.toString();
    }
//    @Ignore
//    @Test
//    public void confCardsTest() {
//        String absolute = System.getProperty("absolute");
//        if (absolute == null) {
//            Assert.fail("no input use -Dabsolute='mydir'");
//        }
//        String resPk;
//        String web;
//        String conf;
//        web = absolute+"resources_cards/";
//        resPk = "";
//        conf = "conf/rules_tarot.xml";
//        testOneFile(conf, web, absolute, resPk, new TarotStandards(), new RulesTarot());
//    }
//    @Ignore
//    @Test
//    public void execCards() {
//        Constants.setSystemLanguage("fr");
//        Navigation nav_ = new Navigation();
//        nav_.setLanguage("fr");
//        nav_.setDataBase(new RulesTarot());
//        nav_.loadConfiguration("resources_cards/conf/rules_tarot.xml", new TarotStandards());
//        nav_.initializeSession();
//        System.out.println(nav_.getHtmlText());
//    }
    public static void init(Configuration _conf, boolean _cust) {
        _conf.setHtmlPage(new HtmlPage());
        _conf.setDocument(null);
        _conf.setCurrentUrl(_conf.getFirstUrl());
        if (_conf.getPrefix() == null) {
            _conf.setPrefix("");
        } else {
            _conf.setPrefix(StringList.concat(_conf.getPrefix(),":"));
        }
        if (_conf.getLateValidators() == null) {
            _conf.setLateValidators(new StringMap<String>());
        }
        if (_conf.getLateTranslators() == null) {
            _conf.setLateTranslators(new StringMap<String>());
        }
        _conf.getStandards().build();
        if (_cust) {
            _conf.getStandards().setContext(_conf.getContext());
            _conf.getContext().setStandards(_conf.getStandards());
            _conf.getStandards().setupOverrides(_conf.getContext());
        }
    }
//    private static void test(String conf, String web, String webtwo, String resPk) {
//        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
//        Configuration conf_ = (Configuration) SerializeXmlObject.fromXmlStringObject(contentConf_);
//        init(conf_, false);
//        Navigation nav = new Navigation();
//        nav.setSession(conf_);
//        conf_.setupClasses(new StringMap<String>());
//        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
//            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", null, e.getValue(), true));
//        }
//        for (String f: StreamTextFile.allSortedFiles(web)) {
//            if (!f.endsWith(".html")) {
//                continue;
//            }
////            System.out.println("\t"+f);
//            String folder_ = f.substring(0, webtwo.length());
//            processOneFile(conf_, folder_, f.substring(webtwo.length()), conf, web, webtwo, resPk, conf_.getStandards());
////            String currentUrl_ = f.substring(webtwo.length());
////            String text_ = ExtractFromResources.loadPage(conf_, new StringMap<String>(), currentUrl_, resPk);
////            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(text_);
////            Document doc_ = res_.getDocument();
////            if (doc_ == null) {
////                System.err.println(res_.getLocation());
////                continue;
////            }
////            conf_.setDocument(doc_);
////            conf_.addPage(new ImportingPage(true));
////            conf_.getLastPage().setPrefix(conf_.getPrefix());
////            String currentBeanName_ = doc_.getDocumentElement().getAttribute(conf_.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
////            conf_.getLastPage().setBeanName(currentBeanName_);
////            FormatHtmlLookFor.checkSyntax(conf_, false, doc_, text_, "fr", resPk);
////            FormatHtmlLookFor.checkSyntax(conf_, false, doc_, text_, "en", resPk);
////            conf_.removeLastPage();
//        }
//    }
    @SuppressWarnings("unused")
    private static void testOneFile(String conf, String web, String webtwo, String resPk, BeanLgNames _stds, Object _db) {
        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
        Configuration conf_ = new Configuration();
        if (_stds == null) {
            conf_.setStandards(new BeanLgNames());
            ContextEl context_ = new ContextEl();
            DefaultInitialization.basicStandards(conf_.getStandards());
            context_.setStandards(conf_.getStandards());
            conf_.getStandards().setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            conf_.getStandards().setContext(context_);
            conf_.setContext(context_);
            context_.setAccessValue(conf_.getAccessValue());
            context_.setStandards(conf_.getStandards());
            init(conf_,false);
        } else {
            conf_.setStandards(_stds);
            ContextEl context_ = new ContextEl();
            context_.setClasses(new Classes());
            conf_.setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            init(conf_,true);
        }
        Navigation nav = new Navigation();
        nav.setSession(conf_);
        conf_.setupClasses(new StringMap<String>());
        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", _db, e.getValue(), true));
        }
        String currentUrl_ = conf_.getFirstUrl();
        processOneFile(conf_, "", currentUrl_, conf, web, webtwo, resPk, _stds);
    }
    private static void test(String conf, String web, String webtwo, String resPk, BeanLgNames _stds, Object _db) {
        String contentConf_ = StreamTextFile.contentsOfFile(web+conf);
        Configuration conf_ = new Configuration();
        if (_stds == null) {
            conf_.setStandards(new BeanLgNames());
            ContextEl context_ = new ContextEl();
            DefaultInitialization.basicStandards(conf_.getStandards());
            context_.setStandards(conf_.getStandards());
            conf_.getStandards().setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            conf_.getStandards().setContext(context_);
            conf_.setContext(context_);
            context_.setAccessValue(conf_.getAccessValue());
            context_.setStandards(conf_.getStandards());
            init(conf_,false);
        } else {
            conf_.setStandards(_stds);
            ContextEl context_ = new ContextEl();
            context_.setClasses(new Classes());
            conf_.setContext(context_);
            ReadConfiguration.load(conf_, DocumentBuilder.parseSax(contentConf_));
            init(conf_,true);
        }
        Navigation nav = new Navigation();
        nav.setSession(conf_);
        conf_.setupClasses(new StringMap<String>());
        for (EntryCust<String, Bean> e: conf_.getBeans().entryList()) {
            conf_.getBuiltBeans().put(e.getKey(), conf_.newBean("fr", _db, e.getValue(), true));
        }
        for (String f: StreamTextFile.allSortedFiles(web)) {
            if (!f.endsWith(".html")) {
                continue;
            }
            String folder_ = f.substring(0, webtwo.length());
            processOneFile(conf_, folder_, f.substring(webtwo.length()), conf, web, webtwo, resPk, _stds);
        }
    }
    private static void processOneFile(Configuration conf_, String _folder,String _url, String conf, String web, String webtwo, String resPk, BeanLgNames _stds) {
        System.out.println("\t"+_folder+_url);
        String currentUrl_ = _url;
        String text_ = ExtractFromResources.loadPage(conf_, new StringMap<String>(), currentUrl_, resPk);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(text_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            Assert.fail(res_.getLocation().display());
        }
        conf_.setDocument(doc_);
        conf_.addPage(new ImportingPage(true));
        conf_.getLastPage().setPrefix(conf_.getPrefix());
        String currentBeanName_ = doc_.getDocumentElement().getAttribute(conf_.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
        conf_.getLastPage().setBeanName(currentBeanName_);
        FormatHtmlLookFor.checkSyntax(conf_, _stds != null, doc_, text_, "fr", resPk);
        FormatHtmlLookFor.checkSyntax(conf_, _stds != null, doc_, text_, "en", resPk);
        conf_.removeLastPage();
    }
}
