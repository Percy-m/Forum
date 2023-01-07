# Forum

企业论坛系统

项目结构：
```
|—— src/main
    |—— java/com/enterprise/forum
        |—— annotation 注解配置
        |—— aspect AOP
        |—— component 组件类
        |—— config 应用配置
        |—— constant 常量配置
        |—— controller 控制器
        |—— domain 数据库实体
        |—— dto 数据传输对象
        |—— exception 自定义异常类
        |—— repository 数据库访问层
        |—— service 服务层
        |—— utils 工具类
        |—— vo 视图对象
        ForumApplication.java
    |—— resources
        |—— static 静态文件
        |—— templates 模板文件
        application.yml 主配置文件
        application-template.yml 配置模板
        data.sql 建表
        schema.sql 插入数据
```

注：启动项目前，请在<code>resources</code>文件夹下新建<code>application-develop.yml</code>文件，将<code>application-template.yml</code>的内容复制到<code>application-develop.yml</code>下，并作适当修改。
