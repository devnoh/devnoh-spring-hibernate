package devnoh.demoapp.controller;

import devnoh.demoapp.model.*;
import devnoh.demoapp.service.*;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class IndexController {

    private static final Logger logger = LogManager.getLogger(IndexController.class);

    @Autowired
    private DemoService demoService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping("/")
    public String index() {
        logger.debug("index()...");
        return "index";
    }

    @RequestMapping("/depts")
    public String depts(ModelMap model) {
        logger.debug("depts()...");
        List<Department> depts = demoService.getAllDepartments();
        model.addAttribute("depts", depts);
        return "depts";
    }

    @RequestMapping("/emps")
    public String emps(@RequestParam(value="deptNo", required=false, defaultValue = "0") int deptNo, ModelMap model) {
        logger.debug("emps()...");
        List<Employee> emps = (deptNo == 0) ? demoService.getAllEmployees() : demoService.findEmployeesByDeptNo(deptNo);
        model.addAttribute("emps", emps);
        model.addAttribute("deptNo", deptNo);
        return "emps";
    }
}
