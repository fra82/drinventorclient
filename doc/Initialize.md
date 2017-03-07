Before start using the Dr. Inventor Framework in your Java code, by means of the Factory class (see [Javadoc](http://backingdata.org/dri/library/latest/javadoc.html)) it is possible to programmatically set the following paper processing options:


## Which PDF-to-text converter to use?
The Dr. Inventor Framework needs to extract semi-structured textual contents from papers in PDF format in order to enalbe any further analysis of their content. To this purpose, the Dr. Inventor Framework integrates two PDF-to-text converters tailored to scientific publications:

 + [PDFX](http://pdfx.cs.man.ac.uk/): an on-line Web service that extract structured textual content from PDF papers by generating an XML file. PDFX can process PDF files up to 5Mb: if a PDF file is greater than 5 Mb the Dr. Inventor Framework tries to compress its size to less than 5 Mb before sending the PDF to PDFX service for conversion to XML.  
*IMPORTANT*: PDFX is an on-line service that is NOT under our control and thus may experiment out-of-service timeslots that would prevent the Dr. Inventor Framework to process PDF papers. For this reason, before using PDFX, you can check the [status of the online service](http://pdfx.cs.man.ac.uk/). This limitation is not present in GROBID that is a stand-alone tool that does not require any connection to external online services. 

 + [GROBID](https://github.com/kermitt2/grobid) - version 0.4.1: a Java-based PDF-to-text conversion software customized to extract structured contents from scientific publications. From version 0.4.1 GROBID can work both in Linux and Windows environments.


It is possible to choose which one of the previous tools to use in order to convert a PDF paper to XML by means of the following code:  
```java
// To use PDFX:
Factory.setPDFtoTextConverter(PDFtoTextConvMethod.PDFX);

// To use GROBID:
Factory.setPDFtoTextConverter(PDFtoTextConvMethod.GROBID);
```  

After initializing the Factory class by means of the _setPDFtoTextConverter_ method, all the subsequent PDF-to-text conversion will exploit the chosen tool.

The following code is useful to check which PDF-to-text conversion tool is currently used:
```java
PDFtoTextConvMethod currentConverter = Factory.checkPDFtoTextConverter();
```  


## Enable and disable scientific text mining modules
When the Dr. Inventor Framework is initialized it is possible to enable or disable the different scientific text mining modules that it integrates. To this purpose, the ModuleConfig object has to be instantiated (see [Javadoc](http://backingdata.org/dri/library/latest/javadoc.html)). This object contains different boolean flags useful to manage the single scientific text mining modules of the Dr. Inventor Framework. If the related boolean flag is set to true, the scientific text mining module under consideration is activated.  
*IMPORTANT*: when a scientific text mining module is disactivated, the objects (or fields of the objects) of the Dr. Inventor Framework Data Model that should provide access to the information extracted by that module are set to null.

The following code shows how to enable / disable the different modules of the Dr. Inventor Framework programmatically:
```java
// Instantiate the ModuleConfig class - the constructor sets all modules enabled by default
ModuleConfig modConfigurationObj = new ModuleConfig();

// Enable the parsing of bibliographic entries by means of online services (Bibsonomy, CrossRef, FreeCite, etc.)
modConfigurationObj.setEnableBibEntryParsing(true);

// Enable BabelNet Word Sense Disambiguation and Entity Linking over the text of the paper
modConfigurationObj.setEnableBabelNetParsing(true);

// Enable the parsing of the information from the header of the paper by means of online services (Bibsonomy, CrossRef, FreeCite, etc.)
modConfigurationObj.setEnableHeaderParsing(true);

// Enable the extraction of candidate terms from the sentences of the paper
modConfigurationObj.setEnableTerminologyParsing(true);

// Enable the dependency parsing of the sentences of a paper
modConfigurationObj.setEnableGraphParsing(true);

// Enable coreference resolution
modConfigurationObj.setEnableCoreferenceResolution(true);

// Enable the extraction of causal relations
modConfigurationObj.setEnableCausalityParsing(true);

// Enable the association of a rhetorical category to the sentences of the paper
modConfigurationObj.setEnableRhetoricalClassification(true);

// Improt the configuration parameters set in the ModuleConfig instance
Factory.setModuleConfig(modConfigurationObj);
```  

The following code is useful to check (print on the standard output) which modules are currently enabled:
```java
System.out.println("Modules' enable status: " + Factory.getModuleConfig().toString());
```  


## Scientific text mining modules that require Web access
In order for the Dr. Inventor Framework to properly work, web access should be provided to:
 * Convert PDF papers to XML by means of the [PDFX](http://pdfx.cs.man.ac.uk/) online service;
 * Enrich the bibliographic entries and header of a paper by means of online services (Bibsonomy, CrossRef, FreeCite, etc.).
