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
////		run.setText("Margit �rtel "); // schreibe Text
////		run.setText("K�chenstudio Tischlerei GmbH");
////		run.addBreak(); // n�chste Zeile
////		run.setText("wird verwaltet von 'KSM - K�chenstudiomanager'");
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
