package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HelloTwitterController {
    final String appId = "akXupoEf39G3plfNdFQ0RohpO";
    final String appSecret = "sxdp8Vw9A220LDmZE8Y3ZLexTt641qRqqWxrnP7wUo5jhY0I2f";
    String appToken = new String();
    List<String> hashTag= new ArrayList<String>(8);
    final String WOMEN = "women";
    final String HORMONES = "hormones";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView printWelcome() {
        //model.addAttribute("message", "Hello world!");
        //return "hello";
        hashTag.add("#women"); // general
        hashTag.add("#women #hormones");
        hashTag.add("#women #pregnancy #maternity");
        hashTag.add("#women #menopause");

        hashTag.add("#women #fashion");

        hashTag.add("#women #yoga");
        hashTag.add("#women #abs");
        hashTag.add("#women #cardio");

        appToken = fetchApplicationAccessToken(appId, appSecret);
        List<Tweet> generalTweets = new ArrayList<Tweet>();
       // for (int i = 0 ; i < hashTag.size(); i++){
            generalTweets = searchTwitter(hashTag.get(0), appToken);
            printTweets(generalTweets,hashTag.get(0));

        //}
        ModelAndView mav = new ModelAndView();
        mav.addObject("tweet",generalTweets);
        //generalTweets.clear();
        return mav;
        //model.addAttribute("tweet", tweets.get(0).getText());
        //return tweets.get(0).getText(        model.addAttribute(WOMEN, generalTweets);

    }


    private void printTweets(List<Tweet> generalTweets, String currenthashT) {
        System.out.println("\n\n============================="+currenthashT + " count = " + generalTweets.size() +"================================\n\n");
        for (Tweet tweet : generalTweets) {
            System.out.println(tweet.getText());
        }
    }


    private static List<Tweet> searchTwitter(String query, String appToken) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + appToken);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        Map<String, ?> result = rest.exchange("https://api.twitter.com/1.1/search/tweets.json?q={query}", HttpMethod.GET, requestEntity, Map.class, query).getBody();
        List<Map<String, ?>> statuses = (List<Map<String, ?>>) result.get("statuses");
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Map<String, ?> status : statuses) {
            tweets.add(new Tweet(Long.valueOf(status.get("id").toString()), status.get("text").toString()));
        }
        return tweets;
    }

    private static String fetchApplicationAccessToken(String appId, String appSecret) {
        OAuth2Operations oauth = new OAuth2Template(appId, appSecret, "", "https://api.twitter.com/oauth2/token");
        return oauth.authenticateClient().getAccessToken();
    }

    private static String promptForInput(String promptText) {
        return JOptionPane.showInputDialog(promptText + " ");
    }
}