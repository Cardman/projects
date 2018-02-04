package code.formathtml;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cards.tarot.RulesTarot;
import cards.tarot.beans.TarotStandards;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustElUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.maths.LgInt;
import code.maths.Rate;
import code.serialize.ConstClasses;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.stream.StreamTextFile;
import code.util.BooleanList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

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
        packages_.removeDuplicates();
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
            body_.append("import code.util.StringMap;\n\n");
            body_.append("public final class ");
            body_.append(simpleName_);
            body_.append(" {\n");
            body_.append("    public static void build(BeanLgNames _std) {\n");
            for (String c: classes_) {
                body_.append("        build");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(_std);\n");
            }
            body_.append("    }\n");
            for (String c: classes_) {
                body_.append("    private static void build");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(BeanLgNames _std) {\n");
                body_.append("        StandardClass type_;\n");
                body_.append("        StringMap<StandardField> fields_;\n");
                body_.append("        StandardField field_;\n");
                body_.append("        CustList<StandardConstructor> constructors_;\n");
                body_.append("        ObjectMap<MethodId, StandardMethod> methods_;\n");
                body_.append("        StandardMethod method_;\n");
                body_.append("        StringList params_;\n");
                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
                boolean abs_ = Modifier.isAbstract(clInfo_.getModifiers());
                String superClass_ = clInfo_.getSuperclass().getName();
                body_.append("        methods_ = new ObjectMap<MethodId, StandardMethod>();\n");
                body_.append("        constructors_ = new CustList<StandardConstructor>();\n");
                body_.append("        fields_ = new StringMap<StandardField>();\n");
                if (abs_) {
                    body_.append("        type_ = new StandardClass(\"").append(c).append("\", fields_, constructors_, methods_, \"").append(superClass_).append("\", MethodModifier.ABSTRACT);\n");
                } else {
                    body_.append("        type_ = new StandardClass(\"").append(c).append("\", fields_, constructors_, methods_, \"").append(superClass_).append("\", MethodModifier.NORMAL);\n");
                }
                for (String s: CustElUtil.GETTERS_SETTERS_FIELDS.getKeys()) {
                    String clPart_ = s.substring(0, s.lastIndexOf('.'));
                    if (!StringList.quickEq(clPart_, c)) {
                        continue;
                    }
                    String fPart_ = s.substring(s.lastIndexOf('.')+1);
                    try {
                        Field info_ = clInfo_.getDeclaredField(fPart_);
                        if (info_.getType() == int.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasPrimInteger(),false,false,type_));\n");
                        } else if (info_.getType() == long.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasPrimLong(),false,false,type_));\n");
                        } else if (info_.getType() == byte.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasPrimByte(),false,false,type_));\n");
                        } else if (info_.getType() == short.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasPrimShort(),false,false,type_));\n");
                        } else if (info_.getType() == Integer.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasInteger(),false,false,type_));\n");
                        } else if (info_.getType() == Long.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasLong(),false,false,type_));\n");
                        } else if (info_.getType() == Byte.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasByte(),false,false,type_));\n");
                        } else if (info_.getType() == Short.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasShort(),false,false,type_));\n");
                        } else if (info_.getType() == String.class) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getAliasString(),false,false,type_));\n");
                        } else if (Listable.class.isAssignableFrom(info_.getType())) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getCustList(),false,false,type_));\n");
                        } else if (ListableEntries.class.isAssignableFrom(info_.getType())) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",_std.getCustMap(),false,false,type_));\n");
                        } else if (Rate.class.isAssignableFrom(info_.getType())) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",\"$Rate\",false,false,type_));\n");
                        } else if (LgInt.class.isAssignableFrom(info_.getType())) {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",\"$LgInt\",false,false,type_));\n");
                        } else {
                            body_.append("        fields_.put(\"").append(fPart_).append("\",new StandardField(\"").append(fPart_).append("\",\"").append(info_.getType().getName()).append("\",false,false,type_));\n");
                        }
                    } catch (Exception _0) {
                        _0.printStackTrace();
                    }
                }
                for (ClassMethodId e: CustElUtil.CALLS) {
                    if (!StringList.quickEq(e.getClassName(), c)) {
                        continue;
                    }
                    for (Method m: clInfo_.getDeclaredMethods()) {
                        if (!StringList.quickEq(e.getConstraints().getName(), m.getName())) {
                            continue;
                        }
                        body_.append("        params_ = new StringList(");
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
                        body_.append(params_.join(","));
                        body_.append(");\n");
                        body_.append("        method_ = new StandardMethod(\"");
                        body_.append(m.getName());
                        body_.append("\",params_,");
                        if (m.getReturnType() == int.class) {
                            body_.append("_std.getAliasPrimInteger()");
                        } else if (m.getReturnType() == long.class) {
                            body_.append("_std.getAliasPrimLong()");
                        } else if (m.getReturnType() == byte.class) {
                            body_.append("_std.getAliasPrimByte()");
                        } else if (m.getReturnType() == short.class) {
                            body_.append("_std.getAliasPrimShort()");
                        } else if (m.getReturnType() == Integer.class) {
                            body_.append("_std.getAliasInteger()");
                        } else if (m.getReturnType() == Long.class) {
                            body_.append("_std.getAliasLong()");
                        } else if (m.getReturnType() == Byte.class) {
                            body_.append("_std.getAliasByte()");
                        } else if (m.getReturnType() == Short.class) {
                            body_.append("_std.getAliasShort()");
                        } else if (m.getReturnType() == String.class) {
                            body_.append("_std.getAliasString()");
                        } else if (Listable.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("_std.getCustList()");
                        } else if (ListableEntries.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("_std.getCustMap()");
                        } else if (Rate.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("\"$Rate\"");
                        } else if (LgInt.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("\"$LgInt\"");
                        } else {
                            body_.append("\"").append(m.getReturnType().getName()).append("\"");
                        }
                        body_.append(", false, MethodModifier.NORMAL,type_);\n");
                        body_.append("        methods_.put(method_.getId(), method_);\n");
                        break;
                    }
                }
                body_.append("        _std.getStandards().put(\"").append(c).append("\", type_);\n");
                body_.append("    }\n");
            }
            for (String c: classes_) {
                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
                body_.append("    public static ResultErrorStd getResult");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(ContextEl _cont, ClassField _classField, Struct _instance) {\n");
                body_.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
                body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
                body_.append("        ");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(" instance_ = (");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(") _instance.getInstance();\n");
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
                    if (!StringList.quickEq(clPart_, c)) {
                        continue;
                    }
                    String fPart_ = key_.substring(key_.lastIndexOf('.')+1);
                    char first_ = fPart_.charAt(0);
                    String simpleNameLoc_ = clPart_.substring(clPart_.lastIndexOf('.') + 1);
                    body_.append("        if (StringList.quickEq(_classField.getFieldName(),\"").append(fPart_).append("\")) {\n");
                    body_.append("            res_.setResult(");
                    String getter_ = "get"+Character.toUpperCase(first_)+fPart_.substring(1);
                    boolean end_ = true;
                    Field info_;
                    try {
                        info_ = clInfo_.getDeclaredField(fPart_);
                        if (info_.getType() == int.class || info_.getType() == Integer.class) {
                            body_.append("new IntStruct(instance_.");
                        } else if (info_.getType() == long.class || info_.getType() == Long.class) {
                            body_.append("new LongStruct(instance_.");
                        } else if (info_.getType() == byte.class || info_.getType() == Byte.class) {
                            body_.append("new ByteStruct(instance_.");
                        } else if (info_.getType() == short.class || info_.getType() == Short.class) {
                            body_.append("new ShortStruct(instance_.");
                        } else if (info_.getType() == String.class) {
                            body_.append("new StringStruct(instance_.");
                        } else {
                            end_ = false;
                        }
                    } catch (Exception _0) {
                        info_ = null;
                        _0.printStackTrace();
                    }
                    if (end_) {
                        body_.append(getter_);
                        body_.append("()));\n");
                    } else if (Listable.class.isAssignableFrom(info_.getType())) {
                        body_.append("new StdStruct(instance_.").append(getter_).append("(),std_.getCustList()));\n");
                    } else if (ListableEntries.class.isAssignableFrom(info_.getType())) {
                        body_.append("new StdStruct(instance_.").append(getter_).append("(),std_.getCustMap()));\n");
                    } else if (Rate.class.isAssignableFrom(info_.getType())) {
                        body_.append("new StdStruct(instance_.").append(getter_).append("(),\"$Rate\"));\n");
                    } else if (LgInt.class.isAssignableFrom(info_.getType())) {
                        body_.append("new StdStruct(instance_.").append(getter_).append("(),\"$LgInt\"));\n");
                    } else {
                        body_.append("new StdStruct(instance_.").append(getter_).append("(),\"").append(info_.getType().getName()).append("\"));\n");
                    }
                    body_.append("            return res_;\n");
                    body_.append("        }\n");
                }
                body_.append("        return res_;\n");
                body_.append("    }\n");
            }
            for (String c: classes_) {
                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
                body_.append("    public static ResultErrorStd setResult");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {\n");
                body_.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
                body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
                body_.append("        ");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(" instance_ = (");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(") _instance.getInstance();\n");
                body_.append("        Object value_ = _value.getInstance();\n");
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
                    if (!StringList.quickEq(clPart_, c)) {
                        continue;
                    }
                    String fPart_ = key_.substring(key_.lastIndexOf('.')+1);
                    char first_ = fPart_.charAt(0);
                    String simpleNameLoc_ = clPart_.substring(clPart_.lastIndexOf('.') + 1);
                    body_.append("        if (StringList.quickEq(_classField.getFieldName(),\"").append(fPart_).append("\")) {\n");
                    body_.append("            instance_.");
                    String setter_ = "set"+Character.toUpperCase(first_)+fPart_.substring(1)+"(";
                    body_.append(setter_);
                    boolean end_ = true;
                    Field info_;
                    try {
                        info_ = clInfo_.getDeclaredField(fPart_);
                        if (info_.getType() == int.class || info_.getType() == Integer.class) {
                            body_.append("(Integer)");
                        } else if (info_.getType() == long.class || info_.getType() == Long.class) {
                            body_.append("(Long)");
                        } else if (info_.getType() == byte.class || info_.getType() == Byte.class) {
                            body_.append("(Byte)");
                        } else if (info_.getType() == short.class || info_.getType() == Short.class) {
                            body_.append("(Short)");
                        } else if (info_.getType() == boolean.class || info_.getType() == Boolean.class) {
                            body_.append("(Boolean)");
                        } else if (info_.getType() == String.class) {
                            body_.append("(String)");
                        } else {
                            body_.append("(");
                            body_.append(info_.getType().getName());
                            body_.append(")");
                        }
                    } catch (Exception _0) {
                        info_ = null;
                        _0.printStackTrace();
                    }
                    body_.append(" value_");
                    body_.append(");\n");
                    body_.append("            res_.setResult(NullStruct.NULL_VALUE);\n");
                    body_.append("            return res_;\n");
                    body_.append("        }\n");
                }
                body_.append("        return res_;\n");
                body_.append("    }\n");
            }
            for (String c: classes_) {
                Class<?> clInfo_ = ConstClasses.classForNameNotInit(c);
                body_.append("    public static ResultErrorStd invokeMethod");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append("(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {\n");
                body_.append("        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();\n");
                body_.append("        ");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(" instance_ = (");
                body_.append(c.substring(c.lastIndexOf('.')+1));
                body_.append(") _instance.getInstance();\n");
                body_.append("        String methodName_ = _method.getConstraints().getName();\n");
                body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
                for (ClassMethodId e: CustElUtil.CALLS) {
                    if (!StringList.quickEq(e.getClassName(), c)) {
                        continue;
                    }
                    for (Method m: clInfo_.getDeclaredMethods()) {
                        if (!StringList.quickEq(e.getConstraints().getName(), m.getName()) || e.getConstraints().getParametersTypes().size() != m.getParameterTypes().length) {
                            continue;
                        }
                        body_.append("        if (StringList.quickEq(methodName_,\"").append(m.getName()).append("\")) {\n");
                        if (m.getReturnType() == void.class) {
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
                                    pBody_.append(a.getName());
                                    pBody_.append(")");
                                }
                                pBody_.append("_args[");
                                pBody_.append(i_);
                                pBody_.append("]");
                                params_.add(pBody_.toString());
                                i_++;
                            }
                            body_.append("            instance_.").append(m.getName()).append("("+params_.join(",")+");\n");
                            body_.append("            res_.setResult(NullStruct.NULL_VALUE);\n");
                            body_.append("            return res_;\n");
                            body_.append("        }\n");
                            continue;
                        } else {
                            body_.append("            res_.setResult(");
                        }
                        boolean end_ = true;
                        try {
                            if (m.getReturnType() == int.class || m.getReturnType() == Integer.class) {
                                body_.append("new IntStruct(instance_.");
                            } else if (m.getReturnType() == long.class || m.getReturnType() == Long.class) {
                                body_.append("new LongStruct(instance_.");
                            } else if (m.getReturnType() == byte.class || m.getReturnType() == Byte.class) {
                                body_.append("new ByteStruct(instance_.");
                            } else if (m.getReturnType() == short.class || m.getReturnType() == Short.class) {
                                body_.append("new ShortStruct(instance_.");
                            } else if (m.getReturnType() == String.class) {
                                body_.append("new StringStruct(instance_.");
                            } else {
                                end_ = false;
                            }
                        } catch (Exception _0) {
                            _0.printStackTrace();
                        }
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
                                pBody_.append(a.getName());
                                pBody_.append(")");
                            }
                            pBody_.append("_args[");
                            pBody_.append(i_);
                            pBody_.append("]");
                            params_.add(pBody_.toString());
                            i_++;
                        }
                        if (end_) {
                            body_.append(m.getName());
                            body_.append("(");
                            body_.append(params_.join(","));
                            body_.append(")));\n");
                        } else if (Listable.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),std_.getCustList()));\n");
                        } else if (ListableEntries.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),std_.getCustMap()));\n");
                        } else if (Rate.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),\"$Rate\"));\n");
                        } else if (LgInt.class.isAssignableFrom(m.getReturnType())) {
                            body_.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),\"$LgInt\"));\n");
                        } else {
                            body_.append("new StdStruct(instance_.").append(m.getName()).append("("+params_.join(",")+"),\"").append(m.getReturnType()).append("\"));\n");
                        }
                        body_.append("            return res_;\n");
                        body_.append("        }\n");
                        break;
                    }
                }
                body_.append("        return res_;");
                body_.append("   }\n");
            }
            body_.append("}\n");
            StreamTextFile.saveTextFile(out+"/"+fullName_, body_.toString());
        }
        String stds_ = "PokemonStandards";
        String pkg_ = "aiki.beans";
        StringBuilder body_ = new StringBuilder();
        body_.append("package ");
        body_.append(pkg_);
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
        body_.append("import code.util.StringMap;\n\n");
        body_.append("public final class ");
        body_.append(stds_);
        body_.append(" extends BeanLgNames {\n");
        body_.append("    ");
        body_.append(stds_);
        body_.append("() {\n");
        body_.append("        setSelectedBoolean(\"sb\");\n");
        body_.append("        DefaultInitialization.basicStandards(this);\n");
        body_.append("    }\n");
        body_.append("    public void buildOther() {\n");
        body_.append("        buildBeans();\n");
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
            body_.append("        ");
            String fullName_ = p + "."+simpleName_;
            body_.append(fullName_+".build(this);\n");
        }
        body_.append("    }\n");
        body_.append("    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {\n");
        body_.append("        ResultErrorStd res_ = new ResultErrorStd();\n");
        for (String t: allTypes_) {
            
        }
        body_.append("        return res_;\n");
        body_.append("    }\n");
        body_.append("}\n");
        //add standards
    }
    
    @Ignore
    @Test
    public void confCardsTest() {
        String resPk;
        String web;
        String webtwo = "C:/Users/cardman/git/tarotbean/";
        String conf;
        web = "C:/Users/cardman/git/tarotbean/resources_cards/";
        resPk = "";
//        conf = "conf/results_belote.xml";
//        testOneFile(conf, web, webtwo, resPk, new BeloteStandards());
        conf = "conf/rules_tarot.xml";
//        conf = "conf/results_tarot.xml";
        testOneFile(conf, web, webtwo, resPk, new TarotStandards(), new RulesTarot());
        System.out.println();
        for (EntryCust<String, BooleanList> e: CustElUtil.GETTERS_SETTERS_FIELDS.entryList()) {
            if (e.getValue().size() != 1) {
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
    public void execCards() {
        Constants.setSystemLanguage("fr");
        Navigation nav_ = new Navigation();
        nav_.setLanguage("fr");
        nav_.setDataBase(new RulesTarot());
        nav_.loadConfiguration("resources_cards/conf/rules_tarot.xml", new TarotStandards());
        nav_.initializeSession();
        System.out.println(nav_.getHtmlText());
    }
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
