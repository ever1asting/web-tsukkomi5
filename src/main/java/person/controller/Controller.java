package person.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/", ""})
public class Controller {
    @ResponseBody
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index() {
        return "<p>Welcome to tsukkomi5</p>" +
                "<p>欢迎来到五班吐槽团主页</p>" +
                "<br /><a href=\"/flash\">点我观看flash列表</a>";
    }
}
