Hasor-RSF

介绍：
  一个轻量化的分布式服务框架，相信这类框架已经不是什么新鲜事物，可用的替代方案有很多这里就不多说了，说一下RSF解决的问题把。

1.分布式
  这个是基本功能之一，消费者会自动轮询本地可用IP地址表以减少对单一服务提供者的访问压力。

2.接口泛化
  服务提供者和服务消费者，之间不需要明确的接口定义。说白了就是提供和消费两个程序各自使用各自的接口，无需搞出一个接口jar做统一约束。RSF会自动根据方法签名来匹配调用。

3.自定义序列化
  支持自定义序列化。内置Hessian、Java两种序列化方案，默认配置 Hessian 版本为4.0。如需更换其它版本直接引入相关Jar包即可，内置的Hessian包名已经经过处理不会产生冲突。

4.同步 or 异步
  基于接口包装的服务调用是同步处理的，因为异步的话无法及时获取返回值。基于RsfClient接口调用远端服务的话，可以同步也可以异步。异步的话有CallBack也有Future。

5.热装载/热卸载
  RSF上的服务都是动态的，服务可以随时发布随时卸载。

6.多版本
  定位一个RSF服务采用Group,Name,Version三个属性。一台服务器可以同时发布同一个服务的不同版本。

7.服务拦截器
  虽说用处可能不是很大，但是对于某些想管理所有远程调用的需求来说还是必要的。比方说：服务的使用授权、还有调用统计以及本地服务调用优先等。



接下来需要解决的问题：

1.服务自动发现
  RSF不同于 dubbo 的是它没有一个服务注册中心。取而代之的是自动发现。

2.服务网关
  面对外网的RSF调用，需要一个RSF服务网关来代为转发。

3.服务监控
  可以全面实现服务的调用流程监控。等等，甚至可以操纵服务上下限。设置服务限流等策略。



目前RSF存在的问题：

1.RSF服务在调用远端服务时如果出现远端拒绝，客户端会进行重试ChooseOther响应信息（尚未支持这一功能）

2.不支持多语言客户端。这个是体力活，如果去支持的话RSF是可以做到的。因为RSF传输协议是自有的传输协议，多语言要解决的只有序列化的问题。



QQ交流群：193943114