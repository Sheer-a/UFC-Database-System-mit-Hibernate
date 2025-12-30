package db2.ufc;

import db2.ufc.util.CRUDProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("db2.ufc.util")
@EnableTransactionManagement

public class Db2UfcApplication {

    private final CRUDProgram crudProgram;

    public Db2UfcApplication(CRUDProgram crudProgram) {
        this.crudProgram = crudProgram;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Db2UfcApplication.class, args);
        CRUDProgram crudProgram = context.getBean(CRUDProgram.class);
        crudProgram.run();
    }

}
