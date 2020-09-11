package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.CustomizableLayout;
import org.audit4j.core.layout.Layout;
import org.audit4j.core.layout.SimpleLayout;
import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.AuditAspect;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AuditConfig {

    @Bean
    public AuditAspect auditAspect() {
        AuditAspect auditAspect = new AuditAspect();
        return auditAspect;
    }

    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
    /*@Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler dbHandler = new DatabaseAuditHandler();
        dbHandler.setEmbedded("true");
        return dbHandler;
    }*/


    private Map<String,String> getProperties() {
    	Map<String,String> properties = new HashMap<String,String>();

    	properties.put("log.file.location", ".");

    	return properties;
    }

    @Bean
    public FileAuditHandler fileAuditHandler() {
    	FileAuditHandler fileAuditHandler = new FileAuditHandler();

    	return fileAuditHandler;
    }
    @Bean
    public DatabaseAuditHandler databaseHandler() {
        DatabaseAuditHandler databaseHandler = new DatabaseAuditHandler();
        databaseHandler.setEmbedded("false");
        databaseHandler.setDb_user("root");
        databaseHandler.setDb_password("root");
        databaseHandler.setDb_url("jdbc:mysql://127.0.0.1:3306/audit?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true");
        databaseHandler.setDb_driver("com.mysql.cj.jdbc.Driver");
        return databaseHandler;
    }
    // If you want to load configurations from file
    // (resources/audit4j.conf.yaml),
    // comment below method
//    @Bean
//    public SpringAudit4jConfig springAudit4jConfig() {
//        SpringAudit4jConfig springAudit4jConfig = new SpringAudit4jConfig();
//        springAudit4jConfig.setLayout(new SimpleLayout());
//        List<Handler> handlers = new ArrayList<Handler>();
//        handlers.add(new ConsoleAuditHandler());
//
//        handlers.add(databaseHandler());
//        //handlers.add(fileAuditHandler());
//        springAudit4jConfig.setHandlers(handlers);
//        springAudit4jConfig.setMetaData(new AuditMetaData());
//        springAudit4jConfig.setProperties(getProperties());
//
//        springAudit4jConfig.setLayout(getLayout());
//
//        return springAudit4jConfig;
//    }

    private Layout getLayout() {

    	//CustomizableLayout layout = new CustomizableLayout();
    	//layout.setTemplate("${eventDate}|${uuid}|actor=${actor}|${action}|origin=${origin} => ${foreach fields field}${field.name} ${field.type}:${field.value}, ${end}");
    	//return layout;

    	return new SimpleLayout();
    }

}
