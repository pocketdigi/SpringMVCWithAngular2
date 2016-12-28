### SpringMVC+MyBatis+FreeMarker Demo
#####模块化分离代码

- biz:Service 业务逻辑层
- client:对外的接口定义
- dal:数据持久化层
- web:控制器层
- common:一些公共类


####用到的框架：

1. SpringMVC
2. Mybatis
3. FreeMarker
4. log4j+slf4j
5. Gson

####使用方法
1. 全局搜索 `${project.groupId}`,`${project.artifactId}`,`${project.version}`替换项目的groupId,artifactId，version。
如果直接使用表达式，打包会有警告，但也不会报错。
2. 更改包名。在web/src/main/java/com.pocketdigi.demo上按shift+F6(快捷键可能不一样，右键-Refactor-rename也是可以的),选择`Rename Package`
3. git remote remove origin
4. git remote add origin xxxxx
