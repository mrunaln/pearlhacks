package com.pearlhacks.test.RootController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mrunalnargunde on 3/20/15.
 */

@RestController
public class RootController {

    @RequestMapping("/")
    public @ResponseBody String home(){
        return "Hello; World";
    }
}
