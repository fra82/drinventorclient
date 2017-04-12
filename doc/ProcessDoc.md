Once imported a scientific publication as explained in the [related section](ImportDoc), we get a Document instance that exposes all the methods needed to access the information extracted from the same paper (title, abstract, sections, bibliography, etc.). Document instance methods returns instances of the objects of the [Scientific Publication Data Model](ScuPubDataModel) defined by the Dr. Inventor Frameowrk: this data model represents by means of java objects all the information mined form a scientific publication.

After explaining how scientific publications are processed, we provide practical example of the different types of information that can be extracted from a scientific publication that has been [imported](ImportDoc) in the Dr. Inventor Framework.

**Please, note that parts of this documentation are currently under editing.**


## Lazy processing
The scientific publication analyses performed by the Dr. Inventor Framework requires execution times that depend on the size of the article to process, on the performance of the computer and on the kind of processing action invoked. As a consequence is normal that some data extraction procedure requires **from seconds to minutes** to be executed. The Dr. Inventor Framework produces on the standard output a log that informs the user on the status of the perocessing actions required on the content of a paper.

The Dr. Inventor Framework implements the following paper analysis principles:

 * after the initial [import](ImportDoc) of the contents of a paper (in PDF or JATS XML format), all the analyses of the publication are **triggered only when the user requires the results by means of one of the methods exposed by the Document instance (lazy processing)**. The first time that a certain type of processing results is required (i.e. sentences of the abstract together with their rhetorical class), their extraction procedure is triggered. As a consequence the first time that a certain type of processing results is required, their retrieval requires a bigger amount of processing time; 
 * the processing results, once computed, are cached and reused for successive requests.


## Get the Header


## Browse the structure of Sections


## Get Sentences: text, rhetorical category, BabelNet concepts, candidate terms.


## Citations and Bibliographic Entries


## The Subject-Verb-Object graph


## Extractive summary


