import org.springframework.batch.item.ItemProcessor;

import com.payments.paymentsystem.model.AccountTransaction;

public class AccountProcessor  implements ItemProcessor <AccountTransaction, AccountTransaction>{
	
	@Override
	public AccountTransaction process(AccountTransaction item ) throws Exception{
		
		return item; 
		
	}

	
	
	

}
