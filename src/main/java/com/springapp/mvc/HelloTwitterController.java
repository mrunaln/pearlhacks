package com.springapp.mvc;

import org.springframework.scheduling.annotation.Scheduled;
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
    public final String WOMEN      = "women";
    public final String HORMONES   = "hormones";
    public final String MATERNITY  = "maternity";
    public final String PREGNANCY  = "pregnancy";
    public final String MENOPAUSE  = "Menopause";
    public final String FASHION    = "fashion";
    public final String YOGA       = "yoga";
    public final String ABS        = "abs";
    public final String CARDIO     = "cardio";
    public List<Tweet> generalTweets = new ArrayList<Tweet>(100);

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    //@Scheduled(fixedRate = 5000)
    public ModelAndView  GetTweets() {

        initHashTags();
        appToken = fetchApplicationAccessToken(appId, appSecret);
        generalTweets = searchTwitter(hashTag.get(0), appToken);
        //printTweets(generalTweets,hashTag.get(0));

        ModelAndView mav = new ModelAndView();
        mav.addObject(WOMEN,generalTweets);
        return mav;
    }

    @RequestMapping("/hormones")
    public ModelAndView getFilteredTweets(){
        List<Tweet> hormonalTweets = new ArrayList<Tweet>(100);
        hormonalTweets = searchTwitter(hashTag.get(1), appToken);

        //printTweets(hormonalTweets);
        ModelAndView mav = new ModelAndView();
        mav.addObject(HORMONES,hormonalTweets);
        return mav;
    }




    private void initHashTags() {
        hashTag.add("#"+ WOMEN); // general

        hashTag.add("#" + WOMEN + "#" + HORMONES);
        hashTag.add("#" + WOMEN + "#" + PREGNANCY);
        hashTag.add("#" + WOMEN + "#" + MENOPAUSE);

        hashTag.add("#" + WOMEN + "#" + FASHION);

        hashTag.add("#" + WOMEN + "#" + YOGA);
        hashTag.add("#" + WOMEN + "#" + ABS);
        hashTag.add("#" + WOMEN + "#" + CARDIO);
    }


    private void printTweets(List<Tweet> generalTweets, String currenthashT) {
        System.out.println("\n\n============================="+ currenthashT + " count = " + generalTweets.size() +"================================\n\n");
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