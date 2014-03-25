dtts
====

### Report different versions on your multi-module maven project.

[Download](https://github.com/EsmerilProgramming/dtts/releases/download/1.3.0/dtts-1.3.0.jar)


```shell
java -jar dtts-1.3.0.jar 
```

or
 
```shell
java -jar dtts-1.3.0.jar /complete_path_to_directory_of_your_multi_module_maven_project
```

### Output sample:

```shell
#############################################################################
Dependencies strangers found: 49
#############################################################################
-----------------------------------------------------------------------------
com.sun.xml.messaging.saaj:saaj-impl:1.3.2
-----------------------------------------------------------------------------
com.sun.xml.messaging.saaj:saaj-impl:1.3.18
-----------------------------------------------------------------------------
commons-lang:commons-lang:2.6
-----------------------------------------------------------------------------
commons-lang:commons-lang:2.4
-----------------------------------------------------------------------------
jline:jline:1.0
-----------------------------------------------------------------------------
jline:jline:0.9.94
-----------------------------------------------------------------------------
junit:junit:4.11
-----------------------------------------------------------------------------
junit:junit:4.10
-----------------------------------------------------------------------------
junit:junit:4.1
-----------------------------------------------------------------------------
#############################################################################
Plugins strangers found: 42
#############################################################################
-----------------------------------------------------------------------------
javax.servlet:jstl:1.2
-----------------------------------------------------------------------------
javax.servlet:jstl:1.1.2
-----------------------------------------------------------------------------
org.apache.maven.plugins:maven-antrun-plugin:1.7
-----------------------------------------------------------------------------
org.apache.maven.plugins:maven-antrun-plugin:1.5
-----------------------------------------------------------------------------
#############################################################################
Files and lines...
#############################################################################
com.sun.xml.messaging.saaj:saaj-impl:1.3.2 
/complete_path_to_directory_of_your_multi_module_maven_project/project/module1/pom.xml - line 78
-----------------------------------------------------------------------------
com.sun.xml.messaging.saaj:saaj-impl:1.3.18 
/complete_path_to_directory_of_your_multi_module_maven_project/project/module1/pom.xml - line 116
-----------------------------------------------------------------------------
commons-lang:commons-lang:2.6: 
/complete_path_to_directory_of_your_multi_module_maven_project/project/module3/pom.xml - line 1765
-----------------------------------------------------------------------------
commons-lang:commons-lang:2.4 found: 
/complete_path_to_directory_of_your_multi_module_maven_project/project/module4/pom.xml - line 178
-----------------------------------------------------------------------------
```