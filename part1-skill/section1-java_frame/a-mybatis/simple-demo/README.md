# mybatis-simple-demo
maven+mybatis简单集成
#### 配置文件
必要的结构如下图:



```xml
<transactionManager type="JDBC"/>
```
事务管理器，mybatis提供了两种事务处理方式：
1. JDBC: 将事务交由jdk的java.sql.Connection处理
2. MANAGED:让容器来管理事务的整个生命周期

```xml
<dataSource type="POOLED">
    <property name="driver" value="java.sql.Driver"/>
    <property name="url" value="jdbc:mysql://ip:3306/test"/>
    <property name="username" value="root"/>
    <property name="password" value="password"/>
</dataSource>
```
数据源，mybatis提供了三种数据源管理方式：
1. UNPOOLED: 不使用连接池，即用即连接，比较消耗资源，用于不会频繁操作数据库的情况
2. POOLED: 使用数据连接池，会先创建默认数量为10的连接数，当使用的时候直接从连接池中取，用完放回连接池，并不会关闭。
3. JNDI: 这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用,容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用

SqlSessionFactoryBuilder->SqlSessionFactory->SqlSession->