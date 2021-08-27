package com.lazy.framework.interceptor;

import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * 自定义 Mybatis 插件，自动设置 createTime 和 updatTime 的值。
 * 拦截 update 操作（添加和修改）
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CustomInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        Date date = new Date();
        for (Field field : declaredFields) {
            if (field.getAnnotation(IDField.class)!=null){
                if (SqlCommandType.INSERT.equals(sqlCommandType)){
                    field.setAccessible(true);
                    field.set(parameter, SnowflakeIdWorker.getId());
                }
            }

            if (field.getAnnotation(CreateTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
                    field.setAccessible(true);
                    field.set(parameter, date);
                }
            }

            if (field.getAnnotation(UpdateTime.class) != null) { // insert 或 update 语句插入 updateTime
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, date);
                }
            }

        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
