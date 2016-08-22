/**
 *
 */
package com.shark.common.utils;

import com.shark.common.exception.SysException;
import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;

/**
 * <p>Date: 13-2-23 下午1:25
 * <p>Version: 1.0
 */
public class ReflectUtils {
    protected static Logger logger = LoggerFactory.getLogger(ReflectUtils.class.getName());

    /**
     * 得到指定类型的指定位置的泛型实参
     *
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type parameterizedType = clazz.getGenericSuperclass();
        //CGLUB subclass target object(泛型在父类上)
        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }
        if (!(parameterizedType instanceof ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }
        return (Class<T>) actualTypeArguments[0];
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
     * 如无法找到, 返回Object.class.
     * eg.
     * public UserDao extends HibernateDao<User>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be determined
     */
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
     * 如无法找到, 返回Object.class.
     * <p/>
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * <pre>
     * 根据给出的名字, 获得对象指定的属性的值.
     * 并且转换为String类型.
     * 不通过getter和setter方法
     * 无视private
     * </pre>
     *
     * @param orig
     * @param fieldName
     * @param p_bIgnoreCase
     * @return
     * @throws IllegalAccessException
     */
    public static String getFieldByName(Object orig, String fieldName, boolean p_bIgnoreCase) throws IllegalAccessException {
        Field[] origFields = getDeclaredFieldsForClass(orig.getClass());
        String fieldNameToFind = fieldName;
        if (p_bIgnoreCase) {
            fieldNameToFind = fieldName.toUpperCase();
        }
        Object objValue = null;
        String name;
        for (int i = 0; i < origFields.length; i++) {
            Field origField = origFields[i];
            name = origField.getName();
            if (p_bIgnoreCase) {
                name = name.toUpperCase();
            }
            if (name.equals(fieldNameToFind)) {
                origField.setAccessible(true);
                objValue = origField.get(orig);
                origField.setAccessible(false);
                break;
            }
        }
        if (objValue != null) {
            return ConvertUtils.convert(objValue);
        } else {
            return null;
        }
    }

