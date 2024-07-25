/**
 *
 * @author rohit
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class TextFunction {
    FileWriter fw;
    FileInputStream fin;
    DataInputStream dis = new DataInputStream(System.in);

    public void createFile(String location) throws IOException {
        fw = new FileWriter(location);
        char ch;
        System.out.println("\nEnter @ to end");
        while ((ch = (char) dis.read()) != '@') {
            fw.write(ch);
        }
        fw.close();
    }

    public void openFile(String location) throws IOException {
        System.out.println("\n\n");
        fin = new FileInputStream(location);
        int ch;
        while ((ch = fin.read()) != -1) {
            System.out.print((char) ch);
        }
        fin.close();
        System.out.println("\n");
    }

    public void appendContent(String location) throws IOException {
        fw = new FileWriter(location, true);
        char ch;
        System.out.println("Enter @ to end");
        while ((ch = (char) dis.read()) != '@') {
            fw.write(ch);
        }
        fw.close();
    }
}

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextFunction t = new TextFunction();
        String location = "";
        System.out.println("Enter 'create filename' to create a file");
        System.out.println("Enter 'open filename' to read the contents of the file");
        System.out.println("Enter 'append filename' to add content to an existing file");
        System.out.println("Enter 'exit' to quit");
        int flag=0;
        String choice = "";
        while (true) {
            System.out.println("\nEnter the location of the file you wish to open:");
            System.out.println("\nIf you don't know the file location, enter 'browse' to navigate to the file location in explorer , exit to quit");
            System.out.print("$ ");
            if(flag==1){
            location = sc.nextLine();
            location = sc.nextLine();
            }
            else
               location = sc.nextLine(); 

            if (location.equalsIgnoreCase("exit")) {
                break;
            }
             flag=0;
            if (location.equalsIgnoreCase("browse")) {
                String[] selectedPath = new String[1];
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                           // FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                            //fileChooser.setFileFilter(filter);
                            int returnValue = fileChooser.showOpenDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = fileChooser.getSelectedFile();
                                selectedPath[0] = selectedFile.getAbsolutePath();
                            }
                        }
                    });
                    location = selectedPath[0];
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("enter the action u want to perform , enter the filename with extension");
            System.out.print("$ ");
            choice = sc.nextLine();
             if (choice.equalsIgnoreCase("exit"))
                break;
            try {
                if (choice.startsWith("create ")) {
                    flag=1;
                    t.createFile(location +"\\"+ choice.substring(7).trim());
                } else if (choice.startsWith("open ")) {
                    t.openFile(location +"\\"+ choice.substring(5).trim());
                } else if (choice.startsWith("append ")) {
                     flag=1;
                    t.appendContent(location + choice.substring(7).trim());
                } else {
                    System.out.println("Invalid command");
                }
            } catch (IOException e) {
                System.out.println("File not found. Check the address.");
            }
        }
    }
}