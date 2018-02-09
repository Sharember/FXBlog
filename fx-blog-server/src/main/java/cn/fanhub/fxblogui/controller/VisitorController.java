package cn.fanhub.fxblogui.controller;

import cn.fanhub.fxblogui.entity.Visitor;
import cn.fanhub.fxblogui.repository.VisitorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author chengfan
 * @date 2018-2-8 22:16:32
 */
@RequestMapping("/visit")
@RestController
public class VisitorController {
    @Resource
    private VisitorRepository visitorRepository;

    @GetMapping
    public String visit(HttpServletRequest request){
        Visitor visitor = new Visitor();
        visitor.setIp(request.getRemoteAddr());
        visitor.setVisitDate(new Date());

        visitorRepository.save(visitor);

        Long count = visitorRepository.count();

        return String.format("你是来自%s的第%d位访问者。",request.getRemoteAddr(),count);
    }

}