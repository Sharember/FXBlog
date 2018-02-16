# FXBlog
springboot + react 搭建的博客系统，coding.....

开发进度参考 dev 分支

具体任务请点击： https://github.com/CFshuming/FXBlog/projects/1


# 启动方式

- 后端启动：

后端基于 springboot、java8、mongodb，确保运行环境为 java8，并且启动了 mongodb（端口号为默认的端口号 27017）

```
cd fx-blog-server
mvn clean install
mvn spring-boot:run // 或者使用 idea 打开，运行 FxBlogServerApplication.main() 方法
```

- 前端启动：

```
cd fx-blog-show
npm install
npm start
```

访问：localhost:8000 即可