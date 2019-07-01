# SpringMVCImplement


    run
    - gradle build
    - java -jar test/build/libs/test-1.0-SNAPSHOT.jar (文件为打包路径下的jar包)

##### gradle.build文件
        描述了文件中的依赖信息（和mvn中的dependency一样）
        jar包 描述了文件的打包信息，打包的方法
        
##### setting.gradle文件
        描述了项目的结构，root目录，还有其中使用的包
        
#### FrameWork
###### -server包
        使用了TomCat Embeded core包，仿照Spring boot的方式，将Tomcat嵌入在项目中
        在其中将 我们实现的DispatcherServlet与Tomcat进行绑定
        端口在6699
        根路径是 “/”
        其中使用的await是为了防止Tomcat异常退出而使用的等待线程
        
###### -core
        ClassScanner是扫描所有的类，从package的名字下，通过classloader，拿到所有运行中的类
        
###### -servlet
        实现了DispatcherServlet
        mappingHandlerList中含有所有的Controller的类
