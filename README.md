# AspectDemo
AspectDemo


面向切面编程：

1. 主要的用途：
    1. 数据的持久化
    2. 方法的性能检测
    3. 方法拦截
    4. 日志的打印等

2. 注意事项：
    1. 集成的app的minSdkVersion 24  最低是24以上


3. android端在as中集成的说明：


    1. 在项目的build.gradle中加入依赖：

            dependencies {
                    classpath 'com.android.tools.build:gradle:3.4.0'
                    classpath 'org.aspectj:aspectjtools:1.9.4'
                }

    2. 在模块的app/build.gradle中加入：

        1. 在依赖中加入：

            implementation 'org.aspectj:aspectjrt:1.9.4'

        2. 在依赖下方加入：

            import org.aspectj.bridge.IMessage
            import org.aspectj.bridge.MessageHandler
            import org.aspectj.tools.ajc.Main

            final def log = project.logger
            final def variants = project.android.applicationVariants

            //在构建工程时，执行编辑
            variants.all { variant ->
                if (!variant.buildType.isDebuggable()) {
                    log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
                    return;
                }

                JavaCompile javaCompile = variant.javaCompile
                javaCompile.doLast {
                    String[] args = ["-showWeaveInfo",
                                     "-1.9",
                                     "-inpath", javaCompile.destinationDir.toString(),
                                     "-aspectpath", javaCompile.classpath.asPath,
                                     "-d", javaCompile.destinationDir.toString(),
                                     "-classpath", javaCompile.classpath.asPath,
                                     "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
                    log.debug "ajc args: " + Arrays.toString(args)

                    MessageHandler handler = new MessageHandler(true);
                    new Main().run(args, handler);
                    for (IMessage message : handler.getMessages(null, true)) {
                        switch (message.getKind()) {
                            case IMessage.ABORT:
                            case IMessage.ERROR:
                            case IMessage.FAIL:
                                log.error message.message, message.thrown
                                break;
                            case IMessage.WARNING:
                                log.warn message.message, message.thrown
                                break;
                            case IMessage.INFO:
                                log.info message.message, message.thrown
                                break;
                            case IMessage.DEBUG:
                                log.debug message.message, message.thrown
                                break;
                        }
                    }
                }
            }

