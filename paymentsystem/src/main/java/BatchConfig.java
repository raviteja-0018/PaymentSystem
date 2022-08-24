
import javax.activation.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;

import com.payments.paymentsystem.model.AccountTransaction;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcCursorItemReader<AccountTransaction> reader()
	{
		JdbcCursorItemReader<AccountTransaction> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource((javax.sql.DataSource) dataSource);
		cursorItemReader.setSql("Select person_id, first_name, last_name, email, age, From person");
		cursorItemReader.setRowMapper(new AccountRowMapper());
		return cursorItemReader;
	}
	
	@Bean
	public AccountProcessor processor()
	{
		return new AccountProcessor();
	}
	
	
	@Bean
	public FlatFileItemWriter<AccountTransaction> writer() {
		
		FlatFileItemWriter<AccountTransaction> writer = new FlatFileItemWriter<AccountTransaction>();	
	writer.setResource(new ClassPathResource("transctions.csv"));
	DelimitedLineAggregator<AccountTransaction> lineAggregator = new DelimitedLineAggregator<AccountTransaction>();
	lineAggregator.setDelimiter(",");
	
	BeanWrapperFieldExtractor<AccountTransaction> fieldExtractor = new BeanWrapperFieldExtractor<AccountTransaction>() ; 
	
	fieldExtractor.setNames(new String[]{"sequence_id", "amount", "debit"});
	lineAggregator.setFieldExtractor(fieldExtractor);
	
	writer.setLineAggregator(lineAggregator);
	return writer;
	
	}
	
	@Bean
	public Step step1()
	{
		return (Step) stepBuilderFactory.get("step1").<AccountTransaction, AccountTransaction>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public Job exportAccountJob()
	{
		return jobBuilderfactory.get("exportAccountJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
