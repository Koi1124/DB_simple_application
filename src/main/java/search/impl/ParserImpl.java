package search.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import vo.Program;

import java.io.IOException;
import java.util.UUID;


public class ParserImpl implements Parser {
    @Override
    public Program parseHtml(String html) {
        Program program = new Program();
        try {
            Document doc = Jsoup.parse(html);
            Elements proInfo = doc.select("div > div.col-sm-8 > table:nth-child(2) > tbody > tr");
            for(Element e:proInfo){
                String title = e.select("td").first().text();
                switch (title) {
                    case"Department":program.setSchool(e.select("td").last().text().trim());break;
                    case"Major":program.setProgramName(e.select("td").last().text().trim());break;
                    case"Degree":program.setDegree(e.select("td").last().text().trim());break;
                    case"Application Deadlines":program.setDeadlineWithAid(e.select("td").last().text().trim());program.setDeadlineWithoutAid(e.select("td").last().text());break;
                    default:break;
                }
            }
            Elements conInfo = doc.select("div > div.col-sm-4 > table > tbody > tr > td");
            for(Element e:conInfo){
                String title = e.select("b").text();
                switch (title) {
                    case"Website":program.setHomepage(e.select("a").text().trim());break;
                    case"Email":program.setEmail(e.select("a").text().trim());break;
                    case"Phone":program.setPhoneNumber(e.text().replace("Phone","").trim());break;
                    case"Mailing Address":program.setLocation(e.text().replace("Mailing Address","").trim());break;
                    default:break;
                }
            }
            String id = UUID.randomUUID().toString().trim().replaceAll("-","");
            program.setId(id);
            if(program.getPhoneNumber()==null) {
                program.setPhoneNumber("NULL");
            }
            if(program.getLocation()==null){
                program.setLocation("NULL");
               }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return program;
    }
}
