# springboot-mybatis-multi-datasource

#### 介绍
springboot整合mybatis多个数据源
这里一个MySQL、一个postgreSQL
需要注意的2点
1. 在application.yml文件里配置mybatis.configuration是无效的，要在每个数据源的配置类里单独配置
2. 每个数据源的配置类里还要添加对应mapper.xml文件的位置，否则mapper无法绑定会报错
