package cc.wechat.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

/**
 * 对象常用方法工具类
 *
 * @author peiyu
 */
public final class BeanUtil {

    /**
     * 此类不需要实例化
     */
    private BeanUtil() {
    }

    /**
     * 判断对象是否为null
     *
     * @param object 需要判断的对象
     * @return 是否为null
     */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /**
     * 判断对象是否不为null
     *
     * @param object 需要判断的对象
     * @return 是否不为null
     */
    public static boolean nonNull(Object object) {
        return null != object;
    }

    /**
     * 判断对象是否为空，如果为空，直接抛出异常
     *
     * @param object       需要检查的对象
     * @param errorMessage 异常信息
     * @return 非空的对象
     */
    public static Object requireNonNull(Object object, String errorMessage) {
        if (null == object) {
            throw new NullPointerException(errorMessage);
        }
        return object;
    }
    
    /**
     * 获取该bean里的全部属性名称.
     * 
     * @param newConfigObject
     * @return
     */
    public static Set<String> getAttributeNames(Object bean) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
        Set<String> attributeNames = new HashSet<String>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (!propertyDescriptor.getName().equals("class")) {
                if (propertyDescriptor.getReadMethod() != null
                    && propertyDescriptor.getWriteMethod() != null) {
                    attributeNames.add(propertyDescriptor.getName());
                }
            }
        }
        return attributeNames;
    }
 
    /**
     * 获取该bean中，对应该属性名的属性值.
     * 
     * @param configObject
     * @param attributeName
     * @return
     */
    public static Object getAttributeVlaue(Object bean, String attributeName) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean.getClass());

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(attributeName)) {
                try {
                    if (propertyDescriptor.getReadMethod() != null) {
                        return propertyDescriptor.getReadMethod().invoke(bean);
                    }
                } catch (InvocationTargetException e) {
                	throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                	throw new RuntimeException(e);
                }
 
            }
        }
        return null;
    }
 
}