    public static void setFieldByName(Object orig, String fieldName, String value, boolean p_bIgnoreCase) throws IllegalAccessException {
        Field[] origFields = getDeclaredFieldsForClass(orig.getClass());
        String fieldNameToFind = fieldName;
        if (p_bIgnoreCase) {
            fieldNameToFind = fieldName.toUpperCase();
        }
        boolean found = false;
        String name;
        for (int i = 0; i < origFields.length; i++) {
            Field origField = origFields[i];
            name = origField.getName();
            if (p_bIgnoreCase) {
                name = name.toUpperCase();
            }
            if (name.equals(fieldNameToFind)) {
                origField.setAccessible(true);
                origField.set(orig, value);
                origField.setAccessible(false);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Field not found. fieldName ='" + StringUtils.trimString(fieldName, "null") + "'");
        }
    }

    /**
     * 复制对象,可控制是否复制空值属性
     *
     * @param objFrom      Object 源对象
     * @param objTo        Object 目标对象
     * @param bIncludeNull boolean 是否复制空值
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static void copyAllPropertiesByName(Object objFrom, Object objTo, boolean bIncludeNull) {
        try {
            if (bIncludeNull) {
                PropertyUtils.copyProperties(objTo, objFrom);
            } else {
                copyProperties(objTo, objFrom, bIncludeNull);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SysException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new SysException(e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new SysException(e.getMessage());
        }
    }

    /**
     * <pre>
     * 复制两个javabean的值，或者一个map到一个javabean的值
     *
     *
     *
     * 自动进行类型转换
     * 不通过getter和setter方法
     * 而且无视private
     * </pre>
     *
     * @param objFrom
     * @param objTo
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void copyFieldsByName(Object objFrom, Object objTo) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        copyFields(objTo, objFrom);
    }

    /**
     * 复制源对象指定属性的属性值到目标对象对应的属性中
     *
     * @param objFrom    Object 源对象
     * @param objTo      Object 目标对象
     * @param sFieldName String 属性名称
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void copyPropertyByName(Object objFrom, Object objTo, String sFieldName) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        BeanUtils.copyProperty(objTo, sFieldName, BeanUtils.getProperty(objFrom, sFieldName));
    }

    /**
     * 转换某对象的属性名和属性值到哈希表中保存<p>
     * 例:<p>
     * class object<br>
     * {<br>
     * int field1=value1;<br>
     * String field2=value2;<br>
     * }<p>
     * <p/>
     * 结果:<br>
     * <table border=1>
     * <tr><td>key</td><td>val</td></tr>
     * <tr><td>field1</td><td>value1</td></tr>
     * <tr><td>field2</td><td>value2</td></tr>
     * </table>
     *
     * @param bean Object 对象
     * @return Map 哈希表
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static Map describe(Object bean) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return BeanUtils.describe(bean);
    }

    /*
      /**
      * 获取除传入对象的的某一指定属性(包括"公有","保护"和"私有",不包括自继承父类的)
      * @param objToListFields Object 对象
      * @param sFieldName String 属性名称



      * @return Field 属性对象



      * @throws NoSuchFieldException
      **
      private static Field getField(Object objToListFields, String sFieldName) throws NoSuchFieldException {
      return objToListFields.getClass().getDeclaredField(sFieldName);
      }
      */

    /**
     * Output the bean's all properties to log file.
     *
     * @param bean
     * @throws IllegalAccessException
     */
    public static void dumpBean(Object bean) throws IllegalAccessException {
        //String gdbName = bean.getClass().getName();
        Map map = ReflectUtils.instanceFieldsToMap(bean, false);
        Iterator itor = map.keySet().iterator();
        logger.debug(" * ##### Dump Bean ##### * ");
        while (itor.hasNext()) {
            String sKey = (String) itor.next();
            logger.debug(" * Key : " + sKey + " " + map.get(sKey) + " * ");
        }
    }

    private static Field findField(Class objClz, String sFieldName) {
        Field fieldToRet = null;
        Map<String, Field> fieldmap = getDeclaredFieldsForClassInMap(objClz);
        Set<String> fieldnames = fieldmap.keySet();
        for (String fieldName : fieldnames) {
            if (fieldName.equalsIgnoreCase(sFieldName)) {
                fieldToRet = fieldmap.get(fieldName);
                break;
            }
        }
        return fieldToRet;
    }


    /**
     * 获取对象某一属性的真实属性名
     * 找不到的时候返回null
     *
     * @param objClz     Object 对象的类
     * @param sFieldName String 属性名
     * @return String 真实的属性名
     */
    public static String findFieldName(Class objClz, String sFieldName) {
        String nameToRet = null;
        Field[] fields = objClz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (fieldName.equalsIgnoreCase(sFieldName)) {
                nameToRet = fieldName;
                break;
            }
        }
        return nameToRet;
    }

