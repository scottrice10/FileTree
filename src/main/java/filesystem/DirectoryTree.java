/*
 In-memory Filesystem
 Scott Rice
*/

package filesystem;

import com.sun.deploy.util.StringUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DirectoryTree {

    private static Directory root;

    public static void main(File fileName) {
        Scanner in;
        try{
            in = new Scanner(new FileReader(fileName));
            root = new Directory("/");

            while(in.nextLine() != null){
                processLine(in.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processLine(String line){
        String[] lineArr = line.split("\\s+");
        String command = lineArr[0];

        if(command.equals("cd")){
            cd(lineArr);
        }

        if(command.equals("mkdir")){
            mkdir(lineArr);
        }

        if(command.equals("rmdir")){
            rmdir(lineArr);
        }

        if(command.equals("pwd")){
            pwd();
        }

        if(command.equals("ls")){
            ls(lineArr);
        }
    }

    public static void cd(String[] directories){
        if(directories[1].equals("..")){
            root = root.getParent();
        } else {
            for(Directory dir : root.getChildren()){
                if(dir.getData().equals(directories[1])){
                    root = dir;
                }
            }
        }
    }

    public static void mkdir(String[] directories){
        Directory newDir = new Directory(directories[1]);
        root.addChild(newDir);
    }

    public static void rmdir(String[] directories){
        Directory childToRemove = null;
        for(Directory dir : root.getChildren()){
            if(dir.getData().equals(directories[1])){
                childToRemove = dir;
            }
        }

        if(childToRemove != null){
            root.removeChild(childToRemove);
        }
    }

    public static void pwd(){
        String currDir = "";

        while(root.getParent() != null){
            currDir += root.getData();
        }

        System.out.println(currDir);
    }

    public static void ls(String[] directories){
        System.out.println(StringUtils.join(Arrays.asList(directories), " "));
    }
}
