The Dr. Inventor Text Mining Framework can extract and enrich the contents of scientific publications both in PDF and [JATS XML](https://jats.nlm.nih.gov/publishing/tag-library/1.1/index.html) formats. It is also possible to analize the contents of plain text excerpts.

Once imported, the contents of a paper can be accessed by means of the method of the interface Document (see [Javadoc](http://backingdata.org/dri/library/latest/javadoc.html)): the Document interface exposes the core set of method useful to retrieve the scturcutred, semantically-rich information mined from a paper. This methods return instances of the objects of the [Scientific Publication Data Model](ScuPubDataModel) defined by the Dr. Inventor Frameowrk: this data model represents by means of java objects all the information mined form a scientific publication.


## Import PDF papers
Once programmatically chosen a PDF-to-text conversion approach (see [Initialization](Initialize) section), it is possible to import the contents of a PDF paperin a Document instance by means of one of the following three approaches:
```java
// Import a PDF paper from full local path:
Document docPDFpaperFromPath = Factory.getPDFloader().parsePDF(PDF-PAPER-LOCAL-FILE-PATH);

// Import a PDF paper by downloading it from its URL:
Document docPDFpaperFromURL = Factory.getPDFloader().parsePDF(new URL("http://www2007.org/workshops/paper_45.pdf"));

// Import a PDF paper by passing a File instance:
Document doc_PDFpaperFromFile = Factory.getPDFloader().parsePDF(new File(PDF-PAPER-LOCAL-FILE-PATH));
```  

*IMPORTANT*: if the Document instance returned by the previous methods is null, there has been some problem in the PDF import process (inspect the program log for more details).


## Import JATS XML papers
Similarly to PDF files, it is possible to import JATS XML files of a scientific articles by means of one of the following three approaches:
```java
// Import a JATS XML paper from full local path:
Document docJATSpaperFromPath = Factory.getJATSloader().parseJATS(JATS-PAPER-LOCAL-FILE-PATH);

// Import a JATS XML paper by downloading it from its URL:
Document docJATSpaperFromURL = Factory.getJATSloader().parseJATS(new URL("http://journals.plos.org/plosone/article/asset?id=10.1371/journal.pone.0141854.XML&download="));

// Import a JATS XML paper by passing a File instance:
Document doc_JATSpaperFromFile = Factory.getJATSloader().parseJATS(new File(JATS-PAPER-LOCAL-FILE-PATH));
```  

*IMPORTANT*: if the Document instance returned by the previous methods is null, there has been some problem in the JATS import process (inspect the program log for more details).



## Import plain text excerpts
Similarly to PDF and JATS XML files, it is possible to import plain texts by means of one of the following four approaches:
```java
// Import a plain text file from full local path:
Document doc_PLAINTEXTpaperFromPath = Factory.getPlainTextLoader().parsePlainText(PLAIN-TEXT-LOCAL-FILE-PATH);

// Import a plain text file by downloading it from its URL:
Document doc_PLAINTEXTpaperFromURL = Factory.getPlainTextLoader().parsePlainText(new URL("http://myexample.com/plainText.txt"));

// Import a plain text file by passing a File instance:
Document doc_PLAINTEXTpaperFromFile = Factory.getPlainTextLoader().parsePlainText(new File(PLAIN-TEXT-LOCAL-FILE-PATH));

// Import a plain text file by passing a File instance:
String stringPlainText = "This is the plain text to process.";
String documentName = "Reference name for the plain text to process.";
Document doc_PLAINTEXTpaperFromFile = Factory.getPlainTextLoader().parseString(stringPlainText, documentName);
```  

*IMPORTANT*: if the Document instance returned by the previous methods is null, there has been some problem in the plain text import process (inspect the program log for more details).

*IMPORTANT*: if the Document instance is generated from a plain text file, the citation analysis module and the header parsing module can't be applied and are thus ignored.

