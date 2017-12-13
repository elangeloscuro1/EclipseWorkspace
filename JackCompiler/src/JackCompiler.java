
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.PrintWriter ;
import java.util.Scanner ;

import javax.swing.JFileChooser ;
import javax.swing.JOptionPane ;
import javax.swing.filechooser.FileFilter ;
import javax.swing.filechooser.FileNameExtensionFilter ;
import javax.swing.filechooser.FileSystemView ;

import asm2hack.Assembler ;
import jack2vm.JackComplier ;
import vm2asm.VMtranslator ;

public class JackCompiler
{

	public static void main(String[] args)
	{
		File dir = new File("dir.txt") ;
		JFileChooser fileChooser = null ;
		try
		{
			dir.createNewFile() ;
			Scanner scanDir = new Scanner(dir) ;
			if(!scanDir.hasNext())
			{
				fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()) ;			
			}
			else
			{
				fileChooser = new JFileChooser(scanDir.nextLine()) ;
			}			
			scanDir.close() ;
			FileFilter filter = new FileNameExtensionFilter("vmCode", "jack", "vm", "asm");
			fileChooser.setFileFilter(filter) ;
			fileChooser.setMultiSelectionEnabled(true) ;
			fileChooser.showOpenDialog(null) ;
			
			PrintWriter writer  = new PrintWriter(dir) ;
			writer.println(fileChooser.getSelectedFile().getParentFile()) ;
			writer.flush() ;
			writer.close() ;
			
			for (File file : fileChooser.getSelectedFiles())
			{
				dir = new File(file.getAbsolutePath()) ;
				String ext = file.getName().substring(file.getName().lastIndexOf(".")) ;				
				char key = file.getName().substring(file.getName().lastIndexOf(".")).charAt(1) ;
				switch (key)
				{
					case 'j':
						if (ext.equalsIgnoreCase(".jack"))
						{
							new JackComplier(file.getAbsolutePath()) ;
							JOptionPane.showMessageDialog(null, file.getName() + " successfully converted to .vm!!!") ;
							break ;
						}
						
					case 'v':
						if (ext.equalsIgnoreCase(".vm"))
						{
							new VMtranslator(file.getAbsolutePath()) ;
							JOptionPane.showMessageDialog(null, file.getName() + " successfully converted to .asm!!!") ;
							break ;
						}
					case 'a':
						if (ext.equalsIgnoreCase(".asm"))
						{
							new Assembler(file.getAbsolutePath()) ;
							JOptionPane.showMessageDialog(null, file.getName() + " successfully converted to .hack!!!") ;
							break ;
						}					
					default:
						JOptionPane.showMessageDialog(null, file.getName() + " is not a .jack/.vm/.asm!!") ;
						break ;
				}
			}			
		}
		catch (Exception e)
		{
			//e.printStackTrace() ;
			if (e.getMessage() != null)
			JOptionPane.showMessageDialog(null, dir.getName() + ": " +  e.getMessage()) ;  
		}		
		
		



		
		

	}
}
