package com.juhnowski.test2.logic;

import com.juhnowski.test2.Example;
import com.juhnowski.test2.db.History;
import com.juhnowski.test2.db.HistoryDAO;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoryLogic {

    private volatile static HistoryLogic mHistoryLogic = getHistoryLogic();

    @Autowired
    HistoryDAO historyDAO;

    private HistoryLogic() {

    }

    public static HistoryLogic getHistoryLogic() {
        if (mHistoryLogic == null) {
            synchronized (HistoryLogic.class) {
                if (mHistoryLogic == null) {
                    mHistoryLogic = new HistoryLogic();
                }
            }
        }
        return mHistoryLogic;
    }

    public History createHistory(int version, Date changeDate, String author, String description, String methods) {
        return historyDAO.createHistory(version, changeDate, author, description, methods);
    }

    public History deleteHistory(long id) {
        History history = historyDAO.getById(id);
        if (history != null) {
            historyDAO.delete(id);
        }
        return history;
    }
    
    public ArrayList<History> getAll(){
        return historyDAO.getAll();
    }
    
    public History getHistory(Long id){
        return historyDAO.getHistory(id);
    }
    
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
}
