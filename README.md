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

- admin 页面启动

```
cd fx-blog-admin
npm install
npm start
```

自动切换端口，访问localhost:8001 即可。

## 功能

- 标签
- 分类
- 代码高亮
- md 编辑器
- 自定义导航
- 评论（畅言）
- 分享（百度分享）
- 访问量
- 点赞

待实现

- 自定义主题
- 图床
- 粘贴上传图片
- 自定义页面
- 打赏
- seo
- 广告
- 反爬虫优化
- 搜索
- and so on。。


目前正在开发阶段，还有很多问题没有解决，不建议现在在本地运行，近期会发布第一版。
