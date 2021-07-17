package com.example.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Controller
public class WebController {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase processes = mongoClient.getDatabase("processes");
    MongoCollection<Document> collection = processes.getCollection("names");

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("user")
    @ResponseBody
    public Document users() {

        System.out.println("Total docs: " + collection.countDocuments());

        Document first = collection.find(eq("name", "Andrii")).first();
        return first;
    }
//
//    @RequestMapping("/")
//    @ResponseBody
//    public String welcome() {
//        return "index";
//    }


    @RequestMapping("/")
    @ResponseBody
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

}
