### 配置

- 先在本机建立 spring_ant_basic 数据库, 再执行 database 中的结构脚本
- 需要修改数据库连接字符串的地方:application.yaml, generatorConfig.xml
- 修改 generatorConfig.xml 中 classPathEntry location 指向自己的 jdbc connector
- 端口号:10002
- 接口测试页面: http://localhost:10002/serverapi/swagger-ui.html
- spring-ant-basic-client 安装包:npm install 执行:npm start 默认端口:8000
- 默认账号密码 administrator 000000

### 工具

- intellij idea: java ide https://www.jetbrains.com/idea/download , 激活:http://www.52pojie.cn/thread-832601-1-1.html 使用:https://www.imsxm.com/2018/07/idea-2018-1-5-crack-patcher.html
- webstorm: 前端 ide https://www.jetbrains.com/webstorm/download 激活同上
- postman: https://www.getpostman.com/
- dbforge for mysql: 数据库工具 http://52pojie.cn/thread-772733-1-1.htm
- git工具: sourcetree, gitkraken
- idea 插件: lombok, Free MyBatis plugin

### 相关网站
- ant design: https://ant.design/docs/react/introduce-cn
- ant design pro: https://pro.ant.design/index-cn
- umi: https://umijs.org
- dva: https://dvajs.com
- MyBatis Generator:http://www.mybatis.org/generator/index.html mybatis 生成器
- swagger https://swagger.io/ api测试框架


### 前端环境
- nodejs 
- yarn https://yarnpkg.com/zh-Hans/ 前端包管理
- yarn 换成淘宝源 https://zhuanlan.zhihu.com/p/35856841
- 项目目录中执行 yarn install 安装包
- 项目目录中执行 npm start 启动前端程序
- 需要了解 react 前端框架 ant.design 基于react的ui库 redux,dva 前端数据流
