package sokoban;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class LevelReader {
	private String inputLevel = "";

	Scanner sc;

	public LevelReader(Scanner sc) {
		this.sc = sc;
		selectFile();
	}

	private void selectFile(){
		System.out.println("Welches Level?");
		String folder = System.getProperty("user.dir");
		String level = sc.next();
		File file = new File(folder + "/levels/" + level + ".txt");
		readFile(file);
	}

	private void selectFileGUI() {
		//Nach Textdateien filtern
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdatei", "txt");

		//Um Dialog zu offnen
		JFileChooser chooser = new JFileChooser();

		chooser.setFileFilter(filter);

		/* Abfrage, ob auf "�ffnen" geklickt wurde */
		if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
			readFile(chooser.getSelectedFile());
		}
	}

	private void readFile(File file){
		try(Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				//Zeile plus X als mMrker �bernehmen
				this.inputLevel += sc.nextLine() + "X";
			}

			if(this.inputLevel == "")
				throw new NoSuchElementException();

		} catch(FileNotFoundException e){
			System.err.println("Level nicht gefunden!");
		} catch(NoSuchElementException e) {
			System.err.println("Keine Zeichen gelesen!");
		}
	}

	@Override
	public String toString(){
		return this.inputLevel;
	}

}
