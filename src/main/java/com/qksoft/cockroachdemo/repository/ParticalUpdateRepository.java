package com.qksoft.cockroachdemo.repository;

import com.qksoft.cockroachdemo.exception.ParticalUpdateException;
import com.qksoft.cockroachdemo.util.PageParam;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于页面字段决定所需要更新字段
 */
@Repository
public class ParticalUpdateRepository {
    @Autowired
    private EntityManager entityManager;

    /**
     * 根据页面字段设置来部分更新实体
     *
     * @param entity 实体对象
     * @param <E>    实体类对应的类定义
     */
    public <E> void save(E entity) throws ParticalUpdateException {
        Assert.notNull(entity, "保存的实体不能为null");

        String pkFieldName = this.getPkFieldName(entity);
        Assert.notNull(pkFieldName, "无法获取主键字段");

        try {
            String pkFieldVal = (String) PropertyUtils.getProperty(entity, pkFieldName);

            if (pkFieldVal == null || pkFieldVal.trim().equals("")) { //新增
                PropertyUtils.setSimpleProperty(entity, pkFieldName, null);
                entityManager.persist(entity);
            } else { //修改，需对比同步数据
                List<String> pageFields = this.getPageFields(entity);

                if (pageFields == null || pageFields.isEmpty()) {
                    throw new ParticalUpdateException("实体无法和页面参数建立关联");
                }

                E dataBaseRecord = entityManager.find((Class<E>) entity.getClass(), pkFieldVal);
                String[] ignoreFields = new String[pageFields.size()];
                BeanUtils.copyProperties(dataBaseRecord, entity, pageFields.toArray(ignoreFields));

                entityManager.merge(entity);
            }
        } catch (Exception e) {
            throw new ParticalUpdateException(e.getMessage());
        }
    }

    /**
     * 获取页面上有的实体类字段
     *
     * @param entity 实体类对象
     * @param <E>    实体类对应的类定义
     * @return 页面上有的实体类字段
     */
    private <E> List<String> getPageFields(E entity) throws ParticalUpdateException {
        Class entityClass = entity.getClass();
        List<String> pageParamNames = this.getPageNames(entityClass);

        if (pageParamNames == null) {
            throw new ParticalUpdateException("实体无法和页面参数建立关联");
        }

        Field[] fields = entityClass.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        List<String> pageFields = new ArrayList<>();
        for (String paraName : pageParamNames) {
            if (fieldNames.contains(paraName)) {
                pageFields.add(paraName);
            }
        }

        return pageFields;
    }

    /**
     * 获取页面上关联的参数名
     *
     * @param entityClass 实体类对应的类定义
     * @return 页面上关联的参数名
     */
    private List<String> getPageNames(Class entityClass) {
        String entityClassName = entityClass.getSimpleName();

        List<String> pageParaNames = PageParam.get(entityClassName);

        if (pageParaNames == null) {
            pageParaNames = PageParam.get(entityClassName + "Bean");
        }

        if (pageParaNames == null) {
            pageParaNames = PageParam.get();
        }

        return pageParaNames;
    }

    /**
     * 获取主键字段名
     *
     * @param entity 实体对象
     * @param <E>    实体类对应的类定义
     * @return 主键字段名
     */
    private <E> String getPkFieldName(E entity) {
        Class entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        String pkFieldName = null;
        if (fields != null) {
            for (Field field : fields) {
                if (this.isPkField(field)) {
                    pkFieldName = field.getName();
                    break;
                }
            }
        }

        return pkFieldName;
    }

    /**
     * 判断是否为实体的主键字段
     *
     * @param field bean的字段信息
     * @return 若为实体的主键字段，则返回true
     */
    private boolean isPkField(Field field) {
        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
        boolean isPkfield = false;
        if (fieldAnnotations != null) {
            for (Annotation fieldAnnotation : fieldAnnotations) {

                if (fieldAnnotation.annotationType().getName().equals("javax.persistence.Id")) {
                    isPkfield = true;
                    break;
                }
            }
        }
        return isPkfield;
    }
}
