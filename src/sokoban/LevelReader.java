package sokoban;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class LevelReader {
	private String inputLevel = "";
	
	public LevelReader() {
		selectFile();
	}
	
	private void selectFile() {
		//Nach Textdateien filtern
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdatei", "txt");
		
		//Um Dialog zu offnen
		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileFilter(filter);
		
		/* Abfrage, ob auf "Öffnen" geklickt wurde */
		if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
			readFile(chooser.getSelectedFile());
		}
	}
	
	private void readFile(File file){
		try(Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				//Zeile plus X als mMrker übernehmen
				this.inputLevel += sc.nextLine() + "X";
			}
			
			if(this.inputLevel == "")
				throw new NoSuchElementException();
			
		} catch(FileNotFoundException e){
			System.err.println("Datei nicht gefunden!");
		} catch(NoSuchElementException e) {
			System.err.println("Keine Zeichen gelesen!");
		}
	}

	@Override
	public String toString(){
		return this.inputLevel;
	}

}
