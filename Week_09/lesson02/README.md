# 作业说明
1. 以transaction server作为入口分别调用userAAccount RPC server和userBAccount RPC server，
   通过dubbo的group将userA和userB区分开调用
2. 将userA server和userB server的本地事务作为分布式事务由hmily管理

# 问题
1. 原本是想将transaction server的事务和userAAccount RPC server、userBAccount RPC server的三个本地事务作为一个分布式事务
   但发现在transaction server的try阶段异常时，不会调用userAAccount RPC server和userBAccount RPC server的canceMethod
   
2. 由于account-api中抽象了一个对外暴露的接口，userAAccount和userBAccount分别实现此接口中的update方法，
   在transaction调用时，当userB try阶段异常时，并不会调用userA的cancelMethod方法，可能是hmily对同一个接口的同一个方法这种使用方式还不支持。
   