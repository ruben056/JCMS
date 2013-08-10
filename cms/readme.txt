// git repository
https://github.com/ruben056/JCMS.git

// online deployment (openshift):
http://rd-myjcms.rhcloud.com/

MYSQL INFO:
----------
MySQL 5.1 database added.  Please make note of these credentials:
       Root User: adminIM4hrvr
   Root Password: unW2h67TWGEP
   Database Name: rd
Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
You can manage your new MySQL database by also embedding phpmyadmin-3.4.
The phpmyadmin username and password will be the same as the MySQL credentials above.


all openshift properties:
OPENSHIFT_MYSQL_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/mysql/
SELINUX_ROLE_REQUESTED=
TERM=xterm
SHELL=/bin/bash
TMPDIR=/tmp/
OPENSHIFT_JBOSSEWS_HTTP_PORT=8080
SSH_CLIENT=91.181.83.73 33890 22
OPENSHIFT_JBOSSEWS_JPDA_PORT=8787
OPENSHIFT_TMP_DIR=/tmp/
OPENSHIFT_MYSQL_DB_PORT=3306
SELINUX_USE_CURRENT_RANGE=
OPENSHIFT_JBOSSEWS_LOG_DIR.erb=<%= ENV['OPENSHIFT_JBOSSEWS_DIR'] %>logs/
OPENSHIFT_REPO_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/app-root/runtime/repo/
OPENSHIFT_HOMEDIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/
OPENSHIFT_GEAR_NAME=rd
OPENSHIFT_MYSQL_DB_HOST=127.7.190.2
CARTRIDGE_VERSION_2=2
SSH_TTY=/dev/pts/3
OPENSHIFT_MYSQL_DB_PASSWORD=unW2h67TWGEP
OPENSHIFT_CLOUD_DOMAIN=rhcloud.com
USER=51e3a9ac4382ecad930000ed
OPENSHIFT_JBOSSEWS_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews/
TMP_DIR=/tmp/
OPENSHIFT_MYSQL_IDENT=redhat:mysql:5.1:0.2.1
TMOUT=300
OPENSHIFT_JBOSSEWS_JDK7=/etc/alternatives/java_sdk_1.7.0
OPENSHIFT_JBOSSEWS_JDK6=/etc/alternatives/java_sdk_1.6.0
OPENSHIFT_MYSQL_DB_USERNAME=adminIM4hrvr
OPENSHIFT_MYSQL_DB_SOCKET=/var/lib/openshift/51e3a9ac4382ecad930000ed/mysql//socket/mysql.sock
MAIL=/var/mail/51e3a9ac4382ecad930000ed
PATH=/etc/alternatives/java_sdk_1.7.0/bin:/etc/alternatives/maven-3.0/bin:/etc/alternatives/java_sdk_1.7.0/bin:/etc/alternatives/maven-3.0/bin::/bin:/usr/bin:/usr/sbin
PWD=/var/lib/openshift/51e3a9ac4382ecad930000ed
OPENSHIFT_JBOSSEWS_IP=127.7.190.1
JAVA_HOME=/etc/alternatives/java_sdk_1.7.0
OPENSHIFT_MYSQL_DB_URL=mysql://adminIM4hrvr:unW2h67TWGEP@127.7.190.2:3306/
OPENSHIFT_JBOSSEWS_IDENT=redhat:jbossews:2.0:0.0.4
OPENSHIFT_APP_DNS=rd-myjcms.rhcloud.com
LANG=en_US.UTF-8
OPENSHIFT_PRIMARY_CARTRIDGE_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews/
OPENSHIFT_JBOSSEWS_PATH_ELEMENT=/etc/alternatives/java_sdk_1.7.0/bin:/etc/alternatives/maven-3.0/bin
OPENSHIFT_GEAR_DNS=rd-myjcms.rhcloud.com
PS1=[rd-myjcms.rhcloud.com \W]\> 
OPENSHIFT_CARTRIDGE_SDK_BASH=/usr/lib/openshift/cartridge_sdk/bash/sdk
SELINUX_LEVEL_REQUESTED=
OPENSHIFT_JBOSSEWS_LOG_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews/logs/
SHLVL=1
M2_HOME=/etc/alternatives/maven-3.0
HOME=/var/lib/openshift/51e3a9ac4382ecad930000ed/
OPENSHIFT_APP_NAME=rd
OPENSHIFT_MYSQL_DB_LOG_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/mysql//log/
OPENSHIFT_DATA_DIR=/var/lib/openshift/51e3a9ac4382ecad930000ed/app-root/data/
TMP=/tmp/
LOGNAME=51e3a9ac4382ecad930000ed
OPENSHIFT_NAMESPACE=myjcms
OPENSHIFT_GEAR_UUID=51e3a9ac4382ecad930000ed
OPENSHIFT_BROKER_HOST=openshift.redhat.com
SSH_CONNECTION=91.181.83.73 33890 10.165.0.156 22
OPENSHIFT_APP_UUID=51e3a9ac4382ecad930000ed
OPENSHIFT_JBOSSEWS_VERSION=2.0
HISTFILE=/var/lib/openshift/51e3a9ac4382ecad930000ed/app-root/data/.bash_history
OPENSHIFT_CARTRIDGE_SDK_RUBY=/usr/lib/openshift/cartridge_sdk/ruby/sdk.rb
_=/bin/env


