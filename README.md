# 定制化代码生成项目

## 项目介绍

基于 React + Spring Boot + Vert.x 响应式编程的 **定制化代码生成项目** 。


![](https://pic.yupi.icu/1/1698933854908-e93e1a08-0d82-4ce2-a020-00522bb10403.png)



完整项目分为 3 个阶段：

1）第一阶段， `本地代码生成器` ，是一个 **基于命令行的脚手架** ，能够根据用户的交互式输入快速生成特定代码。



![](https://pic.yupi.icu/1/1698933854885-965d0281-ecfc-4940-824a-2da48eb5b8ad.png)



2）第二阶段，让我们上升一个层次，带大家开发 `制作代码生成器的工具` 。比如你有一段常用的项目代码，使用该工具，可以快速把项目代码制作为代码生成器，将是提高工作效率的大杀器！



3）第三阶段，让我们再上升一个层次，带大家开发 `在线代码生成器平台` ！你可以在平台上制作发布自己的代码生成器，还可以在线使用别人的代码生成器，甚至可以共享协作！



![](https://pic.yupi.icu/1/1698933854913-c0593377-66c7-4fad-b0de-559d83f28a4e.png)



之所以要分为 3 个阶段，一方面是因为项目是比较庞大的，拆分后学习会更轻松；另一方面是不同阶段的难度不同，大家可以根据自己的学习进度和时间独立学习。



比如即使你什么框架都没学过，也可以完成第一阶段；而如果你学过开发框架，可以完成第二阶段；如果你想学习更多高级知识和优化技巧，再去看第三阶段。够贴心吧~



## 项目背景

主要考虑的是：学习意义、解决的问题、实际应用三个方面。



### 学习意义

1）教程资料少：网上虽然有现成的项目模板，但基本都是别人封装好的，只能按作者的要求生成，并且缺少项目教程；而本项目不仅带大家做自己的代码生成器，还会 **进 2 步** 扩展，打造制作自定义代码生成器的工具和平台。

2）新颖且亮眼：别人写代码，而你做生产代码的脚手架、工具和平台来提高研发效能，降维打击。

3）能学到东西：不再是增删改查的项目，而是包含了大量的实际业务场景、系统设计和解决方案。

4）有区分度：区别于传统 Web 应用，项目涉及命令行应用、响应式编程、性能优化的入门及实战，给你的简历增加竞争力。



### 解决的问题

1）代码生成器本身的作用就是自动生成常见、重复性的代码片段，**解决重复编码、效率低下的问题** 。

2）虽然网上有很多代码生成器，但都是别人制作封装好的，很多时候还是 **无法满足实际开发的定制化需求** （比如要在每个类上增加特定的注解和注释）。这也是为什么明明有代码生成器，很多开发者还是会抱怨自己的工作总是复制粘贴、编写重复的代码、天天 CRUD（增删改查）。如果能够有一个工具帮助开发者快速定制属于自己的代码生成器，那么将进一步提高开发效率。

3）在团队开发中，要生成的代码可能是需要频繁变化和持续更新维护的。如果有一个线上平台来维护多个不同的代码生成器，支持在线编辑和共享生成器，在提高开发效率的同时、将有利于协作共建，打造更高质量的代码生成器。



### 实际应用

举例一些代码生成的实际应用场景，我们将通过本项目进行解决：

1）经常做算法题目的同学，可能需要一套 Java ACM 代码输入模板，能够支持多种不同输入模式（比如单次读取和循环）。

2）经常开发新项目的同学，可能需要一套初始化项目模板代码，比如一键生成 Controller 层代码（替换其中的对象）、整合 Redis 和 MySQL 依赖等。

3）甚至可以制作项目 “换皮” 工具，支持一键给网络热门项目换皮（比如替换项目的名称、Logo 等）






## 需求分析

其实上面已经带大家了解了项目的基本信息、项目的意义和价值。在需求分析阶段，我们通常还要进行一些调研工作，帮助我们明确需求、为后面的设计做好充分的准备。



### 调研

网上有一些代码生成器项目，比如前端 [Ant Design Pro](https://pro.ant.design/zh-CN) 中后台项目脚手架，能够让用户交互式地创建指定的项目；还有后端 MyBatis X 插件，能够让用户通过界面来创建 CRUD 重复代码。但这些项目都是开发者提前制作好了代码生成器，然后让你 **根据他们设置好的规则** 生成代码（或者拉取特定位置的代码），生成后的代码通常还要再自己二次修改，不够灵活。

还有很多所谓的代码生成项目，其实本质上是一个现成的项目模板，让你通过编写对应的配置文件来使用项目，或者还是基于预设的程序来生成特定代码。比如 [编程导航知识星球](https://yuyuanweb.feishu.cn/wiki/VC1qwmX9diCBK3kidyec74vFnde) 的 Spring Boot 万用项目模板，或者网上很多知名的开源管理系统。

这和我们要做的事情不同，我们的目标是升级一个层次！打造一个帮助大家快速制作代码生成器的工具，也就是 “造轮子的轮子”。

然后再升级一个层次！允许用户像发布应用一样，在平台上发布和管理代码生成器，便于共享和协作！



## 技术选型

暂定的核心技术如下，实际开发中还会引入新技术



### 前端

- React 开发框架 + 组件库 + 代码编辑器
- 前端工程化：ESLint + Prettier + TypeScript



### 后端

- Java Spring Boot + MySQL + MyBatis Plus（万用后端模板）
- Java 命令行应用开发
- FreeMarker 模板引擎
- Vert.x 响应式编程
- Caffeine + Redis 多级缓存
- 分布式任务调度系统
- 多种设计模式
- 多种系统设计的巧思
- 对象存储



### 学习重点

其中：Java 命令行应用开发、FreeMarker 模板引擎、Vert.x 响应式编程、设计模式、系统设计的巧思、分布式任务调度系统都是本项目的学习重点，也是校招简历上很少有同学提到的知识实践，学完后能够大大增加简历的区分度！

比如 Vert.x 框架，并发连接处理能力吊打 Spring 几条街！

Web Framework Benchmarks 性能对比网站：https://www.techempower.com/benchmarks/#section=data-r21&test=composite

![](https://pic.yupi.icu/1/1698810692479-1ab9f790-eebb-47e5-84fb-1db6775c03bc-20231103004416471.png)

![](https://pic.yupi.icu/1/1698810756154-01c4352e-aa62-4b99-b5ec-45479adb0e69-20231103004420429.png)



## 项目设计

首先我们要理解代码生成的核心原理。



### 代码生成器的核心原理

一句话：参数 + 模板文件 = 生成的完整代码



比如参数：

```java
作者 = xiong
```



模板文件代码：

```java
-----------
我是 ${作者}
-----------
```



将参数注入到模板文件中，得到生成的完整代码：

```java
-----------
我是 xiong
-----------
```



如果想要使用这套模板生成其他的代码，只需要改变参数的值即可，而不需要改变模板文件。

理解了代码生成器的核心原理后，就可以开始思考项目的设计实现方案了。



------



项目总共分为 3 个阶段，让我们分别来进行设计。



### 第一阶段 - 本地代码生成器

这个阶段，我们的目标是做一个本地（离线）的代码生成器，实现一个简易的 Java ACM 模板项目的定制化生成。

本阶段不依赖复杂的开发框架，**没学过任何开发框架也能学习。**



#### 业务流程

1）准备用于制作代码生成器的原始代码（比如 Java ACM 模板项目），用于后续生成

2）开发者基于原始代码，设置参数、编写动态模板

3）制作可交互的命令行工具，支持用户输入参数，得到代码生成器 jar 包

4）使用者得到代码生成器 jar 包，执行程序并输入参数，从而生成完整代码



流程图如下：

![](https://pic.yupi.icu/1/1698930303648-d82309e8-bbde-43dd-adb2-9d0c647e1e40-20231103004425416.jpeg)



#### 实现思路

1）先根据本地项目，扫描文件树，实现同样的静态代码生成

2）根据本地的项目，预设部分动态参数、编写模板文件，能够传入配置对象进行生成

3）制作可交互的命令行工具，接受用户输入的参数，并动态生成代码

4）封装制作代码生成器 jar 包文件，并简化使用命令



#### 关键问题

如何根据一套项目文件，完整地生成同样一套项目？

如何编写动态模板文件？怎么根据模板和参数生成代码？

如何制作命令行工具？如何交互式接受用户的输入？

怎么将命令行工具制作为 jar 包？怎么简化使用命令？



### 第二阶段 - 代码生成器制作工具

这个阶段，我们的目标是做一个本地的代码生成器制作工具，能够快速将一个项目制作为可以动态定制部分内容的代码生成器。并且以一个 Spring Boot 初始化项目模板（Maven 项目）为例，演示如何根据自己的需要动态生成 Java 后端初始化项目。



#### 业务流程

1）准备用于制作代码生成器的原始代码（比如 Spring Boot 项目模板），用于后续生成

2）开发者基于原始代码，**使用代码生成器制作工具**，来快速设置参数、生成动态模板

3）**使用代码生成器制作工具**，动态生成代码生成器 jar 包

4）使用者得到代码生成器 jar 包，执行程序并输入参数，从而生成完整代码



相比第一阶段的业务流程，本阶段完成后，可以直接使用代码生成器制作工具来快速将固定的项目代码改造为可定制生成的动态模板，并自动生成命令行工具 jar 包。



流程图如下：

![](https://pic.yupi.icu/1/1698843403252-3ee97aaa-c20e-4922-ae0e-1195b44e1c3b-20231103004430323.jpeg)



#### 实现思路

1）使用独立空间来存储管理要生成的原始文件、动态模板文件等

2）使用配置文件来记录要生成的参数和模板文件信息、自定义配置等 **元信息**

3）代码生成器制作工具需要有多种可单独或组合使用的功能，比如从原始文件中抽取参数、动态生成命令行工具、打 jar 包等



#### 关键问题

1）如何使用配置文件来记录参数和模板文件信息？何种结构？

2）怎么能够提高代码生成器的制作效率？工具应该提供哪些能力？

3）如何从原始文件中抽取参数？有哪些类型的参数？有哪些抽取规则？比如布尔类型参数（是否生成）、字符串类型参数（生成指定的值）等

![](https://pic.yupi.icu/1/1698930808644-1dc4e66a-0ea5-4128-aed3-73b828330666.png)

4）如何动态生成配置类？如何动态生成命令行工具？如何动态打 jar 包？



### 第三阶段 - 在线代码生成器平台

经过前两个阶段，我们已经能够使用本地的代码生成器制作工具来快速定制自己的代码生成器了。

但如果我们想和团队其他同学共同维护代码生成器、或者使用其他人的代码生成器，通过本地文件互传的方式肯定就很麻烦了。

所以本阶段我们要打造一个在线代码生成器平台，可以理解为代码生成器的 **应用市场**。所有人都能发布、使用、甚至是在线制作自己的代码生成器！

本阶段我们的终极目标是，在平台上制作和发布项目 “换皮” 工具，帮助小伙伴们一键给网络热门项目换皮！

![](https://pic.yupi.icu/1/1698844599236-cdc91554-bcfe-4028-84b6-2dffb082ed94.png)



#### 业务流程

1）获取用于制作代码生成器的原始代码（手动准备或者远程拉取代码）

2）开发者基于原始代码，使用 **在线代码生成器制作工具**，来快速制作代码生成器

3）开发者发布代码生成器至平台

4）使用者在平台上搜索代码生成器，支持在线使用或者下载离线 jar 包（甚至还可以支持接口调用）



完整业务流程图如下：

![](https://pic.yupi.icu/1/1698931262679-a16ecd8d-4d4c-4c8d-bdcc-eab7554ab10c-20231103004438590.jpeg)





#### 实现思路

1）使用 Web 开发框架实现代码生成器信息的增删改查

2）将本地的配置和文件 **上云**，存储到数据库、对象存储等云服务。

3）通过可视化界面来操作第二阶段的代码生成器制作工具，复用阶段二的成果。



#### 关键问题

1）怎么在云上存储管理代码生成器？

2）如何通过前端开发，提高代码生成器的制作效率？

3）如何通过后端优化，提高代码生成器的制作性能？

4）如何保证代码生成器的存储空间不超限、如何优化存储？



## 新建代码仓库

搭建仓库，点 star 的都是精神股东



代码仓库：https://gitee.com/xgfieefree/xiong-geneator.git



