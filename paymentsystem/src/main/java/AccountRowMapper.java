import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.payments.paymentsystem.model.AccountTransaction;

public class AccountRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		AccountTransaction at= new AccountTransaction();
		at.setSequenceId(rs.getLong("sequence_id"));
		at.setAmount(rs.getInt("amount"));
		at.setDebit(rs.getInt("debit"));
		return null;
	}

}
