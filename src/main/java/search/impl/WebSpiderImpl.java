package search.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import search.WebSpider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebSpiderImpl implements WebSpider {


    @Override
    public List<String> getHtmlFromWeb() {
        final String url="https://apply.grad.ucsd.edu/masters-programs";
        List<String> htmlStr=new ArrayList<>();
        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.drawer div p:nth-child(1) a");
            for(Element element:elements) {
                String programmeUrl = element.absUrl("href");
                Document document = Jsoup.connect(programmeUrl).get();
                Elements id = document.select("div.active");
                for(Element e:id) {
                    Element html = document.selectFirst("#" + e.attr("id"));
                    htmlStr.add(html.html());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return htmlStr;
    }

    @Override
    public Parser getParser() {
        Parser parser = new ParserImpl();
        return parser;
    }
}
