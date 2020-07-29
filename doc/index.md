<h1>Dr. Inventor Text Mining Framework</h1>

**Latest version: 4.0 (29/11/2016)**  
  
**Open-source project distributed under the [GPL v3.0 license](https://www.gnu.org/licenses/gpl-3.0.html)**
  
Dr. Inventor Text Mining Framework is a self-contained java library that enables the automated analysis of scientific publications. The Framework is intended to support researchres as well as any other interested actor in easily bootstrapping any automated analysis and study of scientific articles. Given a paper (PDF or JATS XML), a set of Java methods and classes enables the access to the results of the analyses performed by the Framework. The Dr. Inventor Text Mining Framework supports the characterization of several aspects of scientific publications including the identification of their structural elements, the enrichment of their bibliographic entries by accessing external on-line services, the rhetorical characterization of sentences, named entity linking and disambiguation and the creation of extractive summaries.

This documentation includes detailed information concerning the [architecture](Components) of the Dr. Inventor Text Mining Framework and its [integration and exploitation](Installation) in your code.  
  
**LICENSING**: The Dr. Inventor Text Mining Framework is an open-source project distributed under the [GPL v3.0 license](https://www.gnu.org/licenses/gpl-3.0.html), for non-commercial applications. In case a comercial licence is needed, you can contact the [Large-Scale Text Understanding Research Lab](https://www.upf.edu/web/taln/labs/lastus/research-lines) for more information about licensing conditions and for consulting how the Dr. Inventor Text Mining Framework can suit your application.  
  
  
**Please, note that parts of this documentation are currently under editing.**

## Latest version: 4.0 (released on 29/11/2016)

Links to resources (refer to the [Installation](Installation.md) section to see how import the library in your program):
* [Jar file and dependent jars of the latest version](http://taln.upf.edu/drinventor/lib-4.0-bin.zip). Please, refer the [Installation](Installation) section of this documentation for a detailed explanation of how to use the Dr. Inventor Framework in your code
* [Javadoc of latest version](http://taln.upf.edu/drinventor/doc/)
* [Dr. Inventor Framework Resources folder of the latest version](http://taln.upf.edu/drinventor/DRIresources-4.0.tar.gz)
* [Dr. Inventor Framework Property file of the latest version](http://taln.upf.edu/drinventor/DRIconfig.properties)
* [Client code sample](https://github.com/fra82/driframeworkclient)


<h3>About</h3>

* [Introduction](Introduction.md)
* [References](References.md)
* [Version](Version.md)

<h3>Architecture</h3>

* [Overview](ArchitectureOverview.md)
* [Converting PDF to text](PDFtoText.md)
* [Analyzing citations](Citation.md)
* [Identifying the rhetorical role of sentences](RhetSentence.md)
* [Annotating papers by Babelnet](BabelnetAnn.md)
* [Generating Subject-Verb-Object graphs](SVOgraph.md)
* [Summarizing papers](Summa.md)


<h3>User manual</h3>

* [Installation](Installation.md)
* [Initialize the library](Initialize.md)
* [Import scientific publications](ImportDoc.md)
* [Process scientific publications](ProcessDoc.md)
* [The Scientific Publication Data Model](ScuPubDataModel.md)
* [Store processing results](StoreDoc.md)
* [FAQ](FAQ.md)

