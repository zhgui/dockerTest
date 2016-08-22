package com.shark.common.query;

import com.shark.common.utils.ReflectUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Method;
import java.util.List;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午5:09
 */
public class AliasToBeanResultTransformerIgnorCase implements ResultTransformer {

    private final Class resultClass;
    private Setter[] setters;
    private PropertyAccessor propertyAccessor;

    public AliasToBeanResultTransformerIgnorCase(Class resultClass) {
        if (resultClass == null) throw new IllegalArgumentException("resultClass cannot be null");
        this.resultClass = resultClass;
        propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[]{PropertyAccessorFactory.getPropertyAccessor(resultClass, null), PropertyAccessorFactory.getPropertyAccessor("field")});
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        Object result;

        try {
            if (setters == null) {
                setters = new Setter[aliases.length];
                for (int i = 0; i < aliases.length; i++) {
                    String alias = aliases[i];
                    //System.out.println("alias='" + alias + "' ");
                    if (alias != null) {
                        String newAlias = ReflectUtils.findPropertyName(resultClass, alias);
                        if (newAlias != null) setters[i] = propertyAccessor.getSetter(resultClass, newAlias);
                    }
                }
            }
            result = resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if (setters[i] != null) {
                    //System.out.println("setters[" + i + "] = " + setters[i]);
                    if (tuple[i] != null) {
                        Method method = setters[i].getMethod();
                        if (method != null) {
                            //System.out.println("method='" + method + "' ");
                            //System.out.println(" >>>>>>>>>>>>>>>> 0" + method);
                            Class[] clzs = method.getParameterTypes();
                            //System.out.println(" >>>>>>>>>>>>>>>> 1");
                            Class destType = clzs[0];
                            //System.out.println(" >>>>>>>>>>>>>>>> 2");
                            //System.out.println("tuple[i]='" + tuple[i] + "' ");
                            Object value = ConvertUtils.lookup(destType).convert(destType, tuple[i]);
                            //System.out.println(" >>>>>>>>>>>>>>>> 3");
                            //System.out.println("value='" + value + "' ");
                            setters[i].set(result, value, null);
                            //System.out.println(" >>>>>>>>>>>>>>>> 4");
                        }
                    } else {
                        setters[i].set(result, null, null);
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }

        return result;
    }

    public List transformList(List collection) {
        return collection;
    }
}