system properties:
------------------
1. java.vendor = Oracle Corporation

2. sun.java.launcher = SUN_STANDARD
3. catalina.base = /var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews/
4. sun.management.compiler = HotSpot Tiered Compilers
5. catalina.useNaming = true
6. os.name = Linux
7. sun.boot.class.path = /usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/alt-rt.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/resources.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/rt.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/sunrsasign.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/jsse.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/jce.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/charsets.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/netx.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/plugin.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/rhino.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/jfr.jar:/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/classes
8. java.util.logging.config.file = /var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews//conf/logging.properties
9. OPENSHIFT_APP_UUID = 51e3a9ac4382ecad930000ed
10. java.vm.specification.vendor = Oracle Corporation
11. java.runtime.version = 1.7.0_25-mockbuild_2013_06_26_07_26-b00
12. user.name = 51e3a9ac4382ecad930000ed
13. shared.loader = 
14. tomcat.util.buf.StringCache.byte.enabled = true

15. user.language = en
16. java.naming.factory.initial = org.apache.naming.java.javaURLContextFactory
17. sun.boot.library.path = /usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/i386
18. java.version = 1.7.0_25
19. java.util.logging.manager = org.apache.juli.ClassLoaderLogManager
20. user.timezone = America/New_York
21. java.net.preferIPv4Stack = true
22. sun.arch.data.model = 32
23. java.endorsed.dirs = 
24. sun.cpu.isalist = 
25. sun.jnu.encoding = ANSI_X3.4-1968
26. file.encoding.pkg = sun.io
27. package.access = sun.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.

28. file.separator = /

29. java.specification.name = Java Platform API Specification
30. java.class.version = 51.0
31. user.country = US
32. java.home = /usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre
33. java.vm.info = mixed mode
34. os.version = 2.6.32-358.14.1.el6.x86_64

35. sun.font.fontmanager = sun.awt.X11FontManager
36. path.separator = :

