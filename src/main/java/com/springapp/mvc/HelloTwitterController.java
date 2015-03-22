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
import org.springframework.web.client.RestTemplate;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

@Controller
@RequestMapping("/")
public class HelloTwitterController {
    final String appId = "FIXME";
    final String appSecret = "FIXME";
    String appToken = new String();
    List<String> hashTag= new ArrayList<String>(20);

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public void printWelcome(ModelMap model) {
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
        for (String hashT : hashTag){
            List<Tweet> generalTweets = searchTwitter(hashT, appToken);
            printTweets(generalTweets,hashT);
        }

        //model.addAttribute("tweet", tweets.get(0).getText());
        //return tweets.get(0).getText();
    }


    private void printTweets(List<Tweet> generalTweets, String currenthashT) {
        System.out.println("\n\n============================="+currenthashT +"================================\n\n");
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

    private static final class Tweet {
        private long id;

        private String text;

        public Tweet(long id, String text) {
            this.id = id;
            this.text = text;
        }

        public long getId() {
            return id;
        }

        public String getText() {
            return text;
        }
    }
}