package edu.upf.taln.dri.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.upf.taln.dri.lib.Factory;
import edu.upf.taln.dri.lib.exception.DRIexception;
import edu.upf.taln.dri.lib.model.Document;
import edu.upf.taln.dri.lib.model.ext.Citation;
import edu.upf.taln.dri.lib.model.ext.Section;
import edu.upf.taln.dri.lib.model.ext.Sentence;
import edu.upf.taln.dri.lib.model.ext.SentenceSelectorENUM;
import edu.upf.taln.dri.lib.model.ext.SummaryTypeENUM;
import edu.upf.taln.dri.lib.model.util.DocParse;
import edu.upf.taln.dri.lib.util.ModuleConfig;
import edu.upf.taln.dri.lib.util.PDFtoTextConvMethod;


/**
 * This class shows how to import a scientific publication in the
 * Dr. Inventor Text Mining Framework.
 * @author Francesco Ronzano
 *
 */
public class ParsePaper {

	public static void main(String[] args) {

		Logger.getRootLogger().setLevel(Level.INFO);

		// 1) Set the full path to the Dr. Inventor Framework Property files
		Factory.setDRIPropertyFilePath("/YOUR/LOCAL/PATH/TO/DRIconfig.properties");

		// 2) Programmatically configure the PDF processing options (http://driframework.readthedocs.io/en/latest/Initialize/)
		Factory.setPDFtoTextConverter(PDFtoTextConvMethod.GROBID);

		// 3) Choose the set of scientific text mining modules to use when analyzing scientific (http://driframework.readthedocs.io/en/latest/Initialize/)
		//      3.1) Instantiate the ModuleConfig class - the constructor sets all modules enabled by default
		ModuleConfig modConfigurationObj = new ModuleConfig();

		//      3.2.A) Enable the parsing of bibliographic entries by means of online services (Bibsonomy, CrossRef, FreeCite, etc.)
		modConfigurationObj.setEnableBibEntryParsing(false); // Set to false in order no to parse bibliographic entries

		//      3.2.B) Enable BabelNet Word Sense Disambiguation and Entity Linking over the text of the paper
		//      You should provide your BabelNet API key in the Dr. Inventor Framework Property files to use this feature
		modConfigurationObj.setEnableBabelNetParsing(true);

		//      3.2.C) Enable the parsing of the information from the header of the paper by means of online services (Bibsonomy, CrossRef, FreeCite, etc.)
		modConfigurationObj.setEnableHeaderParsing(false);

		//      3.2.D) Enable the extraction of candidate terms from the sentences of the paper
		modConfigurationObj.setEnableTerminologyParsing(false);

		//      3.2.E) Enable the dependency parsing of the sentences of a paper
		modConfigurationObj.setEnableGraphParsing(true);

		//      3.2.F) Enable coreference resolution
		modConfigurationObj.setEnableCoreferenceResolution(false);

		//      3.2.G) Enable the extraction of causal relations
		modConfigurationObj.setEnableCausalityParsing(false);

		//      3.2.H) Enable the association of a rhetorical category to the sentences of the paper
		modConfigurationObj.setEnableRhetoricalClassification(true);

		// 3.3) Improt the configuration parameters set in the ModuleConfig instance
		Factory.setModuleConfig(modConfigurationObj); 

		// 4) Initialize the library - pre-load the resources needed to process scientific publications
		try {
			Factory.initFramework();
		} catch (DRIexception e) {
			System.out.println("Error while initializing the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		// 5.A) Load PDF from URL
		Document doc_PDFpaperURL = null;
		try {
			doc_PDFpaperURL = Factory.getPDFloader().parsePDF(new URL("http://cs.unibo.it/save-sd/2016/papers/pdf/ronzano-savesd2016.pdf"));
		} catch (DRIexception e) {
			System.out.println("Error while importing a PDF file in the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Error with URL format.");
			e.printStackTrace();
		}

		// 5.B) Load PDF from URL
		Document doc_JATSpaperURL = null;
		try {
			doc_JATSpaperURL = Factory.getJATSloader().parseJATS(new URL("http://journals.plos.org/plosone/article/asset?id=10.1371/journal.pone.0138120.XML"));
		} catch (DRIexception e) {
			System.out.println("Error while importing a PDF file in the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Error with URL format.");
			e.printStackTrace();
		}

		// 5.C) Load PDF from local file
		Document doc_PDFpaperFILE = null;
		try {
			String PDF_FILE_PATH = "/YOUR/LOCAL/PATH/TO/a_PDF_file.pdf";
			doc_PDFpaperFILE = Factory.getPDFloader().parsePDF(PDF_FILE_PATH);
		} catch (DRIexception e) {
			System.out.println("Error while importing a PDF file in the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		// 6) Pre-process the PDF file by means of the enabled modules
		try {
			doc_PDFpaperURL.preprocess();
		} catch (DRIexception e) {
			System.out.println("Error while preprocessing a PDF file by means of the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		try {
			// 7) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Sentence> abstract_SentList = doc_PDFpaperURL.extractSentences(SentenceSelectorENUM.ONLY_ABSTRACT);
			for(Sentence abstract_Sent : abstract_SentList) {
				System.out.println("ABSTRACT SENTENCE > " + abstract_Sent.asString(true));
			}

			// 8) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Citation> citsList = doc_PDFpaperURL.extractCitations();
			for(Citation cit : citsList) {
				System.out.println("CITATION INFO > " + cit.asString(true));
			}

			// 9) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Section> rootSectsList = doc_PDFpaperURL.extractSections(true);
			for(Section rootSect : rootSectsList) {
				System.out.println("ROOT SECTION > " + rootSect.asString(true));
			}

			// 10.A) Get summary by ranking sentences by the centroid method
			List<Sentence> summarySentences_CENTROID = doc_PDFpaperURL.extractSummary(20, SummaryTypeENUM.CENTROID_SECT);
			for(Sentence sent : summarySentences_CENTROID) {
				System.out.println(sent.getText());
			}

			// 10.B) Get summary by ranking sentences by the title
			List<Sentence> summarySentences_TITLE = doc_PDFpaperURL.extractSummary(20, SummaryTypeENUM.TITILE_SIM);
			for(Sentence sent : summarySentences_TITLE) {
				System.out.println(sent.getText());
			}

			// 11) Get the ROS CSV of the ABSTRACT
			String abstractCSV_ROS = DocParse.getDocumentROSasCSVstring(doc_PDFpaperURL, SentenceSelectorENUM.ONLY_ABSTRACT);
			System.out.println(abstractCSV_ROS);

			// 12) Get raw text
			String rawText = doc_PDFpaperURL.getRawText();

			// 13) Get XML representation of the paper (GATE XML)
			String XMLText = doc_PDFpaperURL.getXMLString();
			
			// ******************************************************************************************
			// ******************************************************************************************

			// 14) Parse text
			String plainTextToParse = "During the last decade the amount of scientific articles available online has substantially"
					+ " grown in parallel with the adoption of the Open Access publishing model. Nowadays researchers, as well as"
					+ " any other interested actor, are often overwhelmed by the enormous and continuously growing amount of publications"
					+ " to consider in order to perform any complete and careful assessment of scientific literature. As a consequence,"
					+ " new methodologies and automated tools to ease the extraction, semantic representation and browsing of information"
					+ " from papers are necessary. We propose a platform to automatically extract, enrich and characterize several structural"
					+ " and semantic aspects of scienti c publications, representing them as RDF datasets. We analyze papers by relying on"
					+ " the scientific Text Mining Framework developed in the context of the European Project Dr. Inventor. We evaluate how"
					+ " the  Framework supports two core scientific text analysis tasks: rhetorical sentence classi cation and extractive"
					+ " text summarization. To ease the exploration of the distinct facets of scientific knowledge extracted by our platform,"
					+ " we present a set of tailored Web visualizations. We provide on-line access to both the RDF datasets and the Web"
					+ " visualizations generated by mining the papers of the 2015 ACL-IJCNLP Conference.";
			Document plainTextDocument = Factory.getPlainTextLoader().parseString(plainTextToParse, "PUT_YOUR_DOCUMENT_NAME");

			// 15) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Sentence> document_SentList = plainTextDocument.extractSentences(SentenceSelectorENUM.ALL);
			for(Sentence doct_Sent : document_SentList) {
				System.out.println("PLAIN TEXT SENTENCE > " + doct_Sent.asString(true));
			}

			// 16.A) Get summary by ranking sentences by the centroid method
			List<Sentence> textSummary_CENTROID = plainTextDocument.extractSummary(20, SummaryTypeENUM.CENTROID_SECT);
			for(Sentence sent : textSummary_CENTROID) {
				System.out.println(sent.getText());
			}

			// 16.B) Get summary by ranking sentences by the title
			List<Sentence> textSummary_TITLE = plainTextDocument.extractSummary(20, SummaryTypeENUM.TITILE_SIM);
			for(Sentence sent : textSummary_TITLE) {
				System.out.println(sent.getText());
			}

			// 17) Get the ROS CSV of the ABSTRACT
			String documentCSV_ROS = DocParse.getDocumentROSasCSVstring(plainTextDocument, SentenceSelectorENUM.ONLY_ABSTRACT);
			System.out.println(documentCSV_ROS);


		} catch (DRIexception e) {
			System.out.println("Error while extracting data from PDF file by means of the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}
	}

}
