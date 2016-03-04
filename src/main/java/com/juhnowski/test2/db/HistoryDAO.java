package com.juhnowski.test2.db;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class HistoryDAO extends DAOTemplate<History> {

    public HistoryDAO() {
        super();
        typeParameterClass = History.class;
    }

    public HistoryDAO(Class<History> typeParameterClass) {
        super(typeParameterClass);
    }

    public History createHistory(int version, Date changeDate, String author, String description, String methods) {

        int hashcode = getHashCode(version, changeDate, author, description, methods);
        History added = new History(version, changeDate, author, description, methods, hashcode);
        added.setHashcode(hashcode);

        ArrayList<History> histList = getByHash(hashcode);
        if (histList.size() > 0) {
            update(added);
        } else {
            add(added);
        }

        return added;
    }

    public ArrayList<History> getByHash(int hashcode) {
        return getByField("hashcode", hashcode);
    }

    public History updateHistory(long id, int version, Date changeDate, String author, String description, String methods) {
        History updated = getById(id);
        int hashcode = getHashCode(version, changeDate, author, description, methods);

        if (updated.getHashcode() != hashcode) {
            updated.setVersion(version);
            updated.setChangedate(changeDate);
            updated.setAuthor(author);
            updated.setDescription(description);
            updated.setMethods(methods);
            updated.setHashcode(hashcode);
            update(updated);
        }
        
        return updated;
    }

    private int getHashCode(int version, Date changeDate, String author, String description, String methods) {
        String strToHash = version + changeDate.toString() + author + description + methods;
        return strToHash.hashCode();
    }
    
    public History getHistory(long id){
        return getById(id);
    }
}
