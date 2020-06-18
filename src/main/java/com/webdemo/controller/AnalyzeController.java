package com.webdemo.controller;

import com.webdemo.Mapper.StudentMapper;
import com.webdemo.pojo.Allinformation;
import com.webdemo.pojo.Analyze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Controller
public class AnalyzeController {
    @Autowired
    private StudentMapper stumapper;

    @GetMapping("/analyze/analyze.html")
    public String analyze(Model model)
    {
        List<Analyze> a2016=stumapper.queryAnalyzeDestination("2016");
        List<Analyze> a2017=stumapper.queryAnalyzeDestination("2017");
        List<Analyze> a2018=stumapper.queryAnalyzeDestination("2018");
       // System.out.println(a2016);
        List<String> a2016f=getName(a2016);
        int[] a2016s = getSum(a2016);
        model.addAttribute("af2016",a2016f);
        model.addAttribute("as2016",a2016s);

        List<String> a2017f=getName(a2017);
        int[] a2017s = getSum(a2017);
        model.addAttribute("af2017",a2017f);
        model.addAttribute("as2017",a2017s);

        List<String> a2018f=getName(a2018);
        int[] a2018s = getSum(a2018);
        model.addAttribute("af2018",a2018f);
        model.addAttribute("as2018",a2018s);
        return "analyze/analyze";
    }

    @GetMapping("/Ditu")
    public String analyzeDitu(Model model)
    {
        List<Analyze> ditu=stumapper.queryAnalyzePlace();
        List<String> ditun=getName(ditu);
        int[] ditus = getSum(ditu);
        model.addAttribute("ditun",ditun);
        model.addAttribute("ditus",ditus);
        return "analyze/test";
    }
    @GetMapping("/Bingtu/{year}")
    public String analyzeBingtu(@PathVariable("year")int year, Model model)
    {

        List<Analyze> bingtu=stumapper.queryAnalyzeVcompany(year);
        List<String> bingtun=getName(bingtu);
        int[] bingtus = getSum(bingtu);
        model.addAttribute("bingtun",bingtun);
        model.addAttribute("bingtus",bingtus);
        model.addAttribute("year",year);
        return "analyze/analyze2";
    }
    @GetMapping("/Zhexian/{Ccompany}")
    public String analyzeZhexian(@PathVariable("Ccompany")String Ccompany,Model model)
    {
        List<Analyze> zhexian=stumapper.queryAnalyzeYearByCcompany(Ccompany);
        System.out.println(zhexian);
        List<String> zhexiann=getName(zhexian);
        int[] zhexians = getSum(zhexian);
        List<Allinformation> Allccompany=stumapper.queryCcompanyAll();
        model.addAttribute("zhexiann",zhexiann);
        model.addAttribute("zhexians",zhexians);
        model.addAttribute("Ccompany",Ccompany);
        model.addAttribute("Allccompany",Allccompany);
        return "analyze/dashboard";
    }

    public int[] getSum(List<Analyze> a)
    {
        int[] a1 = new int[a.size()];
        for(int i = 0;i < a.size();i++) {
            a1[i]=a.get(i).getSumm();
        }

        return a1;
    }
    public List<String> getName(List<Analyze> a)
    {
        //System.out.println("测试：");
        List<String> a1=new ArrayList<>();
        for(int i = 0;i < a.size();i++) {
            a1.add(a.get(i).getName());
            //System.out.println(a.get(i).getName());
        }
        return a1;
    }
}
