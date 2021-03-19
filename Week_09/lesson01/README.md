# 作业讲解
1. 将服务端写死查找接口实现类变成泛型和反射
   1. 将RpcfxResolver#resolve参数改为Class<?>
   2. 修改获取Bean的方式为getBean(Class<T>)
2. 将客户端动态代理改成AOP，添加异常处理
   1. 增加注解@Reference标注在需要调用的远程目标接口
   2. 添加AOP切面，扫描comsumer中的consumer包下的类
   3. 当请求TestController#test时，获取到当前类的标注了@Reference的属性，取出注解中的url属性，判断该属性是否为空，不为空则创建该属性的代理类RpcfxInvocationHandler，并赋值
3. 使用Netty+HTTP作为client端传输方式
   1. 创建rpcx-netty工程作为netty server供consumer调用
   
# 可优化点
作业2的AOP需将切面定义在consumer端，不太合理，可更改为自定义BeanPostProcessor在Bean创建后进行对标注了@Reference的属性生成代理类并赋值
