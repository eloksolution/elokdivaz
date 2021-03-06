package in.eloksolutions.evas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.services.CompanyService;

@SpringBootApplication
public class Application {
	public static Map<String, Company> companyCodeMap=new HashMap<>();
	public static Map<Integer, Company> companyIdMap=new HashMap<>();
    public static void main(String[] args) {
    	ApplicationContext ctx=SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            loadCompanies(ctx);
            System.out.println("Companies Codes "+companyCodeMap);
            System.out.println("Companies Ids "+companyIdMap);
                
        };
    }

	private void loadCompanies(ApplicationContext ctx) {
		CompanyService companyService =(CompanyService)ctx.getBean("companyService");
		List<Company> companies=companyService.findAll(null);
		loadCompanies(companies);
	}

	public static void loadCompanies(List<Company> companies) {
		companies.forEach(company->{
			companyCodeMap.put(company.getCode(),company);
			companyIdMap.put(company.getId(),company);
		});
	}

	public static Company getCompany(String code) {
		return companyCodeMap.get(code);
	}

	public static Company getCompanyId(Integer id) {
		return companyIdMap.get(id);
	}
}
