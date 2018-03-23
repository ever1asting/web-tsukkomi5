package person.controller;

import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/flash")
public class FlashController {
    @ResponseBody
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index() {
        String html = "<h1>游戏策划demo</h1><p>尹航，yh17624096642@163.com</p>";
        String staticPath = System.getProperty("user.dir") + "/src/main/resources/static/";
        File flashFolder = new File(staticPath + "flash");
        for (File flashFile: flashFolder.listFiles()) {
            if (flashFile.isDirectory()) {
                // pass
            } else {
                String fileName = flashFile.getName();
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                html += "<a href=\"/flash/play/" + fileName + "\">" + fileName + "</a><br />";
            }
        }
        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/play/{flashFileName}", method = RequestMethod.GET)
    public String play(@PathVariable("flashFileName") String flashFileName) {
        String html = "<h1>" + flashFileName + "</h1>";
        html += "<p>flash加载较慢请耐心等待</p><br />";
        html += "<embed src=\"/flash/" + flashFileName + ".swf\" width=\"600\" height=\"500\"" +
                "type=\"application/x-shockwave-flash\"></embed>";
        return html;
    }
}
