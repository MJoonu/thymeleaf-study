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
import java.util.*;

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

    // 반복
    @GetMapping("/each")
    public String each(Model model) {
        addUser(model);
        return "basic/each";
    }
    // 반복 end

    // 조건부 평가
    @GetMapping("/condition")
    public String condition(Model model) {
        addUser(model);
        return "basic/condition";
    }
    // 조건부 평가 end

    //주석
    @GetMapping("comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }
    // 주석 end

    // block (그룹화)
    @GetMapping("block")
    public String block(Model model) {
        addUser(model);
        return "basic/block";
    }// block (그룹화) end

    // 자바스크립트 인라인
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUser(model);
        return "basic/javascript";
    }// 자바스크립트 인라인 end


    private void addUser(Model model) {

        ArrayList<Object> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }

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
