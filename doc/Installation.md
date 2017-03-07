In order to properly use the Dr. Inventor Text Mining Framework you need to satisfy the following requirements and perform the installation steps described hereinafter.

## System requirements
In order to use the Dr. Inventor Text Mining Framework you should:
*  use *Java 1.8*
*  set the maximum [Java Heap Space](https://www.mkyong.com/Java/find-out-your-Java-heap-memory-size/) equal or greater than *5Gb* (by means of Virtual Machine arguments -Xmx4500m, or by means of system properties)


## How to import and configure the library in your Java project

In order to import the Dr. Inventor Text Mining Framework in your Java project you have to perform the following two steps:

### STEP 1: Import the library
There are two approaches to import the Dr. Inventor Text Mining Framework Java library:  
**Approach A**: If you are using Maven, add to the POM of your Maven project the following repository:  
```javascript
<repositories>
	<repository>
		<id>backingdata-repo</id>
		<name>Backingdata repository</name>
		<url>http://backingdata.org/dri/library/mavenRepo/</url>
	</repository>
</repositories>
```  
and the following dependency (the number of the latest version of the Dr. Inventor Text Mining Framework can be retrieved on the top of the [Index page](Index)):  
```javascript
<dependency>
	<groupId>edu.upf.taln.dri</groupId>
	<artifactId>lib</artifactId>
	<version>LATEST-VERSION-NUMBER-GOES-HERE</version>
</dependency>
```

**Approach B**: You can download [this compressed file](http://backingdata.org/dri/library/latest/jarWithDeps.html) including the library JAR together with all its dependencies so as to improt them in your Java code.

### STEP 2: Configure the library
Once imported the library and its dependences, the following step should be carried out:

 1. Download and extract [Dr. Inventor Framework Resources folder](http://backingdata.org/dri/library/latest/resourceFolder.html)

 2. Download [Dr. Inventor Framework Property file](http://backingdata.org/dri/library/latest/configurationFile.html)

 3. Modify the Dr. Inventor Framework Property file by setting:

     + the value of the _resourceFolder.fullPath_ property equal to the full local path of the Dr. Inventor Framework Resources folder previously downloaded (the path should end WITHOUT the training slash)
     + the value of the _babelnet.APIkey_ property equal to your BabelNet API Key - register to [BabelNet](http://babelnet.org/) to get your API key

 4. When executing a Java program that imports and uses the Dr. Inventor Framework you need to specify the local path to the Dr. Inventor Framework Property file by one of the following methods:

     + by means of a system property passed as the Java VM argument: _-DDRIpropertyFile=DRI-PROPERTY-FILE-LOCAL-PATH_
     + by means of the following initialization code / instruction:
    ```javascript
    Factory.setDRIPropertyFilePath("DRI-PROPERTY-FILE-LOCAL-PATH");
    ```

 5. It is important to specify the Java VM argument: _-Xmx5096m_ in order to enable an heap size equal or greater than 5Gb
 

## How to update from an older version
When you update from an older to a newer version of the Dr. Inventor Text Mining Framework you shuould perform the following steps:

 1.  If the library is imported by Maven, update the library version number of the dependency in your POM

 2.  If the JAR of the library is imported by downloading the JAR together with all its dependencies, [download the compressed file](http://backingdata.org/dri/library/latest/jarWithDeps.html) including the latest JARs (library together with its dependencies)

 3.  Download the new [Dr. Inventor Framework Resources folder](http://backingdata.org/dri/library/latest/resourceFolder.html)

 4.  Download the new [Dr. Inventor Framework Property file](http://backingdata.org/dri/library/latest/configurationFile.html) and replace this file to its old version if any. Properly modify the properties of the new Dr. Inventor Framework Property file by specifying the local path of the new Dr Inventor Library Resources folder and in case the other property values previously described in this page (STEP 2: COnfigure the library).

Remember that each version of the library comes with its own Dr. Inventor Framework Resources folder and its own Dr. Inventor Framework Property file. You should update both these resources as explained when switching to another version of the Dr. Inventor Framework.
