#1、-Xmx512m -Xms512m -XX:-UseAdaptiveSizePolicy 每次new的数组长度为100 * 1024 1s
## Serial串行GC
`2021-01-22T21:48:10.735+0800: [GC (Allocation Failure) 2021-01-22T21:48:10.736+0800: [DefNew: 139776K->17472K(157248K), 0.0224941 secs] 139776K->44237K(506816K), 0.0233612 secs] [Times: user=0.00 sys=0.01, real=0.02 secs]`

`
2021-01-22T21:48:11.557+0800: [Full GC (Allocation Failure) 2021-01-22T21:48:11.557+0800: [Tenured: 349562K->346195K(349568K), 0.0462681 secs] 506472K->346195K(506816K), [Metaspace: 2751K->2751K(1056768K)], 0.0469033 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
`
>生成对象次数10339次
## Parallel 并行GC
`2021-01-22T21:48:21.964+0800: [GC (Allocation Failure) [PSYoungGen: 131472K->21496K(153088K)] 131472K->41920K(502784K), 0.0074426 secs] [Times: user=0.05 sys=0.11, real=0.01 secs]`

`2021-01-22T21:48:22.204+0800: [Full GC (Ergonomics) [PSYoungGen: 24967K->0K(116736K)] [ParOldGen: 317327K->234798K(349696K)] 342295K->234798K(466432K), [Metaspace: 2751K->2751K(1056768K)], 0.0388088 secs] [Times: user=0.30 sys=0.02, real=0.04 secs]`
>生成对象次数9937

总结：对比Serial和Parallel两种GC，可看到SerialGC由于是单线程收集，垃圾回收使用的时间比ParallelGC使用的时间长，STW的时间因此也长，
      但由于堆内存设置较小，串行和并行的GC回收时间差别不大，STW时间差不多,因此生成对象次数相差不大。
     

#2、-Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy 每次new的数组长度为100 * 1024 1s
## Serial串行GC
`2021-01-22T22:28:13.294+0800: [GC (Allocation Failure) 2021-01-22T22:28:13.295+0800: [DefNew: 279616K->34943K(314560K), 0.0395518 secs] 279616K->78124K(1013632K), 0.0401789 secs] [Times: user=0.00 sys=0.03, real=0.04 secs]`
>生成对象次数12793

## Parallel 并行GC
`2021-01-22T22:29:10.347+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43509K(305664K)] 262144K->82537K(1005056K), 0.0122485 secs] [Times: user=0.06 sys=0.09, real=0.01 secs]`
>生成对象次数16066

总结：当设置堆内存为1g时，由于SerialGC年轻代和老年代垃圾收集器都为单线程，因此垃圾回收效率相比于并行GC要低很多。

#3、-Xmx2g -Xms2g -XX:-UseAdaptiveSizePolicy 每次new的数组长度为100 * 1024 1s
##Serial串行GC
`2021-01-22T22:33:38.868+0800: [GC (Allocation Failure) 2021-01-22T22:33:38.869+0800: [DefNew: 559232K->69888K(629120K), 0.0688743 secs] 559232K->141350K(2027264K), 0.0695691 secs] [Times: user=0.05 sys=0.03, real=0.07 secs]`
>生成对象次数12507
## Parallel 并行GC
`2021-01-22T22:33:51.219+0800: [GC (Allocation Failure) [PSYoungGen: 524800K->87025K(611840K)] 524800K->145949K(2010112K), 0.0210772 secs] [Times: user=0.05 sys=0.11, real=0.02 secs]`
>生成对象次数17667

总结：当设置堆内存为2g时，同上，并且由于堆内存变大导致SerialGC回收效率更加低下。
#3、-Xmx4g -Xms4g -XX:-UseAdaptiveSizePolicy 每次new的数组长度为100 * 1024 1s
##Serial串行GC
`2021-01-22T22:40:56.131+0800: [GC (Allocation Failure) 2021-01-22T22:40:56.131+0800: [DefNew: 1118528K->139776K(1258304K), 0.1145584 secs] 1118528K->233140K(4054528K), 0.1150984 secs] [Times: user=0.05 sys=0.06, real=0.12 secs]`
>生成对象次数9156
## Parallel 并行GC
`2021-01-22T22:41:49.058+0800: [GC (Allocation Failure) [PSYoungGen: 1048576K->174591K(1223168K)] 1048576K->235026K(4019712K), 0.0320615 secs] [Times: user=0.11 sys=0.20, real=0.03 secs]`
>生成对象次数14593

