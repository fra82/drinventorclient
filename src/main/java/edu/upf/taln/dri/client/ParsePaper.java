package edu.upf.taln.dri.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import edu.upf.taln.dri.lib.exception.DRIexception;
import edu.upf.taln.dri.lib.model.Document;
import edu.upf.taln.dri.lib.model.ext.Citation;
import edu.upf.taln.dri.lib.model.ext.Section;
import edu.upf.taln.dri.lib.model.ext.Sentence;
import edu.upf.taln.dri.lib.model.ext.SentenceSelectorENUM;
import edu.upf.taln.dri.lib.model.ext.SummaryTypeENUM;
import edu.upf.taln.dri.lib.model.util.DocParse;
import edu.upf.taln.dri.lib.util.PDFtoTextConvMethod;


/**
 * This class shows how to import a scientific publication in the
 * Dr. Inventor Text Mining Framework.
 * @author Francesco Ronzano
 *
 */
public class ParsePaper {

	public static void main(String[] args) {
		
		Logger.getRootLogger().setLevel(Level.ALL);
		
		// 1) Set the property folder
		Factory.setDRIPropertyFilePath("/home/francesco/Desktop/DRILIB_EXP/DRIconfig.properties");

		// 2) Programmatically configure the PDF processing options 
		Factory.setPDFtoTextConverter(PDFtoTextConvMethod.GROBID);
		Factory.setEnableBibEntryParsing(false);

		// 3) Initialize the library - preload the resources needed to process scientific publications
		try {
			Factory.initFramework();
		} catch (DRIexception e) {
			System.out.println("Error while initializing the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		// 4.A) Load PDF from URL
		Document doc_PDFpaperURL = null;
		try {
			doc_PDFpaperURL = Factory.getPDFloader().parsePDF(new URL("http://www2007.org/workshops/paper_45.pdf"));

		} catch (DRIexception e) {
			System.out.println("Error while importing a PDF file in the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Error with URL format.");
			e.printStackTrace();
		}

		// 4.B) Load PDF from URL
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

		// 4.C) Load PDF from local file
		Document doc_PDFpaperFILE = null;
		try {
			String PDF_FILE_PATH = "/home/francesco/Downloads/APPO_DRI_PARSING/NEW_FOLDER/paper3.pdf";
			doc_PDFpaperFILE = Factory.getPDFloader().parsePDF(PDF_FILE_PATH);
		} catch (DRIexception e) {
			System.out.println("Error while importing a PDF file in the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		// 5) Pre-process PDF file
		try {
			doc_PDFpaperFILE.preprocess();
		} catch (DRIexception e) {
			System.out.println("Error while preprocessing a PDF file by means of the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}

		try {
			// 6) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Sentence> abstract_SentList = doc_PDFpaperFILE.extractSentences(SentenceSelectorENUM.ONLY_ABSTRACT);
			for(Sentence abstract_Sent : abstract_SentList) {
				System.out.println("ABSTRACT SENTENCE > " + abstract_Sent.asString(true));
			}

			// 7) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Citation> citsList = doc_PDFpaperFILE.extractCitations();
			for(Citation cit : citsList) {
				System.out.println("CITATION INFO > " + cit.asString(true));
			}

			// 7) Extract abstract sentences from a Dr. Inventor Document (ordered)
			List<Section> rootSectsList = doc_PDFpaperFILE.extractSections(true);
			for(Section rootSect : rootSectsList) {
				System.out.println("ROOT SECTION > " + rootSect.asString(true));
			}

			// 8.A) Get summary by ranking sentences by the centroid method
			List<Sentence> summarySentences_CENTROID = doc_PDFpaperFILE.extractSummary(20, SummaryTypeENUM.CENTROID_SECT);
			for(Sentence sent : summarySentences_CENTROID) {
				System.out.println(sent.getText());
			}

			// 8.B) Get summary by ranking sentences by the title
			List<Sentence> summarySentences_TITLE = doc_PDFpaperFILE.extractSummary(20, SummaryTypeENUM.TITILE_SIM);
			for(Sentence sent : summarySentences_TITLE) {
				System.out.println(sent.getText());
			}

			// 9) Get the ROS CSV of the ABSTRACT
			String abstractCSV_ROS = DocParse.getDocumentROSasCSVstring(doc_PDFpaperFILE, SentenceSelectorENUM.ONLY_ABSTRACT);
			System.out.println(abstractCSV_ROS);

			// 10) Get raw text
			String rawText = doc_PDFpaperFILE.getRawText();

			// 11) Get XML representation of the paper (GATE XML)
			String XMLText = doc_PDFpaperFILE.getXMLString();

		} catch (DRIexception e) {
			System.out.println("Error while extracting data from PDF file by means of the Dr. Inventor Text Mining Framework!");
			e.printStackTrace();
		}
	}

}