37. javax.accessibility.assistive_technologies = org.GNOME.Accessibility.JavaBridge
38. java.vm.version = 23.7-b01
39. jboss.i18n.generate-proxies = true
40. java.awt.printerjob = sun.print.PSPrinterJob
41. sun.io.unicode.encoding = UnicodeLittle
42. tomcat.util.scan.DefaultJarScanner.jarsToSkip = bootstrap.jar,commons-daemon.jar,tomcat-juli.jar,annotations-api.jar,el-api.jar,jsp-api.jar,servlet-api.jar,catalina.jar,catalina-ant.jar,catalina-ha.jar,catalina-tribes.jar,jasper.jar,jasper-el.jar,ecj-*.jar,tomcat-api.jar,tomcat-util.jar,tomcat-coyote.jar,tomcat-dbcp.jar,tomcat-jni.jar,tomcat-spdy.jar,tomcat-i18n-en.jar,tomcat-i18n-es.jar,tomcat-i18n-fr.jar,tomcat-i18n-ja.jar,tomcat-juli-adapters.jar,catalina-jmx-remote.jar,catalina-ws.jar,tomcat-jdbc.jar,commons-beanutils*.jar,commons-codec*.jar,commons-collections*.jar,commons-dbcp*.jar,commons-digester*.jar,commons-fileupload*.jar,commons-httpclient*.jar,commons-io*.jar,commons-lang*.jar,commons-logging*.jar,commons-math*.jar,commons-pool*.jar,jstl.jar,geronimo-spec-jaxrpc*.jar,wsdl4j*.jar,ant.jar,ant-junit*.jar,aspectj*.jar,jmx.jar,h2*.jar,hibernate*.jar,httpclient*.jar,jmx-tools.jar,jta*.jar,log4j*.jar,mail*.jar,slf4j*.jar,xercesImpl.jar,xmlParserAPIs.jar,xml-apis.jar,access-bridge-64.jar,dnsns.jar,jaccess.jar,ldapsec.jar,localedata.jar,sunjce_provider.jar,sunmscapi.jar,sunpkcs11.jar,jhall.jar,tools.jar,sunec.jar,zipfs.jar,gnome-java-bridge.jar,pulse-java.jar,apple_provider.jar,AppleScriptEngine.jar,CoreAudio.jar,dns_sd.jar,j3daudio.jar,j3dcore.jar,j3dutils.jar,jai_core.jar,jai_codec.jar,mlibwrapper_jai.jar,MRJToolkit.jar,vecmath.jar,junit.jar,junit-*.jar,ant-launcher.jar

43. awt.toolkit = sun.awt.X11.XToolkit

44. package.definition = sun.,java.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.
45. java.naming.factory.url.pkgs = org.apache.naming
46. user.home = /var/lib/openshift/51e3a9ac4382ecad930000ed
47. org.apache.catalina.startup.ContextConfig.jarsToSkip = 
48. java.specification.vendor = Oracle Corporation
49. java.library.path = /usr/java/packages/lib/i386:/lib:/usr/lib

50. java.vendor.url = http://java.oracle.com/
51. org.apache.catalina.startup.TldConfig.jarsToSkip = 
52. java.vm.vendor = Oracle Corporation

53. common.loader = ${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar
54. java.runtime.name = OpenJDK Runtime Environment
55. sun.java.command = org.apache.catalina.startup.Bootstrap start
56. java.class.path = :/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews//bin/bootstrap.jar:/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews//bin/commons-logging-tomcat-juli.jar:/var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews//bin/tomcat-juli.jar:/usr/share/java/commons-daemon.jar
57. java.vm.specification.name = Java Virtual Machine Specification
58. java.vm.specification.version = 1.7

59. catalina.home = /var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews/
60. sun.cpu.endian = little
61. sun.os.patch.level = unknown
62. java.io.tmpdir = /var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews//tmp
63. java.vendor.url.bug = http://bugreport.sun.com/bugreport/
64. server.loader = 
65. os.arch = i386
66. java.awt.graphicsenv = sun.awt.X11GraphicsEnvironment
67. java.ext.dirs = /usr/lib/jvm/java-1.7.0-openjdk-1.7.0.25/jre/lib/ext:/usr/java/packages/lib/ext
68. user.dir = /var/lib/openshift/51e3a9ac4382ecad930000ed/jbossews
69. line.separator = 

70. java.vm.name = OpenJDK Server VM
71. file.encoding = UTF-8
72. java.specification.version = 1.7
