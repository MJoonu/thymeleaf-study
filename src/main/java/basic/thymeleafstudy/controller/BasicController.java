package basic.thymeleafstudy.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    // text, uText

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "text");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>text!</b>");
        return "basic/text-unescaped";
    }

    // text, uText end

    // Spring EL
    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("basic-object")
    public String basicObject(HttpSession session) {
        session.setAttribute("sessionData", "new session");
        return "basic/basic-object";
    }


    @Component("basicBean")
    static class NewBean {
        public String hello(String data) {
            return "hello " + data;
        }
    }
    // Spring EL end


    // DateTime
    @GetMapping("date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
    // datetime end

    // url link
    @GetMapping("link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }
    // url link end


    // literal
    @GetMapping("literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }
    // literal end

    // 연산
    @GetMapping("operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }
    // 연산 end


    // th 속성값 설정
    @GetMapping("/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }
    // th 속성값 설정 end


    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