总结：当设置堆内存为4g时，由于堆内存变大导致SerialGC回收效率更加低下，ParallGC的年轻代单次回收时间相对于2g也变长了，导致STW时间变长。

## 8G内存同理，需要根据自己系统的内存使用情况，去调整堆内存的大小，以上测试只是在Case程序中的GC情况，其他业务情况不同GC情况也不同。


#4、-Xmx8g -Xms8g -XX:-UseAdaptiveSizePolicy 每次new的数组长度为100 * 1024 Random 10s
##CMS GC 
>生成对象次数12978

查看日志得到主要信息有`-XX:MaxNewSize=872415232 -XX:NewSize=872415232 -XX:+UseParNewGC` CMS GC设置的默认新生代的大小为832m，Old区即为大约7.19G，通过GC日志分析也可得。
新生代使用ParNew垃圾收集器（Serial的多线程版）
发生了5次YoungGC

![avatar](https://cdn.nlark.com/yuque/0/2021/png/1171730/1611369563355-432c19e6-92be-462d-ba6c-a9e49d242e69.png)

##Parall GC
>生成对象次数7728

查看日志可看到`PSYoungGen      total 2446848K  ParOldGen       total 5592576K` Parallel GC新生代的大小默认为堆内存的1/3 此处约为为2.3G，Old区约为5.3G。
发生了1次YoungGC.

总结：由于我们生成的对象较小，在CMSGC下新生代内存相对较小，因此发生YongGC次数较多，但还未达到FullGC条件，每次YongGC时间较短；
      ParallelGC下新生代内存较大，因此只发生了一次YongGC，但由于新生代有2G所以执行时间长，STW时间比同条件下的CMSGC STW总时间长。

#5、-Xmx8g -Xms8g -XX:-UseAdaptiveSizePolicy 每次new的数组长度为1000 * 1024 Random 10s
##CMS GC
>生成对象次数13981

通过日志分析得到 发生了59次YongGC 3次FullGC(CMSGC)
>增加-XX:MaxGCPauseMillis=30 后 生成对象次数为16147
>>增加-XX:MaxGCPauseMillis=30 并且增加程序运行时间为20s后 生成对象次数为36171



## Parallel GC
>生成对象次数17877

通过日志分析得到 发生了21次YongGC 6次FullGC 
>增加-XX:MaxGCPauseMillis=30 后 生成对象次数为18496
>>增加-XX:MaxGCPauseMillis=30 并且增加程序运行时间为20s后 生成对象次数为36846



## G1 GC
>生成对象次数17846
>增加-XX:MaxGCPauseMillis=30 后 生成对象次数为21248
>增加-XX:MaxGCPauseMillis=30 并且增加程序运行时间为20s后 生成对象次数为49271



总结：由于我们生成的对象较大，CMS分配的新生代内存小832m，因此CMS触发的YongGC较多，而CMS的老年代内存又大7.19G，因此触发FullGC的次数很少；
      Parallel分配的新生代内存大2g，因此CMS触发的YongGC较少，每次回收提升的对象也较少，因此触发FullGC的次数也少。
      相比之下，CMS的STW时间就更长。如果每次生成的数组长度更大，导致Parallel的FullGC次数增多，那么CMS将会更有优势，因此CMS收集大部分阶段是与用户线程并发执行的。
      G1在此条件下与Parallel差不多，相比于CMS，较好。但当开启增加-XX:MaxGCPauseMillis=30 并且延长程序执行时间后后，G1相对于CMS和Parallel更加有优势
      
# 总结
不同垃圾回收器的使用场景不同，需要从软件（程序）、硬件（内存、CPU）等方面综合测试来选择适合我们应用的GC。