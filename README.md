Android开发说明

1. 请定义安卓开发规范
    参照阿里巴巴Android开发手册

2. 请将依赖放置统一目录
    公共依赖在moudle yin中的build.gradle中

3. 定义命名规范
    代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。正确的英文拼写和语法可以让阅读者易于理解，避免歧义。
    类名都以 UpperCamelCase 风格编写。
    方法名都以 lowerCamelCase 风格编写。
    常量名命名模式为 CONSTANT_CASE，全部字母大写，用下划线分隔单词。
    非常量字段名以 lowerCamelCase 风格为基础
    非公有，非静态字段命名以 m 开头。
    静态字段命名以 s 开头。
    其他字段以小写字母开头。

4. 定义注释规范
    每个类完成后应该有作者姓名和联系方式的注释
    每一个成员方法（包括自定义成员方法、覆盖方法、属性方法）的方法头都必须做方法头注释
    块注释与其周围的代码在同一缩进级别。它们可以是 /* ... */ 风格，也可以是 // ... 风格（//后最好带一个空格）。对于多行的 /* ... */ 注释，后续行必须从 * 开始， 并且与前一行的 * 对齐。

5. 日志输出规范
6. 功能测试规范以及性能测试规范
7. 配置文件管理规范
8. 请对项目目录结构进行注释说明
9. 注释请用中文注释


key_store在项目根目录中  打包密码 jinniu123456   alias  jinniu