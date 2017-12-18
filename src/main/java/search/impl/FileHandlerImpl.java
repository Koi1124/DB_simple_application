package search.impl;

import search.FileHandler;
import vo.Program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileHandlerImpl implements FileHandler {

    @Override
    public int program2File(List<Program> programList) {
        File file = new File("D:/IDEA/homework_4/src/main/resources/University of California--San Diego.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for(Program program:programList) {
                String info = program.getUniversity()+"\t"+program.getCountry()+"\t"+program.getProgramName()+"\t"+program.getSchool()
                        +"\t"+program.getDegree()+"\t"+program.getEmail()+"\t"+program.getPhoneNumber()+"\t"+program.getLocation()+"\t"
                        +program.getDeadlineWithAid()+"\t"+program.getDeadlineWithoutAid()+"\t"+program.getHomepage();
                pw.println(info);
                pw.flush();
            }
            pw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return programList.size();
    }
}
