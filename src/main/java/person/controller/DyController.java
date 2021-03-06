package person.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dy")
public class DyController {
    @ResponseBody
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index() {
        String html = "<img src=\"/image/dy-cover.jpg\"></img>";
        html += "<br /><p>应邓总要求加入成绩单</p>";
        html += "<img src=\"/image/dy-cet.jpg\"></img>";
        return html;
    }
}
