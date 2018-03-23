package person.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/waterfall")
public class WaterfallController {
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("waterfall");
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public JSONObject getImage() {
        JSONArray dataArray = new JSONArray();
        String staticPath = System.getProperty("user.dir") + "/src/main/resources/static/";
        File waterfallFolder = new File(staticPath + "image/waterfall");

        // naive random strategy
        File[] images = waterfallFolder.listFiles();
        int DATA_SIZE = 1;
        Random rand = new Random();
        for (int i = 0; i < DATA_SIZE; ++i) {
            File image = images[rand.nextInt(images.length)];
            if (image.isDirectory()) {
                i -= 1;
                continue;
            }
            JSONObject obj = new JSONObject();
            obj.put("src", image.getName());
            dataArray.add(obj);
        }

        JSONObject ret = new JSONObject();
        ret.put("data", dataArray);
        return ret;
    }
}
