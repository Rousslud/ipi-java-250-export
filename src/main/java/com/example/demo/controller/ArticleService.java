package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Article;

public class ArticleService {
	/*@GetMapping("/article")
	public ModelAndView displayArticle(Map<String, Object> model) {
	 
	    /*List<Article> articles = IntStream.range(0, 10)
	      .mapToObj(i -> generateArticle("Article Title " + i))
	      .collect(Collectors.toList());
	 
	    model.put("articles", articles);
	 
	    return new ModelAndView("index", model);
	}*/
}
