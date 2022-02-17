package com.revature;

import org.apache.log4j.BasicConfigurator;

public class ReimbursementDriver {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        JavalinApp app = new JavalinApp();
        app.start(7070);
        System.out.println("testing Jenkins/GCP/Docker");
    }

}
