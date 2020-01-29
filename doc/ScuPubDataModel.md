The results of the analysis of a scientific publications by means of the Dr. Inventor Framework are accessible programmatically thanks to a set of classes that are referred to as the Scientific Publication Data Model. These classes model a scientific document together with its structural and semantic elements: title, abstract, headers, sections, sentences, bibliographic entries, inline citations, terms, etc. Each class of the Scientific Publication Data Model exposes a set of methods that are useful to access the metadata and the semantic features of the specific element of scientific publications under consideration. For instance, the Sentence class represents a sentence of a paper (each instance of the class a specific sentence) and exposes methods to retrieve the text of the sentence, the section in which the sentence occurs, the rhetorical class of the sentence, the inline citations included in the sentence.

After reading this section, the best approach to understand the structure of the Scientific Publication Data Model of the Dr. Inventor Framework is to browse all the classes included in the _edu.upf.taln.dri.lib.model_ package (and sub-packages) of the [Javadoc](http://taln.upf.edu/drinventor/doc/).

**Please, note that parts of this documentation are currently under editing.**


## The IDs of the model instances: unambiguous identification and cross-reference of elements


## The core object: Document