    /**
     * 获取对象某一属性的真实属性名
     * 找不到返回null
     *
     * @param objClz     Object 对象的类
     * @param sFieldName String 属性名
     * @return String 真实的属性名
     */
    public static String findPropertyName(Class objClz, String sFieldName) {
        //Field[] fields = objClz.getDeclaredFields();
        Field[] fields = getDeclaredFields(objClz);
        String sToRet = null;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (fieldName.equalsIgnoreCase(sFieldName)) {
                sToRet = fieldName;
                break;
            }
        }
        return sToRet;
    }

    public static Field[] getDeclaredFields(Class objClz) {
        ArrayList fields = new ArrayList();
        Class curClz = objClz;
        Collections.addAll(fields, curClz.getDeclaredFields());
        while (curClz.getSuperclass() != Object.class) {
            curClz = curClz.getSuperclass();
            Collections.addAll(fields, curClz.getDeclaredFields());
        }
        return (Field[]) fields.toArray(new Field[fields.size()]);
    }

    /**
     * 执行指定Class的无参数构造函数，返回相应的实例。
     *
     * @param clz
     * @return 指定Class的实例
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    public static Object getInstance(Class clz) throws
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor c = clz.getDeclaredConstructor();
        c.setAccessible(true);
        return c.newInstance();
    }

    /**
     * 获取对象指定属性的值
     *
     * @param objFrom     Object 对象
     * @param sFieldName  String 属性名
     * @param bIgnoreCase boolean 是否忽略大小写
     * @return String fieldValue.属性值
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static String getPropertyByName(Object objFrom, String sFieldName, boolean bIgnoreCase) {
        if (bIgnoreCase) {
            sFieldName = findPropertyName(objFrom.getClass(), sFieldName);

        }
        try {
            return BeanUtils.getProperty(objFrom, sFieldName == null ? "" : sFieldName);
        } catch (NoSuchMethodException e) {
            String exceptionName = e.getClass().getName();
            throw new SysException("****** " + exceptionName + " occured ******", e);
        } catch (IllegalAccessException e) {
            String exceptionName = e.getClass().getName();
            throw new SysException("****** " + exceptionName + " occured ******", e);
        } catch (InvocationTargetException e) {
            String exceptionName = e.getClass().getName();
            throw new SysException("****** " + exceptionName + " occured ******", e);
        }
    }

    /**
     * 把集合中的实例存放到Map里面，以p_fieldname指定的属性名作为Map的Key。
     *
     * @param p_fieldname
     * @param p_instCol
     * @return Map
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static Map instColtoMapWithFieldKey(String p_fieldname, Collection p_instCol) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map instMap = new HashMap();
        for (Iterator iterator = p_instCol.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            Object key = getPropertyByName(o, p_fieldname, true);
            instMap.put(key, o);
        }
        return instMap;
    }

    /**
     * 把对象的属性及相应的属性值转换为哈希表,属性名作key,属性值作value,可控制是否只获取公有属性.
     *
     * @param instance                         Object 对象
     * @param bOnlyPublicAccessLevelProperties boolean 是否只获取公有属性
     * @return Map 储存属性和属性值的哈希表
     * @throws IllegalAccessException
     */
    public static Map instanceFieldsToMap(Object instance, boolean bOnlyPublicAccessLevelProperties) throws IllegalAccessException {
        Field[] fields = null;
        Map mapReturn = null;
        if (bOnlyPublicAccessLevelProperties) {
            fields = instance.getClass().getFields();
        } else {
            fields = getFields(instance);
        }
        mapReturn = new HashMap(fields.length);
        for (int i = 0; i < fields.length; i++) {
            String sFieldName = null;
            Object fieldValue = null;
            Field field = fields[i];
            field.setAccessible(true);

            sFieldName = field.getName();
            fieldValue = field.get(instance);
            //System.out.println(sFieldName + " : " + fieldValue);
            mapReturn.put(sFieldName, fieldValue);
        }
        return mapReturn;
    }

    /**
     * 把哈希表中对应于对象的关键字,在哈希表中分别对应的值赋给对象同名的属性,可决定是否忽略属性名称的大小写<p>
     * 例:<p>
     * class beanTo{<br>
     * int field1;<br>
     * String field2;<br>
     * }<p>
     * Map mapForm:<br>
     * <table border=1><tr><td>key</td><td>value</td></tr><tr><td>field1</td><td>value1</td></tr><tr><td>field2</td><td>value2</td></tr></table><p>
     * bollean ignoreCase=true<p>
     * 结果:<br>
     * beanTo.getField1():value1<br>
     * beanTo.getField2();value2<br>
     *
     * @param beanTo     Object 待赋值对象
     * @param mapFrom    Map 保存着值的哈希表,key:field name val:field value
     * @param ignoreCase boolean 是否忽略大小写
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void populate(Object beanTo, Map mapFrom, boolean ignoreCase) throws IllegalAccessException, InvocationTargetException {
        // Do nothing unless both arguments have been specified
        if ((beanTo == null) || (mapFrom == null)) {
            return;
        }

        if (ignoreCase)//忽略大小写的情况
        {
            Iterator names = mapFrom.keySet().iterator();//传入的mapFrom表的迭代对象 key:field name val:field value
            Field[] fields = beanTo.getClass().getDeclaredFields();//取出当前对象的Field对象数组
            Map mapFieldName = new HashMap(fields.length);
            for (int i = 0; i < fields.length; i++)//生成属性名称哈希表的值


            {
                Field field = fields[i];
                String name = field.getName();
                mapFieldName.put(name.toLowerCase(), name);
            }

            while (names.hasNext())//循环传入的mapFrom内容
            {
                // Identify the property name and value(s) to be assigned
                String name = (String) names.next();
                if (name == null) {
                    continue;
                } else if (mapFieldName.get(name.toLowerCase()) == null)//检查是否在刚生成的哈希表中存在此关键值,即检查传入的对象中是否具有相同名字的属性,忽略大小写


                {
                    continue;
                }
                Object value = mapFrom.get(name);

                BeanUtils.setProperty(beanTo, mapFieldName.get(name.toLowerCase()).toString(), value);//beanTo 为一对象,通常为vo object;第二个参数为beanTo对象的属性名,第三个参数为要赋给beanTo第二参数指定的属性名的属性值


            }
            mapFieldName.clear();
        } else {
            BeanUtils.populate(beanTo, mapFrom);//用内部类操作
        }
    }

    /**
     * 为对象指定的属性赋值,可指定是否忽略属性名的大小写
     *
     * @param objTo       Object 对象
     * @param sFieldName  String 属性名称
     * @param value       Object 值
     * @param bIgnoreCase boolean 是否忽略大小写
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void setPropertyByName(Object objTo, String sFieldName, Object value, boolean bIgnoreCase) throws IllegalAccessException, InvocationTargetException {
        if (bIgnoreCase) {
            sFieldName = findPropertyName(objTo.getClass(), sFieldName);
        }
        BeanUtils.copyProperty(objTo, sFieldName, value);
    }

    // -------------------------- OTHER METHODS --------------------------

    /**
     * 和ReflectUtils.describe的区别是, 这个取信息的时候, 不是根据obj参数的属性, 而是根据obj里面所定义的field来生成这个map.
     *
     * @param obj
     * @param bGetSuperClassToo 如果设置了这个参数, 那么不但会取出这个对象的field, 还会取出这个对象所继承的对象的field来构成这个map
     * @return
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws NoSuchFieldException
     */
    public static Map<String, Object> describeByFields(Object obj, boolean bGetSuperClassToo) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        if (obj == null) {
            throw new IllegalArgumentException("No obj specified");
        }
        Class classToView = obj.getClass();
        return describeByFields(obj, classToView, bGetSuperClassToo);
    }

    private static Map<String, Object> describeByFields(Object obj, Class p_classToView, boolean bGetSuperClassToo) throws IllegalAccessException {
        Map<String, Object> toReturn = new HashMap<String, Object>();
        if (bGetSuperClassToo) {
            Class superclz = p_classToView.getSuperclass();
            if (superclz != Object.class) {
                toReturn.putAll(describeByFields(obj, superclz, bGetSuperClassToo));
            }
        }
        Field[] origFields = p_classToView.getDeclaredFields();
        for (Field origField : origFields) {
            String name = origField.getName();
            origField.setAccessible(true);
            toReturn.put(name, origField.get(obj));
        }
        return toReturn;
    }

    /**
     * 复制对象,可控制是否复制空值属性
     *
     * @param dest Object 目标对象
     * @param orig Object 源对象
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    private static void copyFields(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        // Copy the properties, converting as necessary
        if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                System.out.println("------->name:" + name);
                Field destFld = findField(dest.getClass(), name);
                //String fieldname = findFieldName(dest.getClass(), name);
                if (destFld != null) {
                    //Field destFld = dest.getClass().getDeclaredField(fieldname);
                    destFld.setAccessible(true);
                    Class destType = destFld.getType();
                    destFld.set(dest, ConvertUtils.lookup(destType).convert(destType, ((Map) orig).get(name)));
                    //destFld.set(dest, ((Map) orig).get(name));
                    //destFld.setAccessible(false);
                }
            }
        } else
            /* if (orig is a standard JavaBean) */ {
            //Field[] origFields = orig.getClass().getDeclaredFields();
            Field[] origFields = getDeclaredFieldsForClass(orig.getClass());
            for (int i = 0; i < origFields.length; i++) {
                Field origField = origFields[i];
                String name = origField.getName();
                //String fieldname = findFieldName(dest.getClass(), name);
                Field destFld = findField(dest.getClass(), name);
                if (destFld != null) {
                    //Field destFld = dest.getClass().getDeclaredField(fieldname);
                    destFld.setAccessible(true);
                    origField.setAccessible(true);
                    Class destType = destFld.getType();
                    destFld.set(dest, ConvertUtils.lookup(destType).convert(destType, origField.get(orig)));
                    //destFld.setAccessible(false);
                    //origField.setAccessible(false);
                }
            }
        }
    }

    /**
     * 返回指定的类的声明了的字段, 包括其父类所声明的字段, 但是不包含object所声明的字段
     *
     * @param clz
     * @return
     */
    private static Field[] getDeclaredFieldsForClass(Class clz) {
        if (clz == Object.class) {
            return new Field[0];
        } else {
            ArrayList<Field> fieldlist = new ArrayList<Field>();
            fieldlist.addAll(Arrays.asList(clz.getDeclaredFields()));
            Field[] fieldsOfSuperClz = getDeclaredFieldsForClass(clz.getSuperclass());
            if (fieldsOfSuperClz != null) {
                fieldlist.addAll(Arrays.asList(fieldsOfSuperClz));
            }
            return fieldlist.toArray(new Field[0]);
        }
    }

    /**
     * 返回指定的类的声明了的字段, 包括其父类所声明的字段, 但是不包含object所声明的字段
     * <p/>
     * <p/>
     * <p/>
     * Map的key是字段名, 大小写区分, Map的value是Field对象
     *
     * @param clz
     * @return
     */
    private static Map<String, Field> getDeclaredFieldsForClassInMap(Class clz) {
        // todo add a cache to here.
        if (clz == Object.class) {
            return new HashMap<String, Field>();
        } else {
            Map<String, Field> fieldmap = new HashMap<String, Field>();
            ArrayList<Field> fieldlist = new ArrayList<Field>();
            fieldlist.addAll(Arrays.asList(clz.getDeclaredFields()));
            Field[] fieldsOfSuperClz = getDeclaredFieldsForClass(clz.getSuperclass());
            if (fieldsOfSuperClz != null) {
                fieldlist.addAll(Arrays.asList(fieldsOfSuperClz));
            }
            for (Field field : fieldlist) {
                fieldmap.put(field.getName(), field);
            }
            return fieldmap;
        }
    }

    /**
     * 复制对象,可控制是否复制空值属性
     *
     * @param dest         Object 目标对象
     * @param orig         Object 源对象
     * @param bIncludeNull boolean 是否复制空值
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    private static void copyProperties(Object dest, Object orig, boolean bIncludeNull) throws IllegalAccessException, InvocationTargetException {
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    if (bIncludeNull || value != null) {
                        BeanUtils.copyProperty(dest, name, value);
                    }
                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((Map) orig).get(name);
                    if (bIncludeNull || value != null) {
                        BeanUtils.copyProperty(dest, name, value);
                    }
                }
            }
        } else
            /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue;// No point in trying to set an object's class
                }
                if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
                    try {
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        if (bIncludeNull || value != null) {
                            BeanUtils.copyProperty(dest, name, value);
                        }
                    } catch (NoSuchMethodException ex) {
                        ;// Should not happen
                    }
                }
            }
        }
    }

    /**
     * 获取除传入对象的所有属性(包括"公有","保护"和"私有",不包括自继承父类的)
     *
     * @param objToListFields Object 对象
     * @return Field[] 属性对象数组
     */
    private static Field[] getFields(Object objToListFields) {
        // after test, use cache is not faster than no cache. JDK1.4.1
        return objToListFields.getClass().getDeclaredFields();
    }

    /**
     * 将MAP，转换为指定的对象
     *
     * @param clazz
     * @param valMap
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T mapToPOInstance(Class clazz, Map valMap) throws Exception {
        if (clazz == null) {
            return null;
        }
        if (valMap == null) {
            return (T) clazz.newInstance();
        }
        Iterator<String> it = valMap.keySet().iterator();
        Map<String, Object> copyMap = new HashMap<String, Object>();
        while (it.hasNext()) {
            String key = it.next();
            String newKey = key.toLowerCase();
            copyMap.put(newKey, valMap.get(key));
        }
        Field[] fields = clazz.getDeclaredFields();
        Object obj = null;
        obj = clazz.newInstance();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object valObj = copyMap.get(fieldName.toLowerCase());
            if (valObj != null && !"".equals(valObj.toString())) {
                //Class fieldClass=field.getClass();
                Class fieldClass = field.getType();
                if (fieldClass.getName().startsWith("java.")) {
                    if (fieldClass == java.util.Date.class) {
                        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        valObj = df.parse(valObj.toString());
                    }
                    BeanUtils.setProperty(obj, fieldName, valObj);
                }
            }
        }
        return (T) obj;
    }
}
