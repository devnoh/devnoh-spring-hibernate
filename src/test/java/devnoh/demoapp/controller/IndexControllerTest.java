package devnoh.demoapp.controller;

import devnoh.demoapp.config.*;
import devnoh.demoapp.model.*;
import devnoh.demoapp.service.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;
import org.springframework.ui.*;

import java.util.*;

import static org.mockito.Mockito.*;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTest {

    @Mock
    DemoService demoService;

    @InjectMocks
    IndexController indexController;

    @Spy
    ModelMap model;

    @Spy
    List<Department> departments = new ArrayList<Department>();

    @Spy
    List<Employee> employees = new ArrayList<Employee>();

    @Spy
    List<Employee> deptEmployees = new ArrayList<Employee>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIndex() {
        Assert.assertEquals(indexController.index(), "index");
    }

    @Test
    public void testDepts() {
        when(demoService.getAllDepartments()).thenReturn(departments);
        Assert.assertEquals(indexController.depts(model), "depts");
        Assert.assertEquals(model.get("depts"), departments);
        verify(demoService, times(1)).getAllDepartments();
    }

    @Test
    public void testEmps() {
        when(demoService.getAllEmployees()).thenReturn(employees);
        Assert.assertEquals(indexController.emps(0, model), "emps");
        Assert.assertEquals(model.get("emps"), employees);
        Assert.assertEquals(model.get("deptNo"), 0);
        verify(demoService, times(1)).getAllEmployees();

        when(demoService.findEmployeesByDeptNo(10)).thenReturn(deptEmployees);
        Assert.assertEquals(indexController.emps(10, model), "emps");
        Assert.assertEquals(model.get("emps"), deptEmployees);
        Assert.assertEquals(model.get("deptNo"), 10);
        verify(demoService, times(1)).findEmployeesByDeptNo(10);
    }

}
