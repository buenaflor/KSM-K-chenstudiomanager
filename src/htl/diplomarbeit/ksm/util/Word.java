//package htl.diplomarbeit.ksm.util;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.poifs.filesystem.DocumentEntry;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//
//public class Word {
////	public static void createWordDocumentRechnung(String dateiname){
////		XWPFDocument document = new XWPFDocument(); // neues Dokument erstellen
////
////		XWPFParagraph paragraph = document.createParagraph(); // neuen Paragraph erstellen
////		
////		XWPFRun run = paragraph.createRun();
////
////		run.setText("Margit Örtel "); // schreibe Text
////		run.setText("Küchenstudio Tischlerei GmbH");
////		run.addBreak(); // nächste Zeile
////		run.setText("wird verwaltet von 'KSM - Küchenstudiomanager'");
////		
////		try {
////			FileOutputStream output = new FileOutputStream(dateiname);
////			document.write(output);
////			output.close();
////			System.out.println(dateiname + " wurde erstellt.");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//}
