package com.juhnowski.test2.web;

import com.juhnowski.test2.Example;
import com.juhnowski.test2.db.History;
import com.juhnowski.test2.logic.HistoryLogic;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(HistoryController.HISTORY_URI)
public class HistoryController {

    public static final String HISTORY_URI = "/history";
    public static final String TEST_URI = "/test";
    public static final String GET_ALL_URI = "/getall";
    public static final String GET_URI = "/get/{id}";
    public static final String DELETE_URI = "/delete/{id}";
    public static final String ID_VAR = "id";
    
    @Autowired
    HistoryLogic historyLogic;

    @RequestMapping(value = TEST_URI, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String testCheck() {
        StringBuilder sb = new StringBuilder();
        try {
            Example example = new Example();
            Annotation[] classAnnotations = example.getClass().getAnnotations();

            sb.append(example.getClass().getName()).append(" annotations:").append("\n");
            for (Annotation annotation : classAnnotations) {
                sb.append("  ").append(annotation);
            }

            Method[] classMethods = example.getClass().getMethods();
            sb.append(" Method annotations:\n");
            for (Method method : classMethods) {
                Annotation[] methodAnnotations = method.getAnnotations();
                if (methodAnnotations.length > 0) {
                    sb.append("  ").append(method.getName()).append("():");
                }
                for (Annotation annotation : methodAnnotations) {
                    sb.append("    ").append(annotation);
                }
            }

        } catch (Exception exc) {
        }
        return sb.toString();
    }
    
    @RequestMapping(value = GET_ALL_URI, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<History> getAll(){
        return historyLogic.getAll();
    }
    
    @RequestMapping(value = GET_URI, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public History get(@PathVariable(ID_VAR) Long id){
        return historyLogic.getHistory(id);
    }
    
    @RequestMapping(value = DELETE_URI, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public History delete(@PathVariable(ID_VAR) Long id){
        return historyLogic.deleteHistory(id);
    }
    
    	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandler(Exception exception) {
		return exception.getMessage();
	}
}
