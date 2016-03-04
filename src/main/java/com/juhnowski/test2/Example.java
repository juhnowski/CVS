package com.juhnowski.test2;

import com.juhnowski.test2.cvs.Author;
import com.juhnowski.test2.cvs.Date;
import com.juhnowski.test2.cvs.Description;
import com.juhnowski.test2.cvs.HasChanges;
import com.juhnowski.test2.cvs.Version;

@Author("Ilya")
@Date(day = 1, month = 1,year = 2016)
@Version(version = 1)
@Description(description = "initial") 
public class Example {
    
    @HasChanges
    public static void hello(){
        System.out.println("Hello world!");
    }
}
