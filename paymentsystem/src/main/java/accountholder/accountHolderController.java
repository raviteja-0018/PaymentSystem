pack
age accountholder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.springframework.beans.factory.annotation.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



@Controller
public class accountHolderController {

	private long sequenceID;
	private String transactionReference;
	private int amount;
	private int debit;
	private String description;
	private int billReferenceNumber;
	
	
	
	
public long getSequenceID() {
		return sequenceID;
	}




	public void setSequenceID(long sequenceID) {
		this.sequenceID = sequenceID;
	}




	public String getTransactionReference() {
		return transactionReference;
	}




	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public int getDebit() {
		return debit;
	}




	public void setDebit(int debit) {
		this.debit = debit;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public int getBillReferenceNumber() {
		return billReferenceNumber;
	}




	public void setBillReferenceNumber(int billReferenceNumber) {
		this.billReferenceNumber = billReferenceNumber;
	}




@RequestMapping("/export/csv")
public void exportToCsv(HttpServletResponse response ) throws IOException
{
	response.setContentType("text/csv");
	String fileName = "users.csv";
	String headerKey = "Content-Disposition";
	String headervalue = "attachment; filename=" + fileName;
	response.setHeader(headerKey, headervalue);
	
			
			
	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	String[] csvHeader = {"User ID", "E-mail" };
	String[] nameMapping = {"id", "email"};
	
	csvWriter.writeHeader(csvHeader);
	
	csvWriter.close();
}

	
}
